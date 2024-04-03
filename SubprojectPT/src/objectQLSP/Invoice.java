package objectQLSP;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Invoice implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int invoiceID;
    private String productID;
    private Date purchaseDate;
    private double totalAmount;

    public Invoice(int invoiceID, String productID, Date purchaseDate, double totalAmount) {
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.totalAmount = totalAmount;
    }

    public int getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(int invoiceID) {
        this.invoiceID = invoiceID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Invoice ID: " + invoiceID +
                ", Product ID: " + productID +
                ", Purchase Date: " + dateFormat.format(purchaseDate) +
                ", Total Amount: " + totalAmount;
    }
}
