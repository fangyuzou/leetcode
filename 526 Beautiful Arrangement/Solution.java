class Solution {
    int res = 0;
    public int countArrangement(int N) {
        int[] nums = new int[N+1];
        for (int i = 1; i <= N; i++)
            nums[i] = i;
            
        backtrack(N, nums);
        
        return res;
    }
    
    private void backtrack(int pos, int[] nums) {
        if (pos == 0) {
            res++;
        }
        else {
            for (int i = pos; i >= 1; i--) 
                if (nums[i] % pos == 0 || pos % nums[i] == 0) {
                    swap(nums, pos, i);
                    backtrack(pos-1, nums);
                    swap(nums, pos, i);
                }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        if (i != j) {
            int temp = nums[i];
            nums[i]  = nums[j];
            nums[j]  = temp;
        }
    }
}
