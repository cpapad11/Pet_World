package dao;

import bean.Pet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class PetDao {
    public ArrayList<Pet> getNAPetless13() {
        ArrayList<Pet> petList = new ArrayList<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/petworld", "root", "12345!WB");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT Name1, Age FROM Pet WHERE Age > 13 GROUP BY Age ORDER BY Name1");

            if (rs != null) {
                while (rs.next()) {
                    String Name1 = rs.getString("Name1");
                    int Age = rs.getInt("Age");

                    Pet pet = new Pet();
                    pet.setName1(Name1);
                    pet.setAge(Age);
                    petList.add(pet);
                }
            }
            con.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return petList;
    }

    public boolean insertCSVtoPet () {
        String path = "tables_csv_to_import/PetTable.csv";
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
                    PreparedStatement ps = con.prepareStatement("INSERT into Pet values(?, ?, ?, ?, ?)");

                    ps.setLong(1, Integer.parseInt(str[0]));  //petID
                    ps.setString(2, str[1]);                  //Name1
                    ps.setLong(3, Integer.parseInt(str[2]));  //age
                    ps.setString(4, str[3]);                  //type1
                    ps.setLong(5, Integer.parseInt(str[4]));  //cID

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
