package binaryTree;

/**
 * 236. 二叉树的最近公共祖先
 * medium
 */
public class BT03LowestCommonAncestor {

    public static void main(String[] args) {
        //String str = "[3,5,1,6,2,0,8,null,null,7,4]";
        String str = "[3,5,1,2,6]";
        TreeNode treeNode = TreeNode.mkTree(str);
        System.out.println(new BT03LowestCommonAncestor().lowestCommonAncestor(treeNode,
                new TreeNode(5), new TreeNode(6)));
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        if(null == root || root.val == p.val || root.val == q.val) {
            return root;
        }
        //按照上述规则，找到root的左子树的最近公共祖先。
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        //按照上述规则，找到root的右子树的最近公共祖先。
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        //一边找到了，一边没找到，根据上述规则，找到的就是最近公共祖先。
        if(null == left) {
            return right;
        }
        if(null == right) {
            return left;
        }
        //如果在左右子树分别找到了p和q，则说明root是它们两个的最近公共祖先。
        return root;
    }
}
