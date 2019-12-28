import java.util.ArrayList;
import java.util.Scanner;

public class Graph {
    private int[][] matrix; //adjacency matrix
    private int n; //the total number of vertices, will be set once constructor is called
    private ArrayList<Integer>[] list;
    private ArrayList<Integer>[] listFromIncidenceMatrix;
    private int edges = 0;

    public int getEdges() {
        return edges;
    }

    public void setEdges(int edges) {
        this.edges = edges;
    }

    private int[][] incidenceMatrix; // incidence matrix
    //edges will be set while making the list
    Graph(int vertices){
        //constructor to instantiate adjacency matrix;
        n = vertices;
        matrix = new int[n][n];
        list = new ArrayList[n];
        for(int i = 0; i < n; i++){
            list[i] = new ArrayList<>();
        }
        Scanner sc = new Scanner(System.in);
        //enter 1 for an edge and 0 for no edge
        /*for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.println("Enter 1 if there an edge from vertex "+i+" to vertex "+j, 0 otherwise);
                int edge = sc.nextInt();
                matrix[i][j] = edge;
            }
        }*/
        matrix[0][0] = 0;
        matrix[0][1] = 1;
        matrix[0][2] = 1;
        matrix[0][3] = 0;
        matrix[1][0] = 1;
        matrix[1][1] = 0;
        matrix[1][2] = 1;
        matrix[1][3] = 0;
        matrix[2][0] = 1;
        matrix[2][1] = 1;
        matrix[2][2] = 0;
        matrix[2][3] = 1;
        matrix[3][0] = 0;
        matrix[3][1] = 0;
        matrix[3][2] = 1;
        matrix[3][3] = 0;

    }

    //Runs in O(n^2)
    public void displayMatrix(){
        System.out.println("Showing matrix representation");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    //Runs in O(n^2) time
    public void matrixToList(){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(matrix[i][j] == 1){
                    list[i].add(j);
                    edges++;
                }
            }
        }
        edges = edges/2;
    }

    //Runs in O(n*(no of connected nodes to n))
    public void listToIncidenceMatrix(){
        incidenceMatrix = new int[n][edges];
        int edgeCount = 0; // we start by building edges from 0
        for(int from = 0; from < n; from++){
            for(int to : list[from]){
                //check if this edge is already added....
                boolean edgeVisited = checkEdge(from,to);
                if(edgeVisited == false) {
                    incidenceMatrix[from][edgeCount] = 1;
                    incidenceMatrix[to][edgeCount] = 1;
                    edgeCount++;

                }

            }
        }
    }

    //Runs in O(edges*n*n) time
    public void incidenceMatrixToList(){
        listFromIncidenceMatrix = new ArrayList[n];
        for(int i = 0; i < n; i++){
            listFromIncidenceMatrix[i] = new ArrayList<>();
        }
        int edgeCount = 0;
        while(edgeCount < edges){
            int i = 0; // i =from vertex
            while(i < n){ //loop for checking if a vertex i has an edge
                if(incidenceMatrix[i][edgeCount] == 1){
                    int j = 0; // j = to vertex
                    while(j < n){
                        if(j == i){
                            j++;
                            continue;
                        }
                        if(incidenceMatrix[j][edgeCount] == 1){
                            //this means both from and to have same edge
                            //so we add j to i in the list[i]
                            listFromIncidenceMatrix[i].add(j);
                        }
                        j++;
                    }
                }
                i++;
            }
            edgeCount++;
        }
    }

    //Runs in O(n)
    private boolean checkEdge(int from, int to){
        for(int i = 0; i < edges; i++){
            if(incidenceMatrix[from][i] == 1 && incidenceMatrix[to][i] == 1){
                return true;
            }
        }
        return false;
    }

    //Runs in O(n) time
    public void displayList(){
        System.out.println("Showing the graph as a list");
        for(int i = 0; i <n; i++){
            System.out.print(i+": ");
            System.out.println(list[i].toString());
            System.out.println();
        }
    }

    //Runs in O(n*edges)
    public void displayIncidenceMatrix(){
        System.out.println("Incidence matrix: ");
        for(int i = 0; i < n; i++){
            for(int j = 0; j < edges; j++){
                System.out.print(incidenceMatrix[i][j]+" ");
            }
            System.out.println();
        }
    }

    //RUns in O(n) time
    private void displayListFromIncidenceMatrix() {
        System.out.println("List from incidence matrix:");
        for(int i = 0; i <n; i++){
            System.out.print(i+": ");
            System.out.println(listFromIncidenceMatrix[i].toString());
            System.out.println();
        }

    }

    public static void main(String[] args) {
        //The user input is hardcoded, but the code for entering is also written in the constructor

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the total number of vertices?");
        int vertices = sc.nextInt();
        Graph graph = new Graph(vertices);
        graph.displayMatrix();
        System.out.println();
        graph.matrixToList();
        graph.displayList();
        System.out.println();
        System.out.println("No of edges "+graph.getEdges());
        System.out.println();
        graph.listToIncidenceMatrix();
        graph.displayIncidenceMatrix();
        System.out.println();
        graph.displayList();
        System.out.println();
        graph.incidenceMatrixToList();
        graph.displayListFromIncidenceMatrix();


    }


}
