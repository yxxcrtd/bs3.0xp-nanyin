package com.joyintech.tams.model.impairment.batch.eclcalculation.dao;

import com.joyintech.tams.common.base.entity.BokDetailId;
import com.joyintech.tams.common.base.entity.TrdAssetLoss;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.FinDealPledgeDTO;
import com.joyintech.tams.common.base.entity.ModEclResult;
import com.joyintech.tams.model.impairment.batch.eclcalculation.dto.ModLgdConfigDto;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EclCalculationMapper {

    //1.获取理需要处理的每一笔交易数据（计划做在数据加载批处理中）
    List<ModEclResultProcess> selectModEclResultProcessForDeal(Map<String, Object> params);

    int deleteModEclResult(Map<String, Object> params);

    /**
     * 查询特殊资产LGD和一般LGD违约损失率查询信息
     */
    List<ModLgdConfigDto> selectModLgdConfig(Map<String, Object> params);

    /**
     * 查询减值资产管理
     */
    List<TrdAssetLoss> queryEclManage(Map<String, Object> params);

    int insertModEclResult(List<ModEclResult> list);

    /**
     * 回购资产抵押品列表
     *
     * @param finprodId 金融产品代码
     * @return
     */
    List<FinDealPledgeDTO> queryFinDealPledgeByFinprodId(@Param("finprodId") String finprodId);

    /**
     * 查询所有LGD违约损失率信息
     *
     * @param params 查询参数
     * @return
     */
    List<ModLgdConfigDto> selectModLgdConfigAll(Map<String, Object> params);

    String getBokDetailId(@Param("booksetId") String booksetId,
                               @Param("finprodId") String finprodId,
                               @Param("invAim") String invAim);
}
