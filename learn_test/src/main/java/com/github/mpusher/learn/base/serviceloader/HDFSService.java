package com.github.mpusher.learn.base.serviceloader;

/**
 * Created by Sean.liu on 2017/6/29.
 */
public class HDFSService implements IService {
    @Override
    public String sayHello() {
        return "Hello HDFSService";
    }
    @Override
    public String getScheme() {
        return "hdfs";
    }
}
