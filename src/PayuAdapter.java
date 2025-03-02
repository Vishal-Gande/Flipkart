import java.util.Scanner;

public class PayuAdapter implements PaymentGateway{

    Payu payu = new Payu();
    public void upiPayment()
    {
        System.out.println("calling Payu upi");

        //take input string
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter your UPI ID");
        String upiId = sc.nextLine();

        payu.sendRequestToUpi(upiId);
    }
    public void netBankingPayment()
    {
        System.out.println("calling  payu net banking");
        payu.netBanking();
    }
}
