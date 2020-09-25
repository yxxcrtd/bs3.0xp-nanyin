package com.joyintech.service.interf.ilc0001.entity;

import org.springframework.beans.factory.annotation.Required;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbLength;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.ibillinfo.entity.InterfBillInEntity;

/**
 * 接口LC0001输入
 * 
 * @author zhangrui1
 * @version 1.0 2018年10月12日
 * @see InterfBillInEntity
 * @since 1.0
 */
public class InterfLC0001InEntity extends InterfBillInEntity {

    @EsbName(value = "PRODID")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 20)
    @Required
    @Override
    public void setProdId(String prodId) {
        super.setProdId(prodId);
    }

    @EsbName(value = "SALESTARTDATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    @EsbLength(value = 8)
    @Required
    @Override
    public void setStartdate(String startdate) {
        super.setStartdate(startdate);
    }

    @EsbName(value = "SALEENDDATE")
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
