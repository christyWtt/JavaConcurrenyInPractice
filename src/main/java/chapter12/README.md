## **Task 010：**

### 任务描述：

- 读完 Chapter 12(page 292): Testing Concurrent Programs
- 代码实验
  - 针对 Task 009 的 SynchronizedGrocery 和你改进之后的版本
  - 基于JSR166TestCase (https://github.com/google/guava/blob/master/guava-tests/test/com/google/common/util/concurrent/JSR166TestCase.java) 正确性测试
  - 基于JMH https://github.com/openjdk/jmh 进行性能测试

Deadline：5月10日中午12点

最先完成任务且无明显缺陷的前3名同学将各获得一枚免死金牌 🏅️
指出前三名明显缺陷的同学可以抢得🏅️

------

#### Performance Test Result of GroceryPerformanceTestByJMH.java

##### Each of the 10 threads inserts 10,000 elements into lists at the same time.

| Benchmark                                                     | Mode  | Cnt  | Score  | Error    | Units |
|---------------------------------------------------------------| ----- | ---- |--------|----------| ----- |
| GroceryPerformanceTestByJMH.testSingleLockGrocery             | thrpt | 25   | 0.284  | ± 0.016  | ops/s |
| GroceryPerformanceTestByJMH.testSynchronizedGrocery           | thrpt | 25   | 0.263  | ± 0.013  | ops/s |
| GroceryPerformanceTestByJMH.testSynchronizedCollectionGrocery | thrpt | 25   | 0.219  | ± 0.014  | ops/s |
| GroceryPerformanceTestByJMH.testVectorGrocery                 | thrpt | 25   | 0.202  | ± 0.011  | ops/s |
| GroceryPerformanceTestByJMH.testCopyOnWriteGrocery            | thrpt | 25   | 0.106  | ± 0.004  | ops/s |
