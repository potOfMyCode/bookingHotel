<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 11.08.2019
  Time: 20:30
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
    <title>Pay</title>

    <link href="${pageContext.request.contextPath}/css/styles.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/all.css" type="text/css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet" type="text/css"/>
    <link href="${pageContext.request.contextPath}/css/payment.css" rel="stylesheet" type="text/css"/>
    <script src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.js"></script>

</head>
<body style="background-image: url(${pageContext.request.contextPath}/picture/backgrounds/sky2.jpg)">

<jsp:include page="/WEB-INF/user/header.jsp"/>

            <div class="container">
                <c:choose>
                    <c:when test="${sessionScope.curBill.status == false }">
                        <div class="row">
                            <div class="col-1-2 data">
                                <div class="myText">
                                    <span><fmt:message key="id.order"/> </span>
                                    <span>${curReservedRoom.id}</span>
                                </div>
                                <div class="myText">
                                    <span><fmt:message key="total" /></span>
                                    <span>
                                <c:choose>
                                    <c:when test="${sessionScope.curLang.equals('en') }">
                                        <fmt:formatNumber pattern="0.00" value="${curBill.total/2600}"></fmt:formatNumber> $
                                    </c:when>
                                    <c:otherwise>
                                        <fmt:formatNumber pattern="0.00" value="${curBill.total/100}"></fmt:formatNumber> грн
                                    </c:otherwise>
                                </c:choose>
                            </span>
                                </div>
                                <div class="myText">
                                    <span><fmt:message key="booking.typeOfNumber"/> </span>
                                    <span>${curApplication.typeOfNumber}</span>
                                </div>
                                <div class="myText">
                                    <span><fmt:message key="number.room"/> </span>
                                    <span>${curReservedRoom.roomId}</span>
                                </div>
                                <div class="myText">
                                    <span><fmt:message key="booking.dateFrom"/></span>
                                    <c:choose>
                                        <c:when test="${sessionScope.curLang.equals('uk') }">
                                            <tags:localDate date="${curReservedRoom.date_from}" pattern="dd/MM/yyyy"/>
                                        </c:when>
                                        <c:otherwise>
                                            <tags:localDate date="${curReservedRoom.date_from}" pattern="MM/dd/yyyy"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="myText">
                                    <span><fmt:message key="booking.dateTo"/></span>
                                    <c:choose>
                                        <c:when test="${sessionScope.curLang.equals('uk') }">
                                            <tags:localDate date="${curReservedRoom.date_before}" pattern="dd/MM/yyyy"/>
                                        </c:when>
                                        <c:otherwise>
                                            <tags:localDate date="${curReservedRoom.date_before}" pattern="MM/dd/yyyy"/>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="img-cards">
                                    <img id="img_card" src="${pageContext.request.contextPath}/picture/icons/cards.jpg">
                                </div>
                            </div>

                            <div class="col-1-2 paycard">
                                <form autocomplete="off" method="post" action="${pageContext.request.contextPath}/underSky/payByCard" name="pay_form" id="pay_form" data-cid="lfqh" class="form-group">
                                    <input type="hidden" name="step" value="pay">
                                    <input type="hidden" name="type_id" value="1">
                                    <input type="hidden" name="bank_id" value="">
                                    <input type="hidden" name="tkn" value="41bbcfbc451ab850b07b2664700cd247">

                                    <input type="hidden" name="cvv2" value="">
                                    <input type="hidden" name="cvv_map_used" value="">
                                    <div class="card-form">
                                        <div class="card-list">
                                            <div class="card-block guest">
                                                <div class="card">
                                                    <div class="title-block" style="text-align: center"><fmt:message key="pay.by.card"/><span class="amount-block"></span></div>
                                                    <div class="num-block">
                                                        <span><fmt:message key="pay.number.card"/></span><br>
                                                        <input name="cardcode" id="cardcode" type="tel" value="" pattern="\d{4}[\s]?\d{4}[\s]?\d{4}[\s]?\d{4}" placeholder="XXXX XXXX XXXX XXXX" maxlength="19" class="form-control input-lg" required>
                                                    </div>
                                                    <div class="dates-block">
                                                        <span><fmt:message key="pay.Validity"/></span><br>
                                                        <input name="expire_month" id="valid" type="tel" maxlength="2" pattern="\d{2}" value="" required> /
                                                        <input type="tel" id="valid2" name="expire_year" maxlength="2" pattern="\d{2}" value="" required>
                                                    </div>
                                                    <div class="cvv-block">
                                                        <span>CVV2</span><br>
                                                        <input id="cvv" class="input-sm" name="cvv[new]" type="password" value="" placeholder="•••" autocomplete="off" maxlength="3" pattern="\d{3}" required>

                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <p class="total-amount"><fmt:message key="total" />
                                        <span><strong>
                                    <c:choose>
                                        <c:when test="${sessionScope.curLang.equals('en') }">
                                            <fmt:formatNumber pattern="0.00" value="${curBill.total/2600}"></fmt:formatNumber> $
                                        </c:when>
                                        <c:otherwise>
                                            <fmt:formatNumber pattern="0.00" value="${curBill.total/100}"></fmt:formatNumber> грн
                                        </c:otherwise>
                                    </c:choose>
                                </span></strong></p>

                                    <p class="continue">

                                    <div class="pay-button">
                                        <input id="service_confirm_payment_button" type="submit" class="btn btn-primary form-control form-group" value="<fmt:message key="Pay"/>">
                                    </div>

                                    </p>

                                </form>
                            </div>


                        </div>
                    </c:when>
                    <c:otherwise>
                        <div class="row" style="text-align: center">

                            <div class="already_pay">
                                <h2>
                                    <fmt:message key="already.pay.first"/>
                                    <span>${curReservedRoom.roomId}</span>
                                    <fmt:message key="already.pay.second"/>
                                    <c:choose>
                                        <c:when test="${sessionScope.curLang.equals('uk') }">
                                            <tags:localDate date="${curReservedRoom.date_from}" pattern="dd/MM/yyyy"/>
                                        </c:when>
                                        <c:otherwise>
                                            <tags:localDate date="${curReservedRoom.date_from}" pattern="MM/dd/yyyy"/>
                                        </c:otherwise>
                                    </c:choose>
                                </h2>

                                <h4 class="font-weight-normal" style="margin-top: 35px;">
                                    <a class="non" href="#" onclick="go_back()"><fmt:message key="go.back"/> </a><br>
                                    <a class="" href="${pageContext.request.contextPath}/underSky/"><fmt:message key="back.home"/></a>
                                </h4>
                            </div>

                        </div>
                    </c:otherwise>
                </c:choose>



            </div>




</body>
</html>