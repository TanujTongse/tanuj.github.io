<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="javax.servlet.RequestDispatcher" %>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%
Class.forName("com.mysql.jdbc.Driver");
Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/buus","root","ram");
%>