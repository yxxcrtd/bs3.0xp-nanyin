package com.joyintech.service.common.config.service;

import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.common.config.dao.DynamicConfigMapper;
import io.netty.util.internal.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author 李东
 * @version 3.0 2021年6月23日
 * @see DynamicConfigService
 * @since 3.0
 */
@Service
public class DynamicConfigService {

    @Autowired
    private DynamicConfigMapper dynamicConfigMapper;

    /**
     * @param dynId
     * @return  示例： {"all":"secName,secType,issuer","checked":"secName"}
     */
    public String selectByDynId(String dynId) {
        List<Map> list = dynamicConfigMapper.selectByDynId(dynId);
        String jsonStr = "";
        if (list != null && list.size() == 1){
            jsonStr = list.get(0).get("dynInfo") + "";
        }
        return jsonStr;
    }

    /**
     * 
     * @param param {"dynId":"abc", "checked":"secName,secType,issuer"}
     * @return
     */
    public Integer updateByParam(Map<String, Object> param) {
        System.out.println("====abc123====" + param);
        if (param != null){
            String dynId = param.get("dynId") + "";
            if (StringUtil.isNotEmpty(dynId)){
                String jsonStr = selectByDynId(dynId);
                if (StringUtil.isNotEmpty(jsonStr)){
                    Map m = JsonUtils.json2Object(jsonStr, HashMap.class);
                    m.put("checked", param.get("checked"));
                    param.put("dynInfo", JsonUtils.object2Json(m));
                    return dynamicConfigMapper.updateByParam(param);
                }
            }
        }
        return 0;
    }

}
