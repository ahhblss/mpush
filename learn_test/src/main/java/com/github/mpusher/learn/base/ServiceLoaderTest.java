package com.github.mpusher.learn.base;

import com.github.mpusher.learn.base.serviceloader.IService;

import java.util.ServiceLoader;

/**
 * Created by Sean.liu on 2017/6/29.
 */
public class ServiceLoaderTest {

    public static void main(String[] args) {
        ServiceLoader<IService> serviceLoader  = ServiceLoader.load(IService.class);
        for (IService service : serviceLoader) {
            System.out.println(service.getScheme()+"="+service.sayHello());
        }
    }
}
