package LeetCode.todo;


public class Code2327_NumberOfPeople {

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        return 0;
    }

    private int process(int current, int delay, int forget) {
        if (current <= delay) return 1;

        if (current <= forget) {
            return process(current - delay, delay, forget) + process(current - 1, delay, forget);
        }
        process(current - 1, delay, forget);
        return process(current - 1, delay, forget);
    }

    public static void main(String[] args) {

    }
}
