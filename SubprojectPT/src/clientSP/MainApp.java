package clientSP;

import javax.swing.*;
import java.awt.*;

import interfaceQLSP.interfaceProductManager;


public class MainApp extends JFrame {
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel mainPanel = new JPanel(cardLayout);
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenuItem currentMenuItem;

    private interfaceProductManager productManager;
    private ProductPanel productPanel;
    private PromotionPanel promotionPanel;
    // Thêm các biến tham chiếu đến các panel khác nếu cần

    public MainApp(interfaceProductManager productManager) {
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

        promotionPanel = new PromotionPanel();
        mainPanel.add(promotionPanel, "Panel 2");
        // Thêm các panel khác vào mainPanel nếu cần

        // Thêm mainPanel vào frame
        getContentPane().add(mainPanel, BorderLayout.CENTER);
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
