package com.joyintech.tams.svreport.pbc.assetdebt.dao;

import java.util.List;

import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcLoanDetailInfoSubReportVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.pbc.assetdebt.dto.PbcReportAssetDebtDetailDto;
import com.joyintech.tams.svreport.pbc.report.dto.PbcLoanDetailInfoSub;

/**
 * 功能:人行报表-资产负债mapper表2.1
 * @since 1.6.0
 * @version 1.6.0
 * @作者：weiyuqiang
 * @创建时间：2019年3月4日
 * @修改记录：
 */
@Repository
public interface PbcReportAssetDebtDetailMapper {
    int deleteByPrimaryKey(String detailuuid);

    int insert(PbcReportAssetDebtDetailDto record);

    int insertSelective(PbcReportAssetDebtDetailDto record);

    PbcReportAssetDebtDetailDto selectByPrimaryKey(String detailuuid);

    int updateByPrimaryKeySelective(PbcReportAssetDebtDetailDto record);

    int updateByPrimaryKey(PbcReportAssetDebtDetailDto record);

    /**
     * 功能:根据主表uuid查询相关资产负债明细表数据
     * @since 1.6.0
     * @version 1.6.0
     * @author：weiyuqiang 2019年3月5日
     * @param reportuuid
     * @return 
     * List<PbcReportAssetDebtDetailDto>
     */
	List<PbcReportAssetDebtDetailDto> selectByReportuuid(String reportuuid);
    
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
    int deleteByReportDate(@Param("reportStartDate")String reportEndDate,@Param("reportEndDate")String reportStartDate,
            @Param("reporttype")String reporttype,@Param("pbcAccountId")String pbcAccountId,@Param("deptCode")String deptCode);

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
     * List<PbcReportAssetDebtDetailDto>
     */
	List<PbcReportAssetDebtDetailDto> selectByReportuuidForExport(String reportuuid);
    
}