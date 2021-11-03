import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Login extends HttpServlet {
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        String email = request.getParameter("email");
        String pass = request.getParameter("pass");
		
		if(checkUser(email, pass))
        {
			try{
				HttpSession session = request.getSession();
				session.setAttribute("user_id",email);
				response.sendRedirect("Home.jsp");

			}catch(Exception e) {
				e.printStackTrace();
			}
		}
        else
        {
           out.println("Username or Password incorrect");
        }	       
        
    }
	public static boolean checkUser(String email,String pass) 
    {
        boolean st =false;
		Connection conn=null;
        try {
            conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres", "1234");
            PreparedStatement ps = conn.prepareStatement("select * from DB where USER_ID=? and PASSWORD=?");
            ps.setString(1, email);
            ps.setString(2, pass);
            ResultSet rs =ps.executeQuery();
            st = rs.next();

        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return st;                 
    }   
}
