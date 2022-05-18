public class ChessPiece {
    public String name;
    public String type;
    public int gold;
    public int attackPoints;
    public boolean isAvailable;

    public ChessPiece(String name, String type, int gold, int attackPoints) {
        this.type = type;
        this.attackPoints = attackPoints;
        this.name = name;
        this.gold = gold;
        this.isAvailable = false;
    }

    public String toString() {
        return this.name + " " + this.type + " " + this.gold + " " + this.attackPoints + " " + this.isAvailable;
    }

    public String displayStats() {
        return this.name + " " + this.type + " " + this.gold + " " + this.attackPoints;
    }
}
