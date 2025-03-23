<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Chi Tiết Đặt Tour</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
                background-color: #f4f4f4;
            }

            .container {
                display: flex;
                min-height: 100vh;
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

            .booking-section {
                flex: 1;
                padding: 20px;
            }

            .booking-detail {
                display: flex;
                background-color: white;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                overflow: hidden;
            }

            .booking-image {
                width: 40%;
                padding: 20px;
            }

            .booking-image img {
                width: 100%;
                height: auto;
                border-radius: 8px;
                object-fit: cover;
            }

            .booking-info {
                width: 60%;
                padding: 20px;
                background-color: #e6f7ff;
            }

            .booking-title {
                font-size: 28px;
                color: #333;
                margin-bottom: 20px;
                border-bottom: 2px solid #00bfff;
                padding-bottom: 10px;
            }

            .booking-details {
                margin-bottom: 20px;
            }

            .booking-details p {
                margin: 10px 0;
                font-size: 16px;
                line-height: 1.6;
            }

            .booking-price {
                font-size: 24px;
                color: #e91e63;
                font-weight: bold;
                margin: 15px 0;
            }

            .booking-status {
                display: inline-block;
                padding: 8px 15px;
                border-radius: 20px;
                font-size: 16px;
                font-weight: bold;
                margin: 15px 0;
            }

            .status-pending {
                background-color: #ffeb3b;
                color: #333;
            }

            .status-confirmed {
                background-color: #4caf50;
                color: white;
            }

            .status-cancelled {
                background-color: #f44336;
                color: white;
            }

            .status-completed {
                background-color: #2196f3;
                color: white;
            }

            .booking-actions {
                margin-top: 30px;
            }

            .btn {
                display: inline-block;
                padding: 10px 20px;
                border-radius: 4px;
                text-decoration: none;
                font-weight: bold;
                cursor: pointer;
                transition: background-color 0.3s;
            }

            .btn-cancel {
                background-color: #f44336;
                color: white;
            }

            .btn-cancel:hover {
                background-color: #d32f2f;
            }

            .btn-back {
                background-color: #607d8b;
                color: white;
                margin-right: 10px;
            }

            .btn-back:hover {
                background-color: #455a64;
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

            <!-- Booking Section -->
            <div class="booking-section">
                <div class="booking-detail">
                    <div class="booking-image">
                        <img src="img/${booking.image}" alt="${booking.tourName}">
                    </div>
                    <div class="booking-info">
                        <h2 class="booking-title">${booking.tourName}</h2>

                        <div class="booking-details">
                            <p><strong>Mô tả:</strong> ${booking.description}</p>
                            <p><strong>Ngày bắt đầu:</strong> <fmt:formatDate value="${booking.startDate}" pattern="dd/MM/yyyy"/></p>
                            <p><strong>Ngày kết thúc:</strong> <fmt:formatDate value="${booking.endDate}" pattern="dd/MM/yyyy"/></p>
                            <p><strong>Ngày đặt tour:</strong> <fmt:formatDate value="${booking.bookingDate}" pattern="dd/MM/yyyy"/></p>

                            <div class="booking-price"><strong>Giá:</strong> ${booking.price} VND</div>

                            <p><strong>Trạng thái:</strong> 
                                <c:choose>
                                    <c:when test="${booking.statusId == 1}">
                                        <span class="booking-status status-pending">${booking.statusName}</span>
                                    </c:when>
                                    <c:when test="${booking.statusId == 2}">
                                        <span class="booking-status status-confirmed">${booking.statusName}</span>
                                    </c:when>
                                    <c:when test="${booking.statusId == 3}">
                                        <span class="booking-status status-cancelled">${booking.statusName}</span>
                                    </c:when>
                                    <c:when test="${booking.statusId == 4}">
                                        <span class="booking-status status-completed">${booking.statusName}</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="booking-status">${booking.statusName}</span>
                                    </c:otherwise>
                                </c:choose>
                            </p>
                        </div>

                        <div class="booking-actions">
                            <a href="bookingTour" class="btn btn-back">Quay Lại</a>

                            <c:if test="${booking.statusId == 1}">
                                <form action="cancelTour" method="POST">
                                    <input type="hidden" name="id" value="${booking.bookingId}">
                                    <button type="submit" class="btn btn-cancel" onclick="return confirm('Bạn có chắc chắn muốn hủy đặt tour này?')">Hủy Tour</button>
                                </form>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
