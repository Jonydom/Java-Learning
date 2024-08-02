package org.example.algorithm.util;

import java.util.Deque;
import java.util.LinkedList;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    public static TreeNode createBinaryTree(Integer[] arr, int numTh) {
        if (arr.length == 0) {
            return new TreeNode(0);
        }
        Deque<TreeNode> deque = new LinkedList<>();
        TreeNode root = new TreeNode(arr[0]);
        deque.offer(root);

        for (int i = 1; i < arr.length; i+=numTh) {
            TreeNode node = deque.poll();
            if (arr[i] != null) {
                TreeNode leftNode = new TreeNode(arr[i]);
                node.left = leftNode;
                deque.offer(leftNode);
            }
            if (arr[i+1] != null) {
                TreeNode rightNode = new TreeNode(arr[i+1]);
                node.right = rightNode;
                deque.offer(rightNode);
            }
        }
        return root;
    }

    public static void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.val + " ");
        preOrder(root.left);
        preOrder(root.right);
    }

    public static void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val + " ");
        inOrder(root.right);
    }

    public static void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val + " ");
    }

    public static void main(String[] args) {
        Integer[] arr = {1, 2, null, 4, 5};
        TreeNode root = createBinaryTree(arr, 2);
        preOrder(root);
        System.out.println();
        inOrder(root);
        System.out.println();
        postOrder(root);
    }
}
