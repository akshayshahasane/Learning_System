import { useEffect, useState } from "react";
import axios from "axios";

function MyCourses() {

    const [courses, setCourses] = useState([]);
    const [message, setMessage] = useState("");

    useEffect(() => {

        const token = localStorage.getItem("token");

        axios.get("http://localhost:8080/api/enroll/my", {
            headers: {
                Authorization: `Bearer ${token}`
            }
        })
            .then(res => {
                setCourses(res.data);
            })
            .catch(() => {
                setMessage("No enrolled courses found");
            });

    }, []);

    return (
        <div>
            <h2>My Enrolled Courses</h2>

            {message && <p>{message}</p>}

            <ul>
                {courses.map(course => (
                    <li key={course.id}>
                        <b>{course.title}</b> — {course.description}
                    </li>
                ))}
            </ul>

        </div>
    );
}

export default MyCourses;
