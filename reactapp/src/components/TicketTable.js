import React, { useEffect, useState } from "react";

import { useNavigate } from "react-router-dom";
import axiosInstance from "../../api/axiosInstance";

export default function TicketTable() {
  const [tickets, setTickets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const navigate = useNavigate();

  useEffect(() => {
    const fetchTickets = async () => {
      try {
        const res = await axiosInstance.get("/tickets");
        setTickets(res.data);
      } catch (err) {
        setError("Failed to load tickets");
      } finally {
        setLoading(false);
      }
    };
    fetchTickets();
  }, []);

  if (loading) return <CircularProgress />;

  if (error) return <Typography color="error">{error}</Typography>;

  return (
    <Paper sx={{ padding: 2 }}>
      <Typography variant="h5" sx={{ mb: 2 }}>
        Ticket List
      </Typography>
      <Table>
        <TableHead>
          <TableRow>
            <TableCell><strong>ID</strong></TableCell>
            <TableCell><strong>Title</strong></TableCell>
            <TableCell><strong>Status</strong></TableCell>
            <TableCell><strong>Priority</strong></TableCell>
            <TableCell><strong>Actions</strong></TableCell>
          </TableRow>
        </TableHead>
        <TableBody>
          {tickets.map((ticket) => (
            <TableRow key={ticket.id}>
              <TableCell>{ticket.id}</TableCell>
              <TableCell>{ticket.title}</TableCell>
              <TableCell>{ticket.status}</TableCell>
              <TableCell>{ticket.priority}</TableCell>
              <TableCell>
                <Button
                  variant="contained"
                  size="small"
                  sx={{
                    textTransform: "none",
                    backgroundColor: "#1976d2",
                    borderRadius: "8px",
                    px: 2,
                    "&:hover": { backgroundColor: "#115293" },
                  }}
                  onClick={() => navigate(`/tickets/${ticket.id}`)}
                >
                  View
                </Button>
              </TableCell>
            </TableRow>
          ))}
        </TableBody>
      </Table>
    </Paper>
  );
}
