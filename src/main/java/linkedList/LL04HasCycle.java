package linkedList;

import java.util.HashSet;
import java.util.Set;

public class LL04HasCycle {

    /**
     * 给你一个链表的头节点 head ，判断链表中是否有环。
     *
     * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos
     * 来表示链表尾连接到链表中的位置（索引从 0 开始）。注意：pos 不作为参数进行传递。仅仅是为了标识链表的实际情况。
     *
     * 如果链表中存在环，则返回 true 。 否则，返回 false 。
     */


    /**
     * 方法一：哈希表
     * 思路及算法
     *
     * 最容易想到的方法是遍历所有节点，每次遍历到一个节点时，判断该节点此前是否被访问过。
     * 具体地，我们可以使用哈希表来存储所有已经访问过的节点。每次我们到达一个节点，如果该节点已经存在于哈希表中，则说明该链表是环形链表，否则
     * 就将该节点加入哈希表中。重复这一过程，直到我们遍历完整个链表即可。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。最坏情况下我们需要遍历每个节点一次。
     *
     * 空间复杂度：O(N)，其中 N 是链表中的节点数。主要为哈希表的开销，最坏情况下我们需要将每个节点插入到哈希表中一次。
     * @param head
     * @return
     */
/*    private boolean hasCycle(ListNode head) {
        Set setNode = new HashSet<ListNode>();

        while (head != null) {
            if (!setNode.add(head)) {
                return true;
            }

            head = head.next;
        }

        return false;
    }*/

    /**
     * 方法二：快慢指针
     * 思路及算法
     *
     * 本方法需要读者对「Floyd 判圈算法」（又称龟兔赛跑算法）有所了解。
     * 假想「乌龟」和「兔子」在链表上移动，「兔子」跑得快，「乌龟」跑得慢。当「乌龟」和「兔子」从链表上的同一个节点开始移动时，如果该链表中没有环，
     * 那么「兔子」将一直处于「乌龟」的前方；如果该链表中有环，那么「兔子」会先于「乌龟」进入环，并且一直在环内移动。等到「乌龟」进入环时，由于「兔子」的速度快，
     * 它一定会在某个时刻与乌龟相遇，即套了「乌龟」若干圈。
     * 我们可以根据上述思路来解决本题。具体地，我们定义两个指针，一快一满。慢指针每次只移动一步，而快指针每次移动两步。初始时，慢指针在位置 head，
     * 而快指针在位置 head.next。这样一来，如果在移动的过程中，快指针反过来追上慢指针，就说明该链表为环形链表。否则快指针将到达链表尾部，该链表不为环形链表。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(N)，其中 N 是链表中的节点数。
     * 当链表中不存在环时，快指针将先于慢指针到达链表尾部，链表中每个节点至多被访问两次。
     * 当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 N 轮。
     * 空间复杂度：O(1)。我们只使用了两个指针的额外空间。
     */

    private boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (slow != fast) {

            if (fast == null || fast.next == null) {
                return false;
            }

            slow = slow.next;
            fast = fast.next.next;
        }

        return true;
    }
}
