package com.db117.adminstaging;

import org.junit.Test;

/**
 * @author 大兵
 * @date 2018-04-17 10:19
 **/
public class Test1 {
    @Test
    public void testTry() {
        long start = System.currentTimeMillis();

            for (int i = 0; i < 10000; i++) {
                try {
                System.out.println(i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        System.out.println(System.currentTimeMillis() - start);
    }
}
