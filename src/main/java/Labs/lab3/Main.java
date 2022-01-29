package Labs.lab3;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        // Read in the data from the files
        Scanner sc = new Scanner(new File("docs/lab3/in.txt"));

        // First line
        // Stores the number of nodes
        int n = sc.nextInt();
        // Stores the number of paths
        int m = sc.nextInt();

        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();

        // Create each individual node of the graph
        for(int i = 0; i < n; i++){
            graph.add(new ArrayList<>());
        }

        // Reads in the data that describes a path in terms of how the farms connect
        for(int i = 0; i < m; i++){
            int u = sc.nextInt() - 1; // 1
            int v = sc.nextInt() - 1; // 2
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] order = new int[n];
        // Scan which farms to be closed
        for(int i = 0; i < n; i++){
            order[i] = sc.nextInt() - 1;
        }

        DisjointSet ds = new DisjointSet(n);
        boolean[] open = new boolean[n];
        ArrayList<String> answers = new ArrayList<>();

        // Go backwards to open the farms
        for (int i = n - 1; i >= 0; i--){
            open[order[i]] = true;

            // Connect the opening farm with its neighbor. Only if its neighboring farm is open
            for(int neighbor : graph.get(order[i])){
                if(open[neighbor]){
                    ds.union(order[i], neighbor);
                }
            }

            // Prints "YES" if the number of connected components is equal to the unopened farms + 1
            if(DisjointSet.countSets == i + 1){
                answers.add("YES");
            }
            // Print "NO" if it doesn't satisfy the condition above
            else{
                answers.add("NO");
            }

        }

        // Prints out the answers
        Collections.reverse(answers);
        for (String answer : answers) {
            System.out.println(answer);
        }
        System.out.println();
    }
}

class DisjointSet {
    public static Pair[] parents;
    public static int countSets;

    DisjointSet(int m){
        parents = new Pair[m];
        countSets = m;

        // Initialize our disjoint sets
        for(int i = 0; i < m; i++){
            parents[i] = new Pair(i, 0);
        }
    }

    public void union(int item1, int item2) {
        // Find the roots of the 2 items
        int root1 = find(item1);
        int root2 = find(item2);

        countSets--;
        if(root1 == root2){
            return;
        }

        // Attach the smaller tree into the bigger tree
        if(parents[root1].getHeight() > parents[root2].getHeight())
            parents[root2].setID(root1);

        else if(parents[root2].getHeight() > parents[root1].getHeight())
            parents[root1].setID(root2);
            // If they have the same size
        else{
            // Attach the tree with bigger index into the bigger one
            parents[root2].setID(root1);
            // Increase the height of the root
            parents[root1].increaseHeight();
        }

    }

    public int find(int ID){
        // The item is already the root
        if(ID == parents[ID].getID())
            return ID;

        // Find the item parents root
        int result = find(parents[ID].getID());

        // If the result is not the existing parent, make it the parent
        if(result != parents[ID].getID()){
            // Attach that item directly into the root
            parents[ID].setID(result);
            // Decrease the height of the root
            parents[result].decreaseHeight();
        }
        return ID;
    }
}

class Pair{
    private int height;
    private int ID;

    Pair(int myID, int myHeight){
        height = myHeight;
        ID = myID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHeight() {
        return height;
    }

    public void increaseHeight() {
        height++;
    }

    public void decreaseHeight() {
        height--;
    }
}

