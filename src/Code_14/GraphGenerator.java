package Code_14;


import java.util.ArrayList;

// use different graph to covert other type
public class GraphGenerator {

    public static Graph adjacencyListToGraph(ArrayList<DirectedGraphNode> originalGraph) {
        if (originalGraph.isEmpty()) return null;
        Graph newGraph = new Graph();

        for (DirectedGraphNode node : originalGraph) {
            Node node1 = new Node(node.label);
            newGraph.nodes.put(node.label, node1);

            for (DirectedGraphNode neighbor : node.neighbors) {
                Node node2 = new Node(neighbor.label);
                newGraph.nodes.put(neighbor.label, node2);
                newGraph.edges.add(new Edge(-1, node1, node2));
            }
        }

        for (Edge edge : newGraph.edges) {
            Node from = edge.from;
            Node to = edge.to;

            from.nextNode.add(to);
            from.nextEdges.add(edge);
            from.outEdges++;

            to.inEdges++;
        }
        return newGraph;
    }

}
