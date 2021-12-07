// DFS 基本结构:
// 1、允许重复选择:
// dfs(变量) {
//     if(跳出搜索的条件) { ... }
//     if(符合条件) { ... }
//     dfs(跳过当前值的搜索);
//     list 中加入当前值;
//     dfs(有当前值的搜索);
//     list 中去掉当前值;
// }
// 
// 2、不允许重复选择:
// dfs(变量) {
//     if(跳出搜索的条件) { ... }
//     if(符合条件) { ... }
//     for(当前 index 到 末尾 index) {
//         list 中加入当前值;
//         dfs(有当前值的搜索);
//         list 中去掉当前值;
//     }
// }

// 允许重复选择元素的组合
// 给定一个无重复元素的正整数数组 candidates 和一个正整数 target
// 找出 candidates 中所有可以使数字和为目标数 target 的唯一组合
// candidates 中的数字可以无限制重复被选取。如果至少一个所选数字数量不同，则两种组合是唯一的
// 对于给定的输入，保证和为 target 的唯一组合数少于 150 个
// candidates = [2,3,5], target = 8 => [[2,2,2,2],[2,3,3],[3,5]]
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        dfs(candidates, target, res, ls, 0);
        return res;
    }
    public void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> ls, int idx) {
        if(idx == candidates.length) return;
        if(target == 0) {
            res.add(new ArrayList<>(ls));
            return;
        }
        dfs(candidates, target, res, ls, idx + 1); // 跳过当前值的搜索
        if(target - candidates[idx] >= 0) { // 剪枝，如果加上当前的数字结果超出目标值则不再继续搜索
            ls.add(candidates[idx]);
            dfs(candidates, target - candidates[idx], res, ls, idx); // 添加当前值的搜索
            ls.remove(ls.size() - 1); // 回溯，移除当前值，避免重复计算
        }
    }
}

// 含有重复元素集合的组合
// 给定一个可能有重复数字的整数数组 candidates 和一个目标数 target
// 找出 candidates 中所有可以使数字和为 target 的组合
// candidates 中的每个数字在每个组合中只能使用一次，解集不能包含重复的组合
// candidates = [2,5,2,1,2], target = 5 => [[1,2,2],[5]]
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        Arrays.sort(candidates); // 排序后能加快搜索速度，如果有值超过目标值则可以直接停止搜索
        dfs(candidates, target, res, ls, 0);
        return res;
    }
    public void dfs(int[] candidates, int target, List<List<Integer>> res, List<Integer> ls, int idx) {
        if(target == 0) {
            res.add(new ArrayList<>(ls));
            return;
        }
        for(int i = idx; i < candidates.length; i++) {
            if(candidates[i] > target) { // 剪枝，如果有值超过目标值则可以直接停止搜索
                break;
            }
            // 剪枝且避免重复组合，如果当前数字和前一个数字一样则表明已经进行过搜索，直接跳过
            if(i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }
            ls.add(candidates[i]);
            dfs(candidates, target - candidates[i], res, ls, i + 1);
            ls.remove(ls.size() - 1);
        }
    }
}

// 没有重复元素集合的全排列
// 给定一个不含重复数字的整数数组 nums ，返回其 所有可能的全排列
// nums = [1,2,3] => [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        boolean[] u = new boolean[nums.length]; // 记录当前数字是否被取用
        dfs(nums, res, ls, 0, u);
        return res;
    }
    public void dfs(int[] nums, List<List<Integer>> res, List<Integer> ls, int idx, boolean[] u) {
        if(idx == nums.length) {
            res.add(new ArrayList<>(ls));
            return;
        }
        for(int i = 0; i < nums.length; i++) { // 因为要获取全排列，因此从头开始遍历
            if(u[i]) {
                continue;
            }
            ls.add(nums[i]);
            u[i] = true;
            dfs(nums, res, ls, idx + 1, u);
            ls.remove(ls.size() - 1);
            u[i] = false;
        }
    }
}

// 含有重复元素集合的全排列 
// 给定一个可包含重复数字的整数集合 nums ，按任意顺序 返回它所有不重复的全排列
// -10 <= nums[i] <= 10
// nums = [1,1,2] => [[1,1,2],[1,2,1],[2,1,1]]
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ls = new ArrayList<>();
        int[] count = new int[21]; // 储存 -10 到 10，每个数字的个数
        for(int i = 0; i < nums.length; i++) {
            count[nums[i] + 10] += 1;
        }
        Arrays.sort(nums);
        dfs(res, ls, nums, 0, count);
        return res;
    }
    public void dfs(List<List<Integer>> res, List<Integer> ls, int[] nums, int idx, int[] count) {
        if(idx == nums.length) {
            res.add(new ArrayList<>(ls));
            return;
        }
        for(int i = 0; i < nums.length; i++) { // 因为要获取全排列，因此从头开始遍历
            if(count[nums[i] + 10] < 1) { // 当前数字是否已被取用完
                continue;
            }
            // 剪枝且避免重复组合，如果当前数字和前一个数字一样则表明已经进行过搜索，直接跳过
            if(i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            ls.add(nums[i]);
            count[nums[i] + 10] -= 1;
            dfs(res, ls, nums, idx + 1, count);
            ls.remove(ls.size() - 1);
            count[nums[i] + 10] += 1;
        }
    }
}

