// 滑动窗口

// 1、设立 left 和 right 变量，建立滑动窗口
// 2、right 进行自增，扩大窗口，直到窗口内的元素满足要求
// 3、right 停止自增，此时 left 自增，缩小窗口，当窗口内的元素不满足题目要求时，回到2
// 4、right 到达尾部时，结束循环

// -------------

// 窗口大小不固定

// 和大于等于 target 的最短子数组
// 给定一个含有 n 个正整数的数组和一个正整数 target
// 找出该数组中满足其和 ≥ target 的长度最小的
// 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度
// 如果不存在符合条件的子数组，返回 0
// target = 7, nums = [2,3,1,2,4,3] => 2 (子数组 [4,3] 是长度最小的子数组)
class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int len = Integer.MAX_VALUE;
        int l = 0, r = 0, n = nums.length;
        int sum = 0;
        while(r < n) {
            sum += nums[r];
            while(sum >= target) {
                len = Math.min(len, r - l + 1);
                sum -= nums[l];
                l++;
            }
            r++;
        }
        return len == Integer.MAX_VALUE ? 0 : len;
    }
}

// 乘积小于 K 的子数组
// 请找出数组内乘积小于 k 的连续的子数组的个数
// nums = [10,5,2,6], k = 100 => 8
// (乘积小于 100 的子数组分别为: [10], [5], [2], [6], [10,5], [5,2], [2,6], [5,2,6])
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        int res = 0;
        int l = 0, r = 0, n = nums.length;
        long count = 1;
        while(r < n) {
            count *= nums[r];
            while(count >= k && r >= l) { // 左指针可能不会向右收缩到最小子数组，只需要小于 k 就会跳出循环
                count /= nums[l];
                l++;
            }
            // 左指针可能不会向右收缩到最小子数组，如 [5,2,6] 中包含 [5,2,6] [2,6] [6] 三个结果，因此加总的个数为左右之差
            res += r - l + 1;
            r++;
        }
        return res;
    }
}

// 不含重复字符的最长子字符串
// 找出不含有重复字符的 最长连续子字符串 的长度
// s = "abcabcbb" => 3 (无重复字符的最长子字符串是 "abc")
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if(n <= 1) return n;
        int res = 1, l = 0, r = 0, key;
        int[] dict = new int[128];
        while(r < n) {
            key = s.charAt(r);
            l = Math.max(l, dict[key]); // 前面没有该 char 的起始位置
            res = Math.max(res, r - l + 1); // 最长连续字串
            dict[key] = r + 1; // 放入更新的没有该 char 的起始位置
            r++; // 右指针右移
        }
        return res;
    }
}

// -----------

// 窗口大小固定

// 字符串中的变位词
// 给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的某个变位词
// 换句话说，第一个字符串的排列之一是第二个字符串的 子串
// s1 = "ab" s2 = "eidbaooo" => True (s2 包含 s1 的排列之一 "ba")
class Solution {
    public boolean checkInclusion(String s1, String s2) {
        if(s1.length() > s2.length()) return false;
        int[] s1count = new int[26];
        int[] s2count = new int[26];
        for(int i = 0; i < s1.length(); i++) { // 先遍历 s1
            s1count[s1.charAt(i) - 'a'] += 1;
            s2count[s2.charAt(i) - 'a'] += 1;
        }
        if(Arrays.equals(s1count, s2count)) return true;
        for(int i = s1.length(); i < s2.length(); i++) { // 固定窗口大小为 s1 的长度，每次右移一个单词
            s2count[s2.charAt(i) - 'a'] += 1; // 加入单词到窗口中
            s2count[s2.charAt(i - s1.length()) - 'a'] -= 1; // 从窗口中剔除单词
            if(Arrays.equals(s1count, s2count)) return true;
        }
        return false;
    }
}

// -----

// 双指针

// 有效的回文
// 验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写
class Solution {
    public boolean isPalindrome(String s) {
        char[] ch = s.toCharArray();
        int n = ch.length;
        if(n <= 1) return true;
        int l = 0, r = n - 1;
        while(l < r) {
            // 左侧遇到非英文字母和非数字的跳过
            while(l < r && !(ch[l] >= 'a' && ch[l] <= 'z' || 
                    ch[l] >= 'A' && ch[l] <= 'Z' || ch[l] >= '0' && ch[l] <= '9')) {
                l++;
            }
            // 右侧遇到非英文字母和非数字的跳过
            while(l < r && !(ch[r] >= 'a' && ch[r] <= 'z' || 
                    ch[r] >= 'A' && ch[r] <= 'Z' || ch[r] >= '0' && ch[r] <= '9')) {
                r--;
            }
            //大写转小写
            if(ch[l] >= 'a' && ch[l] <= 'z') {
                ch[l] -= 32;
            }
            //大写转小写
            if(ch[r] >= 'a' && ch[r] <= 'z') {
                ch[r] -= 32;
            }
            if(ch[l] == ch[r]) {
                l++;
                r--;
            } else {
                return false;
            }
        }
        return true;
    }
}

// 回文子字符串的个数
// 计算字符串中有多少个回文子字符串
// s = "abc" => 3 (三个回文子串: "a", "b", "c")
// s = "aaa" => 6 ("a", "a", "a", "aa", "aa", "aaa")
class Solution {
    public int countSubstrings(String s) {
        int len = s.length();
        if(len <= 1) {
            return len;
        }
        int res = 0;
        for(int i = 0; i < 2 * len - 1; i++) {
            // 枚举回文中心。l 和 r 初始指向相同，r 先右移，l 再右移，确保遍历所有中心 (中心可能是一个单词或两个)
            int l = i / 2, r = i / 2 + i % 2;
            while(l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
                r++;
                l--;
                res++;
            }
        }
        return res;
    }
}
