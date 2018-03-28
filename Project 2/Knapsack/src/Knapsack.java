/* Amir Yamini
 * Professor Schwartz
 * 11-09-2017
 * Project 2 - Knapsack
 */

import java.util.*;

public class Knapsack
{

    //set private fields here
    private int W;
    private int[] w;
    private int[] b;

    public Knapsack(int W, int[] w, int[] b) {
        //constructor
        this.W = W;
        this.w = w;
        this.b = b;
    }

    public static int[] generateSubset(int k, int n) {
        //  0 <= k <= 2n - 1
        //  Generates the kth subset of { 0,1,..., n-1 }
        //  in the form of the binary representation of k

        int[] subArray = new int[n];

        for (int l = n - 1; l >= 0; l--) {
            //make the first cell mod and the next one div
            //mod k first to get first value of new array
            //then divide k by 2 and update value and repeat
            if (k % 2 == 0) {
                subArray[l] = 0;
            } else if (k % 2 == 1) {
                subArray[l] = 1;
            }
            k = k / 2;
        }
        return subArray;
    }

    public void BruteForceSolution() {
        //Prints all optimal solutions to the 0-1 knapsack problem
        //using brute force algorithm described in Project 2
        //Print solution in format specified in Project 2
        int limit = (int)Math.pow(2,w.length-1)-1;
        int maxBenefit = 0;
        ArrayList<int[]> subsets = new ArrayList<>();
        ArrayList<Integer> subsetBenefits = new ArrayList<>();
        ArrayList<Integer> subsetWeights = new ArrayList<>();

        for(int i = 0; i < limit; i++){
            int[] temp = generateSubset(i,w.length);
            int weightWatchers = 0;
            int benefitWatchers = 0;
            for(int j = temp.length - 1; j > 0; j--){
                if(temp[j] == 1){
                    benefitWatchers += b[b.length - j];
                    weightWatchers += w[w.length - j];
                }
            }
            subsets.add(temp);
            subsetBenefits.add(benefitWatchers);
            subsetWeights.add(weightWatchers);
        }

        for(int i = 0; i < subsetWeights.size(); i++){
            if(subsetWeights.get(i) > W){
                subsets.set(i, null);
                subsetBenefits.set(i,null);
                subsetWeights.set(i,null);
            }
        }

        for(int i = 0; i < subsetBenefits.size(); i++){
            Integer benefit = subsetBenefits.get(i);
            if(benefit != null && benefit > maxBenefit){
                maxBenefit = subsetBenefits.get(i);
            }
        }

        for(int i = 0; i < subsetBenefits.size(); i++){
            if(subsetBenefits.get(i) != null && subsetBenefits.get(i) < maxBenefit){
                subsets.set(i, null);
                subsetBenefits.set(i,null);
                subsetWeights.set(i,null);
            }
        }

        for(int i = 0; i < subsets.size(); i++) {
            if(subsets.get(i) != null){
                System.out.print("Optimal set= {");
                int length = w.length - 1;
                for(int j = 0; j < length; j++){
                    if(subsets.get(i) != null && subsets.get(i)[length - j] == 1){
                        System.out.print(j+1);
                        if(j+1 < length){
                            System.out.print(", ");
                        }
                    }
                }
                System.out.print("} ");

                System.out.print("weight sum = " + subsetWeights.get(i));
                System.out.println(" benefit sum = " + subsetBenefits.get(i));
            }
        }
    }

    public void DynamicProgrammingSolution(boolean printBmatrix) {

        //Prints one optimal solutions to the 0-1 knapsack problem
        // using dynamic programming algorithm described in Project 2
        // Print solution in format specified in Project 2
        // If printmatrix is true, print the OPT matrix.
        int wCol = W + 1;
        int bRows = b.length;
        int[][] B = new int[bRows][wCol];

        for (int k = 1; k < bRows; k++) {
            for (int i = 1; i < wCol; i++) {
                if (i < w[k]) {
                    B[k][i] = B[k - 1][i];
                } else {
                    B[k][i] = Math.max(B[k - 1][i], B[k - 1][i - w[k]] + b[k]);
                }
            }
        }

        int rowTrack = bRows - 1;
        int columnTrack = wCol - 1;
        int optimalBenefit = B[rowTrack][columnTrack];
        int tracker = 0;
        int [] turnAround = new int[bRows];

        while(rowTrack > 0) {
            if (B[rowTrack][columnTrack] == B[rowTrack - 1][columnTrack]) {
                rowTrack--;
            } else {
                turnAround[tracker] = rowTrack;
                columnTrack -= w[rowTrack];
                tracker++;
                rowTrack--;
            }
        }

        int optimalWeight = 0;
        System.out.print("Optimal set= {");

        int [] flipIt = new int[turnAround.length];
        for(int f = 0; f < flipIt.length; f++){
            flipIt[f] = turnAround[turnAround.length-1-f];
        }

        for(int p = 0; p < flipIt.length; p++){
            if(flipIt[p] != 0) {
                System.out.print(flipIt[p]);
                optimalWeight += w[flipIt[p]];
            }
            if (p + 1 != flipIt.length && flipIt[p+1] != 0 && flipIt[p] != 0) {
                System.out.print(", ");
            }
        }
        System.out.print("} ");

        System.out.print("weight sum = " + optimalWeight);
        System.out.println(" benefit sum = " + optimalBenefit);

        if(printBmatrix) {
            for (int p = 0; p < bRows; p++) {
                for (int r = 0; r < wCol; r++) {
                    System.out.print(B[p][r] + " ");
                }
                System.out.println();
            }
        }
    }

    public void GreedyApproximateSolution() {
        //Prints one approximate solution to the 0-1 knapsack problem using a variation of
        //used to solve the Fractional Knapsack Problem.
        double[] values = new double[w.length];
        int weightTotal = 0;
        int benefitTotal = 0;
        double divv;
        int maxBenefit = 0;
        int maxWeight = 0;
        double maxValue = 0;
        int maxValueIndex = 0;

        int[] positions = new int[w.length];


        for (int i = 0; i < values.length; i++) {
            divv = (double) b[i] / w[i];
            values[i] = divv;
        }
        int count = 0;
        int posCount = 0;

        while (count < values.length) {
            maxValue = Integer.MIN_VALUE;
            // Finds maxValue
            for (int v = 0; v < values.length; v++) {
                if (values[v] > maxValue) {
                    maxValue = values[v];
                    maxValueIndex = v;
                }
            }
            weightTotal += w[maxValueIndex];

            if (weightTotal <= W) {
                benefitTotal += b[maxValueIndex];
                positions[posCount] = maxValueIndex;
                posCount++;
                values[maxValueIndex] = Integer.MIN_VALUE;
            } else {
                weightTotal -= w[maxValueIndex];
                break;
            }
            count++;
        }

        System.out.print("Optimal set= { ");
        for (int a = 0; a < positions.length; a++) {
            System.out.print(positions[a]);
            if(a+1 != positions.length && a+1 != 0){

                System.out.print(", ");

            }
        }
        System.out.print("} ");

        System.out.print(" weight sum = " + weightTotal + " benefit sum = " + benefitTotal);
    }



}