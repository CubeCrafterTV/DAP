import java.util.ArrayList;
import java.util.Scanner;

public class AufgabeB {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();

        // Es wird versucht den input via Standard-In zu Integer Werten zu machen und einer Arrayliste hinzuzufügen
        while(input.hasNextLine()) {
            try {
                list.add(Integer.parseInt(input.nextLine()));
            } catch(Exception e) {
                System.out.println("Die Eingabe beinhaltet etwas anderes als eine Ganzzahl.");
                return;
            }
        }

        // Ein Array wird erstellt und mit den Elementen der Arrayliste gefüllt
        int[] numbers = new int[list.size()];

        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = list.get(i);
            //System.out.println(list.get(i));
        }
    }
}
