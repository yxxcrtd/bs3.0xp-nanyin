package com.joyintech.tams.valuation.common.auxiliary.dao;

import com.joyintech.tams.valuation.batch.common.accounting.entity.SubjectBalance;
import com.joyintech.tams.common.base.entity.BokBussDetsub;
import com.joyintech.tams.valuation.common.auxiliary.dto.BokBookdetailCurrentDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yanya
 * @create 2020-12-12 14:16
 */
@Repository
public interface OperationAuxiliaryMapper {

    /**
     * 账务当前表查询
     * @param subjectNo
     * @param booksetIdTmplList
     * @param booksetIdList
     * @param booksetId
     * @return
     */
    List<BokBookdetailCurrentDto> getBookdetailCurrentData(Map<String,Object> paramMap);

    /**
     * 科目余额查询共通方法
     * @param booksetId
     * @param balanceDate
     * @return
     */
    List<SubjectBalance> getBokBalanceData(@Param("booksetId") String booksetId, @Param("queryDate") Date balanceDate);

    /**
     * 凭证最新账务查询共通
     * @param booksetId
     * @param bookDate
     * @param happenDate
     * @return
     */
    List<SubjectBalance> getVoucherLatestAccountingData(@Param("booksetId") String booksetId,
                                                        @Param("bookDate") Date bookDate,
                                                        @Param("happenDate") Date happenDate,
                                                        @Param("bottomLevel") Long bottomLevel,
                                                        @Param("offsetFlag") String offsetFlag
                                                        );
    /**
     * 根据业务明细科目删除已存在的数据
     * @param list
     * @return
     */
    int deleteBokBussDetsubBatch(@Param("list") List<BokBussDetsub> list);

    /**
     * 根据业务明细科目插入数据
     * @param list
     * @return
     */
    int insertBokBussDetsubBatch(@Param("list") List<BokBussDetsub> list);

    /**
     * 根据账务当前表更改后的数据进行更新增值税流水表数据
     * @param list
     * @return
     */
    int updateTaxBokDetailBatch(@Param("list") List<BokBookdetailCurrentDto> list);

    /**
     * 根据账务当前表更改后的数据进行更新账务历史表数据
     * @param list
     * @return
     */
    int updateTaxBokBookdetailAllBatch(@Param("list") List<BokBookdetailCurrentDto> list);

    /**
     * 根据账务当前表更改后的数据进行更新当前表数据
     * @param list
     * @return
     */
    int updateTaxBokBookdetailCurrentBatch(@Param("list") List<BokBookdetailCurrentDto> list);

    /**
     * 根据账务当前表更改后的数据进行更新增值税流水表数据
     * @param dto
     * @return
     */
    int updateTaxBokDetail(BokBookdetailCurrentDto dto);

    /**
     * 根据账务当前表更改后的数据进行更新账务历史表数据
     * @param dto
     * @return
     */
    int updateTaxBokBookdetailAll(BokBookdetailCurrentDto dto);

    /**
     * 根据账务当前表更改后的数据进行更新当前表数据
     * @param dto
     * @return
     */
    int updateTaxBokBookdetailCurrent(BokBookdetailCurrentDto dto);

}
