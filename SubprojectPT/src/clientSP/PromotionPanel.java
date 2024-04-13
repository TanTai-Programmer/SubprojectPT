package clientSP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import interfaceQLSP.interfaceProductManager;
import objectQLSP.Promotion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.sql.Date;

import com.toedter.calendar.JDateChooser;
public class PromotionPanel extends JPanel {
   
	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private JTable table;
	    private JPanel leftSubPanel2;
	    private interfaceProductManager productManager;
	    private List<Promotion> promotionResult;
	    private  List<Promotion> editedPromotion = new ArrayList<>();

	    public PromotionPanel(interfaceProductManager productManager){
	    	this.productManager = productManager;
        setLayout(new GridBagLayout());

        // Phần bên trái (70%)
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout cho linh hoạt

     // Panel con 1 (màu blue)
        JPanel leftSubPanel1 = new JPanel();
        leftSubPanel1.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout

        // Label "Tìm kiếm"
        JLabel searchLabel = new JLabel("Tìm kiếm:");
        searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbcSearchLabel = new GridBagConstraints();
        gbcSearchLabel.gridx = 0;
        gbcSearchLabel.gridy = 0;
        gbcSearchLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcSearchLabel.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        leftSubPanel1.add(searchLabel, gbcSearchLabel);

        // TextField nhập từ khóa tìm kiếm
        JTextField searchField = new JTextField(15);
        searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbcSearchField = new GridBagConstraints();
        gbcSearchField.gridx = 1;
        gbcSearchField.gridy = 0;
        gbcSearchField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcSearchField.weightx = 1.0; // Độ rộng linh hoạt
        gbcSearchField.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        leftSubPanel1.add(searchField, gbcSearchField);

        // Button "Search"
        JButton searchButton = new JButton("Search");
        searchButton.setBackground(new Color(128, 255, 255));
        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        GridBagConstraints gbcSearchButton = new GridBagConstraints();
        gbcSearchButton.gridx = 2;
        gbcSearchButton.gridy = 0;
        gbcSearchButton.anchor = GridBagConstraints.WEST; // Căn trái
        gbcSearchButton.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        leftSubPanel1.add(searchButton, gbcSearchButton);
        
        // ComboBox có 4 tùy chọn
        String[] options = {"Ngày tạo gần nhất", "Ngày tạo xa nhất"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));

        GridBagConstraints gbcComboBox = new GridBagConstraints();
        gbcComboBox.gridx = 0;
        gbcComboBox.gridy = 1;
        gbcComboBox.gridwidth = 3; // Chiếm 3 cột
        gbcComboBox.anchor = GridBagConstraints.EAST; // Căn trái
        gbcComboBox.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        leftSubPanel1.add(comboBox, gbcComboBox);

        leftPanel.add(leftSubPanel1);

        GridBagConstraints gbcLeftSubPanel1 = new GridBagConstraints();
        gbcLeftSubPanel1.gridx = 0;
        gbcLeftSubPanel1.gridy = 0;
        gbcLeftSubPanel1.weightx = 0.7; // Tỷ lệ mở rộng theo chiều ngang
        gbcLeftSubPanel1.weighty = 1.0; // Tỷ lệ mở rộng theo chiều dọc
        gbcLeftSubPanel1.fill = GridBagConstraints.HORIZONTAL; // Mở rộng cả hai chiều
        leftPanel.add(leftSubPanel1, gbcLeftSubPanel1);

        // Panel con 2
        leftSubPanel2 = new JPanel();
        leftSubPanel2.setLayout(new BorderLayout()); // Sử dụng BorderLayout

        
        GridBagConstraints gbcLeftSubPanel2 = new GridBagConstraints();
        gbcLeftSubPanel2.gridx = 0;
        gbcLeftSubPanel2.gridy = 1;
        gbcLeftSubPanel2.fill = GridBagConstraints.BOTH; // Mở rộng cả hai chiều
        leftPanel.add(leftSubPanel2, gbcLeftSubPanel2);

