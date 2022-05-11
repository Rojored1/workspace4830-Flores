

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddItems
 */
@WebServlet("/AddItems")
public class AddItems extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String projectName = Project.getName();
		String item = request.getParameter("item");
		String quantity = request.getParameter("quantity");
		String price = request.getParameter("price");
		String insertToTable = "INSERT INTO " + projectName + " (item, quantity, cost) values (?, ?, ?)";
		Connection connection = null;
		
		try
		{
			DBConnection.getDBConnection();
			connection = DBConnection.connection;
			PreparedStatement statement = connection.prepareStatement(insertToTable);
			statement.setString(1, item);
			statement.setString(2, quantity);
			statement.setString(3, price);
			
			statement.execute();
			connection.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		 PrintWriter out = response.getWriter();
		 out.println(quantity +" "+ item + " was added to "+ projectName + " at " + price +" dollars per unit");
		 response.setContentType("text/html");
		 String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	     out.println(docType + 
	    		 "<html>\n");
		 out.println("<a href=/contractorTool/AddItem.html>Add More Items</a> <br>");
		 out.println("<a href=/contractorTool/HomePage.html>Done</a> <br>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
