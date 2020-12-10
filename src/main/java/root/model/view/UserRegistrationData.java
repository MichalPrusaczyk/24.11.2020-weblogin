package root.model.view;

public class UserRegistrationData {
    private String name;
    private String surname;
    private String login;
    private String pass;
    private String repeatedPass;

    public UserRegistrationData(String name, String surname, String login, String pass, String repeatedPass) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.pass = pass;
        this.repeatedPass = repeatedPass;
    }

    public UserRegistrationData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRepeatedPass() {
        return repeatedPass;
    }

    public void setRepeatedPass(String repeatedPass) {
        this.repeatedPass = repeatedPass;
    }
}