// 生成匹配的括号
// 正整数 n 代表生成括号的对数，生成所有可能的并且 有效的 括号组合
// n = 3 => ["((()))","(()())","(())()","()(())","()()()"]
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        dfs(res, sb, 0, 0, n);
        return res;
    }
    public void dfs(List<String> res, StringBuilder sb, int l, int r, int n) {
        if(r > l || r > n || l > n) { // l 代表左括号，r 代表右括号，右括号不能大于当前放入的左括号数量
            return;
        }
        if(r == n && l == n) {
            res.add(sb.toString());
            return;
        }
        sb.append("(");
        dfs(res, sb, l + 1, r, n);
        sb.deleteCharAt(sb.length() - 1);
        sb.append(")");
        dfs(res, sb, l, r + 1, n);
        sb.deleteCharAt(sb.length() - 1);
    }
}

// 分割回文子字符串
// 给定一个字符串 s ，请将 s 分割成一些子串，使每个子串都是 回文串 ，返回 s 所有可能的分割方案
// 回文串 是正着读和反着读都一样的字符串
// s = "google" => [["g","o","o","g","l","e"],["g","oo","g","l","e"],["goog","l","e"]]
class Solution {
    public String[][] partition(String s) {
        int n = s.length();
        List<List<String>> res = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        boolean[][] dp = new boolean[n][n]; // 动态规划表，记录 String 第 i 位到第 j 位是否为回文串
        for(int i = n - 1; i >= 0; i--) { // 利用动态规划记录回文串情况
            for(int j = i; j < n; j++) {
                // 1、如果两个字符相等且他们的 index 差距小于 1 则他们是回文串
                // 2、如果两个字符相等(在前面的为 i 后面的为 j)，且他们中间的字是回文串，则他们也是回文串
                if(s.charAt(i) == s.charAt(j) && (j - i <= 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                }
            }
        }
        dfs(res, ls, s, 0, dp, n);
        // 将结果放入 String array 中
        String[][] strs = new String[res.size()][];
        for (int i = 0; i < res.size(); i++) {
            strs[i] = new String[res.get(i).size()];
            for (int j = 0; j < strs[i].length; j++) {
                strs[i][j] = res.get(i).get(j);
            }
        }
        return strs;
    }
    public void dfs(List<List<String>> res, List<String> ls, String s, int idx, boolean[][] dp, int n) {
        if(idx == n) { // 搜索到达 String 尾
            res.add(new ArrayList<>(ls));
            return;
        }
        for(int i = idx; i < n; i++) {
            if(dp[idx][i]) { // 如果是回文串
                ls.add(s.substring(idx, i + 1));
                dfs(res, ls, s, i + 1, dp, n);
                ls.remove(ls.size() - 1);
            }
        }
    }
}

// 复原 IP
// 给定一个只包含数字的字符串 s ，用以表示一个 IP 地址，返回所有可能从 s 获得的 有效 IP 地址
// 有效 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔
// 例如: "0.011.255.245"、"192.168.1.312" 和 "192.168@1.1" 是 无效 IP 地址
// s = "10203040" => ["10.20.30.40","102.0.30.40","10.203.0.40"]
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> ans = new ArrayList<>();
        if(s.length() > 12 || s.length() < 4) { // 不满足最低形成合法 IP 的条件
            return ans;
        }
        List<List<String>> res = new ArrayList<>();
        List<String> ls = new ArrayList<>();
        dfs(res, ls, s, 0);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < res.size(); i++) { // 将所有符合要求的 IP 改为 String
            List<String> ss = res.get(i);
            for(int j = 0; j < 3; j++) {
                sb.append(ss.get(j));
                sb.append(".");
            }
            sb.append(res.get(i).get(3));
            ans.add(sb.toString());
            sb.delete(0, sb.length());
        }
        return ans;
    }
    public void dfs(List<List<String>> res, List<String> ls, String s, int idx) {
        if(idx == s.length() && ls.size() == 4) { // IP 只能是四个数字
            res.add(new ArrayList<>(ls));
            return;
        }
        for(int i = idx; i < s.length() && i - idx < 3; i++) {
            if(check(s.substring(idx, i + 1))) {
                ls.add(s.substring(idx, i + 1));
                dfs(res, ls, s, i + 1);
                ls.remove(ls.size() - 1);
            }
        }
    }
    public boolean check(String s) { // 检查是否是合法 IP
        if((s.charAt(0) == '0' && s.length() > 1) || (0 > Integer.parseInt(s) || Integer.parseInt(s) > 255)) {
            return false;
        }
        return true;
    }
}
