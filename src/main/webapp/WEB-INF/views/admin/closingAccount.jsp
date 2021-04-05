<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/setting.jsp" %>
<%@ page import="org.json.simple.JSONArray"%>
<html>
<head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawVisualization);

      function drawVisualization() {
        // Some raw data (not necessarily accurate)
        //var jsonData = document.getElementById("jsonData");
        var data = google.visualization.arrayToDataTable(
        		${jsonArray}
        		/* [
          ['Month', '매출액'],
          ['2020/01',  614],
          ['2020/02',  682],
          ['2020/03',  623],
          ['2020/04',  609],
          ['2020/05',  569],
          ['2020/06',  620]
       			 ] */
        		);

        var options = {
          title : '월별 매출액',
          vAxis: {title: 'WON'},
          hAxis: {title: 'Month'},
          seriesType: 'bars',
        };

        var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
        chart.draw(data, options);        
      }

    </script>
</head>
<body>
<jsp:include page="../include/host_header.jsp"/>
<div id="chart_div" style="width: 1200px; height: 800px; margin: 0 auto;"></div>
<jsp:include page="../include/footer.jsp"/>
</body>
</html>