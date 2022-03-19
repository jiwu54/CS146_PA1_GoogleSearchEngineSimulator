package googleSearchEngineSimulator;

/**
 * 
 * FileName: HeapSort.java
 * 
 * Implementation of Heap Sort and Heap Priority Queue algorithm, 
 * following the pseudo codes from the book Introduction To Algorithms Third Edition
 * 
 * @author JianBin Wu
 *
 */
public class HeapSort{
	/** a variable for size of the heap */
	private int heapSize;
	
	/**
	 * Return the parent of node i.
	 * 
	 * @param i the position of node.
	 * @return the parent of node i.
	 */
	public int parent(int i)
	{
		return i/2;
	}
	
	/**
	 * Return the left child of node i.
	 * 
	 * @param i the position of node.
	 * @return the left child of node i.
	 */
	public int left(int i)
	{
		return 2*i;
	}
	
	/**
	 * Return the right child of node i.
	 * 
	 * @param i the position of node.
	 * @return the right child of node i.
	 */
	
	public int right (int i)
	{
		return 2*i+1;
	}
	
	
	/**
	 * Maintain the max-heap property of a node
	 * 
	 * @param A An given array
	 * @param i the node from the array want to maintain
	 */
	public void maxHeapify(int A[],int i)
	{
		int largest;
		int l = left(i);
		int r = right(i);
		if(l <= heapSize && A[l] > A[i])
		{
			largest = l;
		}
		else {
			largest = i;
		}
		if(r <= heapSize && A[r] > A[largest])
		{
			largest = r;
		}
		if (largest != i)
		{
			int temp = A[i];
			A[i] = A[largest];
			A[largest] = temp;
			
			maxHeapify(A, largest);
		}
	}
	
	/**
	 * Convert the array into a max-heap.
	 * 
	 * @param A the array that is going to convert.
	 */
	
	public void buildMaxHeap(int A[])
	{
		heapSize = A.length - 1;
		for(int i = (A.length- 1) / 2  ; i >= 0; i--)
		{
			maxHeapify(A,i);
		}
	}
	
	/**
	 * Sort the elements in the array by maintaining max heap property.
	 * 
	 * @param A the array that is going to sort.
	 */
	public void heapSort(int A[])
	{
		 buildMaxHeap(A);
		 for (int i = A.length - 1 ; i >= 0; i--)
		 {
			 int temp = A[i];
			 A[i] = A[0];
			 A[0] = temp;
			 heapSize = heapSize -1;
			 maxHeapify(A,0);
		 }
	}
		
	/**
	 * Insert an element key into a max-heap.
	 * 
	 * @param A the array that is being to insert.
	 * @param key the key that is going to insert.
	 */
	public void maxHeapInsert(int A[], int key) 
	{
		heapSize = heapSize + 1;
		A[heapSize] = Integer.MIN_VALUE;
		heapIncreaseKey(A, heapSize, key);
	}
	
	/**
	 * Return and remove the largest element in the max-heap
	 * 
	 * @param A the array where the largest element being remove
	 * @return the largest element from the array.
	 */
	public int heapExtractMax(int A[]) 
	{
		int max;
		if(heapSize < 0)
		{
			throw new IllegalArgumentException("heap underflow");
		}
		max = A[0];
		A[0] = A[heapSize];
		heapSize = heapSize - 1;
		maxHeapify(A,0);
		return max;
	}
	
	/**
	 * Increase the value of the element to a larger value in a max-heap
	 * 
	 * @param A the array where node located.
	 * @param i the node want to increase.
	 * @param key the value being increase.
	 */
	public void heapIncreaseKey(int A[], int i, int key)
	{
		if (key < A[i]) 
		{
			throw new IllegalArgumentException("new key is smaller than current key");
		}
		A[i] = key;
		while(i > 0 && A[parent(i)] < A[i])
		{
			int temp = A[parent(i)];
			A[parent(i)] = A[i];
			A[i] = temp;
			i = parent(i);
		}
	}
	
	/**
	 * Return the maximum element from the max-heap (root)
	 * 
	 * @param A the array of the maximum element being return.
	 * @return the maximum of element from the max-heap.
	 */
	public int heapMaximum(int A[]) 
	{
		return A[0];
	}
	

	
}
