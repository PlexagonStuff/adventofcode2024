package Day3;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class partOne {
    
     public static int findTotalMultiplications() {
        
        String input = null;
        //Created regex to find the correct mults
        Pattern multRegex = Pattern.compile("mul[(]\\d+,\\d+[)]");
        Matcher multFinder;
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get("input3.txt"))) {
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                input += line;
            }
            multFinder = multRegex.matcher(input);
            int totalDistance = 0;
            while (multFinder.find() != false) {
                System.out.println(multFinder.group());
                Pattern pattern = Pattern.compile("\\d+");
                Matcher matcher = pattern.matcher(multFinder.group());
                matcher.find();
                int firstArg = Integer.parseInt(matcher.group());
                matcher.find();
                int secondArg = Integer.parseInt(matcher.group());
                totalDistance += (firstArg * secondArg);
            }
            return totalDistance;
        }
        catch (IOException e) {
            System.out.println("File not read.");
        }
        return 0;
    }
}
