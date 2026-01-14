import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";



function Login() {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [message, setMessage] = useState("");
    const navigate = useNavigate();


    const handleLogin = async (e) => {
        e.preventDefault();

        try {
            const response = await axios.post("http://localhost:8080/api/auth/login", {
                email: email,
                password: password
            });

            setMessage("Login success");

            localStorage.setItem("token", response.data.token);
            localStorage.setItem("role", response.data.role);
            navigate("/dashboard");


            // save token
            localStorage.setItem("token", response.data.token);

            // redirect to dashboard
            navigate("/dashboard");

        } catch (error) {
            setMessage("Invalid email or password");
        }
    };

    return (
        <div className="min-h-screen flex items-center justify-center bg-gray-100">
            <div className="bg-white p-6 rounded-lg shadow-md w-96">
                <h2 className="text-2xl font-bold mb-4 text-center">Login</h2>

                <form onSubmit={handleLogin} className="space-y-3">
                    <input
                        className="w-full border p-2 rounded"
                        type="email"
                        placeholder="Email"
                        value={email}
                        onChange={(e)=>setEmail(e.target.value)}
                    />

                    <input
                        className="w-full border p-2 rounded"
                        type="password"
                        placeholder="Password"
                        value={password}
                        onChange={(e)=>setPassword(e.target.value)}
                    />

                    <button className="w-full bg-blue-600 text-white py-2 rounded hover:bg-blue-700">
                        Login
                    </button>
                </form>

                <p className="text-center text-red-500 mt-2">{message}</p>
            </div>
        </div>
    );

}

export default Login;
