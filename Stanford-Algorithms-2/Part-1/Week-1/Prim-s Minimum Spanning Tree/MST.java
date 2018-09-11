/**
 *   Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 4, September 2018.
 * 
 * @author SimaJeddi
 * 
 * In this programming problem you'll code up Prim's minimum spanning tree algorithm.
 *
 * Download the text file "edges.txt"
 * 
 * 
 * Input Format:
 *  The file contains an adjacency list representation of an undirected weighted graph with 200 vertices
 *  labeled 1 to 200. Each row consists of the node tuples that are adjacent to that particular vertex
 *  along with the length of that edge.
 *  
* Goal:
*  Your task is to run Dijkstra's shortest-path algorithm on this graph, using 1 (the first vertex) as
*  the source vertex, and to compute the shortest-path distances between 1 and every other vertex of the
*  graph. If there is no path between a vertex v and vertex 1, we'll define the shortest-path distance 
*  between 1 and v to be 1000000.
 
*
* Output Format:
*  report the shortest-path distances to the following ten vertices, 
*  in order: 7,37,59,82,99,115,133,165,188,197.
*  You should encode the distances as a comma-separated string of integers. So if you find that all ten
*  of these vertices except 115 are at distance 1000 away from vertex 1 and 115 is 2000 distance away, 
*  then your answer should be 1000,1000,1000,1000,1000,2000,1000,1000,1000,1000. 
*/

package greedy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.*;


public class MST {

public static void dijkstra(Graph G, int s) {
	int ini_Weight = 1000000;
	//vertices processed so far
	ArrayList<Integer> X = new ArrayList<Integer>();
	X.add(s);
	
	ArrayList<Edge> T = new ArrayList<Edge>();
	//computed shortest path distances to each node
	int[] A = new int[G.getNodes().size()+1];
	Arrays.fill(A, ini_Weight);
	A[1] = 0;
	boolean[] visited = new boolean[G.getNodes().size()+1];
	Arrays.fill(visited, false);
	visited[1] = true;
	visited[0] = true;
	
	int min = ini_Weight;
	int selected_v=0, w=0;
	int cost;
	int min_index=0;
	System.out.println("X size before for "+ X.size());
	while(X.size() < G.getNodes().size()) {
		min = ini_Weight;
		for(int kk=0; kk<X.size(); kk++) {
			int v = X.get(kk);
			//System.out.println("V = "+ v);
			List<Edge> Vadjs= G.adj(v);
			//System.out.println("Number of adjacents for v = "+v +" is = "+Vadjs.size());
			for(int j=0; j<Vadjs.size(); j++) {
				//System.out.println("V = "+v+" Num of V adjacencies " + Vadjs.size());
				if(!visited[Vadjs.get(j).destination] || !visited[Vadjs.get(j).source])
			{
					if(Vadjs.get(j).source==v)
						w = Vadjs.get(j).destination;
					else if(Vadjs.get(j).destination==v)
						w = Vadjs.get(j).source;
				cost =  Vadjs.get(j).weight;
				//System.out.println("W = "+ w+ "Cost is = "+ cost+" j = "+j);
				if(cost < min) {
					selected_v =v;
					min = cost;
					min_index = w;
				}
			}
				
		  }
			//for(int k=0; k<X.size(); k++)
				//System.out.println("X "+k+" = " +X.get(k));
		}//chooses next vertex to go
		if(!visited[min_index])
		{	
			X.add(min_index);
			A[min_index] = min;
			visited[min_index] = true;
			T.add(new Edge(selected_v,min_index,min));

		System.out.println("min index = " +min_index +" Cost = "+min+ " Size of X = "+ X.size());
		}
	}
	System.out.println("X size is = "+ X.size());
	
	long sum =0;
	for(int i=0; i<T.size(); i++) {
		System.out.println("T edge weight of " + i+ " equals to = "+ T.get(i).weight);
		sum += T.get(i).weight;
	}
	System.out.println("Prim's Minimum spanning Tree cost => "+ sum);
	
}


public static void read_data(Graph G) throws IOException{
	
	File file = new File(System.getProperty("user.dir") + "/edges.txt");
	BufferedReader input = new BufferedReader(new FileReader(file));

	try 
	{
	// Read a line of text from the file
		String line = input.readLine();
		while (line != null) 
		{
			// Split the line of text into a vector of Strings
			// The \\s+ is the space between ints
			String[] vector = line.split("\\s+");
			if(vector.length == 3) {
			// Keep track of the key values		
			int Source = Integer.parseInt(vector[0]);
    		int Destination = Integer.parseInt(vector[1]); // 004

			if(!G.contains(Source)) 
				G.addNode(Source);
			if(!G.contains(Destination))
				G.addNode(Destination);
			
	    	int Weight = Integer.parseInt(vector[2]); // 034556
	    	G.addEdge(Source, Destination, Weight);
			}
    			    
	    	// Convert the vector of values for the edges to integers
	    	// Read a new line of text*/
	    	line = input.readLine();
		} // while
	System.out.println("Number of Nodes => "+G.getNodes().size()+ "Number of edges => "+ G.getedges().size());
	} // try
	finally
	{
		input.close();
	} // finally	
}

public static void print_str(int[] str) {
	for(int i=0; i<=str.length-1; i++)
		System.out.println( "String "+ i +" = "+ str[i] +"\t");
}

public static void main(String[] arg) throws IOException
{		
	Graph G = new Graph();
	// Construct the initial graph
	read_data(G);
	
	//Run dijkstra by source s 
	int s = 1;
	dijkstra(G, s);
}

}


 class Edge {
	final int source;
    final int weight;
    final int destination;
    public Edge(int source, int destination,int weight){
    	this.source = source;
    	this.destination = destination;
    	this.weight = weight;
    }						
}


 
 class Graph {
    
	private List<Integer> nodes = new ArrayList<>();
    public void addNode(int id) {
    	nodes.add(id);	   	
    }
    
    public boolean contains(int id) {
    	
    	if(nodes.contains(id))
    		return true;
    	else 
    		return false;
    }
    
    public List<Integer> getNodes(){
    	return nodes;
    }
    
    public int getNodeById(int id) {
        return nodes.stream()
            .findFirst().orElse(null);
    }
   
    private List<Edge> edges = new ArrayList<>();
    public void addEdge(int source,int destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }
    
    public List<Edge> getedges(){
    	return edges;
    	
    }
    public List<Edge> adj(int v){
    	
    	 List<Edge> adjList = new ArrayList<Edge>();
    	for(int i=0; i<edges.size(); i++)
    		if((edges.get(i).source==v)|| (edges.get(i).destination==v))
    			adjList.add(edges.get(i));
    		return adjList;
    }
    
}
