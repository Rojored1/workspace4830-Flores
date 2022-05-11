

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ChooseProject
 */
@WebServlet("/ChooseProject")
public class ChooseProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChooseProject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Connection connection = null;
		PrintWriter out = response.getWriter();
		ArrayList <String> projectList = new ArrayList <String>();
		try
		{
			DBConnection.getDBConnection();
			connection = DBConnection.connection;
			
			//GETTING THE TOTAL NUMBER OF PROJECTS
			//STORES THE NUMBER OF TABLES INTO A DOUBLE
			
			
			String insertSql = "show tables;";
			PreparedStatement st = connection.prepareStatement(insertSql);
			ResultSet rs = st.executeQuery("show tables");
			
			//out.println(docType + "<html>");
			while(rs.next())
			{
				projectList.add(rs.getString(1));
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		String title = "Project List";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType +"<html>\n" + //
		            "<head><title>" + title + "</title></head>\n" + //
		            "<body>\n" + 
		            "<link rel = \"stylesheet\"\r\n"
		            + "href=\"homepagecss.css\">"+//
		            "<h1 align=\"center\">" + title + "</h1>\n");
		
		for (int i = 0; i < projectList.size(); i++)
		{
			out.println("<a href=/contractorTool/Project?project="+projectList.get(i)+">"+ projectList.get(i)+"</a> <br>");
		}
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
