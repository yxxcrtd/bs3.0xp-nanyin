package com.joyintech.tams.svreport.chbreport.investreport.chbinvestoperation.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.tams.svreport.chbreport.investreport.chbinvestoperation.dto.ChbInvestPrdInfo;
import com.joyintech.tams.svreport.chbreport.investreport.chbinvestoperation.dto.ChbInvsetAssetInfo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @ClassName ChbInvestOperationMapper
 * @Description: 非标投资运行报告
 * @Author yanya
 * @Date 2021-04-25
 * @Version V1.0
 **/
@Repository
public interface ChbInvestOperationMapper {

    /**
     * 查询产品下拉框
     * @param param
     * @return
     */
    List<SelectOptionDto> queryPrdInfoList(Map<String, Object> param);

    /**
     * 查询投资运行报告数据
     * @param pageParam
     * @return
     */
    List<ChbInvestPrdInfo> qryInvestOperation(Map<String, Object> pageParam);

    /**
     * 查询投资运行报告中产品相关信息
     * @param pageParam
     * @return
     */
    List<ChbInvestPrdInfo> qryChbInvestPrdInfo(Map<String, Object> pageParam);

    /**
     * 查询投资运行报告中非标资产的相关信息
     * @param pageParam
     * @return
     */
    List<ChbInvsetAssetInfo> qryChbInvsetAssetInfo(Map<String, Object> pageParam);


}
