package com.joyintech.tams.invest.settle.balance.dto;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseCashFlowBalanceManagementDto;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-04 10:09
 **/
public class CashFlowBalanceManagement001Dto extends BaseCashFlowBalanceManagementDto {

    public String getSysBussTypeString() {
        if (DictCodeListExt1.平安业务类型_产品强减.getValue().equals(super.getSysBussType())) {
            return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
        }
        else if (DictCodeListExt1.平安业务类型_计划类资产_利率型_赎回.getValue().equals(super.getSysBussType())
                || DictCodeListExt1.平安业务类型_计划类资产_利率型_申购.getValue().equals(super.getSysBussType())) {
            return "计划类资产（利率型）" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
        }
        else if (DictCodeListExt1.平安业务类型_债券类资产行权.getValue().equals(super.getSysBussType())) {
            return "债券类资产-" + SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
        }

        return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, super.getSysBussType());
    }

    public String getMatchSeqnoString() {
        if (StringUtil.isNotEmpty(getMatchSeqno())) {
            return "已匹配";
        } else {
            return "未匹配";
        }
    }

    public String getSysCdateString() {
        if (getSysCdate() != null) {
            return DateUtil.format(getSysCdate());
        }
        return "";
    }

    public String getTruPsString() {
        return SysDataUtil.getNameFromDataDict(DictCode.托管流水借贷方向, super.getTruPs());
    }
}
