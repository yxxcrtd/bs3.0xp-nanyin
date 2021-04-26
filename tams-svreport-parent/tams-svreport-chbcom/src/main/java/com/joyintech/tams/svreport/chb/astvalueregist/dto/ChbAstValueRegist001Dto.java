package com.joyintech.tams.svreport.chb.astvalueregist.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.tams.common.base.entity.ChbAstValueSub;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;

/**
 * 描述:CHB_AST_VAL_SUB表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-22
 */
@TableName("CHB_AST_VAL_SUB")
public class ChbAstValueRegist001Dto extends ChbAstValueSub implements ITableRow{
	
	private static final long serialVersionUID = 8664492633593217854L;
	 /**
     * 反馈状态
     */
    @DictFormat(DictCode.报送反馈状态)
    private String submitFeedbackStatus;
    
    /**
     * 行内资产/负债编码
     */
    @BusinessKey("ASSET_CODE")
    private String assetCode;
    
    /**
     * 估值日期
     */
    @BusinessKey("VAL_DATE")
    private Date valDate;

	public String getSubmitFeedbackStatus() {
		return submitFeedbackStatus;
	}

	public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
		this.submitFeedbackStatus = submitFeedbackStatus;
	}

	public String getStatusStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.getStatus());
	}
	
	public String getSendStatusStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.报送状态, this.getSendStatus());
	}
	
	public String getSubmitFeedbackStatusStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.报送反馈状态, this.getSubmitFeedbackStatus());
	}
	
	public String getValDateExport(){
		return DateUtil.format(this.getValDate(), DateUtil.DEFAULT_OTHER_FORMAT);
	}
	
	public String getUnitValNet4Scale(){
		if(null != this.getUnitValTotal()) {
			return this.getUnitValNet().setScale(4, BigDecimal.ROUND_HALF_UP).toString();
		}
		return null;
	}
	
	public String getUnitValTotal4Scale(){
		if(null != this.getUnitValTotal()){
			return this.getUnitValTotal().setScale(4, BigDecimal.ROUND_HALF_UP).toString();
		}
		return  null;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}

	public Date getValDate() {
		return valDate;
	}

	public void setValDate(Date valDate) {
		this.valDate = valDate;
	}
	
}