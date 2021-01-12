package com.joyintech.tams.svreport.chb.botastpositionregist.dto;

import java.util.Date;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.ChbBotAstPositionSub;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.DictFormat;
import com.joyintech.tams.report.common.base.annotate.TableName;

/**
 * 描述:CHB_AST_VAL_SUB表的实体类
 * @version
 * @author:  Administrator
 * @创建时间: 2019-01-22
 */
@TableName("CHB_BOT_AST_POS_SUB")
public class ChbBotAstPositionRegist001Dto extends ChbBotAstPositionSub implements ITableRow{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8746779395701736198L;
	
	private String registSeqno ;
	
	 /**
     * 反馈状态
     */
    @DictFormat(DictCode.报送反馈状态)
    private String submitFeedbackStatus;
    
    /**
     * 持仓日期
     */
    @BusinessKey("POS_DATE")
    private Date posDate;

    /**
     * 资管计划代码(对应资产管理计划行内资产/负债编码)
     */
    @BusinessKey("PLAN_ASSET_ID")
    private String planAssetId;

    /**
     * 行内资产/负债编码
     */
    @BusinessKey("ASSET_CODE")
    private Object assetCode;
    
    
	public String getRegistSeqno() {
		return registSeqno;
	}

	public void setRegistSeqno(String registSeqno) {
		this.registSeqno = registSeqno;
	}

	public Date getPosDate() {
		return posDate;
	}

	public void setPosDate(Date posDate) {
		this.posDate = posDate;
	}

	public String getPlanAssetId() {
		return planAssetId;
	}

	public void setPlanAssetId(String planAssetId) {
		this.planAssetId = planAssetId;
	}

	public Object getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(Object assetCode) {
		this.assetCode = assetCode;
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
	
	public String getPosDateExport(){
		return DateUtil.format(this.getPosDate(), DateUtil.DEFAULT_OTHER_FORMAT);
	}

	public String getSubmitFeedbackStatus() {
		return submitFeedbackStatus;
	}

	public void setSubmitFeedbackStatus(String submitFeedbackStatus) {
		this.submitFeedbackStatus = submitFeedbackStatus;
	}
	
	
}