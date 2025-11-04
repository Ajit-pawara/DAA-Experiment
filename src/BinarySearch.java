/*
Binary Search (recursive) using array
Time complexity: O(log n)
Space complexity: O(log n) due to recursion stack
CO1, CO2
*/
public class BinarySearch {
// user-defined functions first as requested
public static int binarySearchRec(int[] arr, int low, int high, int key) {
if (low > high) return -1;
int mid = low + (high - low) / 2;
if (arr[mid] == key) return mid;
if (key < arr[mid]) return binarySearchRec(arr, low, mid - 1, key);
return binarySearchRec(arr, mid + 1, high, key);
}


public static void main(String[] args) {
int[] arr = {1,3,5,7,9,11,13,15,17,19};
int key = 13;
int idx = binarySearchRec(arr, 0, arr.length - 1, key);
if (idx == -1) System.out.println("Element not found");
else System.out.println("Found at index: " + idx);
}
}
