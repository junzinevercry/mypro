package com.abs.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

 
public  class UploadFileProperties {
    private static final UploadFileProperties instance = new UploadFileProperties();
    private UploadFileProperties(){}
    Properties props = new Properties();
    {
        InputStream in = null;
        try {
            in = UploadFileProperties.class.getClassLoader().getResourceAsStream("filePath.properties");
            props.load(in);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e.getCause());
        } finally {
            if (in != null) {
                try{ 
                    in.close(); 
                }catch(IOException ex){}
            }
        }
    }
    public static UploadFileProperties getInstance(){
            return instance;
    }
    public String getValue(String key) {
        return props.getProperty(key);
    }
}
