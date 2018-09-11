/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 3, August 2018.
 * 
 * @author Sima Jeddi
 *
 *
 * Input Format:
 * The text file contains a list of the integers from 1 to 10000 in unsorted 
 * order; you should treat this as a stream of numbers, arriving one by one.
 * Letting xi denote the ith number of the file, the kth median mk is defined 
 * as the median of the numbers x1,…,xk. (So, if k is odd, then mk is ((k+1)/2)th 
 * smallest number among x1,…,xk; if k is even, then mk is the (k/2)th smallest 
 * number among x1,…,xk.) 
 * 
 * Output Format:
 * Return the sum of these 10000 medians, modulo 10000 (i.e., only the last 4 
 * digits), that is (m1+m2+m3+⋯+m10000)mod10000.
 * 
 * Time Complexity: O(log(k)) for each median
 */

import java.util.ArrayList;
import java.io.*;


public class Median {
	
	public static void main(String[] args) throws IOException
	{
		ArrayList<Integer> arr = new ArrayList<Integer>();		
		read_element(arr);
		
		System.out.println(arr);
		//Calculates median and prints it for every input
		median(arr);
	}

	public static void median(ArrayList<Integer> arr) {
		ArrayList<Integer> Lower_Heap = new ArrayList<Integer>();
		ArrayList<Integer> Higher_Heap = new ArrayList<Integer>();
		int median;
		int sum = 0;
		//copy arraylist elements into array
		for(int i=0; i<arr.size(); i++) {
			int myelement = arr.get(i);
			System.out.println("element = "+ myelement);
			median = arr.get(0);
			if (i==0)
				median = arr.get(0);
			else if( i==1 ) {
				if(arr.get(0) >= arr.get(1)) {
					Insert_Low(Higher_Heap, arr.get(0));
					Insert_High(Lower_Heap, arr.get(1));
				}
				else {
					Insert_High(Lower_Heap, arr.get(0));
					Insert_Low(Higher_Heap, arr.get(1));
				}
			}
			else {
				if(myelement < getmax(Lower_Heap))
					Insert_High(Lower_Heap, myelement);
				else if(myelement > getmin(Higher_Heap))
					Insert_Low(Higher_Heap, myelement);
				else
					Insert_High(Lower_Heap, myelement);
			}
			
			if((Lower_Heap.size() - Higher_Heap.size()) >= 2) {
				int tmp = Extract_max(Lower_Heap);
				Insert_Low(Higher_Heap, tmp);
			}
			else if((Higher_Heap.size() - Lower_Heap.size()) >= 2) {
				int tmp2 = Extract_min(Higher_Heap); 
				Insert_High(Lower_Heap, tmp2);
			}
			
			if(i==0)
				median = arr.get(0);
			else {
				if(Lower_Heap.size() == Higher_Heap.size())
					median = getmax(Lower_Heap);
				else if(Lower_Heap.size() > Higher_Heap.size())
					median = getmax(Lower_Heap);
		    	else
		    		median = getmin(Higher_Heap);
			}
			//System.out.println("Lower Heap");
			//System.out.println(Lower_Heap);
			sum += median;
			//System.out.println("Higher Heap");
			//System.out.println(Higher_Heap);
			System.out.println("Median of "+ (i+1) +" elements is "+ median);
		}		
		System.out.println("Sum modulo 10000 = "+ sum%10000 );
	}
	
	public static void print(ArrayList<Integer> array) {
		System.out.println(array.size());

		for(int i=0; i<array.size(); i++) {
			System.out.println("Heap elements " + array.get(i)+"\n");
		}
	}

	public static void Insert_High(ArrayList<Integer> Lower_Heap, int myelement) {
		Lower_Heap.add(myelement);
		int k = (Lower_Heap.size()-1);
		while(k > 0) {
			int p = (k-1)/2;
			int parent = Lower_Heap.get(p);
			int child = Lower_Heap.get(k);
			if (parent < child) {
				//Swap Parent and child
				Lower_Heap.set(k, parent);
				Lower_Heap.set(p, child);
				//move up one level
				k = p;
			}
			else 
				break;
		}
	}
	
	public static int getmax(ArrayList<Integer> Lower_Heap) {
		if(Lower_Heap.size() < 1){
			return Integer.MAX_VALUE;
		}
		return Lower_Heap.get(0);	
	}
	
	public static int getmin(ArrayList<Integer> Higher_Heap) {
		if(Higher_Heap.size() < 1){
			return Integer.MAX_VALUE;
		}
		return Higher_Heap.get(0);
	}
	public static int Extract_max(ArrayList<Integer> Lower_Heap) {
		int ret = Lower_Heap.get(0);
		Lower_Heap.set(0, Lower_Heap.get(Lower_Heap.size()-1));
		Lower_Heap.remove(Lower_Heap.size()-1);
		int k = 0;
		int j = 2*k+1;
		int tmp;
		while(j < Lower_Heap.size()) {
			int max = j;
			int r= j+1;
			if(r< Lower_Heap.size()) {
				if(Lower_Heap.get(r) > Lower_Heap.get(j)) 
					max = r;
			}
			if(Lower_Heap.get(max) > Lower_Heap.get(k)) {
				//swap 
				tmp = Lower_Heap.get(max);
				Lower_Heap.set(max, Lower_Heap.get(k));
				Lower_Heap.set(k, tmp);
				k = max;	
				j = 2*k+1;
			}
			else
				break;
		}
		return ret;
	}

	public static int Extract_min(ArrayList<Integer> Higher_Heap) {
		int ret = Higher_Heap.get(0);
		Higher_Heap.set(0, Higher_Heap.get(Higher_Heap.size()-1));
		Higher_Heap.remove(Higher_Heap.size()-1);
		int k = 0;
		int j = 2*k+1;
		int tmp;
		while(j < Higher_Heap.size()) {
			int min = j;
			int r= j+1;
			if(r< Higher_Heap.size()) {
				if(Higher_Heap.get(r) < Higher_Heap.get(j)) 
					min = r;
			}
			if(Higher_Heap.get(min) < Higher_Heap.get(k)) {
				//swap 
				tmp = Higher_Heap.get(min);
				Higher_Heap.set(min, Higher_Heap.get(k));
				Higher_Heap.set(k, tmp);
				k = min;	
				j = 2*k+1;
			}
			else
				break;
		}
		return ret;
	}
	
	public static void Insert_Low(ArrayList<Integer> Higher_Heap, int myelement) {
		Higher_Heap.add(myelement);
		int k = (Higher_Heap.size()-1);
		while(k >= 1) {
			int p = (k-1)/2;
			int parent = Higher_Heap.get(p);
			int child = Higher_Heap.get(k);
			if (parent > child) {
				//Swap Parent and child
				Higher_Heap.set(k, parent);
				Higher_Heap.set(p, myelement);
				//move up one level
				k = p;
			}
			else 
				break;
		}
	}
	
	public static void read_element(ArrayList<Integer> arr) throws IOException 
	{
		File file = new File(System.getProperty("user.dir") + "/Median.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		try 
		{
			// Read a line of text from the file
		    String line = input.readLine();
		    while (line != null) 
		    {
		    	// Read integer values from each line
		    	int value = Integer.parseInt(line);
		    	arr.add(value);
		    	// Read a new line of text
		    	line = input.readLine();
		    } // while
		} // try
		finally
		{
			input.close();
		} // finally
	}
}
