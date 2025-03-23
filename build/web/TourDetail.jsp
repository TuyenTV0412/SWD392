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
.reviews {
                max-width: 600px;
                margin: 20px auto;
                font-family: Arial, sans-serif;
            }

            .reviews h3 {
                font-size: 22px;
                font-weight: bold;
                margin-bottom: 15px;
            }

            .reviews ul {
                list-style: none;
                padding: 0;
            }

            .reviews li {
                background: #fff;
                border: 1px solid #ccc;
                border-radius: 8px;
                padding: 15px;
                margin-bottom: 10px;
                display: flex;
                align-items: flex-start;
                gap: 15px;
            }

            .review-avatar {
                display: flex;
                flex-direction: column;
                align-items: center;
                width: 60px;
            }

            .review-avatar img {
                width: 50px;
                height: 50px;
                border-radius: 50%;
            }

            .review-avatar span {
                font-size: 14px;
                font-weight: bold;
                margin-top: 5px;
            }

            .review-content {
                flex: 1;
            }

            .review-rating {
                font-size: 14px;
                font-weight: bold;
                display: flex;
                align-items: center;
                gap: 5px;
                color: orange;
                background: #f1f1f1;
                padding: 3px 8px;
                border-radius: 5px;
                display: inline-block;
            }

            .reviews p {
                font-size: 14px;
                margin: 5px 0;
            }

            .reviews small {
                font-size: 12px;
                color: gray;
            }

            .pagination {
                display: flex;
                justify-content: center;
                align-items: center;
                margin-top: 10px;
            }

            .pagination button {
                background: none;
                border: none;
                font-size: 18px;
                cursor: pointer;
                padding: 5px 10px;
            }

            .pagination span {
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <div class="container">
            <!-- Header -->
            <div class="header">
                <div class="logo">
                    <span class="logo-text">BookingTour</span>
                </div>
                <div class="user-info"> 
                    <span>User</span>
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
            <!-- HIỂN THỊ DANH SÁCH ĐÁNH GIÁ & PHẢN HỒI -->
            <div class="reviews">
                <h3>Đánh giá của khách hàng</h3>
                <ul>
                    <c:choose>
                        <c:when test="${not empty userReviews}">
                            <c:forEach var="review" items="${userReviews}">
                                <li>
                                    <div class="review-avatar">
                                        <img src="guest-avatar.png" alt="Guest Avatar"> <!-- Avatar mặc định -->
                                        <span>${review.fullName}</span>
                                    </div>
                                    <div class="review-content">
                                        <div class="review-rating">
                                            <span class="star">⭐</span> <span>${review.rating}/5</span>
                                        </div>
                                        <p>${review.comment}</p>
                                        <small>${review.createdAt}</small>
                                    </div>
                                </li>
                            </c:forEach>
                        </c:when>
                        <c:otherwise>
                            <p>Chưa có đánh giá nào.</p>
                        </c:otherwise>
                    </c:choose>
                </ul>


            </div>
        </div>
    </body>
</html>

