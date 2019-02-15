package com.neu.algorithms;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// Uncomment the main method printPixels to check that the array is sorted for each sort

// The timings are given for each sort method with Tim Sort giving the best time

public class Question5 {

	private double pixelArray [];
	
	private static double insertionSortArray[];
	
	private static double quickSortArray[];
	
	private static double mergeSortArray[];
	
	private static double timSortArray[];
	
	//As the run size keeps increasing the sort alogrithm improves
	private final int run = 4096;
	
	public void readImage()
	{
		try 
		{
			// get the BufferedImage, using the ImageIO class
			BufferedImage image = ImageIO.read(this.getClass().getResource("Boston.jpeg"));
			
			//Iterate through the image using width and height
			int w = image.getWidth();
		    int h = image.getHeight();
		    System.out.println("width, height: " + w + ", " + h);

		    pixelArray = new double[w * h];
		    int pixelCount = 0;
		    
		    for (int i = 0; i < h; i++) 
		    {
		      for (int j = 0; j < w; j++) 
		      {
		    	 int pixel = image.getRGB(j, i);
		    	 pixelArray[pixelCount++] = getIntensity(pixel);
		      }
		    }
			
		} 
		catch (IOException e) 
		{
			System.err.println(e.getMessage());
		}
	}
	
	/**
	 * Returns the intensity of the pixel based on its RGB value
	 * @param r
	 * @param g
	 * @param b
	 * @return
	 */
	public double getIntensity(int pixel)
	{
		Color c = new Color(pixel);
		return 0.2989 * c.getRed() + 0.5870 * c.getGreen() + 0.1140 * c.getBlue();
	}
	
	/**
	 * Print the pixel intensities of the image
	 */
	public void printPixels(double [] arr)
	{
		for(double pixel : arr)
			System.out.println(pixel);
	}
	
	/**
	 * Get a new Array to perform sorting
	 */
	public double[] getArray()
	{
		double newArray[] = new double[pixelArray.length];
		
		for(int count=0; count < pixelArray.length; count++)
			newArray[count] = pixelArray[count];
			
		return newArray;
	}
	
	/* Insertion Sort */
	
	/**
	 *  Insertion sort of pixels in the image in the descending order
	 */
	public void insertionSort(double sortArray[], int start, int end)
	{
		for(int count=start; count < end; count++)
		{
			double key = sortArray[count];
			int prev = count - 1;
			
			while(prev >= 0 && sortArray[prev] < key)
			{
				sortArray[prev+1] = sortArray[prev];
				prev = prev - 1;
			}
			
			sortArray[prev+1] = key;
		}
		
	}
	
	/* Heap Sort */
	
	/**
	 * Heap sort the array
	 * @param arr
	 */
	public void heapSort(double arr[]) 
    { 
        int n = arr.length; 
  
        // Build heap (rearrange array) 
        for (int i = n / 2 - 1; i >= 0; i--) 
            heapify(arr, n, i); 
  
        // One by one extract an element from heap 
        for (int i=n-1; i>=0; i--) 
        { 
            // Move current root to end 
            double temp = arr[0]; 
            arr[0] = arr[i]; 
            arr[i] = temp; 
  
            // call max heapify on the reduced heap 
            heapify(arr, i, 0); 
        } 
        
        //printPixels(arr);
    } 
	
	// To heapify a subtree rooted with node i which is 
    // an index in arr[]. n is size of heap 
    void heapify(double arr[], int n, int i) 
    { 
        int smallest = i; // Initialize smallest as root 
        int l = 2*i + 1; // left = 2*i + 1 
        int r = 2*i + 2; // right = 2*i + 2 
  
        // If left child is smaller than root 
        if (l < n && arr[l] < arr[smallest]) 
        	smallest = l; 
  
        // If right child is larger than smallest so far 
        if (r < n && arr[r] < arr[smallest]) 
        	smallest = r; 
  
        // If largest is not root 
        if (smallest != i) 
        { 
            double swap = arr[i]; 
            arr[i] = arr[smallest]; 
            arr[smallest] = swap; 
  
            // Recursively heapify the affected sub-tree 
            heapify(arr, n, smallest); 
        } 
    } 
    
    /* Quick Sort */
    
