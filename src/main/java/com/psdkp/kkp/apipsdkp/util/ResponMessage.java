package com.psdkp.kkp.apipsdkp.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ResponMessage implements BaseUtil {

    /*
    * Responde
    * */

    private String rSuccess="Operation success";
    private String rNotFound="Not found data";
    private String rIsExist="Data is exits";
    private String rNotSave="Data not save";
    private String rNotEdit="Data Not Edit";
    private String rNotDel="Data Not Delete";
    private String rBadRequest="Bad request";
    private String rAttributNull="Atribut Null";
    private String rNotAuthorized="Not Authorzed";
    private String rLoginTroble="Login is trouble";
    private String rIsEmpty="Data is empty";

    Map m = new HashMap();

    @Override
    public Map success() {
        m.clear();
        m.put("message", rSuccess);
        return m;
    }

    @Override
    public Map notFound() {
        m.clear();
        m.put("message", rNotFound);
        return m;
    }

    @Override
    public Map isExist() {
        m.clear();
        m.put("message", rIsExist);
        return m;
    }

    @Override
    public Map notSave() {
        m.clear();
        m.put("message", rNotSave);
        return m;
    }

    @Override
    public Map notEdit() {
        m.clear();
        m.put("message", rNotEdit);
        return m;
    }

    @Override
    public Map notDel() {
        m.clear();
        m.put("message", rNotEdit);
        return m;
    }

    @Override
    public Map badRequest() {
        m.clear();
        m.put("message", rBadRequest);
        return m;
    }

    @Override
    public Map atributNull() {
        m.clear();
        m.put("message", rAttributNull);
        return m;
    }

    @Override
    public Map notAuthorized() {
        m.clear();
        m.put("message", rNotAuthorized);
        return m;
    }

    @Override
    public Map loginTrouble() {
        m.clear();
        m.put("message", rLoginTroble);
        return m;
    }

    @Override
    public Map isEmpty() {
        m.clear();
        m.put("message", rIsEmpty);
        return m;
    }
}
