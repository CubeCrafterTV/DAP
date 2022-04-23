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
		if(true){
			data = null;
			data = new int[10];
			for(int i = 0; i < 10; i++){
				data[i] = i+1;
			}
			System.out.println(Arrays.toString(data));
			System.out.println("Anzahl an 7 elementigen Teilmengen: " + numOfSubsets(data, 0, 9, 7));
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
		
		System.out.println("Anzahl an " + k + " Teilmengen: " + numOfSubsets(data, 0, index, k));
		
	}
	public static int numOfSubsets(int[] data, int leftBound, int rightBound, int size){
		if(leftBound >= size){
			int[] tmp = new int[size];
			for(int i = 0; i < size; i++){
				tmp[i] = data[i];
			}
			System.out.println(Arrays.toString(tmp));
			return 1;
		}
		int numOfSubsets = 0;
		for(int i = leftBound; i<=rightBound; i++){
			 swap(data,leftBound,i);
			 numOfSubsets += numOfSubsets(data,leftBound+1,rightBound,size);
			 swap(data,leftBound,rightBound);
			 rightBound--;
		}
		return numOfSubsets;
	}
	public static void swap(int[] arr, int a, int b){
		int swap = arr[a];
        arr[a] = arr[b];
        arr[b] = swap;
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