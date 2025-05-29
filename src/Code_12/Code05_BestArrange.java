package Code_12;

import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Code05_BestArrange {

    public static int bestArrange(Program[] programs) {
        Arrays.sort(programs, new Comparator<Program>() {
            @Override
            public int compare(Program o1, Program o2) {
                return o1.end - o2.end;
            }
        });

        int maxResult = 0;
        int currentLine = 0;
        for (Program program : programs) {
            if (program.start >= currentLine) {
                maxResult++;
                currentLine = program.end;
            }
        }
        return maxResult;
    }

    public static int bestArrangeByBruteForce(Program[] programs) {
        if (programs.length == 0) return 0;
        return process(programs, 0, 0);
    }

    /**
     * @param programs other meeting
     * @param done     have arranged
     * @param timeLine the current timeLine
     * @return
     */
    public static int process(Program[] programs, int done, int timeLine) {
        if (programs.length == 0) return done;

        int max = done;
        for (int i = 0; i < programs.length; i++) {
            if (programs[i].start >= timeLine) {
                max = Math.max(max, process(removeByIndex(programs, i), done + 1, programs[i].end));
            }
        }
        return max;
    }

    private static Program[] removeByIndex(Program[] programs, int index) {
        if (index > programs.length - 1) {
            return programs;
        }
        ArrayList<Program> temp = new ArrayList<Program>();
        for (int i = 0; i < programs.length; i++) {
            if (i != index) {
                temp.add(programs[i]);
            }
        }
        return temp.toArray(new Program[0]);
    }

    // for test
    public static Program[] getRandomPrograms(int maxSize, int maxValue) {
        int size = NumberOperation.getRandomNumber(maxSize);
        Program[] programs = new Program[size];
        for (int i = 0; i < size; i++) {
            int start;
            do {
                start = NumberOperation.getRandomNumber(maxValue);
            } while (start >= maxValue - 2);
            int end;
            do {
                end = NumberOperation.getRandomNumber(maxValue);
            } while (end <= start);

            programs[i] = new Program(start, end);
        }
        return programs;
    }

    public static void printProgram(Program[] programs) {
        for (Program program : programs) {
            System.out.print("[" + program.start + ", " + program.end + "] ,");
        }
        System.out.println();
    }

    public static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 0; i < Constants.TEST_TIMES; i++) {
            System.out.println("start: the test time  is: " + i);
            Program[] randomPrograms = getRandomPrograms(10, 100);
            int bestArrangeByBruteForce = bestArrangeByBruteForce(randomPrograms);
            int bestArrange = bestArrange(randomPrograms);

            if (bestArrange != bestArrangeByBruteForce) {
                printProgram(randomPrograms);
                System.out.println("bestArrange: " + bestArrange + " bestArrangeByBruteForce: " + bestArrangeByBruteForce);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

    private static class Program {
        int start;
        int end;

        public Program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

}
















