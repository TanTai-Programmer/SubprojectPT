package interfaceQLSP;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import objectQLSP.InvoiceDetail;
import objectQLSP.Product;
import objectQLSP.Promotion;
import objectQLSP.SupplierSP;

public interface interfaceProductManager extends Remote {
	   // Chức năng Thêm
    public void addProduct(Product product) throws RemoteException;
    public void addPromotion(Promotion promotion) throws RemoteException;
    public void addSupplier(SupplierSP supplier) throws RemoteException;

    // Chức năng Đọc
    public List<Product> getProducts() throws RemoteException;
    
    public List<InvoiceDetail> getInvoiceDetails() throws RemoteException;

    public List<Promotion> getPromotions() throws RemoteException;
    public List<SupplierSP> getSuppliers() throws RemoteException;
    
    // Chức năng Cập nhật
    public void updateProduct(Product product) throws RemoteException;
    public void updatePromotion(Promotion promotion) throws RemoteException;
    public void updateSupplier(SupplierSP supplier) throws RemoteException;
    
    // Chức năng Xóa
    // Kiểu dữ liệu String vì id nhập vào không phải số nguyên vd: sp00001
    public void deleteProduct(String productID) throws RemoteException;
    void deletePromotion(String productID, String supplierID) throws RemoteException;
    public void deleteSupplier(String supplierID) throws RemoteException;
    
    // Chức năng Tìm kiếm
    public List<Product> searchProducts(String keyword) throws RemoteException;
    public List<Promotion> searchPromotions(String keyword) throws RemoteException;
    public List<SupplierSP> searchSuppliers(String keyword) throws RemoteException;
    List<InvoiceDetail> searchInvoiceDetails(String invoiceID) throws RemoteException;
}
