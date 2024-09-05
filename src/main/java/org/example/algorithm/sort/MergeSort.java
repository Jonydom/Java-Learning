package org.example.algorithm.sort;

import java.util.Arrays;

/**
 * @author Jonydom
 * @description TODO
 * @date 2024-09-06 0:01
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 6, 1, 7};
        mergeSort(nums, 0, nums.length-1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
    private static void mergeSort(int[] nums, int low, int high) {
        if (low < high) {
            int mid = (low + high) / 2;
            mergeSort(nums, low, mid);
            mergeSort(nums, mid+1, high);
            merge(nums, low, mid, high);
        }
    }
    private static void merge(int[] A, int low, int mid, int high) {
        int[] B = Arrays.copyOf(A, A.length);
        int i = low, j = mid+1, k = i;
        while (i <= mid && j <= high) {
            if (B[i] <= B[j]) {
                A[k] = B[i++];
            } else {
                A[k] = B[j++];
            }
            k++;
        }
        while (i <= mid) A[k++] = B[i++];
        while (j <= high) A[k++] = B[j++];
    }
}
