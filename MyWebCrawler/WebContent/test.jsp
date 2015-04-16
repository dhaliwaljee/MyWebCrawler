<%@ page language="java" 
    pageEncoding="ISO-8859-1"%>
    <%@ page import="org.kd.singh.tags.*, java.util.*" %>

<jsp:useBean id="user" class="org.kd.singh.classes.Crawler">
<%-- 	<jsp:getProperty name="user" property="test"/> --%>
	<jsp:setProperty name="user" property="url" value="http://code.org"/>
<%-- 	<jsp:setProperty name="user" property="url" value="https://bestspace.co"/>
 --%>	<%
		try{
			//user.getLinks();
			ArrayList<Anchor> links = new ArrayList<Anchor>();
			links = user.getAnchorTag();
			for(Anchor a: links){
				out.print("<a href='"+a.getAnchorHref()+"'>"+a.getAnchorLabel()+"</a><br/>");
			}
			ArrayList<String> links1 = new ArrayList<String>();
 			links1 = user.getAnchorLinks();
			for(String a: links1){
				out.print(a+"<br/>");
			}
 			
			
//			user.getSiteMap();
		}catch(Exception e){
			System.out.print(e.getMessage());
		}
	%>
</jsp:useBean>
