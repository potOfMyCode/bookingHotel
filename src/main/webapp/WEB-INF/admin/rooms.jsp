<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12.08.2019
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>
<html>
<head lang="${sessionScope.curLang}">
    <title>Rooms</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/post.js"></script>
    <link href="${pageContext.request.contextPath}/css/numbers.css" type="text/css" rel="stylesheet">
</head>
<body>
<jsp:include page="/WEB-INF/admin/header.jsp"/>

<section class="pricing py-5">
    <div class="wrap-cont">
        <!-- Free Tier -->
        <div class="card-bg-color  mb-5 mb-lg-0">
            <div class="card-body inner-bg">
                <div class="table-container">
                    <div class="my-tk-header">
                        <h3 class="font-weight-light"><fmt:message key="rooms"/></h3>
                    </div>
                    <div class="header-row">
                        <div class="tk-type">
                            <fmt:message key="Room.id"/>
                        </div>
                        <div class="tk-type">
                            <fmt:message key="number.type"/>
                        </div>
                        <div class="tk-shortType">
                            <fmt:message key="number.shortType"/>
                        </div>
                        <div class="tk-price">
                            <fmt:message key="number.price"/>
                        </div>
                        <div class="tk-amount">
                            <fmt:message key="number.amount"/>
                        </div>
                    </div>
                    <c:forEach items="${numbers}" var="number">
                        <c:forEach items="${number.rooms}" var="room">
                            <div class="movie-row movie-row">
                                <div class="tk-type">
                                    ${room.id}
                                </div>

                                <div class="tk-type">
                                    <c:choose>
                                        <c:when test="${sessionScope.curLang.equals('en') }">
                                            ${number.type}
                                        </c:when>
                                        <c:otherwise>
                                            ${number.typeUkr}
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="tk-shortType">
                                        ${number.shortType}
                                </div>
                                <div class="tk-price">
                                    <c:choose>
                                        <c:when test="${sessionScope.curLang.equals('en') }">
                                            <fmt:formatNumber pattern="0.00" value="${number.price/2600}"></fmt:formatNumber> $
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatNumber pattern="0.00" value="${number.price/100}"></fmt:formatNumber> грн
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="tk-amount">
                                        ${number.amount}
                                </div>

                            </div>
                        </c:forEach>

                    </c:forEach>

                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>
