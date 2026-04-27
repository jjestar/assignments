public class Experiment {
    private Sorter  sorter  = new Sorter();
    private Searcher searcher = new Searcher();
    public long measureSortTime(int[] arr, String type) {
        int[] copy = copyArray(arr);
        long startTime = System.nanoTime();
        if (type.equals("basic")) {
            sorter.basicSort(copy);        //BubbleSort
        } else if (type.equals("advanced")) {
            sorter.advancedSort(copy);     //MergeSort
        }
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public long measureSearchTime(int[] arr, int target) {
        long startTime = System.nanoTime();
        searcher.search(arr, target);
        long endTime = System.nanoTime();
        return endTime - startTime;
    }
    public void runAllExperiments() {
        int[] sizes = {10, 100, 1000};
        String[] sizeLabels = {"Small (10)", "Medium (100)", "Large (1000)"};
        System.out.println("===algorithm-performance-experiment-results===");
        for (int s = 0; s < sizes.length; s++) {
            int size = sizes[s];
            System.out.println("  Array Size: " + sizeLabels[s]);
            int[] randomArray = sorter.generateRandomArray(size);
            int[] sortedArray = generateSortedArray(size);
            System.out.println("\n  [SORTING Random Input]");
            long bubbleRandomTime  = measureSortTime(randomArray, "basic");
            long mergeRandomTime   = measureSortTime(randomArray, "advanced");
            System.out.printf("    Bubble Sort : %,15d ns%n", bubbleRandomTime);
            System.out.printf("    Merge Sort  : %,15d ns%n", mergeRandomTime);
            System.out.println("    Winner      : " + faster(bubbleRandomTime, mergeRandomTime,
                    "Bubble Sort", "Merge Sort"));
            System.out.println("\n  [SORTING Sorted Input]");
            long bubbleSortedTime  = measureSortTime(sortedArray, "basic");
            long mergeSortedTime   = measureSortTime(sortedArray, "advanced");
            System.out.printf("    Bubble Sort : %,15d ns%n", bubbleSortedTime);
            System.out.printf("    Merge Sort  : %,15d ns%n", mergeSortedTime);
            System.out.println("    Winner      : " + faster(bubbleSortedTime, mergeSortedTime,
                    "Bubble Sort", "Merge Sort"));
            int worstCaseTarget = -1;
            System.out.println("\n  [SEARCHING Linear Search, Worst Case (target = -1)]");
            long linearRandomTime = measureSearchTime(randomArray, worstCaseTarget);
            long linearSortedTime = measureSearchTime(sortedArray, worstCaseTarget);
            System.out.printf("    On Random Array : %,15d ns%n", linearRandomTime);
            System.out.printf("    On Sorted Array : %,15d ns%n", linearSortedTime);
        }}
    private int[] copyArray(int[] original) {
        int[] copy = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copy[i] = original[i];
        }
        return copy;
    }
    private int[] generateSortedArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }
        return arr;
    }
    private String faster(long timeA, long timeB, String nameA, String nameB) {
        if (timeA < timeB) {
            return nameA + " (by " + (timeB - timeA) + " ns)";
        } else if (timeB < timeA) {
            return nameB + " (by " + (timeA - timeB) + " ns)";
        } else {
            return "Tie";
        }
    }
}