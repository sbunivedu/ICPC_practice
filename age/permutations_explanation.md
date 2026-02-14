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
