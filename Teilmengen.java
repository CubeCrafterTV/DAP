import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Teilmengen{
	
	public static void main(String[] args){
		int[] data = scanIntArray();
		if(data==null || data.length == 0){
			//ERROR
			return;
		}

		//Logical or operation finishes if first argument is true without checking
		//second, so no outofBoundsException will be thrown
		int k = 0;
		if(args.length == 1){
			try{
				k = Integer.parseInt(args[0]);
			} catch(Exception e){
				//ERROR
			}
		} else {
			return;
		}
		System.out.println("Array: " +Arrays.toString(data));
		
		
		int index = removeDuplicates(data);
		System.out.println("Sorted duplicate Array ab " + (index+1) + ": " +Arrays.toString(data));
		
		System.out.println("Anzahl an " + k + "er Teilmengen: " + numOfSubsets(data, 0, index, k));
		
	}
	//Links:  Bereits chosen
	//Rechts: Nicht mehr choosen
	public static int numOfSubsets(int[] data, int start, int rightBound, int size){
		if(start == size){
			printArrFromTill(data,0,size);
			return 1;
		}
		int numOfSubsets = 0;
		for(int i = start; i < rightBound+1; i++){
			swap(data,start,i);
			numOfSubsets += numOfSubsets(data, start+1, rightBound, size);
			swap(data,start,i);
			/**/swap(data,i,rightBound);
			/**/rightBound--;
			/**/i--;
		}
		return numOfSubsets;
	}
	public static void swap(int[] arr, int a, int b){
		int swap = arr[a];
        arr[a] = arr[b];
        arr[b] = swap;
	}
	public static void printArrFromTill(int[] data, int start, int end){
		int[] tmp = new int[end-start];
		for(int i = 0; i < end-start; i++){
			tmp[i] = data[i];
		}
		System.out.println(Arrays.toString(tmp));
	}
	public static int removeDuplicates(int[] data){
		//TODO in O(n)
		Arrays.sort(data);
		int duplicates = 0;
		int previous = 0;
		for(int i = data.length-1; i > 0 ;i--){
			previous = data[i-1];
			if(data[i] == previous){
				int hilfsBroetchen = data[i];
				data[i] = data[data.length-1-duplicates];
				data[data.length-1-duplicates] = hilfsBroetchen;
				duplicates++;
			}
		}
		return data.length-duplicates-1;
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
                System.out.println("ERR0R: Only numbers can be entered.");
                return null;
            }
        }
        return array;
    }

}