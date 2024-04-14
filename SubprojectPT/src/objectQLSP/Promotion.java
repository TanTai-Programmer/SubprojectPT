package objectQLSP;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Promotion implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String productID;
    private String supplierID;
    private double promotionRate;
    private Date startDate;
    private Date endDate;
    private String createdDate; // Thêm cột mới
 // Constructor mới cho Promotion không yêu cầu truyền giá trị cho createdDate
    public Promotion(String productID, String supplierID, double promotionRate, Date startDate, Date endDate) {
        this.productID = productID;
        this.supplierID = supplierID;
        this.promotionRate = promotionRate;
        this.startDate = startDate;
        this.endDate = endDate;
        // Không cần gán giá trị cho createdDate ở đây
    }

    public Promotion(String productID, String supplierID, double promotionRate, Date startDate, Date endDate, String createdDate) {
        this.productID = productID;
        this.supplierID = supplierID;
        this.promotionRate = promotionRate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdDate = createdDate;
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

    public String getCreatedDate() { // Getter cho cột mới
        return createdDate;
    }

    public void setCreatedDate(String createdDate) { // Setter cho cột mới
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return "Product ID: " + productID +
                ", Supplier ID: " + supplierID +
                ", Promotion Rate: " + promotionRate +
                ", Start Date: " + dateFormat.format(startDate) +
                ", End Date: " + dateFormat.format(endDate) +
                ", Created Date: " + createdDate; // Thêm vào chuỗi toString()
    }
}
