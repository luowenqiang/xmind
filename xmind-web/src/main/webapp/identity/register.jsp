<%-- 
    Document   : register
    Created on : 2014-6-2, 13:29:00
    Author     : LuoWenqiang
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setBundle basename="i18n.Commons" var="commons"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><fmt:message key="web.label.register" bundle="${commons}"/></title>
    </head>
    <body>
        <c:if test="${not empty illegalArgumentException}">
            <script>alert("${illegalArgumentException.message}");</script>
        </c:if>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="well">
                    <h2>只需输入邮件地址和密码即可快速注册</h2>
                    <jsp:include page="/WEB-INF/jsp/identity/registerForm.jsp"/>
                </div><!--/span-->
            </div><!--/row-->
            <div class="col-md-3"></div>
        </div><!--/fluid-row-->
    </body>
</html>
