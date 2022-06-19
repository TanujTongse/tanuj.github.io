<!DOCTYPE html>
<html lang="en">
<head>
  <title>Ticket Status</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
  <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body style="background:lightyellow">
 
<div class="container">
<h1 align="center">TICKET STATUS</h1>
<h3 align="center">PLEASE CHECK TIME-TABLE</h3>
<a href="timetable.html">GO TO TIME-TABLE</a><br>
 <%
  String email=(String)session.getAttribute("id");
  out.println("Welcome "+email);
  %>
  <a href="user.html">Logout</a>
  <div class="row">
<%@include file="sto.jsp" %>
<%
String qr="select * from book";
Statement st=con.createStatement();
ResultSet rs=st.executeQuery(qr);
if(rs.next())
{
	do
	{
		String name=rs.getString("name");
		String origin=rs.getString("origin");
		String destination=rs.getString("destination");
		String date=rs.getString("date");
		%>
		<div class="col-sm-4">
          <div class="card" style="width:300px">
          <img class="card-img-top" src="https://i.pinimg.com/originals/ec/7b/78/ec7b78ab3079ace000a7bb5ab3f7c583.gif" height="100%" width="100%">
    <div class="card-body">
      <h4 class="card-title"><%=name %></h4>
      <p class="card-text">
      <%=origin %><br>
      <%=destination %><br>
      <%=date%><br>
      </p>
      <a href="Cancel?name=<%= name %>&origin=<%= origin %>&destination=<%=destination %>>&date=<%= date %>" class="btn btn-primary stretched-link">CANCELL TICKET</a>
    </div>
  </div>
      </div>
		<%
	}while(rs.next());
}
else
{
	out.println("no records found");
}
%>

      
  </div>
</div>

</body>
</html>




