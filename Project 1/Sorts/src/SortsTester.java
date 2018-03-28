
/* Amir Yamini
 */

import java.util.Random;
import java.util.Timer;

public class SortsTester {

	public static void main (String args[])
	{
		Sorts soides = new Sorts();
		
		//BOTH

		long start;
		long end;
		long timeM;
		long timeQ;
		int trialCount = 0;

		int quickCount = 0;
		int mergeCount = 0;

		long quickRun = 0;
		long mergeRun = 0;

		long qAverage = 0;
		long mAverage = 0;

		int arrayCount = 0;
		int arraySize[] = {10,100,1000,10000,100000,1000000,2000000};


		while(arrayCount < 7) {

			int rand1[] = new int[arraySize[arrayCount]];
			int rand2[] = new int[arraySize[arrayCount]];

			System.out.println(arrayCount);

			trialCount = 0;

			mergeRun = 0;
			quickRun = 0;

			mergeCount = 0;
			quickCount = 0;

			while(trialCount < 20) {

				//MERGESORT

				for(int i = 0; i < arraySize[arrayCount]; i++) {
					rand1[i] = (int) (Math.random() * 1000000 + 1);
					rand2[i] = rand1[i];
				}

				start = System.nanoTime();
				soides.mergeSort(rand1, 0, arraySize[arrayCount]-1);
				end = System.nanoTime();

				timeM = end - start;
				//System.out.println(timeM);

				//QUICKSORT

				start = System.nanoTime();
				soides.quickSort(rand2, 0, arraySize[arrayCount]-1);
				end = System.nanoTime();

				timeQ = end - start;
				//System.out.println(timeQ);

				mergeRun += timeM;
				quickRun += timeQ;

				if(timeM > timeQ) {
					//System.out.println("QuickSort Won!");
					quickCount++;
					//System.out.println(quickCount);
				}else {
					//System.out.println("MergeSort Won!");
					mergeCount++;
					//System.out.println(mergeCount);
				}

				trialCount++;	
			}// trials loop

			mAverage = mergeRun/20;
			qAverage = quickRun/20;

			System.out.println(arraySize[arrayCount]);

			System.out.println("M:" + mergeCount + "," + "Q:" + quickCount);

			System.out.println("Merge Average: " + mAverage + ", " + "Quick Average: " + qAverage);

			System.out.println("The mean MergeSort runtime is: " + mAverage / (arraySize[arrayCount] * (Math.log(arraySize[arrayCount]) / Math.log(2))));

			System.out.println("The mean QuickSort runtime is: " + qAverage / (arraySize[arrayCount] * (Math.log(arraySize[arrayCount]) / Math.log(2))));

			arrayCount++;
		}
	} 
}