// Crystal Long - Module 2.1
// Recursive Sort Example (Merge Sort)
// Recursive Sort of Student Discord List (Strings)

public class StudentRecursiveSort {

    // Recursive merge sort method for Strings
    public static void mergeSort(String[] arr, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;

            // Recursively sort left and right halves
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);

            // Merge them together
            merge(arr, left, mid, right);
        }
    }

    // Merge two sorted halves
    private static void merge(String[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        String[] leftArr = new String[n1];
        String[] rightArr = new String[n2];

        // Copy data into temp arrays
        for (int i = 0; i < n1; i++) {
            leftArr[i] = arr[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArr[j] = arr[mid + 1 + j];
        }

        // Merge back into arr
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (leftArr[i].compareToIgnoreCase(rightArr[j]) <= 0) {
                arr[k++] = leftArr[i++];
            } else {
                arr[k++] = rightArr[j++];
            }
        }

        // Copy any remaining elements
        while (i < n1) {
            arr[k++] = leftArr[i++];
        }
        while (j < n2) {
            arr[k++] = rightArr[j++];
        }
    }

    // Main method to test sorting
    public static void main(String[] args) {
        // Your cleaned student list
        String[] students = {
            "Amanda W.",
            "Kitten",
            "JG",
            "JHA86.96",
            "Juedeja",
            "Justin",
            "KK",
            "luish",
            "ProfSue",
            "Tars15"
        };

        System.out.println("Original Student List:");
        for (String s : students) {
            System.out.println(s);
        }

        // Call recursive merge sort
        mergeSort(students, 0, students.length - 1);

        System.out.println("\nSorted Student List:");
        for (String s : students) {
            System.out.println(s);
        }
    }
}
// End of StudentRecursiveSort.java