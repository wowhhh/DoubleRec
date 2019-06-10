package com.wyb.rec.utils.SavaSongList;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GetConn {
	public static Connection conn=null;
	//获取conn
	public static Connection GetConn() throws SQLException
	{
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/doublerec";//连接本地数据库
			//String url="jdbc:mysql://101.132.40.184:3306/musicRe";//连接云端数据库
			conn=DriverManager.getConnection(url,"root", "sspu");
			
			return conn;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//获取statment
	public static Statement GetStat() throws SQLException
	{
		
		Statement stat;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/doublerec";//连接本地数据库
			//String url="jdbc:mysql://101.132.40.184:3306/musicRe";//连接云端数据库
			conn=DriverManager.getConnection(url,"root", "sspu");
			
			stat=conn.createStatement();
			
			return stat;
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//关闭连接
	public static void CloseConn()
	{
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
