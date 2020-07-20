public class Cell {
    int x, y;
    Vector value;
    String type;        // Type is whether there is a wall, cargo, starting point or an empty cell in this Cell
    int timesMutated = 1;

    public Cell(String type) {
        this.type = type;
        value = new Vector(0, 0);
    }

    public Cell(int y, int x) {
        this.x = x;
        this.y = y;
        value = new Vector(0, 0);
    }

    public Cell(int y, int x, String type) {
        this(y, x);
        this.type = type;
        value = new Vector(0, 0);
    }

    public Cell(int y, int x, String type, Vector value) {
        this(y, x, type);
        this.value = value;
    }

    public String toString() {
        return "" + type;
    }

    public boolean check(Cell[][] field, double dx, double dy) {    // Checks whether the cell is outside the field, and whether there is a wall there
        return !(((y + dy < 1 || y + dy >= field.length - 1) || (x + dx < 1 || x + dx >= field[0].length - 1)) || field[(int) Math.round(y + dy)][(int) Math.round(x + dx)].type.equals("w"));
    }

    // This changes the value. The value s an average of vectors that were added to it so to change it back
    // to the sum of Vectors, add a new Vector, and then divide by the new number of Vectors
    public void mutateValueAVG (Vector b) {
        value = value.dot(timesMutated++).plus(b).dot(1.0/timesMutated);
    }

    public Cell shiftBy(Cell[][] field, Vector b) {
        return field[y + (int) Math.round(b.y)][x + (int) Math.round(b.x)];
    }
}