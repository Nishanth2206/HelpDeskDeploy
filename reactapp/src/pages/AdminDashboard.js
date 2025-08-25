import React, { useEffect, useState } from "react";

import axiosInstance from "../api/axiosInstance";

export default function AdminDashboard() {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchTickets = async () => {
      try {
        const res = await axiosInstance.get("/tickets"); // fetch all tickets
        setTickets(res.data);
      } catch (err) {
        console.error("Error fetching tickets:", err);
      } finally {
        setLoading(false);
      }
    };

    fetchTickets();
  }, []);

  return (
   <></>
  );
}
