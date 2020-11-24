package root.model;

public class User {
    private String name;
    private String surname;
    private String login;
    private String pass;

    public User(String name, String surname, String login, String pass) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.pass = pass;
    }

    public User(String login, String pass) {

        this.login = login;
        this.pass = pass;
    }

    public User() {
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

}
