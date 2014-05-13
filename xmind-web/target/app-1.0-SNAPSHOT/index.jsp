<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>在线理财</title>
    </head>
    <body>
        <div class="row">
            <div class="col-xs-12 col-sm-9">
                <!-- 填写记录开始 -->
                <div class="well">
                    每行记录一条流水，用途(来源)和金额用#隔开，记录格式如下：<br/>
                    <span style="color:red">
                        &nbsp;&nbsp;&nbsp;&nbsp;收入记录正数：<code>工资#1234</code>
                    </span><br/>
                    <span style="color:blue">
                        &nbsp;&nbsp;&nbsp;&nbsp;收入记录负数：<kbd style="padding-bottom: 0px;">买菜#-123</kbd>
                    </span><br/>
                    <textarea rows="3" class="form-control"></textarea>
                    <div class="form-actions text-right">
                        <button class="btn btn-primary" type="submit">提交</button>
                        <button class="btn" type="reset">取消</button>
                    </div>
                </div>
                <!-- 填写记录结束 -->

                <!-- 最近7天收支曲线开始 -->
                <div class="well">
                    最近7天收支曲线
                    <div class="box-content">
                        <div id="incomeAndPaymentChart"  
                             class="center" 
                             style="height:200px" ></div>
                    </div>
                </div>
                <!-- 最近7天收支曲线结束 -->
            </div><!--/span-->


            <div class="well col-sm-3" >
                <!-- 用户信息开始 -->
                <jsp:include page="/WEB-INF/jsp/identity/userInfo.jsp"/>
                <!-- 用户信息结束 -->

                <!-- 最近流水开始 -->
                <div class="row thumbnail" style="margin-top: 10px;">
                    最近流水
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>收支</td>
                                <td>时间</td>
                            </tr>
                        </thead>
                        <tbody>
                            <tr class="info">
                                <td>工资收入5000</td>
                                <td>2014-03-01</td>
                            </tr>
                            <tr>
                                <td>买菜支出50</td>
                                <td>2014-03-01</td>
                            </tr>
                            <tr>
                                <td>买菜支出30</td>
                                <td>2014-03-02</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
                <!-- 最近流水结束 -->
            </div><!--/span-->

        </div><!--/row-->
        <script src="<c:url value='/static/flot-0.8.2/jquery.flot.js'/>"></script>
        <script src="<c:url value='/static/flot-0.8.2/jquery.flot.stack.js'/>"></script>
        <script src="<c:url value='/static/flot-0.8.2/jquery.flot.categories.js'/>"></script>
        <script>
            $(function() {
                //收支数据，这些数据从收支记录表中获取
                var income = [["三月一日", 123], ["三月二日", 0], ["三月三日", 0], ["三月四日", 1000], ["三月五日", 0], ["三月六日", 0], ["三月七日", 0]];
                var payment = [["三月一日", 12], ["三月二日", 23], ["三月三日", 16], ["三月四日", 500], ["三月五日", 9], ["三月六日", 43], ["三月七日", 22]];

                var data = [{label: "收入", data: income}, 
                    {label: "支出", data: payment}];

                function plotWithOptions() {
                
                    //用于显示鼠标移动到收支曲线上显示的悬浮框
                    $("<div id='tooltip'></div>").css({
                        position: "absolute",
                        display: "none",
                        border: "1px solid #fdd",
                        padding: "2px",
                        "background-color": "#fee",
                        opacity: 0.80
                    }).appendTo("body");
                    //构建收支曲线
                    var plot = $.plot("#incomeAndPaymentChart", data, {
                        series: {
                            stack: false,//以叠加的方式显示
                            bars: {
                                show: true,//显示柱形图
                                barWidth: 0.6//柱形图的宽度
                            },
                            points: {
                                show: true//显示点
                            }
                        },
                        grid: {
                            hoverable: true,//启动鼠标移动、移出事件
                            clickable: true//启动单击事件
                        },
                        xaxis: {
                            mode: "categories",
                            tickLength: 0
                        }
                    });
                    //绑定收支曲线的鼠标移动事件，移动上去的时候显示具体信息，移开后隐藏
                    $("#incomeAndPaymentChart").bind("plothover", function (event, pos, item) {

                        if (item) {
                            var dataIndex = item.dataIndex;
                            var data = item.series.data[dataIndex];
                            var label = data[0];
                            var y = item.datapoint[1].toFixed(2);

                            $("#tooltip").html(label + " " + item.series.label + " " + y)
                            .css({top: item.pageY+5, left: item.pageX+5})
                            .fadeIn(200);
                        } else {
                            $("#tooltip").hide();
                        }
                    });

                    //收支曲线的点击事件
                    $("#incomeAndPaymentChart").bind("plotclick", function (event, pos, item) {
                        if (item) {
                            //点击收支曲线后高亮显示
                            plot.highlight(item.series, item.datapoint);
                        } else {
                            plot.unhighlight();
                        }
                    });
                }
                //绘制收支曲线
                plotWithOptions();
            });
        </script>
    </body>
</html>
