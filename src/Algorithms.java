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
}
