import java.util.*;

/**
 * @Author: FangyiXu
 * @Date: 2019-08-08 16:24
 */
class Current {
    public int[] twoSum(int[] nums, int target) {
        //1 -> O(n*n)
        /*for(int i=0; i<nums.length; i++) {
            for(int j=nums.length-1; j>i; j--) {
                if(nums[i] + nums[j] == target) {
                    return new int[] {i, j};
                }
            }
        }
        return new int[2];*/

        //2 -> O(n)
        /*Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], i);
        }

        for(int i=0; i< nums.length; i++) {
            Integer num = map.get((target - nums[i]));
            if(num != null && num != i) {
                return new int[]{i, num};
            }
        }
        throw new IllegalArgumentException("No such result！");*/

        //3 -> O(n)
        Map<Integer, Integer> map = new HashMap<>(1024);
        for(int i=0; i<nums.length; i++) {
            int component = target - nums[i];   //目标键：target - 当前值
            if(map.containsKey(component)) {
                return new int[] {map.get(component), i};
            }
            map.put(nums[i], i);    //当前值为键，索引为值
        }

        throw new IllegalArgumentException("No such result！");
    }

     public static void main(String[] args0) {
         Current current = new Current();
         int[] ints = current.twoSum(new int[]{230,863,916,585,981,404,316,785,88,12,70,435,384,778,887,755,740,337,86,92,325,422,815,650,920,125,277,336,221,847,168,23,677,61,400,136,874,363,394,199,863,997,794,587,124,321,212,957,764,173,314,422,927,783,930,282,306,506,44,926,691,568,68,730,933,737,531,180,414,751,28,546,60,371,493,370,527,387,43,541,13,457,328,227,652,365,430,803,59,858,538,427,583,368,375,173,809,896,370,789
         }, 542);
         System.out.println(Arrays.asList(ints[0], ints[1]));
     }
}
