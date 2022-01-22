package dao;

import bean.Product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class ProductDao {
    public ArrayList<Product> getProdFromSup() {
        ArrayList<Product> productList = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM product AS p\n" +
                    "INNER JOIN suplier AS sup \n" +
                    "ON p.sName = sup.sName AND p.phoneNumber = sup.phoneNumber\n" +
                    "WHERE p.sName = \"Judi Wyvill\" and p.PhoneNumber = \"266-965-2848\";");

            if (rs != null) {
                while (rs.next()) {
                    int pID = rs.getInt("pID");
                    String Name1 = rs.getString("Name1");
                    int Price = rs.getInt("Price");
                    int Quantity = rs.getInt("Quantity");
                    String sName = rs.getString("sName");
                    String phoneNumber = rs.getString("phoneNumber");

                    Product product = new Product(pID, Name1, Price, Quantity, sName, phoneNumber);
                    productList.add(product);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return productList;
    }

    public boolean insertCSVtoProduct() {
        String path = "tables_csv_to_import/ProductTable.csv";
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
                    PreparedStatement ps = con.prepareStatement("INSERT into Product values(?, ?, ?, ?, ?, ?)");
                    //System.out.println(str[0] + " "+ str[1] + " "+ str[2]+ " " + str[3] + " "+ str[4]+ " " + str[5]);
                    ps.setLong(1, Integer.parseInt(str[0]));
                    ps.setString(2, str[1]);
                    ps.setLong(3, Integer.parseInt(str[2]));
                    ps.setLong(4, Integer.parseInt(str[3]));
                    ps.setString(5, str[4]);
                    ps.setString(6, str[5]);

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
}
