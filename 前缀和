// 哈希表 + 前缀和思想

// 和为 k 的子数组
// 数组中和为 k 的连续子数组的个数
// nums = [1,2,3], k = 3 => 2
class Solution {
    public int subarraySum(int[] nums, int k) {
        int pre_sum = 0; // 前缀和
        int res = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // 初始化，当前缀和刚好为 k 时直接取用
        for(int i : nums) {
            pre_sum += i;
            res += map.getOrDefault(pre_sum - k, 0); // 目前的前缀和去掉前面的前缀和等于 k
            map.put(pre_sum, map.getOrDefault(pre_sum, 0) + 1); // 将目前遍历的 0-i 的前缀和放入 hashmap 中，前缀和可能重复，因此记录数量
        }
        return res;
    }
}

// 0 和 1 个数相同的子数组
// 找到含有相同数量的 0 和 1 的最长连续子数组，并返回该子数组的长度
class Solution {
    public int findMaxLength(int[] nums) {
        int res = 0;
        int pre_sum = 0; // 前缀和
        HashMap<Integer, Integer> hm = new HashMap<>();
        hm.put(0, -1); // 
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == 0) {
                pre_sum--; // 将 0 看作 -1
            } else {
                pre_sum++;
            }
            if(hm.containsKey(pre_sum)) { // 假设目前有 x 个多出来的 1，则需要在前面找到同样数量的 1 并去掉
                int idx = hm.get(pre_sum); // 0 到 idx 是有 x 个 1 的子数组，需要去掉
                res = Math.max(res, i - idx); // 去掉后的长度
            } else {
                hm.put(pre_sum, i); // 记录当前 index
            }
        }
        return res;
    }
}

// ==========================

// 二维的前缀和

// 二维子矩阵的和
// 计算其子矩形范围内元素的总和，该子矩阵的左上角为 (row1, col1) ，右下角为 (row2, col2)
class NumMatrix {
    int[][] mtx;
    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        mtx = new int[m + 1][n + 1];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                mtx[i + 1][j + 1] = mtx[i][j + 1] + mtx[i + 1][j] - mtx[i][j] + matrix[i][j];
                // (0, 0) 到 (i, j) 的和 = (0, 0)(i - 1, j) + (0, 0)(i, j - 1) - 
                                           (0, 0)(i - 1, j - 1) (去掉前面两块区域重叠的地方，重复加) + (i, j) 的值
            }
        }
    }
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return mtx[row2 + 1][col2 + 1] - mtx[row1][col2 + 1] - 
                            mtx[row2 + 1][col1] + mtx[row1][col1];
        // (row1, col1)(row2, col2) = (0, 0)(row2, col2) - (0, 0)(row1 - 1, col2) - 
                                      (0, 0)(row2, col1 - 1) - (0, 0)(row1 - 1, col1 - 1) (补回前面两块区域重叠的地方，重复减)
    }
}

