package binaryTree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉树序列号和反序列化
 *
 */
public class BT22SerializeDeserialize {

    public static void main(String[] args) {
        String str = "[1,2,5,3,4]";
        //String str = "[1,2,2,null,3,null,3]";
        TreeNode treeNode = TreeNode.mkTree(str);
        String serialize = new BT22SerializeDeserialize().serialize(treeNode);
        System.out.println(serialize);
        System.out.println(new BT22SerializeDeserialize().deSerialize(serialize));
    }

    /**
     * 思路和算法
     *
     * 二叉树的序列化本质上是对其值进行编码，更重要的是对其结构进行编码。可以遍历树来完成上述任务。众所周知，我们一般有两个策略：广度优先搜索和深度优先搜索。
     *
     * 广度优先搜索可以按照层次的顺序从上到下遍历所有的节点
     * 深度优先搜索可以从一个根开始，一直延伸到某个叶，然后回到根，到达另一个分支。根据根节点、左节点和右节点之间的相对顺序，可以进一步将深度优先搜索策略区分为：
     * 先序遍历
     * 中序遍历
     * 后序遍历
     *
     * 我们从根节点 1 开始，序列化字符串是 1,。然后我们跳到根节点 2 的左子树，序列化字符串变成 1,2,。现在从节点 2 开始，我们访问它的左节点 3
     * （1,2,3,None,None,）和右节点 4(1,2,3,None,None,4,None,None)。None,None, 是用来标记缺少左、右子节点，这就是我们在序列化期间保存树结构的方式。
     * 最后，我们回到根节点 1 并访问它的右子树，它恰好是叶节点 5。最后，序列化字符串是 1,2,3,None,None,4,None,None,5,None,None,。
     *
     * 即我们可以先序遍历这颗二叉树，遇到空子树的时候序列化成 None，否则继续递归序列化。那么我们如何反序列化呢？首先我们需要根据 ,
     * 把原先的序列分割开来得到先序遍历的元素列表，然后从左向右遍历这个序列：
     *
     * 先序遍历为例

     * @param root
     * @return
     */
    public String serialize(TreeNode root) {
        return rSerialize(root, "");
    }

    private String rSerialize(TreeNode root, String str) {
        if (root == null) {
            str += "None,";
        } else {
            str += (root.val) + ",";
            str = rSerialize(root.left, str);
            str = rSerialize(root.right, str);
        }
        return str;
    }

    public TreeNode deSerialize(String str) {
        String[] byteArray = str.split(",");

        List<String> strList = new LinkedList<>(Arrays.asList(byteArray));

        return rDeSerialize(strList);
    }

    private TreeNode rDeSerialize(List<String> stringList) {
        if (stringList.get(0).equals("None")) {
            stringList.remove(0);
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(stringList.get(0)));
        stringList.remove(0);
        root.left = rDeSerialize(stringList);
        root.right = rDeSerialize(stringList);

        return root;
    }

}
