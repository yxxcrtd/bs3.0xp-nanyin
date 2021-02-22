/*
 * 文件名：AssetCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.asset.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.CreditAssetpoolDetailMapper;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.FinProductSppiInfoMapper;
import com.joyintech.tams.common.base.entity.AssetTypeConfig;
import com.joyintech.tams.common.base.entity.CreditAssetpoolDetail;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.FinCashCalcRule;
import com.joyintech.tams.common.base.entity.FinCashPayRule;
import com.joyintech.tams.common.base.entity.FinExercisePlan;
import com.joyintech.tams.common.base.entity.FinOpenPlan;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductSppiInfo;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.asset.dao.AssetCommon002Mapper;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockAssetInfoDto;
import com.joyintech.tams.invest.common.dto.mock.asset.MockFinCashPlanDto;
import com.joyintech.tams.invest.common.dto.mock.basicdata.MockQuotationInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资产端共通
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月25日
 * @see BaseCommonAssetCommonService
 * @since 1.0
 */
@Service
public class BaseCommonAssetCommonService extends BaseService {
  //授权额度管理
    @Autowired(required=false)
    private CreditLinesMapper creditLinesMapper;
    //资产系列明细
    @Autowired(required=false)
    private CreditAssetpoolDetailMapper creditAssetpoolDetailMapper;

    @Autowired
    private AssetCommon002Mapper assetCommonMapper;
    
    @Autowired
    private FinProductSppiInfoMapper finProductSppiInfoMapper;

    @Autowired
    private PriceinfoService priceinfoService;
    /**
     * 资产模糊下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getAssetId(Map<String, Object> param){
        param.put("FINPROD_TYPE_TG_ZQ", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        param.put("FINPROD_TYPE_TG_JJ", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
        //增加母资管计划筛选条件
        if (StringUtil.isNotEmpty(param.get("finprodType2"))) {
            if(StringUtil.equals(param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
                param.put("finprodType2", StringUtil.splitToList(null, param.get("finprodType2").toString()+","+ DictCodeListExt1.金融产品类型_投管用_母资管计划.getValue()));
            }else{
                param.put("finprodType2", StringUtil.splitToList(null, param.get("finprodType2").toString()));
            }
        }else{
            param.remove("finprodType2");
        }
        return assetCommonMapper.getAssetId(param);
    }
    
    /**
     * 获取资产联想下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(readOnly = true)
    public Page<SelectOptionDto> getAssetIdOption(int page, int length, @RequestBody Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,() ->assetCommonMapper.getAssetIdOption(param));
    }
    
    /**
     * 交易日历联想下拉框
     * 
     * @param param 参数map
     * @return 交易日历
     * @see
     * @since 1.0
     */
    @Transactional(readOnly = true)
    public List<SelectOptionDto> queryCalendarInfoList(@RequestBody Map<String, Object> param) {
        return assetCommonMapper.queryCalendarInfoList(param);
    }
    
