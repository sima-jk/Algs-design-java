/**
 * Algorithms: Design and Analysis, Part 2
 * 
 * Programming Question - Week 1, September 2018.
 * 
 * @author Sima Jeddi
 * 
 * In this programming problem and the next you'll code up the greedy algorithms from lecture 
 * for minimizing the weighted sum of completion times..
 * Download the text file "jobs.txt"
 * 
 * This file describes a set of jobs with positive and integral weights and lengths. It has
 * the format
 *
 * [number_of_jobs]
 * [job_1_weight] [job_1_length]
 * [job_2_weight] [job_2_length]
 *
 * ...
 *
 * For example, the third line of the file is "74 59", indicating that the second job has 
 * weight 74 and length 59.You should NOT assume that edge weights or lengths are distinct.
 * 
 * For this problem, use the same data set as in the previous problem.
 *
 * Your task now is to run the greedy algorithm that schedules jobs (optimally) in decreasing
 * order of the ratio (weight/length). In this algorithm, it does not matter how you break 
 * ties. You should report the sum of weighted completion times of the resulting schedule --- 
 * a positive integer --- in the box below.
 *
 */

package greedy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.*;

public class Scheduling_Div {

	public static void main(String[] args) throws IOException{
		ArrayList<Job2> JobsArray = new ArrayList<Job2>();
		
		read_data(JobsArray);
		
		
		sort_jobs(JobsArray );
	}
	
	public static void sort_jobs(ArrayList<Job2> JobsArray) {
		
		//Sort Jobs
		Collections.sort(JobsArray, new SortbyDivision());
		
        long lengthSum =0;
        long WeightedSum =0;
        
        for(Job2 job:JobsArray ) {
        	lengthSum += job.get_length();
        	WeightedSum += (lengthSum*job.get_weight());
        	System.out.println("Length Sum = " + lengthSum+ " WeightedSum = "+WeightedSum);
        	
        }
        System.out.println("Total Sum Weighted => " + WeightedSum);
			}
	
	
	public static void read_data(ArrayList<Job2> JobsArray ) throws IOException
	{
		File file = new File(System.getProperty("user.dir") + "/jobs.txt");
		BufferedReader input = new BufferedReader(new FileReader(file));
		int NumJobs = 0;
		int weight;
		int length;

		try 
		{
			// Read a line of text from the file
		    String line = input.readLine();
		    NumJobs = Integer.parseInt(line);
		    while (line != null) 
		    {
		    	// Read integer values from each line
		    	String[] values= line.split("\\s+");
		    	
		    	if(values.length>1) {
		    		weight = Integer.parseInt(values[0]);
		    		length = Integer.parseInt(values[1]);
		    		 JobsArray.add(new Job2(weight, length));
		    	}
		    	// Read a new line of text
		    	line = input.readLine();
		    } // while
		} // try
		finally
		{
			input.close();
		} // finally
		System.out.println("Num of Jobs = "+ NumJobs);
	}
}

//class to reperesent jobs
class Job2{
	private int weight;
	private int length;
	//constructor
	public Job2(int weight, int length)
	{
		this.length = length;
		this.weight = weight;
	}
	
	 public int get_weight() 
	{
		return weight;
	}
	
	 public int get_length() 
		{
			return length;
		}
	 
	 public float JobDiv() 
		{
			return (float)weight / length;
		}
	 
}

class SortbyDivision implements Comparator<Job2> {
	@Override
    

    public int compare(Job2 instance1, Job2 instance2) {
		int result = 0;
		
         if( instance1.JobDiv() > instance2.JobDiv())
         	result = -1;
         else if(instance1.JobDiv() < instance2.JobDiv()) 
        	 result = 1;
         return result;
    }
}
