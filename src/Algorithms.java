import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;

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

        File dir = new File("./primAlgorithm");
        if (dir.length() != 0)
        {
            for(File file: dir.listFiles())
            {
                file.delete();
            }
        }

        int[][] input = Util.generateWeightedGraph(size);
        input[0][0] = 0;
        Node[] nodes = new Node[input.length];
        for (int i = 0; i < input.length; i++)
        {
            input[i][i] = 1000;
            if(i == 0){input[i][i] = 0;}
            nodes[i] = new Node(i,input[i][i]);
        }

        Graph g = Util.drawGraph(input);
        Util.saveGraph(g,filePath);

        int i = 0;
        boolean allChecked = false;
        while(!allChecked)
        {
            Node smallestNode = new Node(1001,1001);
            for(Node node: nodes)
            {
                if(node.value < smallestNode.value && !node.checked)
                {
                    smallestNode = node;
                }
            }
            for (int j = 0; j < input.length; j++)
            {
                if(input[smallestNode.index][j] != 0 && !nodes[j].checked && input[smallestNode.index][j] < input[j][j])
                {
                    input[j][j] = input[smallestNode.index][j];
                    nodes[j].value = input[smallestNode.index][j];
                    nodes[j].parent = nodes[smallestNode.index];
                }
            }
            nodes[smallestNode.index].checked = true;

            g = Util.drawGraph(input);
            Util.saveGraph(g,"./primAlgorithm/solution" + i + ".png");

            for (Node node: nodes)
            {
                if(!node.checked)
                {
                    allChecked = false;
                    break;
                }
                else
                    allChecked= true;
            }
            i++;
        }
    }
}