import guru.nidi.graphviz.attribute.Color;
import guru.nidi.graphviz.attribute.Label;
import guru.nidi.graphviz.attribute.Rank;
import guru.nidi.graphviz.attribute.Style;
import guru.nidi.graphviz.engine.Engine;
import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

import static guru.nidi.graphviz.attribute.Attributes.attr;
import static guru.nidi.graphviz.attribute.Rank.RankDir.BOTTOM_TO_TOP;
import static guru.nidi.graphviz.model.Factory.*;

public class Util {

    public enum TraversingType {
        INORDER,
        PREORDER,
        POSTORDER
    }


    public static boolean arrayIsSorted(int[] input) {
        boolean result = false;
        for (int i = 1; i < input.length; i++) // check if input is sorted
        {
            if (input[i - 1] > input[i]) {
                break;
            } else if (i == input.length - 1) {
                result = true;
            }
        }
        return result;
    }

    public static int[] reverseArray(int[] input) {
        int start = 0;
        int len = input.length - 1;
        for (int i = 0; i < len; i++, len--) {
            Util.swapElements(input, i, len);
        }
        return input;
    }

    public static int[] makeRandomArray() {
        Random random = new Random();
        int arraySize = random.nextInt(100) + 1; // random size 1-100
        return random.ints(arraySize, 1, 101).toArray();
    }

    public static int[] makeRandomArray(int size, int maxValue) {
        Random random = new Random();
        return random.ints(size, 1, maxValue).toArray();
    }

    public static void printArray(int[] input) {
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    public static <E> void printArray(E[] input) {
        Arrays.stream(input).forEach(i -> System.out.print(i + " "));
        System.out.println();
    }

    public static void printMatrix(int[][] input) {
        System.out.print("  | ");
        for (int i = 0; i < input.length; i++) {
            System.out.print(i + " ");
        }
        System.out.print("\n--|");
        for (int i = 0; i < input.length; i++) {
            System.out.print("--");
        }
        System.out.println();

        for (int i = 0; i < input.length; i++) {
            System.out.print((i < 10) ? i + " | " : i + "| ");
            Util.printArray(input[i]);
        }
    }

    public static void maxHeapify(int[] input, int parent, int n) {
        int leftChild = 2 * parent + 1;
        int rightChild = 2 * parent + 2;
        int largest;
        if (leftChild <= n && input[leftChild] > input[parent]) {
            largest = leftChild;
        } else {
            largest = parent;
        }
        if (rightChild <= n && input[rightChild] > input[largest]) {
            largest = rightChild;
        }
        if (largest != parent) {
            Util.swapElements(input, parent, largest);
            Util.maxHeapify(input, largest, n);
        }
    }

    public static void swapElements(int[] input, int a, int b) {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }

    public static int[][] generateWeightedGraph(int size) {
        Random random = new Random();
        int[][] matrix = new int[size][size];
        for (int i = 0; i < size; i++) {
            Arrays.fill(matrix[i], -1);
        }

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (matrix[i][j] == -1) {
                    matrix[i][j] = (i == j) || random.nextInt(10) > 5 ? 0 : random.nextInt(10);
                    matrix[j][i] = matrix[i][j];
                }
            }
        }
        Util.printMatrix(matrix);
        return matrix;
    }

    public static Graph drawGraph(int[][] input, String title, int activeNode) {
        Graph g = graph("Graph")
                .graphAttr().with(Label.of(title))
                .linkAttr().with("class", "link-class");
        for (int i = 0; i < input.length; i++) {
            for (int j = i; j < input.length; j++) {
                if (input[i][j] == 0) {
                    continue;
                }
                if (i == j) {
                    g = g.with(node((char) (65 + i) + " " + input[i][i])
                            .with(i == activeNode ? Color.RED : Color.BLUE));
                } else {
                    g = g.with(node((char) (65 + i) + " " + input[i][i])
                            .with(i == activeNode ? Color.RED : Color.BLUE)
                            .link(to(node((char) (65 + j) + " " + input[j][j]))
                                    .with(attr("label", input[i][j]), Style.DASHED)));
                }
            }
        }
        return g;
    }

    public static Graph drawTree(Node[] nodes, String title) {
        Graph tree = graph("tree").directed()
                .graphAttr().with(Rank.dir(BOTTOM_TO_TOP), Label.of(title))
                .linkAttr().with("class", "link-class");
        for (int i = nodes.length - 1; i > 0; i--) {
            tree = tree.with(node(Character.toString(nodes[i].index + 65))
                    .link(to(node(Character.toString(nodes[i].parent.index + 65)))
                            .with(attr("label", nodes[i].value), Style.DASHED)));
        }
        return tree;
    }

    public static void saveGraph(Graph g, Engine engine, String filePath) throws IOException {
        Graphviz.fromGraph(g).engine(engine).render(Format.PNG).toFile(new File(filePath));
    }

    public static int getBit(int num, int index) {
        return (num >> (index - 1)) & 1;
    }

    public static int setBit(int num, int index, int bit) {
        if (bit == 1) {
            num = num | (1 << (index - 1));
        }
        if (bit == 0) {
            num = num & ~(1 << (index - 1));
        }
        return num;
    }

    public static double log(double num, int base) {
        return Math.log(num) / Math.log(base);
    }

    public static String padBinary(int num, int count) {
        return String.format("%" + count + "s", Integer.toBinaryString(num)).replace(' ', '0');
    }
}
