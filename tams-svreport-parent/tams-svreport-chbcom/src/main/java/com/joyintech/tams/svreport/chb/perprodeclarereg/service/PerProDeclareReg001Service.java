package com.joyintech.tams.svreport.chb.perprodeclarereg.service;


import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.dto.UploadFile;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.system.common.listener.UploadListener;
import com.joyintech.tams.common.svreport.chb.constant.CheckConfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.service.SvrCommonExportService;
import com.joyintech.tams.report.common.util.DateCalcUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubErrorCheck;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dao.ChbPrdDeclareSubHisMapper;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dao.PerProDeclareReg001Mapper;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

@Service(value="svrPerProDeclareReg001Service")
@Transactional(rollbackFor = Exception.class)
public class PerProDeclareReg001Service extends SvrCommonExportService<PerProDeclareRegDto> {
	@Autowired
	private PerProDeclareReg001Mapper perProDeclareReg001Mapper;
	@Autowired
	private ChbPrdDeclareSubHisMapper chbPrdDeclareSubHisMapper;
	@Autowired
	private UploadListener  uploadListener;
	
    @Autowired
    private BeforeSubErrorCheck beforeSubErrorCheck;

	@Autowired
	private CheckReport001Mapper checkReport001Mapper;

	@Autowired
	private ChbCheckRuleoutMapper chbCheckRuleoutMapper;

	private Logger logger = LoggerFactory.getLogger(getClass());

	public Page<PerProDeclareRegDto> selectForPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		pageParam.put("flag", "2");
		//add by leipeng 20200628
		if(CommonUtil.isBAmodel()){
			pageParam.put("model_type", "01");

		}
		if (null != pageParam.get(GlobalConstant.PARAM_PAGE)
				&& null != pageParam.get(GlobalConstant.PARAM_LENGTH)) {
			int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
	        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
			Page<PerProDeclareRegDto> page = queryForPage(
					pagenum,legth,()->perProDeclareReg001Mapper.selectAllData(pageParam));
			dealDataList(page.getRoot(), true);
			return page;
		} else {
			Page<PerProDeclareRegDto> page = queryForPage(PerProDeclareReg001Mapper.class,"selectAllData",pageParam);
			dealDataList(page.getRoot(), true);
			return page;
		}
		

	}

	@SuppressWarnings("unused")
    private void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		 if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<PerProDeclareRegDto> perProDeclareList = JsonUtils.json2List(keyList,
						PerProDeclareRegDto.class);
				pageParam.put("perProDeclareList", perProDeclareList);
			} catch (IOException e) {
//				e.printStackTrace();
	            logger.error(e.getMessage(),e);
			}
		} else {
			// 查询参数条件
			String prodName = (String) pageParam.get("prodName");
			pageParam.put("prodname", prodName);
			String prodInsId=(String) pageParam.get("prodInsId");
			String prodId = (String) pageParam.get("prodId");
			// 数据状态
			String status = (String) pageParam.get("status");
			// 报送状态
			String sendStatus = (String) pageParam.get("sendStatus");
			
			// 将数据状态当做多选下拉框处理
			if (StringUtil.isNotBlank(status)) {
				pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
			}
			// 将行内标识码当做多选下拉框处理
			if (StringUtil.isNotBlank(prodInsId)) {
							pageParam.put("prodInsIdList", StringUtil.splitToList(GlobalConstant.COMMA, prodInsId));
			}
			// 将产品代码当做多选下拉框处理
			if (StringUtil.isNotBlank(prodId)) {
							pageParam.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, prodId));
			}
//			System.out.println(" -------- "+pageParam.get("prodIdList"));
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(sendStatus)) {
				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
			}
