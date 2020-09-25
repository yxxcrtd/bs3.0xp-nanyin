package com.joyintech.tams.invest.risk.namelistmanage.assetnamelist.dto;

import com.joyintech.framework.util.DateUtil;

/**
 * 名单明细DTO
 * @author 张年
 * @version 1.0 2019年07月11日
 * @see RskAccessListDetailDto
 * @since 1.0
 */

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.RskAccessListDetail;
import com.joyintech.tams.constant.DictCode;

public class RskAccessListDetailDto extends RskAccessListDetail {

    /**
     * 名单主体名称(旧)
     */
    private String oldlistDetailName;

    private String operatePs;

    /**
     * 审批人
     */
    private String spr;
     
    /**
     * 名单类型，白名单、黑名单
     */
    private String listType;

    /**
     * 名单级别，一级库、二级库
     */
    private String listLevel;

    public String getOldlistDetailName() {
        return oldlistDetailName;
    }

    public void setOldlistDetailName(String oldlistDetailName) {
        this.oldlistDetailName = oldlistDetailName;
    }

    public String getOperatePs() {
        return operatePs;
    }

    public void setOperatePs(String operatePs) {
        this.operatePs = operatePs;
    }

    public String getSpr() {
        return spr;
    }

    public void setSpr(String spr) {
        this.spr = spr;
    }
    /**
     * 名单操作方式String
     * @return
     */
    public String getOperateTypeStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.名单操作方式, this.getOperateType());
    }
    
    /**
     * <一句话功能简述> <功能详细描述>
     * 
     * @return
     * @see
     * @since 1.0
     */
    public String getListDateStr() {
        return DateUtil.format(super.getListDate());
    }

    public String getListType() {
        return listType;
    }

    public void setListType(String listType) {
        this.listType = listType;
    }

    public String getListLevel() {
        return listLevel;
    }

    public void setListLevel(String listLevel) {
        this.listLevel = listLevel;
    }
}
