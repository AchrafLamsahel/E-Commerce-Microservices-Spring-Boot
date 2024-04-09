package org.authmicroservice.enums;

public enum CustomerMessageValidator {
    SAVED_SUCCESSFULLY("Saved successfully"),
    CHECK_EMAIL_FOR_VALIDATION("Check your email for validation");

    private final String msg;
    CustomerMessageValidator(String msg){
        this.msg = msg;
    }
    public String getMessage(){
        return this.msg;
    }
}
