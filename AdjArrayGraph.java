import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class AdjArrayGraph {
	public static final String PATH_TO_FILE = "graphen/n200m2000.";
	public static final String FILE_ = "mst";
	public static final boolean WEIGHTED = true;
	private int[] firstEdge;
	private int[] destination;
	private int[] weights;
	
	
	public void fromFile(String path){
		try {
			BufferedReader b = new BufferedReader(new FileReader(path));
			String line = b.readLine();
			while(line!=null) {
				if(line.charAt(0) == 'c') {
					line = b.readLine();
					continue;
				}
				if(!line.startsWith("p edge")) return;
			
				String[] nums = line.split(" ");
				int numOfVertecies = Integer.parseInt(nums[2]);
				int numOfEdges = Integer.parseInt(nums[3]);
				firstEdge = new int[numOfVertecies+1];
				destination = new int[numOfEdges];
				weights = new int[numOfEdges];
				break;
			}
			int[] numOfDestinationVertecies = new int[firstEdge.length];
			line = b.readLine();
			while(line != null) {
				if(line.charAt(0) == 'e') {
					String[] nums = line.split(" ");
					int vertex1 = Integer.parseInt(nums[1]);
					int vertex2 = Integer.parseInt(nums[2]);
					for(int i = vertex1+1; i < firstEdge.length; i++) {
						firstEdge[i]++;
					}
					destination[firstEdge[vertex1]+numOfDestinationVertecies[vertex1]] = vertex2;
					numOfDestinationVertecies[vertex1]++;
					line = b.readLine();
				} else {
					System.out.println("Return");
					return;
				}
			}		
		} catch(IOException e) {
			System.out.println("IO Exception");
			e.printStackTrace();
		}
	}
	
	public String toString(){
		String output = "";
		for(int i = 0; i < firstEdge.length-1; i++){
			output += "Knoten " + i + " verbunden zu: ";
			for(int j = 0; j < firstEdge[i+1]-firstEdge[i] ;j++){
				output += destination[firstEdge[i]+j] + ", "; 
			} 
			output += "\n";
		}
		return output;
	}
	
	public int numberOfNodes(){
		return firstEdge.length-1;
	}
	
	public int numberOfEdges(){
		return destination.length;
	}
	
	public int numberOfNeighbours(int node){
		return firstEdge[node+1]-firstEdge[node];
	}
	
	public int getNeighbor(int node, int k) {
		return destination[firstEdge[node]+k];
	}
}