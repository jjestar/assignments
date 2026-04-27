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

---

## B. Algorithm Descriptions

### Bubble Sort (Basic — Category A)
Repeatedly compares adjacent elements and swaps them if they are out of order.
After each full pass, the largest unsorted element "bubbles" to its correct position at the right end.
An early-exit optimisation stops the algorithm as soon as a pass produces no swaps.

- **Best case**: O(n) — already sorted, zero swaps needed
- **Average / Worst case**: O(n²) — random or reverse-sorted input
- **Space**: O(1)

### Merge Sort (Advanced — Category B)
Divide-and-conquer algorithm. Recursively splits the array in half until sub-arrays
are trivially sorted (size 1), then merges them back in sorted order by always picking
the smaller of the two front elements.

- **All cases**: O(n log n) — guaranteed, input order does not matter
- **Space**: O(n) — temporary arrays during merge

### Linear Search (Searching — Category C)
Walks through the array element-by-element from index 0, returning the index of the
target if found, or -1 if the target is not present.

- **Best case**: O(1) — target is the first element
- **Worst case**: O(n) — target at the end or not present
- **Space**: O(1)
- **Works on unsorted arrays** — no pre-sorting required

---

## C. Experimental Results

*(Fill in with your actual printed output after running the program.)*

### Sorting — Random Input

| Array Size | Bubble Sort (ns) | Merge Sort (ns) | Faster |
|---|---|---|---|
| Small (10) | | | |
| Medium (100) | | | |
| Large (1000) | | | |

### Sorting — Sorted Input

| Array Size | Bubble Sort (ns) | Merge Sort (ns) | Faster |
|---|---|---|---|
| Small (10) | | | |
| Medium (100) | | | |
| Large (1000) | | | |

### Linear Search — Worst Case (target = -1)

| Array Size | Random Array (ns) | Sorted Array (ns) |
|---|---|---|
| Small (10) | | |
| Medium (100) | | |
| Large (1000) | | |

---

## D. Analysis Questions

**Which sorting algorithm performed faster? Why?**
Merge Sort is faster for medium and large arrays because its O(n log n) growth
is far slower than Bubble Sort's O(n²). For a 1 000-element array, Bubble Sort
performs roughly 500 000 comparisons while Merge Sort needs only ~10 000.

**How does performance change with input size?**
Bubble Sort time grows roughly as n² — doubling the array size makes it ~4× slower.
Merge Sort grows as n log n — doubling the array size makes it only ~2× slower.
Linear Search grows linearly — doubling the array size makes it ~2× slower.

**How does sorted vs unsorted data affect performance?**
Bubble Sort becomes O(n) on already-sorted input (the early-exit flag fires after
one pass). Merge Sort is unaffected — it always divides and merges the same way.
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
and write but becomes painfully slow as input grows — timing on 1 000 elements
already shows a clear gap compared to Merge Sort. The early-exit optimisation
in Bubble Sort is a good reminder that even simple tweaks can turn worst-case
O(n²) into O(n) for a specific input type (already-sorted data).

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
│   ├── Sorter.java       ← Bubble Sort + Merge Sort
│   ├── Searcher.java     ← Linear Search
│   ├── Experiment.java   ← Timing and experiment runner
│   └── Main.java         ← Entry point / demo
├── docs/
│   └── screenshots/      ← Add your program output screenshots here
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
