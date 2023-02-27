/**
 * @author yuzhengwu
 * 给你一个整数数组 nums，每次 操作 会从中选择一个元素并 将该元素的值减少 1。
 *
 * 如果符合下列情况之一，则数组 A 就是 锯齿数组：
 *
 * 每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] > ...
 * 或者，每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] < ...
 * 返回将数组 nums 转换为锯齿数组所需的最小操作次数。
 * @version 1.0
 * @description
 * @date 2023/2/27 8:06 PM
 */
public class Day230227_Num1144_ArrayZigzag {

    // https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/solutions/2135375/mei-you-si-lu-yi-bu-bu-ti-shi-ni-si-kao-cm0h2/
    // 情况1：每个偶数索引对应的元素都大于相邻的元素，即 A[0] > A[1] < A[2] > A[3] < A[4] >
    //      证明A[0,2,4]不需要减小，A[0]如果减小，说明A[0]本身满足条件，只需要减少A[1,3,5……]
    // 情况2：每个奇数索引对应的元素都大于相邻的元素，即 A[0] < A[1] > A[2] < A[3] > A[4] <
    //      证明A[1,3,5]不需要减小，A[1]如果减小，说明A[1]本身满足条件，只需要减少A[0,2,4……]
    public int movesToMakeZigzag(int[] nums) {
        int[] s = new int[2];
        for (int i = 0, n = nums.length; i < n; ++i) {
            int left = i > 0 ? nums[i - 1] : Integer.MAX_VALUE;
            int right = i < n - 1 ? nums[i + 1] : Integer.MAX_VALUE;
            s[i % 2] += Math.max(nums[i] - Math.min(left, right) + 1, 0);
        }
        return Math.min(s[0], s[1]);
    }

    // 第一思路：由于题目只允许减少，分类讨论，分别对奇数偶数位置的元素进行处理，然后取最小值
    public int movesToMakeZigzagPlanA(int[] nums) {
        int resOdd = 0, resEven = 0;
        int[] numsCopy = nums.clone();
        for (int i = 0; i < nums.length; i = i + 2) {
            int left = i - 1 >= 0 ? numsCopy[i - 1] : Integer.MIN_VALUE;
            int right = i + 1 < nums.length ? numsCopy[i + 1] : Integer.MIN_VALUE;

            if (nums[i] <= left) {
                resOdd += left - nums[i] + 1;
                numsCopy[i - 1] = nums[i] - 1;
            }
            if (nums[i] <= right) {
                resOdd += right - nums[i] + 1;
                numsCopy[i + 1] = nums[i] - 1;
            }
        }
        int[] numsCopy1 = nums.clone();
        for (int i = 1; i < nums.length; i = i + 2) {
            int left = i - 1 >= 0 ? numsCopy1[i - 1] : Integer.MIN_VALUE;
            int right = i + 1 < nums.length ? numsCopy1[i + 1] : Integer.MIN_VALUE;

            if (nums[i] <= left) {
                resEven += left- nums[i] + 1;
                numsCopy1[i - 1] = nums[i] - 1;
            }
            if (nums[i] <= right) {
                resEven += right - nums[i] + 1;
                numsCopy1[i + 1] = nums[i] - 1;
            }
        }

        return Math.min(resEven, resOdd);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Day230227_Num1144_ArrayZigzag num = new Day230227_Num1144_ArrayZigzag();
        System.out.println(num.movesToMakeZigzag(nums));
    }
}
