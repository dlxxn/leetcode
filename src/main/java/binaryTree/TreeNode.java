package binaryTree;

public class TreeNode {
    public static void main(String[] args) {
        String str = "[3,9,20,null,null,15,7]";

        TreeNode node = TreeNode.mkTree(str);

        System.out.println(node);
    }
    public int val;
    public TreeNode left;
    public TreeNode right;
    TreeNode(int x) { val = x; }
    @Override
    public String toString(){
        return Integer.toString(val);
    }

    // int []arr = {3, 9, 20, Integer.MAX_VALUE, Integer.MAX_VALUE, 15, 7};
    private static int[] StrToIntArray(String str) {
        str = str.substring(1, str.length() - 1);
        String []strs = str.split(",");
        int []arr = new int[strs.length];

        for (int i = 0; i < arr.length; i++) {
            if (strs[i].equals("null")) {
                arr[i] = Integer.MAX_VALUE;
            } else {
                arr[i] = Integer.parseInt(strs[i]);
            }
        }

        return arr;
    }

    // String str = "[3,9,20,null,null,15,7]";
    public static TreeNode mkTree(String str) {

        int []arr = StrToIntArray(str);
        TreeNode []nodes = new TreeNode[arr.length + 1];
        for (int i = 1; i < nodes.length; i++) {
            if (arr[i - 1] != Integer.MAX_VALUE) {
                nodes[i] = new TreeNode(arr[i - 1]);
            }else {
                nodes[i] = null;
            }
        }

        TreeNode node = null;
        for (int i = 1; i < nodes.length / 2; i++) {
            node = nodes[i];
            if (node == null) continue;
            node.left = nodes[2 * i];
            node.right = nodes[2 * i + 1];
        }
        return nodes[1];
    }
}
