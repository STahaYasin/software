package sample;

public class Product {
    private int product_id;
    private int stock_id;

    private String name;
    private String description;
    private int price; // in cents

    private Stock stock;

    public String getName(){
        return name;
    }
    public String getDescription(){
        return description;
    }

    public int getProductId(){
        return product_id;
    }
    public int getPrice(){
        return price;
    }

    public Stock getStock(){
        return stock;
    }
}
