package com.joyintech.tams.svreport.pbc.assetdebtdat.dao;

import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcLoanDetailInfoSubReportVo;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportAssetDebtDatDetailDto;
import com.joyintech.tams.svreport.pbc.assetdebtdat.dto.PbcReportProdDatDto;
import com.joyintech.tams.svreport.pbc.report.dto.PbcLoanDetailInfoSub;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 功能:人行报表-资产负债mapper表2.1
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Repository
public interface PbcReportAssetDebtDatDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportAssetDebtDatDetailDto record);

    int insertSelective(PbcReportAssetDebtDatDetailDto record);

    PbcReportAssetDebtDatDetailDto selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportAssetDebtDatDetailDto record);

    int updateByPrimaryKey(PbcReportAssetDebtDatDetailDto record);

    /**
     * 功能:根据主表uuid查询相关资产负债明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月5日
     * @param reportuuid
     * @return
     * List<PbcReportAssetDebtNewDetailDto>
     */
	List<PbcReportAssetDebtDatDetailDto> selectByReportuuid(String reportuuid);

    /**
     * 功能:根据报表类型，报表日期删除明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月12日
     * @param reportDate
     * @param reporttype
     * @return
     * int
     */
    int deleteByReportDate(@Param("reportStartDate") String reportEndDate, @Param("reportEndDate") String reportStartDate,
                           @Param("reporttype") String reporttype, @Param("pbcAccountId") String pbcAccountId, @Param("deptCode") String deptCode);

    int deleteByReportUUid(@Param("reportUuid") String reportUuid);

    int insertDataBySelect(@Param("reportUuid") String reportUuid);

    /**
     * 查询贷款导出详情
     *
     * @param reportuuid
     * @return
     */
    List<PbcLoanDetailInfoSubReportVo> selectDetailInfoSubByReportUuid(String reportuuid);


    /**
     * 查询资金信托份额导出详情
     * @param reportuuid
     * @return
     */
    List<PbcLoanDetailInfoSubReportVo> selectCapitalTrustDetailByReportUuid(String reportuuid);

    /**
     * 根据主表主键查询贷款明细基本信息
     * @param reportuuid
     * @return
     */
    List<PbcLoanDetailInfoSub> selectDKByReportuuid(String reportuuid);

    /**
     * 根据主表主键查询资金信托基本信息
     * @param reportuuid
     * @return
     */
    List<PbcLoanDetailInfoSub> selectXTByReportuuid(String reportuuid);


    /**
     * 功能:导出人行excel相关资产负债明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月5日
     * @param reportuuid
     * @return
     * List<PbcReportAssetDebtNewDetailDto>
     */
	List<PbcReportAssetDebtDatDetailDto> selectByReportuuidForExport(String reportuuid);

    List<PbcReportAssetDebtDatDetailDto> selectDatDiffByReport(@Param("reportuuid") String reportuuid, @Param("reporttype") String reporttype);

    int deleteByUUIDAndType(Map<String,Object> paramsMap);

    List<PbcReportAssetDebtDatDetailDto> selectByDatZipCode(@Param("datZipCode") String datZipCode, @Param("datZipDatType") String datZipDatType);
}