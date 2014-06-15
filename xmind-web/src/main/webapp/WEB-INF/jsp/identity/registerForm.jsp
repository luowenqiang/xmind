<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<form id="registerForm" action="<c:url value='/identity/register'/>" method="post" class="form-horizontal" role="form">
    <style>
        label.error{
            color: red;
            font-weight: normal;
        }
    </style>
    <fieldset style="text-align: left;">
        <div class="form-group">
            <label for="inputEmail" class="col-sm-2 control-label">Email</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="inputEmail" name="email" placeholder="Email" required="required"/>
            </div>
            <c:if test="${not empty error_field_email}">
                <label class="error" for="inputEmail">
                    <script>alert("${error_field_email}");</script>
                </label>
            </c:if>
        </div>
        <div class="form-group">
            <label for="inputPassword" class="col-sm-2 control-label">密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="inputPassword" name="password" placeholder="密码" required="required" minlength="6" />
            </div>
            <c:if test="${not empty error_field_password}">
                <label class="error" for="inputPassword">
                    <script>alert("${error_field_password}");</script>
                </label>
            </c:if>
        </div>
        <div class="form-group">
            <label for="confirmPassword" class="col-sm-2 control-label">确认密码</label>
            <div class="col-sm-10">
                <input type="password" class="form-control" id="confirmPassword" name="confirmPassword" placeholder="请再次输入和上面一样的密码" required="required"/>
            </div>
            <c:if test="${not empty error_field_confirmPassword}">
                <label class="error" for="confirmPassword">
                    <script>alert("${error_field_confirmPassword}");</script>
                </label>
            </c:if>
        </div>
    </fieldset>
    <div class="modal-footer">
        <button class="btn btn-primary" onclick="return validateAndSubmit();" type="submit">注册</button>
        <button type="reset" class="btn">重置</button>
    </div>
</form>
<script>
    $().ready(function() {
        $("#registerForm").validate({
            rules: {
                confirmPassword: {
                    equalTo: "#inputPassword"
                }
            },
            messages: {
                confirmPassword: {
                    equalTo: "请和\"密码\"框中输入相同的密码"
                }
            }
        });
    });
    function validateAndSubmit(){
        return true;
    }
</script>

