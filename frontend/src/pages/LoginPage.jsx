import React, { useState } from "react";
import { login } from "../services/authService";
import { toast } from "react-toastify";

const LoginPage = () => {
  const [data, setData] = useState({ email: "", password: "" });

  const handleChange = (e) => setData({ ...data, [e.target.name]: e.target.value });

  const handleSubmit = (e) => {
    e.preventDefault();
    login(data)
      .then((res) => {
        localStorage.setItem("token", res.token);
        toast.success("Logged in successfully!");
      })
      .catch(() => toast.error("Invalid email or password."));
  };

  return (
    <div>
      <h2>Login</h2>
      <form onSubmit={handleSubmit}>
        <input type="email" name="email" placeholder="Email" value={data.email} onChange={handleChange} required />
        <input type="password" name="password" placeholder="Password" value={data.password} onChange={handleChange} required />
        <button type="submit">Login</button>
      </form>
    </div>
  );
};

export default LoginPage;