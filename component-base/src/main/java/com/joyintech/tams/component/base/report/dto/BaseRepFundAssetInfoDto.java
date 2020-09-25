/*
 * 文件名：BaseRepFundAssetInfoDto.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：qiangchengxi
 * 修改时间：2018年9月4日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.component.base.report.dto;

import java.util.List;

import com.joyintech.tams.common.base.entity.RepFund;

/**
 * 基金资产报送DTO
 * 〈功能详细描述〉
 * @author wangzehui
 * @version 1.0 2018年9月4日
 * @see BaseRepFundAssetInfoDto
 * @since 1.0
 */
public class BaseRepFundAssetInfoDto {
    /**
     * 金融产品代码list
     */
    private List<String> repFinprodIdList;
    /**
     * 基金报送信息List
     */
    private List<RepFund> repFundList;
    
    public List<String> getRepFinprodIdList() {
        return repFinprodIdList;
    }
    
    public void setRepFinprodIdList(List<String> repFinprodIdList) {
        this.repFinprodIdList = repFinprodIdList;
    }

    
    public List<RepFund> getRepFundList() {
        return repFundList;
    }

    
    public void setRepFundList(List<RepFund> repFundList) {
        this.repFundList = repFundList;
    }
    
    
}
