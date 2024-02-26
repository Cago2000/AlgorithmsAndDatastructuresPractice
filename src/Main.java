import java.io.IOException;
public class Main {

    public static void main(String[] args) throws IOException {
        //Sorts.bubbleSort();
        //Sorts.insertionSort();
        //System.out.println(Algorithms.euclideanAlgorithm(56,44));
        //Sorts.quickSortInit();
        //Sorts.countingSort();
        //Sorts.mergeSortInit();
        //Sorts.heapSort();
        //Problems.hireProblem(100,10000000);
        //Problems.fiveComparisonProblem();
        //Algorithms.binarySearchInit();
        //Problems.hatProblem(100000);
        //Algorithms.primsAlgorithm(6);
        Problems.binarySearchTreeSequenceProblem(new int[]{2,252,401,398,330,344,397,363},363);
        Problems.binarySearchTreeSequenceProblem(new int[]{925,202,911,240,912,245,363},363);
        Problems.binarySearchTreeSequenceProblem(new int[]{935,278,347,621,299,392,358,363},363);
    }
}