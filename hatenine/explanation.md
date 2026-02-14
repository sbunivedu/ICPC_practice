# I Hate The Number Nine - Solution Explanation

## Problem Statement
Count how many positive numbers with exactly `d` digits do not contain the digit 9. Output the result modulo 1000000007.

## Solution Approach

### Combinatorics Analysis
For a d-digit positive number without the digit 9:

- **First digit**: Can be 1, 2, 3, 4, 5, 6, 7, or 8 → **8 choices** (cannot be 0 or 9)
- **Remaining (d-1) digits**: Each can be 0, 1, 2, 3, 4, 5, 6, 7, or 8 → **9 choices each** (cannot be 9)

Therefore, the total count is: **8 × 9^(d-1)**

### Examples
- d=1: 8 × 9^0 = 8 × 1 = **8** ✓ (numbers: 1-8)
- d=2: 8 × 9^1 = 8 × 9 = **72** ✓ (first digit 1-8, second digit 0-8)
- d=100: 8 × 9^99 mod 1000000007 = **343393926** ✓

## Implementation Details

Since d can be as large as 10^18, computing 9^(d-1) directly would take too long. The solution uses **binary exponentiation (fast exponentiation)** to compute the power efficiently in O(log d) time.

### Algorithm
1. Initialize `result = 1` and `base = 9`
2. While `exponent > 0`:
   - If exponent is odd, multiply result by base (mod 1000000007)
   - Square the base (mod 1000000007)
   - Divide exponent by 2 (integer division)
3. Multiply final result by 8 (mod 1000000007)

### Time Complexity
- O(log d) per test case for binary exponentiation
- O(T × log d) overall, where T is the number of test cases

## Key Takeaways for Competitive Programmers

### 1. **Combinatorics is Powerful**
Before reaching for complex algorithms, identify the mathematical structure. This problem reduces to a simple formula (8 × 9^(d-1)) through careful counting—recognizing that the first digit has 8 choices and remaining digits have 9 choices each.

### 2. **Use Modular Arithmetic Throughout**
When outputting results modulo a prime (1000000007), apply the modulo operation **during computation**, not just at the end. This prevents integer overflow on large intermediate values: `result %= 1000000007` after each multiplication.

### 3. **Binary Exponentiation is Essential**
With exponents reaching 10^18, naive exponentiation is impossible. **Fast exponentiation** reduces O(d) to O(log d) by exploiting the binary representation of the exponent—this technique is fundamental for modular exponentiation problems.

### 4. **Think About Constraints Early**
The constraint that d can be up to 10^18 signals that direct computation won't work. This forces you to think mathematically rather than simulate. Always analyze input bounds to guide your algorithmic choices.

### 5. **Keep Intermediate Values Modulo-Reduced**
Applying modulo after squaring the base (`base %= 1000000007`) prevents overflow and keeps numbers manageable throughout the algorithm.

### 6. **Test with Edge Cases**
The solution correctly handles d=1 (result is 8) and validates the formula with worked examples before implementing.
