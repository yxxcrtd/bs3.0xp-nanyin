/*
 * 文件名：BussRepDlRepoInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.depo;

import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDlRepoInfoDto;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月5日
 * @see BussRepDlRepoInfoService
 * @since 1.0
 */
public interface BussRepDlRepoInfoService {

    /**
     * 默认entity名称
     */
    String DEFAULT_ENTITY_NAME = "bussRepDlRepoInfo";
    
    /**
     * 获取画面初始化需要的数据字典
     */
    IDictCode[] getInitDictCode();
    
    /**
     * 初始化中债报送
     * <功能详细描述>
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    BussRepDlRepoInfoDto getBussRepDlRepoInfoByProductId(String finprodId);
    
}
