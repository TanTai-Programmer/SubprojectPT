package clientSP;

import javax.swing.*;
import java.awt.*;

import interfaceQLSP.InterfaceProductManager;


public class MainApp extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static CardLayout cardLayout = new CardLayout();
    private static JPanel mainPanel = new JPanel(cardLayout);
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenuItem currentMenuItem;

    private InterfaceProductManager productManager;
    private ProductPanel productPanel;
    private PromotionPanel promotionPanel;
    private SupplierPanel supplierPanel;
    private InvoicePanel invoicePanel;
    // Thêm các biến tham chiếu đến các panel khác nếu cần

    public MainApp(InterfaceProductManager productManager) {
        this.productManager = productManager;
        initializeUI();
    }

    private void initializeUI() {
        setTitle("Manager App");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1300, 800);

        // Thêm các menu item khác vào menu bar
        menuBar.add(createMenuItem("Product Manager", "Panel 1"));
        menuBar.add(createMenuItem("Promotion Manager", "Panel 2"));
        menuBar.add(createMenuItem("Supplier Manager", "Panel 3"));
        menuBar.add(createMenuItem("Invoice Manager", "Panel 4"));
        // Thêm menu bar vào frame
        setJMenuBar(menuBar);

        // Tạo các panel và thêm vào mainPanel
        productPanel = new ProductPanel(productManager); // Khởi tạo productPanel trước khi sử dụng
        mainPanel.add(productPanel, "Panel 1");

        promotionPanel = new PromotionPanel(productManager);
        mainPanel.add(promotionPanel, "Panel 2");
        // Thêm các panel khác vào mainPanel nếu cần
        supplierPanel = new SupplierPanel(productManager);
        mainPanel.add(supplierPanel, "Panel 3");
        
        invoicePanel = new InvoicePanel(productManager);
        mainPanel.add(invoicePanel, "Panel 4");
        // Thêm mainPanel vào frame
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        setLocationRelativeTo(null);
    }


    // Phương thức tạo menu item
    private JMenuItem createMenuItem(String title, String panelName) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(e -> {
            cardLayout.show(mainPanel, panelName);
            if (currentMenuItem != null) {
                currentMenuItem.setForeground(Color.BLACK);
            }
            menuItem.setForeground(Color.RED);
            currentMenuItem = menuItem;
        });
        return menuItem;
    }
}
