/*
 * 文件名：BussRepDlRepoInfoImplService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年11月5日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.business.report.asset.depo.impl;

import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.base.intf.IDictCode;
import com.joyintech.tams.component.business.report.asset.depo.BussRepDlRepoInfoService;
import com.joyintech.tams.component.business.report.asset.depo.dto.BussRepDlRepoInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.invest.common.constant.ServiceApplication;

/**
 * 拆放同业及买入返售报送信息
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年11月5日
 * @see BussRepDlRepoInfoImplService
 * @since 1.0
 */
@Service
public class BussRepDlRepoInfoImplService extends BaseService<BussRepDlRepoInfoDto> implements BussRepDlRepoInfoService{

    @Override
    public IDictCode[] getInitDictCode() {
        return new DictCode[] {DictCode.资产_负债二级类别,DictCode.交易流通场所};
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BussRepDlRepoInfoDto getBussRepDlRepoInfoByProductId(String finprodId) {
        return restTemplate.getForObject(ServiceApplication.ASSET_SERVICE_NAME
            + "/BussRepDlRepoInfo/getBussRepDlRepoInfoByProductId?finprodId="
            + finprodId,BussRepDlRepoInfoDto.class);
    }
}
