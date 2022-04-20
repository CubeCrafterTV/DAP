import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class AufgabeC {
    public static void main(String[] args) {
        // Die Eingabe durch Standard-In wird eingelesen und abgespeichert
        int[] sorted = createArray();

        // Der Array der Eingabe wird sortiert
        Arrays.sort(sorted);

        if(sorted.length == 0) {
            System.out.println("Es wurden keine gültigen Elemente übergeben.");
            return;
        }

        int k = 0;

        // Es wird versucht den Parameter zu einem Integer zu machen
        try {
            k = Integer.parseInt(args[0]);
        } catch(Exception e) {
            System.out.println("Das übergebene Argument muss eine positive Ganzzahl größer als 0 sein.");
            return;
        }

        if(args.length != 1) {
            System.out.println("Es darf nur eine Ganzzahl als Argument übergeben werden.");
            return;
        }
        if(k <= 0 || k > sorted.length) {
            System.out.println("Das übergebene Argument muss eine positive Ganzzahl größer als 0 sein und darf nicht größer sein als die Anzahl der vorher übergebenen Werte.");
            return;
        }

        // Der k kleinste Wert wird zurückgegeben
        System.out.println("Der " + k + ". kleinste Wert ist " + sorted[k-1] + ".");
    }


    public static int[] createArray (){
        Scanner input = new Scanner(System.in);
        ArrayList<Integer> list = new ArrayList<Integer>();

        // Es wird versucht den Eingabe Array zu Integer Werten zu machen und einer Arrayliste hinzuzufügen
        while(input.hasNextLine()) {
            try {
                list.add(Integer.parseInt(input.nextLine()));
            } catch(Exception e) {
                System.out.println("Die Eingabe beinhaltet etwas anderes als eine Ganzzahl.");
                return new int[0];
            }
        }

        // Die Elemente der Arrayliste werden in einen Array übertragen
        int[] numbers = new int[list.size()];

        for(int i = 0; i < numbers.length; i++) {
            numbers[i] = list.get(i);
            //System.out.println(list.get(i));
        }

        return numbers;
    }
}
