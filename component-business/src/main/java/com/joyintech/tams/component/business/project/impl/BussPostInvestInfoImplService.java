/*
 * 文件名：BussPostInvestInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.project.impl;

import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.FinPostInvestInfo;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.project.BussPostInvestInfoService;
import com.joyintech.tams.component.business.project.dto.BussPostInvestInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

import java.util.Map;

/**
 *  投后信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月29日
 * @see BussPostInvestInfoImplService
 * @since 1.0
 */
@Service
public class BussPostInvestInfoImplService extends BaseService<BussPostInvestInfoDto> implements BussPostInvestInfoService{

    /**
     * {@inheritDoc}
     */
    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.我行对应层级,DictCode.交易场所,DictCode.股票质押,
            DictCode.受让资产性质,DictCode.业务品种,DictCode.批复方,DictCode.部门内部资产分类,DictCode.底层资产类型};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public FinPostInvestInfo getBussPostInvestInfoByProductId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussPostInvestInfo/getBussPostInvestInfoByProductId?finprodId="
            + finprodId,
            FinPostInvestInfo.class);
    }

    @Override
    public Page<SelectOptionDto> selectCounterId(Map<String, Object> param) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/selectCounterId", param, SelectOptionDto.class);
    }
}
