import java.io.*;
import java.util.*;

class SinglyLinkedListNode {
    int data;
    SinglyLinkedListNode next;

    SinglyLinkedListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

public class Solution {

    // Merge two sorted linked lists
    public static SinglyLinkedListNode mergeSortedLists(SinglyLinkedListNode l1, SinglyLinkedListNode l2) {
        // Create a dummy node to start the merged list
        SinglyLinkedListNode dummy = new SinglyLinkedListNode(0);
        SinglyLinkedListNode current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data <= l2.data) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Attach remaining nodes
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;

        return dummy.next; // Return head of merged list
    }

    // Print the linked list
    public static void printList(SinglyLinkedListNode head) {
        while (head != null) {
            System.out.print(head.data);
            if (head.next != null) System.out.print(" ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int t = Integer.parseInt(br.readLine().trim()); // number of test cases

        for (int test = 0; test < t; test++) {
            // Read first linked list
            int n1 = Integer.parseInt(br.readLine().trim());
            SinglyLinkedListNode head1 = null, tail1 = null;
            for (int i = 0; i < n1; i++) {
                int val = Integer.parseInt(br.readLine().trim());
                SinglyLinkedListNode node = new SinglyLinkedListNode(val);
                if (head1 == null) head1 = node;
                else tail1.next = node;
                tail1 = node;
            }

            // Read second linked list
            int n2 = Integer.parseInt(br.readLine().trim());
            SinglyLinkedListNode head2 = null, tail2 = null;
            for (int i = 0; i < n2; i++) {
                int val = Integer.parseInt(br.readLine().trim());
                SinglyLinkedListNode node = new SinglyLinkedListNode(val);
                if (head2 == null) head2 = node;
                else tail2.next = node;
                tail2 = node;
            }

            // Merge and print
            SinglyLinkedListNode merged = mergeSortedLists(head1, head2);
            printList(merged);
        }
    }
}
