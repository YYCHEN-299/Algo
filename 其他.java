// 最小时间差
// 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示
class Solution {
    public int findMinDifference(List<String> timePoints) {
        int len = timePoints.size();
        // 最多 24 * 60 = 1440 种时间，超过后一定有相同的，相同的时间相差为 0
        if(len > 1440) {
            return 0;
        }
        int[] timels = new int[len + 1]; // 以分钟数存放每一种时间
        for(int i = 0; i < len; i++) {
            String[] t = timePoints.get(i).split(":");
            int mm = Integer.parseInt(t[0]) * 60 + Integer.parseInt(t[1]);
            timels[i] = mm;
        }
        timels[len] = 1440; // 最大分钟数为 24 * 60
        Arrays.sort(timels); // 对时间进行排序
        timels[len] = timels[0] + 1440; // 将最后一个时间改为最小时间 + 24h 来计算头尾时间的差
        int res = 1440;
        for(int i = 1; i < len + 1; i++) {
            res = Math.min(res, timels[i] - timels[i - 1]);
        }
        return res;
    }
}
