package interfaceQLSP;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

import objectQLSP.Invoice;
import objectQLSP.InvoiceDetail;
import objectQLSP.Product;
import objectQLSP.Promotion;
import objectQLSP.SupplierSP;

public interface interfaceProductManager extends Remote {
	   // Chức năng Thêm
    public void addProduct(Product product) throws RemoteException;
    public void addPromotion(Promotion promotion) throws RemoteException;
    public void addSupplier(SupplierSP supplier) throws RemoteException;
    // Tạo hóa đơn
    boolean createInvoice(List<String> productIds, List<Integer> quantities) throws RemoteException;
    // Chức năng Đọc
    public String getSupplierID(String productID) throws RemoteException;
    
    public List<Product> getProducts() throws RemoteException;
    
    public List<InvoiceDetail> getInvoiceDetails() throws RemoteException;
    public List<Invoice> getInvoice() throws RemoteException;
    public List<Promotion> getPromotions() throws RemoteException;
    public List<SupplierSP> getSuppliers() throws RemoteException;
    
    // Chức năng Cập nhật
    public void updateProduct(Product product) throws RemoteException;
    public void updatePromotion(Promotion promotion) throws RemoteException;
    public void updateSupplier(SupplierSP supplier) throws RemoteException;
    
    // Chức năng Xóa
    // Kiểu dữ liệu String vì id nhập vào không phải số nguyên vd: sp00001
    public void deleteProduct(String productID) throws RemoteException;
    public void deletePromotion(String productID, String supplierID, String promotionRate, String startDateString, String endDateString) throws RemoteException;
    public void deleteSupplier(String supplierID) throws RemoteException;
    
    // Chức năng Tìm kiếm
    public List<Product> searchProducts(String keyword) throws RemoteException;
    public List<Promotion> searchPromotions(String keyword) throws RemoteException;
    public List<SupplierSP> searchSuppliers(String keyword) throws RemoteException;
    public List<InvoiceDetail> searchInvoiceDetails(String invoiceID) throws RemoteException;
    public List<Invoice> searchInvoice(String invoiceID) throws RemoteException;
    
//    // Chức năng sắp xếp sản phẩm
//    public List<Product> sortProductsQuantityASC() throws RemoteException;
//    public List<Product> sortProductsPriceASC() throws RemoteException;
//    public List<Product> sortProductsQuantityDESC() throws RemoteException;
//    public List<Product> sortProductsPriceDESC() throws RemoteException;
//    
    public List<Product> sortProductsByPriceAscending(List<Product> productList) throws RemoteException;
    public List<Product> sortProductsByPriceDescending(List<Product> productList) throws RemoteException;
    public List<Product> sortProductsByQuantityAscending(List<Product> productList)throws RemoteException;
    public List<Product> sortProductsByQuantityDescending(List<Product> productList)throws RemoteException;
    // Chức năng sắp xếp khuyến mãi theo ngày
//    public List<Promotion> sortPromotionsDateASC() throws RemoteException;
//    public List<Promotion> sortPromotionsDateDESC() throws RemoteException;
    
    public List<Promotion> sortPromotionDateDescending(List<Promotion> promotionList)throws RemoteException;
    public List<Promotion> sortPromotionDateAscending(List<Promotion> promotionList)throws RemoteException;
    // Chức năng sắp xếp hóa đơn theo đơn giá
    public List<Invoice> sortInvoicePriceASC() throws RemoteException;
    public List<Invoice> sortInvoicePriceDESC() throws RemoteException;
    //Kiểm tra khoảng thời gian khuyến mãi 
    boolean isPromotionOverlap(String productID, Date startDate, Date endDate) throws RemoteException;
}
