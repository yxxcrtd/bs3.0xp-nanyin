/*
 * 文件名：BussProdImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年10月11日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.prodinfo.impl;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.PtlPortfolio;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.prodinfo.BussProdService;
import com.joyintech.tams.invest.common.prodinfo.dto.IPtlProd;
import com.joyintech.workflow.engine.constant.OperationType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

/**
 * 组合创建类
 * 〈功能详细描述〉
 * @author 裴家俊
 * @version 1.0 2019年10月11日
 * @see BussProdImplService
 * @since 1.0
 */
@Service
public class BussProdImplService extends BaseService implements BussProdService<IPtlProd> {

    /**
     * 插入操作
     * @param prodInfoList
     * @return
     */
    @Override
    public int insertBussProd(IPtlProd... prodInfoList) {
        int retCount=0;
        for(IPtlProd dto:prodInfoList){
            PtlPortfolio entity=new PtlPortfolio();
            BeanUtil.copyPropertiesIgnoreNull(entity, dto);
            if(StringUtil.isEmpty(entity.getRiskEnabled())){
            	entity.setRiskEnabled(BussinessDictCodeListRds.是否_否.getValue());
            }
            retCount+=dealProdAction(entity,OperationType.NEW);
        }
        return retCount;
    }

    /**
     * 更新操作
     * @param prodInfoList
     * @return
     */
    @Override
    public int updateBussProd(IPtlProd... prodInfoList) {
        int retCount=0;
        for(IPtlProd dto:prodInfoList){
            PtlPortfolio entity=new PtlPortfolio();
            BeanUtil.copyPropertiesIgnoreNull(entity, dto);
            retCount+=dealProdAction(entity,OperationType.MODIFY);
        }
        return retCount;
    }

    /**
     * 删除操作
     * @param prodInfoList
     * @return
     */
    @Override
    public int deleteBussProd(IPtlProd... prodInfoList) {
        int retCount=0;
        for(IPtlProd dto:prodInfoList){
            PtlPortfolio entity=new PtlPortfolio();
            BeanUtil.copyPropertiesIgnoreNull(entity, dto);
            retCount+=dealProdAction(entity,OperationType.DELETE);
        }
        return retCount;
    }
    /**
     * 查询需要修改的组合的风险启动状态
     */
    @Override
	public List<PtlPortfolio> selectAllEnaled(IPtlProd... prodInfoList){
    	String[] str = new String[prodInfoList.length];
    	for(int i=0;i<prodInfoList.length;i++){
            str[i] = prodInfoList[i].getPortfolioId();
        }
    	List<String> list = Arrays.asList(str);
    	return simpleRestTemplate.postForList(
                ServiceApplication.RISK_SERVICE_NAME + "/ptlmanagementinfo/selectAllEnaled", list,
                PtlPortfolio.class);
    }
    /**
     * 对应各操作类型
     * @param entity
     * @param type
     * @return
     */
    private int dealProdAction(PtlPortfolio entity, OperationType type) {
        String action = StringUtil.EMPTY;
        if (OperationType.DELETE.equals(type)) {
            action = "delete";
        }
        else if (OperationType.NEW.equals(type)) {
            action = "insert";
        }
        else {
            action = "update";
        }
        return simpleRestTemplate.postForObject(
                ServiceApplication.RISK_SERVICE_NAME + "/ptlmanagementinfo/" + action, entity,
                Integer.class);
    }
}
