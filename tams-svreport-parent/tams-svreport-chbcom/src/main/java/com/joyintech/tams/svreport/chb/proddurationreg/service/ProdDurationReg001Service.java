package com.joyintech.tams.svreport.chb.proddurationreg.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.svreport.chb.constant.CheckConfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.util.SvrMaptoEntityUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.service.SvrCommonExportService;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubErrorCheck;
import com.joyintech.tams.svreport.chb.proddurationreg.dao.ProdDurationReg001Mapper;
import com.joyintech.tams.svreport.chb.proddurationreg.dto.ProdDurationReg001Dto;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 产品存续期登记service
 *
 * @author lingxq
 * @version 1.0 2019年1月22日
 * @see ProdDurationReg001Service
 * @since 1.0
 */
@Service(value="svrProdDurationReg001Service")
@Transactional(rollbackFor = Exception.class)
public class ProdDurationReg001Service extends SvrCommonExportService<ProdDurationReg001Dto> {

	@Autowired
	private ProdDurationReg001Mapper prodDurationReg001Mapper;
	@Autowired
    private CheckReport001Mapper checkReport001Mapper;
	 @Autowired
    private ChbCheckRuleoutMapper chbCheckRuleoutMapper;
    @Autowired
    private BeforeSubErrorCheck beforeSubErrorCheck;

    private Logger logger = LoggerFactory.getLogger(getClass());
    
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

	@Autowired
	private SvrMaptoEntityUtil svrMaptoEntityUtil;
	private String tableName = "CHB_PRD_DEAL_SUB";

	/**
	 * 中债基础数据mapper
	 */
	@Autowired
	private BaseMapper baseMapper;

	/**
	 * 查询页面数据
	 *
	 * @param pageParam
	 *            查询条件
	 * @return 结果列表
	 * @see
	 * @since 1.0
	 */
	public Page<ProdDurationReg001Dto> selectForPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		//将发送状态改为空
		pageParam.put("sendStatus", "");
		
		//add by leipeng 20200628
		String model_type = null;
//		if(CommonUtil.isBAmodel()){
			pageParam.put("model_type", "01");
//		}
		
