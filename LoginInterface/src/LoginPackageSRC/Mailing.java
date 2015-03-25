package LoginPackageSRC;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mailing {
	public static void main(String[] args) 
	{
		// Recipient's email ID needs to be mentioned.
		String to = "alexander.nill@mycit.ie";

		// Sender's email ID needs to be mentioned
		final String from = "raffkopf222@gmx.net";
		final String pwd = "mypassword";

		// Get system properties
		Properties props = System.getProperties();

		// Setup mail server
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "mail.gmx.net");
		props.put("mail.smtp.port", "587");
		
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(from, pwd);
			}
		  });
		 

		try 
		{
			// Create a default MimeMessage object.
			MimeMessage message = new MimeMessage(session);

			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));

			// Set To: header field of the header.
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					to));

			// Set Subject: header field
			message.setSubject("This is the Subject Line!");

			// Now set the actual message
			message.setText("This is actual message");

			// Send message
			Transport.send(message);
			System.out.println("Sent message successfully....");
		} catch (MessagingException mex) {
			mex.printStackTrace();
		}
	}
}
