package binaryTree;

/**
 * 判断一个树是否是另一个棵树的子树
 *
 * 给你两棵二叉树 root 和 subRoot 。检验 root 中是否包含和 subRoot 具有相同结构和节点值的子树。如果存在，返回 true ；否则，返回 false 。
 *
 * 二叉树 tree 的一棵子树包括 tree 的某个节点和这个节点的所有后代节点。tree 也可以看做它自身的一棵子树。
 */
public class BT24IsSubTree {
    public static void main(String[] args) {
        //String strS = "[3,4,5,1,2]";
        String strS = "[3,4,5,1,2,null,null,null,null,0]";
        String strT = "[4,1,2]";
        TreeNode treeNodeS = TreeNode.mkTree(strS);
        TreeNode treeNodeT = TreeNode.mkTree(strT);
        //System.out.println(new BT24IsSubTree().isSubtree(treeNodeRoot, treeNodeSub));
        System.out.println(new BT24IsSubTree().isSubtree(treeNodeS, treeNodeT));
    }

    /**
     * 递归
     * 解题思路
     * 看到题目描述，首先判断一个树是否是另一棵树的子树，很明显想到可以用递归，但是两棵树完全相同也可以看做一棵树是另一棵树
     * 的子树。所以自然而然想到用一个判断两棵树是否相同的递归函数。
     * @param s
     * @param t
     * @return
     */
    /*public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) return true;   // t 为 null 一定都是 true
        if (s == null) return false;  // 这里 t 一定不为 null, 只要 s 为 null，肯定是 false
        return isSubtree(s.left, t) || isSubtree(s.right, t) || isSameTree(s,t);
    }

    *//**
     * 判断两棵树是否相同
     *//*
    public boolean isSameTree(TreeNode s, TreeNode t){
        if (s == null && t == null) return true;
        if (s == null || t == null) return false;
        if (s.val != t.val) return false;
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }*/

    /**
     * 方法一：深度优先搜索暴力匹配
     * 思路和算法
     *
     * 这是一种最朴素的方法——深度优先搜索枚举 ss 中的每一个节点，判断这个点的子树是否和 tt 相等。如何判断一个节点的子树是
     * 否和 tt 相等呢，我们又需要做一次深度优先搜索来检查，即让两个指针一开始先指向该节点和 tt 的根，然后「同步移动」两根指
     * 针来「同步遍历」这两棵树，判断对应位置是否相等
     *
     * 复杂度分析
     * 时间复杂度：对于每一个 s上的点，都需要做一次深度优先搜索来和 t 匹配，匹配一次的时间代价是 O(∣t∣)，那么总的时间代价
     * 就是 O(∣s∣×∣t∣)。故渐进时间复杂度为 O(∣s∣×∣t∣)。
     * 空间复杂度：假设 s 深度为 ds t 的深度为 dt 任意时刻栈空间的最大使用代价是O(max{ds* dt})。故渐进空间复杂度为 O(max{ds,dt})。
     * @param s
     * @param t
     * @return
     */
    private boolean isSubtree(TreeNode s, TreeNode t) {
        return dfs(s, t);
    }

    private boolean dfs(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        return check(s, t) || dfs(s.left, t) || dfs(s.right, t);
    }

    private boolean check(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null || s.val != t.val) {
            return false;
        }
        return check(s.left, t.left) && check(s.right, t.right);
    }
}
