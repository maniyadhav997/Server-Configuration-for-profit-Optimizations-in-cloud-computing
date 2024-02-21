package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.RegisterBean;
import com.bean.VMBean;
import com.dao.CloudDao;

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter o = response.getWriter();
		String fname = request.getParameter("fn");
		String lname = request.getParameter("ln");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		String mob = request.getParameter("mob");
		String loc = request.getParameter("loc");
		String sql = "select hostid,virtualmachine,users from host1 where location='"
				+ loc + "'";
		String vm = "", host = "", ta = "";
		int tot = 0, i = 0;
		ResultSet rs;
		try {
			rs = CloudDao.getData(sql);
			if (rs.next()) {
				host = rs.getString(1);
				vm = rs.getString(2);
				tot = rs.getInt(3);
			} else {
				vm = null;
				host = "Host1";
				sql = "select users from host1 where hostid='" + host + "'";
				rs = CloudDao.getData(sql);
				while (rs.next()) {
					tot = rs.getInt(1);
				}
				sql = "select sent from loc where location='" + loc + "'";
				rs = CloudDao.getData(sql);
				if (rs.next()) {

				} else {
					ta = loc.substring(0, 3);
					Connection con = CloudDao.connect1(host);
					sql = "CREATE TABLE "
							+ ta
							+ "send (UserId VARCHAR(250),Recipient VARCHAR(250),Subject VARCHAR(250),Body VARCHAR(5000),Image MEDIUMBLOB,Date VARCHAR(250),Content VARCHAR(250)) ";
					Statement st = con.createStatement();
					i = st.executeUpdate(sql);
					if (i == 0) {
						sql = "insert into loc values(?,?)";
						con = CloudDao.connect();
						PreparedStatement ps = con.prepareStatement(sql);
						ps.setString(1, loc);
						ps.setString(2, ta + "send");
						i = ps.executeUpdate();
						System.out.println("the value"+i);
						if (i !=-1) {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('User register success');");
							o.println("window.location='Register.jsp';</script>");

						} else {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Please enter valid Details');");
							o.println("window.location='Register.jsp';</script>");
						}
					}
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			o.println("<script type=\"text/javascript\">");
			o.println("alert('Please enter valid Details');");
			o.println("window.location='Register.jsp';</script>");
		}
		if (vm != null) {
			tot = tot + 1;
			sql = "update host1 set users=" + tot + " where virtualmachine='"
					+ vm + "'";
			System.out.println(sql);
			i = CloudDao.update(sql);
			if (i > 0) {
				sql = "insert into virtualmachine values(?,?,?,?,?)";
				
				VMBean vb = new VMBean();
				vb.setEmail(email);
				vb.setVm(vm);
				vb.setHost(host);
				vb.setLoc(loc);
				vb.setUsage(0.0);
				i = CloudDao.VMSet(sql, vb);
				if (i > 0) {
					RegisterBean rb = new RegisterBean();
					rb.setFname(fname);
					rb.setLname(lname);
					rb.setEmail(email);
					rb.setPwd(pwd);
					rb.setMob(mob);
					rb.setLoc(loc);
					sql = "insert into users values(?,?,?,?,?,?)";
					i = CloudDao.register(sql, rb);
					if (i > 0) {
						o.println("<script type=\"text/javascript\">");
						o.println("alert('Registered Successfully');");
						o.println("window.location='Login.jsp';</script>");
					} else {
						o.println("<script type=\"text/javascript\">");
						o.println("alert('User Already Exist');");
						o.println("window.location='Register.jsp';</script>");
					}
				} else {
					o.println("<script type=\"text/javascript\">");
					o.println("alert('Please enter valid Details');");
					o.println("window.location='Register.jsp';</script>");
				}
			} else {
				o.println("<script type=\"text/javascript\">");
				o.println("alert('Please enter valid Details');");
				o.println("window.location='Register.jsp';</script>");
			}
		} else {
			tot = tot + 1;
			sql = "update host1 set users=" + tot + " where hostid='" + host
					+ "'";
			i = CloudDao.update(sql);
			if (i > 0) {
				sql = "insert into virtualmachine values(?,?,?,?,?)";
				VMBean vb = new VMBean();
				vb.setEmail(email);
				vb.setVm(vm);
				vb.setHost(host);
				vb.setLoc(loc);
				vb.setUsage(0.0);
				i = CloudDao.VMSet(sql, vb);
				if (i > 0) {
					RegisterBean rb = new RegisterBean();
					rb.setFname(fname);
					rb.setLname(lname);
					rb.setEmail(email);
					rb.setPwd(pwd);
					rb.setMob(mob);
					rb.setLoc(loc);
					sql = "insert into users values(?,?,?,?,?,?)";
					i = CloudDao.register(sql, rb);
					if (i > 0) {
						o.println("<script type=\"text/javascript\">");
						o.println("alert('Registered Successfully');");
						o.println("window.location='Login.jsp';</script>");
					} else {
						o.println("<script type=\"text/javascript\">");
						o.println("alert('User Already Exist');");
						o.println("window.location='Register.jsp';</script>");
					}
				} else {
					o.println("<script type=\"text/javascript\">");
					o.println("alert('Please enter valid Details');");
					o.println("window.location='Register.jsp';</script>");
				}
			} else {
				o.println("<script type=\"text/javascript\">");
				o.println("alert('Please enter valid Details');");
				o.println("window.location='Register.jsp';</script>");
			}
		}

	}
}
