import java.util.ArrayList;
import java.io.IOException;
public class MiniSpannbaum {
	//public static final String PATH_TO_FILE = "graphen/ex1.";
	//public static final String FILE_ = "mst";
	//public static final boolean WEIGHTED = false;
	public static void main(String[] args) {
		if(args.length != 2){
			System.out.println("Falsche Anzahl an Inputs");
			return;
		}
		Graph graph = new Graph();
		/*for(int i = 1; i < 8; i++){
			graph.fromFile("graphen/ex"+i+".mst");
			System.out.println("mst: " + i + " " + graph.isZusammenhaengend());
			graph.fromFile("graphen/ex"+i+".nomst");
			System.out.println("nomst: " + i + " " + graph.isZusammenhaengend());
		}*/			
		try {
			graph.fromFile(args[0]);
		} catch(IOException e){
			System.out.println("IO Fehler");
			e.printStackTrace();
		}
		int[][] edges;
		if(args[1].equals("kruskal")){
			edges = kruskal(graph);
		} else if(args[1].equals("prim")){
			edges = kruskal(graph);
		} else {
			System.out.println("Kein passender Algorithmusname angegeben");
			return;
		}
		if(edges == null){
			System.out.println("Der Graph ist nicht minimierbar");
			return;
		}
		String output = "";
		for(int i = 0; i < edges.length; i++){
			output += "Knoten " + edges[i][0] + " mit Knoten " + edges[i][1] + " (" + edges[i][2] + ") verbunden \n";
		}
		System.out.println(output);
		//graph.fromFile(PATH_TO_FILE +  (WEIGHTED ? "no" : "") + FILE_);
	}
	public static void sort(int[][] arr){
        for(int i=0; i < arr.length; i++){  
            for(int j=1; j < (arr.length-i); j++){  
                if(arr[j-1][2] > arr[j][2]){  
                    swap(arr,j-1,j); 
                }             
            }  
         }  
	}
	public static int[][] kruskal(Graph graph){
		if(!graph.isZusammenhaengend())return null;
		int[][] edges = graph.getEdges();
		sort(edges);
		ArrayList<int[]> minimumEdges = new ArrayList<>();
		
		int[] comp = new int[graph.numberOfNodes()];
		ArrayList<ArrayList<Integer>> compInv = new ArrayList<>();
		for(int i = 0; i < comp.length; i++){
			comp[i] = i;
			compInv.add(new ArrayList<Integer>());
			compInv.get(i).add(i);
		}
		for(int i = 1; i < edges.length; i++){
			if(comp[edges[i][0]] != comp[edges[i][1]]){
				minimumEdges.add(edges[i]);
				if(compInv.get(comp[edges[i][0]]).size() > compInv.get(comp[edges[i][1]]).size()){
					while(!compInv.get(comp[edges[i][1]]).isEmpty()){
						compInv.get(comp[edges[i][0]]).add(compInv.get(comp[edges[i][1]]).get(0));
						compInv.get(comp[edges[i][1]]).remove(0);
					}
					comp[edges[i][1]] = comp[edges[i][0]];
				} else {
					while(!compInv.get(comp[edges[i][0]]).isEmpty()){
						compInv.get(comp[edges[i][1]]).add(compInv.get(comp[edges[i][0]]).get(0));
						compInv.get(comp[edges[i][0]]).remove(0);
					}
					comp[edges[i][0]] = comp[edges[i][1]];
				}
			}
		}
		int[][] output = new int[minimumEdges.size()][3];
		for(int i = 0; i < minimumEdges.size(); i++){
			output[i][0] = minimumEdges.get(i)[0];
			output[i][1] = minimumEdges.get(i)[1];
			output[i][2] = minimumEdges.get(i)[2];
		}
		return output;
	}
	
	public static int[][] prim(Graph graph){
		return null;
	}
	public static void swap(int[][] arr, int a, int b){
		for(int i = 0; i < arr[0].length; i++){
			int hilfsBroetchen = arr[a][i];
			arr[a][i] = arr[b][i];
			arr[b][i] = hilfsBroetchen;
		}
	}
	
}