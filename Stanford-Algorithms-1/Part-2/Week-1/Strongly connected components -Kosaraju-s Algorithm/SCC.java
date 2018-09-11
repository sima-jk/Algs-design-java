/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 1, August 2018.
 * 
 * @author SimaJeddi
 * 
 * Input Format:
 *  The file contains the edges of a directed graph. Vertices are labeled as positive integers from
 *  1 to 875714. Every row indicates an edge, the vertex label in first column is the tail and the 
 *  vertex label in second column is the head (recall the graph is directed, and the edges are directed 
 *  from the first column vertex to the second column vertex). So for example, the 11th
 *  row looks likes : "2 47646". This just means that the vertex with label 2 has an outgoing edge to
 *   the vertex with label 47646

* Goal:
*  Your task is to code up the algorithm from the video lectures for computing strongly connected
*  components (SCCs), and to run this algorithm on the given graph.

* Output Format: 
*  You should output the sizes of the 5 largest SCCs in the given graph, in decreasing order of sizes,
*  separated by commas . So if your algorithm computes the sizes of the five largest 
*  SCCs to be 500, 400, 300, 200 and 100, then your answer should be "500,400,300,200,100". 
*  If your algorithm finds less than 5 SCCs, then write 0 for the remaining terms. 
*  Thus, if your algorithm computes only 3 SCCs whose sizes are 400, 300, and 100, then your answer
*   should be "400,300,100,0,0" . 

* WARNING:
*  This is the most challenging programming assignment of the course. 
*  Because of the size of the graph you may have to manage memory carefully.
 * 
 * 
 */


package SCCs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

//Java program to demonstrate working of HashSet
import java.util.*;

