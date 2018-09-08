/* this problem asks to find all the root-to-leaf paths that sum up to giver target.
    DFS is applied. 
    Note that the recursion itself has a natural graph structure and stack structure.
    */
    
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null)
            return res;
        helper(root, sum, res, new ArrayList<Integer>());
        return res;
    }
    
    private void helper(TreeNode root, int sum, List<List<Integer>> res, List<Integer> list) {
        if (root == null) {
            if (sum == 0)
                res.add(new ArrayList<>(list));
            return;
        }  
        
        list.add(root.val);
        sum -= root.val;
        if (root.left == null) {
            helper(root.right, sum, res, list);
        }
        else if (root.right == null) {
            helper(root.left, sum, res, list);
        }
        else {
            helper(root.left, sum, res, list);
            helper(root.right, sum, res, list);
        }
        list.remove(list.size()-1);
    }
}
