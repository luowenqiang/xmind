<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>账户未激活</title>
    </head>
    <body>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="well">
                    激活邮件已发送，请打开邮箱收取新收件，按照邮件中的提示进行激活。<br/>
                    如果未收到激活邮件，点击<a href="<c:url value='/identity/active/resend?email=${email}'/>">这里</a>重新发送激活邮件。
                </div><!--/span-->
            </div><!--/row-->
            <div class="col-md-3"></div>
        </div><!--/fluid-row-->
    </div><!--/.fluid-container-->
</body>
</html>
