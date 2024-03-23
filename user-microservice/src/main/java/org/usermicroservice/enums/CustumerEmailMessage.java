package org.usermicroservice.enums;

public enum CustumerEmailMessage {
    PROFILE_SAVED_SUCCESSFULLY("USER FROM WEBSITE TRAILER"),
    PROFILE_UPDATED_SUCCESSFULLY("Votre profil a été modifié avec succès ."),
    RESET_PASSWORD_SUBJECT("Réinitialisez votre mot de passe | VEBENCO TRAILER"),
    VERIFICATION_SUBJECT("Confirmation du compte | Vebenco trailer");

    private final String msg;
    CustumerEmailMessage(String msg){
        this.msg = msg;
    }
    public String getMessage(){
        return this.msg;
    }
}
