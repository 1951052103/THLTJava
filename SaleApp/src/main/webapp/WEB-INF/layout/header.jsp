<%-- 
    Document   : header
    Created on : Jul 24, 2022, 3:34:29 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<nav class="navbar navbar-expand-sm navbar-dark bg-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="javascript:void(0)">Sale app</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#mynavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="mynavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#">&#128293; Trang chu</a>
                </li>

                <c:forEach items="${categories}" var="c">
                    <c:url value="/" var="cUrl">
                        <c:param name="cateId" value="${c.id}" />
                    </c:url>
                    <li class="nav-item">
                        <a class="nav-link" href="${cUrl}">${c.name}</a>
                    </li>
                </c:forEach>

                <sec:authorize access="!isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link text-success" href="<c:url value="/login" />">Dang Nhao</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link text-success" href="<c:url value="/register" />">Dang Ky</a>
                    </li>
                </sec:authorize>
                <sec:authorize access="isAuthenticated()">
                    <li class="nav-item">
                        <a class="nav-link text-danger" href="<c:url value="/" />">
                            ${pageContext.session.getAttribute("currentUser").firstName}
                            <sec:authentication property="principal.username"/>
                        </a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link text-danger" href="<c:url value="/logout" />">Dang xuat</a>
                    </li>
                </sec:authorize>
                    
                <sec:authorize access="hasRole('ROLE_ADMIN')">
                    <li class="nav-item">
                        <a class="nav-link text-info" href="<c:url value="/admin/products" />">Quan ly</a>
                    </li>
                </sec:authorize>    
            </ul>

            <c:url value="/" var="action" />
            <form method="get" action="${action}" class="d-flex">
                <input class="form-control me-2" type="text" name="kw" placeholder="Nhap tu khoa de tim...">
                <button type="submit" class="btn btn-primary" type="button">Tim</button>
            </form>
        </div>
    </div>
</nav>
