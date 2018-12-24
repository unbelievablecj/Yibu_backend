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
 * Servlet implementation class Verified
 * 处理发送验证码的请求，接收邮箱，发送邮件
 */
@WebServlet("/Verified")
public class Verified extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Verified() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//通过邮件发送验证码
		doPost(request, response);
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
		} catch (Exception e) {
			e.printStackTrace();
		}
		User user;
		Answer a=new Answer();
		user=g.fromJson(result, User.class);
		if(user==null) {
			a.setRes("fail");
			String s=g.toJson(a);
			pw.write(s);
			pw.flush();
			pw.close();
		}
		else {
			String mail=user.getUser_mail();
			MailTool mt =new MailTool();
			String ver;
			a.setRes("success");
			String s=g.toJson(a);
			pw.write(s);
			pw.flush();
			pw.close();
			try {
				ver = mt.sendmail(mail);
				user.setUser_ver(ver);
				UserDao.setVerify(user);
			} catch (MessagingException e) {
				log(e.getMessage());
				e.printStackTrace();
			} catch (SQLException e) {
				a.setRes("sql_fail");
				e.printStackTrace();
			}
			
		}
	}

}
