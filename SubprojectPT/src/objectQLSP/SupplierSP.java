package objectQLSP;
import java.io.Serializable;

public class SupplierSP implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String supplierID;
    private String supplierName;
    private String address;
    private String phoneNumber;

    public SupplierSP(String supplierID, String supplierName, String address, String phoneNumber) {
        this.supplierID = supplierID;
        this.supplierName = supplierName;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getSupplierID() {
        return supplierID;
    }

    public void setSupplierID(String supplierID) {
        this.supplierID = supplierID;
    }

    public String getSupplierName() {
        return supplierName;
    }

    public void setSupplierName(String supplierName) {
        this.supplierName = supplierName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "Supplier ID: " + supplierID +
                ", Supplier Name: " + supplierName +
                ", Address: " + address +
                ", Phone Number: " + phoneNumber;
    }
}
