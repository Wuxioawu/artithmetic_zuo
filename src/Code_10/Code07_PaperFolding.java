package Code_10;

public class Code07_PaperFolding {


    public static void printPaperFolding(int n) {
        if (n <= 0) return;

        process(1, n, true);

    }

    public static void process(int currentLayer, int maxLayer, boolean isLeft) {
        if (currentLayer > maxLayer) {
            return;
        }
        process(currentLayer + 1, maxLayer, true);
        System.out.print(isLeft ? " down " : " up ");
        process(currentLayer + 1, maxLayer, false);
    }

    public static void main(String[] args) {
        printPaperFolding(3);
    }

}
