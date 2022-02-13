package linkedList;

/**
 * 给你单链表的头指针 head 和两个整数left 和 right ，其中left <= right 。请你反转从位置 left 到位置 right 的链表节点，
 * 返回 反转后的链表 。
 *
 * medium
 */
public class LL06ReverseBetween {

    public static void main(String[] args) {
        int[] input1 = new int[]{1,2,3,4,5};
        ListNode list1 = ListNode.buildListNode(input1);
        System.out.println(new LL06ReverseBetween().reverseBetween(list1, 2, 4));
    }


    /**
     * 方法二：一次遍历「穿针引线」反转链表（头插法）
     *
     * 复杂度分析：
     *
     * 时间复杂度：O(N)，其中 N 是链表总节点数。最多只遍历了链表一次，就完成了反转。
     *
     * 空间复杂度：O(1)。只使用到常数个变量。
     * @param head
     * @param left
     * @param right
     * @return
     */
    /*private ListNode reverseBetween(ListNode head, int left, int right) {

        ListNode dummyNode = new ListNode(-1);

        dummyNode.next = head;

        ListNode prev = dummyNode;

        for (int i=0 ; i< left -1; i++) {
            prev = prev.next;
        }

        ListNode curr = prev.next;

        ListNode next;

        for (int i = 0; i < right-left; i++) {
            next = curr.next;
            curr.next = next.next;
            next.next = prev.next;
            prev.next = next;
        }

        return dummyNode.next;
    }*/

    public ListNode reverseBetween(ListNode head, int left, int right) {
        // 设置 dummyNode 是这一类问题的一般做法
        ListNode dummyNode = new ListNode(-1);
        dummyNode.next = head;
        ListNode pre = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        for (int i = 0; i < right - left; i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = pre.next;
            pre.next = next;
        }
        return dummyNode.next;
    }
}
