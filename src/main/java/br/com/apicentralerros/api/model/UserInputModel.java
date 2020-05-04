package br.com.apicentralerros.api.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserInputModel {

    @NotNull
    private String email;

    @NotNull
    @Size(min = 6, max = 6)
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
