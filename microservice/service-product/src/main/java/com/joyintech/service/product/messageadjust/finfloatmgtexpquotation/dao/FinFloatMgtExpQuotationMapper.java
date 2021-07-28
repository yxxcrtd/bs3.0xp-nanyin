package com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dao;


import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.common.base.entity.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dto.IntRateWeight;
import com.joyintech.service.product.messageadjust.bmquotation.dto.BussBenchMarkInfoDto;
import com.joyintech.service.product.messageadjust.finfloatmgtexpquotation.dto.FinFloatMgtExpQuotationDto;

/**
 * 浮动管理费报价查询微服务端mapper
 * @author wangkang
 *
 */
@Repository
public interface FinFloatMgtExpQuotationMapper {
    /**
    * 产品代码联想下拉框
    * @param pageParam Map<String, Object>
    * @return List<SelectOptionDto>
    * @see
    * @since 1.0
    */
   List<SelectOptionDto> getFinprodId(Map<String, Object> pageParam);
   /**
    * 浮动管理费报价查询
    * @param pageParam Map<String, Object>
    * @return List<FinFloatMgtExpQuotationDto>
    * @see
    * @since 1.0
    */
   List<FinFloatMgtExpQuotationDto> selectForPage(Map<String, Object> pageParam); 
   /**
    * 产品代码时联动费用名称等
    * @param pageParam
    * @return
    * @see
    * @since 1.0
    */
   FinFloatMgtExpQuotationDto getFeeinfos(Map<String, Object> param);
   /**
    * 费用名称联想框
    * <功能详细描述>
    * @return
    * @see
    * @since 1.0
    */
   List<SelectOptionDto> getFeeInfo(Map<String, Object> param);
   /**
    * 
    * 产品代码，生效日联动利差等
    * <功能详细描述>
    * @param param
    * @return
    * @see
    * @since 1.0
    */
   List<FinFloatMgtExpQuotationDto> getRuleDetail(Map<String,Object> param);
   /**
    * 基准来源 = 利率行情时
    * 从利率行情表中取得基准利率
    * @param string 
    * @return
    * @see
    * @since 1.0
    */
   BigDecimal getIntRate(String string);
   /**
    * 基准类型 = 复合基准时
    * 查询出该基准代码所对应的所有行情信息
    * @return
    * @see
    * @since 1.0
    */
   List<IntRateWeight> getIntRateWeight(Map<String, Object> param);
   /**
    * 根据产品代码生效日得到基准编号 
    * <功能详细描述>
    * @param param
    * @return
    * @see
    * @since 1.0
    */
   List<FinFloatMgtExpQuotationDto> getIndexId(Map<String, Object> param);
   
   /**
    * 根据生效日判断浮动管理费计提数量
    * <功能详细描述>
    * @param param
    * @return
    * @see
    * @since 1.0
    */
   int getFloatFeeCount(Map<String,Object> param);
   /**
    * 查看，修改时的数据
    * <功能详细描述>
    * @param param
    * @return
    * @see
    * @since 1.0
    */
   FinFloatMgtExpQuotationDto selectDetail(Map<String,Object> param);
   /**
    * 根据产品代码得到起息日和到期日
    * <功能详细描述>
    * @param finprodId
    * @return
    * @see
    * @since 1.0
    */
   FinFloatMgtExpQuotationDto getVdateAndMdate(@Param("finprodId")String finprodId);
   /**
    * 根据生效日和现金流代码判断有无明细数据
    * <功能详细描述>
    * @param finFloatCalcRuleDetailKey
    * @return
    * @see
    * @since 1.0
    */
   int selectByPrimaryKey(FinFloatCalcRuleDetailKey key);
   
   /**
    * 
    * 查询现金流信息是否已存在
    * <功能详细描述>
    * @param map Map<String, Object>
    * @return FinFloatMgtExpQuotationDto
    * @see
    * @since 1.0
    */
   FinFloatMgtExpQuotationDto selectAlreadHave(Map<String, Object> map);
   
   /**
    * 
    * 删除浮动管理费
    * <功能详细描述>
    * @param key FinFloatCalcRuleDetailKey
    * @return int
    * @see
    * @since 1.0
    */
   int deleteByPrimaryKey(FinFloatCalcRuleDetailKey key);
   /**
    * 查询是否是存续期产品
    * @param pageParam
    * @return
    */
   FinFloatMgtExpQuotationDto queryProductByProdId(Map<String, Object> pageParam);
   /**
    * 根据产品代码查找开放规则
    * @param param
    * @return
    */
   FinOpenRule queryOpenRuleByFinprodId(Map<String, Object> param);
   
   FinFloatMgtExpQuotationDto queryProductByProdIdAndProdStyle(Map<String, Object> param);
   
   /**
    * 查看，修改时的业绩基准数据
    * <功能详细描述>
    * @param param
    * @return
    * @see
    * @since 1.0
    */
   BussBenchMarkInfoDto selectBussBenchMarkInfo(Map<String,Object> param);
   /**
    * 删除浮动管理费的业绩基准信息
    * @param param 参数
    * @return
    */
   int deleteBussBenchMarkInfo(Map<String,Object> param);
   /**
    * 根据现金流id和生效日查询出明细列表
    * @param key 主键
    * @return
    */
   List<FinFloatCalcRuleDetail> queryFloatDetailByIdAndDate(FinFloatCalcRuleDetailKey key);
   /**
    * 根据现金流id和生效日删除
    * @param key 主键
    * @return
    */
   int deleteFloatDetailByIdAndDate(FinFloatCalcRuleDetailKey key);
   
   /**
    * 根据现金流id删除
    * @param cashId 现金流Id
    * @return
    */
   int deleteFloatScheByCashId(@Param("cashId")String cashId);   
   /**
    * 通过现金流代码获取计提计划
    * @param cashId 现金流Id
    * @return 计提计划列表
    */
   List<FinFloatAccrualSche> queryFloatScheByCashId(@Param("cashId")String cashId, @Param("effDate")Date effDate); 
   /**
    * 根据现金流id删除
    * @param cashId 现金流Id
    * @return
    */
   int deleteCashPlanByCashId(@Param("cashId")String cashId);

   int deleteFloatAccrByPrimaryKey(FinFloatAccrualScheKey key);
   
   //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 start
   int checkeffDate(Map<String,Object> param);
   //ZGBSS-42 非首次新建浮动管理费报价时 生效日选择开放计划开放开始日+1天作为生效日 add by yujiaping 20210118 end
   
   String getopenMode(Map<String,Object> param);
   String getopenType(Map<String,Object> param);
   String getisInitial(Map<String,Object> param);
}