

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Contact")
public class Contact extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String address=request.getParameter("address");
		String contact=request.getParameter("contact");
		String massage=request.getParameter("massage");
		try
		{
		  Class.forName("com.mysql.jdbc.Driver");
		  Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/buus","root","ram");
		  String qr="insert into contact values(?,?,?,?)";
		  PreparedStatement ps=con.prepareStatement(qr);
		  ps.setString(1, name);
		  ps.setString(2, address);
		  ps.setString(3, contact);
		  ps.setString(4, massage);
	      int i=ps.executeUpdate();
	      if(i>0)
		  {
	    	RequestDispatcher rd=request.getRequestDispatcher("index.html");
			rd.include(request, response);
			out.println("<script>window.alert('Sucessfully Send The Massage');</script>");
		  }
	      else
	      {
		    RequestDispatcher rd=request.getRequestDispatcher("contact.html");
		    rd.include(request, response);
		    out.println("<script>window.alert('Something went to wrong');</script>");
	       }
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}
				
}


