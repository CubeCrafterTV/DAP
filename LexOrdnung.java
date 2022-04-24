import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
public class LexOrdnung{
	
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
		if(k > fakultaet(data.length)){
			System.out.println("K ist zu groß");
			return;
		}
		System.out.println("Array: " +Arrays.toString(data));
		Arrays.sort(data);
		find(data,0,k);
		System.out.println("Die " + k + ". kleinste Permutation ist: " + Arrays.toString(data));
		//Ordnung: p < q: Es gibt ein i, so dass bis i-1 alles gleich ist, und p[i] < als alle qs bis dahin
	}
	public static void find(int[] arr, int leftBound, int k){
		if(k==1 || leftBound == arr.length-1)return;
		
		int i = (k-1)/fakultaet(arr.length-1-leftBound) + leftBound;
		for(int j = leftBound+1;  j<=i; j++){
			int swap = arr[leftBound];
			arr[leftBound] = arr[j];
			arr[j] = swap;			
		}
		find(arr, leftBound+1, k-(i-leftBound)*fakultaet(arr.length-1-leftBound));
	}
	public static int fakultaet(int i){
		if(i<=0)return 1;
		return i*fakultaet(i-1);
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