package com.joyintech.service.interf.ilc0002.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillInEntity;

/**
 * 接口LC0002输入
 * 
 * @author 杨静敏
 * @version 1.0 2018年11月21日
 * @see InterfLC0002InEntity
 * @since 1.0
 */
public class InterfLC0002InEntity extends InterfBillInEntity {

    @EsbName(value = "PRODID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setProdId(String prodId) {
        super.setProdId(prodId);
    }

    @EsbName(value = "StartDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public void setStartdate(String startdate) {
        super.setStartdate(startdate);
    }

    @EsbName(value = "EndDate")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public void setEnddate(String enddate) {
        super.setEnddate(enddate);
    }

    @EsbName(value = "MKTDEPTNO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setSaleDept(String saleDept) {
        super.setSaleDept(saleDept);
    }

}
