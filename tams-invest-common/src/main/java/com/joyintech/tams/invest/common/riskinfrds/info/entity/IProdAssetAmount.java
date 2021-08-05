package com.joyintech.tams.invest.common.riskinfrds.info.entity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.tams.invest.common.constantrds.RedisKeyEnum;
import com.joyintech.tams.invest.common.riskinfrds.base.entity.IBusiness;

/**
 * 资产的实时规模信息
 * 
 * @author zy_zhangrui
 *
 */
public interface IProdAssetAmount extends IBusiness {

    @Override
    default String getKey() {
        return RedisKeyEnum.资产实时规模信息.key();
    }

    @Override
    default String getField() {
        return getPortfolioCode() + GlobalConstant.COLON + getAssetCode();
    }

    /**
     * 取得 组合代码
     * 
     * @return 组合代码
     */
    String getPortfolioCode();

    /**
     * 取得 资产代码
     * 
     * @return 资产代码
     */
    String getAssetCode();

    /**
     * 取得 资产规模-金额
     * 
     * @return 资产规模-金额
     */
    BigDecimal getAmount();

    /**
     * 取得 资产规模-数量
     * 
     * @return 资产规模-数量
     */
    BigDecimal getNum();

    /**
     * 取得 预占实占的规模变化情况<br>
     * key:预占实占编号<br>
     * value:数组[2]{金额，数量}
     * 
     * @return 预占实占的规模变化情况
     */
    Map<String, BigDecimal[]> getOcpList();

    /**
     * 设置 预占实占的规模变化情况
     * 
     * @param ocpList 预占实占的规模变化情况
     */
    void setOcpList(Map<String, BigDecimal[]> ocpList);

    /**
     * 添加预占实占的规模变化情况
     * 
     * @param ocpCode 预占实占编号
     * @param amount 金额变化值
     * @param num 数量变化值
     */
    default void addOcp(String ocpCode, BigDecimal amount, BigDecimal num) {
        Map<String, BigDecimal[]> ocpList = getOcpList();
        if (ocpList == null) {
            ocpList = new HashMap<>();
            setOcpList(ocpList);
        }
        BigDecimal[] arr = new BigDecimal[2];
        arr[0] = amount == null ? BigDecimal.ZERO : amount;
        arr[1] = num == null ? BigDecimal.ZERO : num;
        ocpList.put(ocpCode, arr);
    }

    /**
     * 变更（部分释放）预占实占的规模变化情况
     * 
     * @param ocpCode 预占实占编号
     * @param amount 金额变化值
     * @param num 数量变化值
     */
    default void subOcp(String ocpCode, BigDecimal amount, BigDecimal num) {
        Map<String, BigDecimal[]> ocpList = getOcpList();
        if (ocpList != null && !ocpList.isEmpty()) {
            BigDecimal[] arr = ocpList.get(ocpCode);
            if (arr == null || arr.length != 2) {
                return;
            }
            arr[0] = ArithmeticUtil.subtract(arr[0], amount == null ? BigDecimal.ZERO : amount);
            arr[1] = ArithmeticUtil.subtract(arr[1], num == null ? BigDecimal.ZERO : num);
            ocpList.put(ocpCode, arr);
        }
    }

    /**
     * 删除预占实占的规模变化情况
     * 
     * @param ocpCode 预占实占编号
     */
    default void removeOcp(String ocpCode) {
        Map<String, BigDecimal[]> ocpList = getOcpList();
        if (ocpList != null && !ocpList.isEmpty()) {
            ocpList.remove(ocpCode);
        }
    }

    /**
     * 是否包含预占实占信息
     * 
     * @param ocpCode 预占实占编号
     * @return true:包含
     */
    default boolean containsOcp(String ocpCode) {
        Map<String, BigDecimal[]> ocpList = getOcpList();
        return ocpList != null && ocpList.containsKey(ocpCode);
    }

    default BigDecimal ocpValue(String ocpCode, int index) {
        Map<String, BigDecimal[]> ocpList = getOcpList();
        if (ocpList != null && !ocpList.isEmpty() && ocpList.get(ocpCode) != null && ocpList.get(ocpCode).length > index) {
            return ocpList.get(ocpCode)[index];
        }
        return BigDecimal.ZERO;
    }

    default BigDecimal ocpAmount(String ocpCode) {
        return ocpValue(ocpCode, 0);
    }

    default BigDecimal ocpNum(String ocpCode) {
        return ocpValue(ocpCode, 1);
    }
}
