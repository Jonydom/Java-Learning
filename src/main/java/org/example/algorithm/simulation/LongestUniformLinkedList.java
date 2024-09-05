package org.example.algorithm.simulation;

import org.example.algorithm.util.ListNode;
import java.util.*;

/**
 * @author Jonydom
 * @description TODO
 * @date 2024-09-05 23:27
 */
public class LongestUniformLinkedList {
    public static void main(String[] args) {
        ListNode linkedList = ListNode.createLinkedList(new int[]{1, 2, 3, 2, 4, 2});
        ListNode result = longestUniformLinkedList(linkedList);
        ListNode.printLinkedList(result);
    }
    private static ListNode longestUniformLinkedList(ListNode head) {
        ListNode dummy = new ListNode(0);
        ListNode cur = head;
        Map<Integer, Integer> map = new HashMap<>();
        int maxFre = 0;
        int maxValue = 0;
        while (cur != null) {
            map.put(cur.getVal(), map.getOrDefault(cur.getVal(), 0) + 1);
            if (map.get(cur.getVal()) > maxFre) {
                maxFre = map.get(cur.getVal());
                maxValue = cur.getVal();
            }
            cur = cur.next;
        }
        cur = head;
        ListNode pre = dummy;
        while (cur != null) {
            if (cur.getVal() == maxValue) {
                pre.next = cur;
                pre = pre.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }
}
