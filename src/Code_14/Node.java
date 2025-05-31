package Code_14;

import java.util.ArrayList;
import java.util.List;

public class Node {
    int value;

    int inEdges;
    int outEdges;
    List<Node> nextNode;
    List<Edge> nextEdges;

    public Node(int value) {
        this.value = value;
        this.inEdges = 0;
        this.outEdges = 0;
        nextNode = new ArrayList<>();
        nextEdges = new ArrayList<>();
    }

}
