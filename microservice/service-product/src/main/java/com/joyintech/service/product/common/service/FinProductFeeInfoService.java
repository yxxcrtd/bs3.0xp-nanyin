/*
 * 文件名：FinProductFeeInfoService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：张亮
 * 修改时间：2018年10月9日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.common.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.joyintech.framework.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.dao.MstFeeinfoMapper;
import com.joyintech.tams.common.base.entity.FinCashPlan;
import com.joyintech.tams.common.base.entity.MstFeeinfo;
import com.joyintech.tams.common.base.entity.StlCapacctInfo;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dao.FinProductFeeInfoMapper;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品费用信息共通
 * 
 * @author 张亮
 * @version 1.0 2018年10月9日
 * @see FinProductFeeInfoService
 * @since 1.0
 */
@Service("backFinProductFeeInfoService")
public class FinProductFeeInfoService {

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private FinProductFeeInfoMapper finProductFeeInfoMapper;

    /**
     * {@linkplain}
     */
    @Autowired(required = false)
    private MstFeeinfoMapper mstFeeinfoMapper;

    /**
     * 查询费用信息
     * 
     * @param map 参数
     * @return 费用信息下拉框
     */
    public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map) {
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        map.put("relationChannel", DictCodeList.关联范围_通道.getValue());
        map.put("relationOther", DictCodeList.关联范围_其他.getValue());
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动管理费.getValue());
        map.put("feeTypeTrust", DictCodeList.费用品种类型_托管费.getValue());
        //新增投资顾问费 add by chendijun 2019/10/15
        map.put("feeTypeInvest", DictCodeListExt2.费用品种类型_投资顾问费.getValue());
        map.put("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeTypeSale", DictCodeList.费用品种类型_销售手续费.getValue());
        map.put("feeTypeOthSale", DictCodeList.费用品种类型_代销费.getValue());
        map.put("feeTypeThService", DictCodeList.费用品种类型_外包服务费.getValue());
        map.put("fee10",DictCodeList.费用品种类型_通道托管费.getValue());
        map.put("fee11",DictCodeList.费用品种类型_通道管理费.getValue());
        map.put("fee99",DictCodeList.费用品种类型_其他费用.getValue());
        map.put("yesFlag", DictCodeList.是否_是.getValue());
        map.put("yesChannel",DictCodeListExt1.金融产品类型_投管用_通道.getValue());
        map.put("PROFIT_TYPE02", DictCodeList.收益类型_收益型.getValue());
        map.put("BILL_BASE_04", DictCodeList.计费基数_费用品种_存续本金.getValue());
        map.put("CHARGE_TYPE_01", DictCodeList.收费类型_计提式费用.getValue());
        return finProductFeeInfoMapper.queryFeeInfoList(map);
    }

    /**
     * 根据费用代码查询费用基本信息
     * 
     * @param feeId 费用代码
     * @return
     */
    public MstFeeinfo queryMstFeeinfoByFeeId(String feeId) {
        return mstFeeinfoMapper.selectByPrimaryKey(feeId);
    }

    /**
     * 根据金融产品代码查询费用列表信息
     * 
     * @param finprodId 金融产品代码
     * @return 费用列表信息
     */
    public List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId(String finprodId,String finprodTypeTG) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        
     // 一体化对应etliu start
//        map.put("feeTypeTrust", DictCodeListExt2.费用类型_托管费.getValue());
//        map.put("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
//        map.put("feeTypeSale", DictCodeListExt1.一次性费用类型_投管_交易手续费.getValue());
        map.put("feeTypeTrust", DictCodeList.费用品种类型_托管费.getValue());
      //新增投资顾问费 add by chendijun 2019/10/15
        map.put("feeTypeInvest", DictCodeListExt2.费用品种类型_投资顾问费.getValue());
        map.put("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeTypeSale", DictCodeList.费用品种类型_销售手续费.getValue());
        map.put("feeTypeWBFWF", DictCodeList.费用品种类型_外包服务费.getValue());
     // 一体化对应etliu end
        map.put("feeType10",DictCodeList.费用品种类型_通道托管费.getValue());
        map.put("feeType11",DictCodeList.费用品种类型_通道管理费.getValue());
        map.put("feeType99",DictCodeList.费用品种类型_其他费用.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("feeTypeOthSale", DictCodeList.费用品种类型_代销费.getValue());
        map.put("feeTypeThService", DictCodeList.费用品种类型_外包服务费.getValue());
        if(StringUtil.equals(finprodTypeTG,DictCodeListExt1.金融产品类型_投管用_通道.getValue())){
            map.put("finprodType", DictCodeListExt1.金融产品类型_通道.getValue());
            return finProductFeeInfoMapper.queryFeeInfoByFinprodIdForChannel(map);
        }
        return finProductFeeInfoMapper.queryFeeInfoByFinprodId(map);
    }

    /**
     * 根据金融产品代码查询费用列表信息包含浮动管理费
     * 
     * @param finprodId 金融产品代码
     * @return 费用列表信息
     */
    public List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodIdEx(String finprodId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("finprodId", finprodId);
        map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_系列模板.getValue());
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        
     // 一体化对应etliu start
