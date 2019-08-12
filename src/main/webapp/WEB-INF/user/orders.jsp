<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 08.08.2019
  Time: 12:52
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
    <title>Orders</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link  href="${pageContext.request.contextPath}/css/applications.css" type="text/css" rel="stylesheet">
    <link  href="${pageContext.request.contextPath}/css/showtimes.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/orders.js"></script>

</head>
<body>
<jsp:include page="/WEB-INF/user/header.jsp"/>
<div class="container">
    <section class="pricing py-5">
        <div class="wrap-cont">
            <!-- Free Tier -->
            <div class="card-bg-color  mb-5 mb-lg-0">
                <div class="card-body inner-bg">
                    <div class="table-container">
                        <div class="danger" style=" background-color: #e7f3fe; border-left: 6px solid #2196F3; height: 10%; width: 770px; color: darkgreen;">
                            <p><strong>Info!</strong> <fmt:message key="orders.info"/> </p>
                        </div>
                        <div class="my-tk-header">
                            <h3 class="font-weight-light"><fmt:message key="header.applications"/></h3>
                        </div>
                        <div class="header-row">
                            <div class="bk-id">
                                <fmt:message key="id"/>
                            </div>
                            <div class="tk-mov-name">
                                <fmt:message key="typeOfNumber"/>
                            </div>
                            <div class="tk-mov-date">
                                <fmt:message key="booking.dateFrom"/>
                            </div>
                            <div class="tk-mov-date">
                                <fmt:message key="booking.dateTo"/>
                            </div>
                            <div class="tk-mov-date">
                                <fmt:message key="dateOfBooking"/>
                            </div>
                        </div>
                        <c:forEach items="${applicationsForCurrentUser}" var="application">

                            <c:choose>
                                <c:when test="${application.status == false}">
                                    <div class="movie-row movie-row-red">
                                        <div class="bk-id">
                                                ${application.id}
                                        </div>
                                        <div class="tk-mov-name">
                                                ${application.typeOfNumber}
                                        </div>
                                        <div class="tk-mov-date">
                                            <c:choose>
                                                <c:when test="${sessionScope.curLang.equals('uk') }">
                                                    <tags:localDate date="${application.dateFrom}" pattern="dd/MM/yyyy"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <tags:localDate date="${application.dateFrom}" pattern="MM/dd/yyyy"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="tk-mov-date">
                                            <c:choose>
                                                <c:when test="${sessionScope.curLang.equals('uk') }">
                                                    <tags:localDate date="${application.dateTo}" pattern="dd/MM/yyyy"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <tags:localDate date="${application.dateTo}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="tk-mov-date">
                                            <c:choose>
                                                <c:when test="${sessionScope.curLang.equals('uk') }">
                                                    <tags:localDate date="${application.dateOfBooking}" pattern="dd/MM/yyyy"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <tags:localDate date="${application.dateOfBooking}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="seeMore-btn">
                                            <button class="btn btn-success" type="button" onclick="notify_info_user('${application.id}','${application.name}','${application.surname}',
                                                    '${application.amountPeople}','${application.amounChildren}','${application.typeOfNumber}',
                                                    '${application.note}','${application.dateFrom}','${application.dateTo}','${application.dateOfBooking}',
                                                    '${application.status}'); appearDivById('sure-span')">
                                                <img src="${pageContext.request.contextPath}/picture/icons/seeMore.jpg"/>
                                            </button>
                                        </div>
                                        <button type="button" class="btn btn-danger" onclick="notify_cancel_application('${application.id}');appearDivById('sure-span-delete')">
                                            <img src="${pageContext.request.contextPath}/picture/icons/trash30.png"/>
                                        </button>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="movie-row movie-row-green">
                                        <div class="bk-id">
                                                ${application.id}
                                        </div>
                                        <div class="tk-mov-name">
                                                ${application.typeOfNumber}
                                        </div>
                                        <div class="tk-mov-date">
                                            <c:choose>
                                                <c:when test="${sessionScope.curLang.equals('uk') }">
                                                    <tags:localDate date="${application.dateFrom}" pattern="dd/MM/yyyy"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <tags:localDate date="${application.dateFrom}" pattern="MM/dd/yyyy"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="tk-mov-date">
                                            <c:choose>
                                                <c:when test="${sessionScope.curLang.equals('uk') }">
                                                    <tags:localDate date="${application.dateTo}" pattern="dd/MM/yyyy"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <tags:localDate date="${application.dateTo}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="tk-mov-date">
                                            <c:choose>
                                                <c:when test="${sessionScope.curLang.equals('uk') }">
                                                    <tags:localDate date="${application.dateOfBooking}" pattern="dd/MM/yyyy"/>
                                                </c:when>
                                                <c:otherwise>
                                                    <tags:localDate date="${application.dateOfBooking}"/>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="seeMore-btn">

                                            <c:choose>
                                                <c:when test="${application.note == null }">
                                                    <button type="button" onclick="notify_info_user_booked_room('${application.id}','${application.typeOfNumber}','${application.dateFrom}',
                                                            '${application.dateTo}'); appearDivById('sure-span-room')">
                                                        <img src="${pageContext.request.contextPath}/picture/icons/room.jpg"/>
                                                    </button>
                                                </c:when>
                                                <c:otherwise>
                                                    <button type="button" onclick="notify_info_user_booked_room_refuse('${application.id}','${application.typeOfNumber}','${application.dateFrom}',
                                                            '${application.dateTo}','${application.note}'); appearDivById('sure-span-room-refuse')">
                                                        <img src="${pageContext.request.contextPath}/picture/icons/room.jpg"/>
                                                    </button>
                                                </c:otherwise>
                                            </c:choose>


                                        </div>
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>

                        <div id="sure-span" class="sure-span" style="display:none">
                            <div class="order-top">
                                <h3 class="font-weight-normal"><fmt:message key="header.application"/></h3>
                                <p class="font-weight-normal disp"><fmt:message key="id"/></p> -
                                <p id="app_id" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="booking.name.field"/></p> -
                                <p id="app_name" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="booking.surname.field"/></p> -
                                <p id="app_surname" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="amountPeople"/></p> -
                                <p id="app_people" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="amountChildren"/></p> -
                                <p id="app_children" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="typeOfNumber"/></p> -
                                <p id="app_type" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="booking.note"/></p> -
                                <p id="app_note" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="booking.dateFrom"/></p> -
                                <p id="app_dateFrom" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="booking.dateTo"/></p> -
                                <p id="app_dateTo" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="dateOfBooking"/></p> -
                                <p id="app_dateOfBooking" class="disp"></p>
                                <br>
                                <p class="font-weight-normal disp"><fmt:message key="staus"/></p> -
                                <p id="app_status" class="disp"></p>
                            </div>

                            <div class="order-bottom">
                                <button class="btn btn-success" onclick="cls_span('sure-span')">
                                    <fmt:message key="Ok"/>
                                </button>
                                <button class="btn btn-outline-secondary" onclick="cls_span('sure-span')">
                                    <fmt:message key="cancel"/>
                                </button>
                            </div>
                        </div>

                        <div id="sure-span-delete" class="sure-span" style="display:none">
                            <div class="order-top">
                                <h3 class="font-weight-normal"><fmt:message key="delete.application"/></h3>
                                <p class="font-weight-normal disp"><fmt:message key="id.application"/></p> -
                                <p id="application_id" class="disp"></p>
                            </div>

                            <div class="order-bottom">
                                <button class="btn btn-danger" onclick="post('/remove_application', {applicationId : document.getElementById('application_id').innerText}, 'post')">
                                    <fmt:message key="remove"/>
                                </button>
                                <button class="btn btn-outline-secondary" onclick="cls_span('sure-span-delete')">
                                    <fmt:message key="cancel"/>
                                </button>
                            </div>
                        </div>

                        <div id="sure-span-room-refuse" class="sure-span" style="display:none">

                            <div class="order-top">
                                <h3 class="font-weight-normal"><fmt:message key="header.application"/></h3>
                                <p class="font-weight-normal disp"><fmt:message key="id"/></p> -
                                <p id="room_id_app_refuse" class="disp"></p>
                                <br/>
                                <p class="font-weight-normal disp"><fmt:message key="typeOfNumber"/></p> -
                                <p id="room_type_refuse" class="disp"></p>
                                <br/>
                                <p class="font-weight-normal disp"><fmt:message key="booking.dateFrom"/></p> -
                                <p id="room_date_from_refuse" class="disp"></p>
                                <br/>
                                <p class="font-weight-normal disp"><fmt:message key="booking.dateTo"/></p> -
                                <p id="room_date_to_refuse" class="disp"></p>
                                <br/>
                                <p id="room_note_refuse" class="disp" style="color: orangered"></p>
                            </div>

                            <div class="pay-bottom">
                                <button class="btn btn-success" onclick="post('/go_booking', {}, 'post')">
                                    <fmt:message key="give.another.app"/>
                                </button>
                                <button class="btn btn-outline-secondary" onclick="cls_span('sure-span-room-refuse')">
                                    <fmt:message key="cancel"/>
                                </button>
                            </div>

                        </div>

                        <div id="sure-span-room" class="sure-span" style="display:none">

                            <div class="order-top">
                                <h3 class="font-weight-normal"><fmt:message key="header.application"/></h3>
                                <p class="font-weight-normal disp"><fmt:message key="id"/></p> -
                                <p id="room_id_app" class="disp"></p>
                                <br/>
                                <p class="font-weight-normal disp"><fmt:message key="typeOfNumber"/></p> -
                                <p id="room_type" class="disp"></p>
                                <br/>
                                <p class="font-weight-normal disp"><fmt:message key="booking.dateFrom"/></p> -
                                <p id="room_date_from" class="disp"></p>
                                <br/>
                                <p class="font-weight-normal disp"><fmt:message key="booking.dateTo"/></p> -
                                <p id="room_date_to" class="disp"></p>
                                <br/>
                                <p class="font-weight-normal disp"><fmt:message key="pay.info"/></p>
                            </div>

                            <div class="pay-bottom">
                                <button class="btn btn-success" onclick="post('/go_pay', {applicationId : document.getElementById('room_id_app').innerText}, 'post')">
                                    <fmt:message key="Pay"/>
                                </button>
                                <button class="btn btn-outline-secondary" onclick="cls_span('sure-span-room')">
                                    <fmt:message key="cancel"/>
                                </button>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
</div>


</body>
</html>