import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class Radixsort{
	public static void main(String[] args){
		int[] data = scanIntArray();
        //scanIntArray returns null in case of error while reading input
        if(data==null || data.length == 0){
            System.out.println("Error: You have to enter at least one number, and can only enter numbers");
            return;
        }
		
		System.out.println("Array:        " + Arrays.toString(data));
		lsdRadix(data);
		System.out.println("Sorted Array: " + Arrays.toString(data));
	}
	public static void sortByByte(int[] input, int l, int r, int b){
		//System.out.println("CountingSort Iteration++");
		int[] frequency = new int[256];
		for(int i = l; i <= r; i++){
			int bite = (input[i] >> (8*b)) & 0xFF;
			frequency[bite]++;
		}
		for(int i = 1; i < frequency.length; i++){
			frequency[i] += frequency[i-1];
		}
		//System.out.println(Arrays.toString(frequency));
		int[] tmpCopy = new int[r-l+1];
		for(int i = l; i <= r; i++){
			tmpCopy[i] = input[i];
		}
		
		for(int i = l; i <= r; i++){
			int bite = (tmpCopy[i] >> (8*b)) & 0xFF;
			input[l+frequency[bite]-1] = tmpCopy[i];
			frequency[bite]--;
		}
	}
	public static void lsdRadix(int[] data){
		for(int i = 0; i < 4; i++){
			sortByByte(data, 0, data.length-1,i);
		}
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