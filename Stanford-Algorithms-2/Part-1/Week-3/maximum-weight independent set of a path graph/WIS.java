/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 3, August 2018.
 * 
 * @author SimaJeddi
 *
 * In this programming problem you'll code up the dynamic programming algorithm for computing
 * a maximum-weight independent set of a path graph.
 *
 * Download the text file  "mwis.txt"
 * 
 * This file describes the weights of the vertices in a path graph (with the weights listed in
 * the order in which vertices appear in the path). It has the following format:
 * 
 * [number_of_vertices]
 * [weight of first vertex]
 * [weight of second vertex]
 * ...
 * 
 * For example, the third line of the file is "6395702," indicating that the weight of the 
 * second vertex of the graph is 6395702.
 *
 * Your task in this problem is to run the dynamic programming algorithm (and the
 * reconstruction procedure) from lecture on this data set. The question is: of the vertices
 * 1, 2, 3, 4, 17, 117, 517, and 997, which ones belong to the maximum-weight independent set?
 * (By "vertex 1" we mean the first vertex of the graph---there is no vertex 0.) 
 * In the box below, enter a 8-bit string, where the ith bit should be 1 if the ith of these 
 * 8 vertices is in the maximum-weight independent set, and 0 otherwise.
 * 
 * For example, if you think that the vertices 1, 4, 17, and 517 are in the maximum-weight 
 * independent set and the other four vertices are not, then you should enter the string 
 * 10011010 in the box below.
 *
 */


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class WIS {
	public static int Num_vertices;
	
	public static void main(String[] args) throws IOException{
		
		HashMap<Integer, Integer> Weights = new HashMap<Integer, Integer>();

		//read input file 
		Weights = read_element(Weights);
		
		 HashMap<Integer, Integer> A = new HashMap<Integer, Integer>();
		 HashMap<Integer, Integer> VerticesOfIS = new HashMap<Integer, Integer>();
		 VerticesOfIS = MWIS(A, Weights);
		 
		 //Output wanted results
		 int[] arr = {1, 2, 3, 4, 17, 117, 517, 997};
		 boolean[] s = new boolean[8];

			for(int i=0;i<8;i++) 
				s[i] =  VerticesOfIS.containsKey(arr[i]);
			
			for(int i=0; i<s.length; i++)
				System.out.println("IS Contains vertex number "+arr[i]+"  => "+ s[i]);

}

	public static HashMap<Integer, Integer>  MWIS(HashMap<Integer, Integer> A, HashMap<Integer, Integer> Weights){
		A.put(-1, 0);
		A.put(0, 0);
		A.put(1, Weights.get(1));
		
		for(int i=2; i<Num_vertices; i++) {
			int element = Math.max(A.get(i-1), A.get(i-2)+Weights.get(i));
			A.put(i, element);
		}
		
		int j = Num_vertices;
		HashMap<Integer, Integer> S = new HashMap<Integer, Integer>();
		
		while(j >= 1) {
			if(A.get(j-1) >= A.get(j-2)+Weights.get(j))
				j--;
			else {
				S.put(j,0);
				j = j-2;
			}
		}

		return S;
		}
	
	
	public static  HashMap<Integer, Integer> read_element( HashMap<Integer, Integer> Weights) throws IOException 
	{

		File file = new File(System.getProperty("user.dir") + "/mwis.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		int count = 1;
		try 
		{
			// Read a line of text from the file
			String line = input.readLine();
			Num_vertices = Integer.parseInt(line);
	    	line = input.readLine();

	    	while (line != null) 
	    	{
	    		int Weight = Integer.parseInt(line);
	    		Weights.put(count, Weight);
	    		count++;
	    	
	    		// Read a new line of text
	    		line = input.readLine();
	    	} // while
		} // try
		finally
		{
		input.close();
		} // finally
		System.out.println("Number of read elements = "+ Weights.size());
		return Weights;
	}
}
