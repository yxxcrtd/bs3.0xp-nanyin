/*
 * 文件名：FinProductBuildService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangliang
 * 修改时间：2018年9月17日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
package com.joyintech.service.product.productelement.build.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.product.common.dto.BussFinProductLayeredInfoDto;
import com.joyintech.service.product.common.dto.FinProductAnapleroticInfoDto;
import com.joyintech.service.product.common.dto.FinProductCommonInfoDto;
import com.joyintech.service.product.common.service.FinProductAnapleroticService;
import com.joyintech.service.product.productelement.build.dao.FinProductBuildMapper;
import com.joyintech.service.product.productelement.build.dto.FinProductBuild001Dto;
import com.joyintech.service.product.productelement.build.dto.MstCustomerNatureDto;
import com.joyintech.tams.common.base.dao.FinCashInfoMapper;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
//import com.joyintech.tams.common.base.dao.PrdLayerParamMapper;
import com.joyintech.tams.common.base.entity.FinBaseRuleInfo;
import com.joyintech.tams.common.base.entity.FinCashInfo;
import com.joyintech.tams.common.base.entity.FinProductAdd;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.valuationinterf.dto.BussFinProductFundAccountInfoDto;

/**
 * 产品新建管理
 *
 * @author zhangliang
 * @version 1.0 2018年9月17日
 * @see FinProductBuildService
 * @since 1.0
 */
@Service("backFinProductBuildService")
public class FinProductBuildService extends BaseService<FinProductBuild001Dto> {

	/**
	 * {@linkplain}
	 */
	@Autowired
	private FinProductBuildMapper finProductBuildMapper;

	/**
     * {@linkplain}
     */
    @Autowired
	private FinProductAddMapper finProductAddMapper;
    @Autowired
    private FinCashInfoMapper finCashInfoMapper;
    @Autowired
    private FinProductAnapleroticService finProductAnapleroticService;

	/**
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public Page<FinProductBuild001Dto> selectForPage(int page, int length, Map<String, Object> pageParam) throws Exception {
    	pageParam.put("statusValidE", DictCodeListExt1.有效状态_有效.getValue());
    	pageParam.put("statusValidA", DictCodeListExt1.有效状态_待处理.getValue());
    	pageParam.put("statusValidEName", DictCodeListExt1.有效状态_有效.getCodeName());
        pageParam.put("statusValidAName", DictCodeListExt1.有效状态_待处理.getCodeName());
    	pageParam.put("manObiTypeFinProd", DictCodeListExt1.管理对象类型_金融产品.getValue());
    	pageParam.put("cashTypeFinProd", DictCodeListExt1.现金流业务类型_金融产品.getValue());
    	pageParam.put("cashFirstTypeInt", DictCodeListExt1.现金流一级类型_利息.getValue());
    	pageParam.put("cashSecondTypeInt", DictCodeListExt1.现金流二级类型_利息.getValue());
    	pageParam.put("yes", DictCodeList.是否_是.getValue());
    	pageParam.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        if (StringUtil.isNotEmpty(pageParam.get("targetCustomType"))) {
            
        } 
    	Page<FinProductBuild001Dto> dto = super.queryForPage(page, length, () -> finProductBuildMapper.selectForPage(pageParam));
    	return dto;
    }

    public List<FinBaseRuleInfo> getFinBaseRuleInfo(Map<String, Object> map){
        return finProductBuildMapper.getFinBaseRuleInfo(map);
    }

    /**
     * 发行状态管理的查询逻辑
     * 使用给定的sqlID检索分页数据。返回类型是Service的泛型。
     * @param pageParam pageParam
     * @return Page类  Page
     * @throws Exception 异常信息
     */
    public List<FinProductBuild001Dto> selectForList(Map<String, Object> pageParam) {
        pageParam.put("statusValidE", DictCodeListExt1.有效状态_有效.getValue());
        pageParam.put("effectStatus", DictCodeListExt1.有效状态_有效.getValue());
        pageParam.put("statusValidEName", DictCodeListExt1.有效状态_有效.getCodeName());
        pageParam.put("statusValidAName", DictCodeListExt1.有效状态_待处理.getCodeName());
        // 去掉待处理的查询条件
        pageParam.put("manObiTypeFinProd", DictCodeListExt1.管理对象类型_金融产品.getValue());
        pageParam.put("cashTypeFinProd", DictCodeListExt1.现金流业务类型_金融产品.getValue());
        pageParam.put("cashFirstTypeInt", DictCodeListExt1.现金流一级类型_利息.getValue());
        pageParam.put("cashSecondTypeInt", DictCodeListExt1.现金流二级类型_利息.getValue());
        pageParam.put("yes", DictCodeList.是否_是.getValue());
        pageParam.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return finProductBuildMapper.selectForPage(pageParam);
    }

