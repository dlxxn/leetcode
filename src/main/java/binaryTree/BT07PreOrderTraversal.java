package binaryTree;

import java.util.ArrayList;
import java.util.List;

/**
 * 前序遍历
 * easy
 * 首先我们需要了解什么是二叉树的前序遍历：按照访问根节点——左子树——右子树的方式遍历这棵树，
 * 而在访问左子树或者右子树的时候，我们按照同样的方式遍历，直到遍历完整棵树。因此整个遍历过程天然具有递归的性质，我们可以直接用递归函数来模拟这一过程。
 */
public class BT07PreOrderTraversal {
    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,null]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT07PreOrderTraversal().preOrderTraversal(treeNode));
    }

    /**
     * 递归遍历
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 nn 是二叉树的节点数。每一个节点恰好被遍历一次。
     *
     * 空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为 O(\log n)，最坏情况下树呈现链状，为 O(n)。
     * @param root
     * @return
     */
//    public List<Integer> preOrderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//
//        preOrder(root, res);
//
//        return res;
//
//    }
//
//    private void preOrder(TreeNode root, List<Integer> res) {
//        if (root == null) {
//            return;
//        }
//        res.add(root.val);
//
//        preOrder(root.left, res);
//
//        preOrder(root.right, res);
//    }

    /**
     * 迭代
     * @param root
     * @return
     */
    /*public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        Deque<TreeNode> deque = new LinkedList<TreeNode>();

        while (root != null || !deque.isEmpty()) {

            while (root != null) {
                res.add(root.val);
                deque.push(root);
                root = root.left;
            }

            root = deque.poll();
            root = root.right;
        }

        return res;
    }*/

    /**
     * 莫里斯
     * 思路与算法
     * 有一种巧妙的方法可以在线性时间内，只占用常数空间来实现前序遍历。这种方法由 J. H. Morris 在 1979 年的论文「Traversing Binary Trees Simply and Cheaply」中首次提出，因此被称为 Morris 遍历。
     * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其前序遍历规则总结如下：
     * 新建临时节点，令该节点为 root；
     * 如果当前节点的左子节点为空，将当前节点加入答案，并遍历当前节点的右子节点；
     * 如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点：
     * 如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点。然后将当前节点加入答案，并将前驱节点的右子节点更新为当前节点。当前节点更新为当前节点的左子节点。
     * 如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。当前节点更新为当前节点的右子节点。
     * 重复步骤 2 和步骤 3，直到遍历结束。
     * 这样我们利用 Morris 遍历的方法，前序遍历该二叉树，即可实现线性时间与常数空间的遍历。
     *
     * 复杂度分析
     * 时间复杂度：O(n)，其中 nn 是二叉树的节点数。没有左子树的节点只被访问一次，有左子树的节点被访问两次。
     * 空间复杂度：O(1)。只操作已经存在的指针（树的空闲指针），因此只需要常数的额外空间。
     */

    public List<Integer> preOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        TreeNode p1 = root, p2 = null;

        while (p1 != null) {
            p2 = p1.left;
            if (p2 != null) {
                while (p2.right != null && p2.right != p1) {
                    p2 = p2.right;
                }
                if (p2.right == null) {
                    res.add(p1.val);
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                }
            } else {
                res.add(p1.val);
            }
            p1 = p1.right;
        }
        return res;
    }
}
