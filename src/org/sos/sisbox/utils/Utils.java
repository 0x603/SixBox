package org.sos.sisbox.utils;

import java.sql.Timestamp;

/**
 * Created by Lodour on 2017/8/15 14:02.
 * 常用工具函数
 */
public class Utils {
    public static Timestamp getCurrentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
