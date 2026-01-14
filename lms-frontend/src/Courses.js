import { useEffect, useState } from "react";
import axios from "axios";

function Courses() {

    const [courses, setCourses] = useState([]);
    const [message, setMessage] = useState("");

    useEffect(() => {

        const token = localStorage.getItem("token");

        axios.get("http://localhost:8080/api/courses", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(res => {
                setCourses(res.data);
            })
            .catch(() => {
                setMessage("Failed to load courses");
            });

    }, []);

    return (
        <div>
            <h2>Available Courses</h2>

            {message && <p>{message}</p>}

            <ul>
                {courses.map(course => (
                    <li key={course.id}>
                        {course.title} — {course.description}
                    </li>
                ))}
            </ul>

        </div>
    );
}

export default Courses;
