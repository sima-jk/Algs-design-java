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
 *
 * Your task in this problem is to run the greedy algorithm that schedules jobs in decreasing 
 * order of the difference (weight - length). Recall from lecture that this algorithm is not
 * always optimal. 
 * 
 * IMPORTANT: if two jobs have equal difference (weight - length), you should
 * schedule the job with higher weight first.
 * 
 * Beware: if you break ties in a different way, 
 * you are likely to get the wrong answer. You should report the sum of weighted completion 
 * times of the resulting schedule --- a positive integer --- in the box below.
 * 
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

public class Scheduling_Diff {

	public static void main(String[] args) throws IOException{
		ArrayList<Job1> JobsArray = new ArrayList<Job1>();
		
		read_data(JobsArray);
		
		
		sort_jobs(JobsArray );
	}
	
	public static void sort_jobs(ArrayList<Job1> JobsArray) {
		
		//Sort Jobs
		Collections.sort(JobsArray, new SortbyDiff());
		
        long lengthSum =0;
        long WeightedSum =0;
        
        for(Job1 job:JobsArray ) {
        	lengthSum += job.get_length();
        	WeightedSum += (lengthSum*job.get_weight());
        	System.out.println("Length Sum = " + lengthSum+ " WeightedSum = "+WeightedSum);
        	
        }
        System.out.println("Total Sum Weighted => " + WeightedSum);
			}
	
	
	public static void read_data(ArrayList<Job1> JobsArray ) throws IOException
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
		    		 JobsArray.add(new Job1(weight, length));
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
class Job1{
	private int weight;
	private int length;
	//constructor
	public Job1(int weight, int length)
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
	 
	 public int JobDiff() 
		{
			return weight - length;
		}
	 
}

class SortbyDiff implements Comparator<Job1> {
	@Override
    

    public int compare(Job1 instance1, Job1 instance2) {
		int result = 0;
		
         if( instance1.JobDiff() > instance2.JobDiff())
         	result = -1;
         else if(instance1.JobDiff() < instance2.JobDiff()) 
        	 result = 1;
         else if((instance1.JobDiff() == instance2.JobDiff()) && instance1.get_weight()>instance2.get_weight())
        	 result = -1;
         else if((instance1.JobDiff() == instance2.JobDiff()) && instance1.get_weight()<instance2.get_weight())
        	 result = 1;
         
         return result;
    }
}
