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

1. Thread number:[10], Insert elements number: [100000], The [synchronizedMap]'s execution time:[137497685]
2. Thread number:[10], Insert elements number: [100000], The [concurrentHashMap]'s execution time:[420243841]
3. Thread number:[10], Insert elements number: [100000], The [improvedMap]'s execution time:[73645222]

##### 2nd time around:

1. Thread number:[10], Insert elements number: [1000000], The [synchronizedMap]'s execution time:[1081626892]
2. Thread number:[10], Insert elements number: [1000000], The [concurrentHashMap]'s execution time:[1198313871]
3. Thread number:[10], Insert elements number: [1000000], The [improvedMap]'s execution time:[589260058]

##### 3rd time around:

1. Thread number:[10], Insert elements number: [10000000], The [synchronizedMap]'s execution time:[8965381830]
2. Thread number:[10], Insert elements number: [10000000], The [concurrentHashMap]'s execution time:[5765383616]
3. Thread number:[10], Insert elements number: [10000000], The [improvedMap]'s execution time:[9217293556]

#### Performance Test Result of MapPerformanceTestByTestHarness.java

##### 1st time around:

1. Thread number:[10], Insert elements number: [100000], The [synchronizedMap]'s execution time:[199424714]
2. Thread number:[10], Insert elements number: [100000], The [concurrentHashMap]'s execution time:[176289888]
3. Thread number:[10], Insert elements number: [100000], The [improvedMap]'s execution time:[132407406]

##### 2nd time around:

1. Thread number:[10], Insert elements number: [1000000], The [synchronizedMap]'s execution time:[1107319219]
2. Thread number:[10], Insert elements number: [1000000], The [concurrentHashMap]'s execution time:[1133236406]
3. Thread number:[10], Insert elements number: [1000000], The [improvedMap]'s execution time:[588259503]

##### 3rd time around:

1. Thread number:[10], Insert elements number: [10000000], The [synchronizedMap]'s execution time:[9775139596]
2. Thread number:[10], Insert elements number: [10000000], The [concurrentHashMap]'s execution time:[7350619549]
3. Thread number:[10], Insert elements number: [10000000], The [improvedMap]'s execution time:[9810450682]