package clientSP;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import java.util.ArrayList;
import java.util.List;

import interfaceQLSP.interfaceProductManager;
import objectQLSP.Invoice;
import objectQLSP.InvoiceDetail;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;

public class InvoicePanel extends JPanel {
   
	/**
	 * 
	 */
		private static final long serialVersionUID = 1L;
		private JPanel leftSubPanel2;
	    private interfaceProductManager invoiceManager;
	    private List<Invoice> invoiceResult;
	    public InvoicePanel(interfaceProductManager invoiceManager){
	    	this.invoiceManager = invoiceManager;
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
        searchButton.addActionListener(e -> {
       	 // Lấy từ khóa tìm kiếm từ TextField
           String keyword = searchField.getText(); 
			try {
				invoiceResult = invoiceManager.searchInvoice(keyword);
		           // Cập nhật bảng hiển thị dữ liệu với kết quả tìm kiếm
	            updateTable(invoiceResult, leftSubPanel2);
			} catch (RemoteException e1) {
				e1.printStackTrace();
			}
       });
        
        GridBagConstraints gbcSearchButton = new GridBagConstraints();
        gbcSearchButton.gridx = 2;
        gbcSearchButton.gridy = 0;
        gbcSearchButton.anchor = GridBagConstraints.WEST; // Căn trái
        gbcSearchButton.insets = new Insets(5, 5, 5, 5); // Khoảng cách giữa các thành phần
        leftSubPanel1.add(searchButton, gbcSearchButton);

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
        	invoiceResult = invoiceManager.getInvoice();
			updateTable(invoiceResult, leftSubPanel2);
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

        // Label "Nhập thông tin hóa đơn"
        JLabel label = new JLabel("Nhập thông tin Hóa đơn");
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
        JTextField productIDField = new JTextField(20);
        productIDField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        productIDField.setPreferredSize(new Dimension(200, 25));
        GridBagConstraints gbcproductIDLabel = new GridBagConstraints();
        gbcproductIDLabel.gridx = 0;
        gbcproductIDLabel.gridy = 3;
        gbcproductIDLabel.anchor = GridBagConstraints.WEST;
        gbcproductIDLabel.insets = new Insets(5, 10, 5, 5);
        rightSubPanel2.add(productIDLabel, gbcproductIDLabel);
        GridBagConstraints gbcproductIDField = new GridBagConstraints();
        gbcproductIDField.gridx = 1;
        gbcproductIDField.gridy = 3;
        gbcproductIDField.fill = GridBagConstraints.HORIZONTAL;
        gbcproductIDField.weightx = 1.0;
        gbcproductIDField.insets = new Insets(5, 5, 5, 10);
        rightSubPanel2.add(productIDField, gbcproductIDField);

        // Label và TextField cho Quantity
        JLabel quantityLabel = new JLabel("Quantity:");
        quantityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        JTextField quantityField = new JTextField(20);
        quantityField.setFont(new Font("Tahoma", Font.PLAIN, 16));
        quantityField.setPreferredSize(new Dimension(200, 25));
        GridBagConstraints gbcquantityLabel = new GridBagConstraints();
        gbcquantityLabel.gridx = 0;
        gbcquantityLabel.gridy = 4;
        gbcquantityLabel.anchor = GridBagConstraints.WEST;
        gbcquantityLabel.insets = new Insets(5, 10, 5, 5);
        rightSubPanel2.add(quantityLabel, gbcquantityLabel);
        GridBagConstraints gbcquantityField = new GridBagConstraints();
        gbcquantityField.gridx = 1;
        gbcquantityField.gridy = 4;
        gbcquantityField.fill = GridBagConstraints.HORIZONTAL;
        gbcquantityField.weightx = 1.0;
        gbcquantityField.insets = new Insets(5, 5, 5, 10);
        rightSubPanel2.add(quantityField, gbcquantityField);
        
        // Tạo model cho bảng mini
        DefaultTableModel miniTableModel = new DefaultTableModel();
        miniTableModel.setColumnIdentifiers(new String[]{"Product ID", "Quantity"});

        // Tạo JTable với model đã tạo
        JTable miniTable = new JTable(miniTableModel);

        // Đặt kích thước cho cột trong bảng mini
        miniTable.getColumnModel().getColumn(0).setPreferredWidth(100);
        miniTable.getColumnModel().getColumn(1).setPreferredWidth(100);

        // Tạo JScrollPane để bao bọc bảng mini
        JScrollPane miniTableScrollPane = new JScrollPane(miniTable);
        miniTableScrollPane.setPreferredSize(new Dimension(220, 150));

        // Thêm bảng mini vào rightSubPanel2
        GridBagConstraints gbcMiniTableScrollPane = new GridBagConstraints();
        gbcMiniTableScrollPane.gridx = 0;
        gbcMiniTableScrollPane.gridy = 5; // Điểm bắt đầu của dòng mới
        gbcMiniTableScrollPane.gridwidth = 2; // Đặt chiều rộng của JScrollPane để nó trải dài qua cả hai cột
        gbcMiniTableScrollPane.fill = GridBagConstraints.HORIZONTAL; // Giãn JScrollPane theo chiều ngang
        gbcMiniTableScrollPane.insets = new Insets(10, 10, 10, 10); // Đặt khoảng cách giữa các thành phần
        rightSubPanel2.add(miniTableScrollPane, gbcMiniTableScrollPane);
        
     // Tạo nút "Thêm sản phẩm khác"
        JButton addMoreProductsButton = new JButton("Thêm sản phẩm khác");
        addMoreProductsButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
        // Tạo danh sách để lưu trữ thông tin sản phẩm và số lượng
        List<String> productIds = new ArrayList<>();
        List<Integer> quantities = new ArrayList<>();
     // Thêm sự kiện cho nút "Thêm sản phẩm khác"
        addMoreProductsButton.addActionListener(e -> {
            // Lấy thông tin từ hai trường "Product ID" và "Quantity"
            String productId = productIDField.getText();
            String quantityStr = quantityField.getText();

            // Kiểm tra xem có nhập đủ thông tin không
            if (!productId.isEmpty() && !quantityStr.isEmpty()) {
                try {
                    int quantity = Integer.parseInt(quantityStr);

                    // Thêm thông tin này vào model của bảng mini
                    miniTableModel.addRow(new Object[]{productId, quantity});

                    // Xóa nội dung của hai trường "Product ID" và "Quantity" để chuẩn bị cho việc nhập sản phẩm mới
                    productIDField.setText("");
                    quantityField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số lượng là một số nguyên.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin sản phẩm.");
            }
        });

        // Thêm nút "Thêm sản phẩm khác" vào rightSubPanel2
        GridBagConstraints gbcAddMoreProductsButton = new GridBagConstraints();
        gbcAddMoreProductsButton.gridx = 0;
        gbcAddMoreProductsButton.gridy = 6; // Điểm bắt đầu của dòng mới
        gbcAddMoreProductsButton.gridwidth = 2; // Đặt chiều rộng của nút để nó trải dài qua cả hai cột
        gbcAddMoreProductsButton.fill = GridBagConstraints.HORIZONTAL; // Giãn nút theo chiều ngang
        gbcAddMoreProductsButton.insets = new Insets(10, 10, 10, 10); // Đặt khoảng cách giữa các thành phần
        rightSubPanel2.add(addMoreProductsButton, gbcAddMoreProductsButton);
        
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
        JButton addButton = new JButton("Tạo hóa đơn");
        addButton.setPreferredSize(new Dimension(120, 40)); // Thiết lập kích thước ưu tiên cho nút "Add"
        rightSubPanel3.add(addButton);

        // Xử lý sự kiện khi người dùng nhấn nút để tạo hóa đơn
        addButton.addActionListener(e -> {
            // Cập nhật danh sách sản phẩm và số lượng từ bảng mini
            for (int i = 0; i < miniTableModel.getRowCount(); i++) {
                String productId = miniTableModel.getValueAt(i, 0).toString();
                int quantity = Integer.parseInt(miniTableModel.getValueAt(i, 1).toString());
                productIds.add(productId);
                quantities.add(quantity);
            }

            // Gọi phương thức tạo hóa đơn từ đối tượng invoiceManager với danh sách sản phẩm và số lượng hiện tại
            boolean result = false;
            try {
                result = invoiceManager.createInvoice(productIds, quantities);
            } catch (RemoteException e1) {
                e1.printStackTrace();
            }
            
            if (result) {
                JOptionPane.showMessageDialog(null, "Hóa đơn đã được tạo thành công.");
                try {
                    invoiceResult = invoiceManager.getInvoice();
                } catch (RemoteException e1) {
                    e1.printStackTrace();
                }
                updateTable(invoiceResult, leftSubPanel2);
                // Xóa danh sách sản phẩm và số lượng để chuẩn bị cho hóa đơn mới
                productIds.clear();
                quantities.clear();
            } else {
                JOptionPane.showMessageDialog(null, "Đã xảy ra lỗi khi tạo hóa đơn.");
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
    
    private void updateTable(List<Invoice> invoiceList, JPanel panel) {
        // Xóa bảng hiện tại khỏi panel
        panel.removeAll();

        // Chuyển đổi dữ liệu thành mảng 2 chiều
        String[][] data = new String[invoiceList.size()][3];
        for (int i = 0; i < invoiceList.size(); i++) {
            Invoice invoice = invoiceList.get(i);
            data[i][0] = invoice.getInvoiceID();
            data[i][1] = invoice.getCreateDate().toString(); // Chuyển đổi Timestamp thành chuỗi ngày tháng
            data[i][2] = String.valueOf(invoice.getTotalAmount());
        }

        // Tiêu đề cột
        String[] columnNames = {"Invoice ID", "Create Date", "Total Amount"};

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
        JTable table = new JTable(model);
        table.setFont(new Font("Tahoma", Font.PLAIN, 16));
        table.setFillsViewportHeight(true); // Đảm bảo bảng lấp đầy kích thước của JScrollPane
        JScrollPane scrollPaneTable = new JScrollPane(table);
        panel.add(scrollPaneTable, BorderLayout.CENTER); // Thêm scrollPane vào vị trí CENTER của panel

        // Customize header
        JTableHeader header = table.getTableHeader();
        header.setBackground(Color.BLUE); // Set header background color
        header.setForeground(Color.WHITE); // Set header text color
        Font currentFont = header.getFont();
        Font newFont = currentFont.deriveFont(Font.BOLD, 18f);
        header.setFont(newFont);

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
                    String invoiceId = model.getValueAt(row, 0).toString();
                    double totalAmount = Double.parseDouble(model.getValueAt(row, 2).toString());
                    // Hiển thị cửa sổ nổi để chỉnh sửa thông tin
                    // Gọi phương thức showInvoiceDetailWindow để hiển thị thông tin chi tiết hóa đơn
                    List<InvoiceDetail> invoiceDetails = null;
					try {
						invoiceDetails = invoiceManager.searchInvoiceDetails(invoiceId);
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    showInvoiceDetailWindow(invoiceId, invoiceDetails, totalAmount);
                }
            }
        });
    }
    private void showInvoiceDetailWindow(String invoiceID, List<InvoiceDetail> invoiceDetails, double totalAmount) {
        // Tạo cửa sổ nổi
        JFrame detailFrame = new JFrame("Thông tin chi tiết hóa đơn");

        // Tạo panel để chứa thông tin chi tiết hóa đơn và nhãn hiển thị tổng số tiền
        JPanel detailPanel = new JPanel(new BorderLayout());

        // Tạo model cho bảng chi tiết hóa đơn
        DefaultTableModel detailModel = new DefaultTableModel();
        detailModel.addColumn("Product ID");
        detailModel.addColumn("Product Name");
        detailModel.addColumn("Promotion Rate");
        detailModel.addColumn("Price");
        detailModel.addColumn("Create Date");
        detailModel.addColumn("Quantity");

        // Đổ dữ liệu từ danh sách InvoiceDetail vào model của bảng chi tiết hóa đơn
        for (InvoiceDetail detail : invoiceDetails) {
            Object[] rowData = {detail.getProductID(), detail.getProductName(), detail.getPromotionRate(), detail.getPrice(), detail.getCreateDate(), detail.getQuantity()};
            detailModel.addRow(rowData);
        }

        // Tạo bảng chi tiết hóa đơn
        JTable detailTable = new JTable(detailModel);

        // Thêm bảng vào panel
        detailPanel.add(new JScrollPane(detailTable), BorderLayout.CENTER);

        // Tạo nhãn để hiển thị tổng số tiền đơn hàng
        JLabel totalAmountLabel = new JLabel("Tổng đơn: " + totalAmount);
        totalAmountLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        totalAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Thêm nhãn vào phía dưới bảng
        detailPanel.add(totalAmountLabel, BorderLayout.SOUTH);

        // Thêm panel vào cửa sổ chi tiết hóa đơn
        detailFrame.getContentPane().add(detailPanel);
        detailFrame.pack();
        detailFrame.setLocationRelativeTo(null); // Hiển thị cửa sổ ở giữa màn hình
        detailFrame.setVisible(true);
    }


}