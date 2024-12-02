package Day2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class partTwo {
    
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
            
            System.out.println(rightList);
            for (int i = 0; i < rightList.size(); i++) {
                //Create every possible combination of the report and then checks each for safety individual. O(n^2)
                int secondTotalDistance = 0;
                ArrayList<ArrayList<Integer>> tacos = new ArrayList<>();
                tacos.add(rightList.get(i));
                for (int z = 0; z < rightList.get(i).size(); z++) {
                    ArrayList<Integer> secondTaco = (ArrayList<Integer>)rightList.get(i).clone();
                    secondTaco.remove(z);
                    tacos.add(secondTaco);
                }
                System.out.println(tacos);
                for (int j = 0; j < tacos.size(); j++) {
                    secondTotalDistance += checkSafety(tacos.get(j));
                }
                if (secondTotalDistance >= 1) {
                    totalDistance++;
                }
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


