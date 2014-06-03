<%-- 
    Document   : list
    Created on : 2014-5-27, 21:46:51
    Author     : LuoWenqiang
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>库存</title>
        <script>
            $(document).ready(function() {
                loadData(0);
            });
            /*
             * 加载数据的函数，加载出来的数据主要用于显示数据列表
             */
            function loadData(pageNumber, pageSize) {
                if (!pageNumber) {
                    //如果没有传递页码进来，则直接显示第一页数据
                    pageNumber = 0;
                }
                if (!pageSize) {
                    //没有选择页码，则默认每页显示10条
                    pageSize = 10;
                }
                $.ajax(
                        {
                            url: "<c:url value="/rest/sale/inventory"/>?pageNumber=" + pageNumber + "&pageSize=" + pageSize,
                            type: "GET",
                            contentType: "application/json",
                            success: function(page, textStatus, jqXHR) {
                                //显示数据在表格
                                showData(page);
                                //显示分页工具条
                                showPageBar(page);
                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                alert(errorThrown);
                            }
                        }
                );
            }

            function showData(page) {
                //清除表格中的内容
                $("#dataList").empty();
                var content = page.content;
                for (var i = 0; i < content.length; i++) {
                    var dataRow = content[i];
                    var row = $("#dataTemplate").clone();
                    for (var item in dataRow) {
                        //alert(item + "的值=" + dataRow[item]);
                        var cell = row.find("#" + item);
                        if (cell) {
                            cell.html(dataRow[item]);
                        }
                    }
                    row.appendTo($("#dataList"));
                }
                //for (var r in content) {
                //克隆模板，创建一个新数据行
                //var row = $("#dataTemplate").clone();
                //for (attribute in r) {
                //循环json对象的属性，并赋值到数据行中对应的列，此处列的id就是相应的属性名称   
                //    row.find("#" + attribute).html(r[attribute]);
                //}

                //row.appendTo($("#dataList"));
                //}
            }
            //显示分页工具条
            function showPageBar(page) {
                //计算开始、结束的页码数
                var startPageNumber = 1;
                var endPageNumber = page.totalPages;
                //最大显示的页码数
                var maxPageNumbers = 5;
                if (page.totalPages > maxPageNumbers) {
                    //当前页码的前面两个
                    startPageNumber = page.number - 2;
                    if (startPageNumber < 1) {
                        startPageNumber = 1;
                    }
                    endPageNumber = startPageNumber + maxPageNumbers - 1;
                    if (endPageNumber > page.totalPages) {
                        var d = endPageNumber - page.totalPages;
                        //如果结束的页码数超出最大页码数，把结束页码置为最大页码数
                        endPageNumber = page.totalPages;
                        startPageNumber = startPageNumber - d;
                        if (startPageNumber < 1) {
                            startPageNumber = 1;
                        }
                    }
                }
                /*
                 <ul class="pagination" id="paginationButtons">
                 <li class="disabled"><a href="#">&laquo;</a></li>
                 <li><a href="#">1</a></li>
                 <li><a href="#">2</a></li>
                 <li><a href="#">3</a></li>
                 <li><a href="#">4</a></li>
                 <li><a href="#">5</a></li>
                 <li><a href="#">&raquo;</a></li>
                 </ul>
                 */
                //当前页码数
                var number = page.number;
                //总页数
                //var totalPages = page.totalPages;
                //总记录数
                //var totalElements = page.totalElements;

                $("#paginationButtons").empty();
                //如果不是第一页，输出上一页按钮
                if (page.firstPage) {
                    //第一页，显示一个不可操作的上一页
                    var button = "<li class='disabled'><span>&laquo;</span></li>";
                    $("#paginationButtons").append(button);
                } else {
                    //否则显示可操作的上一页
                    var button = "<li><span class='btn-link' onclick='loadData(" + (number - 1) + ")'>&laquo;</span></li>";
                    $("#paginationButtons").append(button);
                }

                //根据开始页码和结束页码，输出页码选择按钮
                for (var i = startPageNumber; i <= endPageNumber; i++) {
                    var button = "<li><span class='btn-link' onclick='loadData(" + (i - 1) + ")'>" + i + "</span></li>";
                    if (i === (number + 1)) {
                        //输出选中的，当前页
                        button = "<li class='active'><span>" + i + "</span></li>";
                    }
                    $("#paginationButtons").append(button);
                }

                if (page.lastPage) {
                    //最后一页，显示一个不可操作的下一页
                    var button = "<li class='disabled'><span>&raquo;</span></li>";
                    $("#paginationButtons").append(button);
                } else {
                    //否则显示一个可操作的下一页
                    var button = "<li><span class='btn-link' onclick='loadData(" + (number + 1) + ")'>&raquo;</span></li>";
                    $("#paginationButtons").append(button);
                }
            }

            /**
             * 添加数据到数据库
             */
            function addData() {
                var operation = $("input[name='operation']:checked").val();
                var model = $("input[name='model']").val();
                var quantity = parseInt($("input[name='quantity']").val());
                if (operation === "in") {
                    //入库
                    if (quantity <= 0) {
                        alert("入库时数量必须大于0");
                    }
                } else if (operation === "out") {
                    //出库
                    if (quantity <= 0) {
                        alert("出库时数量必须大于0");
                    }
                    //转换成负数
                    quantity = 0 - quantity;
                } else {
                    alert("请选择操作类型");
                }
                var json = new Object();
                json.model = model;
                json.quantity = quantity;
                var jsonText = JSON.stringify(json);
                //alert(jsonText);
                $.ajax(
                        {
                            url: "<c:url value="/rest/sale/inventory"/>",
                            type: "PUT",
                            data: jsonText,
                            contentType: "application/json",
                            success: function(data, textStatus, jqXHR) {
                                //调用loadData函数加载数据
                                loadData(0);
                                //提示用户
                                $("#commonsTipDialog #commonsTipDialogContent").text(data.msg);
                                $('#commonsTipDialog').modal();
                            },
                            error: function(jqXHR, textStatus, errorThrown) {
                                alert(errorThrown);
                            }
                        }
                );
            }
        </script>
    </head>
    <body>
        <div class="row">
            <div class="col-xs-12 col-sm-9">
                <!-- 填写记录开始 -->
                <div class="well">
                    <label>入库<input name="operation" required="required" type="radio" value="in"/></label>
                    <label>出库<input name="operation" required="required" type="radio" value="out"/></label>
                    &nbsp;&nbsp;
                    <label>型号：<input name="model" style="width: 120px;" required="required" /></label>
                    &nbsp;&nbsp;
                    <label>数量：<input name="quantity" style="width: 50px;" required="required" /></label>
                    <button class="btn btn-primary" onclick="addData()">提交</button>
                </div>
                <!-- 填写记录结束 -->
                <!-- 进出库记录开始 -->
                <div class="well box-content">
                    <table class="table table-bordered table-striped table-condensed">
                        <thead>
                            <tr>
                                <th>时间</th>
                                <th>型号</th>
                                <th>数量</th>
                                <th>当前库存</th>                                          
                            </tr>
                        </thead>   
                        <tbody id="dataList">
                        </tbody>
                    </table>
                    <table class="hidden">
                        <tr id="dataTemplate">
                            <td id="operationTime"></td>
                            <td id="model"></td>
                            <td id="quantity"></td>
                            <td id="inventory"></td>
                        </tr>
                    </table>
                    <div class="text-center">
                        <ul class="pagination" id="paginationButtons">
                        </ul>
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
