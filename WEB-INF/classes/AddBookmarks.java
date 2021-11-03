import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.text.*;
import java.util.*;

public class AddBookmarks extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			HttpSession session = request.getSession();
			String email = (String)session.getAttribute("user_id");
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			if(session.getAttribute("user_id")!=null){
		        
				try{

					String tag = request.getParameter("tag");
					String url = request.getParameter("url");	
					Connection conn = null;
					Statement stmt = null;
					PreparedStatement pstmt = null;
					String sql;
					String query;
					ResultSet rs;
					
					conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
					
					stmt = conn.createStatement(
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY
					);
					sql = "CREATE TABLE IF NOT EXISTS BOOKMARKS " +
					"(USER_Id        TEXT    NOT NULL, " +
					" TAG       	 TEXT     NOT NULL, " +
					" URL            TEXT    NOT NULL)";
					stmt.executeUpdate(sql);
					query = "Select * from BOOKMARKS";
					rs = stmt.executeQuery(query);
					sql = "INSERT INTO BOOKMARKS (USER_ID,TAG,URL)"+
					"VALUES(?,?,?)";
					pstmt = conn.prepareStatement(sql);
					pstmt.setString(1,email);
					pstmt.setString(2,tag);
					pstmt.setString(3,url);
					pstmt.executeUpdate();
					System.out.println("added");
					response.sendRedirect("Home.jsp");
						
				}catch (Exception e) {
					e.printStackTrace();
					System.err.println(e.getClass().getName()+": "+e.getMessage());
				}
			}else{
				response.sendRedirect("index.html");
			}
		}
}
