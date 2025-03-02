public class NotificationService implements OrderPlacedObservers {

    public void onOrderPlaced(Order order)
    {
        System.out.println("Notification: Order placed successfully, sit back and enjoy!");
    }

    public NotificationService(Flipkart flipkart) {
        flipkart.addOrderPlacedObservers(this);
    }
}
