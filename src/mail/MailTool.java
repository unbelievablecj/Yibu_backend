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
 * ���ڷ�����֤���ʼ�
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
		String subject="һ����֤��";
		String content="��֤��Ϊ��"+ver+"\n";
		String fileList[]=null;
			Properties p = new Properties(); 
			p.put("mail.smtp.auth", "true");
			p.put("mail.transport.protocol", "smtp");
			p.put("mail.smtp.host", "smtp.163.com");
			p.put("mail.smtp.port", "25");
			// �����Ự
			Session session = Session.getInstance(p);
			Message msg = new MimeMessage(session); 
			// ������Ϣ
			BodyPart messageBodyPart = new MimeBodyPart();
			Multipart multipart = new MimeMultipart();
			msg.setFrom(new InternetAddress(from)); 
			// ������
			String toList = null;
			String toListcs = null;
			String toListms = null;
			// ����,
			if (to != null) {
				//toList = getMailList(to);
				toList=to;
				InternetAddress[] iaToList = InternetAddress.parse(toList);
				msg.setRecipients(Message.RecipientType.TO, iaToList); // �ռ���
			}

			// ����
			if (cs != null) {
				//toListcs = getMailList(cs);
				toListcs = cs;
				InternetAddress[] iaToListcs = InternetAddress.parse(toListcs);
				msg.setRecipients(Message.RecipientType.CC, iaToListcs); // ������
			}
			msg.setSentDate(new Date()); // ��������
			msg.setSubject(subject); // ����
			msg.setText(content); // ����
			// ��ʾ��html��ʽ���ı�����
			messageBodyPart.setContent(content, "text/html;charset=utf-8");
			multipart.addBodyPart(messageBodyPart);
			msg.setContent(multipart);
			// �ʼ�������������֤
			Transport tran = session.getTransport("smtp");
			tran.connect("smtp.163.com", "fzcj5114@163.com", "chenjing51");
			tran.sendMessage(msg, msg.getAllRecipients()); // ����
			System.out.println("�ʼ����ͳɹ�");
		return ver;
	}
	/**
	 * ��Ӹ���
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
	 * ���ڻ�ȡ�����б�
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