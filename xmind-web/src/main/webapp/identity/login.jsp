<%-- 
    Document   : login
    Created on : 2014-6-2, 13:29:19
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
        <title><fmt:message key="web.label.signIn" bundle="${commons}"/></title>
    </head>
    <body>
        <c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
            <script>alert("${sessionScope.SPRING_SECURITY_LAST_EXCEPTION.message}");</script>
        </c:if>
        <c:remove scope="session" var="SPRING_SECURITY_LAST_EXCEPTION"/>
        <div>
            <div class="row">
                <div class="col-xs-4">
                    <blockquote style="border-left: 0px">
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;将程序员的思想表现为一个个的微程序，每个微程序都是为了解决实际的问题！</p>
                        <p>&nbsp;&nbsp;&nbsp;&nbsp;自由之思想，独立的精神是程序员的根本，我们将这种自由和独立，融入到一个大集体形成更庞大的力量！</p>
                    </blockquote>
                </div>
                <div class="col-xs-4">
                    <form id="localSigninForm" class="form-horizontal" role="form" method="POST" action="<c:url value='/identity/do_login'/>">
                        <h2 class="form-signin-heading">请先登陆微程序</h2>
                        <fieldset style="text-align: left;">
                            <div class="form-group">
                                <label for="email" class="col-sm-2 control-label">Email</label>
                                <div class="col-sm-10">
                                    <input type="email" class="form-control" id="email" name="j_username" placeholder="Email" required="required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">密码</label>
                                <div class="col-sm-10">
                                    <input type="password" class="form-control" id="password" name="j_password" placeholder="密码" required="required"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-10">
                                    <div class="checkbox">
                                        <label>
                                            <input type="checkbox" name="_spring_security_remember_me" value="true"/>记住
                                        </label>
                                    </div>
                                </div>
                            </div>
                        </fieldset>
                        <div class="form-actions text-right">
                            <button class="btn btn-primary" type="submit">登陆</button>
                            <a id="registerButton" class="btn">注册</a>
                        </div>
                    </form>
                    <!-- 注册对话框开始 -->
                    <div class="modal fade" id="registerModal" tabindex="-1" role="dialog" aria-labelledby="registerModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal">×</button>
                                    <h3>注册新用户</h3>
                                </div>
                                <div class="modal-body">
                                    <jsp:include page="/WEB-INF/jsp/identity/registerForm.jsp"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script>
                        $('#registerButton').click(function(e) {
                            e.preventDefault();
                            $('#registerModal').modal();
                        });
                    </script>
                    <!-- 注册对话框结束 -->
                </div>
                <div class="col-xs-4" style="height: 230px; vertical-align: bottom; border-left: gray solid 1px; position:relative;">
                    最新上线的功能：<br/>
                    库存管理：最简单的库存管理，实现了基本的进出库录入以及库存统计。
                </div>
            </div>
        </div>
    </body>
</html>
