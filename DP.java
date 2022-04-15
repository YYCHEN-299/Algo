// 动态规划: 各小问题之间存在重叠部分
// 1、找出边界情况
// 2、找出每种情况间的联系，列出状态转移方程

// 爬楼梯的最少成本
// 数组的每个下标作为一个阶梯，第 i 个阶梯对应着一个非负数的体力花费值 cost[i]，下标从 0 开始
// 每当爬上一个阶梯都要花费对应的体力值，一旦支付了相应的体力值，就可以选择向上爬一个阶梯或者爬两个阶梯
// 请找出达到楼层顶部的最低花费。在开始时，你可以选择从下标为 0 或 1 的元素作为初始阶梯
// cost = [10, 15, 20] => 15
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        dp[0] = dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            // 到某一步有两种选择，一种是前一节阶梯，另一种是前前一节阶梯
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[n];
    }
}

// 房屋偷盗
// 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
// 请计算 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额
// nums = [1,2,3,1] => 4
class Solution1 {
    public int rob(int[] nums) {
        int n = nums.length;
        if(n == 0) {
            return 0;
        }
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[n];
    }
}

class Solution2 {
    public int rob(int[] nums) {
        // 由于每次变动的数字只有三个，因此可以使用滚动数组
        int pre = 0, cur = 0, tmp;
        for(int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }
}

// 环形房屋偷盗
// 这个地方所有的房屋都 围成一圈 ，这意味着第一个房屋和最后一个房屋是紧挨着的
// 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警
// 请计算 在不触动警报装置的情况下 ，今晚能够偷窃到的最高金额
// nums = [2,3,2] => 3
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 1) {
            return nums[0];
        }
        int pre1 = 0, cur1 = 0, temp1;
        // 分两种情况讨论，不偷头或不偷尾
        for(int i = 0; i < nums.length - 1; i++) {
            temp1 = cur1;
            cur1 = Math.max(cur1, pre1 + nums[i]);
            pre1 = temp1;
        }
        int pre2 = 0, cur2 = 0, temp2;
        for(int i = 1; i < nums.length; i++) {
            temp2 = cur2;
            cur2 = Math.max(cur2, pre2 + nums[i]);
            pre2 = temp2;
        }
        return Math.max(cur1, cur2);
    }
}

// 粉刷房子
// 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种
// 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同
// costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推
// 请计算出粉刷完所有房子最少的花费成本
// costs = [[17,2,17],[16,16,5],[14,3,19]] => 10
class Solution {
    public int minCost(int[][] costs) {
        int r = costs[0][0], b = costs[0][1], g = costs[0][2];
        int tempR, tempB, tempG;
        for(int i = 1; i < costs.length; i++) {
            tempR = r;
            tempB = b;
            tempG = g;
            r = Math.min(tempB, tempG) + costs[i][0];
            b = Math.min(tempR, tempG) + costs[i][1];
            g = Math.min(tempR, tempB) + costs[i][2];
        }
        return Math.min(Math.min(r, b), g);
    }
}

// 翻转字符
// 如果一个由 '0' 和 '1' 组成的字符串，是以一些 '0'（可能没有 '0'
// 后面跟着一些 '1'（也可能没有 '1'）的形式组成的，那么该字符串是 单调递增 的
// 我们可以将任何 '0' 翻转为 '1' 或者将 '1' 翻转为 '0'
// 返回使 s 单调递增 的最小翻转次数
// s = "00110" => 1
class Solution {
    public int minFlipsMonoIncr(String s) {
        int zero = 0, one = 0;
        for(char c : s.toCharArray()) {
            if(c == '0') {
                // 如果当前数字是 0，要让它变成 1 需要前面都是 0 或前面都是 1
                // 因此比较两种情况哪种情况需要的转换次数少，再加一次当前数字的转换次数
                one = Math.min(zero, one) + 1;
                // 如果当前数字是 0，要让它依旧是 0 则不需要任何操作
            } else {
                // 如果当前数字是 1，要让它依旧是 1 需要前面都是 0 或前面都是 1
                // 因此比较两种情况哪种情况需要的转换次数少
                one = Math.min(zero, one);
                // 如果当前数字是 1，要让它变成 0，需要对它翻转一次，前面的数字则不需要任何操作
                zero = zero + 1;
            }
        }
        return Math.min(zero, one);
    }
}

