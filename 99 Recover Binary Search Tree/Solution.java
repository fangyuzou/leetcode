/* in order to identify the swapped TreeNodes, do inorder transverse.
 */
 
 
class Solution {

    TreeNode first = null;
    TreeNode second = null;     // record the mistaken nodes
    TreeNode previous = new TreeNode(Integer.MIN_VALUE);    // record the previous mistaken node
    
    public void recoverBST(TreeNode root) {
        inorder(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    
    private void inorder(TreeNode root) {
        if (root == null)
            return;
            
        inorder(root.left);
        /* In a valid BST, we always have (previous < root). If this is violated, there must be a mistake.
           The first time the mistake is the previous; the second time the mistake is the root (in this case
           the (first) is no longer (null).) */
           
        if (root.val < previous.val) {
            if (first == null) {
                first = previous;
            }
            second = root;
        }
        previous = root;
        inorder(root.right);
    }
}
