<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>首页</title>
    </head>
    <body>
        注册时间：${SPRING_SECURITY_CONTEXT.authentication.principal.user.passwordExpireTime}<br/>
        密码过期时间：${SPRING_SECURITY_CONTEXT.authentication.principal.user.passwordExpireTime}<br/>
        上次密码修改时间：${SPRING_SECURITY_CONTEXT.authentication.principal.user.passwordUpdateTime}<br/>
    </body>
</html>
