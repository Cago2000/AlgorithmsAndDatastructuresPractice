import java.util.Arrays;

public class Sorts {
    public static void heapSort()
    {
        int[] input = Util.makeRandomArray();
        input[0] = 0;
        Util.printArray(input);
        int n = input.length-1;
        for (int i = n/2; i >= 1 ; i--)
        {
            maxHeapify(input,i,n);
        }
        for(int i = n; i >= 2 ; i--)
        {
            int temp = input[1];
            input[1] = input[i];
            input[i] = temp;
            maxHeapify(input,1,i-1);
        }
        Util.printArray(input);
    }
    public static void maxHeapify(int[] input, int parent, int n)
    {
        int leftChild = 2*parent;
        int rightChild= (2*parent)+1;
        int largest;
        if(leftChild <= n && input[leftChild] > input[parent])
        {
            largest = leftChild;
        }
        else
        {
            largest = parent;
        }
        if(rightChild <= n && input[rightChild] > input[largest])
        {
            largest = rightChild;
        }
        if(largest != parent)
        {
            int temp = input[parent];
            input[parent] = input[largest];
            input[largest] = temp;
            maxHeapify(input,largest,n);
        }
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
        for (int i = left; i <= right; i++) {
            System.out.print(input[i]+" ");
        }
        System.out.println();

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

    public static void countingSort()
    {
        int[] input = Util.makeRandomArray();
        int[] output = new int[input.length];
        Util.printArray(input);

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
        Util.printArray(output);
    }


    public static void quickSortInit()
    {
        int[] input = Util.makeRandomArray();
        Util.printArray(input);
        Util.printArray(quickSort(input,0,input.length));
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
                int temp = input[i];
                input[i] = input[j];
                input[j] = temp;
            }
        }
        int temp = input[j];                    //swapping pivot to correct place ==> j counter
        input[j] = input[left];
        input[left] = temp;

        System.out.println("Pivot: " + pivotElement);
        System.out.print("Left Partition: ");
        for (int a = 0; a <= j; a++) {
            System.out.print(input[a]+" ");
        }
        System.out.print("\nRight Partition: ");
        for (int a = j+1; a < right; a++) {
            System.out.print(input[a]+" ");
        }
        System.out.println("\n----------------------------------------");


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
}
