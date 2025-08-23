import React, { useState } from "react";
import axios from "axios";
import { useNavigate, Link } from "react-router-dom";


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
   <></>
  );
}
