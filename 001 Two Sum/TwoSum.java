/**
 * checking whether there are two elements in the array whose sum is the given target. The function returns the 
 * two indices of the two elements. We assume that there is at most just one such pair. 
 * @param nums, target 
 * @return result
 */
 
import java.util.HashMap;
public class TwoSum {
    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(target - nums[i])){
                result[1] = i;
                result[0] = map.get(target - nums[i]);
                return result;
            }
            map.put(nums[i], i);
        }
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 8};
        int target = 9;
        int[] result = twoSum(nums, target);
        System.out.println("[" + result[0] + "," + result[1] + "]");
    }
}
