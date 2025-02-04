package INL2;

//Java-koden ska vara prydligt objektorienterad. Detta betyder att du ska ha klasser som
//motsvarar de tabeller i databasen som du interagerar med. När du läser från tabellerna
//skapar du upp objekt av motsvarande klass, i Java, och låter inte lösryckta data från
//databasen åka runt i programmet.

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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

            //Query samt objekt för Märke
            ResultSet rs1 = stmt.executeQuery("select id, namn from Märke");
            List<Marke> marke = new ArrayList<>();
            while (rs1.next()) {
                Marke temp = new Marke();
                int id1 = rs1.getInt("id");
                temp.setId(id1);
                String namn1 = rs1.getString("namn");
                temp.setNamn(namn1);
                marke.add(temp);
            }
                marke.forEach(m -> System.out.println(m.getId() + " " + m.getNamn()));


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