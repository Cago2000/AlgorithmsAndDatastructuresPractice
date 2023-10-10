import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
        bubbleSort();
    }

    public static void bubbleSort()
    {
        Random random = new Random();
        int arraySize = random.nextInt(40) + 1; // random size 1-40
        int[] input = new int[arraySize];

        for (int i = 0; i < arraySize; i++) {
            input[i] = random.nextInt(101); // random number 1-100
        }

        //input = new int[]{1,2,3,4,5,6,7,8,9,10}; // sorted array
        int comparisons = 0;
        boolean sorted = false;
        printArray(input);

        for(int i = 1; i < input.length;i++) // check if input is sorted
        {
            comparisons++;
            if(input[i-1]>input[i])
            {
                break;
            }
            else if(i == input.length-1){
                sorted = true;
            }
        }

        if(!sorted){
            for(int i = 0; i < input.length; i++){
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
        else
        {
            System.out.println("Comparisons = " + comparisons);
        }
    }

    public static void printArray(int[] input) {
        for (int i:input) {
            System.out.print(i +" ");
        }
        System.out.println();
    }
}


