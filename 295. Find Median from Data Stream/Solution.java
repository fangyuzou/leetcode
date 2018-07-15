/** Design a data structure that supports the following two operations:
  *     void addNum(int num) - Add a integer number from the data stream to the data structure.
  *     double findMedian() - Return the median of all elements so far.
  *
  * The question to design a data structure to main the median of online stream of data. 
  * The idea is to main a MaxPriorirtyQueue and a MinPriorityQueue to alternatively adding elements.
  */
  
public MedianFinder {
    private PriorityQueue<Integer> left;    // left half stored in a MaxPriorityQueue
    private PriorityQueue<Integer> right;   // right half stored in a MinPriorityQueue
    
    public MedianFinder() {
        left  = new PriorityQueue<Integer>((a,b)->Integer.compare(b,a));     // lambda expression to define descending order
        right = new PriorityQueue<Integer>();
    }
    
    public void addNum(int num) {
        if (left.size() == right.size()) {
            if (!right.isEmpty() && num > right.peek())  { left.offer(right.poll()); right.offer(num); }
            else  left.offer(num);
        }
        else {
            if (num < left.peek())  { right.offer(left.poll()); left.offer(num); }
            else  right.offer(num);
        }
    }
    
    public double median() {
        if (left.size() > right.size())  return left.peek();
        else  return (left.peek() + right.peek() + 0.0)/2;
    }
}
