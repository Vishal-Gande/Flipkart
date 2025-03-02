public class Product {
    int productId;
    String productName;
    int productPrice;
    String productDescription;
    int quantity;

    public Product(int productId, String productName, int productPrice, String productDescription, int quantity) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productDescription = productDescription;
        this.quantity = quantity;
    }
}
