import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// Java program to send email 


public class SendEmail {

	@Autowired
	private static JavaMailSender javaSender;

	public static void main(String[] args) {
		
		javaSender = new JavaMailSenderImpl();
		// email ID of Recipient.
		String recipient = "just21097varg@gmail.com";

		// email ID of Sender.
		String sender = "just21097varg@gmail.com";

		try {
			// MimeMessage object.
			SimpleMailMessage message = new SimpleMailMessage();

			// Set From Field: adding senders email to from field.
			message.setFrom(sender);

			// Set To Field: adding recipient's email to from field.
			message.setTo(recipient);

			// Set Subject: subject of the email
			message.setSubject("This is Subject");

			// set body of the email.
			message.setText("This is a test mail");

			javaSender.createMimeMessage();
			javaSender.send(message);
			
			System.out.println("Mail successfully sent");
		}
		finally {
			
		}
	}
}
