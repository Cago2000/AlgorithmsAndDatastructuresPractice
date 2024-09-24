import java.util.*;
import java.util.concurrent.TimeUnit;

public class Problems {

    public static void fiveComparisonProblem() {
        int[] input = Util.makeRandomArray(4, 100);
        Util.printArray(input);
        if (input[0] > input[1]) {
            Util.swapElements(input, 0, 1);
        }
        if (input[2] > input[3]) {
            Util.swapElements(input, 2, 3);
        }
        if (input[0] > input[2]) {
            Util.swapElements(input, 0, 2);
        }
        if (input[1] > input[3]) {
            Util.swapElements(input, 1, 3);
        }
        if (input[1] > input[2]) {
            Util.swapElements(input, 1, 2);
        }
        Util.printArray(input);
    }

    public static double hireProblem(int amountOfRuns, int candidateCount) {
        long startTime = System.nanoTime();
        double sumOfHires = 0;
        for (int k = 1; k <= amountOfRuns; k++) {
            Random random = new Random();
            double[] candidates = new double[candidateCount];
            for (int i = 0; i < candidateCount; i++) {
                candidates[i] = random.nextDouble(0, 10000); //random double from 0 to 10000.0
            }
            double currentBest = -1;
            double countOfHires = 0;

            double percentage = ((double) k / amountOfRuns) * 100.0;
            System.out.println("Run " + k + "/" + amountOfRuns + " (" + (int) percentage + "%)");
            for (int i = 0; i < candidateCount; i++) {
                if (candidates[i] > currentBest) {
                    currentBest = candidates[i];
                    countOfHires++;
                }
            }
            sumOfHires += countOfHires;
        }
        double averageHires = sumOfHires / amountOfRuns;
        long endTime = System.nanoTime();
        long totalRuntimeInMs = (endTime - startTime) / 1000000;
        System.out.println("Average of hires for " + candidateCount + " candidates in " + amountOfRuns + " runs: " + averageHires);
        System.out.println("Natural log of " + candidateCount + ": " + Math.log(candidateCount));
        System.out.println("Runtime: " + totalRuntimeInMs + "ms");
        return averageHires;
    }

    public static void hatProblem(int amountOfRuns, int hatCount) {
        long startTime = System.nanoTime();
        float averageCorrectHats = 0;
        for (int i = 0; i < amountOfRuns; i++) {

            double percentage = ((double) i / amountOfRuns) * 100.0;
            System.out.println("Run " + i + "/" + amountOfRuns + " (" + (int) percentage + "%)");
            Integer[] input = new Integer[hatCount];
            for (int j = 0; j < input.length; j++) {
                input[j] = j;
            }
            List<Integer> list = Arrays.asList(input);
            Collections.shuffle(list);
            list.toArray(input);

            int correctHats = 0;
            for (int j = 0; j < input.length; j++) {
                if (j == input[j]) {
                    correctHats++;
                }
            }
            averageCorrectHats += correctHats;
        }
        long endTime = System.nanoTime();
        long totalRuntimeInMs = (endTime - startTime) / 1000000;
        System.out.println("On average " + averageCorrectHats / amountOfRuns + " hats have been returned to their rightful owner in " + amountOfRuns + " runs");
        System.out.println("Runtime: " + totalRuntimeInMs + "ms");
    }

