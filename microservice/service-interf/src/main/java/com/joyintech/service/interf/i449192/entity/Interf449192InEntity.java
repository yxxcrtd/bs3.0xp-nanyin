package com.joyintech.service.interf.i449192.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.tams.common.base.entity.InterfProdLadderDetail;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.prodtrade.dto.InterfAliveProdEntity;

/**
 * 接口449192输入
 * 
 * @author whm
 * @version 1.0 2018年11月13日
 * @see Interf449192InEntity
 * @since 1.0
 */

public class Interf449192InEntity extends InterfAliveProdEntity<InterfProdLadderDetail> {

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
     * 到期日期
     */
    @EsbName(value = "Enddate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public String getEnddate() {
        return super.getcDate();
    }
}
