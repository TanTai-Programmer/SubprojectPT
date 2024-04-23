package clientSP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.List;
import interfaceQLSP.InterfaceProductManager;
import objectQLSP.Product;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class ProductPanel extends JPanel {
   
	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private JTable table;
	    private JPanel leftSubPanel2;
	    private InterfaceProductManager productManager;
	    private List<Product> productResult;
	    private  List<Product> editedProducts = new ArrayList<>();
	    private boolean dataModified = false;

	    public ProductPanel(InterfaceProductManager productManager){
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
        searchButton.addActionListener(e -> {
        	 // Lấy từ khóa tìm kiếm từ TextField
            String keyword = searchField.getText(); 
            
			try {
				handleOtherAction();
				productResult = productManager.searchProducts(keyword);
		           // Cập nhật bảng hiển thị dữ liệu với kết quả tìm kiếm
	            updateTable(productResult, leftSubPanel2);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
        });
        // ComboBox có 4 tùy chọn
        String[] options = {"Giá tăng dần", "Giá giảm dần", "Số lượng tăng dần", "Số lượng giảm dần"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));

        comboBox.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            try {
				if (selectedOption.equals("Giá tăng dần")) {
					handleOtherAction();
                    List<Product> sortedProducts = productManager.sortProductsByPriceAscending(productResult);
                    // Gọi phương thức để sử dụng sortedProducts
                    updateTable(sortedProducts,leftSubPanel2);
                } else if (selectedOption.equals("Giá giảm dần")) {
                	handleOtherAction();
                    List<Product> sortedProducts = productManager.sortProductsByPriceDescending(productResult);
                    // Gọi phương thức để sử dụng sortedProducts
                    updateTable(sortedProducts,leftSubPanel2);
                } else if (selectedOption.equals("Số lượng giảm dần")) {
                	handleOtherAction();
                    List<Product> sortedProducts = productManager.sortProductsByQuantityDescending(productResult);
                    // Gọi phương thức để sử dụng sortedProducts
                    updateTable(sortedProducts,leftSubPanel2);
                }else if (selectedOption.equals("Số lượng tăng dần")) {
                	handleOtherAction();
                    List<Product> sortedProducts = productManager.sortProductsByQuantityAscending(productResult);
                    // Gọi phương thức để sử dụng sortedProducts
                    updateTable(sortedProducts,leftSubPanel2);
                }
                // Xử lý các lựa chọn khác nếu cần
            } catch (RemoteException ex) {
                ex.printStackTrace();
                // Xử lý ngoại lệ nếu cần
            }
        });
        
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
        try {
			productResult = productManager.getProducts();
			updateTable(productResult, leftSubPanel2);
		} catch (RemoteException e1) {
			e1.printStackTrace();
		}
        
        
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
     // Xử lý sự kiện cho nút "Update"
        btnNewButton.addActionListener(e -> {
            // Lặp qua danh sách các đối tượng Product đã chỉnh sửa
            for (Product editedProduct : editedProducts) {
                // Cập nhật thông tin của đối tượng Product lên server từ xa
                try {
                	
                    productManager.updateProduct(editedProduct);
                    productResult =   productManager.getProducts();
                    updateTable(productResult, leftSubPanel2);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
            // Xóa danh sách các đối tượng Product đã chỉnh sửa sau khi cập nhật thành công
            editedProducts.clear();
            // Sau khi cập nhật, bạn có thể làm các công việc khác như cập nhật lại bảng
        });

        
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
            	handleOtherAction();
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
        addButton.addActionListener(e -> {
            // Lấy thông tin từ các trường nhập
            String productID = productIDField.getText();
            String productName = productNameField.getText();
            String priceText = priceField.getText();
            String quantityText = quantityField.getText();
            String description = descriptionArea.getText();
            String supplierID = supplierIDField.getText();
            
            // Kiểm tra xem các trường nhập liệu có được nhập đầy đủ không
            if (productID.isEmpty() || productName.isEmpty() || priceText.isEmpty() || quantityText.isEmpty() || description.isEmpty() || supplierID.isEmpty()) {
                // Hiển thị thông báo nếu thiếu trường nào đó
                JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                return; // Dừng việc thực hiện thêm sản phẩm nếu có trường nhập liệu thiếu
            }

            // Tiến hành thêm sản phẩm nếu các trường nhập liệu đầy đủ
            try {
                double price = Double.parseDouble(priceText);
                int quantity = Integer.parseInt(quantityText);
                Product product = new Product(productID, productName, price, quantity, description, supplierID);
                handleOtherAction();
                productManager.addProduct(product);
                updateProductTable();
                // Xóa dữ liệu từ các trường nhập
                productIDField.setText("");
                productNameField.setText("");
                priceField.setText("");
                quantityField.setText("");
                descriptionArea.setText("");
                supplierIDField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số hợp lệ cho giá và số lượng.");
            } catch (RemoteException ex) {
                ex.printStackTrace();
            }
        });


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
    private void updateProductTable() {
        try {
            // Lấy danh sách sản phẩm từ ProductManager
             productResult = productManager.getProducts();

            // Cập nhật bảng hiển thị với danh sách sản phẩm mới
            updateTable(productResult, leftSubPanel2);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
    }

    private void updateTable(List<Product> productList, JPanel panel) {
        // Xóa bảng hiện tại khỏi panel
        panel.removeAll();

        // Chuyển đổi dữ liệu thành mảng 2 chiều
        String[][] data = new String[productList.size()][6];
        for (int i = 0; i < productList.size(); i++) {
            Product product = productList.get(i);
            data[i][0] = product.getProductID();
            data[i][1] = product.getProductName();
            data[i][2] = String.valueOf(product.getPrice());
            data[i][3] = String.valueOf(product.getQuantity());
            data[i][4] = product.getDescription();
            data[i][5] = product.getSupplierID();
        }

        // Tiêu đề cột
        String[] columnNames = {"Product ID", "Product Name", "Price", "Quantity", "Description", "Supplier ID"};

     // Tạo bảng hiển thị dữ liệu
        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa trực tiếp
            }
        };
        table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setFillsViewportHeight(true); // Đảm bảo bảng lấp đầy kích thước của JScrollPane
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setViewportBorder(null);
        panel.add(scrollPaneTable, BorderLayout.CENTER); // Thêm scrollPane vào vị trí CENTER của panel


        // Customize header
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLUE); // Set header background color
        header.setForeground(Color.WHITE); // Set header text color
        Font currentFont = header.getFont();
        Font newFont = currentFont.deriveFont(Font.BOLD, 18f);
        header.setFont(newFont);

        // Thêm bảng mới vào panel
        panel.add(scrollPaneTable, BorderLayout.CENTER);

        // Cập nhật lại panel
        panel.revalidate();
        panel.repaint();
        // Sự kiện lắng nghe cho bảng
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Kiểm tra xem người dùng đã nhấn đúp chuột hay chưa
                if (e.getClickCount() == 2) {
                    // Lấy chỉ số hàng của dòng được chọn
                    int row = table.getSelectedRow();
                    // Lấy dữ liệu từ mô hình bảng
                    TableModel model = table.getModel();
                    // Lấy dữ liệu từ các cột của dòng được chọn
                    String productID = model.getValueAt(row, 0).toString();
                    String productName = model.getValueAt(row, 1).toString();
                    String price = model.getValueAt(row, 2).toString();
                    String quantity = model.getValueAt(row, 3).toString();
                    String description = model.getValueAt(row, 4).toString();
                    String supplierID = model.getValueAt(row, 5).toString();
                    // Hiển thị cửa sổ nổi để chỉnh sửa thông tin
                    showEditWindow(productID, productName, price, quantity, description, supplierID, row);
                }
            }
        });
    }
    private void showEditWindow(String productID, String productName, String price, String quantity, String description, String supplierID, int row) {
        // Tạo cửa sổ nổi
        JFrame editFrame = new JFrame("Edit Product");

        // Tạo các thành phần của cửa sổ
        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label và TextField cho Product ID
        JLabel productIDLabel = new JLabel("Product ID:");
        editPanel.add(productIDLabel, gbc);

        JTextField productIDField = new JTextField(20);
        productIDField.setText(productID);
        gbc.gridx = 1;
        editPanel.add(productIDField, gbc);

        // Label và TextField cho Product Name
        JLabel productNameLabel = new JLabel("Product Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        editPanel.add(productNameLabel, gbc);

        JTextField productNameField = new JTextField(20);
        productNameField.setText(productName);
        gbc.gridx = 1;
        editPanel.add(productNameField, gbc);
        // Label và TextField cho Price
        JLabel priceLabel = new JLabel("Price:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        editPanel.add(priceLabel, gbc);

        JTextField priceField = new JTextField(20);
        priceField.setText(price);
        gbc.gridx = 1;
        editPanel.add(priceField, gbc);

        // Label và TextField cho Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        editPanel.add(quantityLabel, gbc);

        JTextField quantityField = new JTextField(20);
        quantityField.setText(quantity);
        gbc.gridx = 1;
        editPanel.add(quantityField, gbc);

        // Label và TextField cho Description
        JLabel descriptionLabel = new JLabel("Description:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        editPanel.add(descriptionLabel, gbc);

        JTextArea descriptionArea = new JTextArea(5, 20);
        descriptionArea.setText(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        gbc.gridx = 1;
        editPanel.add(scrollPane, gbc);

        // Label và TextField cho Supplier ID
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        editPanel.add(supplierIDLabel, gbc);

        JTextField supplierIDField = new JTextField(20);
        supplierIDField.setText(supplierID);
        gbc.gridx = 1;
        editPanel.add(supplierIDField, gbc);

        // Thêm nút "Lưu"
        JButton saveButton = new JButton("Lưu");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        editPanel.add(saveButton, gbc);

     // Xử lý sự kiện lưu
        saveButton.addActionListener(e -> {
            // Lấy dữ liệu từ các trường nhập trong cửa sổ nổi
            String editedProductID = productIDField.getText();
            String editedProductName = productNameField.getText();
            double editedPrice = Double.parseDouble(priceField.getText());
            int editedQuantity = Integer.parseInt(quantityField.getText());
            String editedDescription = descriptionArea.getText();
            String editedSupplierID = supplierIDField.getText();
         // Tạo một đối tượng Product mới từ các thông tin chỉnh sửa
            Product editedProduct = new Product(editedProductID, editedProductName, editedPrice, editedQuantity, editedDescription, editedSupplierID);

            // Thêm sản phẩm chỉnh sửa vào danh sách editedProducts
            editedProducts.add(editedProduct);

            // Cập nhật dòng tương ứng trong bảng
            table.setValueAt(editedProductID, row, 0);
            table.setValueAt(editedProductName, row, 1);
            table.setValueAt(editedPrice, row, 2);
            table.setValueAt(editedQuantity, row, 3);
            table.setValueAt(editedDescription, row, 4);
            table.setValueAt(editedSupplierID, row, 5);
            dataModified = true;
            // Đóng cửa sổ nổi sau khi lưu
            editFrame.dispose();
        });


        // Đặt cấu hình cho cửa sổ nổi và hiển thị nó
        editFrame.getContentPane().add(editPanel);
        editFrame.pack();
        editFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        editFrame.setVisible(true);
    }
    private void handleOtherAction() {
        if (dataModified) {
            int choice = JOptionPane.showConfirmDialog(null, "Bạn chưa cập nhật dữ liệu. Bạn có muốn cập nhật", "Cảnh báo", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
            	for (Product editedProduct : editedProducts) {
                    // Cập nhật thông tin của đối tượng Promotion lên server từ xa
                    try {
                    	
                        productManager.updateProduct(editedProduct);
                        productResult = productManager.getProducts();
                        updateTable(productResult,leftSubPanel2);
                        dataModified = false;
                    } catch (RemoteException ex) {
                        ex.printStackTrace();
                    }
                }
            }else {
            	editedProducts.clear();
            	dataModified = false;
            }
        }
        // Thực hiện các thao tác khác ở đây
    }
}