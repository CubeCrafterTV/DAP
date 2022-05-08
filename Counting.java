import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Counting{
    public static void main(String[] args){
        int[] data = scanIntArray();
        //scanIntArray returns null in case of error while reading input
        if(data==null || data.length == 0){
            System.out.println("Error: You have to enter at least one number, and can only enter numbers");
            return;
        }

        int min = getMin( data );
        int max = getMax( data );

        int[] result = count( data, min, max );

        System.out.println("The minimum value: " + min);
        System.out.println("The maximum value: " + max);
        System.out.println("Frequencies: " + Arrays.toString( result ));

    }

    
    public static int getMin( int[] data ){
        int min = data[0];
        for( int i = 1; i < data.length; i++ ){
            if( data[i] < min ){
                min = data[i];
            }
        }
        return min;
    }

    
    public static int getMax( int[] data ){
        int max = data[0];
        for( int i = 1; i < data.length; i++ ){
            if( data[i] > max ){
                max = data[i];
            }
        }
        return max;
    }

    
    public static int[] count( int[] A, int min, int max ){
        int[] C = new int[max - min + 1];

        for( int i = 0; i < A.length; i++ ){
            C[A[i] - min] = C[A[i] - min] + 1;
        }
        return C;
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