import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;


public class Teilmengen{
	
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
				if(k<0) throw new Exception();
			} catch(Exception e){
				System.out.println("You can only enter positive Integers");
				return;
			}
		} else {
			System.out.println("One Integer parameter has to be entered");
			return;
		}
		System.out.println("Array: " +Arrays.toString(data));
		
		//Gives index of last non duplicate number
		int index = removeDuplicates(data);
		if(index < k){
			System.out.println("Not enough distinct values to form big enough subsets");
			return;
		}
		System.out.println("Sorted duplicate Array ab " + (index+1) + ": " +Arrays.toString(data));
		
		System.out.println("Anzahl an " + k + "er Teilmengen: " + numOfSubsets(data, 0, index, k));
		
	}
	//start indicates how many elements are currently fixed
	//rightBound indicates up to which index the elements are allowed in the subset
	
	public static int numOfSubsets(int[] data, int start, int rightBound, int size){
		//when all the elements of the subset are fixed
		if(start == size){
			printArrFromTill(data,0,size);
			return 1;
		}
		int numOfSubsets = 0;
		//every number gets chosen to be the first element one time. every possible subset with the
		//starting element is generated. After that, every remaining subset cant contain the first element, 
		//so it is swapped to the end, where elements are stored that wont be used anymore
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
		//When sorted, duplicate entries will always be next to each other.
		
		for(int i = data.length-1; i > 0 ;i--){
			previous = data[i-1];
			//When a neighboring value is duplicate, the current one will be swapped into
			//the duplicate part of the array on the big end.
			if(data[i] == previous){
				int hilfsBroetchen = data[i];
				data[i] = data[data.length-1-duplicates];
				data[data.length-1-duplicates] = hilfsBroetchen;
				duplicates++;
			}
		}
		//return index of last non duplicate number
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
                return null;
            }
        }
        return array;
    }

}