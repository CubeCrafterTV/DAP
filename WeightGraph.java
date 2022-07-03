import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class WeightGraph {
    private WeightNode[] nodes;
    private int numNodes;
    private int numEdges;

    public WeightGraph (int numberNodes, int numberEdges) {
        numNodes = numberNodes;
        numEdges = numberEdges;
        nodes = new WeightNode[numNodes];

        for( int i=0; i < numNodes; i++ ) {
            nodes[i] = new WeightNode(i);
        }
    }

    public int getNumNodes(){
        return numNodes;
    }

    public int getNumEdges(){
        return numEdges;
    }

    public boolean contains( int id ) {
        if( id >= 0 && id < numNodes ) {
            return true;
        } else {
            return false;
        }
    }

    public WeightNode getNode( int id ) {
        for( int i=0; i < nodes.length; i++ ) {
            if( id == nodes[i].getID() ) {
                return nodes[i];
            }
        }
        return null;
    }

    public boolean addEdge( int src, int dst, int weight) {
        if( contains( src ) && contains( dst ) ) {
            getNode( src ).addEdge( getNode( dst ), weight );
            return true;
        } else {
            return false;
        }
    }

    public static WeightGraph fromFile( String filepath ) throws IOException{
        BufferedReader b = new BufferedReader( new FileReader(filepath) );
        String line = b.readLine();
        WeightGraph tempGraph = new WeightGraph( 0, 0 );

        while(line!=null) {
            if(line.charAt(0) == 'c') {
                line = b.readLine();
                continue;
            }
            if(!line.startsWith("p edge")) return null;

            String[] nums = line.split(" ");
            tempGraph = new WeightGraph( Integer.parseInt( nums[2] ), Integer.parseInt( nums[3] ) );
            break;
        }

        line = b.readLine();
        //int i = 0;
        while(line != null) {
            if(line.charAt(0) == 'e') {
                String[] nums = line.split(" ");
                int srcNode = Integer.parseInt(nums[1]);
                int dstNode = Integer.parseInt(nums[2]);
                int weight = Integer.parseInt(nums[3]);

                tempGraph.addEdge( srcNode, dstNode, weight);
                //i++;
                line = b.readLine();
            } else {
                //System.out.println("Return");
            }
        }
        return tempGraph;

    }

    public String toString() {
        String output = "";
        for( int i=0; i < nodes.length; i++ ) {
            for( int k=0; k < nodes[i].getEdgeList().size(); k++ ) {
                output += "Knoten " + nodes[i].getID() + " ist verbunden zu Knoten " + nodes[i].getEdgeList().get(k).getDst().getID()
                        + " mit Gewicht " + nodes[i].getEdgeList().get(k).getWeight() + "\n";
            }
        }
        return output;
    }
}