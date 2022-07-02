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

public class EdmondsKarp{
	public static void main(String[] args){
		if(args.length != 3) {
			System.out.println("Falsche Anzahl an Inputs");
			return;
		}
		String path = args[0];
		try{
			int src = Integer.parseInt(args[1]),dst = Integer.parseInt(args[2]);
		} catch(Exception e){
			System.out.println("Argument 1 und 2 müssen Zahlen sein");
		}
		Graph test;
		try{
			test = Graph.fromFile(path);
		} catch(IOException e){
			System.out.println("Fehler beim einlesen der Datei");
			return;
		}
		
		fuegeRestKnotenHinzu(test);
		
	}
	public static void fuegeRestKnotenHinzu(Graph graph){
		int i = 0;
		while(graph.contains(i))){
			LinkedList<Edge> edges = graph.getNode(i).getAdjList();
			for(int j = 0; j < edges.size(); j++){
				Edge edge = edges.get(j);
				graph.addEdge(edge.getSrc(), edge.getDst(), edge.getCap());
			}
			i++;
		}
	}
}