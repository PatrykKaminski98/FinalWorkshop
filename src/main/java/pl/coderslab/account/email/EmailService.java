package pl.coderslab.account.email;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailService{

    private final static String SUBJECT = "Confirm email in 'Policzona Micha!;";

    public void send(String to, String email) {
        try{
            MimeMessage message = MailMessagePreparer.prepareTextMessageObject(to, SUBJECT, email);
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}