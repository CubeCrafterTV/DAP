import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.LinkedList;

public class WeightEdge {
    private WeightNode src;
    private WeightNode dst;
    private int weight;


    public WeightEdge( WeightNode firstN, WeightNode secondN, int intWeight ) {
        src = firstN;
        dst = secondN;
        weight = intWeight;
    }

    public WeightNode getSrc() {
        return src;
    }

    public WeightNode getDst() {
        return dst;
    }

    public int getWeight() {
        return weight;
    }
}