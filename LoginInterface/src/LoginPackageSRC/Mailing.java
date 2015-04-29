package LoginPackageSRC;

import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;

public class Mailing {
	ArrayList<String> recipients;
	String subjectLine, mailContent;
	public Mailing() {
		super();
		recipients = new ArrayList<String>();
	}
	void addRecipients(String recipient)
	{
		recipients.add(recipient);
	}	
	public void addMemberAccounts(ArrayList<MemberAccount> arrayList) {
		Iterator<MemberAccount> iter = arrayList.iterator();
		while(iter.hasNext())
		{
			MemberAccount committee = iter.next();
			addRecipients(committee.getEmail());
		}
	}
	public void setSubjectLine(String subjectLine) {
		this.subjectLine = subjectLine;
	}
	public void setMailContent(String mailContent) {
		this.mailContent = mailContent;
	}
	public void sendMail()
	{
		// The sending mail and password
		final String from = "societycit@gmail.com";		
		final String pwd = "as1234df";

		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
			new javax.mail.Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(from, pwd);
				}
			}
		);
		try 
		{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			Iterator<String> iter = recipients.iterator();
			while(iter.hasNext())
			{
				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(
						iter.next()));
			}

			// Set Subject: header field
			message.setSubject(subjectLine);

			// Now set the actual message
			message.setText(mailContent);

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}

}
