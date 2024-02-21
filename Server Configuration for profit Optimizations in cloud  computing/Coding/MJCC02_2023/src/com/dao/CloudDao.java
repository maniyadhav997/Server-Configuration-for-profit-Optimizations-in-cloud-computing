package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bean.HostBean;
import com.bean.RegisterBean;
import com.bean.VMBean;

public class CloudDao {
	public static Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/MJCC02_2023", "root", "root");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
	}

	public static ResultSet getData(String sql) throws SQLException {
		// TODO Auto-generated method stub
		Connection con = connect();
		ResultSet rs = null;
		PreparedStatement ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return rs;
	}

	public static int register(String sql, RegisterBean rb) {
		// TODO Auto-generated method stub
		int i = 0;
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, rb.getFname());
			ps.setString(2, rb.getLname());
			ps.setString(3, rb.getEmail());
			ps.setString(4, rb.getPwd());
			ps.setString(5, rb.getMob());
			ps.setString(6, rb.getLoc());
			i = ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public static int update(String sql) {
		// TODO Auto-generated method stub
		int i = 0;
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			i = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}

	public static int VMSet(String sql, VMBean vb) {
		// TODO Auto-generated method stub
		int i = 0;
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, vb.getVm());
			ps.setString(2, vb.getEmail());
			ps.setString(3, vb.getLoc());
			ps.setString(4, vb.getHost());
			ps.setDouble(5, vb.getUsage());
			i = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
	
	public static Connection connect1(String cont) {
		// TODO Auto-generated method stub
		Connection con = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/mjcc02_2023", "root", "root");
			
		}catch(SQLException | ClassNotFoundException e){
			e.printStackTrace();
		}
		return con;
	}

	public static ResultSet getData1(String sql, Connection con) throws SQLException {
		// TODO Auto-generated method stub
		ResultSet rs = null;
		PreparedStatement ps = con.prepareStatement(sql);
		rs = ps.executeQuery();
		return rs;
	}

	public static int setHost(HostBean hb, String sql) {
		// TODO Auto-generated method stub
		int i = 0;
		Connection con = connect();
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, hb.getHost());
			ps.setString(2, hb.getVm());
			ps.setInt(3, hb.getUsers());
			ps.setString(4, hb.getLoc());
			ps.setDouble(5, hb.getCap());
			i = ps.executeUpdate();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return i;
	}
}
