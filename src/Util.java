import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import java.io.File;
import java.io.IOException;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Engine;

import java.util.Arrays;
import java.util.Random;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.*;
import static guru.nidi.graphviz.model.Factory.*;
import static guru.nidi.graphviz.model.Factory.node;

public class Util {

    public static boolean arrayIsSorted(int[] input)
    {
        int comparisons = 0;
        boolean result = false;
        for(int i = 1; i < input.length;i++) // check if input is sorted
        {
            comparisons++;
            if(input[i-1]>input[i])
            {
                break;
            }
            else if(i == input.length-1)
            {
                result = true;
                System.out.println("Comparisons = " + comparisons);
            }
        }
        return result;
    }

    public static int[] makeRandomArray()
    {
        Random random = new Random();
        int arraySize = random.nextInt(100) + 1; // random size 1-100
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
        {
            array[i] = random.nextInt(101); // random number 1-100
        }
        return array;
    }
    public static int[] makeRandomArray(int size, int maxValue)
    {
        Random random = new Random();
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = random.nextInt(maxValue); // random number 1-100
        }
        return array;
    }

    public static void printArray(int[] input)
    {
        for (int i:input)
        {
            System.out.print(i +" ");
        }
        System.out.println();
    }

    public static<E> void printArray(E[] input)
    {
        for (E i:input)
        {
            System.out.print(i +" ");
        }
        System.out.println();
    }

    public static void printMatrix(int[][] input)
    {
        System.out.print("  | ");
        for (int i = 0; i < input.length; i++) {System.out.print(i + " ");}
        System.out.print("\n--|");
        for (int i = 0; i < input.length; i++)
        {
            System.out.print("--");
        }
        System.out.println();

        for (int i = 0; i < input.length; i++) {
            System.out.print((i<10) ? i+" | " : i+"| ");
            Util.printArray(input[i]);
        }
    }

    public static void swapElements(int[] input, int a, int b)
    {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }

    public static int[][] generateWeightedGraph(int size)
    {
        Random random = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(matrix[i],-1);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if(matrix[i][j] == -1)
                {
                    matrix[i][j] = (i == j) || random.nextInt(10) > 5 ? 0 : random.nextInt(10);
                    matrix[j][i] = matrix[i][j];
                }
            }
        }
        Util.printMatrix(matrix);
        return matrix;
    }

    public static Graph drawGraph(int[][] input, String title){
        Graph g = graph("Graph").graphAttr().with(Label.of(title))
                .linkAttr().with("class", "link-class");
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                if(input[i][j] == 0 || i == j){continue;}
                g = g.with(node((char) (65 + i) + " " + input[i][i])
                        .link(to(node((char) (65 + j) + " " + input[j][j]))
                                .with(attr("label", input[i][j]), Style.DASHED)));
            }
        }
        return g;
    }

    public static Graph drawTree(Node[] nodes, String title)
    {
        Graph tree = graph("Tree").directed()
                .graphAttr().with(Rank.dir(BOTTOM_TO_TOP),Label.of(title))
                .linkAttr().with("class", "link-class");
        for(int i = nodes.length-1; i > 0; i--) {
            tree = tree.with(node(Character.toString(nodes[i].index + 65))
                    .link(to(node(Character.toString(nodes[i].parent.index + 65)))
                            .with(attr("label", nodes[i].value), Style.DASHED)));
        }
        return tree;
    }

    public static void saveGraph(Graph g, Engine engine, String filePath) throws IOException {
        Graphviz.fromGraph(g).engine(engine).render(Format.PNG).toFile(new File(filePath));
    }
}
