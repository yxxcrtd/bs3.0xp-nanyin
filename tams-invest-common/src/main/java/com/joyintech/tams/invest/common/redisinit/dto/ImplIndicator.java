/*
 * 文件名：ImplIndicator.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：裴家俊
 * 修改时间：2019年9月2日
 * 修改内容：新建
 * 系统名称：中信风险管理系统V1.0
 */

package com.joyintech.tams.invest.common.redisinit.dto;

import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.RskIsupIndicator;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicator;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndicatorResult;
import com.joyintech.tams.invest.common.riskinfrds.indic.entity.IIndividLimit;
import com.joyintech.tams.invest.common.riskinfrds.info.entity.IProdInfo;
import com.joyintech.tams.invest.common.risktrialrds.util.RiskUtil;

import java.util.*;

/**
 * 风险指标实现类
 * @author 裴家俊
 * @version 1.0 2019年9月2日
 * @see ImplIndicator
 * @since 1.0
 */
public class ImplIndicator extends RskIsupIndicator implements IIndicator {

    /**
     * 序列号
     */
    private static final long serialVersionUID = 7160706173695973703L;

    private  Map<String, IIndicatorResult> indResultMap;

    private  List<String> indParamList;

    private Map<String, Set<String>> individUnite;

    private Map<String, Set<String>> individAstDel;

    private Map<String, Map<String, IIndividLimit>> individSpecAstLimit;

    private Map<String, IIndividLimit> individSpecLimitRedis;

    private String exprGradRedis;

    private boolean isAbsRedis;

    @Override
    public boolean getIsAbs() {
        return isAbsRedis;
    }

    @Override
    public String getExprGrad() {
        return exprGradRedis;
    }

    @Override
    public List<String> getIndParamList() {
        return indParamList;
    }

    @Override
    public Map<String, IIndicatorResult> getIndicatorResult() {
        return indResultMap;
    }

    @Override
    public void setIndicatorResult(Map<String, IIndicatorResult> result) {
        this.indResultMap=result;
    }

    @Override
    public Map<String, Set<String>> getIndividUnite() {
        return individUnite;
    }

    @Override
    public Map<String, Map<String, IIndividLimit>> getIndividLimit() {
        return individSpecAstLimit;
    }

    @Override
    public Map<String, Set<String>> getIndividAstDel() {
        return individAstDel;
    }

    @Override
    public String getLimitCond() {
        if(StringUtil.isNotEmpty(this.getFoulCond()))
            return this.getFoulCond();
        else{
            return StringUtil.isNotEmpty(this.getApproveCond())?this.getApproveCond():this.getWarnCond();
        }
    }

    public void setIndParamList() {
        if(StringUtil.isNotEmpty(this.getExprGrad()))
            indParamList= RiskUtil.analysisIndParam(this.getExprGrad(),new ArrayList<String>());
    }

    public void setIndividUnite(Map<String, Set<String>> individUnite) {
        this.individUnite = individUnite;
    }

    public void setIndividAstDel(Map<String, Set<String>> individAstDel) {
        this.individAstDel = individAstDel;
    }

    public void setIndividSpecAstLimit(Map<String, Map<String, IIndividLimit>> individSpecAstLimit) {
        this.individSpecAstLimit = individSpecAstLimit;
    }

    @Override
    public Map<String, IIndividLimit> getPtlIndividLimit() {
        return individSpecLimitRedis;
    }

    public void setIndividSpecLimitRedis(Map<String, IIndividLimit> individSpecLimitRedis) {
        this.individSpecLimitRedis = individSpecLimitRedis;
    }

    public void setExprGradRedis(String exprGradRedis) {
        this.exprGradRedis = exprGradRedis;
    }

    public void setAbsRedis(boolean absRedis) {
        isAbsRedis = absRedis;
    }

    @Override
    public boolean isExcpWhenDivZero(IProdInfo prodInfo) {
        Date sysDate = DateUtil.parse(DateUtil.getSysDate());
        if (CommonUtil.isType(getIsExcpWhenDivZero(), BussinessDictCodeListRds.除0通过条件_起息日通过)) {
            return !(prodInfo != null && prodInfo.getVdate() != null && prodInfo.getVdate().compareTo(sysDate) == 0);
        }
        return !CommonUtil.isType(getIsExcpWhenDivZero(), BussinessDictCodeListRds.除0通过条件_无条件通过);
    }


}
