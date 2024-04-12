//package clientSP;
//
//import javax.swing.*;
//import java.awt.*;
//
//import javax.swing.table.JTableHeader;
//
//class ProductPanel extends JPanel {
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public ProductPanel() {
//	    setLayout(new GridBagLayout());
//
//	    // Phần bên trái (60%)
//	    JPanel leftPanel = new JPanel();
//	    leftPanel.setLayout(new GridLayout(3, 1)); // Sử dụng GridLayout cho 3 panel con
//
//	    // Panel con 1 (màu blue)
//	    JPanel leftSubPanel1 = new JPanel();
//	    leftSubPanel1.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout
//
//	    // Label "Tìm kiếm"
//	    JLabel searchLabel = new JLabel("Tìm kiếm:");
//	    searchLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	    GridBagConstraints gbcSearchLabel = new GridBagConstraints();
//	    gbcSearchLabel.gridx = 0;
//	    gbcSearchLabel.gridy = 0;
//	    gbcSearchLabel.anchor = GridBagConstraints.WEST; // Căn trái
//	    gbcSearchLabel.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
//	    leftSubPanel1.add(searchLabel, gbcSearchLabel);
//
//	    // TextField nhập từ khóa tìm kiếm
//	    JTextField searchField = new JTextField(15);
//	    searchField.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	    GridBagConstraints gbcSearchField = new GridBagConstraints();
//	    gbcSearchField.gridx = 1;
//	    gbcSearchField.gridy = 0;
//	    gbcSearchField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
//	    gbcSearchField.weightx = 1.0; // Độ rộng linh hoạt
//	    gbcSearchField.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
//	    leftSubPanel1.add(searchField, gbcSearchField);
//
//	    // Button "Search"
//	    JButton searchButton = new JButton("Search");
//	    searchButton.setBackground(new Color(128, 255, 255));
//	    searchButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
//	    GridBagConstraints gbcSearchButton = new GridBagConstraints();
//	    gbcSearchButton.gridx = 2;
//	    gbcSearchButton.gridy = 0;
//	    gbcSearchButton.anchor = GridBagConstraints.WEST; // Căn trái
//	    gbcSearchButton.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
//	    leftSubPanel1.add(searchButton, gbcSearchButton);
//
//	    // ComboBox có 4 tùy chọn
//	    String[] options = {"Tăng theo giá", "Giảm theo giá", "Tăng theo số lượng", "Giảm theo số lượng"};
//	    JComboBox<String> comboBox = new JComboBox<>(options);
//	    comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    GridBagConstraints gbcComboBox = new GridBagConstraints();
//	    gbcComboBox.gridx = 0;
//	    gbcComboBox.gridy = 1;
//	    gbcComboBox.gridwidth = 3; // Chiếm 3 cột
//	    gbcComboBox.anchor = GridBagConstraints.EAST; // Căn trái
//	    gbcComboBox.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
//	    leftSubPanel1.add(comboBox, gbcComboBox);
//
//	    leftPanel.add(leftSubPanel1);
//
//	    // Panel con 2
//	    JPanel leftSubPanel2 = new JPanel();
//	    leftSubPanel2.setLayout(new BorderLayout()); // Sử dụng BorderLayout
//	    leftPanel.add(leftSubPanel2);
//
//	    // Dữ liệu mẫu cho bảng
//	    String[][] data = {
//	        {"sp00001", "Sản phẩm 1", "100.000", "48", "Mô tả sản phẩm 1", "s00001"},
//	        // Thêm dữ liệu khác tại đây...
//	    };
//
//	    // Tiêu đề cột
//	    String[] columnNames = {"Product ID", "Product Name", "Price", "Quantity", "Description", "Supplier ID"};
//
//	    // Tạo bảng hiển thị dữ liệu
//	    JTable table = new JTable(data, columnNames);
//	    table.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    table.setFillsViewportHeight(true); // Đảm bảo bảng lấp đầy kích thước của JScrollPane
//	    JScrollPane scrollPaneTable = new JScrollPane(table);
//	    scrollPaneTable.setViewportBorder(null);
//	    leftSubPanel2.add(scrollPaneTable, BorderLayout.CENTER); // Thêm scrollPane vào vị trí CENTER của leftSubPanel2
//
//	    // Customize header
//	    JTableHeader header = table.getTableHeader();
//	    header.setBackground(Color.BLUE); // Set header background color
//	    header.setForeground(Color.WHITE); // Set header text color
//
//	    // Get the current font
//	    Font currentFont = header.getFont();
//	    // Derive a new font with size 18 and bold style
//	    Font newFont = currentFont.deriveFont(Font.BOLD, 18f);
//	    // Set the new font for the header
//	    header.setFont(newFont);
//
//	 // Panel con 3
//	    JPanel leftSubPanel3 = new JPanel();
//	    leftPanel.add(leftSubPanel3);
//
//	    JButton btnNewButton = new JButton("Update");
//	    btnNewButton.setPreferredSize(new Dimension(120, 40)); // Thiết lập kích thước ưu tiên cho nút "Update"
//	    leftSubPanel3.add(btnNewButton);
//
//	    JButton btnNewButton_1 = new JButton("Delete");
//	    btnNewButton_1.setPreferredSize(new Dimension(120, 40)); // Thiết lập kích thước ưu tiên cho nút "Delete"
//	    leftSubPanel3.add(btnNewButton_1);
//
//	    GridBagConstraints gbcLeftPanel = new GridBagConstraints();
//	    gbcLeftPanel.gridx = 0;
//	    gbcLeftPanel.gridy = 0;
//	    gbcLeftPanel.weightx = 0.7; // Tỷ lệ 6:4
//	    gbcLeftPanel.weighty = 0.7; // Mở rộng theo chiều dọc
//	    gbcLeftPanel.fill = GridBagConstraints.BOTH;
//	    add(leftPanel, gbcLeftPanel);
//
//	    // Phần bên phải (40%)
//	    JPanel rightPanel = new JPanel();
//	    rightPanel.setLayout(new GridLayout(3, 1)); // Sử dụng GridLayout cho 3 panel con
//
//	    // Panel con 1
//	    JPanel rightSubPanel1 = new JPanel();
//	    rightSubPanel1.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout
//	    rightPanel.add(rightSubPanel1);
//
//	    // Label "Nhập thông tin sản phẩm"
//	    JLabel label = new JLabel("Nhập thông tin sản phẩm");
//	    label.setForeground(new Color(0, 128, 255));
//	    label.setFont(new Font("Tahoma", Font.BOLD, 20));
//	    GridBagConstraints gbcLabel = new GridBagConstraints();
//	    gbcLabel.gridx = 0;
//	    gbcLabel.gridy = 0;
//	    gbcLabel.anchor = GridBagConstraints.WEST; // Căn trái
//	    gbcLabel.insets = new Insets(10, 10, 10, 10); // Khoảng cách giữa các thành phần
//	    rightSubPanel1.add(label, gbcLabel);

//
//	    // Panel con 2
//	    JPanel rightSubPanel2 = new JPanel();
//	    rightSubPanel2.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout
//	    rightPanel.add(rightSubPanel2);
//
//	    // Label và TextField cho Product ID
//	    JLabel productIDLabel = new JLabel("Product ID:");
//	    productIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    JTextField productIDField = new JTextField(20); // Số ký tự mặc định
//	    productIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    productIDField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
//	    GridBagConstraints gbcProductIDLabel = new GridBagConstraints();
//	    gbcProductIDLabel.gridx = 0;
//	    gbcProductIDLabel.gridy = 0;
//	    gbcProductIDLabel.anchor = GridBagConstraints.WEST; // Căn trái
//	    gbcProductIDLabel.insets = new Insets(10, 10, 5, 5); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(productIDLabel, gbcProductIDLabel);
//	    GridBagConstraints gbcProductIDField = new GridBagConstraints();
//	    gbcProductIDField.gridx = 1;
//	    gbcProductIDField.gridy = 0;
//	    gbcProductIDField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
//	    gbcProductIDField.weightx = 1.0; // Độ rộng linh hoạt
//	    gbcProductIDField.insets = new Insets(10, 5, 5, 10); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(productIDField, gbcProductIDField);
//
//	    // Label và TextField cho Product Name
//	    JLabel productNameLabel = new JLabel("Product Name:");
//	    productNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    JTextField productNameField = new JTextField(20); // Số ký tự mặc định
//	    productNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    productNameField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
//	    GridBagConstraints gbcProductNameLabel = new GridBagConstraints();
//	    gbcProductNameLabel.gridx = 0;
//	    gbcProductNameLabel.gridy = 1;
//	    gbcProductNameLabel.anchor = GridBagConstraints.WEST; // Căn trái
//	    gbcProductNameLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(productNameLabel, gbcProductNameLabel);
//	    GridBagConstraints gbcProductNameField = new GridBagConstraints();
//	    gbcProductNameField.gridx = 1;
//	    gbcProductNameField.gridy = 1;
//	    gbcProductNameField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
//	    gbcProductNameField.weightx = 1.0; // Độ rộng linh hoạt
//	    gbcProductNameField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(productNameField, gbcProductNameField);
//
//	    // Label và TextField cho Price
//	    JLabel priceLabel = new JLabel("Price:");
//	    priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    JTextField priceField = new JTextField(20); // Số ký tự mặc định
//	    priceField.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    priceField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
//	    GridBagConstraints gbcPriceLabel = new GridBagConstraints();
//	    gbcPriceLabel.gridx = 0;
//	    gbcPriceLabel.gridy = 2;
//	    gbcPriceLabel.anchor = GridBagConstraints.WEST; // Căn trái
//	    gbcPriceLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(priceLabel, gbcPriceLabel);
//	    GridBagConstraints gbcPriceField = new GridBagConstraints();
//	    gbcPriceField.gridx = 1;
//	    gbcPriceField.gridy = 2;
//	    gbcPriceField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
//	    gbcPriceField.weightx = 1.0; // Độ rộng linh hoạt
//	    gbcPriceField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(priceField, gbcPriceField);
//
//	    // Label và TextField cho Quantity
//	    JLabel quantityLabel = new JLabel("Quantity:");
//	    quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    JTextField quantityField = new JTextField(20); // Số ký tự mặc định
//	    quantityField.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    quantityField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
//	    GridBagConstraints gbcQuantityLabel = new GridBagConstraints();
//	    gbcQuantityLabel.gridx = 0;
//	    gbcQuantityLabel.gridy = 3;
//	    gbcQuantityLabel.anchor = GridBagConstraints.WEST; // Căn trái
//	    gbcQuantityLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(quantityLabel, gbcQuantityLabel);
//	    GridBagConstraints gbcQuantityField = new GridBagConstraints();
//	    gbcQuantityField.gridx = 1;
//	    gbcQuantityField.gridy = 3;
//	    gbcQuantityField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
//	    gbcQuantityField.weightx = 1.0; // Độ rộng linh hoạt
//	    gbcQuantityField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(quantityField, gbcQuantityField);
//
//	    // Label và TextField cho Description
//	    JLabel descriptionLabel = new JLabel("Description:");
//	    descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    JTextArea descriptionArea = new JTextArea(5, 20); // Kích thước cụ thể (5 dòng, 20 cột)
//	    descriptionArea.setLineWrap(true); // Tự động xuống dòng khi đến cuối dòng
//	    descriptionArea.setWrapStyleWord(true); // Chỉ cắt từ khi cần thiết
//	    JScrollPane scrollPane = new JScrollPane(descriptionArea); // Thêm thanh cuộn
//	    GridBagConstraints gbcDescriptionLabel = new GridBagConstraints();
//	    gbcDescriptionLabel.gridx = 0;
//	    gbcDescriptionLabel.gridy = 4;
//	    gbcDescriptionLabel.anchor = GridBagConstraints.NORTHWEST; // Căn trái và phía trên
//	    gbcDescriptionLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(descriptionLabel, gbcDescriptionLabel);
//	    GridBagConstraints gbcScrollPane = new GridBagConstraints();
//	    gbcScrollPane.gridx = 1;
//	    gbcScrollPane.gridy = 4;
//	    gbcScrollPane.fill = GridBagConstraints.BOTH; // Giãn theo cả chiều ngang và chiều dọc
//	    gbcScrollPane.weightx = 1.0; // Độ rộng linh hoạt
//	    gbcScrollPane.weighty = 1.0; // Độ cao linh hoạt
//	    gbcScrollPane.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(scrollPane, gbcScrollPane);
//
//	    // Label và TextField cho Supplier ID
//	    JLabel supplierIDLabel = new JLabel("Supplier ID:");
//	    supplierIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    JTextField supplierIDField = new JTextField(20); // Số ký tự mặc định
//	    supplierIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
//	    supplierIDField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
//	    GridBagConstraints gbcSupplierIDLabel = new GridBagConstraints();
//	    gbcSupplierIDLabel.gridx = 0;
//	    gbcSupplierIDLabel.gridy = 5;
//	    gbcSupplierIDLabel.anchor = GridBagConstraints.WEST; // Căn trái
//	    gbcSupplierIDLabel.insets = new Insets(5, 10, 10, 5); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(supplierIDLabel, gbcSupplierIDLabel);
//	    GridBagConstraints gbcSupplierIDField = new GridBagConstraints();
//	    gbcSupplierIDField.gridx = 1;
//	    gbcSupplierIDField.gridy = 5;
//	    gbcSupplierIDField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
//	    gbcSupplierIDField.weightx = 1.0; // Độ rộng linh hoạt
//	    gbcSupplierIDField.insets = new Insets(5, 5, 10, 10); // Khoảng cách giữa các thành phần
//	    rightSubPanel2.add(supplierIDField, gbcSupplierIDField);

//	 // Panel con 3
//	    JPanel rightSubPanel3 = new JPanel();
//
//	    // Button "Add"
//	    JButton addButton = new JButton("Add");
//	    addButton.setPreferredSize(new Dimension(120, 40)); // Thiết lập kích thước ưu tiên cho nút "Add"
//	    rightSubPanel3.add(addButton);
//
//	    
//
//	    // Sử dụng FlowLayout và đặt alignment cho nút là CENTER
//	    FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
//	    rightSubPanel3.setLayout(flowLayout);
//
//	    // Thêm panel vào rightPanel
//	    rightPanel.add(rightSubPanel3);

//	    GridBagConstraints gbcRightPanel = new GridBagConstraints();
//	    gbcRightPanel.gridx = 1;
//	    gbcRightPanel.gridy = 0;
//	    gbcRightPanel.weightx = 0.3; // Tỷ lệ 6:4
//	    gbcRightPanel.weighty = 0.7; // Mở rộng theo chiều dọc
//	    gbcRightPanel.fill = GridBagConstraints.BOTH;
//	    add(rightPanel, gbcRightPanel);
//	    
//	}
//
//	@Override
//	public Dimension getPreferredSize() {
//	    return new Dimension(800, 600); // Kích thước mặc định
//	}
//}
//
package clientSP;
import javax.swing.*;
import javax.swing.table.JTableHeader;

import java.awt.*;

public class ProductPanel extends JPanel {
    
	public ProductPanel() {
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
        String[] options = {"Giá tăng dần", "Giá giảm dần", "Số lượng tăng dần", "Số lượng giảm dần"};
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
        JPanel leftSubPanel2 = new JPanel();
        leftSubPanel2.setLayout(new BorderLayout()); // Sử dụng BorderLayout

        // Dữ liệu mẫu cho bảng
        String[][] data = {
            {"sp00001", "Sản phẩm 1", "100.000", "48", "Mô tả sản phẩm 1", "s00001"},
            // Thêm dữ liệu khác tại đây...
        };

        // Tiêu đề cột
        String[] columnNames = {"Product ID", "Product Name", "Price", "Quantity", "Description", "Supplier ID"};

        // Tạo bảng hiển thị dữ liệu
        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setFillsViewportHeight(true); // Đảm bảo bảng lấp đầy kích thước của JScrollPane
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setViewportBorder(null);
        leftSubPanel2.add(scrollPaneTable, BorderLayout.CENTER); // Thêm scrollPane vào vị trí CENTER của leftSubPanel2

        // Customize header
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLUE); // Set header background color
        header.setForeground(Color.WHITE); // Set header text color

        // Get the current font
        Font currentFont = header.getFont();
        // Derive a new font with size 18 and bold style
        Font newFont = currentFont.deriveFont(Font.BOLD, 18f);
        // Set the new font for the header
        header.setFont(newFont);

        GridBagConstraints gbcLeftSubPanel2 = new GridBagConstraints();
        gbcLeftSubPanel2.gridx = 0;
        gbcLeftSubPanel2.gridy = 1;
//        gbcLeftSubPanel2.weightx = 1; // Tỷ lệ mở rộng theo chiều ngang
//        gbcLeftSubPanel2.weighty = 4.0; // Tỷ lệ mở rộng theo chiều dọc (lớn hơn)
        gbcLeftSubPanel2.fill = GridBagConstraints.BOTH; // Mở rộng cả hai chiều
        leftPanel.add(leftSubPanel2, gbcLeftSubPanel2);

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
        rightPanel.setBackground(Color.PINK); // Màu nền
        rightPanel.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout cho linh hoạt

        // Panel con 1
        JPanel rightSubPanel1 = new JPanel();
        rightSubPanel1.setLayout(new GridBagLayout()); // Sử dụng GridBagLayout

        // Label "Nhập thông tin sản phẩm"
        JLabel label = new JLabel("Nhập thông tin sản phẩm");
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

        // Label và TextField cho Product Name
        JLabel productNameLabel = new JLabel("Product Name:");
        productNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField productNameField = new JTextField(20); // Số ký tự mặc định
        productNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        productNameField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcProductNameLabel = new GridBagConstraints();
        gbcProductNameLabel.gridx = 0;
        gbcProductNameLabel.gridy = 1;
        gbcProductNameLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcProductNameLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(productNameLabel, gbcProductNameLabel);
        GridBagConstraints gbcProductNameField = new GridBagConstraints();
        gbcProductNameField.gridx = 1;
        gbcProductNameField.gridy = 1;
        gbcProductNameField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcProductNameField.weightx = 1.0; // Độ rộng linh hoạt
        gbcProductNameField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(productNameField, gbcProductNameField);

        // Label và TextField cho Price
        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField priceField = new JTextField(20); // Số ký tự mặc định
        priceField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        priceField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcPriceLabel = new GridBagConstraints();
        gbcPriceLabel.gridx = 0;
        gbcPriceLabel.gridy = 2;
        gbcPriceLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcPriceLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(priceLabel, gbcPriceLabel);
        GridBagConstraints gbcPriceField = new GridBagConstraints();
        gbcPriceField.gridx = 1;
        gbcPriceField.gridy = 2;
        gbcPriceField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcPriceField.weightx = 1.0; // Độ rộng linh hoạt
        gbcPriceField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(priceField, gbcPriceField);

        // Label và TextField cho Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField quantityField = new JTextField(20); // Số ký tự mặc định
        quantityField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        quantityField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcQuantityLabel = new GridBagConstraints();
        gbcQuantityLabel.gridx = 0;
        gbcQuantityLabel.gridy = 3;
        gbcQuantityLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcQuantityLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(quantityLabel, gbcQuantityLabel);
        GridBagConstraints gbcQuantityField = new GridBagConstraints();
        gbcQuantityField.gridx = 1;
        gbcQuantityField.gridy = 3;
        gbcQuantityField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcQuantityField.weightx = 1.0; // Độ rộng linh hoạt
        gbcQuantityField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(quantityField, gbcQuantityField);

        // Label và TextField cho Description
        JLabel descriptionLabel = new JLabel("Description:");
        descriptionLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextArea descriptionArea = new JTextArea(5, 20); // Kích thước cụ thể (5 dòng, 20 cột)
        descriptionArea.setLineWrap(true); // Tự động xuống dòng khi đến cuối dòng
        descriptionArea.setWrapStyleWord(true); // Chỉ cắt từ khi cần thiết
        JScrollPane scrollPane = new JScrollPane(descriptionArea); // Thêm thanh cuộn
        GridBagConstraints gbcDescriptionLabel = new GridBagConstraints();
        gbcDescriptionLabel.gridx = 0;
        gbcDescriptionLabel.gridy = 4;
        gbcDescriptionLabel.anchor = GridBagConstraints.NORTHWEST; // Căn trái và phía trên
        gbcDescriptionLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(descriptionLabel, gbcDescriptionLabel);
        GridBagConstraints gbcScrollPane = new GridBagConstraints();
        gbcScrollPane.gridx = 1;
        gbcScrollPane.gridy = 4;
        gbcScrollPane.fill = GridBagConstraints.BOTH; // Giãn theo cả chiều ngang và chiều dọc
        gbcScrollPane.weightx = 1.0; // Độ rộng linh hoạt
        gbcScrollPane.weighty = 1.0; // Độ cao linh hoạt
        gbcScrollPane.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(scrollPane, gbcScrollPane);

        // Label và TextField cho Supplier ID
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField supplierIDField = new JTextField(20); // Số ký tự mặc định
        supplierIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        supplierIDField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcSupplierIDLabel = new GridBagConstraints();
        gbcSupplierIDLabel.gridx = 0;
        gbcSupplierIDLabel.gridy = 5;
        gbcSupplierIDLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcSupplierIDLabel.insets = new Insets(5, 10, 10, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(supplierIDLabel, gbcSupplierIDLabel);
        GridBagConstraints gbcSupplierIDField = new GridBagConstraints();
        gbcSupplierIDField.gridx = 1;
        gbcSupplierIDField.gridy = 5;
        gbcSupplierIDField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcSupplierIDField.weightx = 1.0; // Độ rộng linh hoạt
        gbcSupplierIDField.insets = new Insets(5, 5, 10, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(supplierIDField, gbcSupplierIDField);

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