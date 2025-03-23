<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Admin Dashboard</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <style>
            .sidebar {
                background-color: #6366F1;
                min-height: 100vh;
                color: white;
            }
            .sidebar a {
                color: white;
                text-decoration: none;
            }
            .menu-item {
                padding: 15px;
                text-align: center;
                border-radius: 10px;
                margin: 5px 0;
                transition: background-color 0.3s;
            }
            .menu-item:hover, .menu-item.active {
                background-color: #22D3EE;
            }
            .main-content {
                background-color: #f5f5f5;
                min-height: 100vh;
                padding: 20px;
            }
            .content-card {
                background-color: white;
                border-radius: 10px;
                padding: 20px;
                box-shadow: 0 4px 6px rgba(0,0,0,0.1);
            }
            .header {
                background-color: #6366F1;
                color: white;
                padding: 15px;
                text-align: right;
            }
            .table th, .table td {
                vertical-align: middle;
            }
            .search-box {
                max-width: 300px;
                float: right;
                margin-bottom: 20px;
            }
            .action-btn {
                cursor: pointer;
                margin: 0 5px;
            }
            .edit-icon {
                color: #3B82F6;
            }
            .delete-icon {
                color: #EF4444;
            }
            .description-cell {
                max-width: 250px;
                white-space: nowrap;
                overflow: hidden;
                text-overflow: ellipsis;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid p-0">
            <div class="row g-0">
                <!-- Sidebar -->
                <div class="col-md-2 sidebar">
                    <div class="d-flex flex-column">
                        <div class="p-3 text-center fw-bold fs-4">Dashboard</div>
                        <div class="p-2">Quản lí</div>

                        <div class="menu-item">
                            <a href="#">Profile</a>
                        </div>
                        <div class="menu-item active">
                            <a href="${pageContext.request.contextPath}/admin/tour">Tour</a>
                        </div>
                        <div class="menu-item">
                            <a href="#">Order</a>
                        </div>
                        <div class="menu-item">
                            <a href="#">Account</a>
                        </div>
                    </div>
                </div>

                <!-- Main Content -->
                <div class="col-md-10 p-0">
                    <!-- Header -->
                    <div class="header">
                        <div class="dropdown d-inline-block">
                            <button class="btn btn-light dropdown-toggle" type="button" id="userDropdown" data-bs-toggle="dropdown" aria-expanded="false">
                                An Đức Nhat <i class="fas fa-angle-down ms-1"></i>
                            </button>
                            <ul class="dropdown-menu" aria-labelledby="userDropdown">
                                <li><a class="dropdown-item" href="#">Profile</a></li>
                                <li><a class="dropdown-item" href="#">Settings</a></li>
                                <li><hr class="dropdown-divider"></li>
                                <li><a class="dropdown-item" href="${pageContext.request.contextPath}/logout">Logout</a></li>
                            </ul>
                        </div>
                    </div>

                    <!-- Content Area -->
                    <div class="main-content">
                        <div class="content-card">
                            <h2 class="mb-4">Manage Tour</h2>

                            <!-- Header với List Tour và Add Tour Button -->
                            <div class="d-flex justify-content-between align-items-center mb-4">
                                <h5>List Tour</h5>
                                <div>
                                    <a href="${pageContext.request.contextPath}/admin/tour" class="btn btn-secondary me-2">
                                        <i class="fas fa-sync-alt"></i> Làm mới danh sách
                                    </a>
                                    <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#addTourModal">
                                        <i class="fas fa-plus"></i> Add Tour
                                    </button>
                                </div>
                            </div>

                            <!-- Search Box -->
                            <div class="search-box mb-3">
                                <div class="input-group">
                                    <input type="text" id="searchInput" class="form-control" placeholder="Search tours...">
                                    <button class="btn btn-outline-secondary" type="button" onclick="searchTours()">
                                        <i class="fas fa-search"></i> Search
                                    </button>
                                </div>
                            </div>

                            <!-- Hiển thị thông báo -->
                            <c:if test="${not empty sessionScope.message}">
                                <div class="alert alert-success alert-dismissible fade show" role="alert">
                                    ${sessionScope.message}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                                <c:remove var="message" scope="session" />
                            </c:if>
                            <c:if test="${not empty sessionScope.error}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    ${sessionScope.error}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                                <c:remove var="error" scope="session" />
                            </c:if>

                            <!-- Tour Table -->
                            <div class="table-responsive">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>ID</th>
                                            <th>Hình ảnh</th>
                                            <th>Tên tour</th>
                                            <th>Mô tả</th>
                                            <th>Sửa</th>
                                            <th>Xóa</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="tour" items="${tours}">
                                            <tr>
                                                <td>${tour.tourID}</td>
                                                <td>
                                                    <c:if test="${not empty tour.image}">
                                                        <img src="${pageContext.request.contextPath}/${tour.image}" 
                                                             alt="${tour.tourName}" 
                                                             style="width: 80px; height: 60px; object-fit: cover;">
                                                    </c:if>
                                                    <c:if test="${empty tour.image}">
                                                        <div style="width: 80px; height: 60px; background-color: #f0f0f0; display: flex; align-items: center; justify-content: center;">
                                                            <i class="fas fa-image text-muted"></i>
                                                        </div>
                                                    </c:if>
                                                </td>
                                                <td>${tour.tourName}</td>
                                                <td class="description-cell" title="${tour.description}">${tour.description}</td>
                                                <td>
                                                    <span class="action-btn edit-icon" data-bs-toggle="modal" data-bs-target="#editTourModal" 
                                                          onclick="prepareEditModal(${tour.tourID})">
                                                        <i class="fas fa-edit"></i>
                                                    </span>
                                                </td>
                                                <td>
                                                    <span class="action-btn delete-icon" data-bs-toggle="modal" data-bs-target="#deleteTourModal" 
                                                          onclick="prepareDeleteModal(${tour.tourID}, '${tour.tourName}')">
                                                        <i class="fas fa-trash-alt"></i>
                                                    </span>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Add Tour Modal -->
        <div class="modal fade" id="addTourModal" tabindex="-1" aria-labelledby="addTourModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="addTourModalLabel">Thêm Tour Mới</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form id="addTourForm" action="${pageContext.request.contextPath}/admin/tour/add" method="post" enctype="multipart/form-data">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="tourName" class="form-label">Tên tour</label>
                                    <input type="text" class="form-control" id="tourName" name="tourName" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="price" class="form-label">Giá</label>
                                    <input type="number" class="form-control" id="price" name="price" step="0.01" min="0" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="startDate" class="form-label">Ngày bắt đầu</label>
                                    <input type="date" class="form-control" id="startDate" name="startDate" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="endDate" class="form-label">Ngày kết thúc</label>
                                    <input type="date" class="form-control" id="endDate" name="endDate" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="availableSeats" class="form-label">Số chỗ trống</label>
                                <input type="number" class="form-control" id="availableSeats" name="availableSeats" min="0" required>
                            </div>

                            <div class="mb-3">
                                <label for="description" class="form-label">Mô tả</label>
                                <textarea class="form-control" id="description" name="description" rows="4" required></textarea>
                            </div>

                            <div class="mb-3">
                                <label for="image" class="form-label">Hình ảnh</label>
                                <input type="file" class="form-control" id="image" name="image" accept="image/*" required>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <button type="submit" class="btn btn-primary">Thêm Tour</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Edit Tour Modal -->
        <div class="modal fade" id="editTourModal" tabindex="-1" aria-labelledby="editTourModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-lg">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="editTourModalLabel">Chỉnh sửa Tour</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form id="editTourForm" action="${pageContext.request.contextPath}/admin/tour/edit" method="post" enctype="multipart/form-data">
                        <div class="modal-body">
                            <input type="hidden" id="editTourID" name="tourID">

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="editTourName" class="form-label">Tên tour</label>
                                    <input type="text" class="form-control" id="editTourName" name="tourName" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="editPrice" class="form-label">Giá</label>
                                    <input type="number" class="form-control" id="editPrice" name="price" step="0.01" min="0" required>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-6 mb-3">
                                    <label for="editStartDate" class="form-label">Ngày bắt đầu</label>
                                    <input type="date" class="form-control" id="editStartDate" name="startDate" required>
                                </div>
                                <div class="col-md-6 mb-3">
                                    <label for="editEndDate" class="form-label">Ngày kết thúc</label>
                                    <input type="date" class="form-control" id="editEndDate" name="endDate" required>
                                </div>
                            </div>

                            <div class="mb-3">
                                <label for="editAvailableSeats" class="form-label">Số chỗ trống</label>
                                <input type="number" class="form-control" id="editAvailableSeats" name="availableSeats" min="0" required>
                            </div>

                            <div class="mb-3">
                                <label for="editDescription" class="form-label">Mô tả</label>
                                <textarea class="form-control" id="editDescription" name="description" rows="4" required></textarea>
                            </div>

                            <div class="mb-3">
                                <label for="editImage" class="form-label">Hình ảnh mới (không bắt buộc)</label>
                                <input type="file" class="form-control" id="editImage" name="image" accept="image/*">
                            </div>

                            <div class="mb-3" id="currentImageContainer">
                                <label class="form-label">Hình ảnh hiện tại</label>
                                <div>
                                    <img id="currentImage" src="" alt="Current Tour Image" style="max-width: 200px; max-height: 150px;">
                                </div>
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                            <button type="submit" class="btn btn-primary">Lưu thay đổi</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>

        <!-- Delete Tour Modal -->
        <div class="modal fade" id="deleteTourModal" tabindex="-1" aria-labelledby="deleteTourModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="deleteTourModalLabel">Xác nhận xóa</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <p>Bạn có chắc chắn muốn xóa tour này?</p>
                        <p>Tour: <span id="deleteTourName"></span></p>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <form id="deleteTourForm" action="${pageContext.request.contextPath}/admin/tour/delete" method="post">
                            <input type="hidden" id="deleteTourID" name="tourID">
                            <button type="submit" class="btn btn-danger">Xóa</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <!-- Bootstrap Bundle with Popper -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
        <!-- jQuery -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

        <script>
                                                              // Hàm chuẩn bị modal chỉnh sửa tour
                                                              function prepareEditModal(tourId) {
                                                                  // Hiển thị loading hoặc spinner để người dùng biết đang tải dữ liệu
                                                                  $('#editTourForm input, #editTourForm textarea').val('Loading...');

                                                                  // Gọi API để lấy thông tin chi tiết tour
                                                                  $.ajax({
                                                                      url: '${pageContext.request.contextPath}/admin/tour/getDetails',
                                                                      type: 'GET',
                                                                      data: {id: tourId},
                                                                      dataType: 'json',
                                                                      success: function (tour) {
                                                                          console.log("Tour data received:", tour); // Debug log

                                                                          // Điền thông tin vào form
                                                                          $('#editTourID').val(tour.tourID);
                                                                          $('#editTourName').val(tour.tourName || '');
                                                                          $('#editPrice').val(tour.price || '');

                                                                          // Định dạng ngày cho input date
                                                                          if (tour.startDate) {
                                                                              var startDate = new Date(tour.startDate);
                                                                              $('#editStartDate').val(formatDate(startDate));
                                                                          }

                                                                          if (tour.endDate) {
                                                                              var endDate = new Date(tour.endDate);
                                                                              $('#editEndDate').val(formatDate(endDate));
                                                                          }

                                                                          $('#editAvailableSeats').val(tour.availableSeats || '');
                                                                          $('#editDescription').val(tour.description || '');

                                                                          // Hiển thị ảnh hiện tại
                                                                          if (tour.image) {
                                                                              $('#currentImage').attr('src', '${pageContext.request.contextPath}/' + tour.image);
                                                                              $('#currentImageContainer').show();
                                                                          } else {
                                                                              $('#currentImageContainer').hide();
                                                                          }
                                                                      },
                                                                      error: function (xhr, status, error) {
                                                                          console.error('Error fetching tour details:', error);
                                                                          console.log(xhr.responseText); // Xem response từ server
                                                                          alert('Không thể lấy thông tin tour. Vui lòng thử lại sau.');
                                                                      }
                                                                  });
                                                              }

                                                              // Hàm chuẩn bị modal xóa tour
                                                              function prepareDeleteModal(tourId, tourName) {
                                                                  $('#deleteTourID').val(tourId);
                                                                  $('#deleteTourName').text(tourName);
                                                              }

                                                              // Hàm tìm kiếm tour
                                                              function searchTours() {
                                                                  var keyword = $('#searchInput').val().trim();
                                                                  if (keyword) {
                                                                      window.location.href = '${pageContext.request.contextPath}/admin/tour?search=' + encodeURIComponent(keyword);
                                                                  }
                                                              }

                                                              // Hàm định dạng ngày YYYY-MM-DD cho input date
                                                              function formatDate(date) {
                                                                  var year = date.getFullYear();
                                                                  var month = (date.getMonth() + 1).toString().padStart(2, '0');
                                                                  var day = date.getDate().toString().padStart(2, '0');
                                                                  return year + '-' + month + '-' + day;
                                                              }

                                                              // Bắt sự kiện nhấn Enter trên ô tìm kiếm
                                                              $('#searchInput').on('keyup', function (e) {
                                                                  if (e.key === 'Enter') {
                                                                      searchTours();
                                                                  }
                                                              });

                                                              // Thêm tooltip cho các cell mô tả bị cắt ngắn
                                                              $(function () {
                                                                  $('[data-toggle="tooltip"]').tooltip();
                                                                  $('.description-cell').tooltip();
                                                              });

                                                              // Kiểm tra ngày end phải sau ngày start
                                                              $('#addTourForm, #editTourForm').on('submit', function (e) {
                                                                  var formId = $(this).attr('id');
                                                                  var startDateId = formId === 'addTourForm' ? '#startDate' : '#editStartDate';
                                                                  var endDateId = formId === 'addTourForm' ? '#endDate' : '#editEndDate';

                                                                  var startDate = new Date($(startDateId).val());
                                                                  var endDate = new Date($(endDateId).val());

                                                                  if (endDate <= startDate) {
                                                                      e.preventDefault();
                                                                      alert('Ngày kết thúc phải sau ngày bắt đầu');
                                                                  }
                                                              });
        </script>
    </body>
</html>