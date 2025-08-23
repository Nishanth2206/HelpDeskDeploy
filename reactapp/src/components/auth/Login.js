import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";
import { TextField, Button, Box, Typography, Paper } from "@mui/material";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
  headers: { "Content-Type": "application/json" }
});

export default function Login() {
  const navigate = useNavigate();
  const [form, setForm] = useState({ email: "", password: "" });
  const [msg, setMsg] = useState("");

  const handleChange = (e) => setForm({ ...form, [e.target.name]: e.target.value });

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMsg("");

    try {
      const res = await axiosInstance.post("/api/auth/login", form);

      const user = {
        name: res.data.name,
        email: res.data.email,
        role: res.data.role
      };

      localStorage.setItem("user", JSON.stringify(user));
      localStorage.setItem("token", "dummy");

      setMsg(res.data.message || "Login successful");
      navigate(`/${user.role.toLowerCase()}`, { replace: true });

    } catch (err) {
      console.log(err.response);
      setMsg(err.response?.data?.message || "Login failed. Please try again.");
    }
  };

  return (
    <Box
      sx={{
        display: "flex",
        justifyContent: "center",
        alignItems: "center",
        minHeight: "100vh",
        bgcolor: "linear-gradient(135deg, #efeaeaff 0%, #030580ff 100%)"
      }}
    >
      <Paper
        elevation={3}
        sx={{
          p: 4,
          width: 400,
          borderRadius: 3,
          background: "linear-gradient(145deg, #eae1e7ff, #f1e6eaff)",
          textAlign: "center"
        }}
      >
        <Typography
          variant="h5"
          gutterBottom
          sx={{ color: "#602ac4ff", fontWeight: "bold" }}
        >
          Login
        </Typography>

        {msg && (
          <Typography
            sx={{ mt: 2 }}
            color={msg.toLowerCase().includes("success") ? "#422caeff" : "#d50000"}
          >
            {msg}
          </Typography>
        )}

        <form onSubmit={handleSubmit}>
          <TextField
            fullWidth
            margin="normal"
            label="Email"
            type="email"
            name="email"
            value={form.email}
            onChange={handleChange}
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
            fullWidth
            margin="normal"
            label="Password"
            type="password"
            name="password"
            value={form.password}
            onChange={handleChange}
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
          <Button
            type="submit"
            variant="contained"
            fullWidth
            sx={{
              mt: 2,
              background: "linear-gradient(45deg, #4d25c2ff, #1a14ceff)",
              color: "white",
              fontWeight: "bold",
              "&:hover": { background: "linear-gradient(45deg, #4615afff, #511ab8ff)" }
            }}
          >
            Login
          </Button>
        </form>

        <Typography sx={{ mt: 2, color: "#06010aff" }}>
          Don’t have an account?{" "}
          <Link to="/signup" style={{ color: "#0f2fe6ff", fontWeight: "bold" }}>
            Sign up
          </Link>
        </Typography>
      </Paper>
    </Box>
  );
}
