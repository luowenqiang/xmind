<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${result.title}</title>
    </head>
    <body>
        <div class="row">
            <div class="col-md-3"></div>
            <div class="col-md-6">
                <div class="well">
                    ${result.message}
                </div><!--/span-->
            </div><!--/row-->
            <div class="col-md-3"></div>
        </div><!--/fluid-row-->
    </div><!--/.fluid-container-->
</body>
</html>
