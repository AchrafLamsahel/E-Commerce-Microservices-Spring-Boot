package org.usermicroservice.emails;

public interface IMailService {
    void sendForRegister(String to, String subject, String body);
}
