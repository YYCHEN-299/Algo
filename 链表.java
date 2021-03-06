/**
 * 单向链表的基本结构
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

// 快慢指针
 
// 删除链表的倒数第 n 个结点
//     1, 2, 3, 4, 5    n = 2
//   s f                初始状态
//   s       f          快指针先走 n 步，快慢指针间差 n 个结点
//           s       f  快指针走到尾部，慢指针恰好是倒数第 n - 1 个结点
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
		ListNode ln = new ListNode(0, head);
        ListNode fast = head, slow = ln;
        while(n > 0) {
            fast = fast.next;
            n--;
        }
        while(fast != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return ln.next;
    }
}

// 链表中环的入口节点
// 给定一个链表，返回链表开始入环的第一个节点
// 3 -> 2 -> 0 -> -4
//      ↑----------|
// 返回索引为 1 的链表节点
public class Solution {
    public ListNode detectCycle(ListNode head) {
        if(head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        while(fast != null) {
            slow = slow.next;
            if(fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if(fast == slow) {
                ListNode ln = head;
                while(ln != slow) {
                    ln = ln.next;
                    slow = slow.next;
                }
                return ln;
            }
        }
        return null;
    }
}

// 重排链表
// L0 → L1 → … → Ln-1 → Ln
// 请将其重新排列后变为:
// L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
// [1,2,3,4,5] => [1,5,2,4,3]
class Solution {
    public void reorderList(ListNode head) {
        if(head == null) return;
        ListNode fast = head, slow = head;
        // 使用快慢指针将链表分割成前后两半
		while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode pre = null, now = slow.next;
        slow.next = null; // 将前半段和后半段链表分割开
        // 将后一半链表反转
		while(now != null) {
            ListNode s = now.next;
            now.next = pre;
            pre = now;
            now = s;
        }
        ListNode l1 = head;
        ListNode l2 = pre;
		// 拼接两段链表
        while(l1 != null && l2 != null) {
            ListNode s1 = l1.next;
            ListNode s2 = l2.next;
            l1.next = l2;
            l1 = s1;
            l2.next = l1;
            l2 = s2;
        }
    }
}

// 两个链表的第一个重合节点
// 给定两个单链表的头节点，找出两个单链表相交的起始节点
//        a1 -> a2 -> c1 -> c2 -> c3
//  b1 -> b2 -> b3 -- ↑
// 遍历完 A 后进入 B: (a1 -> a2 -> c1 -> c2 -> c3) -> (b1 -> b2 -> b3 -> [c1] -> c2 -> c3)
// 遍历完 B 后进入 A: (b1 -> b2 -> b3 -> c1 -> c2 -> c3) -> (a1 -> a2 -> [c1] -> c2 -> c3)
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if(headA == null || headB == null) {
            return null;
        }
        ListNode A = headA, B = headB;
        while(A != B) {
	    	// 遍历完 A 后进入 B，同理，遍历完 B 后进入 A
	    	// 这样使得两个链表长度相等，继续移动他们就会相遇
            A = A == null ? headB : A.next;
            B = B == null ? headA : B.next;
        }
        return A;
    }
}

// 排序的循环链表
// 给定循环单调非递减列表中的一个点，向这个列表中插入一个新元素，使这个列表仍然是循环升序的
// 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针
// 如果列表为空，需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点
// head = [3,4,1], insertVal = 2 => [3,4,1,2]
class Solution {
    public Node insert(Node head, int insertVal) {
        Node n = head;
        if(n == null) { // 如果是空的，则创建一个循环有序列表
            Node res = new Node(insertVal);
            res.next = res;
            return res;
        }
        while(n.next != head) { // 遍历
			// 当前结点的值大于下一个结点的值，就是升序的结尾，即当前是最大值，下一个是最小值
            if(n.val > n.next.val) {
                if(n.next.val >= insertVal) break; // 如果新增的值小于最小值
                else if(n.val <= insertVal) break; // 或大于最大值
            }
			// 如果新增值能正好放入两个结点之间
            if(n.val <= insertVal && insertVal <= n.next.val) {
                break;
            }
            n = n.next;
        }
		// 在符合要求的结点跳出循环，并加入新值，新值的下一个结点为原下一个结点
        n.next = new Node(insertVal, n.next);
        return head;
    }
}

// =======

// 反转思想

// 反转链表
class Solution {
    public ListNode reverseList(ListNode head) {
        ListNode pre = null;
        ListNode now = head;
        while(now != null) {
            ListNode s = now.next;
            now.next = pre;
            pre = now;
            now = s;
        }
        return pre;
    }
}

// 链表中的两数相加
// l1 = [7,2,4,3], l2 = [5,6,4] => [7,8,0,7]
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre1 = null;
        ListNode now1 = l1;
		// 反转链表
        while(now1 != null) {
            ListNode s = now1.next;
            now1.next = pre1;
            pre1 = now1;
            now1 = s;
        }
        ListNode pre2 = null;
        ListNode now2 = l2;
		// 反转链表
        while(now2 != null) {
            ListNode s = now2.next;
            now2.next = pre2;
            pre2 = now2;
            now2 = s;
        }
        ListNode res = new ListNode();
        ListNode ln = res;
        int flag = 0;
		// 倒着进行加总
        while(pre1 != null || pre2 != null) {
            int a = pre1 == null ? 0 : pre1.val;
            int b = pre2 == null ? 0 : pre2.val;
            int sum = a + b + flag;
            flag = sum / 10;
            sum = sum % 10;
            ln.next = new ListNode(sum);
            ln = ln.next;
            pre1 = pre1 == null ? null : pre1.next;
            pre2 = pre2 == null ? null : pre2.next;
        }
        if(flag == 1) {
            ln.next = new ListNode(1);
        }
        ListNode pre3 = null;
        ListNode now3 = res.next;
		// 将结果反转回来
        while(now3 != null) {
            ListNode s = now3.next;
            now3.next = pre3;
            pre3 = now3;
            now3 = s;
        }
        return pre3;
    }
}

// =======

// 双向链表

// 展平多级双向链表
// [1,2,3,4,5,6,null,null,null,7,8,9,10,null,null,11,12] => [1,2,3,7,8,11,12,9,10,4,5,6]
// 1 <-> 2 <-> 3 <-> 4 <-> 5 <-> 6
//             ↓
//             7 <-> 8 <-> 9 <-> 10
//                   ↓
//                   11 <-> 12
class Solution {
    public Node flatten(Node head) {
        dfs(head);
        return head;
    }
    public Node dfs(Node n) {
        Node now = n;
        Node last = null;
        while(now != null) {
            Node nxt = now.next;
            if(now.child != null) { // 如果当前节点有孩子
                // 将当前节点和孩子的头连接
				now.next = now.child;
                now.child.prev = now;
				Node ch = dfs(now.child); // 对孩子进行深度优先搜索
                if(nxt != null) { // 如果当前节点的下一个不为空则把它和孩子的尾连接
                    nxt.prev = ch;
                    ch.next = nxt;
                }
                now.child = null; // 切断当前节点和孩子的关联
                last = ch; // 当前最后一个结点为孩子的尾
            } else {
                last = now; // 当前最后一个结点为没有孩子的结点
            }
            now = nxt;
        }
        return last;
    }
}

// 最近最少使用缓存
// 运用所掌握的数据结构，实现 LRU (Least Recently Used，最近最少使用) 缓存机制 
// LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
// int get(int key) 如果 key 存在，则返回值，否则返回 -1
// void put(int key, int value) 如果key 已存在，则变更其值；如果不存在，则插入该组「关键字-值」
// 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间
// LRUCache lRUCache = new LRUCache(2);
// lRUCache.put(1, 1); // 缓存是 {1=1}
// lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
// lRUCache.get(1);    // 返回 1
// lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
// lRUCache.get(2);    // 返回 -1 (未找到)
class Node { // 自定义双向链表结点
    int key;
    int value;
    Node next;
    Node prev;
    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}
class LRUCache {
    private Map<Integer, Node> mp = new HashMap<>();
    private int size;
    private Node head, tail;
    public LRUCache(int capacity) {
        size = capacity;
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }
    public int get(int key) {
        if(!mp.containsKey(key)) return -1;
        Node n = mp.get(key);
        moveToTail(n, n.value); // 最近调用过的移动到尾部
        return n.value;
    }
    public void put(int key, int value) {
        if(mp.containsKey(key)) {
            moveToTail(mp.get(key), value); // 更新过的移动到尾部
        } else {
            if(mp.size() == size) { // 如果空间不足，去掉头
                Node n = head.next;
                del(n);
                mp.remove(n.key);
            }
            Node n = new Node(key, value);
            insertToTail(n); // 新加入的放在尾部
            mp.put(key, n);
        }
    }
    public void del(Node n) {
        n.prev.next = n.next;
        n.next.prev = n.prev;
    }
    public void moveToTail(Node n, int v) {
        del(n); // 删除该节点的老位置
        n.value = v; // 更新该节点的值
        insertToTail(n); // 将该节点放到尾部
    }
    public void insertToTail(Node n) {
        tail.prev.next = n;
        n.prev = tail.prev;
        n.next = tail;
        tail.prev = n;
    }
}

// -------

// 链表排序
// 给定链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表
// head = [4,2,1,3] => [1,2,3,4]
class Solution {
    public ListNode sortList(ListNode head) {
        if(head == null || head.next == null) return head;
        // 将链表分割成前后两段
		ListNode secondHead = Split(head);
        // 对两段链表分别进行排序
		head = sortList(head);
        secondHead = sortList(secondHead);
        // 链表合并时进行排序
		return Merge(head, secondHead);
    }
    public ListNode Merge(ListNode head1, ListNode head2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while(head1 != null && head2 != null) {
            if(head1.val <= head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        if(head1 != null) {
			cur.next = head1;
		} else {
			cur.next = head2;
		}
        return dummy.next;
    }
    public ListNode Split(ListNode head) {
        ListNode slow = head, fast = head.next;
		// 利用快慢指针找到当前链表的中点，并将链表从中间分割，返回中间点
        while(fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        return newHead;
    }
}

// 合并排序链表
// 给定一个链表数组，每个链表都已经按升序排列
// 请将所有链表合并到一个升序链表中，返回合并后的链表
// lists = [[1,4,5],[1,3,4],[2,6]] => [1,1,2,3,4,4,5,6]
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        return merge(lists, 0, lists.length - 1);
    }
    public ListNode merge(ListNode[] lists, int l, int r) {
        if(l == r) {
            return lists[l];
        }
        if(l > r) {
            return null;
        }
        int mid = (r - l) / 2 + l;
        // 二分法
		return mergeTwoList(merge(lists, l, mid), merge(lists, mid + 1, r));
    } 
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = new ListNode(-1);
        ListNode node = head;
        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                node.next = l1;
                l1 = l1.next;
            } else {
                node.next = l2;
                l2 = l2.next;
            }
            node = node.next;
        }
        node.next = l1 == null ? l2 : l1;
        return head.next;
    }
}
