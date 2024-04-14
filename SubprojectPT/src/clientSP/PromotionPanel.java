package clientSP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.List;

import interfaceQLSP.interfaceProductManager;
import objectQLSP.Promotion;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

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
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy từ khoá tìm kiếm từ trường nhập liệu
                String keyword = searchField.getText();
				try {
					promotionResult = productManager.searchPromotions(keyword);
					updateTable(promotionResult, leftSubPanel2);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
            }
        });
        // ComboBox có 4 tùy chọn
        String[] options = {"Ngày tạo gần nhất", "Ngày tạo xa nhất"};
        JComboBox<String> comboBox = new JComboBox<>(options);
        comboBox.setFont(new Font("Tahoma", Font.PLAIN, 16));
        comboBox.addActionListener(e -> {
            String selectedOption = (String) comboBox.getSelectedItem();
            try {
				if (selectedOption.equals("Ngày tạo gần nhất")) {
                    List<Promotion> sortedPromotion = productManager.sortPromotionDateDescending(promotionResult);
                    // Gọi phương thức để sử dụng sortedProducts
                    updateTable(sortedPromotion,leftSubPanel2);
                } else if (selectedOption.equals("Ngày tạo xa nhất")) {
                    List<Promotion> sortedPromotion = productManager.sortPromotionDateAscending(promotionResult);
                    // Gọi phương thức để sử dụng sortedProducts
                    updateTable(sortedPromotion,leftSubPanel2);
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
			promotionResult = productManager.getPromotions();
			updateTable(promotionResult, leftSubPanel2);
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
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
            // Lặp qua danh sách các đối tượng Promotion đã chỉnh sửa
            for (Promotion editedPromotions : editedPromotion) {
                // Cập nhật thông tin của đối tượng Promotion lên server từ xa
                try {
                	
                    productManager.updatePromotion(editedPromotions);
                    promotionResult = productManager.getPromotions();
                    updateTable(promotionResult,leftSubPanel2);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
            // Xóa danh sách các đối tượng Promotion đã chỉnh sửa sau khi cập nhật thành công
            editedPromotion.clear();
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
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một khuyến mãi để xóa.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Lấy ID của sản phẩm từ dòng được chọn
            String productID = table.getValueAt(selectedRow, 0).toString();
            String promotionID = table.getValueAt(selectedRow, 1).toString();
            // Gọi phương thức deleteProduct từ xa với ID của sản phẩm
            try {
                productManager.deletePromotion(productID, promotionID);
                // Xóa sản phẩm từ bảng dữ liệu
                DefaultTableModel model = (DefaultTableModel) table.getModel();
                model.removeRow(selectedRow); // Xóa dòng tương ứng từ bảng

                // Hiển thị thông báo thành công (nếu cần)
                JOptionPane.showMessageDialog(null, "Đã xóa khuyến mãi thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
            } catch (RemoteException ex) {
                ex.printStackTrace();
                // Xử lý ngoại lệ khi gặp lỗi khi gọi phương thức từ xa
                JOptionPane.showMessageDialog(null, "Lỗi khi xóa khuyến mãi.", "Lỗi", JOptionPane.ERROR_MESSAGE);
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
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField supplierIDField = new JTextField(20); // Số ký tự mặc định
        supplierIDField.setEditable(false);
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

        // Lấy ra mã nhà cung cấp và gán vào jtextfield supplierIDField
        productIDField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                String productID = productIDField.getText();
                try {
                    String supplierID = productManager.getSupplierID(productID);
                    if (supplierID != null) {
                        supplierIDField.setText(supplierID);
                    } else {
                    	  // Hiển thị thông báo khi kết quả trả về null
                        JOptionPane.showMessageDialog(null, "Mã sản phẩm sai hoặc Không tìm thấy nhà cung cấp tương ứng với mã sản phẩm của bạn!");
                        supplierIDField.setText("");
                    }
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        });

        
        // Label và TextField cho Phần trăm khuyến mãi
        JLabel promotionRateLabel = new JLabel("Tỉ lệ khuyến mãi:");
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
                String promotionRateText = promotionRateField.getText();
                java.util.Date startDateUtil = startDateChooser.getDate();
                java.util.Date endDateUtil = endDateChooser.getDate();

                // Kiểm tra xem các trường nhập liệu có được nhập đầy đủ không
                if (productID.isEmpty() || supplierID.isEmpty() || promotionRateText.isEmpty() || startDateUtil == null || endDateUtil == null) {
                    // Hiển thị thông báo nếu thiếu trường nào đó
                    JOptionPane.showMessageDialog(null, "Vui lòng điền đầy đủ thông tin.");
                    return; // Dừng việc thực hiện thêm promotion nếu có trường nhập liệu thiếu
                }

                // Tiến hành thêm promotion nếu các trường nhập liệu đầy đủ
                try {
                	
                    double promotionRate = Double.parseDouble(promotionRateText);
                    java.sql.Date startDate = new java.sql.Date(startDateUtil.getTime());
                    java.sql.Date endDate = new java.sql.Date(endDateUtil.getTime());
                    
                 // Kiểm tra xem có sự trùng lặp với khuyến mãi hiện có không
                    if (productManager.isPromotionOverlap(productID, startDate, endDate)) {
                        JOptionPane.showMessageDialog(null, "Khuyến mãi cho sản phẩm này đã tồn tại trong khoảng thời gian bạn đã chọn.");
                        return; // Dừng việc thực hiện thêm khuyến mãi nếu có sự trùng lặp
                    }
                    Promotion promotion = new Promotion(productID, supplierID, promotionRate, startDate, endDate);
                    productManager.addPromotion(promotion);
                    updatePromotionTable();
                    // Xóa dữ liệu trong các trường nhập liệu sau khi thêm promotion thành công
                    productIDField.setText("");
                    supplierIDField.setText("");
                    promotionRateField.setText("");
                    startDateChooser.setDate(null);
                    endDateChooser.setDate(null);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá trị số hợp lệ cho tỷ lệ khuyến mãi.");
                } catch (RemoteException ex) {
                    ex.printStackTrace();
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
    private void displayPromotionTable(JPanel panel) {
        // Lấy dữ liệu từ đối tượng phân tán
        try {
            promotionResult = productManager.getPromotions();
        } catch (RemoteException e) {
            e.printStackTrace(); // Xử lý ngoại lệ nếu cần thiết
            return;
        }

        // Chuyển đổi dữ liệu thành mảng 2 chiều
        String[][] data = new String[promotionResult.size()][5];
        for (int i = 0; i < promotionResult.size(); i++) {
            Promotion promotion = promotionResult.get(i);
            data[i][0] = promotion.getProductID();
            data[i][1] = promotion.getSupplierID();
            data[i][2] = String.valueOf(promotion.getPromotionRate());
            data[i][3] = promotion.getStartDate().toString();
            data[i][4] = promotion.getEndDate().toString();
        }

        // Tiêu đề cột
        String[] columnNames = {"Product ID", "Supplier ID", "Promotion Rate", "Start Date", "End Date"};

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
                	String productID = model.getValueAt(row, 0).toString();
                	String supplierID = model.getValueAt(row, 1).toString();
                	String promotionRate = model.getValueAt(row, 2).toString();

                	// Chuyển đổi kiểu dữ liệu của startDate từ String sang java.sql.Date và sau đó thành java.util.Date
                	String startDateString = model.getValueAt(row, 3).toString();
                	java.sql.Date startDateSQL = java.sql.Date.valueOf(startDateString);
                	java.util.Date startDate = new java.util.Date(startDateSQL.getTime());

                	// Tương tự cho endDate
                	String endDateString = model.getValueAt(row, 4).toString();
                	java.sql.Date endDateSQL = java.sql.Date.valueOf(endDateString);
                	java.util.Date endDate = new java.util.Date(endDateSQL.getTime());

                	// Hiển thị cửa sổ nổi để chỉnh sửa thông tin
                	showEditWindow(productID, supplierID, promotionRate, startDate, endDate, row);
                }
            }
        });
    }
    private void updatePromotionTable() {
        try {
            // Lấy danh sách khuyến mại mới nhất từ ProductManager
            promotionResult = productManager.getPromotions();

            // Cập nhật bảng hiển thị với danh sách khuyến mại mới
            updateTable(promotionResult, leftSubPanel2);
        } catch (RemoteException ex) {
            ex.printStackTrace();
            // Xử lý ngoại lệ nếu cần
        }
    }

    private void updateTable(List<Promotion> promotionList, JPanel panel) {
        // Xóa bảng hiện tại khỏi panel
        panel.removeAll();

        // Chuyển đổi dữ liệu thành mảng 2 chiều
        String[][] data = new String[promotionList.size()][5];
        for (int i = 0; i < promotionList.size(); i++) {
            Promotion promotion = promotionList.get(i);
            data[i][0] = promotion.getProductID();
            data[i][1] = promotion.getSupplierID();
            data[i][2] = String.valueOf(promotion.getPromotionRate());
            data[i][3] = promotion.getStartDate().toString();
            data[i][4] = promotion.getEndDate().toString();
        }

        // Tiêu đề cột
        String[] columnNames = {"Product ID", "Supplier ID", "Promotion Rate", "Start Date", "End Date"};

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
                	String supplierID = model.getValueAt(row, 1).toString();
                	String promotionRate = model.getValueAt(row, 2).toString();

                	// Chuyển đổi kiểu dữ liệu của startDate từ String sang java.sql.Date và sau đó thành java.util.Date
                	String startDateString = model.getValueAt(row, 3).toString();
                	java.sql.Date startDateSQL = java.sql.Date.valueOf(startDateString);
                	java.util.Date startDate = new java.util.Date(startDateSQL.getTime());

                	// Tương tự cho endDate
                	String endDateString = model.getValueAt(row, 4).toString();
                	java.sql.Date endDateSQL = java.sql.Date.valueOf(endDateString);
                	java.util.Date endDate = new java.util.Date(endDateSQL.getTime());

                	// Hiển thị cửa sổ nổi để chỉnh sửa thông tin
                	showEditWindow(productID, supplierID, promotionRate, startDate, endDate, row);
                }
            }
        });
    }
    private void showEditWindow(String productID, String supplierID, String promotionRate, java.util.Date startDate, java.util.Date endDate, int row) {
        // Tạo cửa sổ nổi
        JFrame editFrame = new JFrame("Edit Promotion");

        // Tạo panel chứa các trường nhập dữ liệu
        JPanel editPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label và TextField cho Product ID
        JLabel productIDLabel = new JLabel("Product ID:");
        productIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        editPanel.add(productIDLabel, gbc);

        JTextField productIDField = new JTextField(20);
        productIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        productIDField.setText(productID);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(productIDField, gbc);

        // Label và TextField cho Supplier ID
        JLabel supplierIDLabel = new JLabel("Supplier ID:");
        supplierIDLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        editPanel.add(supplierIDLabel, gbc);

        JTextField supplierIDField = new JTextField(20);
        supplierIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        supplierIDField.setText(supplierID);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(supplierIDField, gbc);

        // Label và TextField cho Promotion Rate
        JLabel promotionRateLabel = new JLabel("Promotion Rate:");
        promotionRateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        editPanel.add(promotionRateLabel, gbc);

        JTextField promotionRateField = new JTextField(20);
        promotionRateField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        promotionRateField.setText(promotionRate);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(promotionRateField, gbc);

        // Label và JDateChooser cho Start Date
        JLabel startDateLabel = new JLabel("Start Date:");
        startDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        editPanel.add(startDateLabel, gbc);

        JDateChooser startDateChooser = new JDateChooser(startDate);
        startDateChooser.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(startDateChooser, gbc);

        // Label và JDateChooser cho End Date
        JLabel endDateLabel = new JLabel("End Date:");
        endDateLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.weightx = 0.0;
        gbc.fill = GridBagConstraints.NONE;
        editPanel.add(endDateLabel, gbc);

        JDateChooser endDateChooser = new JDateChooser(endDate);
        endDateChooser.setPreferredSize(new Dimension(200, 25));
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(endDateChooser, gbc);

        // Thêm nút "Lưu"
        JButton saveButton = new JButton("Lưu");
        saveButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        editPanel.add(saveButton, gbc);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lấy dữ liệu từ các trường nhập liệu
                String editedProductID = productIDField.getText();
                String editedSupplierID = supplierIDField.getText();
                double editedPromotionRate = Double.parseDouble(promotionRateField.getText());
                
                // Lấy ngày tháng được chọn từ JDateChooser
                java.util.Date startDateUtil = startDateChooser.getDate();
                java.util.Date endDateUtil = endDateChooser.getDate();
                
                // Kiểm tra xem ngày tháng đã được chọn hay chưa
                if (startDateUtil != null && endDateUtil != null) {
                    // Chuyển đổi sang kiểu java.sql.Date
                    java.sql.Date startDate = new java.sql.Date(startDateUtil.getTime());
                    java.sql.Date endDate = new java.sql.Date(endDateUtil.getTime());
                    
                    // Tạo đối tượng Promotion từ dữ liệu nhập liệu
                    Promotion editedPromotions = new Promotion(editedProductID, editedSupplierID, editedPromotionRate, startDate, endDate);

                    // Thêm sản phẩm chỉnh sửa vào danh sách editedProducts
                    editedPromotion.add(editedPromotions);
                    
                    // Cập nhật dữ liệu tại dòng chỉnh sửa trong bảng
                    table.setValueAt(editedProductID, row, 0);
                    table.setValueAt(editedSupplierID, row, 1);
                    table.setValueAt(editedPromotionRate, row, 2);
                    table.setValueAt(startDate, row, 3);
                    table.setValueAt(endDate, row, 4);
                }
                
                // Đóng cửa sổ nổi sau khi lưu
                editFrame.dispose();
            }
        });

        // Đặt cấu hình cho cửa sổ nổi và hiển thị nó
        editFrame.getContentPane().add(editPanel);
        editFrame.pack();
        editFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        editFrame.setVisible(true);
    }
}