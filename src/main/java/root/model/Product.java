package root.model;

public class Product {

    private int id;
    private String name;
    private String shape;
    private double length;
    private String size;
    private double price;
    private String ean;
    private Category category;

    public Product(int id, String name, String shape, double length, String size, double price, Category category,String ean) {
        this.id = id;
        this.name = name;
        this.shape = shape;
        this.length = length;
        this.size = size;
        this.price = price;
        this.category = category;
        this.ean = ean;
    }

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getEan() {
        return ean;
    }

    public void setEan(String ean) {
        this.ean = ean;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public enum Category{
        BUTTON1,
        BUTTON2,
    }
    @Override
    public Object clone() {
        return new Product(this.id, this.name, this.shape, this.price, this.size, this.length, this.category,this.ean);
    }
}

