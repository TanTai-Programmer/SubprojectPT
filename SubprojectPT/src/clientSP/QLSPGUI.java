package clientSP;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class QLSPGUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTable table_1;
    private JTable table_2;
    private JTable table_3;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;
    private JTextField textField_10;
    private JTextField textField_11;
    private JButton updateButton_2;
    private JButton deleteButton_2;
    private JButton addButton_1;
    private JButton updateButton;
    private JButton deleteButton;
    private JButton addButton_2;
    private JLabel label_6;
    private JLabel label_7;
    private JLabel label_8;
    private JLabel label_9;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JLabel label_10;
    private JLabel label_11;
    private JLabel label_12;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_12;
    private JButton addButton_3;
    private JLabel label_13;
    private JTextField textField_13;
    private JLabel label_14;
    private JLabel label_15;
    private JLabel label_16;
    private JLabel label_17;
    private JTextField textField_14;
    private JTextField textField_15;
    private JTextField textField_16;
    private JTextField textField_17;
    private JButton btnNewButton;
    private JButton btnNewButton_1;
    private JButton btnNewButton_2;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

       
                    QLSPGUI frame = new QLSPGUI();
                    frame.setVisible(true);
                
    }

    /**
     * Create the frame.
     */
    public QLSPGUI() {
    	setTitle("QLSP");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 781, 490);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
        tabbedPane.setBounds(10, 11, 745, 430);
        contentPane.add(tabbedPane);

        // Panel quản lý sản phẩm
        JPanel productPanel = new JPanel();
        tabbedPane.addTab("Product Manager", null, productPanel, null);
        productPanel.setLayout(null);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 11, 340, 327);
        productPanel.add(scrollPane);

        table = new JTable();
        table.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "ProductId", "ProductName", "Price", "Quantity", "Description", "SupplierId"
                }
        ));
        scrollPane.setViewportView(table);

        JLabel label = new JLabel("Product ID:");
        label.setBounds(394, 11, 127, 20);
        productPanel.add(label);

        JLabel label_1 = new JLabel("Product Name:");
        label_1.setBounds(394, 42, 99, 20);
        productPanel.add(label_1);

        JLabel label_2 = new JLabel("Price:");
        label_2.setBounds(394, 73, 99, 20);
        productPanel.add(label_2);

        JLabel label_3 = new JLabel("Quantity:");
        label_3.setBounds(394, 93, 127, 20);
        productPanel.add(label_3);

        JLabel label_4 = new JLabel("Description:");
        label_4.setBounds(394, 124, 127, 20);
        productPanel.add(label_4);

        JLabel label_5 = new JLabel("Supplier ID:");
        label_5.setBounds(394, 155, 181, 20);
        productPanel.add(label_5);

        textField_6 = new JTextField();
        textField_6.setBounds(531, 11, 166, 20);
        productPanel.add(textField_6);
        textField_6.setColumns(10);

        textField_7 = new JTextField();
        textField_7.setColumns(10);
        textField_7.setBounds(531, 42, 166, 20);
        productPanel.add(textField_7);

        textField_8 = new JTextField();
        textField_8.setColumns(10);
        textField_8.setBounds(531, 73, 166, 20);
        productPanel.add(textField_8);

        textField_9 = new JTextField();
        textField_9.setColumns(10);
        textField_9.setBounds(531, 93, 166, 20);
        productPanel.add(textField_9);

        textField_10 = new JTextField();
        textField_10.setColumns(10);
        textField_10.setBounds(531, 124, 166, 20);
        productPanel.add(textField_10);

        textField_11 = new JTextField();
        textField_11.setColumns(10);
        textField_11.setBounds(531, 155, 166, 20);
        productPanel.add(textField_11);

        JButton addButton = new JButton("Add");
        addButton.setBounds(616, 368, 65, 23);
        productPanel.add(addButton);

        JButton updateButton_1 = new JButton("Update");
        updateButton_1.setBounds(61, 368, 65, 23);
        productPanel.add(updateButton_1);

        JButton deleteButton_1 = new JButton("Delete");
        deleteButton_1.setBounds(177, 368, 63, 23);
        productPanel.add(deleteButton_1);
        
        btnNewButton = new JButton("Sort");
        btnNewButton.setBounds(362, 315, 87, 23);
        productPanel.add(btnNewButton);

        // Panel quản lý nhà cung cấp
        JPanel supplierPanel = new JPanel();
        tabbedPane.addTab("Supplier Manager", null, supplierPanel, null);
        supplierPanel.setLayout(null);

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setBounds(10, 11, 424, 338);
        supplierPanel.add(scrollPane_1);

        table_1 = new JTable();
        table_1.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                    {null, null, null, null},
                },
                new String[] {
                    "SupplierId", "SupplierName", "Address", "PhoneNumber"
                }
        ));
        scrollPane_1.setViewportView(table_1);

        updateButton_2 = new JButton("Update");
        updateButton_2.setBounds(95, 368, 65, 23);
        supplierPanel.add(updateButton_2);

        deleteButton_2 = new JButton("Delete");
        deleteButton_2.setBounds(211, 368, 63, 23);
        supplierPanel.add(deleteButton_2);

        addButton_1 = new JButton("Add");
        addButton_1.setBounds(650, 368, 51, 23);
        supplierPanel.add(addButton_1);

        label_14 = new JLabel("Supplier ID:");
        label_14.setBounds(452, 21, 107, 20);
        supplierPanel.add(label_14);

        label_15 = new JLabel("Supplier Name:");
        label_15.setBounds(452, 52, 107, 20);
        supplierPanel.add(label_15);

        label_16 = new JLabel("Address:");
        label_16.setBounds(452, 83, 107, 20);
        supplierPanel.add(label_16);

        label_17 = new JLabel("Phone Number:");
        label_17.setBounds(452, 114, 107, 20);
        supplierPanel.add(label_17);

        textField_14 = new JTextField();
        textField_14.setBounds(569, 21, 132, 23);
        supplierPanel.add(textField_14);
        textField_14.setColumns(10);

        textField_15 = new JTextField();
        textField_15.setColumns(10);
        textField_15.setBounds(569, 52, 132, 23);
        supplierPanel.add(textField_15);

        textField_16 = new JTextField();
        textField_16.setColumns(10);
        textField_16.setBounds(569, 83, 132, 23);
        supplierPanel.add(textField_16);

        textField_17 = new JTextField();
        textField_17.setColumns(10);
        textField_17.setBounds(569, 114, 132, 23);
        supplierPanel.add(textField_17);
        
        btnNewButton_1 = new JButton("Sort");
        btnNewButton_1.setBounds(452, 326, 87, 23);
        supplierPanel.add(btnNewButton_1);

        // Panel quản lý khuyến mãi
        JPanel promotionPanel = new JPanel();
        tabbedPane.addTab("Promotion Manager", null, promotionPanel, null);
        promotionPanel.setLayout(null);

        JScrollPane scrollPane_3 = new JScrollPane();
        scrollPane_3.setBounds(10, 11, 398, 329);
        promotionPanel.add(scrollPane_3);

        table_3 = new JTable();
        table_3.setModel(new DefaultTableModel(
                new Object[][] {
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                    {null, null, null, null, null, null},
                },
                new String[] {
                    "ProductId", "SupplierId", "PromotionRate", "StartDate", "EndDate"
                }
        ));
        scrollPane_3.setViewportView(table_3);

        updateButton = new JButton("Update");
        updateButton.setBounds(70, 368, 106, 23);
        promotionPanel.add(updateButton);

        deleteButton = new JButton("Delete");
        deleteButton.setBounds(222, 368, 98, 23);
        promotionPanel.add(deleteButton);

        addButton_2 = new JButton("Add");
        addButton_2.setBounds(625, 368, 63, 23);
        promotionPanel.add(addButton_2);

        label_6 = new JLabel("Product ID:");
        label_6.setBounds(418, 11, 115, 20);
        promotionPanel.add(label_6);

        label_7 = new JLabel("Supplier ID:");
        label_7.setBounds(418, 46, 115, 20);
        promotionPanel.add(label_7);

        label_8 = new JLabel("Promotion Rate:");
        label_8.setBounds(418, 77, 115, 20);
        promotionPanel.add(label_8);

        label_9 = new JLabel("Start Date:");
        label_9.setBounds(418, 108, 115, 20);
        promotionPanel.add(label_9);

        textField = new JTextField();
        textField.setBounds(524, 7, 133, 23);
        promotionPanel.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setColumns(10);
        textField_1.setBounds(524, 76, 133, 23);
        promotionPanel.add(textField_1);

        textField_2 = new JTextField();
        textField_2.setColumns(10);
        textField_2.setBounds(524, 42, 133, 23);
        promotionPanel.add(textField_2);

        textField_3 = new JTextField();
        textField_3.setColumns(10);
        textField_3.setBounds(524, 107, 133, 23);
        promotionPanel.add(textField_3);

        label_13 = new JLabel("End Date:");
        label_13.setBounds(418, 140, 115, 20);
        promotionPanel.add(label_13);

        textField_13 = new JTextField();
        textField_13.setColumns(10);
        textField_13.setBounds(524, 139, 133, 23);
        promotionPanel.add(textField_13);
        
        btnNewButton_2 = new JButton("Sort");
        btnNewButton_2.setBounds(421, 317, 87, 23);
        promotionPanel.add(btnNewButton_2);
        
                // Panel quản lý hóa đơn
                JPanel invoicePanel = new JPanel();
                tabbedPane.addTab("Invoice Manager", null, invoicePanel, null);
                invoicePanel.setLayout(null);
                
                        JScrollPane scrollPane_2 = new JScrollPane();
                        scrollPane_2.setBounds(10, 11, 426, 330);
                        invoicePanel.add(scrollPane_2);
                        
                                table_2 = new JTable();
                                table_2.setModel(new DefaultTableModel(
                                        new Object[][] {
                                            {null, null, null},
                                            {null, null, null},
                                            {null, null, null},
                                            {null, null, null},
                                        },
                                        new String[] {
                                            "InvoiceId", "CreateDate", "TotalMount"
                                        }
                                ));
                                scrollPane_2.setViewportView(table_2);
                                
                                        label_10 = new JLabel("Invoice ID:");
                                        label_10.setBounds(464, 11, 119, 20);
                                        invoicePanel.add(label_10);
                                        
                                                label_11 = new JLabel("Create Date:");
                                                label_11.setBounds(464, 42, 101, 20);
                                                invoicePanel.add(label_11);
                                                
                                                        label_12 = new JLabel("Total Amount:");
                                                        label_12.setBounds(464, 73, 109, 20);
                                                        invoicePanel.add(label_12);
                                                        
                                                                textField_4 = new JTextField();
                                                                textField_4.setBounds(593, 11, 125, 20);
                                                                invoicePanel.add(textField_4);
                                                                textField_4.setColumns(10);
                                                                
                                                                        textField_5 = new JTextField();
                                                                        textField_5.setColumns(10);
                                                                        textField_5.setBounds(593, 42, 125, 20);
                                                                        invoicePanel.add(textField_5);
                                                                        
                                                                                textField_12 = new JTextField();
                                                                                textField_12.setColumns(10);
                                                                                textField_12.setBounds(593, 73, 125, 20);
                                                                                invoicePanel.add(textField_12);
                                                                                
                                                                                        addButton_3 = new JButton("Add");
                                                                                        addButton_3.setBounds(587, 368, 90, 23);
                                                                                        invoicePanel.add(addButton_3);
                                                                                        
                                                                                        JButton addButton_3_1 = new JButton("Detail");
                                                                                        addButton_3_1.setBounds(143, 368, 90, 23);
                                                                                        invoicePanel.add(addButton_3_1);
                                                                                        
                                                                                        JComboBox comboBox = new JComboBox();
                                                                                        comboBox.setModel(new DefaultComboBoxModel(new String[] {"Sort(day)", "Sort(price)"}));
                                                                                        comboBox.setBounds(464, 319, 101, 22);
                                                                                        invoicePanel.add(comboBox);
    }
}
