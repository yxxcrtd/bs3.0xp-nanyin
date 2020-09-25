/*
 * 文件名：BussRepDepositInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.depo;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDepositInfoDto;

/**
 * 存款类资产中债报送信息
 * @author zhangnian
 * @version 1.0 2018年9月17日
 * @see BussRepDepositInfoService
 * @since 1.0
 */
public interface BussRepDepositInfoService {
    
    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussRepDepositInfo";
    
    /**
     * 获取画面初始化需要的数据字典
     */
    IDictCode[] getInitDictCode();
    
    BussRepDepositInfoDto getBussRepDepositInfoDto(BussRepDepositInfoDto dto);
    
    /**
     * 初始化中债报送
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    BussRepDepositInfoDto getBussRepDepositInfoByProductId(String finprodId);
}
