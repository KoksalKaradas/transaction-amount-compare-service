package com.assignment.demo.integration.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisQuery {

  private static final Logger logger = LoggerFactory.getLogger(RedisQuery.class);

  @Autowired
  private RedisTemplate<String, Object> redisTemplate;

  public Object getKeyValue(String key) {
    try {
      return redisTemplate.opsForValue().get(key);
    } catch (Exception e) {
      logger.error(String.format("RedisQuery getKeyValue error for key : %s", key), e);
      throw e;
    }
  }

}