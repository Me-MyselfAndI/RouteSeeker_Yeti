
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

    public double getAngle (){ return x != 0 ? Math.atan(y / x) : Math.atan(y/(x+0.0001)); }

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
        if (x == 0)
            return new Vector(y, Math.PI/2);
        double angle = Math.acos(x/(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))));
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
        return this.getAngle() == vector.getAngle() && vector.getValue() < this.getValue();
    }





    public static void main(String[] args) {
        Vector a = Vector.CreateFromCortesian(6, 8);
        System.out.println(a.containsPoint(3, 4));
    }
}
