package com.example.sprint_7.Type;

public class CourierCredential {

    private String login;
    private String password;

    public CourierCredential() {
    }

    public CourierCredential(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static CourierCredential getCredentials(Courier courier){
        return new CourierCredential(courier.getLogin(),courier.getPassword());
    }
}
