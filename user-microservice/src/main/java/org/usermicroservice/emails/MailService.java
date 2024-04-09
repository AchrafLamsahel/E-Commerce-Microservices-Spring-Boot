package org.usermicroservice.emails;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class MailService implements IMailService {
    private final JavaMailSender javaMailSender;
    @Value("${spring.mail.email}")
    private static  String email;
    @Override
    public void sendMail(String toEmail, String subject, String body) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(email);
        simpleMailMessage.setTo(toEmail);
         simpleMailMessage.setText(body);
         simpleMailMessage.setSubject(subject);
         javaMailSender.send(simpleMailMessage);
         log.info("Email Sent Successfully !");
    }

    @Async
    public void sendEmail(SimpleMailMessage email) {
        javaMailSender.send(email);
    }
}
