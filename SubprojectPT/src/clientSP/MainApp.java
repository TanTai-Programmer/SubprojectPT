package clientSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApp {
    private static CardLayout cardLayout = new CardLayout();
    private static JPanel mainPanel = new JPanel(cardLayout);
    private static JMenuBar menuBar = new JMenuBar();
    private static JMenuItem currentMenuItem;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Manager App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1200, 800);

            menuBar.add(createMenuItem("Product Manager", "Panel 1"));
            menuBar.add(createMenuItem("Promotion Manager", "Panel 2"));
            menuBar.add(createMenuItem("Supplier Manager", "Panel 3"));
            menuBar.add(createMenuItem("Invoice Manager", "Panel 4"));
            frame.setJMenuBar(menuBar);

            ProductPanel panel1 = new ProductPanel();
            mainPanel.add(panel1, "Panel 1");

            PromotionPanel panel2 = new PromotionPanel();
            mainPanel.add(panel2, "Panel 2");

            frame.add(mainPanel, BorderLayout.CENTER);

            frame.setVisible(true);
        });
    }

    private static JMenuItem createMenuItem(String title, String panelName) {
        JMenuItem menuItem = new JMenuItem(title);
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, panelName);
                if (currentMenuItem != null) {
                    currentMenuItem.setForeground(Color.BLACK);
                }
                menuItem.setForeground(Color.RED);
                currentMenuItem = menuItem;
            }
        });
        return menuItem;
    }
}
