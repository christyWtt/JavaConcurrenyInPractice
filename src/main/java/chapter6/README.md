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

#### Performance Test Result of MapPerformanceTestByImprovedTestHarness.java

##### 1st time around:

1. Thread number:[10], Insert elements number: [100000], The [synchronizedMap]'s execution time:[203347475]
2. Thread number:[10], Insert elements number: [100000], The [concurrentHashMap]'s execution time:[171440847]
3. Thread number:[10], Insert elements number: [100000], The [improvedMap]'s execution time:[119546826]

##### 2nd time around:

1. Thread number:[10], Insert elements number: [1000000], The [synchronizedMap]'s execution time:[1298834369]
2. Thread number:[10], Insert elements number: [1000000], The [concurrentHashMap]'s execution time:[661098871]
3. Thread number:[10], Insert elements number: [1000000], The [improvedMap]'s execution time:[722855685]

##### 3rd time around:

1. Thread number:[10], Insert elements number: [10000000], The [synchronizedMap]'s execution time:[9606519328]
2. Thread number:[10], Insert elements number: [10000000], The [concurrentHashMap]'s execution time:[7043498117]
3. Thread number:[10], Insert elements number: [10000000], The [improvedMap]'s execution time:[9859073457]

#### Performance Test Result of MapPerformanceTestByTestHarness.java

##### 1st time around:

1. Thread number:[10], Insert elements number: [100000], The [synchronizedMap]'s execution time:[204776485]
2. Thread number:[10], Insert elements number: [100000], The [concurrentHashMap]'s execution time:[194734441]
3. Thread number:[10], Insert elements number: [100000], The [improvedMap]'s execution time:[114288792]

##### 2nd time around:

1. Thread number:[10], Insert elements number: [1000000], The [synchronizedMap]'s execution time:[1210934928]
2. Thread number:[10], Insert elements number: [1000000], The [concurrentHashMap]'s execution time:[4233712530]
3. Thread number:[10], Insert elements number: [1000000], The [improvedMap]'s execution time:[799863930]

##### 3rd time around:

1. Thread number:[10], Insert elements number: [10000000], The [synchronizedMap]'s execution time:[9632888834]
2. Thread number:[10], Insert elements number: [10000000], The [concurrentHashMap]'s execution time:[6775976122]
3. Thread number:[10], Insert elements number: [10000000], The [improvedMap]'s execution time:[8787005643]