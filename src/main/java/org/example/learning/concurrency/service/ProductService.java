package org.example.learning.concurrency.service;

import java.util.HashMap;
import java.util.Map;

/**
 * 商品名称
 */
public class ProductService {

    private static final Map<Integer, String> map = new HashMap<>();
    static {
        map.put(1,"iphone14");
        map.put(2,"iphone 蓝牙耳机");
        map.put(3,"Mac Book Pro-详情图内容");
        map.put(4,"小香风深蓝色大衣");
        map.put(5,"清热解火菊花茶");
        map.put(6,"补肝养肾枸杞大枣茶");
        map.put(7,"颈椎病康复指南");
    }
    public String getById(int id) {
        try {
            Thread.sleep(1000);
            System.out.println("ProductService # getById方法运行线程：" + Thread.currentThread().getName());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return map.get(id);
    }

}
