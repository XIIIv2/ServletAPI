<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="hd" tagdir="/WEB-INF/tags" %>
<%@ taglib uri="/WEB-INF/functions/contains.tld" prefix="fn" %>
<html>
    <head>
        <hd:header mytitle="New Order"/>
    </head>
<body class="bg-body-secondary">
    <div class="container p-2">
        <div class="container-sm px-5 d-flex justify-content-center align-items-center">
            <div class="m-3 px-5 py-3 bg-white" style="width: 50%;">
                <h4 class="text-center mb-3 mt-3 text-warning">
                    <c:if test="${order != null}">
                        Edit Order #${order.id}
                    </c:if>
                    <c:if test="${order == null}">
                        New Order
                    </c:if>
                </h4>
                <c:if test="${order != null}">
                    <form action="<%=request.getContextPath()%>/update" method="post">
                </c:if>
                <c:if test="${order == null}">
                    <form action="<%=request.getContextPath()%>/insert" method="post">
                </c:if>
                <div>
                    <c:if test="${order != null}">
                        <input type="hidden" name="id" value="<c:out value='${order.id}' />" />
                    </c:if>
                </div>
                <div class="m-2">
                    <label for="date"><span style="font-size:12px;">Date</span></label>
                    <input type="text" class="form-control fw-light" id="date" name="date"
                        placeholder="Date..."  value="<c:out value='${order.date}' />" />
                </div>
                <div class="m-2">
                    <h6>Cost: ${order.getCost()}</h6>
                </div>
                <div class="m-2">
                    <legend>Choose order products</legend>
                    <select id="orderProducts" name="orderProducts" multiple="multiple" class="form-control fw-light" style="width:450px">
                        <c:forEach var="product" items="${productsList}">
                            <option value="${product.id}"<c:if test="${ order != null && fn:contains(order.products, product) }"> selected="selected"</c:if>>
                                ${product.name} - \$${product.cost}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                <div class="container text-center mb-4"><br>
                    <a href="<%=request.getContextPath()%>/list"
                    class="btn btn-outline-primary btn-sm">Cancel</a>
                    &nbsp;&nbsp;
                    <button type="submit" class="btn btn-primary btn-sm">Save</button>
                </div>
            </div>
        </div>
    </div>
<hd:footer/>