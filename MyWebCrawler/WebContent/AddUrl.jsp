<%@page import="org.kd.singh.db.Database"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.kd.singh.classes.KDCrawlingApp"%>
<%@page import="org.kd.singh.classes.Crawler"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		KDCrawlingApp app = new KDCrawlingApp();
		String base_url = "http://www.w3schools.com";
 		app.setUrl(base_url);
		ArrayList<Crawler> list = app.getLinks();
/* 		for(Crawler obj: list){
			out.print(obj.getUrl()+"<br/>");
		}
 */ 		Database db = new Database("mysearchengine","root","123456");
		db.saveInfo(list, app);
	%>
</body>
</html>