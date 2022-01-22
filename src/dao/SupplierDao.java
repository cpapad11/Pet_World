package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class SupplierDao {

    public boolean insertCsvSupplier() {
        String path = "tables_csv_to_import/SuplierData.csv";
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
                    PreparedStatement ps = con.prepareStatement("INSERT into suplier values(?, ?)");
                    ps.setString(1, str[0]);
                    ps.setString(2, str[1]);

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
