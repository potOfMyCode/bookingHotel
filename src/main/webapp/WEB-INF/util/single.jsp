<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 07.08.2019
  Time: 18:14
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
    <title>Single</title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
</head>


<body data-gr-c-s-loaded="true" style="background-image: url(${pageContext.request.contextPath}/picture/backgrounds/sky3.jpg)">
<jsp:include page="/WEB-INF/${curRole}/header.jsp"/>
<div id="container">

    <div class="container-fluid patternback">
        <div class="container contentblock" style="background-color: #f6f6fd;">
            <img src="${pageContext.request.contextPath}/picture/booking/single-room-title.jpg"
                 style="width: 100%; margin: 0; padding: 0; background-size: 100%" >

            <div class="row">
                <div class="component col-md-8 col-sm-12">

                    <div class="item-page">
                        <div class="page-header">
                            <h1 itemprop="headline"><fmt:message key="single"/></h1>
                        </div>


                        <div itemprop="articleBody">
                            <p><fmt:message key="sinl.desc"/><br></p>

                            <h3><fmt:message key="room.features"/></h3>
                            <div class="row">
                                <div class="col-sm-6 col-xs-12">
                                    <ul>
                                        <li><fmt:message key="room.area.sinle"/></li>
                                        <li><fmt:message key="bed.single"/></li>
                                        <li><fmt:message key="airConditioning"/></li>
                                        <li><fmt:message key="wifi"/></li>
                                        <li><fmt:message key="in-roomSafe"/></li>
                                        <li><fmt:message key="tv"/></li>
                                        <li><fmt:message key="businessPlace"/></li>
                                    </ul>
                                </div>
                                <div class="col-sm-6 col-xs-12">
                                    <ul>
                                        <li><fmt:message key="miniBar"/></li>
                                        <li><fmt:message key="Telephone"/></li>
                                        <li><fmt:message key="Shower"/></li>
                                        <li><fmt:message key="Towels"/></li>
                                        <li><fmt:message key="hairDryer"/></li>
                                        <li><fmt:message key="accomodation.single"/></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div id="carouselExampleIndicators" class="carousel slide" data-ride="carousel" >
                        <ol class="carousel-indicators">
                            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
                            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
                        </ol>
                        <div class="carousel-inner" role="listbox" >
                            <div class="carousel-item active" style="background-image: url(${pageContext.request.contextPath}/picture/booking/single-room-1.jpg);
                                    background-size: contain"></div>
                            <div class="carousel-item" style="background-image: url(${pageContext.request.contextPath}/picture/booking/single-room-2.jpg);
                                    background-size: contain"></div>
                        </div>
                        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="sr-only"><fmt:message key="previous"/></span>
                        </a>
                        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="sr-only"><fmt:message key="next"/></span>
                        </a>
                    </div>

                    <div class="incontentbottom">
                        <a class="well " href="${pageContext.request.contextPath}/underSky/go_booking?${pageContext.request.queryString}">
                            <h1 class="page-header"><fmt:message key="booking"/></h1>
                        </a>
                    </div>
                </div>
                <div class="right col-md-4 col-sm-12">

                    <div class="list-group">
                        <a href="${pageContext.request.contextPath}/underSky/go_booking?${pageContext.request.queryString}" class="list-group-item list-group-item-action"><fmt:message key="booking"/></a>
                        <a href="${pageContext.request.contextPath}/underSky/go_single?${pageContext.request.queryString}" class="list-group-item active"><fmt:message key="single"/></a>
                        <a href="${pageContext.request.contextPath}/underSky/go_double?${pageContext.request.queryString}" class="list-group-item list-group-item-action"><fmt:message key="double"/></a>
                        <a href="${pageContext.request.contextPath}/underSky/go_triple?${pageContext.request.queryString}" class="list-group-item list-group-item-action"><fmt:message key="triple(luxe)"/></a>
                        <a href="${pageContext.request.contextPath}/underSky/go_luxe?${pageContext.request.queryString}" class="list-group-item list-group-item-action"><fmt:message key="luxe"/></a>
                    </div>

                </div>
            </div>
        </div>
    </div>


    <footer>

        <div class="footermenu">
            <ul class="nav menu mod-list">
                <li class="item-124"><a href="#"><h4><fmt:message key="booking"/></h4></a></li>
                <li class="item-125 alias-parent-active"><a href="#"><h4><fmt:message key="hotel"/></h4></a></li>
                <li class="item-126"><a href="#"><h4><fmt:message key="restaurant"/></h4></a></li>
                <li class="item-127"><a href="#"><h4><fmt:message key="contacts"/></h4></a></li>
            </ul>

        </div>

        <div class="copyright">
            <fmt:message key="copyright"/><fmt:message key="underSky"/>, 2019	</div>
    </footer>



</div>


</body>

</html>

