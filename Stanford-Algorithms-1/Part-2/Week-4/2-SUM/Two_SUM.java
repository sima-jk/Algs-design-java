/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 4, August 2018.
 * 
 * @author SimaJeddi
 *
 * Goal:
 *  The goal of this problem is to implement a variant of the 2-SUM algorithm.
 *
 *
 * Input Format:
 *  The file contains 1 million integers, both positive and negative (there might be some repetitions!).
 *  This is your array of integers, with the i-th row of the file specifying the i-th entry of the array.
 *
 * Output:
 *  Your task is to compute the number of target values t in the interval [-10000,10000] (inclusive) such
 *  that there are distinct numbers x,y in the input file that satisfy x+y=t.
 *  (NOTE: ensuring distinctness requires a one-line addition to the algorithm from lecture.)
 *  
 * 
*/

import java.io.*;
import java.util.Hashtable;
import java.util.Set;


public class Two_SUM {

	
	public static void main(String[] args) throws IOException{
		
		Hashtable< Long  , Integer> arr = new Hashtable< Long , Integer>();
 
		read_data(arr);
	
		TwoSUM( arr);
	}
	public static void TwoSUM(Hashtable<Long, Integer> arr){
		int TWO_SUM = 0;
		int min = -10000;
		int max = 10000;
		int t = min;
		Long X; 
		int value;
		Set<Long> keys = arr.keySet();

		while(t <= max) {
			boolean flag = false;
		    for(Long key: keys){
				X = key;
				value = arr.get(key);
				
				if(arr.containsKey(t-X)) {
					if((arr.get(t-X) > value) && !flag) {
						flag = true;
						TWO_SUM++;
						System.out.println("Two SUM for t = "+ t +" equals to "+ TWO_SUM + "\n");
					}
				}
			}
			t++;
		}
		System.out.println("Final = "+ TWO_SUM);	
	}
	
	public static void read_data(Hashtable< Long, Integer> arr) throws IOException {
		int count =1;
		File file = new File(System.getProperty("user.dir") + "/algo1-programming_prob-2sum.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		try 
		{
			// Read a line of text from the file
		    String line = input.readLine();
		    while (line != null) 
		    {
		    	// Read integer values from each line
		    	Long value = Long.parseLong(line);
		    	arr.put(value,count);
		    	count++;
		    	// Read a new line of text
		    	line = input.readLine();
		    } // while
		} // try
		finally
		{
			input.close();
		} // finally
		System.out.println("Num of lines = "+ count);
	}
}
