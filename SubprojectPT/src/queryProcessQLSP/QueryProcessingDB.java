package queryProcessQLSP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import objectQLSP.Invoice;
import objectQLSP.InvoiceDetail;
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
            statement.setTimestamp(4, new java.sql.Timestamp(promotion.getStartDate().getTime()));
            statement.setTimestamp(5, new java.sql.Timestamp(promotion.getEndDate().getTime()));

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
    
    public List<InvoiceDetail> getInvoiceDetails() {
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        String query = "SELECT * FROM invoice_detail ORDER BY invoiceid";
        try (PreparedStatement statement = this.connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String invoiceID = resultSet.getString("invoiceid");
                String productID = resultSet.getString("productid");
                String productName = resultSet.getString("productname");
                double promotionRate = resultSet.getDouble("promotionrate");
                double price = resultSet.getDouble("price");
                Date createDate = resultSet.getDate("createdate");
                int quantity = resultSet.getInt("quantitybuy"); // Lấy số lượng sản phẩm
                InvoiceDetail invoiceDetail = new InvoiceDetail(invoiceID, productID, productName, promotionRate, price, createDate, quantity);
                invoiceDetails.add(invoiceDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceDetails;
    }

    public List<InvoiceDetail> searchInvoiceDetails(String invoiceID) {
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        String query = "SELECT * FROM invoice_detail WHERE invoiceid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, invoiceID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String productID = resultSet.getString("productid");
                String productName = resultSet.getString("productname");
                double promotionRate = resultSet.getDouble("promotionrate");
                double price = resultSet.getDouble("price");
                Date createDate = resultSet.getDate("createdate");
                int quantity = resultSet.getInt("quantitybuy"); // Lấy số lượng sản phẩm
                InvoiceDetail invoiceDetail = new InvoiceDetail(invoiceID, productID, productName, promotionRate, price, createDate, quantity);
                invoiceDetails.add(invoiceDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoiceDetails;
    }
    
    // Tìm kiếm hóa đơn theo mã hóa đơn invoiceid
    public List<Invoice> searchInvoice(String invoiceID) {
        List<Invoice> invoices = new ArrayList<>();
        String query = "SELECT * FROM invoice WHERE invoiceid = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, invoiceID);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String invoiceId = resultSet.getString("invoiceid");
                Date createDate = resultSet.getDate("createdate");
                double totalAmount = resultSet.getDouble("totalamount");
                Invoice invoice = new Invoice(invoiceID, createDate, totalAmount);
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    //Chức năng sắp xếp bảng sản phẩm
 // Đối với chức năng sắp xếp danh sách sản phẩm theo giá từ thấp đến cao
    public List<Product> sortProductsByPriceAscending(List<Product> productList) {
        // Sắp xếp danh sách sản phẩm theo giá từ thấp đến cao
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p1.getPrice(), p2.getPrice());
            }
        });
        return productList;
    }
    public List<Product> sortProductsByPriceDescending(List<Product> productList) {
        // Sắp xếp danh sách sản phẩm theo giá từ cao đến thấp
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Double.compare(p2.getPrice(), p1.getPrice()); // Đảo ngược thứ tự so sánh
            }
        });
        return productList;
    }
    public List<Product> sortProductsByQuantityAscending(List<Product> productList) {
        // Sắp xếp danh sách sản phẩm theo số lượng từ thấp đến cao
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p1.getQuantity(), p2.getQuantity());
            }
        });
        return productList;
    }

    public List<Product> sortProductsByQuantityDescending(List<Product> productList) {
        // Sắp xếp danh sách sản phẩm theo số lượng từ cao đến thấp
        Collections.sort(productList, new Comparator<Product>() {
            @Override
            public int compare(Product p1, Product p2) {
                return Integer.compare(p2.getQuantity(), p1.getQuantity()); // Đảo ngược thứ tự so sánh
            }
        });
        return productList;
    }

