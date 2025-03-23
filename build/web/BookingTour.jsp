<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Tour Đã Đặt</title>
        <style>
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
                background-color: #00bcd4;
            }

            .booking-section h2 {
                color: white;
                text-align: center;
                font-size: 28px;
                margin-bottom: 20px;
            }

            .booking-list {
                display: flex;
                flex-direction: column;
                gap: 20px;
            }

            .booking-card {
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                overflow: hidden;
                display: flex;
            }

            .booking-image {
                width: 200px;
                height: 150px;
                object-fit: cover;
            }

            .booking-details {
                padding: 15px;
                flex: 1;
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }

            .booking-title {
                font-size: 20px;
                font-weight: bold;
                color: #333;
                margin-bottom: 10px;
                display: flex;
                justify-content: space-between;
                align-items: center;
            }

            .detail-btn {
                display: inline-block;
                background-color: #2196f3;
                color: white;
                padding: 5px 15px;
                border-radius: 4px;
                text-decoration: none;
                font-weight: bold;
                margin-left: auto;
            }


            .booking-info {
                display: grid;
                grid-template-columns: 1fr 1fr;
                gap: 10px;
            }

            .booking-price {
                color: #e91e63;
                font-weight: bold;
                font-size: 18px;
            }

            .booking-date {
                color: #555;
            }

            .booking-status {
                display: inline-block;
                padding: 5px 10px;
                border-radius: 20px;
                font-size: 14px;
                font-weight: bold;
            }

            .status-pending {
                background-color: yellow;
                color: #333;
            }

            .status-confirmed {
                background-color: yellow;
                color: white;
            }

            .status-cancelled {
                background-color: greenyellow;
                color: white;
            }

            .status-completed {
                background-color: red;
                color: white;
            }

            .no-bookings {
                text-align: center;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .detail-btn {
                display: inline-block;
                background-color: #2196f3;
                color: white;
                padding: 5px 15px;
                border-radius: 4px;
                text-decoration: none;
                margin-top: 5px;
                font-weight: bold;
                float: right;
            }

            .detail-btn:hover {
                background-color: #0b7dda;
            }

            .action-buttons {
                margin-top: 10px;
                display: flex;
                justify-content: flex-end;
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
            <!-- Booking Section -->
            <div class="booking-section">
                <h2>TOUR ĐÃ ĐẶT</h2>

                <div class="booking-list">
                    <c:if test="${empty list}">
                        <div class="no-bookings">
                            <p>Bạn chưa đặt tour nào.</p>
                        </div>
                    </c:if>

                    <c:forEach var="booking" items="${list}">
                        <div class="booking-card">
                            <img src="img/${booking.image}" alt="${booking.tourName}" class="booking-image">
                            <div class="booking-details">
                                <div class="booking-title">${booking.tourName}</div>
                                <div class="booking-info">
                                    <div>
                                        <p><strong>Mô tả:</strong> ${booking.description}</p>
                                        <p><strong>Ngày bắt đầu:</strong> <fmt:formatDate value="${booking.startDate}" pattern="dd/MM/yyyy"/></p>
                                        <p><strong>Ngày kết thúc:</strong> <fmt:formatDate value="${booking.endDate}" pattern="dd/MM/yyyy"/></p>
                                    </div>
                                    <div>
                                        <p class="booking-price"><strong>Giá:</strong> ${booking.price} VND</p>
                                        <p class="booking-date"><strong>Ngày đặt:</strong> <fmt:formatDate value="${booking.bookingDate}" pattern="dd/MM/yyyy"/></p>
                                        <p>
                                            <strong>Trạng thái:</strong> 
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
                                        <div class="booking-title">
                                            <a href="cancelTour?id=${booking.bookingId}" class="detail-btn">Chi tiết</a>
                                            <c:if test="${booking.statusId == 3 && booking.rating == null}">
                                                <a href="feedback?tourID=${booking.tourId}" class="detail-btn">Gửi Phản Hồi</a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>

    </div>
</body>
</html>
