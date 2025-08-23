import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import {
  TextField,
  Button,
  Box,
  Typography,
  Paper,
  MenuItem
} from "@mui/material";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: { "Content-Type": "application/json" }
});

export default function Signup() {
  const navigate = useNavigate();
  const [form, setForm] = useState({
    name: "",
    email: "",
    password: "",
    role: "EMPLOYEE"
  });
  const [msg, setMsg] = useState("");

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSignup = async (e) => {
    e.preventDefault();
    setMsg("");

    try {
      const res = await axiosInstance.post("/api/auth/signup", form);
      setMsg(res.data.message || "Signup successful!");
      setTimeout(() => navigate("/login"), 1500);
    } catch (err) {
      console.log(err.response);
      if (err.response?.data?.message) {
        setMsg(err.response.data.message);
      } else if (err.response?.data?.errors) {
        setMsg(Object.values(err.response.data.errors).join(", "));
      } else {
        setMsg("Signup failed. Please try again.");
      }
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        bgcolor: "linear-gradient(135deg, #c77dff 0%, #ff80bf 100%)"
      }}
    >
      <Paper
        elevation={6}
        sx={{
          p: 4,
          borderRadius: 3,
          width: 400,
          textAlign: "center",
          background: "linear-gradient(145deg, #f3e5f5, #fce4ec)"
        }}
      >
        <Typography
          variant="h5"
          gutterBottom
          sx={{ color: "#8e24aa", fontWeight: "bold" }}
        >
          Signup
        </Typography>

        {msg && (
          <Typography
            sx={{ mb: 2 }}
            color={msg.toLowerCase().includes("success") ? "#4a148c" : "#d50000"}
          >
            {msg}
          </Typography>
        )}

        <Box
          component="form"
          onSubmit={handleSignup}
          sx={{ display: "flex", flexDirection: "column", gap: 2 }}
        >
          <TextField
            label="Name"
            name="name"
            value={form.name}
            onChange={handleChange}
            fullWidth
            required
            sx={{
              "& .MuiInputLabel-root": { color: "#6a1b9a" },
              "& .MuiOutlinedInput-root": {
                "& fieldset": { borderColor: "#ba68c8" },
                "&:hover fieldset": { borderColor: "#8e24aa" },
                "&.Mui-focused fieldset": { borderColor: "#6a1b9a" }
              }
            }}
          />
          <TextField
            label="Email"
            type="email"
            name="email"
            value={form.email}
            onChange={handleChange}
            fullWidth
            required
            sx={{
              "& .MuiInputLabel-root": { color: "#6a1b9a" },
              "& .MuiOutlinedInput-root": {
                "& fieldset": { borderColor: "#ba68c8" },
                "&:hover fieldset": { borderColor: "#8e24aa" },
                "&.Mui-focused fieldset": { borderColor: "#6a1b9a" }
              }
            }}
          />
          <TextField
            label="Password"
            type="password"
            name="password"
            value={form.password}
            onChange={handleChange}
            fullWidth
            required
            helperText="Password must be at least 6 characters"
            sx={{
              "& .MuiInputLabel-root": { color: "#6a1b9a" },
              "& .MuiOutlinedInput-root": {
                "& fieldset": { borderColor: "#ba68c8" },
                "&:hover fieldset": { borderColor: "#8e24aa" },
                "&.Mui-focused fieldset": { borderColor: "#6a1b9a" }
              }
            }}
          />
          <TextField
            select
            label="Role"
            name="role"
            value={form.role}
            onChange={handleChange}
            fullWidth
            sx={{
              "& .MuiInputLabel-root": { color: "#6a1b9a" },
              "& .MuiOutlinedInput-root": {
                "& fieldset": { borderColor: "#ba68c8" },
                "&:hover fieldset": { borderColor: "#8e24aa" },
                "&.Mui-focused fieldset": { borderColor: "#6a1b9a" }
              }
            }}
          >
            <MenuItem value="EMPLOYEE">Employee</MenuItem>
            <MenuItem value="AGENT">Agent</MenuItem>
            <MenuItem value="ADMIN">Admin</MenuItem>
          </TextField>
          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{
              background: "linear-gradient(45deg, #8e24aa, #ff4081)",
              color: "white",
              fontWeight: "bold",
              "&:hover": {
                background: "linear-gradient(45deg, #6a1b9a, #f50057)"
              }
            }}
          >
            Signup
          </Button>
        </Box>

        <Typography sx={{ mt: 3, color: "#6a1b9a" }}>
          Already have an account? <Link to="/login" style={{ color: "#8e24aa", fontWeight: "bold" }}>Login</Link>
        </Typography>
      </Paper>
    </Box>
  );
}
