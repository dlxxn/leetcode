package linkedList;

public class ListNodeRandom {
    int val;
    ListNodeRandom next;
    ListNodeRandom random;

    public ListNodeRandom(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }

    public static void main(String[] args) {
        int[] input = new int[]{1, 2, 3, 4, 5};
        ListNodeRandom head = buildListNode(input);
        while (head != null) {
            System.out.println("val: " + head.val + "\tlistNode: " + head.next);
            head = head.next;
        }
    }

    /**
     * 构造ListNode
     */
    public static ListNodeRandom buildListNode(int[] input) {
        ListNodeRandom first = null, last = null, newNode;
        if (input.length > 0) {
            for (int i = 0; i < input.length; i++) {
                newNode = new ListNodeRandom(input[i]);
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
