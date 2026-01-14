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
            .then(res => setCourses(res.data))
            .catch(() => setMessage("Failed to load courses"));
    }, []);


    const enrollCourse = async (courseId) => {

        const token = localStorage.getItem("token");

        try {

            await axios.post(
                `http://localhost:8080/api/enroll/${courseId}`,
                {},
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            setMessage("Enrolled Successfully ✔️");

        } catch (err) {
            setMessage("Enrollment failed ❌");
        }

    };

    return (
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
            {courses.map(course => (
                <div key={course.id} className="bg-white p-4 rounded shadow">
                    <h3 className="font-bold text-lg">{course.title}</h3>
                    <p className="text-gray-600">{course.description}</p>

                    <button
                        onClick={() => enrollCourse(course.id)}
                        className="mt-3 bg-green-600 text-white px-3 py-1 rounded"
                    >
                        Enroll
                    </button>
                </div>
            ))}
        </div>

    );
}

export default Courses;