	 /**
	  *  The main function that implements QuickSort()
	  *  arr[] --> Array to be sorted, 
	  *  low  --> Starting index,
	  *  high  --> Ending index
	  */
	 void quickSort(double arr[], int low, int high) 
	 { 
	     if (low < high) 
	     { 
	         // pi is partitioning index, arr[pi] is now at right place 
	         int pi = partition(arr, low, high); 
	
	         // Recursively sort elements before partition and after partition 
	         quickSort(arr, low, pi-1); 
	         quickSort(arr, pi+1, high); 
	     }
	 } 
	 
	 /**
     * This function takes last element as pivot, places the pivot element at its correct 
     * position in sorted array, and places all smaller (smaller than pivot) to left of	 
     * pivot and all greater elements to right of pivot
     **/
	 int partition(double arr[], int low, int high) 
	 { 
	     double pivot = arr[high];  
	     int i = (low-1); // index of smaller element 
	     for (int j=low; j<high; j++) 
	     { 
	         // If current element is smaller than or 
	         // equal to pivot 
	         if (arr[j] > pivot) 
	         { 
	             i++; 
	
	             // swap arr[i] and arr[j] 
	             double temp = arr[i]; 
	             arr[i] = arr[j]; 
	             arr[j] = temp; 
	         } 
	     } 
	
	     // swap arr[i+1] and arr[high] (or pivot) 
	     double temp = arr[i+1]; 
	     arr[i+1] = arr[high]; 
	     arr[high] = temp; 
	
	     return i+1; 
	 } 
	 
	 /* Merge Sort */

	/**
	 *  Merges two subarrays of arr[]. 
     *	First subarray is arr[l..m] 
     *	Second subarray is arr[m+1..r]
     */ 
    void merge(double arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        double L[] = new double [n1]; 
        double R[] = new double [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] > R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
  
    // Main function that sorts arr[l..r] using 
    // merge() 
    void mergeSort(double arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            mergeSort(arr, l, m); 
            mergeSort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    } 
    
    /**
     * Get the minimum value
     */
    int minimum(int a, int b)
    {
    	if(a < b)
    		return a;
    	else
    		return b;
    }
	
    /* Tim Sort */
    void timSort(double arr[])
    {
    	int i, size, start, mid, end;	
    	
       for(i=0; i<arr.length; i+=run)
       {
    		insertionSort(arr,i,minimum((i+32), (arr.length-1)));
    		
       }
    	
       for(size = run; size < arr.length; size = 2*size)
    	{
    		for(start=0; start < arr.length; start += 2*size)
    		{
    			mid = start + size - 1;
    			end = minimum((start + 2*size-1) , (arr.length-1));
    			
    			if(end == arr.length-1)
    				mid = end-1;
    			
    			merge(arr, start, mid, end);
    		}
    	}
    	
    }
    
	public static void main(String[] args) {
		
		Question5 q = new Question5();
		
		//Read the image
		q.readImage();
		
		//Print the pixel intensities of the image
		//q.printPixels();
		
		//Insertion sort implementation
		long startTime = System.currentTimeMillis();
		insertionSortArray = q.getArray();
		q.insertionSort(insertionSortArray, 0, insertionSortArray.length);
		long endTime = System.currentTimeMillis();
	    System.out.println("Insertion Sort : " + (endTime - startTime) + " milliseconds");
		//q.printPixels(insertionSortArray);
		
		
		//Heap Sort implementation
	    startTime = System.currentTimeMillis();
		q.heapSort(q.getArray());
	    endTime = System.currentTimeMillis();
	    System.out.println("Heap Sort : " + (endTime - startTime) + " milliseconds");
		
		//Quick Sort Implementation
	    startTime = System.currentTimeMillis();
		quickSortArray = q.getArray();
		q.quickSort(quickSortArray, 0, quickSortArray.length-1);
	    endTime = System.currentTimeMillis();
	    System.out.println("Quick Sort : " + (endTime - startTime) + " milliseconds");
		//q.printPixels(quickSortArray);
		
		//Merge Sort Implementation
	    startTime = System.currentTimeMillis();
		mergeSortArray = q.getArray();
		q.mergeSort(mergeSortArray, 0, mergeSortArray.length-1);
		endTime = System.currentTimeMillis();
	    System.out.println("Merge Sort : " + (endTime - startTime) + " milliseconds");
		//q.printPixels(mergeSortArray);
		
		//Tim Sort Implementation
	    startTime = System.currentTimeMillis();
		timSortArray = q.getArray();
		q.timSort(timSortArray);
		endTime = System.currentTimeMillis();
	    System.out.println("Tim Sort : " + (endTime - startTime) + " milliseconds");
		//q.printPixels(timSortArray);
	}
}