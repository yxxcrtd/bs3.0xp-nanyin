/*
 * 文件名：BussRepOtherInvestTargetInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月2日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.project.impl;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.project.BussRepOtherInvestTargetInfoService;
import com.joyintech.tams.component.business.report.asset.project.dto.BussRepOtherInvestTargetInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 其他资产报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月2日
 * @see BussRepOtherInvestTargetInfoImplService
 * @since 1.0
 */
@Service
public class BussRepOtherInvestTargetInfoImplService extends BaseService<BussRepOtherInvestTargetInfoDto> implements BussRepOtherInvestTargetInfoService{

    /**
     * {@inheritDoc}
     */
    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.资产_负债二级类别,DictCode.交易流通场所,DictCode.投资阶段,DictCode.机构所属行业一级分类,DictCode.机构所属行业二级分类
            ,DictCode.发行机构类型_按技术领域划分,DictCode.发行机构类型_按规模划分,DictCode.发行机构类型_按经济类型划分,DictCode.行内资产类别
            ,DictCode.股票类型,DictCode.行政区划_市,DictCode.重点监控行业和领域类别,DictCode.主体评级_中债,DictCode.资产评级, DictCode.所属国家或地区,DictCode.人行机构规模,DictCode.受让形式};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussRepOtherInvestTargetInfoDto getBussRepOtherInvestTargetInfoByProductId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussRepOtherInvestTargetInfo/getBussRepOtherInvestTargetInfoByProductId?finprodId="
            + finprodId,BussRepOtherInvestTargetInfoDto.class);
    }

}
