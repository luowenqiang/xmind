<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${title}</title>
    </head>
    <body>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="well">
                    ${msg}<br/>
                    <c:if test="${!empty redirect}">
                        5秒后自动跳转，如果不能自动跳转请点击<a href="<c:url value='${redirect}'/>">这里</a>继续。
                    </c:if>
                </div><!--/span-->
            </div><!--/row-->
            <div class="col-md-3"></div>
        </div><!--/fluid-row-->
    </div><!--/.fluid-container-->
</body>
</html>
