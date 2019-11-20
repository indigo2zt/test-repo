package pl.lodz.p.spjava.web.utils;


import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

@Stateless
public class EmailUtils {

    //@Resource(lookup = "mail/PrzychodniaMail")
    //private Session mailSession;


    public void sendEmail(String email, String tekst){
        /*Message message = new MimeMessage(mailSession);
        String sub = "test";

        try {
            message.setSubject(sub);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setText(tekst);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
*/
    }

}
