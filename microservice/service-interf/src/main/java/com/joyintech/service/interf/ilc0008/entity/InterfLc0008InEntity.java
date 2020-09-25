/*
 * 文件名：InterfLc0008InEnity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月12日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0008.entity;

import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;
import com.joyintech.service.interf.ilc0007.entity.InterfLc0007InEntity;

/**
 * Lc0008输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月12日
 * @see InterfLc0008InEntity
 * @since 1.0
 */
public class InterfLc0008InEntity extends InterfLc0007InEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 1435504776661246334L;

    /**
     * 还款金额
     */
    private String reqayamt;

    /**
     * 到期日
     */
    private String expirydate;

    /**
     * 获取 还款金额
     * 
     * @return 还款金额
     */
    public String getReqayamt() {
        return reqayamt;
    }

    /**
     * 设置 借据流水号
     * 
     * @param duebillseqno 借据流水号
     */
    @EsbName(value = "DUEBILL_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setDuebillseqno(String duebillseqno) {
        super.setDuebillseqno(duebillseqno);
    }

    /**
     * 设置 放款指令编号
     * 
     * @param putoutorderno 放款指令编号
     */
    @EsbName(value = "PUTOUT_ORDER_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setPutoutorderno(String putoutorderno) {
        super.setPutoutorderno(putoutorderno);
    }

    /**
     * 设置 业务品种编号
     * 
     * @param bussvarietyno 业务品种编号
     */
    @EsbName(value = "BUSS_VARIETY_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    @Override
    public void setBussvarietyno(String bussvarietyno) {
        super.setBussvarietyno(bussvarietyno);
    }

    /**
     * 设置 还款金额
     * 
     * @param reqayamt 还款金额
     */
    @EsbName(value = "REPAY_AMT")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setReqayamt(String reqayamt) {
        this.reqayamt = reqayamt;
    }

    /**
     * 获取 到期日
     * 
     * @return 到期日
     */
    public String getExpirydate() {
        return expirydate;
    }

    /**
     * 设置 到期日
     * 
     * @param expirydate 到期日
     */
    @EsbName(value = "EXPIRY_DATE")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

}
