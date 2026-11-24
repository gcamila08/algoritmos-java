# Algorithms in Java – Integrative Group Task (Part II)

Central University of Ecuador · Faculty of Engineering and Applied Sciences · Information Systems
Subject: Algorithms · Fourth semester · Academic period 2026–2027

## Team members
- Narváez Farinango Genesis Camila
- Merino Gonzales Miguel David
- Riofrio Vivanco Daniel Antonio 
- Oña Mejía Erick Rolando

## Description
Console application in Java that processes academic records. It implements and evaluates:

| Activity | Class | Content |
|---|---|---|
| 1. Complexity analysis | `Complexity` | Linear sum O(n), nested comparisons O(n²) and halving until 1 O(log n), counting the dominant operation. |
| 2. Iteration and recursion | `Iteration`, `Recursion` | Factorial, array sum and array maximum, each one in an iterative and a recursive version, with input validation, operation count and execution time. |
| 3. Sorting | `Sorting` | Bubble Sort, Selection Sort, Insertion Sort and Shell Sort implemented from scratch (no `Arrays.sort()` or similar), counting comparisons, movements and time. |
| 4. Benchmark | `Benchmark` | Compares the four sorting algorithms with 10, 100, 1,000 and 10,000 elements and five data types (ascending, descending, random, repeated and nearly sorted). Exports the results to CSV. |

## Counting criterion
- **Comparison:** each time two elements of the array are compared.
- **Movement:** each write of an element in the array.
  - Bubble Sort and Selection Sort: one swap = 3 movements.
  - Insertion Sort and Shell Sort: 1 when the key is saved + 1 for each shift + 1 when the key is placed.
- **Time:** measured with `System.nanoTime()`, only around the algorithm (data generation and console output are excluded). Each algorithm runs on an identical copy of the data.

## Requirements
- Java (JDK 23, as set in `pom.xml`)
- Maven
- IntelliJ IDEA (recommended)

## How to run
1. Clone the repository and open the project in IntelliJ IDEA.
2. Run `src/main/java/ec/edu/uce/algoritmos/Main.java`.
3. The console shows the results of the four activities, and the benchmark file is created at `results/tables/benchmark_results.csv`.

## Project structure
```
algoritmos-java/
├── README.md
├── pom.xml
├── src/main/java/ec/edu/uce/algorithms/
│   ├── Complexity.java
│   ├── Iteration.java
│   ├── Recursion.java
│   ├── Sorting.java
│   ├── Benchmark.java
│   └── Main.java
├── results/
│   ├── tables/        (CSV and Excel files)
│   ├── charts/        (comparison charts)
│   └── screenshots/   (execution evidence)
└── report/
    └── practical-report.pdf
```
