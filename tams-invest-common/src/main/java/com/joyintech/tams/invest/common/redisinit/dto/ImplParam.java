/*
 * 文件名：ImplParam.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年8月27日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskIsupIndicatorParaDtl;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.riskinfrds.param.entity.IParam;

/**
 * 风险指标参数实现类
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see ImplParam
 * @since 1.0
 */
public class ImplParam  extends RskIsupIndicatorParaDtl implements IParam{

    private static final long serialVersionUID = 7524316940371198896L;

    private String isForbid;

    private String isUpRedis;

    private String paramDataType;

    private boolean isFreshCanTrdRedis;

    private boolean isEnumTypeRedis;

    private boolean isEnumUpRedis;

    private String[] fieldArrayRedis;

    @Override
    public boolean isFreshCanTrd() {
        return this.isFreshCanTrdRedis;
    }

    @Override
    public boolean isEnumType() {
       return this.isEnumTypeRedis;
    }

    @Override
    public boolean isUpRedis() {
        return this.isEnumUpRedis;
    }

    @Override
    public String[] getRedisFieldArr() {
       return this.fieldArrayRedis;
    }

    public void setFreshCanTrdRedis(String freshCanTrdRedis) {
        if(StringUtil.isNotEmpty(freshCanTrdRedis))
            isFreshCanTrdRedis=  BussinessDictCodeListRds.是否_是.getValue().equals(freshCanTrdRedis);
        else
            isFreshCanTrdRedis= false;
    }

    public void setEnumTypeRedis(String isForbid) {
        if(StringUtil.isNotEmpty(isForbid))
            isEnumTypeRedis=   BussinessDictCodeListRds.是否_是.getValue().equals(isForbid);
        else
            isEnumTypeRedis= false;
    }

    public void setFieldArrayRedis(String redisField) {
        if(StringUtil.isNotEmpty(redisField)){
            this.fieldArrayRedis= redisField.split(GlobalConstant.COMMA);
        }
    }

    public String getIsForbid() {
        return isForbid;
    }

    public void setIsForbid(String isForbid) {
        this.isForbid = isForbid;
        if(StringUtil.isNotEmpty(isForbid))
            isEnumTypeRedis=   BussinessDictCodeListRds.是否_是.getValue().equals(isForbid);
        else
            isEnumTypeRedis= false;
    }

    /**
     * 参数名称
     */
    private String paraName;

    @Override
    public String getParaName() {
        return paraName;
    }

    /**
     * 设置 参数名称
     *
     * @param paraName 参数名称
     */
    public void setParaName(String paraName) {
        this.paraName = paraName;
    }

    public String getIsUpRedis() {
        return isUpRedis;
    }

    public void setIsUpRedis(String isUpRedis) {
        this.isUpRedis = isUpRedis;
        if(StringUtil.isNotEmpty(isUpRedis))
            isEnumUpRedis= !BussinessDictCodeListRds.是否_否.getValue().equals(isUpRedis);
        else
            isEnumUpRedis= true;
    }

    @Override
    public String getParamDataType() {
        return paramDataType;
    }

    public void setParamDataType(String paramDataType) {
        this.paramDataType = paramDataType;
    }
    
    /**
     * 机构号
     */
    private String institutionCode;

    
    /**
     * 获取 institutionCode
     * @return institutionCode.
     */
    public String getInstitutionCode() {
        return institutionCode;
    }

    
    /**
     * 设置 institutionCode
     * @param institutionCode institutionCode
     */
    public void setInstitutionCode(String institutionCode) {
        this.institutionCode = institutionCode;
    }
    
    
}
