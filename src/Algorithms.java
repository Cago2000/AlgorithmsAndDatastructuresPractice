import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.model.Graph;

import java.io.IOException;
import java.util.Arrays;

import static guru.nidi.graphviz.model.Factory.node;
public class Algorithms {

    public static int euclideanAlgorithm(int a, int b)
    {
        if(b>a)
        {
            int temp = a;
            a = b;
            b = temp;
        }
        if(b == 0){return a;}

        return euclideanAlgorithm(b, a % b);
    }

    public static void binarySearchInit()
    {
        int[] input = new int[]{1,2,3,4,6,8,9,11};
        Util.printArray(input);
        binarySearch(input,0,input.length,9,0);
    }
    public static void binarySearch(int[] input,int left, int right, int wantedNumber, int comparisonCount)
    {
        if(right - left < 1)
        {
            return;
        }
        int mid = (left + right) / 2;
        if(wantedNumber < input[mid])
        {
            comparisonCount++;
            binarySearch(input,left, mid, wantedNumber,comparisonCount);
        }
        if(wantedNumber > input[mid])
        {
            comparisonCount++;
            binarySearch(input,mid+1,right, wantedNumber,comparisonCount);
        }
        if (wantedNumber == input[mid])
        {
            comparisonCount++;
            System.out.println("Wanted number " + wantedNumber + " was found!");
            System.out.println("Comparisons: " + comparisonCount);
        }
    }

    public static void primsAlgorithm(int size, String filePath) throws IOException {

        int[][] input = Util.generateWeightedGraph(size);
        int[] nodeValues = new int[size];
        Arrays.fill(nodeValues,1000);
        Graph g = GenerateGraph.generatePrimInitialGraph(input,0,filePath);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++)
            {
                if(i > j && input[i][j] != 0 && input[i][j] < nodeValues[j])
                {
                    g = g.with(node(nodeValues[j]+", "+j).with(Label.markdown(input[i][j] + ",fuba "+ j)));
                    nodeValues[j] = input[i][j];
                }
            }
            filePath = "./primAlgorithm/" + (i+1) + ".png";
            GenerateGraph.saveGraph(g,filePath);
        }
    }
}
