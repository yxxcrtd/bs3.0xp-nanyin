package com.joyintech.service.basicdata.marketdata.mstinterestratequotationinfo.dto;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.joyintech.tams.common.base.entity.MstIntRateInfo;
import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;

/**
 * 基准利率实体类
 * @author fanshuanghu
 * @version 1.0 2018年8月17日
 * @see ComInterestRateQuotationInfoDto
 * @since 1.0
 */
public class ComInterestRateQuotationInfoDto extends MstIntRateInfo implements ITableRow {

    /**
     * 序列化值
     */
    private static final long serialVersionUID = 938342833630662060L;



    /**
     * 选中的数据
     */
    private transient List<ComInterestRateQuotationInfoDto> selectedItem;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 页面模型
     */
    private String pageModel;

    /**
     * 请求的结构名
     */
    private String elementId;

    /**
     * 获取选择的数据
     * @return List<MstInterestRateQuotationInfoDto> 实体类集合
     * @see
     * @since 1.0
     */
    public List<ComInterestRateQuotationInfoDto> getSelectedItem() {
        return selectedItem;
    }

    /**
     * 设置选择的数据
     * @param selectedItem 实体类集合
     * @see
     * @since 1.0
     */
    public void setSelectedItem(List<ComInterestRateQuotationInfoDto> selectedItem) {
        this.selectedItem = selectedItem;
    }

    /**
     * 获取操作类型
     * @return String 操作类型
     * @see
     * @since 1.0
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设置操作类型
     * @param operateType 操作类型
     * @see
     * @since 1.0
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 获取page模型
     * @return String page模型
     * @see
     * @since 1.0
     */
    public String getPageModel() {
        return pageModel;
    }

    /**
     * 设置page模型
     * @param pageModel page模型
     * @see
     * @since 1.0
     */
    public void setPageModel(String pageModel) {
        this.pageModel = pageModel;
    }

    /**
     * 获取请求控件Id
     * @return String 请求控件Id
     * @see
     * @since 1.0
     */
    public String getElementId() {
        return elementId;
    }

    /**
     * 设置请求控件Id
     * @param elementId 请求控件Id
     * @see
     * @since 1.0
     */
    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    /**
     * 利率代码
     */
    @Override
    public String getIntRateId() {
        return super.getIntRateId();
    }

    /**
     * 发布日字符串格式
     * @return String 发布日字符串格式
     */
    public String getEffDateStr() {
        return DateUtil.format(super.getEffDate());
    }

    /**
     * 生效日
     * {@inheritDoc}
     */
    public Date getEffDate() {
        return super.getEffDate();
    }

    /**
     * 货币
     */
    @Override
    public String getCcy() {
        return super.getCcy();
    }

    /**
     * 利率限制条件
     * {@inheritDoc}
     */
    public BigDecimal getIntRate() {
        return super.getIntRate();
    }

    /**
     * 获取发布日
     * {@inheritDoc}
     */
    public Date getRelaeseDate() {
        return super.getRelaeseDate();
    }
}
