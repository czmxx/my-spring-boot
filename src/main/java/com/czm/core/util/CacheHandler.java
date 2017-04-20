package com.czm.core.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by CHENZHANMEI on 2017/3/11.
 * 缓存类
 */
public class CacheHandler {

    private static final long SECOND_TIME = 1000;
    private static final ConcurrentHashMap<String, CacheEntity> map;
    private static final List<CacheEntity> tempList;

    static {
        tempList = new ArrayList<>();
        map = new ConcurrentHashMap<>(new HashMap<String, CacheEntity>(1 << 18));
        new Thread(new TimeoutTimerThread()).start();
    }

    /**
     * 增加缓存对象
     *
     * @param key
     * @param ce
     */
    public static void addCache(String key, CacheEntity ce) {
        addCache(key, ce, ce.getOutTime());
    }

    /**
     * 增加缓存对象
     *
     * @param key
     * @param ce
     * @param validityTime 有效时间
     */
    public static synchronized void addCache(String key, CacheEntity ce, long validityTime) {
        ce.setOutTime(System.currentTimeMillis() + validityTime * SECOND_TIME);
        map.put(key, ce);
        //添加到过期处理队列
        tempList.add(ce);
    }

    /**
     * 获取缓存对象
     *
     * @param key
     * @return
     */
    public static synchronized CacheEntity getCache(String key) {
        return map.get(key);
    }

    /**
     * 检查是否含有制定key的缓冲
     *
     * @param key
     * @return
     */
    public static synchronized boolean isConcurrent(String key) {
        return map.containsKey(key);
    }

    /**
     * 删除缓存
     *
     * @param key
     */
    public static synchronized void removeCache(String key) {
        map.remove(key);
    }

    /**
     * 获取缓存大小
     */
    public static int getCacheSize() {
        return map.size();
    }

    /**
     * 清除全部缓存
     */
    public static synchronized void clearCache() {
        tempList.clear();
        map.clear();
    }

    static class TimeoutTimerThread implements Runnable {
        public void run() {
            while (true) {
                try {
                    checkTime();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * 过期缓存的具体处理方法
         *
         * @throws Exception
         */
        private void checkTime() throws Exception {
            //"开始处理过期 ";
            CacheEntity tce = null;
            long timeout = 1000L;

            //" 过期队列大小 : "+tempList.size());
            if (1 > tempList.size()) {
                Thread.sleep(timeout);
                return;
            }
            tce = tempList.get(0);
            timeout = tce.getOutTime() - System.currentTimeMillis();
            //" 过期时间 : "+timoutTime);
            if (0 < timeout) {
                //设定过期时间
                Thread.sleep(timeout);
                return;
            }
            // System.out.print(" 清除过期缓存 ： "+tce.getCacheKey());
            //清除过期缓存和删除对应的缓存队列
            tempList.remove(tce);
            removeCache(tce.getKey());
        }
    }
}
