import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.Instant;
import java.time.Duration;

public class QuicksortDP{
    public static void main(String[] args){
        System.out.println("Quicksortdemo: Enter an Integer Array and confirm with enter to sort.");
        //Standard int array input
        int[] array = scanIntArray();
        if(array==null) return;

        System.out.println("Array to sort: " + Arrays.toString(array));

        Instant start = Instant.now();

        qsort(array);

        Instant finish = Instant.now();
        System.out.println("Es wurde in " + (Duration.between(start, finish)).toMillis() + " Millisekunden gefinished");

        assert isSorted(array);

        if(array.length < 20) {
            System.out.println("Sorted Array: " + Arrays.toString(array));
        }
        System.out.println("Min: " + array[array.length-1] + ", Med: " + array[array.length/2] + ", Max: " + array[0]);
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

    public static void qsort(int[] data){
        //recursion start
        qsort(data,0,data.length-1);
    }

    public static void qsort(int[] data, int l, int r){
        //if l and r are out of bound, the bounds are in wrong order or it contains just one element (l=r)
        //the method doesnt continue the recursion and stops
        if(l < 0 || r >= data.length || r-l < 1)return;

        //recursion
        int[] pivots = partition(data, l, r);
        int pivot1 = pivots[0];
        int pivot2 = pivots[1];

        qsort(data, l, pivot1-1);
        qsort(data, pivot1+1, pivot2-1);
        qsort(data, pivot2+1, r);
    }
    public static int[] partition(int[] data, int l, int r){
        System.out.println("partition call");
        int swap;

        if(data[l] < data[r]) {
            swap = data[l];
            data[l] = data[r];
            data[r] = swap;
        }
        int pivot1 = data[l];
        int pivot2 = data[r];

        //System.out.println("Der Array: " + Arrays.toString(data) + " auf der Laenge " + l + " bis " + r + ". Pivot ist " + pivot );
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

        for( int i = l+1; i <= r-1-smallerNums; i++ ) {
            if(data[i] > pivot1){
                swap = data[i];
                data[i] = data[l+1+biggerNums];
                data[l+1+biggerNums] = swap;
                biggerNums++;
            }
            else if( data[i] < pivot2 ) {
                swap = data[i];
                data[i] = data[r-1-smallerNums];
                data[r-1-smallerNums] = swap;
                smallerNums++;
                i--;
            }
        }

        swap = data[l];
        data[l] = data[l+biggerNums];
        data[l+biggerNums] = swap;

        swap = data[r];
        data[r] = data[r-smallerNums];
        data[r-smallerNums] = swap;

        return new int[]{l+biggerNums, r-smallerNums};
    }

    public static boolean isSorted(int[] array){
        int previous = array[0];
        for(int i = 0; i < array.length; i++){
            if(array[i] > previous) return false;
            previous = array[i];
        }
        return true;
    }
}