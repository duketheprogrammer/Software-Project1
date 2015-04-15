package LoginPackageSRC;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class Mailing {
	public static void main(String[] args) 
	{
		// sending emails to myself for testing
		String to [] = new String[2];
		to[0] = "alexander.nill@mycit.ie";
		to[1] = "anill@stud.hs-offenburg.de";

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

			for(int i = 0; i<to.length; i++)
			{
				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.BCC, new InternetAddress(
						to[i]));
			}

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
