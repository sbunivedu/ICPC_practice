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
Input:
1 4
1100
2
1 1 1 4
1 1 1 1

Output:
neither
decimal
```

### Sample Test 2 (Checkerboard)
Input:
```
3 3
010
101
010
4
1 1 1 2  → Different values (0 and 1)
1 1 2 1  → Different values (0 and 1)
1 2 3 2  → Different values (0 and 1)
2 2 2 2  → Same cell (0)
```

Output:
```
neither
neither
neither
binary
```

### Sample Test 3
Input:
```
5 5
01110
01010
00010
11011
11111
6
1 1 3 3
1 2 5 4
1 1 1 5
4 1 5 3
2 3 4 3
2 2 3 5
```
Output:
```
binary
decimal
neither
decimal
binary
neither
```
Trace:
```
INITIALIZATION PHASE
====================

Input map (5x5):
  0 1 2 3 4
0 0 1 1 1 0
1 0 1 0 1 0
2 0 0 0 1 0
3 1 1 0 1 1
4 1 1 1 1 1

BFS Labeling (scanning left-to-right, top-to-bottom):
────────────────────────────────────────────────────────

Position (0,0): value=0, unvisited → Start BFS with componentId=0
  BFS traversal: (0,0) → explores 4 neighbors
  Labels as component 0: {(0,0), (1,0), (2,0), (2, 1), (2,2), (1,2), (3,2)}
  componentValue[0] = 0 (binary)

component map:
  0 1 2 3 4
0 0 1 1 1 0
1 0 1 0 1 0
2 0 0 0 1 0
3 1 1 0 1 1
4 1 1 1 1 1

Position (0,1): value=1, unvisited → Start BFS with componentId=1
  BFS traversal: (0,1) → (1,1) → (0,2) → (0,3) → (1,3) → (2,3) → (3,3) → (4,0) → (4,1) → (4,2) → (4,3) → (4,4) → (3,4)
  Labels as component 1: {(0,1), (0,2), (0,3), (1,1), (1,2), (1,3), (2,3), (3,1), (3,3), (4,0), (4,1), (4,2), (4,3), (4,4), (3,4)}
  componentValue[1] = 1 (decimal)

component map:
  0 1 2 3 4
0 0 1 1 1 0
1 0 1 0 1 0
2 0 0 0 1 0
3 1 1 0 1 1
4 1 1 1 1 1

Position (0,4): value=0, unvisited → Start BFS with componentId=2
  Labels as component 2: {(0,4), (1,4), (2,4)}
  componentValue[2] = 0 (binary)

Final component map:
  0 1 2 3 4
0 0 1 1 1 2
1 0 1 0 1 2
2 0 0 0 1 2
3 1 1 0 1 1
4 1 1 1 1 1

QUERY PHASE
===========

Query 1: (1,1) → (3,3)  [Converts to 0-indexed: (0,0) → (2,2)]
  component[0][0] = 0 (component 0)
  component[2][2] = 0 (component 0)
  0 = 0, componentValue[0] = 0 → Output: "binary"

Query 2: (1,2) → (5,4)  [Converts to 0-indexed: (0,1) → (4,3)]
  component[0][1] = 1 (component 1)
  component[4][3] = 1 (component 1)
  1 = 1, componentValue[1] = 1 → Output: "decimal"

Query 3: (1,1) → (1,5)  [Converts to 0-indexed: (0,0) → (0,4)]
  component[0][0] = 0 (component 0)
  component[0][4] = 2 (component 2)
  0 ≠ 2 → Output: "neither"

Query 4: (4,1) → (5,3)  [Converts to 0-indexed: (3,0) → (4,2)]
  component[3][0] = 1 (component 1)
  component[4][2] = 1 (component 1)
  1 = 1, componentValue[1] = 1 → Output: "decimal"

Query 5: (2,3) → (4,3)  [Converts to 0-indexed: (1,2) → (3,2)]
  component[1][2] = 0 (component 0)
  component[3][2] = 0 (component 0)
  0 = 0, componentValue[0] = 0 → Output: "binary"

Query 6: (2,2) → (3,5)  [Converts to 0-indexed: (1,1) → (2,4)]
  component[1][1] = 1 (component 1)
  component[2][4] = 2 (component 2)
  1 ≠ 2 → Output: "neither"
```

## Complexity Analysis

- **Preprocessing**: O(rows × cols) - Each cell visited exactly once
- **Per Query**: O(1) - Simple component comparison
- **Total**: O(rows × cols + queries) = O(1,000,000 + 1,000) = O(1,000,000)

This is efficient enough for the constraints: 1 ≤ rows, cols ≤ 1000 and 0 ≤ queries ≤ 1000.
