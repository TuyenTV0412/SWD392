<%-- 
    Document   : Hotel
    Created on : Mar 22, 2025, 7:04:11 PM
    Author     : admin
--%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://cdn.tailwindcss.com"></script>
    <title>Hotel Management</title>
</head>
<body class="p-6 bg-gray-100">
    <div class="max-w-5xl mx-auto bg-white p-6 rounded-lg shadow-md">
        <form action="servlethotel" method="get">
            <input type="text" name="search" placeholder="Search" class="w-full p-2 border rounded-lg mb-4">
            <button type="submit" class="p-2 bg-blue-500 text-white rounded">Search</button>
        </form>
        
        <div class="grid grid-cols-3 gap-4">
            <!-- Table -->
            <div class="col-span-2">
                <table class="w-full border-collapse border border-gray-300">
                    <thead>
                        <tr class="bg-gray-200">
                            <th class="border p-2">Hotel Name</th>
                            <th class="border p-2">Location</th>
                            <th class="border p-2">Description</th>
                            <th class="border p-2">Room Available</th>
                            <th class="border p-2">Hotel ID</th>
                        </tr>
                    </thead>
                    <tbody id="hotelTable">
                        <c:forEach var="c" items="${hotelList}">
                            <tr class="cursor-pointer hover:bg-gray-100" onclick="fillDetails('${c[0]}', '${c[1]}', '${c[2]}', '${c[3]}', '${c[4]}')">
                                <td class="border p-2 text-center">${c[0]}</td>
                                <td class="border p-2 text-center">${c[1]}</td>
                                <td class="border p-2 text-center">${c[2]}</td>
                                <td class="border p-2 text-center">${c[3]}</td>
                                <td class="border p-2 text-center">${c[4]}</td>
                                
                            </tr> 
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            
            <!-- Details Panel -->
            <div class="bg-gray-50 p-4 rounded-lg shadow-md">
                <form id="hotelForm" action="servlethotel" method="get">
                    
                    <input type="text" id="hotelID" name="hotelID">
                    <div>
                        <label class="text-gray-700">Name</label>
                        <input type="text" name="hotelName" id="hotelName" class="w-full border p-2 rounded bg-gray-200" required>
                    </div>
                    <div>
                        <label class="text-gray-700">Location</label>
                        <input type="text" name="hotelLocation" id="hotelLocation" class="w-full border p-2 rounded bg-gray-200" required>
                    </div>
                    <div>
                        <label class="text-gray-700">Description</label>
                        <textarea name="hotelDescription" id="hotelDescription" class="w-full border p-2 rounded bg-gray-200" required></textarea>
                    </div>
                    <div>
                        <label class="text-gray-700">Room Available</label>
                        <input type="number" name="roomAvailable" id="roomAvailable" class="w-full border p-2 rounded bg-gray-200" required>
                    </div>
                    
                      <input type="hidden" id="formAction" name="action" >
                      <!--<button type="submit" value="delete" style="color: red">Xóa tài khoản</button>-->
                      
                    <!-- Buttons -->
                    <div class="mt-4 space-x-2">
                        <button type="button" onclick="submitForm('create')" class="p-2 bg-green-500 text-white rounded">Create New Hotel</button>
                        <button type="button" onclick="submitForm('update')" class="p-2 bg-yellow-500 text-white rounded">Update Hotel</button>
                        <button type="button" onclick="submitForm('delete')" class="p-2 bg-red-500 text-white rounded">Delete Hotel</button>
                    </div>
                </form>

                <!-- Display Messages -->
                <c:if test="${not empty message}">
                    <div class="mt-4 p-2 text-white bg-${messageType == 'success' ? 'green' : 'red'}-500 rounded">
                        ${message}
                    </div>
                </c:if>
            </div>
        </div>
    </div>

    <script>
        function fillDetails(name, location, description, roomAvailable, id) {
            document.getElementById('hotelName').value = name;
            document.getElementById('hotelLocation').value = location;
            document.getElementById('hotelDescription').value = description;
            document.getElementById('roomAvailable').value = roomAvailable;
            document.getElementById('hotelID').value = id;
        }

        function submitForm(action) {
            let confirmMessage = "Confirm?";
            if (action === 'create') confirmMessage = "Are you sure you want to create this hotel?";
            if (action === 'update') confirmMessage = "Are you sure you want to update this hotel?";
            if (action === 'delete') confirmMessage = "Are you sure you want to delete this hotel?";
            
            if (confirm(confirmMessage)) {
                document.getElementById('formAction').value = action;
                document.getElementById('hotelForm').submit();
            }
        }
    </script>
</body>
</html>
