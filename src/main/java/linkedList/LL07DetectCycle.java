package linkedList;

import java.util.HashSet;

/**
 * 给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
 *
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连
 * 接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 *
 * medium
 * */
public class LL07DetectCycle {

    /**
     * 方法一：哈希表
     * 思路与算法
     *
     * 一个非常直观的思路是：我们遍历链表中的每个节点，并将它记录下来；一旦遇到了此前遍历过的节点，就可以判定链表中存在环。借助哈希表可以很方便地实现。
     复杂度分析

     时间复杂度：O(N)，其中 N 为链表中节点的数目。我们恰好需要访问链表中的每一个节点。

     空间复杂度：O(N)，其中 N 为链表中节点的数目。我们需要将链表中的每个节点都保存在哈希表当中。
     */

    private ListNode detectCycle(ListNode head) {

        HashSet<ListNode> visited = new HashSet<>();

        ListNode pos = head;

        while (pos != null) {
            if (visited.contains(pos)) {
                return pos;
            } else {
                visited.add(pos);
            }

            pos = pos.next;
        }

        return null;
    }

    /**
     * 方法二：快慢指针
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 N 为链表中节点的数目。在最初判断快慢指针是否相遇时，slow 指针走过的距离不会超过链表的总长度；随后寻找入环点时，
     * 走过的距离也不会超过链表的总长度。因此，总的执行时间为 O(N)+O(N)=O(N)+O(N)=O(N)。
     *
     * 空间复杂度：O(1)。我们只使用了slow,fast,ptr 三个指针。
     */
}
