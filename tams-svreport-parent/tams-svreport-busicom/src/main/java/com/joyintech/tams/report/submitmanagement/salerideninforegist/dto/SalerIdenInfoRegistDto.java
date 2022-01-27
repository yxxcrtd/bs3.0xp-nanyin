
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
            String time = DateUtil.formatDateTime(this.getSalerBirthday());
            return time.substring(0,10).replace("-", "").replace("/", "");
        }
        return null;
    }

    //销售人员证件类型字符串化
    public String getSalerCredentTypeStr() {
        return this.getSalerCredentType();
    }
    //所属地区字符串化
    public String getSalerAreaTypeStr() {
        return this.getSalerAreaType();
    }
    //销售人员学历字符串化
    public String getSalerEduLevelStr() { return this.getSalerEduLevel(); }

    //性别字符串化
    public String getSalerSexStr() {
        return this.getSalerSex();
    }

    //销售人员学位字符串化
    public String getSalerDegreeStr() { return this.getSalerDegree(); }

    //专业技术职称字符串化
    public String getSalerLevleStr() { return this.getSalerLevle(); }

    //当前状态字符串化
    public String getSalerStatusStr() { return this.getSalerStatus(); }

    //销售人员理财专业证书字符串化
    public String getSalerCertificateTypeStr() {
        if(this.getSalerCertificateType() != null && this.getSalerCertificateType() != ""){
            int SalerCertificateTypeNum = ciShu(this.getSalerCertificateType(),",");
            if(SalerCertificateTypeNum == 0){
                return this.getSalerCertificateType() + " " +  SysDataUtil.getNameFromDataDict(DictCode.销售人员理财专业证书, this.getSalerCertificateType());
            }else{
                String SalerCertificateTypeSr = this.getSalerCertificateType().replace(",","");
                String SalerCertificateType = "无";
                for(int i = 0;i < (SalerCertificateTypeNum + 1);i++){
                    int j = (i+1) * 2;
                    if(SalerCertificateType.equals("无")){
                        SalerCertificateType =SalerCertificateTypeSr.substring(j-2,j) + " " + SysDataUtil.getNameFromDataDict(DictCode.销售人员理财专业证书, SalerCertificateTypeSr.substring(j-2,j));
                    }else{
                        SalerCertificateType = SalerCertificateType + "," + SalerCertificateTypeSr.substring(j-2,j) + " " + SysDataUtil.getNameFromDataDict(DictCode.销售人员理财专业证书, SalerCertificateTypeSr.substring(j-2,j));
                    }
                }
                return SalerCertificateType;
            }
        }
        return null;
    }

    //可销售产品风险等级字符串化
    public String getSalerRiskLevelStr() {
        if(this.getSalerRiskLevel() != null && this.getSalerRiskLevel() != ""){
            int SalerRiskLevelNum = ciShu(this.getSalerRiskLevel(),",");
            if(SalerRiskLevelNum == 0){
                return this.getSalerRiskLevel() + " " +  SysDataUtil.getNameFromDataDict(DictCode.可销售产品风险等级, this.getSalerRiskLevel());
            }else{
                String SalerRiskLevelSr = this.getSalerRiskLevel().replace(",","");
                String SalerRiskLevel = "无";
                for(int i = 0;i < (SalerRiskLevelNum + 1);i++){
                    int j = (i+1) * 2;
                    if(SalerRiskLevel.equals("无")){
                        SalerRiskLevel =SalerRiskLevelSr.substring(j-2,j) + " " + SysDataUtil.getNameFromDataDict(DictCode.可销售产品风险等级, SalerRiskLevelSr.substring(j-2,j));
                    }else{
                        SalerRiskLevel = SalerRiskLevel + "," + SalerRiskLevelSr.substring(j-2,j) + " " + SysDataUtil.getNameFromDataDict(DictCode.可销售产品风险等级, SalerRiskLevelSr.substring(j-2,j));
                    }
                }
                return SalerRiskLevel;
            }
        }
        return null;
    }

    /**
     * 判断str1中包含str2的个数
     * @param str1
     * @param str2
     * @return counter
     */
    public static int ciShu(String str1,String str2){
        int counter = 0;
        for(int i = 0;i < str1.length() ;i++) {
            int index = str1.indexOf(str2, i);
            if (i == index) {
                counter++;
            }
        }
        return counter;
    }
}
