import React, { useEffect, useState } from "react";

import { getUser } from "../../utils/auth";
import axiosInstance from "../../api/axiosInstance";

const STATUSES = ["OPEN", "IN_PROGRESS", "RESOLVED", "CLOSED"];

export default function AgentDashboard() {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(false);
  const [open, setOpen] = useState(false);
  const [viewMode, setViewMode] = useState(false); // 👈 for view-only modal
  const [selectedTicket, setSelectedTicket] = useState(null);
  const [newStatus, setNewStatus] = useState("");
  const [comment, setComment] = useState("");
  const [snackbar, setSnackbar] = useState({ open: false, message: "", severity: "success" });

  const user = getUser();
  const userName = user?.name || "Agent";
  const userEmail = user?.email || "";

  const loadTickets = async () => {
    if (!userEmail) return;
    try {
      setLoading(true);
      const res = await axiosInstance.get(`/tickets?assignedAgent=${userEmail}`);
      setTickets(Array.isArray(res.data) ? res.data : []);
    } catch (err) {
      console.error(err);
      showSnackbar("Failed to load tickets. Try again.", "error");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadTickets();
    // eslint-disable-next-line
  }, [userEmail]);

  const handleOpen = (ticket, isView = false) => {
    setSelectedTicket(ticket);
    setNewStatus(ticket.status || "");
    setComment("");
    setViewMode(isView);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    setSelectedTicket(null);
    setViewMode(false);
  };

  const showSnackbar = (message, severity = "success") => {
    setSnackbar({ open: true, message, severity });
  };

  const handleUpdate = async () => {
    if (!selectedTicket) return;
    try {
      await axiosInstance.put(`/tickets/${selectedTicket.id}/status`, {
        status: newStatus,
        comment: comment || null,
      });
      showSnackbar("Ticket updated successfully!");
      handleClose();
      await loadTickets();
    } catch (err) {
      console.error("Update ticket error:", err.response?.data || err.message);
      const message = err.response?.data?.message || "Failed to update ticket.";
      showSnackbar(message, "error");
    }
  };

  const handleDelete = async (ticketId) => {
    if (!window.confirm("Are you sure you want to delete this ticket?")) return;
    try {
      await axiosInstance.delete(`/tickets/${ticketId}`);
      showSnackbar("Ticket deleted successfully!", "success");
      await loadTickets();
    } catch (err) {
      console.error("Delete ticket error:", err.response?.data || err.message);
      const message = err.response?.data?.message || "Failed to delete ticket.";
      showSnackbar(message, "error");
    }
  };

  return (
    
  );
}
