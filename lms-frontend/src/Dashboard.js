import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";



function Dashboard() {

    const navigate = useNavigate();

    const token = localStorage.getItem("token");

    const handleLogout = () => {
        localStorage.removeItem("token");
        navigate("/login");
    };

    return (
        <div>
            <h2>Student Dashboard</h2>

            <p>You are logged in ✔️</p>

            <button onClick={handleLogout}>Logout</button>

            {/*<p><b>Your JWT:</b></p>*/}
            {/*<small>{token}</small>*/}

            <Link to="/courses">
                <button>View Courses</button>
            </Link>



        </div>
    );

}

export default Dashboard;
