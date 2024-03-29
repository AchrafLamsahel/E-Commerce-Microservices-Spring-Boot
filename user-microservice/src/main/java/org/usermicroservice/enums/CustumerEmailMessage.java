package org.usermicroservice.enums;

public enum CustumerEmailMessage {
    PROFILE_SAVED_SUCCESSFULLY("Your Profil saved Successfully "),
    PROFILE_UPDATED_SUCCESSFULLY("Your Profil updated Successfully ."),
    RESET_PASSWORD_SUBJECT("Réinitialisez votre mot de passe "),
    VERIFICATION_SUBJECT("Confirmation du compte | Vebenco trailer");

    private final String msg;
    CustumerEmailMessage(String msg){
        this.msg = msg;
    }
    public String getMessage(){
        return this.msg;
    }
}
