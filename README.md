# DAA-Assignment_1
This repository contains Java implementations of key Divide and Conquer algorithms (MergeSort, QuickSort, and QuickSelect) with built-in benchmarking and performance metrics tracking.
Key Algorithm Optimizations:

MergeSort: Uses a single shared auxiliary buffer to reduce memory allocations and switches to Insertion Sort for sub-arrays with size n ≤ 15

QuickSort: Implements Dijkstra's 3-way partitioning for duplicate elements, randomized pivot selection, and "Smaller Side First" tail-recursion control to guarantee max recursion depth ≤ 2log2n

QuickSelect: Fully iterative loop-based implementation achieving O(n) time complexity and O(1) extra space. 

Requirements:

Java JDK 17 or higher
IntelliJ IDEA (or any standard Java IDE)

How to Run

Run Tests:
Run AlgorithmTest.

Generate Benchmark Data:
Run BenchmarkRunner.main().
This creates the results.csv file in the project root folder.
