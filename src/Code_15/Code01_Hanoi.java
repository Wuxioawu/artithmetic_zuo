package Code_15;

import tools.Constants;
import tools.NumberOperation;

import java.util.ArrayList;
import java.util.List;

public class Code01_Hanoi {

    private final static int CONSTANT_LEFT = 1;
    private final static int CONSTANT_RIGHT = 2;
    private final static int CONSTANT_MID = 3;

    // from left to right

    private static void leftToRight(int n, List<Result> resultList, boolean startPrint) {
        if (n == 1) {
            if (startPrint) {
                System.out.println("Move 1 from left to right");
            }
            resultList.add(new Result(1, CONSTANT_LEFT, CONSTANT_RIGHT));
            return;
        }
        leftToMid(n - 1, resultList, startPrint);

        if (startPrint) {
            System.out.println("Move " + n + " from left to right");
        }

        resultList.add(new Result(n, CONSTANT_LEFT, CONSTANT_RIGHT));
        midToRight(n - 1, resultList, startPrint);
    }

    private static void midToRight(int n, List<Result> resultList, boolean startPrint) {
        if (n == 1) {
            if (startPrint) {
                System.out.println("Move 1 from mid to right");
            }
            resultList.add(new Result(1, CONSTANT_MID, CONSTANT_RIGHT));
            return;
        }

        midToLeft(n - 1, resultList, startPrint);
        if (startPrint) {
            System.out.println("Move " + n + " from mid to right");
        }
        resultList.add(new Result(n, CONSTANT_MID, CONSTANT_RIGHT));
        leftToRight(n - 1, resultList, startPrint);
    }

    private static void midToLeft(int n, List<Result> resultList, boolean startPrint) {
        if (n == 1) {
            if (startPrint) {
                System.out.println("Move 1 from mid to left");
            }
            resultList.add(new Result(1, CONSTANT_MID, CONSTANT_LEFT));
            return;
        }

        midToRight(n - 1, resultList, startPrint);
        if (startPrint) {
            System.out.println("Move " + n + " from mid to left");
        }
        resultList.add(new Result(n, CONSTANT_MID, CONSTANT_LEFT));
        rightToLeft(n - 1, resultList, startPrint);
    }

    private static void rightToLeft(int n, List<Result> resultList, boolean startPrint) {
        if (n == 1) {
            if (startPrint) {
                System.out.println("Move 1 from right to left");
            }
            resultList.add(new Result(1, CONSTANT_RIGHT, CONSTANT_LEFT));
            return;
        }
        rightToMid(n - 1, resultList, startPrint);

        if (startPrint) {
            System.out.println("Move " + n + " from right to left");
        }

        resultList.add(new Result(n, CONSTANT_RIGHT, CONSTANT_LEFT));
        midToLeft(n - 1, resultList, startPrint);
    }

    private static void rightToMid(int n, List<Result> resultList, boolean startPrint) {
        if (n == 1) {
            if (startPrint) {
                System.out.println("Move 1 from right to mid");
            }
            resultList.add(new Result(1, CONSTANT_RIGHT, CONSTANT_MID));
            return;
        }

        rightToLeft(n - 1, resultList, startPrint);

        if (startPrint) {
            System.out.println("Move " + n + " from right to mid");
        }

        resultList.add(new Result(n, CONSTANT_RIGHT, CONSTANT_MID));
        leftToMid(n - 1, resultList, startPrint);

    }

    private static void leftToMid(int n, List<Result> resultList, boolean startPrint) {
        if (n == 1) {
            if (startPrint) {
                System.out.println("Move 1 from left to mid");
            }
            resultList.add(new Result(1, CONSTANT_LEFT, CONSTANT_MID));
            return;
        }

        leftToRight(n - 1, resultList, startPrint);
        if (startPrint) {
            System.out.println("Move " + n + " from left to mid");
        }

        resultList.add(new Result(n, CONSTANT_LEFT, CONSTANT_MID));
        rightToMid(n - 1, resultList, startPrint);
    }

    private static int findTheNum(String str) {
        if (str.contains("left")) return CONSTANT_LEFT;
        if (str.contains("right")) return CONSTANT_RIGHT;
        if (str.contains("mid")) return CONSTANT_MID;

        return -1;
    }

    // define a variable to represent a left
    private static void summary(int n, String from, String to, String other, List<Result> resultList, boolean startPrint) {
        if (n == 1) {
            if (startPrint) {
                System.out.println("Move 1 from " + from + " to " + to);
            }
            resultList.add(new Result(1, findTheNum(from), findTheNum(to)));
            return;
        }

        summary(n - 1, from, other, to, resultList, startPrint);

        if (startPrint) {
            System.out.println("Move " + n + " from " + from + " to " + to);
        }

        resultList.add(new Result(n, findTheNum(from), findTheNum(to)));
        summary(n - 1, other, to, from, resultList, startPrint);
    }

    static class Result {
        int order;
        int from;
        int to;

        public Result(int order, int from, int to) {
            this.order = order;
            this.from = from;
            this.to = to;
        }

        @Override
        public boolean equals(Object obj) {
            return order == ((Result) obj).order && from == ((Result) obj).from && to == ((Result) obj).to;
        }
    }

    private static boolean checkResultList(List<Result> list1, List<Result> list2) {
        if (list1.isEmpty() && list2.isEmpty()) return true;
        if (list1.isEmpty() || list2.isEmpty()) return false;

        if (list1.size() != list2.size()) return false;

        for (int i = 0; i < list1.size(); i++) {
            Result r1 = list1.get(i);
            Result r2 = list2.get(i);

            if (!r1.equals(r2)) {
                return false;
            }
        }
        return true;
    }

    private static void test() {
        System.out.println(Constants.START_TEST);
        for (int i = 1; i <= 100; i++) {
            int random = NumberOperation.getRandomNumber(20) + 1;
            ArrayList<Result> result_1 = new ArrayList<>();
            ArrayList<Result> result_2 = new ArrayList<>();

            leftToRight(random, result_1, false);
            summary(random, "left", "right", "mid", result_2, false);

            if (!checkResultList(result_1, result_2)) {
                System.out.println(Constants.CODE_ERROR);
                return;
            }
        }
        System.out.println(Constants.TEST_FINISH);
    }

    public static void main(String[] args) {
        test();
    }

}
