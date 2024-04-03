package objectQLSP;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Promotion implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int promotionID;
    private String productID;
    private String supplierID;
    private double promotionRate;
    private Date startDate;
    private Date endDate;

    public Promotion(int promotionID, String productID, String supplierID, double promotionRate, Date startDate, Date endDate) {
        this.promotionID = promotionID;
        this.productID = productID;
        this.supplierID = supplierID;
        this.promotionRate = promotionRate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getPromotionID() {
        return promotionID;
    }

    public void setPromotionID(int promotionID) {
        this.promotionID = promotionID;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public double getPromotionRate() {
        return promotionRate;
    }

    public void setPromotionRate(double promotionRate) {
        this.promotionRate = promotionRate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Promotion ID: " + promotionID +
                ", Product ID: " + productID +
                ", Supplier ID: " + supplierID +
                ", Promotion Rate: " + promotionRate +
                ", Start Date: " + dateFormat.format(startDate) +
                ", End Date: " + dateFormat.format(endDate);
    }
}
