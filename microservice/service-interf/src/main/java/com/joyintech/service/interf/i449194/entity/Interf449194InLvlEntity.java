package com.joyintech.service.interf.i449194.entity;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;
import com.joyintech.service.interf.core.constant.InterfDictCodeList;

/**
 * 滚动N天产品成立接口(449194)-输入-分层信息-对公和零售
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月7日
 * @see Interf449194InLvlEntity
 * @since 1.0
 */
public class Interf449194InLvlEntity extends InterfProdLadderDetail {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 6827477688782539666L;

    @EsbName(value = "RefAddRate")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 8, scale = 6)
    @Override
    public BigDecimal getRefaddrate() {
        return super.getRefaddrate();
    }

    @EsbName(value = "StartAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 15, scale = 2)
    @Override
    public BigDecimal getStartamount() {
        BigDecimal stAmt = super.getStartamount();
        return stAmt == null ? BigDecimal.ZERO : stAmt;
    }

    @EsbName(value = "EndAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 15, scale = 2)
    @Override
    public BigDecimal getEndamount() {
        BigDecimal edAmt = super.getEndamount();
        return edAmt == null ? new BigDecimal(999999999) : edAmt;
    }

    @EsbName(value = "StartCustomerLevel")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(2)
    @Override
    public String getStartcustomerlevel() {
        String startCusLvl = super.getStartcustomerlevel();
        if (StringUtil.equals("01", startCusLvl)) {
            return InterfDictCodeList.零售客户等级_普通.getValue();
        }
        else if (StringUtil.equals("02", startCusLvl)) {
            return InterfDictCodeList.零售客户等级_财富投资.getValue();
        }
        else if (StringUtil.equals("03", startCusLvl)) {
            return InterfDictCodeList.零售客户等级_私人银行.getValue();
        }
        return super.getStartcustomerlevel();
    }

    @EsbName(value = "EndCustomerLevel")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(2)
    @Override
    public String getEndcustomerlevel() {
        return getStartcustomerlevel();
    }

    @EsbName(value = "StartDayNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @EsbNumber(length = 4)
    @Override
    public Short getStartdaynum() {
        Short stDay = super.getStartdaynum();
        return stDay == null ? 1 : stDay;
    }

    @EsbName(value = "EndDayNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @EsbNumber(length = 4)
    @Override
    public Short getEnddaynum() {
        Short edDay = super.getEnddaynum();
        return edDay == null ? 9999 : edDay;
    }

}
