import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;


public class AlliedRobot {

    private Cell[][] initialField, field;
    private final double marginOfTimeError = 3;
    private final double defaultTactTime = 1.0;
    Cell pos;
    ArrayList <AllieSequenceRecord> sequence;
    double avgVel, shootingTime;

    public AlliedRobot (Cell[][] field, String teamNum, int cargo, double... tactTimeHolder) {
        this.field = field;
        this.initialField = field;
        double tactTime = tactTimeHolder.length == 0 ? defaultTactTime : tactTimeHolder[0];

        Scanner in = new Scanner("");
        try {
            in = new Scanner(new File("Allies_strategies/" + teamNum + ".txt"));
        }
        catch (Exception e) {}
        String currLine;

        in.nextInt();
        avgVel = in.nextDouble();
        shootingTime = in.nextDouble();

        sequence = new ArrayList<AllieSequenceRecord>();

        in.nextLine();
        while (true) {
            currLine = in.nextLine().replaceAll("\\(", "").replaceAll(",", "").replaceAll("\\)" , " ");
            Scanner scannedLine = new Scanner(currLine);
            int [] coords = new int[2];
            String action = "";

            try {
                coords[1] = scannedLine.nextInt();
                coords[0] = scannedLine.nextInt();
            } catch (Exception e){
                break;
            }
            if (scannedLine.hasNext()) {
                action = scannedLine.next();
                if (action.equals("l"))
                    cargo ++;
                else if (action.equals("s")) {
                    cargo = 0;
                }
            }

            sequence.add(new AllieSequenceRecord(field[coords[1]][coords[0]], cargo));

            System.out.println(coords[0] + ", " + coords[1] + " " + action);
        }

        double time = 0;
        sequence.get(0).time = 0;
        for (int i = 1; i < sequence.size(); ++i) {
            AllieSequenceRecord prevNode = sequence.get(i-1), currNode = sequence.get(i);
            time += Math.sqrt (Math.pow(currNode.cell.x - prevNode.cell.x, 2) + Math.pow(currNode.cell.y - prevNode.cell.y, 2))/avgVel;
            currNode.time = time;
        }

        in.close();

    }


    public Cell[][] leaveMarkOnField (double currTime) {

        double tempTime;
        AllieSequenceRecord lateBoundary;
        int lateBoundaryIndex = sequence.size();
        do {
            lateBoundaryIndex--;
            lateBoundary = sequence.get(lateBoundaryIndex);
            tempTime = lateBoundary.time;
            if (tempTime <= currTime + marginOfTimeError) {
                break;
            }
        } while (tempTime > 0);

        AllieSequenceRecord earlyBoundary;
        int earlyBoundaryIndex = -1;
        do {
            earlyBoundaryIndex++;
            earlyBoundary = sequence.get(earlyBoundaryIndex);
            tempTime = earlyBoundary.time;
            if (tempTime >= currTime - marginOfTimeError || earlyBoundaryIndex == lateBoundaryIndex) {
                break;
            }
        } while (tempTime < currTime);



        for (int i = 0; i < earlyBoundaryIndex; ++i) {
            AllieSequenceRecord currNode = sequence.get(i), nextNode = sequence.get(i+1);
            Vector totalDisplacement= Vector.CreateFromCartesian(nextNode.cell.x - currNode.cell.x, nextNode.cell.y - currNode.cell.y);
            Vector increment = totalDisplacement.dot(1/(nextNode.time - currNode.time));

            if (increment.getValue() > 0.75)
                increment = new Vector(0.75, increment.getAngle());



            for (Vector currDisplacement = new Vector(0, 0); currDisplacement.getValue() < totalDisplacement.getValue(); currDisplacement.add(increment)){
                Cell currCell = currNode.cell.shiftBy(field, currDisplacement);
                if (!currCell.checkBeingInsideField(field, 0, 0))
                    break;
                if (currCell.type.contains("r"))
                    currCell.type = currCell.type.replaceAll("r", "");
            }
        }

        for (int i = earlyBoundaryIndex; i < lateBoundaryIndex - 1; ++i) {
            AllieSequenceRecord currNode = sequence.get(i), nextNode = sequence.get(i+1);
            Vector totalDisplacement= Vector.CreateFromCartesian(nextNode.cell.x - currNode.cell.x, nextNode.cell.y - currNode.cell.y);
            Vector increment = totalDisplacement.dot(1/(nextNode.time - currNode.time));

            if (increment.getValue() > 0.75)
                increment = new Vector(0.75, increment.getAngle());



            for (Vector currDisplacement = new Vector(0, 0); currDisplacement.getValue() < totalDisplacement.getValue(); currDisplacement.add(increment)){
                Cell currCell = currNode.cell.shiftBy(field, currDisplacement);
                if (!currCell.checkBeingInsideField(field, 0, 0))
                    break;
                if (!currCell.type.contains("r"))
                    currCell.type = currCell.type.concat("r");
            }
        }


        // Enable the following if you suspect that this function isn't working properly.
        // Look at when and where the allie paths are marked
        /*for (int i = 0; i < field.length; ++i) {
            for (int j = 0; j < field[i].length; ++j)
                System.out.print(field[i][j]);
            System.out.println();
        }*/


        return field;
    }

    public Cell[][] clearTraces () {
        field = initialField;
        return field;
    }

    public Cell[][] markWholePath () {
        for (int i = 0; i < sequence.size() - 1; ++i) {
            AllieSequenceRecord currNode = sequence.get(i), nextNode = sequence.get(i+1);
            Vector totalDisplacement= Vector.CreateFromCartesian(nextNode.cell.x - currNode.cell.x, nextNode.cell.y - currNode.cell.y);
            Vector increment = totalDisplacement.dot(1/(nextNode.time - currNode.time));

            if (increment.getValue() > 0.75)
                increment = new Vector(0.75, increment.getAngle());



            for (Vector currDisplacement = new Vector(0, 0); currDisplacement.getValue() < totalDisplacement.getValue(); currDisplacement.add(increment)){
                Cell currCell = currNode.cell.shiftBy(field, currDisplacement);

                if (!currCell.checkBeingInsideField (field, 0, 0))
                    break;
                currCell.type = "r";
            }
        }

        return field;
    }
}
