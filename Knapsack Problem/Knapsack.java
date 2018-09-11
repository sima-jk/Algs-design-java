/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 2, September 2018.
 * 
 * @author Sima Jeddi
 *
 * In this programming problem and the next you'll code up the knapsack algorithm from lecture.
 * Let's start with a warm-up. Download the text file "knapsack1.txt".
 * 
 * This file describes a knapsack instance, and it has the following format:
 * [knapsack_size][number_of_items]
 * [value_1] [weight_1]
 * [value_2] [weight_2]
 * ...
 * 
 * For example, the third line of the file is "50074 659", indicating that the second item has
 * value 50074 and size 659, respectively.
 * 
 * You can assume that all numbers are positive. You should assume that item weights and the 
 * knapsack capacity are integers.
 * 
 * Output the value of the optimal solution.
 * 
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class Knapsack {

	public static int knapsack_size;
	public static int number_of_items;
	
	public static void main(String[] args) throws IOException{
		
		HashMap<Integer, Integer> Weights = new HashMap<Integer, Integer>();
		HashMap<Integer, Integer> Values = new HashMap<Integer, Integer>();
		
		read_data(Weights, Values);
		
		knapsack(Weights, Values);
	}
	
	
	public static void knapsack(HashMap<Integer, Integer> Weights, HashMap<Integer, Integer> Values) {
		int A[][] = new int[number_of_items+1][knapsack_size+1];
		
		for(int x=0; x<=knapsack_size; x++)
			A[0][x] = 0;
		
		for(int i=1;i<=number_of_items;i++) {
			for(int x=0; x<=knapsack_size; x++) {
				if(x >= Weights.get(i)) {
					A[i][x] = Math.max(A[i-1][x], A[i-1][x-Weights.get(i)]+Values.get(i));
				}
				else
					A[i][x] = A[i-1][x];
			}
		}
		System.out.println("A[n,w] = "+ A[number_of_items][knapsack_size]);
	}
	
	public static void read_data(HashMap<Integer, Integer> Weights, HashMap<Integer,Integer> Values) throws IOException  {
		
		File file = new File(System.getProperty("user.dir") + "/knapsack1.txt");
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
		System.out.println("Number of Weights elements = "+ Weights.size()+"Number of values = " + Values.size());
	
	}
}
