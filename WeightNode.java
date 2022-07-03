import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class WeightNode {
    private LinkedList<WeightEdge> edgeList;
    private int id;
	private boolean marked = false;
    public WeightNode( int pid ) {
        edgeList = new LinkedList<WeightEdge>();
        id = pid;
    }

    public int getID() {
        return id;
    }
	
	public void setMark(boolean pMark){
		marked = pMark;
	}
	public boolean isMarked(){
		return marked;
	}
    public LinkedList<WeightEdge> getEdgeList() {
        return edgeList;
    }

    public void addEdge( WeightNode dst, int weight) {
        WeightEdge tempEdge = new WeightEdge( this,  dst, weight );
        edgeList.add(tempEdge);
    }

    public boolean equals( WeightNode other ) {
        if( other.getID() == this.getID() ) {
            return true;
        } else {
            return false;
        }
    }
}