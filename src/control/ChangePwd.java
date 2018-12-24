package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.UserDao;
import mail.MailTool;
import model.Answer;
import model.User;

/**
 * Servlet implementation class ChangePwd
 * 处理更改密码的请求，首先核对验证码在修改
 * @author unbel
 */
@WebServlet("/ChangePwd")
public class ChangePwd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePwd() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user;
		user=g.fromJson(result, User.class);
		Answer a=new Answer();
		if(user==null) {
			a.setRes("fail");
		}
		else {
			int tmp;
			try {
				tmp=UserDao.changePwd(user);
				log(new Integer(tmp).toString());
				if(tmp==1) {
					a.setRes("Ver_Wrong");
				}
				else if(tmp==2) {
					a.setRes("User_Not_Exist");
				}
				else if(tmp==0){
					a.setRes("success");
				}
			} catch (SQLException e) {
				a.setRes("fail");
				e.printStackTrace();
			}
		}
		String s=g.toJson(a);
		pw.write(s);
		pw.flush();
		pw.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
