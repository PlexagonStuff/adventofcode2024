package Day4;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class partTwo {
    static ArrayList<ArrayList<Character>> wordSearch = new ArrayList<>();
    public static int findTotalDistance() {
        try (BufferedReader fileReader = Files.newBufferedReader(Paths.get("input4.txt"))) {
            String line = null;
            while ((line = fileReader.readLine()) != null) {
                ArrayList<Character> temp = new ArrayList<>();
                for (int i = 0; i < line.length(); i++) {
                    temp.add(line.charAt(i));
                }
                wordSearch.add(temp);
            }
            int totalXMASFound = 0;
            for (int y = 0; y < wordSearch.size(); y++) {
                for (int x = 0; x < wordSearch.get(y).size(); x++) {
                    if (wordSearch.get(y).get(x) == 'A') {
                        totalXMASFound += findXMAS(y,x);
                    }
                }
            }
            return totalXMASFound;
        }
        catch (Exception e) {
            System.out.println("File not read.");
        }
        return 0;
    }

    private static int findXMAS(int row, int column) {
        int correctChecks = 0;
        char checkOne;
        if (validLocation(row+1, column+1)) {
            checkOne = wordSearch.get(row+1).get(column+1);
            if (validLocation(row-1, column-1)) {
                if (checkOne == 'M' && wordSearch.get(row-1).get(column-1) == 'S') {
                    correctChecks++;
                }
                else if ((checkOne == 'S' && wordSearch.get(row-1).get(column-1) == 'M')) {
                    correctChecks++;
                }
            }
        }
        if (validLocation(row+1, column-1)) {
            checkOne = wordSearch.get(row+1).get(column-1);
            if (validLocation(row-1, column+1)) {
                if (checkOne == 'M' && wordSearch.get(row-1).get(column+1) == 'S') {
                    correctChecks++;
                }
                else if ((checkOne == 'S' && wordSearch.get(row-1).get(column+1) == 'M')) {
                    correctChecks++;
                }
            }
        }
        return (correctChecks / 2);
    }

    private static int findForwards(int row, int column) {
        if (validLocation(row, column+1) && wordSearch.get(row).get(column+1) == 'M') {
            if (validLocation(row, column+2) &&wordSearch.get(row).get(column+2) == 'A') {
                if (validLocation(row, column+3) && wordSearch.get(row).get(column+3) == 'S') {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static int findBackwards(int row, int column) {
        if (validLocation(row, column-1) && wordSearch.get(row).get(column-1) == 'M') {
            if (validLocation(row, column-2) &&wordSearch.get(row).get(column-2) == 'A') {
                if (validLocation(row, column-3) && wordSearch.get(row).get(column-3) == 'S') {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static int findAbove(int row, int column) {
        if (validLocation(row-1, column) && wordSearch.get(row-1).get(column) == 'M') {
            if (validLocation(row-2, column) &&wordSearch.get(row-2).get(column) == 'A') {
                if (validLocation(row-3, column) && wordSearch.get(row-3).get(column) == 'S') {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static int findBelow(int row, int column) {
        if (validLocation(row+1, column) && wordSearch.get(row+1).get(column) == 'M') {
            if (validLocation(row+2, column) &&wordSearch.get(row+2).get(column) == 'A') {
                if (validLocation(row+3, column) && wordSearch.get(row+3).get(column) == 'S') {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static int findNE(int row, int column) {
        if (validLocation(row-1, column+1) && wordSearch.get(row-1).get(column+1) == 'M') {
            if (validLocation(row-2, column+2) &&wordSearch.get(row-2).get(column+2) == 'A') {
                if (validLocation(row-3, column+3) && wordSearch.get(row-3).get(column+3) == 'S') {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static int findNW(int row, int column) {
        if (validLocation(row-1, column-1) && wordSearch.get(row-1).get(column-1) == 'M') {
            if (validLocation(row-2, column-2) &&wordSearch.get(row-2).get(column-2) == 'A') {
                if (validLocation(row-3, column-3) && wordSearch.get(row-3).get(column-3) == 'S') {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static int findSW(int row, int column) {
        if (validLocation(row+1, column-1) && wordSearch.get(row+1).get(column-1) == 'M') {
            if (validLocation(row+2, column-2) &&wordSearch.get(row+2).get(column-2) == 'A') {
                if (validLocation(row+3, column-3) && wordSearch.get(row+3).get(column-3) == 'S') {
                    return 1;
                }
            }
        }
        return 0;
    }

    private static int findSE(int row, int column) {
        if (validLocation(row+1, column+1) && wordSearch.get(row+1).get(column+1) == 'M') {
            if (validLocation(row+2, column+2) &&wordSearch.get(row+2).get(column+2) == 'A') {
                if (validLocation(row+3, column+3) && wordSearch.get(row+3).get(column+3) == 'S') {
                    return 1;
                }
            }
        }
        return 0;
    }




    private static boolean validLocation(int row, int column) {
        if (row < 0 || row >= wordSearch.size() || column < 0 || column >= wordSearch.get(row).size()) {
            return false;
        }
        return true;
    }

}



