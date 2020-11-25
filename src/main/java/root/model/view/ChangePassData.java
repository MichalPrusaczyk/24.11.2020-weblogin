package root.model.view;

public class ChangePassData {
    private String pass;
    private String newPass;
    private String repeatedNewPass;

    public ChangePassData(String pass, String newPass, String repeatedNewPass) {
        this.pass = pass;
        this.newPass = newPass;
        this.repeatedNewPass = repeatedNewPass;
    }

    public ChangePassData() {
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getNewPass() {
        return newPass;
    }

    public void setNewPass(String newPass) {
        this.newPass = newPass;
    }

    public String getRepeatedNewPass() {
        return repeatedNewPass;
    }

    public void setRepeatedNewPass(String repeatedNewPass) {
        this.repeatedNewPass = repeatedNewPass;
    }
}
