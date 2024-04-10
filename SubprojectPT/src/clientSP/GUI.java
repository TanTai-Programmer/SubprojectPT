package clientSP;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI {
    private JFrame frame;
    private JPanel cardPanel;
    private CardLayout cardLayout;

    public GUI() {
        frame = new JFrame("MultiPanel Menu Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Tạo các panel chức năng khác nhau
        JPanel panel1 = createPanel("Panel 1", "This is Panel 1 content.");
        JPanel panel2 = createPanel("Panel 2", "This is Panel 2 content.");
        JPanel panel3 = createPanel("Panel 3", "This is Panel 3 content.");

        // Thêm các panel vào cardPanel với các tên duy nhất
        cardPanel.add(panel1, "panel1");
        cardPanel.add(panel2, "panel2");
        cardPanel.add(panel3, "panel3");

        frame.add(cardPanel, BorderLayout.CENTER);

        // Tạo menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem menuItemPanel1 = new JMenuItem("Panel 1");
        menuItemPanel1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panel1");
            }
        });
        menu.add(menuItemPanel1);

        JMenuItem menuItemPanel2 = new JMenuItem("Panel 2");
        menuItemPanel2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panel2");
            }
        });
        menu.add(menuItemPanel2);

        JMenuItem menuItemPanel3 = new JMenuItem("Panel 3");
        menuItemPanel3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "panel3");
            }
        });
        menu.add(menuItemPanel3);

        menuBar.add(menu);
        frame.setJMenuBar(menuBar);

        frame.setVisible(true);
    }

    private JPanel createPanel(String title, String content) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        panel.add(titleLabel, BorderLayout.NORTH);

        JTextArea textArea = new JTextArea(content);
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}
