import React, { useState } from "react";
import { register } from "../services/authService";
import { toast } from "react-toastify";

const Signup = () => {
  const [data, setData] = useState({
    firstName: "",
    lastName: "",
    email: "",
    password: "",
    address: "",
    zipCode: "",
    city: "",
    country: "",
  });
  const handleChange = (event) => {
    setData({ ...data, [event.target.name]: event.target.value });
  };
  const handleSubmit = (event) => {
    event.preventDefault();
    signup(data)
      .then(() => {
        localStorage.setItem("token", res.token);
        toast.success("Customer Registered Successfully!");
        setData({ firstName: "", lastName: "", email: "", password: "", address: "", zipCode: "", city: "", country: ""});
      })
      .catch(() => toast.error("Registration Failed. Try Again!"));
  };
  return (
    <div>
      <h2>Signup</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" name="firstName" placeholder="First Name" value={data.firstName} onChange={handleChange} required />
        <input type="text" name="lastName" placeholder="Last Name" value={data.lastName} onChange={handleChange} required />
        <input type="email" name="email" placeholder="Email" value={data.email} onChange={handleChange} required />
        <input type="password" name="password" placeholder="Password" value={data.password} onChange={handleChange} required />
        <input type="text" name="address" placeholder="Address" value={data.address} onChange={handleChange} required />
        <input type="text" name="zipCode" placeholder="Zip code" value={data.zipCode} onChange={handleChange} required />
        <input type="city" name="city" placeholder="City" value={data.city} onChange={handleChange} required />
        <input type="country" name="country" placeholder="Country" value={data.country} onChange={handleChange} required />
        <button type="submit">Register</button>
      </form>
    </div>
  );
};
export default Signup;