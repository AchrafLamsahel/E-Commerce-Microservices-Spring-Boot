package org.usermicroservice.emails;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailService implements IMailService {
    private final JavaMailSender javaMailSender;

    @Override
    public void sendMail(String toEmail, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom("ecommercemicroservice2024@gmail.com");
        simpleMailMessage.setTo(toEmail);
         simpleMailMessage.setText(body);
         simpleMailMessage.setSubject(subject);
         javaMailSender.send(simpleMailMessage);
         log.info("Email Sent Successfully !");
    }
}
