<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 12.08.2019
  Time: 17:32
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
    <title>Reserved rooms</title>
    <link href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/numbers.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/applications.css" type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/post.js"></script>
    <script src="${pageContext.request.contextPath}/js/orders.js"></script>

    <script src="${pageContext.request.contextPath}/js/jquery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
    <link href="${pageContext.request.contextPath}/css/jquery-ui.css" rel="stylesheet"/>

    <script>
        $( function() {
            <c:choose>
            <c:when test="${sessionScope.curLang.equals('uk') }">
            $( "#dateFrom" ).datepicker({ dateFormat: 'dd/mm/yy' });
            $( "#dateTo" ).datepicker({ dateFormat: 'dd/mm/yy' });
            </c:when>
            <c:otherwise>
            $( "#dateFrom" ).datepicker();
            $( "#dateTo" ).datepicker();
            </c:otherwise>
            </c:choose>

            var newDate = new Date();
            newDate.setDate(newDate.getDate()+1);
            $('#dateFrom').datepicker('setDate', newDate);
            newDate.setDate(newDate.getDate()+1);
            $('#dateTo').datepicker('setDate', newDate);
        } );
    </script>

</head>
<body>
<jsp:include page="/WEB-INF/admin/header.jsp"/>

<section class="pricing py-5">
    <div class="wrap-cont">
        <!-- Free Tier -->
        <div class="card-bg-color  mb-5 mb-lg-0">
            <div class="card-body inner-bg">

                <div class="row">
                    <form action="${pageContext.request.contextPath}/underSky/search">
                        <label for="dateFrom"><fmt:message key="from"/></label>
                        <input id="dateFrom" name="dateFrom" class="" style="border-radius: 1em" required>
                        <label for="dateTo"><fmt:message key="to"/></label>
                        <input id="dateTo" name="dateTo" class="" style="border-radius: 1em" required>
                        <button type="submit" class="btn btn-primary"><fmt:message key="search"/> </button>
                    </form>

                </div>


                <div class="table-container">
                    <div class="my-tk-header">
                        <h3 class="font-weight-light"><fmt:message key="rooms"/></h3>
                        <br>
                        <h5>
                            ${sessionScope.date1}
                            :
                            ${sessionScope.date2}
                        </h5>
                    </div>
                    <div class="header-row">
                        <div class="tk-type">
                            <fmt:message key="Id"/>
                        </div>
                        <div class="tk-type">
                            <fmt:message key="idUser"/>
                        </div>
                        <div class="tk-shortType">
                            <fmt:message key="Room.id"/>
                        </div>
                        <div class="tk-price">
                            <fmt:message key="booking.dateFrom"/>
                        </div>
                        <div class="tk-amount">
                            <fmt:message key="booking.dateTo"/>
                        </div>
                    </div>
                    <c:forEach items="${reservedRoomsAndBills}" var="number">
                            <div class="movie-row <c:if test="${number.value.status == true}">movie-row-green</c:if>
                                                    <c:if test="${number.value.status == false}">movie-row-orange</c:if>">
                                <div class="tk-type">
                                       ${number.key.id}
                                </div>

                                <div class="tk-type">
                                   ${number.key.userId}
                                </div>
                                <div class="tk-shortType">
                                        ${number.key.roomId}
                                </div>
                                <div class="tk-price">
                                    <c:choose>
                                        <c:when test="${sessionScope.curLang.equals('uk') }">
                                            <tags:localDate date="${number.key.date_from}" pattern="dd/MM/yyyy"/>
                                        </c:when>
                                        <c:otherwise>
                                            <tags:localDate date="${number.key.date_from}" pattern="MM/dd/yyyy"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="tk-amount">
                                    <c:choose>
                                        <c:when test="${sessionScope.curLang.equals('uk') }">
                                            <tags:localDate date="${number.key.date_before}" pattern="dd/MM/yyyy"/>
                                        </c:when>
                                        <c:otherwise>
                                            <tags:localDate date="${number.key.date_before}" pattern="MM/dd/yyyy"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="delete-btn">
                                    <button type="button" class="btn btn-danger circu" onclick="notify_cancel('${number.key.id}');appearDivById('sure-span')">
                                        <img src="${pageContext.request.contextPath}/picture/icons/trash30.png"/>
                                    </button>
                                </div>
                            </div>

                    </c:forEach>
                    <div id="sure-span" class="sure-span" style="display:none">
                        <div class="order-top">
                            <h3 class="font-weight-normal"><fmt:message key="delete.reservation"/></h3>
                            <p class="font-weight-normal disp"><fmt:message key="reservation.number"/></p> -
                            <p id="reservation_id" class="disp"></p>
                        </div>

                        <div class="order-bottom">
                            <button class="btn btn-danger" onclick="post('/remove_reservation', {reservedRoomId : document.getElementById('reservation_id').innerText}, 'post')">
                                <fmt:message key="remove"/>
                            </button>
                            <button class="btn btn-outline-secondary" onclick="cls_span('sure-span')">
                                <fmt:message key="cancel"/>
                            </button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

</body>
</html>

