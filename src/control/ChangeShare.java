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

import dao.GonglueDao;
import dao.UserDao;
import model.Answer;
import model.Gonglue;
import model.User;

/**
 * Servlet implementation class ChangeShare
 * 处理修改攻略类，通过id判断
 * @author unbel
 */
@WebServlet("/ChangeShare")
public class ChangeShare extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeShare() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		Gonglue gong;
		gong=g.fromJson(result, Gonglue.class);
		Answer a=new Answer();
		if(gong==null) {
			a.setRes("fail");
		}
		else {
			try {
				if(GonglueDao.update(gong)) {
					a.setRes("success");
				}
				else {
					a.setRes("fail");
				}
			} catch (SQLException e) {
				a.setRes("sql_wrong");
				e.printStackTrace();
			}
		}
		String s=g.toJson(a);
		pw.write(s);
		pw.flush();
		pw.close();
	}

}
