package Code_14;

import java.util.ArrayList;
import java.util.List;

public class DirectedGraphNode {
    int label;
    List<DirectedGraphNode> neighbors;

    DirectedGraphNode(int x) {
        label = x;
        neighbors = new ArrayList<DirectedGraphNode>();
    }
}
