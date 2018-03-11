package com.psdkp.kkp.apipsdkp.util;

import java.util.Map;

public interface BaseUtil {

    Map success();
    Map notFound();
    Map isExist();
    Map notSave();
    Map notEdit();
    Map notDel();
    Map badRequest();
    Map atributNull();
    Map notAuthorized();
    Map loginTrouble();
    Map isEmpty();
}
