package com.joyintech.tams.svreport.chb.employeeinforegist.dto;

import com.joyintech.framework.export.paginator.domain.ITableRow;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.tams.common.base.entity.ChbEmployeeInfoSub;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.TableName;


/**
 * @author lingxq
 * @version 1.0 2019年1月22日
 * @see EmployeeInfoReg001Dto
 * @since 1.0
 */
@TableName("CHB_EMPLOYEE_INFO_SUB")
public class EmployeeInfoReg001Dto extends ChbEmployeeInfoSub implements ITableRow{
	
	private static final long serialVersionUID = 1L;
	public String getRegistSeqno(){
		return getSumitSeq() == null ? "" : getSumitSeq();
	}
	public String getSubmitTimeStr() {
        return getSubmitTime() == null ? null : DateUtil.formatDateTime(getSubmitTime());
    }
	
	public String getChbSubmitDeadlineStr() {
        return getSubmitDeadline() == null ? null : DateUtil.formatDateTime(getSubmitDeadline());
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
	
	public String getEmployeeTypeStr(){
		return SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, this.getEmployeeType());
	}
}
