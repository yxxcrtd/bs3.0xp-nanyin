package com.joyintech.tams.model.impairment.batch.eclcalculation.dto;

import com.joyintech.batch.batchbase.baseclass.entity.NeedOperateEntity;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 二叉树模型-reader
 */
public class EclCalculationReaderDto extends NeedOperateEntity {

    /**
     * 资产类型
     */
    private String secType;

    /**
     * 资产细分类
     */
    private String secDetailType;

    /**
     * 投资目的
     */
    private String invAim;

    /**
     * 减值结果过程集合<账套代码,减值结果过程明细>
     */
    private List<ModEclResultProcess> modEclResultProcessList;

    /**
     * 总质押金额
     */
    private BigDecimal totalPledgeAmt;

    /**
     * 质押券信息
     */
    private List<FinDealPledgeDTO> finDealPledgeDTOList;

    /**
     * 债券类、存款类、回购类资产LGD违约损失率配置
     */
    Map<String, ModLgdConfigDto> otherLgdConfigMap;
    /**
     * 其他债权类资产LGD违约损失率配置
     */
    Map<String, ModLgdConfigDto> deltRightLgdConfigMap;
    /**
     * 特殊资产LGD违约损失率配置
     */
    Map<String, ModLgdConfigDto> specialLgdConfigMap;


    /**
     * @return 资产类型
     */
    public String getSecType() {
        return secType;
    }

    /**
     * 设置 资产类型
     *
     * @param secType 资产类型
     */
    public void setSecType(String secType) {
        this.secType = secType;
    }

    /**
     * @return 资产细分类
     */
    public String getSecDetailType() {
        return secDetailType;
    }

    /**
     * 设置 资产细分类
     *
     * @param secDetailType 资产细分类
     */
    public void setSecDetailType(String secDetailType) {
        this.secDetailType = secDetailType;
    }

    /**
     * @return modEclResultProcessList
     */
    public List<ModEclResultProcess> getModEclResultProcessList() {
        return modEclResultProcessList;
    }

    /**
     * 设置 质押券信息
     *
     * @param modEclResultProcessList 质押券信息
     */
    public void setModEclResultProcessList(List<ModEclResultProcess> modEclResultProcessList) {
        this.modEclResultProcessList = modEclResultProcessList;
    }

    /**
     * @return 总质押金额
     */
    public BigDecimal getTotalPledgeAmt() {
        return totalPledgeAmt;
    }

    /**
     * 设置 总质押金额
     *
     * @param totalPledgeAmt 总质押金额
     */
    public void setTotalPledgeAmt(BigDecimal totalPledgeAmt) {
        this.totalPledgeAmt = totalPledgeAmt;
    }

    /**
     * @return 质押券信息
     */
    public List<FinDealPledgeDTO> getFinDealPledgeDTOList() {
        return finDealPledgeDTOList;
    }

    /**
     * 设置 finDealPledgeDTOList
     *
     * @param finDealPledgeDTOList finDealPledgeDTOList
     */
    public void setFinDealPledgeDTOList(List<FinDealPledgeDTO> finDealPledgeDTOList) {
        this.finDealPledgeDTOList = finDealPledgeDTOList;
    }

    /**
     * @return 投资目的
     */
    public String getInvAim() {
        return invAim;
    }

    /**
     * 设置 投资目的
     *
     * @param invAim 投资目的
     */
    public void setInvAim(String invAim) {
        this.invAim = invAim;
    }

    /**
     * @return 债券类、存款类、回购类资产LGD违约损失率配置
     */
    public Map<String, ModLgdConfigDto> getOtherLgdConfigMap() {
        return otherLgdConfigMap;
    }

    /**
     * 设置 债券类、存款类、回购类资产LGD违约损失率配置
     *
     * @param otherLgdConfigMap 债券类、存款类、回购类资产LGD违约损失率配置
     */
    public void setOtherLgdConfigMap(Map<String, ModLgdConfigDto> otherLgdConfigMap) {
        this.otherLgdConfigMap = otherLgdConfigMap;
    }

    /**
     * @return 其他债权类资产LGD违约损失率配置
     */
    public Map<String, ModLgdConfigDto> getDeltRightLgdConfigMap() {
        return deltRightLgdConfigMap;
    }

    /**
     * 设置 其他债权类资产LGD违约损失率配置
     *
     * @param deltRightLgdConfigMap 其他债权类资产LGD违约损失率配置
     */
    public void setDeltRightLgdConfigMap(Map<String, ModLgdConfigDto> deltRightLgdConfigMap) {
        this.deltRightLgdConfigMap = deltRightLgdConfigMap;
    }

    /**
     * @return 特殊资产LGD违约损失率配置
     */
    public Map<String, ModLgdConfigDto> getSpecialLgdConfigMap() {
        return specialLgdConfigMap;
    }

    /**
     * 设置 特殊资产LGD违约损失率配置
     *
     * @param specialLgdConfigMap 特殊资产LGD违约损失率配置
     */
    public void setSpecialLgdConfigMap(Map<String, ModLgdConfigDto> specialLgdConfigMap) {
        this.specialLgdConfigMap = specialLgdConfigMap;
    }
}
