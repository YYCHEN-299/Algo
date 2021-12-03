/**
 * 前缀树 是一种树形数据结构，用于高效地存储和检索字符串数据集中的键
 * 这一数据结构有相当多的应用情景，例如自动补完和拼写检查
 */

// 实现前缀树
// Trie() 初始化前缀树对象
// void insert(String word) 向前缀树中插入字符串 word
// boolean search(String word) 如果字符串 word 在前缀树中，返回 true；否则，返回 false
// boolean startsWith(String prefix) 如果之前已经插入的字符串 word 的前缀之一为 prefix ，返回 true ；否则，返回 false
class Trie {
    class TreeNode { // 自定义树节点
        TreeNode[] next;
        boolean isEnd;
        public TreeNode() {
            next = new TreeNode[26]; // 当前节点的下一个结点，有26种可能性(26个英文字母)
            isEnd = false; // 当前结点是否是一个单词的最后一个字母
        }
    }
    TreeNode root;
    /** Initialize your data structure here. */
    public Trie() {
        root = new TreeNode(); // 根节点中的 next 保存每个单词的第一个字母
    }
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TreeNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            if(cur.next[word.charAt(i) - 'a'] == null) {
                cur.next[word.charAt(i) - 'a'] = new TreeNode();
            }
            cur = cur.next[word.charAt(i) - 'a'];
        }
        cur.isEnd = true;
    }
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TreeNode cur = root;
        for(int i = 0; i < word.length(); i++) {
            if(cur.next[word.charAt(i) - 'a'] == null) {
                return false;
            }
            cur = cur.next[word.charAt(i) - 'a'];
        }
        return cur.isEnd;
    }
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TreeNode cur = root;
        for(int i = 0; i < prefix.length(); i++) {
            if(cur.next[prefix.charAt(i) - 'a'] == null) {
                return false;
            }
            cur = cur.next[prefix.charAt(i) - 'a'];
        }
        return true;
    }
}

// 神奇的字典
// 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同
// 请判定能否只将一个单词中一个字母换成另一个字母，使得所形成的新单词存在于已构建的神奇字典中
// MagicDictionary() 初始化对象
// void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
// bool search(String searchWord) 给定一个字符串 searchWord ，判定是否符合上述条件
// 输入:
// inputs = ["MagicDictionary", "buildDict", "search", "search", "search", "search"]
// inputs = [[], [["hello", "leetcode"]], ["hello"], ["hhllo"], ["hell"], ["leetcoded"]]
// 输出: [null, null, false, true, false, false]
class MagicDictionary {
    HashSet<String> st;
    HashMap<String, Integer> mp;
    /** Initialize your data structure here. */
    public MagicDictionary() {
        st = new HashSet<>();
        mp = new HashMap<>();
    }
    public void buildDict(String[] dictionary) {
        for(int i = 0; i < dictionary.length; i++) {
            st.add(dictionary[i]);
            for(String str : getNb(dictionary[i])) {
                mp.put(str, mp.getOrDefault(str, 0) + 1);
            }
        }
    }
    private String[] getNb(String str) {
        String[] res = new String[str.length()];
        StringBuilder sb = new StringBuilder(str);
        for(int i = 0; i < str.length(); i++) {
            char ch = sb.charAt(i);
            sb.setCharAt(i, '*');
            res[i] = sb.toString();
            sb.setCharAt(i, ch);
        }
        return res;
    }
    public boolean search(String searchWord) {
        for(String str : getNb(searchWord)) {
            if(mp.getOrDefault(str, 0) > 1 || (mp.getOrDefault(str, 0) == 1 && !st.contains(searchWord))) {
                return true;
            }
        }
        return false;
    }
}

// 最短的单词编码
// 单词数组 words 的 有效编码 由任意助记字符串 s 和下标数组 indices 组成，且满足：
// words.length == indices.length
// 助记字符串 s 以 '#' 字符结尾
// 对于每个下标 indices[i] ，s 的一个从 indices[i] 开始、到下一个 '#' 字符结束（不包括 '#'）的 子字符串 恰好与 words[i] 相等
// 给定一个单词数组 words ，返回成功对 words 进行编码的最小助记字符串 s 的长度
// words = ["time", "me", "bell"] => 10
// 一组有效编码为 s = "time#bell#" 和 indices = [0, 2, 5]
// words[0] = "time" ，s 开始于 indices[0] = 0 到下一个 '#' 结束的子字符串
// words[1] = "me" ，s 开始于 indices[1] = 2 到下一个 '#' 结束的子字符串
// words[2] = "bell" ，s 开始于 indices[2] = 5 到下一个 '#' 结束的子字符串
class Solution1 {
    class TreeNode { // 后缀树(倒着的前缀树)
        TreeNode[] children;
        int count; // 记录当前 char 的儿子个数
        TreeNode() {
            children = new TreeNode[26];
            count = 0;
        }
        public TreeNode getOrSet(char c) {
            if(children[c - 'a'] == null) {
                children[c - 'a'] = new TreeNode();
                count++;
            }
            return children[c - 'a'];
        }
    }
    public int minimumLengthEncoding(String[] words) {
        TreeNode trie = new TreeNode();
        Map<TreeNode, Integer> nodes = new HashMap<>();
        for(int i = 0; i < words.length; i++) { // 遍历 words
            String word = words[i];
            TreeNode cur = trie;
            for(int j = word.length() - 1; j >= 0; j--) { // 倒序遍历每个 char
                cur = cur.getOrSet(word.charAt(j)); // 建立后缀树(倒着的前缀树)
            }
            nodes.put(cur, i); // 将每个单词的首位 char 放入表中，并保存 index
        }
        int ans = 0;
        for(TreeNode node : nodes.keySet()) {
            // 如果该单词的首位 char 的儿子个数不为 0，则说明存在以该单词结尾的更长的单词
            if(node.count == 0) {
                ans += words[nodes.get(node)].length() + 1; // 长度多一位 '#'
            }
        }
        return ans;
    }
}

