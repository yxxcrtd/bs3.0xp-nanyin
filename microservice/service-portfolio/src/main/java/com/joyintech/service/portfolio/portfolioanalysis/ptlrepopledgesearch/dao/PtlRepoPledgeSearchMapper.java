package com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dao;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlRepoPledgeSearchDto;
import com.joyintech.service.portfolio.portfolioanalysis.ptlrepopledgesearch.dto.PtlSubstitutionRepoPledgeDto;
import com.joyintech.tams.common.base.entity.FinPledgeDisplaceDetail;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 债券质押查询Mapper
 *
 * @author liuwei
 * @version 1.0 2018年9月10日
 * @see PtlRepoPledgeSearchMapper
 * @since 1.0
 */
@Repository
public interface PtlRepoPledgeSearchMapper {

    /**
     * 组合代码的联想下拉框事件
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getCounterId(Map<String, Object> map);

    /**
     * 资产代码的联想下拉框事件
     *
     * @param map 查询参数
     * @return 查询结果
     * @see
     * @since 1.0
     */
    List<SelectOptionDto> getFinprodMarketId(Map<String, Object> map);

    /**
     * 查询界面查询事件
     *
     * @param pageParam 页面参数
     * @return 结果
     * @see
     * @since 1.0
     */
    List<PtlRepoPledgeSearchDto> select(Map<String, Object> pageParam);

    /**
     * 画面查询方法->质押券置换查询
     *
     * @param param 画面参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    List<PtlSubstitutionRepoPledgeDto>  substitutionFindPage(Map<String, Object> param);

    /**
     * 画面查询方法->质押券置换明细查询
     *
     * @param param 画面参数
     * @return 查询结果集
     * @throws Exception 异常
     */
    List<FinPledgeDisplaceDetail>  getPledgeDetail(Map<String, Object> param);


}
