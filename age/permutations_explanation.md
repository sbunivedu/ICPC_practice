# Permutations.java Explanation

## Overview
This is a **permutation generator** that uses backtracking recursion to generate all possible orderings of the input array.

## Key Intuition Behind the Recursion

The recursion works by **building permutations incrementally from left to right**:

1. **Fix positions one at a time**: The `start` parameter marks which position you're **currently** deciding. Everything before `start` is **already fixed** in the permutation.

2. **Try each remaining element**: For each position `start`, the loop tries placing each element from `start` **onwards** at that position.

3. **Recurse deeper**: After placing an element, recursively solve the rest (positions `start + 1` onward).

4. **Backtrack**: After exploring all permutations with that element fixed, swap it back to **undo** the choice and try the next element.

## How It Works: Choose-Explore-Unchoose Pattern

```java
for (int i = start; i < nums.length; i++) {
  swap(nums, start, i);           // CHOOSE: place element i at position start
  permute_r(nums, start + 1);     // EXPLORE: recursively generate rest
  swap(nums, start, i);           // UNCHOOSE: backtrack (restore original state)
}
```

This pattern is the foundation of backtracking:
- **Choose**: Make a decision (swap)
- **Explore**: Recursively solve the subproblem
- **Unchoose**: Undo the decision to try other options

## Example: Generating Permutations of [1, 2, 3]

```
(nums=[1, 2, 3] start=0)
├─ Fix 1 at pos 0 → [1, 2, 3] (nums=[1, 2, 3] start=1)
│  ├─ Fix 2 at pos 1 → [1, 2, 3] (nums=[1, 2, 3] start=2)
│  │  └─ Fix 3 at pos 2 → [1, 2, 3] ✓ (print this)
│  └─ Fix 3 at pos 1 → [1, 3, 2] (nums=[1, 3, 2] start=3)
│     └─ Fix 2 at pos 2 → [1, 3, 2] ✓ (print this)
├─ Fix 2 at pos 0 → [2, 1, 3] (nums=[2, 1, 3] start=1)
│  ├─ Fix 1 at pos 1 → [2, 1, 3] (nums=[2, 1, 3] start=2)
│  │  └─ Fix 3 at pos 2 → [2, 1, 3] ✓ (print this)
│  └─ Fix 3 at pos 1 → [2, 3, 1] (nums=[2, 3, 1] start=2)
│     └─ Fix 1 at pos 2 → [2, 3, 1] ✓ (print this)
└─ Fix 3 at pos 0 → [3, 2, 1] (nums=[3, 2, 1] start=1)
   ├─ Fix 2 at pos 1 → [3, 2, 1] (nums=[3, 2, 1] start=2)
   │  └─ Fix 1 at pos 2 → [3, 2, 1] ✓ (print this)
   └─ Fix 1 at pos 1 → [3, 1, 2] (nums=[3, 1, 2] start=2)
      └─ Fix 2 at pos 2 → [3, 1, 2] ✓ (print this)
```

## Base Case

```java
if (start == nums.length) {
  System.out.println(Arrays.toString(nums));
  return;
}
```

When `start` reaches the end of the array, all positions are fixed, forming one complete permutation. This is printed and the recursion backtracks.

## Why This Works

- The algorithm systematically explores every possible way to arrange the elements
- By swapping back after each recursive call, it reuses the same array and efficiently backtracks
- Time complexity: **O(n × n!)** — there are n! permutations, each taking O(n) to print
- Space complexity: **O(n)** — recursion depth is at most n

## Key Takeaway

The power of backtracking is that it explores all possibilities by:
1. Making a choice
2. Recursively solving with that choice
3. Undoing the choice to explore alternatives

This allows the code to be simple and elegant while exhaustively exploring the **solution space**.

## Competitive Programming Lessons

### 1. **Recognize Graph Problems in Disguise**
The problem isn't about ages directly—it's a **Hamiltonian Path problem**. A strong competitive programmer identifies that arranging items with constraints maps to graph problems. Here: two ages can be adjacent if GCD > 1, making this an edge connectivity problem.

### 2. **Precompute Relationships to Speed Up Search**
Build an adjacency matrix O(n²) upfront to store all pairwise GCD values. During backtracking, you get O(1) constraint lookups instead of recalculating GCD repeatedly. This is a fundamental optimization: trade space for time.

### 3. **Master the Backtracking Pattern: Choose-Explore-Unchoose**
The core backtracking cycle is:
- **Choose**: Make a decision (place an element)
- **Explore**: Recursively solve the remaining problem
- **Unchoose**: Undo the decision to try alternatives

This pattern is ubiquitous in competitive programming (permutations, combinations, Sudoku, N-Queens, etc.).

### 4. **Understand When to Use Permutations**
Generate all possible orderings only when necessary. Here, searching all permutations is a brute-force approach that works for small n. Recognize that Hamiltonian Path is NP-complete—this algorithm scales poorly for n > 20.

### 5. **Use Early Exit Conditions**
Check for impossible cases first: ages containing 1 make GCD(1, x) = 1 always, so no valid arrangement exists. Early pruning saves time.

### 6. **Implement Backtracking with Care**
Whether recursive or iterative, maintain explicit state:
- Track which elements are used (used[] flags)
- Store the current partial solution (order[])
- Properly restore state when backtracking

### 7. **Clone or Copy Collections When Storing Results**
When collecting permutations, use nums.clone() to add a copy of the array, not a reference. Otherwise, all stored results point to the same modified array.

### 8. **Distinguish "No Solution" from "Algorithm Timeout"**
A completed backtracking search that finds nothing is different from hitting time limits. Understanding algorithm limitations helps you decide when to optimize or change approaches in a competition.
