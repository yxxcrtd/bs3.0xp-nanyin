/*
 * 文件名：ChbBotAstPositionRegist001Service.java
 * 版权：Copyright 2016-2017 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管业务监管报送平台
 * 修改人：zhangzhongliang
 * 修改时间：2018年5月8日
 * 修改内容：新建
 * 系统名称：理财资管业务监管报送平台
 */

package com.joyintech.tams.svreport.chb.botastpositionregist.service;


import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.BaseMapper;
import com.joyintech.tams.common.svreport.chb.constant.CheckConfConstants;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbCheckRuleoutMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbCheckRuleout;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.service.SvrCommonExportService;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPosSubHisMapper;
import com.joyintech.tams.svreport.chb.botastpositionregist.dao.ChbBotAstPositionRegist001Mapper;
import com.joyintech.tams.svreport.chb.botastpositionregist.dto.ChbBotAstPositionRegist001Dto;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubCheck;
import com.joyintech.tams.svreport.chb.datacheck.beforesub.service.BeforeSubErrorCheck;
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
 * 底层资产持仓信息登记service
 *
 * @author zhangzhongliang
 * @version 1.0 2019年2月24日
 * @see ChbBotAstPositionRegist001Service
 * @since 1.0
 */
@Service(value="svrChbBotAstPositionRegist001Service")
public class ChbBotAstPositionRegist001Service extends SvrCommonExportService<ChbBotAstPositionRegist001Dto> {

	/**
	 * 投资者持有信息登记mapper
	 */
	@Autowired
	private ChbBotAstPositionRegist001Mapper chbBotAstPositionRegist001Mapper;

	@Autowired
	private ChbBotAstPosSubHisMapper chbBotAstPosSubHisMapper;
	
	@Autowired
	private CheckReport001Mapper checkReport001Mapper;
	
	@Autowired
	private ChbCheckRuleoutMapper chbCheckRuleoutMapper;
	
	@Autowired
	private BeforeSubCheck beforeSubCheck;
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
    @SuppressWarnings("unused")
    private static final String IS_SUCESS_INVESTORS_CHECK = "01";

    private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private BeforeSubErrorCheck beforeSubErrorCheck;

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
	public Page<ChbBotAstPositionRegist001Dto> searchPage(Map<String, Object> pageParam) {
		dealSearchParam(pageParam);
		if(CommonUtil.isBAmodel()){
			pageParam.put("model_type", "01");
		}
        if (pageParam.get(GlobalConstant.PARAM_PAGE) != null
                && pageParam.get(GlobalConstant.PARAM_LENGTH) != null){
    		int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
            int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
    		Page<ChbBotAstPositionRegist001Dto> page = queryForPage(
    				pagenum,legth,()->chbBotAstPositionRegist001Mapper.searchPage(pageParam));
    		dealResultlist(page.getRoot());
    		return page;
        } else {
            Page<ChbBotAstPositionRegist001Dto> page = queryForPage(
                    ChbBotAstPositionRegist001Mapper.class,"searchPage",pageParam);
            dealResultlist(page.getRoot());
            return page;
        }
	}

