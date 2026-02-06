import java.util.*;
// TC: O(N), Auxiliary space: O(K)
class Solution {

    public int[] maxSlidingWindow(int[] nums, int k) {
        final int n = nums.length;
        int[] result = new int[n - k + 1];

        // Deque stores indices (not values)
        // WHY: indices help us
        // 1) know when an element goes out of the window
        // 2) access values from nums when needed
        Deque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {

            // 1. Remove index from FRONT if it is out of the current window
            // WHY:
            // Current window is [i - k + 1, i]
            // If the index at the front == i - k,
            // it means that element is no longer inside the window
            // Keeping it would give a wrong maximum
            if (!dq.isEmpty() && dq.peekFirst() == i - k) {
                dq.pollFirst();
            }

            // 2. Remove smaller elements from BACK
            // WHY:
            // If nums[i] is greater than elements at the back,
            // those elements can NEVER become the maximum
            // because:
            // - nums[i] is bigger
            // - nums[i] will stay in the window longer (newer index)
            // So we remove all smaller useless candidates
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i]) {
                dq.pollLast();
            }

            // 3. Add current index to BACK
            // WHY:
            // This element may become maximum for the current
            // or future windows, so we keep it as a candidate
            dq.offerLast(i);

            // 4. When the first window is complete, record the max
            // WHY:
            // Before index (k - 1), the window size is smaller than k
            // Once i >= k - 1, we have a full window
            // The front of the deque always stores the index
            // of the maximum element for the current window
            if (i >= k - 1) {
                result[i - k + 1] = nums[dq.peekFirst()];
            }
        }

        return result;
    }
}
