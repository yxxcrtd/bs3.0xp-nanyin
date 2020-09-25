/**
 * 
 */
package com.joyintech.service.product.productduration.establish.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.systemtools.service.SystemToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.tams.common.base.entity.PaTrdFinProductDealTotal;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dto.BussFinProductFeeInfoDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.productduration.establish.dao.FinProductEstablishMapper;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishBranchDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishLayerDto;
import com.joyintech.service.product.productduration.establish.dto.FinProductEstablishShowDto;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.constant.DictCode;

/**
 * @author zhangliang
 *
 */
@Service("backFinProductEstablishService")
public class FinProductEstablishService extends BaseService<FinProductEstablishDto>{

    /**
     * {@linkplain}
     */
    @Autowired
    private FinProductEstablishMapper finProductEstablishMapper;
    
    /**
     * {@linkplain}
     */
    @Autowired
    private IPlatformDate iPlatformDate;

    @Autowired
    private SystemToolService systemToolService;

    /**
     * 产品成立信息查询
     * 
     * @param page 分页
     * @param length 展示个数
     * @param map 参数
     * @return 成立信息
     * @throws Exception 异常信息
     */
    public Page<FinProductEstablishDto> productEstablishInfoQuery (int page, int length, Map<String, Object> map) throws Exception{
        map.put("statusValidA", DictCodeListExt1.有效状态_流程中.getValue());
        map.put("statusValidE", DictCodeListExt1.有效状态_有效.getValue());
        map.put("statusValidN", DictCodeListExt1.有效状态_撤销.getValue());
        map.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("dealMode01", DictCodeList.处理模式_普通.getValue());
        map.put("dealMode11", DictCodeList.处理模式_分档.getValue());
        map.put("dealMode10", DictCodeList.处理模式_周期型.getValue());
        map.put("dealMode12", DictCodeList.处理模式_分档周期型.getValue());
        map.put("dealStatusInit", DictCodeListExt1.交易状态_初始.getValue());
        map.put("yesNoNo", DictCodeList.是否_否.getValue());
        map.put("businessTypeEstablish", DictCodeListExt1.平安业务类型_产品成立.getValue());
        map.put("yes", DictCodeList.是否_是.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        Page<FinProductEstablishDto> dto = super.queryForPage(page, length, () -> finProductEstablishMapper.productEstablishInfoQuery(map));
        List<FinProductEstablishDto> ldto = dto.getRoot();
        for (FinProductEstablishDto mdto : ldto) {
        	//销售部门
        List<String> slist1 = new ArrayList<String>();
        List<String> saleDepartmentList = StringUtil.splitToList(GlobalConstant.COMMA,
                mdto.getSaleDepartment());
        Map<String, String> valueMap = systemToolService.getDataDictItems(DictCode.销售部门);
        if (valueMap != null) {
            for (int i = 0; i < saleDepartmentList.size(); i++) {
                if (StringUtil.isNotEmpty(saleDepartmentList.get(i))) {
                    String str = valueMap.get(saleDepartmentList.get(i));
                    if (str != null) {
                        slist1.add(str);
                    }
                }
            }
        }
        String saleDepartment = StringUtil.joinString(slist1, GlobalConstant.COMMA);
        mdto.setSaleDepartment(saleDepartment);
        }
        return dto;
    }
    
    /**
     * 根据金融产品代码查询费用列表
     * @param map 金融产品代码
     * @return 费用列表
     */
    public List<BussFinProductFeeInfoDto> queryFeeInfoByFinprodId(Map<String, Object> map){
        map.put("cashType1", DictCodeListExt1.现金流一级类型_费用.getValue());
        map.put("finprodType", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        map.put("feeTypeTrust", DictCodeList.费用品种类型_托管费.getValue());
        map.put("feeTypeFix", DictCodeList.费用品种类型_固定管理费.getValue());
        map.put("feeTypeSale", DictCodeList.费用品种类型_销售手续费.getValue());
        map.put("platDate", iPlatformDate.getSysDate());
        return finProductEstablishMapper.queryFeeInfoByFinprodId(map);
    }
    
    /**
     * 根据金融产品代码查询分层金额明细
     * @param map 参数
     * @return 分层金额明细
     */
    public List<FinProductEstablishLayerDto> queryLayAmtDetailByFinprodId(Map<String, Object> map){
        return finProductEstablishMapper.queryLayAmtDetailByFinprodId(map);
    }
    
    /**
     * 根据金融产品代码查询分层金额明细
     * @param bussId 业务编号
     * @return 分层金额明细
     */
    public List<FinProductEstablishLayerDto> queryLayAmtDetailByBussId(String bussId){
        return finProductEstablishMapper.queryLayAmtDetailByBussId(bussId);
    }
    
    /**
     * 根据金融产品代码查询募集金额明细
     * @param map 参数
     * @return 募集金额明细
     */
    public PaTrdFinProductDealTotal queryRaiseAmtInfoByFinprodId(Map<String, Object> map){
        map.put("businessTypeEstablish", DictCodeListExt1.平安业务类型_产品成立.getValue());
        return finProductEstablishMapper.queryRaiseAmtInfoByFinprodId(map);
    }
    
    /**
     * 根据金融产品代码查询分行募集明细
     * @param map 参数
     * @return 分行募集明细
     */
    public List<FinProductEstablishBranchDto> queryBranchRaiseInfoByFinprodId(Map<String, Object> map){
        return finProductEstablishMapper.queryBranchRaiseInfoByFinprodId(map);
    }
    
    /**
     * 根据金融产品代码查询分行募集明细
     * @param bussId 交易编号
     * @return 分行募集明细
     */
    public List<FinProductEstablishBranchDto> queryBranchRaiseInfoByBussId(String bussId){
        return finProductEstablishMapper.queryBranchRaiseInfoByBussId(bussId);
    }
    
    /**
     * 查询分行信息
     * @param map 参数
     * @return 分行信息
     */
    public List<SelectOptionDto> queryBranchInfoIdList(Map<String, Object> map){
        map.put("counterType", DictCodeListExt1.交易对手类型_分行.getValue());
        return finProductEstablishMapper.queryBranchInfoIdList(map);
    }
    
    /**
     * 查询分行信息
     * @param map 参数
     * @return 分行信息
     */
    public List<SelectOptionDto> queryBranchInfoNameList(Map<String, Object> map){
        map.put("counterType", DictCodeListExt1.交易对手类型_分行.getValue());
        return finProductEstablishMapper.queryBranchInfoNameList(map);
    }
    
    /** 删除交易类金融产品
     * @param map 参数
     * @return DB
     */
    public int deleteFinTradeProductByFinprodId(Map<String, Object> map){
        return finProductEstablishMapper.deleteFinTradeProductByFinprodId(map);
    }
    
    /** 删除现金流信息
     * @param map 参数
     * @return DB
     */
    public int deleteFinCashInfoByFinprodId(Map<String, Object> map){
        return finProductEstablishMapper.deleteFinCashInfoByFinprodId(map);
    }
    
    /** 删除现金流计算规则信息
     * @param map 参数
     * @return DB
     */
    public int deleteFinCashCalcRuleByFinprodId(Map<String, Object> map){
        return finProductEstablishMapper.deleteFinCashCalcRuleByFinprodId(map);
    }
    
    /**
     * 产品代码联想下拉框
     * 
     * @param param 参数map
     * @return 产品信息
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> queryProdIdInfoIssueSuc(int page,int length,Map<String, Object> param) {
        param.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        param.put("ISSUE_STATUS_01", DictCodeListExt1.发行状态_发行成功.getValue());
        param.put("ISSUE_STATUS_03", DictCodeListExt1.发行状态_确认发行.getValue());

        Page<SelectOptionDto> resultList = this.queryForPageForSelectReturn(page, length,
                () -> finProductEstablishMapper.queryProdIdInfoIssueSuc(param));

        return resultList;
    }
    
    /**
     * 根据金融产品代码查询产品信息
     * @param map 参数
     * @return 产品信息
     */
    public FinProductCommonInfoDto queryProdInfoByFinprodId(Map<String, Object> map){
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        return finProductEstablishMapper.queryProdInfoByFinprodId(map);
    }
    
    /**
     * 根据金融产品代码查询交易汇总表 产品信息
     * @param map
     * @return
     */
    public FinProductEstablishShowDto selectProdInfoByFinprodId(Map<String, Object> map){
    	map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("statusValidA", DictCodeListExt1.有效状态_流程中.getValue());
        return finProductEstablishMapper.selectProdInfoByFinprodId(map);
    }
    
    public Integer selectIsBranch(Map<String, Object> map){
    	return finProductEstablishMapper.selectIsBranch(map);
    }
    
    public Integer selectIsTrade(Map<String, Object> map){
    	return finProductEstablishMapper.selectIsTrade(map);
    }
    /**
     * add by dingjunfeng 2019-3-29
     * @param map
     * @return
     */
    public Integer selectDealAdd(Map<String, Object> map){
    	return finProductEstablishMapper.selectDealAdd(map);
    }
    /**
     * add by wm 2019-07-24
     * @param map
     * @return
     */
    public Integer selectOtherSurDeal(Map<String, Object> map){
    	map.put("ps01",DictCodeList.交易方向_产品认购.getValue());
    	map.put("yesOrNoN",DictCodeList.是否_否.getValue());
    	map.put("tradeStatus01",DictCodeListExt1.交易状态_初始.getValue());
    	return finProductEstablishMapper.selectOtherSurDeal(map);
    }
}
