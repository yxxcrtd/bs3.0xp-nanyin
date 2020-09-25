/*
 * 文件名：BussRepFundInfoServiceImpl.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.fund.impl;


import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.component.business.report.asset.fund.BussRepFundInfoService;
import com.joyintech.tams.component.business.report.asset.fund.dto.BussRepFundInfoDto;
import com.joyintech.tams.invest.common.constant.ServiceApplication;


/**
 * 基金资产中债报送信息组件实现类
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月18日
 * @see BussRepFundInfoServiceImpl
 * @since 1.0
 */
@Service
public class BussRepFundInfoServiceImpl extends BaseService<BussRepFundInfoDto> implements BussRepFundInfoService{

    /**
     * {@inheritDoc}
     */
    @Override
    public BussRepFundInfoDto getByFinprodId(String finprodId) {
        return restTemplate.getForObject(
            ServiceApplication.ASSET_SERVICE_NAME + "/FinFundTarget/getFundInfoByFinprodId?finprodId="
                +finprodId,BussRepFundInfoDto.class);
    }


    

   

}
