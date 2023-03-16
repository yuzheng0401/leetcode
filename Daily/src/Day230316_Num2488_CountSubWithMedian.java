import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuzhengwu
 * @version 1.0
 * @description 给你一个长度为 n 的数组 nums ，该数组由从 1 到 n 的 不同 整数组成。另给你一个正整数 k 。
 * 统计并返回 nums 中的 中位数 等于 k 的非空子数组的数目。
 * 注意：
 * 数组的中位数是按 递增 顺序排列后位于 中间 的那个元素，如果数组长度为偶数，则中位数是位于中间靠 左 的那个元素。
 * 例如，[2,3,1,4] 的中位数是 2 ，[8,4,3,5,1] 的中位数是 4 。
 * 子数组是数组中的一个连续部分。
 * @date 2023/3/16 10:15 PM
 */
public class Day230316_Num2488_CountSubWithMedian {

    // https://leetcode.cn/problems/count-subarrays-with-median-k/solutions/2172092/javapythonwen-ti-zhuan-hua-qian-zhui-he-ysf1b/
    // 子数组 ∑[0,i] = f[0] + f[1] …… + f[i]
    //       ∑[0,j] = f[0] + f[1] …… + f[j]
    //       ∑[i,j] = f[i] + f[i+1] …… + f[j] = ∑[0,j] - ∑[0,i]
    // [2,5,1,4,3,6]  k = 1 [5,1] [1,4] [1]
    public int countSubarrays(int[] nums, int k) {
        int kIdx = -1;      // 目标值索引
        int diff = 0;       // [0, i]的diff值
        Map<Integer, Integer> map = new HashMap<>();    // 统计每个前缀和的出现次数
        map.put(diff, 1);   // -1位置的diff值为0，即diff值为0有一个
        int count = 0;      // 统计满足条件的子数组个数
        for(int i = 0; i < nums.length; i++){
            if(nums[i] == k){
                // 目标值，前缀和不变，记录目标值索引
                kIdx = i;
            }else if(nums[i] > k){
                // diff = greatCount - lessCount，一个大于中位数的元素，greatCount+1相当于diff+1
                diff++;
            }else{
                // diff = greatCount - lessCount，一个小于中位数的元素，lessCount+1相当于diff-1
                diff--;
            }
            if(kIdx < 0){
                // 目标值未找到，说明是目标值之前的元素，更新哈希表这个前缀和的个数
                // 子数组的左端点
                map.put(diff, map.getOrDefault(diff, 0) + 1);
            }else{
                // 目标值已找到，说明是目标值及其之后的元素，从哈希表中查找满足条件的左端点个数
                // 子数组的右端点，查找满足条件的左端点
                count += map.getOrDefault(diff, 0) + map.getOrDefault(diff - 1, 0);
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2,3,1};
        Day230316_Num2488_CountSubWithMedian num = new Day230316_Num2488_CountSubWithMedian();
        System.out.println(num.countSubarrays(nums, 3));
    }
}
