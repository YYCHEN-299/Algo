在对问题求解时，总是做出在当前看来是最好的选择
1、建立数学模型来描述问题
2、把求解的问题分成若干个子问题
3、对每个子问题求解，得到子问题的局部最优解
4、把子问题的解局部最优解合成原来解问题的一个解

// 供暖器
// 设计一个有固定加热半径的供暖器向所有房屋供暖
// 在加热器的加热半径范围内的每个房屋都可以获得供暖
// 给出位于一条水平线上的房屋和供暖器的位置，请找出可以覆盖所有房屋的最小加热半径
// houses = [1,2,3,4], heaters = [1,4] => 1 (在位置1, 4上有两个供暖器。加热半径设为1，这样所有房屋就都能得到供暖)
// 房子: x x x x
// 供暖: x     x
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        int r = 0, i = 0, j = 0, d = 0, d1, d2;
        Arrays.sort(houses); // 排序
        Arrays.sort(heaters); // 排序
        while(i < houses.length) { // 遍历每个房子
            if(j == heaters.length - 1) { // 如果遍历到最后一个供暖器，接下来只有该供暖器最近
                d = Math.abs(houses[i] - heaters[j]);
                i += 1;
            } else {
                d1 = Math.abs(houses[i] - heaters[j]); // 当前房子有两种选择，一种是位于 j 的供暖或者下一个供暖
                d2 = Math.abs(houses[i] - heaters[j + 1]);
                if(d1 >= d2) { // 如果位于 j 的供暖是更远的，那么选择下一个供暖
                    j += 1;
                } else { // 如果当前的供暖是更近的，就选择这个并记录距离
                    d = d1;
                    i += 1; // 保留这个供暖，检查它对下一个房屋是否也是更近的供暖
                }
            }
            r = Math.max(r, d); // 记录最大的供暖半径
        }
        return r;
    }
}

// 分发糖果
// 老师想给孩子们分发糖果，有 N 个孩子站成了一条直线，老师会根据每个孩子的表现，预先给他们评分。
// 每个孩子至少分配到 1 个糖果
// 评分更高的孩子必须比他两侧的邻位孩子获得更多的糖果
// 那么这样下来，老师至少需要准备多少颗糖果呢
// [1,5,3,4,4] => 7
class Solution {
    public int candy(int[] ratings) {
        // 分别查看左侧右侧是否有比自己低的人，如果有则比他的糖果数量多 1，如果没有则拿 1 颗糖
        int[] left = new int[ratings.length];
        left[0] = 1;
        for(int i = 1; i < ratings.length; i++) {
            if(ratings[i] > ratings[i - 1]) {
                left[i] = left[i - 1] + 1;
            } else {
                left[i] = 1;
            }
        }
        int[] right = new int[ratings.length];
        right[ratings.length - 1] = 1;
        for(int i = ratings.length - 2; i >= 0; i--) {
            if(ratings[i] > ratings[i + 1]) {
                right[i] = right[i + 1] + 1;
            } else {
                right[i] = 1;
            }
        }
        // 最后取能拿到的最大数量
        int res = 0;
        for(int i = 0; i < ratings.length; i++) {
            res += Math.max(left[i], right[i]);
        }
        return res;
    }
}
