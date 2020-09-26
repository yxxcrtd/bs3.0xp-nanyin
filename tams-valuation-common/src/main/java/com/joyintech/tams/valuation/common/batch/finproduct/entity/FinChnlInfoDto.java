package com.joyintech.tams.valuation.common.batch.finproduct.entity;
/**
 * @author yanya
 * @create 2019-12-12 10:31
 */

import com.joyintech.framework.base.BaseEntity;

/**
 * @ClassName FinChnlInfoDto
 * @Description: 通道信息DTO
 * @Author yanya
 * @Date 2019/12/31
 * @Version V1.0
 **/
public class FinChnlInfoDto extends BaseEntity {

    private static final long serialVersionUID = 7834749590935769461L;
    /**
     * 通道代码
     */
    private String chlId;

    /**
     * 通道名称
     */
    private String chlName;

    /**
     * 收取方式
     */
    private String collectType;

    public String getChlId() {
        return chlId;
    }

    public void setChlId(String chlId) {
        this.chlId = chlId;
    }

    public String getChlName() {
        return chlName;
    }

    public void setChlName(String chlName) {
        this.chlName = chlName;
    }

    public String getCollectType() {
        return collectType;
    }

    public void setCollectType(String collectType) {
        this.collectType = collectType;
    }
}
