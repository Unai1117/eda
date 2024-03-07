package libraries.util;

/**
 * The Sorting class contains:
 * - The implementation of the in-place sorting algorithms D&Q
 * Quick Sort and Merge Sort.
 * - A method to check whether two generic arrays are equal.
 * 
 * @author (EDA)
 * @version (Curso 2017-2018)
 */

public class Sorting {

    // QUICK SORT -------------------------------------------------
    /**
     * Quicksort sorting algorithm (Hoare, 1963).
     * Using Weiss' partition algorithm, with median 3
     * to compute the pivot.
     * 
     * @param a Its elements implement the Comparable interface
     */
    public static <T extends Comparable<T>> void quickSort(T[] a) {
        quickSort(a, 0, a.length - 1);
    }

    // Sorts the array a[left, right] with quickSort, left <= right
    private static <T extends Comparable<T>> void quickSort(T[] a,
            int left, int right) {
        if (left < right) {
            T pivot = median3(a, left, right);
            int i = left;
            int j = right - 1;
            for (; i < j;) {
                while (pivot.compareTo(a[++i]) > 0) {
                    ;
                }
                while (pivot.compareTo(a[--j]) < 0) {
                    ;
                }
                swap(a, i, j);
            }
            swap(a, i, j); // Undo the last change
            swap(a, i, right - 1); // Restore the pivot
            // SORTED PIVOT -->
            quickSort(a, left, i - 1); // Recursively sort the smaller elements
            quickSort(a, i + 1, right); // Recursively sort the larger elements
        }
    }

    // Swap the elements i and j of array a
    private static <T> void swap(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    // After computing the median3 of the subarray a[left, right],
    // returns the value of the pivot
    private static <T extends Comparable<T>> T median3(T[] a,
            int left, int right) {
        int c = (left + right) / 2;
        if (a[c].compareTo(a[left]) < 0) {
            swap(a, left, c);
        }
        if (a[right].compareTo(a[left]) < 0) {
            swap(a, left, right);
        }
        if (a[right].compareTo(a[c]) < 0) {
            swap(a, c, right);
        }
        // hide the pivot at position right-1
        swap(a, c, right - 1);
        return a[right - 1];
    }

    // MERGE SORT --------------------------------------------
    // VERSION 1 (as seen in the theory lectures):
    /**
     * Sorts array v in ascending order.
     * 
     * @param v Its elements must implement the Comparable interface
     */
    public static <T extends Comparable<T>> void mergeSort1(T[] v) {
        mergeSort1(v, 0, v.length - 1);
    }

    /**
     * IFF i<=f: sorts the subarray v[i, f] in ascending order.
     * 
     * @param v Its elements must implement the Comparable interface
     * @param i Lower bound of the interval to be sorted
     * @param f Upper bound of the interval to be sorted
     */
    private static <T extends Comparable<T>> void mergeSort1(T[] v,
            int i, int f) {
        if (i < f) {
            int m = (i + f) / 2;
            mergeSort1(v, i, m);
            mergeSort1(v, m + 1, f);
            merge1(v, i, f, m);
        }
    }

    /**
     * Merges internally the subarrays v[i, m] and v[m + 1, f],
     * both are already sorted in ascending order.
     *
     * @param v Its elements must implement the Comparable interface
     * @param i Lower bound of the interval to be merged
     * @param f Upper bound of the interval to be merged
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> void merge1(T[] v,
            int i, int f, int m) {
        int a = i, b = m + 1, k = 0;
        T[] aux = (T[]) new Comparable[f - i + 1];
        while (a <= m && b <= f) {
            if (v[a].compareTo(v[b]) < 0) {
                aux[k++] = v[a++];
            } else {
                aux[k++] = v[b++];
            }
        }
        while (a <= m) {
            aux[k++] = v[a++];
        }
        while (b <= f) {
            aux[k++] = v[b++];
        }

        for (a = i, k = 0; a <= f; a++, k++) {
            v[a] = aux[k];
        }
    }

    // MERGE SORT --------------------------------------------
    // VERSION 2
    /**
     * Sorts array v in ascending order.
     *
     * @param v Its elements must implement the Comparable interface
     */
    public static <T extends Comparable<T>> void mergeSort2(T[] v) {
        T[] aux = mergeSort2(v, 0, v.length - 1);
        for (int i = 0; i < aux.length; i++) {
            v[i] = aux[i];
        }
    }

    /**
     * IFF i<=f: returns an array with the elements of
     * the subarray v[i, f] sorted in ascending order.
     *
     * @param v Its elements must implement the Comparable interface
     * @param i Lower bound of the interval to be sorted
     * @param f Upper bound of the interval to be sorted
     * @return T[], the array that results from sorting v[i, f]
     */
    private static <T extends Comparable<T>> T[] mergeSort2(T[] v, int i, int f) {
        if (i < f) {
            int mid = (i + f) / 2;
            return merge2(mergeSort2(v, i, mid), mergeSort2(v, mid + 1, f));
        } else if (i == f) {
            T[] aux = (T[]) new Comparable[1];
            aux[0] = v[i];
            return aux;
        } else {
            T[] aux = (T[]) new Comparable[2];
            if (v[1].compareTo(v[f]) > 0) {
                aux[0] = v[f];
                aux[1] = v[i];
            } else {
                aux[0] = v[i];
                aux[1] = v[f];
            }
            return aux;
        }
    }

    /**
     * Returns the array that results from merging v1 and v2,
     * two arrays that are already sorted in ascending order.
     *
     * @param v1 Its elements must implement the Comparable interface
     * @param v2 Its elements must implement the Comparable interface
     * @return T[], the array that results from merging v1 and v2
     */
    @SuppressWarnings("unchecked")
    private static <T extends Comparable<T>> T[] merge2(T[] v1, T[] v2) {
        /* TO BE COMPLETED */
        int l = 0, r = 0, k = 0;
        T[] aux = (T[]) new Comparable[v1.length + v2.length];
        while (l < v1.length && r < v2.length) {
            if (v1[l].compareTo(v2[r]) < 0) {
                aux[k++] = v1[l++];
            } else {
                aux[k++] = v2[r++];
            }
        }
        while (l < v1.length)
            aux[k++] = v1[l++];
        while (r < v2.length)
            aux[k++] = v2[r++];

        return aux;
    }

    // Auxiliary method --------------------------------------------
    /**
     * Checks whether arrays a and b are equal element to element.
     * 
     * @param a Its elements must implement the Comparable interface
     * @return boolean, the result of this check
     */
    public static <T extends Comparable<T>> boolean areEqual(T[] a, T[] b) {
        boolean equal = true;
        if (a.length != b.length) {
            equal = false;
        } else {
            for (int i = 0; i < a.length && equal; i++) {
                equal = (a[i].compareTo(b[i]) == 0);
            }
        }
        return equal;
    }
}