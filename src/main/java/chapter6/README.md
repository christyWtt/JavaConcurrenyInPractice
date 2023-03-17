## **Task 005：**

### 任务描述：

- 读完 Chapter 6 (page 142)
- 改进 Listing 5.11 提供的 TestHarness 工具，新类名 ImprovedTestHarness，要求：
  - 使用 CyclicBarrier 而不是 CountDownLatch;
  - 不得使用 unbounded thread;
  - 分别使用 ImprovedTestHarness 和 Listing 5.11 提供的TestHarness 对 Task 004进行简单(非严格)的度量，比较结果；

Deadline：3月15日中午12点

最先完成任务且无明显缺陷的前3名同学将各获得一枚免死金牌 🏅️
指出前三名明显缺陷的同学可以抢得🏅️


------

#### Performance Test Result
##### Test method:[TestHarness.timeTasks]
1. Thread number:[10], Insert elements number: [1000000], The [synchronizedMap]'s execution time:[2496338457]
2. Thread number:[10], Insert elements number: [1000000], The [concurrentHashMap]'s execution time:[2430365780]
3. Thread number:[10], Insert elements number: [1000000], The [improvedMap]'s execution time:[1552848079]

##### Test method:[ImprovedTestHarness.timeTasks]
1. Thread number:[10], Insert elements number: [1000000], The [synchronizedMap]'s execution time:[1955551686]
2. Thread number:[10], Insert elements number: [1000000], The [concurrentHashMap]'s execution time:[1210734760]
3. Thread number:[10], Insert elements number: [1000000], The [improvedMap]'s execution time:[1332947335]

##### Test method:[ImprovedTestHarness.timeTasksWithFuture]
1. Thread number:[10], Insert elements number: [1000000], The [synchronizedMap]'s execution time:[1218751314]
2. Thread number:[10], Insert elements number: [1000000], The [concurrentHashMap]'s execution time:[768958005]
3. Thread number:[10], Insert elements number: [1000000], The [improvedMap]'s execution time:[1323613359]