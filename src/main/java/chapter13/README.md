## **Task 010：**

### 任务描述：

- 读完 Chapter 13(page 208): Explicit lock
- 代码实验
  - 使用 ReentrantLock 改进 Task 009 的 SynchronizedGrocery
  - 分别使用非公平锁(nonfair lock ) 和 公平锁 (fair lock) 实现并基于JMH https://github.com/openjdk/jmh 进行性能对比

Deadline：5月22日中午12点

最先完成任务且无明显缺陷的前3名同学将各获得一枚免死金牌 🏅️
指出前三名明显缺陷的同学可以抢得🏅️

------

#### Performance Test Result of ReentrantLockPerformanceTestByJMH.java in chapter13

##### Each of the 10 threads inserts 10,000 elements into lists at the same time.
(More is better)

| Benchmark                                                     | Mode  | Cnt  | Score  | Error    | Units |
|---------------------------------------------------------------| ----- | ---- |--------|----------| ----- |
| ReentrantLockPerformanceTestByJMH.testFairLock                | thrpt | 25   | 0.242  | ± 0.030  | ops/s |
| ReentrantLockPerformanceTestByJMH.testNonFairLock             | thrpt | 25   | 0.431  | ± 0.054  | ops/s |