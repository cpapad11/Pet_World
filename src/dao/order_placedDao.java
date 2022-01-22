package dao;

import bean.Customer;
import bean.OrderPlaced;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class order_placedDao {

    public boolean insertCSVtoOrder_placed () {
        String path = "tables_csv_to_import/OrderPlaced.csv";
        String line = "";
        int result = 0;

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            int c = 1;

            while ((line = br.readLine()) != null) {
                String[] str = line.split(",");
                if (c > 1) {
                    Statement stmt = con.createStatement();
                    PreparedStatement ps = con.prepareStatement("INSERT into OrderPlaced values(?, ?, ?, ?, ?)");

                    ps.setString(1, str[0]);
                    ps.setLong(2, Integer.parseInt(str[1]));
                    ps.setLong(3, Integer.parseInt(str[2]));
                    ps.setLong(4, Integer.parseInt(str[3]));
                    ps.setLong(5, Integer.parseInt(str[4]));

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

    public ArrayList<ArrayList<Integer>> numOfOrders_under50() {
        ArrayList<ArrayList<Integer>> rslt = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(price), price FROM orderplaced WHERE price < 50 GROUP BY price ORDER BY price");

            if (rs != null) {
                while (rs.next()) {
                    int COUNTprice = rs.getInt("COUNT(price)");
                    int price = rs.getInt("price");

                    ArrayList<Integer> temp = new ArrayList<>();
                    temp.add(COUNTprice);
                    temp.add(price);
                    rslt.add(temp);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return rslt;
    }

    public Customer custWithMostOrders() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Max(c.Name1) FROM Orderplaced AS ord INNER JOIN Customer AS c ON c.cID = ord.cID");

            if (rs != null) {
                while (rs.next()) {
                    Customer new1 = new Customer();
                    new1.setName(rs.getString("Max(c.Name1)"));
                    return new1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public OrderPlaced maxPriceOrder () {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Orderplaced where price = (select Max(price) from Orderplaced)");

            if (rs != null) {
                while (rs.next()) {
                    OrderPlaced new1 = new OrderPlaced(rs.getString("Date1"),
                            rs.getInt("Quantity"), rs.getInt("price"),
                            rs.getInt("cID"), rs.getInt("pID"));

                    return new1;
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
