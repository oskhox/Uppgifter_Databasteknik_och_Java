package INL2;

public class Skoval {
    protected int id;
    protected int antal;
    protected int bestallningID;
    protected int skoID;

    public Skoval(){}

    public Skoval(int id, int antal, int bestallningID, int skoID) {
        this.id = id;
        this.antal = antal;
        this.bestallningID = bestallningID;
        this.skoID = skoID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAntal() {
        return antal;
    }

    public void setAntal(int antal) {
        this.antal = antal;
    }

    public int getBestallningID() {
        return bestallningID;
    }

    public void setBestallningID(int bestallningID) {
        this.bestallningID = bestallningID;
    }

    public int getSkoID() {
        return skoID;
    }

    public void setSkoID(int skoID) {
        this.skoID = skoID;
    }
}