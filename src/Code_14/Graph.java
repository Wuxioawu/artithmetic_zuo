package Code_14;

import java.util.HashMap;
import java.util.HashSet;

// node and edges make up the all graph
public class Graph {

    public HashMap<Integer, Node> nodes;
    public HashSet<Edge> edges;
    public Node start;

    public Graph() {
        nodes = new HashMap<>();
        edges = new HashSet<>();
    }
}
