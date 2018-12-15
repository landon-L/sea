package com.egova.viewport.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;

import java.util.*;

/**
 * @Author: ldy
 * @Desctrption:
 * @Date:Create at 2018/1/11 14:39
 * @Modified By:
 */
@Component
public class RedisClient implements InitializingBean, DisposableBean {
    private static Log log = LogFactory.getLog(RedisClient.class);
    @Value("${spring.redis.host}")
    private String redisIP;

    @Value("${spring.redis.port}")
    private int redisPort;


    private static JedisPool jedisPool = null;

//    //初始化redis连接池
//    static{
//        JedisPoolConfig config = new JedisPoolConfig();
//        //配置最大jedis实例数
//        config.setMaxTotal(1000);
//        //配置资源池最大闲置数
//        config.setMaxIdle(200);
//        //等待可用连接的最大时间
//        config.setMaxWaitMillis(10000);
//        //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的
//        config.setTestOnBorrow(true);
//        jedisPool = new JedisPool(redisIP, redisPort);
//    }


    @Override
    public void destroy() throws Exception {
        if (jedisPool != null) {
            jedisPool.destroy();
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        JedisPoolConfig config = new JedisPoolConfig();
        config.setTestOnBorrow(true);
        jedisPool = new JedisPool(redisIP, redisPort);
    }

    //获取Jedis实例
    public synchronized static Jedis getJedis(){
        if (jedisPool != null){
            Jedis resource = jedisPool.getResource();
            return resource;
        }else{
            return null;
        }
    }

    /**
     * 获取数据
     *
     * @param dbIdx 数据库索引
     * @param key 数据key
     * @return 数据的值
     */
    public String getValue(int dbIdx, String key) {
        String value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIdx);
            value = jedis.get(key);
        }
        catch (Exception e) {
            // 释放redis对象
            jedis.close();
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
        return value;
    }

    /**
     * 存储数据
     *
     * @param dbIdx 数据库索引
     * @param key 数据的键
     * @param value 数据的值
     */
    public void setKeyValue(int dbIdx, String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIdx);
            jedis.set(key, value);
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
    }

    /**
     * 存储数据，并设置过期天数
     *
     * @param dbIdx 数据库索引
     * @param key 数据的键
     * @param value 数据的值
     * @param day 有效天数
     */
    public void setKeyValue(int dbIdx, String key, String value, int day) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIdx);
            jedis.set(key, value);
            jedis.expire(key, getExpireTime(day));
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
    }

    /**
     * 根据key的样式字符串获取所有匹配的key的集合
     *
     * @param dbIndex 数据库db
     * @param keyPattern key的样式字符串，如：a_*_b
     * @return 所有匹配的key的集合
     * @author zj
     * @date 2017年1月4日 下午12:10:45
     */
    public Set<String> getKeySet(int dbIndex, String keyPattern) {
        Jedis jedis = null;
        Set<String> keys = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIndex);
            keys = jedis.keys(keyPattern);
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
        if (keys == null) {
            keys = new HashSet<String>();
        }
        return keys;
    }

    /**
     * 根据set集合的长度
     * @param dbIndex
     * @param
     * @return
     */
    public long getKeySetSize(int dbIndex, String key) {
        Jedis jedis = null;
        Long res = 0L;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIndex);
            res = jedis.scard(key);
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
        return res;
    }

    /**
     * 根据hash类型数据的长度
     * @param dbIndex
     * @param
     * @return
     */
    public long getHashMapSize(int dbIndex, String key) {
        Jedis jedis = null;
        Long res = 0L;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIndex);
            res = jedis.hlen(key);
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
        return res;
    }

    /**
     * 根据list集合的长度
     * @param dbIndex
     * @param
     * @return
     */
    public long getKeyListSize(int dbIndex, String key) {
        Jedis jedis = null;
        Long res = 0L;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIndex);
            res = jedis.llen(key);
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
        return res;
    }

    /**
     * 根据key的样式字符串获取所有匹配的key和value的键值对map
     *
     * @param dbIndex 数据库db
     * @param keyPattern key的样式字符串，如：a_*_b
     * @return 所有匹配的key和value的键值对map
     * @author zj
     * @date 2017年1月4日 下午12:10:45
     */
    public Map<String, String> getKeyValueMap(int dbIndex, String keyPattern) {
        Jedis jedis = null;
        Map<String, String> map = new HashMap<String, String>();
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIndex);
            Set<String> keys = jedis.keys(keyPattern);
            for (String key : keys) {
                map.put(key, jedis.get(key));
            }
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
        return map;
    }

    /**
     * 获取map结构的数据
     * @param dbIdx
     * @param key
     * @return
     */
    public Map<String, String> getMap(int dbIdx, String key) {
        Map<String, String> value = null;
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIdx);
            value = jedis.hgetAll(key);
        } catch (Exception e) {
            log.error("redis操作出现异常", e);
        } finally {
            // 返还到连接池
            jedis.close();
        }
        return value;
    }
    /**
     * 批量存储数据
     *
     * @param dbIdx 数据库索引
     * @param kv 键值对Map
     */
    public void setBatchKeyValues(int dbIdx, Map<String, String> kv) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIdx);
            Pipeline pipeline = jedis.pipelined();
            // pipeline.select(dbIdx);
            // pipeline.multi();
            for (Map.Entry<String, String> m : kv.entrySet()) {
                pipeline.set(m.getKey(), m.getValue());
            }
            // pipeline.exec();
            pipeline.syncAndReturnAll();

        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
    }

    /**
     * 删除数据
     *
     * @param dbIdx 数据库索引
     * @param key 数据的键
     */
    public void delValue(int dbIdx, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIdx);
            jedis.del(key);
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
    }

    /**
     * 设置数据有效时间
     *
     * @param dbIdx 数据库索引
     * @param key 数据的键
     * @param day 有效天数
     * @author zj
     * @date 2017年1月4日 下午12:00:11
     */
    public void setExpireTime(int dbIdx, String key, int day) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            jedis.select(dbIdx);
            jedis.expire(key, getExpireTime(day));
        }
        catch (Exception e) {
            log.error("redis操作出现异常", e);
        }
        finally {
            // 返还到连接池
            jedis.close();
        }
    }

    private int getExpireTime(int nDay) {
        int diff = 86400;
        if (nDay != 1) {
            return nDay * diff;
        }
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.set(Calendar.MILLISECOND, 999);
        diff = (int) (cal.getTimeInMillis() - System.currentTimeMillis()) / 1000;

        return diff;
    }
}
