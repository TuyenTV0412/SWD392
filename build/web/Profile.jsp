<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Profile Page</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
        }

        .container {
            display: flex;
            height: 100vh;
        }

        .sidebar {
            width: 250px;
            background-color: #e0e0e0;
            padding: 20px;
            display: flex;
            flex-direction: column;
        }

        .sidebar h2 {
            color: #333;
            font-size: 24px;
            margin: 0;
        }

        .sidebar a {
            text-decoration: none;
            color: #000;
            padding: 10px;
            margin-top: 15px;
            border-radius: 5px;
            background-color: #dcdcdc;
        }

        .sidebar a:hover {
            background-color: #4caf50;
            color: white;
        }

        .profile-section {
            flex: 1;
            padding: 20px;
            background-color: #00bcd4;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
        }

        .profile-section h2 {
            color: white;
            text-align: center;
            font-size: 28px;
            margin-bottom: 20px;
        }

        .profile-info {
            background-color: #80deea;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            margin: 0 auto;
            width: 80%;
        }

        .profile-info p {
            font-size: 18px;
            margin: 10px 0;
            color: #333;
        }

        .profile-info .info {
            display: inline-block;
            width: calc(100% - 20px);
            padding: 10px;
            background-color: #b2ebf2;
            border-radius: 5px;
            margin-top: 5px;
            color: #000;
        }

        .edit-btn {
            margin-top: 20px;
            background-color: #4caf50;
            color: white;
            padding: 10px 20px;
            text-decoration: none;
            border-radius: 5px;
            text-align: center;
            display: inline-block;
        }

        .edit-btn:hover {
            background-color: #388e3c;
        }

        /* Modal Styles */
        .modal {
            display: none; /* Hidden by default */
            position: fixed; /* Stay in place */
            z-index: 1; /* Sit on top */
            left: 0;
            top: 0;
            width: 100%; /* Full width */
            height: 100%; /* Full height */
            overflow: auto; /* Enable scroll if needed */
            background-color: rgb(0,0,0); /* Fallback color */
            background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
            padding-top: 60px;
        }

        .modal-content {
            background-color: #fefefe;
            margin: 5% auto;
            padding: 20px;
            border: 1px solid #888;
            width: 80%;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }

        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }

        /* Input styles for form inside modal */
        .modal-form input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        .modal-form button {
            background-color: #4caf50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .modal-form button:hover {
            background-color: #388e3c;
        }

        /* Toast Notification at top-left */
        .toast-left {
            visibility: hidden;
            max-width: 50px;
            height: 50px;
            margin-left: -125px;
            background-color: #4CAF50; /* Green */
            color: white;
            text-align: center;
            border-radius: 2px;
            padding: 16px;
            position: fixed;
            z-index: 1;
            left: 10px; /* Position it at the top-left */
            top: 30px;
            font-size: 17px;
        }

        .toast-left.error {
            background-color: #f44336; /* Red */
        }

        .toast-left.show {
            visibility: visible;
            animation: fadein 0.5s, fadeout 0.5s 2.5s;
        }

        @keyframes fadein {
            from {top: 0; opacity: 0;} 
            to {top: 30px; opacity: 1;}
        }

        @keyframes fadeout {
            from {top: 30px; opacity: 1;} 
            to {top: 0; opacity: 0;}
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Sidebar -->
        <div class="sidebar">
            <h2>Quản Lý</h2>
            <a href="profile">Thông Tin Tài Khoản</a>
            <a href="changePass.jsp">Đổi Mật Khẩu</a>
            <a href="bookingTour">Tour đã đặt</a>
            <a href="home">Quay Về</a>
        </div>

        <!-- Profile Section -->
        <div class="profile-section">
            <h2>THÔNG TIN TÀI KHOẢN</h2>
           
            <div class="profile-info">
                <h5 style="color: greenyellow">${mess}</h5>
                <p><strong>Tên Tài Khoản</strong>: ${user.fullName}</p>
                <p><strong>Email</strong>: ${user.email}</p>
                <p><strong>Số Điện Thoại</strong>: ${user.phone}</p>
                <p><strong>Ngày Tạo</strong>: ${user.creatAt}</p>
                <a href="#" class="edit-btn" id="openModalBtn">Chỉnh sửa</a>
            </div>
        </div>
    </div>

    <!-- Modal -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close" id="closeModalBtn">&times;</span>
            <h2>Chỉnh sửa Thông Tin</h2>
            <form action="profile" method="POST" class="modal-form">
                <label for="fullName">Tên Tài Khoản</label>
                <input type="text" id="fullName" name="fullName" value="${user.fullName}" required>

                <label for="email">Email</label>
                <input type="email" id="email" name="email" value="${user.email}" required>

                <label for="phone">Số Điện Thoại</label>
                <input type="text" id="phone" name="phone" value="${user.phone}" required>

                <label for="creatAt">Ngày Tạo</label>
                <input type="text" id="creatAt" name="creatAt" value="${user.creatAt}" disabled>

                <button type="submit">Cập Nhật</button>
            </form>
        </div>
    </div>

<script>
    document.addEventListener("DOMContentLoaded", function() {
        const modal = document.getElementById("myModal");
        const openModalBtn = document.getElementById("openModalBtn");
        const closeModalBtn = document.getElementById("closeModalBtn");

        openModalBtn.addEventListener("click", function(event) {
            event.preventDefault(); // Ngăn chặn chuyển hướng nếu là thẻ `<a>`
            modal.style.display = "block";
        });

        closeModalBtn.addEventListener("click", function() {
            modal.style.display = "none";
        });

        window.addEventListener("click", function(event) {
            if (event.target === modal) {
                modal.style.display = "none";
            }
        });
    });
</script>


</body>
</html>
