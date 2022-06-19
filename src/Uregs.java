

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Uregs")
public class Uregs extends HttpServlet
{
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/buus","root","ram");
			String q="insert into user values(?,?,?)";
			PreparedStatement ps=con.prepareStatement(q);
			ps.setString(1, name);
			ps.setString(2, email);
			ps.setString(3, password);
			int i=ps.executeUpdate();
			if(i>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("user.html");
				rd.include(request, response);
				out.println("<script>window.alert('Sucessfully Registerd')</script>");
			}
			else
			{
				response.sendRedirect("uregs.html");
				out.println("<script>window.alert('Something went to wrong')</script>");
			}
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