        add(leftPanel); // Thêm leftPanel vào ProductPanel
        // Panel con 3
        JPanel leftSubPanel3 = new JPanel();

        // Nút "Update"
        JButton btnNewButton = new JButton("Update");
        btnNewButton.setPreferredSize(new Dimension(120, 40)); // Thiết lập kích thước ưu tiên cho nút "Update"
        leftSubPanel3.add(btnNewButton);
        // Nút "Delete"
        JButton btnNewButton_1 = new JButton("Delete");
        btnNewButton_1.setPreferredSize(new Dimension(120, 40)); // Thiết lập kích thước ưu tiên cho nút "Delete"
        leftSubPanel3.add(btnNewButton_1);
        // Xử lý sự kiện khi nhấn nút "Delete"
        btnNewButton_1.addActionListener(e -> {
            // Lấy chỉ số hàng của dòng được chọn trong bảng
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                // Kiểm tra xem người dùng đã chọn một dòng trong bảng chưa
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một sản phẩm để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy ID của sản phẩm từ dòng được chọn
            String productID = table.getValueAt(selectedRow, 0).toString();

            // Gọi phương thức deleteProduct từ xa với ID của sản phẩm
            try {
                productManager.deleteProduct(productID);
                // Xóa sản phẩm từ bảng dữ liệu
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(selectedRow); // Xóa dòng tương ứng từ bảng

                // Hiển thị thông báo thành công (nếu cần)
                JOptionPane.showMessageDialog(null, "Đã xóa sản phẩm thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                // Xử lý ngoại lệ khi gặp lỗi khi gọi phương thức từ xa
                JOptionPane.showMessageDialog(null, "Lỗi khi xóa sản phẩm.", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });


        GridBagConstraints gbcLeftSubPanel3 = new GridBagConstraints();
        gbcLeftSubPanel3.gridx = 0;
        gbcLeftSubPanel3.gridy = 2;
        gbcLeftSubPanel3.weightx = 1; // Tỷ lệ mở rộng theo chiều ngang
        gbcLeftSubPanel3.weighty = 1.0; // Tỷ lệ mở rộng theo chiều dọc
        gbcLeftSubPanel3.fill = GridBagConstraints.BOTH; // Mở rộng cả hai chiều
        leftPanel.add(leftSubPanel3, gbcLeftSubPanel3);

        GridBagConstraints gbcLeftPanel = new GridBagConstraints();
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 0;
        gbcLeftPanel.weightx = 0.8; // Tỷ lệ 7:3
        gbcLeftPanel.weighty = 1; // Mở rộng theo chiều dọc
        gbcLeftPanel.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbcLeftPanel);

        // Phần bên phải (30%)
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout cho linh hoạt

        // Panel con 1
        JPanel rightSubPanel1 = new JPanel();
        rightSubPanel1.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout

        // Label "Nhập thông tin khuyến mãi"
        JLabel label = new JLabel("Nhập thông tin khuyến mãi");
        label.setForeground(new Color(0, 128, 255));
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        GridBagConstraints gbcLabel = new GridBagConstraints();
        gbcLabel.gridx = 0;
        gbcLabel.gridy = 0;
        gbcLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcLabel.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
        rightSubPanel1.add(label, gbcLabel);

        GridBagConstraints gbcRightSubPanel1 = new GridBagConstraints();
        gbcRightSubPanel1.gridx = 0;
        gbcRightSubPanel1.gridy = 0;
        gbcRightSubPanel1.weightx = 1; // Tỷ lệ mở rộng theo chiều ngang
        gbcRightSubPanel1.weighty = 1.0; // Tỷ lệ mở rộng theo chiều dọc
        gbcRightSubPanel1.fill = GridBagConstraints.BOTH; // Mở rộng cả hai chiều
        rightPanel.add(rightSubPanel1, gbcRightSubPanel1);

     // Panel con 2
        JPanel rightSubPanel2 = new JPanel();
        rightSubPanel2.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout

        // Label và TextField cho Product ID
        JLabel productIDLabel = new JLabel("Product ID:");
        productIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField productIDField = new JTextField(20); // Số ký tự mặc định
        productIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        productIDField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcProductIDLabel = new GridBagConstraints();
        gbcProductIDLabel.gridx = 0;
        gbcProductIDLabel.gridy = 0;
        gbcProductIDLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcProductIDLabel.insets = new Insets(10, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(productIDLabel, gbcProductIDLabel);
        GridBagConstraints gbcProductIDField = new GridBagConstraints();
        gbcProductIDField.gridx = 1;
        gbcProductIDField.gridy = 0;
        gbcProductIDField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcProductIDField.weightx = 1.0; // Độ rộng linh hoạt
        gbcProductIDField.insets = new Insets(10, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(productIDField, gbcProductIDField);

        // Label và TextField cho Mã nhà cung cấp
        JLabel supplierIDLabel = new JLabel("Mã nhà cung cấp:");
        supplierIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField supplierIDField = new JTextField(20); // Số ký tự mặc định
        supplierIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        supplierIDField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcSupplierIDLabel = new GridBagConstraints();
        gbcSupplierIDLabel.gridx = 0;
        gbcSupplierIDLabel.gridy = 1;
        gbcSupplierIDLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcSupplierIDLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(supplierIDLabel, gbcSupplierIDLabel);
        GridBagConstraints gbcSupplierIDField = new GridBagConstraints();
        gbcSupplierIDField.gridx = 1;
        gbcSupplierIDField.gridy = 1;
        gbcSupplierIDField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcSupplierIDField.weightx = 1.0; // Độ rộng linh hoạt
        gbcSupplierIDField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(supplierIDField, gbcSupplierIDField);

        // Label và TextField cho Phần trăm khuyến mãi
        JLabel promotionRateLabel = new JLabel("Phần trăm khuyến mãi:");
        promotionRateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField promotionRateField = new JTextField(20); // Số ký tự mặc định
        promotionRateField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        promotionRateField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcPromotionRateLabel = new GridBagConstraints();
        gbcPromotionRateLabel.gridx = 0;
        gbcPromotionRateLabel.gridy = 2;
        gbcPromotionRateLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcPromotionRateLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(promotionRateLabel, gbcPromotionRateLabel);
        GridBagConstraints gbcPromotionRateField = new GridBagConstraints();
        gbcPromotionRateField.gridx = 1;
        gbcPromotionRateField.gridy = 2;
        gbcPromotionRateField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcPromotionRateField.weightx = 1.0; // Độ rộng linh hoạt
        gbcPromotionRateField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(promotionRateField, gbcPromotionRateField);

        // Label cho Ngày bắt đầu
        JLabel startDateLabel = new JLabel("Ngày bắt đầu:");
        startDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbcStartDateLabel = new GridBagConstraints();
        gbcStartDateLabel.gridx = 0;
        gbcStartDateLabel.gridy = 3;
        gbcStartDateLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcStartDateLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(startDateLabel, gbcStartDateLabel);

        // JDateChooser cho Ngày bắt đầu
        JDateChooser startDateChooser = new JDateChooser();
        startDateChooser.setPreferredSize(new Dimension(200, 25));
        GridBagConstraints gbcStartDateChooser = new GridBagConstraints();
        gbcStartDateChooser.gridx = 1;
        gbcStartDateChooser.gridy = 3;
        gbcStartDateChooser.fill = GridBagConstraints.HORIZONTAL;
        gbcStartDateChooser.weightx = 1.0;
        gbcStartDateChooser.insets = new Insets(5, 5, 5, 10);
        rightSubPanel2.add(startDateChooser, gbcStartDateChooser);

        // Label cho Ngày kết thúc
        JLabel endDateLabel = new JLabel("Ngày kết thúc:");
        endDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        GridBagConstraints gbcEndDateLabel = new GridBagConstraints();
        gbcEndDateLabel.gridx = 0;
        gbcEndDateLabel.gridy = 4;
        gbcEndDateLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcEndDateLabel.insets = new Insets(5, 10, 10, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(endDateLabel, gbcEndDateLabel);

        // JDateChooser cho Ngày kết thúc
        JDateChooser endDateChooser = new JDateChooser();
        endDateChooser.setPreferredSize(new Dimension(200, 25));
        GridBagConstraints gbcEndDateChooser = new GridBagConstraints();
        gbcEndDateChooser.gridx = 1;
        gbcEndDateChooser.gridy = 4;
        gbcEndDateChooser.fill = GridBagConstraints.HORIZONTAL;
        gbcEndDateChooser.weightx = 1.0;
        gbcEndDateChooser.insets = new Insets(5, 5, 10, 10);
        rightSubPanel2.add(endDateChooser, gbcEndDateChooser);

        GridBagConstraints gbcRightSubPanel2 = new GridBagConstraints();
        gbcRightSubPanel2.gridx = 0;
        gbcRightSubPanel2.gridy = 1;
        gbcRightSubPanel2.weightx = 1; // Tỷ lệ mở rộng theo chiều ngang
        gbcRightSubPanel2.weighty = 2.2; // Tỷ lệ mở rộng theo chiều dọc (lớn hơn)
        gbcRightSubPanel2.fill = GridBagConstraints.BOTH; // Mở rộng cả hai chiều
        rightPanel.add(rightSubPanel2, gbcRightSubPanel2);

        // Panel con 3
        JPanel rightSubPanel3 = new JPanel();

        // Button "Add"
        JButton addButton = new JButton("Add");
        addButton.setPreferredSize(new Dimension(120, 40)); // Thiết lập kích thước ưu tiên cho nút "Add"
        rightSubPanel3.add(addButton);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                String productID = productIDField.getText();
                String supplierID = supplierIDField.getText();
                double promotionRate = Double.parseDouble(promotionRateField.getText());
             // Lấy ngày tháng được chọn từ JDateChooser
                java.util.Date startDateUtil = startDateChooser.getDate();
                java.util.Date endDateUtil = endDateChooser.getDate();

                // Chuyển đổi sang kiểu java.sql.Date
                java.sql.Date startDate = new java.sql.Date(startDateUtil.getTime());
                java.sql.Date endDate = new java.sql.Date(endDateUtil.getTime());
                // Tạo đối tượng Promotion từ dữ liệu nhập liệu
                Promotion promotion = new Promotion(productID, supplierID, promotionRate, startDate, endDate);

                // Gọi phương thức addPromotion với đối tượng Promotion được tạo
                try {
                    productManager.addPromotion(promotion);
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
            }
        });

        // Sử dụng FlowLayout và đặt alignment cho nút là CENTER
        FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
        rightSubPanel3.setLayout(flowLayout);

        GridBagConstraints gbcRightSubPanel3 = new GridBagConstraints();
        gbcRightSubPanel3.weighty = 1.0;
        gbcRightSubPanel3.gridx = 0;
        gbcRightSubPanel3.gridy = 2;
        gbcRightSubPanel3.weightx = 1;
        gbcRightSubPanel3.fill = GridBagConstraints.BOTH; // Mở rộng cả hai chiều
        rightPanel.add(rightSubPanel3, gbcRightSubPanel3);

        GridBagConstraints gbcRightPanel = new GridBagConstraints();
        gbcRightPanel.gridx = 1;
        gbcRightPanel.gridy = 0;
        gbcRightPanel.weightx = 0.2; // Tỷ lệ 7:3
        gbcRightPanel.weighty = 1; // Mở rộng theo chiều dọc
        gbcRightPanel.fill = GridBagConstraints.BOTH;
        add(rightPanel, gbcRightPanel);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // Kích thước mặc định
    }
}