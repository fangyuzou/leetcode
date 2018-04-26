这道题寻找配对的 a 和 b 使得 a + b = target. 采用 HashMap 记录 (nums[i], i) pair. 对每个key nums[i],
寻找与之配对的 target - nums[i] 是否已经在key中.
