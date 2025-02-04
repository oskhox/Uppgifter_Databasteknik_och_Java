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

            //Query samt objekt för Skonamn
            ResultSet rs2 = stmt.executeQuery("select titel, MärkeID from Skonamn");
            List<Skonamn> skonamn = new ArrayList<>();
            while (rs2.next()) {
                Skonamn temp = new Skonamn();
                String titel1 = rs2.getString("titel");
                temp.setTitel(titel1);
                int märkeID = rs2.getInt("märkeID");
                temp.setMarkeID(märkeID);
                skonamn.add(temp);
            }
            skonamn.forEach(m -> System.out.println(m.getTitel() + " " + m.getMarkeID()));

            //Query samt objekt för Sko
            ResultSet rs3 = stmt.executeQuery("select id, skonamnTitel, storlek, färg, pris, antal_i_lager from Sko");
            List<Sko> sko = new ArrayList<>();
            while (rs3.next()) {
                Sko temp = new Sko();
                int id3 = rs3.getInt("id");
                temp.setId(id3);
                String skonamnTitel = rs3.getString("skonamnTitel");
                temp.setSkonamnTitel(skonamnTitel);
                int storlek3 = rs3.getInt("storlek");
                temp.setStorlek(storlek3);
                String färg3 = rs3.getString("färg");
                temp.setFarg(färg3);
                int pris3 = rs3.getInt("pris");
                temp.setPris(pris3);
                int antal_i_lager3 = rs3.getInt("antal_i_lager");
                temp.setAntal_i_lager(antal_i_lager3);
                sko.add(temp);
            }
            sko.forEach(m -> System.out.println(m.getId() + " " + m.getSkonamnTitel() + " " + m.getStorlek() + " " + m.getFarg() +
                   " " + m.getPris() + " " + m.getAntal_i_lager()));

            //Query samt objekt för Kategori
            ResultSet rs4 = stmt.executeQuery("select id, namn from Kategori");
            List<Kategori> kategori = new ArrayList<>();
            while (rs4.next()) {
                Kategori temp = new Kategori();
                int id4 = rs4.getInt("id");
                temp.setId(id4);
                String namn4 = rs4.getString("namn");
                temp.setNamn(namn4);
                kategori.add(temp);
            }
            kategori.forEach(m -> System.out.println(m.getId() + " " + m.getNamn()));

            //Query samt objekt för Kategori_sko
            ResultSet rs5 = stmt.executeQuery("select kategoriID, skoID from Kategori_Sko");
            List<Kategori_Sko> kategori_sko = new ArrayList<>();
            while (rs5.next()) {
                Kategori_Sko temp = new Kategori_Sko();
                int kategoriID = rs5.getInt("kategoriID");
                temp.setKategoriID(kategoriID);
                int skoID5 = rs5.getInt("skoID");
                temp.setSkoID(skoID5);
                kategori_sko.add(temp);
            }
            kategori_sko.forEach(m -> System.out.println(m.getKategoriID() + " " + m.getSkoID()));

            //Query samt objekt för Kund
            ResultSet rs6 = stmt.executeQuery("select id, för_och_efternamn, ort, epost, losenord from Kund");
            List<Kund> kund = new ArrayList<>();
            while (rs6.next()) {
                Kund temp = new Kund();
                int id6 = rs6.getInt("id");
                temp.setId(id6);
                String för_och_efternamn6 = rs6.getString("för_och_efternamn");
                temp.setFor_och_efternamn(för_och_efternamn6);
                String ort = rs6.getString("ort");
                temp.setOrt(ort);
                String epost = rs6.getString("epost");
                temp.setEpost(epost);
                String losenord = rs6.getString("losenord");
                temp.setLosenord(losenord);
                kund.add(temp);
            }
            kund.forEach(m -> System.out.println(m.getId() + " " + m.getFor_och_efternamn() + " " + m.getOrt() + " " +
                    m.getEpost() + " " + m.getLosenord()));

            //Query samt objekt för Beställning
            ResultSet rs7 = stmt.executeQuery("select datum, kundID, status from Beställning");
            List<Bestallning> bestallning = new ArrayList<>();
            while (rs7.next()) {
                Bestallning temp = new Bestallning();
                java.sql.Date datum = rs7.getDate("datum");
                temp.setDatum(datum);
                int kundID7 = rs7.getInt("kundID");
                temp.setKundID(kundID7);

                String statusString = rs7.getString("status"); //hämtar status i form av sträng
                Bestallning.Status statusEnum = Bestallning.Status.valueOf(statusString); //konverterar till enum
                temp.setStatus(statusEnum); //sätter enumet som värde

                bestallning.add(temp);
            }
            bestallning.forEach(m -> System.out.println(m.getDatum() + " " + m.getKundID() + " " + m.getStatus()));

            //Query samt objekt för Skoval
            ResultSet rs8 = stmt.executeQuery("select id, antal, beställningID, skoID from Skoval");
            List<Skoval> skoval = new ArrayList<>();
            while (rs8.next()) {
                Skoval temp = new Skoval();
                int id8 = rs8.getInt("id");
                temp.setId(id8);
                int antal = rs8.getInt("antal");
                temp.setAntal(antal);
                int beställningID8 = rs8.getInt("beställningID");
                temp.setBestallningID(beställningID8);
                int skoID8 = rs8.getInt("skoID");
                temp.setSkoID(skoID8);
                skoval.add(temp);
            }
            skoval.forEach(m -> System.out.println(m.getId() + " " + m.getAntal() + " " + m.getBestallningID() + " " +
                    m.getSkoID()));

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