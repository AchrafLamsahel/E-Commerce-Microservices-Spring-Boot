package org.usermicroservice.exceptions;

public class RoleNoteFoundException extends RuntimeException{
    public RoleNoteFoundException(String message) {
        super(message);
    }
}
