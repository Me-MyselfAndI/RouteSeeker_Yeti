public class Cell {
    int x, y;
    Vector value;
    String type;
    int timesMutated = 1;

    public Cell (String type){
        this.type = type;
        value = new Vector(0, 0);
    }

    public Cell (int y, int x) {
        this.x = x;
        this.y = y;
        value = new Vector(0, 0);
    }

    public Cell (int y, int x, String type) {
        this (y, x);
        this.type = type;
        value = new Vector(0, 0);
    }

    public Cell (int y, int x, String type, Vector value) {
        this (y, x, type);
        this.value = value;
    }

    public Cell (Cell cell){
        this (cell.y, cell.x, cell.type, cell.value);
    }

    public String toString (){
        return "" + type;
    }

    public boolean check (Cell[][] field, double dx, double dy) {
        return !(((y + dy < 0 || y + dy >= field.length) || (x + dx < 0 || x + dx >= field[0].length)) || field[(int) Math.ceil(y + dy)][(int) Math.ceil(x + dx)].type == "w");
    }

    public int changeCargo () {
        if (toString().equals("l")) {
            return 1;
        }
        else if (toString().equals("s")) {
            return -1;
        }
        else return 0;
    }

    public void mutateValueAVG (Vector b) {
        value = value.dot(timesMutated);
        value = value.plus(b);
        value = value.dot(1.0/(timesMutated+1));
        timesMutated ++;
    }

}
