// 栈是仅在尾部进行插入或删除操作的线性表
// 它按照后进先出的原则存储数据

// 后缀表达式
// 根据 逆波兰表示法，求该后缀表达式的计算结果
// tokens = ["2","1","+","3","*"] => 9 (((2 + 1) * 3) = 9)
class Solution {
    public int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        int num1, num2, res;
        for(String c : tokens) {
            switch(c) {
                case "+":
                    num2 = stack.pop(); // 遇到运算符 pop 出栈顶的头两个数字
                    num1 = stack.pop();
                    res = num1 + num2;
                    stack.push(res); // 将本次运算的结果压入栈
                    break;
                case "-":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    res = num1 - num2;
                    stack.push(res);
                    break;
                case "*":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    res = num1 * num2;
                    stack.push(res);
                    break;
                case "/":
                    num2 = stack.pop();
                    num1 = stack.pop();
                    res = num1 / num2;
                    stack.push(res);
                    break;
                default:
                    stack.push(Integer.parseInt(c)); // 如果不是运算符就是数字，将数字压入栈
            }
        }
        return stack.pop();
    }
}

// 小行星碰撞
// 对于数组中的每一个元素，其绝对值表示小行星的大小，正负表示小行星的移动方向（正表示向右移动，负表示向左移动）
// 每一颗小行星以相同的速度移动，找出碰撞后剩下的所有小行星
// 碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。两颗移动方向相同的行星，永远不会发生碰撞。
// asteroids = [5,10,-5] => [5,10]
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        Stack<Integer> res = new Stack<>();
        for(int i = 0; i < asteroids.length; i++) {
            int n = asteroids[i];
            if(n < 0) { // 左移星
                if(res.empty()) {
                    res.push(n);
                } else {
                    // 若栈顶是右移星且右移星比当前星小
                    while(!res.empty() && res.peek() > 0 && res.peek() < -n) {
                        res.pop();
                    }
                    // 若栈内都是左移星或栈空直接加入该星
                    if(res.empty() || res.peek() < 0) {
                        res.push(n);
                    } else if(res.peek() == -n) { // 小行星相等，两者都会爆炸
                        res.pop();
                    }
                }
            } else { // 右移星
                res.push(n);
            }
        }
        int[] output = new int[res.size()];
        for (int i = output.length - 1; i >= 0; i--) { // 倒序存入剩余的星星
            output[i] = res.pop();
        }
        return output;
    }
}

// 每日温度
// 请根据每日 气温 列表 temperatures ，重新生成一个列表，要求其对应位置的输出:
// 要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替
// temperatures = [73,74,75,71,69,72,76,73] => [1,1,4,2,1,1,0,0]
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        Stack<Integer> idx = new Stack<>(); // 栈内存放元素 index
        for(int i = 0; i < temperatures.length; i++) {
            while(!idx.empty() && temperatures[idx.peek()] < temperatures[i]) {
                int k = idx.pop(); // 栈内 index 对应的温度低于 i 处的温度的都 pop 出
                res[k] = i - k; // index 之差
            }
            idx.push(i);
        }
        return res;
    }
}

