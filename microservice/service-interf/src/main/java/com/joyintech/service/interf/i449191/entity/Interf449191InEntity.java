package com.joyintech.service.interf.i449191.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.i449181.entity.Interf449181InEntity;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 接口449191输入
 * 
 * @author whm
 * @version 1.0 2018年10月12日
 * @see Interf449181InEntity
 * @since 1.0
 */
public class Interf449191InEntity extends InterfAliveProdEntity<InterfProdLadderDetail> {

    /**
     * 取得金融产品代码
     * 
     * @return String 金融产品代码
     * @see
     * @since 1.0
     */
    @EsbName(value = "ShortName")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 70)
    @Required
    @Override
    public String getProdid() {
        return super.getProdid();
    }

    /*
     * 成立日期
     */
    @EsbName(value = "Startdate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getStartdate() {
        return super.getcDate();
    }
}
