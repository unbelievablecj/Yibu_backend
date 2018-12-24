package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import db.DBUtil;
import model.User;
//TODO 缺少事务回滚的能力
/**
 * 用于处理用户类的事务处理
 * 
 * @author unbel
 *
 */
public class UserDao {
	static String loginSql="SELECT * FROM user where Uemail=? and Upw=?";
	static String getVerSql="SELECT Utestma from user where Uemail=?";
	static String registerSql="UPDATE user SET Uon=1,Upw=?,Uname=? where Uemail=?";
	static String changePwdSql="Update user set Upw=? where Uemail=?";
	static String isUserExistSql = "select * from user where Uemail=? and Uon=1";
	static String insertSql="insert into user (Uemail,Upw,Uon) VALUES (?,?,?)";
	static String setVerifySql="UPDATE user set Utestma=? WHERE Uemail=?";
	static String getUserSql="SELECT * from user where Uemail=?";
	public static boolean setVerify(User user) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return false;
		}
		if(isUsereExist(user)) {
			PreparedStatement stmt=conn.prepareStatement(setVerifySql);
			stmt.setString(1, user.getUser_ver());
			stmt.setString(2, user.getUser_mail());
			stmt.executeUpdate();
		}
		else {
			insert(user);
			PreparedStatement stmt=conn.prepareStatement(setVerifySql);
			stmt.setString(1, user.getUser_ver());
			stmt.setString(2, user.getUser_mail());
			stmt.executeUpdate();
		}
		return true;
	}
	public static boolean isUsereExist(User user) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		String sql="select * from user where Uemail=?";
		PreparedStatement stmt=conn.prepareStatement(sql);
		stmt.setString(1, user.getUser_mail());
		ResultSet rs=stmt.executeQuery();
		conn.close();
		if(rs.next()) {
			//System.out.println(rs.getString(1));
			return true;
		}
		//System.out.println("sql:"+user.getUser_mail());
		return false;
	}
	public static int insert(User user) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		PreparedStatement stmt =conn.prepareStatement(insertSql);
		stmt.setString(1, user.getUser_mail());
		stmt.setString(2, user.getUser_pwd());
		stmt.setInt(3, 0);
		//stmt.executeUpdate(insertSql);
		stmt.execute();
		conn.close();
		return 1;
	}
	public static int register(User user) throws SQLException{
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return 1;
		}
		if(isUserExist(user)) {
			conn.close();
			return 2;
		}
		else if(!getVer(user).equals(user.getUser_ver())){
			conn.close();
			return 1;
		}
		else {
			String sql="select * from user where Uemail=?";
			PreparedStatement st=conn.prepareStatement(sql);
			st.setString(1, user.getUser_mail());
			ResultSet rs=st.executeQuery();
			if(!rs.next()) {
				insert(user);
			}
			PreparedStatement stmt=conn.prepareStatement(registerSql);
			stmt.setString(1, user.getUser_pwd());
			stmt.setString(2, user.getUser_name());
			stmt.setString(3, user.getUser_mail());
			
			stmt.execute();
			conn.close();
			return 0;
		}
		
	}
	public static boolean login(User user) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return false;
		}
		PreparedStatement stmt=conn.prepareStatement(loginSql);
		stmt.setString(1, user.getUser_mail());
		stmt.setString(2, user.getUser_pwd());
		ResultSet rs=stmt.executeQuery();
		conn.close();
		if(rs.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	public static boolean isUserExist(User user) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return false;
		}
		PreparedStatement stmt;
		stmt=conn.prepareStatement(isUserExistSql);
		stmt.setString(1, user.getUser_mail());
		ResultSet rs=stmt.executeQuery();
		conn.close();
		if(rs.next()) {
			return true;
		}
		else {
			return false;
		}
	}
	public static String getVer(User user) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return null;
		}
		PreparedStatement stmt;
		stmt=conn.prepareStatement(getVerSql);
		stmt.setString(1, user.getUser_mail());
		ResultSet rs=stmt.executeQuery();
		conn.close();
		if(rs.next()) {
			return rs.getString(1);
		}
		else {
			return null;
		}
	}
	public static int changePwd(User user) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			System.out.println("null");
			return 0;
		}
		if(!isUserExist(user)) {
			return 2;
		}
		if(!getVer(user).equals(user.getUser_ver())){
			return 1;
		}
		PreparedStatement stmt=conn.prepareStatement(changePwdSql);
		stmt.setString(1, user.getUser_pwd());
		stmt.setString(2, user.getUser_mail());
		stmt.executeUpdate();
		conn.close();
		return 0;
	}
	public static User getUser(User user) throws SQLException {
		DBUtil db=new DBUtil();
		Connection conn=db.getConnection();
		if(conn==null) {
			return null;
		}
		if(!isUserExist(user)) {
			return null;
		}
		PreparedStatement stmt=conn.prepareStatement(getUserSql);
		stmt.setString(1, user.getUser_mail());
		ResultSet rs=stmt.executeQuery();
		while(rs.next()) {
			user.setUser_name(rs.getString(2));
			user.setUser_ver(rs.getString(6));
		}
		
		conn.close();
		return user;
	}
}
