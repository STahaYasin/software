package sample.Objects;

import sample.Objects.Stock;

public class Product {
    private int product_id;
    //private int stock_id;

    private String name;
    private String description;
    private int price; // in cents

    private Stock stock;

    public Stock getStock(){
        return stock;
    }

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

    public void setName(String name){ this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(int price){ this.price = price; }

    public Product(){
        this(-1);
    }

    /**
     * Initializes the new product with its id, default value = -1
     * @param productId
     */
    public Product(int productId){
        this.product_id = productId;
    }

    @Override
    public String toString(){
        return name + " (" + description + "): " + price;
    }
}
