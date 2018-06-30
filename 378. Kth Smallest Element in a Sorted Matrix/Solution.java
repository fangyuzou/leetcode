/** 378. Kth Smallest Element in a Sorted Matrix.
  * Link: https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/description/
  *
  * We propose three methods here. 
  * 	1 PriorityQueue, 
  *		2 Binary Search (for range),
  */
  
public class Solution {

	/* Priorty Queue */
	public int kthSmallest(int[][] matrix, int k) {
		// We define a MaxPriorityQueue and add elements from the matrix to the queue. We keep a fixed capacity k 
		// for the queue. 
		
		PriorityQueue<Integer> pq = new PriorityQueue<>(a,b -> Integer.compare(b,a));
		for (int i = 0; i < matrix.length; i++)
			for (int j = 0; j < matrix[0].length; j++) {
				if (pq.size() < k)  pq.offer(matrix[i][j]);
				else {
					int peek = pq.peek();
					if (peek < matrix[i][j])  break;	
					// too large, we don't replace the peek in the queue. Since the matrix 
					// is sorted, we don't need to check the rest in the row.
					pq.poll();
					pq.offer(matrix[i][j]); 	// replace the peek
				}
			}
		return pq.peek();
	}
	
	/* Binary Search (for range) */
	public int kthSmallest(int[][] matrix, int k) {
		// We will binary search the range between min and max. The search condition is whether the number of elements 
		// less than or equal to the target is less than or greater than k
		
		int lo = matrix[0][0], hi = matrix[matrix.length -1][matrix[0].length -1];
		while (lo < hi) {
			int mid = lo + (hi - lo)/2;
			if (countLessEqual(matrix, mid) < k) lo = mid + 1;
			else  hi = mid;
		}
		return lo;
	}
	
	private int countLessEqual(int[][] matrix, int target) {
		// We will fully utilize the fact that the matrix is sorted. We start to search along the reverse diagonal.
		// In each row we try to search the position where the element is the smallest one bigger than the target.
		// Therefore, in the next row we don't need to go right as we already know that those elements on the right 
		// of the current column will be bigger than the target.
		// Attention: be careful to the conner case when the very right element is still no greater than the target.
		
		int r = matrix.length;
		int c = matrix[0].length;
		int count = 0;
		
		for (int i = 0, j = c; i < r && j >= 0; i++) {
			while (j > 0 && matrix[i][j-1] > target) j--;
			count += j;
		}
		return count;
	}
}
	
