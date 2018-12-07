package sample.Objects;

public class Order {
    private int order_id;
    private int product_id;

    private Product product;
    private int count;

    public Order(Product product, int count){
        this.product = product;
        this.count = count;
    }

    public double getPrice(){
        if(product == null) return 0;

        return product.getPrice() * count;
    }
    public int getProductId(){
        return product.getProductId(); // TODO select one of those
        //return product_id;
    }
    public boolean isTheSameProduct(Product product){
        return isTheSameProduct(product.getProductId());
    }
    public boolean isTheSameProduct(int product_id){
        return product_id == this.product_id;
    }
    public void Add(){
        Add(1);
    }
    public void Add(int count){
        this.count += count;
    }
    public void Remove(){
        Remove(1);
    }
    public boolean Remove(int count){
        if(this.count < count){
            this.count = 0;
        }
        else{
            this.count -= count;
        }
        if(this.count <= 0){
            return true;
        }
        return false;
    }

    @Override
    public String toString(){
        return String.valueOf(count) + " x " + product.toString();
    }
}
