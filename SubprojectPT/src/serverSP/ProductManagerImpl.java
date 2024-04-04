package serverSP;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.util.List;

import databaseQLSP.DatabaseConnection;
import interfaceQLSP.interfaceProductManager;
import objectQLSP.Invoice;
import objectQLSP.Product;
import objectQLSP.Promotion;
import objectQLSP.SupplierSP;
import queryProcessQLSP.QueryProcessingDB;

public class ProductManagerImpl extends UnicastRemoteObject implements interfaceProductManager {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QueryProcessingDB queryProcessor;

    public ProductManagerImpl() throws RemoteException {
        super();
        // Tạo đối tượng DatabaseConnection và kết nối đến cơ sở dữ liệu
        DatabaseConnection dbConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/quanlysanpham", "root", "");
        Connection connection = dbConnection.getConnection();
        
        // Khởi tạo QueryProcessingDB với đối tượng Connection
        queryProcessor = new QueryProcessingDB(connection);
    }

    // Chức năng Thêm
    @Override
    public void addProduct(Product product) throws RemoteException {
        queryProcessor.addProduct(product);
    }

    @Override
    public void addPromotion(Promotion promotion) throws RemoteException {
        queryProcessor.addPromotion(promotion);
    }

    @Override
    public void addSupplier(SupplierSP supplier) throws RemoteException {
        queryProcessor.addSupplier(supplier);
    }

    // Chức năng Cập nhật
    @Override
    public void updateProduct(Product product) throws RemoteException {
        queryProcessor.updateProduct(product);
    }

    @Override
    public void updatePromotion(Promotion promotion) throws RemoteException {
        queryProcessor.updatePromotion(promotion);
    }

    @Override
    public void updateSupplier(SupplierSP supplier) throws RemoteException {
        queryProcessor.updateSupplier(supplier);
    }

    // Chức năng Xóa
    @Override
    public void deleteProduct(String productID) throws RemoteException {
        queryProcessor.deleteProduct(productID);
    }

    @Override
    public void deletePromotion(String productID, String supplierID) throws RemoteException {
        queryProcessor.deletePromotion(productID, supplierID);
    }
    @Override
    public void deleteSupplier(String supplierID) throws RemoteException {
        queryProcessor.deleteSupplier(supplierID);
    }

    // Chức năng Tìm kiếm
    @Override
    public List<Product> searchProducts(String keyword) throws RemoteException {
        return queryProcessor.searchProducts(keyword);
    }

    @Override
    public List<Invoice> searchInvoices(String keyword) throws RemoteException {
        return queryProcessor.searchInvoices(keyword);
    }

    @Override
    public List<Promotion> searchPromotions(String keyword) throws RemoteException {
        return queryProcessor.searchPromotions(keyword);
    }

    @Override
    public List<SupplierSP> searchSuppliers(String keyword) throws RemoteException {
        return queryProcessor.searchSuppliers(keyword);
    }

    // Chức năng Đọc
    @Override
    public List<Product> getProducts() throws RemoteException {
        return queryProcessor.getProducts();
    }

    @Override
    public List<Invoice> getInvoices() throws RemoteException {
        return queryProcessor.getInvoices();
    }

    @Override
    public List<Promotion> getPromotions() throws RemoteException {
        return queryProcessor.getPromotions();
    }

    @Override
    public List<SupplierSP> getSuppliers() throws RemoteException {
        return queryProcessor.getSuppliers();
    }
}
