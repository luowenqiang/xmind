<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="org.springframework.security.core.AuthenticationException"%>
<%@page import="org.springframework.security.authentication.BadCredentialsException"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>登陆</title>

        <style type="text/css">
            .login_btn{display:inline-block;background-repeat:no-repeat;font-size:12px;text-decoration:none;color:#055675;}
            .login_btn00{width:230px;height:48px;background-image:url(http://mat1.gtimg.com/app/opent/images/websites/login/login_icon_1.png);}
            .login_btn01{width:170px;height:32px;background-image:url(http://mat1.gtimg.com/app/opent/images/websites/login/login_icon_2.png);}
            .login_btn02{width:120px;height:24px;background-image:url(http://mat1.gtimg.com/app/opent/images/websites/login/login_icon_3.png);}
            .login_btn03{width:105px;height:16px;background-image:url(http://mat1.gtimg.com/app/opent/images/websites/login/login_icon_4.png);}
            .login_btn10{padding-left:20px;background-image:url(http://mat1.gtimg.com/app/opent/images/websites/share/weiboicon16.png);}
            .login_btn20{}
            img{border:none;vertical-align:middle;}
            .logout_btn{display:none;margin:0px;padding:0px;font-size:12px;line-height:1.75;font-family:Arial,Helvetica,sans-serif;vertical-align:middle;color:rgb(100,100,100);}
            .head_img{display:inline-block;vertical-align:middle;}
            .head_img img{vertical-align:middle;}
            .logout_right{display:inline-block;vertical-align:middle;}
            .nick_text{display:inline-block;margin-left:4px;text-decoration:initial;color:rgb(0,120,182);}
            .logout_text{margin-left:5px;vertical-align:middle;text-decoration:initial;color:rgb(153,153,153);cursor:pointer;}
        </style>
        <script src="http://mat1.gtimg.com/app/openjs/openjs.js"></script>
        <script src="<c:url value="/static/jquery-validation-1.11.1/jquery.validate.js"/>"></script>
        <script src="<c:url value="/static/jquery-validation-1.11.1/localization/messages_zh.js"/>"></script>
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
                        $('#registerButton').click(function(e){
                            e.preventDefault();
                            $('#registerModal').modal();
                        });
                    </script>
                    <!-- 注册对话框结束 -->
                </div>
                <div class="col-xs-4" style="height: 230px; vertical-align: bottom; border-left: gray solid 1px; position:relative;">
                    <!-- 腾讯微博登陆开始 -->
                    <div class="qq_account_log" id="qq_account_log" style="position:absolute; bottom:0px;">
                        <a class="login_btn login_btn02" id="login_btn" href="javascript:;">
                        </a>
                        <div class="logout_btn" id="logout_btn"></div>
                    </div>
                    <script defer="defer">
                        var login_btn = document.getElementById("login_btn"),
                        logout_btn = document.getElementById("logout_btn");
                        function login(){
                            T.login(function(loginStatus){
                                getUserInfo();
                                login_btn.style.display = "none"
                                logout_btn.style.display = "inline-block";
                            },function(loginError){
                                alert(loginError.message);
                            });
                        }
                        function logout(){
                            logout_btn.style.display = "none";
                            login_btn.style.display = "inline-block";
                            T.logout();
                        }
                        function getUserInfo(){
                            T.api("/user/info")
                            .success(function(response){
                                if(response.ret === 0){
                                    var html="",imgsrc="",data=response.data;
                                    imgsrc = data.head;
                                    html = '<a class="head_img" href="http://t.qq.com/'
                                        + data.name +'" target="_blank"><img src="'
                                        + imgsrc +'" /></a><span class="logout_right"><a class="nick_text" href="http://t.qq.com/' 
                                        + data.name +'" target="_blank" title="'
                                        +data.nick +'">'
                                        + data.nick +'</a><a class="logout_text" id="logout_text"　href="javascript:void(0);">退出</a></span>';
                                    logout_btn.innerHTML = html;
                                    var logout_text = document.getElementById("logout_text");
                                    logout_text.onclick = logout;
                                }else{
                                    alert(response.ret);
                                }
                            })
                            .error(function(code,message){
                                alert(message);
                            });
                        }
                        function init(){
                            T.init({appkey:801487384});
                            if(!T.loginStatus()){
                                login_btn.style.display = "inline-block";
                                logout_btn.style.display = "none";
                            }else{
                                getUserInfo();
                                login_btn.style.display = "none";
                                logout_btn.style.display = "inline-block";
                            }
                            login_btn.onclick = login;
                        }
                        init();
                    </script>
                    <!-- 腾讯微博登陆结束 -->
                </div>
            </div>
        </div>
    </body>
</html>
