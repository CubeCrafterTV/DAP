import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;

public class Selects{
	public static void main(String[] args){
		int[] data = scanIntArray();
		int k;
		//scanIntArray returns null in case of error while reading input
		if(data==null || data.length == 0){
			System.out.println("Error: You have to enter at least one number, and can only enter numbers");
			return;
		}
		
		//Error handling
		if(args.length == 1){
			try{
				k = Integer.parseInt(args[0]);
				if(k<=0) throw new Exception();
			} catch(Exception e){
				System.out.println("You can only enter positive Integers");
				return;
			}
		} else {
			System.out.println("One Integer parameter has to be entered");
			return;
		}
		if(k >= data.length){
			System.out.println("Parameter too big for array");
			return;
		}
		
		buildMinHeap(data);

		//assert isMinHeap(data);
		
		System.out.println(Arrays.toString(data));
		System.out.println(isMinHeap(data));
		System.out.println(heapSelect(data, k));
	}
	public static void minHeapify(int[] data, int i, int n){
		if(2*i > n)	return;
		int indexSmallerChild = 0;
		if(2*i+1>n){
			indexSmallerChild = 2*i;
		} else {
			indexSmallerChild = (data[2*i-1] < data[2*i+1-1]) ? 2*i : 2*i+1;
		}
		if(data[indexSmallerChild-1] < data[i-1]){
			swap(data, i-1, indexSmallerChild-1);
			minHeapify(data, indexSmallerChild,n);
		} 
		
	}
	public static void buildMinHeap(int[] data){
		for(int i = data.length/2; i>=1; i--){
			minHeapify(data,i, data.length);
		}
	}
	public static int extractMin(int[] data, int n){
		if(n == 0) return 0;
		if(n == 1) return data[0];
		swap(data, 0, n-1);
		minHeapify(data, 1, n-1);
		return data[n-1];
	}
	public static int heapSelect(int[] data, int k){
		int result = 0;
		for(int i = 0; i< k; i++){
			result = extractMin(data,data.length-i);
		}
		return result;
		
	}	
	public static boolean isMinHeap(int[] data){
		for(int i = 1; i < data.length/2; i++){
			if(data[i-1] > data[2*i-1] || data[i-1] > data[2*i+1-1]){
				return false;
			}
		}
		return true;
	}
	
	public static int[] scanIntArray(){
        Scanner scanner = new Scanner(System.in);
        ArrayList<String> inputs = new ArrayList<String>();
        while(scanner.hasNextLine()){
            String inputWord = scanner.nextLine();
            if(inputWord.equals("")) break;
            inputs.add(inputWord);
        }
        int[] array = new int[inputs.size()];
        for(int i = 0; i < array.length; i++){
            try{
                array[i] = Integer.parseInt(inputs.get(i));
            } catch(Exception e){
                return null;
            }
        }
        return array;
    }
	public static void swap(int[] data, int a, int b){
		int hilfsBroetchen = data[a];
		data[a] = data[b];
		data[b] = hilfsBroetchen;
	}
}