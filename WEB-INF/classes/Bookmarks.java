import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Bookmarks extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			HttpSession session = request.getSession();
			String email = (String)session.getAttribute("user_id");
			response.setContentType("text/html;charset=UTF-8");
			if(session.getAttribute("user_id")!=null){
				try{
					String tag = request.getParameter("tag");
					PrintWriter out = response.getWriter();
					Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
					
				
					PreparedStatement ps = conn.prepareStatement("select * from BOOKMARKS WHERE USER_ID=?	AND TAG=? ");
					ps.setString(1,email);
					ps.setString(2,tag);
					ResultSet rs =ps.executeQuery();
					out.println("<HTML>");
					out.println("<BODY>");
					out.println("<CENTER>");
					out.println("<table BORDER=1 CELLPADDING=2% CELLSPACING=0 WIDTH=50% >");
					out.println("<th>TAG</th>");
					out.println("<th>URL</th>");
				
				
					while(rs.next()){
					out.println("<tr>");
					out.print("<td>"+rs.getString("TAG")+ "</td>");
					out.print("<td><a href='"+rs.getString("URL")+ "'> "+rs.getString("URL")+ " </td>");
					
					out.println("</tr>");
					}
					out.print("</table>");
					out.print("<br></br>");
					out.print("<button><a href='addBookmarks.html'> add </button>");
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}				
			}else{
				response.sendRedirect("index.html");
			}
			
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			HttpSession session = request.getSession();
			String email = (String)session.getAttribute("user_id");
			response.setContentType("text/html;charset=UTF-8");
			if(session.getAttribute("user_id")!=null){
				try{
					PrintWriter out = response.getWriter();
					Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
					
				
					PreparedStatement ps = conn.prepareStatement("select * from BOOKMARKS WHERE USER_ID=?");
					ps.setString(1,email);
					ResultSet rs =ps.executeQuery();
					out.println("<HTML>");
					out.println("<BODY>");
					out.println("<CENTER>");
					out.println("<table BORDER=1 CELLPADDING=2% CELLSPACING=0 WIDTH=50% >");
					out.println("<th>TAG</th>");
					out.println("<th>URL</th>");
				
				
					while(rs.next()){
					out.println("<tr>");
					out.print("<td>"+rs.getString("TAG")+ "</td>");
					out.print("<td><a href='"+rs.getString("URL")+ "'> "+rs.getString("URL")+ " </td>");
					
					out.println("</tr>");
					}
					out.print("</table>");
					out.print("<br></br>");
					out.print("<button><a href='addBookmarks.html'> add </button>");
					conn.close();
				}catch(Exception e) {
					e.printStackTrace();
				}				
			}else{
				response.sendRedirect("index.html");
			}
			
	}
}