/*
 * (C) Copyright 2015-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Contributors:
 *   ohun@live.cn (夜色)
 */

package com.mpush.api.spi;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public final class SpiLoader {
    private static final Map<String, Object> CACHE = new ConcurrentHashMap<>();

    public static void clear() {
        CACHE.clear();
    }

    public static <T> T load(Class<T> clazz) {
        return load(clazz, null);
    }

    @SuppressWarnings("unchecked")
    public static <T> T load(Class<T> clazz, String name) {
        String key = clazz.getName();
        Object o = CACHE.get(key);
        if (o == null) {
            //未缓存
            T t = load0(clazz, name);
            if (t != null) {
                CACHE.put(key, t);
                return t;
            }
        } else if (clazz.isInstance(o)) {
//            Determines if the specified o is assignment-compatible
//                    with the object represented by this clazz
            return (T) o;
        }
        return load0(clazz, name);
    }

    public static <T> T load0(Class<T>  clazz, String name) {
        ServiceLoader<T> factories = ServiceLoader.load(clazz);
        T t = filterByName(factories, name);

        //??什么情况会出现class用不同的classload加载
        if (t == null) {
            factories = ServiceLoader.load(clazz, SpiLoader.class.getClassLoader());
            t = filterByName(factories, name);
        }

        if (t != null) {
            return t;
        } else {
            throw new IllegalStateException("Cannot find META-INF/services/" + clazz.getName() + " on classpath");
        }
    }

    /**
     * 获取指定名称的服务实现类，如果有多个实现类，使用Spi.order大的实现类
     * @param factories
     * @param name
     * @param <T>
     * @return
     */
    private static <T> T filterByName(ServiceLoader<T> factories, String name) {
        Iterator<T> it = factories.iterator();
        if (name == null) {
            List<T> list = new ArrayList<T>(2);
            while (it.hasNext()) {
                list.add(it.next());
            }
            if (list.size() > 1) {
                list.sort((o1, o2) -> {
                    Spi spi1 = o1.getClass().getAnnotation(Spi.class);
                    Spi spi2 = o2.getClass().getAnnotation(Spi.class);
                    int order1 = spi1 == null ? 0 : spi1.order();
                    int order2 = spi2 == null ? 0 : spi2.order();
                    return order1 - order2;
                });
            }
            if (list.size() > 0) return list.get(0);
        } else {
            while (it.hasNext()) {
                T t = it.next();
                if (name.equals(t.getClass().getName()) ||
                        name.equals(t.getClass().getSimpleName())) {
                    return t;
                }
            }
        }
        return null;
    }
}
