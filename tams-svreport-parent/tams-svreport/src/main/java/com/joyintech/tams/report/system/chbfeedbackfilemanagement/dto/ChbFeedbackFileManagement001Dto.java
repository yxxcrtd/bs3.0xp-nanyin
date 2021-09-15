package com.joyintech.tams.report.system.chbfeedbackfilemanagement.dto;

import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.entity.ChbFeedbackFile;
import com.joyintech.tams.constant.DictCode;

public class ChbFeedbackFileManagement001Dto extends ChbFeedbackFile {

    /**
     * 业务组类型
     * @return BUSINESS_GROUP_TYPE 业务组类型
     */
    public String getBusinessGroupTypeName() {
        //TODO：字典还没确定，等确定后需要设置转换
        return getBusinessGroupType();
    }

    /**
     * 处理状态
     * @return HANDLE_STATUS 处理状态
     */
    public String getHandleStatusName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.中债一二三期文件处理状态, this.getHandleStatus()))?
                SysDataUtil.getNameFromDataDict(DictCode.中债一二三期文件处理状态, this.getHandleStatus()):this.getHandleStatus();
    }

    /**
     * 报文指令类型
     * @return REPORT_ETR_TYPE 报文指令类型
     */
    public String getReportEtrTypeName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.报表名称, this.getReportEtrType()))?
                SysDataUtil.getNameFromDataDict(DictCode.报表名称, this.getReportEtrType()):this.getReportEtrType();
    }

    /**
     * 是否迁移
     * @return MOVE_FLAG 是否迁移
     */
    public String getMoveFlagName() {
        return StringUtil.isNotEmpty(SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getMoveFlag()))?
                SysDataUtil.getNameFromDataDict(DictCode.是否_中债, this.getMoveFlag()):this.getMoveFlag();
    }
}
