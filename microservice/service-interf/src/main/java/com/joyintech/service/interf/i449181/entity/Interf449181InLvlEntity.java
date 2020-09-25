package com.joyintech.service.interf.i449181.entity;

import java.math.BigDecimal;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;

/**
 * 接口449181输入.分层信息
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see Interf449181InLvlEntity
 * @since 1.0
 */
public class Interf449181InLvlEntity extends InterfProdLadderDetail {

    /**
     * <意义，目的和功能，以及可能被用到的地方>
     */
    private static final long serialVersionUID = -2233820073001913981L;

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
        return super.getStartamount();
    }

    @EsbName(value = "EndAmount")
    @EsbFieldType(value = EsbFieldType.DOUBLE)
    @EsbNumber(length = 15, scale = 2)
    @Override
    public BigDecimal getEndamount() {
        return super.getEndamount();
    }
}
