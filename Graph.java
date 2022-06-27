import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class Graph {
	private int[][] edges; //vertex1 vertex2 weight
	private int numVertex;
	
	public void fromFile(String path) throws IOException{
		BufferedReader b = new BufferedReader(new FileReader(path));
		String line = b.readLine();
		while(line!=null) {
			if(line.charAt(0) == 'c') {
				line = b.readLine();
				continue;
			}
			if(!line.startsWith("p edge")) return;
			
			String[] nums = line.split(" ");
			numVertex = Integer.parseInt(nums[2]);
			edges = new int[Integer.parseInt(nums[3])][3];
			break;
		}
		line = b.readLine();
		int i = 0;
		while(line != null) {
			if(line.charAt(0) == 'e') {
				String[] nums = line.split(" ");
				int vertex1 = Integer.parseInt(nums[1]);
				int vertex2 = Integer.parseInt(nums[2]);
				int weight = Integer.parseInt(nums[3]);
					
				edges[i] = new int[]{vertex1,vertex2,weight};
				i++;
				line = b.readLine();
			} else {
				System.out.println("Return");
				return;
			}
		}		
	}
	
	public String toString(){
		String output = "";
		for(int i = 0; i < edges.length; i++){
			output += "Knoten " + edges[i][0] + " mit Knoten " + edges[i][1] + " (" + edges[i][2] + ") verbunden \n";
		}
		return output;
	}
	
	public int numberOfNodes(){
		return numVertex;
	}
	
	public int numberOfEdges(){
		return edges.length;
	}
	public int[][] getEdges(){
		return edges;
	}
	
	public boolean isZusammenhaengend(){
		int[][] isZusammenhaengend = new int[numVertex][2];
		for(int i = 0; i < isZusammenhaengend.length; i++){
			isZusammenhaengend[i][0] = i;
		}
		isZusammenhaengend[0][1] = 1;
		int zusammenhaengend = 1;
		for(int j = 0; j < isZusammenhaengend.length; j++){
			for(int i = j; i < edges.length; i++){
				if(edges[i][0] == isZusammenhaengend[j][0]){				
					 int index = searchIn2Arr(isZusammenhaengend,edges[i][1]);
					 if(index <= j){
						 continue;
					 } else {
						 isZusammenhaengend[index][1]=1;
						 swap(isZusammenhaengend,index,j+1);
					 }
				}
				if(edges[i][1] == isZusammenhaengend[j][0]){
					int index = searchIn2Arr(isZusammenhaengend,edges[i][0]);
					if(index <= j){
						continue;
					} else {
						isZusammenhaengend[index][1]=1;
						swap(isZusammenhaengend,index,j+1);
					}
				}
			}
		}
		for(int i = 0; i < isZusammenhaengend.length; i++){
			if(isZusammenhaengend[i][1] == 0) return false;
		}
		return true;
	}
	public int searchIn2Arr(int[][] arr, int number){
		int index=0;
		for(int i = 0; i < arr.length; i++){
			if(arr[i][0]==number){
				return i;
			}
		}
		return -1;
	}
	public void swap(int[][] arr, int a, int b){
		int hilfsBroetchen0=arr[a][0];
		int hilfsBroetchen1=arr[a][1];
		arr[a][0] = arr[b][0];
		arr[a][1] = arr[b][1];
		arr[b][0] = hilfsBroetchen0;
		arr[b][1] = hilfsBroetchen1;
	}
}