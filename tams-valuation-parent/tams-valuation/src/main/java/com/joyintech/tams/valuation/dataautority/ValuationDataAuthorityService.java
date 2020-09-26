/*
 * 文件名：ValuationDataAuthorityService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：wangzhiang
 * 修改时间：2018年9月10日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */
 
package com.joyintech.tams.valuation.dataautority;

import java.util.ArrayList;
import java.util.List;

import com.joyintech.framework.security.impl.DataAuthorityServiceImpl;



/**
 * @author wangzhiang
 * @version 1.0 2018年9月10日
 * @see ValuationDataAuthorityService
 * @since 1.0
 */
//@Component
//@Primary	
public class ValuationDataAuthorityService extends DataAuthorityServiceImpl {

    /**
     * 指定不需要数据权限控制的package
     */
    @Override
    public List<String> ignoreMapper() {
        List<String> mapper = new ArrayList<>();
        mapper.add("com.joyintech.**");
        
        return mapper;
    }

}
