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
            frame.setSize(800, 600);

            menuBar.add(createMenuItem("Product Manager", "Panel 1"));
            menuBar.add(createMenuItem("Promotion Manager", "Panel 2"));
            menuBar.add(createMenuItem("Supplier Manager", "Panel 3"));
            menuBar.add(createMenuItem("Invoice Manager", "Panel 4"));
            frame.setJMenuBar(menuBar);

            mainPanel.add(createProductManagerPanel(), "Panel 1");
            mainPanel.add(createPanel("Panel 2"), "Panel 2");
            mainPanel.add(createPanel("Panel 3"), "Panel 3");
            mainPanel.add(createPanel("Panel 4"), "Panel 4");
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
                    currentMenuItem.setBorder(null);
                }
                menuItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED));
                currentMenuItem = menuItem;
            }
        });
        return menuItem;
    }

    private static JPanel createPanel(String title) {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createTitledBorder(title));
        return panel;
    }

    private static JPanel createProductManagerPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JPanel dataEntryPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.gridx = 0;
        c.gridy = 0;
        dataEntryPanel.add(new JLabel("Product ID:"), c);
        c.gridx = 1;
        dataEntryPanel.add(new JTextField(10), c);
        c.gridx = 0;
        c.gridy = 1;
        dataEntryPanel.add(new JLabel("Product Name:"), c);
        c.gridx = 1;
        dataEntryPanel.add(new JTextField(10), c);
        c.gridx = 0;
        c.gridy = 2;
        dataEntryPanel.add(new JLabel("Price:"), c);
        c.gridx = 1;
        dataEntryPanel.add(new JTextField(10), c);
        c.gridx = 0;
        c.gridy = 3;
        dataEntryPanel.add(new JLabel("Quantity:"), c);
        c.gridx = 1;
        dataEntryPanel.add(new JTextField(10), c);
        c.gridx = 0;
        c.gridy = 4;
        dataEntryPanel.add(new JLabel("Description:"), c);
        c.gridx = 1;
        dataEntryPanel.add(new JTextField(10), c);
        c.gridx = 0;
        c.gridy = 5;
        dataEntryPanel.add(new JLabel("Supplier ID:"), c);
        c.gridx = 1;
        dataEntryPanel.add(new JTextField(10), c);
        c.gridx = 1;
        c.gridy = 6;
        dataEntryPanel.add(new JButton("Add"), c);

        JPanel dataTablePanel = new JPanel(new BorderLayout());
        dataTablePanel.add(new JTextField(), BorderLayout.NORTH);
        dataTablePanel.add(new JTable(new Object[][]{}, new Object[]{"Product ID", "Product Name", "Price", "Quantity", "Description", "Supplier ID"}), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Delete"));
        dataTablePanel.add(buttonPanel, BorderLayout.SOUTH);

        panel.add(dataEntryPanel, BorderLayout.EAST);
        panel.add(dataTablePanel, BorderLayout.CENTER);

        panel.setBorder(BorderFactory.createTitledBorder("Product Manager"));

        return panel;
    }
}

