package com.joyintech.tams.svreport.chb.prodpersonissue.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.joyintech.framework.util.CacheUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.svreport.chb.constant.CheckConfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.service.SvrCommonExportService;
import com.joyintech.tams.report.common.util.DateCalcUtil;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubErrorCheck;
import com.joyintech.tams.svreport.chb.perprodeclarereg.dto.PerProDeclareRegDto;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.svreport.chb.prodpersonissue.dao.ChbPrdIssueSubHisMapper;
import com.joyintech.tams.svreport.chb.prodpersonissue.dao.ProdPersonIssue001Mapper;
import com.joyintech.tams.svreport.chb.prodpersonissue.dto.ProdPersonIssue001Dto;
import com.joyintech.tams.svreport.chb.prodraisetotalregist.dto.RaiseTotalRegist001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;


@Service(value="svrProdPersonIssue001Service")
@Transactional(rollbackFor = Exception.class)
public class ProdPersonIssue001Service extends SvrCommonExportService<ProdPersonIssue001Dto>{
	 @Autowired
	private ProdPersonIssue001Mapper prodPersonIssue001Mapper;
   /**
     * 长sql最大一次插入或者更新条数
     */
    @Value("${application.reportInsertLongCount}")
    public  String reportInsertLongCount;
    /**
     * sql最大一次最大删除条数
     */
    @Value("${application.reportSelectOrDeleteCount}")
    public  String reportSelectOrDeleteCount;
    /**
     * 短sql最大一次最大插入条数
     */
    @Value("${application.reportInsertCount}")
    public  String reportInsertCount;
    /**
     * 短sql最大一次最大插入条数
     */
    @Value("${application.reportSelectOrDeleteCountForShenFen}")
    public  int reportSelectOrDeleteCountForShenFen;

    /**
     * 检查通过标志
     */
    private static final String IS_SUCESS_INVESTORS_CHECK = "01";

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ChbPrdIssueSubHisMapper chbPrdIssueSubHisMapper;
    @Autowired
    private CheckReport001Mapper checkReport001Mapper;
    @Autowired
    private ChbCheckRuleoutMapper chbCheckRuleoutMapper;
    @Autowired
    private BeforeSubErrorCheck beforeSubErrorCheck;

	 /**
     * 个人发行登记
     * @param param 查询条件
     * @return 返回分页信息
     * @see
     * @since 1.0
     */
	public Page<ProdPersonIssue001Dto> searchForPage(Map<String, Object> param) {
		 // 设置查询参数
         setSearchParam(param);
         // 实时跑批  未报送  在上游库查询产品修改日期
         // 查询数据
         //添加多法人查询逻辑
         if(CommonUtil.isBAmodel()){
        	 param.put("model_type", "01");
         }
         
         if (null != param.get(GlobalConstant.PARAM_PAGE)
        		 && null != param.get(GlobalConstant.PARAM_LENGTH)) {
        	 int page = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
             int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
    		 Page<ProdPersonIssue001Dto> pageList = super.queryForPage(page,legth,()->prodPersonIssue001Mapper.queryForProdPersonIssuePage(param));
    		 // 数据处理
    	     setResultList(pageList);
    		 return pageList;
         } else {
        	 Page<ProdPersonIssue001Dto> pageList = queryForPage(ProdPersonIssue001Mapper.class,"queryForProdPersonIssuePage",param);
        	 setResultList(pageList);
             return pageList;
         }
        
	}
	
