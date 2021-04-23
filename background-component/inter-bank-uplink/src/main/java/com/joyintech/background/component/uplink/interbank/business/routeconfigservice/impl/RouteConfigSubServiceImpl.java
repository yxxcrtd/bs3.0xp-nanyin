/*
 * 文件名：RouteConfigSubServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：caoguo
 * 修改时间：2020年5月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.background.component.uplink.interbank.business.routeconfigservice.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.background.component.common.pack.common.util.IDGenerator;
import com.joyintech.background.component.common.pack.common.util.StringUtil;
import com.joyintech.background.component.uplink.interbank.business.dto.RouteConfigSubDto;
import com.joyintech.background.component.uplink.interbank.business.routeconfigservice.RouteConfigSubService;
import com.joyintech.background.component.uplink.interbank.common.DictCodeList;
import com.joyintech.background.component.uplink.interbank.database.dao.IbuIntfQuoteSchemeMapper;
import com.joyintech.background.component.uplink.interbank.database.dao.IbuIntfSchemeMapper;
import com.joyintech.background.component.uplink.interbank.database.dao.IbuRouteConfigMapper;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfAttrConfig;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfQuoteScheme;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfQuoteSchemeKey;
import com.joyintech.background.component.uplink.interbank.database.entity.IbuIntfScheme;

/**
 * 路由配置子组件实现类 〈功能详细描述〉
 * 
 * @author caoguo
 * @version 1.0 2020年5月9日
 * @see RouteConfigSubServiceImpl
 * @since 1.0
 */
@Service
public class RouteConfigSubServiceImpl implements RouteConfigSubService {

    @Autowired
    private IbuRouteConfigMapper ibuRouteConfigMapper;

    @Autowired
    private IbuIntfSchemeMapper ibuIntfSchemeMapper;

    @Autowired
    private IbuIntfQuoteSchemeMapper ibuIntfQuoteSchemeMapper;

    /**
     * {@inheritDoc}
     */
    @Override
    public IbuIntfScheme getDefaultScheme() {
        return ibuRouteConfigMapper.getDefaultScheme(DictCodeList.是否_是.getValue());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public IbuIntfScheme getSchemeForAttr(Map<String, String> attrMap) {
        // 属性为空时，直接返回默认方案
        if (attrMap != null && !attrMap.isEmpty()) {
            // 查询所有的配置方案
            List<RouteConfigSubDto> routeConfigSubDtoList = ibuRouteConfigMapper.queryALLScheme();
            // 属性为空时，直接返回默认方案
            if (!StringUtil.isEmptyList(routeConfigSubDtoList)) {
                for (RouteConfigSubDto routeConfigSubDto : routeConfigSubDtoList) {
                    IbuIntfScheme result = toMatchAttr(attrMap, routeConfigSubDto);
                    if (result != null) {
                        return result;
                    }
                }
            }
        }
        return getDefaultScheme();
    }

    /**
     * 属性匹配逻辑 <功能详细描述>
     * 
     * @param attrMap
     * @param routeConfigSubDto
     * @see
     * @since 1.0
     */
    private IbuIntfScheme toMatchAttr(Map<String, String> attrMap,
                                      RouteConfigSubDto routeConfigSubDto) {
        List<IbuIntfAttrConfig> ibuIntfAttrConfigList = routeConfigSubDto.getAtrrList();
        // 已匹配数量
        int matchCount = 0;
        // ibuIntfAttrConfigList不为空
        if (!StringUtil.isEmptyList(ibuIntfAttrConfigList)) {
            for (IbuIntfAttrConfig ibuIntfAttrConfig : ibuIntfAttrConfigList) {
                String attrValue = attrMap.get(ibuIntfAttrConfig.getAttrId());
                if (StringUtil.isNotEmpty(attrValue)) {
                    if (!attrValue.equals(ibuIntfAttrConfig.getAttrValue())) {
                        break;
                    }
                    else {
                        matchCount++ ;
                    }
                }
                else {
                    break;
                }
            }
            if (matchCount == ibuIntfAttrConfigList.size()) {
                return routeConfigSubDto;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertScheme(IbuIntfScheme ibuIntfScheme) {
        ibuIntfScheme.setSchemeId(IDGenerator.generate());
        ibuIntfSchemeMapper.insert(ibuIntfScheme);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateScheme(String schemeId, IbuIntfScheme ibuIntfScheme) {
        ibuIntfScheme.setSchemeId(schemeId);
        ibuIntfSchemeMapper.updateByPrimaryKeySelective(ibuIntfScheme);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleleScheme(String schemeId) {
        ibuIntfSchemeMapper.deleteByPrimaryKey(schemeId);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setDefaultScheme(String schemeId) {
        Map<String, String> condition = new HashMap<String, String>();
        condition.put("yes", DictCodeList.是否_是.getValue());
        condition.put("no", DictCodeList.是否_否.getValue());
        ibuRouteConfigMapper.updateIsdefaultToNo(condition);
        IbuIntfScheme ibuIntfScheme = new IbuIntfScheme();
        ibuIntfScheme.setSchemeId(schemeId);
        ibuIntfScheme.setIsdefault(DictCodeList.是否_是.getValue());
        ibuIntfSchemeMapper.updateByPrimaryKeySelective(ibuIntfScheme);
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void insertQuoteScheme(String schemeId, Map<String, String> attrMap) {
        if (attrMap != null && !attrMap.isEmpty()) {
            String arrtGrpId = IDGenerator.generate();
            IbuIntfQuoteScheme ibuIntfQuoteScheme = new IbuIntfQuoteScheme();
            ibuIntfQuoteScheme.setSchemeId(schemeId);
            ibuIntfQuoteScheme.setArrtGrpId(arrtGrpId);
            List<IbuIntfAttrConfig> configList = new ArrayList<IbuIntfAttrConfig>();
            for (String key : attrMap.keySet()) {
                IbuIntfAttrConfig ibuIntfAttrConfig = new IbuIntfAttrConfig();
                ibuIntfAttrConfig.setArrtGrpId(arrtGrpId);
                ibuIntfAttrConfig.setAttrId(key);
                ibuIntfAttrConfig.setAttrValue(attrMap.get(key));
                configList.add(ibuIntfAttrConfig);
                // ibuIntfAttrConfig.setAttrType(DictCodeList.);
            }
            ibuIntfQuoteSchemeMapper.insert(ibuIntfQuoteScheme);
            ibuRouteConfigMapper.batchInsertAttrTable(configList);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void updateQuoteScheme(String oldSchemeId, String newSchemeId, String arrtGrpId,
                                  Map<String, String> attrMap) {
        deleteQuoteScheme(oldSchemeId,arrtGrpId);
        insertQuoteScheme(newSchemeId,attrMap);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteQuoteScheme(String schemeId, String arrtGrpId) {
        IbuIntfQuoteSchemeKey key = new IbuIntfQuoteSchemeKey();
        key.setArrtGrpId(arrtGrpId);
        key.setSchemeId(schemeId);
        ibuIntfQuoteSchemeMapper.deleteByPrimaryKey(key);

    }

}