public class SCC {



public static void DFS() {}
//Global variable for DFS-Loop
public static int t =0;
public static int s =0;

//Number of nodes
public static int N = 875714;

public static boolean[] flags = new boolean[N+1];

//public static ArrayList<Integer> finish_time = new ArrayList<Integer>(Collections.nCopies(N+1, null));

public static int[] finish_time =  new int[N+1];
//public static ArrayList<Integer> leader = new ArrayList<Integer>(Collections.nCopies(N+1, null));

public static int[] leader =  new int[N+1];

public static void DFS_Rev( HashMap< Integer, ArrayList<Integer>> adjList_head,int n)
{
	flags[n] = true;
	
	ArrayList<Integer> edges = adjList_head.get(n);	
	//for(int i=0; i<= edges.size()-1; i++)	
	//	System.out.println("edges "+ i + " = "+ edges.get(i));

	for(int j=0;j<edges.size(); j++) {
		if(!flags[edges.get(j)])
			DFS_Rev( adjList_head, edges.get(j));
	}
	t++;
	//System.out.println("t = "+ t);

	finish_time[n]=t;////////////////
	//System.out.println("element = "+t +"is added at index = "+n +"\\ "+finish_time[n]);
}

public static void DFS(HashMap< Integer, ArrayList<Integer>> adjList_tail, int vertex, int s)
{
	flags[vertex] = true;
	leader[vertex]= s;
	ArrayList<Integer> edges = adjList_tail.get(vertex);		
	for(int j=0;j<edges.size(); j++)
		if(!flags[edges.get(j)])
			DFS(adjList_tail, edges.get(j), s);
	
	
}
public static void DFS_LOOP(HashMap< Integer, ArrayList<Integer>> adjList_tail, HashMap< Integer, ArrayList<Integer>> adjList_head)
{
	for (int i=adjList_tail.size(); i>=1; i--)
		flags[i] = false;
	for (int n=adjList_tail.size(); n>=1; n--)
		if(!flags[n])
			DFS_Rev( adjList_head, n);
	
	List<Integer> Order = new ArrayList<Integer>();
	for (int i : finish_time)
	{
		Order.add(i);
	}
	
	//Print out order calculated in DFS_Rev
	for (int i=adjList_tail.size(); i>=1; i--) {
		flags[i] = false;
		System.out.println("finish time = "+ Order.get(i));
	}
	
	//Calls DFS in calculated order
	int count = N;
	while(count > 0 ) {
		int vertex = Order.indexOf(count);
		//System.out.println("vertex = "+ vertex+ "count = "+count );
		if(!flags[vertex]) {
			s = vertex;
			DFS(adjList_tail, vertex, s);
		}
		count--;
		//System.out.println("Count = "+ count);
	}			
}

public static void construct(HashMap< Integer, ArrayList<Integer>> adjList_tail,
		HashMap< Integer, ArrayList<Integer>> adjList_head) throws IOException
{
	File file = new File(System.getProperty("user.dir") + "/SCC.txt");
	BufferedReader input = new BufferedReader(new FileReader(file));
	

    for(int v=1; v<=N; v++){
    	adjList_tail.put(v, new ArrayList<Integer>());
    	adjList_head.put(v, new ArrayList<Integer>());
    }
	
    try 
	{
	// Read a line of text from the file
		String line = input.readLine();
		while (line != null) 
		{
			// Split the line of text into a vector of Strings
			// The \\s+ is the space between ints
			String[] vector = line.split("\\s+");
			// Keep track of the key values
			int value = Integer.parseInt(vector[0]);
			int value2 = Integer.parseInt(vector[1]);
			
	         
	        // insert (vertex, list) pairs into dictionary
	        // insert neighbors into list for vertex 
			
			adjList_tail.get(value).add(value2);
			adjList_head.get(value2).add(value);
			
	
			// Convert the vector of values for the edges to integers
					
			// Read a new line of text
			line = input.readLine();
		} // while
	} // try
	finally
	{
		input.close();
	} // finally
}//construct end

public static void main(String[] arg) throws IOException
{
	//Map that contains vertices as tails (straight DFS call) 
	HashMap<Integer, ArrayList<Integer>> adjList_tail = new HashMap<Integer, ArrayList<Integer>>();
	//Map that contains vertices as heads (Reverse DFS call)
	HashMap<Integer, ArrayList<Integer>> adjList_head = new HashMap<Integer, ArrayList<Integer>>();

	
	// Construct the initial graph
	construct(adjList_tail,adjList_head);
	
	//Kosaraju's Two-pass Algorithm	
	DFS_LOOP(adjList_tail,adjList_head);
	
	//Print leaders of each SCC to check
	for(int i=1;i<leader.length; i++)
		System.out.println("leader "+ i+ " = "+ leader[i]);
	//SCC count

	//Set is used to find the num of duplicate elements in array
	Set<Integer> singlesSet  = new HashSet<>();
	for(int l =1; l<=leader.length-1; l++) 	
		singlesSet.add(leader[l]);
		
		
	int[] countSCC = new int[singlesSet.size()];
	int[] SCC_Components = new int[singlesSet.size()]; 
	
	//Convert set to array
	SCC_Components = singlesSet.stream().mapToInt(Integer::intValue).toArray();
	
	
	for(int l =1; l<=leader.length-1; l++) {
		for(int index = 0; index<singlesSet.size();index++ )
			if(SCC_Components[index]==leader[l])
				countSCC[index]++;
	}	

	
	for(int j=0;j<SCC_Components.length; j++)
		System.out.println("SCC component leaders = "+SCC_Components[j]);
	
	for(int j=0;j<countSCC.length; j++)
		System.out.println("SCC count "+j+" = "+countSCC[j]);
	
	System.out.println("countSCC.length = "+ countSCC.length);
	
	//Sort SCCs based on number of elements
	Arrays.sort(countSCC);
	 
    System.out.printf("5 largest elements of array : "+
                      (countSCC[countSCC.length-1])+ "\t"+(countSCC[countSCC.length-2])+"\t"+
                      (countSCC[countSCC.length-3])+ "\t"+ (countSCC[countSCC.length-4])+ "\t"+
                      (countSCC[countSCC.length-5]));
}//main end 

}//SCC class end
