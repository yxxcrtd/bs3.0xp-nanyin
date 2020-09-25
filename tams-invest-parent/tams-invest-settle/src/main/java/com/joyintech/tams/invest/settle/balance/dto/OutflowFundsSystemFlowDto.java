package com.joyintech.tams.invest.settle.balance.dto;

import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.constant.*;
import com.joyintech.tams.invest.common.dto.settle.balance.BaseOutflowFundsSystemFlowDto;

/**
 * @program: tams-invest-parent
 * @description:
 * @author: 强成西
 * @create: 2018-12-04 10:34
 **/
public class OutflowFundsSystemFlowDto extends BaseOutflowFundsSystemFlowDto {

    public String getPaBizTypeString() {
        if (DictCodeListExt1.平安业务类型_产品强减.getValue().equals(super.getPaBizType())) {
            return SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
        }
        else if (DictCodeListExt3.平安业务类型_已到期资产存续期.getValue().equals(super.getPaBizType())) {

            return "逾期:" + SysDataUtil.getNameFromDataDict(DictCode.金融产品类型_投管用, getFinprodType2())
                   + SysDataUtil.getNameFromDataDict(DictCode.交易方向, super.getPs());
        }
        return SysDataUtil.getNameFromDataDict(DictCode.平安业务类型, getPaBizType());
    }

    public String getStatusValidString() {
        return SysDataUtil.getNameFromDataDict(DictCode.画面数据状态, getStatusValid());
    }

    public String getMatchStatusString() {
        if (DictCodeList.是否_是.getValue().equals(getMatchStatus())) {
            return "已匹配";
        }
        else {
            return "未匹配";
        }
    }
}
