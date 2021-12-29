package linkedList;

public class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5};
        ListNode head = buildListNode(input);
        while (head != null) {
            System.out.println("val: " + head.val + "\tlistNode: " + head.next);
            head = head.next;
        }
    }

    /**
     * 构造ListNode
     */
    public static ListNode buildListNode(int[] input) {
        ListNode first = null, last = null, newNode;
        if (input.length > 0) {
            for (int i = 0; i < input.length; i++) {
                newNode = new ListNode(input[i]);
                newNode.next = null;
                if (first == null) {
                    first = newNode;
                    last = newNode;
                } else {
                    last.next = newNode;
                    last = newNode;
                }
            }
        }
        return first;
    }
}
