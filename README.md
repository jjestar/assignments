# Assignment 3: Sorting and Searching Algorithm Analysis

## A. Project Overview

This project implements and compares three fundamental algorithms:

| Category | Algorithm | Big-O (Worst) |
|---|---|---|
| Basic Sorting | Bubble Sort | O(n²) |
| Advanced Sorting | Merge Sort | O(n log n) |
| Searching | Linear Search | O(n) |

The goal is to measure actual execution time with `System.nanoTime()` across
different array sizes (10 / 100 / 1 000 elements) and input types (random vs sorted),
then compare the results against theoretical Big-O predictions.

<img width="1280" height="719" alt="photo_1_2026-04-27_21-42-58" src="https://github.com/user-attachments/assets/83cf00b0-e1a3-49c3-9842-e09d1cd4d992" />
---

## B. Algorithm Descriptions

### Bubble Sort (Basic — Category A)
Repeatedly compares adjacent elements and swaps them if they are out of order.
After each full pass, the largest unsorted element "bubbles" to its correct position at the right end.
An early-exit optimisation stops the algorithm as soon as a pass produces no swaps.

| Case | Time Complexity |
|---|---|
| Best (already sorted) | O(n) |
| Average | O(n²) |
| Worst (reverse sorted) | O(n²) |
| Space | O(1) |


### Merge Sort (Advanced — Category B)
Divide-and-conquer algorithm. Recursively splits the array in half until sub-arrays
are trivially sorted (size 1), then merges them back in sorted order by always picking
the smaller of the two front elements.

| Case | Time Complexity |
|---|---|
| Best | O(n log n) |
| Average | O(n log n) |
| Worst | O(n log n) |
| Space | O(n) |

  <img width="1280" height="719" alt="photo_2_2026-04-27_21-42-58" src="https://github.com/user-attachments/assets/12169e1f-53f5-4f89-8da8-c0efaa76a2f8" />


### Linear Search (Searching — Category C)
Walks through the array element-by-element from index 0, returning the index of the
target if found, or -1 if the target is not present.
| Case | Time Complexity |
|---|---|
| Best (target at index 0) | O(1) |
| Average | O(n) |
| Worst (target not found) | O(n) |
| Space | O(1) |


<img width="1280" height="719" alt="photo_3_2026-04-27_21-42-58" src="https://github.com/user-attachments/assets/b429e351-78b1-4e63-9ea7-2a00f7a639a0" />

---

## C. Experimental Results



### Sorting — Random Input

| Array Size | Bubble Sort (ns) | Merge Sort (ns) | Faster |
|---|---|---|---|
| Small (10) | 6,900 | 7,500 | Bubble Sort (by 600 ns) |
| Medium (100) | 154,800 | 68,200 | **Merge Sort (by 86,600 ns)** |
| Large (1000) | 6,068,100 | 223,500 | **Merge Sort (by 5,844,600 ns)** 

### Sorting — Sorted Input

| Array Size | Bubble Sort (ns) | Merge Sort (ns) | Faster |
|---|---|---|---|
| Small (10) | 1,700 | 6,600 | Bubble Sort (by 4,900 ns) |
| Medium (100) | 3,600 | 100,000 | **Bubble Sort (by 96,400 ns)** |
| Large (1000) | 8,300 | 132,800 | **Bubble Sort (by 124,500 ns)** |

### Linear Search — Worst Case (target = -1)

| Array Size | Random Array (ns) | Sorted Array (ns) |
|---|---|---|
| Small (10) | 3,500 | 400 |
| Medium (100) | 3,400 | 1,600 |
| Large (1000) | 16,300 | 14,400 |

<img width="1280" height="719" alt="photo_4_2026-04-27_21-42-58" src="https://github.com/user-attachments/assets/ae38c666-00cd-4d69-8d4b-bc90c28768dd" />

---

## D. Analysis Questions

**Which sorting algorithm performed faster? Why?**
Merge sort is a way faster since it does a way less comparisons than Bubble Sort

**How does performance change with input size?**
Bubble Sort time grows roughly as n^2 - doubling the array size makes it 4 times slower.
Merge Sort grows as n log n - doubling the array size makes it only 2 times slower.
Linear Search grows linearly - doubling the array size makes it 2 times slower.

**How does sorted vs unsorted data affect performance?**
Bubble Sort becomes O(n) on already sorted input (the early-exit flag fires after
one pass). Merge Sort is unaffected it always divides and merges the same way.
Linear Search is unaffected because it checks elements one by one regardless of order.

**Do the results match the expected Big-O complexity?**
Yes, generally. Real timings also reflect cache behaviour and JVM JIT compilation,
so the very first run is often slower than subsequent runs.

**Which searching algorithm is more efficient?**
For unsorted data, Linear Search is the only option and is O(n). If the array were
sorted, Binary Search (O(log n)) would be dramatically faster, but it requires a
sorted input.

**Why does Binary Search require a sorted array?**
Binary Search eliminates half the remaining elements at each step by deciding
"target must be in the left half or the right half." That decision is only valid
when elements are in order; on an unsorted array the decision would be arbitrary
and incorrect.

---

## E. Reflection

Implementing these algorithms revealed how dramatically theoretical complexity
translates into real performance differences. Bubble Sort is easy to understand
and write but becomes painfully slow as input grows - timing on 1 000 elements
already shows a clear gap compared to Merge Sort. The early-exit optimisation
in Bubble Sort is a good reminder that even simple tweaks can turn worst-case
O(n^2) into O(n) for a specific input type (already-sorted data).

The most interesting practical lesson was that Merge Sort's extra memory usage
(O(n) for temporary arrays) is a real trade-off worth knowing. On memory-constrained
systems, an in-place sort like Heap Sort might be preferred even though it is
harder to implement. Theoretical Big-O gives the big picture, but real-world choices
also depend on input distribution, cache performance, and available memory.

---

## Repository Structure

```
assignment3-sorting-searching/
├── src/
│   ├── Sorter.java       - Bubble Sort + Merge Sort
│   ├── Searcher.java     - Linear Search
│   ├── Experiment.java   - Timing and experiment runner
│   └── Main.java         - Entry point
├── docs/
│   └── screenshots/
├── README.md
└── .gitignore
```

## How to Run

```bash
# From the project root, compile all source files
javac src/*.java -d out/

# Run the program
java -cp out Main
```
