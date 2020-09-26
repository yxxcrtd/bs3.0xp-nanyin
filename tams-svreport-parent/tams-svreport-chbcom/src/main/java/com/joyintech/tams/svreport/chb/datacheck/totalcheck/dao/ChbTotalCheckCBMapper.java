package com.joyintech.tams.svreport.chb.datacheck.totalcheck.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.joyintech.tams.svreport.chb.datacheck.totalcheck.dto.ChbTotalCheckDetailDto;

/**
 * 功能:中债总体核查批处理mapper
 * @since 1.7.0
 * @version 1.7.0
 * @作者：weiyuqiang
 * @创建时间：2019年4月19日
 * @修改记录：
 */
@Repository
public interface ChbTotalCheckCBMapper {

    /**
     * 功能:根据查询日期、募集币种查询存续产品只数（包括人民币、非人民币）
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param queryDate 查询日期
     * @param ccy 币种
     * @param openType 开放类型，01开放式，02封闭式
     * @return 
     * int 存续产品只数
     */
    public BigDecimal selectProdSubstainsCount(@Param("queryDate")Date queryDate,@Param("ccy")String ccy,
            @Param("openType")String openType,@Param("prodIgnoreList")List<String> prodIgnoreList);

    /**
     * 功能:根据查询日期、募集币种理财产品存续金额(万元)（包括人民币、非人民币）
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param queryDate 查询日期
     * @return 
     * List<Map<String,Object>> 
     */
    public List<Map<String,Object>> selectProdSubstainsAmt(@Param("queryDate")Date queryDate);

    /**
     * 功能:根据查询日期、募集币种理财产品存续金额(万元)（包括人民币、非人民币）从头寸取值
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param queryDate 查询日期
     * @return 
     * List<Map<String,Object>> 
     */
    public List<Map<String,Object>> selectProdSubstainsAmtFromPosition(@Param("queryDate")Date queryDate);
    
    /**
     * 功能:根据查询产品持仓
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @param ccy 币种
     * @param openType 开放类型，01开放式，02封闭式
     * @param isChlBtm 是否穿透，01穿透，02不穿透
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectAssetPosition(@Param("queryDate")Date queryDate,
            @Param("openType")String openType ,@Param("isChlBtm")String isChlBtm,@Param("prodIgnoreList")List<String> prodIgnoreList);

    /**
     * 功能:根据查询日期查询产品募集总量登记明细
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectPrdRaiseTotal(@Param("queryDate")Date queryDate,@Param("prodIgnoreList")List<String> prodIgnoreList);

    /**
     * 功能:根据查询日期查询月末底层持仓报送情况
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectMonthBotAssetHold(@Param("queryDate")Date queryDate,@Param("monthEndDate")Date monthEndDate);

    /**
     * 功能:根据查询日期查询资产持仓为负的产品
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectAssetHoldLoad(@Param("queryDate")Date queryDate,@Param("prodIgnoreList")List<String> prodIgnoreList);

    /**
     * 功能:根据查询日期查询现金持仓为负的产品
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectCashHoldLoad(@Param("queryDate")Date queryDate,@Param("prodIgnoreList")List<String> prodIgnoreList);

    /**
     * 功能:根据查询日期查询现金持仓为负的产品
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectTradePrice(@Param("queryDate")Date queryDate,@Param("prodIgnoreList")List<String> prodIgnoreList);

    /**
     * 功能:根据查询日期查询现金持仓为负的产品
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectAstBotAmt(@Param("queryDate")Date queryDate,@Param("monthEndDate")Date monthEndDate);

    /**
     * 功能:根据查询日期查询产品存续期交易
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月19日
     * @param queryDate 查询日期
     * @return 
     * List<Map<String,Object>> 
     */
    public List<Map<String,String>> selectPrdDealAmt(@Param("queryDate")Date queryDate);

    /**
     * 功能:根据查询产品募集交易
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @param openType 开放类型，01开放式，02封闭式
     * @param isChlBtm 是否穿透，01穿透，02不穿透
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectPrdRaiseDeal(@Param("queryDate")Date queryDate,
            @Param("openType")String openType ,@Param("isChlBtm")String isChlBtm,@Param("prodIgnoreList")List<String> prodIgnoreList);
    
    /**
     * 功能:根据查询产品存续期交易
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @param openType 开放类型，01开放式，02封闭式
     * @param isChlBtm 是否穿透，01穿透，02不穿透
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectPrdDurDeal(@Param("queryDate")Date queryDate,
            @Param("openType")String openType ,@Param("isChlBtm")String isChlBtm,@Param("prodIgnoreList")List<String> prodIgnoreList);

    /**
     * 功能:根据查询产品资产交易
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param queryDate 查询日期
     * @param openType 开放类型，01开放式，02封闭式
     * @param isChlBtm 是否穿透，01穿透，02不穿透
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectPrdAssetDeal(@Param("queryDate")Date queryDate,
            @Param("openType")String openType ,@Param("isChlBtm")String isChlBtm,@Param("prodIgnoreList")List<String> prodIgnoreList);

    /**
     * 功能:根据查询日期查询产品初始持仓
     * @since 1.7.0
     * @version 1.7.0
     * @author：weiyuqiang 2019年4月22日
     * @param openType 开放类型，01开放式，02封闭式
     * @return 
     * List<ChbTotalCheckDetailDto>
     */
    public List<ChbTotalCheckDetailDto> selectPrdInitAmt(@Param("queryDate")Date queryDate,@Param("openType")String openType ,
            @Param("prodIgnoreList")List<String> prodIgnoreList);

}
