

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
 * Servlet implementation class MyHomePage
 * This Page will hold the logic to create a new project
 */
@WebServlet("/MyHomePage")
public class MyHomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String projectName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyHomePage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		projectName = request.getParameter("projectName");
		String createTableStatement = "CREATE TABLE " + projectName + " (item VARCHAR(20), quantity VARCHAR(20), cost VARCHAR(20));";
		System.out.println(createTableStatement);
		Connection connection = null;
		try
		{
			DBConnection.getDBConnection();
			connection = DBConnection.connection;
			PreparedStatement statement = connection.prepareStatement(createTableStatement);
			statement.execute();
			connection.close();
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		System.out.println(projectName);
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Successfully created project";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType +"<html>\n" + //
		            "<head><title>" + title + "</title></head>\n" + //
		            "<body>\n" + 
		            "<link rel = \"stylesheet\"\r\n"
		            + "href=\"homepagecss.css\">"+//
		            "<h1 align=\"center\">" + title + "</h1>\n");
	    out.println("<a href=/contractorTool/AddItemstoNew.html>ADD ITEMS</a> <br>"
	    		);
	    out.println("</body></html>");
	}
	
	public static String getName()
	{
		return projectName;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
