import java.util.ArrayList;
import java.util.Random;

import static java.lang.Math.pow;


public class Robot {
    final private double robotDimension = 2;  // Here place an upper-estimate on possible length/width of a robot.
    final int maxCargo = 5;
    Cell pos;
    int cargo;
    double maxAcc, shootingTime;
    ArrayList <RobotSequenceRecord> sequence;
    private ArrayList <Cell> usedCargo;
    Vector vel;
    boolean running;

    public Robot (Cell pos, double maxAcc, double shootingTime, int cargo) {
        running = true;
        this.pos = pos;
        this.maxAcc = maxAcc;
        this.shootingTime = shootingTime;
        sequence = new ArrayList<RobotSequenceRecord>();
        usedCargo = new ArrayList<Cell>();
        this.cargo = cargo;
        vel = new Vector(0, 0);
    }

    private static Vector stochasticAcceleration (Robot robot, double stochasticConstant, double avgFieldValue) {
        Random rand = new Random();
        // Создаем ускорение, равное value-вектору, scaled в случайное (в дальнейшем - мб неслучайное) значение не более maxAcc/avgFieldValue раз
        Vector acc = avgFieldValue == 0 ? new Vector(0, 0) : robot.pos.value.dot(rand.nextDouble()*robot.maxAcc/avgFieldValue);
        // Добавляем к нему стохастический вектор, по модулю не больший, чем maxAcc*stochasticConstant
        acc.add(new Vector(robot.maxAcc*stochasticConstant*rand.nextDouble(), (rand.nextDouble()*2 - 1)*Math.PI));
        // Если вышло по модулю более, чем maxAcc, то уменьшаем до maxAcc
        if (acc.getValue() > robot.maxAcc)
            acc = new Vector(robot.maxAcc, acc.getAngle());
        return acc;
    }

    public Cell move (Cell[][] field, double dT, double stochasticConstant, double avgFieldValue, double remainingTime){    // Сделать так, чтобы от totalFieldValue зависело ускорение
        // The greater is proximity coefficient, the greater influence will the stochastic vector have onto the process.

        boolean wayIsClear;
        Vector acc, deltaPos;
        int counter = 0;
        do {
            counter ++;
            acc = stochasticAcceleration(this, stochasticConstant, avgFieldValue);
            deltaPos = new Vector(vel.dot(dT).plus(acc.dot(pow(dT, 2) / 2)));  // Using dP = v_0*t + at^2/2
            wayIsClear = deltaPos.wayIsClear(field, pos, robotDimension);
        } while (counter <= 15 && !wayIsClear/*pos.check(field, (int) Math.round(deltaPos.x), (int) Math.round(deltaPos.y))*/);
        // Почему-то deltaPos портится при передаче в реальное движение

        sequence.add(new RobotSequenceRecord(pos, acc, cargo));
        if (pos.type.equals("s")) {
            remainingTime -= cargo*shootingTime;
            cargo = 0;
            vel = new Vector(0, 0);
        }
        else if (pos.type.equals("l") && cargo < maxCargo) {
            boolean cargoIsFree = true;
            for (Cell temp : usedCargo) {
                if (temp.equals(pos))
                    cargoIsFree = false;
            }
            if (cargoIsFree) {
                cargo++;
                usedCargo.add(pos);
                cargo = cargo;
            }
        }
        vel.add(acc.dot(dT));



        if (remainingTime > dT && wayIsClear) {
            pos = field[pos.y + (int) deltaPos.y][pos.x + (int) deltaPos.x];
            return move(field, dT, stochasticConstant, avgFieldValue, remainingTime - dT);
            /*
            if (pos.check(field, (int) Math.round(deltaPos.x), (int) Math.round(deltaPos.y))) {
                pos = field[pos.y + (int) deltaPos.y][pos.x + (int) deltaPos.x];
                vel = new Vector(vel.plus(acc.dot(dT)));
            } else {
                running = false;
                vel = new Vector(vel.plus(acc.dot(dT)));
            }
            */
        }
        return pos;
    }
}
