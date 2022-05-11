

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Project
 */
@WebServlet("/Project")
public class Project extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String projectName;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Project() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	    projectName = request.getParameter("project");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Items in " + projectName;
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType +"<html>\n" + //
		            "<head><title>" + title + "</title></head>\n" + //
		            "<body>\n" + 
		            "<link rel = \"stylesheet\"\r\n"
		            + "href=\"homepagecss.css\">"+//
		            "<h1 align=\"center\">" + title + "</h1>\n");
		Connection connection = null;
		PreparedStatement statement = null;
		
		try
		{
			DBConnection.getDBConnection();
			connection = DBConnection.connection;
			String selectSql = "SELECT * FROM "+ projectName + "";
			statement = connection.prepareStatement(selectSql);
			ResultSet rs = statement.executeQuery();
			final DecimalFormat df = new DecimalFormat("0.00");
			double total = 0;
			while(rs.next())
			{
				String item = rs.getString("item").trim();
				String quantity = rs.getString("quantity").trim();
				String price = rs.getString("cost").trim();
				total += Double.parseDouble(price) * Double.parseDouble(quantity);
				out.println("<div>Item:" + item + " ");
				out.println("quantity:" + quantity + " ");
				out.println("Price per unit:" + price + "</div><br/>");
			}
			out.println("<div>Project Total: " + df.format(total) + " </div>");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		out.println("<a href=/contractorTool/AddItem.html>Add More Items</a> <br>");
		out.println("<a href=/contractorTool/HomePage.html>Exit</a> <br>");
		
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