    /**
	 * 组合代码联想下拉框
	 *
	 * @param param 参数map
	 * @return 组合信息
	 * @see
	 * @since 1.0
	 */
    public Page<SelectOptionDto> queryGroupIdInfo(@RequestBody Map<String, Object> param,int page,int length){
    	param.put("param", param);
    	param.put("manObiTypeFinProd", DictCodeListExt1.管理对象类型_金融产品.getValue());
        return this.queryForPageForSelectReturn(page, length, ()-> finProductBuildMapper.queryGroupIdInfo(param));
    }

    /**
	 * 产品代码联想下拉框
	 *
	 * @param param 参数map
	 * @return 产品信息
	 * @see
	 * @since 1.0
	 */
    public Page<SelectOptionDto> queryProdIdInfo(@RequestBody Map<String, Object> param,int page,int lenght){
    	param.put("param", param);
    	param.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
		return this.queryForPageForSelectReturn(page, lenght, ()-> finProductBuildMapper.queryProdIdInfo(param));
    }

    /**
     * @param finprodId 金融产品代码
     * @return 查询个数
     */
    public int querySchedulingCountByFinprodId(String finprodId){
        return finProductBuildMapper.querySchedulingCountByFinprodId(finprodId);
    }

    /**
     * @param finprodId 金融产品代码
     * @param finprodTypeProd 金融产品类型
     * @return 查询个数
     */
    public int queryProdCountByFinprodId(String finprodId){
        return finProductBuildMapper.queryProdCountByFinprodId(finprodId, DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
    }

    /**
     * 根据金融产品代码查询产品信息
     * @param map 参数
     * @return 产品信息
     */
    public FinProductCommonInfoDto queryProdInfoByFinprodId(Map<String, Object> map){
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("PS_ZJSX", DictCodeList.交易方向_中间付息.getValue());
        map.put("FINPROD_TYPE_LCCP", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("YES_NO_N", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        FinProductCommonInfoDto dto=finProductBuildMapper.queryProdInfoByFinprodId(map);
        if(dto!=null){
        	//add by gcl 20193/18 start
        	 Integer reg=dto.getRepFinprodStage();
             String tru=dto.getTrusteeId();
             dto.setRepFinprodStageC(reg);
             dto.setTruOrg(tru);
           //add by gcl 20193/18 end\
          map.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
          map.put("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
          List<BussFinProductFundAccountInfoDto> list= finProductBuildMapper.getFunInfo(map);
          for(BussFinProductFundAccountInfoDto account:list){
        	  String feeType="";
        	  if(StringUtil.isNotEmpty(account.getFeeType())){
        		  String [] accArr=account.getFeeType().split(",");
        		  for(int i=0;i<accArr.length;i++){
        			FinCashInfo cash= finCashInfoMapper.selectByPrimaryKey(accArr[i]);
        			if(cash!=null)
        			  feeType+=cash.getFeeId();
        			  if(i!=accArr.length-1)feeType+=",";
        		  }
        		  account.setFeeType(feeType);
        	 }
          }
          dto.setBussFinProductFundAccountInfoDto(list);
          List<BussFinProductLayeredInfoDto> layList=finProductBuildMapper.getLayerParam(map);
          dto.setBussFinProductLayeredInfoDto(layList);
          //合版本业绩回补相关 add by wangcheng 20191028 start
          if(!StringUtil.equals(DictCodeList.收益类型_收益型.getValue(), dto.getProfitType())){
              map.put("finprodId", dto.getFinprodId());
              List<FinProductAnapleroticInfoDto> FinProductAnapleroticInfoTabList = finProductAnapleroticService.queryAnapleroticInfoById(map);
              if(!StringUtil.isEmptyList(FinProductAnapleroticInfoTabList)){
                  dto.setIsAnaplerotic(DictCodeList.是否_是.getValue());
              }
              dto.setFinProductAnapleroticTabInfoDtoList(FinProductAnapleroticInfoTabList);
          }
        //合版本业绩回补相关 add by wangcheng 20191028 end
        }

        return dto;
    }

    /**
     * 根据金融产品代码修改发行状态
     * @param finProductBuild001Dto 更新对象
     * @return DB
     */
    public int updateIssueStatusByFinprodId(FinProductBuild001Dto finProductBuild001Dto){
		FinProductAdd record = new FinProductAdd();
		record.setFinprodId(finProductBuild001Dto.getFinprodId());
		record.setIssueStatus(finProductBuild001Dto.getNewIssueStatus());
		record.setIssueStatusRemark(finProductBuild001Dto.getIssueStatusRemark());
		record.setUpdateUser(finProductBuild001Dto.getUpdateUser());
		record.setUpdateTime(finProductBuild001Dto.getUpdateTime());
		record.setCreateUser(finProductBuild001Dto.getCreateUser());
		record.setCreateTime(finProductBuild001Dto.getCreateTime());
		record.setCreateDept(finProductBuild001Dto.getCreateDept());
		if (0 < finProductAddMapper.updateByPrimaryKeySelective(record)) {
			// 更新分层产品的发行状态
			finProductBuild001Dto.setIssueStatus(finProductBuild001Dto.getNewIssueStatus());
			return finProductBuildMapper.updateIssueStatusByPfinprodId(finProductBuild001Dto);
		}
		return 0;
    }
    /**
     * 查询机构
     *
     * @param dto dto
     * @return 结
     * @since 1.0
     */
    public Page<SelectOptionDto> queryTrusteeId(Map<String, Object> map,int page,int length) {
    	String pageModel = (String) map.get("pageModel");
    	List<MstCustomerNatureDto> mstCustomerNatureList = finProductBuildMapper.queryTrusteeId(map);
    	List<SelectOptionDto> list = new ArrayList<SelectOptionDto>();
    	for(int i=0;i<mstCustomerNatureList.size();i++){
    		SelectOptionDto dto = new SelectOptionDto();
    		List<String> stringList = StringUtil.splitToList(GlobalConstant.COMMA,
    				mstCustomerNatureList.get(i).getCustomerCharater());
    		//查看模式下有可能该产品的托管机构已经没有托管机构的标识，但是还是需要显示出来。
    		if(pageModel != null && StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_REFERENCE)) {
    			dto.setId(mstCustomerNatureList.get(i).getCustomerId());
    			dto.setText(mstCustomerNatureList.get(i).getCustomerName());
    			list.add(dto);
    		} else {
    			if(StringUtil.isNotEmpty(stringList.get(3))){
    				dto.setId(mstCustomerNatureList.get(i).getCustomerId());
        			dto.setText(mstCustomerNatureList.get(i).getCustomerName());
        			list.add(dto);
    			}
    		}
    	}
    	Page<SelectOptionDto> pag = new Page<>();
    	pag.setRoot(list);
    	pag.setRecords(list.size());
    	return pag;
    }
    /**
     * 根据金融产品代码查询产品信息
     * @param map 参数
     * @return 产品信息
     */
    public List<SelectOptionDto> queryLayerProdInfoByFinprodId(Map<String, Object> map){
        List<BussFinProductLayeredInfoDto> layList=finProductBuildMapper.getLayerParam(map);
        List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
        if(layList != null && !layList.isEmpty()) {
        	for(BussFinProductLayeredInfoDto item : layList) {
        		if(StringUtil.isNotEmpty(item.getProdStyle())){
	        		SelectOptionDto option = new SelectOptionDto();
	        		option.setId(item.getFinprodId());
	        		option.setText(item.getProdStyle());
	        		options.add(option);
        		}
        	}
        }
        return options;
    }
    
    /**
     * 根据金融产品代码查询产品信息
     * @param map 参数
     * @return 产品信息
     */
    public FinProductCommonInfoDto queryProdInfoByProdId(Map<String, Object> map){
        map.put("cashType1", DictCodeListExt1.现金流一级类型_利息.getValue());
        map.put("cashType2", DictCodeListExt1.现金流二级类型_利息.getValue());
        map.put("PS_ZJSX", DictCodeList.交易方向_中间付息.getValue());
        map.put("FINPROD_TYPE_LCCP", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        map.put("YES_NO_N", DictCodeList.是否_否.getValue());
        map.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        FinProductCommonInfoDto dto=finProductBuildMapper.queryProdInfoByProdId(map);
        if(dto!=null){
        	//add by gcl 20193/18 start
        	 Integer reg=dto.getRepFinprodStage();
             String tru=dto.getTrusteeId();
             dto.setRepFinprodStageC(reg);
             dto.setTruOrg(tru);
           //add by gcl 20193/18 end
          map.put("PROFIT_TYPE_01", DictCodeList.收益类型_净值型.getValue());
          map.put("DISPOSE_PATTERN_11", DictCodeList.处理模式_分档.getValue());
          List<BussFinProductFundAccountInfoDto> list= finProductBuildMapper.getFunInfo(map);
          for(BussFinProductFundAccountInfoDto account:list){
        	  String feeType="";
        	  if(StringUtil.isNotEmpty(account.getFeeType())){
        		  String [] accArr=account.getFeeType().split(",");
        		  for(int i=0;i<accArr.length;i++){
        			FinCashInfo cash= finCashInfoMapper.selectByPrimaryKey(accArr[i]);
        			if(cash!=null)
        			  feeType+=cash.getFeeId();
        			  if(i!=accArr.length-1)feeType+=",";
        		  }
        		  account.setFeeType(feeType);
        	 }
          }
          dto.setBussFinProductFundAccountInfoDto(list);
          List<BussFinProductLayeredInfoDto> layList=finProductBuildMapper.getLayerParam(map);
          dto.setBussFinProductLayeredInfoDto(layList);
        }

        return dto;
    }
    
    /**
     * 获取分层产品产品款型
	* @param finprodId
	* @return
	*/
    public List<SelectOptionDto> queryLayerProdStyleList(Map<String, Object> map){
        List<BussFinProductLayeredInfoDto> layList=finProductBuildMapper.getLayerParamList(map);
        List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
        if(layList != null && !layList.isEmpty()) {
        	for(BussFinProductLayeredInfoDto item : layList) {
        		if(item!=null){
            		SelectOptionDto option = new SelectOptionDto();
            		option.setId(item.getProdStyle());
            		option.setText(item.getProdStyle());
            		options.add(option);
        		}
        	}
        }
        return options;
    }
    /**
     * 获取分层产品产品款型
     * @param finprodId
     * @return
     */
    public List<SelectOptionDto> getLayerParamFinprodIdList(Map<String, Object> map){
    	List<BussFinProductLayeredInfoDto> layList=finProductBuildMapper.getLayerParamFinprodIdList(map);
    	List<SelectOptionDto> options = new ArrayList<SelectOptionDto>();
    	if(layList != null && !layList.isEmpty()) {
    		for(BussFinProductLayeredInfoDto item : layList) {
    			if(item!=null&&StringUtil.isNotEmpty(item.getProdStyle())){
    				SelectOptionDto option = new SelectOptionDto();
        			option.setId(item.getFinprodId());
        			option.setText(item.getFinprodId()+":"+item.getProdStyle());
        			options.add(option);
    			}
    		}
    	}
    	return options;
    }
    
    
    /**
     * 获取产品分层信息中的目标客户类型
     * @param map
     * @return
     */
    public List<BussFinProductLayeredInfoDto> getLayerParam(Map<String, Object> map){
    	List<BussFinProductLayeredInfoDto> list = new ArrayList<>();
    	list = finProductBuildMapper.getLayerParam(map);
    	return list;
    }

    
    
    public int  getPreview(String finprodId) {
    	return finProductBuildMapper.getPreview(finprodId);
    }
    public  List<FinProductBuild001Dto>  getTermType(Map<String, Object> map) {
    	return finProductBuildMapper.getTermType(map);
    }

	public List<FinProductBuild001Dto> getTermType02(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return finProductBuildMapper.getTermType02(map);
	}
	
	public List<FinProductBuild001Dto> getTermType03(Map<String, Object> map) { 
        return finProductBuildMapper.getTermType03(map);
    }
	
	public String getOpenMode(String finprodId) {
		// TODO Auto-generated method stub
		return finProductBuildMapper.getOpenMode(finprodId);
	}
	
	public List<SelectOptionDto> queryPrdDesign(Map<String, Object> param){
		param.put("dataStatus_effective", DictCodeListExt1.有效状态_有效.getValue());
		return finProductBuildMapper.queryPrdDesign(param);
	}

}