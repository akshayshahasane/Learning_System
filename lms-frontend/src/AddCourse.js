import { useState } from "react";
import axios from "axios";

function AddCourse() {

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [message, setMessage] = useState("");

    const handleAddCourse = async (e) => {
        e.preventDefault();

        const token = localStorage.getItem("token");

        try {
            await axios.post(
                "http://localhost:8080/api/courses",
                { title, description },
                {
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                }
            );

            setMessage("Course added successfully ✔️");
            setTitle("");
            setDescription("");

        } catch (err) {
            setMessage("Failed to add course ❌");
        }
    };

    return (
        <div>
            <h2>Add Course (Admin)</h2>

            <form onSubmit={handleAddCourse}>
                <input
                    placeholder="Course title"
                    value={title}
                    onChange={(e)=>setTitle(e.target.value)}
                /><br/>

                <input
                    placeholder="Description"
                    value={description}
                    onChange={(e)=>setDescription(e.target.value)}
                /><br/>

                <button type="submit">Add Course</button>
            </form>

            <p>{message}</p>
        </div>
    );
}

export default AddCourse;
