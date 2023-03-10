## **Task 004：**

### 任务描述：

- 读完 Chapter 5 (page 118)
- 测试以下3个Map 在并发场景的性能:
  - Collections.synchronizedMap
  - ConcurrentHashMap
  - 你在 Task 003 中实现的线程安全的 `Map`
- 测试方式二选一：
  - 使用 Listing 5.11 提供的TestHarness 工具，进行简单(非严格)的度量；
  - 或者采用JMH (可以参考https://confluence.kingland-apps.com/display/~biyan@ksd.kingland.cc/2022/03/25/Code+Performance+and+Microbenchmarking)， 进行相对严格的度量；

Deadline：3月6日中午12点

最先完成任务且无明显缺陷的前3名同学将各获得一枚免死金牌 🏅️
指出前三名明显缺陷的同学可以抢得🏅️

使用相对严格模式证实自己的实现比 ConcurrentHashMap性能更好的，可以获得🏅️一枚

------

#### Performance Test Result of MapPerformanceTestByTestHarness.java

##### 1st time around:

1. Thread number:[10], Insert elements number: [10000], The [synchronizedMap]'s execution time:[89013454]
2. Thread number:[10], Insert elements number: [10000], The [concurrentHashMap]'s execution time:[107672002]
3. Thread number:[10], Insert elements number: [10000], The [improvedMap]'s execution time:[28068123]

##### 2nd time around:

1. Thread number:[10], Insert elements number: [50000], The [synchronizedMap]'s execution time:[184427106]
2. Thread number:[10], Insert elements number: [50000], The [concurrentHashMap]'s execution time:[242584787]
3. Thread number:[10], Insert elements number: [50000], The [improvedMap]'s execution time:[53475196]

##### 3rd time around:

1. Thread number:[10], Insert elements number: [100000], The [synchronizedMap]'s execution time:[198636921]
2. Thread number:[10], Insert elements number: [100000], The [concurrentHashMap]'s execution time:[425830746]
3. Thread number:[10], Insert elements number: [100000], The [improvedMap]'s execution time:[194187151]



#### Performance Test Result of MapPerformanceTestByJMH.java

##### Each of the 10 threads inserts 10,000 elements into a map at the same time.

| Benchmark                                     | Mode  | Cnt  | Score   | Error    | Units |
| --------------------------------------------- | ----- | ---- |---------|----------| ----- |
| MapPerformanceTestByJMH.testConcurrentHashMap | thrpt | 10   | 225.316 | ± 37.813 | ops/s |
| MapPerformanceTestByJMH.testImprovedMap       | thrpt | 10   | 127.230 | ± 15.126 | ops/s |
| MapPerformanceTestByJMH.testSynchronizedMap   | thrpt | 10   | 79.882  | ± 2.986  | ops/s |

##### Each of the 10 threads inserts 50,000 elements into a map at the same time.

| Benchmark                                     | Mode  | Cnt  | Score  | Error   | Units |
| --------------------------------------------- | ----- | ---- |--------| ------- | ----- |
| MapPerformanceTestByJMH.testConcurrentHashMap | thrpt | 10   | 40.698 | ± 4.916 | ops/s |
| MapPerformanceTestByJMH.testImprovedMap       | thrpt | 10   | 30.730 | ± 3.540 | ops/s |
| MapPerformanceTestByJMH.testSynchronizedMap   | thrpt | 10   | 23.162 | ± 3.905 | ops/s |

##### Each of the 10 threads inserts 100,000 elements into a map at the same time.

| Benchmark                                     | Mode  | Cnt  | Score  | Error   | Units |
| --------------------------------------------- | ----- | ---- |--------|---------| ----- |
| MapPerformanceTestByJMH.testConcurrentHashMap | thrpt | 10   | 19.485 | ± 2.425 | ops/s |
| MapPerformanceTestByJMH.testImprovedMap       | thrpt | 10   | 20.659 | ± 1.553 | ops/s |
| MapPerformanceTestByJMH.testSynchronizedMap   | thrpt | 10   | 11.234 | ± 0.176 | ops/s |
