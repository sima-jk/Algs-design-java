/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 3, August 2018.
 * 
 * @author SimaJeddi
 *
 * In this programming problem and the next you'll code up the greedy algorithm from the 
 * lectures on Huffman coding.

 * Download the text file "huffman.txt"
 * 
 * This file describes an instance of the problem. It has the following format:
 * [number_of_symbols]
 * [weight of symbol #1]
 * [weight of symbol #2]
 *
 * ...
 * For example, the third line of the file is "6852892," indicating that the weight of the 
 * second symbol of the alphabet is 6852892. (We're using weights instead of frequencies,
 * like in the "A More Complex Example" video.)
 *
 * Your task in this problem is to run the Huffman coding algorithm from lecture on this data
 * set. What is the maximum length of a codeword in the resulting Huffman code?
 *
 * Continuing the previous problem, what is the minimum length of a codeword in your Huffman code?
 *
 * ADVICE: If you're not getting the correct answer, try debugging your algorithm using some 
 * small test cases. And then post them to the discussion forum!
 *
 *
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Comparator;

public class Huffman {

	public static int Num_vertices;
	public static PriorityQueue<HuffmanNode> Heap = new PriorityQueue<HuffmanNode>( new MyComparator());
	public static int max = Integer.MIN_VALUE;
	public static int min = Integer.MAX_VALUE;
	 //Create a root node

	public static HuffmanNode root = null;

	
	public static void main(String[] args ) throws IOException{
		
		//ArrayList<Integer> Array = new ArrayList<Integer>();

		 Heap = read_element(Heap);
		 huffman(Heap);
		 

		 //Prints Question 1 answer
		 FindMax(root, "");

		 //Prints Question 2 answer
		 FindMin(root, "");
	}
	public static void huffman(PriorityQueue<HuffmanNode> Heap) {
		while(Heap.size()>1) {
			//first min extract
			HuffmanNode value1 = Heap.peek();
			Heap.poll();
			//second min extract
			HuffmanNode value2 = Heap.peek();
			Heap.poll();
			
			// second extracted node as the right child.
			String s ="-";
			HuffmanNode e = new HuffmanNode(value1.Weight+value2.Weight, s, value2, value1);
			Heap.add(e);
			root = e;
		}
		System.out.println("Heap size after huffman coding = "+ Heap.size());
		System.out.println("Final root name = "+ root.name+"root Weight = "+ root.Weight);
	}
	
	public static void FindMin(HuffmanNode root, String s) {
		
		if(root.left 
				== null 
				&& root.right 
				== null
				&& root.name != "-"
				) {
			if(s.length() < min) {
				min = s.length();
				System.out.println("Min Length of CodeWord => "+ min);
			}
			return;
		}
		FindMin(root.left,s+"0");
		FindMin(root.right,s+"1");
		
	}
	
	public static void FindMax(HuffmanNode root, String s) {
		
		if(root.left 
				== null 
				&& root.right 
				== null
				&& root.name != "-"
				) {
			if(s.length() > max) {
				max = s.length();
				System.out.println("Max Length of CodeWord => "+ max);
			}
			return;
		}
		FindMax(root.left,s+"0");
		FindMax(root.right,s+"1");
	}
	
	public static PriorityQueue<HuffmanNode> read_element(PriorityQueue<HuffmanNode> arr) throws IOException 
	{

		File file = new File(System.getProperty("user.dir") + "/huffman.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		int count = 0;
		try 
		{
			// Read a line of text from the file
		    String line = input.readLine();
		     Num_vertices = Integer.parseInt(line);
		    	line = input.readLine();

		    while (line != null) 
		    {

		    	// Read integer values from each line
		    	//HN.Weight = Integer.parseInt(line);
		    	//HN.left = null;
		    	//HN.right = null;
		    	//HN.name
		    	int Weight = Integer.parseInt(line);
		    	HuffmanNode HN = new HuffmanNode(Weight, String.valueOf(count), null, null);

		    	arr.add(HN);
		    	count++;
		    	
		    	// Read a new line of text
		    	line = input.readLine();
		    } // while
		} // try
		finally
		{
			input.close();
		} // finally
		System.out.println("Number of read elements = "+ arr.size());
		return arr;
	}
	
	
}

class HuffmanNode{
	int Weight;
//	PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder());

	HuffmanNode right;
	HuffmanNode left;
	String name;
	public HuffmanNode(int w, String name, HuffmanNode r, HuffmanNode l) {
		
		// TODO Auto-generated constructor stub
		this.Weight = w;
		this.left = l;
		this.right = r;
		this.name = name;
	}
	
}

//comparator class helps to compare the node
//on the basis of one of its attribute.
//Here we will be compared
//on the basis of data values of the nodes.
class MyComparator implements Comparator<HuffmanNode> {
 public int compare(HuffmanNode x, HuffmanNode y)
 {

     return x.Weight - y.Weight;
 }
}
