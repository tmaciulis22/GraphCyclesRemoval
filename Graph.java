import java.util.*;

public class Graph<T> {
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

    public void removeCycles(){
        if(edgeCount+1 == nodes.size()) {
            System.out.println("Graph does not have cycles");
            return;
        }

        Stack<Node<T>> nodeStack = new Stack<>();

        dfs(nodes.get(0), nodeStack);
        System.out.println();
    }

    public void dfs(Node<T> head, Stack<Node<T>> nodeStack){
        System.out.print(head.value);

        head.mark();
        if(!nodeStack.contains(head))
            nodeStack.push(head);

        List<Node<T>> neighboursToRemove = new ArrayList<>();

        head.neighbours.forEach(neighbour -> {
            if(!neighbour.isVisited) {
                System.out.print(" -> ");

                neighbour.mark();
                nodeStack.push(neighbour);

                dfs(neighbour, nodeStack);
            }else if(!nodeStack.contains(neighbour)) {
                neighboursToRemove.add(neighbour);
            }
        });

        nodeStack.pop();
        head.removeNeighbours(neighboursToRemove);
    }

    class Node<T>{
        T value;
        boolean isVisited = false;
        List<Node> neighbours = new ArrayList<>();

        public Node(T value){
            this.value = value;
        }

        public void addNeighbour(Node n) {
            if(!neighbours.contains(n))
                neighbours.add(n);
        }

        public void removeNeighbours(List<Node<T>> neighboursToRemove) {
            neighbours.removeAll(neighboursToRemove);

            neighboursToRemove.forEach(neighbour ->
                neighbour.neighbours.remove(this)
            );
        }

        public void mark() {
            isVisited = true;
        }
    }
}
