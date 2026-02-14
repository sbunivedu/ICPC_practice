# Luke.java Explanation

## Summary

- Problem: Arrange children (given ages) in a line so every adjacent pair has gcd > 1; if impossible print "Neibb".
- High-level approach: Build an undirected graph where each child is a vertex and an edge exists between two vertices iff gcd(age_i, age_j) > 1. Search for a Hamiltonian path that visits all vertices exactly once. If found, print the ages in that order; otherwise print "Neibb".

## Key Data Structures

- `children[]`: the ages (input values) — nodes of the graph.
- `adj[][]`: adjacency matrix, `1` when two indices share a factor > 1, else `0`.
- `order[]`: stores a partial or complete permutation of indices; initialized to `-1`.
- `used[]`: flags (0/1) marking whether an index is already placed in `order`.

## Early Pruning

- If any age equals `1`, the program prints `Neibb` and exits immediately because `gcd(1, x) == 1` for all `x`, so a node with age `1` cannot be adjacent to any other node.

## Graph Construction

- For every pair `(i, j)` with `i != j`, compute `GCD(children[i], children[j])`. If result != 1, set `adj[i][j] = adj[j][i] = 1`; otherwise set to `0`.

## Search Method — Iterative Backtracking

- The code implements backtracking without recursion using two counters:
  - `counter1` — current depth / next free position in `order` (how many chosen so far).
  - `counter` — candidate index being tested for the current `counter1` position.

- Algorithm flow (choose-explore-unchoose):
  1. If `used[counter] == 0` (candidate unused):
     - If `counter1 == 0`, place candidate as first element.
     - Else, require `adj[order[counter1-1]][counter] == 1` to place it.
     - When placed: mark `used`, advance `counter1`, reset `counter` to `0` for the next depth.
     - If no candidate remains (`counter` reaches `len`), backtrack: decrement `counter1`, restore `used` for that index, and resume trying the next candidate.
  2. If `used[counter] == 1`, skip it (`counter++`) and backtrack if candidates are exhausted.

- The loop ends successfully when `order[len-1] != -1` (a full ordering is found), printing the ages in that order. If the algorithm backtracks past the first position, it prints `Neibb`.

## Why This Works

- The iterative search explores all permutations consistent with adjacency constraints. If any Hamiltonian path exists in the graph, this backtracking will find it.
- Nodes are treated as distinct by index, so duplicate ages are allowed and handled correctly.

## Complexity

- Building adjacency: O(n^2 · cost(GCD)).
- Search: exponential in `n` (worst-case O(n!)) because finding a Hamiltonian path is combinatorial.
- Space: O(n^2) for the adjacency matrix and O(n) for recursion-equivalent state (`order`, `used`).

Hamiltonian Path

* Definition: A path in a graph that visits every vertex exactly once.
* Hamiltonian cycle: A Hamiltonian path that starts and ends at the same vertex (also called a Hamiltonian circuit).
* Example: In a path graph with vertices A—B—C, A→B→C is a Hamiltonian path; it's a cycle only if there is an edge C—A.
* Decision complexity: Determining whether a general graph has a Hamiltonian path is **NP-complete** (no known polynomial-time algorithm).
* Why it matters here: `Luke.java` builds a graph (vertices = children, edge if gcd>1) and searches for an ordering of all vertices — exactly a Hamiltonian path problem on that graph.
* Practical note: For small n or structured graphs, **backtracking** (like the code) can find a Hamiltonian path; for large arbitrary graphs you need heuristics or exponential-time search.

A related problem is TSP (Traveling Salesman Problem): given a complete weighted graph, find a Hamiltonian cycle of minimum total weight (or report its cost).

Every Hamiltonian-cycle instance can be encoded as a TSP instance: give weight 1 to graph edges present and a large weight (or ∞) to non-edges. A low-cost TSP tour then corresponds to a **Hamiltonian cycle** in the original graph.

Thus TSP (decision: is there a tour ≤ K?) is NP-hard; Hamiltonian cycle is **NP-complete**, and TSP generalizes it.

Hamiltonian Path/Cycle (decision) is NP-complete.
TSP (optimization) is NP-hard; its decision form is NP-complete.
Practical/algorithmic notes:
Exact TSP algorithms (Held–Karp DP) run in O(n^2 2^n) time; backtracking can solve Hamiltonian path/cycle for small n.
For metric TSP there are approximation algorithms (e.g., Christofides gives 1.5-approximation); no polynomial approximation exists for general TSP unless **P=NP**.

Relevance to Luke.java / LukeRecur.java:
Those programs search for a Hamiltonian path in an unweighted graph (edge exists if gcd>1). They solve the existence/search problem (backtracking), not an optimization TSP.

## Remarks & Possible Improvements

- The iterative implementation is functionally correct but harder to read than a recursive backtracker. `LukeRecur.java` provides a clearer recursive version.
- Useful early checks not implemented: connectedness of the graph (if the graph is disconnected, no Hamiltonian path exists), degree-based pruning, or sorting/heuristic choices to improve search order.
- For large `n` this exact approach may be too slow; consider heuristics or problem-specific observations if input sizes justify them.