//			System.out.println(" -------- "+pageParam.get("sendStatusList"));
			// 反馈状态
			String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(submitFeedbackStatus)) {
				pageParam.put("submitFeedbackStatusList",
						StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
			}
			String submitStatus = (String) pageParam.get("submitStatus");
            
			pageParam.put("submitDeadLineStart",
					DateCalcUtil.getStartOfDay((String) pageParam.get("submitDeadLineStart")));
			pageParam.put("submitDeadLineEnd", DateCalcUtil.getEndOfDay((String) pageParam.get("submitDeadLineEnd")));
			
			pageParam.put("raise_startdateStart",
					DateCalcUtil.getStartOfDay((String) pageParam.get("raise_startdateStart")));
			pageParam.put("raise_startdateEnd", DateCalcUtil.getEndOfDay((String) pageParam.get("raise_startdateEnd")));
			
			pageParam.put("statusDateStart",
					DateCalcUtil.getStartOfDay((String) pageParam.get("statusDateStart")));
			pageParam.put("statusDateEnd", DateCalcUtil.getEndOfDay((String) pageParam.get("statusDateEnd")));
			
			pageParam.put("submitDateStart",
					DateCalcUtil.getStartOfDay((String) pageParam.get("submitDateStart")));
			pageParam.put("submitDateEnd", DateCalcUtil.getEndOfDay((String) pageParam.get("submitDateEnd")));

		}
	}

	public void dealDataList(List<PerProDeclareRegDto> root, boolean flag) {
		SimpleDateFormat format=new SimpleDateFormat("yyyyMMdd");
		root.forEach(item -> {
			if(item.getSubmitFeedbackStatus()!=null){
				item.setSubmitFeedbackStatus(SvrDataUtil.getNameFromDataDict(DictCode.报送反馈状态, item.getSubmitFeedbackStatus()));
			}
			if(item.getRaisePstrdate()!=null){
				item.setRaisePstrDateSTR(format.format(item.getRaisePstrdate()));
			}
			if(item.getRaisePenddate()!=null){
				item.setRaisePendDateSTR(format.format(item.getRaisePenddate()));
			}
			if(item.getStatus()!=null){
				item.setStatusSTR(SvrDataUtil.getNameFromDataDict(DictCode.报送数据状态, item.getStatus()));
			}
			if(item.getSendStatus()!=null){
				item.setSendStatusSTR(SvrDataUtil.getNameFromDataDict(DictCode.报送状态, item.getSendStatus()));
			}
			if(item.getProfitType()!=null){
				item.setProfitType(item.getProfitType().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品收益类型, item.getProfitType()));
			}
			if(item.getTermtype()!=null){
				item.setTermtype(item.getTermtype().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品期限, item.getTermtype()));
			}
			if(item.getInvestArae()!=null){
				item.setInvestArae(item.getInvestArae().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资金投向地区, item.getInvestArae()));
			}
		    if(item.getInvestCountry()!=null && flag){
		    	List<String> investCountrys=StringUtil.splitToList(GlobalConstant.COMMA, item.getInvestCountry());
				item.setInvestCountry("");
				for(String investCountry : investCountrys){
					item.setInvestCountry(item.getInvestCountry()+","+investCountry+" "+SvrDataUtil.getNameFromDataDict(DictCode.所属国家或地区, investCountry));
				}
				item.setInvestCountry(item.getInvestCountry().substring(1));
		    }
			
			if(item.getFinanSerModel()!=null){
				item.setFinanSerModel(item.getFinanSerModel().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.理财业务服务模式, item.getFinanSerModel()));			
			}
			if(item.getOperateMode()!=null){
				item.setOperateMode(item.getOperateMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品运作模式, item.getOperateMode()));
			}
			if(item.getBookMode()!=null){
				item.setBookMode(item.getBookMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.会计核算方式_中债, item.getBookMode()));
			}
			if(item.getCmmode()!=null){
				item.setCmmode(item.getCmmode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品资产配置方式_中债, item.getCmmode()));
			}
			if(item.getPmmode()!=null){
				item.setPmmode(item.getPmmode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.管理模式, item.getPmmode()));
			}
			if(item.getPricedMode()!=null){
				item.setPricedMode(item.getPricedMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品定价方式, item.getPricedMode()));
			}
			if(item.getInvAssetType()!=null){
				item.setInvAssetType(item.getInvAssetType().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.资金实际投向, item.getInvAssetType()));
			}
			if(item.getConPrdTraget()!=null){
				List<String> conPrdTragets=StringUtil.splitToList(GlobalConstant.COMMA, item.getConPrdTraget());
				item.setConPrdTraget("");
				for(String conPrdTraget : conPrdTragets){
					item.setConPrdTraget(item.getConPrdTraget()+","+conPrdTraget+" "+SvrDataUtil.getNameFromDataDict(DictCode.标的类别, conPrdTraget));
				}
				item.setConPrdTraget(item.getConPrdTraget().substring(1));
			}
			if(item.getContMode()!=null){
				item.setContMode(item.getContMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.合作模式, item.getContMode()));
			}
			if(item.getRiskType()!=null){
				List<String> riskTypeS=StringUtil.splitToList(GlobalConstant.COMMA, item.getRiskType());
				item.setRiskType("");
				for(String riskType : riskTypeS){
					item.setRiskType(item.getRiskType()+","+riskType+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资者风险偏好, riskType));
				}
				item.setRiskType(item.getRiskType().substring(1));
			}
			if(item.getInvestPrinDate()!=null){
				item.setInvestPrinDate(item.getInvestPrinDate().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日_中债, item.getInvestPrinDate()));
			}
			if(item.getInvestProfDate()!=null){
				item.setInvestProfDate(item.getInvestProfDate().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资本金_收益_到账日_中债, item.getInvestProfDate()));
			}
			if(item.getRiskLevel()!=null){
				item.setRiskLevel(item.getRiskLevel().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品风险等级_申报, item.getRiskLevel()));
			}
			if(item.getTermRightFlag()!=null){
				item.setTermRightFlag(item.getTermRightFlag().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.有无_中债, item.getTermRightFlag()));
			}
			if(item.getCusRedeemFlag()!=null){
				item.setCusRedeemFlag(item.getCusRedeemFlag().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.有无_中债, item.getCusRedeemFlag()));
			}
			if(item.getCreditFlag()!=null){
				item.setCreditFlag(item.getCreditFlag().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.有无_中债, item.getCreditFlag()));
			}
			if(item.getCreditType()!=null){
				List<String> creditTypes=StringUtil.splitToList(GlobalConstant.COMMA, item.getCreditType());
				String crediType="";
				for(String creditType : creditTypes){
					crediType=crediType+","+creditType+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品增信机构类型, creditType);
				}
				item.setCreditType(crediType.substring(1));
			}
			
			if(item.getCreditMode()!=null){
				item.setCreditMode(item.getCreditMode()+" "+SvrDataUtil.getNameFromDataDict(DictCode.产品增信形式, item.getCreditMode()));
			}
			if(item.getIsYield()!=null){
				item.setIsYield(item.getIsYield().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.有无_中债, item.getIsYield()));
			}
			if(item.getIsYieldMeasBasis()!=null){
				item.setIsYieldMeasBasis(item.getIsYieldMeasBasis().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getIsYieldMeasBasis()));
			}
			if(item.getSaleArea()!=null){
				List<String> saleAreas=StringUtil.splitToList(GlobalConstant.COMMA, item.getSaleArea());
				item.setSaleArea("");
				for(String saleArea : saleAreas){
					item.setSaleArea(item.getSaleArea()+","+saleArea+" "+SvrDataUtil.getNameFromDataDict(DictCode.销售区域_中债, saleArea));
				}
				item.setSaleArea(item.getSaleArea().substring(1));
			}
			
			if(item.getOutDepCtry()!=null && flag){
				List<String> outDepCtrys=StringUtil.splitToList(GlobalConstant.COMMA, item.getOutDepCtry());
				item.setOutDepCtry("");
				for(String outDepCtry : outDepCtrys){
					item.setOutDepCtry(item.getOutDepCtry()+","+outDepCtry+" "+SvrDataUtil.getNameFromDataDict(DictCode.所属国家或地区, outDepCtry));
				}
				item.setOutDepCtry(item.getOutDepCtry().substring(1));
			}
			
			if(item.getRaiseCcy()!=null && flag){
				List<String> raiseCCYs=StringUtil.splitToList(GlobalConstant.COMMA, item.getRaiseCcy());
				item.setRaiseCcy("");
				for(String raiseCCY : raiseCCYs){
					item.setRaiseCcy(item.getRaiseCcy()+","+SvrDataUtil.getNameFromDataDict(DictCode.币种_中债, raiseCCY));
				}
				item.setRaiseCcy(item.getRaiseCcy().substring(1));
			}
			
			if(item.getCashPrinCcy()!=null && flag){
				List<String> cashPrinCCYs=StringUtil.splitToList(GlobalConstant.COMMA, item.getCashPrinCcy());
				item.setCashPrinCcy("");
				for(String cashPrinCCY : cashPrinCCYs){
					item.setCashPrinCcy(item.getCashPrinCcy()+","+SvrDataUtil.getNameFromDataDict(DictCode.币种_中债, cashPrinCCY));
				}
				item.setCashPrinCcy(item.getCashPrinCcy().substring(1));
			}
			
			if(item.getCashProfCcy()!=null && flag){
				List<String> cashProfCCYs=StringUtil.splitToList(GlobalConstant.COMMA, item.getCashProfCcy());
				item.setCashProfCcy("");
				for(String cashProfCCY : cashProfCCYs){
					item.setCashProfCcy(item.getCashProfCcy()+","+SvrDataUtil.getNameFromDataDict(DictCode.币种_中债, cashProfCCY));
				}
				item.setCashProfCcy(item.getCashProfCcy().substring(1));
			}
			
			//募集方式
			if(item.getRaiseType()!=null){
				item.setRaiseType(item.getRaiseType().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.募集方式_中债, item.getRaiseType()));
			}
			//投资性质
			if(item.getIvstorientation()!=null){
				item.setIvstorientation(item.getIvstorientation().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.投资性质, item.getIvstorientation()));
			}
			//是否金融同业专属
			if(item.getExcFinSector()!=null){
				item.setExcFinSector(item.getExcFinSector().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getExcFinSector()));
			}
			//是否设置最短持有期限
			if(item.getIsShortTerm()!=null){
				item.setIsShortTerm(item.getIsShortTerm().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getIsShortTerm()));
			}
			//最短持有期后是否自由赎回
			if(item.getIsFreeRedem()!=null){
				item.setIsFreeRedem(item.getIsFreeRedem().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债_期限, item.getIsFreeRedem()));
			}
			//是否现金管理类
			if(item.getCashManaCate()!=null){
				item.setCashManaCate(item.getCashManaCate().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, item.getCashManaCate()));
			}
			// 境内托管机构名称
            if(item.getInDepName()!=null){
                item.setInDepName(item.getInDepName().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.境内托管机构名称, item.getInDepName()));
            }
		});
	}

	public List<PerProDeclareRegDto> selectAllList(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		pageParam.put("flag", "1");
		List<PerProDeclareRegDto> dataList =  (List<PerProDeclareRegDto>) pageParam.get("perProDeclareList");

		List<PerProDeclareRegDto> dtoList = new ArrayList<PerProDeclareRegDto>();
		//this.selectAllDataBatch(dataList, dtoList);
//		investorsHoldInfo001Mapper.selectAllData(pageParam);
		dtoList=perProDeclareReg001Mapper.selcteDataByList(dataList);
		dealDataList(dtoList, false);
		return dtoList;
	}

	
	
	
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		Map<String, Object> pageParam = exportParam.getSelectParam();
		String pageIds = (String) pageParam.get("pageIds");
		if (StringUtil.isNotEmpty(pageIds)) {
			List<String> pageNames = Arrays.asList(pageIds.split(GlobalConstant.COMMA));
			pageParam.replace("pageIds", pageIds, pageNames);
		} else {
			pageParam.replace("pageIds", pageIds, null);
		}

		// 设置参数
		dealSearchParam(pageParam);
		pageParam.put("flag", "1");
		//无查询参数的情况
		exportParam.config("/com/joyintech/tams/svreport/chb/perprodeclarereg/conf/export_conf.xml")
		.select(() -> dealDataList(perProDeclareReg001Mapper.selectAllData(pageParam), false));
	}

    public List<PerProDeclareRegDto> selectAllData(Map<String, Object> map){
        return perProDeclareReg001Mapper.selectAllData(map);
    }
    
    @Override
    public com.joyintech.framework.grid.Page<PerProDeclareRegDto> selectPageData(Map<String, Object> pageParam) {
        List<PerProDeclareRegDto> data =  restTemplate.postForList(
                    SvrServiceApplication.CHB_SERVICE_NAME + "/PerProDeclareReg/selectAllData",pageParam,
                    PerProDeclareRegDto.class);
        dealDataList(data,false);
        com.joyintech.framework.grid.Page<PerProDeclareRegDto> page = new com.joyintech.framework.grid.Page<PerProDeclareRegDto>();
        page.setRoot(data);
        //page.setTotal(data.size());
		int pageSize = StringUtil.integerValueOf(pageParam.get(GlobalConstant.PARAM_LENGTH).toString(), StringUtil.integerValueOf(CacheUtil.getSysParam(GlobalConstant.EXPORT_EXCEL_ROW_COUNT),50000));

		if(data!=null){
			page.setTotal(data.size()/pageSize+1);
		}else{
			page.setTotal(0);
		}

        return  page;
    }
	/**
     * 手动处理
     * @param map 
     */
	public void updateSumStaus(Map<String, Object> map) {
		dealSearchParam(map);
		List<PerProDeclareRegDto> proDeclareList=new ArrayList<PerProDeclareRegDto>();
		List<PerProDeclareRegDto> subList=new ArrayList<PerProDeclareRegDto>();
		//界面未勾选
		if(map.get("perProDeclareList")==null){
			proDeclareList = perProDeclareReg001Mapper.selectAllData(map);
		}else {
			//界面勾选
			@SuppressWarnings("unchecked")
			List<PerProDeclareRegDto> dataList =  (List<PerProDeclareRegDto>) map.get("perProDeclareList");
			subList=perProDeclareReg001Mapper.selcteDataByList(dataList);
			for(PerProDeclareRegDto dto : subList){
				if(DictCodeListExt2.报送状态_未报送.getValue().equals(dto.getSendStatus())){
					proDeclareList.add(dto);
				}
			}
		}
		if(!CollectionUtils.isEmpty(proDeclareList)){
			// 每批处理的数据条数
			int partSize = 500;
			// 存放业务数据的临时list，数据量不超过partSize
			List<PerProDeclareRegDto> tmpList = new ArrayList<PerProDeclareRegDto>();
			// 分批次处理
			for (int j = 0; j < proDeclareList.size(); j++) {
				tmpList.add(proDeclareList.get(j));
				if (partSize == tmpList.size() || j == proDeclareList.size() - 1) {
					//找sub表有没有已报送的数据
					List<String> regList = perProDeclareReg001Mapper.selectRegistSeqnoList(tmpList,"02");
					//更新sub表数据（中债通过、手工处理）
					updateInterfSubmitAndSeq(DictCodeListExt2.报送状态_手工处理.getValue(),DictCodeListExt2.报送反馈状态_中债通过.getValue(), tmpList);
					if(!CollectionUtils.isEmpty(regList)){
						List<List<String>> regseqnolist = createStringList(regList,20);
						for(int i=0;i<regseqnolist.size();i++){
							//将已报送的数据移至his表 insert select 
							chbPrdDeclareSubHisMapper.insertSubHisBatch(regseqnolist.get(i));
							//将已报送的数据从sub表物理删除
							perProDeclareReg001Mapper.deleteRegistSeqnoList(regseqnolist.get(i));
						}
					}
					tmpList.clear();
				}
			}
		}
	}

	private List<List<String>> createStringList(List<String> targe,int size) {
		List<List<String>> listArr = new ArrayList<List<String>>();
		//获取被拆分的数组个数
		int arrSize = targe.size()%size==0?targe.size()/size:targe.size()/size+1;
		for(int i=0;i<arrSize;i++) {
			List<String> sub = new ArrayList<String>();
			//把指定索引数据放入到list中
			for(int j = i*size;j <= size*(i+1)-1;j++) {
				if(j <= targe.size()-1) {
					sub.add(targe.get(j));
				}
			}
			listArr.add(sub);
		}
		return listArr;
	}
	
	private void updateInterfSubmitAndSeq(String sendStatus, String submitFeeBackStatus ,List<PerProDeclareRegDto> proDeclareList) {
		if (StringUtil.isEmptyList(proDeclareList)) {
 			return;
 		}
		int maxDataCountOneTime = 10;
		int dataCount = proDeclareList.size();
		if (dataCount <= maxDataCountOneTime) {
			perProDeclareReg001Mapper.updateInterfSubmitAndSeqBatch(sendStatus, submitFeeBackStatus,proDeclareList);
 			return;
 		}
		List<PerProDeclareRegDto> newDataList = new ArrayList<PerProDeclareRegDto>();
 		newDataList.addAll(proDeclareList);
 	    // 分批处理
 	    int part = dataCount / maxDataCountOneTime; // 分批数
 	   for (int i = 0; i < part; i++) {
			logger.info("单次更新登记表start===");
			perProDeclareReg001Mapper.updateInterfSubmitAndSeqBatch(sendStatus,submitFeeBackStatus,
                newDataList.subList(0, maxDataCountOneTime));
			newDataList.subList(0, maxDataCountOneTime).clear();
			logger.info("单次更新登记表end===");
		}
		// 最后剩下的数据
		if (CollectionUtils.isNotEmpty(newDataList)) {
			logger.info("单次更新登记表start===");
			perProDeclareReg001Mapper.updateInterfSubmitAndSeqBatch(sendStatus,submitFeeBackStatus, newDataList);
			logger.info("单次更新登记表end===");
		}
	}

	/**
 	 * 已手工处理的数据将报送状态改为未报送
 	 * 取消手工处理
 	 * @param upKey
 	 *            传入主键
 	 * @see
 	 * @since 1.0
 	 */
 	public String cancelMannul(Map<String, Object> map) {
 		dealSearchParam(map);
 		List<PerProDeclareRegDto> proDeclareList=new ArrayList<PerProDeclareRegDto>();
 		//界面未勾选
		if(map.get("perProDeclareList")==null){
			proDeclareList = perProDeclareReg001Mapper.selectAllData(map);
		}else {
			//界面勾选
			@SuppressWarnings("unchecked")
			List<PerProDeclareRegDto> dataList =  (List<PerProDeclareRegDto>) map.get("perProDeclareList");
			proDeclareList=perProDeclareReg001Mapper.selcteDataByList(dataList);
		}
		if(!CollectionUtils.isEmpty(proDeclareList)){
			//找sub表有没有未报送的数据
			List<String> unregList = perProDeclareReg001Mapper.selectRegistSeqnoList(proDeclareList,"01");
			if(!CollectionUtils.isEmpty(unregList)){
				return "fail";
			}
			//查询his表，取最新的已报送数据
			List<String> seqnolist = chbPrdDeclareSubHisMapper.selectByprodId(proDeclareList);
			//变更手工处理的数据（报送状态：未报送）
			List<List<PerProDeclareRegDto>> lists = ListUtils.partition(proDeclareList, 10);
			if(CollectionUtils.isNotEmpty(lists)){
				for(List<PerProDeclareRegDto> pro : lists){
					perProDeclareReg001Mapper.cancelMannultRegist(proDeclareList);
				}
			}
			if(!CollectionUtils.isEmpty(seqnolist)){
				List<List<String>> regList = createStringList(seqnolist, 20);
				for(int i=0;i<regList.size();i++){
					//将his表数据移至sub表
					perProDeclareReg001Mapper.insertSeletForSub(regList.get(i));
					//his表数据物理删除
					chbPrdDeclareSubHisMapper.deleteBySeqno(regList.get(i));
				}
			}
		}
		return "success";
 	}
	
