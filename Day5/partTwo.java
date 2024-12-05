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



public class partTwo {
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
                //System.out.println(line);
                //S//ystem.out.println(totalScore);
                String[] values = line.split(",");
                //System.out.println(Arrays.toString(values));
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
                ArrayList<Integer> sortedThing = new ArrayList<>();
                if (!correct) {
                    System.out.println(Arrays.toString(values));
                    sortedThing.add(Integer.parseInt(values[0]));
                    for (int i = 1; i < values.length; i++) {
                        int value = Integer.parseInt(values[i]);
                        int z;
                        Ordering priority = pages.get(value);
                        System.out.println(priority.belowPriority);
                        System.out.println(priority.abovePriority);
                        //Start from bottom and check until a guaranteed lower priority
                        for (z = sortedThing.size()-1;  z > 0; z--) {
                            if (!priority.belowPriority.contains(sortedThing.get(z))) {
                                //If something is no longer below priority, then it must be evaluated as to whether it has above priority
                                break;
                            }
                        }
                        System.out.println(z);
                        for (z = z; z < sortedThing.size(); z++) {
                            if (priority.abovePriority.contains(sortedThing.get(z))) {
                                //This pushes that value above the newly added item because it has above priority
                                sortedThing.add(z+1, value);
                                break;
                            }
                        }
                        //If the value is not in the array, that means that everything is below it and nothing is above it, so insert at the top
                        if (!sortedThing.contains(value)) {
                            sortedThing.add(0,value);
                        }
                        
                    }
                    System.out.println(sortedThing);
                    totalScore += sortedThing.get(sortedThing.size() / 2);
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