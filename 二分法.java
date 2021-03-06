// 查找有序数组时优先考虑二分法，如果数组无序可以考虑先排序后用二分

// 标准二分查找
// 数组中有唯一的target
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left <= right) { // 循环条件包含等于
        	int mid = left + (right - left) / 2;
            if(nums[mid] == target) {
                return mid;
            } else if(nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}

// 查找左边界
// 第一个为target的值
class Solution {
	public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right) { // 循环条件不包含等于
            int mid = left + (right - left) / 2;
            if(nums[mid] < target) { // 遇到相同的取它的左边不需要加等号，遇到相同的取它的右边加等号
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}

// 查找右边界
// 最后一个为target的值
class Solution {
    public int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while(left < right) { // 循环条件不包含等于
            int mid = left + (right - left) / 2 + 1; // 中间点需要右偏
            if(nums[mid] > target) { // 遇到相同的取它的右边不需要加等号，遇到相同的取它的左边加等号
                right = mid - 1;
            } else {
                left = mid;
            }
        }
        return right;
    }
}

// ----

// 例题

// 山峰数组的顶部
// arr.length >= 3
// 存在 i（0 < i < arr.length - 1）使得:
// arr[0] < arr[1] < ... arr[i-1] < arr[i]
// arr[i] > arr[i+1] > ... > arr[arr.length - 1]
// arr = [1,3,5,4,2] => 2
class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 1, r = arr.length - 2, mid; // 注意左右指针不能顶到最左最右
        while(l < r) {
            mid = l + (r - l) / 2;
            if(arr[mid] < arr[mid + 1]) { // 查找左边第一个满足 arr[i] > arr[i + 1] 的 index
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
}

// 排序数组中只出现一次的数字
// 给定一个只包含整数的有序数组，每个元素都会出现两次，唯有一个数只会出现一次，请找出这个唯一的数字
class Solution {
    public int singleNonDuplicate(int[] nums) {
        int l = 0, r = nums.length - 1, mid = 0;
        while(l <= r) {
            mid = (r - l) / 2 + l;
            // 1、mid 和下一个数相同 2、mid 和前一个数相同
	    if(mid < nums.length - 1 && nums[mid] == nums[mid + 1]) {
                if(mid % 2 == 0) { // 如果 mid 为双数，说明 mid 前的数字都是成对出现的，出现一次的数在 mid 后
                    l = mid + 2;
                } else { // mid 为单数，说明出现一次的数在 mid 前
                    r = mid - 1;
                }
            } else if(mid > 0 && nums[mid - 1] == nums[mid]) {
                if(mid % 2 == 0) {
                    r = mid - 2;
                } else {
                    l = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return -1;
    }
}

// 按权重生成随机数
// 给定一个正整数数组 w ，其中 w[i] 代表下标 i 的权重（下标从 0 开始）
// 请写一个函数，随机地获取下标 i，选取下标 i 的概率与 w[i] 成正比
// 选取下标 i 的概率为 w[i] / sum(w)
class Solution {
    int[] ww;
    int sum = 0;
    public Solution(int[] w) {
        ww = new int[w.length];
        for(int i = 0; i < w.length; i++) {
            sum += w[i];
            ww[i] = sum;
        }
    }
    public int pickIndex() {
        int rd = (int)(Math.random() * sum) + 1;
        int l = 0, r = ww.length - 1, mid = 0;
        while(l < r) {
            mid = (r - l) / 2 + l;
            if(ww[mid] < rd) { // 取第一个大于 target 的值
                l = mid + 1;
            }
            else {
                r = mid;
            }
        }
        return l;
    }
}

// 求平方根
// 给定一个非负整数 x ，计算并返回 x 的正数平方根
// 如果平方根不是整数，输出只保留整数的部分
// x = 8 => 2 (8 的平方根是 2.82842...，舍去小数)
class Solution {
    public int mySqrt(int x) {
        int l = 0, r = x, mid;
        while(l < r) {
            mid = l + (r - l) / 2 + 1; // 中间点需要右偏
            if(x / mid < mid) { // 最后一个为 target 的值(mid * mid > x)
                r = mid - 1;
            } else { // mid * mid <= x
                l = mid;
            }
        }
        return r;
    }
}

// 狒狒吃香蕉
// 狒狒喜欢吃香蕉。有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫将在 H 小时后回来
// 狒狒可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。
// 如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉，下一个小时才会开始吃另一堆的香蕉
// 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）
// piles = [3,6,7,11], H = 8 => 4
class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        Arrays.sort(piles);
        if(piles.length == h) return piles[piles.length - 1];
        int l = 1, r = piles[piles.length - 1], mid;
        while(l < r) {
            mid = l + (r - l) / 2;
            if(getH(piles, mid) > h) { // 左边界，找第一个小于等于 h 的
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        return l;
    }
    public int getH(int[] piles, int s) { // 辅助函数，求当前速度下需要几小时吃完
        int res = 0;
        for(int i : piles) {
            res += (i + s - 1) / s; // i 除 s 整除
        }
        return res;
    }
}
