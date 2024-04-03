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
