package INL2;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

public class ShoeProgram {

    private final Scanner sc;
    private int validatedEmailId;
    private int validatedUserId;
    private int shoeInput;
    private int activeOrderId;
    Connection con;

    private final List<Marke> marke = new ArrayList<>();
    private final List<Skonamn> skonamn = new ArrayList<>();
    private final List<Sko> sko = new ArrayList<>();
    private final List<Kategori> kategori = new ArrayList<>();
    private final List<Kategori_Sko> kategori_sko = new ArrayList<>();
    private final List<Kund> kund = new ArrayList<>();
    private final List<Bestallning> bestallning = new ArrayList<>();
    private final List<Skoval> skoval = new ArrayList<>();

    //Skapar upp connection redan i konstruktorn så att jag kan använda den i interaktion med SP senare
    public ShoeProgram() {
        sc = new Scanner(System.in);
        Properties p = new Properties();

        try {
            p.load(new FileInputStream("src/INL2/Settings.properties"));
            con = DriverManager.getConnection(
                    p.getProperty("connectionString"),
                    p.getProperty("name"),
                    p.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        tablesToObjects();
        verifyEmail();
    }

    public void tablesToObjects() {
        try (Statement stmt = con.createStatement()) {

            //Query samt objekt för Märke
            ResultSet rs1 = stmt.executeQuery("select id, namn from Märke");
            while (rs1.next()) {
                Marke temp = new Marke();
                int id1 = rs1.getInt("id");
                temp.setId(id1);
                String namn1 = rs1.getString("namn");
                temp.setNamn(namn1);
                marke.add(temp);
            }

            //Query samt objekt för Skonamn
            ResultSet rs2 = stmt.executeQuery("select titel, MärkeID from Skonamn");
            while (rs2.next()) {
                Skonamn temp = new Skonamn();
                String titel1 = rs2.getString("titel");
                temp.setTitel(titel1);
                int märkeID = rs2.getInt("märkeID");
                temp.setMarkeID(märkeID);
                skonamn.add(temp);
            }

            //Query samt objekt för Sko
            ResultSet rs3 = stmt.executeQuery("select id, skonamnTitel, storlek, färg, pris, antal_i_lager from Sko");
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

            //Query samt objekt för Kategori
            ResultSet rs4 = stmt.executeQuery("select id, namn from Kategori");
            while (rs4.next()) {
                Kategori temp = new Kategori();

                int id4 = rs4.getInt("id");
                temp.setId(id4);
                String namn4 = rs4.getString("namn");
                temp.setNamn(namn4);
                kategori.add(temp);
            }

            //Query samt objekt för Kategori_sko
            ResultSet rs5 = stmt.executeQuery("select kategoriID, skoID from Kategori_Sko");
            while (rs5.next()) {
                Kategori_Sko temp = new Kategori_Sko();

                int kategoriID = rs5.getInt("kategoriID");
                temp.setKategoriID(kategoriID);
                int skoID5 = rs5.getInt("skoID");
                temp.setSkoID(skoID5);
                kategori_sko.add(temp);
            }

            //Query samt objekt för Kund
            ResultSet rs6 = stmt.executeQuery("select id, för_och_efternamn, ort, epost, losenord from Kund");
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

            //Query samt objekt för Beställning
            ResultSet rs7 = stmt.executeQuery("select id, datum, kundID, status from Beställning");
            while (rs7.next()) {
                Bestallning temp = new Bestallning();

                int id7 = rs7.getInt("id");
                temp.setId(id7);
                java.sql.Date datum = rs7.getDate("datum");
                temp.setDatum(datum);
                int kundID7 = rs7.getInt("kundID");
                temp.setKundID(kundID7);

                String statusString = rs7.getString("status"); //hämtar status i form av sträng
                Bestallning.Status statusEnum = Bestallning.Status.valueOf(statusString); //konverterar till enum
                temp.setStatus(statusEnum); //sätter enumet som värde
                bestallning.add(temp);
            }

            //Query samt objekt för Skoval
            ResultSet rs8 = stmt.executeQuery("select id, antal, beställningID, skoID from Skoval");
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //Själva programmet som interagerar mot objekten

    public void verifyEmail() {
        boolean matchingEmail = false;

        System.out.println("Hej, skriv in din e-postadress för att logga in på ditt konto:");
        String inputEmail = sc.nextLine();

        for (Kund k : kund) {
            if (k.getEpost().equalsIgnoreCase(inputEmail)) {
                matchingEmail = true;
                validatedEmailId = k.getId(); //spara kund-id där e-postadressen stämmer
                break;
            }
        }

        if (matchingEmail) {
            System.out.println("E-postadressen hittad.");
            verifyPassword();
        } else {
            System.out.println("E-postadressen hittades inte. Pröva igen.");
        }
    }

    public void verifyPassword() {
        boolean matchingPassword = false;
        System.out.println("Skriv in ditt lösenord:");
        String inputPwd = sc.nextLine();

        for (Kund k : kund) {
            if (k.getId() == validatedEmailId) {
                if (k.getLosenord().equals(inputPwd)) {
                    matchingPassword = true;
                    validatedUserId = k.getId(); //sparar den helt inloggade kundens id för att använda senare
                }
            }
        }
        if (matchingPassword) {
            System.out.println("Lösenordet var rätt. Du är nu inloggad.");
            selectShoe();
        } else {
            System.out.println("Lösenordet var fel. Pröva igen.");
        }
    }

    public void selectShoe() {
        System.out.println("Följande skor finns i lager:");
        sko.forEach(m -> System.out.println(m.getId() + ". " + m.getSkonamnTitel() + ". Storlek: " + m.getStorlek() + ". Färg: " + m.getFarg() +
                ". Pris: " + m.getPris() + ". I lager: " + m.getAntal_i_lager() + " stycken."));
        System.out.println("Välj vilken specifik sko du vill ha genom att ange dess nummer:");
        shoeInput = sc.nextInt();

        for (Sko s : sko) {
            if (s.getId() == shoeInput) {
                System.out.println("Du har valt skon " + s.getSkonamnTitel() + ", i storlek " + s.getStorlek() +
                        " med färgen " + s.getFarg().toLowerCase() + " som kostar " + s.getPris() + " kronor.");
            }
        }
        activeOrderCheck();
    }

    //Hämtar kunds tidigare beställningar och sparar id för kunds aktiva beställning, om sådan finns
    public void activeOrderCheck() {
        boolean activeOrderFound = false;
        for (Bestallning b : bestallning) {
            if (b.getKundID() == validatedUserId && b.getStatus() == Bestallning.Status.AKTIV) {
                activeOrderId = b.getId();
                System.out.println("Du har redan en aktiv beställning. Skon läggs till i den aktiva beställningen.");
                activeOrderFound = true;
                break;
            }
        }
        if (!activeOrderFound) {
            System.out.println("Du har ingen aktiv beställning. Skon läggs till i en ny beställning.");
            activeOrderId = -1; //Gör så att ny beställning skapas i addShoe()
        }
        addShoe();
    }

    //Anropar, med callable statement, min SP, med -1 om ingen aktiv beställning hittades
    public void addShoe() {
        try {
            CallableStatement stm = con.prepareCall("CALL AddToCart(?, ?, ?)");
            stm.setInt(1, activeOrderId);
            stm.setInt(2, validatedUserId);
            stm.setInt(3, shoeInput);
            stm.executeQuery();
            System.out.println("Din beställning är bekräftad.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Din beställning gick inte igenom. Pröva igen.");
        }
    }

    public static void main(String[] args) {
        new ShoeProgram();
    }
}