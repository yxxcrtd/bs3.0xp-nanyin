
package com.joyintech.tams.report.submitmanagement.salerideninforegist.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbSalerIdenSub;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.report.common.base.annotate.BusinessKey;
import com.joyintech.tams.report.common.base.annotate.TableName;
import com.joyintech.workflow.engine.dto.ProcessEntity;

/**
 * 〈一句话功能简述〉 〈功能详细描述〉
 *
 * @author Administrator
 * @version 1.0 2021.12.22
 * @see SalerIdenInfoRegistDto
 * @since 1.0
 */
@TableName("CHB_SALER_IDEN_SUB")
public class SalerIdenInfoRegistDto extends ChbSalerIdenSub implements ProcessEntity {

    @BusinessKey("SALER_INS_NO")
    public String salerInsNo;//内部编码业务主键
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

    //出生日期字符串化
    public String getSalerBirthdayStr() {
        if(StringUtil.isNotEmpty(this.getSalerBirthday())){
            return DateUtil.formatDateTime(this.getSalerBirthday());
        }
        return null;
    }

    //销售人员证件类型字符串化
    public String getSalerCredentTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售人员证件类型, this.getSalerCredentType());
    }
    //所属地区字符串化
    public String getSalerAreaTypeStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.销售区域_中债, this.getSalerAreaType());
    }
    //销售人员学历字符串化
    public String getSalerEduLevelStr() { return SysDataUtil.getNameFromDataDict(DictCode.销售人员学历, this.getSalerEduLevel()); }

    //性别字符串化
    public String getSalerSexStr() {
        return SysDataUtil.getNameFromDataDict(DictCode.性别_中债, this.getSalerSex());
    }

    //销售人员学位字符串化
    public String getSalerDegreeStr() { return SysDataUtil.getNameFromDataDict(DictCode.销售人员学位, this.getSalerDegree()); }

    //专业技术职称字符串化
    public String getSalerLevleStr() { return SysDataUtil.getNameFromDataDict(DictCode.销售人员专业技术职称, this.getSalerLevle()); }

    //当前状态字符串化
    public String getSalerStatusStr() { return SysDataUtil.getNameFromDataDict(DictCode.销售人员当前状态, this.getSalerStatus()); }
}
