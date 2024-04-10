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


//Này là để chạy trên 2 máy 1 máy thật 1 máy ảo chạy vmnet 8 cấp ip động

//package serverSP;
//
//import java.rmi.Naming;
//import java.rmi.registry.LocateRegistry;
//
//public class serverRun {
//    public static void main(String[] args) {
//        try {
//            // Khởi tạo ProductManagerImpl
//            ProductManagerImpl productManager = new ProductManagerImpl();
//
//            // Đăng ký đối tượng phân tán với địa chỉ IP cụ thể
//            String serverIP = "192.168.145.1"; // Địa chỉ IP của máy chủ
//            String serviceName = "ProductManager"; // Tên dịch vụ
//            String registryURL = "rmi://" + serverIP + "/" + serviceName;
//
//            LocateRegistry.createRegistry(1099);
//            Naming.rebind(registryURL, productManager);
//            
//            System.out.println("Server is ready at: " + registryURL);
//        } catch (Exception e) {
//            System.err.println("Server exception: " + e.toString());
//            e.printStackTrace();
//        }
//    }
//}