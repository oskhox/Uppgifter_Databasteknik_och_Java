package INL2;

public class Kategori_Sko {

    protected int kategoriID;
    protected int skoID;

    public Kategori_Sko(){}

    public Kategori_Sko(int kategoriID, int skoID) {
        this.kategoriID = kategoriID;
        this.skoID = skoID;
    }

    public int getKategoriID() {
        return kategoriID;
    }

    public void setKategoriID(int kategoriID) {
        this.kategoriID = kategoriID;
    }

    public int getSkoID() {
        return skoID;
    }

    public void setSkoID(int skoID) {
        this.skoID = skoID;
    }
}