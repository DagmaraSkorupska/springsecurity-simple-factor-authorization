package pl.skorupska.springsecuritysimplefactorauthorization.exception;

public class UserNotExistException extends RuntimeException {

    public UserNotExistException(String username) {
        super("Could not find User" + username);
    }
}
