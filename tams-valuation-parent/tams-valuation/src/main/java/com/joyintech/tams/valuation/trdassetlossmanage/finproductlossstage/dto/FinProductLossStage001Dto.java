package com.joyintech.tams.valuation.trdassetlossmanage.finproductlossstage.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;

import java.util.Date;
import java.util.List;

public class FinProductLossStage001Dto extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 资产id
     */
    private String finprodId;

    /**
     * 资产名称
     */
    private String finprodValue;

    public String getFinprodValue() {
        return finprodValue;
    }

    public void setFinprodValue(String finprodValue) {
        this.finprodValue = finprodValue;
    }

    /**
     * 生成日期
     */
    private Date effDate;
    private String effDateStr;

    /**
     *  失效日期
     */
    private Date invalidDate;

    /**
     * 阶段
     */
    private String stage;

    /**
     * 流水号
     */

    /**
     * 选中的数据
     * @return
     */
    private List<FinProductLossStage001Dto> selectedItem;

    /**
     * 操作类型
     * @return
     */
    private String operateType;

    public List<FinProductLossStage001Dto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<FinProductLossStage001Dto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    private String seqNo;
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    public Date getEffDate() {
        return effDate;
    }

    public void setEffDate(Date effDate) {
        this.effDate = effDate;
    }

    public Date getInvalidDate() {
        return invalidDate;
    }

    public void setInvalidDate(Date invalidDate) {
        this.invalidDate = invalidDate;
    }

    public String getStage() {
        return stage;
//        return SysDataUtil.getNameFromDataDict(DictCode.减值阶段,this.stage);
    }

    private String stageStr;
    public String getStageStr(){
        return SysDataUtil.getNameFromDataDict(DictCode.减值阶段,this.stage);
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getEffDateStr() {
        return effDateStr;
    }

    public void setEffDateStr(String effDateStr) {
        this.effDateStr = effDateStr;
    }

    public void setStageStr(String stageStr) {
        this.stageStr = stageStr;
    }
}
