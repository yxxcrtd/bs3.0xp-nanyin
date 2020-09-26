
package com.joyintech.tams.report.submitmaintenance.businessdetailed.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.report.submitmaintenance.businessdetailed.dto.EquiteBusiDetails001Dto;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.entity.InterestsBusinessDetailed;
import com.joyintech.tams.report.submitmaintenance.businessdetailed.entity.ProductRegist;


/**
 * 产品净值Mapper
 *
 * @author zyf
 * @version 1.0 2017年9月14日
 * @see IBDMapper
 * @since 1.0
 */
@Repository
public interface IBDMapper {
    
    /**
     * 查询产品登记编码
     * <一句话功能简述>
     * <功能详细描述>
     * @param prodId
     * @return
     * @see
     * @since 1.0
     */
    ProductRegist selectRegCodeByProdId(@Param("prodId")String prodId);
    
    int saveIbdDataBatch(@Param("ibdList")List<InterestsBusinessDetailed> ibdList,
                         @Param("isOpen")String isOpen,@Param("coverList")List<String> coverList);

    int insertBtachInterfEquityBusi(List<InterestsBusinessDetailed> list);

    int updateBatchEquityBusi(List<InterestsBusinessDetailed> list);

    int insertEquityBusiHis(List<InterestsBusinessDetailed> list);
    /**
     * 通过主键查询明细表中已经存在的数据
     * @param list excel导入内容
     * @return 表中存在内容
     * @see
     * @since 1.0
     */
    List<EquiteBusiDetails001Dto> queryBusiDetailList(@Param("list") List<EquiteBusiDetails001Dto> list);
}
