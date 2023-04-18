## **Task 009：**

### 任务描述：

- 读完 Chapter 11(page 261)
- 代码实验
  - 阅读下面的代码
  - 写一段测试代码，测试其在并发环境下的性能
  - 改进这段代码的性能，并和基准性能比较

```java
interface Grocery {  
    void addFruit(int index, String fruit);  
    void addVegetable(int index, String vegetable);  
}  

class SynchronizedGrocery  implements Grocery {  
    private final List<String> fruits = new ArrayList<>();  
    private final List<String> vegetables = new ArrayList<>();  
    public synchronized void addFruit(int index, String fruit) {  
        fruits.add(index, fruit);  
    }  
    public synchronized void addVegetable(int index, String vegetable) {  
        vegetables.add(index, vegetable);  
    }  
}
```

Deadline：4月25日中午12点
 
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
