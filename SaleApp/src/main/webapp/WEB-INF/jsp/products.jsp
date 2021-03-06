<%-- 
    Document   : products
    Created on : Jul 24, 2022, 3:53:29 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info">QUAN LY SAN PHAM</h1>

<div class="spinner-border text-secondary" id="myLoading"></div>

<table class="table">
    <tr>
        <th>Image</th>
        <th>Name</th>
        <th>Price</th>
        <th></th>
    </tr>
    <tbody id="adminProd">
        <tr>
        </tr>
    </tbody>
</table>

<script src="<c:url value='/js/product.js' />"></script>
<script>
    <c:url value="/api/products" var="endpoint" />
    window.onload = function() {
        loadAdminProducts('${endpoint}');
    }
</script>