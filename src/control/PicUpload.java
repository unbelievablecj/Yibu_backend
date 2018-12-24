package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.google.gson.Gson;

import model.Answer;
import model.Gonglue;

/**
 * Servlet implementation class PicUpload
 * 处理图片上传请求，把图片存在本地，此webapp的根目录下，图片名使用gettime来获取
 * @author unbel 
 */
@WebServlet("/PicUpload")
public class PicUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PicUpload() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        PrintWriter pw= response.getWriter();
		Gson g=new Gson();
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		factory.setSizeThreshold(1024 * 1024);
		List items = null;
		String filename = new String();
		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}
		if(items==null) {
			pw.write("fail");
			pw.close();
		}
		else {
		Iterator iter = items.iterator();
		Answer a=new Answer();
		while (iter.hasNext()) {
			FileItem item = (FileItem) iter.next();
			if (!item.isFormField()) {
				filename = System.currentTimeMillis() + ".jpg";
				String path=request.getServletContext().getRealPath(filename);
				String imgsrc=path;
				System.out.println(imgsrc);
				InputStream is = item.getInputStream();
				File out=new File(imgsrc);
				if(!out.exists()) {
					out.createNewFile();
				}
				FileOutputStream fos = new FileOutputStream(imgsrc);
				byte b[] = new byte[1024 * 1024];
				int length = 0;
				while (-1 != (length = is.read(b))) {
					fos.write(b, 0, length);
				}
				fos.flush();
				fos.close();
				
				a.setRes(filename);
				pw.write(g.toJson(a));
				pw.flush();
				
			} else {
				a.setRes("fail");
				pw.write(g.toJson(a));
				pw.flush();
			}
		}
		
		pw.close();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
