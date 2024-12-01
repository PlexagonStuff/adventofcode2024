package Day1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class partTwo {
    
    public static int findTotalSimilarity() {
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get("input1.txt"))) {
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                Scanner lineScanner = new Scanner(line);
                leftList.add(lineScanner.nextInt());
                rightList.add(lineScanner.nextInt());
            }
            int similarityScore = 0;
            for (int i = 0; i < leftList.size(); i++) {
                similarityScore += (findAllOccurances(rightList, leftList.get(i)) * leftList.get(i));
            }

            return similarityScore;
        }
        catch (Exception e) {
            System.out.println("File not read.");
        }
        return 0;
    }

    private static int findAllOccurances(ArrayList<Integer> list, int match) {
        int total = 0;
        for (int i = 0; i < list.size(); i++) {
            if ((int)list.get(i) == match) {
                total++;
            }
        }
        return total;
    }

}
