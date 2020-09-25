/*
 * 文件名：InterfLc0007InEntity.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：changwenbin
 * 修改时间：2018年11月10日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.interf.ilc0007.entity;

import com.joyintech.framework.base.BaseEntity;
import com.joyintech.service.interf.core.annotation.EsbFieldType;
import com.joyintech.service.interf.core.annotation.EsbName;

/**
 * Lc0007 实时查询借据余额输入
 * 
 * @author changwenbin
 * @version 1.0 2018年11月10日
 * @see InterfLc0007InEntity
 * @since 1.0
 */
public class InterfLc0007InEntity extends BaseEntity {

    /**
     * 序列号
     */
    private static final long serialVersionUID = -5341798329088446007L;

    /**
     * 借据流水号
     */
    private String duebillseqno;

    /**
     * 放款指令编号
     */
    private String putoutorderno;

    /**
     * 业务品种编号
     */
    private String bussvarietyno;

    /**
     * 平台日期
     */
    private String cdate;

    /**
     * 现金流二级类型code值
     */
    private String benjiCode;

    /**
     * 获取 借据流水号
     * 
     * @return 借据流水号
     */
    public String getDuebillseqno() {
        return duebillseqno;
    }

    /**
     * 设置 借据流水号
     * 
     * @param duebillseqno 借据流水号
     */
    @EsbName(value = "DUEBILL_SEQ_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setDuebillseqno(String duebillseqno) {
        this.duebillseqno = duebillseqno;
    }

    /**
     * 获取 放款指令编号
     * 
     * @return 放款指令编号
     */
    public String getPutoutorderno() {
        return putoutorderno;
    }

    /**
     * 设置 放款指令编号
     * 
     * @param putoutorderno 放款指令编号
     */
    @EsbName(value = "PUTOUT_ORDER_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setPutoutorderno(String putoutorderno) {
        this.putoutorderno = putoutorderno;
    }

    /**
     * 获取 业务品种编号
     * 
     * @return 业务品种编号
     */
    public String getBussvarietyno() {
        return bussvarietyno;
    }

    /**
     * 设置 业务品种编号
     * 
     * @param bussvarietyno 业务品种编号
     */
    @EsbName(value = "BUSS_VARIETY_NO")
    @EsbFieldType(value = EsbFieldType.STRING)
    public void setBussvarietyno(String bussvarietyno) {
        this.bussvarietyno = bussvarietyno;
    }

    /**
     * 获取 平台日期
     * 
     * @return 平台日期
     */
    public String getCdate() {
        return cdate;
    }

    /**
     * 设置 平台日期
     * 
     * @param cdate 平台日期
     */
    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    /**
     * 获取 现金流二级类型code值
     * 
     * @return 现金流二级类型code值
     */
    public String getBenjiCode() {
        return benjiCode;
    }

    /**
     * 设置 现金流二级类型code值
     * 
     * @param benjiCode 现金流二级类型code值
     */
    public void setBenjiCode(String benjiCode) {
        this.benjiCode = benjiCode;
    }

}
