<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="hd" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <hd:header mytitle="Orders App"/>
</head>
<body class="bg-body-secondary">
    <div class="container p-2">
        <div class="container">
            <div class="text-left mb-3">
                <a href="<%=request.getContextPath()%>/new" class="btn btn-primary btn-sm m-2">+&nbsp;Add Order</a>
            </div>
            <div class="bg-white p-3">
                <table class="table caption-top">
                    <caption class="fs-3 fw-semibold">Orders</caption>
                    <thead class="table-light">
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Date</th>
                            <th scope="col">Cost</th>
                            <th scope="col">Products</th>
                            <th scope="col">Action</th>
                        </tr>
                    </thead>
                    <tbody class="table-group-divider">
                    <c:forEach var="book" items="${ordersList}">
                        <tr>
                            <td><c:out value="${order.id}" /></td>
                            <td><c:out value="${order.date}" /></td>
                            <td><c:out value="${order.cost}" /></td>
                            <td><c:out value="${order.products}" /></td>
                            <td>
                                <a class="btn btn-warning btn-sm"
                                href="<%=request.getContextPath()%>/edit?id=<c:out value='${order.id}' />"
                                role="button">Edit</a>
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <a class="btn btn-danger btn-sm"
                                href="<%=request.getContextPath()%>/delete?id=<c:out value='${order.id}' />"
                                role="button">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</body>
</html>