// 最长斐波那契数列
// 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
// 1、n >= 3
// 2、对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
// 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如不存在，返回  0 
// arr = [1,2,3,4,5,6,7,8] => 5 ([1,2,3,5,8])
class Solution {
    public int lenLongestFibSubseq(int[] arr) {
        Map<Integer, Integer> hm = new HashMap<>();
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            hm.put(arr[i], i); // 放入每个数字的 index
        }
        int[][] dp = new int[n][n]; // a 到 b 中斐波那契的长度，a > b
        int res = 0;
        for(int i = 2; i < n; i++) {
            for(int j = i - 1; j >= 0; j--) {
                // 剪枝，此时比 j 更小的值已经不能成为i，j之前的斐波那契数列的项
                // 比如 8 5 之前有3，但 8 3 之前没有 5
                if(arr[i] - arr[j] >= arr[j]) {
                    break;
                }
                if(hm.containsKey(arr[i] - arr[j])) {
                    // 斐波那契最小长度是 2
                    dp[i][j] = Math.max(2, dp[j][hm.get(arr[i] - arr[j])]) + 1;
                    res = Math.max(res, dp[i][j]); // 更新最长的结果
                }
            }
        }
        return res;
    }
}

// 最少回文分割
// 给定一个字符串 s，请将 s 分割成一些子串，使每个子串都是回文串
// 返回符合要求的 最少分割次数
// s = "aab" => 1 (["aa","b"])
class Solution {
    public int minCut(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        // 标记回文串 index，记录 i 到 j 是否是回文串
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                // 1、如果两个字符相等且他们的 index 差距小于 1 则他们是回文串
                // 2、如果两个字符相等(在前面的为 i 后面的为 j)，且他们中间的字是回文串，则他们也是回文串
                if(s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        int[] count = new int[n];
        for(int i = 0; i < n; i++) {
            if(dp[0][i]) {
                count[i] = 0;
            } else {
                count[i] = i; // index 为 i 长度为 i + 1，最多需要切割 i 次
                for(int j = 1; j <= i; j++) {
                    if(dp[j][i]) { // 如果 j 到 i 是回文串
                        // 当前位置需要分割的最小次数是 0 到 j - 1 需要分割的次数 + 1 (当前位置也需要割一次)
                        count[i] = Math.min(count[i], count[j - 1] + 1);
                    }
                }
            }
        }
        return count[n - 1];
    }
}

// 最长公共子序列
// 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在，返回 0
// 一个字符串的 子序列 是指这样一个新的字符串:
// 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
// text1 = "abcde", text2 = "ace" => 3
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        // dp 边界情况: 
        // 当 i = 0 时，text1 是空字符串，和任何字符串的最长公共子序列的长度都是 0，因此对任意 j，有 dp[0][j] = 0
        // 当 j = 0 时，text2 是空字符串，同理，dp[i][0] = 0
        for(int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for(int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                // 当 text1[i] = text2[j] 时:
                // 最长公共子序列 = text1[0 : i − 1] 和 text2[0 : j − 1] 的最长公共子序列 + 1 (当前的公共字符)
                // 当 text1[i] != text2[j] 时，有两种情况:
                // 1、text1[0 : i - 1] 和 text2[0 : j] 的最长公共子序列
                // 2、text1[0 : i] 和 text2[0 : j - 1] 的最长公共子序列
                if(c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

// 字符串交织
// 给定三个字符串 s1、s2、s3，请判断 s3 能不能由 s1 和 s2 交织（交错） 组成
// 两个字符串 s 和 t 交织 的定义与过程如下，其中每个字符串都会被分割成若干 非空 子字符串:
// s = s1 + s2 + ... + sn
// t = t1 + t2 + ... + tm
// |n - m| <= 1
// 交织 是 s1 + t1 + s2 + t2 + s3 + t3 + ... 或者 t1 + s1 + t2 + s2 + t3 + s3 + ...
// s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac" => true
// s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc" => false
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        int a = s1.length(), b = s2.length(), c = s3.length();
        if(a + b != c) { // 长度不对首先排除
            return false;
        }
        boolean[][] dp = new boolean[a + 1][b + 1];
        dp[0][0] = true; // 两个字符串都为空时，可以交织形成第三个空的字符串
        // 记录没有 s2 的情况下，s1 能组成 s3 的头部多少个字符
        for(int i = 0; i < a; i++) {
            if(s1.charAt(i) == s3.charAt(i)) {	
                dp[i + 1][0] = true;
            } else {
                break;
            }
        }
        // 记录没有 s1 的情况下，s2 能组成 s3 的头部多少个字符
        for(int j = 0; j < b; j++) {
            if(s2.charAt(j) == s3.charAt(j)) {
                dp[0][j + 1] = true;
            } else {
                break;
            }
        }
        // f(i, j) 为 true 表示 s1[0, i] 和 s2[0, j] 能交织成 s3[0, i + j + 1]
        for(int i = 0; i < a; i++) {
            for(int j = 0; j < b; j++) {
                dp[i + 1][j + 1] = ((s1.charAt(i) == s3.charAt(i + j + 1)) && dp[i][j + 1]) || 
                                             ((s2.charAt(j) == s3.charAt(i + j + 1)) && dp[i + 1][j]);
            }
        }
        return dp[a][b];
    }
}

// 子序列的数目
// 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
// 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串
// 例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是
// s = "rabbbit", t = "rabbit" => 3
class Solution {
    public int numDistinct(String s, String t) {
        // dp 记录 s[i : ] 的子序列中 t[j : ] 出现的次数
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for(int i = 0; i <= s.length(); i++) { // 边界情况，如果 t 是空字串，则 s 中有一个子序列是 t
            dp[i][t.length()] = 1;
        }
        for(int i = s.length() - 1; i >= 0; i--) {
            for(int j = t.length() - 1; j >= 0; j--) {
                // 如果两者在某位置上字符相等:
                // s[i :] 子序列中 t[j : ] 的数目 = s[i + 1 : ] 子序列中 t[j + 1 : ] 的数目 + s[i + 1 : ] 子序列中 t[j : ] 的数目
                //                                              将当前字符看作子序列的一部分            将当前字符不看做子序列的一部分
                if(s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j + 1] + dp[i + 1][j];
                } else {
                    dp[i][j] = dp[i + 1][j]; // 当前字符不是子序列的一部分
                }
            }
        }
        return dp[0][0];
    }
}

// ---------

// 背包问题
// 此类问题的一般描述为:
// 能否选择若干物品，使它们刚好放满一个容量为 t 的背包
// 1、若每种物品只有一个，则为 0 - 1 背包问题
// 2、若每个物品的个数有限，则为多重背包问题
// 3、若每个物品的个数无限，则为完全背包问题
// 
// 设 f(i, j) 表示能否从前 i 个物品 (物品编号为 0 ~ i - 1) 中选择若干物品放满容量为 j 的背包
// 对于 f(i, j) 存在两个选择，第一个选择是将标号为 i - 1 的物品放入背包
// 如果能从前 i - 1 个物品中选择若干物品放满容量为 j - nums[i - 1] 的背包
// (即 f(i - 1, j - nums[i - 1]) 为 true) 那么 f(i, j) 为 true
// 另一个选择是不把标号为 i - 1 的物品放入背包，如果能从前 i - 1 个物品中选择若干物品放满容量为 j 的背包
// (即 f(i - 1, j) 为 true)，那么 f(i, j) 为 true
// => f(i, j) = f(i - 1, j - nums[i - 1]) || f(i - 1, j)
// 边界条件: 
// 当 j 等于 0 时，即背包容量为空，只要不选择物品就可以，所以 f(i, 0) 为 true
// 当 i 等于 0 时，即物品数量为 0，那么除了空背包都无法装满，所以当 j 大于 0 时，f(0, j) 为 fasle

// ---------------

// 0 - 1 背包问题

// 分割等和子集
// 给定一个非空的正整数数组 nums ，请判断能否将这些数字分成元素和相等的两部分
// nums = [1,5,11,5] => true ([1, 5, 5] 和 [11])
class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 == 1) { // 如果和为奇数，说明无法分割成两部分
            return false;
        }
        sum /= 2; // 目标背包的大小为和的一半
        boolean[][] dp = new boolean[nums.length + 1][sum + 1];
        dp[0][0] = true; // 边界条件，当背包为空时，不选择物品即可达到要求
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 0; j <= sum; j++) {
                dp[i][j] = dp[i - 1][j]; // 不把该物品放入背包是否能达到要求
                if (!dp[i][j] && j >= nums[i - 1]) {
                    // 把该物品放入背包，剩余的背包容量是否能被填满
                    dp[i][j] = dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][sum];
    }
}

