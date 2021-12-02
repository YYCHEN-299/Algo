// 队列是一种特殊的线性表，是一种先进先出(FIFO)的数据结构
// 只允许在前端进行删除，在后端进行插入

// 滑动窗口的平均值
// 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值
// MovingAverage(int size) 用窗口大小 size 初始化对象
// double next(int val) 成员函数 next 每次调用时会往滑动窗口增加一个整数，返回滑动窗口里数字的平均值
class MovingAverage {
    Queue<Integer> q = new LinkedList<>();
    int size, idx = 0;
    double sum = 0.0;
    public MovingAverage(int size) {
        this.size = size;
    }
    public double next(int val) {
        idx++;
        if(idx > size) {
            idx--;
            sum -= q.poll();
        }
        sum += val;
        q.add(val);
        double res = sum / idx;
        return res;
    }
}

// 最近请求次数
// RecentCounter() 初始化计数器，请求数为 0
// int ping(int t) 在时间 t 添加一个新请求，返回在 [t-3000, t] 内发生的请求数
class RecentCounter {
    int requests;
    Queue<Integer> q = new LinkedList<>();
    public RecentCounter() {
        requests = 0;
    }
    public int ping(int t) {
        while(q.peek() != null && q.peek() < t - 3000) {
            q.poll();
            requests -= 1;
        }
        q.add(t);
        requests += 1;
        return requests;
    }
}

// -----------------------

// Priority Queue: 优先队列
// 基于优先堆的一个无界队列，这个优先队列中的元素可以默认自然排序或者通过提供的 Comparator 在队列实例化时排序

// 数据流的第 K 大数值
// KthLargest(int k, int[] nums) 使用整数 k 和整数流 nums 初始化对象
// int add(int val) 将 val 插入数据流 nums 后，返回当前数据流中第 k 大的元素
class KthLargest {
    PriorityQueue<Integer> pq;
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        pq = new PriorityQueue<Integer>();
        for(int x : nums) {
            add(x);
        }
    }
    public int add(int val) {
        pq.offer(val);
        if(pq.size() > k) {
            pq.poll();
        }
        return pq.peek();
    }
}

// 出现频率最高的 k 个数字
// 给定一个整数数组 nums 和一个整数 k ，返回其中出现频率前 k 高的元素。可以按 任意顺序 返回答案
// nums = [1,1,1,2,2,3], k = 2 => [1,2]
class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        HashMap<Integer, Integer> hm = new HashMap<>();
        for(int i : nums) { // 计算每个数字出现的次数
            hm.put(i, hm.getOrDefault(i, 0) + 1);
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] m, int[] n) { // 自定义比较器
                return m[1] - n[1];
            }
        });
        for(Map.Entry<Integer, Integer> e : hm.entrySet()) { // 获取哈希表中的元素
            int a = e.getKey();
            int b = e.getValue();
            if(pq.size() == k) {
                if(pq.peek()[1] < b) { // 队列头部是当前队列中最小的值
                    pq.poll();
                    pq.add(new int[]{a, b});
                }
            } else {
                pq.add(new int[]{a, b});
            }
        }
        int[] res = new int[k];
        int idx = 0;
        while(pq.size() != 0) {
            res[idx++] = pq.poll()[0];
        }
        return res;
    }
}

// 和最小的 k 个数对
// 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k
// 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2
// 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk)
// nums1 = [1,7,11], nums2 = [2,4,6], k = 3 => [1,2],[1,4],[1,6]
class Solution {
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        int[][] nums = new int[n * m][2];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) { // 枚举所有可能性
                nums[i * m + j][0] = nums1[i];
                nums[i * m + j][1] = nums2[j];
            }
        }
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            public int compare(int[] a, int[] b) { // 自定义比较器
                return (a[0] + a[1]) - (b[0] + b[1]);
            }
        });
        for(int z = 0; z < nums.length; z++) {
            pq.add(nums[z]);
        }
        List<List<Integer>> res = new ArrayList<>();
        for(int z = 0; z < k && z < nums.length; z++) {
            List<Integer> ls = new ArrayList<>();
            ls.add(pq.peek()[0]);
            ls.add(pq.poll()[1]);
            res.add(ls);
        }
        return res;
    }
}
