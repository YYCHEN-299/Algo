/**
 * 二叉树遍历
 *          1
 *         / \
 *        3   2
 *       / \   \
 *      5   3   9
 * NLR: 前序遍历 (Preorder Traversal 亦称 先序遍历)
 * —— 访问根结点的操作发生在遍历其左右子树之前
 * LNR: 中序遍历 (Inorder Traversal)
 * —— 访问根结点的操作发生在遍历其左右子树之中 (间)
 * LRN: 后序遍历 (Postorder Traversal)
 * —— 访问根结点的操作发生在遍历其左右子树之后
 * 层序遍历
 * 从根节点出发，首先访问第一层的树根节点，然后自上而下，自左至右逐层访问树的结点
 * 
 * 广度优先搜索和深度优先搜索:
 * 1、广度优先搜索可以按照层次的顺序从上到下遍历所有的节点
 * 2、深度优先搜索可以从一个根开始，一直延伸到某个叶，然后回到根，到达另一个分支
 * 根据根节点、左节点和右节点之间的相对顺序，可以进一步将深度优先搜索策略区分为:
 * 先序遍历、中序遍历、后序遍历
 * 
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

// 往完全二叉树添加节点
// 完全二叉树是每一层 (除最后一层外) 都是完全填充 (节点数达到最大，第 n 层有 2 ^ (n - 1) 个节点)
// 并且所有的节点都尽可能地集中在左侧
// CBTInserter(TreeNode root) 使用根节点为 root 的给定树初始化该数据结构
// CBTInserter.insert(int v)  向树中插入一个新节点，节点类型为 TreeNode，值为 v
// 使树保持完全二叉树的状态，并返回插入的新节点的父节点的值
// CBTInserter.get_root() 将返回树的根节点
class CBTInserter {
    TreeNode r;
    Queue<TreeNode> q = new LinkedList<>(); // 保存左右子树都空的结点
    public CBTInserter(TreeNode root) {
        Queue<TreeNode> qq = new LinkedList<>();
        r = root;
        qq.offer(root);
        while(!qq.isEmpty()) { // 层序遍历
            TreeNode t = qq.poll();
            if(t.left == null || t.right == null) { // 保存左右子树都空的结点
                q.offer(t);
            }
            if(t.left != null) { // 先查左子树
                qq.offer(t.left);
            }
            if(t.right != null) { // 后查右子树
                qq.offer(t.right);
            }
        }
    }
    public int insert(int v) {
        TreeNode t = q.peek(); // 获取当前有空的结点
        if(t.left == null) {
            t.left = new TreeNode(v);
            q.offer(t.left); // 尾部加入新插入的结点
        } else {
            t.right = new TreeNode(v);
            q.poll(); // 右侧也加入了结点，因此从有空的节点中删除
            q.offer(t.right);
        }
        return t.val;
    }
    public TreeNode get_root() {
        return r;
    }
}

// 二叉树每层的最大值
// 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值
// root = [1,3,2,5,3,null,9] => [1,3,9]
//          1
//         / \
//        3   2
//       / \   \
//      5   3   9
class Solution {
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        int max, len;
        Queue<TreeNode> q = new LinkedList<>();
        if(root != null) {
            q.offer(root);
        }
        while(!q.isEmpty()) { // BFS 广度优先搜索
            len = q.size();
            max = Integer.MIN_VALUE;
            for(int i = 0; i < len; i++) {
                TreeNode t = q.poll();
                max = Math.max(max, t.val);
                if(t.left != null) {
                    q.offer(t.left);
                }
                if(t.right != null) {
                    q.offer(t.right);
                }
            }
            res.add(max);
        }
        return res;
    }
}

// 二叉树最底层最左边的值
// 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值
// [1,2,3,4,null,5,6,null,null,7] => 7
//          1
//         / \
//        2   3
//       /   / \
//      4   5   6
//         /
//        7
class Solution {
    public int findBottomLeftValue(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        TreeNode res = root;
        while(!q.isEmpty()) { // BFS 广度优先搜索
            int len = q.size();
            for(int i = 0; i < len; i++) {
                TreeNode t = q.poll();
                if(i == 0) { // 最左侧的结点一定是第一个出队的
                    res = t;
                }
                if(t.left != null) {
                    q.offer(t.left);
                }
                if(t.right != null) {
                    q.offer(t.right);
                }
            }
        }
        return res.val;
    }
}

// 二叉树的右侧视图 (获取所有最右节点)
// 给定一个二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        if(root != null) q.offer(root);
        TreeNode t;
        int len;
        List<Integer> res = new ArrayList<>();
        while(!q.isEmpty()) { // BFS 广度优先搜索
            len = q.size();
            for(int i = 0; i < len; i++) {
                t = q.poll();
                if(i == len - 1) { // 最右侧的结点一定是最后一个出队的
                    res.add(t.val);
                }
                if(t.left != null) {
                    q.offer(t.left);
                }
                if(t.right != null) {
                    q.offer(t.right);
                }
            }
        }
        return res;
    }
}

// 二叉树剪枝
// 二叉树，树的每个节点的值是 0 or 1。请剪除该二叉树中所有节点的值为 0 的子树
// [1,null,0,0,1] => [1,null,0,null,1]
//     1                   1 
//      \                   \
//       0        =>         0
//      / \                   \
//     0   1                   1
class Solution {
    public TreeNode pruneTree(TreeNode root) {
        if(root == null) {
            return root;
        }
        // 递归实现后续遍历
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if(root.left == null && root.right == null && root.val == 0) {
            return null;
        }
        return root;
    }
}

// 序列化与反序列化二叉树
// 保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构
// root = [1,2,3,null,null,4,5] => [1,2,3,null,null,4,5]
public class Codec {
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        return rserialize(root, sb).toString();
    }
    public TreeNode deserialize(String data) {
        String[] dataArray = data.split(",");
        List<String> dataList = new ArrayList<>(Arrays.asList(dataArray));
        return rdeserialize(dataList);
    }
    public StringBuilder rserialize(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("N,");
        } else { // DFS + 前序遍历
            sb.append(String.valueOf(root.val) + ","); // 放入当前结点
            sb = rserialize(root.left, sb);
            sb = rserialize(root.right, sb);
        }
        return sb;
    }
    public TreeNode rdeserialize(List<String> dataList) {
        if(dataList.get(0).equals("N")) {
            dataList.remove(0);
            return null;
        }
        // DFS + 前序遍历
        TreeNode root = new TreeNode(Integer.valueOf(dataList.get(0))); // 读取当前结点
        dataList.remove(0); // 去掉当前结点
        root.left = rdeserialize(dataList);
        root.right = rdeserialize(dataList);
        return root;
    }
}

// 从根节点到叶节点的路径数字之和
// 给定一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字
// 每条从根节点到叶节点的路径都代表一个数字
// 计算从根节点到叶节点生成的 所有数字之和
// root = [1,2,3] => 25
//     1
//    / \
//   2   3
// 从根到叶子节点路径 1 -> 2 代表数字 12
// 从根到叶子节点路径 1 -> 3 代表数字 13
// 数字总和 = 12 + 13 = 25
class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    public int dfs(TreeNode root, int sum) {
        if(root == null) {
            return 0;
        }
        // 当前的数字 = 前一个结点的数字 * 10 (前一个结点的值移到高一位) + 当前的值
        sum = sum * 10 + root.val;
        if(root.left == null && root.right == null) { // 如果当前结点为叶子节点则返回 sum
            return sum;
        } else {
            // DFS + 前序遍历
            return dfs(root.left, sum) + dfs(root.right, sum);
        }
    }
}

// 向下的路径节点之和
// 求二叉树里节点值之和等于 targetSum 的 路径 的数目
// 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的 (只能从父节点到子节点)
// root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8 => 3 (和等于 8 的路径有 3 条)
//              10
//            /    \
//          [5]    [-3]
//         /   \      \
//       [3]   [2]    [11]
//      /   \    \
//     3    -2   [1]
class Solution {
    public int pathSum(TreeNode root, int targetSum) {
        HashMap<Long, Integer> mp = new HashMap<>();
        mp.put(0L, 1); // 前缀和 = 0 的数量 = 1
        return dfs(root, mp, targetSum, 0L);
    }
    public int dfs(TreeNode root, HashMap<Long, Integer> mp, int target, long sum) {
        if (root == null) {
            return 0;
        }
        sum += root.val;
        // 前缀和思想，如果之前的前缀和 - target = 当前的前缀和，说明可以去掉之前的前缀和，得到 target
        int res = mp.getOrDefault(sum - target, 0);
        mp.put(sum, mp.getOrDefault(sum, 0) + 1); // 当前前缀和的数量 + 1
        // DFS + 前序遍历
        res += dfs(root.left, mp, target, sum);
        res += dfs(root.right, mp, target, sum);
        mp.put(sum, mp.getOrDefault(sum, 0) - 1); // 回溯，去掉一个当前的和，避免重复计算
        return res;
    }
}

// 节点之和最大的路径
// 路径 被定义为一条从树中任意节点出发，沿父节点-子节点连接，达到任意节点的序列
// 同一个节点在一条路径序列中 至多出现一次 。该路径 至少包含一个 节点，且不一定经过根节点
// 路径和 是路径中各节点值的总和
// 返回二叉树 最大路径和，即所有路径上节点值之和的最大值
// root = [-10,9,20,null,null,15,7] => 42 (最优路径是 15 -> 20 -> 7 ，和 = 15 + 20 + 7 = 42)
//          -10
//         /   \
//        9    20
//            /  \
//           15   7
class Solution {
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null) {
            return 0;
        }
        dfs(root);
        return res;
    }
    public int dfs(TreeNode root) {
        if(root == null) {
            return 0;
        }
        // DFS + 后序遍历
        // 获取左右子树的和，如果是负数则设和为 0 (相当于砍掉该分支)
        int left = Math.max(0, dfs(root.left));
        int right = Math.max(0, dfs(root.right));
        res = Math.max(res, left + right + root.val); // 更新以当前结点为根的当前最大和
        return root.val + Math.max(left, right); // 返回当前结点 + 左右子树的最大值 (回到当前节点的根节点，左右子树选一边才是合法路径)
    }
}

// 二叉搜索树中的中序后继
// 给定一棵二叉搜索树和其中的一个节点 p ，找到该节点在树中的中序后继。如果节点没有中序后继，请返回 null
// 节点 p 的后继是值比 p.val 大的节点中键值最小的节点，即按中序遍历的顺序节点 p 的下一个节点
// root = [5,3,6,2,4,null,null,1], p = 6 => null
// 
// 二叉搜索树:
// 若左子树不空，则左子树上所有结点均小于根结点，若右子树不空，则右子树上所有结点均大于根结点
// 使用中序遍历可以对其节点进行从小到大的遍历
class Solution {
    // 使用 LinkedList 实现 List 而不是 ArrayList (需要查找 index 时，LinkedList 更快，但只做 add 和 get 的操作，ArrayList更快)
    List<TreeNode> ls = new LinkedList<>();
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        dfs(root);
        if(ls.indexOf(p) + 1 == ls.size()) return null; // 如果要找的结点是树中最后一个节点则没有结果
        return ls.get(ls.indexOf(p) + 1); // list 中符合要求的结点的下一个就是结果
    }
    // 因为是二叉搜索树，所以中序遍历后节点一定从小到大塞进 list 中
    private void dfs(TreeNode root) {
        if(root == null) return;
        dfs(root.left);
        ls.add(root);
        dfs(root.right);
    }
}
