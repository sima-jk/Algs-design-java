/**
 * 
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 2, September 2018.
 * 
 * @author Sima Jeddi
 * 
 * Question 1  
 * 
 * Download the following text file: "QuickSort.txt"
 * The file contains all of the integers between 1 and 10,000 (inclusive, with no repeats) in
 * unsorted order. The integer in the ith row of the file gives you the ith entry of an input 
 * array.
 *
 * Your task is to compute the total number of comparisons used to sort the given input file
 * by QuickSort. As you know, the number of comparisons depends on which elements are chosen
 * as pivots, so we'll ask you to explore three different pivoting rules.
 *
 * You should not count comparisons one-by-one. Rather, when there is a recursive call on a 
 * subarray of length mm, you should simply add m-1m−1 to your running total of comparisons. 
 * (This is because the pivot element is compared to each of the other m-1m−1 elements in the 
 * subarray in this recursive call.)
 *
 * WARNING: The Partition subroutine can be implemented in several different ways, and 
 * different implementations can give you differing numbers of comparisons. For this problem,
 * you should implement the Partition subroutine exactly as it is described in the video 
 * lectures (otherwise you might get the wrong answer).
 *
 * DIRECTIONS FOR THIRD PROBLEM:
 *
 * Compute the number of comparisons (as in Problem 1), using the "median-of-three" pivot rule.
 * [The primary motivation behind this rule is to do a little bit of extra work to get much
 * better performance on input arrays that are nearly sorted or reverse sorted.]
 * 
 * In more detail, you should choose the pivot as follows. Consider the first, middle, and 
 * final elements of the given array. (If the array has odd length it should be clear what the
 * "middle" element is; for an array with even length 2k2k, use the kth
 * element as the "middle" element. So for the array 4 5 6 7, the "middle" element is the
 * second one ---- 5 and not 6!) Identify which of these three elements is the median 
 * (i.e., the one whose value is in between the other two), and use this as your pivot. 
 * 
 * As discussed in the first and second parts of this programming assignment, be sure to 
 * implement Partition exactly as described in the video lectures (including exchanging the 
 * pivot element with the first element just before the main Partition subroutine).
 * 
 * 
 * EXAMPLE: For the input array 8 2 4 5 7 1 you would consider the first (8), middle (4), 
 * and last (1) elements; since 4 is the median of the set {1,4,8}, you would use 4 as your
 * pivot element.
 * 
 * 
 * SUBTLE POINT: A careful analysis would keep track of the comparisons made in identifying 
 * the median of the three candidate elements. You should NOT do this. That is, as in the
 * previous two problems, you should simply add m-1m−1 to your running total of comparisons 
 * every time you recurse on a sub-array with length m.
 * 
 * */

package MyTries;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class QuickSort_third {
	
	public static void main(String[] args) {
		try {
			 Scanner scan = new Scanner(new File("QuickSort.txt")); //provide file name from outside
		        int counter =0; //keep track of how many integers in the file
		        while(scan.hasNextInt()) 
		        {
		            counter++;
		            scan.nextInt();
		        }
		        scan.close();
		        Scanner scan2 = new Scanner(new File("QuickSort.txt")); 
		        int arr[] = new int[counter];
		        for(int i=0;i<counter;i++)
		        {
		            arr[i]=scan2.nextInt(); //fill the array with the integers
		        }
		        scan2.close();

		        int C = QuickSort(arr, arr.length);
				System.out.println("Number of comparisons is = " + C);
		        }
			catch(FileNotFoundException e){
				System.out.println("Error in reading file");
			}
	}
	
		static int QuickSort(int[] arr, int n) {
			int lo =0;
			int hi = arr.length-1;

			return Quick_Sort(arr, lo, hi);
		}
		
		static int Quick_Sort(int[] arr, int lo, int hi) {
			int compNum = 0;
			int p = 0;
			if(lo<hi) {
				compNum += hi-lo;
				p = partition(arr, lo, hi);
				compNum += Quick_Sort(arr, lo, p-1);
				compNum += Quick_Sort(arr, p+1, hi);
			}			
			return compNum;
		}
		
		
		
		static int partition(int[] arr, int lo, int hi) {
			int j,tmp;

			
		
			int mid, median;
			if(((hi-lo+1)%2)==1)
				 mid = lo+(hi-lo+1)/2;
			else
				 mid = lo+(hi-lo+1)/2-1;
			

			if((arr[hi]<arr[mid] && arr[lo]<arr[hi] && arr[lo]<arr[mid])||(arr[hi]<arr[lo] && arr[mid]<arr[hi] && arr[mid]<arr[lo]))
				median = hi;
			else if((arr[lo]<arr[mid] && arr[mid]<arr[hi] && arr[lo]<arr[hi] )||(arr[hi]<arr[mid] && arr[mid]<arr[lo] && arr[hi]<arr[lo]))
				median = mid;
			else
				median = lo;
			
			tmp = arr[lo];
			arr[lo] = arr[median];
			arr[median] = tmp;
			
		
			int p = arr[lo];
			int i = lo+1;
			
			for( j=lo+1; j<=hi; j++) {
				if( arr[j] < p ) {
					tmp = arr[i];
					arr[i] = arr[j];
					arr[j] = tmp;
					i = i+1;
				}
			}
			tmp = arr[lo];
			arr[lo] = arr[i-1];
			arr[i-1] = tmp;	
			
			return i-1;
		}
		
	}
