<%@page import="semi.StockDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="semi.StockVo"%>
<%@page import="java.util.List"%>
<%@page import="semi.ListQuery"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>stock_change.jsp</title>
<link rel='stylesheet' href='./css/stock_change.css'>
<script defer src='./js/fish.js'></script>
<%
List<String> fishList = new ListQuery().getDataStockList("fish");
List<StockVo> stockList = (ArrayList<StockVo>)request.getAttribute("list");
%>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);     
      
      function drawChart() {
         var data = google.visualization.arrayToDataTable([
              ['비고', '입고량', '출고량', '재고량'], 
              <%for(int i=0; i<stockList.size(); i++){%>
              ['<%=stockList.get(i).getStock_date()%>', <%=stockList.get(i).getiAmt()%>, <%=stockList.get(i).getoAmt()%>, <%=stockList.get(i).getsAmt()%>],
            <%}%>
          ]);
      	
         
        var options = {
          title: "${sVo.stock_fishName}",
          <% if("DARK".equals(session.getAttribute("sessionMode"))){ %>
          titleTextStyle : {
        	 color : '#fff'
          },
          <%}%>
          hAxis: {title: "",  titleTextStyle: {color: '#333'},<% if("DARK".equals(session.getAttribute("sessionMode"))){ %>textStyle : {color : '#fff'}<%}%>},
          vAxis: {minValue: 0, <% if("DARK".equals(session.getAttribute("sessionMode"))){ %>textStyle : {color : '#fff'}<%}%>},
         
          <% 
          if("DARK".equals(session.getAttribute("sessionMode"))){	%>
	          backgroundColor:{
	        	  fill: "#000",
	        	  
	       	   },
          	legend : {
          		textStyle : {color : '#fff'}
          	}
        <%}%>
        };

        var chart = new google.visualization.AreaChart(document.getElementById('stock_change_div_chart'));
        chart.draw(data, options);
      }
</script>

</head>
<body id=stock_change_body>
<h2 id='stock_change_h2'>재고 변동표</h2>
<form name='stock_change_frm' id='stock_change_frm' method='post'>
   <div id='stock_change_div'>
      <div id = 'indiv_container'>
         <div id='stock_change_indiv1'>
               <span id='stock_change_fishName'>어종명-어종코드</span>
               <span id='stock_change_fishName'>
                  <input list ='stock_fishName' name='stock_fishName' value='${sVo.stock_fishName}' />
                  <datalist id='stock_fishName'>
                      <%for(int i=0; i<fishList.size(); i++){ %>
                        <%out.print("<option value='" + fishList.get(i) + "'>"); %>
                      <% } %>
                  </datalist>
               </span>
            </div>
            <div id='stock_change_indiv2'>
               <span id='stock_change_type'>일별</span>
               <span id='stock_change_type'>
                  <label id='stock_change_type'>
                     <input type='radio' name='stock_type' value='일별' ${(sVo.stock_type eq "일별")? "checked":""}/>
                  </label>
               </span>
            </div>
            <div id='stock_change_indiv3'>
               <span id='stock_change_type'>월별</span>
               <span id='stock_change_type'>
                  <label id='stock_change_type'>
                     <input type='radio' name='stock_type' value='월별' ${(sVo.stock_type eq '월별')? 'checked':""}/>
                  </label>
               </span>
            </div>
            <div id='stock_change_indiv4'>
               <span id='stock_change_date'>기준일자</span>
               <span id='stock_change_date'>
                  <input type='date' name='stock_date1' value='${sVo.stock_date1}'/>
                  <output>~</output>
                  <input type='date' name='stock_date2' value='${sVo.stock_date2}'/>
               </span>
            </div>
           <input type='button' id='stock_change_btn_search' value='검색'/>
      </div>
      <div id='stock_change_div_chart' style="width:1090px; height:500px;">chart</div>
   </div>
</form>
</body>
</html>