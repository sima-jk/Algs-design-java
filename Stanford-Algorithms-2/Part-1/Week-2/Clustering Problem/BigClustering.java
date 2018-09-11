/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 2, August 2018.
 * 
 * @author SimaJeddi
 *
 * In this question your task is again to run the clustering algorithm from lecture,
 * but on a MUCH bigger graph. So big, in fact, that the distances (i.e., edge costs)
 * are only defined implicitly, rather than being provided as an explicit list.
 * 
 * The data set is  clustering_big.txt. 
 * 
 * The format is:
 * 
 * [# of nodes] [# of bits for each node's label]
 * [first bit of node 1] ... [last bit of node 1]
 * [first bit of node 2] ... [last bit of node 2]
 * ...
 * 
 * For example, the third line of the file "0 1 1 0 0 1 1 0 0 1 0 1 1 1 1 1 1 0 1 0 1 1 0 1"
 * denotes the 24 bits associated with node #2.
 * 
 * The distance between two nodes u and v in this problem is defined as the Hamming 
 * distance--- the number of differing bits --- between the two nodes' labels. For example,
 * the Hamming distance between the 24-bit label of node #2 above and the label 
 * "0 1 0 0 0 1 0 0 0 1 0 1 1 1 1 1 1 0 1 0 0 1 0 1" is 3 (since they differ in the 3rd, 7th,
 * and 21st bits).
 * 
 * The question is: what is the largest value of k such that there is a k-clustering with 
 * spacing at least 3? That is, how many clusters are needed to ensure that no pair of nodes 
 * with all but 2 bits in common get split into different clusters?
 * 
 * NOTE: The graph implicitly defined by the data file is so big that you probably can't 
 * write it out explicitly, let alone sort the edges by cost. So you will have to be a little
 * creative to complete this part of the question. For example, is there some way you can 
 * identify the smallest distances without explicitly looking at every pair of nodes?
 */

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BigClustering {

	public static int numOfVertices =0;
	public static int HammingL =0;
	public static void main(String[] args) throws IOException{
		HashMap<Integer,Integer> HM = new HashMap<Integer, Integer>();
		read_data(HM);
		
		UnionFind UF  = new UnionFind(numOfVertices);
		
		List<Integer> list1D = new ArrayList<Integer>();
		List<Integer> list2D = new ArrayList<Integer>();
		List<Integer> FriendsList = new ArrayList<Integer>();

		while(!HM.isEmpty()) {
			list2D.clear();
			list1D.clear();
			FriendsList.clear();
			
			int key = HM.keySet().iterator().next();
			int value = HM.remove(key);
		
			list1D = OneD(key);
			list2D = TwoD(key);
			for(int index=0; index<list1D.size(); index++) {
				int searchValue = list1D.get(index);
						
				if(HM.containsKey(searchValue)) {
					Integer Added = HM.remove(searchValue);
					FriendsList.add(searchValue);
					UF.union( Added,value);
				}
			}
			list1D.clear();
		
			for(int index=0; index<list2D.size(); index++) {
				int searchValue = list2D.get(index);
					
				if(HM.containsKey(searchValue)) {
					Integer Added = HM.remove(searchValue);
					FriendsList.add(searchValue);
					UF.union( Added,value);
				}
			}
			list2D.clear();
			List<Integer> Neighbors = new ArrayList<Integer>();

			while(!FriendsList.isEmpty()) {
				Neighbors.clear();
				for(int i=0; i<FriendsList.size(); i++) {
					list1D = OneD(FriendsList.get(i));
					list2D = TwoD(FriendsList.get(i));

					for(int index=0; index<list1D.size(); index++) {
						int searchValue = list1D.get(index);
						if(HM.containsKey(searchValue)) {
							Integer Added = HM.remove(searchValue);
							Neighbors.add(searchValue);
							UF.union( Added, value);
						}
					}
					list1D.clear();
				
					for(int index=0; index<list2D.size(); index++) {
						int searchValue = list2D.get(index);
						if(HM.containsKey(searchValue)) {
							Integer Added = HM.remove(searchValue);
							Neighbors.add(searchValue);
							UF.union( Added, value);
						}
					}
					list2D.clear();
				}
				FriendsList.clear();
				FriendsList.addAll(Neighbors);  
			}
		}
		System.out.println("Number of Clusters ==> "+ UF.Count());
	}
	
	//this function computes arrays with one Hamming diff of arr
	public static List<Integer> OneD(int arr) {
		List<Integer> list1D = new ArrayList<Integer>();
		for(int i=0; i<HammingL; i++) {
			int newValue = arr ^ (1<<i);
			list1D.add(newValue);
		}
		return list1D;
	}
	
	//this function computes arrays with Two Hamming diff of arr
	public static List<Integer> TwoD(int arr) {
		List<Integer> list2D = new ArrayList<Integer>();
		for(int i=0; i<HammingL; i++) {
			for(int j=i+1; j<HammingL; j++) {
				int newValue = arr ^ (1<<i) ^ (1<<j);
				list2D.add(newValue);
			}
		}
		return list2D;
	}
	
	
	public static void read_data(HashMap<Integer, Integer> HM) throws IOException{
		
		File file = new File(System.getProperty("user.dir") + "/clustering_big.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		int vertexnum =0;
		try 
		{
		// Read a line of text from the file
			String line = input.readLine();
			String[] vector = line.split("\\s+");
			HammingL = Integer.parseInt(vector[1]);

			while (line != null) 
			{
				// Split the line of text into a vector of Strings
				// The \\s+ is the space between ints
				vector = line.split("\\s+");
			
				if(vector.length == HammingL) {
				// Keep track of the key values	
					int sum =0;
					for(int i=0; i<HammingL; i++) {
						sum += Integer.parseInt(vector[i])*Math.pow(2, HammingL-i-1);
					}
					if(!HM.containsKey(sum)) {
					HM.put( sum, vertexnum);
			    	vertexnum++;
					}
				}
				
		    	// Convert the vector of values for the edges to integers
		    	// Read a new line of text*/
		    	line = input.readLine();
			} // while
		System.out.println("Number of vertices = "+HM.size()+ " HammingL = "+HammingL);
		numOfVertices = HM.size();
		} // try
		finally
		{
			input.close();
		} // finally			
	}	
}
