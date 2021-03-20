package pl.skiba.springsecuritysimplefactorauth.model;

import javax.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @OneToOne
    private appUser appUser;

    public Token() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public pl.skiba.springsecuritysimplefactorauth.model.appUser getAppUser() {
        return appUser;
    }

    public void setAppUser(pl.skiba.springsecuritysimplefactorauth.model.appUser appUser) {
        this.appUser = appUser;
    }
}
