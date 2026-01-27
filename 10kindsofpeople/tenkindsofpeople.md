# 10 Kinds of People - Solution

## Problem Summary

The problem asks us to determine if two locations on a grid are connected through cells of the same type (0 or 1). A "binary user" can only traverse through cells with value 0, and a "decimal user" can only traverse through cells with value 1.

For each query:
- Output **"binary"** if both points are connected through 0s
- Output **"decimal"** if both points are connected through 1s
- Output **"neither"** if they're not connected or have different values

## Original Issues

The original recursive DFS solution had critical flaws:

1. **Map Mutation**: The code modified `map[r1][c1] = -1` to mark visited cells. After the first query, the map was corrupted, causing incorrect results for subsequent queries.

2. **Stack Overflow Risk**: Deep recursion for grids up to 1000×1000 could exceed the stack limit.

3. **Inefficiency**: Each query performed a complete search from scratch, O(rows × cols) per query, leading to O(queries × rows × cols) total time complexity.

## Refactored Solution

The optimized approach uses **connected component labeling** with BFS:

### Algorithm

1. **Pre-processing Phase** (O(rows × cols)):
   - Scan the entire grid
   - For each unvisited cell, perform BFS to label all connected cells with the same component ID
   - Store the value (0 or 1) for each component

2. **Query Phase** (O(1) per query):
   - For each query, check if both points have the same component ID
   - If yes, output the corresponding type ("binary" or "decimal")
   - If no, output "neither"

### Key Improvements

| Aspect | Original | Refactored |
|--------|----------|-----------|
| **Time Complexity** | O(queries × rows × cols) | O(rows × cols + queries) |
| **Space Complexity** | O(rows × cols) | O(rows × cols) |
| **Map Mutation** | ✗ Mutates during search | ✓ No mutation |
| **Stack Overflow Risk** | ✓ High (deep recursion) | ✗ None (iterative BFS) |
| **Query Response** | O(rows × cols) | O(1) |

## Implementation Details

### Data Structures

```java
int[][] map              // Original grid (0 or 1)
int[][] component        // Component ID for each cell
int[] componentValue     // Value (0 or 1) for each component ID
```

### BFS Component Labeling

```java
private static void bfsLabel(int startR, int startC, int compId, int value)
```

- Uses a queue for iterative traversal
- Marks cells with the component ID
- Explores in 4 directions: north, south, east, west
- Only visits unvisited cells with matching value

### Query Processing

```java
if(component[r1][c1] == component[r2][c2]) {
    // Same component - output the value type
} else {
    // Different components - output "neither"
}
```

## Test Results

### Sample Test 1
```
Input:  1 4
        1100
        2
        1 1 1 4
        1 1 1 1

Output: neither
        decimal
```
✓ Correct

### Sample Test 2 (Checkerboard)
```
Input:  3 3
        010
        101
        010
        4
        1 1 1 2  → Different values (0 and 1)
        1 1 2 1  → Different values (0 and 1)
        1 2 3 2  → Different values (0 and 1)
        2 2 2 2  → Same cell (0)

Output: neither
        neither
        neither
        binary
```
✓ Correct

## Complexity Analysis

- **Preprocessing**: O(rows × cols) - Each cell visited exactly once
- **Per Query**: O(1) - Simple component comparison
- **Total**: O(rows × cols + queries) = O(1,000,000 + 1,000) = O(1,000,000)

This is efficient enough for the constraints: 1 ≤ rows, cols ≤ 1000 and 0 ≤ queries ≤ 1000.