public AjaxResult uploadFile(Map<String, Object> map) throws ZipException, IOException {
 		
 		String srcName=(String) map.get("param[0][srcName]");
		String fileNo=(String) map.get("param[0][fileNo]");
		String fileProdId=srcName.substring(0, srcName.lastIndexOf("."));
		String targetFileSTR=(String) map.get("param[0][targetFile]");
		String registSeqno=(String) map.get("registSeqno");
		String prodId=(String) map.get("prodId");
		File file=new File(targetFileSTR);
		
		if(!fileProdId.equals(prodId)){
			if(file.exists()){
				file.delete();
			}
			uploadListener.remove(fileNo);
			return new AjaxResult("文件名与所选产品Id不匹配，上传失败！");
		}else {
			int fileNum=0;
			try (ZipFile zipFile=new ZipFile(new File(targetFileSTR),Charset.forName("GBK"));//或者ZipFile zipFile=new ZipFile(zipFile);
					){				
				for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements();){
				    ZipEntry entry=e.nextElement();
				   if(entry.getName().substring(entry.getName().lastIndexOf("/")+1).length()>0){
					   fileNum++;
				   }
				}
			} catch (Exception e) {
				throw e;
			}
			
			PerProDeclareRegDto  perProDeclareRegDto=new PerProDeclareRegDto();
			perProDeclareRegDto.setRegistSeqno(registSeqno);
			perProDeclareRegDto.setFileNo(fileNo);
			perProDeclareRegDto.setFileNum(new BigDecimal(fileNum));
			perProDeclareReg001Mapper.updateFileNo(perProDeclareRegDto);
		}
		return new AjaxResult("附件上传成功！");
 		
 	}
	public void downloadFile(Map<String, Object> map) {
//		System.out.println("downloadFile   "+map);
		
       
	}

	public void deltFile(Map<String, Object> map) {
		String registSeqno=(String) map.get("registSeqno");
		String fileNo=(String) map.get("fileNo");
		UploadFile file=uploadListener.fetch(fileNo);
		uploadListener.remove(fileNo);
		if(file!=null && file.getTargetFile().exists()){
			file.getTargetFile().delete();
		}
		PerProDeclareRegDto perProDeclareRegDto=new PerProDeclareRegDto();
		perProDeclareRegDto.setRegistSeqno(registSeqno);
		perProDeclareRegDto.setFileNo("");
		perProDeclareRegDto.setFileNum(BigDecimal.ZERO);
		perProDeclareReg001Mapper.updateFileNo(perProDeclareRegDto);
	}

	public String getFileList(Map<String, Object> map) throws IOException {
		StringBuffer res=new StringBuffer();
        if(map.get("fileNo")==null){
			return URLEncoder.encode("附件不存在", "UTF-8").replace("+","%20");
		}
		String fileNo=(String) map.get("fileNo");
		
		UploadFile file=uploadListener.fetch(fileNo);
		if(file==null||file.getTargetFile()==null){
			return URLEncoder.encode("附件丢失", "UTF-8").replace("+","%20");
		}
		res.append(file.getFileName()+":   "+file.getSize()/1000+"K<br><br>");
		try(ZipFile zipFile=new ZipFile(file.getTargetFile(),Charset.forName("GBK"))){
			for (Enumeration<? extends ZipEntry> e = zipFile.entries(); e.hasMoreElements();){
			    ZipEntry entry=e.nextElement();
			   if(entry.getName().substring(entry.getName().lastIndexOf("/")+1).length()>0){
				   res.append(entry.getName().substring(entry.getName().lastIndexOf("/")+1)+"<br>");
			   }
			}
		}
		catch (FileNotFoundException e) {
			logger.error(e.getMessage(),e);
			return URLEncoder.encode("文件未找到", "UTF-8").replace("+","%20");
		}
		String msg = URLEncoder.encode(res.toString(), "UTF-8").replace("+","%20");
		return msg;
	}
	
	/**
	 * @Description 检查登记表中小于今天所有未报送的数据
	 * @return
	 * @return List<CheckReport001Dto>
	 * @version 1.7 2019年6月24日
	 */
	public List<CheckReport001Dto> check(Date batchDate){
		Date checkdate = DateUtil.parse(DateUtil.getSysDate());
		// 供跑批使用
		if (StringUtil.isNotEmpty(batchDate)){
			checkdate = batchDate;
		}
		// 数据日期为系统时间
		// 删除今天所有已检查的数据
		checkReport001Mapper.delReportDatasByCodeAndDate(DictCodeListExt2.报表名称_产品申报登记.getValue(),checkdate);
		//增加例外情况过滤
		List<ChbCheckRuleout> listRuleout=chbCheckRuleoutMapper.selectDataByCheckTypeAndReprotCode(CheckConfConstants.CHB_CHECK_TYPE_B, CheckConfConstants.CHB_PRD_DECLARE_SUB);
		Map<String, List<ChbCheckRuleout>> mapRuleout = listRuleout.stream().collect(
				JoyinCollectors.groupingBy(x -> x.getProdId()));

		//检查数据
		//报送前强制的错误检查
		List<CheckReport001Dto> resultList = beforeSubErrorCheck.checkProdDeclareSub(checkdate,null,mapRuleout);

		//报错检查异常数据
		int batchCount = Integer.parseInt( null == SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT)?"100" : SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_INSERT_COUNT));// 每批commit的个数
		int batchLastIndex = batchCount;// 每批最后一个的下标
		for (int index = 0; index < resultList.size();) {
			if (batchLastIndex > resultList.size() - 1) {
				batchLastIndex = resultList.size();
				checkReport001Mapper.batchInsert(resultList.subList(index, batchLastIndex));
				break;// 数据删除完毕,退出循环
			} else {
				checkReport001Mapper.batchInsert(resultList.subList(index, batchLastIndex));
				index = batchLastIndex;// 设置下一批下标
				batchLastIndex = index + batchCount;
			}
		}
		return resultList;
	}
	
	/**
	 *  查询行内标识码
	 * @param map 入参
	 * @return 行内标识码列表
	 */
	public List<SelectOptionDto> queryProdInsIdList(Map<String,Object> map){
		return perProDeclareReg001Mapper.queryProdInsIdList(map);
	}
}
