package serverSP;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import interfaceQLSP.interfaceProductManager;
import objectQLSP.Invoice;
import objectQLSP.Product;
import objectQLSP.Promotion;
import objectQLSP.SupplierSP;

public class ProductManagerImpl extends UnicastRemoteObject implements interfaceProductManager {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Connection connection;

    public ProductManagerImpl() throws RemoteException {
        super();
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/quanlysanpham", "root", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getProducts() throws RemoteException {
        List<Product> products = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM product")) {
            while (resultSet.next()) {
                String productID = resultSet.getString("productid");
                String productName = resultSet.getString("productname");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String description = resultSet.getString("description");
                String supplierID = resultSet.getString("supplierid");
                products.add(new Product(productID, productName, price, quantity, description, supplierID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public List<Invoice> getInvoices() throws RemoteException {
        List<Invoice> invoices = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM invoice")) {
            while (resultSet.next()) {
                int invoiceID = resultSet.getInt("invoiceid");
                String productID = resultSet.getString("productid");
                Date purchaseDate = resultSet.getDate("purchasedate");
                double totalAmount = resultSet.getDouble("totalamount");
                invoices.add(new Invoice(invoiceID, productID, purchaseDate, totalAmount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }

    @Override
    public List<Promotion> getPromotions() throws RemoteException {
        List<Promotion> promotions = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM promotion")) {
            while (resultSet.next()) {
                int promotionID = resultSet.getInt("promotionid");
                String productID = resultSet.getString("productid");
                String supplierID = resultSet.getString("supplierid");
                double promotionRate = resultSet.getDouble("promotionrate");
                Date startDate = resultSet.getDate("startdate");
                Date endDate = resultSet.getDate("enddate");
                promotions.add(new Promotion(promotionID, productID, supplierID, promotionRate, startDate, endDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotions;
    }
    @Override
    public List<SupplierSP> getSuppliers() throws RemoteException {
        List<SupplierSP> suppliers = new ArrayList<>();
        String query = "SELECT * FROM supplier";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String supplierID = resultSet.getString("supplierid");
                String supplierName = resultSet.getString("suppliername");
                String address = resultSet.getString("address");
                String phoneNumber = resultSet.getString("phonenumber");
                SupplierSP supplier = new SupplierSP(supplierID, supplierName, address, phoneNumber);
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

}
