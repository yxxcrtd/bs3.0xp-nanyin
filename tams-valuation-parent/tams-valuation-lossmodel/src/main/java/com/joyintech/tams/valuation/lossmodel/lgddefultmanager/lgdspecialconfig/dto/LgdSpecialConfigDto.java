/*
 * 文件名：LgdSpecialConfigDto.java
 * 版权：Copyright 2021-2022 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：中邮理财资产管理系统V1.0
 * 修改人：yuzj
 * 修改时间：2021年6月23日
 * 修改内容：新建
 * 系统名称：中邮理财资产管理系统V1.0
 */

package com.joyintech.tams.valuation.lossmodel.lgddefultmanager.lgdspecialconfig.dto;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.validation.groups.ValidateGroupEdit;
import com.joyintech.workflow.engine.annotation.PrimaryKey;
import com.joyintech.workflow.engine.dto.ProcessEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

public class LgdSpecialConfigDto extends BaseEntity implements ITableRow, ProcessEntity  {
    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -904100591006148547L;
    /**
     * 流水号
     */
    private String seqNo;

    /**
     * 金融产品代码
     */
    private String finprodId;

    /**
     * 金融产品代码
     */
    private String finprodIds;

    /**
     * 金融产品名称
     */
    private String finprodName;

    /**
     * LGD(%)
     */
    private BigDecimal lgd;


    /**
     * 操作类型
     */
    private String operateType;

    private String pageModel;

    /**
     * 画面Id
     */
    private String pageId;

    /**
     * elementId
     */
    private String elementId;
    /**
     * 选中的数据
     */
    private transient List<LgdSpecialConfigDto> selectedItem;



    @PrimaryKey(PrimaryKey.Field.PK_1)
    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    @NotBlank(message = "资产代码不能为空!", groups = {ValidateGroupEdit.class})
    public String getFinprodId() {
        return finprodId;
    }

    public void setFinprodId(String finprodId) {
        this.finprodId = finprodId;
    }

    @NotBlank(message = "资产名称不能为空！", groups = {ValidateGroupEdit.class})
    public String getFinprodName() {
        return finprodName;
    }

    public void setFinprodName(String finprodName) {
        this.finprodName = finprodName;
    }

    @NotNull(message = "LGD不能为空！", groups = {ValidateGroupEdit.class})
    public BigDecimal getLgd() {
        return lgd;
    }

    public void setLgd(BigDecimal lgd) {
        this.lgd = lgd;
    }



    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getPageModel() {
        return pageModel;
    }

    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    public String getPageId() {
        return pageId;
    }

    public void setPageId(String pageId) {
        this.pageId = pageId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    public String getFinprodIds() {
        return finprodIds;
    }

    public void setFinprodIds(String finprodIds) {
        this.finprodIds = finprodIds;
    }

    public List<LgdSpecialConfigDto> getSelectedItem() {
        return selectedItem;
    }

    public void setSelectedItem(List<LgdSpecialConfigDto> selectedItem) {
        this.selectedItem = selectedItem;
    }
}
