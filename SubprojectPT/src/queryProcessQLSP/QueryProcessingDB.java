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
    // Đối với chức năng thêm 
    public void addProduct(Product product) {
        String query = "INSERT INTO product (productid, productname, price, quantity, description, supplierid) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductID());
            statement.setString(2, product.getProductName());
            statement.setDouble(3, product.getPrice());
            statement.setInt(4, product.getQuantity());
            statement.setString(5, product.getDescription());
            statement.setString(6, product.getSupplierID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addPromotion(Promotion promotion) {
        String query = "INSERT INTO promotion (productid, supplierid, promotionrate, startdate, enddate) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, promotion.getProductID());
            statement.setString(2, promotion.getSupplierID());
            statement.setDouble(3, promotion.getPromotionRate());
            statement.setDate(4, new java.sql.Date(promotion.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(promotion.getEndDate().getTime()));
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSupplier(SupplierSP supplier) {
        String query = "INSERT INTO supplier (supplierid, suppliername, address, phonenumber) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supplier.getSupplierID());
            statement.setString(2, supplier.getSupplierName());
            statement.setString(3, supplier.getAddress());
            statement.setString(4, supplier.getPhoneNumber());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addInvoice(Invoice invoice) {
        String query = "INSERT INTO invoice (invoiceid, productid, purchasedate, totalamount) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, invoice.getInvoiceID());
            statement.setString(2, invoice.getProductID());
            statement.setDate(3, new java.sql.Date(invoice.getPurchaseDate().getTime()));
            statement.setDouble(4, invoice.getTotalAmount());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Đối với chức năng cập nhật
    public void updateProduct(Product product) {
        String query = "UPDATE product SET productname = ?, price = ?, quantity = ?, description = ?, supplierid = ? WHERE productid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, product.getProductName());
            statement.setDouble(2, product.getPrice());
            statement.setInt(3, product.getQuantity());
            statement.setString(4, product.getDescription());
            statement.setString(5, product.getSupplierID());
            statement.setString(6, product.getProductID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void updatePromotion(Promotion promotion) {
        String query = "UPDATE promotion SET productid = ?, supplierid = ?, promotionrate = ?, startdate = ?, enddate = ? WHERE productid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, promotion.getProductID());
            statement.setString(2, promotion.getSupplierID());
            statement.setDouble(3, promotion.getPromotionRate());
            statement.setDate(4, new java.sql.Date(promotion.getStartDate().getTime()));
            statement.setDate(5, new java.sql.Date(promotion.getEndDate().getTime()));
            statement.setString(6, promotion.getProductID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateSupplier(SupplierSP supplier) {
        String query = "UPDATE supplier SET suppliername = ?, address = ?, phonenumber = ? WHERE supplierid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supplier.getSupplierName());
            statement.setString(2, supplier.getAddress());
            statement.setString(3, supplier.getPhoneNumber());
            statement.setString(4, supplier.getSupplierID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    //Đối với chức năng xóa sản phẩm
    public void deleteProduct(String productID) {
        String query = "DELETE FROM product WHERE productid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deletePromotion(String productID, String supplierID) {
        String query = "DELETE FROM promotion WHERE productid = ? AND supplierid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, productID);
            statement.setString(2, supplierID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void deleteSupplier(String supplierID) {
        String query = "DELETE FROM supplier WHERE supplierid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, supplierID);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Đối với chức năng tìm kiếm
    public List<Product> searchProducts(String keyword) {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM product WHERE productname LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String productID = resultSet.getString("productid");
                String productName = resultSet.getString("productname");
                double price = resultSet.getDouble("price");
                int quantity = resultSet.getInt("quantity");
                String description = resultSet.getString("description");
                String supplierID = resultSet.getString("supplierid");
                Product product = new Product(productID, productName, price, quantity, description, supplierID);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
    public List<Promotion> searchPromotions(String keyword) {
        List<Promotion> promotions = new ArrayList<>();
        String query = "SELECT * FROM promotion WHERE productid LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String productID = resultSet.getString("productid");
                String supplierID = resultSet.getString("supplierid");
                double promotionRate = resultSet.getDouble("promotionrate");
                Date startDate = resultSet.getDate("startdate");
                Date endDate = resultSet.getDate("enddate");
                Promotion promotion = new Promotion(productID, supplierID, promotionRate, startDate, endDate);
                promotions.add(promotion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return promotions;
    }


    public List<SupplierSP> searchSuppliers(String keyword) {
        List<SupplierSP> suppliers = new ArrayList<>();
        String query = "SELECT * FROM supplier WHERE suppliername LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, "%" + keyword + "%");
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
    public List<Invoice> searchInvoices(String keyword) {
        List<Invoice> searchResult = new ArrayList<>();
        String query = "SELECT * FROM invoice WHERE invoiceid = ? OR productid = ? OR purchasedate = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, keyword); // Bind parameter for invoiceid
            statement.setString(2, keyword); // Bind parameter for productid
            statement.setString(3, keyword); // Bind parameter for purchasedate
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int invoiceID = resultSet.getInt("invoiceid");
                String productID = resultSet.getString("productid");
                Date purchaseDate = resultSet.getDate("purchasedate");
                double totalAmount = resultSet.getDouble("totalamount");
                // Tạo một đối tượng Invoice từ kết quả của truy vấn và thêm vào danh sách kết quả
                searchResult.add(new Invoice(invoiceID, productID, purchaseDate, totalAmount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return searchResult;
    }


    //Đối với chức năng xem
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
                String productID = resultSet.getString("productid");
                String supplierID = resultSet.getString("supplierid");
                double promotionRate = resultSet.getDouble("promotionrate");
                Date startDate = resultSet.getDate("startdate");
                Date endDate = resultSet.getDate("enddate");
                promotions.add(new Promotion(productID, supplierID, promotionRate, startDate, endDate));
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
