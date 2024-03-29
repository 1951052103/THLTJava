<%-- 
    Document   : product-details
    Created on : Aug 16, 2022, 5:57:30 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<h1 class="text-center text-info">CHI TIET SAN PHAM</h1>

<div class="row">
    <div class="col-md-5">
        <img src="${product.image}" class="img-fluid" />
    </div>
    <div class="col-md-7">
        <h1>${product.name}</h1>
    </div>
</div>

<c:url value="/api/products/${product.id}/comments" var="url" />

<sec:authorize access="isAuthenticated()">
    <div class="form-group">
        <textarea class="form-control" id="contentId" placeholder="Noi dung comment"></textarea>
    </div>
    <input type="button" onclick="addComment('${url}', ${product.id})" value="Binh luan" class="btn btn-danger" /> 
</sec:authorize>
<sec:authorize access="!isAuthenticated()">
    <strong>VUi long de <a href="<c:url value="/login" />">dang nhap</a> binh luan</strong>
</sec:authorize>


<ul id="comments" class="list-group">

</ul>               

<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.4/moment-with-locales.min.js"></script>
<script src="<c:url value="/js/product.js" />"></script>
<script>
        window.onload = function () {
            loadComments('${url}')
        }
</script>