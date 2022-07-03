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
import java.util.Arrays;
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
		
		int result = fordFulkerson(graph,src,dst);
		if(result==-1){
			System.out.println("Kein Weg gefunden");
			return;
		}
		System.out.println("Der gesuchte Flow ist:" + result);
		
	}
	public static int fordFulkerson(WeightGraph graph, int src, int dst){
		int[] flow = new int[graph.getNumEdges()];
		int dstFlow = 0;
		int[] transitionTable = new int[graph.getNumNodes()];
		for(int i = 1; i < transitionTable.length; i++){
			transitionTable[i] = graph.getNode(i-1).getEdgeList().size() + transitionTable[i-1];
		}
		
		ArrayList<Integer> augmentedPath = bfs(graph, src, dst, flow, transitionTable);
		//boolean loopEntered = false;
		while(augmentedPath != null){
			//loopEntered = true;
			System.out.println(augmentedPath.size() + " Pfad:       " + augmentedPath.toString());
			System.out.println(transitionTable.length + " TransTable: " + Arrays.toString(transitionTable));
			System.out.println(flow.length + " Flow:       " + Arrays.toString(flow));
			//TODO bfs muss bei suchen eines Pfades den flow berücksichtigen
			int bottleneckCapacity = Integer.MAX_VALUE;
			//find out bottleneck
			for(int i = 0; i < augmentedPath.size()-1; i++){
				//suche aktuelle Kante
				LinkedList<WeightEdge> edges = graph.getNode(augmentedPath.get(i)).getEdgeList();
				WeightEdge edge = null;
				int edgeNum = 0;
				for(int j = 0; j < edges.size(); j++){
					if(edges.get(j).getDst().getID() == augmentedPath.get(i+1)){
						edge = edges.get(j);
						edgeNum = j;
					}
				}
				if(edge == null){
					System.out.println("Irgendein komischer Fehler ist passiert");
					return -1;
				}
				//System.out.println(" Cap:       " + edge.getWeight());
				//System.out.println(" Flow:       " + flow[transitionTable[i]+edgeNum] + "  edgedNum: " + edgeNum);
				int currentBottleneck = edge.getWeight()-flow[transitionTable[augmentedPath.get(i)]+edgeNum];
				if(currentBottleneck < bottleneckCapacity) bottleneckCapacity = currentBottleneck;
			}
			//adjusting path with new bottleneckCapacity
			for(int i = 0; i < augmentedPath.size()-1; i++){
				//suche aktuelle Kante
				LinkedList<WeightEdge> edges = graph.getNode(augmentedPath.get(i)).getEdgeList();
				WeightEdge edge = null;
				int edgeNum = -1;
				for(int j = 0; j < edges.size(); j++){
					if(edges.get(j).getDst().getID() == augmentedPath.get(i+1)){
						edge = edges.get(j);
						edgeNum = j;
						break;
					}
				}
				if(edge == null){
					System.out.println("Irgendein komischer Fehler ist passiert");
					return -1;
				}
				flow[transitionTable[i]+edgeNum]+=bottleneckCapacity;
			}
			dstFlow+=bottleneckCapacity;
			//System.out.println(transitionTable.length + " TransTable: " + Arrays.toString(transitionTable));
			//System.out.println(flow.length + " Flow:       " + Arrays.toString(flow));
			
			augmentedPath = bfs(graph, src, dst, flow, transitionTable);
			
			
		}
		//if(!loopEntered)return -1;
		return dstFlow;
	}
	public static ArrayList<Integer> bfs(WeightGraph graph, int src, int dst, int[] flow, int[] transitionTable){
		int[] pre = new int[sizeOf(graph)];
		ArrayList<Integer> handled = new ArrayList<>();
		for(int i = 0; i < sizeOf(graph);i++){
			graph.getNode(i).setMark(false);
			pre[i] = -1;
		}
		handled.add(src);
		graph.getNode(src).setMark(true);
		while(pre[dst] == -1 && !handled.isEmpty() ){
			int indexCurrentNode = handled.size()-1;
			LinkedList<WeightEdge> edges = graph.getNode(handled.get(indexCurrentNode)).getEdgeList();
			for(int i = 0; i < edges.size(); i++){
				//Einzige veränderte Zeile für int[] flow und int[]transitionTable
				WeightNode nextNode = edges.get(i).getDst();
				if(!nextNode.isMarked() && flow[transitionTable[handled.get(indexCurrentNode)]+i] < edges.get(i).getWeight()){
					nextNode.setMark(true);
					pre[nextNode.getID()] = handled.get(indexCurrentNode);
					handled.add(nextNode.getID());
				}
			}
			handled.remove(indexCurrentNode);
		}
		if(pre[dst] == -1)return null;
		ArrayList<Integer> path = new ArrayList<>();
		int node = dst;
		path.add(dst);
		while(node != src){
			node = pre[node];
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