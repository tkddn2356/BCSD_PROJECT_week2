package com.sangwookim.service;

import java.util.List;
import java.util.Set;

public interface RedisService {
    //    String testString(String key, String value);
    public String setStr(String key, String value);

    public String getStr(String key);

    List<String> testList(String key, String value);

    String testHash(String key, String field, String value);

    Set<String> testSet(String key, String value);

    Set<String> testSortedSet(String key, String value, Double score);
}
