package linkedList;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * 给你两个单链表的头节点 headA 和 headB ，请你找出并返回两个单链表相交的起始
 * 节点。如果两个链表不存在相交节点，返回 null 。
 * easy
 */
public class LL05GetIntersectionNode {

    public static void main(String[] args) {
        int[] input1 = new int[]{1,9,1,2,4};
        int[] input2 = new int[]{3,2,4};
        ListNode list1 = ListNode.buildListNode(input1);
        ListNode list2 = ListNode.buildListNode(input2);
        System.out.println(new LL05GetIntersectionNode().getIntersectionNode(list1, list2));
    }


    /**
     *方法一：哈希集合
     * 思路和算法
     *
     * 判断两个链表是否相交，可以使用哈希集合存储链表节点。
     *
     * 首先遍历链表headA，并将链表headA 中的每个节点加入哈希集合中。然后遍历链表headB，对于遍历到的每个节点，判断该节点是否在哈希集合中：
     *
     * 如果当前节点不在哈希集合中，则继续遍历下一个节点；
     *
     * 如果当前节点在哈希集合中，则后面的节点都在哈希集合中，即从当前节点开始的所有节点都在两个链表的相交部分，因此在链表headB 中遍历到的第一个
     * 在哈希集合中的节点就是两个链表相交的节点，返回该节点。
     *
     * 如果链表headB中的所有节点都不在哈希集合中，则两个链表不相交，返回null。
     * 复杂度分析
     *
     * 时间复杂度：O(m+n)，其中 m 和 nn 是分别是链表 headA 和 headB 的长度。需要遍历两个链表各一次。
     *
     * 空间复杂度：O(m)，其中 m 是链表headA 的长度。需要使用哈希集合存储链表 headA 中的全部节点。
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<ListNode>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 方法二：双指针
     * 思路和算法
     *
     * 使用双指针的方法，可以将空间复杂度降至O(1)。
     *
     * 只有当链表headA 和 headB 都不为空时，两个链表才可能相交。因此首先判断链表headA 和 headB 是否为空，如果其中至少有一个链表为空，则两个链表一定不相交，
     * 返回null。
     *
     * 当链表}headA 和 headB 都不为空时，创建两个指针 pA 和 pB，初始时分别指向两个链表的头节点headA 和 headB，然后将两个指针
     * 依次遍历两个链表的每个节点。
     * 具体做法如下：
     *
     * 每步操作需要同时更新指针 pA 和 pB。
     *
     * 如果指针 pA 不为空，则将指针 pA 移到下一个节点；如果指针 pB 不为空，则将指针 pB 移到下一个节点。
     *
     * 如果指针 pA 为空，则将指针 pA 移到链表 headB 的头节点；如果指针 pB 为空，则将指针 pB 移到链表headA 的头节点。
     *
     * 当指针 pA 和 pB 指向同一个节点或者都为空时，返回它们指向的节点或者null。
     *
     * pA走过的路径为A链+B链
     *
     * pB走过的路径为B链+A链
     *
     * pA和pB走过的长度都相同，都是A链和B链的长度之和，相当于将两条链从尾端对齐，如果相交，则会提前在相交点相遇，
     * 如果没有相交点，则会在最后相遇。
     *
     * pA:1->2->3->4->5->6->null->9->5->6->null
     * pB:9->5->6->null->1->2->3->4->5->6->null
     */

 /*   public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA, pB = headB;
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }*/
}
