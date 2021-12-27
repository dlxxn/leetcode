package binaryTree;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 中序遍历
 * 首先我们需要了解什么是二叉树的中序遍历：按照访问左子树——根节点——右子树的方式遍历这棵树，而在访问左子树或者右子树的时候我们按照同样的方式遍历，直到遍历完整棵树
 */
public class BT04InorderTraversal {
    public static void main(String[] args) {
        String str = "[1,2,3,4,5,6,null]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT04InorderTraversal().inorderTraversal(treeNode));
    }

    /**
     * 递归遍历
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
     * 空间复杂度：O(n)。空间复杂度取决于递归的栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
     */

//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//        inOrder(root, res);
//
//        return res;
//    }
//
//    public void inOrder(TreeNode root, List<Integer> res) {
//        if (root == null) {
//            return;
//        }
//
//        inOrder(root.left, res);
//        res.add(root.val);
//        inOrder(root.right, res);
//    }

    /**
     * 迭代
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 为二叉树节点的个数。二叉树的遍历中每个节点会被访问一次且只会被访问一次。
     * 空间复杂度：O(n)。空间复杂度取决于栈深度，而栈深度在二叉树为一条链的情况下会达到 O(n) 的级别。
     */

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();

        Deque<TreeNode> stk = new LinkedList<TreeNode>();

        while (root != null || !stk.isEmpty()) {
            while (root != null) {
                stk.push(root);
                root = root.left;
            }

            root = stk.pop();
            res.add(root.val);

            root = root.right;
        }

        return res;
    }

    /**
     * 莫里斯遍历
     * 用递归和迭代的方式都使用了辅助的空间，而莫里斯遍历的优点是没有使用任何辅助空间。
     * 缺点是改变了整个树的结构，强行把一棵二叉树改成一段链表结构。
     * 时间复杂度:找到每个前驱节点的复杂度是 O(n)，因为 n 个节点的二叉树有 n-1 条边，每条边只可能使用 2 次(一次定位到节点，一次找到前驱节点)，故时间复杂度为 O(n)
     * 空间复杂度：O(1)
     */

//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<Integer>();
//        TreeNode pre = null;
//        while(root!=null) {
//            //如果左节点不为空，就将当前节点连带右子树全部挂到
//            //左节点的最右子树下面
//            if(root.left!=null) {
//                pre = root.left;
//                while(pre.right!=null) {
//                    pre = pre.right;
//                }
//                pre.right = root;
//                //将root指向root的left
//                TreeNode tmp = root;
//                root = root.left;
//                tmp.left = null;
//                //左子树为空，则打印这个节点，并向右边遍历
//            } else {
//                res.add(root.val);
//                root = root.right;
//            }
//        }
//        return res;
//    }

//    public List<Integer> inorderTraversal(TreeNode root) {
//        List<Integer> res = new ArrayList<>();
//        TreeNode pre = null;
//
//        while (root != null) {
//            if (root.left != null) {
//                pre = root.left;
//                while (pre.right != null) {
//                    pre = pre.right;
//                }
//
//                pre.right = root;
//
//                TreeNode tmp = root;
//                root = root.left;
//                tmp.left = null;
//            } else  {
//                res.add(root.val);
//                root = root.right;
//            }
//        }
//        return res;
//    }
}
