package Uppgift_18a_Trigger;

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class JDBCDemo {
    public static void main(String[] args) {

        Properties p = new Properties();

        try {
            p.load(new FileInputStream("src/JDBC_uppgift_properties/Settings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection(
                p.getProperty("connectionString"),
                p.getProperty("name"),
                p.getProperty("password"));

             Statement stmt = con.createStatement()) {

            //Query nr 1
            ResultSet rs1 = stmt.executeQuery("select count(*) from child");
            while (rs1.next()) {
                int numberOfChildren = rs1.getInt(1);
                System.out.println("Antal barn: " + numberOfChildren);
            }

            //Query nr 2
            ResultSet rs2 = stmt.executeQuery("select count(*) from elf;");
            while (rs2.next()) {
                int numberOfElfs = rs2.getInt(1);
                System.out.println("Antal nissar: " + numberOfElfs);
            }

            //Query nr 3
            ResultSet rs3 = stmt.executeQuery("select name from elf");
            System.out.print("Nissarna heter: ");
            while (rs3.next()) {
                String elfNames = rs3.getString("name");
                System.out.print(elfNames + " ");
            }
            System.out.println(" ");

            //Query nr 4
            ResultSet rs4 = stmt.executeQuery("select avg(niceNumber) from report");
            while (rs4.next()) {
                int averageNiceNumber = rs4.getInt(1);
                System.out.println("Medelvärde på barnens snällhet: " + averageNiceNumber);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}