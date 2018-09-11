/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 2, August 2018.
 * 
 * @author SimaJeddi
 * 
 *
 * Programming Question - Week 2, August 2018.
 * 
 * In this programming problem and the next you'll code up the clustering algorithm 
 * from lecture for computing a max-spacing k-clustering. Download the text file here.
 * This file describes a distance function (equivalently, a complete graph with edge
 * costs). It has the following format:
 *
 * [number_of_nodes]
 * [edge 1 node 1] [edge 1 node 2] [edge 1 cost]
 * [edge 2 node 1] [edge 2 node 2] [edge 2 cost]
 * ...
 * 
 * There is one edge (i,j) for each choice of 1≤i<j≤n, where n is the number of nodes.
 * For example, the third line of the file is "1 3 5250", indicating that the distance
 * between nodes 1 and 3 (equivalently, the cost of the edge (1,3)) is 5250. You can 
 * assume that distances are positive, but you should NOT assume that they are distinct.
 * 
 * Your task in this problem is to run the clustering algorithm from lecture on this 
 * data set, where the target number k of clusters is set to 4. What is the maximum 
 * spacing of a 4-clustering?
 * 
 * ADVICE: If you're not getting the correct answer, try debugging your algorithm using
 * some small test cases. And then post them to the discussion forum!

*/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SingleLinkage {

	private static int numOfVertices = 0;
	private static int k = 4;
	
	public static void main(String args[]) throws IOException
	{
		List<Edge> edges = read_data();
		
		Collections.sort(edges);
		
		UnionFind UF  = new UnionFind(numOfVertices);
		for(Edge e : edges) {
			UF.union(e.getI(), e.getJ());
			if(UF.Count() == k) {
				break;
			}
		}
		 int max = Integer.MAX_VALUE;
			
	     for(Edge e : edges)
	        {
	            if(UF.find(e.getI()) != UF.find(e.getJ())){
	                max = Math.min(max, e.getCost());				
	        }
		}
	     System.out.println("Maximum spacing of a 4-clustering ==> "+max);
	     System.out.println("Integer Max value = "+Integer.MAX_VALUE);
	     System.out.println("numOfVertices = "+numOfVertices);
	}
	
	public static ArrayList<Edge> read_data() throws IOException{
		ArrayList<Edge> edges ;
		
		File file = new File(System.getProperty("user.dir") + "/clustering1.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));

		try 
		{
		// Read a line of text from the file
			String line = input.readLine();
			 numOfVertices = Integer.parseInt(line);
			edges = new ArrayList<Edge>(numOfVertices);
			
			while (line != null) 
			{
				// Split the line of text into a vector of Strings
				// The \\s+ is the space between ints
				String[] vector = line.split("\\s+");
			
				if(vector.length == 3) {
				// Keep track of the key values		
				int Source = Integer.parseInt(vector[0]);
	    		int Destination = Integer.parseInt(vector[1]); // 004
	    		int cost = Integer.parseInt(vector[2]);
	    		
		    	edges.add(new Edge(Source -1 , Destination - 1, cost));
		    	System.out.println("source = "+ Source+"Destination = " + Destination+ "Cost = "+ cost);
				}
				
		    	// Convert the vector of values for the edges to integers
		    	// Read a new line of text*/
		    	line = input.readLine();
			} // while
		System.out.println("Number of edges => "+edges.size());
		} // try
		finally
		{
			input.close();
		} // finally	
	return edges;
	}
}

class Edge implements Comparable<Edge>{
	int i,j,v;
	public Edge(int i, int j, int v) {
		this.i = i;
		this.j = j;
		this.v = v;
	}
	
	public int getI(){
		return i;
	}
	
	public int getJ() {
		return j;	
	}
	
	public int getCost() {
		return v;
	}
	
	@Override
	public int compareTo(Edge edge) {
		int result;
		if(this.getCost() >= edge.getCost())
			result = 1;
		else
			result = -1;
		return result;
	}
	
}

class UnionFind{
	int num; // number of components
	int[] leader ;

	public UnionFind(int NumOfVertices) {
		num = NumOfVertices;
		leader = new int[NumOfVertices];
		for(int i=0; i<NumOfVertices; i++) {
			leader[i] = i;	
		}	
	} 
	public int find(int i) {
		int orginali = i;
		int root = leader[i];
		while(i!=leader[i]) {
			root = leader[i];
			i = leader[i];
		}
		
		i = orginali;
		while(i!=root) {
			int newi = leader[i];
			leader[i] = root;
			i = newi;
		}
		return root;
		
	}
	public int Count() {
		return num;
		
	}
	
	public void union(int i, int j) {
		int p = find(i);
		int q = find(j);
		if(p==q)
			return;
		else {
			leader[p] = q;
		}
		num--;	
	}
}
