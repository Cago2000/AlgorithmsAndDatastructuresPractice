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
        while(a>b)
        {
            a -= b;
        }
        euclideanAlgorithm(a, a % b);
        return a;
    }

    public static void binarySearchInit()
    {
        int[] input = new int[]{1,2,3,4,6,8,9,11};
        Util.printArray(input);
        binarySearch(input,0,input.length,9,0);
    }
    public static void binarySearch(int[] input,int left, int right, int wantedNumber, int comparisonCount)
    {
        if(right - left < 1)
        {
            return;
        }
        int mid = (left + right) / 2;
        System.out.println(input[mid]);
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
}
