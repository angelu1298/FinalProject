<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <% session.setAttribute("mem_id","admin");%>
    <% session.setAttribute("mem_no",1); %>
       
    <%-- 
    <jsp:forward page="./obs_list.brn"/>\
 --%>
         <jsp:forward page="./my_view.brn"/>


      <%-- <jsp:forward page="./bringtype.brn"/> --%>
   <%-- <jsp:forward page="./goweekly.brn"/> --%> 
   