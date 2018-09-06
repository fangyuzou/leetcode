class Solution {
    public boolean search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return false;
        /* each time tell whether 
                    nums[mid] == target
            if hit then we are done; otherwise we divide the array into two parts: 
                    [lo, mid], [mid, hi]
            tell whether left part is sorted or the right part is sorted.
            if the left part is sorted, then we can easily tell whether the target 
            hits in the left part by comparing 
                    nums[lo] <= target < nums[mid]
            if we can't tell which part is sorted, this is due to duplicates. 
            We let 
                    lo++
            In the worst case, say target = 2 while nums = [1,1,1,1]
            this anounts to worst O(n) complexity.
        */
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (target == nums[mid])
                return true;
            if (nums[lo] < nums[mid]) {
                // the left part is sorted
                if (nums[lo] <= target && target < nums[mid])
                    // target hits in the sorted part
                    hi = mid - 1;
                else
                    lo = mid + 1;
            }
            else if (nums[lo] > nums[mid])
                // the right part is sorted
                if (nums[mid] < target && target <= nums[hi])
                    // target hits in the sorted part
                    lo = mid + 1;
                else
                    hi = mid - 1;
            }
            else // duplicates, can't tell which part is sorted
                lo++;
        }
        return nums[lo] == target;
    }
