package com.haike.web.util;

import java.util.UUID;

public class Guid {

    /**
     * 获得一个去掉"-"符号的UUID
     * 
     * @param args
     */
    public static String getUuid() {
        return UUID.randomUUID().toString().trim().replaceAll("-", "");
    }
}
