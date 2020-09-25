/*
 * 文件名：AssetCommonService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangnian
 * 修改时间：2018年10月25日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.service.asset.common.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.asset.common.dao.AssetCommonMapper;
import com.joyintech.tams.common.base.dao.CreditAssetpoolDetailMapper;
import com.joyintech.tams.common.base.dao.CreditLinesMapper;
import com.joyintech.tams.common.base.dao.FinProductSppiInfoMapper;
import com.joyintech.tams.common.base.entity.AssetTypeConfig;
import com.joyintech.tams.common.base.entity.FinProductTypeConfig;
import com.joyintech.tams.common.base.entity.CreditAssetpoolDetail;
import com.joyintech.tams.common.base.entity.CreditLines;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.common.base.entity.FinProductSppiInfo;
import com.joyintech.tams.common.base.entity.FinProductType;
import com.joyintech.tams.common.base.entity.MstCustomerInfoAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.asset.dto.AssetCommonInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.MultilevelDataAuthorityUtil;

import java.lang.reflect.Array;
import java.util.*;

/**
 * 资产端共通
 * 〈功能详细描述〉
 * @author zhangnian
 * @version 1.0 2018年10月25日
 * @see AssetCommonService
 * @since 1.0
 */
@Service
public class AssetCommonService extends BaseService{
  //授权额度管理
    @Autowired(required=false)
    private CreditLinesMapper creditLinesMapper;
    //资产系列明细
    @Autowired(required=false)
    private CreditAssetpoolDetailMapper creditAssetpoolDetailMapper;

    @Autowired
    private AssetCommonMapper assetCommonMapper;
    
    @Autowired
    private FinProductSppiInfoMapper finProductSppiInfoMapper;
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
        //增加母资管计划筛选条件
        if (StringUtil.isNotEmpty(param.get("finprodType2"))) {
            if(StringUtil.equals(param.get("finprodType2"), DictCodeListExt1.金融产品类型_投管用_计划类资产.getValue())){
                param.put("finprodType2", StringUtil.splitToList(null, param.get("finprodType2").toString()+","+DictCodeListExt1.金融产品类型_投管用_母资管计划.getValue()));
            }else{
                param.put("finprodType2", StringUtil.splitToList(null, param.get("finprodType2").toString()));
            }
        }else {
        	param.put("finprodType2",null);
        }
        //多级法人改造 add by jingqiongqiong 20200616
        List<SuggestDto> returnList = new ArrayList<SuggestDto>();
        //共用资产
        List<SuggestDto> ListPub = assetCommonMapper.getAssetIdPub(param);
        returnList.addAll(ListPub);
        //非公用资产
        List<SuggestDto> ListPrivate = assetCommonMapper.getAssetId(param);
        returnList.addAll(ListPrivate);
        return returnList;
        //多级法人改造 add by jingqiongqiong 20200616
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
    public Page<SelectOptionDto> getAssetIdOption(int page,int length,@RequestBody Map<String, Object> param) {
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
    public Page<SelectOptionDto> getPflshList(int page, int length,Map<String, Object> param) {
        param.put("ZCSX", "0002");
        param.put("XLSX", "0003");
        // 多级法人改造 add by jingqiongqiong 20200615
        //当前用户所属机构代码
        String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
        //右上角选择框为空时 存放本级机构及有权限的下级机构 选择时 存放所选机构
        List<String> resourcesTreeInstCodeList =  LoginUserUtil.getTreeInstCode();
        param.put("institutionCode",createInst);
        param.put("resourcesTreeInstCodeList",resourcesTreeInstCodeList);
        // 多级法人改造 add by jingqiongqiong 20200615
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
    public Page<SelectOptionDto> getActualCreditProvid(int page, int length,Map<String, Object> param) {
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
    		//多级法人改造 add by jingqiongqiong 20200622
    		//当前用户所属机构代码
    		String createInst =  MultilevelDataAuthorityUtil.getService().queryOrganizationCode(LoginUserUtil.getUserName());
    		entity.setInstitutionCode(createInst);
    		//多级法人改造 add by jingqiongqiong 20200622
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
     * @param finProductType
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
        				&&( (StringUtil.isEmpty(config.getType7())&& StringUtil.isEmpty(finProductType.getType7())  ) || StringUtil.equals(config.getType7(), finProductType.getType7()) )
        				&&StringUtil.equals(config.getType8(), finProductType.getType8())
        				&&StringUtil.equals(config.getIsCreatePortfolio(), DictCodeList.是否_是.getValue())){
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
     * @param sppiInfo
     * @return
     */
	public String insertSppiInfo(FinProductSppiInfo sppiInfo) {
		finProductSppiInfoMapper.insert(sppiInfo);
		return DictCodeList.是否_是.getValue();
	}

    @SuppressWarnings("unchecked")
    public Page<SelectOptionDto> getSysDictCode(int page, int length, Map<String, Object> param) {
        return this.queryForPageForSelectReturn(page, length,
                () -> assetCommonMapper.getSysDictCode(param));
    }
}
