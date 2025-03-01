import java.util.ArrayList;

public class Order {
    public int orderID;
    public int customerID;
    public ArrayList<Product> orderProducts;

    public Order(int orderID, int customerID, ArrayList<Product> orderProducts) {
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderProducts = new ArrayList<>();
        this.orderProducts.addAll(orderProducts);

    }
}