	/**
	 * @Description 设置查询结果
	 * @param pageList
	 * @return void
	 * @version 1.7 2019年5月31日
	 */
	private void setResultList(Page<ProdPersonIssue001Dto> pageList){
		if(!CollectionUtils.isEmpty(pageList.getRoot())){
			for(ProdPersonIssue001Dto dto : pageList.getRoot()){
				if(!StringUtil.isEmpty(dto.getGradeType())){
					dto.setGradeType(dto.getGradeType().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.层级类别, dto.getGradeType()));
				}
				if(!StringUtil.isEmpty(dto.getOpenCircle())){
					dto.setOpenCircle(dto.getOpenCircle().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.规律开放周期, dto.getOpenCircle()));
				}
				if(!StringUtil.isEmpty(dto.getHolidayOnFlag())){
					dto.setHolidayOnFlag(dto.getHolidayOnFlag().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, dto.getHolidayOnFlag()));
				}
				if(!StringUtil.isEmpty(dto.getIsConstracte())){
					dto.setIsConstracte(dto.getIsConstracte().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.是否_中债, dto.getIsConstracte()));
				}
				if(!StringUtil.isEmpty(dto.getOpenBuss())){
					dto.setOpenBuss(dto.getOpenBuss().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.开放期业务, dto.getOpenBuss()));
				}
				if(!StringUtil.isEmpty(dto.getOpenMode())){
					dto.setOpenMode(dto.getOpenMode().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.开放模式_中债,dto.getOpenMode()));
				}
				if(!StringUtil.isEmpty(dto.getSubmitFeedbackStatus())){
					dto.setSubmitFeedbackStatus(SvrDataUtil.getNameFromDataDict(DictCode.报送反馈状态,dto.getSubmitFeedbackStatus()));
				}
				if(!StringUtil.isEmpty(dto.getManageType())){
					dto.setManageType(dto.getManageType().trim()+" "+SvrDataUtil.getNameFromDataDict(DictCode.管理方式_发行登记,dto.getManageType()));
				}
				dto.setProdRegCode(dto.getProdRegistCode());
			}
		}
	}
	
    /**
     * 设置查询参数
     *
     * @param param 参数
     */
    private void setSearchParam(Map<String, Object> param) {
    	// 选择行数据时
		if (param.containsKey("keyList")) {
			String keyList = param.get("keyList").toString();
			try {
				List<ProdPersonIssue001Dto> prodpersonissueList = JsonUtils.json2List(keyList,
						ProdPersonIssue001Dto.class);
				param.put("prodpersonissueList", prodpersonissueList);
			} catch (IOException e) {
//				e.printStackTrace();
	            logger.error(e.getMessage(),e);
			}
		} else{
	        if (null != param) {
	        	// 将产品代码当做多选下拉框处理
	    		String prodId = (String) param.get("prodId");
	    		if (StringUtil.isNotBlank(prodId)) {
	    			param.put("prodIdList", StringUtil.splitToList(GlobalConstant.COMMA, prodId));
	    		}
	    		//产品登记编码
	    		String prodRegistCode = (String) param.get("prodRegistCode");
	    		if (StringUtil.isNotBlank(prodRegistCode)) {
	    			param.put("prodRegistCodeList", StringUtil.splitToList(GlobalConstant.COMMA, prodRegistCode));
	    		}
	    		//募集起始日期
	    		param.put("raiseVdateStart", param.get("raiseVdateStart"));
		        param.put("raiseVdateEnd", DateCalcUtil.getEndOfDay((String)param.get("raiseVdateEnd")));
	    		//产品起始日
		        param.put("prdVdateStart", param.get("prdVdateStart"));
		        param.put("prdVdateEnd", DateCalcUtil.getEndOfDay((String)param.get("prdVdateEnd")));
		        String status = (String)param.get("status");
		        String submitStatus = (String)param.get("submitStatus");
		        String submitFeedbackStatus = (String)param.get("submitFeedbackStatus");
		        param.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
		        param.put("submitStatusList", StringUtil.splitToList(GlobalConstant.COMMA, submitStatus));
		        param.put("submitFeedbackStatusList", StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
		        //报送截止日
		        param.put("submitDeadLineStart", param.get("submitDeadLineStart"));
		        param.put("submitDeadLineEnd", DateCalcUtil.getEndOfDay((String)param.get("submitDeadLineEnd")));
	    		//数据日期
		        param.put("updateTimeStart", param.get("updateTimeStart"));
		        param.put("updateTimeEnd", DateCalcUtil.getEndOfDay((String)param.get("updateTimeEnd")));
		        //报送日
		        param.put("submitDateStart", param.get("submitDateStart"));
		        param.put("submitDateEnd", DateCalcUtil.getEndOfDay((String)param.get("submitDateEnd")));
	        }
		}
    }
    
    /**
     * {@inheritDoc}
     */
	@Override
    protected void buildExportParam(ExportParam exportParam) {
        Map<String, Object> pageParam = exportParam.getSelectParam();
        setSearchParam(pageParam);
        String pageIds = (String)pageParam.get("pageIds");
        if(StringUtil.isNotEmpty(pageIds)) {
            List<String> pageNames =  Arrays.asList(pageIds.split(GlobalConstant.COMMA));
            pageParam.replace("pageIds", pageIds, pageNames);
        }
        else{
            pageParam.replace("pageIds", pageIds, null);
        }

        // 导出方式flg
        String exportflag = (String)pageParam.get("export_method_flg");

        // 导出（未选中数据）
        if (StringUtil.equals(exportflag, "export")) {
            // 设置参数
            String flag = (String)pageParam.get("flag");
            String configFile = null;
            // 导出中债excel
            if (StringUtil.equals("0", flag)) {
                configFile = "/com/joyintech/tams/svreport/chb/prodpersonissue/conf/export_conf.xml";
            }
            else if(StringUtil.equals("1", flag)) {
                configFile = "/com/joyintech/tams/report/submitmanagement/astvalueregist/conf/export_conf_all.xml";
            }
            else if(StringUtil.equals("2", flag)) {
            	configFile = "/com/joyintech/tams/report/submitmanagement/astvalueregist/conf/export_conf_del.xml";
            }

            exportParam.config(configFile).select(() -> prodPersonIssue001Mapper.queryForProdPersonIssuePage(pageParam));
        }
        // 将选择的数据导出成excel
        else if (StringUtil.equals(exportflag, "exportSelect")) {
        	 String exportKeys =(String) pageParam.get("expKeys");
        	 // 处理画面参数
             List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, exportKeys);
            exportParam.config("/com/joyintech/tams/svreport/chb/prodpersonissue/conf/export_conf.xml").select(() -> prodPersonIssue001Mapper.queryExportListByKeys(keys));
        }

    }

    
    public List<ProdPersonIssue001Dto> queryForProdPersonIssuePage(Map<String, Object> param){
        return prodPersonIssue001Mapper.queryForProdPersonIssuePage(param);
    }
    
    public List<ProdPersonIssue001Dto> queryExportListByKeys(List<String> keys){
        return prodPersonIssue001Mapper.queryExportListByKeys(keys);
    }
    
    @Override
    public com.joyintech.framework.grid.Page<ProdPersonIssue001Dto> selectPageData(Map<String, Object> pageParam) {
        List<ProdPersonIssue001Dto> data = null;
        String exportMethodFlg = (String)pageParam.get("export_method_flg");
        if (StringUtil.equals(exportMethodFlg, "export")) {
            data =  restTemplate.postForList(
                    SvrServiceApplication.CHB_SERVICE_NAME + "/ProdPersonIssue/queryForProdPersonIssuePage",pageParam,
                    ProdPersonIssue001Dto.class);
        }  else {
         String exportKeys =(String) pageParam.get("expKeys");
         // 处理画面参数
            List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, exportKeys);
            data =  restTemplate.postForList(
                        SvrServiceApplication.CHB_SERVICE_NAME + "/ProdPersonIssue/queryExportListByKeys",keys,
                        ProdPersonIssue001Dto.class);
        }
        com.joyintech.framework.grid.Page<ProdPersonIssue001Dto> page = new com.joyintech.framework.grid.Page<ProdPersonIssue001Dto>();
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
 	 * 手工处理
 	 * @param map
 	 * @since 1.0
 	 */
 	@SuppressWarnings("unchecked")
 	public void updateSumStaus(Map<String, Object> map) {
 		
 		this.setSearchParam(map);
 		List<ProdPersonIssue001Dto> registList  = new ArrayList<ProdPersonIssue001Dto>();
 		List<ProdPersonIssue001Dto> subList = (List<ProdPersonIssue001Dto>)map.get("prodpersonissueList");
 		if(!CollectionUtils.isEmpty(subList)){
 			for(ProdPersonIssue001Dto dto : subList){
 				if(DictCodeListExt2.报送状态_未报送.getValue().equals(dto.getSendStatus())){
 					registList.add(dto);
 				}
 			}
 		}else{
 			//按条件查询全部
 			registList = prodPersonIssue001Mapper.queryForProdPersonIssuePage(map);
 		}
 		if(!CollectionUtils.isEmpty(registList)){
 			// 每批处理的数据条数
 			int partSize = 500;
	 		// 存放业务数据的临时list，数据量不超过partSize
	 		List<ProdPersonIssue001Dto> tmpList = new ArrayList<ProdPersonIssue001Dto>();
	 		// 分批次处理
	 		for (int j = 0; j < registList.size(); j++) {
	 			tmpList.add(registList.get(j));
	 			if (partSize == tmpList.size() || j == registList.size() - 1) {
	 				//找sub表有没有已报送的数据
	 				List<String> regList = prodPersonIssue001Mapper.selectRegistSeqnoList(tmpList,"02");
	 				//更新sub表数据（中债通过、手工处理）
	 	 			this.updateInterfSubmitAndSeq(DictCodeListExt2.报送状态_手工处理.getValue(),DictCodeListExt2.报送反馈状态_中债通过.getValue(), tmpList);
	 	 			if(!CollectionUtils.isEmpty(regList)){
	 					List<List<String>> regseqnolist = createStringList(regList,20);
	 					for(int i=0;i<regseqnolist.size();i++){
	 						//将已报送的数据移至his表 insert select 
	 						chbPrdIssueSubHisMapper.insertSubHisBatch(regseqnolist.get(i));
	 						//将已报送的数据从sub表物理删除
	 						prodPersonIssue001Mapper.deleteRegistSeqnoList(regseqnolist.get(i));
	 					}
	 				}
	 	 			tmpList.clear();
	 			}
	 		}
 		}
 	}
 	
 	
 	/**
 	 * 分批次执行updateInterfSubmitAndSeq
 	 *
 	 * @param investList
 	 *            数据
 	 * @see
 	 * @since 1.0
 	 */
 	private void updateInterfSubmitAndSeq(String sendStatus,String submitFeeBackStatus , List<ProdPersonIssue001Dto> investList) {
 		if (StringUtil.isEmptyList(investList)) {
 			return;
 		}
 		int maxDataCountOneTime = 10;
 		int dataCount = investList.size();
 		if (dataCount <= maxDataCountOneTime) {
 			prodPersonIssue001Mapper.updateInterfSubmitAndSeqBatch(sendStatus,submitFeeBackStatus, investList);
 			return;
 		}
 		List<ProdPersonIssue001Dto> newDataList = new ArrayList<ProdPersonIssue001Dto>();
 		newDataList.addAll(investList);
 		// 分批处理
 		int part = dataCount / maxDataCountOneTime; // 分批数
 		for (int i = 0; i < part; i++) {
 			logger.info("单次更新登记表start===");
 			prodPersonIssue001Mapper.updateInterfSubmitAndSeqBatch(sendStatus,submitFeeBackStatus,
                 newDataList.subList(0, maxDataCountOneTime));
 			newDataList.subList(0, maxDataCountOneTime).clear();
 			logger.info("单次更新登记表end===");
 		}
 		// 最后剩下的数据
 		if (CollectionUtils.isNotEmpty(newDataList)) {
 			logger.info("单次更新登记表start===");
 			prodPersonIssue001Mapper.updateInterfSubmitAndSeqBatch(sendStatus,submitFeeBackStatus, newDataList);
 			logger.info("单次更新登记表end===");
 		}
 	}

 	
 	/**
 	 * 已手工处理的数据将报送状态改为未报送
 	 *
 	 * @param upKey
 	 *            传入主键
 	 * @see
 	 * @since 1.0
 	 */
	@SuppressWarnings("unchecked")
	public String cancelMannul(Map<String, Object> map) {
 		this.setSearchParam(map);
 		List<ProdPersonIssue001Dto> list = new ArrayList<ProdPersonIssue001Dto>();
 		if(map.get("flag") != null && "01".equals(map.get("flag"))){
 			list = prodPersonIssue001Mapper.queryForProdPersonIssuePage(map);
 		}else{
 			list = (List<ProdPersonIssue001Dto>) map.get("prodpersonissueList");
 		}
 		if(!CollectionUtils.isEmpty(list)){
 			//找sub表有没有未报送的数据
			List<String> unregList = prodPersonIssue001Mapper.selectRegistSeqnoList(list,"01");
			if(!CollectionUtils.isEmpty(unregList)){
				return "fail";
			}
 			//查询his表，取最新的已报送数据
 			List<String> seqnolist = chbPrdIssueSubHisMapper.selectByprodId(list);
 			List<List<ProdPersonIssue001Dto>> lists = ListUtils.partition(list, 10);
 			if(CollectionUtils.isNotEmpty(lists)){
 				for(List<ProdPersonIssue001Dto> prod : lists){
 					prodPersonIssue001Mapper.cancelMannultRegist(prod);
 				}
 			}
 	 		if(!CollectionUtils.isEmpty(seqnolist)){
				List<List<String>> regList = createStringList(seqnolist, 20);
				for(int i=0;i<regList.size();i++){
					//将his表数据移至sub表
					prodPersonIssue001Mapper.insertSeletForSub(regList.get(i));
					//his表数据物理删除
					chbPrdIssueSubHisMapper.deleteBySeqno(regList.get(i));
				}
			}
 		}
 		return "success";
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
	
	public List<ExcelValidDto> checkData(List<String> checkKeys){
		Map<String,CheckReport001Dto> checkmap = new HashMap<String,CheckReport001Dto>();
		Map<String,ProdPersonIssue001Dto> repmap = new HashMap<String,ProdPersonIssue001Dto>();
		List<ProdPersonIssue001Dto> issuelist = prodPersonIssue001Mapper.queryExportListByKeys(checkKeys);
		repmap = issuelist.stream().collect(Collectors.toMap(ProdPersonIssue001Dto::getRegistSeqno,(p)->p,(key1,key2)->key1));
		List<ExcelValidDto> result = new ArrayList<ExcelValidDto>();
		List<CheckReport001Dto> resultlist  = new ArrayList<CheckReport001Dto>();
		// 检查登记表中小于今天所有未报送的数据
		if(CollectionUtils.isNotEmpty(checkKeys)){
			// 调用检查方法
			resultlist.addAll(check(null));
			checkmap = resultlist.stream().collect(Collectors.toMap(CheckReport001Dto::getRefseqno,(p)->p,(key1,key2)->key1));
			for(String key : checkKeys){
				if(checkmap.get(key)!=null){
					ExcelValidDto exc=new ExcelValidDto();
					exc.setCheckResult(checkmap.get(key).getCheckResult());
					exc.setErrorInfo(checkmap.get(key).getErrorInfo());
					exc.setWarnInfo(checkmap.get(key).getWarnInfo());
					exc.setValidKey(checkmap.get(key).getValidKey());
					result.add(exc);
				}else{
					ExcelValidDto exc=new ExcelValidDto();
					exc.setValidKey("产品代码:"+repmap.get(key).getProdId());
					exc.setCheckResult("01");
					result.add(exc);
				}
			}
		}
		return result;
	}
	
	/**
	 * @Description 检查登记表中小于今天所有未报送的数据
	 * @return
	 * @return List<CheckReport001Dto>
	 * @version 1.7 2019年6月24日
	 */
	public List<CheckReport001Dto> check(Date batchDate){
		// 数据日期为系统时间
		Date checkdate = DateUtil.parse(DateUtil.getSysDate());
		// 供跑批使用
		if (StringUtil.isNotEmpty(batchDate)){
			checkdate = batchDate;
		}
		// 删除今天所有已检查的数据
		checkReport001Mapper.delReportDatasByCodeAndDate(DictCodeListExt2.报表名称_产品发行登记.getValue(),checkdate);
		//增加例外情况过滤
        List<ChbCheckRuleout> listRuleout=chbCheckRuleoutMapper.selectDataByCheckTypeAndReprotCode(CheckConfConstants.CHB_CHECK_TYPE_B, CheckConfConstants.CHB_PRD_ISSUE_SUB);
        Map<String, List<ChbCheckRuleout>> mapRuleout = listRuleout.stream().collect(
                 JoyinCollectors.groupingBy(x -> x.getProdId()));
        //检查数据
        List<CheckReport001Dto> resultList = beforeSubErrorCheck.checkProdIssueReg(checkdate,null,mapRuleout);
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

	public List<String> getCheckRepList(Map<String, Object> param) {
		List<String> keylist = new ArrayList<String>();
		// 设置查询参数
        setSearchParam(param);
		List<ProdPersonIssue001Dto> list = prodPersonIssue001Mapper.queryForProdPersonIssuePage(param);
		if(CollectionUtils.isNotEmpty(list)){
			for(ProdPersonIssue001Dto dto : list){
				keylist.add(dto.getRegistSeqno());
			}
		}
		return keylist;
	}
	public List<ProdPersonIssue001Dto> selectForExport(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		@SuppressWarnings("unchecked")
		List<ProdPersonIssue001Dto> dataList =  (List<ProdPersonIssue001Dto>) pageParam.get("investList");

		List<ProdPersonIssue001Dto> dtoList = new ArrayList<ProdPersonIssue001Dto>();
		this.selectAllDataBatch(dataList, dtoList);
		return dtoList;
	}

	private void selectAllDataBatch(List<ProdPersonIssue001Dto> dataList, List<ProdPersonIssue001Dto> dtoList) {
		if (StringUtil.isEmptyList(dataList)) {
            return;
        }
		//add by leipeng 20200628
		String model_type = null;
		if(CommonUtil.isBAmodel()){
			model_type = "01";
		}
		
        int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount, "2");
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
        	dtoList.addAll(prodPersonIssue001Mapper.selectAllDataByList(dataList,model_type));
            return;
        }

        List<ProdPersonIssue001Dto> newDataList = new ArrayList<ProdPersonIssue001Dto>();
        newDataList.addAll(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
        	dtoList.addAll(prodPersonIssue001Mapper.selectAllDataByList(newDataList.subList(0, maxDataCountOneTime),model_type));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
        	dtoList.addAll(prodPersonIssue001Mapper.selectAllDataByList(newDataList,model_type));
        }
	}
	/**
     * 配置项中非空处理
     * @param Count
     * @param flag
     * @return
     */
    public int parsToInt(String count,String flag){
   	 if(StringUtil.isNotEmpty(count)&&Integer.parseInt(count)<=0){
   		 if("1".equals(flag)){
   	         	return 20;
   		 }
   		 else if("2".equals(flag)){
	         	return 500;
   		 }
   		 else {
	         	return 100;
   		 }
        }
        else{
        	return Integer.parseInt(count);
        }
   }	
	private void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<ProdPersonIssue001Dto> investList = JsonUtils.json2List(keyList,
						ProdPersonIssue001Dto.class);
				pageParam.put("investList", investList);
			} catch (IOException e) {
//				e.printStackTrace();
	            logger.error(e.getMessage(),e);
			}
		} else {
			// 查询参数条件
			// 将产品代码当做多选下拉框处理
			String prodName = (String) pageParam.get("prodName");
			if (StringUtil.isNotBlank(prodName)) {
				pageParam.put("prodNameList", StringUtil.splitToList(GlobalConstant.COMMA, prodName));
			}
			//产品登记编码
			String regCode = (String) pageParam.get("regCode[]");
			if (StringUtil.isNotBlank(regCode)) {
				pageParam.put("regCodeList", StringUtil.splitToList(GlobalConstant.COMMA, regCode));
			}
			// 报送状态
			String sendStatus = (String) pageParam.get("sendStatus");
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(sendStatus)) {
				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
			}
			// 反馈状态
			String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(submitFeedbackStatus)) {
				pageParam.put("submitFeedbackStatusList", StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
			}
			String status = (String) pageParam.get("status");
			// 将数据状态当做多选下拉框处理
			if (StringUtil.isNotBlank(status)) {
				pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
			}
		}
		
	}

}
