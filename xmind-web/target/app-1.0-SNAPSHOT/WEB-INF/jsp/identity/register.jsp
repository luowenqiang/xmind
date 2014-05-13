<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <title>新用户注册</title>
        <script src="<c:url value="/static/jquery-validation-1.11.1/jquery.validate.js"/>"></script>
        <script src="<c:url value="/static/jquery-validation-1.11.1/localization/messages_zh.js"/>"></script>
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
    </div><!--/.fluid-container-->
</body>
</html>
