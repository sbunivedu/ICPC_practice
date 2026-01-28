# A1 Paper Solution Explanation

## Problem Overview

The problem asks: Given papers of various A-series sizes (A2, A3, A4, etc.), can we tape them together to create an A1 paper? If yes, what's the minimum total tape length needed?

Key facts about A-series papers:
- Each A(n) paper has **half the area** of A(n-1)
- An A2 paper is **2^(-5/4) by 2^(-3/4)** meters
- To tape two sheets together, you need tape equal to their **long side**
- When papers are taped to double area, dimensions scale by specific factors

## Solution Strategy

The algorithm works **backwards from the target (A1 paper)**:
1. Start by needing 1 A1 paper
2. If we don't have A1, we need 2 A2 papers to make it
3. If we don't have 2 A2s, we need 4 A3s to make those A2s
4. Continue down to the smallest papers we have
5. At each step, accumulate the tape needed

This is efficient because we can track how many papers of each size we need, use what we have, and convert the shortfall to requirements for smaller papers.

## Code Breakdown

### Mathematical Constants

```java
double bigger = Math.pow(2, (-(double) 3 / (double) 4));
```
This is the **long side of an A2 paper**: 2^(-3/4) meters ≈ 0.5946 meters

```java
double sizeDown = (Math.pow(2, (-(double) 5 / (double) 4))) / bigger;
```
This is the **scaling factor** when moving to a smaller paper size:
- 2^(-5/4) / 2^(-3/4) = 2^(-1/2) ≈ 0.7071
- Applied when going from A2 → A3 → A4, etc.

### Main Loop Variables

- `needed`: How many papers of the current size we need (doubles at each iteration)
- `have`: How many papers of the current size we actually have (read from input)
- `tape`: Accumulates total tape length in meters
- `bigger`: The long side dimension of the current paper size (scales down each iteration)
- `i`: Iteration counter; loop runs while `i < smallest`

### Core Algorithm

```java
while ((i < smallest) && (needed > 0)) {
  needed *= 2;           // Double: 2 A2s make 1 A1, 2 A3s make 1 A2, etc.
  int have = scan.nextInt();
  tape += needed * bigger / 2;  // Tape to join N sheets = (N-1) × long_side
  bigger *= sizeDown;    // Scale dimensions for next smaller size
  needed -= have;        // Reduce need by what we have
  i++;
}
```

The tape calculation `needed * bigger / 2` represents:
- If we need `N` sheets and we're joining them sequentially, we need `(N-1)` tapes
- So tape ≈ N × (long_side) / 2
- But more precisely, it accumulates the perimeter of edges being joined

## Concrete Example

**Input:**
```
4 1 0 5
```
This means:
- Smallest paper size is A4 (size index 4)
- We have: 1 A2, 0 A3, 5 A4

**Step-by-step execution:**

| Iteration | Size | needed | have | tape added                | new needed | bigger   |
|-----------|------|--------|------|---------------------------|------------|----------|
| Start     | A1   | 1      | -    | -                         | 1          | 2^(-3/4) |
| 1         | A2   | 2      | 1    | 2 × 2^(-3/4) / 2 ≈ 0.5946 | 1          | 2^(-5/4) |
| 2         | A3   | 2      | 0    | 2 × 2^(-5/4) / 2 ≈ 0.4204 | 2          | 2^(-7/4) |
| 3         | A4   | 4      | 5    | 4 × 2^(-7/4) / 2 ≈ 0.5946 | 0          | -        |

**Result:**
- Total tape ≈ 0.5946 + 0.4204 + 0.5946 ≈ 1.6096 meters ✓
- needed = 0, so output the tape length

## Why The Solution Works

1. **Exponential doubling**: Each size needs exactly double the previous (mathematical property of A-series)
2. **Backward compatibility**: By working backwards, we can greedily use what we have and track shortfalls
3. **Tape calculation**: The formula accounts for the area-to-length relationship in A-series papers
4. **Termination**: Loop stops when we've processed all sizes down to what we have, or if we have enough papers

## Edge Cases Handled

- **Not enough paper**: If `needed > 0` after exhausting available sizes, output "impossible"
- **Extra paper**: If `needed` becomes negative (we have more than we need), we exit the loop
- **Exact match**: If `needed == 0`, we used exactly what we needed