//        map.put("feeTypeTrust", DictCodeListExt2.费用类型_托管费.getValue());
//        map.put("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
//        map.put("feeTypeSale", DictCodeListExt1.一次性费用类型_投管_交易手续费.getValue());
        map.put("feeTypeTrust", DictCodeList.费用品种类型_托管费.getValue());
      //新增投资顾问费 add by chendijun 2019/10/15
        map.put("feeTypeInvest", DictCodeListExt2.费用品种类型_投资顾问费.getValue());
        map.put("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeTypeSale", DictCodeList.费用品种类型_销售手续费.getValue());
        map.put("feeTypeWBFWF", DictCodeList.费用品种类型_外包服务费.getValue());
        map.put("feeTypeFloat", DictCodeList.费用品种类型_浮动管理费.getValue());
     // 一体化对应etliu end
        
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("feeTypeOthSale", DictCodeList.费用品种类型_代销费.getValue());
        map.put("feeTypeThService", DictCodeList.费用品种类型_外包服务费.getValue());
        return finProductFeeInfoMapper.queryFeeInfoByFinprodIdEx(map);
    }

    /**
     * 根据参数查询付费计划
     * 
     * @param map 金融产品代码
     * @return 付费计划信息
     */
    public List<FinCashPlan> queryFeePlanListByFinprodId(Map<String, Object> map) {
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        return finProductFeeInfoMapper.queryFeePlanListByFinprodId(map);
    }
    
    /**
     * 根据组合代码查询费用列表信息
     * 
     * @param map 组合代码
     * @return 费用列表信息
     */
    public List<BussFinProductFeeInfoDto> queryFeeInfoByPtlId(Map<String, Object> map) {
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        
     // 一体化对应etliu start
//        map.put("feeTypeTrust", DictCodeListExt2.费用类型_托管费.getValue());
//        map.put("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
//        map.put("feeTypeSale", DictCodeListExt1.一次性费用类型_投管_交易手续费.getValue());
        map.put("feeTypeTrust", DictCodeList.费用品种类型_托管费.getValue());
        //新增投资顾问费 add by chendijun 2019/10/15
        map.put("feeTypeInvest", DictCodeListExt2.费用品种类型_投资顾问费.getValue());
        map.put("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeTypeSale", DictCodeList.费用品种类型_销售手续费.getValue());
        map.put("feeTypeWBFWF", DictCodeList.费用品种类型_外包服务费.getValue());
     // 一体化对应etliu end
        
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("feeTypeOthSale", DictCodeList.费用品种类型_代销费.getValue());
        map.put("feeTypeThService", DictCodeList.费用品种类型_外包服务费.getValue());
        return finProductFeeInfoMapper.queryFeeInfoByPtlId(map);
    }
    
    /**
     * 查询资金账户费用信息
     * 
     * @param map 参数
     * @return 费用信息下拉框
     */
    public List<SelectOptionDto> queryFundFeeInfoList(Map<String, Object> map) {
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        String initvalue = (String) map.get("initValue");
        if(StringUtil.isNotEmpty(initvalue)){
            map.put("initValue",StringUtil.splitToList(",",initvalue));
        }
        return finProductFeeInfoMapper.queryFundFeeInfoList(map);
    }
    /**
     * 获取资金账号信息
     * @param acctNo 资金账号
     * @return 资金账号信息
     * @see
     * @since 1.0
     */
    public StlCapacctInfo getCctInfo(String acctNo){
        String[] list = acctNo.split("_");
        acctNo = list[0];
        String ccy = list[1];
        return finProductFeeInfoMapper.getCctInfo(acctNo,ccy);
    }
    
    public BussFinProductFeeInfoDto queryMstFeeinfoForRateAdjByFeeId(Map<String, Object> map){
        return finProductFeeInfoMapper.queryMstFeeinfoForRateAdjByFeeId(map);
    }
    
}
