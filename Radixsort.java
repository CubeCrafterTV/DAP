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
		int[] data1 = copyArray(data);
		System.out.println("lsd Array:        " + Arrays.toString(data));
		lsdRadix(data);
		System.out.println("lsd Sorted Array: " + Arrays.toString(data));
		System.out.println("");
		System.out.println("msd Array:        " + Arrays.toString(data1));
		msdRadix(data1);
		System.out.println("msd Sorted Array: " + Arrays.toString(data1));
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
	public static void msdRadix(int[] data){
		msdRadix(data,0,data.length-1,3);
	}
	public static void msdRadix(int[] data, int l, int r, int b){
		if(r-l+1 <= 32) inSort(data,l,r);
		sortByByte(data,l,r,b);
		int[] C = new int[257];
		for(int i = l+1; i <= r; i++){
			if(((data[i] >> (8*b)) & 0xFF) == ((data[i-1] >> (8*b)) & 0xFF)){
				C[(data[i] >> (8*b)) & 0xFF]++;
			} else {
				C[(data[i] >> (8*b)) & 0xFF] = 1;
			}
		}
		for(int i = 0; i < C.length-1; i++){
			msdRadix(data, l+C[i],l+C[i+1]-1 ,b-1);
		}
	}
	public static void inSort(int[] data, int l, int r){
		
		int temp;
		for (int i = l+1; i <= r; i++) {
			temp = data[i];
			int j = i;
			while (j > l && data[j - 1] > temp) {
				data[j] = data[j - 1];
				j--;
			}
			data[j] = temp;
		}
	}
	public static int[] copyArray(int[] data){
		int[] result = new int[data.length];
		for(int i = 0; i < data.length; i++){
			result[i] = data[i];
		}
		return result;
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