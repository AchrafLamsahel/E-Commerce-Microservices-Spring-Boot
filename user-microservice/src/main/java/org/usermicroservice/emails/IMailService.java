package org.usermicroservice.emails;

import jakarta.mail.MessagingException;
import org.usermicroservice.entities.ConfirmationToken;

public interface IMailService {
    void sendConfirmationEmail(ConfirmationToken confirmationToken,String senderEmail)
            throws MessagingException;
}
