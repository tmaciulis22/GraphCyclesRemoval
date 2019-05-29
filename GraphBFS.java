import java.util.*;

public class GraphBFS<T> {
    List<Node<T>> nodes = new ArrayList<>();
    int edgeCount = 0;

    public void addEdge(T src, T dest){
        if(src == dest) {
            return;
        }
        Node<T> srcNode = null, destNode = null;

        for(Node n : nodes) {
            if(n.value == src){
                srcNode = n;
            }
            if(n.value == dest){
                destNode = n;
            }
        }

        if(srcNode == null) {
            srcNode = new Node<>(src);
            nodes.add(srcNode);
        }
        if(destNode == null) {
            destNode = new Node<>(dest);
            nodes.add(destNode);
        }
        srcNode.addNeighbour(destNode);
        destNode.addNeighbour(srcNode);
        edgeCount++;
    }

    public void printGraph(){
        nodes.forEach(node -> {
                    System.out.print("Head (" + node.value + ") neighbours: " + node.value);
                    node.neighbours.forEach(neighbour -> System.out.print(" - " + neighbour.value));
                    System.out.println();
                }
        );
    }

    public GraphBFS<T> removeCycles(){
        if(edgeCount+1 == nodes.size()) {
            System.out.println("Graph does not have cycles");
            return this;
        }

        return bfs();
    }

    public GraphBFS<T> bfs(){
        List<Node<T>> nodeList = new LinkedList<>();
        GraphBFS<T> newGraph = new GraphBFS<>();

        if(!nodes.isEmpty()) {
            nodeList.add(nodes.get(0));
            nodes.get(0).mark();
            System.out.print(nodes.get(0).value);
        }

        while(!nodeList.isEmpty()){
            Node<T> currentNode = nodeList.remove(0);
            currentNode.mark();

            currentNode.neighbours.forEach(neighbour -> {
                if (!neighbour.isVisited && !nodeList.contains(neighbour)) {
                    System.out.print(" -> " + neighbour.value);
                    nodeList.add(neighbour);
                    newGraph.addEdge(currentNode.value, neighbour.value);
                }
            });
        }
        System.out.println();
        return newGraph;
    }

    class Node<T>{
        T value;
        boolean isVisited = false;
        List<Node<T>> neighbours = new ArrayList<>();

        public Node(T value){
            this.value = value;
        }

        public void addNeighbour(Node n) {
            if(!neighbours.contains(n))
                neighbours.add(n);
        }

        public void mark() {
            isVisited = true;
        }
    }
}
