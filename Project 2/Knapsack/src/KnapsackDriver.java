/* Amir Yamini
 * Professor Schwartz
 * 11-09-2017
 * Project 2 - KnapsackDriver
 */

public class KnapsackDriver {

    public static void main( String[] args)
    {
        /*int n = 7;
        int[] weights = {-1, 60, 50, 60, 50, 70, 70, 45};
        int W = 100;
        int[] benefits = {-1, 180, 95, 40, 95, 40, 40, 105};

        int [] weightss = {-1, 2, 4, 3, 4, 4, 1};
        int Ws = 10;
        int [] valuess = {-1, 1, 2, 3, 3, 3, 6};

        // Print input values as required in Project 2

        System.out.println("\nBrute Force Solution");
        Knapsack kp1 = new Knapsack(W, weights, benefits);
        kp1.BruteForceSolution();

        System.out.println("\nDynamic Programming Solution");
        Knapsack kp3 = new Knapsack(Ws,weightss, valuess);
        kp3.DynamicProgrammingSolution(true);

        System.out.println("\nGreedy Approximate Solution");
        Knapsack kp4 = new Knapsack(W, weights, benefits);
        kp4.GreedyApproximateSolution();*/

//        //Testcase #1
        System.out.println("Number 1");
        int n = 7;
        int[] weights1 = {-1, 60, 50, 60, 50, 70, 70, 45};
        int W1 = 100;
        int[] benefits1 = {-1, 180, 95, 40, 95, 40, 40, 105};

        Knapsack kp1 = new Knapsack(W1, weights1, benefits1);
        System.out.println("\nBrute Force Solution");
        kp1.BruteForceSolution();
        System.out.println("\nDynamic Programming Solution");
        kp1.DynamicProgrammingSolution(false);
        System.out.println("\nGreedy Approximate Solution");
        kp1.GreedyApproximateSolution();
        System.out.println();
//
//        //Testcase #2
          System.out.println();
        System.out.println("Number 2");
        int n2 = 18;
        int[] weights2 = {-1,25,4,2,5,6,2,7,8,2,1,1,3,5,8,9,6,3,2};
        int W2 = 39;
        int[] benefits2 = {-1,75,7,4,3,2,6,8,7,9,6,5,4,8,10,8,1,2,2};

        Knapsack kp2 = new Knapsack(W2, weights2, benefits2);
        System.out.println("\nBrute Force Solution");
        kp2.BruteForceSolution();
        System.out.println("\nDynamic Programming Solution");
        kp2.DynamicProgrammingSolution(false);
        System.out.println("\nGreedy Approximate Solution");
        kp2.GreedyApproximateSolution();
        System.out.println();

        //Testcase #3
        System.out.println();
        System.out.println("Number 3");
        int n3 = 20;
        int[] weights3 = {-1,10,14,35,12,16,20,13,7,2,4,3,10,5,6,17,7,9,3,4,3};
        int W3 = 29;
        int[] benefits3 = {-1,2,13,41,1,12,5,31,2,41,16,2,12,1,13,4,51,6,12,1,9};

        Knapsack kp3 = new Knapsack(W3, weights3, benefits3);
        System.out.println("\nBrute Force Solution");
        kp3.BruteForceSolution();
        System.out.println("\nDynamic Programming Solution");
        kp3.DynamicProgrammingSolution(false);
        System.out.println("\nGreedy Approximate Solution");
        kp3.GreedyApproximateSolution();
        System.out.println();

        //Testcase #4
        System.out.println();
        System.out.println("Number 4");
        int n4 = 7;
        int[] weights4 = {-1,2,5,3,2,5,3,7 };
        int W4 = 10;
        int[] benefits4 = {-1,5,10,5,20,15,5,10};

        Knapsack kp4 = new Knapsack(W4, weights4, benefits4);
        System.out.println("\nBrute Force Solution");
        kp4.BruteForceSolution();
        System.out.println("\nDynamic Programming Solution");
        kp4.DynamicProgrammingSolution(true);
        System.out.println("\nGreedy Approximate Solution");
        kp4.GreedyApproximateSolution();
        System.out.println();
    }
}
