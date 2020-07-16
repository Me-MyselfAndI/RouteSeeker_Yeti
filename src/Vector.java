

public class Vector {
    double x, y;

    public Vector (double radius, double angle) {
        this.x = radius*Math.cos(angle);
        this.y = radius*Math.sin(angle);
    }

    public Vector(Vector b) {
        this.x = b.x;
        this.y = b.y;
    }

    public double getAngle (){
        return x == 0 && y == 0 ? 0 : Math.acos(x/Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)))*Math.signum(y);

    }

    public double getValue () {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    public double dot (Vector b) {
        return Math.cos(angleBetween(b))*getValue()*b.getValue();
    }

    public Vector dot (double b) {
        Vector res = new Vector(this);
        res.x *= b;
        res.y *= b;
        return res;
    }

    public double angleBetween (Vector b) {
        return b.getAngle() - getAngle();
    }

    public Vector plus (Vector b) {
        Vector res = new Vector(this);
        res.x += b.x;
        res.y += b.y;
        return res;
    }

    public static Vector CreateFromCortesian (double x, double y) {
        if (x == 0 && y == 0)
            return new Vector(0,0);
        double angle = Math.acos(x/Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2)))*Math.signum(y);
        return new Vector(x/Math.cos(angle), angle);
    }

    public String toString() {
        return "(" + Math.round(x) + ";" + Math.round(y) + ")";
    }

    public void setValue (Vector value) {
        x = value.x;
        y = value.y;
    }

    public void add (Vector a) {
        this.setValue(this.plus(a));
    }


    public boolean containsPoint (double xCoord, double yCoord) {
        Vector vector = Vector.CreateFromCortesian(xCoord, yCoord);
        return this.getAngle() == vector.getAngle() && vector.getValue() <= this.getValue();
    }
    public boolean wayIsClear (Cell[][] field, Cell pos, double robotWidth) {
        return Vector.wayIsClear(field, pos, this, robotWidth);
    }

    public static boolean wayIsClear (Cell[][] field, Cell pos, Vector dPos, double robotWidth) {
        if (!pos.check(field, dPos.x, dPos.y)) return false;

        if (Math.abs(dPos.x) < 3 && pos.x > 42 && pos.x < 58)
             System.out.println("Look here");

        double dPosValue = dPos.getValue();
        Vector increment = new Vector(0.5, dPos.getAngle());
        for (Vector i = new Vector (0,0); i.getValue() <= dPosValue; i.add(increment)) {
            Cell currCell = pos.shiftBy(field, i);
            for (double xDist = -robotWidth; xDist <= robotWidth; xDist += .5)
                for (double yDist = -robotWidth; yDist <= robotWidth; yDist += .5)
                    if (!currCell.check(field, xDist, yDist))
                        return false;
        }

        return true;
    }





    public static void main(String[] args) {
        Vector a = Vector.CreateFromCortesian(2, 5);

        Vector b = Vector.CreateFromCortesian(-2, 5), c = Vector.CreateFromCortesian(2, -5), d = Vector.CreateFromCortesian(-2, -5);
        System.out.println(d.getAngle());

        Vector increment = new Vector(0.2, c.getAngle());
        for (Vector i = new Vector(0, 0); i.getValue() <= a.getValue(); i.add(increment)) {
            System.out.println(i);
        }
    }
}
