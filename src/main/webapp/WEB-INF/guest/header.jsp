<%--
  Created by IntelliJ IDEA.
  User: PC
  Date: 02.08.2019
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.curLang}"/>
<fmt:setBundle basename="lang" scope="session"/>
<c:set var="user" value="${sessionScope.sessionUser}"/>
<jsp:useBean id="getParams" scope="page" class="java.lang.String"/>

<script src="${pageContext.request.contextPath}/js/signAjax.js"></script>
<script src="${pageContext.request.contextPath}/js/post.js"></script>

<nav class="navbar navbar-expand-lg navbar-dark mynavbar header shadow-c" style="background-color: #0dbc93">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/underSky/"><fmt:message key="header.title"/></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarResponsive">
            <ul class="navbar-nav ml-auto">

                <li class="nav-item mybutton">
                    <a class="nav-link" href="${pageContext.request.contextPath}/underSky/numbers"><fmt:message key="header.point1"/></a>
                </li>
                <li class="nav-item mybutton">
                    <a class="nav-link" href="${pageContext.request.contextPath}/underSky/go_double"><fmt:message key="header.point2"/></a>
                </li>
                <li class="nav-item dropdown mybutton">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        <fmt:message key="header.language"/>
                    </a>

                    <div class="crystal shadow-c dropdown-menu dropdown-menu-right animate slideIn" aria-labelledby="navbarDropdown">
                        <a class="dropdown-item" onclick="{return renewPage(deleteGetParam('curLang'))}"
                            style="padding-left: 12px" href="#">
                            <img src="${pageContext.request.contextPath}/picture/langs/United-Kingdom.png"
                                 style="margin-right: 24px"/><fmt:message key="header.lang.eng"/>
                        </a>
                        <a class="dropdown-item" onclick="{return renewPage(setGetParam('curLang', 'uk'))}"
                           style="padding-left: 12px;" href="#">
                            <img src="${pageContext.request.contextPath}/picture/langs/Ukraine.png"
                                 style="margin-right: 24px"/><fmt:message key="header.lang.ukr"/>
                        </a>
                    </div>
                </li>
                <li class="nav-item mybutton">
                    <a class="btn nav-link rounded singup" href="${pageContext.request.contextPath}/underSky/go_registration" style="background: #ffc107; color: black;"><fmt:message key="header.signup"/></a>
                </li>
                <li class="dropdown mybutton">
                    <a href="#" class="btn-warning nav-link rounded login" data-toggle="dropdown" style="background: #ffc107; color: black;"><fmt:message key="header.login"/><span class="caret"></span></a>
                    <ul id="login-dp" class="dropdown-menu shadow-c">
                        <li>
                            <div class="row">
                                <div class="col-md-12">
                                    <form class="form" method="post" role="form" accept-charset="UTF-8" id="login-nav">
                                        <div class="form-group">
                                            <label class="sr-only" for="username"></label>
                                            <input id="username" type="text" class="form-control" placeholder="<fmt:message key="header.inplaceholder.email"/>" required>
                                        </div>
                                        <div class="form-group">
                                            <label class="sr-only" for="password"></label>
                                            <input id="password" type="password" class="form-control" placeholder="<fmt:message key="header.inplaceholder.password"/>" required>
                                        </div>
                                        <div class="form-group">
                                            <button id="signBtn" type="button" class="btn btn-primary btn-block"><fmt:message key="header.login"/></button>
                                        </div>
                                    </form>
                                    <div id="errorInp" class="alert alert-danger">
                                        <span id="osp"></span>
                                    </div>
                                </div>
                            </div>
                        </li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
</nav>

