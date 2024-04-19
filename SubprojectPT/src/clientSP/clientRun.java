package clientSP;

import javax.swing.*;

import interfaceQLSP.interfaceProductManager;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class clientRun extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
        try {
            // Kết nối đến registry trên máy chủ
            Registry registry = LocateRegistry.getRegistry("localhost");

            // Lấy ra đối tượng từ xa từ registry
            interfaceProductManager productManager = (interfaceProductManager) registry.lookup("ProductManager");

            // Tạo một instance của MainApp và truyền interfaceProductManager vào đó
            MainApp mainApp = new MainApp(productManager);
            mainApp.setVisible(true);

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
