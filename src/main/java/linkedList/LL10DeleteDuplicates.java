package linkedList;

/**
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，
 * 只留下不同的数字 。返回 已排序的链表 。
 *
 * medium
 */
public class LL10DeleteDuplicates {

    public static void main(String[] args) {
        int[] input1 = new int[]{1, 2, 2, 3, 4, 5, 5, 6};
        ListNode list1 = ListNode.buildListNode(input1);
        System.out.println(new LL10DeleteDuplicates().deleteDuplicates(list1));
    }

    /**
     * 方法一：一次遍历
     * 思路与算法
     *
     * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的
     * 元素。由于链表的头节点可能会被删除，因此我们需要额外使用一个哑节点（dummy node）指向链表的头节点。
     *
     * 具体地，我们从指针 ur 指向链表的哑节点，随后开始对链表进行遍历。如果当前cur.next 与 cur.next.next 对应的元素相同，那
     * 么我们就需要将cur.next 以及所有后面拥有相同元素值的链表节点全部删除。我们记下这个元素值 x，随后不断将cur.next 从链表
     * 中移除，直到cur.next 为空节点或者其元素值不等于 x 为止。此时，我们将链表中所有元素值为 x 的节点全部删除。
     *
     * 如果当前cur.next 与 cur.next.next 对应的元素不相同，那么说明链表中只有一个元素值为 cur.next 的节点，那么我们就可以将
     * cur 指向 cur.next。
     *
     * 当遍历完整个链表之后，我们返回链表的的哑节点的下一个节点dummy.next 即可。
     *
     * 细节需要注意cur.next 以及 cur.next.next 可能为空节点，如果不加以判断，可能会产生运行错误
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 n 是链表的长度。
     * 空间复杂度：O(1)。
     *
     * @return
     */
    /*private ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummyNode = new ListNode(0, head);

        ListNode cur = dummyNode;

        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;

                while (cur.next != null & cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummyNode.next;
    }*/

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }

        ListNode dummy = new ListNode(0, head);

        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            if (cur.next.val == cur.next.next.val) {
                int x = cur.next.val;
                while (cur.next != null && cur.next.val == x) {
                    cur.next = cur.next.next;
                }
            } else {
                cur = cur.next;
            }
        }

        return dummy.next;
    }
}
