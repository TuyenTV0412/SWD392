<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Đặt Tour</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .container {
                max-width: 1200px;
                margin: 0 auto;
                padding: 20px;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                background-color: #00bfff;
                padding: 10px 20px;
                border-radius: 8px;
            }

            .logo {
                display: flex;
                align-items: center;
            }

            .logo-img {
                width: 50px;
                height: 50px;
            }

            .logo-text {
                font-size: 24px;
                font-weight: bold;
                color: #fff;
                margin-left: 10px;
            }

            .user-info {
                display: flex;
                align-items: center;
            }

            .user-icon {
                width: 40px;
                height: 40px;
            }

            .user-info span {
                margin-left: 10px;
                font-size: 16px;
                color: #fff;
            }

            .main-content {
                display: flex;
                margin-top: 20px;
                gap: 20px;
            }

            .tour-info {
                flex: 4;
                background-color: #e6f7ff;
                padding: 20px;
                border-radius: 8px;
            }

            .tour-info h2 {
                font-size: 28px;
                color: #007bff;
            }

            .tour-info p {
                font-size: 16px;
                margin-bottom: 10px;
            }

            .tour-info img {
                width: 100%;
                height: auto;
                border-radius: 8px;
            }

            .price {
                font-size: 18px;
                color: #ff4500;
                font-weight: bold;
            }

            .customer-info {
                flex: 8;
                background-color: #b3e0ff;
                padding: 30px;
                border-radius: 8px;
                font-size: 20px;
            }

            .customer-info h2 {
                font-size: 28px;
                text-align: center;
                font-weight: bold;
            }

            .customer-info p {
                font-size: 22px;
                margin-bottom: 15px;
                font-weight: bold;
            }

            .customer-info span {
                display: block;
                background-color: #87CEEB;
                padding: 15px;
                border-radius: 8px;
                text-align: center;
                margin-bottom: 20px;
                font-size: 22px;
                font-weight: bold;
            }

            .buttons {
                display: flex;
                justify-content: center;
                gap: 20px;
                margin-top: 20px;
            }

            .btn {
                padding: 15px 30px;
                font-size: 18px;
                font-weight: bold;
                border: none;
                border-radius: 8px;
                cursor: pointer;
                transition: 0.3s;
            }

            .btn-book {
                background-color: #28a745;
                color: white;
            }

            .btn-book:hover {
                background-color: #218838;
            }

            .btn-back {
                background-color: #dc3545;
                color: white;
            }

            .btn-back:hover {
                background-color: #c82333;
            }
        </style>
    </head>
    <body>

        <div class="container">
            <div class="header">
                <div class="logo">
                    <img src="img/logo.png" alt="Logo" class="logo-img">
                    <span class="logo-text">BookingTour</span>
                </div>
                <div class="user-info">
                    <img src="img/user-icon.png" alt="User Icon" class="user-icon">
                    <span>${user.fullName}</span>
                </div>
            </div>

            <div class="main-content">
                <div class="tour-info">
                    <h2>${tour.tourName}</h2>
                    <img src="img/${tour.image}" alt="${tour.tourName}" />
                    <p><strong>Địa Điểm:</strong> ${tour.tourName}</p>
                    <p><strong>Ngày Bắt Đầu:</strong> ${tour.startDate}</p>
                    <p><strong>Ngày Kết Thúc:</strong> ${tour.endDate}</p>
                    <p class="price"><strong>Giá:</strong> ${tour.price} VND</p>
                    <p><strong>Mô Tả:</strong> ${tour.description}</p>
                </div>

                <div class="customer-info">
                    <h2>Thông Tin Khách Hàng</h2>
                    <span>${user.fullName}</span>
                    <span>${user.email}</span>
                    <span>${user.phone}</span>
                    <form action="booking" method="POST" class="modal-form">
                        <input type="hidden" name="tourID" value="${tour.tourID}">
                        <div class="buttons">
                            <button type="submit" class="btn btn-book">Đặt Tour</button>
                            <a href="home" class="btn btn-back">Quay Lại</a>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
