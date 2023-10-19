import java.util.Random;

public class Main {
    static int comparisons = 0;
    public static void main(String[] args)
    {
        bubbleSort();
        insertionSort();
    }

    public static void insertionSort()
    {
        int[] input = makeRandomArray();
        //input = new int[]{1,4,3,5,2}; // test array
        //input = new int[]{1,2,3,4,5,6,7,8,9,10}; // sorted array
        comparisons = 0;
        boolean sorted;
        System.out.println("Insertion Sort:");
        printArray(input);
        sorted = arrayIsSorted(input);
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
                        printArray(input);
                    }
                }
            }
        }
        comparisons = 0;
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void bubbleSort()
    {
        int[] input = makeRandomArray();
        //input = new int[]{1,2,3,4,5,6,7,8,9,10}; // sorted array
        comparisons = 0;
        boolean sorted;
        System.out.println("Bubble Sort:");
        printArray(input);
        sorted = arrayIsSorted(input);
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
                        printArray(input);
                    }
                }
            }
        }
        comparisons = 0;
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }

    public static void printArray(int[] input)
    {
        for (int i:input)
        {
            System.out.print(i +" ");
        }
        System.out.println();
    }

    public static int[] makeRandomArray()
    {
        Random random = new Random();
        int arraySize = random.nextInt(40) + 1; // random size 1-40
        int[] array = new int[arraySize];
        for (int i = 0; i < arraySize; i++)
        {
            array[i] = random.nextInt(101); // random number 1-100
        }
        return array;
    }

    public static boolean arrayIsSorted(int[] input)
    {
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
}


