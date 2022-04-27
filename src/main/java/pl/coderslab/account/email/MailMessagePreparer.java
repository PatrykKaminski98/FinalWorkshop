package pl.coderslab.account.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailMessagePreparer {
    public static MimeMessage prepareTextMessageObject(String recipent, String subject,String content) throws MessagingException {
        MimeMessage textMessage = prepareMessageObject(recipent, subject);
        textMessage.setContent(content, "text/html");
        return textMessage;
    }

    private static MimeMessage prepareMessageObject(String recipent, String subject) throws MessagingException{
        //prepare configuration
        Properties properties = MailConfiguration.getConfiguration();
        MailAuthenticator authenticator = new MailAuthenticator();

        //create session
        Session session = Session.getInstance(properties, authenticator);

        //prepare Message
        MimeMessage mimeMessage = new MimeMessage(session);
        mimeMessage.setSubject(subject,"UTF-8");
        PasswordAuthentication passwordAuthentication = authenticator.getPasswordAuthentication();
        mimeMessage.setFrom(passwordAuthentication.getUserName());
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipent));
        return mimeMessage;

    }
}
