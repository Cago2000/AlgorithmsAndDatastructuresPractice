import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
        //bubbleSort();
        //insertionSort();
        System.out.println(euclideanAlgorithm(56,44));
    }

    public static void insertionSort()
    {
        int comparisons = 0;
        int[] input = Util.makeRandomArray();
        //input = new int[]{1,4,3,5,2}; // test array
        //input = new int[]{1,2,3,4,5,6,7,8,9,10}; // sorted array
        comparisons = 0;
        boolean sorted;
        System.out.println("Insertion Sort:");
        Util.printArray(input);
        sorted = Util.arrayIsSorted(input);
        if(!sorted)
        {
            for(int i = 1; i < input.length; i++)
            {
                for(int j = i; j > 0; j--)
                {
                    comparisons++;
                    if(input[j] < input[j-1])
                    {
                        int temp;
                        temp = input[j-1];
                        input[j-1] = input[j];
                        input[j] = temp;
                        System.out.println("Swapped " + input[j-1] + " with "+ input[j] + ", Comparisons = " + comparisons);
                        Util.printArray(input);
                    }
                }
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void bubbleSort()
    {
        int comparisons = 0;
        int[] input = Util.makeRandomArray();
        //input = new int[]{1,2,3,4,5,6,7,8,9,10}; // sorted array
        comparisons = 0;
        boolean sorted;
        System.out.println("Bubble Sort:");
        Util.printArray(input);
        sorted = Util.arrayIsSorted(input);
        if(!sorted)
        {
            for(int i = 0; i < input.length; i++)
            {
                for(int j = i + 1; j < input.length;j++)
                {
                    comparisons++;
                    if(input[i] > input[j])
                    {
                        int temp;
                        temp = input[i];
                        input[i] = input[j];
                        input[j] = temp;
                        System.out.println("Swapped " + input[j] + " with "+ input[i] + ", Comparisons = " + comparisons);
                        Util.printArray(input);
                    }
                }
            }
        }
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    public static int euclideanAlgorithm(int a, int b)
    {
        if(b>a)
        {
            int temp = a;
            a = b;
            b = temp;
        }
        if(b == 0){return a;}
        while(a>b)
        {
            a -= b;
        }
        euclideanAlgorithm(a, a % b);
        return a;
    }
}


