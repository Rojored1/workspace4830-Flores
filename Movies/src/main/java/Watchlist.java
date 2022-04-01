

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Watchlist
 */
@WebServlet("/Watchlist")
public class Watchlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Watchlist() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String keyword = request.getParameter("keyword");
	    search(keyword, response);
	}

	void search(String keyword, HttpServletResponse response) throws IOException
	{
		
		 response.setContentType("text/html");
	      PrintWriter out = response.getWriter();
	      String title = "Watchlist Result";
	      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
	            "transitional//en\">\n"; //
	      out.println(docType + //
	            "<html>\n" + //
	            "<head><title>" + title + "</title></head>\n" + //
	            "<body bgcolor=\"#f0f0f0\">\n" + //
	            "<h1 align=\"center\">" + title + "</h1>\n");
	      
	      Connection connection = null;
	      PreparedStatement preparedStatement = null;
	      try {
	         DBConnection.getDBConnection();
	         connection = DBConnection.connection;

	         if (keyword.isEmpty()) {
	            String selectSQL = "SELECT * FROM WatchList";
	            preparedStatement = connection.prepareStatement(selectSQL);
	         } else {
	            String selectSQL = "SELECT * FROM WatchList";
	            preparedStatement = connection.prepareStatement(selectSQL);
	         }
	         ResultSet rs = preparedStatement.executeQuery();
	         while(rs.next())
	         {
	        	 int id = rs.getInt("id");
	        	 String movieName = rs.getString("name").trim();
	        	 String movieStatus = rs.getString("status").trim();
	        	 String movieRating = rs.getString("rating").trim();
	        	 String movieComment = rs.getString("comment").trim();
	        	 
	        	 if (keyword.isEmpty() || movieName.contains(keyword) || movieRating.contains(keyword))
	        	 {
	        		 out.println("ID: " + id + ", ");
	                 out.println("Movie: " + movieName + ", ");
	                 out.println("Status: " + movieStatus + ", ");
	                 out.println("Rating: " + movieRating + ", ");
	                 out.println("Comments: " + movieComment + "<br>");
	        	 }
	         }
	         out.println("<a href=/Movies/search_flores.html>Search Watchlist</a> <br>");
	         out.println("</body></html>");
	         rs.close();
	         preparedStatement.close();
	         connection.close();
	}
	      catch (SQLException se) {
	          se.printStackTrace();
	       } catch (Exception e) {
	          e.printStackTrace();
	       } finally {
	          try {
	             if (preparedStatement != null)
	                preparedStatement.close();
	          } catch (SQLException se2) {
	          }
	          try {
	             if (connection != null)
	                connection.close();
	          } catch (SQLException se) {
	             se.printStackTrace();
	          }
	       }
	    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
