package INL2;

public class Kategori {

    protected int id;
    protected int namn;

    public Kategori(){}

    public Kategori(int id, int namn) {
        this.id = id;
        this.namn = namn;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNamn() {
        return namn;
    }

    public void setNamn(int namn) {
        this.namn = namn;
    }
}