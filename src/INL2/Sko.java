package INL2;

public class Sko {

    protected int id;
    protected String skonamnTitel;
    protected int storlek;
    protected String farg;
    protected int pris;
    protected int antal_i_lager;

    public Sko() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkonamnTitel() {
        return skonamnTitel;
    }

    public void setSkonamnTitel(String skonamnTitel) {
        this.skonamnTitel = skonamnTitel;
    }

    public int getStorlek() {
        return storlek;
    }

    public void setStorlek(int storlek) {
        this.storlek = storlek;
    }

    public String getFarg() {
        return farg;
    }

    public void setFarg(String farg) {
        this.farg = farg;
    }

    public int getPris() {
        return pris;
    }

    public void setPris(int pris) {
        this.pris = pris;
    }

    public int getAntal_i_lager() {
        return antal_i_lager;
    }

    public void setAntal_i_lager(int antal_i_lager) {
        this.antal_i_lager = antal_i_lager;
    }
}