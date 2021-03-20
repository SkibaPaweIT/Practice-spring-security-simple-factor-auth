package pl.skiba.springsecuritysimplefactorauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.skiba.springsecuritysimplefactorauth.model.Token;
import pl.skiba.springsecuritysimplefactorauth.model.appUser;
import pl.skiba.springsecuritysimplefactorauth.repo.AppUserRepo;
import pl.skiba.springsecuritysimplefactorauth.repo.TokenRepo;

import javax.mail.MessagingException;
import java.util.UUID;

@Service
public class userService {

    private AppUserRepo appUserRepo;
    private TokenRepo tokenRepo;
    private PasswordEncoder passwordEncoder;
    private MailService mailService;

    @Autowired
    public userService(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder, TokenRepo tokenRepo , MailService mailService) {
        this.appUserRepo = appUserRepo;
        this.passwordEncoder = passwordEncoder;
        this.tokenRepo = tokenRepo;
        this.mailService = mailService;
    }

    public void addUser(appUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole("ROLE_USER");
        appUserRepo.save(appUser);
        sendToken(appUser);
    }

    private void sendToken(appUser appUser) {
        String tokenValue = UUID.randomUUID().toString();
        Token token = new Token();
        token.setValue(tokenValue);
        token.setAppUser(appUser);
        tokenRepo.save(token);

        String url = "http://localhost:8080/token?value=" + tokenValue; //Tu powinny zostać użyte zmienne środowiskowe albo informacje w properties
        try {
            mailService.sendMail(appUser.getUsername(), "Potwierdzaj to!" , url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
