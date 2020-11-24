package root.database.impl.buttonrepository;

import org.springframework.stereotype.Component;
import root.model.Product;
import root.model.buttons.Button3Product;

import java.util.ArrayList;
import java.util.List;

@Component
public class Button3RepositoryImpl implements IButtonRepository {

    private final List<Button3Product> button3Products = new ArrayList<>();

    {
        this.button3Products.add(new Button3Product("Aluminium", Button3Product.ButtonNumber.BUTTON3));
    }



    @Override
    public List<Button3Product> getButton3() {
        List<Button3Product> button3Products = new ArrayList<>();

        for (Button3Product button3Product : this.button3Products) {
            if (button3Product.getButtonNumber() == Button3Product.ButtonNumber.BUTTON3) {
                button3Products.add((Button3Product) button3Products);
            }
        }
        return button3Products;
    }
}




