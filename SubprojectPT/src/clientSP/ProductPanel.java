package clientSP;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.ActionListener;
//import java.awt.event.ActionEvent;
//
//import javax.swing.border.EmptyBorder;
//
//public class ProductPanel extends JPanel {
//    /**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//
//	public ProductPanel() {
//        setLayout(new BorderLayout());
//
//        // Phần bên trái
//        JPanel leftPanel = new JPanel();
//        leftPanel.setBackground(new Color(128, 255, 255));
//        leftPanel.setLayout(new GridLayout(0, 1, 0, 0));
//        
//        // Thanh tìm kiếm và nút tìm kiếm
//        JTextField searchField = new JTextField();
//        searchField.setForeground(new Color(192, 192, 192));
//        searchField.setText("Nhập vào ID sản phẩm để tìm kiếm ... ");
//        searchField.setColumns(40);
//        searchField.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        JButton searchButton = new JButton("Search");
//        searchButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
//        
//        JPanel searchPanel = new JPanel();
//        searchPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
//        searchPanel.add(searchField);
//        searchPanel.add(searchButton);
//        
//        leftPanel.add(searchPanel);
//        
//        // Bảng hiển thị dữ liệu
//        JTable table = new JTable();
//        JScrollPane scrollPane = new JScrollPane(table);
//        
//        leftPanel.add(scrollPane);
//        
//        // Nút Update và Delete
//        JButton updateButton = new JButton("Update");
//        updateButton.setFont(new Font("Tahoma", Font.BOLD, 16));
//        updateButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Đặt khoảng đệm
//        updateButton.addActionListener(new ActionListener() {
//        	public void actionPerformed(ActionEvent e) {
//        	}
//        });
//        JButton deleteButton = new JButton("Delete");
//        deleteButton.setFont(new Font("Tahoma", Font.BOLD, 16));
//        deleteButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Đặt khoảng đệm
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
//        
//        buttonPanel.add(updateButton);
//        buttonPanel.add(deleteButton);
//        
//        leftPanel.add(buttonPanel);
//
//        // Phần bên phải
//        JPanel rightPanel = new JPanel();
//        rightPanel.setLayout(new BorderLayout());
//        
//        JLabel titleLabel = new JLabel("Nhập thông tin sản phẩm");
//        titleLabel.setForeground(new Color(255, 0, 0));
//        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
//        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
//        
//        rightPanel.add(titleLabel, BorderLayout.NORTH);
//        
//        JPanel inputPanel = new JPanel();
//        inputPanel.setBackground(new Color(255, 210, 252));
//        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
//        GridBagLayout gbl_inputPanel = new GridBagLayout();
//        gbl_inputPanel.columnWidths = new int[] {500, 0};
//        gbl_inputPanel.rowHeights = new int[] {27, 27, 27, 27, 27, 27, 27, 27, 27, 76, 27, 27, 0};
//        gbl_inputPanel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
//        gbl_inputPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
//        inputPanel.setLayout(gbl_inputPanel);
//        
//        JLabel label = new JLabel("Product ID:");
//        label.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        GridBagConstraints gbc_label = new GridBagConstraints();
//        gbc_label.fill = GridBagConstraints.BOTH;
//        gbc_label.insets = new Insets(0, 0, 5, 0);
//        gbc_label.gridx = 0;
//        gbc_label.gridy = 0;
//        inputPanel.add(label, gbc_label);
//        JTextField textField = new JTextField();
//        GridBagConstraints gbc_textField = new GridBagConstraints();
//        gbc_textField.fill = GridBagConstraints.BOTH;
//        gbc_textField.insets = new Insets(0, 0, 5, 0);
//        gbc_textField.gridx = 0;
//        gbc_textField.gridy = 1;
//        inputPanel.add(textField, gbc_textField);
//        JLabel label_1 = new JLabel("Product Name:");
//        label_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        GridBagConstraints gbc_label_1 = new GridBagConstraints();
//        gbc_label_1.fill = GridBagConstraints.BOTH;
//        gbc_label_1.insets = new Insets(0, 0, 5, 0);
//        gbc_label_1.gridx = 0;
//        gbc_label_1.gridy = 2;
//        inputPanel.add(label_1, gbc_label_1);
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.insets = new Insets(0, 0, 5, 0);
//        gbc.gridx = 0;
//        gbc.gridy = 3;
//        JTextField textField_2 = new JTextField();
//        inputPanel.add(textField_2, gbc);
//        JLabel label_2 = new JLabel("Price:");
//        label_2.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        GridBagConstraints gbc_label_2 = new GridBagConstraints();
//        gbc_label_2.fill = GridBagConstraints.BOTH;
//        gbc_label_2.insets = new Insets(0, 0, 5, 0);
//        gbc_label_2.gridx = 0;
//        gbc_label_2.gridy = 4;
//        inputPanel.add(label_2, gbc_label_2);
//        GridBagConstraints gbc_1 = new GridBagConstraints();
//        gbc_1.fill = GridBagConstraints.BOTH;
//        gbc_1.insets = new Insets(0, 0, 5, 0);
//        gbc_1.gridx = 0;
//        gbc_1.gridy = 5;
//        JTextField textField_1 = new JTextField();
//        inputPanel.add(textField_1, gbc_1);
//        JLabel label_3 = new JLabel("Quantity:");
//        label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        GridBagConstraints gbc_label_3 = new GridBagConstraints();
//        gbc_label_3.fill = GridBagConstraints.BOTH;
//        gbc_label_3.insets = new Insets(0, 0, 5, 0);
//        gbc_label_3.gridx = 0;
//        gbc_label_3.gridy = 6;
//        inputPanel.add(label_3, gbc_label_3);
//        GridBagConstraints gbc_2 = new GridBagConstraints();
//        gbc_2.fill = GridBagConstraints.BOTH;
//        gbc_2.insets = new Insets(0, 0, 5, 0);
//        gbc_2.gridx = 0;
//        gbc_2.gridy = 7;
//        JTextField textField_4 = new JTextField();
//        inputPanel.add(textField_4, gbc_2);
//        JLabel label_4 = new JLabel("Description:");
//        label_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        
//                GridBagConstraints gbc_label_4 = new GridBagConstraints();
//                gbc_label_4.fill = GridBagConstraints.BOTH;
//                gbc_label_4.insets = new Insets(0, 0, 5, 0);
//                gbc_label_4.gridx = 0;
//                gbc_label_4.gridy = 8;
//                inputPanel.add(label_4, gbc_label_4);
//        GridBagConstraints gbc_3 = new GridBagConstraints();
//        gbc_3.fill = GridBagConstraints.BOTH;
//        gbc_3.insets = new Insets(0, 0, 5, 0);
//        gbc_3.gridx = 0;
//        gbc_3.gridy = 9;
//        JTextField textField_5 = new JTextField();
//        inputPanel.add(textField_5, gbc_3);
//        JLabel label_5 = new JLabel("Supplier ID:");
//        label_5.setFont(new Font("Tahoma", Font.PLAIN, 14));
//        GridBagConstraints gbc_label_5 = new GridBagConstraints();
//        gbc_label_5.fill = GridBagConstraints.BOTH;
//        gbc_label_5.insets = new Insets(0, 0, 5, 0);
//        gbc_label_5.gridx = 0;
//        gbc_label_5.gridy = 10;
//        inputPanel.add(label_5, gbc_label_5);
//        GridBagConstraints gbc_4 = new GridBagConstraints();
//        gbc_4.fill = GridBagConstraints.BOTH;
//        gbc_4.gridx = 0;
//        gbc_4.gridy = 11;
//        JTextField textField_3 = new JTextField();
//        inputPanel.add(textField_3, gbc_4);
//        
//        rightPanel.add(inputPanel, BorderLayout.CENTER);
//        
//        JButton addButton = new JButton("Add");
//        addButton.setFont(new Font("Tahoma", Font.BOLD, 16));
//        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Đặt khoảng đệm
//        rightPanel.add(addButton, BorderLayout.SOUTH);
//        
//        // Thêm hai panel con vào panel chính
//        add(leftPanel, BorderLayout.WEST);
//        add(rightPanel, BorderLayout.CENTER);
//    }
//}
import javax.swing.*;
import java.awt.*;

class ProductPanel extends JPanel {
    public ProductPanel() {
        setLayout(new GridBagLayout());

        // Phần bên trái (70%)
        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(Color.CYAN); // Màu nền

        GridBagConstraints gbcLeftPanel = new GridBagConstraints();
        gbcLeftPanel.gridx = 0;
        gbcLeftPanel.gridy = 0;
        gbcLeftPanel.weightx = 0.7; // Tỷ lệ 7:3
        gbcLeftPanel.fill = GridBagConstraints.BOTH;
        add(leftPanel, gbcLeftPanel);

        // Phần bên phải (30%)
        JPanel rightPanel = new JPanel();
        rightPanel.setBackground(Color.PINK); // Màu nền

        GridBagConstraints gbcRightPanel = new GridBagConstraints();
        gbcRightPanel.gridx = 1;
        gbcRightPanel.gridy = 0;
        gbcRightPanel.weightx = 0.3; // Tỷ lệ 7:3
        gbcRightPanel.fill = GridBagConstraints.BOTH;
        add(rightPanel, gbcRightPanel);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(800, 600); // Kích thước mặc định
    }
}