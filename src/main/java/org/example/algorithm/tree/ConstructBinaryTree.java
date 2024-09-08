package org.example.algorithm.tree;

import org.example.algorithm.util.TreeNode;

import java.util.Arrays;

/**
 * @author Jonydom
 * @description 105. 从前序与中序遍历序列构造二叉树
 * @date 2024-09-08 13:51
 */
public class ConstructBinaryTree {
    public static TreeNode buildTree(int[] preorder, int[] inorder) {
        // 没有元素了，不用再递归构建了
        if (preorder.length == 0) return null;
        // 分割点
        int rootValue = preorder[0];
        TreeNode root = new TreeNode(rootValue);

        int i;
        for (i = 0; i < inorder.length; i++) {
            if (rootValue == inorder[i]) break;
        }
        int[] leftInorder = Arrays.copyOfRange(inorder, 0, i);
        int[] rightInorder = Arrays.copyOfRange(inorder, i+1, inorder.length);
        int[] leftPreorder = Arrays.copyOfRange(preorder, 1, 1 + leftInorder.length);
        int[] rightPreorder = Arrays.copyOfRange(preorder, 1 + leftInorder.length, preorder.length);

        root.left = buildTree(leftPreorder, leftInorder);
        root.right = buildTree(rightPreorder, rightInorder);

        return root;

    }

    public static void main(String[] args) {
        int[] preorder = new int[]{3,9,20,15,7};
        int[] inorder = new int[]{9,3,15,20,7};
        TreeNode treeNode = buildTree(preorder, inorder);
        TreeNode.preOrder(treeNode);
    }
}