// 直方图最大矩形面积
// 给定非负整数数组，数组中的数字用来表示柱状图中各个柱子的高度
// 每个柱子彼此相邻，且宽度为 1
// 求在该柱状图中，能够勾勒出来的矩形的最大面积
// heights = [2,1,5,6,2,3] => 10
//                    __6__
//              __5__|     |
//             |     |     |
//             |     |     |      __3__
//  __2__      |     |     |__2__|     |
// |     |__1__|     |     |     |     |
// |     |     |     |     |     |     |
// 最大矩形为阴影部分:
//                    __6__
//              __5__|     |
//             |■■■■■|■■■■■|
//             |■■■■■|■■■■■|      __3__
//  __2__      |■■■■■|■■■■■|__2__|     |
// |     |__1__|■■■■■|■■■■■|     |     |
// |     |     |■■■■■|■■■■■|     |     |
class Solution {
    public int largestRectangleArea(int[] heights) {
        if(heights.length == 1) {
            return heights[0];
        }
        int[] l = new int[heights.length];
        int[] r = new int[heights.length];
        Stack<Integer> st = new Stack<>();
        st.push(-1); // 虚拟一个最左侧的 index = -1
        // 寻找左侧第一个比当前柱子低的柱子坐标
        for(int i = 0; i < heights.length; i++) {
            // 如果栈顶的柱子比当前柱子高，直接 pop
            while(st.peek() != -1 && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            l[i] = st.peek();
            st.push(i);
        }
        st.clear(); // 清空栈
        st.push(heights.length); // 虚拟一个最右侧的 index = 数组长度
        // 寻找右侧第一个比当前柱子低的柱子坐标
        for(int i = heights.length - 1; i >= 0; i--) {
            // 如果栈顶的柱子比当前柱子高，直接 pop
            while(st.peek() != heights.length && heights[st.peek()] >= heights[i]) {
                st.pop();
            }
            r[i] = st.peek();
            st.push(i);
        }
        int s = 0;
        for(int i = 0; i < heights.length; i++) {
            // 每一个柱子能获得的最大面积 = 柱子的高 * 左右侧第一个比他低的柱子之间的距离
            s = Math.max(s, heights[i] * (r[i] - l[i] - 1));
        }
        return s;
    }
}

// 矩阵中最大的矩形
// 给定一个由 0 和 1 组成的矩阵 matrix ，找出只包含 1 的最大矩形，并返回其面积
// matrix = ["10100","10111","11111","10010"] => 6
// 1  0   1   0   0
// 1  0  [1] [1] [1]
// 1  1  [1] [1] [1]
// 1  0   0   1   0
class Solution {
    public int maximalRectangle(String[] matrix) {
        if(matrix.length == 0 || matrix[0].length() == 0) return 0;
        int[] col = new int[matrix[0].length()];
        int s = 0;
        for(int i = 0; i < matrix.length; i++) {
            for(int j = 0; j < matrix[i].length(); j++) {
                // 读每一行，如果是 1 则加总到当前行该列一共有几个 1，如果是 0 则将该列改为 0
                if(matrix[i].charAt(j) == '0') {
                    col[j] = 0;
                } else {
                    col[j]++;
                }
            }
            // 每叠加完一行，就进行面积运算，取最大值
            s = Math.max(s, comp(col));
        }
        return s;
    }
    private int comp(int[] col) {
        int[] l = new int[col.length];
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        for(int i = 0; i < col.length; i++) {
            while(st.peek() != -1 && col[st.peek()] >= col[i]) {
                st.pop();
            }
            l[i] = st.peek();
            st.push(i);
        }
        int[] r = new int[col.length];
        st.clear();
        st.push(col.length);
        for(int i = col.length - 1; i >=0; i--) {
            while(st.peek() != col.length && col[st.peek()] >= col[i]) {
                st.pop();
            }
            r[i] = st.peek();
            st.push(i);
        }
        int s = 0;
        for(int i = 0; i < col.length; i++) {
            s = Math.max(s, (r[i] - l[i] - 1) * col[i]);
        }
        return s;
    }
}

// 字符串解码
// 给定一个经过编码的字符串，返回它解码后的字符串
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数
// 可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的
// s = "3[a2[c]]" => "accaccacc"
class Solution {
    int ptr;
    public String decodeString(String s) {
        Stack<String> st = new Stack<>();
        ptr = 0;
        while(ptr < s.length()) {
            char cur = s.charAt(ptr);
            if(Character.isDigit(cur)) {
                // 获取一个数字进栈，将多位数字更改为一个 String
                String digits = getDigits(s);
                st.push(digits);
            } else if(Character.isLetter(cur) || cur == '[') {
                // 获取一个字母或者左括号进栈
                st.push(String.valueOf(s.charAt(ptr))); 
                ptr += 1;
            } else if(cur == ']') {
                StringBuilder sb = new StringBuilder();
                while (!"[".equals(st.peek())) {
                    sb.append(st.pop());
                }
                st.pop(); // 左括号出栈
                // 此时 st 栈顶为当前 sub 对应的字符串应该出现的次数
                int t = Integer.parseInt(st.pop());
                StringBuilder output = new StringBuilder();
                while(t > 0) {
                    output.append(sb);
                    t -= 1;
                }
                // 将构造好的字符串入栈
                // 存入的字符串是反着的，但最后会将整个字符串翻转，因此没有影响
                st.push(output.toString());
                ptr += 1;
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!st.isEmpty()) {
            sb.append(st.pop());
        }
        sb.reverse();
        return sb.toString();
    }
    public String getDigits(String s) { // 避免多位数字的情况
        StringBuilder sb = new StringBuilder();
        while(Character.isDigit(s.charAt(ptr))) {
            sb.append(s.charAt(ptr));
            ptr += 1;
        }
        return sb.toString();
    }
}
