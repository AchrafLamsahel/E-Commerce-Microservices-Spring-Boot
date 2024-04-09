package org.usermicroservice.emails;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.usermicroservice.entities.ConfirmationToken;


@Service
@Slf4j
@AllArgsConstructor
public class MailService implements IMailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.email}")
    private static  String email;



    @Override
    public void sendConfirmationEmail(ConfirmationToken confirmationToken,String senderEmail) throws MessagingException {
        //MIME - HTML message
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(senderEmail); // Set sender's email address
        helper.setTo(confirmationToken.getUser().getEmail());
        //helper.setTo(confirmationToken.getUser().getLastname());
        helper.setSubject("Confirm you E-Mail - E-Commerce Application Registration");
        helper.setText("<html>" +
                        "<body>" +
                        "<h2>Dear "+ confirmationToken.getUser().getFirstname() + ",</h2>"
                        + "<br/> We're excited to have you get started. " +
                        "Please click on below link to confirm your account."
                        + "<br/> "  + generateConfirmationLink(confirmationToken.getConfirmationToken())+" " +
                        "<br/> Regards,<br/>" +
                        "E-Commerce Registration team" +
                        "</body>" +
                        "</html>"
                , true);
        javaMailSender.send(message);
    }

    private String generateConfirmationLink(String token){
        return "<a href=http://localhost:8085/users/confirm-account?token="+token+">Confirm Email</a>";
    }

}
