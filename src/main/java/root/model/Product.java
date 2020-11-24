package root.model;

public class Product {

    private String name;
    private String shape;
    private double length;
    private String size;
    private Category category;

    public Product(String name, String shape, double length,String size, Category category) {
        this.name = name;
        this.shape = shape;
        this.length = length;
        this.size = size;
        this.category = category;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public enum Category{
        BUTTON1,
        BUTTON2,
        BUTTON4;
    }
}

