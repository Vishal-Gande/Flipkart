public class CourierService implements OrderPlacedObservers{

    public void onOrderPlaced(Order order)
    {
        System.out.println("calling courier service with order details");
    }

    public CourierService(Flipkart flipkart){

        flipkart.addOrderPlacedObservers(this);
    }

}
