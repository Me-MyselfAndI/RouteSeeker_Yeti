public class RobotSequenceRecord {

    Cell cell;
    Vector acc;
    int cargo;
    String action;

    public RobotSequenceRecord (Cell cell, Vector acc, int cargo) {
        this.cell = cell;
        this.acc = acc;
        this.cargo = cargo;
    }
}
