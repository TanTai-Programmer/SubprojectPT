package clientSP;
import javax.swing.*;
import java.awt.*;

public class PromotionPanel extends JPanel {
    public PromotionPanel() {
        setLayout(new GridLayout(5, 2, 5, 5));

        add(new JLabel("Product ID:"));
        add(new JTextField());

        add(new JLabel("Supplier ID:"));
        add(new JTextField());

        add(new JLabel("Promotion Rate:"));
        add(new JTextField());

        add(new JLabel("Start Date:"));
        add(new JTextField());

        add(new JLabel("End Date:"));
        add(new JTextField());
    }
}
