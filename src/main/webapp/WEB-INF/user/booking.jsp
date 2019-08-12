<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 08.08.2019
  Time: 12:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang"/>
<html lang="${sessionScope.curLang}">
<head>
    <title><fmt:message key="booking"/></title>
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/styles.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/booking_validation.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>
    <script src="${pageContext.request.contextPath}/js/bookingAjax.js"></script>


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

            var newDate = new Date;
            newDate.setDate(newDate.getDate()+1);
            $('#dateFrom').datepicker('setDate', newDate);
            newDate.setDate(newDate.getDate()+1);
            $('#dateTo').datepicker('setDate', newDate);
        } );
    </script>
</head>
<body style="background-image: url(${pageContext.request.contextPath}/picture/backgrounds/sky3.jpg)">
<jsp:include page="/WEB-INF/user/header.jsp"/>

<div class="container" >
    <div class="row">
        <div class="col-lg-10 col-xl-9 mx-auto">
            <div class="card card-signin flex-row ">
                <%--<div class="card-img-left d-none d-md-flex">
                    <!-- Background image for card set in CSS! -->
                </div>--%>
                <div class="card-body">
                    <h5 class="card-title text-center"><fmt:message key="booking.intro"/></h5>
                    <form class="form-signin">
                        <div class="form-label-group">
                            <label for="name"><fmt:message key="booking.name.field"/></label>
                            <input type="text" id="name" name="name" class="form-control" pattern="^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$" placeholder="<fmt:message key="booking.name.field"/>" required autofocus>
                            <span class="error" aria-live="polite"></span>
                        </div>

                        <div class="form-label-group">
                            <label for="surname"><fmt:message key="booking.surname.field"/></label>
                            <input type="text" id="surname" name="surname" class="form-control" pattern="^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$" placeholder="<fmt:message key="booking.surname.field"/>" required>
                        </div>

                        <hr>

                        <ul class="navbar-nav ml-auto" >
                            <li class="nav-item">
                                <label for="dateFrom"><fmt:message key="booking.dateFrom"/></label>
                                <input id="dateFrom" name="dateFrom" class="form-control" required>
                            </li>
                            <li class="nav-item">
                                <label for="dateTo"><fmt:message key="booking.dateTo"/></label>
                                <input id="dateTo" name="dateTo" class="form-control" required>
                            </li>
                        </ul>
                        <hr>

                        <div class="form-label-group">
                            <label for="count"><fmt:message key="booking.countOfPeople"/></label>
                            <select id="count" name="count" size="1" class="form-control" required>
                                <option value="1" selected="selected">1</option>
                                <option value="2">2</option>
                                <option value="3">3</option>
                            </select>
                            <label for="kids"><fmt:message key="booking.countOfChildren"/></label>&nbsp;
                                <select id="kids" name="kids" size="1" class="form-control" required>
                                    <!--  <option value="0" disabled="disabled">--Выбрать--</option>-->
                                    <option value="0" selected="selected">нет</option>
                                    <option value="1">1</option>
                                    <option value="2">2</option>
                                </select>
                            <label for="typeOfNumber"><fmt:message key="booking.typeOfNumber"/></label>&nbsp;
                            <select id="typeOfNumber" name="typeOfNumber" size="1" class="form-control" required>
                                <!--  <option value="0" disabled="disabled">--Выбрать--</option>-->
                                <option value="<fmt:message key="single"/>" selected="selected"><fmt:message key="single"/> </option>
                                <option value="<fmt:message key="double"/>"><fmt:message key="double"/> </option>
                                <option value="<fmt:message key="double.1child"/>"><fmt:message key="double.1child"/> </option>
                                <option value="<fmt:message key="double.2children"/>"><fmt:message key="double.2children"/> </option>
                                <option value="<fmt:message key="triple(luxe)"/>"><fmt:message key="triple(luxe)"/> </option>
                                <option value="<fmt:message key="luxe"/> "><fmt:message key="luxe"/> </option>
                            </select>
                        </div>

                        <div class="form-label-group">
                            <label for="note"><fmt:message key="booking.note"/></label>
                            <textarea id="note" name="note" class="form-control" placeholder="<fmt:message key="booking.note.desc"/>" ></textarea>
                        </div>

                        <nav class="navbar navbar-expand-lg mynavbar header" style="padding-bottom: 20px;">
                            <div class="collapse navbar-collapse" id="navbarResponsive">
                                <ul class="navbar-nav ml-auto">
                                    <li class="nav-item mybutton">
                                        <a class="nav-link" href="${pageContext.request.contextPath}/underSky/?${pageContext.request.queryString}"><fmt:message key="header.home"/>
                                            <span class="sr-only">(current)</span>
                                        </a>
                                    </li>
                                    <li class="nav-item dropdown mybutton">
                                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <fmt:message key="header.language"/>
                                        </a>
                                        <!-- Here's the magic. Add the .animate and .slide-in classes to your .dropdown-menu and you're all set! -->
                                        <div class="crystal shadow-c dropdown-menu dropdown-menu-right animate slideIn" aria-labelledby="navbarDropdown">
                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/underSky/go_booking"
                                               style="padding-left: 12px;">
                                                <img src="${pageContext.request.contextPath}/picture/langs/United-Kingdom.png"
                                                     style="margin-right: 24px"/><fmt:message key="header.lang.eng"/>
                                            </a>
                                            <a class="dropdown-item" href="${pageContext.request.contextPath}/underSky/go_booking?curLang=uk"
                                               style="padding-left: 12px;">
                                                <img src="${pageContext.request.contextPath}/picture/langs/Ukraine.png"
                                                     style="margin-right: 24px"/><fmt:message key="header.lang.ukr"/>
                                            </a>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </nav>
                        <div id="errorInp" class="alert alert-danger alert-span" style="border-radius: 5rem;">
                            <span id="osp" class="alert-span"></span>
                        </div>
                        <button id="booking-btn" class="btn btn-lg btn-primary btn-block text-uppercase" type="button"><fmt:message key="booking"/></button>

                    </form>

                </div>

            </div>
        </div>
    </div>
</div>

</body>
</html>