class Solution2 {
    public int minimumLengthEncoding(String[] words) {
        Set<String> strs = new HashSet<>();
        for(int i = 0; i < words.length; i++) {
            strs.add(words[i]);
        }
        Set<String> hs = new HashSet<>();
        for(String s : strs) {
            for(int j = s.length() - 1; j > 0; j--) {
                hs.add(s.substring(j, s.length()));
            }
        }
        int res = 0;
        for(String s : strs) {
            if(!hs.contains(s)) {
                res += 1 + s.length();
            }
        }
        return res;
    }
}

// 单词之和
// MapSum() 初始化 MapSum 对象
// void insert(String key, int val) 插入 key-val 键值对，字符串表示键 key ，整数表示值 val
// 如果键 key 已经存在，那么原来的键值对将被替代成新的键值对
// int sum(string prefix) 返回所有以该前缀 prefix 开头的键 key 的值的总和
class MapSum {
    class TreeNode {
        TreeNode[] next;
        boolean isEnd;
        int val;
        public TreeNode() {
            next = new TreeNode[26];
            isEnd = false;
            val = 0;
        }
    }
    TreeNode tn;
    public MapSum() {
        tn = new TreeNode();
    }
    public void insert(String key, int val) {
        TreeNode t = tn;
        for(int i = 0; i < key.length(); i++) {
            if(t.next[key.charAt(i) - 'a'] == null) {
                t.next[key.charAt(i) - 'a'] = new TreeNode();
            }
            t = t.next[key.charAt(i) - 'a'];
        }
        t.val = val;
        t.isEnd = true;
    }
    public int sum(String prefix) {
        TreeNode t = tn;
        for(int i = 0; i < prefix.length(); i++) {
            if(t.next[prefix.charAt(i) - 'a'] == null) {
                return 0;
            }
            t = t.next[prefix.charAt(i) - 'a'];
        }
        return dfs(t, 0);
    }
    public int dfs(TreeNode t, int count) {
        if(t.isEnd) {
            count += t.val;
        }
        for(int i = 0; i < 26; i++) {
            if(t.next[i] != null) {
                count = dfs(t.next[i], count);
            }
        }
        return count;
    }
}

// 最大的异或
// 给定一个整数数组 nums ，返回 nums[i] XOR nums[j] 的最大运算结果，其中 0 ≤ i ≤ j < n
// nums = [3,10,5,25,2,8] => 28 (最大运算结果是 5 XOR 25 = 28)
class Solution {
    class TreeNode {
        // 左子树指向表示 0 的子节点
        TreeNode left = null;
        // 右子树指向表示 1 的子节点
        TreeNode right = null;
    }
    TreeNode root = new TreeNode();
    // int 32 有 31 位，最多只需要右移 30 位
    static final int HIGH_BIT = 30;
    public int findMaximumXOR(int[] nums) {
        int n = nums.length;
        int x = 0;
        for(int i = 1; i < n; i++) {
            add(nums[i - 1]);
            x = Math.max(x, check(nums[i]));
        }
        return x;
    }
    public void add(int num) {
        TreeNode cur = root;
        for(int k = HIGH_BIT; k >= 0; k--) { // 从最高位遍历到最低位
            int bit = (num >> k) & 1;
            if(bit == 0) {
                if(cur.left == null) {
                    cur.left = new TreeNode();
                }
                cur = cur.left;
            } else {
                if(cur.right == null) {
                    cur.right = new TreeNode();
                }
                cur = cur.right;
            }
        }
    }
    public int check(int num) {
        TreeNode cur = root;
        int x = 0;
        for(int k = HIGH_BIT; k >= 0; k--) { // 从最高位遍历到最低位
            int bit = (num >> k) & 1;
            if(bit == 0) {
                // a_i 的第 k 个二进制位为 0，应当往表示 1 的子节点 right 走
                if(cur.right != null) {
                    cur = cur.right;
                    x = x * 2 + 1;
                } else {
                    cur = cur.left;
                    x = x * 2;
                }
            } else {
                // a_i 的第 k 个二进制位为 1，应当往表示 0 的子节点 left 走
                if(cur.left != null) {
                    cur = cur.left;
                    x = x * 2 + 1;
                } else {
                    cur = cur.right;
                    x = x * 2;
                }
            }
        }
        return x;
    }
}
