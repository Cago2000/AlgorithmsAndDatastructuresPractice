import guru.nidi.graphviz.engine.Engine;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if(left >= right)
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
    
    public static void primsAlgorithm(int size) throws IOException {

        if(size<2){return;}
        File dir = new File("./graphs/primAlgorithm");
        Arrays.stream(dir.listFiles()).forEach(File::delete);
        int[][] input = Util.generateWeightedGraph(size);
        Node[] nodes = new Node[input.length];
        for (int i = 0; i < input.length; i++)
        {
            input[i][i] = 1000;
            if(i == 0){input[i][i] = 0;}
            nodes[i] = new Node(i,input[i][i]);
        }

        Graph g = Util.drawGraph(input,"Initial",-1);
        Util.saveGraph(g, Engine.CIRCO,"./graphs/primAlgorithm/initial.png");

        int i = 0;
        boolean allChecked = false;
        while(!allChecked)
        {
            Optional<Node> smallestNode = Arrays.stream(nodes).filter((node) -> !node.checked).min(Comparator.comparing(Node::getValue));
            Node min = smallestNode.get();
            for (int j = 0; j < input.length; j++)
            {
                if(input[min.index][j] != 0 && !nodes[j].checked && input[min.index][j] < input[j][j])
                {
                    input[j][j] = input[min.index][j];
                    nodes[j].value = input[min.index][j];
                    nodes[j].parent = nodes[min.index];
                }
            }

            nodes[min.index].checked = true;
            if(Arrays.stream(nodes).allMatch((node) -> node.checked)) {allChecked = true;}

            String title = "PrioQueue{" + Arrays.stream(nodes)
                    .filter(node -> !node.checked)
                    .map(node -> (char) (65 + node.index))
                    .map(Object::toString)
                    .collect(Collectors.joining(",")) +
                    "}, Step " + (i + 1) + ", Current Node: " + (char) (65 + min.index);

            g = Util.drawGraph(input, title, min.index);
            Util.saveGraph(g, Engine.CIRCO,"./graphs/primAlgorithm/step" + (i+1) + ".png");

            i++;
        }
        g = Util.drawTree(nodes, "Final tree");
        Util.saveGraph(g, Engine.DOT,"./graphs/primAlgorithm/finalTree.png");

        String path = "C:\\Users\\Crusader\\IdeaProjects\\AlgorithmsAndDatastructurePractice\\graphs\\primAlgorithm\\initial.png";
        String expr = "rundll32 \"C:\\Program Files (x86)\\Windows Photo Viewer\\PhotoViewer.dll\", ImageView_Fullscreen " + path;
        Runtime.getRuntime().exec(expr);
    }

    public static void dijkstraAlgorithm(int size) throws IOException {

        if(size<2){return;}
        File dir = new File("./graphs/dijkstraAlgorithm");
        Arrays.stream(dir.listFiles()).forEach(File::delete);
        int[][] input = Util.generateWeightedGraph(size);
        Node[] nodes = new Node[input.length];
        for (int i = 0; i < input.length; i++)
        {
            input[i][i] = 1000;
            if(i == 0){input[i][i] = 0;}
            nodes[i] = new Node(i,input[i][i]);
        }
        Graph g = Util.drawGraph(input,"Initial",-1);
        Util.saveGraph(g, Engine.CIRCO,"./graphs/dijkstraAlgorithm/initial.png");

        int i = 0;
        boolean allChecked = false;
        while(!allChecked)
        {
            Optional<Node> smallestNode = Arrays.stream(nodes).filter((node) -> !node.checked).min(Comparator.comparing(Node::getValue));
            Node min = smallestNode.get();
            for (int j = 0; j < input.length; j++)
            {
                if(input[min.index][j] != 0 && !nodes[j].checked && input[j][j] > (input[min.index][j] + min.getValue()))
                {
                    input[j][j] = input[min.index][j] + min.getValue();
                    nodes[j].value = input[j][j];
                    nodes[j].parent = nodes[min.index];
                }
            }

            nodes[min.index].checked = true;
            if(Arrays.stream(nodes).allMatch((node) -> node.checked)) {allChecked = true;}

            String title = "PrioQueue{" + Arrays.stream(nodes)
                    .filter(node -> !node.checked)
                    .map(node -> (char) (65 + node.index))
                    .map(Object::toString)
                    .collect(Collectors.joining(",")) +
                    "}, Step " + (i + 1) + ", Current Node: " + (char) (65 + min.index);

            g = Util.drawGraph(input, title, min.index);
            Util.saveGraph(g, Engine.CIRCO,"./graphs/dijkstraAlgorithm/step" + (i+1) + ".png");

            i++;
        }
        g = Util.drawTree(nodes, "minimum spanning tree");
        Util.saveGraph(g, Engine.DOT,"./graphs/dijkstraAlgorithm/finalTree.png");

        String path = "C:\\Users\\Crusader\\IdeaProjects\\AlgorithmsAndDatastructurePractice\\graphs\\dijkstraAlgorithm\\initial.png";
        String expr = "rundll32 \"C:\\Program Files (x86)\\Windows Photo Viewer\\PhotoViewer.dll\", ImageView_Fullscreen " + path;
        Runtime.getRuntime().exec(expr);
    }
}