import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class Sorts {
    public static void heapSort()
    {
        int[] input = Util.makeRandomArray();
        input[0] = 0;
        Util.printArray(input);
        int n = input.length-1;
        for (int i = n/2; i >= 1 ; i--)
        {
            Util.maxHeapify(input,i,n);
        }
        for(int i = n; i >= 2 ; i--)
        {
            Util.swapElements(input,1,i);
            Util.maxHeapify(input,1,i-1);
        }
        Util.printArray(input);
    }

    public static void mergeSortInit()
    {
        int[] input = Util.makeRandomArray();
        Util.printArray(input);
        input = mergeSort(input,0,input.length-1);
        Util.printArray(input);
    }

    public static int[] mergeSort(int[] input, int left, int right)
    {
        if(left>=right)
        {
            return new int[]{input[left]};
        }

        int mid = (left+right)/2;
        int[] leftSide = mergeSort(input,left,mid);
        int[] rightSide = mergeSort(input,mid+1,right);
        int[] mergedArray = new int[leftSide.length+rightSide.length];

        int leftCounter = 0, rightCounter = 0, mergedCounter = 0;
        while(leftCounter < leftSide.length && rightCounter < rightSide.length)
        {
            if(leftSide[leftCounter] < rightSide[rightCounter])
            {
                mergedArray[mergedCounter] = leftSide[leftCounter];
                leftCounter++;
                mergedCounter++;
            }
            else
            {
                mergedArray[mergedCounter] = rightSide[rightCounter];
                rightCounter++;
                mergedCounter++;
            }
        }
        while(leftCounter < leftSide.length)
        {
            mergedArray[mergedCounter] = leftSide[leftCounter];
            leftCounter++;
            mergedCounter++;
        }
        while(rightCounter < rightSide.length)
        {
            mergedArray[mergedCounter] = rightSide[rightCounter];
            rightCounter++;
            mergedCounter++;
        }
        return mergedArray;
    }

    public static void countingSort(int size, int maxValue)
    {
        int[] input = Util.makeRandomArray(size, maxValue);
        int[] output = new int[input.length];
        //Util.printArray(input);

        long startTime = System.nanoTime();
        int maxNumber = 0;
        for(int i:input)
        {
            if(i > maxNumber){maxNumber = i;}
        }
        int[] countingArray = new int[maxNumber+1];

        Arrays.fill(countingArray,0,countingArray.length-1,0);

        for(int i:input) {
            countingArray[i] += 1;
        }
        for(int i = 1; i < countingArray.length; i++) {
            countingArray[i]+=countingArray[i-1];
        }
        for (int i = input.length-1; i >= 0 ; i--) {
            output[countingArray[input[i]]-1] = input[i];
            countingArray[input[i]] -= 1;
        }
        long endTime = System.nanoTime() - startTime;
        //Util.printArray(output);
        System.out.println("Time for counting sort in ms: " + (float) TimeUnit.NANOSECONDS.toMillis(endTime));
    }


    public static void quickSortInit(int size, int maxValue)
    {
        long startTime = System.nanoTime();
        int[] input = Util.makeRandomArray(size, maxValue);
        //Util.printArray(input);
        input = quickSort(input,0,input.length);
        //Util.printArray(input);
        long endTime = System.nanoTime() - startTime;
        System.out.println("Time for quick sort in ms: " + (float) TimeUnit.NANOSECONDS.toMillis(endTime));
    }
    public static int[] quickSort(int[] input,int left,int right)
    {
        if(left>=right){return input;}

        int pivotElement = input[left];
        int j = left;
        for(int i = left+1; i < right; i++)
        {
            if(input[i]<=pivotElement)
            {
                j = j + 1;
                Util.swapElements(input,i,j);
            }
        }
        Util.swapElements(input,left,j);
        quickSort(input,left,j);                //recursive calls
        quickSort(input,j+1,right);
        return input;
    }
    public static void insertionSort()
    {
        int comparisons = 0;
        int[] input = Util.makeRandomArray();
        boolean sorted = Util.arrayIsSorted(input);
        System.out.println("Insertion Sort:");
        Util.printArray(input);
        if(!sorted)
        {
            for(int i = 1; i < input.length; i++)
            {
                for(int j = i; j > 0; j--)
                {
                    comparisons++;
                    if(input[j] < input[j-1])
                    {
                        Util.swapElements(input,j-1,j);
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
        int[] input = Util.makeRandomArray(10000, 100);
        System.out.println("Bubble Sort with " + input.length + " elements:");
        Util.printArray(input);
        if(!Util.arrayIsSorted(input))
        {
            for(int i = 0; i < input.length; i++)
            {
                for(int j = i + 1; j < input.length;j++)
                {
                    comparisons++;
                    if(input[i] > input[j])
                    {
                        Util.swapElements(input,i,j);
                        //System.out.println("Swapped " + input[j] + " with "+ input[i] + ", Comparisons = " + comparisons);
                        //Util.printArray(input);
                    }
                }
            }
        }
        System.out.println("comparisons: " + comparisons + ", n²:" + Math.pow(input.length, 2));
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------");
    }
}
