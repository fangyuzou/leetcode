

class Solution {
    // backtrack solution
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        
        Arrays.sort(nums);
        backtrack(res, new ArrayList<Integer>(), nums, new boolean[nums.length]);
        
        return res;
    }
    
    private void backtrack(List<List<Integer>> res, List<Integer> list, int[] nums, boolean[] used) {
        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        
        for (int i = 0; i < nums.length; i++) {
            if (used[i] == true || i > 0 && nums[i] == nums[i-1] && used[i-1] == true)
                continue;
            used[i] = true;
            list.add(nums[i]);
            backtrack(res, list, nums, used);
            used[i] = false;
            list.remove(list.size() - 1);
        }
    }
    
    // another solution to use HashSet to get rid of duplicates. This is slower.
    public List<List<Integer>> permuteUnique1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0)
            return res;
        
        Set<List<Integer>> set = new HashSet<List<Integer>>();
        set = helper(nums, 0, set);
        for (List<Integer> perm : set)
            res.add(perm);
        return res;
    }
    
    private Set<List<Integer>> helper(int[] nums, int start, Set<List<Integer>> set) {
        if (start == nums.length)
            return set;
        
        int num = nums[start];
        if (start == 0) {
            List<Integer> perm = new ArrayList<>();
            perm.add(num);
            set.add(perm);
            return helper(nums, start+1, set);
        }
        else {
            Set<List<Integer>> newset = new HashSet<>();
            for (List<Integer> perm : set) {
                for (int i = 0; i < start; i++) {
                    List<Integer> newperm = new ArrayList<>(perm);
                    newperm.add(i, num);
                    newset.add(newperm);
                }
                perm.add(num);
                newset.add(perm);
            }
            return helper(nums, start+1, newset);
        }
        
    }
}
