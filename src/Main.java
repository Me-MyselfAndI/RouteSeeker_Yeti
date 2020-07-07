import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        Random rand = new Random();

        final double cargoConstant = 0.8;   // Strategic value of having one more cargo
        final double matchTime = 15;        // Match time in seconds
        final double timePrecision = 15;    // This is how many tacts there are in one match
        final double wallConstant = 0;      // The greater is this constant, the more points robot loses for bumping into a wall
        final double stochasticConstant = 0.4;   // This determines how much influence probability vector has
        final double startLineIncrement = 0.01;  // This determines how much the program adds to the good starting cells and subtracts from the bad ones
        final Cell[][] field = {        // w = Wall/obstacle;    l = Loading station;    s = suitable to Shoot;     i = Initiation line
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('l'), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('l'), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('l'), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('l'), new Cell(' '), new Cell('l'), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell('w'), new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell('w'), new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell('w'), new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('i'), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w')},
                {new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w')},
                {new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell('s'), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell(' '), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w')},
                {new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w'), new Cell('w')}
        };

        // This is an array for counting all nonzero cells.
        boolean [][] valueIsZero = new boolean[field.length][field[0].length];

        // Setting of all values of coordinates for the cells; also sets up zero value array.
        for (int i = 0; i < field[0].length; ++i) {
            for (int j = 0; j < field.length; ++j) {
                field[j][i].x = i;
                field[j][i].y = j;
                valueIsZero[j][i] = true;
            }
        }


        int startLine = 0;              // This needs to be outside of the loop, in order to be accessible later
        for (; startLine < field.length && field[startLine][1].type != 'i'; ++startLine);  // This finds startLine


        // The following is an array storing data of how valuable each cell on the starting line is for being the starting cell.
        double[] startLineValues = new double[field[startLine].length];


        // Assigns initial values to the startLine. These are just scalars that indicate
        // the likelihood of getting many points if started the game from the respective cell
        for (int i = 1; i < startLineValues.length - 1; ++i) {
            startLineValues[i] = 1;
        }
        // First and last cells are walls => the robot should not consider starting from there, so make their values zeros.
        startLineValues[0] = 0;
        startLineValues[startLineValues.length - 1] = 0;

        // This array stores how many times did the robot start at every initial cell
        double totalStartLineValue = startLineValues.length - 2;

        double bestScore = -1;      // This stores the best achieved score out of alltrials
        ArrayList<RobotSequenceRecord> bestSequence = new ArrayList<>();   // This stores the sequence that achieved best possible score
        String outputOfBestSequence = "";       // This stores the output of the best sequence

        long totalScore = 0;            // This scores the total of all scores up to the current trial

        // This stores total (NOT net) value of all cells.
        long totalValue = 0;
        int nonzeroTrialsAmount = 0;
        double avgScoreForNonzeroTrials = 0;
        for (int trial = 1; trial <= 1000000; ++trial) {         // Makes 800000 trials

            if (trial % 20000 == 1) {
                // This whole thing inside the brackets is needed to make program faster.
                // When total score reaches some particular mark, every cell's value gets divided by a constant.
                // This lets the program avoid getting enormously slow close to 500,000th trial


                for (int i = 1; i < startLineValues.length - 1; ++i) startLineValues[i] = 1;
                startLineValues[0] = 0;
                startLineValues[startLineValues.length - 1] = 0;

                for (int i = 1; i < startLineValues.length - 1; ++i) {
                    startLineValues[i] = 1;
                }
                totalStartLineValue = startLineValues.length - 2;


                /*for (int i = 0; i < field[0].length; ++i) {         //  This divides every value by 200
                    for (int j = 0; j < field.length; ++j) {        //
                        field[j][i].value = field[j][i].value.dot(0.001);    //
                    }
                }

                */
                //totalScore = 0;     // We will count the total score from start since now, since its only use is to trigger this if-statement above

            }


            double randomStartLineValue = rand.nextDouble()*totalStartLineValue;
            if (randomStartLineValue == totalStartLineValue || randomStartLineValue == 0) {
                System.out.println("Alert");
            }
                //  - Создать случайное число не более суммарного значения клеток
                //  - Цикл for, пока счетсик не достигнет этого числа
                //  - Счетчик каждый раз растет на startLineValues[i]
                //  -
                //  -
                //  -

            int startCellXCoord;
            for (startCellXCoord = startLineValues.length - 1; startCellXCoord > 0 && randomStartLineValue >= 0.05; randomStartLineValue -= startLineValues[startCellXCoord]){
                startCellXCoord --;
                if (startCellXCoord < 2)
                    System.out.println("Look closer");
            }

            /*
            for (int counter = (int) (rand.nextDouble()*totalStartLineValue); counter > 0 && startCellXCoord > 0; counter -= startLineValues[startCellXCoord]) {
                startCellXCoord --;
            }

            startCellXCoord --;

            */

            System.out.println(startCellXCoord);
            if (startCellXCoord < 1 || startCellXCoord > startLineValues.length - 1) {
                System.out.println("Alert");
            }


            // The following block is to calculate the total amount of nonzero-valued cells.
            // It will be used for calculating the avg field value among nonzero cells
            int totalNonzeroValues = 0;
            for (boolean[] i:valueIsZero) {
                for (boolean j:i) {
                    if (!j) totalNonzeroValues ++;
                }
            }


            // Creating the robot. The long expression of rand.nextInt(...) picks random cell except for the first and last one.
            Robot robot = new Robot (new Cell (startLine, startCellXCoord), 30, 1, 3);
            double increment = matchTime/timePrecision; // This is an increment of time each "turn"
            System.out.println("\n\n\t\tTrial #" + trial);


            robot.move(field, increment, stochasticConstant, totalValue/(totalNonzeroValues + 1), timePrecision);
            RobotSequenceRecord bumpedInto = robot.sequence.get(robot.sequence.size() - 1);   // This cell will be used to penalize behaviour that led to bumping into the wall


            System.out.println("End.");
            double score = cargoConstant*robot.cargo;          // Here you should calculate what is a strategical value of having one more cargo already loaded at the end of the autonomous.
            String output = "";

            //Здесь должно быть начисление очков
            double[] scoreMarks = new double[robot.sequence.size()];
            int cargo = 3;
            for (int i = 0; i < robot.sequence.size(); ++i) {
                Cell currCell = robot.sequence.get(i).cell;

                output = output.concat("(" + currCell.x + "; " + currCell.y + ") -->\n");

                // If the robot is on the loading kind of cell, and there is enough space to load, it should do that. Cargo increases by 1.
                if (currCell.type == 'l' && cargo < robot.maxCargo) {
                    cargo ++;
                    output = output.concat("l ");
                }
                else while (currCell.type == 's' && cargo > 0) {    // Similarly, if there is a place to shoot, the score grows, and load decreases
                    cargo --;
                    output = output.concat("s ");
                    score += 3;
                }
                scoreMarks[i] = score;  // Record the running score into scoreMarks of the current cell
            }
            if (cargo != robot.cargo)
                for (int i : new int [] {1, 1, 1})
                    System.out.println("!!!ALERT!!!\n\n");

            System.out.println(output);
            totalScore += score;    // Increasing the total score of all trials by this score


            if (score != cargo*cargoConstant) {
                avgScoreForNonzeroTrials = avgScoreForNonzeroTrials*nonzeroTrialsAmount + score;
                nonzeroTrialsAmount ++;
                avgScoreForNonzeroTrials/= nonzeroTrialsAmount;
            }







            if (score > bestScore || (score == bestScore && robot.sequence.size() < bestSequence.size())) {    // This is for recording the best possible sequence
                bestScore = score;
                bestSequence = robot.sequence;
                outputOfBestSequence = output;
            }

            // Ниже должно быть начисление весов (Привет, Даня. Тут нечего переводить, всё ценное написано по-английски)



            // Here we will reassign values.
            for (int i = robot.sequence.size() - 1; i > 1; --i) {       // DO NOT mutate values of the starting cell here.
                                                                        // It is useless and leads to overwhelmingly high
                                                                        // numbers on the starting line
                RobotSequenceRecord currNode = robot.sequence.get(i);



                totalValue -= currNode.cell.value.getValue();    // Every time we assign scores, the value changes. So, we subtract it here and add back the new after the value of the cell is changed
                currNode.cell.mutateValueAVG (Vector.CreateFromCortesian(currNode.acc.x, currNode.acc.y).dot(scoreMarks[i]));
                if (currNode.cell.value.getValue() > 1000) {
                    System.out.println("Alert");
                }
                totalValue += currNode.cell.value.getValue();

                // Because the chance that some cell became zero after some number of changes is disappearingly small and
                // doesn't affect anything, unless happens too often, it is ok to just mark any cell that ever got changed as nonzero
                // for the sake of reducing O(n^2) of checking each cell for its value to O(n) of the following operation
                valueIsZero[currNode.cell.y][currNode.cell.x] = false;

            }

            double tempDiff = startLineValues[robot.sequence.get(0).cell.x] * (score > avgScoreForNonzeroTrials ? (startLineValues[robot.sequence.get(0).cell.x] > totalStartLineValue/20 ? 0 : 0.2) : (startLineValues[robot.sequence.get(0).cell.x] > totalStartLineValue/100 ? -0.001 : 0));
            startLineValues[robot.sequence.get(0).cell.x] += tempDiff;
            totalStartLineValue += tempDiff;

            /*
            startLineValues[robot.sequence.get(0).cell.x] += score > 1.0 * totalScore / trial ? startLineIncrement : (startLineValues[robot.sequence.get(0).cell.x] > 0.2 ? -startLineIncrement : 0);
            totalStartLineValue += score > 1.0 * totalScore / trial ? startLineIncrement : (startLineValues[robot.sequence.get(0).cell.x] > 0.2 ? -startLineIncrement : 0);
            */

            //
            if (robot.sequence.size() > 1) {
                RobotSequenceRecord lastNode = robot.sequence.get(robot.sequence.size() - 1);

                totalValue -= lastNode.cell.value.getValue();
                lastNode.cell.mutateValueAVG (Vector.CreateFromCortesian(-lastNode.acc.x, -lastNode.acc.y).dot((timePrecision-robot.sequence.size())*wallConstant));
                System.out.println();
                totalValue += lastNode.cell.value.getValue();
            }

            if (trial % 1000 <= 3) {
                for (int i = 0; i < field[0].length; ++i) {
                    for (int j = 0; j < field.length; ++j) {
                        System.out.print(field[j][i].value);
                    }
                    System.out.println();
                }
            }



            // Here we reevaluate the best possible value for the stochasticConstant
            // The idea is, the faster the score grows, the better is the coefficient.
            // We use the function: (last 60 trials avg score - previous 60 trials avg score) / 60 is how much the stochasticConstant grows
            //
            // Same with wallConstant, but 80 instead of 60, as these have to be different numbers, not divisible by one another

            System.out.println("Score: " + score);
            System.out.println("Average score per trial: " + ((double)Math.round(1000.0*totalScore/trial))/1000);
            System.out.println("Average score per nonzero trial: " + ((double)Math.round(10000.0*avgScoreForNonzeroTrials))/10000);

        }
        System.out.println("The best sequence yields " + bestScore + " points (not accounting for escaping the vertical volume of the initial line):");
        System.out.println(outputOfBestSequence);
    }
}
