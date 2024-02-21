package com.control;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bean.HostBean;
import com.dao.CloudDao;

/**
 * Servlet implementation class Build
 */
@WebServlet("/Build")
public class Build extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Build() {
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
		String vm = request.getParameter("vm");
		double cap = Double.parseDouble(request.getParameter("cap"));
		String loc = request.getParameter("loc");
		String sql = "select hostid from host1 where hostid='" + host + "'";
		int i = 0, users = 0, tusers = 0, j = 0;
		String ta = "";
		Connection con;
		ResultSet rs;
		try {
			rs = CloudDao.getData(sql);
			if (rs.next()) { //verifying host availability
				sql = "select location from virtualmachine where location='"
						+ loc + "'";
				rs = CloudDao.getData(sql);
				while (rs.next()) {//no of users at that location
					users++;
				}
				if (users >= 2) {//morethan 7 host will create
					sql = "select users from host1 where hostid='Host1'";
					rs = CloudDao.getData(sql);
					rs.next();
					tusers = rs.getInt(1);
					tusers = tusers - users;
					sql = "UPDATE host1 SET users="+tusers+" WHERE hostId='Host1'";
					j = CloudDao.update(sql);
					sql = "insert into host1 value(?,?,?,?,?)";
					HostBean hb = new HostBean();
					hb.setCap(cap*1000);
					hb.setHost(host);
					hb.setLoc(loc);
					hb.setUsers(users);
					hb.setVm(vm);
					i = CloudDao.setHost(hb, sql);//created new host and updated at host
					if (i > 0 && j > 0) {
						sql = "update virtualmachine set virtualmachine='" + vm
								+ "', hostid='" + host + "' where location='"
								+ loc + "'";
						i = CloudDao.update(sql);//allocating host details to users
						if (i > 0) {
							sql = "select sent from loc where location='" + loc
									+ "'";
							rs = CloudDao.getData(sql);
							rs.next();
							ta = rs.getString(1);
							con = CloudDao.connect1(host);
							sql = "CREATE TABLE "
									+ ta
									+ " (UserId VARCHAR(250),Recipient VARCHAR(250),Subject VARCHAR(250),Body VARCHAR(5000),Image MEDIUMBLOB,Date VARCHAR(250),Content VARCHAR(250)) ";
							Statement st = con.createStatement();
							i = st.executeUpdate(sql);//creating table for transfer
							if (i == 0) {
								/*sql = "Insert " + host + "." + ta
										+ " select * from host1." + ta;
								i = Cc17Dao.update(sql);//Data is transfer
								if (i > 0) {
									sql = "Drop table host1." + ta;
									i = Cc17Dao.update(sql);
*/									o.println("<script type=\"text/javascript\">");
									o.println("alert('Virtual Machine Created Successfully');");
									o.println("window.location='Network.jsp';</script>");
								/*} else {
									o.println("<script type=\"text/javascript\">");
									o.println("alert('Network not created');");
									o.println("window.location='Network.jsp';</script>");
								}*/
							} else {
								o.println("<script type=\"text/javascript\">");
								o.println("alert('Network not created');");
								o.println("window.location='Network.jsp';</script>");
							}
						} else {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Network not created');");
							o.println("window.location='Network.jsp';</script>");
						}
					} else {
						o.println("<script type=\"text/javascript\">");
						o.println("alert('Network not created');");
						o.println("window.location='Network.jsp';</script>");
					}
				} else {
					o.println("<script type=\"text/javascript\">");
					o.println("alert('Less number of users available no need of new host');");
					o.println("window.location='Network.jsp';</script>");
				}
			} else {//there is no host available
					sql = "select location from virtualmachine where location='"
							+ loc + "'";
					rs = CloudDao.getData(sql);
					while (rs.next()) {//no of users at that location
						users++;
					}
					if (users >= 2) {//morethan 7 host will create
						sql = "create database " + host;
						i = CloudDao.update(sql);//creating host
						if (i > 0) {
						sql = "select users from host1 where hostid='Host1'";
						rs = CloudDao.getData(sql);
						rs.next();
						tusers = rs.getInt(1);
						tusers = tusers - users;
						sql = "UPDATE host1 SET users="+tusers+" WHERE hostId='Host1'";
						j = CloudDao.update(sql);
						sql = "insert into host1 value(?,?,?,?,?)";
						HostBean hb = new HostBean();
						hb.setCap(cap*1000);
						hb.setHost(host);
						hb.setLoc(loc);
						hb.setUsers(users);
						hb.setVm(vm);
						i = CloudDao.setHost(hb, sql);//created new host and updated at host
						if (i > 0 && j > 0) {
							sql = "update virtualmachine set virtualmachine='"
									+ vm + "', hostid='" + host
									+ "' where location='" + loc + "'";
							i = CloudDao.update(sql);//allocating host details to users
							if (i > 0) {
								sql = "select sent from loc where location='"
										+ loc + "'";
								rs = CloudDao.getData(sql);
								rs.next();
								ta = rs.getString(1);
								con = CloudDao.connect1(host);
								sql = "CREATE TABLE "
										+ ta
										+ " (UserId VARCHAR(250),Recipient VARCHAR(250),Subject VARCHAR(250),Body VARCHAR(5000),Image MEDIUMBLOB,Date VARCHAR(250),Content VARCHAR(250)) ";
								Statement st = con.createStatement();
								i = st.executeUpdate(sql);//creating table for transfer
								if (i == 0) {
									/*sql = "Insert " + host + "." + ta
											+ " select * from host1." + ta;
									i = Cc17Dao.update(sql);//Data is transfer
									if (i > 0) {
										sql = "Drop table host1." + ta;
										i = Cc17Dao.update(sql);*/
										o.println("<script type=\"text/javascript\">");
										o.println("alert('Virtual Machine Created Successfully');");
										o.println("window.location='Network.jsp';</script>");
									/*} else {
										o.println("<script type=\"text/javascript\">");
										o.println("alert('Network not created');");
										o.println("window.location='Network.jsp';</script>");
									}*/
								} else {
									o.println("<script type=\"text/javascript\">");
									o.println("alert('Network not created');");
									o.println("window.location='Network.jsp';</script>");
								}
							} else {
								o.println("<script type=\"text/javascript\">");
								o.println("alert('Network not created');");
								o.println("window.location='Network.jsp';</script>");
							}
						} else {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Network not created');");
							o.println("window.location='Network.jsp';</script>");
						}
					} else {
						o.println("<script type=\"text/javascript\">");
						o.println("alert('Less number of users available no need of new host');");
						o.println("window.location='Network.jsp';</script>");
					}
				} else {
					o.println("<script type=\"text/javascript\">");
					o.println("alert('Network not created');");
					o.println("window.location='Network.jsp';</script>");
				}
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
