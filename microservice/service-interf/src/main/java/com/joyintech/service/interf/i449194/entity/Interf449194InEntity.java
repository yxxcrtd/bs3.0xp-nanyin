package com.joyintech.service.interf.i449194.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 滚动N天产品成立接口(449194)-输入-对公零售
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月7日
 * @see Interf449194InEntity
 * @since 1.0
 */
public class Interf449194InEntity extends InterfAliveProdEntity<Interf449194InLvlEntity> {

    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    @Override
    public String getProdid() {
        return super.getProdid();
    }

    @EsbName(value = "StartDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getStartdate() {
        return super.getcDate();
    }

    @EsbName(value = "RefAddRateNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @EsbNumber(length = 4)
    @Required
    @Override
    public int getListCnt() {
        return super.getLvlList() == null ? 0 : super.getLvlList().size();
    }

    @EsbName(value = "RefAddRate")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    @Override
    public List<Interf449194InLvlEntity> getLvlList() {
        return super.getLvlList();
    }

}
