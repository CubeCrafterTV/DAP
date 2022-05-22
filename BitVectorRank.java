public class BitVectorRank{

    BitVector bv;
    int[] array;

    public BitVectorRank (BitVector vector, int c) {
        bv = vector;



        array = new int[size() / c];

        for (int j = 1; j < array.length; j++) {
            if (bv.get(i-1)) {
                array[]
            }
        }


         


        //Methode mit Array größe size()+1

        /*

        array = new int[size() + 1];

        for (int i = 1; i < array.length; i++) {
            if (bv.get(i - 1)) {
                array[i] = array[i - 1] + 1;
            } else {
                array[i] = array[i - 1];
            }
        }

         */

    }

    public int size () {
        return bv.size();
    }

    public int rank (int index) {


        ////Methode mit Array größe size()+1

        if (index > size()) {
            throw new IllegalArgumentException("Es werden durch den Bit-Vektor keine " + index+1 + " gültigen Bits dargestellt.");
        } if (index < 0) {
            throw new IllegalArgumentException("Es werden durch den Bit-Vektor keine " + index + " gültigen Bits dargestellt.");
        }

        return array[index];
    }
}