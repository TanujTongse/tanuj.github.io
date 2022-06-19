

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

@WebServlet("/Payment")
public class Payment extends HttpServlet {
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		String name=request.getParameter("name");
		String cvv=request.getParameter("cvv");
		String card=request.getParameter("card");
		String date=request.getParameter("date");
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/buus","root","ram");
			String q="insert into payment values(?,?,?,?)";
			PreparedStatement ps=con.prepareStatement(q);
			ps.setString(1, name);
			ps.setString(2, cvv);
			ps.setString(3, card);
			ps.setString(4, date);
			int i=ps.executeUpdate();
			if(i>0)
			{
				RequestDispatcher rd=request.getRequestDispatcher("timetable.html");
				rd.include(request, response);
				out.println("<script>window.alert('Sucessfully Book The Seat')</script>");
			}
			else
			{
				RequestDispatcher rd=request.getRequestDispatcher("pay.jsp");
				rd.include(request, response);
				out.println("<script>window.alert('Something went wrong and try again');</script>");
			}
			con.close();
		}
		catch(Exception e)
		{
			out.println(e);
		}
	}

}
