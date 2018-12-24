package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import dao.UserDao;
import model.Answer;
import model.User;

/**
 * Servlet implementation class Login
 * 处理用户登陆
 * @author unbel
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw= response.getWriter();
		Gson g=new Gson();
		StringBuffer sb = new StringBuffer("");
		String result = "";
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "utf-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
			br.close();
			result = sb.toString();
			System.out.println(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user;
		user=g.fromJson(result, User.class);
		 
		
		Answer a=new Answer();
		if(user==null) {
			user=new User();
		}
		else {
			try {
				if(UserDao.login(user)) {
					user=UserDao.getUser(user);
				}
				else {
					user=new User();
					user.setUser_pwd("no");
				}
			} catch (SQLException e) {
				user=new User();
				user.setUser_pwd("数据库错误");
				e.printStackTrace();
			}
		}
		String s=g.toJson(user);
		pw.write(s);
		pw.flush();
		pw.close();
	}

}
