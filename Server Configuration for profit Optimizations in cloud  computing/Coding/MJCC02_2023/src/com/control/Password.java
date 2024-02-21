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
 * Servlet implementation class Password
 */
@WebServlet("/Password")
public class Password extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Password() {
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
		PrintWriter o = response.getWriter();
		String email = request.getParameter("email");
		String opwd = request.getParameter("opwd");
		String pwd = request.getParameter("pwd");
		HttpSession session = request.getSession(false);
		if(email.equals(session.getAttribute("email"))){
		String sql = "select password from users where email='"+session.getAttribute("email")+"'";
		try {
			ResultSet rs = CloudDao.getData(sql);
			while(rs.next()){
				if(opwd.equals(rs.getString(1))){
					sql = "update users set password='"+pwd+"' where email='"+email+"'";
					int i = CloudDao.update(sql);
					if(i > 0){
						o.println("<script type=\"text/javascript\">");
						o.println("alert('Password Updated Successfully');");
						o.println("window.location='UserHome.jsp';</script>");
					}else{
						o.println("<script type=\"text/javascript\">");
						o.println("alert('Please enter valid details');");
						o.println("window.location='Password.jsp';</script>");
					}
				}else{
					o.println("<script type=\"text/javascript\">");
					o.println("alert('Please enter valid details');");
					o.println("window.location='Password.jsp';</script>");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}else{
			o.println("<script type=\"text/javascript\">");
			o.println("alert('Please enter your email id only');");
			o.println("window.location='Password.jsp';</script>");
		}
	}

}
