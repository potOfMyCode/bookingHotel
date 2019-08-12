<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12.08.2019
  Time: 22:26
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
    <title><fmt:message key="booking"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/picture/backgrounds/sky2.jpg)">
<jsp:include page="/WEB-INF/guest/header.jsp"/>

<div id="container" style="position:absolute;
width:100%;
top:42%;
text-align:center;
color: yellow">
    <p><h1><fmt:message key="booking.not.auth"/></h1></p>
    <a href="${pageContext.request.contextPath}/underSky/go_login"><h4 style="color: red"><fmt:message key="header.login"/></h4></a>
    <a href="${pageContext.request.contextPath}/underSky/go_registration"><h4 style="color: deeppink"><fmt:message key="header.loginbar.join"/></h4></a>
</div>


</body>
</html>

