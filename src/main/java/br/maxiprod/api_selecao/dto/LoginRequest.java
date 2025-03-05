package br.maxiprod.api_selecao.dto;

import java.util.Objects;

public class LoginRequest {

    private String usernameOrEmail;

    private String password;

    public LoginRequest() {
    }

    public LoginRequest(String usernameOrEmail, String password) {
        this.usernameOrEmail = usernameOrEmail;
        this.password = password;
    }

    public String getUsernameOrEmail() {
        return this.usernameOrEmail;
    }

    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginRequest usernameOrEmail(String usernameOrEmail) {
        setUsernameOrEmail(usernameOrEmail);
        return this;
    }

    public LoginRequest password(String password) {
        setPassword(password);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof LoginRequest)) {
            return false;
        }
        LoginRequest loginRequest = (LoginRequest) o;
        return Objects.equals(usernameOrEmail, loginRequest.usernameOrEmail)
                && Objects.equals(password, loginRequest.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(usernameOrEmail, password);
    }

    @Override
    public String toString() {
        return "{" +
                " usernameOrEmail='" + getUsernameOrEmail() + "'" +
                ", password='" + getPassword() + "'" +
                "}";
    }

}
