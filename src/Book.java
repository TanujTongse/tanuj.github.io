

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Book")
public class Book extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String origin=request.getParameter("origin");
		String destination=request.getParameter("destination");
		String date=request.getParameter("date");
		String email=request.getParameter("email");
		try
		{	
			if(origin=="BETUL"||origin=="CHICHOLI"||origin=="SARNI"||origin=="PARATWADA"||origin=="BHIMPUR")
		    {
	    	 if(destination=="PARATWADA"||destination=="SARNI"||destination=="BETUL"||destination=="CHICHOLI")
		     {
		    	RequestDispatcher rd=request.getRequestDispatcher("pay.jsp");
		    	rd.include(request, response);
		    	out.println("<script>window.alert('BUS IS AVAILBLE YOU CAN BOOK NOW')</script>");
		     }
	    	 Class.forName("com.mysql.jdbc.Driver");
				Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/buus","root","ram");
				String qr="insert into book values(?,?,?,?,?)";
				PreparedStatement ps=con.prepareStatement(qr);
				ps.setString(1, name);
				ps.setString(2, origin);
				ps.setString(3, destination);
				ps.setString(4, date);
				ps.setString(5, email);
				int i=ps.executeUpdate();
				con.close();	
		    }
		     else
				{
			    	response.sendRedirect("book.html");
					out.println("<script>window.alert('BUS IS NOT ABAILBLE ');</script>");
				}
			
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
