import { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";


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

            // save token
            localStorage.setItem("token", response.data.token);

            // redirect to dashboard
            navigate("/dashboard");

        } catch (error) {
            setMessage("Invalid email or password");
        }
    };

    return (
        <div>
            <h2>Login</h2>

            <form onSubmit={handleLogin}>

                <input
                    type="email"
                    placeholder="Enter email"
                    value={email}
                    onChange={(e)=>setEmail(e.target.value)}
                /><br/>

                <input
                    type="password"
                    placeholder="Enter password"
                    value={password}
                    onChange={(e)=>setPassword(e.target.value)}
                /><br/>

                <button type="submit">Login</button>
            </form>

            <p>{message}</p>
        </div>
    );
}

export default Login;
