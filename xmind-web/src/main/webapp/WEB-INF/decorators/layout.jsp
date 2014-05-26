<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n.Settings" var="settings"/>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta name="description" content="">
        <meta name="author" content="">
        <!-- The fav icon -->
        <link href='<c:url value="/static/images/favicon.ico"/>' rel="icon" type="image/x-icon"/>
        <link href='<c:url value="/static/images/favicon.ico"/>' rel="shortcut icon" type="image/x-icon" />

        <title>微程序--<sitemesh:write property='title'>无标题</sitemesh:write></title>

        <!-- Bootstrap core CSS -->
        <link href="<c:url value="/static/bootstrap-3.1.1/css/bootstrap.css"/>" rel="stylesheet"/>
        <!--<link href="<c:url value="/static/bootstrap-3.1.1/css/bootstrap-theme.css"/>" rel="stylesheet"/>-->

        <!-- Custom styles for this template -->
        <link href="<c:url value="/static/css/offcanvas.css"/>" rel="stylesheet"/>

        <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
          <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script src="<c:url value="/static/jquery-1.11.0/jquery-1.11.0.min.js"/>"></script>
        <script src="<c:url value="/static/bootstrap-3.1.1/js/bootstrap.js"/>"></script>
        <script src="<c:url value="/static/js/offcanvas.js"/>"></script>
    <sitemesh:write property='head'></sitemesh:write>
</head>

<body>
    <div class="navbar navbar-fixed-top navbar-inverse">
        <div class="navbar-inner">
            <div class="container-fluid">
                <div class="navbar-header">
                    <a class="navbar-brand" href="<c:url value="/"/>">微程序</a>
                </div>
                <div class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <!-- 不能太多项目，否则会导致页面布局混乱，为了适应多种终端建议最多6项 -->
                        <li><a href="<c:url value="/"/>">首页</a></li>
                        <li><a href="<c:url value="/about"/>">关于</a></li>
                        <li><a href="<c:url value="/contact"/>">联系</a></li>
                    </ul>
                    <div  class="navbar-right">
                        <c:if test="${empty SPRING_SECURITY_CONTEXT}">
                            <ul class="nav navbar-nav">
                                <li>
                                    <a href="<c:url value='/identity/register'/>">注册</a>
                                </li>
                                <li>
                                    <a href="<c:url value='/identity/login'/>">登陆</a>
                                </li>
                            </ul>
                        </c:if>
                        <c:if test="${!empty SPRING_SECURITY_CONTEXT}">
                            <!-- user dropdown starts -->
                            <ul class="nav navbar-nav">
                                <li id="fat-menu" class="dropdown">
                                    <a role="button" class="dropdown-toggle" style="cursor: pointer;" data-toggle="dropdown">
                                        ${SPRING_SECURITY_CONTEXT.authentication.principal.username}
                                        <b class="caret"></b>
                                    </a>
                                    <ul class="dropdown-menu" role="menu">
                                        <li><a href="<c:url value="/identity/profile"/>"><span class="icon icon-color icon-profile"></span>
                                                <fmt:message key="option.user.profile" bundle="${settings}"/>
                                            </a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="<c:url value="/identity/do_logout"/>">
                                                <span class="icon icon-color icon-locked"></span>
                                                <fmt:message key="option.user.logout" bundle="${settings}"/>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </c:if>
                    </div>
                    <!-- user dropdown ends -->
                </div><!-- /.nav-collapse -->
            </div>
        </div>
    </div>

    <div class="container">

        <sitemesh:write property='body'>空的网页</sitemesh:write>


        <hr>
        <footer>
            <p>&copy; 2014</p>
        </footer>
    </div><!--/.container-->
</body>
</html>
