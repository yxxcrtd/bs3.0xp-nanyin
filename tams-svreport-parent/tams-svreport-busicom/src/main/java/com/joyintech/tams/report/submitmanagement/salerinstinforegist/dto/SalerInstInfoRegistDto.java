
package com.joyintech.tams.report.submitmanagement.salerinstinforegist.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbSalerInstSub;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Administrator
 * @version 1.0 2021.12.22
 * @see SalerInstInfoRegistDto
 * @since 1.0
 */
@TableName("CHB_SALER_INST_SUB")
public class SalerInstInfoRegistDto extends ChbSalerInstSub implements ProcessEntity {

    @BusinessKey("SALER_ORG_CODE")
    public String salerOrgCode;//总部或分支机构编码业务主键
    //报送状态字符串化
    public String getStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.报送数据状态, this.getStatus());
    }
    //报送状态字符串化
    public String getSendStatusStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.报送状态, this.getSendStatus());
    }
    //报送反馈状态字符串化
    public String getSubmitFeedbackStatusStr() { return SysDataUtil.getNameFromDataDict(DictCode.报送反馈状态, this.getSubmitFeedbackStatus()); }
    //报送时间字符串化
    public String getSubmitTimeStr() {
        if(StringUtil.isNotEmpty(this.getSubmitTime())){
            return DateUtil.formatDateTime(this.getSubmitTime());
        }
        return null;
    }
    //报送截止日字符串化
    public String getSubmitDeadlineStr() {
        if(StringUtil.isNotEmpty(this.getSubmitDeadline())){
            return DateUtil.formatDateTime(this.getSubmitDeadline());
        }
        return null;
    }

    //机构层级字符串化
    public String getSalerOrgLevelStr() {
        return this.getSalerOrgLevel();
    }
}
