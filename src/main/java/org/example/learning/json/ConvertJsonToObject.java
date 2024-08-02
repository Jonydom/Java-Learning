package org.example.learning.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import org.example.learning.json.dto.ExamAddUserMetaInfoQuery;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConvertJsonToObject {
    public static void main(String[] args) {
        Map<String, Object> map = createMap();
        ExamAddUserMetaInfoQuery object = convertMapToObject(map);
        System.out.println(object);
    }
    private static Map<String, Object> createMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("sexAndMarry", "101");
        map.put("age", "201");
        map.put("routine", new JSONArray(Lists.newArrayList(Arrays.asList("301", "302", "303"))));
        map.put("highOccurDisease", new JSONArray(Arrays.asList("401", "402")));
        map.put("instPrefer", "501");
        return map;
    }

    private static ExamAddUserMetaInfoQuery convertMapToObject(Map<String, Object> map) {
        String jsonString = JSON.toJSONString(map);
        return JSON.parseObject(jsonString, ExamAddUserMetaInfoQuery.class);
    }

}
