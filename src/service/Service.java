package service;

import bean.Customer;
import bean.OrderPlaced;
import bean.Pet;
import bean.Product;
import dao.*;

import java.util.ArrayList;

public class Service {

    private final CustomerDao custDao = new CustomerDao();
    private final PetDao petDao = new PetDao();
    private final order_placedDao orderDao = new order_placedDao();
    private final ProductDao productDao = new ProductDao();
    private final SupplierDao SupplierDao = new SupplierDao();
    private final WishlistDao wishlistDao = new WishlistDao();


    public ArrayList<Customer> moreThanOnePet() {
        return custDao.moreThanOnePet();
    }

    public boolean insertCSVtoCustomer () {
        return custDao.insertCSVtoCustomer();
    }

    public boolean insertCSVtoPet () { return petDao.insertCSVtoPet(); }

    public boolean insertCSVtoOrder_placed () { return orderDao.insertCSVtoOrder_placed(); }

    public ArrayList<ArrayList<Integer>> numOfOrders_under50() { return orderDao.numOfOrders_under50(); }

    public Customer custWithMostOrders() { return orderDao.custWithMostOrders(); }

    public OrderPlaced maxPriceOrder () { return orderDao.maxPriceOrder(); }

    public boolean insertCSVtoProduct() { return productDao.insertCSVtoProduct(); }

    public boolean insertCsvSupplier() { return SupplierDao.insertCsvSupplier(); }

    public boolean insertCSVtoWishlist() { return wishlistDao.insertCSVtoWishlist(); }

    public ArrayList<Pet> getNAPetless13() { return petDao.getNAPetless13(); }

    public ArrayList<Product> getProdFromSup() { return productDao.getProdFromSup(); }

    public ArrayList<Customer> CustomersNameWhenAge1 () { return custDao.CustomersNameWhenAge1(); }

    public ArrayList<Customer> CustomersWithNoPets () { return custDao.CustomersWithNoPets(); }
}
