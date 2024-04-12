package clientSP;
//
//import java.rmi.RemoteException;
//import java.rmi.registry.LocateRegistry;
//import java.rmi.registry.Registry;
//import java.sql.Date;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import javax.swing.JFrame;
//
//import interfaceQLSP.interfaceProductManager;
//import objectQLSP.InvoiceDetail;
//import objectQLSP.Product;
//import objectQLSP.Promotion;
//import objectQLSP.SupplierSP;
//
//
//public class clientRun extends JFrame{
//	/**
//	 * 
//	 */
//	private static final long serialVersionUID = 1L;
//	
//    public static void main(String[] args) {
//        try {
//            // Kết nối đến registry trên máy chủ
//            Registry registry = LocateRegistry.getRegistry("localhost");
//            
//            //Kết nối đến máy ảo hoặc IP máy chủ đăng ký 
//            //Registry registry = LocateRegistry.getRegistry("192.168.145.1", 1099);
//            
//            // Lấy ra đối tượng từ xa từ registry
//            interfaceProductManager productManager = (interfaceProductManager) registry.lookup("ProductManager");
//            MainApp mainApp = new MainApp(productManager); // Tạo đối tượng MainApp
//            mainApp.run(); // Hiển thị giao diện
//            Scanner scanner = new Scanner(System.in);
//            int choice;
//            do {
//                System.out.println("Menu:");
//                System.out.println("1. Xem danh sách sản phẩm");
//                System.out.println("2. Xem danh sách chi tiết hóa đơn");
//                System.out.println("3. Xem danh sách khuyến mãi");
//                System.out.println("4. Xem danh sách nhà cung cấp");
//                System.out.println("5. Thêm sản phẩm");
//                System.out.println("6. Cập nhật sản phẩm");
//                System.out.println("7. Xóa sản phẩm");
//                System.out.println("8. Tìm kiếm sản phẩm");
//                System.out.println("9. Thêm khuyến mãi");
//                System.out.println("10. Cập nhật khuyến mãi");
//                System.out.println("11. Xóa khuyến mãi");
//                System.out.println("12. Tìm kiếm khuyến mãi");
//                System.out.println("14. Thêm nhà cung cấp");
//                System.out.println("15. Cập nhật nhà cung cấp");
//                System.out.println("16. Xóa nhà cung cấp");
//                System.out.println("17. Tìm kiếm hóa đơn");
//                System.out.println("18. Tạo hóa đơn");
//                System.out.println("19. Xem danh sách sản phẩm sắp xếp theo số lượng tăng dần");
//                System.out.println("20. Xem danh sách sản phẩm sắp xếp theo giá tăng dần");
//                System.out.println("21. Xem danh sách sản phẩm sắp xếp theo số lượng giảm dần");
//                System.out.println("22. Xem danh sách sản phẩm sắp xếp theo giá giảm dần");
//                System.out.println("0. Thoát");
//                
//                System.out.print("Nhập lựa chọn của bạn: ");
//                choice = scanner.nextInt();
//
//                // Xử lý lựa chọn của người dùng
//                switch (choice) {
//                    case 1:
//                        displayProducts(productManager);
//                        break;
//                    case 2:
//                    	displayInvoiceDetails(productManager);
//                        break;
//                    case 3:
//                        displayPromotions(productManager);
//                        break;
//                    case 4:
//                        displaySuppliers(productManager);
//                        break;
//                    case 5:
//                        addProduct(scanner, productManager);
//                        break;
//                    case 6:
//                        updateProduct(scanner, productManager);
//                        break;
//                    case 7:
//                        deleteProduct(scanner, productManager);
//                        break;
//                    case 8:
//                        searchProduct(scanner, productManager);
//                        break;
//                    case 9:
//                        addPromotion(scanner, productManager);
//                        break;
//                    case 10:
//                        updatePromotion(scanner, productManager);
//                        break;
//                    case 11:
//                        deletePromotion(scanner, productManager);
//                        break;
//                    case 12:
//                        searchPromotion(scanner, productManager);
//                        break;
//                    case 14:
//                        addSupplier(scanner, productManager);
//                        break;
//                    case 15:
//                        updateSupplier(scanner, productManager);
//                        break;
//                    case 16:
//                        deleteSupplier(scanner, productManager);
//                        break;
//                    case 17:
//                    	searchInvoiceDetail(scanner, productManager);
//                        break;
//                    case 18:
//                        createInvoice(scanner, productManager); // Thêm tùy chọn tạo hóa đơn
//                        break;
//                    case 19:
//                        displayProductsSortQuantityASC(productManager);
//                        break;
//                    case 20:
//                        displayProductsSortPriceASC(productManager);
//                        break;
//                    case 21:
//                        displayProductsSortQuantityDESC(productManager);
//                        break;
//                    case 22:
//                        displayProductsSortPriceDESC(productManager);
//                        break;
//                    case 0:
//                        System.out.println("Đã thoát");
//                        break;
//                    default:
//                        System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
//                        break;
//                }
//            } while (choice != 0);
//
//        } catch (Exception e) {
//            System.err.println("Client exception: " + e.toString());
//            e.printStackTrace();
//        }
//    }
//
//    // Hiển thị danh sách sản phẩm
//    private static void displayProducts(interfaceProductManager productManager) {
//        try {
//            List<Product> products = productManager.getProducts();
//            System.out.println("Danh sách sản phẩm:");
//            for (Product product : products) {
//                System.out.println(product);
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while getting products: " + e.getMessage());
//        }
//    }
//
//    // Hiển thị danh sách hóa đơn
// // Hiển thị danh sách hóa đơn
//    private static void displayInvoiceDetails(interfaceProductManager productManager) {
//        try {
//            List<InvoiceDetail> invoiceDetails = productManager.getInvoiceDetails();
//            System.out.println("Danh sách chi tiết hóa đơn:");
//            for (InvoiceDetail invoiceDetail : invoiceDetails) {
//                System.out.println(invoiceDetail);
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while getting invoice details: " + e.getMessage());
//        }
//    }
//
//    // Hiển thị danh sách khuyến mãi
//    private static void displayPromotions(interfaceProductManager productManager) {
//        try {
//            List<Promotion> promotions = productManager.getPromotions();
//            System.out.println("Danh sách khuyến mãi:");
//            for (Promotion promotion : promotions) {
//                System.out.println(promotion);
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while getting promotions: " + e.getMessage());
//        }
//    }
//
//    // Hiển thị danh sách nhà cung cấp
//    private static void displaySuppliers(interfaceProductManager productManager) {
//        try {
//            List<SupplierSP> suppliers = productManager.getSuppliers();
//            System.out.println("Danh sách nhà cung cấp:");
//            for (SupplierSP supplier : suppliers) {
//                System.out.println(supplier);
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while getting suppliers: " + e.getMessage());
//        }
//    }
// // Thêm sản phẩm
//    private static void addProduct(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.println("Nhập thông tin sản phẩm mới:");
//            System.out.print("ID sản phẩm: ");
//            String productID = scanner.next();
//            scanner.nextLine(); // Xóa bất kỳ ký tự newline còn lại trong bộ đệm
//            System.out.print("Tên sản phẩm: ");
//            String productName = scanner.nextLine(); // Sử dụng nextLine() để đọc dữ liệu kiểu String
//            System.out.print("Giá: ");
//            double price = scanner.nextDouble();
//            System.out.print("Số lượng: ");
//            int quantity = scanner.nextInt();
//            scanner.nextLine(); // Xóa bất kỳ ký tự newline còn lại trong bộ đệm
//            System.out.print("Mô tả: ");
//            String description = scanner.nextLine(); // Sử dụng nextLine() để đọc dữ liệu kiểu String
//            System.out.print("ID nhà cung cấp: ");
//            String supplierID = scanner.next();
//
//            Product newProduct = new Product(productID, productName, price, quantity, description, supplierID);
//            productManager.addProduct(newProduct);
//            System.out.println("Sản phẩm đã được thêm thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while adding product: " + e.getMessage());
//        }
//    }
//
// // Xóa sản phẩm
//    private static void deleteProduct(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.print("Nhập ID sản phẩm cần xóa: ");
//            String productID = scanner.next();
//            productManager.deleteProduct(productID);
//            System.out.println("Sản phẩm có ID " + productID + " đã được xóa thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while deleting product: " + e.getMessage());
//        }
//    }
//
// // Cập nhật thông tin sản phẩm
//    private static void updateProduct(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.print("Nhập ID sản phẩm cần cập nhật: ");
//            String productID = scanner.next();
//            System.out.println("Nhập thông tin mới cho sản phẩm:");
//            System.out.print("Tên sản phẩm: ");
//            String productName = scanner.next();
//            System.out.print("Giá: ");
//            double price = scanner.nextDouble();
//            System.out.print("Số lượng: ");
//            int quantity = scanner.nextInt();
//            System.out.print("Mô tả: ");
//            String description = scanner.next();
//            System.out.print("ID nhà cung cấp: ");
//            String supplierID = scanner.next();
//
//            Product updatedProduct = new Product(productID, productName, price, quantity, description, supplierID);
//            productManager.updateProduct(updatedProduct);
//            System.out.println("Sản phẩm có ID " + productID + " đã được cập nhật thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while updating product: " + e.getMessage());
//        }
//    }
//    // Tìm kiếm sản phẩm
//    private static void searchProduct(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.print("Nhập từ khóa tìm kiếm sản phẩm: ");
//            String keyword = scanner.next();
//            List<Product> searchResult = productManager.searchProducts(keyword);
//            if (searchResult.isEmpty()) {
//                System.out.println("Không tìm thấy sản phẩm nào phù hợp.");
//            } else {
//                System.out.println("Kết quả tìm kiếm:");
//                for (Product product : searchResult) {
//                    System.out.println(product);
//                }
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while searching product: " + e.getMessage());
//        }
//    }
//    
// // Thêm khuyến mãi
//    private static void addPromotion(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.println("Nhập thông tin khuyến mãi mới:");
//            // Không cần nhập ID khuyến mãi nếu đã xóa cột này khỏi cơ sở dữ liệu
//            //int promotionID = scanner.nextInt(); 
//
//            System.out.print("ID sản phẩm: ");
//            String productID = scanner.next();
//            System.out.print("ID nhà cung cấp: ");
//            String supplierID = scanner.next();
//            System.out.print("Tỷ lệ khuyến mãi: ");
//            double promotionRate = scanner.nextDouble();
//            System.out.print("Ngày bắt đầu (YYYY-MM-DD): ");
//            String startDateStr = scanner.next();
//            Date startDate = Date.valueOf(startDateStr);
//            System.out.print("Ngày kết thúc (YYYY-MM-DD): ");
//            String endDateStr = scanner.next();
//            Date endDate = Date.valueOf(endDateStr);
//
//            // Promotion ID không cần thiết nếu đã xóa cột này khỏi CSDL
//            //Promotion newPromotion = new Promotion(promotionID, productID, supplierID, promotionRate, startDate, endDate);
//            Promotion newPromotion = new Promotion(productID, supplierID, promotionRate, startDate, endDate);
//            productManager.addPromotion(newPromotion);
//            System.out.println("Khuyến mãi đã được thêm thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while adding promotion: " + e.getMessage());
//        } catch (Exception e) {
//            System.err.println("Error: Invalid input format or data. Please try again.");
//            scanner.nextLine(); // Clear the buffer
//        }
//    }
// // Cập nhật khuyến mãi
//    private static void updatePromotion(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.println("Nhập thông tin mới cho khuyến mãi:");
//            System.out.print("ID sản phẩm cần cập nhật: ");
//            String productID = scanner.next();
//            System.out.print("ID nhà cung cấp mới: ");
//            String newSupplierID = scanner.next();
//            System.out.print("Tỷ lệ khuyến mãi mới: ");
//            double newPromotionRate = scanner.nextDouble();
//            System.out.print("Ngày bắt đầu mới (YYYY-MM-DD): ");
//            String newStartDateStr = scanner.next();
//            Date newStartDate = Date.valueOf(newStartDateStr);
//            System.out.print("Ngày kết thúc mới (YYYY-MM-DD): ");
//            String newEndDateStr = scanner.next();
//            Date newEndDate = Date.valueOf(newEndDateStr);
//
//            Promotion updatedPromotion = new Promotion(productID, newSupplierID, newPromotionRate, newStartDate, newEndDate);
//            productManager.updatePromotion(updatedPromotion);
//            System.out.println("Khuyến mãi cho sản phẩm có ID " + productID + " đã được cập nhật thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while updating promotion: " + e.getMessage());
//        } catch (Exception e) {
//            System.err.println("Error: Invalid input format or data. Please try again.");
//            scanner.nextLine(); // Clear the buffer
//        }
//    }
// // Xóa khuyến mãi
//    private static void deletePromotion(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.print("Nhập ID sản phẩm cần xóa khuyến mãi: ");
//            String productID = scanner.next();
//            System.out.print("Nhập ID nhà cung cấp của sản phẩm: ");
//            String supplierID = scanner.next();
//            productManager.deletePromotion(productID, supplierID);
//            System.out.println("Khuyến mãi cho sản phẩm có ID " + productID + " của nhà cung cấp " + supplierID + " đã được xóa thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while deleting promotion: " + e.getMessage());
//        } catch (Exception e) {
//            System.err.println("Error: Invalid input format or data. Please try again.");
//            scanner.nextLine(); // Clear the buffer
//        }
//    }
//    //Tìm kiếm khuyến mãi
//    private static void searchPromotion(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.print("Nhập từ khóa tìm kiếm khuyến mãi: ");
//            String keyword = scanner.next();
//            List<Promotion> searchResult = productManager.searchPromotions(keyword);
//            if (searchResult.isEmpty()) {
//                System.out.println("Không tìm thấy khuyến mãi nào phù hợp.");
//            } else {
//                System.out.println("Kết quả tìm kiếm:");
//                for (Promotion promotion : searchResult) {
//                    System.out.println(promotion);
//                }
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while searching promotion: " + e.getMessage());
//        }
//    }
// // Thêm nhà cung cấp
//    private static void addSupplier(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.println("Nhập thông tin nhà cung cấp mới:");
//            System.out.print("ID nhà cung cấp: ");
//            String supplierID = scanner.next();
//            scanner.nextLine(); // Xóa bất kỳ ký tự newline còn lại trong bộ đệm
//            System.out.print("Tên nhà cung cấp: ");
//            String supplierName = scanner.nextLine(); // Sử dụng nextLine() để đọc dữ liệu kiểu String
//            System.out.print("Địa chỉ: ");
//            String address = scanner.nextLine(); // Sử dụng nextLine() để đọc dữ liệu kiểu String
//            System.out.print("Số điện thoại: ");
//            String phoneNumber = scanner.next();
//
//            SupplierSP newSupplier = new SupplierSP(supplierID, supplierName, address, phoneNumber);
//            productManager.addSupplier(newSupplier);
//            System.out.println("Nhà cung cấp đã được thêm thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while adding supplier: " + e.getMessage());
//        }
//    }
//
//    // Xóa nhà cung cấp
//    private static void deleteSupplier(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.print("Nhập ID nhà cung cấp cần xóa: ");
//            String supplierID = scanner.next();
//            productManager.deleteSupplier(supplierID);
//            System.out.println("Nhà cung cấp có ID " + supplierID + " đã được xóa thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while deleting supplier: " + e.getMessage());
//        }
//    }
//
//    // Cập nhật thông tin nhà cung cấp
//    private static void updateSupplier(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            System.out.print("Nhập ID nhà cung cấp cần cập nhật: ");
//            String supplierID = scanner.next();
//            System.out.println("Nhập thông tin mới cho nhà cung cấp:");
//            System.out.print("Tên nhà cung cấp: ");
//            String supplierName = scanner.next();
//            scanner.nextLine(); // Xóa bất kỳ ký tự newline còn lại trong bộ đệm
//            System.out.print("Địa chỉ: ");
//            String address = scanner.nextLine(); // Sử dụng nextLine() để đọc dữ liệu kiểu String
//            System.out.print("Số điện thoại: ");
//            String phoneNumber = scanner.next();
//
//            SupplierSP updatedSupplier = new SupplierSP(supplierID, supplierName, address, phoneNumber);
//            productManager.updateSupplier(updatedSupplier);
//            System.out.println("Nhà cung cấp có ID " + supplierID + " đã được cập nhật thành công.");
//        } catch (RemoteException e) {
//            System.err.println("Error while updating supplier: " + e.getMessage());
//        }
//    }
//    private static void searchInvoiceDetail(Scanner scanner, interfaceProductManager productManager) {
//        System.out.print("Nhập mã hóa đơn cần tìm kiếm: ");
//        scanner.nextLine(); 
//        String invoiceID = scanner.nextLine();
//        try {
//            List<InvoiceDetail> invoiceDetails = productManager.searchInvoiceDetails(invoiceID);
//            if (invoiceDetails.isEmpty()) {
//                System.out.println("Không tìm thấy chi tiết hóa đơn với mã " + invoiceID);
//            } else {
//                System.out.println("Danh sách chi tiết hóa đơn với mã " + invoiceID + ":");
//                for (InvoiceDetail invoiceDetail : invoiceDetails) {
//                    System.out.println(invoiceDetail);
//                }
//            }
//        } catch (RemoteException e) {
//            System.err.println("Lỗi khi tìm kiếm chi tiết hóa đơn: " + e.getMessage());
//        }
//    }
//    private static void createInvoice(Scanner scanner, interfaceProductManager productManager) {
//        try {
//            List<String> productIds = new ArrayList<>();
//            List<Integer> quantities = new ArrayList<>();
//
//            // Nhập thông tin sản phẩm và số lượng
//            System.out.println("Nhập thông tin sản phẩm và số lượng (nhập '0' để tạo hóa đơn):");
//            while (true) {
//                System.out.print("Nhập ID sản phẩm: ");
//                String productId = scanner.next();
//                if (productId.equals("0")) {
//                    break;
//                }
//
//                System.out.print("Nhập số lượng: ");
//                int quantity = scanner.nextInt();
//
//                productIds.add(productId);
//                quantities.add(quantity);
//
//                System.out.println("Nhập '1' để tiếp tục thêm sản phẩm, nhập '0' để tạo hóa đơn:");
//                int choice = scanner.nextInt();
//                if (choice == 0) {
//                    break;
//                }
//            }
//
//            // Gọi phương thức tạo hóa đơn từ đối tượng productManager
//            boolean result = productManager.createInvoice(productIds, quantities);
//            if (result) {
//                System.out.println("Hóa đơn đã được tạo thành công.");
//            } else {
//                System.out.println("Đã xảy ra lỗi khi tạo hóa đơn.");
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while creating invoice: " + e.getMessage());
//        } catch (Exception e) {
//            System.err.println("Error: Invalid input format or data. Please try again.");
//            scanner.nextLine(); // Clear the buffer
//        }
//    }
//    private static void displayProductsSortQuantityASC(interfaceProductManager productManager) {
//        try {
//            List<Product> products = productManager.sortProductsQuantityASC();
//            System.out.println("Danh sách sản phẩm:");
//            for (Product product : products) {
//                System.out.println(product);
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while getting products: " + e.getMessage());
//        }
//    }
//    private static void displayProductsSortPriceASC(interfaceProductManager productManager) {
//        try {
//            List<Product> products = productManager.sortProductsPriceASC();
//            System.out.println("Danh sách sản phẩm:");
//            for (Product product : products) {
//                System.out.println(product);
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while getting products: " + e.getMessage());
//        }
//    }
//    private static void displayProductsSortQuantityDESC(interfaceProductManager productManager) {
//        try {
//            List<Product> products = productManager.sortProductsQuantityASC();
//            System.out.println("Danh sách sản phẩm:");
//            for (Product product : products) {
//                System.out.println(product);
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while getting products: " + e.getMessage());
//        }
//    }
//    private static void displayProductsSortPriceDESC(interfaceProductManager productManager) {
//        try {
//            List<Product> products = productManager.sortProductsPriceASC();
//            System.out.println("Danh sách sản phẩm:");
//            for (Product product : products) {
//                System.out.println(product);
//            }
//        } catch (RemoteException e) {
//            System.err.println("Error while getting products: " + e.getMessage());
//        }
//    }
//}
//
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
