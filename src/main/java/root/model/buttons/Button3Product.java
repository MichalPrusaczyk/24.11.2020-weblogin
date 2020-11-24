package root.model.buttons;

public class Button3Product {

    private String name;
    private ButtonNumber buttonNumber;

    public Button3Product(String name, ButtonNumber buttonNumber) {
        this.name = name;
        this.buttonNumber = buttonNumber;
    }

    public Button3Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ButtonNumber getButtonNumber() {
        return buttonNumber;
    }

    public void setButtonNumber(ButtonNumber buttonNumber) {
        this.buttonNumber = buttonNumber;
    }

    public enum ButtonNumber{
        BUTTON3;
    }
}

