package Code_03;

public class SingleNode {
    public int value;
    public SingleNode next;
    public SingleNode(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof SingleNode && ((SingleNode) obj).value == this.value;
    }
}




