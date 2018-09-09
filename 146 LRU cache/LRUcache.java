/** this problem is to design a data structure that supports:
    1. a queue with fixed capacity, any recently used element will be moved to the end of the queue
    2. when capacity is reached, shall remove the first element of the queue before inserting new element
    3. all get and put operation shall be O(1)
    
    To safisfy O(1) insertion for a queue, we need to used doubly linked list.
    To identify the position of elements in the Doubly Linked List, we should use a HashMap to store the nodes.
    */
    
class LRUcache {
    private final int capacity;
    private final Node head;
    private final Node tail;
    // private final HashMap<int, Node> map;
    private final HashMap<Integer, Node> map;
    
    public LRUcache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
    }
    
    public get(int key) {
        Node nd = map.get(key);
        if (nd == null) {
            return -1;
        } 
        else {
            remove(nd);
            insert(nd);
            return nd.val;
        }
    }
    
    public void insert(int key, int value) {
        Node nd = map.get(key);
        if (nd != null) {
            nd.val = value;
            remove(nd);
            insert(nd);
        }
        else {
            if (map.size() == capacity) {
                nd = tail.prev;
                remove(nd);
                map.remove(nd.key);
            }
            
            nd = new Node(key, value);
            insert(nd);
            map.put(key, nd);
        }
    }
    
    private void put(Node nd) {
        nd.next = head.next;
        head.next.prev = nd;
        
        head.next = nd;
        nd.prev = head;
    }
    
    private void remove(Node nd) {
        nd.prev.next = nd.next;
        nd.next.prev = nd.prev;
    }
    
    class Node {
        int key;
        int val;
        Node prev;
        Node next;
        public Node (int key = 0, int value = 0) {
            this.key = key;
            this.value = value;
            prev = null;
            next = null;
        }
    }
}
