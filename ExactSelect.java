import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class ExactSelect{
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
                System.out.println("You can only enter one positive Integer");
                return;
            }
        } else {
            System.out.println("One Integer parameter has to be entered");
            return;
        }

        //countingSort( data );

        int kSmallest = exactSelect( data, k );
        System.out.println("The k smallest value is: " + kSmallest);

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


    public static int exactSelect( int[] data, int k ){
        int min = getMin( data );
        int max = getMax( data );

        boolean[] freq = new boolean[max - min + 1];

        for( int i = 0; i < data.length; i++ ){
            freq[data[i] - min] = true;
        }


        int pos = -1;
        int count = 0;
        while( pos < freq.length-1 && count < k ){
            pos++;
            if( freq[pos] ){
                count++;
            }
        }

        if( count == k ){
            return pos + min;
        } else {
            System.out.println("There are no " + k + " different values.");
            return Integer.MAX_VALUE;
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