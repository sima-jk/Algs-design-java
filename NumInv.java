package myTests;

//import java.util.*;  
import java.io.File;
import java.io.FileNotFoundException;
//import java.util.Arrays;
import java.util.Scanner;
import java.math.BigInteger;

public class NumInv {
	
	
	static BigInteger Inv_Count(int[] arr, int n ) {
		
		int[] tmp = new int[n];
		BigInteger Inv_Count = sort_Count(arr, tmp, 0, n-1);
		return Inv_Count;
	}
	
	static BigInteger sort_Count(int[] arr, int[] tmp, int lo, int hi) {
		int mid = 0; 
		BigInteger Ninv = new BigInteger("0");

		if (lo<hi){
			mid = (hi+lo)/2;
			
			Ninv = sort_Count(arr, tmp, lo, mid);
			Ninv =  Ninv.add(sort_Count(arr, tmp, mid+1, hi));
			
			Ninv = Ninv.add(merge_Count(arr, tmp, lo, mid+1, hi));
		}
		//BigInteger b = BigInteger.valueOf(Ninv);
		return Ninv;
	}
	
	static BigInteger merge_Count(int[] arr, int[] tmp, int lo, int mid, int hi) {
		
		BigInteger Ninv = new BigInteger("0");
		
		int i,j,k,l;
		i = lo;
		j = mid;
		k = lo;
		
		while((i<= mid-1) && (j<=hi)) {
			if(arr[i] <= arr[j])
				tmp[k++] = arr[i++];
			else {
				tmp[k++] = arr[j++];
				Ninv = Ninv.add(BigInteger.valueOf(mid-i));
			}
		}
		while (i<=mid-1)
			tmp[k++] = arr[i++];
		while(j<=hi)
			tmp[k++] = arr[j++];
			
		for( l=lo; l<=hi ;l++)
			arr[l]=tmp[l]; //why this is copying only from lo to hi range??
		// how this copying will be affect the other methods since it is not returned!!
		//will this line change arr in sort_Count method??
		//BigInteger a = BigInteger.valueOf(Ninv);
		return Ninv;
	}
	public static void main(String[] args) {
		   /* File file = new File("IntegerArray.txt");

			try {
		    Scanner sc = new Scanner(file);

	        while (sc.hasNextLine()) {
	            int arr[i++] = sc.nextInt();
	            //System.out.println(i);
	        }
	        sc.close();
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			*/
			
			/*BufferedReader in = new BufferedReader(new FileReader("H:\\New folder (2)\\Algorithms Design\\Divide and Conquer, Sorting and Searching, and Randomized Algorithms\\Week 2\\Programming Assignment\\IntegerArray.txt"));
			String str;

			List<String> list = new ArrayList<String>();
			while((str = in.readLine()) != null){
			    list.add(str);
			}

			String[] stringArr = list.toArray(new String[0]);
			int[] arr =  new int[stringArr.length]; 
			for (int i = 0; i < stringArr.length; i++) 
			    arr[i] = Integer.parseInt(stringArr[i]);
			    */
			try {
			 Scanner scan = new Scanner(new File("IntegerArray.txt")); //provide file name from outside
		        int counter =0; //keep track of how many integers in the file
		        while(scan.hasNextInt()) 
		        {
		            counter++;
		            scan.nextInt();
		        }
		        scan.close();
		        Scanner scan2 = new Scanner(new File("IntegerArray.txt")); 
		        int arr[] = new int[counter];
		        for(int i=0;i<counter;i++)
		        {
		            arr[i]=scan2.nextInt(); //fill the array with the integers
		        }
		        scan2.close();
				//System.out.println("Number of integers are = " + counter);

		        BigInteger C = Inv_Count(arr, arr.length);
				System.out.println("Number of inversion are = " + C);
		        }
			catch(FileNotFoundException e){
				//e.printStackTrace();
				System.out.println("Error in reading file");
			}
			//int[] arr = new int[] {1, 20, 6, 4, 5};
			
			//int C = Inv_Count(arr, arr.length-1);
			//System.out.println("Number of inversion are = " + C);
		}

}

