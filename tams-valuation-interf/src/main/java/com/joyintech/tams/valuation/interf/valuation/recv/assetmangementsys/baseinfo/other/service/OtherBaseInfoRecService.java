/*
 * 文件名：OtherBaseInfoRecService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：估值核算系统V1.0
 * 修改人：王波
 * 修改时间：2018年6月26日
 * 修改内容：新建
 * 系统名称：估值核算系统V1.0
 */

package com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.other.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.valuation.common.interf.base.constant.DictCodeInterfList;
import com.joyintech.tams.valuation.common.interf.base.constant.TempConstant;
import com.joyintech.tams.valuation.common.interf.base.dto.OtherBaseInfoCommonResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateOrderInfoReaderResult;
import com.joyintech.tams.valuation.common.interf.base.dto.TemplateSourceFlgDTO;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other.ValAccountInfoInterfService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other.ValCustomerInfoInterfService;
import com.joyintech.tams.valuation.common.interf.base.service.baseinfo.other.ValHolidayInfoInterfService;
import com.joyintech.tams.valuation.interf.valuation.recv.assetmangementsys.baseinfo.other.entity.OtherBaseInfoForAssetProcessorResult;

@Service
public class OtherBaseInfoRecService {
    
    /**
     * 机构信息  模板处理Service
     */
    @Autowired
    private ValCustomerInfoInterfService valCustomerInfoInterfService;
    
    /**
     * 账户信息  模板处理Service
     */
    @Autowired
    private ValAccountInfoInterfService valAccountInfoInterfService;
    
    /**
     * 节假日信息  模板处理Service
     */
    @Autowired
    private ValHolidayInfoInterfService valHolidayInfoInterfService;
    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public OtherBaseInfoForAssetProcessorResult execute(TemplateOrderInfoReaderResult acctEntity) {

        OtherBaseInfoCommonResult result = new OtherBaseInfoCommonResult();
        // 设置启动源属性
        TemplateSourceFlgDTO templateSourceFlgDTO = new TemplateSourceFlgDTO();
        templateSourceFlgDTO.setCallSourceType(DictCodeInterfList.文件解析功能启动源_TASK.getValue());

        // 其他基础数据接口-市场机构信息
        if (TempConstant.机构信息.equals(acctEntity.getTmpEName())) {
            /**modify by dongjun on 20181012 start 返回结果集修改**/
            result = valCustomerInfoInterfService.execute(
                templateSourceFlgDTO, acctEntity);
            /**modify by dongjun on 20181012 end 返回结果集修改**/
        }
        //其他基础数据接口-账户信息
        else if(TempConstant.账户信息.equals(acctEntity.getTmpEName())){
        	result = valAccountInfoInterfService.execute(templateSourceFlgDTO, acctEntity);
        }
        else if(TempConstant.节假日信息表.equals(acctEntity.getTmpEName())){
            result = valHolidayInfoInterfService.execute(templateSourceFlgDTO, acctEntity);
        }
        return new OtherBaseInfoForAssetProcessorResult(result);
    }
}
