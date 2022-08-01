<%-- 
    Document   : products
    Created on : Jul 24, 2022, 3:53:29 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info">QUAN LY SAN PHAM</h1>

<c:url value="/admin/products" var="action" />

<form:form method="post" action="${action}" modelAttribute="product" enctype="multipart/form-data" >

    <form:errors path="*" element="div" cssClass="alert alert-danger" />

    <div class="form-floating mb-3 mt-3">
        <form:input type="text" class="form-control" path="name" id="name" placeholder="name" />
        <label for="name">Ten san pham</label>
        <form:errors path="name" element="div" cssClass="alert alert-danger" />
    </div>
    <div class="form-floating mb-3 mt-3">
        <form:input type="number" class="form-control" path="price" id="price" placeholder="price" name="price" />
        <label for="price">Gia san pham</label>
    </div>
    <div class="form-floating">
        <form:select path="categoryId" class="form-select" id="sel1" name="sellist">
            <c:forEach items="${categories}" var="c">
                <option value="${c.id}">${c.name}</option>
            </c:forEach>
        </form:select>
        <label for="sel1" class="form-label">Danh muc</label>
    </div>
    <div>
        <input type="submit" value="Them san pham" class="btn btn-danger" />
    </div>
</form:form>

<div class="spinner-border text-secondary" id="myLoading"></div>

<table class="table">
    <tr>
        <th>Image</th>
        <th>Name</th>
        <th>Price</th>
        <th></th>
    </tr>
    <tbody id="adminProd">
    </tbody>
</table>

<script src="<c:url value='/js/product.js' />"></script>
<script>
    <c:url value="/api/products" var="endpoint" />
        window.onload = function () {
            loadAdminProducts('${endpoint}');
        }
</script>