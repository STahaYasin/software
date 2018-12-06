package sample.Handlers;

import sample.Objects.Product;
import sample.SQL_Classes.SQLConnectionManager;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ProductsManager {
    private static ProductsManager instance;

    private ArrayList<Product> products;

    public static ProductsManager getInstance() {
        if(instance == null) instance = new ProductsManager();
        return instance;
    }

    public ArrayList<Product> getProducts(){
        return products;
    }

    private ProductsManager(){
        products = new ArrayList<>();
        UpdateList();
    }

    public void UpdateList(){
        if(products == null) products = new ArrayList<>();

        try {
            Connection connection = SQLConnectionManager.getConnection();
            Statement statement = connection.createStatement();
            ResultSet sqlProduct = statement.executeQuery("SELECT * FROM products");

            while(sqlProduct.next()){
                Product product = new Product(sqlProduct.getInt("product_id"));
                product.setName(sqlProduct.getString("name"));
                product.setDescription(sqlProduct.getString("description"));
                product.setPrice(sqlProduct.getInt("price"));

                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // TODO alert error;
        }
    }

    public void AddProduct(Product product){
        if(products == null) products = new ArrayList();
    }
}
