package objectQLSP;
import java.io.Serializable;
import java.util.Date;

public class Invoice implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String invoiceID;
    private Date createDate;
    private double totalAmount;

    public Invoice(String invoiceID, Date createDate, double totalAmount) {
        this.invoiceID = invoiceID;
        this.createDate = createDate;
        this.totalAmount = totalAmount;
    }

    public String getInvoiceID() {
        return invoiceID;
    }

    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice ID: " + invoiceID +
                ", Create Date: " + createDate +
                ", Total Amount: " + totalAmount;
    }
}
