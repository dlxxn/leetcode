package binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 判断二叉树是否是对称的
 */
public class BT15Symmetric {
    public static void main(String[] args) {
        String str = "[1,2,2,3,4,4,3]";
        //String str = "[1,2,2,null,3,null,3]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT15Symmetric().isSymmetric(treeNode));
    }

    private boolean isSymmetric(TreeNode root) {
        return  check(root, root);
    }


    /**
     * 递归
     * 我们可以实现这样一个递归函数，通过「同步移动」两个指针的方法来遍历这棵树，p 指针和 q 指针一开始都指向这棵树的根，
     * 随后 p右移时，q 左移，p 左移时，q 右移。每次检查当前 p 和 q 节点的值是否相等，如果相等再判断左右子树是否对称。
     *
     * 复杂度分析
     *
     * 假设树上一共 n 个节点。
     *
     * 时间复杂度：这里遍历了这棵树，渐进时间复杂度为 O(n)。
     * 空间复杂度：这里的空间复杂度和递归使用的栈空间有关，这里递归层数不超过 nn，故渐进空间复杂度为 O(n)。
     */

    /*private boolean check(TreeNode p, TreeNode q) {
        if (p == null && q == null) {
            return true;
        }

        if (p == null ||q == null) {
            return false;
        }

        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }*/

    /**
     * 迭代
     * 思路和算法
     *
     * 「方法一」中我们用递归的方法实现了对称性的判断，那么如何用迭代的方法实现呢？首先我们引入一个队列，这是把递归程序改写成迭代程序的常用方法。
     * 初始化时我们把根节点入队两次。每次提取两个结点并比较它们的值（队列中每两个连续的结点应该是相等的，而且它们的子树互为镜像），然后将两个结点
     * 的左右子结点按相反的顺序插入队列中。当队列为空时，或者我们检测到树不对称（即从队列中取出两个不相等的连续结点）时，该算法结束。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，同「方法一」。
     * 空间复杂度：这里需要用一个队列来维护节点，每个节点最多进队一次，出队一次，队列中最多不会超过 n 个点，故渐进空间复杂度为 O(n)。
     */

    private boolean check(TreeNode u, TreeNode v) {
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(u);
        queue.offer(v);

        while (!queue.isEmpty()) {
            u = queue.poll();
            v = queue.poll();

            if (u == null && v == null) {
                continue;
            }
            if ((u == null || v == null) || (u.val != v.val)) {
                return false;
            }

            queue.offer(u.left);
            queue.offer(v.right);

            queue.offer(u.right);
            queue.offer(v.left);

        }

        return true;
    }
}
