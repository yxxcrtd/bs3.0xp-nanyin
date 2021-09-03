package com.joyintech.tams.model.impairment.batch.pdcalculate.dao;

import com.joyintech.tams.model.impairment.batch.pdcalculate.dto.*;
import com.joyintech.tams.common.base.entity.ModEclResultProcess;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 减值-PD计算批处理DAO
 */
@Repository
public interface PdCalculateMapper {

    /**
     * @Description:根据跑批日删除PD计算过的数据
     * @auther: yuzj
     * @date: 2021/7/14
     * @param:
     * @return:
     */
    void deletePdCalculateResult(@Param("plateDate") Date  plateDate,@Param("finprodIdList") List<String> finprodIdList);


    /**
     * @Description:根据跑批日获取待处理资产信息List
     * @auther: yuzj
     * @date: 2021/7/14
     * @param:
     * @return:
     */
    List<ModEclResultProcess> selectWaitPdCalculateList(Map<String, Object> params);

    /**
     * @Description:获取前瞻因子权重配置信息
     * @auther: yuzj
     * @date: 2021/7/15
     * @param:
     * @return:
     */
    ModPdWeightConfigDto selectModPdWeightConfigInfo();


    /**
     * @Description:获取年份的评级PD配置列表
     * @auther: yuzj
     * @date: 2021/7/16
     * @param:
     * @return:
     */
    ModGradePdConfigDto selectModGradePdConfigList(Map<String,Object> params);

    /**
     * @Description:获取年份的前瞻因子
     * @auther: yuzj
     * @date: 2021/7/16
     * @param:
     * @return:
     */
    ModPdFactorConfigDto selectModPdFactorConfig(@Param("yearStr") String yearStr,@Param("quarterStr") String quarterStr);

    /**
     * @Description:用于判断使用的参数是否已存在减值配置使用情况表
     * @auther: yuzj
     * @date: 2021/7/17
     * @param:
     * @return:
     */
    int selectCountConfigUseAge(@Param("configSeqNo") String configSeqNo,@Param("configType") String configType);

    /**
     * @Description:批量插入资产定价表
     * @auther: yuzj
     * @date: 2021/7/19
     * @param:
     * @return:
     */
    int insertConfigUseageResultPremium(@Param("configUseageResultList")List<ModConfigUseageResultDto> configUseageResultList);

    /**
     * @Description:获取年份的综合系数
     * @auther: weipeng
     * @date: 2021/08/30
     * @param:
     * @return:
     */
    ModPdFactorConfigDto getCombinedFactorConfig(@Param("yearStr") String yearStr);

}
