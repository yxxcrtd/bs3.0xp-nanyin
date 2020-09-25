/*
 * 文件名：BussRepFundInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月18日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.fund;


import com.joyintech.tams.component.business.report.asset.fund.dto.BussRepFundInfoDto;

/**
 * 基金资产中债报送信息组件service
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月18日
 * @see BussRepFundInfoService
 * @since 1.0
 */
public interface BussRepFundInfoService {
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussRepFundInfo";
    
    /**
     * 基金中债报送信息参数获取
     * @param prudoctId 金融产品代码
     * @return PrdValParam
     */
    BussRepFundInfoDto getByFinprodId(String finprodId);

    
    
}
