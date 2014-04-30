package com.abs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class SystemProperties {
    private static final SystemProperties instance = new SystemProperties();

    private SystemProperties() {
    }

    Properties props = new Properties();
    {
        InputStream in = null;
        try {
            in = SystemProperties.class.getClassLoader().getResourceAsStream("system.properties");
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException ex) {
                }
            }
        }
    }

    public static SystemProperties getInstance() {
        return instance;
    }

    /**
     * 得到某一属性的值.
     * 
     * @param key
     *            取得其值的键
     * @return key的值
     */
    public String getValue(String key) {
        if (props.containsKey(key)) {
            // 得到某一属性的值
            String value = props.getProperty(key);
            return value;
        } else
            return "";
    }
}
