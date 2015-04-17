<%@page import="org.kd.singh.classes.KDCrawlingApp"%>
<%@page import="org.kd.singh.classes.Crawler"%>
<%@ page language="java" 
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.kd.singh.tags.*, java.util.*" %>

<jsp:useBean id="user" class="org.kd.singh.classes.KDCrawlingApp">
<%-- 	<jsp:getProperty name="user" property="test"/> --%>
<%-- 	<jsp:setProperty name="user" property="url" value="http://code.org"/> --%>
	<jsp:setProperty name="user" property="url" value="http://www.w3schools.com"/>
	<%
		try{
			ArrayList<Crawler> list = user.getLinks();
			for(Crawler obj: list){
				//System.out.println(obj.getUrl());
				out.print("<p>"+obj.getName()+" = "+obj.getUrl()+"<br/>");
				out.print(obj.getKeyWords()+"</p>");
			}
// 			out.println("Title = "+user.getTitle());
			
//			user.getSiteMap();
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
	%>
</jsp:useBean>
