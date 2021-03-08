package pl.skorupska.springsecuritysimplefactorauthorization.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skorupska.springsecuritysimplefactorauthorization.model.Token;
import pl.skorupska.springsecuritysimplefactorauthorization.model.UserApp;
import pl.skorupska.springsecuritysimplefactorauthorization.repo.TokenRepo;
import pl.skorupska.springsecuritysimplefactorauthorization.repo.UserAppRepo;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class UserService {

    private final UserAppRepo userAppRepo;
    private final PasswordEncoder passwordEncoder;
    private final TokenRepo tokenRepo;
    private final MailService mailService;

    public UserService(UserAppRepo userAppRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo, MailService mailService) {
        this.userAppRepo = userAppRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }

    public void addUser(UserApp userApp) {
        userApp.setPassword(passwordEncoder.encode(userApp.getPassword())); //podaje jawne a potem koduje
        userApp.setRole("ROLE_USER");
        userAppRepo.save(userApp);
        sendToken(userApp);
    }

    private void sendToken(UserApp userApp) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setUserApp(userApp);
        tokenRepo.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue;

        try {
            mailService.sendMail(userApp.getEmail(), "Potwierdzaj to", url, false );
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
