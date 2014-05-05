package com.abs.service.base;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Service;

@Service
public class BaseService {

    public String getJSONResult(String key, String value) {
        JSONObject result = new JSONObject();
        result.put(key, value);
        return result.toString();
    }

}
