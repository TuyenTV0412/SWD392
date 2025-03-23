<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gửi Phản Hồi</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding: 20px;
        }
        .container {
            max-width: 500px;
            background: white;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            margin: auto;
        }
        h2 {
            text-align: center;
            color: #333;
        }
        label {
            font-weight: bold;
            display: block;
            margin-top: 10px;
        }
        textarea {
            width: 100%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .star-rating {
            display: flex;
            justify-content: center;
            gap: 5px;
            margin-top: 10px;
        }
        .star {
            font-size: 24px;
            cursor: pointer;
            color: #ccc;
        }
        .star.checked {
            color: gold;
        }
        .submit-btn {
            background-color: #28a745;
            color: white;
            border: none;
            padding: 10px;
            width: 100%;
            border-radius: 4px;
            margin-top: 15px;
            cursor: pointer;
        }
        .submit-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

    <div class="container">
        <h2>Gửi Phản Hồi</h2>
        <form action="feedback" method="post">
            <input type="hidden" name="tourID" value="${tourID}">
            <input type="hidden" name="userID" value="${sessionScope.UserID}">
            <label for="rating">Đánh Giá:</label>
            <div class="star-rating">
                <span class="star" data-value="1">&#9733;</span>
                <span class="star" data-value="2">&#9733;</span>
                <span class="star" data-value="3">&#9733;</span>
                <span class="star" data-value="4">&#9733;</span>
                <span class="star" data-value="5">&#9733;</span>
            </div>
            <input type="hidden" id="rating" name="rating" value="5"> 

            <label for="message">Phản hồi:</label>
            <textarea id="message" name="message" rows="4" ></textarea>

            <button type="submit" class="submit-btn">Gửi Phản Hồi</button>
        </form>
    </div>

    <script>
        document.addEventListener("DOMContentLoaded", function() {
            const stars = document.querySelectorAll(".star");
            const ratingInput = document.getElementById("rating");

            stars.forEach(star => {
                star.addEventListener("click", function() {
                    let value = this.getAttribute("data-value");
                    ratingInput.value = value;

                    stars.forEach(s => s.classList.remove("checked"));
                    for (let i = 0; i < value; i++) {
                        stars[i].classList.add("checked");
                    }
                });
            });
        });
    </script>

</body>
</html>