	private void dealResultlist(List<ChbBotAstPositionRegist001Dto> resultlist){
		if(!CollectionUtils.isEmpty(resultlist)){
			for(ChbBotAstPositionRegist001Dto dto : resultlist){
				if(StringUtil.isNotEmpty(dto.getSubmitFeedbackStatus())){
					dto.setSubmitFeedbackStatus(SvrDataUtil.getNameFromDataDict(DictCode.报送反馈状态,dto.getSubmitFeedbackStatus()));
				}
				//银行模式时,资产数据取当前机构的产品登记编码
				if(!CommonUtil.isBAmodel()){
					dto.setIssuePartyId(CommonUtil.selectBankCode());
				}
			}
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
				List<ChbBotAstPositionRegist001Dto> botAstPosList = JsonUtils.json2List(keyList,
						ChbBotAstPositionRegist001Dto.class);
				pageParam.put("botAstPosList", botAstPosList);
			} catch (IOException e) {
//				e.printStackTrace();
	            logger.error(e.getMessage(),e);
			}
		} else {
			// 查询参数条件
			// 数据状态
			String status = (String) pageParam.get("status");
			// 报送状态
			String sendStatus = (String) pageParam.get("sendStatus");
			// 反馈状态
			String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");


			// 将数据状态当做多选下拉框处理
			if (StringUtil.isNotBlank(status)) {
				pageParam.put("statusList", StringUtil.splitToList(GlobalConstant.COMMA, status));
			}
			// 将报送状态当做多选下拉框处理
			if (StringUtil.isNotBlank(sendStatus)) {
				pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
			}
			// 将反馈状态当做多选下拉框处理
			if (StringUtil.isNotBlank(submitFeedbackStatus)) {
				pageParam.put("submitFeedbackStatusList",
						StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
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
        dealSearchParam(pageParam);
        String pageIds = (String)pageParam.get("pageIds");
        if(StringUtil.isNotEmpty(pageIds)) {
            List<String> pageNames =  Arrays.asList(pageIds.split(GlobalConstant.COMMA));
            pageParam.replace("pageIds", pageIds, pageNames);
        }
        else{
            pageParam.replace("pageIds", pageIds, null);
        }

        // 导出方式flg
        String exportflag = (String)pageParam.get("exportflag");

        // 导出（未选中数据）
        if (StringUtil.equals(exportflag, "export")) {
            // 设置参数
            String flag = (String)pageParam.get("flag");
            String configFile = null;
            // 导出中债excel
            if (StringUtil.equals("0", flag)) {
                configFile = "/com/joyintech/tams/svreport/chb/botastpositionregist/conf/export_conf.xml";
            }
            else if(StringUtil.equals("1", flag)) {
                configFile = "/com/joyintech/tams/svreport/chb/botastpositionregist/conf/export_conf_all.xml";
            }
            else if(StringUtil.equals("2", flag)) {
            	configFile = "/com/joyintech/tams/svreport/chb/botastpositionregist/conf/export_conf_del.xml";
            }

            exportParam.config(configFile).select(() -> chbBotAstPositionRegist001Mapper.searchPage(pageParam));
        }
        // 将选择的数据导出成excel
        else if (StringUtil.equals(exportflag, "exportSelected")) {
        	List<ChbBotAstPositionRegist001Dto> dataList =  (List<ChbBotAstPositionRegist001Dto>) pageParam.get("botAstPosList");// 查询参数列表

            String flag = (String)pageParam.get("flag");
            String configFile = null;
            // 导出中债excel
            if (StringUtil.equals("0", flag)) {
                configFile = "/com/joyintech/tams/svreport/chb/botastpositionregist/conf/export_conf.xml";
            }
            else {
                configFile = "/com/joyintech/tams/svreport/chb/botastpositionregist/conf/export_conf_all.xml";
            }
            exportParam.config(configFile).select(() -> chbBotAstPositionRegist001Mapper.queryExportListByKeys(dataList));
        }

     // 将选择的数据导出成excel删除模板
        else if (StringUtil.equals(exportflag, "exportDel")) {
        	List<ChbBotAstPositionRegist001Dto> dataList =  (List<ChbBotAstPositionRegist001Dto>) pageParam.get("botAstPosList");// 查询参数列表
            String flag = (String)pageParam.get("flag");
            String configFile = null;

            if (StringUtil.equals("2", flag)) {
                configFile = "/com/joyintech/tams/svreport/chb/botastpositionregist/conf/export_conf_del.xml";
            }
            exportParam.config(configFile).select(() -> chbBotAstPositionRegist001Mapper.queryExportListByKeys(dataList));
        }
    }



    public List<ChbBotAstPositionRegist001Dto> searchPageExport(Map<String,Object> map){
     return chbBotAstPositionRegist001Mapper.searchPage(map);
   }
   
   public List<ChbBotAstPositionRegist001Dto> queryExportListByKeys(List<ChbBotAstPositionRegist001Dto> registList){
    return chbBotAstPositionRegist001Mapper.queryExportListByKeys(registList);
   }
   @Override
   public com.joyintech.framework.grid.Page<ChbBotAstPositionRegist001Dto> selectPageData(Map<String, Object> pageParam) {
	 //update by leipeng 20191221 修改导出为框架导出
	   Page<ChbBotAstPositionRegist001Dto> page = null;
    String exportMethodFlg = (String)pageParam.get("exportflag");
    
    String model_type = "";
    
    if(CommonUtil.isBAmodel()){
		pageParam.put("model_type", "01");
		model_type = "01";
	}
    
    if (StringUtil.equals(exportMethodFlg, "export")) {
//        data =  restTemplate.postForList(
//                    SvrServiceApplication.CHB_SERVICE_NAME + "/ChbBotAstPositionRegist/searchPageExport",pageParam,
//                    ChbBotAstPositionRegist001Dto.class);
		page = searchPage(pageParam);
    }  else {
           @SuppressWarnings("unchecked")
           List<ChbBotAstPositionRegist001Dto> dataList =  (List<ChbBotAstPositionRegist001Dto>) pageParam.get("botAstPosList");
           
           int pagenum = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_PAGE).toString());
           int legth = Integer.parseInt(pageParam.get(GlobalConstant.PARAM_LENGTH).toString());
   		 
           if(CommonUtil.isBAmodel()){
        	   page = queryForPage(
          				pagenum,legth,()->chbBotAstPositionRegist001Mapper.queryExportListByKeysEx(dataList,"01"));
           }else{
        	   page = queryForPage(
         				pagenum,legth,()->chbBotAstPositionRegist001Mapper.queryExportListByKeys(dataList));
           }
   		dealResultlist(page.getRoot());
           
//           
//           data =  restTemplate.postForList(
//                        SvrServiceApplication.CHB_SERVICE_NAME + "/ChbBotAstPositionRegist/queryExportListByKeys",dataList,
//                        ChbBotAstPositionRegist001Dto.class);
    }
//    com.joyintech.framework.grid.Page<ChbBotAstPositionRegist001Dto> page = new com.joyintech.framework.grid.Page<ChbBotAstPositionRegist001Dto>();
//    page.setRoot(data);
//    page.setTotal(data.size());
       return  page;
   }
    /**
	 * <一句话功能简述> <功能详细描述>
	 *
	 * @param map
	 * @see
	 * @since 1.0
	 */
	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor = Exception.class)
	public void updateSumStaus(Map<String, Object> map) {
		dealSearchParam(map);
		List<ChbBotAstPositionRegist001Dto> chbBotAstPositionRegistListOrg  = new ArrayList<ChbBotAstPositionRegist001Dto>();
		chbBotAstPositionRegistListOrg = (List<ChbBotAstPositionRegist001Dto>)map.get("botAstPosList");
		if(StringUtil.isEmptyList(chbBotAstPositionRegistListOrg)){
			//按条件查询全部
			chbBotAstPositionRegistListOrg = chbBotAstPositionRegist001Mapper.searchPage(map);
		}
		
        List<ChbBotAstPositionRegist001Dto> chbBotAstPositionRegistList = new ArrayList<ChbBotAstPositionRegist001Dto>();
        for(ChbBotAstPositionRegist001Dto m : chbBotAstPositionRegistListOrg){
			// modify by zhangliang 20210309 手工处理与前台逻辑一致：只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理 start
//            // 手工处理只处理未报送的
//            if (DictCodeListExt2.报送状态_未报送.getValue().equals(m.getSendStatus())){
//            	chbBotAstPositionRegistList.add(m);
//            }
			chbBotAstPositionRegistList.add(m);
			// modify by zhangliang 20210309 手工处理与前台逻辑一致：只能选择报送状态为未报送、或报送状态为接口报送且反馈状态为发送失败和中债退回的数据进行手工处理 end
        }
		
		if(!CollectionUtils.isEmpty(chbBotAstPositionRegistList)){
			// 每批处理的数据条数
			int partSize = 500;
			// 存放业务数据的临时list，数据量不超过partSize
			List<ChbBotAstPositionRegist001Dto> tmpList = new ArrayList<ChbBotAstPositionRegist001Dto>();
			// 分批次处理
			for (int j = 0; j < chbBotAstPositionRegistList.size(); j++) {
				tmpList.add(chbBotAstPositionRegistList.get(j));
				if (partSize == tmpList.size() || j == chbBotAstPositionRegistList.size() - 1) {
					//找sub表有没有已报送的数据
					List<String> regList = chbBotAstPositionRegist001Mapper.selectRegistSeqnoList(tmpList,"02");
					//更新sub表数据（中债通过、手工处理）
					this.updateInterfSubmitAndSeq(DictCodeListExt2.报送状态_手工处理.getValue(), tmpList);
//		        	if(isupdateHis){
		        		if(!CollectionUtils.isEmpty(regList)){
		    				List<List<String>> regseqnolist = createStringList(regList,20);
		    				for(int i=0;i<regseqnolist.size();i++){
		    					//将已报送的数据移至his表 insert select 
		    					chbBotAstPosSubHisMapper.insertSubHisBatch(regseqnolist.get(i));
		    					//将已报送的数据从sub表物理删除
		    					chbBotAstPositionRegist001Mapper.deleteRegistSeqnoList(regseqnolist.get(i));
		    				}
		    			}
//		        	}
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
	@Transactional(rollbackFor = Exception.class)
	public void updateInterfSubmitAndSeq(String sendStatus, List<ChbBotAstPositionRegist001Dto> investList) {
		if (StringUtil.isEmptyList(investList)) {
			return;
		}
		int maxDataCountOneTime = reportSelectOrDeleteCountForShenFen;
		int dataCount = investList.size();
		if (dataCount <= maxDataCountOneTime) {
			chbBotAstPositionRegist001Mapper.updateInterfSubmitAndSeqBatch(sendStatus, investList);
			return;
		}

		List<ChbBotAstPositionRegist001Dto> newDataList = new ArrayList<ChbBotAstPositionRegist001Dto>();
		newDataList.addAll(investList);

		// 分批处理
		int part = dataCount / maxDataCountOneTime; // 分批数
		for (int i = 0; i < part; i++) {
			logger.info("单次更新登记表start===");
			chbBotAstPositionRegist001Mapper.updateInterfSubmitAndSeqBatch(sendStatus,
                newDataList.subList(0, maxDataCountOneTime));
			newDataList.subList(0, maxDataCountOneTime).clear();
			logger.info("单次更新登记表end===");
		}
		// 最后剩下的数据
		if (CollectionUtils.isNotEmpty(newDataList)) {
			logger.info("单次更新登记表start===");
			chbBotAstPositionRegist001Mapper.updateInterfSubmitAndSeqBatch(sendStatus, newDataList);
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
	@Transactional(rollbackFor = Exception.class)
	public String cancelMannul(Map<String, Object> map) {
		dealSearchParam(map);
		List<ChbBotAstPositionRegist001Dto> list = new ArrayList<ChbBotAstPositionRegist001Dto>();
		if(map.get("flag") != null && "01".equals(map.get("flag"))){
			list = chbBotAstPositionRegist001Mapper.searchPage(map);
		}else{
			list = (List<ChbBotAstPositionRegist001Dto>) map.get("botAstPosList");
		}
		
		if(!CollectionUtils.isEmpty(list)){
        	//找sub表有没有未报送的数据
			List<String> unregList = chbBotAstPositionRegist001Mapper.selectRegistSeqnoList(list,"01");
			if(!CollectionUtils.isEmpty(unregList)){
				return "fail";
			}
        	//查询his表，取最新的已报送数据
 			List<String> seqnolist = chbBotAstPosSubHisMapper.selectByprodId(list);
 			chbBotAstPositionRegist001Mapper.cancelMannultRegist(list);
        	if(!CollectionUtils.isEmpty(seqnolist)){
				List<List<String>> regList = createStringList(seqnolist, 20);
				for(int i=0;i<regList.size();i++){
					//将his表数据移至sub表
					chbBotAstPositionRegist001Mapper.insertSeletForSub(regList.get(i));
					//his表数据物理删除
					chbBotAstPosSubHisMapper.deleteBySeqno(regList.get(i));
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
	
	public List<String> getCheckRepList(Map<String, Object> param) {
		List<String> keylist = new ArrayList<String>();
		dealSearchParam(param);
		List<ChbBotAstPositionRegist001Dto> list = chbBotAstPositionRegist001Mapper.searchPage(param);
		if(CollectionUtils.isNotEmpty(list)){
			for(ChbBotAstPositionRegist001Dto dto : list){
				keylist.add(dto.getRegistSeqno());
			}
		}
		return keylist;
	}
	
	public Map<String,Object> checkData(List<String> checkKeys){
		Map<String,CheckReport001Dto> checkmap = new HashMap<String,CheckReport001Dto>();
		Map<String,ChbBotAstPositionRegist001Dto> repmap = new HashMap<String,ChbBotAstPositionRegist001Dto>();
        Map<String,Object> returnMap = new HashMap<>();
		List<ChbBotAstPositionRegist001Dto> issuelist = chbBotAstPositionRegist001Mapper.selectByRegistNo(checkKeys);
		dealResultlist(issuelist);
		repmap = issuelist.stream().collect(Collectors.toMap(ChbBotAstPositionRegist001Dto::getRegistSeqno,(p)->p,(key1,key2)->key1));
		List<ExcelValidDto> result = new ArrayList<ExcelValidDto>();
		// 查询检查表
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
					exc.setValidKey("底层资产/负债编码:"+repmap.get(key).getAssetCode());
					exc.setCheckResult("01");
					result.add(exc);
				}
			}
            returnMap.put("excelValidDtoList",result);
		}
        returnMap.put("resultList",issuelist);
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
		checkReport001Mapper.delReportDatasByCodeAndDate(DictCodeListExt2.报表名称_资管计划底层持仓信息登记.getValue(),checkdate);
		//增加例外情况过滤
        List<ChbCheckRuleout> listRuleout=chbCheckRuleoutMapper.selectDataByCheckTypeAndReprotCode(CheckConfConstants.CHB_CHECK_TYPE_B, CheckConfConstants.CHB_BOT_AST_POS_SUB);
        Map<String, List<ChbCheckRuleout>> mapRuleout = listRuleout.stream().collect(
                 JoyinCollectors.groupingBy(x -> x.getProdId()));
		//检查数据
		List<CheckReport001Dto> resultList = new ArrayList<CheckReport001Dto>();
		//报送前强制的错误检查
		resultList.addAll(beforeSubErrorCheck.checkChbBotAstReg(checkdate,null,mapRuleout));
        //检查数据
		resultList.addAll(beforeSubCheck.checkBotAstReg(checkdate,mapRuleout));
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
	
}
