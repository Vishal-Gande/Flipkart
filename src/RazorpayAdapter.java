public class RazorpayAdapter implements PaymentGateway{

    Razorpay razorpay = new Razorpay();
    public void upiPayment(){

        // call actual third party code now - which cud have diff fn signatures, diff implementation etc
        razorpay.generateUpiQR();
    }

    public void netBankingPayment(){
        // call rz netbanking
    }
}
