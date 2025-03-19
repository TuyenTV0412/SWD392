<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Home Page</title>
        <link rel="stylesheet" href="styles.css"> <!-- Liên kết file CSS -->
        <style>
            /* General styles */
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            /* Header styles */
            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 20px;
                background-color: #007bff;
                color: #fff;
            }

            .header-left h1 {
                margin: 0;
            }

            .header-right a {
                margin-left: 15px;
                padding: 10px 20px;
                background-color: #0056b3;
                border-radius: 4px;
                color: #fff;
                text-decoration: none;
            }

            .header-right a:hover {
                background-color: #003d80;
            }

            /* Container styles */
            .container {
                padding: 20px;
            }

            /* Tour list styles */
            .tour-list {
                display: grid;
                grid-template-columns: repeat(4, 1fr);
                gap: 20px; /* Khoảng cách giữa các card */
            }

            /* Tour card styles */
            .tour-card {
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            .tour-image {
                width: 100%;
                height: 150px; /* Đảm bảo kích thước ảnh đồng đều */
                object-fit: cover; /* Giữ tỷ lệ ảnh */
                border-radius: 8px 8px 0 0; /* Bo góc trên cùng của ảnh */
            }

            .tour-card h3 {
                font-size: 20px;
            }

            .tour-card p {
                font-size: 14px;
            }

            .price {
                color: #007bff;
            }

            .btn-details {
                display: inline-block;
                padding: 10px 20px;
                background-color: #28a745;
                color: #fff;
                text-decoration: none;
            }

            .btn-details:hover {
                background-color: #218838;
            }

        </style>
         <script type="text/javascript">
            // Hàm để ẩn thông báo sau 2 giây
            function hideMessage() {
                var messageElement = document.getElementById("message");
                if (messageElement) {
                    setTimeout(function () {
                        messageElement.style.display = "none";
                    }, 2000); // 2 giây
                }
            }

            // Gọi hàm hideMessage khi trang đã tải xong
            window.onload = hideMessage;
        </script>
    </head>
    <body>
        <!-- Header -->
        <header class="header">
            <div class="header-left">
                <h1>Welcome to Your Home Page!</h1>
            </div>
            <div class="header-right">
                <c:choose>
                    <c:when test="${sessionScope.user == null}">
                        <a href="login" class="btn-profile">Login</a>
                    </c:when>
                    <c:otherwise>
                        <a href="profile" class="btn-profile">Profile</a>
                        <a href="logout" class="btn-logout">Logout</a>
                    </c:otherwise>
                </c:choose>
            </div>

        </header>

        <!-- Main container for the content -->
        <div class="container">
            <p>Here are the available tours:</p>
             <%
                    String mess = (String) request.getAttribute("mess");
                    if (mess != null) {
                %>
                <p style="color: orange" id="message">${mess}</p>
                <%
                    }
                %>

            <!-- Tour list -->
            <div class="tour-list">
                <c:forEach var="tour" items="${tour}">
                    <div class="tour-card">
                        <img src="img/${tour.image}" alt="${tour.tourName} Image" class="tour-image">
                        <h3>${tour.tourName}</h3>
                        <p>${tour.description}</p>
                        <p>Start Date: ${tour.startDate}</p>
                        <p>End Date: ${tour.endDate}</p>
                        <p class="price">Price: $${tour.price}</p>
                        <a href="tourDetail?id=${tour.tourID}" class="btn-details">View Details</a>
                    </div>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
