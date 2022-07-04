import java.util.Arrays;
import java.util.ArrayList;
import java.util.LinkedList;
import java.io.IOException;
public class EdmondsKarp{
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
		if(graph.getNumNodes() <= src || src < 0 || graph.getNumNodes() <= dst || dst < 0){
			System.out.println("Gesuchte Knoten nicht im Graphen (zu groß oder klein)");
			return;
		}
		
		int[][] results = edmond(graph,src,dst);
		int[] flow = results[0];
		int[] transitionTable = results[1];
		ArrayList<Integer> reachableNodes = bfs(graph,src,flow,transitionTable);
		ArrayList<WeightEdge> removeable = new ArrayList<>();
		for(int i = 0; i < reachableNodes.size(); i++){
			LinkedList<WeightEdge> edges = graph.getNode(reachableNodes.get(i)).getEdgeList();
			for(int j = 0; j < edges.size(); j++){
				if(!isIn(reachableNodes,edges.get(j).getDst().getID())){
					removeable.add(edges.get(j));
				}
			}
		}
		System.out.println("Anzahl der Kanten für den Mindestschnitt: " + removeable.size());
		for(int i = 0; i < removeable.size(); i++){
			System.out.println("    Kante von " +removeable.get(i).getSrc().getID() + " zu " 
			+ removeable.get(i).getDst().getID()+ " mit Kosten " + removeable.get(i).getWeight());
		}
		
	}
	public static int[][] edmond(WeightGraph graph, int src, int dst){
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
			//System.out.println(augmentedPath.size() + " Pfad:       " + augmentedPath.toString());
			//System.out.println(transitionTable.length + " TransTable: " + Arrays.toString(transitionTable));
			//System.out.println(flow.length + " Flow:       " + Arrays.toString(flow));
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
					return null;
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
					return null;
				}
				flow[transitionTable[augmentedPath.get(i)]+edgeNum]+=bottleneckCapacity;
			}
			dstFlow+=bottleneckCapacity;
			//System.out.println(transitionTable.length + " TransTable: " + Arrays.toString(transitionTable));
			//System.out.println(flow.length + " Flow:       " + Arrays.toString(flow));
			
			augmentedPath = bfs(graph, src, dst, flow, transitionTable);
			
			
		}
		//if(!loopEntered)return -1;
		//return dstFlow;
		return new int[][]{flow,transitionTable};
	}
	public static ArrayList<Integer> bfs(WeightGraph graph, int src, int dst, int[] flow, int[] transitionTable){
		int[] pre = new int[graph.getNumNodes()];
		ArrayList<Integer> handled = new ArrayList<>();
		for(int i = 0; i < graph.getNumNodes();i++){
			graph.getNode(i).setMark(false);
			pre[i] = -1;
		}
		handled.add(src);
		graph.getNode(src).setMark(true);
		while(pre[dst] == -1 && !handled.isEmpty() ){
			int currentNode = handled.get(0);
			LinkedList<WeightEdge> edges = graph.getNode(currentNode).getEdgeList();
			for(int i = 0; i < edges.size(); i++){
				WeightNode nextNode = edges.get(i).getDst();
				//System.out.println(flow[transitionTable[currentNode]+i] + "  " + edges.get(i).getWeight());
				if(flow[transitionTable[currentNode]+i] < edges.get(i).getWeight()){
					if(!nextNode.isMarked()){
						nextNode.setMark(true);
						pre[nextNode.getID()] = currentNode;
						handled.add(nextNode.getID());
					}
				}
			}
			handled.remove(0);
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
	public static ArrayList<Integer> bfs(WeightGraph graph, int src, int[] flow, int[] transitionTable){

		ArrayList<Integer> handled = new ArrayList<>();
		ArrayList<Integer> accesible = new ArrayList<>();
		for(int i = 0; i < graph.getNumNodes();i++){
			graph.getNode(i).setMark(false);
		}
		handled.add(src);
		graph.getNode(src).setMark(true);
		while(!handled.isEmpty() ){
			int currentNode = handled.get(0);
			LinkedList<WeightEdge> edges = graph.getNode(currentNode).getEdgeList();
			for(int i = 0; i < edges.size(); i++){
				WeightNode nextNode = edges.get(i).getDst();
				//System.out.println(flow[transitionTable[currentNode]+i] + "  " + edges.get(i).getWeight());
				if(flow[transitionTable[currentNode]+i] < edges.get(i).getWeight()){
					if(!nextNode.isMarked()){
						nextNode.setMark(true);
						handled.add(nextNode.getID());
						accesible.add(nextNode.getID());
					}
				}
			}
			handled.remove(0);
		}
		
		return accesible;
	}
	public static boolean isIn(ArrayList<Integer> list, int item){
		for(int i = 0; i < list.size(); i++){
			if(list.get(i)==item)return true;
		}
		return false;
	}
}