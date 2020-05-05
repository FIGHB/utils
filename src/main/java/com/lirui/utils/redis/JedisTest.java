package com.lirui.utils.redis;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * <dependency>
 * <groupId>redis.clients</groupId>
 * <artifactId>jedis</artifactId>
 * <version>2.9.0</version>
 * </dependency>
 *
 * @author lirui
 * @date 2020/5/5 15:41
 */
@Slf4j
public class JedisTest {

    static Jedis jedis;

    public static void main(String[] args) {
        // jedis连接创建
        log.info("create connection");
        jedis = new Jedis("127.0.0.1", 6379);
        // 测试连接
        log.info("ping jedis: " + jedis.ping());
        jedis.set("key1", "Hello World");//set
        String key1 = jedis.get("key1");//get
        log.info("test key :key1 = " + key1);

        log.info("测试set赋值");
        jedis.sadd("set", "赵成龙", "赵双龙", "赵艳会");// set赋值（无序唯一）
        // print set value
        for (String str : jedis.smembers("set")) {
            log.info(str);
        }
        // 删除指定的键
        jedis.del("set");
        // 判断指定的键是否存在
        log.info(String.valueOf(jedis.exists("set")));
        // 以字符串形式返回存储在指定键的值的数据类型
        log.info(jedis.type("key"));
        // 返回set中元素的数量
        log.info(String.valueOf(jedis.scard("set")));
        // sort set
        log.info("测试有序集合");
        jedis.zadd("math", 99, "Long");
        jedis.zadd("math", 89, "hui");
        jedis.zadd("math", 88, "xuan");
        Set<String> set1 = jedis.zrange("math", 0, 3);
        for (String s : set1) {
            log.info(s);
        }

        System.out.println("-----------------------------------------------------------------------------------------");
        log.info("给hash指定的field设置指定的值");
        jedis.hset("has", "name", "赵成龙");
        jedis.hset("has", "sex", "男");
        jedis.hset("has", "born", "1995-10-16");
        jedis.hset("has", "signature", "得不到的永远在骚动，被偏爱的都有恃无恐");
        jedis.hdel("has", "born");
        jedis.hget("has", "signature");
        Map<String, String> map = jedis.hgetAll("has");
        Set<String> set = map.keySet();
        for (String s : set) {
            log.info(s + " = " + map.get(s));
        }
    }
}
