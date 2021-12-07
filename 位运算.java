// 二进制加法
// "11" + "10" => "101"
class Solution {
    public String addBinary(String a, String b) {
        StringBuffer ans = new StringBuffer();
        int n = Math.max(a.length(), b.length()), carry = 0;
        for(int i = 0; i < n; ++i) {
            carry += i < a.length() ? (a.charAt(a.length() - 1 - i) - '0') : 0;
            carry += i < b.length() ? (b.charAt(b.length() - 1 - i) - '0') : 0;
            ans.append((char)(carry % 2 + '0'));
            carry /= 2;
        }
        if(carry > 0) {
            ans.append('1');
        }
        ans.reverse();
        return ans.toString();
    }
}

// 整数除法
// 给定两个整数 a 和 b，求它们的除法的商 a / b
// 不得使用乘号 '*'、除号 '/' 以及求余符号 '%'
// -2^31 <= a, b <= 2^31 - 1
// 向下取整
class Solution {
    public int divide(int a, int b) {
        if(a == Integer.MIN_VALUE && b == -1) {
            return Integer.MAX_VALUE;
        }
        int res = 0;
        boolean flag = (a > 0) ^ (b > 0);
        a = Math.abs(a);
        b = Math.abs(b);
        for(int i = 31; i >= 0; i--) {
            if((a >>> i) - b >= 0) { // >>> 表示不带符号位右移
                a -= (b << i);
                res += (1 << i);
            }
        }
        return flag ? -res : res;
    }
}

// 前 n 个数字二进制中 1 的个数
// n = 5 => [0,1,1,2,1,2]
// 0 --> 0
// 1 --> 1
// 2 --> 10
// 3 --> 11
// 4 --> 100
// 5 --> 101
class Solution {
    public int[] countBits(int n) {
        int[] output = new int[n + 1]; // 前缀和思想
        for(int i = 1; i <= n; i++) {
            output[i] = output[i >> 1] + (i & 1);
        }
        return output;
    }
}

// 只出现一次的数字
// 除某个元素仅出现 一次 外，其余每个元素都恰出现 三次
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for(int i = 31; i >= 0; i--) {
            int count = 0;
            for(int n : nums) {
                count += (n >> i) & 1;
            }
            if(count % 3 != 0) { // 第 i 个二进制位就是数组中所有元素的第 i 个二进制位之和除以 3 的余数
                ans |= 1 << i;
            }
        }
        return ans;
    }
}

// -------

// 状态压缩

// 位运算还可做状态压缩使用，int有32位，可以存放32个状态
// 状态压缩就是用二进制 bit 位来表示每个下标的元素取或者不取，如果取则该位 bit 为 1，反之为 0
// 由于 java 的 int 有 32 位，因此最大可以储存 31 个不同元素的状态

// 所有子集
// 给定一个整数数组 nums ，数组中的元素 互不相同
// 返回该数组所有可能的子集
// 解集 不能 包含重复的子集，1 <= nums.length <= 10
// nums = [1,2,3] => [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
class Solution1 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < Math.pow(2, nums.length); i++) { // 共有 2 的 n 次方种子集
            List<Integer> ls = new ArrayList<>();
            for(int j = 0; j < nums.length; j++) {
                if((i & (1 << j)) > 0) { // 如果表示第 j 位数字的二进制为 1 则将 j 位的数字加入 list
                    ls.add(nums[j]);
                }
            }
            res.add(ls);
        }
        return res;
    }
}

class Solution2 {
    List<List<Integer>> res = new LinkedList<>();
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> temp = new LinkedList<>();
        res.add(temp);
        dfs(0, nums, temp);
        return res;
    }
    public void dfs(int index, int[] nums, List<Integer> temp) {
        if(index >= nums.length) {
            return;
        }
        for(int i = index; i < nums.length; i++) {
            temp.add(nums[i]);
            res.add(new LinkedList<>(temp));
            dfs(i + 1, nums, temp);
            temp.remove(temp.indexOf(nums[i]));
        }
    }
}

// 含有 k 个元素的组合
// 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合
// n = 4, k = 2 => [[2,4],[3,4],[2,3],[1,2],[1,3],[1,4]]
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        for(int i = 0; i < (1 << n); i++) { // 共有多少种可能性
            if(Integer.bitCount(i) == k) { // 如果 bit 是 1 的个数等于要求的个数
                for(int j = 0; j < n; j++) {
                    if((i & (1 << j)) > 0) {
                        ls.add(j + 1);
                    }
                }
            }
            if(ls.size() > 0) {
                res.add(new ArrayList<>(ls));
                ls.clear();
            }
        }
        return res;
    }
}
