package control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.GonglueDao;
import model.Answer;
import model.Ask;
import model.Gonglue;

/**
 * Servlet implementation class SearchNear
 * 接收经纬度，返回此经纬度最近的攻略
 * @author unbel
 */
@WebServlet("/SearchNear")
public class SearchNear extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchNear() {
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
		Ask ask;
		Gonglue gl=new Gonglue();
		ask=g.fromJson(result, Ask.class);
		Answer a=new Answer();
		if(ask==null) {
			a.setRes("fail");
		}
		else {
			try {
				gl.setLongitude(ask.getJing());
				gl.setLatitude(ask.getWei());
				List<Gonglue> res=new ArrayList<Gonglue>();
				List<Gonglue> ls = GonglueDao.searchNear(gl);
				Collections.sort(ls, new Comparator<Gonglue>(){
		            public int compare(Gonglue p1, Gonglue p2) {
		            	double d1=Math.sqrt(Math.pow(p1.getGjing()-gl.getGjing(), 2.0)+Math.pow(p1.getGwei()-gl.getGwei(), 2.0));
		            	double d2=Math.sqrt(Math.pow(p2.getGjing()-gl.getGjing(), 2.0)+Math.pow(p2.getGwei()-gl.getGwei(), 2.0));
		                if(d1 > d2){
		                    return 1;
		                }
		                if(d1 == d2){
		                    return 0;
		                }
		                return -1;
		            }
		        });
				for(int i=ask.getBegin()-1;i<ask.getEnd()&&i<ls.size();i++) {
					res.add(ls.get(i));
				}
				a.setRes(g.toJson(res));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
