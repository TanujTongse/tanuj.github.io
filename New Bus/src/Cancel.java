

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

@WebServlet("/Cancel")
public class Cancel extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/buus","root","ram");
			
			String q="delete from book where name=?";
			PreparedStatement ps=con.prepareStatement(q);
			ps.setString(1, name);
			int i=ps.executeUpdate();
			if(i>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("Uhome.jsp");
				rd.include(request,response);
				out.println("<script>window.alert('Sucessfully Cancelled Your Ticket');</script>");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("Uhome.jsp");
				rd.include(request,response);
				out.println("<script>window.alert('Not Cancelled Your Ticket');</script>");
			}
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
