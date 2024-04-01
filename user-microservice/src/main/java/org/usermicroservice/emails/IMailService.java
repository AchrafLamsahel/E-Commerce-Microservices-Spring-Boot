package org.usermicroservice.emails;

public interface IMailService {
    void sendMail(String toEmail, String subject, String body);
}
