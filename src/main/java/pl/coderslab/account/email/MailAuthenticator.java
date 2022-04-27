package pl.coderslab.account.email;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class MailAuthenticator extends Authenticator {
    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication("mariuszwojciechowski763@gmail.com", "codersl@b");
    }
}
