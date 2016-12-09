<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="css/home.css" />
</head>
<body>

<div id="page">

<div id="heading">
<p>Twitter Insight</p>
</div>

<div id="searchbar">
<div>
<span id="fix">See what people are talking about...</span>
</div>
<form action="${pageContext.request.contextPath}/Interface" method="POST">
<input type="text" id="searchtweet" name="search" placeholder="search"/>
<input type="submit" id="submit" value="Search">
</form>
</div>

<div id="results">
<% HttpSession output=request.getSession(true); %>
<% if(output.getAttribute("searchResult")!=null) {%>
<% ArrayList<String> result= (ArrayList<String>)output.getAttribute("searchResult");
	for(int i=0;i<result.size();i++) {%>
<span id="tweet">
	<ul>
		<li><button onclick="alert('Hello')"><%= result.get(i) %></button></li>
	</ul>	
</span>
<% } %>
<% } else if(output.getAttribute("searched")!=null) { %>
<span id="nothing">
	<p>No results found</p>
</span>
<% } %>
</div>

</div>
</body>
</html>