    /**
     * 资产提供行下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(readOnly = true)
    public List<SelectOptionDto> getTradeOpponent(@RequestBody Map<String, Object> param) {
        return assetCommonMapper.getTradeOpponent(param);
    }
    
    /**
     * 客户信息下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(readOnly = true)
    public List<SelectOptionDto> getCustomerName(@RequestBody Map<String, Object> param) {
        return assetCommonMapper.getCustomerName(param);
    }
    
    /**
     * 客户信息下拉框
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @Transactional(readOnly = true)
    public List<SelectOptionDto> getMarketInstitutionCustomerName(@RequestBody Map<String, Object> param) {
        if (param == null) {
            param = new HashMap<String, Object>();
        }
        param.put("CUSTOMER_TYPE", DictCodeList.客户类型_法人.getValue());
        param.put("IS_DEPOSIT_BANK", DictCodeList.是否_是.getValue());
        return assetCommonMapper.getCustomerName(param);
    }
	
	@SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getMarketInstitutionCustomerName1(int page, int length, Map<String, Object> param) {
        param.put("CUSTOMER_TYPE", DictCodeList.客户类型_法人.getValue());
        param.put("IS_DEPOSIT_BANK", DictCodeList.是否_是.getValue());
        return this.queryForPageForSelectReturn(page, length, ()->assetCommonMapper.getCustomerName(param));
    }
    
    @Transactional(readOnly = true)
    public AssetCommonInfoDto getAssetInfoToRateAdj(@RequestBody Map<String, Object> param){
        return assetCommonMapper.getAssetInfoToRateAdj(param);
    }
    
    public List<FinProduct> selectAllAsset(){
        return assetCommonMapper.selectAllAsset();
    }

    /**
     * <一句话功能简述>
     * <功能详细描述>
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getPflshList(int page, int length, Map<String, Object> param) {
        param.put("ZCSX", "0002");
        param.put("XLSX", "0003");
        return this.queryForPageForSelectReturn(page, length, ()->assetCommonMapper.getPflshList(param));
    }

    /**
     * 查询实际授信主体
     * <功能详细描述>
     * @param page
     * @param length
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getActualCreditProvid(int page, int length, Map<String, Object> param) {
        param.put("CUSTOMER_TYPE_01", DictCodeList.客户类型_法人.getValue());
        return this.queryForPageForSelectReturn(page, length, ()->assetCommonMapper.getActualCreditProvid(param));
    }

    /**
     * 增加授信关联
     * <功能详细描述>
     * @param entity
     * @see
     * @since 1.0
     */
    public void insertCredit(CreditLines entity) {
      //增加授信关联
        if(StringUtil.equals(entity.getCreditMstId(), "0002")){
            creditLinesMapper.updateByPrimaryKeySelective(entity);
        }
        else{
            CreditAssetpoolDetail creditAssetpoolDetail = new CreditAssetpoolDetail();
            String seqno = assetCommonMapper.getCreditAssetpoolSeqno(entity.getSeqno());
            if (StringUtil.isNotEmpty(seqno)) {
                creditAssetpoolDetail.setSeqno(seqno);
                creditAssetpoolDetail.setFinprodId(entity.getCustomerId());
                creditAssetpoolDetailMapper.insert(creditAssetpoolDetail);
            }
        }
    }

    /**
     *删除授信关联
     * <功能详细描述>
     * @param creditLines
     * @see
     * @since 1.0
     */
    public void deleteCredit(CreditLines creditLines) {
        //是资产授信则修改授信额度表
        String finprodId = creditLines.getCustomerId();
        //CreditLines entity = new CreditLines();
        //entity.setCustomerId(null);
        String customerId = null;
        if(assetCommonMapper.deleteCreditLinesByFinprodId(customerId,finprodId)>0){
            return;
        }
        //是系列授信则删除系列授信明细表
        assetCommonMapper.deleteCreditAssetpoolDetailByFinprodId(finprodId);
    }

    /**
     * 修改授信关联
     * <功能详细描述>
     * @param creditLines
     * @see
     * @since 1.0
     */
    public void updateCredit(CreditLines creditLines) {
        deleteCredit(creditLines);
        insertCredit(creditLines);
    }
    
    /**
     * 资产是否重复
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public  int isRepeated(Map<String, Object> param){
        return assetCommonMapper.isRepeated(param);
    }

    
    /**
     * 发行人下拉框联动带出中债信息
     * <功能详细描述>
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public MstCustomerInfoAdd getRepInfoByIssuer(Map<String,Object> param){
        return assetCommonMapper.getRepInfoByIssuer(param);
    }
    // 根据资产三级分类检索五级分类  add by yjm 2019-10-08 start
    /**
     * 资产五级分类联动检索
     *
     * @param param 参数
     * @return 结果
     * @see
     * @since 1.0
     */
    public FinProductTypeConfig getAssetThreeType(FinProductTypeConfig param) {
        //优先查询wind分类
        param.setConfigType(DictCodeListExt2.配置类型_WIND分类.getValue());
        List<FinProductTypeConfig> list = assetCommonMapper.getAssetThreeType(param);
        if (StringUtil.isNotEmpty(list) && list.size() == 1){
            return list.get(0);
        }else{
            param.setConfigType(DictCodeListExt2.配置类型_投管分类.getValue());
            list = assetCommonMapper.getAssetThreeType(param);
            if (StringUtil.isEmptyList(list) || list.size() > 1 || list.get(0) == null) {
                return new FinProductTypeConfig();
            }
            return list.get(0);
        }         
    }
    // 根据资产三级分类检索五级分类  add by yjm 2019-10-08 end
    
