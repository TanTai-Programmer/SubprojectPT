package queryProcessQLSP;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class invoiceCreator {
    private Connection connection;

    public invoiceCreator(Connection connection) {
        this.connection = connection;
    }

    public boolean createInvoice(List<String> productIds, List<Integer> quantities) throws SQLException {
        String invoiceId = generateInvoiceId(); // Tạo mã hóa đơn tự động

        try {
            // Bắt đầu một giao dịch
            connection.setAutoCommit(false);

            // Tạo hóa đơn
            PreparedStatement statement = connection.prepareStatement("INSERT INTO invoice (invoiceid, createdate) VALUES (?, ?)");
            statement.setString(1, invoiceId);
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.executeUpdate();
            statement.close();

            // Thêm chi tiết hóa đơn
            double totalAmount = 0;
            for (int i = 0; i < productIds.size(); i++) {
                String productId = productIds.get(i);
                int quantity = quantities.get(i);

                // Lấy thông tin sản phẩm
                double price = getProductPrice(productId);
                double discountedPrice = applyPromotion(productId, price);
                String productName = getProductName(productId);
                // Tính tổng tiền
                totalAmount += (discountedPrice * quantity);

                // Thêm chi tiết hóa đơn
                statement = connection.prepareStatement("INSERT INTO invoice_detail (invoiceid, productid, productname, quantitybuy, price, promotionrate, createdate) VALUES (?, ?, ?, ?, ?, ?, ?)");
                statement.setString(1, invoiceId);
                statement.setString(2, productId);
                statement.setString(3, productName); // Thêm tên sản phẩm vào câu lệnh SQL
                statement.setInt(4, quantity);
                statement.setDouble(5, discountedPrice);
                statement.setDouble(6, (discountedPrice < price) ? getPromotionRate(productId) : 0.0); // Lưu tỷ lệ khuyến mãi, nếu không có khuyến mãi thì gán 0%
                statement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
                statement.executeUpdate();
                statement.close();
            }

            // Cập nhật tổng tiền của hóa đơn
            statement = connection.prepareStatement("UPDATE invoice SET totalamount = ? WHERE invoiceid = ?");
            statement.setDouble(1, totalAmount);
            statement.setString(2, invoiceId);
            statement.executeUpdate();
            statement.close();

            // Commit giao dịch
            connection.commit();
            return true;
        } catch (SQLException e) {
            connection.rollback(); // Rollback nếu có lỗi xảy ra
            e.printStackTrace();
            return false;
        } finally {
            connection.setAutoCommit(true); // Đặt lại chế độ auto commit sau khi thực hiện xong giao dịch
        }
    }

    private String generateInvoiceId() {
        // Sử dụng ký tự chuyển đổi 't' cho các giá trị thời gian
        return String.format("hd%ty%tm%td%tH%tM%tS", System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis(), System.currentTimeMillis());
    }

    private double getProductPrice(String productId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT price FROM product WHERE productid = ?");
        statement.setString(1, productId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("price");
        }
        return 0;
    }
    private String getProductName(String productId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT productname FROM product WHERE productid = ?");
        statement.setString(1, productId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("productname");
        }
        return null;
    }
    private double applyPromotion(String productId, double price) throws SQLException {
        double discountedPrice = price;

        // Kiểm tra xem sản phẩm có trong danh sách khuyến mãi và đang trong thời gian áp dụng không
        PreparedStatement statement = connection.prepareStatement("SELECT promotionrate FROM promotion WHERE productid = ? AND startdate <= CURDATE() AND enddate >= CURDATE()");
        statement.setString(1, productId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            double promotionRate = resultSet.getDouble("promotionrate");
            discountedPrice = price - (price * promotionRate);
        }

        return discountedPrice;
    }

    private double getPromotionRate(String productId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT promotionrate FROM promotion WHERE productid = ? AND startdate <= CURDATE() AND enddate >= CURDATE()");
        statement.setString(1, productId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getDouble("promotionrate");
        }
        return 0.0; // Trả về 0% nếu không tìm thấy khuyến mãi
    }
}
