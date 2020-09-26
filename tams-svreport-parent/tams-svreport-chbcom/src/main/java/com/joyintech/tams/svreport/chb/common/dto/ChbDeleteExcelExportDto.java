package com.joyintech.tams.svreport.chb.common.dto;

import java.util.List;

import com.joyintech.framework.base.BaseEntity;

/**
 * 功能:描述:导出删除模板的实体类
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年5月31日
 * @修改记录：
 */
public class ChbDeleteExcelExportDto extends BaseEntity {
    
    /**
     * 发行机构代码
     */
    private String issuePartyId;
    
    /**
     * 删除类型：01 产品存续期信息  02 资产要素明细信息  03 交易信息 04 资产估值信息 05 底层资产信息
     */
    private String deletType;
    
    /**
     * 删除原因
     */
    private String deleteReason;
    
    /**
     * 要导出的详细信息
     */
    private List<ChbDeleteExcelExportDetailDto> detaiList;

    public String getIssuePartyId() {
        return issuePartyId;
    }

    public void setIssuePartyId(String issuePartyId) {
        this.issuePartyId = issuePartyId;
    }

    public String getDeletType() {
        return deletType;
    }

    public void setDeletType(String deletType) {
        this.deletType = deletType;
    }

    public List<ChbDeleteExcelExportDetailDto> getDetaiList() {
        return detaiList;
    }

    public void setDetaiList(List<ChbDeleteExcelExportDetailDto> detaiList) {
        this.detaiList = detaiList;
    }

    public String getDeleteReason() {
        return deleteReason;
    }

    public void setDeleteReason(String deleteReason) {
        this.deleteReason = deleteReason;
    }
    
    
}