		if(pageParam.get(GlobalConstant.PARAM_PAGE)!=null){
			int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
	        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
			Page<ProdDurationReg001Dto> page = queryForPage(
					pagenum,legth,()->prodDurationReg001Mapper.selectAllData(pageParam));
			dealResultlist(page.getRoot());
			return page;
		}else{
			Page<ProdDurationReg001Dto> page = queryForPage(ProdDurationReg001Mapper.class,"selectAllData",pageParam);
			dealResultlist(page.getRoot());
			return page;
		}
		
	}
	private void dealResultlist(List<ProdDurationReg001Dto> resultlist){
		if(!CollectionUtils.isEmpty(resultlist)){
			for(ProdDurationReg001Dto dto : resultlist){
				if(StringUtil.isNotEmpty(dto.getSubmitFeedbackStatus())){
					dto.setSubmitFeedbackStatus(SvrDataUtil.getNameFromDataDict(DictCode.报送反馈状态,dto.getSubmitFeedbackStatus()));
				}
			}
		}
	}
	public List<ProdDurationReg001Dto> selectForExport(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		@SuppressWarnings("unchecked")
		List<ProdDurationReg001Dto> dataList =  (List<ProdDurationReg001Dto>) pageParam.get("prodDurationList");

		List<ProdDurationReg001Dto> dtoList = new ArrayList<ProdDurationReg001Dto>();
		this.selectAllDataBatch(dataList, dtoList);
		return dtoList;
	}

	private void selectAllDataBatch(List<ProdDurationReg001Dto> dataList, List<ProdDurationReg001Dto> dtoList) {
		if (StringUtil.isEmptyList(dataList)) {
            return;
        }
        int maxDataCountOneTime = this.parsToInt(reportSelectOrDeleteCount, "2");
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
        	dtoList.addAll(prodDurationReg001Mapper.selectAllDataByList(dataList));
            return;
        }

        List<ProdDurationReg001Dto> newDataList = new ArrayList<ProdDurationReg001Dto>();
        newDataList.addAll(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
        	dtoList.addAll(prodDurationReg001Mapper.selectAllDataByList(newDataList.subList(0, maxDataCountOneTime)));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
        	dtoList.addAll(prodDurationReg001Mapper.selectAllDataByList(newDataList));
        }
	}
	/**
	 * 处理查询前的参数 <功能详细描述>
	 *
	 * @param pageParam
	 * @see
	 * @since 1.0
	 */
	public void dealSearchParam(Map<String, Object> pageParam) {
		// 选择行数据时
		if (pageParam.containsKey("keyList")) {
			String keyList = pageParam.get("keyList").toString();
			try {
				List<ProdDurationReg001Dto> prodDurationList = JsonUtils.json2List(keyList,
						ProdDurationReg001Dto.class);
				pageParam.put("prodDurationList", prodDurationList);
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
			String regCode = (String) pageParam.get("regCode");
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
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(submitFeedbackStatus)) {
				pageParam.put("submitFeedbackStatusList", StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
			}
			// 反馈状态
			String status = (String) pageParam.get("status");
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(status)) {
				pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
			}
		}
	}


	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void buildExportParam(ExportParam exportParam) {
		Map<String, Object> pageParam = exportParam.getSelectParam();
        String pageIds = (String)pageParam.get("pageIds");
        if(StringUtil.isNotEmpty(pageIds)) {
            List<String> pageNames =  Arrays.asList(pageIds.split(GlobalConstant.COMMA));
            pageParam.replace("pageIds", pageIds, pageNames);
        }
        else{
            pageParam.replace("pageIds", pageIds, null);
        }

        // 导出方式flg
        String exportMethodFlg = (String)pageParam.get("exportType");

        dealSearchParam(pageParam);
        // 导出（未选中数据）
        if (StringUtil.equals(exportMethodFlg, "allExport")) {
            // 设置参数
            String configFile = null;
            // 导出中债excel
            configFile = "/com/joyintech/tams/svreport/chb/proddurationreg/conf/export_conf.xml";

            exportParam.config(configFile).select(() -> prodDurationReg001Mapper.selectAllData(pageParam));
        }
        // 将选择的数据导出成excel
        else if (StringUtil.equals(exportMethodFlg, "selectExport")) {
        	List<ProdDurationReg001Dto> dataList =  (List<ProdDurationReg001Dto>) pageParam.get("prodDurationList");// 查询参数列表
        	String configFile = "/com/joyintech/tams/svreport/chb/proddurationreg/conf/export_conf.xml";
			exportParam.config(configFile).select(() -> prodDurationReg001Mapper.queryExportListByKeys(dataList));
       }
        // 将选择的数据导出成excel删除模板
        else if (StringUtil.equals(exportMethodFlg, "deleteExport")) {
        	List<ProdDurationReg001Dto> dataList =  (List<ProdDurationReg001Dto>) pageParam.get("prodDurationList");// 查询参数列表
        	String configFile = "/com/joyintech/tams/svreport/chb/proddurationreg/conf/export_conf_del.xml";
			exportParam.config(configFile).select(() -> prodDurationReg001Mapper.queryExportListByKeys(dataList));
        }
	}


    public List<ProdDurationReg001Dto>  selectAllData(Map<String, Object> pageParam){
        return prodDurationReg001Mapper.selectAllData(pageParam);
    }

    public List<ProdDurationReg001Dto> queryExportListByKeys(List<ProdDurationReg001Dto> prodDurationRegistList){
        return prodDurationReg001Mapper.queryExportListByKeys(prodDurationRegistList);
    }
       
    @Override
    public com.joyintech.framework.grid.Page<ProdDurationReg001Dto> selectPageData(Map<String, Object> pageParam) {
//        List<ProdDurationReg001Dto> data = null;
        String exportMethodFlg = (String)pageParam.get("exportType");
      //update by leipeng 20191221 修改导出为框架导出
        Page<ProdDurationReg001Dto> page = null; 
        if (StringUtil.equals(exportMethodFlg, "allExport")) {
//            data =  restTemplate.postForList(
//                    SvrServiceApplication.CHB_SERVICE_NAME + "/ProdDurationReg/selectAllData",pageParam,
//                    ProdDurationReg001Dto.class);
//            page = selectForPage(pageParam);

			page = selectForPage(pageParam);

        }  else {
            @SuppressWarnings("unchecked")
            List<ProdDurationReg001Dto> dataList =  (List<ProdDurationReg001Dto>) pageParam.get("prodDurationList");
            
            int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
	        int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
           page = queryForPage(
					pagenum,legth,()->prodDurationReg001Mapper.queryExportListByKeys(dataList));
//            data =  restTemplate.postForList(
//                        SvrServiceApplication.CHB_SERVICE_NAME + "/ProdDurationReg/queryExportListByKeys",dataList,
//                        ProdDurationReg001Dto.class);
        }
//        com.joyintech.framework.grid.Page<ProdDurationReg001Dto> page = new com.joyintech.framework.grid.Page<ProdDurationReg001Dto>();
//        page.setRoot(data);
//        page.setTotal(data.size());
        return  page;
    }

    /**
     * 手动处理
     * @param map 
     */
	@SuppressWarnings("unchecked")
	public void updateSumStaus(Map<String, Object> map) {
		dealSearchParam(map);
		List<ProdDurationReg001Dto> prodDurationList = new ArrayList<ProdDurationReg001Dto>();
		//选择的手动处理
		prodDurationList = (List<ProdDurationReg001Dto>) map.get("prodDurationList");
        if (StringUtil.isEmptyList(prodDurationList)) {
        	//未选中数据
			prodDurationList = prodDurationReg001Mapper.selectAllData(map);
		}
		 

        // 报送状态为接口报送且反馈状态为发送失败和中债退回的数据 该部分数据的报送流水号列表
        List<String> registSeqList = new ArrayList<String>();
        Iterator<ProdDurationReg001Dto> it=prodDurationList.iterator();
        while(it.hasNext()){
        	ProdDurationReg001Dto m =  it.next();
			// modify by zhangliang 20210309 手工处理与前台逻辑一致：只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理 start
//			if(m.getSendStatus().equals("01")){
//				registSeqList.add(m.getRegistSeqno());
//			}else{
//				it.remove();
//			}
			registSeqList.add(m.getRegistSeqno());
			// modify by zhangliang 20210309 手工处理与前台逻辑一致：只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理 end
		}
        // 报送状态为接口报送且反馈状态为发送失败和中债退回的数据 更新反馈状态
        if (!StringUtil.isEmptyList(registSeqList)) {
            // 分批处理
            int maxDataCountOneTime = 300;
            int dataCount = registSeqList.size();
            if (dataCount <= maxDataCountOneTime) {
            	prodDurationReg001Mapper.updateSubmitStatusToOk(registSeqList);
            }
            else {
                List<String> newDataList = new ArrayList<String>();
                newDataList.addAll(registSeqList);

                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                	prodDurationReg001Mapper.updateSubmitStatusToOk(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                	prodDurationReg001Mapper.updateSubmitStatusToOk(newDataList);
                }
            }
        }
		this.updateInterfSubmitAndSeq(DictCodeListExt2.报送状态_手工处理.getValue(), prodDurationList, false);
	}

	/**
	 * 分批次执行updateInterfSubmitAndSeq
	 *
	 * @param prodDurationList
	 *            数据
	 * @see
	 * @since 1.0
	 */
	private void updateInterfSubmitAndSeq(String sendStatus, List<ProdDurationReg001Dto> prodDurationList, boolean isSendAllData) {
		if (StringUtil.isEmptyList(prodDurationList)) {
			return;
		}
		int maxDataCountOneTime = reportSelectOrDeleteCountForShenFen;
		int dataCount = prodDurationList.size();
		if (dataCount <= maxDataCountOneTime) {
			//已报送数据进留痕表
			prodDurationReg001Mapper.insertReportHis(prodDurationList);
            //删除正式表已报送数据
			prodDurationReg001Mapper.deleteLastSubmitRegist(prodDurationList);
			prodDurationReg001Mapper.updateInterfSubmitAndSeqBatch(sendStatus, prodDurationList);
			return;
		}

		// 对于批处理报送全量的数据，直接更新所有检查通过的未报送数据，状态修改为接口报送
		if (isSendAllData) {
		    logger.info("updateAllInterfSubmitAndSeqBatch全量更新登记表start===");
		  //已报送数据进留痕表
			prodDurationReg001Mapper.insertReportHis(prodDurationList);
            //删除正式表已报送数据
			prodDurationReg001Mapper.deleteLastSubmitRegist(prodDurationList);
		    prodDurationReg001Mapper.updateAllInterfSubmitAndSeqBatch(sendStatus);
		    logger.info("updateAllInterfSubmitAndSeqBatch全量更新登记表end===");
		    return;
		}

		List<ProdDurationReg001Dto> newDataList = new ArrayList<ProdDurationReg001Dto>();
		newDataList.addAll(prodDurationList);

		// 分批处理
		int part = dataCount / maxDataCountOneTime; // 分批数
		for (int i = 0; i < part; i++) {
			logger.info("单次更新登记表start===");
			//已报送数据进留痕表
			prodDurationReg001Mapper.insertReportHis(newDataList.subList(0, maxDataCountOneTime));
            //删除正式表已报送数据
			prodDurationReg001Mapper.deleteLastSubmitRegist(newDataList.subList(0, maxDataCountOneTime));
			prodDurationReg001Mapper.updateInterfSubmitAndSeqBatch(sendStatus,
                newDataList.subList(0, maxDataCountOneTime));
			newDataList.subList(0, maxDataCountOneTime).clear();
			logger.info("单次更新登记表end===");
		}
		// 最后剩下的数据
		if (CollectionUtils.isNotEmpty(newDataList)) {
			logger.info("单次更新登记表start===");
			//已报送数据进留痕表
			prodDurationReg001Mapper.insertReportHis(newDataList);
            //删除正式表已报送数据
			prodDurationReg001Mapper.deleteLastSubmitRegist(newDataList);
			prodDurationReg001Mapper.updateInterfSubmitAndSeqBatch(sendStatus, newDataList);
			logger.info("单次更新登记表end===");
		}
	}
	
	/**
	 * 已手工处理的数据将报送状态改为未报送
	 *
	 * @param map
	 *            传入主键
	 * @see
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
	public String cancelMannul(Map<String, Object> map) {
		dealSearchParam(map);
		List<ProdDurationReg001Dto> prodDurationList = new ArrayList<ProdDurationReg001Dto>();
		//选择的手动处理
		prodDurationList = (List<ProdDurationReg001Dto>) map.get("prodDurationList");
        if (StringUtil.isEmptyList(prodDurationList)) {
        	//未选中数据
        	map.put("sendStatus", "03");
			prodDurationList = prodDurationReg001Mapper.selectAllData(map);
		}
		// 报送状态为接口报送且反馈状态为发送失败和中债退回的数据 该部分数据的报送流水号列表
        List<String> registSeqList = new ArrayList<String>();
		for(ProdDurationReg001Dto m : prodDurationList){
			registSeqList.add(m.getRegistSeqno());
		}
		if (!StringUtil.isEmptyList(registSeqList)) {
			//找sub表有没有未报送的数据
			List<String> unregList = prodDurationReg001Mapper.selectRegistSeqnoList(prodDurationList,"01");
			if(!CollectionUtils.isEmpty(unregList)){
				return "fail";
			}
            // 分批处理
            int maxDataCountOneTime = 300;
            int dataCount = registSeqList.size();
            if (dataCount <= maxDataCountOneTime) {
            	 // 将履历表中履历号最大的数据挪回登记表
            	prodDurationReg001Mapper.insertReportDataFromHis(prodDurationList);
                // 删除履历表中移除数据
            	prodDurationReg001Mapper.deleteLastSubmitHis(prodDurationList);
            	prodDurationReg001Mapper.updateProdDurationRegist(registSeqList);
            }
            else {
                List<String> newDataList = new ArrayList<String>();
                newDataList.addAll(registSeqList);

                // 分批处理
                int part = dataCount / maxDataCountOneTime; // 分批数
                for (int i = 0; i < part; i++ ) {
                	 // 将履历表中履历号最大的数据挪回登记表
                	prodDurationReg001Mapper.insertReportDataFromHis(prodDurationList);
                    // 删除履历表中移除数据
                	prodDurationReg001Mapper.deleteLastSubmitHis(prodDurationList);
                	prodDurationReg001Mapper.updateProdDurationRegist(newDataList.subList(0, maxDataCountOneTime));
                    newDataList.subList(0, maxDataCountOneTime).clear();
                }
                // 最后剩下的数据
                if (CollectionUtils.isNotEmpty(newDataList)) {
                	 // 将履历表中履历号最大的数据挪回登记表
                	prodDurationReg001Mapper.insertReportDataFromHis(prodDurationList);
                    // 删除履历表中移除数据
                	prodDurationReg001Mapper.deleteLastSubmitHis(prodDurationList);
                	prodDurationReg001Mapper.updateProdDurationRegist(newDataList);
                }
            }
        }
		// 将履历表中履历号最大的数据挪回登记表
//		prodDurationReg001Mapper.insertInvestorsHoldInfoRegist(list);
		return "success";
	}	 /**
	 /**
     * 数据检查
     * @param checkKeys 检查数据的关键字
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public Map<String,Object> checkData(Map<String, Object> param){
      
        List<ProdDurationReg001Dto> equityInfoRegistList = new ArrayList<ProdDurationReg001Dto>();
        param.put("submitStatus", DictCodeListExt2.报送状态_未报送.getValue());
        param.put("deptCode", CommonUtil.getDeptCode());
        List<String> keyList = new ArrayList<String>();
		Map<String,Object> returnMap = new HashMap<>();
        if(param.get("checkKeys")!=null){
        	keyList=StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("checkKeys"));
        	param.put("registList", keyList);
        }
        Map<String,ProdDurationReg001Dto> repmap = new HashMap<String,ProdDurationReg001Dto>();
        dealSearchParam(param);
        equityInfoRegistList= prodDurationReg001Mapper.selectAllData(param);
		dealResultlist(equityInfoRegistList);
        List<String> checkKeys = new ArrayList<String>();
        List<ExcelValidDto> checkResult = new ArrayList<>();
		if(CollectionUtils.isNotEmpty(equityInfoRegistList)){
			repmap = equityInfoRegistList.stream().collect(Collectors.toMap(ProdDurationReg001Dto::getRegistSeqno,(p)->p,(key1,key2)->key1));
			for(ProdDurationReg001Dto dto : equityInfoRegistList){
				checkKeys.add(dto.getRegistSeqno());
				// add by zhangliang 此处逻辑处理是因为直连报送时生产的xml文件要求yyyy-MM-dd格式，而导出excel手工报送要求yyyyMMdd格式  start
				if (StringUtil.isNotEmpty(dto.getVdate())){
					dto.setVdate(DateUtil.format(DateUtil.parse(dto.getVdate())));
				}
				if (StringUtil.isNotEmpty(dto.getMdate())){
					dto.setMdate(DateUtil.format(DateUtil.parse(dto.getMdate())));
				}
				// add by zhangliang 此处逻辑处理是因为直连报送时生产的xml文件要求yyyy-MM-dd格式，而导出excel手工报送要求yyyyMMdd格式  end
			}
		}else{
			if (CollectionUtils.isEmpty(equityInfoRegistList)) {
	            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
				returnMap.put("excelValidDtoList",checkResult);
	            return returnMap;
	        }
		}
        // 调用检查方法
		Map<String,CheckReport001Dto> checkmap = new HashMap<String,CheckReport001Dto>();
			if(CollectionUtils.isNotEmpty(checkKeys)){
				List<CheckReport001Dto> resultlist = check(null);
				checkmap = resultlist.stream().collect(Collectors.toMap(CheckReport001Dto::getRefseqno,(p)->p,(key1,key2)->key1));
				for(String key : checkKeys){
					if(checkmap.get(key)!=null){
						ExcelValidDto exc=new ExcelValidDto();
						exc.setCheckResult(checkmap.get(key).getCheckResult());
						exc.setErrorInfo(checkmap.get(key).getErrorInfo());
						exc.setWarnInfo(checkmap.get(key).getWarnInfo());
						exc.setValidKey(checkmap.get(key).getValidKey());
						checkResult.add(exc);
					} else {
						ExcelValidDto exc=new ExcelValidDto();
						exc.setValidKey("产品代码:"+repmap.get(key).getProdId() + (StringUtil.isNotEmpty(repmap.get(key).getMdate()) ? ";业务结束日:"+repmap.get(key).getMdate() : ""));
						exc.setCheckResult("01");
						checkResult.add(exc);
					}
				}
				returnMap.put("excelValidDtoList",checkResult);
			}
		returnMap.put("resultList",equityInfoRegistList);
		return returnMap;
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
		checkReport001Mapper.delReportDatasByCodeAndDate(DictCodeListExt2.报表名称_产品存续期登记.getValue(),checkdate);
		//增加例外情况过滤
        List<ChbCheckRuleout> listRuleout=chbCheckRuleoutMapper.selectDataByCheckTypeAndReprotCode(CheckConfConstants.CHB_CHECK_TYPE_B, CheckConfConstants.CHB_PRD_DEAL_SUB);
        Map<String, List<ChbCheckRuleout>> mapRuleout = listRuleout.stream().collect(
                 JoyinCollectors.groupingBy(x -> x.getProdId()));
        //检查数据
        List<CheckReport001Dto> resultList =new ArrayList<CheckReport001Dto>();
        resultList.addAll(beforeSubErrorCheck.checkProdDurationReg(checkdate,null,mapRuleout));
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
     * 配置项中非空处理
     * @param count
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
}
