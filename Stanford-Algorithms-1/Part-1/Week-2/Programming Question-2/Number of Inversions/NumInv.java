/**
 * Algorithms: Design and Analysis, Part 1
 * 
 * Programming Question - Week 2, July 2018.
 * 
 * @author Sima Jeddi
 *
 *
 * Download the following text file: "IntegerArray.txt"
 * This file contains all of the 100,000 integers between 1 and 100,000 (inclusive) in some 
 * order, with no integer repeated.
 * 
 * Your task is to compute the number of inversions in the file given, where the ith row of
 *  the file indicates the ith entry of an array.
 *
 * Because of the large size of this array, you should implement the fast divide-and-conquer 
 * algorithm covered in the video lectures.
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.math.BigInteger;

public class NumInv {
	
	static BigInteger Inv_Count(int[] arr, int n ) {
		
		int[] tmp = new int[n];
		BigInteger Inv_Count = sort_Count(arr, tmp, 0, n-1);
		return Inv_Count;
	}
	
	static BigInteger sort_Count(int[] arr, int[] tmp, int lo, int hi) {
		int mid = 0; 
		BigInteger Ninv = new BigInteger("0");

		if (lo<hi){
			mid = (hi+lo)/2;
			
			Ninv = sort_Count(arr, tmp, lo, mid);
			Ninv =  Ninv.add(sort_Count(arr, tmp, mid+1, hi));
			
			Ninv = Ninv.add(merge_Count(arr, tmp, lo, mid+1, hi));
		}
		return Ninv;
	}
	
	static BigInteger merge_Count(int[] arr, int[] tmp, int lo, int mid, int hi) {
		
		BigInteger Ninv = new BigInteger("0");
		
		int i,j,k,l;
		i = lo;
		j = mid;
		k = lo;
		
		while((i<= mid-1) && (j<=hi)) {
			if(arr[i] <= arr[j])
				tmp[k++] = arr[i++];
			else {
				tmp[k++] = arr[j++];
				Ninv = Ninv.add(BigInteger.valueOf(mid-i));
			}
		}
		while (i<=mid-1)
			tmp[k++] = arr[i++];
		while(j<=hi)
			tmp[k++] = arr[j++];
			
		for( l=lo; l<=hi ;l++)
			arr[l]=tmp[l]; 
		return Ninv;
	}
	public static void main(String[] args) {
		  
			try {
			 Scanner scan = new Scanner(new File("IntegerArray.txt")); //provide file name from outside
		        int counter =0; //keep track of how many integers in the file
		        while(scan.hasNextInt()) 
		        {
		            counter++;
		            scan.nextInt();
		        }
		        scan.close();
		        Scanner scan2 = new Scanner(new File("IntegerArray.txt")); 
		        int arr[] = new int[counter];
		        for(int i=0;i<counter;i++)
		        {
		            arr[i]=scan2.nextInt(); //fill the array with the integers
		        }
		        scan2.close();
				//System.out.println("Number of integers are = " + counter);

		        BigInteger C = Inv_Count(arr, arr.length);
				System.out.println("Number of inversion are = " + C);
		        }
			catch(FileNotFoundException e){
				//e.printStackTrace();
				System.out.println("Error in reading file");
			}
			
			//System.out.println("Number of inversion are = " + C);
		}

}
