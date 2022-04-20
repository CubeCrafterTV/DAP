import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Permutations{
    public static void main(String[] args){
        //Standard number input per Commandline into Array
        //When the input is finished, pressing Enter will end the loop to start the program
        System.out.println("Permutationprogramm: Input numbers and end your input by pressing \"Enter\".");
        Scanner input = new Scanner(System.in);
        ArrayList<String> inputList = new ArrayList<String>();
        while(input.hasNextLine()){
            String inputWord = input.nextLine();
            if(inputWord.equals("")) break;
            inputList.add(inputWord);
        }

        int[] array = new int[inputList.size()];
        for(int i = 0; i < array.length; i++){
            try{
                array[i] = Integer.parseInt(inputList.get(i));
            } catch(Exception e){
                System.out.println("ERR0R: Only numbers can be entered.");
                return;
            }
        }
        //Recursion start, zero elements are fixed -- print out the final permutation number.
        System.out.println(" There were " + printPermutations(array,0) + " permutations." );
    }
    /*
     * The method gets an array and the index to which all elements are already fixed.
     * It generates all possible positions for the first not fixed index and recursively
     * calculates the sub-permutations for each possible "then-fixed" start, adding them.
     * The recursion ends, when all elements are fixed, meaning d is equal to array.length-1
     */
    public static int printPermutations(int[] array, int d){

        if(d == array.length-1) {
            System.out.println(Arrays.toString(array));
            return 1;
        }
        int numOfPermutations = 0;

        for(int i = d; i < array.length; i++){
            int swap = array[i];
            array[i] = array[d];
            array[d] = swap;

            numOfPermutations += printPermutations(array, d+1);
            //Swapping back for aesthetic reasons
            swap = array[i];
            array[i] = array[d];
            array[d] = swap;
        }

        return numOfPermutations;
    }
}