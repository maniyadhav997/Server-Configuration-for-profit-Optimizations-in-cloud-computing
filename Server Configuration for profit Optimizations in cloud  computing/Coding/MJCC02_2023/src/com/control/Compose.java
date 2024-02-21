package com.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.dao.CloudDao;

/**
 * Servlet implementation class Compose
 */
@MultipartConfig(maxFileSize = 16177215)
@WebServlet("/Compose")
public class Compose extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Compose() {
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
		HttpSession session = request.getSession(false);
		String uid = (String) session.getAttribute("email");
		String recipient = request.getParameter("rcp");
		String sub = request.getParameter("sub");
		String body = request.getParameter("body");
		Date d = new Date();
		String s = "" + d;
		InputStream inpstm = null, inpstm1 = null;
		Part img = request.getPart("photo");
		if (img != null) {
			inpstm = img.getInputStream();
			inpstm1 = img.getInputStream();
		}
		String s1 = img.getContentType();
		Connection con = null;
		String loc = "", loc1 = "";
		String host = "", host1 = "";
		String ta = "", ta1 = "";
		double d1;
		ResultSet rs;
		int i = 0, j = 0, k = 0, l = 0;
		//recipient and user is same
		if (recipient.equalsIgnoreCase(uid)) {
			String sql = "select location from users where Email='" + recipient
					+ "'";
			try {
				rs = CloudDao.getData(sql);
				while (rs.next()) {
					loc = rs.getString(1); //getting location of user
				}
				sql = "select hostid,capacity from host1 where location='" + loc + "'";
				rs = CloudDao.getData(sql);
				double cap = 0.0;
				if (rs.next()) {
					host = rs.getString(1); //based on location host is detecting
					cap = rs.getDouble(2);
				} else {
					host = "Host1";
					cap = 1;
				}
if(cap <= 0.0){
	o.println("<script type=\"text/javascript\">");
	o.println("alert('Virtual Machine is Failed Wait until it is migrated');");
	o.println("window.location='Compose.jsp';</script>");
				}else{
					if (host.equals("Host1")){
						o.println("<script type=\"text/javascript\">");
						o.println("alert('Virtual Machine Not Available');");
						o.println("window.location='Compose.jsp';</script>");
					}else{
				sql = "select sent from loc where location='" + loc + "'";
				rs = CloudDao.getData(sql);
				rs.next();
				ta = rs.getString(1);  //table information
				con = CloudDao.connect1(host);
				sql = "insert into " + ta + " values(?,?,?,?,?,?,?)";
				PreparedStatement ps = con.prepareStatement(sql);
				ps.setString(1, uid);
				ps.setString(2, recipient);
				ps.setString(3, sub);
				ps.setString(4, body);
				ps.setBinaryStream(5, inpstm, (int) img.getSize());
				ps.setString(6, s);
				ps.setString(7, s1);
				i = ps.executeUpdate();
				if (i > 0) {
					  // host is default host
						sql = "select usage1 from virtualmachine where userid ='"
								+ uid + "'";
						rs = CloudDao.getData(sql);
						rs.next();
						d1 = rs.getDouble(1);
						d1 = d1 + (int) img.getSize() / 1000;
						sql = "update virtualmachine set usage1=" + d1
								+ " where userid='" + uid + "'";
						i = CloudDao.update(sql); //updating the data usage by user
						if (i > 0) {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Sent Successfully');");
							o.println("window.location='UserHome.jsp';</script>");
						} else {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Please Send after Sometime');");
							o.println("window.location='Compose.jsp';</script>");
						}
				} else { //Not a default host
						sql = "select usage1 from virtualmachine where userid ='"
								+ uid + "'";
						rs = CloudDao.getData(sql);
						rs.next();
						d1 = rs.getDouble(1);
						d1 = d1 + (int) img.getSize() / 1000;
						sql = "update virtualmachine set usage1=" + d1
								+ " where userid='" + uid + "'";
						i = CloudDao.update(sql); // updating the data usage by user
						sql = "select capacity from host1 where location ='"
								+ loc + "'";
						rs = CloudDao.getData(sql);
						rs.next();
						d1 = rs.getDouble(1);
						d1 = d1 - (int) img.getSize() / 1000;
						sql = "update host1 set capacity=" + d1
								+ " where location='" + loc + "'";
						j = CloudDao.update(sql); // how much data is available in host
						if (i > 0 && j > 0) {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Sent Successfully');");
							o.println("window.location='UserHome.jsp';</script>");
						} else {
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Please Send after Sometime');");
							o.println("window.location='Compose.jsp';</script>");
						}
					}
				} }
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else {//user and recipient is different
			String sql = "select location from users where Email='" + recipient
					+ "'";
			try {
				rs = CloudDao.getData(sql);
				while (rs.next()) {
					loc = rs.getString(1); // location of recipient
				}
				sql = "select hostid,capacity from host1 where location='" + loc + "'";
				rs = CloudDao.getData(sql);
				double cap1 = 0.0, cap2 = 0.0;
				if (rs.next()) {
					host = rs.getString(1); //host of recipient
					cap1 = rs.getDouble(2);
				} else {
					host = "Host1";
					cap1 = 1;
				}
				sql = "select sent from loc where location='" + loc + "'";
				rs = CloudDao.getData(sql);
				rs.next();
				ta = rs.getString(1); // table of recipient
				sql = "select location from users where email='"
						+ session.getAttribute("email") + "'";

				rs = CloudDao.getData(sql);
				while (rs.next()) {
					loc1 = rs.getString(1); //location of sender
				}
				sql = "select hostid,capacity from host1 where location='" + loc1 + "'";
				rs = CloudDao.getData(sql);
				if (rs.next()) {
					host1 = rs.getString(1); //host of the sender
					cap2 = rs.getDouble(2);
				} else {
					host1 = "Host1";
					cap2 = 1;
				}
				if(cap1 <= 0.0 || cap2 <= 0.0){
					o.println("<script type=\"text/javascript\">");
					o.println("alert('Virtual Machine is Failed Wait until it is migrated');");
					o.println("window.location='Compose.jsp';</script>"); 
				}else{
				if(host.equals("Host1") && host1.equals("Host1")){
					
					o.println("<script type=\"text/javascript\">");
					o.println("alert('Virtual Machine Not Available');");
					o.println("window.location='Compose.jsp';</script>");
				}else{
						if (host.equals("Host1")) { // host of recipient is default
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Virtual Machine Not Present at Your Location');");
							o.println("window.location='Compose.jsp';</script>");
						}else if(host1.equals("Host1")){ // sender host is default
							o.println("<script type=\"text/javascript\">");
							o.println("alert('Virtual Machine Not Present at Recipient Location');");
							o.println("window.location='Compose.jsp';</script>");
						}else{
							if(loc.equals(loc1)){
								sql = "select sent from loc where location='" + loc1 + "'";
								rs = CloudDao.getData(sql);
								rs.next();
								ta1 = rs.getString(1); // table of user
								con = CloudDao.connect1(host);
								sql = "insert into " + ta + " values(?,?,?,?,?,?,?)";
								PreparedStatement ps = con.prepareStatement(sql);
								ps.setString(1, uid);
								ps.setString(2, recipient);
								ps.setString(3, sub);
								ps.setString(4, body);
								ps.setBinaryStream(5, inpstm, (int) img.getSize());
								ps.setString(6, s);
								ps.setString(7, s1);
								i = ps.executeUpdate();
								if(i > 0){
									sql = "select usage1 from virtualmachine where userid ='"
											+ uid + "'";
									rs = CloudDao.getData(sql);
									rs.next();
									d1 = rs.getDouble(1);
									d1 = d1 + (int) img.getSize() / 1000;
									sql = "update virtualmachine set usage1=" + d1
											+ " where userid='" + uid + "'";
									k = CloudDao.update(sql); // updating usage of sender
									sql = "select usage1 from virtualmachine where userid ='"
											+ recipient + "'";
									rs = CloudDao.getData(sql);
									rs.next();
									d1 = rs.getDouble(1);
									d1 = d1 + (int) img.getSize() / 1000;
									sql = "update virtualmachine set usage1=" + d1
											+ " where userid='" + recipient + "'";
									l = CloudDao.update(sql);//updating usage of recipient
									sql = "select capacity from host1 where location ='"
											+ loc + "'";
									rs = CloudDao.getData(sql);
									rs.next();
									d1 = rs.getDouble(1);
									d1 = d1 - (int) img.getSize() / 1000;
									sql = "update host1 set capacity=" + d1
											+ " where location='" + loc + "'";
									j = CloudDao.update(sql); // how much data is available in host
									sql = "select capacity from host1 where location ='"
											+ loc1 + "'";
									rs = CloudDao.getData(sql);
									rs.next();
									d1 = rs.getDouble(1);
									d1 = d1 - (int) img.getSize() / 1000;
									sql = "update host1 set capacity=" + d1
											+ " where location='" + loc1 + "'";
									i = CloudDao.update(sql); // how much data is available in host
									if (k > 0 && l > 0 && j > 0 && i > 0) {
										o.println("<script type=\"text/javascript\">");
										o.println("alert('Sent Successfully');");
										o.println("window.location='UserHome.jsp';</script>");
									} else {
										o.println("<script type=\"text/javascript\">");
										o.println("alert('Please Send after Sometime');");
										o.println("window.location='Compose.jsp';</script>");
									}
								}
							}else{
							sql = "select sent from loc where location='" + loc1 + "'";
							rs = CloudDao.getData(sql);
							rs.next();
							ta1 = rs.getString(1); // table of user
							con = CloudDao.connect1(host);
							sql = "insert into " + ta + " values(?,?,?,?,?,?,?)";
							PreparedStatement ps1 = con.prepareStatement(sql);
							ps1.setString(1, uid);
							ps1.setString(2, recipient);
							ps1.setString(3, sub);
							ps1.setString(4, body);
							ps1.setBinaryStream(5, inpstm, (int) img.getSize());
							ps1.setString(6, s);
							ps1.setString(7, s1);
							i = ps1.executeUpdate();
							con = CloudDao.connect1(host1);
							sql = "insert into " + ta1 + " values(?,?,?,?,?,?,?)";
							ps1 = con.prepareStatement(sql);
							ps1.setString(1, uid);
							ps1.setString(2, recipient);
							ps1.setString(3, sub);
							ps1.setString(4, body);
							ps1.setBinaryStream(5, inpstm1, (int) img.getSize());
							ps1.setString(6, s);
							ps1.setString(7, s1);
							j = ps1.executeUpdate();
							if(i > 0 && j > 0){
							sql = "select usage1 from virtualmachine where userid ='"
									+ uid + "'";
							rs = CloudDao.getData(sql);
							rs.next();
							d1 = rs.getDouble(1);
							d1 = d1 + (int) img.getSize() / 1000;
							sql = "update virtualmachine set usage1=" + d1
									+ " where userid='" + uid + "'";
							k = CloudDao.update(sql); // updating usage of sender
							sql = "select usage1 from virtualmachine where userid ='"
									+ recipient + "'";
							rs = CloudDao.getData(sql);
							rs.next();
							d1 = rs.getDouble(1);
							d1 = d1 + (int) img.getSize() / 1000;
							sql = "update virtualmachine set usage1=" + d1
									+ " where userid='" + recipient + "'";
							l = CloudDao.update(sql);//updating usage of recipient
							sql = "select capacity from host1 where location ='"
									+ loc + "'";
							rs = CloudDao.getData(sql);
							rs.next();
							d1 = rs.getDouble(1);
							d1 = d1 - (int) img.getSize() / 1000;
							sql = "update host1 set capacity=" + d1
									+ " where location='" + loc + "'";
							j = CloudDao.update(sql); // how much data is available in host
							sql = "select capacity from host1 where location ='"
									+ loc1 + "'";
							rs = CloudDao.getData(sql);
							rs.next();
							d1 = rs.getDouble(1);
							d1 = d1 - (int) img.getSize() / 1000;
							sql = "update host1 set capacity=" + d1
									+ " where location='" + loc1 + "'";
							i = CloudDao.update(sql); // how much data is available in host
							if (k > 0 && l > 0 && j > 0 && i > 0) {
								o.println("<script type=\"text/javascript\">");
								o.println("alert('Sent Successfully');");
								o.println("window.location='UserHome.jsp';</script>");
							} else {
								o.println("<script type=\"text/javascript\">");
								o.println("alert('Please Send after Sometime');");
								o.println("window.location='Compose.jsp';</script>");
							}
							}
						}
						}
					}
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

}