//    public List<Product> sortProductQuantityASC() {
//        List<Product> products = new ArrayList<>();
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM product ORDER BY quantity ASC;")) {
//            while (resultSet.next()) {
//                String productID = resultSet.getString("productid");
//                String productName = resultSet.getString("productname");
//                double price = resultSet.getDouble("price");
//                int quantity = resultSet.getInt("quantity");
//                String description = resultSet.getString("description");
//                String supplierID = resultSet.getString("supplierid");
//                products.add(new Product(productID, productName, price, quantity, description, supplierID));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
//    }
//    public List<Product> sortProductPriceASC() {
//        List<Product> products = new ArrayList<>();
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM product ORDER BY price ASC;")) {
//            while (resultSet.next()) {
//                String productID = resultSet.getString("productid");
//                String productName = resultSet.getString("productname");
//                double price = resultSet.getDouble("price");
//                int quantity = resultSet.getInt("quantity");
//                String description = resultSet.getString("description");
//                String supplierID = resultSet.getString("supplierid");
//                products.add(new Product(productID, productName, price, quantity, description, supplierID));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
//    }
//    public List<Product> sortProductQuantityDESC() {
//        List<Product> products = new ArrayList<>();
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM product ORDER BY quantity DESc;")) {
//            while (resultSet.next()) {
//                String productID = resultSet.getString("productid");
//                String productName = resultSet.getString("productname");
//                double price = resultSet.getDouble("price");
//                int quantity = resultSet.getInt("quantity");
//                String description = resultSet.getString("description");
//                String supplierID = resultSet.getString("supplierid");
//                products.add(new Product(productID, productName, price, quantity, description, supplierID));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
//    }
//    public List<Product> sortProductPriceDESC() {
//        List<Product> products = new ArrayList<>();
//        try (Statement statement = connection.createStatement();
//             ResultSet resultSet = statement.executeQuery("SELECT * FROM product ORDER BY price DESC;")) {
//            while (resultSet.next()) {
//                String productID = resultSet.getString("productid");
//                String productName = resultSet.getString("productname");
//                double price = resultSet.getDouble("price");
//                int quantity = resultSet.getInt("quantity");
//                String description = resultSet.getString("description");
//                String supplierID = resultSet.getString("supplierid");
//                products.add(new Product(productID, productName, price, quantity, description, supplierID));
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return products;
//    }
    
    // Phương thức sắp xếp danh sách Promotion theo số lượng giảm dần
    public List<Promotion> sortPromotionDateAscending(List<Promotion> promotionList){
        // Sắp xếp danh sách Promotion theo ngày tăng dần
        Collections.sort(promotionList, new Comparator<Promotion>() {
            @Override
            public int compare(Promotion p1, Promotion p2) {
                // So sánh ngày bắt đầu
                return p1.getStartDate().compareTo(p2.getStartDate());
            }
        });
        return promotionList;
    }
    public List<Promotion> sortPromotionDateDescending(List<Promotion> promotionList) {
        // Sắp xếp danh sách Promotion theo ngày giảm dần
        Collections.sort(promotionList, new Comparator<Promotion>() {
            @Override
            public int compare(Promotion p1, Promotion p2) {
                // So sánh ngày bắt đầu
                return p2.getStartDate().compareTo(p1.getStartDate());
            }
        });
        return promotionList;
    }
//    public List<Promotion> sortPromotionsDateASC() {
//        List<Promotion> promotions = new ArrayList<>();
//        String query = "SELECT * FROM `promotion` ORDER BY `promotion`.`startdate` ASC";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                String productID = resultSet.getString("productid");
//                String supplierID = resultSet.getString("supplierid");
//                double promotionRate = resultSet.getDouble("promotionrate");
//                Date startDate = resultSet.getDate("startdate");
//                Date endDate = resultSet.getDate("enddate");
//                Promotion promotion = new Promotion(productID, supplierID, promotionRate, startDate, endDate);
//                promotions.add(promotion);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return promotions;
//    }
//    public List<Promotion> sortPromotionsDateDESC() {
//        List<Promotion> promotions = new ArrayList<>();
//        String query = "SELECT * FROM `promotion` ORDER BY `promotion`.`startdate` DESC";
//        try (PreparedStatement statement = connection.prepareStatement(query)) {
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                String productID = resultSet.getString("productid");
//                String supplierID = resultSet.getString("supplierid");
//                double promotionRate = resultSet.getDouble("promotionrate");
//                Date startDate = resultSet.getDate("startdate");
//                Date endDate = resultSet.getDate("enddate");
//                Promotion promotion = new Promotion(productID, supplierID, promotionRate, startDate, endDate);
//                promotions.add(promotion);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return promotions;
//    }
    public List<Invoice> sortInvoicePriceASC() {
        List<Invoice> invoices = new ArrayList<>();
        String query = "SELECT * FROM `invoice` ORDER BY `invoice`.`totalamount` ASC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String invoiceId = resultSet.getString("invoiceid");
                Date createDate = resultSet.getDate("createdate");
                double totalAmount = resultSet.getDouble("totalamount");
                Invoice invoice = new Invoice(invoiceId, createDate, totalAmount);
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
    public List<Invoice> sortInvoicePriceDESC() {
        List<Invoice> invoices = new ArrayList<>();
        String query = "SELECT * FROM `invoice` ORDER BY `invoice`.`totalamount` DESC";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String invoiceId = resultSet.getString("invoiceid");
                Date createDate = resultSet.getDate("createdate");
                double totalAmount = resultSet.getDouble("totalamount");
                Invoice invoice = new Invoice(invoiceId, createDate, totalAmount);
                invoices.add(invoice);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return invoices;
    }
}
