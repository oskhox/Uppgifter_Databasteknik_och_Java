package INL2;

//Java-koden ska vara prydligt objektorienterad. Detta betyder att du ska ha klasser som
//motsvarar de tabeller i databasen som du interagerar med. När du läser från tabellerna
//skapar du upp objekt av motsvarande klass, i Java, och låter inte lösryckta data från
//databasen åka runt i programmet.

import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class ShoeProgram {
    public static void main(String[] args) {

        Properties p = new Properties();

        try {
            p.load(new FileInputStream("src/INL2/Settings.properties"));
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


            //Programmet och queries
            System.out.println("Var god skriv in din e-postadress:");
            //Query som kollar att e-postadressen finns
            System.out.println("Var god skriv in ditt lösenord:");
            //Query som kollar att lösenordet stämmer
            System.out.println("test");


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}