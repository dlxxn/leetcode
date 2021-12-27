package binaryTree;

/**
 * 剑指 Offer 36. 二叉搜索树与双向链表 中序遍历
 * medium
 */
public class BT17TreeToDoublyList {
    public static void main(String[] args) {
        String str = "[4,2,5,1,3]";
        //String str = "[1,2,2,null,3,null,3]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT17TreeToDoublyList().treeToDoublyList(treeNode));
    }

    TreeNode head, pre;

    /**
     * 解题思路：
     * 本文解法基于性质：二叉搜索树的中序遍历为 递增序列 。
     * 将 二叉搜索树 转换成一个 “排序的循环双向链表” ，其中包含三个要素：
     * 排序链表： 节点应从小到大排序，因此应使用 中序遍历 “从小到大”访问树的节点。
     * 双向链表： 在构建相邻节点的引用关系时，设前驱节点 pre 和当前节点 cur ，不仅应构建 pre.right = cur ，也应构建 cur.left = pre 。
     * 循环链表： 设链表头节点 head 和尾节点 tail ，则应构建 head.left = tail 和 tail.right = head 。
     *
     * 中序遍历左-根-右， 根据以上分析，考虑使用中序遍历访问树的各节点 cur ；并在访问每个节点时构建 cur 和前驱节点 pre 的引用指向；
     * 中序遍历完成后，最后构建头节点和尾节点的引用指向即可。
     *
     * 复杂度分析：
     * 时间复杂度 O(N) ： N 为二叉树的节点数，中序遍历需要访问所有节点。
     * 空间复杂度 O(N) ： 最差情况下，即树退化为链表时，递归深度达到 N，系统使用 O(N) 栈空间。
     * @param root
     * @return
     */
    public TreeNode treeToDoublyList(TreeNode root) {
        if (root == null) {
            return null;
        }
        dfs(root);

        pre.right = head;
        //进行头节点和尾节点的相互指向，这两句的顺序也是可以颠倒的
        head.left = pre;

        return head;
    }

    public void dfs(TreeNode cur) {
        if (cur == null) {
            return;
        }
        dfs(cur.left);

        //pre用于记录双向链表中位于cur左侧的节点，即上一次迭代中的cur,当pre==null时，cur左侧没有节点,即此时cur为双向链表中的头节点
        if (pre == null) {
            head = cur;
        } else {
            //反之，pre!=null时，cur左侧存在节点pre，需要进行pre.right=cur的操作。
            pre.right = cur;
        }

        //pre是否为null对这句没有影响,且这句放在上面两句if else之前也是可以的。
        cur.left = pre;

        //pre指向当前的cur
        pre = cur;
        //全部迭代完成后，pre指向双向链表中的尾节点
        dfs(cur.right);
    }
}
