<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Change Password</title>
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

        .change-password-section {
            flex: 1;
            padding: 20px;
            background-color: #00bcd4;
            display: flex;
            flex-direction: column;
            justify-content: flex-start;
        }

        .change-password-section h2 {
            color: white;
            text-align: center;
            font-size: 28px;
            margin-bottom: 20px;
        }

        .change-password-form {
            background-color: #80deea;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            margin: 0 auto;
            width: 80%;
        }

        .change-password-form input {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            border: 1px solid #ccc;
            font-size: 16px;
        }

        .change-password-form button {
            background-color: #4caf50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        .change-password-form button:hover {
            background-color: #388e3c;
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

        <!-- Change Password Section -->
        <div class="change-password-section">
            <h2>ĐỔI MẬT KHẨU</h2>
           
            <div class="change-password-form">         
                <form action="changePass" method="POST"> 
                    <h5>${mess}</h5>
                    <input type="password" name="currentPassword" placeholder="Mật khẩu hiện tại" required>
                    <input type="password" name="newPassword" placeholder="Mật khẩu mới" required>
                    <input type="password" name="confirmPassword" placeholder="Xác nhận mật khẩu mới" required>
                    <button type="submit">Đổi Mật Khẩu</button>
                </form>
            </div>
        </div>
    </div>
                
<!--                <script>
    // Hàm để ẩn thông báo sau 2 giây
    function hideMessages() {
        var errorMessage = document.getElementById("message");
        var successMessage = document.getElementById("successMessage");
        
        if (errorMessage) {
            setTimeout(function () {
                errorMessage.style.display = "none";
            }, 2000); // 2 giây
        }
        
        if (successMessage) {
            setTimeout(function () {
                successMessage.style.display = "none";
            }, 2000); // 2 giây
        }
    }

    // Gọi hàm hideMessages khi trang đã tải xong
    window.onload = hideMessages;
</script>-->
</body>
</html>
