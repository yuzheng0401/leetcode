import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * 1 <= n <= 45
 * 解：f(x) 表示爬到第 x 级台阶的方案数，考虑最后一步可能跨了一级台阶，也可能跨了两级台阶 f(x)=f(x−1)+f(x−2)
 */
public class Num70_ClimbingStairs {

    // 由于f(x)只和 f(x−1)与 f(x−2)有关，用「滚动数组思想」把空间复杂度优化成 O(1)
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    // 直接 return climbStairs(n - 1) + climbStairs(n - 2) 超过时间限制
    public int climbStairsPlanA(int n) {
        if (n <= 1) {
            return 1;
        }
        return climbStairsPlanA(n - 1) + climbStairsPlanA(n - 2);
    }

    // 用一个map存储已经计算过的值 时间0ms 100% 空间 38.5m 18.91%
        private Map<Integer, Integer> map = new HashMap<>();
    public int climbStairsPlanB(int n) {
        if (n <= 1) {
            return 1;
        }
        if (map.containsKey(n)) {
            return map.get(n);
        }
        int res =  climbStairsPlanB(n - 1) + climbStairsPlanB(n - 2);
        map.put(n, res);
        return res;
    }

    public static void main(String[] args) {

    }
}