    public static void binarySearchTreeSequenceProblem(int[] input, int searchedNumber) {
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int number : input) {
            if (searchedNumber > number) {
                right.add(number);
            }
            if (searchedNumber < number) {
                left.add(number);
            }

        }
        boolean valid = Util.arrayIsSorted(Util.reverseArray(left.stream().mapToInt(x -> x).toArray())) && Util.arrayIsSorted(right.stream().mapToInt(x -> x).toArray());
        System.out.print("Valid: " + valid + ", Input: ");
        Util.printArray(input);
    }

    public static void findMissingBitProblem(int bitCount) {
        int numberCount = (int) Math.pow(2, bitCount);
        int missingIndex = new Random().nextInt(numberCount) + 1;
        int[] numbers = new int[numberCount - 1];
        for (int i = 0, index = 0; i < numberCount; i++) {
            if (i != missingIndex) {
                numbers[index] = i + 1;
                index++;
            }
        }
        Util.printArray(numbers);
        System.out.println("Missing number: " + findMissingBit(0, 1, numbers, (int) Util.log((numbers.length + 1), 2)));
    }

    public static int findMissingBit(int missingNumber, int bitIndex, int[] numbers, int bitCount) {
        int zeroCount = 0;
        int oneCount = 0;
        for (int number : numbers) {
            if (Util.getBit(number, bitIndex) == 0) {
                zeroCount++;
            } else {
                oneCount++;
            }
        }
        if (zeroCount > oneCount) {
            missingNumber = Util.setBit(missingNumber, bitIndex, 1);
        }
        System.out.println("bitIndex: " + bitIndex
                + " | zeros: " + zeroCount
                + " | ones: " + oneCount
                + " | bits: " + Util.padBinary(missingNumber, bitCount)
                + " | number: " + missingNumber);
        if (bitIndex < bitCount) {
            missingNumber = findMissingBit(missingNumber, bitIndex + 1, numbers, bitCount);
        }
        return missingNumber;
    }

    public static void maxTeilfeldProblem(int size) {
        Random random = new Random();
        int[] array = random.ints(size, -100, 100).toArray();
        int maximum = 0, currentMaximum = 0;
        long startTime = System.nanoTime();
        for (int value : array) {
            currentMaximum = Math.max(currentMaximum + value, 0);
            maximum = Math.max(maximum, currentMaximum);
        }
        System.out.println("Best sum: " + maximum);
        long endTime = System.nanoTime() - startTime;
        System.out.println("Time for maxTeilfeldProblem with " + array.length + " elements in ms: " + (float) TimeUnit.NANOSECONDS.toMillis(endTime));
    }
    public static boolean montyHallProblem(boolean switch_doors)
    {
        Random random = new Random();
        int[] doors = new int[]{0, 0, 0};
        int car = random.nextInt(3);
        doors[car] = 1;
        Util.printArray(doors);
        int firstChoice = random.nextInt(3);
        System.out.println("First Choice: " + (firstChoice+1));
        int firstReveal = firstChoice;
        while(firstReveal == firstChoice || doors[firstReveal] != 0){
            firstReveal = random.nextInt(3);
        }
        System.out.println("First Reveal: A goat was behind door number " + (firstReveal+1) + "!");
        int secondChoice = firstChoice;
        int secondReveal = firstReveal;
        if(switch_doors)
        {
            while(secondChoice == firstChoice || secondChoice == firstReveal){
                secondChoice = random.nextInt(3);
            }
            System.out.println("You are switching to door number " + (secondChoice+1) + "!");
            while(secondReveal == secondChoice || secondReveal == firstReveal) {
                secondReveal = random.nextInt(3);
            }
            if(doors[secondReveal] == 1){
                System.out.println("Second Reveal: A car was behind door number " + (secondReveal+1) + "!");
                System.out.println("Second Choice: A goat was behind door number " + (secondChoice+1) + "! You lost!");
                return false;
            }
            else
            {
                System.out.println("Second Reveal: A goat was behind door number " + (secondReveal+1) + "!");
                System.out.println("Second Choice: A car was behind door number " + (secondChoice+1) + "! You won!");
                return true;
            }
        }
        return firstChoice == car;
    }

    public static void montyHallRuns(int runs, boolean switch_doors)
    {
        int wins = 0, losses = 0;
        for(int i = 0; i < runs; i++)
        {
            if(Problems.montyHallProblem(switch_doors)){
                wins++;
                continue;
            }
            losses++;
        }
        System.out.println("Wins: " + wins + ", Losses: " + losses);
        System.out.print("Win% for "+ (switch_doors ? "Switch" : "Stay"));
        System.out.println("-Policy: " + (float) wins/runs);
    }
}
