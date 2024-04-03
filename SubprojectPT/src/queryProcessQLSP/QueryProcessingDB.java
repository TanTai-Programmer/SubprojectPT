package queryProcessQLSP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import objectQLSP.Invoice;
import objectQLSP.Product;
import objectQLSP.Promotion;
import objectQLSP.SupplierSP;

public class QueryProcessingDB {
	private Connection connection;

    public QueryProcessingDB(Connection connection) {
        this.connection = connection;
    }

    public List<Product> getProducts() {
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

    public List<Invoice> getInvoices() {
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

    public List<Promotion> getPromotions() {
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

    public List<SupplierSP> getSuppliers() {
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
