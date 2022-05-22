public class BitVector{

    int size;
    int[] vector;

    public BitVector (int k) {
        if (k < 0) {
            throw new IllegalArgumentException("Es kann nur ein nicht-negativer Wert übergeben werden.");
        }

        size = k;
        if (size % 32 == 0) {
            k = size/32;
        } else {
            k = (size/32)+1;
        }
        vector = new int[k];
    }


    public int size () {
        return size;
    }


    public boolean get (int index) {
        if (index >= size || index < 0) {
            throw new IllegalArgumentException("Es werden durch den Bit-Vektor keine " + index + " gültigen Bits dargestellt.");
        }

        int word = vector[index / 32];
        int shifted = word >> index % 32;
        int mask = 1;
        boolean result = (shifted & mask) > 0;

        return result;
    }


    public void set (int index, boolean value) {
        if (index > size) {
            throw new IllegalArgumentException("Es werden durch den Bit-Vektor keine " + index + " gültigen Bits dargestellt.");
        }

        if (value) {
            int word = vector[index / 32];
            int mask = 1 << index % 32;
            word = word | mask;

            vector[index / 32] = word;

        } else {
            int word = vector[index / 32];
            int mask = 1 << index % 32;
            mask = ~mask;
            word = word & mask;

            vector[index / 32] = word;
        }
    }
}

























