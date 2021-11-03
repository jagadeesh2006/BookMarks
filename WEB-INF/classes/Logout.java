import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Logout extends HttpServlet {
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("index.html");
	}
}