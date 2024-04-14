package serverSP;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import databaseQLSP.DatabaseConnection;
import interfaceQLSP.interfaceProductManager;
import objectQLSP.Invoice;
import objectQLSP.InvoiceDetail;
import objectQLSP.Product;
import objectQLSP.Promotion;
import objectQLSP.SupplierSP;
import queryProcessQLSP.invoiceCreator;
import queryProcessQLSP.QueryProcessingDB;

public class ProductManagerImpl extends UnicastRemoteObject implements interfaceProductManager {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private QueryProcessingDB queryProcessor;
	private invoiceCreator invoiceCreator;
    public ProductManagerImpl() throws RemoteException {
        super();
        // Tạo đối tượng DatabaseConnection và kết nối đến cơ sở dữ liệu
        DatabaseConnection dbConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/quanlysanpham", "root", "");
        Connection connection = dbConnection.getConnection();
        
        // Khởi tạo QueryProcessingDB với đối tượng Connection
        queryProcessor = new QueryProcessingDB(connection);
        invoiceCreator = new invoiceCreator(connection);
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
    public List<InvoiceDetail> getInvoiceDetails() throws RemoteException {
        return queryProcessor.getInvoiceDetails();
    }

    @Override
    public List<Promotion> getPromotions() throws RemoteException {
        return queryProcessor.getPromotions();
    }

    @Override
    public List<SupplierSP> getSuppliers() throws RemoteException {
        return queryProcessor.getSuppliers();
    }

	@Override
	public List<InvoiceDetail> searchInvoiceDetails(String invoiceID) throws RemoteException {
		return queryProcessor.searchInvoiceDetails(invoiceID);
	}

	   @Override
	    public boolean createInvoice(List<String> productIds, List<Integer> quantities) throws RemoteException {
	        try {
	            // Gọi phương thức tạo hóa đơn từ lớp InvoiceCreator
	            return invoiceCreator.createInvoice(productIds, quantities);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            throw new RemoteException("Error while creating invoice", e);
	        }
	    }

//	@Override
//	public List<Product> sortProductsQuantityASC() throws RemoteException {
//		return queryProcessor.sortProductQuantityASC();
//	}
//
//	@Override
//	public List<Product> sortProductsPriceASC() throws RemoteException {
//		return queryProcessor.sortProductPriceASC();
//	}
//
//	@Override
//	public List<Product> sortProductsQuantityDESC() throws RemoteException {
//		return queryProcessor.sortProductQuantityDESC();
//	}
//
//	@Override
//	public List<Product> sortProductsPriceDESC() throws RemoteException {
//		return queryProcessor.sortProductPriceDESC();
//	}

	@Override
	public List<Invoice> searchInvoice(String invoiceID) throws RemoteException {
		return queryProcessor.searchInvoice(invoiceID);
	}

//	@Override
//	public List<Promotion> sortPromotionsDateASC() throws RemoteException {
//		return queryProcessor.sortPromotionsDateASC();
//	}
//
//	@Override
//	public List<Promotion> sortPromotionsDateDESC() throws RemoteException {
//		return queryProcessor.sortPromotionsDateDESC();
//	}

	@Override
	public List<Invoice> sortInvoicePriceASC() throws RemoteException {
		return queryProcessor.sortInvoicePriceASC();
	}

	@Override
	public List<Invoice> sortInvoicePriceDESC() throws RemoteException {
		return queryProcessor.sortInvoicePriceDESC();
	}

	@Override
	public List<Product> sortProductsByPriceAscending(List<Product> productList) throws RemoteException {
		return queryProcessor.sortProductsByPriceAscending(productList);
	}
	@Override
	public List<Product> sortProductsByPriceDescending(List<Product> productList) throws RemoteException {
		return queryProcessor.sortProductsByPriceAscending(productList);
	}
	public List<Product> sortProductsByQuantityAscending(List<Product> productList) throws RemoteException{
		return queryProcessor.sortProductsByQuantityAscending(productList);
	}
	public List<Product> sortProductsByQuantityDescending(List<Product> productList) throws RemoteException{
		return queryProcessor.sortProductsByQuantityDescending(productList);
	}

	@Override
	public List<Promotion> sortPromotionDateDescending(List<Promotion> productList) throws RemoteException {
		return queryProcessor.sortPromotionDateDescending(productList);
	}

	@Override
	public List<Promotion> sortPromotionDateAscending(List<Promotion> productList) throws RemoteException {
		return queryProcessor.sortPromotionDateAscending(productList);
	}
	
	@Override
	public String getSupplierID(String productID) throws RemoteException{
		 return queryProcessor.getSupplierID(productID);
	 }

	@Override
	public boolean isPromotionOverlap(String productID, Date startDate, Date endDate) throws RemoteException {
		return queryProcessor.isPromotionOverlap(productID, startDate, endDate);
	}
	
}
