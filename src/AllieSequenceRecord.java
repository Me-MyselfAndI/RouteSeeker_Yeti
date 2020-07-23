public class AllieSequenceRecord extends SequenceRecord {

    Vector vel;

    public AllieSequenceRecord (Cell cell, Vector vel, int cargo) {
        super(cell, cargo);
        this.vel = vel;
    }

    public AllieSequenceRecord (Cell cell, int cargo) {
        super(cell, cargo);
    }

}
