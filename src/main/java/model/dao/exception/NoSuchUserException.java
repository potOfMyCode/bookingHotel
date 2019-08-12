package model.dao.exception;

public class NoSuchUserException extends Exception {
    public NoSuchUserException(String message){
        super(message);
    }
    public NoSuchUserException(String message, Throwable cause){
        super(message, cause);
    }
}
