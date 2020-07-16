public class Cell {
    int x, y;
    Vector value;
    String type;
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

    public Cell(Cell cell) {
        this(cell.y, cell.x, cell.type, cell.value);
    }

    public String toString() {
        return "" + type;
    }

    public boolean check(Cell[][] field, double dx, double dy) {
        return !(((y + dy < 1 || y + dy >= field.length - 1) || (x + dx < 1 || x + dx >= field[0].length - 1)) || field[(int) Math.round(y + dy)][(int) Math.round(x + dx)].type.equals("w"));
    }

    public void mutateValueAVG (Vector b) {
        value = value.dot(timesMutated);
        value = value.plus(b);
        value = value.dot(1.0 / (timesMutated + 1));
        timesMutated++;
    }

    public Cell shiftBy(Cell[][] field, Vector b) {
        return field[y + (int) Math.round(b.y)][x + (int) Math.round(b.x)];
    }
}