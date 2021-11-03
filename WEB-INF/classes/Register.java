import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
public class Register extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
		Login login = new Login();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
		String name = request.getParameter("name");
		try{
			PreparedStatement ps=null;
			Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
			Statement stmt = conn.createStatement();
			String sql = "CREATE TABLE IF NOT EXISTS DB " +
            "(USER_ID    TEXT PRIMARY KEY   NOT NULL," +
			" USER_NAME  TEXT     NOT NULL, " +
            " PASSWORD   TEXT     NOT NULL)";
			
			stmt.executeUpdate(sql);
			ps = conn.prepareStatement("select * from DB WHERE USER_ID=?");
			ps.setString(1, email);
			ResultSet rs =ps.executeQuery();
			if(rs.next()){
				out.println("mailId already registered");
				out.print("<a href=\"index.html\">Login</a>");				
			}else{
				sql="INSERT INTO DB(USER_ID,USER_NAME,PASSWORD)"+"VALUES(?,?,?)";			
				ps = conn.prepareStatement(sql);
				ps.setString(1,email);
				ps.setString(2,name);
				ps.setString(3,pass);
				ps.executeUpdate();
				if(login.checkUser(email, pass))
				{
					try{
						HttpSession session = request.getSession();
						session.setAttribute("user_id",email);
						response.sendRedirect("Home.jsp");
		
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
				response.sendRedirect("index.html");
				
			}
			conn.close();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
	