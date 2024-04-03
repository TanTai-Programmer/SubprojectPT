package clientSP;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

import interfaceQLSP.interfaceProductManager;
import objectQLSP.Invoice;
import objectQLSP.Product;
import objectQLSP.Promotion;
import objectQLSP.SupplierSP;

public class clientRun {

    public static void main(String[] args) {
        try {
            // Kết nối đến registry trên máy chủ
            Registry registry = LocateRegistry.getRegistry("localhost");
            
            // Lấy ra đối tượng từ xa từ registry
            interfaceProductManager productManager = (interfaceProductManager) registry.lookup("ProductManager");

            // Hiển thị menu cho người dùng
            Scanner scanner = new Scanner(System.in);
            int choice;
            do {
                System.out.println("Menu:");
                System.out.println("1. Xem danh sách sản phẩm");
                System.out.println("2. Xem danh sách hóa đơn");
                System.out.println("3. Xem danh sách khuyến mãi");
                System.out.println("4. Xem danh sách nhà cung cấp");
                System.out.println("0. Thoát");
                System.out.print("Nhập lựa chọn của bạn: ");
                choice = scanner.nextInt();
                
                // Xử lý lựa chọn của người dùng
                switch (choice) {
                    case 1:
                        displayProducts(productManager);
                        break;
                    case 2:
                        displayInvoices(productManager);
                        break;
                    case 3:
                        displayPromotions(productManager);
                        break;
                    case 4:
                        displaySuppliers(productManager);
                        break;
                    case 0:
                        System.out.println("Đã thoát");
                        break;
                    default:
                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                        break;
                }
            } while (choice != 0);

            scanner.close();

        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }

    // Hiển thị danh sách sản phẩm
    private static void displayProducts(interfaceProductManager productManager) {
        try {
            List<Product> products = productManager.getProducts();
            System.out.println("Danh sách sản phẩm:");
            for (Product product : products) {
                System.out.println(product);
            }
        } catch (RemoteException e) {
            System.err.println("Error while getting products: " + e.getMessage());
        }
    }

    // Hiển thị danh sách hóa đơn
    private static void displayInvoices(interfaceProductManager productManager) {
        try {
            List<Invoice> invoices = productManager.getInvoices();
            System.out.println("Danh sách hóa đơn:");
            for (Invoice invoice : invoices) {
                System.out.println(invoice);
            }
        } catch (RemoteException e) {
            System.err.println("Error while getting invoices: " + e.getMessage());
        }
    }

    // Hiển thị danh sách khuyến mãi
    private static void displayPromotions(interfaceProductManager productManager) {
        try {
            List<Promotion> promotions = productManager.getPromotions();
            System.out.println("Danh sách khuyến mãi:");
            for (Promotion promotion : promotions) {
                System.out.println(promotion);
            }
        } catch (RemoteException e) {
            System.err.println("Error while getting promotions: " + e.getMessage());
        }
    }

    // Hiển thị danh sách nhà cung cấp
    private static void displaySuppliers(interfaceProductManager productManager) {
        try {
            List<SupplierSP> suppliers = productManager.getSuppliers();
            System.out.println("Danh sách nhà cung cấp:");
            for (SupplierSP supplier : suppliers) {
                System.out.println(supplier);
            }
        } catch (RemoteException e) {
            System.err.println("Error while getting suppliers: " + e.getMessage());
        }
    }
}
