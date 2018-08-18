package hello.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.function.Function;
import java.util.function.IntFunction;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.AuthenticationFailedException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHepler {
	private String from;
	private String smtpServer;
	private String port;

	private boolean ifAuth = false;
	private String username;
	private String password;
	private boolean isExchange = false;
	private String domain;
	private List<MimeBodyPart> attachments = new ArrayList<MimeBodyPart>();

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public boolean isExchange() {
		return isExchange;
	}

	public void setExchange(boolean isExchange) {
		this.isExchange = isExchange;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public void closeAuth() {
		this.ifAuth = false;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public MailHepler(String smtpServer, String from) {
		this.smtpServer = smtpServer;
		this.from = from;
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 * @throws NullPointerException
	 *             if username or password is null
	 */
	public MailHepler setAuth(String username, String password) {
		if (username == null) {
			throw new NullPointerException("username couldn't be null ");
		}
		if (password == null) {
			throw new NullPointerException("password couldn't be null ");
		}
		this.username = username;
		this.password = password;
		ifAuth = true;
		return this;

	}

	/**
	 * 
	 * @param domain
	 * @return
	 * @throws NullPointerException
	 *             if domain is null
	 */
	public MailHepler enableExchange(String domain) {
		if (domain == null) {
			throw new NullPointerException("domain couldn't be null ");
		}
		this.domain = domain;
		isExchange = true;
		return this;

	}

	public MailHepler addFileAttachment(String displayName, String filePath) throws MessagingException {
		DataSource fds = new FileDataSource(filePath);// �õ�����Դ
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setDataHandler(new DataHandler(fds));
		mbp.setFileName(displayName);
		attachments.add(mbp);
		return this;
	}

	public MailHepler addFileAttachment(String filePath) throws MessagingException {
		DataSource fds = new FileDataSource(filePath);// �õ�����Դ
		MimeBodyPart mbp = new MimeBodyPart();
		mbp.setDataHandler(new DataHandler(fds));
		mbp.setFileName(fds.getName());
		attachments.add(mbp);
		return this;
	}

	/**
	 * 
	 * @param displayName
	 *            发件人名
	 * @param to 收件人邮箱
	 * @param subject
	 *            主题
	 * @param content
	 *            内容 html
	 * @return {state:success/failed,message}
	 * @throws MessagingException
	 */
	public HashMap<String, String> send(String displayName, List<String> to, String subject, String content) {
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("state", "success");
		String message = "�ʼ����ͳɹ���";
		Session session = null;
		Properties props = new Properties();
		props.put("mail.smtp.host", smtpServer);

		if (isExchange) {
			if (domain == null || domain.equals("")) {
				throw new RuntimeException("domain is null");
			}
			props.setProperty("mail.smtp.auth.ntlm.domain", domain);
		}
		if (ifAuth) { // ��������Ҫ�����֤
			props.put("mail.smtp.auth", "true");
			// MailAuthenticator smtpAuth = new MailAuthenticator(username,
			// password);
			// session = Session.getDefaultInstance(props, smtpAuth);
			session = Session.getDefaultInstance(props);
		} else {
			props.put("mail.smtp.auth", "false");
			session = Session.getDefaultInstance(props, null);
		}
		session.setDebug(true);
		Transport trans = null;
		try {
			Message msg = new MimeMessage(session);
			try {
				Address from_address = new InternetAddress(from, displayName);
				msg.setFrom(from_address);
			} catch (java.io.UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			InternetAddress[] address = to.stream().map(new Function<String, InternetAddress>() {
				public InternetAddress apply(String s) {
					try {
						return new InternetAddress(s);
					} catch (AddressException e) {
						throw new RuntimeException("fail to create recipient address", e);
					}
				}
			}).toArray(new IntFunction<InternetAddress[]>() {
				public InternetAddress[] apply(int value) {
					return new InternetAddress[value];
				}
			});
			msg.setRecipients(Message.RecipientType.TO, address);
			msg.setSubject(subject);
			Multipart mp = new MimeMultipart();
			MimeBodyPart mbp = new MimeBodyPart();
			mbp.setContent(content, "text/html;charset=gb2312");
			mp.addBodyPart(mbp);
			if (null != attachments && !attachments.isEmpty()) {// �и���
				for (MimeBodyPart attachment : attachments) {
					mp.addBodyPart(attachment);
				}
			}
			msg.setContent(mp); // Multipart���뵽�ż�
			msg.setSentDate(new Date()); // �����ż�ͷ�ķ�������
			// �����ż�
			msg.saveChanges();
			trans = session.getTransport("smtp");
			trans.connect(smtpServer, username, password);
			trans.sendMessage(msg, msg.getAllRecipients());
			trans.close();

		} catch (AuthenticationFailedException e) {
			map.put("state", "failed");
			message = "发送失败\n" + "邮箱验证失败!";
			e.printStackTrace();
		} catch (MessagingException e) {
			message = "发送失败\n" + e.getMessage();
			map.put("state", "failed");
			e.printStackTrace();
			Exception ex = null;
			if ((ex = e.getNextException()) != null) {
				System.out.println(ex.toString());
				ex.printStackTrace();
			}
		}
		map.put("message", message);
		return map;
	}

	public static void main(String[] args) {
		String smtpServer = "smtp.163.com";
		String from = "XXX";
		String userName = from;
		String password = "xxx";
		List<String> to = Arrays.asList("xxx@xx.com");
		String subject = "修改密码";
		String content = "您正在修改密码，请访问以下链接进行操作：<br>\nhttps://www.163.com/updatePass?smp=mk3434kj23kjnj9u7nvg5434n23k4nm23n4j234j525nmnjj45j35nn5m2njnk5n32nk23523==";
		MailHepler mail = new MailHepler(smtpServer, from).setAuth(userName, password);
		System.out.println(mail.send("平台官方", to, subject, content));
	}
}
