import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";


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
    <></>
  );
}
