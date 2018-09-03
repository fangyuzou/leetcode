class Solution {
    List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        
        // Check trivial cases
        if (candidates == null || candidates.length == 0)
            return res;
        
        // Sort the array
        Arrays.sort(candidates);
        
        backtrack(target, candidates, 0, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    private void backtrack(int target, int[] candidates, int idx, List<Integer> sol, List<List<Integer>> res) {
        // when it is a valid solution
        if (target == 0 && !sol.isEmpty()) {
            res.add(new ArrayList(sol));
            return;
        }
        
        if (target < 0 || idx == candidates.length)
            return;
        
        for (int i = idx; i < candidates.length; i++) {
            // deal with the duplicates
            if (i > idx && candidates[i] == candidates[i-1])
                continue;
            
            sol.add(candidates[i]);
            backtrack(target - candidates[i], candidates, i + 1, sol, res);
            sol.remove(sol.size() - 1);
        }
        
        return;
    }
}
