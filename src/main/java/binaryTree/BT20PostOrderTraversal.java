package binaryTree;

import java.util.*;

/**
 * 后续遍历 左--右--根
 */
public class BT20PostOrderTraversal {
    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,null]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT20PostOrderTraversal().postOrderTraversal(treeNode));
    }

    /**
     * 递归
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 n 是二叉搜索树的节点数。每一个节点恰好被遍历一次。
     *
     * 空间复杂度：O(n)，为递归过程中栈的开销，平均情况下为 O(logn)，最坏情况下树呈现链状，为 O(n)。
     * @param root
     * @return
     */
    /*public List<Integer> postOrderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postOrder(root, res);
        return res;
    }

    private void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) {
            return;
        }

        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }*/

    /**
     * 迭代
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 n 是二叉搜索树的节点数。每一个节点恰好被遍历一次。
     *
     * 空间复杂度：O(n)，为迭代过程中显式栈的开销，平均情况下为 O(logn)，最坏情况下树呈现链状，为 O(n)。
     * @param root
     * @return
     */
    public List<Integer> postOrderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//
//        Deque<TreeNode> stk = new LinkedList<TreeNode>();
//
//        while (root != null || !stk.isEmpty()) {
//            while (root != null) {
//                stk.push(root);
//                root = root.left;
//            }
//
//            root = stk.poll();
//            res.add(root.val);
//            root = root.right;
//        }
//
//        return res;

        List<Integer> res = new ArrayList<Integer>();
        if (root == null) {
            return res;
        }

        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        TreeNode prev = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 莫里斯
     * 思路与算法
     *
     * 有一种巧妙的方法可以在线性时间内，只占用常数空间来实现后序遍历。这种方法由 J. H. Morris 在 1979 年的论文「Traversing Binary Trees Simply and Cheaply」
     * 中首次提出，因此被称为 Morris 遍历。
     *
     * Morris 遍历的核心思想是利用树的大量空闲指针，实现空间开销的极限缩减。其后序遍历规则总结如下：
     *
     * 新建临时节点，令该节点为 root；
     *
     * 如果当前节点的左子节点为空，则遍历当前节点的右子节点；
     *
     * 如果当前节点的左子节点不为空，在当前节点的左子树中找到当前节点在中序遍历下的前驱节点；
     *
     * 如果前驱节点的右子节点为空，将前驱节点的右子节点设置为当前节点，当前节点更新为当前节点的左子节点。
     *
     * 如果前驱节点的右子节点为当前节点，将它的右子节点重新设为空。倒序输出从当前节点的左子节点到该前驱节点这条路径上的所有节点。当前节点更新为当前节点的右子节点。
     *
     * 重复步骤 2 和步骤 3，直到遍历结束。
     *
     * 这样我们利用 Morris 遍历的方法，后序遍历该二叉搜索树，即可实现线性时间与常数空间的遍历。
     *
     * 复杂度分析
     *
     * 时间复杂度：O(n)，其中 nn 是二叉树的节点数。没有左子树的节点只被访问一次，有左子树的节点被访问两次。
     *
     * 空间复杂度：O(1)。只操作已经存在的指针（树的空闲指针），因此只需要常数的额外空间。
     * @param root
     * @return
     */
    /*public List<Integer> postOrderTraversal(TreeNode root) {
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
                    p2.right = p1;
                    p1 = p1.left;
                    continue;
                } else {
                    p2.right = null;
                    addPath(res, p1.left);
                }
            }
            p1 = p1.right;
        }
        addPath(res, root);
        return res;
    }

    public void addPath(List<Integer> res, TreeNode node) {
        int count = 0;
        while (node != null) {
            ++count;
            res.add(node.val);
            node = node.right;
        }
        int left = res.size() - count, right = res.size() - 1;
        while (left < right) {
            int temp = res.get(left);
            res.set(left, res.get(right));
            res.set(right, temp);
            left++;
            right--;
        }
    }*/
}
