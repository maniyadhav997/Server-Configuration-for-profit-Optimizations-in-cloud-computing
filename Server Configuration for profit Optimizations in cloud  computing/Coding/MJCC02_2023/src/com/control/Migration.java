package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.CloudDao;

/**
 * Servlet implementation class Migration
 */
@WebServlet("/Migration")
public class Migration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Migration() {
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
	@SuppressWarnings("resource")
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter o = response.getWriter();
		String host = request.getParameter("host");
		String loc = request.getParameter("loc");
		String nhost = request.getParameter("nhost");
		String sql = "", h = "";
		ResultSet rs;
		Connection con;
		Statement st;
		int i = 0, j = 0;
		try {
			if (host.equals(nhost)) {
				o.println("<script type=\"text/javascript\">");
				o.println("alert('It is previous host please select other host');");
				o.println("window.location='Host.jsp';</script>");
			} else {
				sql = "select hostid from host1 where hostid='" + nhost + "'";
				rs = CloudDao.getData(sql);
				if (rs.next()) {
					sql = "select sent from loc where location ='" + loc + "'";
					rs = CloudDao.getData(sql);
					rs.next();
					h = rs.getString(1);
					con = CloudDao.connect1(nhost);
					System.out.println("======>");
					sql = "CREATE TABLE "
							+ h
							+ " (UserId VARCHAR(250),Recipient VARCHAR(250),Subject VARCHAR(250),Body VARCHAR(5000),Image MEDIUMBLOB,Date VARCHAR(250),Content VARCHAR(250)) ";
					st = con.createStatement();
					i = st.executeUpdate(sql);
					if (i == 0) {
						sql = "Insert " + nhost + "." + h + " select * from "
								+ host + "." + h;
						i = CloudDao.update(sql);// Data is transfer
						if (i > 0) {
							double cap = 40000;
							sql = "Drop table " + host + "." + h;
							i = CloudDao.update(sql);
							sql = "UPDATE host1 SET hostid='"+nhost+"',capacity="+cap +"WHERE location='"+loc+"'";
							i = CloudDao.update(sql);
							sql = "UPDATE virtualmachine SET hostid='"+nhost+"' WHERE location='"+loc+"'";
							j = CloudDao.update(sql);
							if (i > 0 && j > 0) {
								o.println("<script type=\"text/javascript\">");
								o.println("alert('Virtual Machine is Migrated');");
								o.println("window.location='Host.jsp';</script>");
							} else {
								o.println("<script type=\"text/javascript\">");
								o.println("alert('Virtual Machine not Migrated');");
								o.println("window.location='Host.jsp';</script>");
							}
						} else {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Host not created');");
							o.println("window.location='Host.jsp';</script>");
						}
					} else {
						o.println("<script type=\"text/javascript\">");
						o.println("alert('Host not created');");
						o.println("window.location='Host.jsp';</script>");
					}
				} else {
					sql = "create database " + nhost;
					i = CloudDao.update(sql);// creating host
					if (i > 0) {
							sql = "select sent from loc where location ='"
									+ loc + "'";
							rs = CloudDao.getData(sql);
							rs.next();
							h = rs.getString(1);
							con = CloudDao.connect1(nhost);
							sql = "CREATE TABLE "
									+ h
									+ " (UserId VARCHAR(250),Recipient VARCHAR(250),Subject VARCHAR(250),Body VARCHAR(5000),Image MEDIUMBLOB,Date VARCHAR(250),Content VARCHAR(250)) ";
							st = con.createStatement();
							i = st.executeUpdate(sql);
							if (i == 0) {
								sql = "Insert " + nhost + "." + h
										+ " select * from " + host + "." + h;
								i = CloudDao.update(sql);// Data is transfer
								if (i > 0) {
									double cap = 40000;
									sql = "Drop table " + host + "." + h;
									i = CloudDao.update(sql);
									sql = "UPDATE host1 SET hostid='"+nhost+"',capacity="+cap +"WHERE location='"+loc+"'";
									i = CloudDao.update(sql);
									sql = "UPDATE virtualmachine SET hostid='"+nhost+"' WHERE location='"+loc+"'";
									j = CloudDao.update(sql);
									if (i > 0 && j > 0) {
										o.println("<script type=\"text/javascript\">");
										o.println("alert('Virtual Machine is Migrated');");
										o.println("window.location='Host.jsp';</script>");
									} else {
										o.println("<script type=\"text/javascript\">");
										o.println("alert('Virtual Machine not Migrated');");
										o.println("window.location='Host.jsp';</script>");
									}
								}
							} else {
								o.println("<script type=\"text/javascript\">");
								o.println("alert('Host not created');");
								o.println("window.location='Host.jsp';</script>");
							}
						}
					}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
