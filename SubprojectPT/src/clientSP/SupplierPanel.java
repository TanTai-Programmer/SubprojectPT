package clientSP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

import interfaceQLSP.interfaceProductManager;
import objectQLSP.Product;
import objectQLSP.SupplierSP;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class SupplierPanel extends JPanel {
   
	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private JTable table;
	    private JPanel leftSubPanel2;
	    private interfaceProductManager supplierManager;
	    private List<SupplierSP> supplierResult;
	    private  List<SupplierSP> editedSuppliers = new ArrayList<>();

	    public SupplierPanel(interfaceProductManager supplierManager){
	    	this.supplierManager = supplierManager;
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
				supplierResult = supplierManager.searchSuppliers(keyword);
		           // Cập nhật bảng hiển thị dữ liệu với kết quả tìm kiếm
	            updateTable(supplierResult, leftSubPanel2);
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
        // ComboBox có 4 tùy chọn
//        String[] options = {"Giá tăng dần", "Giá giảm dần", "Số lượng tăng dần", "Số lượng giảm dần"};
//        JComboBox<String> comboBox = new JComboBox<>(options);
//        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
//
//        comboBox.addActionListener(e -> {
//            String selectedOption = (String) comboBox.getSelectedItem();
//            try {
//				if (selectedOption.equals("Giá tăng dần")) {
//                    List<Product> sortedProducts = productManager.sortProductsByPriceAscending(productResult);
//                    // Gọi phương thức để sử dụng sortedProducts
//                    updateTable(sortedProducts,leftSubPanel2);
//                } else if (selectedOption.equals("Giá giảm dần")) {
//                    List<Product> sortedProducts = productManager.sortProductsByPriceDescending(productResult);
//                    // Gọi phương thức để sử dụng sortedProducts
//                    updateTable(sortedProducts,leftSubPanel2);
//                } else if (selectedOption.equals("Số lượng giảm dần")) {
//                    List<Product> sortedProducts = productManager.sortProductsByQuantityAscending(productResult);
//                    // Gọi phương thức để sử dụng sortedProducts
//                    updateTable(sortedProducts,leftSubPanel2);
//                }else if (selectedOption.equals("Số lượng tăng dần")) {
//                    List<Product> sortedProducts = productManager.sortProductsByQuantityDescending(productResult);
//                    // Gọi phương thức để sử dụng sortedProducts
//                    updateTable(sortedProducts,leftSubPanel2);
//                }
//                // Xử lý các lựa chọn khác nếu cần
//            } catch (RemoteException ex) {
//                ex.printStackTrace();
//                // Xử lý ngoại lệ nếu cần
//            }
//        });
        
//        GridBagConstraints gbcComboBox = new GridBagConstraints();
//        gbcComboBox.gridx = 0;
//        gbcComboBox.gridy = 1;
//        gbcComboBox.gridwidth = 3; // Chiếm 3 cột
//        gbcComboBox.anchor = GridBagConstraints.EAST; // Căn trái
//        gbcComboBox.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
//        leftSubPanel1.add(comboBox, gbcComboBox);

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
        displayProductTable(leftSubPanel2); // Tạo bảng và thêm vào leftSubPanel2
        
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
            // Lặp qua danh sách các đối tượng Supplier đã chỉnh sửa
            for (SupplierSP editedSupplier : editedSuppliers) {
                // Cập nhật thông tin của đối tượng supplier lên server từ xa
                try {
                	
                    supplierManager.updateSupplier(editedSupplier);
                    supplierResult =   supplierManager.getSuppliers();
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
            // Xóa danh sách các đối tượng supplier đã chỉnh sửa sau khi cập nhật thành công
            editedSuppliers.clear();
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
            String supplierID = table.getValueAt(selectedRow, 0).toString();

            // Gọi phương thức deleteProduct từ xa với ID của sản phẩm
            try {
                supplierManager.deleteSupplier(supplierID);
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
        JLabel label = new JLabel("Nhập thông tin nhà cung cấp");
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

        // Label và TextField cho Supplier ID
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField supplierIDField = new JTextField(20); // Số ký tự mặc định
        supplierIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        supplierIDField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcsupplierIDLabel = new GridBagConstraints();
        gbcsupplierIDLabel.gridx = 0;
        gbcsupplierIDLabel.gridy = 0;
        gbcsupplierIDLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcsupplierIDLabel.insets = new Insets(10, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(supplierIDLabel, gbcsupplierIDLabel);
        GridBagConstraints gbcsupplierIDField = new GridBagConstraints();
        gbcsupplierIDField.gridx = 1;
        gbcsupplierIDField.gridy = 0;
        gbcsupplierIDField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcsupplierIDField.weightx = 1.0; // Độ rộng linh hoạt
        gbcsupplierIDField.insets = new Insets(10, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(supplierIDField, gbcsupplierIDField);

        // Label và TextField cho Supplier Name
        JLabel supplierNameLabel = new JLabel("Supplier Name:");
        supplierNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField supplierNameField = new JTextField(20); // Số ký tự mặc định
        supplierNameField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        supplierNameField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcsupplierNameLabel = new GridBagConstraints();
        gbcsupplierNameLabel.gridx = 0;
        gbcsupplierNameLabel.gridy = 1;
        gbcsupplierNameLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcsupplierNameLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(supplierNameLabel, gbcsupplierNameLabel);
        GridBagConstraints gbcsupplierNameField = new GridBagConstraints();
        gbcsupplierNameField.gridx = 1;
        gbcsupplierNameField.gridy = 1;
        gbcsupplierNameField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcsupplierNameField.weightx = 1.0; // Độ rộng linh hoạt
        gbcsupplierNameField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(supplierNameField, gbcsupplierNameField);

        // Label và TextField cho Address
        JLabel addressLabel = new JLabel("Address:");
        addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField addressField = new JTextField(20); // Số ký tự mặc định
        addressField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        addressField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcaddressLabel = new GridBagConstraints();
        gbcaddressLabel.gridx = 0;
        gbcaddressLabel.gridy = 2;
        gbcaddressLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcaddressLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(addressLabel, gbcaddressLabel);
        GridBagConstraints gbcaddressField = new GridBagConstraints();
        gbcaddressField.gridx = 1;
        gbcaddressField.gridy = 2;
        gbcaddressField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcaddressField.weightx = 1.0; // Độ rộng linh hoạt
        gbcaddressField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(addressField, gbcaddressField);

        // Label và TextField cho PhoneNumber
        JLabel PhoneNumberLabel = new JLabel("Phone Number:");
        PhoneNumberLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField PhoneNumberField = new JTextField(20); // Số ký tự mặc định
        PhoneNumberField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        PhoneNumberField.setPreferredSize(new Dimension(200, 25)); // Kích thước cụ thể
        GridBagConstraints gbcPhoneNumberLabel = new GridBagConstraints();
        gbcPhoneNumberLabel.gridx = 0;
        gbcPhoneNumberLabel.gridy = 3;
        gbcPhoneNumberLabel.anchor = GridBagConstraints.WEST; // Căn trái
        gbcPhoneNumberLabel.insets = new Insets(5, 10, 5, 5); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(PhoneNumberLabel, gbcPhoneNumberLabel);
        GridBagConstraints gbcPhoneNumberField = new GridBagConstraints();
        gbcPhoneNumberField.gridx = 1;
        gbcPhoneNumberField.gridy = 3;
        gbcPhoneNumberField.fill = GridBagConstraints.HORIZONTAL; // Giãn theo chiều ngang
        gbcPhoneNumberField.weightx = 1.0; // Độ rộng linh hoạt
        gbcPhoneNumberField.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(PhoneNumberField, gbcPhoneNumberField);
        JScrollPane scrollPane = new JScrollPane();
        GridBagConstraints gbcScrollPane = new GridBagConstraints();
        gbcScrollPane.gridx = 1;
        gbcScrollPane.gridy = 4;
        gbcScrollPane.fill = GridBagConstraints.BOTH; // Giãn theo cả chiều ngang và chiều dọc
        gbcScrollPane.weightx = 1.0; // Độ rộng linh hoạt
        gbcScrollPane.weighty = 1.0; // Độ cao linh hoạt
        gbcScrollPane.insets = new Insets(5, 5, 5, 10); // Khoảng cách giữa các thành phần
        rightSubPanel2.add(scrollPane, gbcScrollPane);

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
            String supplierID = supplierIDField.getText();
            String supplierName = supplierNameField.getText();
			String address = addressField.getText();
            String PhoneNumber = PhoneNumberField.getText();
            
            // Tạo đối tượng Supplier từ thông tin đã nhập
            SupplierSP supplier = new SupplierSP(supplierID, supplierName, address, PhoneNumber);
            
            // Gọi phương thức addsupplier của supplierPanel
            try {
				supplierManager.addSupplier(supplier);
				updateSupplierTable();
				 // Xóa dữ liệu từ các trường nhập
		        supplierIDField.setText("");
		        supplierNameField.setText("");
		        addressField.setText("");
		        PhoneNumberField.setText("");
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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

    private void updateSupplierTable() {
			// TODO Auto-generated method stub
			
		}

	@Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // Kích thước mặc định
    }
    
    
    // Phương thức để tạo và điền dữ liệu vào bảng
    private void displayProductTable(JPanel panel) {
        // Lấy dữ liệu từ đối tượng phân tán
        try {
            supplierResult = supplierManager.getSuppliers();
        } catch (RemoteException e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu cần thiết
            return;
        }

        // Chuyển đổi dữ liệu thành mảng 2 chiều
        String[][] data = new String[supplierResult.size()][6];
        for (int i = 0; i < supplierResult.size(); i++) {
            SupplierSP supplier = supplierResult.get(i);
            data[i][0] = supplier.getSupplierID();
            data[i][1] = supplier.getSupplierName();
            data[i][2] = supplier.getAddress();
            data[i][3] = String.valueOf(supplier.getPhoneNumber());
            
        }

        // Tiêu đề cột
        String[] columnNames = {"Supplier ID", "Supplier Name", "Address", "Phone Number"};

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
        table = new JTable(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"Supplier ID", "Supplier Name", "Address", "Phone Number"
        	}
        ));
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setFillsViewportHeight(true); // Đảm bảo bảng lấp đầy kích thước của JScrollPane
        JScrollPane scrollPaneTable = new JScrollPane(table);
        scrollPaneTable.setViewportBorder(null);
        panel.add(scrollPaneTable, BorderLayout.CENTER); // Thêm scrollPane vào vị trí CENTER của panel

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
                    String supplierID = model.getValueAt(row, 0).toString();
                    String supplierName = model.getValueAt(row, 1).toString();
                    String address = model.getValueAt(row, 2).toString();
                    String PhoneNumber = model.getValueAt(row, 3).toString();
                    
                    // Hiển thị cửa sổ nổi để chỉnh sửa thông tin
                    showEditWindow(supplierID, supplierName, address, PhoneNumber, row);
                }
            }
        });
    }

    private void updateProductTable() {
        try {
            // Lấy danh sách sản phẩm từ ProductManager
             supplierResult = supplierManager.getSuppliers();

            // Cập nhật bảng hiển thị với danh sách sản phẩm mới
            updateTable(supplierResult, leftSubPanel2);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
    }

    private void updateTable(List<SupplierSP> supplierList, JPanel panel) {
        // Xóa bảng hiện tại khỏi panel
        panel.removeAll();

        // Chuyển đổi dữ liệu thành mảng 2 chiều
        String[][] data = new String[supplierList.size()][6];
        for (int i = 0; i < supplierList.size(); i++) {
      	    SupplierSP supplier = supplierList.get(i);
            data[i][0] = supplier.getSupplierID();
            data[i][1] = supplier.getSupplierName();
            data[i][2] = supplier.getAddress();
            data[i][3] = supplier.getPhoneNumber();
           
        }

        // Tiêu đề cột
        String[] columnNames = {"Supplier ID", "Supplier Name", "Address", "PhoneNumber"};

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
        		    String supplierID = model.getValueAt(row, 0).toString();
                    String supplierName = model.getValueAt(row, 1).toString();
                    String address = model.getValueAt(row, 2).toString();
                    String PhoneNumber = model.getValueAt(row, 3).toString();
                    // Hiển thị cửa sổ nổi để chỉnh sửa thông tin
                    showEditWindow(supplierID, supplierName, address, PhoneNumber, row);
                }
            }
        });
    }
    private void showEditWindow(String supplierID, String supplierName, String address, String PhoneNumber, int row) {
        // Tạo cửa sổ nổi
        JFrame editFrame = new JFrame("Edit Supplier");

        // Tạo các thành phần của cửa sổ
        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label và TextField cho supplier ID
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        editPanel.add(supplierIDLabel, gbc);

        JTextField supplierIDField = new JTextField(20);
        supplierIDField.setText(supplierID);
        gbc.gridx = 1;
        editPanel.add(supplierIDField, gbc);

        // Label và TextField cho supplier Name
        JLabel supplierNameLabel = new JLabel("supplier Name:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        editPanel.add(supplierNameLabel, gbc);

        JTextField supplierNameField = new JTextField(20);
        supplierNameField.setText(supplierName);
        gbc.gridx = 1;
        editPanel.add(supplierNameField, gbc);
     // Label và TextField cho Address
        JLabel AddressLabel = new JLabel("Address:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        editPanel.add(AddressLabel, gbc);

        JTextField AddressField = new JTextField(20);
        AddressField.setText(address);
        gbc.gridx = 1;
        editPanel.add(AddressField, gbc);

     // Label và TextField cho PhoneNumber
        JLabel PhoneNumberLabel = new JLabel("PhoneNumber:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        editPanel.add(PhoneNumberLabel, gbc);

        JTextField PhoneNumberField = new JTextField(20);
        PhoneNumberField.setText(PhoneNumber);
        gbc.gridx = 1;
        editPanel.add(PhoneNumberField, gbc);

       

        // Tạo các thành phần cho các trường dữ liệu khác tương tự

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
            String editedSupplierID = supplierIDField.getText();
            String editedSupplierName = supplierNameField.getText();
            String editedAddress =  AddressField.getText();
            String editedPhoneNumber = PhoneNumberField.getText();

         // Tạo một đối tượng Supplier mới từ các thông tin chỉnh sửa
            SupplierSP editedSupplier = new SupplierSP(editedSupplierID, editedSupplierName, editedAddress, editedPhoneNumber);

            // Thêm sản phẩm chỉnh sửa vào danh sách editedProducts
            editedSuppliers.add(editedSupplier);

            // Cập nhật dòng tương ứng trong bảng
            table.setValueAt(editedSupplierID, row, 0);
            table.setValueAt(editedSupplierName, row, 1);
            table.setValueAt(editedAddress, row, 2);
            table.setValueAt(editedPhoneNumber, row, 3);
            

            // Đóng cửa sổ nổi sau khi lưu
            editFrame.dispose();
        });


        // Đặt cấu hình cho cửa sổ nổi và hiển thị nó
        editFrame.getContentPane().add(editPanel);
        editFrame.pack();
        editFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        editFrame.setVisible(true);
    }

}