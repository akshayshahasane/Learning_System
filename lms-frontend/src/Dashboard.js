function Dashboard() {

    const token = localStorage.getItem("token");

    return (
        <div>
            <h2>Student Dashboard</h2>

            <p>You are logged in ✔️</p>

            <p><b>JWT Token:</b></p>
            <p>{token}</p>

        </div>
    );
}

export default Dashboard;
