package com.qiubai.util;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.qiubai.tool.ReadProperties;

public class JavaMail {
	
	private String smtp = ReadProperties.read("email", "smtp");
	private String auth = ReadProperties.read("email", "auth");
	private String user = ReadProperties.read("email", "user");
	private String name = ReadProperties.read("email", "name");
	private String pwd = ReadProperties.read("email", "pwd");
	private MimeMessage message;
	private Session session;

	public JavaMail() {
		Properties props = System.getProperties();
		props.setProperty("mail.smtp.host", smtp);
		props.setProperty("mail.smtp.auth", auth);
		session = Session.getInstance(props);
		/* s.setDebug(true);There is debug information after starting */
		message = new MimeMessage(session);
	}

	/**
	 * send email
	 * @param head
	 * @param content
	 * @param revicer
	 * @return
	 */
	public boolean sendEmail(String head, String content, String revicer) {
		try {
			InternetAddress from = new InternetAddress(user);
			message.setFrom(from);
			InternetAddress to = new InternetAddress(revicer);
			message.setRecipient(Message.RecipientType.TO, to);
			message.setSubject(head); // subject of email
			// Email content, also can make plain text "text/plain"
			message.setContent(content, "text/html;charset=utf-8");
			message.saveChanges();
			Transport transport = session.getTransport("smtp");
			// SMTP authentication,user name and password of the email you use
			transport.connect(smtp, name, pwd);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			return true;
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}