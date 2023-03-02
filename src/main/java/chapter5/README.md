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

Thread number:[10], Insert elements number: [10000], The [synchronizedMap]'s execution time:[2004173761]
Thread number:[10], Insert elements number: [10000], The [concurrentHashMap]'s execution time:[869936449]
Thread number:[10], Insert elements number: [10000], The [improvedMap]'s execution time:[746303678]

##### 2nd time around:

Thread number:[10], Insert elements number: [50000], The [synchronizedMap]'s execution time:[3555030832]
Thread number:[10], Insert elements number: [50000], The [concurrentHashMap]'s execution time:[2123428974]
Thread number:[10], Insert elements number: [50000], The [improvedMap]'s execution time:[2186931819]

##### 3rd time around:

Thread number:[10], Insert elements number: [100000], The [synchronizedMap]'s execution time:[4689291638]
Thread number:[10], Insert elements number: [100000], The [concurrentHashMap]'s execution time:[3697688260]
Thread number:[10], Insert elements number: [100000], The [improvedMap]'s execution time:[3855817621]



#### Performance Test Result of MapPerformanceTestByJMH.java 

##### Each of the 10 threads inserts 10,000 elements into a map at the same time.

Benchmark                                      Mode  Cnt    Score     Error  Units
MapPerformanceTestByJMH.testConcurrentHashMap  thrpt   10  190.087 ± 112.032  ops/s
MapPerformanceTestByJMH.testImprovedMap        thrpt   10  137.022 ±  26.556  ops/s
MapPerformanceTestByJMH.testSynchronizedMap    thrpt   10  130.179 ±  14.412  ops/s

##### Each of the 10 threads inserts 50,000 elements into a map at the same time.

Benchmark                                      Mode  Cnt   Score   Error  Units
MapPerformanceTestByJMH.testConcurrentHashMap  thrpt   10  55.453 ± 7.530  ops/s
MapPerformanceTestByJMH.testImprovedMap        thrpt   10  35.170 ± 5.101  ops/s
MapPerformanceTestByJMH.testSynchronizedMap    thrpt   10  35.439 ± 3.525  ops/s

##### Each of the 10 threads inserts 100,000 elements into a map at the same time.

Benchmark                                      Mode  Cnt   Score   Error  Units
MapPerformanceTestByJMH.testConcurrentHashMap  thrpt   10  28.121 ± 1.864  ops/s
MapPerformanceTestByJMH.testImprovedMap        thrpt   10  20.060 ± 1.553  ops/s
MapPerformanceTestByJMH.testSynchronizedMap    thrpt   10  17.781 ± 1.634  ops/s