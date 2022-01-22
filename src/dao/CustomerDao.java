package dao;

import bean.Customer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class CustomerDao {

    public ArrayList<Customer> moreThanOnePet() {
        ArrayList<Customer> rslt = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.Name1 FROM customer AS c INNER JOIN Pet AS p on c.cID = p.cID GROUP BY c.Name1 HAVING COUNT(*) > 1");

            if (rs != null) {
                while (rs.next()) {
                    String name1 = rs.getString("name1");

                    Customer customer = new Customer();
                    customer.setName(name1);
                    rslt.add(customer);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rslt;
    }

    public boolean insertCSVtoCustomer() {
        String path = "tables_csv_to_import/CustomerTable.csv";
        String line = "";
        int result = 0;
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            BufferedReader br = new BufferedReader(new FileReader(path));
            int c = 1;
            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");

                if (c > 1) {
                    Statement stmt = con.createStatement();
                    PreparedStatement ps = con.prepareStatement("INSERT into customer values(?, ?, ?, ?, ?, ?)");

                    ps.setLong(1, Integer.parseInt(str[0]));  //cID
                    ps.setString(2, str[1]);   //Name1
                    ps.setString(3, str[2]);   //Address
                    ps.setString(4, str[3]);   //Phone
                    ps.setString(5, str[4]);   //email
                    ps.setLong(6, Integer.parseInt(str[5])); //age

                    result = ps.executeUpdate();
                }
                if (c == 1) {c = 2;}  //Avoid inserting the attributes which is first line of csv
            }
            con.close();
        } catch (IOException | SQLException e) {
            System.out.println("SQLException: " + e.getMessage());
    }

        return result == 1;
    }

    public ArrayList<Customer> CustomersNameWhenAge1() {
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT C.Name1 FROM customer AS C \n" +
                    "INNER JOIN Pet AS P \n" +
                    "ON C.cID = P.cID\n" +
                    "WHERE P.Age = 1;");

            if (rs != null) {
                while (rs.next()) {
                    String Name1 = rs.getString("name1");

                    Customer customer = new Customer();
                    customer.setName(Name1);
                    customerList.add(customer);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customerList;
    }

    public ArrayList<Customer> CustomersWithNoPets() {
        ArrayList<Customer> customerList = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT c.cID FROM Customer AS c\n" +
                    "LEFT JOIN Pet AS p\n" +
                    "ON c.cID = p.cID\n" +
                    "WHERE p.cID IS NULL;");

            if (rs != null) {
                while (rs.next()) {
                    int cID = rs.getInt("cID");

                    Customer customer = new Customer();
                    customer.setcID(cID);
                    customerList.add(customer);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return customerList;
    }
}
