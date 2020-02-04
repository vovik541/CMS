package app.CommonsEmail;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;
import org.apache.log4j.Logger;

import java.util.List;

public class MailCommons {

    public static final String CMS_MAIL = "cms.mail.reminder@gmail.com";
    public static final String PASSWORD_MAIL = "123456Seven";

    private static final Logger logger = Logger.getLogger(MailCommons.class);

    public static void sendMail(List<String> emails, String subject, String message){

        try {
            Email email = new SimpleEmail();

            // Configuration
            email.setHostName("smtp.googlemail.com");
            email.setSmtpPort(465);
            email.setAuthenticator(new DefaultAuthenticator(CMS_MAIL,
                    PASSWORD_MAIL));

            // Required for gmail
            email.setSSLOnConnect(true);

            // Sender
            email.setFrom(CMS_MAIL);

            // Email title
            email.setSubject(subject);

            // Email message.
            email.setMsg(message);

            // Receivers
//            email.addTo("conanzhill@gmail.com");

            for (String receiverEmail: emails){
                email.addTo(receiverEmail);
                logger.info(receiverEmail);
            }

            email.send();
            logger.info("Emails were sent");
        } catch (Exception e) {
            e.printStackTrace();
            logger.warn("Emails weren't sent");
        }
    }
}
