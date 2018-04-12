package com.psdkp.kkp.apipsdkp.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponMessage implements BaseUtil {
    
    Map m = new HashMap();

    @Override
    public Map SUCCESS_GET(Object data) {
        m.clear();
        m.put("code",AppConstants.C_SUCCESS_GET);
        m.put("message",AppConstants.SUCCESS_GET);
        m.put("data", data);
        return m;
    }


    @Override
    public Map BAD_REUQEST() {
        m.clear();
        m.put("code",AppConstants.C_BAD_REQUEST);
        m.put("message",AppConstants.BAD_REQUEST);
        m.put("data", null);
        return m;
    }

    @Override
    public Map DUPLICATE(String atribut) {
        m.clear();
        m.put("code",AppConstants.C_DUPLICATE);
        m.put("message",AppConstants.DUPLICATE + " \'"+atribut+"\'");
        m.put("data", null);
        return m;
    }

    @Override
    public Map SUCCESS_PROCESS_DATA() {
        m.clear();
        m.put("code",AppConstants.C_SUCCESS_PROCESS);
        m.put("message",AppConstants.SUCCESS_PROCESS);
        m.put("data", null);
        return m;
    }

    @Override
    public Map NOT_FOUND(String atribut) {
        m.clear();
        m.put("code",AppConstants.C_NOT_FOUND);
        m.put("message",AppConstants.NOT_FOUND + " \'"+atribut+"\'");
        m.put("data", null);
        return m;
    }
}
