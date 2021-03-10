package ru.dv.mailhelper.exceptions;

public class MailingNotFoundException extends RuntimeException {
    public MailingNotFoundException(String message) {
        super(message);
    }
}
