package root.services;

public interface IBasketService {
    void addToBasket(int productId);
    double calculateBill();
    void removeProductFromBasket(int productId);
}