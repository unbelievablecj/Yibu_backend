package mail;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.*;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;

import dao.UserDao;
import model.User;
/**
 * 用于发送验证码邮件
 * @author unbel
 *
 */
public class MailTool {
	public MailTool() {

	}
	/**
	 * send mail,return ver
	 */
	public String sendmail(String to)throws  MessagingException{
		Random r=new Random();
		int ran=r.nextInt(1000000);
		String ver=new Integer(ran).toString();
		String s[]= {"","0","00","000","0000","00000"};
		int i=6-ver.length();
		ver=s[i]+ver;
		String from="fzcj5114@163.com";
		String cs="fzcj5114@163.com";
		String ms=null;
		String subject="一步验证码";
		String content="验证码为："+ver+"\n";
		String fileList[]=null;
			Properties p = new Properties(); 
			p.put("mail.smtp.auth", "true");
			p.put("mail.transport.protocol", "smtp");
			p.put("mail.smtp.host", "smtp.163.com");
			p.put("mail.smtp.port", "25");
			// 建立会话
			Session session = Session.getInstance(p);
			Message msg = new MimeMessage(session); 
			// 建立信息
			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			msg.setFrom(new InternetAddress(from)); 
			// 发件人
			String toList = null;
			String toListcs = null;
			String toListms = null;
			// 发送,
			if (to != null) {
				//toList = getMailList(to);
				toList=to;
				InternetAddress[] iaToList = InternetAddress.parse(toList);
				msg.setRecipients(Message.RecipientType.TO, iaToList); // 收件人
			}

			// 抄送
			if (cs != null) {
				//toListcs = getMailList(cs);
				toListcs = cs;
				InternetAddress[] iaToListcs = InternetAddress.parse(toListcs);
				msg.setRecipients(Message.RecipientType.CC, iaToListcs); // 抄送人
			}
			msg.setSentDate(new Date()); // 发送日期
			msg.setSubject(subject); // 主题
			msg.setText(content); // 内容
			// 显示以html格式的文本内容
			messageBodyPart.setContent(content, "text/html;charset=utf-8");
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			// 邮件服务器进行验证
			Transport tran = session.getTransport("smtp");
			tran.connect("smtp.163.com", "fzcj5114@163.com", "chenjing51");
			tran.sendMessage(msg, msg.getAllRecipients()); // 发送
			System.out.println("邮件发送成功");
		return ver;
	}
	/**
	 * 添加附件
	 * @param fileList
	 * @param multipart
	 * @throws MessagingException
	 * @throws UnsupportedEncodingException
	 */
	public void addTach(String fileList[], Multipart multipart)
			throws MessagingException, UnsupportedEncodingException {
		for (int index = 0; index < fileList.length; index++) {
			MimeBodyPart mailArchieve = new MimeBodyPart();
			FileDataSource fds = new FileDataSource(fileList[index]);
			mailArchieve.setDataHandler(new DataHandler(fds));
			mailArchieve.setFileName(MimeUtility.encodeText(fds.getName(), "GBK", "B"));
			multipart.addBodyPart(mailArchieve);
		}
	}
	/**
	 * 用于获取发送列表
	 * @param mailArray
	 * @return
	 */
	private String getMailList(String[] mailArray) {

		StringBuffer toList = new StringBuffer();
		int length = mailArray.length;
		if (mailArray != null && length < 2) {
			toList.append(mailArray[0]);
		} else {
			for (int i = 0; i < length; i++) {
				toList.append(mailArray[i]);
				if (i != (length - 1)) {
					toList.append(",");
				}

			}
		}
		return toList.toString();

	}
}