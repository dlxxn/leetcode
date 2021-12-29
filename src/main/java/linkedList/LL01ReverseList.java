package linkedList;

/**
 * 翻转链表
 * easy
 */
public class LL01ReverseList {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5};
        ListNode head = ListNode.buildListNode(input);
        System.out.println(new LL01ReverseList().reverseList(head));
    }

    /**
     * 方法一：迭代
     * 假设存在链表 1→2→3→∅，我们想要把它改成∅←1←2←3。
     *
     * 在遍历列表时，将当前节点的next 指针改为指向前一个元素。由于节点没有引用其上一个节点，因此必须事先存储其前一个元素。
     * 在更改引用之前，还需要另一个指针来存储下一个节点。不要忘记在最后返回新的头引用！
     * @param head
     * @return
     */
    /*public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }*/

    /*public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;

        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }*/

    /**
     * 方法二：递归
     * 递归版本稍微复杂一些，其关键在于反向工作。假设列表的其余部分已经被反转，现在我们应该如何反转它前面的部分？
     *
     * 假设列表为：
     * n1→…→nk−1→nk→nk+1→…→nm→∅若从节点 nk+1到nm已经被反转，而我们正处于nk。
     * n1→…→nk−1→nk→nk+1←…←nm
     * 我们希望 nk+1的下一个节点指向 nk。
     * 所以，nk.next.next=nk。
     * 要小心的是 n1的下一个必须指向∅ 。如果你忽略了这一点，你的链表中可能会产生循环。如果使用大小为 22的链表测试代码，则可
     * 能会捕获此错误。
     *
     * reverseList: head=1
     *     reverseList: head=2
     * 	    reverseList: head=3
     * 		    reverseList:head=4
     * 			    reverseList:head=5
     * 					终止返回 cur = 5
     * 				cur = 5->4->null
     * 				4.next.next->4，即5->4
     * 			cur=5->4->3->null
     * 			3.next.next->3，即4->3
     * 		cur = 5->4->3->2->null
     * 		2.next.next->2，即3->2
     * 	cur = 5->4->3->2->1->null
     * 	1.next.next->1，即2->1
     *
     * 	最后返回cur
     * 	不妨假设链表为1，2，3，4，5。按照递归，当执行reverseList（5）的时候返回了5这个节点，reverseList(4)中的p就是5这个节点
     * 	，我们看看reverseList（4）接下来执行完之后，5->next = 4, 4->next = null。这时候返回了p这个节点，也就是链表5->4->null，
     * 	接下来执行reverseList（3），代码解析为4->next = 3,3->next = null，这个时候p就变成了，5->4->3->null, reverseList(2),
     * 	reverseList(1)依次类推，p就是:5->4->3->2->1->null
     *
     * 	复杂度分析
     *
     * 时间复杂度：O(n)，假设 n 是列表的长度，那么时间复杂度为 O(n)。
     * 空间复杂度：O(n)，由于使用递归，将会使用隐式栈空间。递归深度可能会达到 n 层。
     * @return
     */
    public ListNode reverseList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode p = reverseList(head.next);

        head.next.next = head;
        head.next = null;

        return p;

    }




}
