# JUnit is not a suitable testing framework

JUnit output:
```
2) test_search(BPlusTreeTest)
junit.framework.ComparisonFailure: expected:<[[10][50][70][120],]*NULL*> but was:<[]*NULL*>
	at junit.framework.Assert.assertEquals(Assert.java:100)
	at junit.framework.Assert.assertEquals(Assert.java:107)
	at junit.framework.TestCase.assertEquals(TestCase.java:269)
	at BPlusTreeTest.test_search(BPlusTreeTest.java:94)
	at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
	at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62)
	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
	at java.lang.reflect.Method.invoke(Method.java:498)
	at junit.framework.TestCase.runTest(TestCase.java:176)
	at junit.framework.TestCase.runBare(TestCase.java:141)
	at junit.framework.TestResult$1.protect(TestResult.java:122)
	at junit.framework.TestResult.runProtected(TestResult.java:142)
	at junit.framework.TestResult.run(TestResult.java:125)
	at junit.framework.TestCase.run(TestCase.java:129)
	at junit.framework.TestSuite.runTest(TestSuite.java:252)
	at junit.framework.TestSuite.run(TestSuite.java:247)
	at org.junit.internal.runners.JUnit38ClassRunner.run(JUnit38ClassRunner.java:86)
	at org.junit.runners.Suite.runChild(Suite.java:128)
	at org.junit.runners.Suite.runChild(Suite.java:27)
	at org.junit.runners.ParentRunner$3.run(ParentRunner.java:290)
	at org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:71)
	at org.junit.runners.ParentRunner.runChildren(ParentRunner.java:288)
	at org.junit.runners.ParentRunner.access$000(ParentRunner.java:58)
	at org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:268)
	at org.junit.runners.ParentRunner.run(ParentRunner.java:363)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:137)
	at org.junit.runner.JUnitCore.run(JUnitCore.java:115)
	at org.junit.runner.JUnitCore.runMain(JUnitCore.java:77)
	at org.junit.runner.JUnitCore.main(JUnitCore.java:36)
```

## 1) Excessive garbage output
- See above

## 2) Modifies the expected output when printing to console:

```java
assertEquals("[10][50][70][120]", "*NULL*");
```

Becomes
```
expected:<[[10][50][70][120],]*NULL*> but was:<[]*NULL*>
```
