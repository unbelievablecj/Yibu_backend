package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DBUtil;
import model.Gonglue;
//TODO 缺少事务回滚的能力
/**
* 用于处理攻略类的事务处理
* 
* @author unbel
*
*/
public class GonglueDao {
	static String insertSql="INSERT INTO gonglue (Gpoint,Guser,Gjing,Gwei,Gcomment,Gpicture,GnumLikes,Gpublish,Gtitle,GdoStrategy,Glabel) VALUES (?,?,?,?,?,?,?,?,?,?,?) ";
	static String delSql="DELETE FROM gonglue WHERE Gid = ?";
	static String updateSql="UPDATE gonglue SET "
			+ " Gpoint=? Gjing=? Gwei=? Guser=? Gcomment=? Gpicture=? GnumLikes=? Gpublish=? Gtitle=? GdoStrategy=? Glabel=? "
			+ " where  Gid = ?";
	static String searchIdSql = "select * from  gonglue where  Gid = ?";//查对应攻略
	static String searchUserSql="select Gid from gonglue WHERE Guser=?";//用户查所有攻略
	static String searchNearSql="select * from gonglue where Gjing between ? and ? and Gwei between ? and ?";
	static String test="INSERT INTO gonglue (Gpoint) VALUES (?)";
	public static int insert(Gonglue g) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		PreparedStatement stmt =conn.prepareStatement(insertSql);
		stmt.setString(1, g.getRoute());
		stmt.setString(2, g.getGuser());
		stmt.setDouble(3, g.getLongitude());
		stmt.setDouble(4, g.getLatitude());
		stmt.setString(5, g.getComment());
		stmt.setString(6, g.getPicture());
		stmt.setString(7, g.getNum_likes());
		stmt.setString(8, g.getPublish_time());
		stmt.setString(9, g.getTitle());
		stmt.setString(10, g.getDotStrategy());
		stmt.setString(11, g.getLabel());
		stmt.executeUpdate();
		
		conn.close();
		return 1;
	}
	public static int del(Gonglue g) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		PreparedStatement stmt =conn.prepareStatement(delSql);
		stmt.setInt(1, g.getGid());
		stmt.executeUpdate(delSql); 
		conn.close();
		return 1;
	}
	
	public static boolean update(Gonglue g) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return false;
		}
		if(searchById(g)==null)return false;
		PreparedStatement stmt=conn.prepareStatement(updateSql);
		stmt.setString(1, g.getRoute());
		stmt.setDouble(2, g.getLongitude());
		stmt.setDouble(3, g.getLatitude());
		stmt.setString(4, g.getGuser());
		stmt.setString(5, g.getComment());
		stmt.setString(6, g.getPicture());
		stmt.setString(7, g.getNum_likes());
		stmt.setString(8, g.getPublish_time());
		stmt.setString(9, g.getTitle());
		stmt.setString(10,g.getDotStrategy());
		stmt.setString(11, g.getLabel());
		stmt.setInt(12,g.getGid());
		stmt.executeUpdate();
		conn.close();
		return true;
	}
	
	public static Gonglue searchById(Gonglue g) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement stmt;
		stmt=conn.prepareStatement(searchIdSql);
		stmt.setInt(1, g.getGid());
		ResultSet rs=stmt.executeQuery();
		conn.close();
		Gonglue gl=new Gonglue();
		if(rs.next()) {
			gl.setGid(rs.getInt(1));
			gl.setGpoint(rs.getString(4));
			gl.setGuser(rs.getString(5));
			gl.setComment(rs.getString(6));
			gl.setGjing(rs.getDouble(2));
			gl.setGwei(rs.getDouble(3));
			gl.setPicture(rs.getString(7));
			return gl;
		}
		else {
			return null;
		}
		
	}
	
	public static List<Gonglue> searchNear(Gonglue g) throws SQLException{
		List<Gonglue> ls=new ArrayList<Gonglue>();
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement stmt=conn.prepareStatement(searchNearSql);
		double d=2.5;
		stmt.setDouble(1, g.getLongitude()-d);
		stmt.setDouble(2, g.getLongitude()+d);
		stmt.setDouble(3, g.getLatitude()-d);
		stmt.setDouble(4, g.getLatitude()+d);
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			Gonglue gl=new Gonglue();
			gl.setGid(rs.getInt(1));
			gl.setRoute(rs.getString(4));
			gl.setGuser(rs.getString(5));
			gl.setComment(rs.getString(6));
			gl.setLongitude(rs.getDouble(2));
			gl.setLatitude(rs.getDouble(3));
			gl.setPicture(rs.getString(7));
			gl.setNum_likes(rs.getString(8));
			gl.setPublish_time(rs.getString(9));
			gl.setTitle(rs.getString(10));
			gl.setDotStrategy(rs.getString(11));
			gl.setLabel(rs.getString(12));
			ls.add(gl);
		}
		return ls;
	}
	public static List<Integer> searchByUser(Gonglue g) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement stmt;
		stmt=conn.prepareStatement(searchUserSql);
		stmt.setString(1, g.getGuser());
		ResultSet rs=stmt.executeQuery();
		conn.close();
		List<Integer> ls=new ArrayList<>();
		while(rs.next()) {
			ls.add(rs.getInt(1));
		}
		return ls;
	}
	 
}
