/**
 * @author yuzhengwu
 * @version 1.0
 * @description n 座城市和一些连接这些城市的道路 roads 共同组成一个基础设施网络。每个 roads[i] = [ai, bi] 都表示在城市 ai 和 bi 之间有一条双向道路。
 * 两座不同城市构成的 城市对 的 网络秩 定义为：与这两座城市 直接 相连的道路总数。如果存在一条道路直接连接这两座城市，则这条道路只计算 一次 。
 * 整个基础设施网络的 最大网络秩 是所有不同城市对中的 最大网络秩 。
 * 给你整数 n 和数组 roads，返回整个基础设施网络的 最大网络秩 。
 * @date 2023/3/15 9:52 PM
 */
public class Day230315_Num1615_MaximalNetwork {

    public int maximalNetworkRank(int n, int[][] roads) {
        int[] count = new int[n];
        boolean[][] connected = new boolean[n][n];
        for (int i = 0; i < roads.length; i++) {
            count[roads[i][0]]++;
            count[roads[i][1]]++;
            connected[roads[i][0]][roads[i][1]] = true;
            connected[roads[i][1]][roads[i][0]] = true;
        }
        int max = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                max = Math.max(max, count[i] + count[j] - (connected[i][j] ? 1 : 0));
            }
        }
        return max;
    }
}
