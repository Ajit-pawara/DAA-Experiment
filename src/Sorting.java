/*
QuickSort average: O(n log n), worst O(n^2) (rare)
MergeSort: O(n log n) always
Space: QuickSort in-place O(log n) recursion; MergeSort O(n) extra
CO1, CO2
*/
public class Sorting {
// Quick sort
public static void quickSort(int[] a, int low, int high) {
if (low < high) {
int p = partition(a, low, high);
quickSort(a, low, p - 1);
quickSort(a, p + 1, high);
}
}
private static int partition(int[] a, int low, int high) {
int pivot = a[high];
int i = low - 1;
for (int j = low; j < high; j++) {
if (a[j] <= pivot) {
i++; swap(a, i, j);
}
}
swap(a, i+1, high);
return i+1;
}
private static void swap(int[] a, int i, int j) {
int t = a[i]; a[i] = a[j]; a[j] = t;
}


// Merge sort
public static void mergeSort(int[] a, int l, int r) {
if (l < r) {
int m = l + (r - l)/2;
mergeSort(a, l, m);
mergeSort(a, m+1, r);
merge(a, l, m, r);
}
}
private static void merge(int[] a, int l, int m, int r) {
int n1 = m - l + 1;
int n2 = r - m;
int[] L = new int[n1];
int[] R = new int[n2];
for (int i=0;i<n1;i++) L[i]=a[l+i];
for (int j=0;j<n2;j++) R[j]=a[m+1+j];
int i=0,j=0,k=l;
while (i<n1 && j<n2) {
if (L[i] <= R[j]) a[k++] = L[i++]; else a[k++] = R[j++];
}
while (i<n1) a[k++] = L[i++];
while (j<n2) a[k++] = R[j++];
}


public static void main(String[] args) {
int[] arr = {34,7,23,32,5,62};
int[] a1 = arr.clone();
quickSort(a1,0,a1.length-1);
System.out.print("QuickSort: "); for (int x: a1) System.out.print(x+" "); System.out.println();
int[] a2 = arr.clone();
mergeSort(a2,0,a2.length-1);
System.out.print("MergeSort: "); for (int x: a2) System.out.print(x+" "); System.out.println();
}
}
