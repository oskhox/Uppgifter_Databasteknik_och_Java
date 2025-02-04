package INL2;

public class Skonamn {

    protected String titel;
    protected int markeID;

    public Skonamn(){}

    public Skonamn(String titel, int markeID) {
        this.titel = titel;
        this.markeID = markeID;
    }

    public String getTitel() {
        return titel;
    }

    public void setTitel(String titel) {
        this.titel = titel;
    }

    public int getMarkeID() {
        return markeID;
    }

    public void setMarkeID(int markeID) {
        this.markeID = markeID;
    }
}