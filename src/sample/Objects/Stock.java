package sample.Objects;

public class Stock {
    private int stock_id;

    private int aantal;
    private int minimum;

    private Suplier suplier;

    public int getStock_id(){
        return stock_id;
    }
    public int getAantal(){
        return aantal;
    }
    public int getMinimum(){
        return minimum;
    }
    public Suplier getSuplier(){
        return suplier;
    }
    public void setSuplier(Suplier suplier){
        this.suplier = suplier;
    }
}
