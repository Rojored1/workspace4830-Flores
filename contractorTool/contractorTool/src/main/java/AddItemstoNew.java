

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
@WebServlet("/AddItemstoNew")
public class AddItemstoNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddItemstoNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String projectName = MyHomePage.getName();
		
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
		 String title = "Successfully Added Items";
		 
		 response.setContentType("text/html");
		 String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	     out.println(docType + 
	    		 "<html>\n");
	     out.println("<head><title>" + title + "</title></head>\n" + //
		            "<body>\n" + 
		            "<link rel = \"stylesheet\"\r\n"
		            + "href=\"homepagecss.css\">"+//
		            "<h1 align=\"center\">" + title + "</h1>\n"
		           + "<p>" + quantity +" "+ item + " was added to "+ projectName + " at " + price +" dollars per unit"+ "</p>");
		 out.println("<a href=/contractorTool/AddItemstoNew.html>Add More Items</a> <br>");
		 out.println("<a href=/contractorTool/HomePage.html>Done</a> <br>");
		 out.println("</body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
