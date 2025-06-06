package model;
public class Part extends Base {
    private String name;
    private int quantity;
    private double price;

    public Part() {}

    public Part(int id, String name, int quantity, double price) {
        super(id);
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}
