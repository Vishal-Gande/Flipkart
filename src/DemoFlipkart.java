public class DemoFlipkart {

    public static void main(String[] args) {

        Flipkart flipkart = Flipkart.getInstance();

        // helps check if singleton is correctly implemented
        Flipkart flipkart2 = Flipkart.getInstance();

        // calls observer's constructor, which further calls add observer fn of flipkart
        CourierService cs = new CourierService(flipkart);
        NotificationService ns = new NotificationService(flipkart);

        User user1 = new User("vish", 1, "vish@gmail.com");
        User user2 = new User("prav", 2, "prav@gmail.com");
        User user3 = new User("neethu", 3, "neethu@gmail.com");

        flipkart.addUser(user1);
        flipkart.addUser(user2);
        flipkart.addUser(user3);

        flipkart.removeUser(user3);

        Product p1 = new Product(1,"iphone16", 58000,"apple phone ", 5);
        Product p2 = new Product(2, "TV", 44000, "Sony Bravia TV", 10);

        flipkart.addProduct(p1);
        flipkart.addProduct(p2);

        flipkart.addtoCart(user1,p1);
        flipkart.addtoCart(user1,p2);

        Order o1 = new Order(1,1, flipkart.getCart(user1));
        flipkart.placeOrder(user1,o1,"payu");

        flipkart.displayOrders(user1);

    }

}
