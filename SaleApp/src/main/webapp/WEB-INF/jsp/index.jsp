<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<c:if test="${products.size() == 0}">
    <p>
        <strong>Khong co san pham nao!!!</strong>
    </p>
</c:if>
    
<ul class="pagination">
    <c:forEach begin="1" end="${Math.ceil(productCounter/8)}" var="i">
        <c:url value="/" var="c">
            <c:param value="${i}" name="page" />
        </c:url>
        
        <li class="page-item"><a class="page-link" href="${c}">${i}</a></li>
    </c:forEach>
</ul> 
    
<div class="row">
    <c:forEach items="${products}" var="p">
        <div class="col-md-3 col-xs-12" style="padding:10px;">
            <div class="card">
                <img class="card-img-top" src="https://res.cloudinary.com/dxxwcby8l/image/upload/v1647248652/dkeolz3ghc0eino87iec.jpg" alt="Card image">
                <div class="card-body">
                    <h4 class="card-title">${p.name}</h4>
                    <p class="card-text">
                        <fmt:setLocale value = "en_US"/>
                        <fmt:formatNumber value = "${p.price}" type = "currency"/>
                    </p>
                    <a href="#" class="btn btn-primary">Xem chi tiet</a>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
