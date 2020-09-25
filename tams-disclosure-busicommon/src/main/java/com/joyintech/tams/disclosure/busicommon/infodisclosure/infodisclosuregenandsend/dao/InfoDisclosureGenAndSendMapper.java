package com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.common.base.entity.RepInstallLog;
import com.joyintech.tams.common.base.entity.RepScreen;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.entity.InfoDisclosureGenAndSendEntity;
import com.joyintech.tams.disclosure.busicommon.infodisclosure.infodisclosuregenandsend.entity.RepProdIndexEntity;


/**
 * 信息披露生成及发送核心批处理mappermapper
 * @author wangmin
 * @version 1.0 2018年11月28日
 * @see InfoDisclosureGenAndSendMapper
 * @since 1.0
 */
@Repository("disclosureInfoDisclosureGenAndSendMapper")
public interface InfoDisclosureGenAndSendMapper {
	/**
	 * 获取所有未执行与未生成状态数据
	 * @return list
	 */
	List<InfoDisclosureGenAndSendEntity> getAllRepInstallFileList();
	/**
     * 
     * 获取指定报表的查询条件
     * <功能详细描述>
     * @param repId
     * @return
     * @see
     * @since 1.0
     */
    List<RepScreen> getRepScreenList(String repId);
    /**
     * 更新批处理组装信息表状态
     * @param infoDisclosureGenAndSendEntity 条件
     * @return
     */
    int updateRepInstallStatus(InfoDisclosureGenAndSendEntity infoDisclosureGenAndSendEntity);
    /**
     * 通过jobSeq更新日志表
     * @param repInstallLog 日志dto
     * @return 
     */
    int updateByJobSeqSelective(RepInstallLog repInstallLog);
    /**
     * 通过jobSeq获取执行状态count
     * @param jobSeq 作业流水号
     * @return
     */
    List<InfoDisclosureGenAndSendEntity> getExecStatusList(String jobSeq);
    /**
     * 
     * 获取所有指定报表的查询条件
     * 
     * @return
     * @see
     * @since 1.0
     */
    List<RepScreen> getAllRepScreenList();
    /**
     * 通过产品代码，结束日期查询最近产品指标表相关信息
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    RepProdIndexEntity getRepPordIndexInfo(@Param("prodId") String prodId, @Param("endDate") Date endDate);
    /**
     * 通过产品代码，结束日期查询估值日当天产品指标表相关信息
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    RepProdIndexEntity getRepPordValueIndexInfo(@Param("prodId") String prodId, @Param("endDate") Date endDate);
    /**
     * 通过jobSeq获取共有多少未执行数据
     * @param jobSeq 作业seq
     * @return
     */
    int getTotalByJobSeq(String jobSeq);
    /**
     * 通过产品代码，结束日期查询估值日当天产品指标表相关信息（到期公告使用,货币型产品使用）
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    RepProdIndexEntity getRepPordValueCurrencyMdateInfo(@Param("prodId") String prodId, @Param("endDate") Date endDate,
    		@Param("actMdate") Date actMdate);
    /**
     * 通过产品代码，结束日期查询估值日当天产品指标表相关信息（到期公告使用,净值型产品使用）
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    RepProdIndexEntity getRepPordValueNetMdateInfo(@Param("prodId") String prodId, @Param("endDate") Date endDate,
    		@Param("actMdate") Date actMdate);
    /**
     * 获取净值型产品截止日的单位净值
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    RepProdIndexEntity getRepPordValueNetByEnddate(@Param("prodId") String prodId, @Param("endDate") Date endDate);
    /**
     * 获取货币型产品截止日的单位净值
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    RepProdIndexEntity getRepPordValueCurrencyByEnddate(@Param("prodId") String prodId, @Param("endDate") Date endDate);
    /**
     * 获取净值型产品截止日的资产情况
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    List<RepProdIndexEntity> selectNetProdAssetPercent(@Param("prodId") String prodId, @Param("endDate") Date endDate);
    /**
     * 获取货币型产品截止日的资产情况
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    List<RepProdIndexEntity> selectCurrencyProdAssetPercent(@Param("prodId") String prodId, @Param("endDate") Date endDate);
    /**
     * 获取净值型产品截止日的前十资产
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    List<RepProdIndexEntity> selectNetProdAssetPercentTen(@Param("prodId") String prodId, @Param("endDate") Date endDate);
    /**
     * 获取货币型产品截止日的前十资产
     * @param prodId 产品代码
     * @param endDate 结束日
     * @return
     */
    List<RepProdIndexEntity> selectCurrencyProdAssetPercentTen(@Param("prodId") String prodId, @Param("endDate") Date endDate);
    /**
     * 获取预期收益性产品收益率
     * @param prodId 产品代码
     * @return
     */
    RepProdIndexEntity selectProfitYield(@Param("prodId") String prodId);
    /**
     * 获取净值型产品单位性质
     * @param prodId 产品代码
     * @return
     */
    RepProdIndexEntity selectNetYield(@Param("prodId") String prodId);
    /**
     * 获取货币型产品七日年化
     * @param prodId 产品代码
     * @return
     */
    RepProdIndexEntity selectCurrencyYield(@Param("prodId") String prodId);
    /**
     * 获取预期收益性产品费用情况
     * @param prodId 产品代码
     * @return
     */
    RepProdIndexEntity selectProfitFee(@Param("prodId") String prodId);
    /**
     * 获取净值型产品费用情况
     * @param prodId 产品代码
     * @return
     */
    RepProdIndexEntity selectNetFee(@Param("prodId") String prodId);
    /**
     * 获取货币型产品费用情况
     * @param prodId 产品代码
     * @return
     */
    RepProdIndexEntity selectCurrencyFee(@Param("prodId") String prodId);
}
