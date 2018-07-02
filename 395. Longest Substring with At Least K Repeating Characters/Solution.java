/** 395. Longest Substring with At Least K Repeating Characters. 
  * https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/description/
  * Two ideas on this problem:
  * 1 divide and conquer.
  * 2 two pointer method.
  *
  */
class Solution {

    /* divide and conquer
     * the key point is to find out which chars cannot be such a substring. Once we encouter such a char, we divide 
     * the string into two part and do the search again.
     * little trick to optimize: we exclude a substring of impossible chars as long as possible.
     */
    public int longestSubstring(String s, int k) {
        if (s == null) return 0;
        return helper(s, 0, s.length()-1, k);
    }
    
    private int helper(String s, int lo, int hi, int k) {
        if (hi < lo) return 0;
        
        int[] count = new int[26];
        for (int i = lo; i <= hi; i++) count[s.charAt(i) - 'a']++;
        
        int left, right;
        for (left = lo; left <= hi; left++) {
            if (count[s.charAt(left) - 'a'] < k) {
                right = left+1;
                while (right < s.length() && count[s.charAt(right) - 'a'] < k) right++;
                return Math.max(helper(s, lo, left-1, k), helper(s, right, hi, k));
            }
        }
        return hi-lo+1;
    }
    
    
    
    
    /* two pointer method
     * the problem here is that the conditions for a valid substring is two loose that we are not able to apdate the pointers
     * we divide it into deterministic subproblems:
     * We specify three quantities: numDiffChar (number of different chars in the substring), maxNumDiffChar (maximum number 
     * of different chars), numNoLessThanK (number of chars repeating no less than K times). 
     * the set of valid substring is the union of the set (maxNumDiffChar = numNoLessThanK = i for i = 1, 2, ..., 26). 
     * for each case the subproblem is deterministic.
     */
     
    public int longestSubstring(String s, int k) {
        if (s == null) return 0;
        
        int res = 0, maxNumDiffChar, numDiffChar, numNoLessThanK, lo, hi;
        for (maxNumDiffChar = 1; maxNumDiffChar <= 26; maxNumDiffChar++) {
            int[] count = new int[26];
            numDiffChar = 0;
            numNoLessThanK = 0;
            lo = 0;
            hi = 0;
            
            while (hi < s.length()) {
                if (numDiffChar <= maxNumDiffChar) {
                    int idx = s.charAt(hi) - 'a';
                    if (count[idx] == 0)     // a new char
                        numDiffChar++;
                    count[idx]++;
                    if (count[idx] == k)    // a new char that repeats k times
                        numNoLessThanK++;
                    hi++;
                }
                else {
                    int idx = s.charAt(lo) - 'a';
                    if (count[idx] == k) 
                        numNoLessThanK--;
                    count[idx]--;
                    if (count[idx] == 0)
                        numDiffChar--;
                    lo++;
                }
                if (numDiffChar == maxNumDiffChar && numNoLessThanK == numDiffChar)
                    res = Math.max(res, hi-lo);
            }
        }
        return res;
    }
}
     
