import React, { useState, useEffect } from "react";

import axiosInstance from "../../api/axiosInstance"; // ✅ fixed import
import { useNavigate } from "react-router-dom";

export default function TicketList() {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    axiosInstance
      .get("/tickets")
      .then((res) => {
        setTickets(res.data);
        setLoading(false);
      })
      .catch((err) => {
        console.error("Error fetching tickets:", err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading tickets...</p>;

  return (
    <></>
  );
}
