package com.sangwookim.service;

import com.sangwookim.util.RedisConfig;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public class RedisServiceImpl implements RedisService {

//    ConfigurableApplicationContext ctx = new AnnotationConfigApplicationContext(RedisConfig.class);
//    RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>)ctx.getBean("redisTemplate");
//    ValueOperations<String, Object> valueOps = redisTemplate.opsForValue();

    @Resource(name = "redisTemplate")
    private ValueOperations<String, String> valueOps;
    @Resource(name = "redisTemplate")
    private ListOperations<String, String> listOps;
    @Resource(name = "redisTemplate")
    private HashOperations<String, String, String> hashOps;
    @Resource(name = "redisTemplate")
    private SetOperations<String, String> setOps;
    @Resource(name = "redisTemplate")
    private ZSetOperations<String, String> zSetOps;

    public String setStr(String key, String value) {
        valueOps.set(key, value);
        return valueOps.get(key);
    }

    public String getStr(String key) {
        String result = valueOps.get(key);
        return result;
    }

    public List<String> testList(String key, String value) {
        listOps.rightPush(key, value);
        return listOps.range(key, 0, -1);
    }

    public String testHash(String key, String field, String value) {
        hashOps.put(key, field, value);
        return hashOps.get(key, field);
    }

    public Set<String> testSet(String key, String value) {
        setOps.add(key, value);
        return setOps.members(key);
    }

    public Set<String> testSortedSet(String key, String value, Double score) {
        zSetOps.add(key, value, score);
        return zSetOps.range(key, 0, -1);
    }
}
