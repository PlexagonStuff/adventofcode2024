package Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PriorityQueue;
import java.util.Scanner;

public class partOne {
    
    public static int findTotalDistance() {
        PriorityQueue<Integer> leftList = new PriorityQueue<>();
        PriorityQueue<Integer> rightList = new PriorityQueue<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get("input1.txt"))) {
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                Scanner lineScanner = new Scanner(line);
                leftList.add(lineScanner.nextInt());
                rightList.add(lineScanner.nextInt());
            }
            int totalDistance = 0;
            while (!leftList.isEmpty()) {
                totalDistance += (Math.abs(leftList.poll()- rightList.poll()));
            }
            return totalDistance;
        }
        catch (Exception e) {
            System.out.println("File not read.");
        }
        return 0;
    }

}
