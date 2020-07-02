import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.pow;


public class Robot {
    Cell pos;
    int cargo;
    double maxAcc, shootingdT;
    ArrayList <RobotSequenceRecord> sequence;
    Vector vel;
    boolean running;

    public Robot (Cell pos, double maxAcc, double shootingdT, int cargo) {
        running = true;
        this.pos = pos;
        this.maxAcc = maxAcc;
        this.shootingdT = shootingdT;
        sequence = new ArrayList<RobotSequenceRecord>();
        this.cargo = cargo;
        vel = new Vector(0, 0);
    }

    private static Vector stochasticAcceleration (Robot robot, double stochasticConstant, double avgFieldValue) {
        Random rand = new Random();
        // Создаем ускорение, равное value-вектору, scaled в случайное (в дальнейшем - мб неслучайное) значение не более maxAcc/avgFieldValue раз
        Vector acc = avgFieldValue == 0 ? new Vector(0, 0): robot.pos.value.dot(rand.nextDouble()*robot.maxAcc/avgFieldValue);
        // Добавляем к нему стохастический вектор, по модулю не больший, чем maxAcc*stochasticConstant
        acc.add(new Vector(robot.maxAcc*stochasticConstant*rand.nextDouble(), (rand.nextDouble()*2 - 1)*Math.PI));
        // Если вышло по модулю более, чем maxAcc, то уменьшаем до maxAcc
        if (acc.getValue() > robot.maxAcc)
            acc = new Vector(robot.maxAcc, acc.getAngle());
        return acc;
    }

    public Cell move (Cell[][] field, double dT, double stochasticConstant, double avgFieldValue){    // Сделать так, чтобы от totalFieldValue зависело ускорение
        // The greater is proximity coefficient, the greater influence will the stochastic vector have onto the process.

        if (pos.type == 's') {
            IT IS NOT FINISHED HERE
        }

        Vector acc = stochasticAcceleration(this, stochasticConstant, avgFieldValue);
        Vector deltaPos = new Vector(vel.dot(dT).plus(acc.dot(pow(dT, 2)/2)));  // Using dP = v_0*t + at^2/2

        // Почему-то deltaPos портится при передаче в реальное движение


        /** TAKE THIS INTO ACCOUNT IF YOU ARE ABOUT TO CHANGE THIS CODE!!!
         * Due to the cells being numbered from left to right, and from top to bottom and all vectors being also from left to right, but from bottom to top,
         we have to ADD the x-component, but SUBTRACT the y-component
         */
        sequence.add(new RobotSequenceRecord(pos, acc));

        if (pos.check(field, (int) Math.round(deltaPos.x), (int) Math.round(deltaPos.y))) {
            pos = field[pos.y + (int) deltaPos.y][pos.x + (int) deltaPos.x];
            vel = new Vector(vel.plus(acc.dot(dT)));
        }
        else {
            running = false;
            vel = new Vector(vel.plus(acc.dot(dT)));
        }
        return pos;
    }
}
