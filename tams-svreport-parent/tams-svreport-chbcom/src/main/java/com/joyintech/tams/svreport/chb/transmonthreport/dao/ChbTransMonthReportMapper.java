package com.joyintech.tams.svreport.chb.transmonthreport.dao;

import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportSubDetailDto;
import com.joyintech.tams.svreport.chb.transmonthreport.dto.ChbTransMonthReportSubDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 理财产品转型情况月度统计表登记Mapper
 *
 * @author 杜保军
 * @version 1.0 2020年6月4日
 * @since 1.0
 */
@Repository
public interface ChbTransMonthReportMapper {

    /**
     * 根据画面查询条件查询符合条件的记录
     *
     * @param pageParam 查询参数
     * @return 理财产品转型情况月度统计表集合
     */
    List<ChbTransMonthReportSubDto> searchPage(Map<String, Object> pageParam);

    /**
     * 将原表数据插入到履历表中
     *
     * @param registList 需要处理的数据
     * @return 插入条数
     */
    int insertReportHis(@Param("registList") List<ChbTransMonthReportSubDto> registList);

    /**
     * 删除原表数据
     *
     * @param registList 需要处理的数据
     * @return 删除的条数
     */
    int deleteLastSubmitRegist(@Param("registList") List<ChbTransMonthReportSubDto> registList);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     *
     * @param registList 需要处理的数据
     * @see
     * @since 1.0
     */
    void insertReportDataFromHis(@Param("registList") List<ChbTransMonthReportSubDto> registList);

    /**
     * 取消手工处理时将履历表中的数据移回登记表
     *
     * @param registList 需要处理的数据
     * @see
     * @since 1.0
     */
    void deleteReportDataHis(@Param("registList") List<ChbTransMonthReportSubDto> registList);

    /**
     * 批量更新发送状态
     *
     * @param sendStatus 发送状态
     * @param registList 需要更新的数据
     */
    void updateInterfSubmitAndSeqBatch(@Param("sendStatus") String sendStatus, @Param("registList") List<ChbTransMonthReportSubDto> registList);

    /**
     * 取消手工处理，将发送状态改为未报送
     *
     * @param list 需要处理的数据
     * @return 更新条数
     */
    Integer cancelMannultRegist(List<ChbTransMonthReportSubDto> list);

    /**
     * 根据主键查询理财产品转型情况月度统计表数据.
     *
     * @param keyList 主键集合
     * @return 报表数据
     */
    List<ChbTransMonthReportSubDto> searchByKeys(List<String> keyList);

    /**
     * 根据主键查询理财产品转型情况月度统计表数据
     * 
     * @param reportuuid
     * @return
     */
    ChbTransMonthReportSubDto selectMonthReportSubByPrimaryKey(String reportuuid);

    /**
     * 查询理财产品转型情况月度统计表明细数据
     * 
     * @param selectParam 检索参数
     * @return 理财产品转型情况月度统计表明细
     */
    List<ChbTransMonthReportSubDetailDto> selectMonthReportSubDetail(Map<String, Object> selectParam);

    /**
     * 查询所有的余额模板
     * 
     * @return 余额模板
     */
    List<String> selectAllBalanceTemplate();
}