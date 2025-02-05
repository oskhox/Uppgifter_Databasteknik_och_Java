package INL2;

import java.sql.Date;

public class Bestallning {

    public enum Status {
        AKTIV,
        BETALD
    }

    public Bestallning(){}

    protected int id;
    protected java.sql.Date datum;
    protected int kundID;
    protected Status status;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public int getKundID() {
        return kundID;
    }

    public void setKundID(int kundID) {
        this.kundID = kundID;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}