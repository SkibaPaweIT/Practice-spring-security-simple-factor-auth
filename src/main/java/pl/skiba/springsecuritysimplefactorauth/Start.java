package pl.skiba.springsecuritysimplefactorauth;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.TransactionUsageException;
import pl.skiba.springsecuritysimplefactorauth.model.appUser;
import pl.skiba.springsecuritysimplefactorauth.repo.AppUserRepo;

@Configuration
public class Start {

    private AppUserRepo appUserRepo;

    public Start(AppUserRepo appUserRepo, PasswordEncoder passwordEncoder) {
        this.appUserRepo = appUserRepo;

        appUser admin = new appUser();
        admin.setUsername("jan");
        admin.setPassword(passwordEncoder.encode("nowak"));
        admin.setRole("ROLE_ADMIN");
        admin.setEnabled(true);
        appUserRepo.save(admin);

        appUser appUser = new appUser();
        appUser.setUsername("marek");
        appUser.setPassword(passwordEncoder.encode("marek"));
        appUser.setRole("ROLE_USER");
        appUser.setEnabled(true);
        appUserRepo.save(appUser);
    }




}