    /**
     * 根据type6,7,8查询是否自动生成委外组合
     * @param map
     * @return
     */
    public boolean selectIsCreatePortfolio(FinProductType finProductType) {
    	boolean isCreatePortfolio = false;
    	List <AssetTypeConfig>  configlist = null;
        if(finProductType!=null ){
        	Map<String, Object> map = new HashMap<>();
        	map.put("type_6", finProductType.getType6());
        	map.put("type_7", finProductType.getType7());
        	map.put("type_8", finProductType.getType8());
        	configlist =assetCommonMapper.selectAssetTypeConfig(map);
        }
        if(configlist!=null && !configlist.isEmpty()){
        	for(AssetTypeConfig config:configlist ){
        		if(StringUtil.equals(config.getType6(), finProductType.getType6())
        				&& StringUtil.equals(config.getType7(), finProductType.getType7())
        				&& StringUtil.equals(config.getType8(), finProductType.getType8())
        				&& StringUtil.equals(config.getIsCreatePortfolio(), DictCodeList.是否_是.getValue())){
        			isCreatePortfolio = true;
        			break;
        		}
        	}
        }
        return isCreatePortfolio;
    }
    /**
     * 获取资产到期日
     * yjm 20191014
     * @param finprodId
     * @return
     */
    public AssetCommonInfoDto getMdateByFinproId(String finprodId){
        return assetCommonMapper.getMdateByFinproId(finprodId);
    }

    /**
     * 投管SPPI测试信息输入接口
     * @param paramMap
     * @return
     */
	public String insertSppiInfo(FinProductSppiInfo sppiInfo) {
		finProductSppiInfoMapper.insert(sppiInfo);
		return DictCodeList.是否_是.getValue();
	}
	/**
     * 金融产品基本信息查询
     *
     */
    public List<MockAssetInfoDto> selectAsset(Map<String, Object> map) throws Exception {
    	//基本信息批量查询
    	List<MockAssetInfoDto> list = selectAssetjb(map);
    	//现金流计算规则批量查询
    	List<FinCashCalcRule> listxjl = selectAssetxjl(map);
    	//现金流计划批量查询
    	List<MockFinCashPlanDto> listjh = selectAssetjh(map);
    	//行权计划批量查询
    	List<FinExercisePlan> listxq = selectAssetxq(map);
    	//回售权下一行权计划列表
    	List<FinExercisePlan> listxqhs = selectAssetxqhs(map);
    	//现金流支付规则批量查询
    	List<FinCashPayRule> listzf = selectxjlZf(map);
    	//基金开放计划批量查询
    	List<FinOpenPlan> listfund = selectFundOpenjh(map);

        //行情信息
        List<MockQuotationInfoDto> listhq = new ArrayList<>();
        List<MockQuotationInfoDto> listhqGYJZ = new ArrayList<>();
    	if(StringUtil.isNotEmpty(map.get("isForMock"))&& StringUtil.equals(DictCodeList.是否_是.getValue(),(String) map.get("isForMock"))){
            //行情信息
            listhqGYJZ = priceinfoService.selectPriceinfo(map);
            map.remove("isForMock");
            //行情信息
            listhq = priceinfoService.selectPriceinfo(map);
        }else{
            //行情信息
            listhq = priceinfoService.selectPriceinfo(map);
        }

    	for(MockAssetInfoDto dto:list){
    		List<FinCashCalcRule> listxjlk = new ArrayList<FinCashCalcRule>();
        	List<MockFinCashPlanDto> listjhk = new ArrayList<MockFinCashPlanDto>();
        	List<FinExercisePlan> listxqk = new ArrayList<FinExercisePlan>();
        	List<FinExercisePlan> listxqhsk = new ArrayList<FinExercisePlan>();
        	List<FinCashPayRule> listzfk = new ArrayList<FinCashPayRule>();
        	List<FinOpenPlan> listfundk = new ArrayList<FinOpenPlan>();
        	List<MockQuotationInfoDto> listhqk = new ArrayList<MockQuotationInfoDto>();
            List<MockQuotationInfoDto> listhqkGYJZ = new ArrayList<MockQuotationInfoDto>();
    		//dto.setFinprodId(dto.getFinProduct().getFinprodId());
    		dto.setBranch(dto.getFinProductType().getBranch());
    		dto.setTgAssetType(dto.getFinProductType().getType8());
    		dto.getFinProductType().setFinprodId(dto.getFinprodId());
    		for(FinCashCalcRule dto1:listxjl){
    			if(dto1.getFinprodId().equals(dto.getFinprodId())){
    				listxjlk.add(dto1);
    			}
    		}
    		dto.setFinCashCalcRuleList(listxjlk);
    		for(MockFinCashPlanDto dto1:listjh){
    			if(dto1.getFinCashInfo().getFinprodId().equals(dto.getFinprodId())){
    				listjhk.add(dto1);
    			}
    		}
    		dto.setMockFinCashPlanDtoList(listjhk);
    		for(FinExercisePlan dto1:listxq){
    			if(dto1.getFinprodId().equals(dto.getFinprodId())){
    				listxqk.add(dto1);
    			}
    		}
    		dto.setFinExercisePlanList(listxqk);
    		for(FinExercisePlan dto1:listxqhs){
    			if(dto1.getFinprodId().equals(dto.getFinprodId())){
    				listxqhsk.add(dto1);
    			}
    		}
    		dto.setResaleFinExercisePlanList(listxqhsk);
    		for(FinCashPayRule dto1:listzf){
    			if(dto1.getFinprodId().equals(dto.getFinprodId())){
    				listzfk.add(dto1);
    			}
    		}
    		dto.setFinCashPayRuleList(listzfk);
    		for(FinOpenPlan dto1:listfund){
    			if(dto1.getFinprodId().equals(dto.getFinprodId())){
    				listfundk.add(dto1);
    			}
    		}
    		dto.setFundOpenPlanList(listfundk);
    		for(MockQuotationInfoDto dto1:listhq){
    			if(dto1.getFinprodId().equals(dto.getFinProduct().getFinprodMarketId())){
    				listhqk.add(dto1);
    			}
    		}
    		dto.setMockQuotationInfoDtoList(listhqk);

            for(MockQuotationInfoDto dto1:listhqGYJZ){
                if(dto1.getFinprodId().equals(dto.getFinProduct().getFinprodMarketId())){
                    listhqkGYJZ.add(dto1);
                }
            }
            dto.setMockQuotationInfoDtoListForGYJZ(listhqkGYJZ);

    	}
        return list;
    }
    