// 加减的目标值
// 给定一个正整数数组 nums 和一个整数 target
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式:
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1"
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目
// nums = [1,1,1,1,1], target = 3 => 5
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        // 需要目标值为 target，数组和为 target 时多出的值如果可以相互抵消则可以构造出目标值
        // 此时问题转换为: 能否将数组和为 target 时多出的值分成元素和相等的两部分
        int t = sum - target;
        // 如果 nums 的和小于 target 或需要消去的目标值不是双数则无法构造
        if(t < 0 || t % 2 == 1) {
            return 0;
        }
        t /= 2;
        int[][] dp = new int[nums.length + 1][t + 1];
        dp[0][0] = 1;
        for(int i = 1; i <= nums.length; i++) {
            for(int j = 0; j <= t; j++) {
                dp[i][j] = dp[i - 1][j]; // 不把该物品放入背包，有几种方法填满
                if(j >= nums[i - 1]) {
                    // 把该物品放入背包，剩余的背包容量能被几种方法填满
                    dp[i][j] += dp[i - 1][j - nums[i - 1]];
                }
            }
        }
        return dp[nums.length][t];
    }
}

// --------------

// 完全背包问题

// 最少的硬币数目
// 给定不同面额的硬币 coins 和一个总金额 amount
// 编写一个函数来计算可以凑成总金额所需的最少的硬币个数
// 如果没有任何一种硬币组合能组成总金额，返回 -1
// 可以认为每种硬币的数量是无限的
// coins = [1, 2, 5], amount = 11 => 3 (5 + 5 + 1 = 11)
public class Solution {
    public int coinChange(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[max]; // dp 存放组成金额 i 所需要的最少硬币数量
        Arrays.fill(dp, max); // 硬币最面值为 1，因此先假设最大硬币数量 = 金额 + 1
        dp[0] = 0; // 边界条件，当金额为 0 时不需要任何硬币
        for(int i = 1; i <= amount; i++) { // 遍历每一个金额
            for(int j = 0; j < coins.length; j++) {
                if(coins[j] <= i) { // 取的硬币要小于当前需要的金额
                    // 计算取了当前硬币后剩余的金额所需要的硬币
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        // 最后检查组成金额 amount 所需的最少硬币数量是否被修改
        // 如果没有被修改则 = amount + 1 > amount，说明没有一种组合能组成规定金额
        return dp[amount] > amount ? -1 : dp[amount];
    }
}

// 排列的数目
// 给定一个由 不同 正整数组成的数组 nums ，和一个目标整数 target
// 请从 nums 中找出并返回总和为 target 的元素组合的个数
// 数组中的数字可以在一次排列中出现任意次，但是顺序不同的序列被视作不同的组合。
// nums = [1,2,3], target = 4 => 7
class Solution {
    public int combinationSum4(int[] nums, int target) {
        int max = target + 1;
        int[] dp = new int[max]; // dp 存放组成 i 的方案数量
        dp[0] = 1; // 边界，当目标值为 0 时，什么都不取，只有一种方案
        for(int i = 1; i < max; i++) {
            for(int j = 0; j < nums.length; j++) {
                if(i >= nums[j]) {
                    // 如果当前可取的数字小于当前目标值
                    // 则计算取当前数字后有几种方案能组成剩下的值
                    // 加总每一种情况即为组成当前目标值的方案数
                    dp[i] += dp[i - nums[j]];
                }
            }
        }
        return dp[target];
    }
}
