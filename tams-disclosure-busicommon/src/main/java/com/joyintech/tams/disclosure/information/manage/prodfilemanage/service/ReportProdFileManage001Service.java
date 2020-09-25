package com.joyintech.tams.disclosure.information.manage.prodfilemanage.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.editor.language.json.converter.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.tams.common.base.dao.RepProdFileProdInfoDtoMapper;
import com.joyintech.tams.common.base.entity.RepInvassetkindDto;
import com.joyintech.tams.common.base.entity.RepProdFileFeeInfoDto;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.disclosure.common.common.constant.DisclosureDictCodeList;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dao.ReportProdFileManage001Mapper;
import com.joyintech.tams.disclosure.information.manage.prodfilemanage.dto.ReportProdFileManage001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;

/**
 * 产品文件管理Service层
 * -微服务
 * @author hpp
 *
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ReportProdFileManage001Service extends CommonExportService<ReportProdFileManage001Dto>{
	/**
     * sql最大一次最大删除条数
     */
//    @Value("${application.reportSelectOrDeleteCount}")
    public String reportSelectOrDeleteCount = "50";
    
	@Autowired
	private ReportProdFileManage001Mapper reportProdFileManage001Mapper;
	
	@Autowired
	private RepProdFileProdInfoDtoMapper repProdFileProdInfoDtoMapper;
	
	/**
	 * 分页查询条件-产品代码下拉框
	 * @param params
	 * @return
	 */
	public List<SelectOptionDto> getPrdInfoSelectPF(Map<String, Object> params) {
		return reportProdFileManage001Mapper.getPrdInfoSelectPF(params);
	}
	
	/**
	 * 分页查询条件-产品系列下拉框
	 * @param params
	 * @return
	 */
	public List<SelectOptionDto> getProdSeriesSelectPF(Map<String, Object> params) {
		return reportProdFileManage001Mapper.getProdSeriesSelectPF(params);
	}
	
	/**
	 * 分页查询事件
	 * @param params
	 * @return
	 */
	public Page<ReportProdFileManage001Dto> searchPage(Map<String, Object> params) {
		int page = Integer.parseInt(params.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(params.get(GlobalConstant.PARAM_LENGTH).toString());
        
		//产品代码
		String prodId = (String)params.get("prodId");
        if(!StringUtil.isBlank(prodId)){
        	params.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, prodId));
        }
        //收益类型
        String profitType = (String)params.get("profitType");
        if(!StringUtil.isBlank(profitType)){
        	params.put("profitTypeList", StringUtil.splitToList(GlobalConstant.COMMA, profitType));
        }
        //产品系列
        String prodSeries = (String)params.get("prodSeries");
        if(!StringUtil.isBlank(prodSeries)){
        	params.put("prodSeriesList", StringUtil.splitToList(GlobalConstant.COMMA, prodSeries));
        }
        //发行方式
        String issueType = (String)params.get("issueType");
        if(!StringUtil.isBlank(issueType)){
        	params.put("issueTypeList", StringUtil.splitToList(GlobalConstant.COMMA, issueType));
        }
        //有效状态
        String dataStatus = (String)params.get("dataStatus");
        if(!StringUtil.isBlank(dataStatus)){
        	params.put("dataStatusList", StringUtil.splitToList(GlobalConstant.COMMA, dataStatus));
        }
        //募集方式
        String raiseType = (String)params.get("raiseType");
        if(!StringUtil.isBlank(raiseType)){
        	params.put("raiseTypeList", StringUtil.splitToList(GlobalConstant.COMMA, raiseType));
        }
        //募集币种
        String ccy = (String)params.get("ccy");
        if(!StringUtil.isBlank(ccy)){
        	params.put("ccyList", StringUtil.splitToList(GlobalConstant.COMMA, ccy));
        }
        //产品收益标识
        String profitFlag = (String)params.get("profitFlag");
        if(!StringUtil.isBlank(profitFlag)){
        	params.put("profitFlagList", StringUtil.splitToList(GlobalConstant.COMMA, profitFlag));
        }
        
        Page<ReportProdFileManage001Dto> pageList =queryForPage(
        		page,legth,()->reportProdFileManage001Mapper.searchPage(params));
        
        //返回结果处理
        dealReturnInfo(pageList.getRoot());
        return pageList;
	}
	
	/**
	 * 查询返回结果处理
	 * @param pageList
	 */
	private void dealReturnInfo(List<ReportProdFileManage001Dto> pageList) {
		for (ReportProdFileManage001Dto dto : pageList) {
			dto.setProfitType(SysDataUtil.getNameFromDataDict(DictCode.收益类型, dto.getProfitType()));
			dto.setDataStatusStr(SysDataUtil.getNameFromDataDict(DictCode.维护数据状态, dto.getDataStatus()));
			dto.setIssueType(SysDataUtil.getNameFromDataDict(DictCode.开放类型, dto.getIssueType()));
			dto.setProfitFlag(SysDataUtil.getNameFromDataDict(DictCode.产品收益标识, dto.getProfitFlag()));
			dto.setCcy(SysDataUtil.getNameFromDataDict(DictCode.币种, dto.getCcy()));
			dto.setRaiseType(SysDataUtil.getNameFromDataDict(DictCode.募集方式, dto.getRaiseType()));
			dto.setRaiseVdateStr(DateUtil.format(dto.getRaiseVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
			dto.setRaiseEnddateStr(DateUtil.format(dto.getRaiseEnddate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
			dto.setVdateStr(DateUtil.format(dto.getVdate(), DateUtil.DEFAULT_DATE_FORMAT_EN));
		}
	}
	
	/**
	 * 根据主键查询产品文件产品要素信息
	 * @param param
	 * @return
	 */
	public ReportProdFileManage001Dto selectByPrimaryKey(Map<String, Object> param){
		String produuid = param.get("produuid").toString();
//		return repProdFileProdInfoDtoMapper.selectByPrimaryKey(produuid);
		return reportProdFileManage001Mapper.selectByPrimaryKeyPF(produuid);
	}
	
	/**
	 * 保存提交
	 * @param dto
	 */
	public void doSave(ReportProdFileManage001Dto dto){
		String pageModel = dto.getPageModel();
		String dataStatus =DisclosureDictCodeList.维护数据状态_有效.getValue();
		dto.setDataStatus(dataStatus);
		dto.setUpdateUser(CommonUtil.getUserName());
		dto.setUpdateTime(DateUtil.now());
		//费用列表数据处理
		List<RepProdFileFeeInfoDto> dataList = dto.getFeeLists();
		for(RepProdFileFeeInfoDto feedto:dataList){
			feedto.setProdId(dto.getProdId());
			feedto.setFeerate(feedto.getFeerate().divide(new BigDecimal(100),8,BigDecimal.ROUND_HALF_UP));
		}
		//投资种类区间列表数据处理
		List<RepInvassetkindDto> investLists = dto.getInvestLists();
		for(RepInvassetkindDto invto:investLists){
			invto.setProdId(dto.getProdId());
			invto.setCreateUser(CommonUtil.getUserName());
			invto.setCreateTime(DateUtil.now());
			invto.setUpdateUser(CommonUtil.getUserName());
			invto.setUpdateTime(DateUtil.now());
		}
		if(StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_NEW)){
			dto.setProduuid(IDGenerator.generate(36));
			dto.setOrgCode(CommonUtil.getOrgCode());
			dto.setCreateDept(CommonUtil.getDeptCode());
			dto.setCreateUser(CommonUtil.getUserName());
			dto.setCreateTime(DateUtil.now());
			//1 插入产品文件产品要素表 BeanUtil.copyPropertiesIgnoreNull(record, item);
//			repProdFileProdInfoDtoMapper.insert(dto);
			reportProdFileManage001Mapper.insertPF(dto);
			
			//2 插入产品文件费用信息表
			if(dataList.size()>0){
				reportProdFileManage001Mapper.insertFeeInfoBatchPF(dataList);
			}
			//3 批量插入投资比例信息
			reportProdFileManage001Mapper.insertInvestKindBatchPF(investLists);
		}else if(StringUtil.equals(pageModel, GlobalConstant.DETAIL_PAGE_MODEL_EDIT)){
			//1 更新产品文件产品要素表
//			repProdFileProdInfoDtoMapper.updateByPrimaryKeySelective(dto);
			reportProdFileManage001Mapper.updateByPrimaryKeyPF(dto);
			
			//2 更新产品文件费用信息表：全删全插
			List<ReportProdFileManage001Dto> subList = new ArrayList<ReportProdFileManage001Dto>();
			subList.add(dto);
			reportProdFileManage001Mapper.deleteFeeInfoBatchPF(subList);
			if(dataList.size()>0){
				reportProdFileManage001Mapper.insertFeeInfoBatchPF(dataList);
			}
			
			//3 更新投资比例信息表：全删全插
			reportProdFileManage001Mapper.deleteInvestKindBatchPF(subList);
			reportProdFileManage001Mapper.insertInvestKindBatchPF(investLists);
		}
	}
	
	/**
	 * 根据主键删除数据
	 * @param dto
	 */
	public void doDelete(ReportProdFileManage001Dto dto){
		List<ReportProdFileManage001Dto> keyList = dto.getSelectedItem();
		//1 分批次逻辑删除
        this.updateByKeyListBatch(DisclosureDictCodeList.维护数据状态_删除.getValue(), keyList);
        
        //2 批量删除产品文件费用信息表
		reportProdFileManage001Mapper.deleteFeeInfoBatchPF(keyList);
		
		//3 更新投资比例信息表：全删全插
		reportProdFileManage001Mapper.deleteInvestKindBatchPF(keyList);
	}
	
	/**
	 * 批量逻辑更新产品文件产品要素表
	 * @param status
	 * @param keyList
	 */
	public void updateByKeyListBatch(String statusD, List<ReportProdFileManage001Dto> keyList){
		if(CollectionUtils.isEmpty(keyList)){
			return;
		}
		int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount);
		int dataCount = keyList.size();
		if(dataCount <= maxDataCountOneTime){
			reportProdFileManage001Mapper.updateByKeyList(keyList);
			return;
		}
		
		List<ReportProdFileManage001Dto> newDataList = new ArrayList<ReportProdFileManage001Dto>();
		newDataList.addAll(keyList);
		
		//分批处理
		int part = dataCount/maxDataCountOneTime;//分批数
		for(int i=0;i<part;i++){
			reportProdFileManage001Mapper.updateByKeyList(newDataList.subList(0, maxDataCountOneTime));
			newDataList.subList(0, maxDataCountOneTime).clear();
		}
		//最后剩下的数据
		if(CollectionUtils.isNotEmpty(newDataList)){
			reportProdFileManage001Mapper.updateByKeyList(newDataList);
		}
	}
	
	/**
     * 配置项中非空处理
     * 
     * @param Count
     * @param flag
     * @return
     */
    public int parsToInt(String count) {
        if (StringUtil.isNotEmpty(count) && Integer.parseInt(count) <= 0) {
            return 100;
        } else {
            return Integer.parseInt(count);
        }
    }
    
    /**
     * 查询产品代码是否已存在
     * @param prodId
     * @return
     */
    public int checkProdInfoExistPF(String prodId){
    	return reportProdFileManage001Mapper.checkProdInfoExistPF(prodId);
    }
    
    /**
     * 查询费用信息
     * @param map 参数
     * @return 费用信息下拉框
     */
    public List<SelectOptionDto> queryFeeInfoList(Map<String, Object> map) {
        map.put("relationProd", DictCodeList.关联范围_产品.getValue());
        map.put("relationChannel", DictCodeList.关联范围_通道.getValue());
        map.put("relationOther", DictCodeList.关联范围_其他.getValue());
        map.put("CHARGE_TYPE_01", DictCodeList.收费类型_计提式费用.getValue());
        return reportProdFileManage001Mapper.queryFeeInfoList(map);
    }
    
    /**
	 * 查询费用信息列表
	 * @param prodId
	 * @return
	 */
	public List<ReportProdFileManage001Dto> queryFeeInfoPF(String prodId){
		return reportProdFileManage001Mapper.queryFeeInfoPF(prodId);
	}
	
	/**
	 * 查询投资比例区间信息列表
	 * @param prodId
	 * @return
	 */
	public List<ReportProdFileManage001Dto> queryInvestProporPF(String prodId){
		List<ReportProdFileManage001Dto> investLists = reportProdFileManage001Mapper.queryInvestProporPF(prodId);
		
//		List<ReportProdFileManage001Dto> investLists = new ArrayList<ReportProdFileManage001Dto>();
//		if(StringUtil.isNotEmpty(dto)){
//	        String[] investTypes = dto.getInvestTypes().split(";");
//	        String[] investPropors = dto.getInvestPropors().split(";");
//	        for(int i = 0;i<investTypes.length;i++){
//	        	ReportProdFileManage001Dto investdto = new ReportProdFileManage001Dto();
//	        	investdto.setInvestTypes(investTypes[i]);
//	        	investdto.setInvestPropors(investPropors[i]);
//	        	investLists.add(investdto);
//	    	}
//		}
		return investLists;
	}
	
}
