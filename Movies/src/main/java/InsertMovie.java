

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
 * Servlet implementation class InsertMovie
 */
@WebServlet("/InsertMovie")
public class InsertMovie extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertMovie() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String movieName = request.getParameter("name");
		String watchStatus = request.getParameter("status");
		String rating = request.getParameter("rating");
		String comments = request.getParameter("comment");
		
		Connection connection = null;
		String insertSql = "INSERT INTO WatchList (id, NAME, STATUS, RATING, COMMENT) values (default, ?, ?, ?, ?)";
		
		try
		{
			DBConnection.getDBConnection();
			connection = DBConnection.connection;
			PreparedStatement statement = connection.prepareStatement(insertSql);
			
			statement.setString(1, movieName);
			statement.setString(2, watchStatus);
			statement.setString(3, rating);
			statement.setString(4, comments);
			
			statement.execute();
			connection.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		//Content
		
		response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Insert Data to DB table";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h2 align=\"center\">" + title + "</h2>\n" + //
	            "<ul>\n" + //

	            "  <li><b>Movie Name</b>: " + movieName + "\n" + //
	            "  <li><b>Watch Status</b>: " + watchStatus + "\n" + //
	            "  <li><b>Rating</b>: " + rating + "\n" + //
	            "  <li><b>Comments</b>: " + comments + "\n" + //

	            "</ul>\n");

	      out.println("<a href=/Movies/search_flores.html>Search Data</a> <br>");
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
