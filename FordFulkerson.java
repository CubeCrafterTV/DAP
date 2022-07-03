/*
Edge{
	Node getSrc()
	Node getDst()
	int getCap()
	int getFlow()
	setFlow(int flow)
}
Node{
	addEdge(Node dst)
	boolean equals(Object other)
	int getId()
	LinkedList<Edge> getAdjList()
}
Graph{
	boolean contains(int id)
	getNode(int id)
	addEdge(int src, int dsr)
	static Graph fromFile(String filepath)
	String toString()
}
*/
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.IOException;
public class FordFulkerson{
	public static void main(String[] args){
		if(args.length != 3) {
			System.out.println("Falsche Anzahl an Inputs");
			return;
		}
		String path = args[0];
		int src,dst;
		try{
			src = Integer.parseInt(args[1]);
			dst = Integer.parseInt(args[2]);
		} catch(Exception e){
			System.out.println("Argument 1 und 2 müssen Zahlen sein");
			return;
		}
		WeightGraph graph;
		try{
			graph = WeightGraph.fromFile(path);
		} catch(IOException e){
			System.out.println("Fehler beim einlesen der Datei");
			return;
		}
		if(sizeOf(graph) <= src || src < 0 || sizeOf(graph) <= dst || dst < 0){
			System.out.println("Gesuchte Knoten nicht im Graphen (zu groß oder klein)");
			return;
		}
		ArrayList<Integer> npath = bfs(graph,src,dst);
		if(npath == null){
			System.out.println("Kein weg gefunden");
			return;
		}
		System.out.println(npath.toString());
		
	}
	public static void fordFulkerson(WeightGraph graph, int src, int dst){
		ArrayList<Integer> augmentedPath = bfs(graph, src, dst);
	}
	public static ArrayList<Integer> bfs(WeightGraph graph, int src, int dst){
		int[][] pre = new int[sizeOf(graph)][2];
		ArrayList<Integer> handled = new ArrayList<>();
		for(int i = 0; i < sizeOf(graph);i++){
			graph.getNode(i).setMark(false);
			pre[i][1] = -1;
		}
		handled.add(src);
		graph.getNode(src).setMark(true);
		while(pre[dst][1] == -1 && !handled.isEmpty() ){
			int indexCurrentNode = handled.size()-1;
			LinkedList<WeightEdge> edges = graph.getNode(handled.get(indexCurrentNode)).getEdgeList();
			for(int i = 0; i < edges.size(); i++){
				if(!edges.get(i).getDst().isMarked()){
					edges.get(i).getDst().setMark(true);
					pre[edges.get(i).getDst().getID()][1] = handled.get(handled.size()-1);
					handled.add(edges.get(i).getDst().getID());
				}
			}
			handled.remove(indexCurrentNode);
		}
		if(pre[dst][1] == -1)return null;
		ArrayList<Integer> path = new ArrayList<>();
		int node = dst;
		path.add(dst);
		while(node != src){
			node = pre[node][1];
			path.add(0,node);
			
		} 
		return path;
	}
	public static int sizeOf(WeightGraph graph){
		int i = 0;
		while(graph.contains(i)){
			i++;
		}
		return i;
	}
}