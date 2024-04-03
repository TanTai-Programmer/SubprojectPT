package interfaceQLSP;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import objectQLSP.Invoice;
import objectQLSP.Product;
import objectQLSP.Promotion;
import objectQLSP.SupplierSP;

public interface interfaceProductManager extends Remote {
    List<Product> getProducts() throws RemoteException;
    List<Invoice> getInvoices() throws RemoteException;
    List<Promotion> getPromotions() throws RemoteException;
    List<SupplierSP> getSuppliers() throws RemoteException;
}
