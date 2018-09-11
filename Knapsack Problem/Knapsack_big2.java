/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 2, September 2018.
 * 
 * @author Sima Jeddi
 *
 * This problem also asks you to solve a knapsack instance, but a much bigger one.
 * 
 * Download the text file "knapsack_big.txt"
 * 
 * This file describes a knapsack instance, and it has the following format:
 * [knapsack_size][number_of_items]
 * [value_1] [weight_1]
 * [value_2] [weight_2]
 * ...
 * 
 * For example, the third line of the file is "50074 834558", indicating that the second item 
 * has value 50074 and size 834558, respectively. As before, you should assume that item weights
 * and the knapsack capacity are integers.
 * 
 * This instance is so big that the straightforward iterative implementation uses an infeasible 
 * amount of time and space. So you will have to be creative to compute an optimal solution. 
 * 
 * One idea is to go back to a recursive implementation, solving subproblems --- and, of course, 
 * caching the results to avoid redundant work --- only on an "as needed" basis. 
 * Also, be sure to think about appropriate data structures for storing and looking up solutions
 * to subproblems.
 * 
 * Output the value of the optimal solution.
 * 
 * 
 * Result: 
 * The proposed algorithm solves problem in ~70 seconds which is a lot of improvement compared 
 * to dynamic programming version of knapsack algorithm which requires a huge amount of cache 
 * for this data-set.
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Knapsack_big2 {
	public static int knapsack_size;
	public static int number_of_items;
	
	public static void main(String[] args) throws IOException{
		long startTime = System.nanoTime();
		
		HashMap<Integer, Integer> Weights = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> Values = new HashMap<Integer, Integer>();
		
		read_data(Weights, Values);

		int[] a = new int[knapsack_size+1];
		knapsack(Weights, Values,a ,0);
		
		long endTime = System.nanoTime();
		long duration = (endTime - startTime);
		System.out.println("Execution time = "+ duration/1000000000+" Seconds");
		
	}

	public static void knapsack(HashMap<Integer, Integer> Weights, HashMap<Integer, Integer> Values, int[] arr, int i) {
		int x= 0;
		int[] tmp = new int[knapsack_size+1];

		if( i <= number_of_items) {

			while( x<=knapsack_size && i!=0) {
				if (x >= Weights.get(i))
					tmp[x] = Math.max(arr[x],arr[x-Weights.get(i)]+Values.get(i));
				else
					tmp[x] = arr[x];
				x++;
			}
			
			if ( x == knapsack_size+1 )
				arr = tmp;
			
		knapsack(Weights, Values, arr, i+1);
		}
		else {
			
			System.out.println("Value of optimal solution = "+ arr[knapsack_size]);
			return;
		}
	}
	
public static void read_data(HashMap<Integer, Integer> Weights, HashMap<Integer,Integer> Values) throws IOException  {
		
		File file = new File(System.getProperty("user.dir") + "/knapsack_big.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		int count = 1;
		try 
		{
			// Read a line of text from the file
		    String line = input.readLine();
			String[] vector = line.split("\\s+");

		    knapsack_size = Integer.parseInt(vector[0]);
		    number_of_items = Integer.parseInt(vector[1]);
		    
		    line = input.readLine();

		    while (line != null) 
		    {
		    	
				vector = line.split("\\s+");

		    	// Read integer values from each line
		    	int value = Integer.parseInt(vector[0]);
				int weight = Integer.parseInt(vector[1]);
				    
				Weights.put(count, weight);
				Values.put(count, value);
				 
		    	count++;
		    	
		    	// Read a new line of text
		    	line = input.readLine();
		    } // while
		} // try
		finally
		{
			input.close();
		} // finally
		System.out.println("Number of Weights elements = "+ Weights.size()+ "\n" + "Number of values = " + Values.size());
	}
}
