package org.example.learning.date;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class testSimpleDateFormat {
    public static void main(String[] args) {
        // 获取当前日期
        Date date = new Date();
        // 定义日期格式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 格式化日期
        String formattedDate = dateFormat.format(date);
        System.out.println(formattedDate);
        Long aLong = Long.valueOf("0");
        System.out.println("aLong = " + aLong);
        ArrayList<String> objects = Lists.newArrayList();
        System.out.println("objects = " + CollectionUtils.isEmpty(objects));
        System.out.println("objects = " + objects.size());
    }
}
