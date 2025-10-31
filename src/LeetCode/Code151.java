package LeetCode;

public class Code151 {

    public String reverseWords(String s) {
        String[] split = s.split(" ");
        StringBuffer sb = new StringBuffer();
        for (int i = split.length - 1; i >= 0; i--) {
            if (!split[i].isEmpty()) {
                sb.append(split[i]);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {
        String s = "  hello  world  ";
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            System.out.println("[" + split[i] + "]");
        }
    }
}
