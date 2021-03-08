package pl.skorupska.springsecuritysimplefactorauthorization;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.skorupska.springsecuritysimplefactorauthorization.model.UserApp;
import pl.skorupska.springsecuritysimplefactorauthorization.repo.UserAppRepo;

@Configuration
public class Start {

    private UserAppRepo userAppRepo;


    public Start(UserAppRepo userAppRepo, PasswordEncoder passwordEncoder) {
        this.userAppRepo = userAppRepo;

        UserApp appUserJanusz = new UserApp();
        appUserJanusz.setUsername("Janusz");
        appUserJanusz.setPassword(passwordEncoder.encode("Janusz"));
        appUserJanusz.setRole("ROLE_ADMIN");
        appUserJanusz.setEnabled(true);

        UserApp appUserBogdan = new UserApp();
        appUserBogdan.setUsername("Bogdan");
        appUserBogdan.setPassword(passwordEncoder.encode("Bogdan"));
        appUserBogdan.setRole("ROLE_USER");
        appUserBogdan.setEnabled(true);

        userAppRepo.save(appUserJanusz);
        userAppRepo.save(appUserBogdan);
    }
}