    /**
     * 基本信息批量查询
     *
     */
    public List<MockAssetInfoDto> selectAssetjb(Map<String, Object> map) throws Exception {
        map.put("FINPROD_TYPE_TG_F02", DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue());
        map.put("FINPROD_TYPE_TG_F03", DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue());
        map.put("FINPROD_TYPE_TG_F04", DictCodeListExt1.金融产品类型_投管用_债券买断式正回购.getValue());
        map.put("FINPROD_TYPE_TG_F05", DictCodeListExt1.金融产品类型_投管用_债券买断式逆回购.getValue());
        return assetCommonMapper.selectAssetjb(map);
    }
    
    /**
     * 现金流计算规则批量查询
     *
     */
    public List<FinCashCalcRule> selectAssetxjl(Map<String, Object> map) throws Exception {
        return assetCommonMapper.selectAssetxjl(map);
    }
    
    /**
     * 现金流计划批量查询
     *
     */
    public List<MockFinCashPlanDto> selectAssetjh(Map<String, Object> map) throws Exception {
        return assetCommonMapper.selectAssetjh(map);
    }
    
    /**
     * 行权计划批量查询
     *
     */
    public List<FinExercisePlan> selectAssetxq(Map<String, Object> map) throws Exception {
        List<String> exerciseTypeList = new ArrayList<>();
        exerciseTypeList.add(DictCodeList.行权方式_赎回.getValue());
        exerciseTypeList.add(DictCodeList.行权方式_回售.getValue());
    	map.put("exerciseTypeList", exerciseTypeList);
        return assetCommonMapper.selectAssetxq(map);
    }
    
    /**
     * 回售权下一行权计划列表
     *
     */
    public List<FinExercisePlan> selectAssetxqhs(Map<String, Object> map) throws Exception {
        List<String> exerciseTypeList = new ArrayList<>();
        exerciseTypeList.add(DictCodeList.行权方式_回售.getValue());
        map.put("exerciseTypeList", exerciseTypeList);
        return assetCommonMapper.selectAssetxq(map);
    }
    
    /**
     * 现金流支付规则批量查询
     *
     */
    public List<FinCashPayRule> selectxjlZf(Map<String, Object> map) throws Exception {
        return assetCommonMapper.selectxjlZf(map);
    }
    
    /**
     * 基金开放计划批量查询
     *
     */
    public List<FinOpenPlan> selectFundOpenjh(Map<String, Object> map) throws Exception {
    	map.put("openTypeshui", DictCodeList.申赎开放类型_赎回开放.getValue());
    	map.put("openTypesh", DictCodeList.申赎开放类型_申赎开放.getValue());
    	map.put("finprodType2", DictCodeListExt1.金融产品类型_投管用_基金.getValue());
    	return assetCommonMapper.selectFundOpenjh(map);
    }
}
