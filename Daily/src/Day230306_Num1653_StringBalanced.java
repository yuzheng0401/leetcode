/**
 * @author yuzhengwu
 * @version 1.0
 * @description 给你一个字符串 s ，它仅包含字符 'a' 和 'b'​​​​ 。
 * 你可以删除 s 中任意数目的字符，使得 s 平衡 。当不存在下标对 (i,j) 满足 i < j ，且 s[i] = 'b' 的同时 s[j]= 'a' ，此时认为 s 是 平衡 的。
 * 请你返回使 s 平衡 的 最少 删除次数。
 * @date 2023/3/6 9:21 PM
 */
public class Day230306_Num1653_StringBalanced {

    public int minimumDeletions(String s) {
        int len  = s.length();
        int[] leftB = new int[len + 1], rightA = new int[len + 1];
        leftB[0] = 0;
        rightA[len] = 0;
        // a a b a b b a b
        //0 1 2 3 4 5 6 7 8
        for (int i = 1; i <= len; i++) {
            leftB[i] = leftB[i - 1] + (s.charAt(i - 1) == 'b' ? 1 : 0);
        }
        for (int i = len - 1; i >= 0; i--) {
            rightA[i] = rightA[i + 1] + (s.charAt(i) == 'a' ? 1 : 0);
        }
        int res = s.length();
        for (int i = 0; i <= len ; i++) {
            res = Math.min(leftB[i] + rightA[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3};
        Day230306_Num1653_StringBalanced num = new Day230306_Num1653_StringBalanced();
        System.out.println(num.minimumDeletions("b"));
    }
}
