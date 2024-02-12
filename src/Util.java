import java.util.Random;

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

    public static void swapElements(int[] input, int a, int b)
    {
        int temp = input[a];
        input[a] = input[b];
        input[b] = temp;
    }
}
