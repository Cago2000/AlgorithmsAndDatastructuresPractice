import java.lang.Math;
import java.util.Random;

public class Problems {

    public static double hireProblem(int amountOfRuns, int candidateCount)
    {
        long startTime = System.nanoTime();
        double sumOfHires = 0;
        for(int k = 1; k <= amountOfRuns; k++)
        {
            Random random = new Random();
            double[] candidates = new double[candidateCount];
            for (int i = 0; i < candidateCount; i++)
            {
                candidates[i] = random.nextDouble(0,10000); //random double from 0 to 10000.0
            }
            double currentBest = -1;
            double countOfHires = 0;

            double percentage = ((double) k/amountOfRuns)*100.0;
            System.out.println("Run " + k + "/" + amountOfRuns+" (" + (int) percentage + "%)");
            for(int i = 0; i < candidateCount; i++)
            {
                if(candidates[i] > currentBest)
                {
                    currentBest = candidates[i];
                    countOfHires++;
                }
            }
            sumOfHires += countOfHires;
        }
        double averageHires = sumOfHires/amountOfRuns;
        long endTime = System.nanoTime();
        long totalRuntimeInMs = (endTime - startTime)/1000000;
        System.out.println("Average of hires for " + candidateCount +" candidates in " + amountOfRuns+ " runs: " + averageHires);
        System.out.println("Natural log of " + candidateCount + ": " + Math.log(candidateCount));
        System.out.println("Runtime: " + totalRuntimeInMs + "ms");
        return averageHires;
    }
}
