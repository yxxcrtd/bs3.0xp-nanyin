package com.joyintech.tams.invest.entrust.home.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 
 */
@Service
public class HomeService extends BaseService<ITableRow> {

    public Page<SelectOptionDto> selectPage(Map<String, Object> param) {
        return restTemplate.postForPage(
                ServiceApplication.COMMON_SERVICE_NAME + "/dynamicConfig/selectPage", param,
                SelectOptionDto.class);
    }

    public String getCheckedConfigList(Map<String, Object> param) {
        return restTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/dynamicConfig/selectByDynId", param,
                String.class);
    }

    /**
     * 根据动态id获取动态配置信息
     * @param dynId
     * @return
     */
    public String getChecked(String dynId) {
        Map<String, Object> param = new HashedMap();
        param.put("dynId", dynId);
        String str = restTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/dynamicConfig/selectByDynId", param,
                String.class);
        Map m = JsonUtils.json2Object(str, Map.class);
        
        String result = m.get("checked") + "";
        return result;
    }
    
    public Integer save(Map<String, Object> param) {
        return restTemplate.postForObject(
                ServiceApplication.COMMON_SERVICE_NAME + "/dynamicConfig/updateByParam", param,
                Integer.class);
    }
}
