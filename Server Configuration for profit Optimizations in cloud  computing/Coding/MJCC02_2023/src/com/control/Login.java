package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.CloudDao;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String uid = request.getParameter("uid");
		String pwd = request.getParameter("pwd");
		PrintWriter o = response.getWriter();
		int i = 0;
		try {
				
				String sql = "select * from users";
				try {
					ResultSet rs = CloudDao.getData(sql);
					while (rs.next()) {
						if (rs.getString(3).equals(uid)
								&& rs.getString(4).equals(pwd)) {
							i++;
							HttpSession session = request.getSession();
							session.setAttribute("email", uid);
							session.setAttribute("user", rs.getString(3));
							session.setAttribute("loc", rs.getString(6));
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			if (i > 0) {
				response.sendRedirect("UserHome.jsp");
			} else {
				o.println("<script type=\"text/javascript\">");
				o.println("alert('Please enter valid Details');");
				o.println("window.location='Login.jsp';</script>");
			}
		} catch (NullPointerException e) {
			o.println("<script type=\"text/javascript\">");
			o.println("alert('Please enter valid Details');");
			o.println("window.location='Login.jsp';</script>");
		}
	}

}
