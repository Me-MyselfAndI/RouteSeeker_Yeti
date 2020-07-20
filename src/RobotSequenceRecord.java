public class RobotSequenceRecord {  // This is a class for keeping record of each tact of robot movement

    Cell cell;
    Vector acc;
    int cargo;

    public RobotSequenceRecord (Cell cell, Vector acc, int cargo) {
        this.cell = cell;
        this.acc = acc;
        this.cargo = cargo;
    }
}
