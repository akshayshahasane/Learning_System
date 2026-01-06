import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./Login";
import Dashboard from "./Dashboard";

function App() {

    const token = localStorage.getItem("token");

    return (
        <Routes>

            {/* default redirect */}
            <Route path="/" element={<Navigate to="/login" />} />

            <Route path="/login" element={<Login />} />

            {/* Protected Route */}
            <Route
                path="/dashboard"
                element={ token ? <Dashboard /> : <Navigate to="/login" /> }
            />

        </Routes>
    );
}

export default App;
