/**
 * TreeSet
 * TreeSet 是一个有序的 Set 集合
 * 基本操作: add、remove、contains 提供 log(n) 的时间复杂度
 */

// 值和下标之差都在给定的范围内
// 给你一个整数数组 nums 和两个整数 k 和 t 。判断是否存在 两个不同下标 i 和 j
// 使得 abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k (-k <= (i - j) <= k)
// 如果存在则返回 true，不存在返回 false
// nums = [1,2,3,1], k = 3, t = 0 => true
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> ts = new TreeSet<>(); // 有序集合
        for(int i = 0; i < nums.length; i++) { // 有序集合 + 滑动窗口
            // ceiling: 有序集合中大于等于目标值的最小元素
            // floor: 有序集合中小于等于目标值的最大元素
            Long n = ts.ceiling((long)nums[i] - (long)t);
            if(n != null && n <= (long)nums[i] + (long)t) {
                return true;
            }
            ts.add((long)nums[i]); // 添加元素到滑动窗口中
            if(i >= k) { // 从滑动窗口中删除元素
                ts.remove((long)nums[i - k]);
            }
        }
        return false;
    }
}

/**
 * TreeMap
 * TreeMap 是一个能比较元素大小的Map集合，会对传入的key进行了大小排序
 * TreeMap实现了红黑树的结构，形成了一颗二叉树
 */

// 日程表
// MyCalendar 有一个 book(int start, int end)方法
// 它意味着在 start 到 end 时间内增加一个日程安排
// 注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为 start <= x < end
// 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生重复预订
// 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true
// 否则，返回 false 并且不要将该日程安排添加到日历中
// 输入:
// ["MyCalendar","book","book","book"]
// [[],[10,20],[15,25],[20,30]]
// 输出: [null,true,false,true]
class MyCalendar {
    TreeMap<Integer, Integer> timeMap;
    public MyCalendar() {
        timeMap = new TreeMap<>();
    }
    public boolean book(int start, int end) {
        // ceiling: 有序集合中大于等于目标值的最小元素
        // floor: 有序集合中小于等于目标值的最大元素
        Map.Entry<Integer, Integer> floorPeriod = timeMap.floorEntry(start);
        Map.Entry<Integer, Integer> ceilPeriod = timeMap.ceilingEntry(start);
        int lastEnd = Integer.MIN_VALUE;
        int nextStart = Integer.MAX_VALUE;
        if(floorPeriod != null) {
            lastEnd = floorPeriod.getValue();
        }
        if(ceilPeriod != null) {
            nextStart = ceilPeriod.getKey();
        }
        if(lastEnd <= start && end <= nextStart) {
            timeMap.put(start, end);
            return true;
        }
        return false;
    }
}
