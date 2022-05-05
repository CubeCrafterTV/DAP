import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.time.Instant;
import java.time.Duration;

public class Selects2{
	public static void main(String[] args){
		/*int[] data = scanIntArray();
		int k;
		int whichAlgorithm = 3;
		scanIntArray returns null in case of error while reading input
		if(data==null || data.length == 0){
			System.out.println("Error: You have to enter at least one number into the list");
			return;
		}
		
		//Error handling
		
		try{
			k = Integer.parseInt(args[0]);
			if(k<=0) throw new Exception();
			
			String tmp = args[1];
			if(tmp.equals("heap")){
				whichAlgorithm = 0;
			}else if(tmp.equals("quickf")){
				whichAlgorithm = 1;
			}else if(tmp.equals("quickr")){
				whichAlgorithm = 2;
			}
			if(whichAlgorithm == 4) whichAlgorithm = 4/0;
		} catch(Exception e){
			System.out.println("You can only enter positive Integers, and one of the three texts");
			return;
		}
		if(k >= data.length){
			System.out.println("Parameter too big for array");
			return;
		}
		
		doAction(data,k,whichAlgorithm);*/
		testAlgorithms();
		
	}
	public static void testAlgorithms(){
		int numOfTests = 10000;
		Random rdm = new Random();
		int numOfSuccess1 = 0;
		int numOfSuccess2 = 0;
		int numOfSuccess3 = 0;
		int numOfSuccess12 = 0;
		int numOfSuccess22 = 0;
		int numOfSuccess32 = 0;
		int k;
		for(int i = 0; i < numOfTests; i++){		
			int[] arr1 = createRandomArray(100,false);
			int[] arr2 = copyArray(arr1);
			int[] arr3 = copyArray(arr1);
			k = rdm.nextInt(arr1.length)+1;
			buildMinHeap(arr1);
			int guess1 = heapSelect(arr1,k);
			int guess2 = quickSelectFirst(arr2,k);
			int guess3 = quickSelectRand(arr3,k);
			Arrays.sort(arr1);
			if(arr1[k-1] == guess1)numOfSuccess1++;
			if(arr1[k-1] == guess2)numOfSuccess2++;
			if(arr1[k-1] == guess3)numOfSuccess3++;
			arr1 = createRandomArray(100,true);
			arr2 = copyArray(arr1);
			arr3 = copyArray(arr1);
			k = rdm.nextInt(arr1.length)+1;
			buildMinHeap(arr1);
			guess1 = heapSelect(arr1,k);
			guess2 = quickSelectFirst(arr2,k);
			guess3 = quickSelectRand(arr3,k);
			Arrays.sort(arr1);
			if(arr1[k-1] == guess1)numOfSuccess12++;
			if(arr1[k-1] == guess2)numOfSuccess22++;
			if(arr1[k-1] == guess3)numOfSuccess32++;
		}	
		System.out.println("");
		System.out.println("");
		System.out.println("Testing HeapSelect:");
		System.out.println("...");
		System.out.println(numOfSuccess1 + "/" + numOfTests+" corrects w/o duplicates");
		System.out.println(numOfSuccess12 + "/" + numOfTests +" corrects with duplicates");
		System.out.println("");
		System.out.println("");
		System.out.println("Testing QuickFirst:");
		System.out.println("...");
		System.out.println(numOfSuccess2 + "/" + numOfTests +" corrects w/o duplicates");
		System.out.println(numOfSuccess22 + "/" + numOfTests +" corrects with duplicates");
		System.out.println("");
		System.out.println("");
		System.out.println("Testing QuickRand:");
		System.out.println("...");
		System.out.println(numOfSuccess3 + "/" + numOfTests + " corrects w/o duplicates");
		System.out.println(numOfSuccess32 + "/" + numOfTests +" corrects with duplicates");
	}
	public static void doAction(int[] data, int k, int whichAlgorithm){
		int[] data1 = copyArray(data);
		int[] data2 = copyArray(data1);
		int element;
		if(whichAlgorithm == 0){
			System.out.println("Heapselect:");
			buildMinHeap(data);
			System.out.println(Arrays.toString(data));
			System.out.println(isMinHeap(data));
			System.out.println(heapSelect(data, k));
		} else if(whichAlgorithm == 1){
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("Quickselect First");
			System.out.println(Arrays.toString(data1));
			
			element = quickSelectFirst(data1, k);
			System.out.println(Arrays.toString(data1));
			System.out.println(element);
		} else if(whichAlgorithm == 2){
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("Quickselect Random");
			System.out.println(Arrays.toString(data2));
			
			element = quickSelectRand(data2, k);
			System.out.println(Arrays.toString(data2));
			System.out.println(element);
		}
	}
	public static void quickSort(int[] data, int l, int r){
		if(l < 0 || r >= data.length || r-l < 1)return;

        //recursion
        int middle = partition(data, l,l, r);
        quickSort(data, l, middle-1);
        quickSort(data, middle+1, r);
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
	public static int partition(int[] data, int l, int p, int r){

        swap(data,l,p);
        
        int pivot = data[l];
        
        int smallerNums = 0;
        int biggerNums = 0;

		/*
		The pivot value is initialized as the first number of the interval and the count of smaller and bigger numbers can be stored in their variables.
		Now the loop iterates over the specified interval starting from the number after the pivot until every number greater or equal to the pivot is on the left side
		of the interval and every number smaller is on the right.
		Smaller numbers will be swapped with the last number that isn't known to be smaller than the pivot and the other number will be examined again.
		After all numbers of the interval are sorted into bigger or equal to and smaller than the pivot, the pivot is swapped with the last number bigger than the pivot,
		ensuring that the pivot is between those two partitions.
		*/

        for( int i = l+1; i <= r-biggerNums; i++){
            if(data[i] < pivot){
                smallerNums++;

            } else if (data[i] >= pivot){
				swap(data,i,r-biggerNums);
                biggerNums++;
                i--;
            }
        }
		swap(data,l,smallerNums+l);

        return smallerNums+l;
	}
	public static int quickSelectFirst(int[] data, int l, int r, int k){
		if(r-l < 1)return data[l];
		int pivot = partition(data,l,l,r);
		if(pivot==k-1)return data[pivot];
		
		if(k > pivot){
			return quickSelectFirst(data, pivot+1,r,k);
		}  else{
			return quickSelectFirst(data, l,pivot-1,k);
		}
	}
	public static int quickSelectFirst(int[] data, int k){
		return quickSelectFirst(data, 0,data.length-1, k);
	}
	//Funktioniert manchmal nicht der Hurensohn
	public static int quickSelectRand(int[] data, int l, int r, int k){
		
		if(r-l < 1)return data[l];
		int rdm = new Random().nextInt(r-l);
		int pivot = partition(data,l,l+rdm,r);
		if(pivot==k-1)return data[pivot];
		
		if(k > pivot){
			return quickSelectFirst(data, pivot+1,r,k);
		}  else{
			return quickSelectFirst(data, l,pivot-1,k);
		}
	}
	public static int quickSelectRand(int[] data, int k){
		return quickSelectRand(data, 0,data.length-1,k);
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
	public static int[] createRandomArray(int length, boolean duplicates){
		if(length == 0) return new int[]{};
		int[] arr = new int[length];
		Random rdm = new Random();
		for(int i = 0; i < arr.length; i++){
			arr[i] = duplicates ? i/2 : i;
		}
		for(int i = 0; i < arr.length*2; i++){
			swap(arr,rdm.nextInt(arr.length),rdm.nextInt(arr.length));
		}
		return arr;
	}
	public static int[] copyArray(int[] data){
		int[] copy = new int[data.length];
		for(int i = 0; i < copy.length; i++){
			copy[i] = data[i];
		}
		return copy;
	}
}