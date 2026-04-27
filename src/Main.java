 public class Main {
        public static void main(String[] args) {
            Sorter sorter = new Sorter();
            Searcher searcher = new Searcher();
            Experiment experiment = new Experiment();
            System.out.println("alogirthm correctness demonstration");
            int[] demoArray = {64, 25, 12, 22, 11, 90, 3, 47, 58, 36};
            System.out.println("Original array:");
            sorter.printArray(demoArray);
            int[] bubbleDemo = copyOf(demoArray);
            sorter.basicSort(bubbleDemo);
            System.out.println("\nAfter Bubble Sort:");
            sorter.printArray(bubbleDemo);
            int[] mergeDemo = copyOf(demoArray);
            sorter.advancedSort(mergeDemo);
            System.out.println("\nAfter Merge Sort:");
            sorter.printArray(mergeDemo);
            int target = 22;
            int foundIndex = searcher.search(demoArray, target);
            System.out.println("\nLinear Search for value " + target + ":");
            if (foundIndex != -1) {
                System.out.println("  Found at index " + foundIndex + " Correct");
            } else {
                System.out.println("  Not found in array");
            }
            int missingTarget = 999;
            int notFound = searcher.search(demoArray, missingTarget);
            System.out.println("\nLinear Search for value " + missingTarget + " (not in array):");
            System.out.println("  Result: " + notFound + " (−1 means not found)");
            System.out.println();
            experiment.runAllExperiments();
        }
        private static int[] copyOf(int[] original) {
            int[] copy = new int[original.length];
            for (int i = 0; i < original.length; i++) {
                copy[i] = original[i];
            }
            return copy;
        }
    }