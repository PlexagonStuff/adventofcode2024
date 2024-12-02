package Day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class partOne {
    
    public static int findTotalDistance() {
        
        ArrayList<ArrayList<Integer>> rightList = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get("input2.txt"))) {
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                Scanner lineScanner = new Scanner(line);
                ArrayList<Integer> leftList = new ArrayList<>();
                while (lineScanner.hasNextInt()) {
                    leftList.add(lineScanner.nextInt());
                }
                rightList.add(leftList);
            }
            int totalDistance = 0;
            for (int i = 0; i < rightList.size(); i++) {
                totalDistance += checkSafety(rightList.get(i));
            }
            return totalDistance;
        }
        catch (Exception e) {
            System.out.println("File not read.");
        }
        return 0;
    }

    private static int checkSafety(ArrayList<Integer> reports) {
        Integer firstDistance = null;
        for (int i = 0; i < reports.size(); i++) {
            if (i+1 != reports.size()) {
                int distance = reports.get(i) - reports.get(i+1);
                if (!(Math.abs(distance) >= 1 && Math.abs(distance) < 4)) {
                    return 0;
                }
                if (firstDistance == null) {
                    firstDistance = distance;
                }
                else {
                    if (Math.signum(firstDistance.intValue()) != Math.signum(distance)){
                        return 0;
                    }
                }
            }
        }
        return 1;
    }

}


