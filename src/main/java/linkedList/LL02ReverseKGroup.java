package linkedList;

/**
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * k是一个正整数，它的值小于或等于链表的长度。
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * hard
 */
public class LL02ReverseKGroup {
    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5};
        ListNode head = ListNode.buildListNode(input);
        System.out.println(new LL02ReverseKGroup().reverseKGroup(head, 2));
    }

    /**
     * 步骤分解:
     *
     * 1链表分区为已翻转部分+待翻转部分+未翻转部分
     * 2每次翻转前，要确定翻转链表的范围，这个必须通过 k 此循环来确定
     * 3需记录翻转链表前驱和后继，方便翻转完成后把已翻转部分和未翻转部分连接起来
     * 4初始需要两个变量 pre 和 end，pre 代表待翻转链表的前驱，end 代表待翻转链表的末尾
     * 5经过k此循环，end 到达末尾，记录待翻转链表的后继 next = end.next
     * 6翻转链表，然后将三部分链表连接起来，然后重置 pre 和 end 指针，然后进入下一次循环
     * 7特殊情况，当翻转部分长度不足 k 时，在定位 end 完成后，end==null，已经到达末尾，说明题目已完成，直接返回即可
     * 8时间复杂度为 O(n*K) 最好的情况为 O(n) 最差的情况 O(n^2)
     * 9空间复杂度为 O(1) 除了几个必须的节点指针外，我们并没有占用其他空间
     * @param head
     * @param k
     * @return
     */
/*    private ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        //定义一个假的节点。
        ListNode dummy = new ListNode(0);
        //假节点的next指向head。
        // dummy->1->2->3->4->5
        dummy.next = head;
        //初始化pre和end都指向dummy。pre指每次要翻转的链表的头结点的上一个节点。end指每次要翻转的链表的尾节点
        ListNode pre = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            //循环k次，找到需要翻转的链表的结尾,这里每次循环要判断end是否等于空,因为如果为空，end.next会报空指针异常。
            //dummy->1->2->3->4->5 若k为2，循环2次，end指向2
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            //如果end==null，即需要翻转的链表的节点数小于k，不执行翻转。
            if (end == null) {
                break;
            }
            //先记录下end.next,方便后面链接链表
            ListNode next = end.next;
            //然后断开链表
            end.next = null;
            //记录下要翻转链表的头节点
            ListNode start = pre.next;
            //翻转链表,pre.next指向翻转后的链表。1->2 变成2->1。 dummy->2->1
            pre.next = reverse(start);
            //翻转后头节点变到最后。通过.next把断开的链表重新链接。
            start.next = next;
            //将pre换成下次要翻转的链表的头结点的上一个节点。即start
            pre = start;
            //翻转结束，将end置为下次要翻转的链表的头结点的上一个节点。即start
            end = start;
        }
        return dummy.next;
    }

    //链表翻转
    // 例子：   head： 1->2->3->4
    public ListNode reverse(ListNode head) {
        //单链表为空或只有一个节点，直接返回原单链表
        if (head == null || head.next == null){
            return head;
        }
        //前一个节点指针
        ListNode preNode = null;
        //当前节点指针
        ListNode curNode = head;
        //下一个节点指针
        ListNode nextNode = null;
        while (curNode != null){
            nextNode = curNode.next;//nextNode 指向下一个节点,保存当前节点后面的链表。
            curNode.next=preNode;//将当前节点next域指向前一个节点   null<-1<-2<-3<-4
            preNode = curNode;//preNode 指针向后移动。preNode指向当前节点。
            curNode = nextNode;//curNode指针向后移动。下一个节点变成当前节点
        }
        return preNode;

    }*/

    /*public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i =0; i < k && end != null; i++) {
                end = end.next;
            }

            if (end == null) {
                break;
            }
            ListNode next = end.next;
            end.next = null;

            ListNode start = prev.next;
            prev.next = reverse(start);

            start.next = next;
            prev = start;
            end = start;

        }

        return dummy.next;
    }*/

    private ListNode reverseKGroup(ListNode head, int k) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode end = dummy;

        while (end.next != null) {
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }

            if (end == null) {
                break;
            }

            ListNode next = end.next;
            end.next = null;

            ListNode start = prev.next;
            prev.next = reverse(start);

            start.next = next;

            prev = start;
            end = start;

        }

        return dummy.next;
    }

    private ListNode reverse(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }

        return prev;
    }
}
