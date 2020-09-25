package com.joyintech.service.interf.i449181.entity;

import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.core.annotation.EsbNumber;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 接口449181输入
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see Interf449181InEntity
 * @since 1.0
 */
public class Interf449181InEntity extends InterfAliveProdEntity<Interf449181InLvlEntity> {

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
        return super.getStartdate();
    }

    @EsbName(value = "EndDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getEnddate() {
        return super.getEnddate();
    }

    /**
     * 取得分层数量
     * 
     * @return int 分层数量
     * @see
     * @since 1.0
     */
    @EsbName(value = "RefAddRateNum")
    @EsbFieldType(value = EsbFieldType.INT)
    @EsbNumber(length = 4)
    @Required
    @Override
    public int getListCnt() {
        return super.getListCnt();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @EsbName(value = "RefAddRate")
    @EsbFieldType(value = EsbFieldType.ARRAY)
    public List<Interf449181InLvlEntity> getLvlList() {
        return super.getLvlList();
    }
}
