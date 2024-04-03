package objectQLSP;
import java.io.Serializable;

public class Product implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productID;
    private String productName;
    private double price;
    private int quantity;
    private String description;
    private String supplierID;

    public Product(String productID, String productName, double price, int quantity, String description, String supplierID) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
        this.description = description;
        this.supplierID = supplierID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }
    @Override
    public String toString() {
        return "Product ID: " + productID +
               ", Product Name: " + productName +
               ", Price: " + price +
               ", Quantity: " + quantity +
               ", Description: " + description +
               ", Supplier ID: " + supplierID;
    }
}
