package com.db117.adminstaging;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

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

    @Test
    public void encode() {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密"0"
        String encode = bCryptPasswordEncoder.encode("admin");
        System.out.println(encode);
        //结果：$2a$10$/eEV4X7hXPzYGzOLXfCizu6h7iRisp7I116wPA3P9uRcHAKJyY4TK
    }
}
