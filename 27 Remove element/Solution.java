class Solution {
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;
        int insertionIdx = 0;
        /* copy the array while ignore the elements of the given value */
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val)  nums[insertionIdx++] = nums[i];
        }
        return insertionIdx;
    }
}
