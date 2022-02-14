package linkedList;

/**
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 */
public class LL18RotateRightList {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4};
        ListNode head = ListNode.buildListNode(input);
        System.out.println(new LL18RotateRightList().ratateRight(head, 2));
    }

    /**
     * 方法一：闭合为环
     * 思路及算法
     *
     * 记给定链表的长度为 n，注意到当向右移动的次数 k≥n 时，我们仅需要向右移动k mod n 次即可。因为每 n 次移动都会让链表变
     * 为原状。这样我们可以知道，新链表的最后一个节点为原链表的第(n−1)−(k mod n) 个节点（从 0 开始计数）。
     *
     * 这样，我们可以先将给定的链表连接成环，然后将指定位置断开。
     *
     * 具体代码中，我们首先计算出链表的长度 n，并找到该链表的末尾节点，将其与头节点相连。这样就得到了闭合为环的链表。然后
     * 我们找到新链表的最后一个节点（即原链表的第(n−1)−(k mod n) 个节点），将当前闭合为环的链表断开，即可得到我们所需要的结果。
     *
     * 特别地，当链表长度不大于 1，或者 k为 n 的倍数时，新链表将与原链表相同，我们无需进行任何处理。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，最坏情况下，我们需要遍历该链表两次。
     *
     * 空间复杂度：O(1)，我们只需要常数的空间存储若干变量
     * @param head
     * @return
     */
    private ListNode ratateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }

        ListNode iter = head;
        int n = 1;
        while (iter.next != null) {
            iter = iter.next;
            n++;
        }

        int add = n - k % n;

        //k是n的倍数，链表直接返回
        if (add == n) {
            return head;
        }

        iter.next = head;

        while (add --> 0) {
            iter = iter.next;
        }
        ListNode ret = iter.next;
        iter.next = null;
        return  ret;

    }
}
