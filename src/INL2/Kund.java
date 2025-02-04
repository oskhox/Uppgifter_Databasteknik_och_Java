package INL2;

public class Kund {

    protected int id;
    protected String for_och_efternamn;
    protected String ort;
    protected String epost;
    protected String losenord;

    public Kund(){}

    public Kund(int id, String for_och_efternamn, String ort, String epost, String losenord) {
        this.id = id;
        this.for_och_efternamn = for_och_efternamn;
        this.ort = ort;
        this.epost = epost;
        this.losenord = losenord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFor_och_efternamn() {
        return for_och_efternamn;
    }

    public void setFor_och_efternamn(String for_och_efternamn) {
        this.for_och_efternamn = for_och_efternamn;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getEpost() {
        return epost;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public String getLosenord() {
        return losenord;
    }

    public void setLosenord(String losenord) {
        this.losenord = losenord;
    }
}