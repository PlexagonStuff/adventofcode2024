package Day5;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class partOne {
    public static int findTotalDistance() {
        HashMap<Integer,Ordering> pages = new HashMap<>();
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get("input5.txt"))) {
            String line = null;
            while (!(line = fileReader.readLine()).equals("")) {
                Pattern multRegex = Pattern.compile("\\d+");
                Matcher multFinder;
                multFinder = multRegex.matcher(line);
                multFinder.find();
                int key = Integer.parseInt(multFinder.group());
                multFinder.find();
                int lesser = Integer.parseInt(multFinder.group());
                if (!pages.containsKey(key)) {
                    pages.put(key, new Ordering());
                    pages.get(key).belowPriority.add(lesser);
                }
                else if (pages.containsKey(key)) {
                    pages.get(key).belowPriority.add(lesser);
                }
                if (!pages.containsKey(lesser)) {
                    pages.put(lesser, new Ordering());
                    pages.get(lesser).abovePriority.add(key);
                }
                else if (pages.containsKey(lesser)) {
                    pages.get(lesser).abovePriority.add(key);
                }
            }
            System.out.println(pages);
            int totalScore = 0;
            while ((line = fileReader.readLine()) != null) {
                System.out.println(line);
                System.out.println(totalScore);
                String[] values = line.split(",");
                System.out.println(Arrays.toString(values));
                boolean correct = true;
                for (int i = 0; i < values.length; i++) {
                    int value = Integer.parseInt(values[i]);
                    for (int z = i; z < values.length; z++) {
                        Ordering priority = pages.get(Integer.parseInt(values[z]));
                        if (priority.belowPriority.contains(value)) {
                            correct = false;
                        }
                    }
                    for (int z = i; z > -1; z--) {
                        Ordering priority = pages.get(Integer.parseInt(values[z]));
                        if (priority.abovePriority.contains(value)) {
                            correct = false;
                        }
                    }
                }
                if (correct) {
                    totalScore += Integer.parseInt(values[values.length / 2]);
                }
            }
            return totalScore;
        }
        catch (Exception e) {
            System.out.println("File not read.");
        }
        return 0;
    }

}