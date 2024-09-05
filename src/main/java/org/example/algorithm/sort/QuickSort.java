package org.example.algorithm.sort;

/**
 * @author Jonydom
 * @description TODO
 * @date 2024-09-05 23:55
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] nums = new int[]{5, 2, 6, 1, 7};
        quickSort(nums, 0, nums.length-1);
        for (int i = 0; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
    private static void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int pivotpos = partition(nums, low, high);
            quickSort(nums, low, pivotpos - 1);
            quickSort(nums, pivotpos + 1, high);
        }
    }
    private static int partition(int[] nums, int low, int high) {
        int pivot = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= pivot) high--;
            nums[low] = nums[high];
            while (low < high && nums[low] <= pivot) low++;
            nums[high] = nums[low];
        }
        nums[low] = pivot;
        return low;
    }
}
