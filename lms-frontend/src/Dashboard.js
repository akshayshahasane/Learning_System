import { Link, useNavigate } from "react-router-dom";

function Dashboard() {

    const navigate = useNavigate();
    const role = localStorage.getItem("role");

    const handleLogout = () => {
        localStorage.clear();
        navigate("/login");
    };

    return (
        <div className="min-h-screen bg-gray-100 p-6">
            <div className="max-w-xl mx-auto bg-white p-6 rounded shadow">
                <h2 className="text-xl font-bold mb-4">Dashboard</h2>
                <p className="mb-4">Role: {role}</p>

                <div className="flex gap-3 flex-wrap">
                    {role === "STUDENT" && (
                        <>
                            <Link to="/courses" className="btn">Courses</Link>
                            <Link to="/my-courses" className="btn">My Courses</Link>
                        </>
                    )}

                    {role === "ADMIN" && (
                        <Link to="/add-course" className="btn">Add Course</Link>
                    )}
                </div>

                <button
                    onClick={handleLogout}
                    className="mt-6 bg-red-500 text-white px-4 py-2 rounded"
                >
                    Logout
                </button>
            </div>
        </div>
    );

}

export default Dashboard;
