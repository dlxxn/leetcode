package binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树转为单链表
 *
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 *
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 */
public class BT26Flatten {
    public static void main(String[] args) {
        String str = "[1,2,5,3,4,null,6]";
        //String str = "[1,2,2,null,3,null,3]";
        TreeNode treeNode = TreeNode.mkTree(str);
        new BT26Flatten().flatten(treeNode);
        //System.out.println(treeNode);
    }

    /**
     * 方法一：前序遍历
     * 将二叉树展开为单链表之后，单链表中的节点顺序即为二叉树的前序遍历访问各节点的顺序。因此，可以对二叉树进行前序遍历，获得各节点被访问到的顺序。
     * 由于将二叉树展开为链表之后会破坏二叉树的结构，因此在前序遍历结束之后更新每个节点的左右子节点的信息，将二叉树展开为单链表。
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 n 是二叉树的节点数。前序遍历的时间复杂度是 O(n)，前序遍历之后，需要对每个节点更新左右子节点的信息，时间复杂度也是 O(n)。
     *
     * 空间复杂度：O(n)，其中 n 是二叉树的节点数。空间复杂度取决于栈（递归调用栈或者迭代中显性使用的栈）和存储前序遍历结果的列表的大小，栈内的元素个数不会超过 nn，前序遍历列表中的元素个数是 n。
     */

    /**
     * 递归
     * @param root
     */
/*    private void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<TreeNode>();
        preOrder(root, list);

        for (int i = 1; i< list.size(); i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }

        System.out.println(root);
    }

    private void preOrder(TreeNode root, List<TreeNode> list) {
        if (root != null) {
            list.add(root);
            preOrder(root.left, list);
            preOrder(root.right, list);
        }
    }*/

    /**
     * 迭代
     * @param root
     */
    /*private void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode node = root;

        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }

        for (int i = 1; i<list.size(); i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }*/

    /**
     * 方法三：寻找前驱节点
     * 前两种方法都借助前序遍历，前序遍历过程中需要使用栈存储节点。有没有空间复杂度是 O(1) 的做法呢？
     *
     * 注意到前序遍历访问各节点的顺序是根节点、左子树、右子树。如果一个节点的左子节点为空，则该节点不需要进行展开操作。如果一个节点的左子节点不为空，
     * 则该节点的左子树中的最后一个节点被访问之后，该节点的右子节点被访问。该节点的左子树中最后一个被访问的节点是左子树中的最右边的节点，也是该节点的前驱节点。
     * 因此，问题转化成寻找当前节点的前驱节点。
     *
     * 具体做法是，对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，将当前节点的右子节点赋给前驱节点的右子节点，
     * 然后将当前节点的左子节点赋给当前节点的右子节点，并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 是二叉树的节点数。展开为单链表的过程中，需要对每个节点访问一次，在寻找前驱节点的过程中，每个节点最多被额外访问一次。
     * 空间复杂度：O(1)。
     * @param root
     */
    /*public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null) {
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
        System.out.println(root);
    }*/

    public void flatten(TreeNode root) {
        TreeNode curr = root;

        while (curr != null) {
            if (curr.left != null) {
                TreeNode next = curr.left;
                TreeNode processor = next;
                while (processor.right != null) {
                    processor = processor.right;
                }
                processor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }

            curr = curr.right;
        }

        System.out.println(root);
    }
}
