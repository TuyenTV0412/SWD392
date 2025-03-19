<%-- 
    Document   : TourDetail
    Created on : 12 Mar 2025, 11:05:16
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Thông Tin Chi Tiết</title>
    <link rel="stylesheet" href="styles.css"> <!-- Liên kết file CSS -->
    <style>
        /* General Styles */
body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}

/* Container */
.container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 20px;
}

/* Header */
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

/* Tour Detail Section */
.tour-detail {
    display: flex;
    margin-top: 20px;
}

.tour-image {
    flex-basis: 50%;
}

.image {
    width: 100%;
    height: auto;
    border-radius: 8px;
}

.tour-info {
    flex-basis: 50%;
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

.price {
    font-size: 18px;
    color: #ff4500;
}

.contact {
    font-size: 16px;
}

/* Buttons */
.buttons {
    margin-top: 20px;
}

.btn {
    display: inline-block;
    padding: 10px 20px;
    text-decoration: none;
    border-radius: 4px;
}

.btn-book {
    background-color: #28a745;
    color: #fff;
}

.btn-book:hover {
    background-color: #218838;
}

.btn-back {
    background-color: #dc3545;
    color: #fff;
}

.btn-back:hover {
    background-color: #c82333;
}

    </style>
</head>
<body>
    <div class="container">
        <!-- Header -->
        <div class="header">
            <div class="logo">
                <img src="img/logo.png" alt="Logo" class="logo-img">
                <span class="logo-text">BookingTour</span>
            </div>
            <div class="user-info">
                <img src="img/user-icon.png" alt="User Icon" class="user-icon">
                <span>Nguyen Van A</span>
            </div>
        </div>

        <!-- Tour Info Section -->
        <div class="tour-detail">
            <!-- Tour Image -->
            <div class="tour-image">
                <img src="img/${tour.image}" alt="${tour.tourName}" class="image">
            </div>

            <!-- Tour Information -->
            <div class="tour-info">
                <h2>${tour.tourName}</h2>

                <p><strong>Địa Điểm:</strong> ${tour.tourName}</p>
                <p><strong>Ngày Bắt Đầu:</strong> ${tour.startDate}</p>
                <p><strong>Ngày Kết Thúc:</strong> ${tour.endDate}</p>
                <p class="price"><strong>Giá:</strong> ${tour.price} VND</p>
                <p><strong>Mô Tả:</strong> ${tour.description}</p>

                <!-- Contact and Buttons -->
<!--                <p class="contact"><strong>Tìm hiểu thêm:</strong> 0989999999</p>-->
                <div class="buttons">
                    <a href="booking?id=${tour.tourID}" class="btn btn-book">Đặt Tour</a>
                    <a href="home" class="btn btn-back">Quay Lại</a>
                </div>
            </div>
        </div>
    </div>
</body>
</html>

