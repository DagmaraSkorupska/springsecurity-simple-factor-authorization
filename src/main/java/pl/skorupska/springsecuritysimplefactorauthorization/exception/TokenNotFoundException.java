package pl.skorupska.springsecuritysimplefactorauthorization.exception;

public class TokenNotFoundException extends RuntimeException{

    public TokenNotFoundException(String value) {
        super("Could not find" + value);
    }
}
