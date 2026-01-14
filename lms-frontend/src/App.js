import { Routes, Route, Navigate } from "react-router-dom";
import Login from "./Login";
import Dashboard from "./Dashboard";
import Register from "./Register";
import Courses from "./Courses";
import AddCourse from "./AddCourse";
import MyCourses from "./MyCourses";





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
            <Route path="/register" element={<Register />} />

            <Route
                path="/courses"
                element={ token ? <Courses /> : <Navigate to="/login" /> }
            />

            <Route
                path="/add-course"
                element={
                    token && localStorage.getItem("role") === "ADMIN"
                        ? <AddCourse />
                        : <Navigate to="/dashboard" />
                }
            />

            <Route
                path="/my-courses"
                element={ token ? <MyCourses /> : <Navigate to="/login" /> }
            />




        </Routes>




    );
}

export default App;
