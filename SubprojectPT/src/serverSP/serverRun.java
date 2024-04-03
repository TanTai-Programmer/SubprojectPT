package serverSP;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class serverRun {
    public static void main(String[] args) {
        try {
            // Khởi tạo ProductManagerImpl
            ProductManagerImpl productManager = new ProductManagerImpl();

            // Đăng ký đối tượng phân tán
            LocateRegistry.createRegistry(1099);
            Naming.rebind("ProductManager", productManager);
            
            System.out.println("Server is ready.");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
