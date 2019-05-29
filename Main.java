
public class Main {

    public static void main(String[] args) {
        GraphBFS<Integer> graph = new GraphBFS<>();

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 2);
        graph.addEdge(1, 4);
        graph.addEdge(3, 1);

//        graph.addEdge(0, 1);
//        graph.addEdge(1, 2);
//        graph.addEdge(2, 3);
//        graph.addEdge(1, 4);
//        graph.addEdge(2, 5);
//        graph.addEdge(2, 0);

//        graph.addEdge(0, 1);
//        graph.addEdge(0, 2);
//        graph.addEdge(1, 2);
//        graph.addEdge(1, 3);

        graph.printGraph();
        System.out.println("-----------------");

        graph = graph.removeCycles();
        System.out.println("-----------------");

        graph.printGraph();
    }
}
