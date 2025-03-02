import java.util.ArrayList;
import java.util.HashMap;

public class Flipkart {

    public static Flipkart flipkart;

    private HashMap<Integer, Product> products; // product id, product
    private HashMap<Integer, User> users; // user id, user
    private HashMap<Integer, ArrayList<Order>> orders; // order ID as key, orders as values
    private HashMap<Integer, ArrayList<Product>> carts; // customer ID as key, products in cart as values
    private ArrayList<OrderPlacedObservers> orderPlacedObservers;

    private Flipkart(){
        this.products = new HashMap<>();
        this.users = new HashMap<>();
        this.orders = new HashMap<>();
        this.carts = new HashMap<>();
        this.orderPlacedObservers = new ArrayList<>();
    }

    public void addOrderPlacedObservers(OrderPlacedObservers opo) {
        this.orderPlacedObservers.add(opo);
    }

    // calling all order observers
    private void callOrderPlacedObservers(Order order) {
        for (OrderPlacedObservers opo : orderPlacedObservers) {
            opo.onOrderPlaced(order);
        }
    }


    // singleton pattern
    public static  Flipkart getInstance(){
        if(flipkart == null) {
            synchronized (Flipkart.class) {
                if (flipkart == null) {
                    flipkart = new Flipkart();
                }
                return flipkart;
            }
        }
        return flipkart;
    }

    public void addUser(User user)
    {
        this.users.put(user.userId, user);
        System.out.println("user added: " + user.userId);
    }

    public void updateUser(User user)
    {
        this.users.remove(user.userId);
        this.users.put(user.userId, user);
    }

    public void removeUser(User user)
    {
        this.users.remove(user.userId);
    }

    public void addProduct(Product product)
    {
        this.products.put(product.productId, product );
    }
    public void updateProduct(Product product)
    {
        this.products.remove(product.productId);
        this.products.put(product.productId, product);
    }

    public void addtoCart(User user, Product product)
    {
        if(this.carts.containsKey(user.userId))
        {
            this.carts.get(user.userId).add(product);
        }
        else
        {
            ArrayList<Product> products = new ArrayList<>();
            products.add(product);
            this.carts.put(user.userId, products);
        }
    }

    public void removefromCart(User user, Product product)
    {
        this.carts.get(user.userId).remove(product);
        if(this.carts.get(user.userId).isEmpty())
        {
            this.carts.remove(user.userId);
        }
    }


    public void dropCart(User user)
    {
        this.carts.remove(user.userId);
    }

    private boolean checkInventory(ArrayList<Product> products)
    {
        synchronized(this) {

            for (int i = 0; i < products.size(); i++) {

                Product product = products.get(i);
                if (this.products.containsKey(product.productId) && this.products.get(product.productId).quantity > 0) {
                    System.out.println("product:" + product.productName + " is available");
                }
                else {
                    System.out.println("product:" + product.productName + " is not available");
                    System.out.println("Order cannot be placed");
                    return false;
                }
            }
            return true;
        }

    }

    private boolean processPayment(String paymentMethod){

        // adapter and factory design patterns
        // also depicts Liskov's substitution principle
        PaymentGatewayFactory pg = new PaymentGatewayFactory(); // create an instance
        PaymentGateway gateway = pg.getPaymentGateway(paymentMethod);
        gateway.upiPayment();
        return true;
    }

    private void registerOrder(User user, Order order)
    {
        if(this.orders.containsKey(user.userId))
        {
            this.orders.get(user.userId).add(order);
        }
        else
        {
            ArrayList<Order> orders = new ArrayList<>();
            orders.add(order);
            this.orders.put(user.userId, orders);
        }
    }

    private void adjustInventoryPostOrder(Order order)
    {
        for(int i=0;i<order.orderProducts.size();i++)
        {
            Product product = order.orderProducts.get(i);
            this.products.get(product.productId).quantity--;
        }
    }

    public void placeOrder(User user, Order order , String paymentMethod)
    {
        // check inventory and lock the product for user
        if(this.checkInventory(order.orderProducts))
        {
            // payment
            if(this.processPayment(paymentMethod))
            {
                // place order and manage inventory
                this.registerOrder(user, order);
                this.adjustInventoryPostOrder(order);

                // call all order observers - payment gw, notif services, couriers etc
                this.callOrderPlacedObservers(order);
            }
        }
    }

    public void displayOrders(User user)
    {
        for(Order order : this.orders.get(user.userId))
        {
            System.out.println("orderID:" + order.orderID );
        }
    }

    public void cancelOrder(User user, Order order)
    {
        this.orders.get(user.userId).remove(order);
    }

    public ArrayList<Product> getCart(User user)
    {
        return this.carts.get(user.userId);
    }

}
