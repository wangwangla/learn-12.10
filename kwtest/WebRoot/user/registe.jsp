<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'registe.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
 
    <h1>注册页面</h1>
    
   <Font size="3" , color="red" > ${msg }</Font>
    <%-- action="${pageContext.request.contextPath }" --%>
    <form action ="<c:url value='/RegisteServlet'/>" method="post">
  用户名：<input type="text" name="name" value="${name }" ><Font size="3" , color="red" >${errors.name }</Font>
    <br/>
密　码：<input type="password" name="pass" value="${pass }"   ><Font size="3" , color="red" >${errors.pass }</Font>
	<br/>

  <input type="submit" value="提交">
 <c:url value='/Userzhuce'/>
 </form>
  </body>

</html>
