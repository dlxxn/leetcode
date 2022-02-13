package linkedList;

import java.util.ArrayList;

/**
 * 给你一个链表数组，每个链表都已经按升序排列。
 *
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * hard
 */
public class LL08MergeKLists {

    public static void main(String[] args) {
        int[] input1 = new int[]{1,2,3};
        int[] input2 = new int[]{2,5};
        int[] input3 = new int[]{4,6};
        ListNode list1 = ListNode.buildListNode(input1);
        ListNode list2 = ListNode.buildListNode(input2);
        ListNode list3 = ListNode.buildListNode(input3);
        ArrayList<ListNode> listNodes = new ArrayList<>();
        listNodes.add(list1);
        listNodes.add(list2);
        listNodes.add(list3);
        System.out.println(new LL08MergeKLists().mergeKLists(listNodes));
    }

    /**
     * 复杂度分析
     *
     * 时间复杂度：假设每个链表的最长度是 n。在第一次合并后，ans 的长度为 n；第二次合并后，ans 的长度为2×n，第 i 次合并后，ans 的长度为
     * i×n。第 i 次合并的时间代价是 O(n + (i - 1))=O(i×n)，故渐进时间复杂度为 O(k^2 n)
     * 空间复杂度：没有用到与 k 和 n 规模相关的辅助空间，故渐进空间复杂度为 O(1)。
     * @param listNodes
     * @return
     */
    private ListNode mergeKLists(ArrayList<ListNode> listNodes) {
        ListNode ans = null;
        for (int i=0; i < listNodes.size(); i++) {
            ans = mergeTwoList(ans, listNodes.get(i));
        }

        return ans;
    }

    /**
     * 前置知识：合并两个有序链表
     * 思路
     * 在解决「合并K个排序链表」这个问题之前，我们先来看一个更简单的问题：如何合并两个有序链表？假设链表 a 和 b 的长度都是 n，如何在 O(n)的时
     * 间代价以及 O(1)的空间代价完成合并？ 这个问题在面试中常常出现，为了达到空间代价是 O(1)，我们的宗旨是「原地调整链表元素的 next 指针完成合并」。
     * 以下是合并的步骤和注意事项，对这个问题比较熟悉的读者可以跳过这一部分。此部分建议结合代码阅读。
     *
     * 首先我们需要一个变量 head 来保存合并之后链表的头部，你可以把 head 设置为一个虚拟的头（也就是 head 的 val 属性不保存任何值），这是为了方便代码的书写，
     * 在整个链表合并完之后，返回它的下一位置即可。
     * 我们需要一个指针 tail 来记录下一个插入位置的前一个位置，以及两个指针 aPtr 和 bPtr 来记录 a 和 b 未合并部分的第一位。注意这里的描述，
     * tail 不是下一个插入的位置，aPtr 和 bPtr 所指向的元素处于「待合并」的状态，也就是说它们还没有合并入最终的链表。 当然你也可以给他们赋予
     * 其他的定义，但是定义不同实现就会不同。
     * 当 aPtr 和 bPtr 都不为空的时候，取 val 熟悉较小的合并；如果 aPtr 为空，则把整个 bPtr 以及后面的元素全部合并；bPtr 为空时同理。
     * 在合并的时候，应该先调整 tail 的 next 属性，再后移 tail 和 *Ptr（aPtr 或者 bPtr）。那么这里 tail 和 *Ptr 是否存在先后顺序呢？
     * 它们谁先动谁后动都是一样的，不会改变任何元素的 next 指针。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)。
     * 空间复杂度：O(1)。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoList(ListNode l1, ListNode l2) {
        ListNode dummyNode = new ListNode(0);

        ListNode prev = dummyNode;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }

            prev = prev.next;
        }

        prev.next = l1 == null ?l2:l1;

        return dummyNode.next;
    }
}
