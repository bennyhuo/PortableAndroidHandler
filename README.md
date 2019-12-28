# PortableAndroidHandler
Pure Java implementation of Android Handler.  This is helpful to test Android Handler based logic in a pure Java project.

Use `DelayQueue`  internal to implement the `MessageQuue`. 

The project is still working, and most of the Android Handler related apis will be completed later on.

## Usage

It has been deployed to jCenter.

```
compile 'com.bennyhuo.java:portable-android-handler:0.2'
```

First you should setup your own looper thread like what Android does in `ActivityThread`:

``` java
Looper.prepare();
// Create a new Thread to use your handler here.
Looper.loop();
//unreachable unless you quit the looper.
```

## Reference

This library is mentioned in my Android Interview Tutorials: [破解Android高级面试](https://s.imooc.com/SBS30PR)
