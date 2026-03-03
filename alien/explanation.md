# Alien Integers — Detailed Explanation

This document explains the approach taken in `Main1.java` to solve the "Alien Integers" problem from Kattis:
https://open.kattis.com/problems/alienintegers

## Problem Summary
Given a non-negative integer **N** (up to 1,000,000 digits), we consider its decimal representation and determine which digits from 0 to 9 are **not** present in it. Those missing digits define a new numeral system that the alien civilization uses. The task is to find the integer(s) that:

1. Consist solely of digits that do **not** appear in N.
2. Are as close as possible to N by absolute difference.
3. If multiple such integers tie for minimal difference, list them all in ascending order.
4. If every digit from 0–9 appears in N, print `Impossible`.

## Key Observations
- Only the digits absent from **N** may be used. We call them **alien digits**.
- The candidate numbers can have lengths **L-1**, **L**, or **L+1**, where **L** is the length of N's string.
- For each length, we construct the extremal possible number using the allowable digits:
  - *Length L–1*: largest number of that size (e.g. `999` if 9 is allowed).
  - *Length L+1*: smallest number of that size, ensuring the first digit is non-zero.
  - *Length L*: We consider all alien digits for the first place, and then fill the rest with either the minimum or maximum allowed digit depending on whether the prefix is smaller or larger than N.
- Avoid leading zeros for multi-digit candidates.

## Algorithm Details
1. **Read input** as a string to preserve leading zeros and to easily gather digit counts.
2. **Detect used digits** with a boolean array. If all 10 digits appear, output `Impossible`.
3. **Collect alien digits** into a list and determine the smallest (`minD`) and largest (`maxD`).
4. **Generate possible candidates** based on length rules (L-1, L, L+1), taking care of zero restrictions.
5. **Compute absolute differences** between each candidate and N.
6. **Track the smallest difference** and all candidates that achieve it using a sorted set (`TreeSet`).
7. **Print results** separated by spaces, or `Impossible` if no candidates exist.

## Example
Input: `123`
- Used digits: {1, 2, 3}
- Alien digits: {0, 4, 5, 6, 7, 8, 9}
- Generate candidates like `99`, `400`, `500`, `600`, `700`,`800`,`900`, `4000`, etc.
- Determine which is closest to `123` (in this case `99`).

Input: `3`
- Used digits: {3}
- Alien digits: {0, 1, 2, 4, 5, 6, 7, 8, 9}
- Generate candidates like `0`, `1`, `2`, `4`, `5`, `6`, `7`, `8`, `9`, `10`.
- Determine which is closest to `3` (in this case `2` and `4`).

## Notes on Implementation
- A helper method `repeatDigit(int digit, int times)` builds a string by repeating a single digit.
- The program handles extremely large inputs by treating them as strings and using `Long.parseLong` only on constructed candidate numbers (all within 64-bit range due to constraints). However, in practice, candidates may exceed `long` if N has thousands of digits; the input limit ensures that either actual values are manageable or the problem is solved via string logic.

## Edge Cases
- **Leading zeros**: Skip constructing numbers that would begin with zero unless the number is exactly `0`.
- **All digits used**: Immediately output `Impossible`.
- **Tie for closest**: Print all matching candidates in sorted order.
