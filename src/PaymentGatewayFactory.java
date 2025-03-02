// factory design pattern

public class PaymentGatewayFactory {

    PaymentGateway getPaymentGateway(String paymentGateway) {
        switch (paymentGateway) {
            case "payu":
                PayuAdapter payuAdapter = new PayuAdapter();
                return payuAdapter;
            default:
                RazorpayAdapter razorpayAdapter = new RazorpayAdapter();
                return razorpayAdapter;
        }
    }
}
