package com.joyintech.service.valuation.datatransplant.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.BokBalance;
import com.joyintech.tams.common.base.entity.BokBookdetailAll;
import com.joyintech.tams.common.base.entity.BokBookdetailCurrent;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.ValSubjectTableid;

/**
 * 数据移植余额移植mapper
 * 
 * @author chengzhong
 * @version 1.0 2018年11月10日
 * @see AccountsBalanceTransplantMapper
 * @since 1.0
 */
@Repository(value="serviceAccountsBalanceTransplantMapper")
public interface AccountsBalanceTransplantMapper {

    /**
     * 插入账务明细代码表<一句话功能简述> <功能详细描述>
     * 
     * @param list
     * @return
     * @see
     * @since 1.0
     */
    int insertBokDetailId(List<BokDetailId> list);

    /**
     * 插入业务明细科目表
     * 
     * @param list 账务明细代码列表
     * @see
     * @since 1.0
     */
    void insertBokBussDetsub(List<BokBussDetsub> list);

    /**
     * 插入账务明细（当前表）
     * 
     * @param list 账务明细代码列表
     * @see
     * @since 1.0
     */
    void insertBokBookdetailCurrent(List<BokBookdetailCurrent> list);

    /**
     * 插入账务明细（历史表）
     * 
     * @param list 批处理用账务明细
     * @see
     * @since 1.0
     */
    void insertBokBookdetailAll(List<BokBookdetailAll> list);

    /**
     * 插入科目余额表
     * 
     * @param list 批处理用账务明细
     * @see
     * @since 1.0
     */
    void insertBokBalance(List<BokBalance> list);

    /**
     * 根据科目号头部获取场景代码
     * 
     * @param subjectHead 科目号头部
     * @return
     * @see
     * @since 1.0
     */
    String getTableId(@Param("subjectHead") String subjectHead);
    
    /**
     * 获取场景代码
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<ValSubjectTableid> getTableIds();
    
    /**
     * 
     * 查询当日已有余额数量<查询一句话功能简述>
     * <功能详细描述>
     * @param booksetId
     * @param balanceDate
     * @param ccy
     * @return
     * @see
     * @since 1.0
     */
    int getTdyBanance(@Param("booksetId") String booksetId,
    		@Param("balanceDate") Date balanceDate,
    		@Param("oCcy") String oCcy);
}