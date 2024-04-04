package objectQLSP;
import java.io.Serializable;
import java.util.Date;

public class InvoiceDetail implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String invoiceID;
    private String productID;
    private String productName;
    private double promotionRate;
    private double price;
    private Date createDate;

    public InvoiceDetail(String invoiceID, String productID, String productName, double promotionRate, double price, Date createDate) {
        this.invoiceID = invoiceID;
        this.productID = productID;
        this.productName = productName;
        this.promotionRate = promotionRate;
        this.price = price;
        this.createDate = createDate;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
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

    public double getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(double promotionRate) {
        this.promotionRate = promotionRate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return "Invoice ID: " + invoiceID +
                ", Product ID: " + productID +
                ", Product Name: " + productName +
                ", Promotion Rate: " + promotionRate +
                ", Price: " + price +
                ", Create Date: " + createDate;
    }
}
