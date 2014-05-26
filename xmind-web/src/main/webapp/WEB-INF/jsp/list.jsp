<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>进销存列表</title>
        <style>
            ul {
                list-style: disc outside none;
                padding: 0px;
            }
            .pagination {
                height: 36px;
                margin: 18px 0;
                width: 100%;
            }
            .pagination ul {
                display: inline-block;
                *display: inline;
                /* IE7 inline-block hack */

                *zoom: 1;
                margin-left: 0;
                margin-bottom: 0;
                -webkit-border-radius: 3px;
                -moz-border-radius: 3px;
                border-radius: 3px;
                -webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
                -moz-box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
                box-shadow: 0 1px 2px rgba(0, 0, 0, 0.05);
            }
            .pagination li {
                display: inline;
            }
            .pagination a {
                float: left;
                padding: 0 14px;
                line-height: 34px;
                text-decoration: none;
                border: 1px solid #ddd;
                border-left-width: 0;
            }
            .pagination a:hover,
            .pagination .active a {
                background-color: #f5f5f5;
            }
            .pagination .active a {
                color: #999999;
                cursor: default;
            }
            .pagination .disabled span,
            .pagination .disabled a,
            .pagination .disabled a:hover {
                color: #999999;
                background-color: transparent;
                cursor: default;
            }
            .pagination li:first-child a {
                border-left-width: 1px;
                -webkit-border-radius: 3px 0 0 3px;
                -moz-border-radius: 3px 0 0 3px;
                border-radius: 3px 0 0 3px;
            }
            .pagination li:last-child a {
                -webkit-border-radius: 0 3px 3px 0;
                -moz-border-radius: 0 3px 3px 0;
                border-radius: 0 3px 3px 0;
            }
            .pagination-centered {
                text-align: center;
            }
            .pagination-right {
                text-align: right;
            }
        </style>
    </head>
    <body>
        <div class="row">
            <div class="col-xs-12 col-sm-9">
                <!-- 填写记录开始 -->
                <div class="well">
                    <select required="required" name="operation">
                        <option value="">-- 请选择流水类型 --</option>
                        <option value="in">入库</option>
                        <option value="out">出库</option>
                    </select>
                    型号：<input name="type" style="width: 120px;" required="required" /> 
                    数量：<input name="number" style="width: 50px;" required="required" />
                    <button class="btn btn-primary" type="submit">提交</button>
                </div>
                <!-- 填写记录结束 -->
                <!-- 进出库记录开始 -->
                <div class="well">
                    <div class="box-content">
                        <table class="table table-bordered table-striped table-condensed">
                            <thead>
                                <tr>
                                    <th>时间</th>
                                    <th>型号</th>
                                    <th>数量</th>
                                    <th>剩余库存</th>                                          
                                </tr>
                            </thead>   
                            <tbody>
                                <tr>
                                    <td class="center">2012/01/01</td>
                                    <td class="center">DEV-0021-009</td>
                                    <td class="center">-100</td>
                                    <td class="center">900</td>                                       
                                </tr>
                                <tr>
                                    <td class="center">2012/01/01</td>
                                    <td class="center">DEV-0021-009</td>
                                    <td class="center">-100</td>
                                    <td class="center">900</td>                                       
                                </tr>
                                <tr>
                                    <td class="center">2012/01/01</td>
                                    <td class="center">DEV-0021-009</td>
                                    <td class="center">-100</td>
                                    <td class="center">900</td>                                       
                                </tr>                                 
                            </tbody>
                        </table>
                        <div class="pagination pagination-centered">
                            <ul>
                                <li><a href="#">Prev</a></li>
                                <li class="active">
                                    <a href="#">1</a>
                                </li>
                                <li><a href="#">2</a></li>
                                <li><a href="#">3</a></li>
                                <li><a href="#">4</a></li>
                                <li><a href="#">Next</a></li>
                            </ul>
                        </div>    
                    </div>
                </div>
                <!-- 进出库记录结束 -->
            </div><!--/span-->


            <div class="well col-sm-3" >
                <!-- 用户信息开始 -->
                <div class="row">
                    <div class="col-xs-5">
                        <img class="img-rounded" src="<c:url value='/static/images/100.jpg'/>"/>
                    </div>
                    <div class="col-xs-5">
                        罗
                        <div>关联QQ</div>
                    </div>
                </div>
                <!-- 用户信息结束 -->

            </div><!--/span-->

        </div><!--/row-->
    </body>
</html>
