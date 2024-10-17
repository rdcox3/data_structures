import java.io.*;

class ListNode {
    int value;
    ListNode next;
    ListNode(int x) { value = x; }
}

public class NaturalMergeSort {

    public static void main(String[] args) {
        // Example usage
        ListNode head = loadLinkedListData("input.txt");
        head = naturalMergeSort(head);
        saveLinkedListData("output_merge.txt", head);
    }

    public static ListNode naturalMergeSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode left = head, right = split(head);
        left = naturalMergeSort(left);
        right = naturalMergeSort(right);
        return merge(left, right);
    }

    private static ListNode split(ListNode head) {
        ListNode slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode middle = slow.next;
        slow.next = null;
        return middle;
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        while (l1 != null && l2 != null) {
            if (l1.value < l2.value) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }
        current.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    public static ListNode loadLinkedListData(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            ListNode dummy = new ListNode(0);
            ListNode current = dummy;
            while ((line = reader.readLine()) != null) {
                current.next = new ListNode(Integer.parseInt(line));
                current = current.next;
            }
            return dummy.next;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveLinkedListData(String fileName, ListNode head) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            ListNode current = head;
            while (current != null) {
                writer.write(Integer.toString(current.value));
                writer.newLine();
                current = current.next;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
