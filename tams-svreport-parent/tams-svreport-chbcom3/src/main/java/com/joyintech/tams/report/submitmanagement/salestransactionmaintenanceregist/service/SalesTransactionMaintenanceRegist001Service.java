package com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.service;

import com.alibaba.fastjson.JSON;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.datasource.DataSource;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.system.batch.batchstart.service.SysBatchStart001Service;
import com.joyintech.tams.common.base.dao.ChbBatchParaMapper;
import com.joyintech.tams.common.base.entity.ChbBatchPara;
import com.joyintech.tams.common.base.entity.ChbSubmitStatus;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.report.common.constant.BatchBeanName;
import com.joyintech.tams.report.common.constant.SvrServiceApplication;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.DateCalcUtil;
import com.joyintech.tams.report.common.util.ReportGlobalConstant;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.interf.cnbond.service.CnBondInterfService;
import com.joyintech.tams.report.submitmaintenance.salestransactionmaintenance.validate.SalesTransactionMaintenanceValid;
import com.joyintech.tams.report.submitmanagement.checkreport.dao.CheckReport001Mapper;
import com.joyintech.tams.report.submitmanagement.checkreport.dto.CheckReport001Dto;
import com.joyintech.tams.report.submitmanagement.equityinforegist.common.EquityInfoRegistCommonUtil;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dao.SalesTransactionMaintenanceRegist001Mapper;
import com.joyintech.tams.report.submitmanagement.salestransactionmaintenanceregist.dto.SalesTransactionMaintenanceRegist001Dto;
import com.joyintech.tams.report.system.subinfobatchlog.dto.ChbSubinfoBatchLog;
import com.joyintech.tams.systemtools.constant.DataIndexSequenceType;
import com.joyintech.tams.systemtools.util.CommonUtil;
import com.joyintech.tools.collectors.JoyinCollectors;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author Administrator
 * @version 1.0 2017年9月19日
 * @see SalesTransactionMaintenanceRegist001Service
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SalesTransactionMaintenanceRegist001Service extends CommonExportService<SalesTransactionMaintenanceRegist001Dto>{

    @Autowired
    private SalesTransactionMaintenanceRegist001Mapper salesTransactionMaintenanceRegist001Mapper;
    
    @Autowired
    private CnBondInterfService cnBondInterfService;

    /**
     * CheckReport001Mapper
     */
    @Autowired
    private CheckReport001Mapper checkReport001Mapper;
    
    @Autowired
    private ChbBatchParaMapper chbBatchParaMapper;
    
    @Autowired
    private SysBatchStart001Service sysBatchStart001Service;
    
    /**
     * 长sql最大一次插入或者更新条数
     */
    @Value("${application.reportInsertLongCount}")
    public  int reportInsertLongCount;
    /**
     * sql最大一次最大删除条数
     */
    @Value("${application.reportSelectOrDeleteCount}")
    public  int reportSelectOrDeleteCount;
    /**
     * 短sql最大一次最大插入条数
     */
    @Value("${application.reportInsertCount}")
    public  int reportInsertCount;
    /**
     * CheckReport001Mapper
     */

    /**
     * 短sql最大一次最大插入条数
     */
    @Value("${application.reportSelectOrDeleteCountForShenFen}")
    public  int reportSelectOrDeleteCountForShenFen;
    private static final String IS_CHECK_SUCESS="01";

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 分页查询
     * @param param 查询条件
     * @return 返回分页信息
     * @see
     * @since 1.0
     */
    public Page<SalesTransactionMaintenanceRegist001Dto> searchForPage(Map<String, Object> param){
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
	    int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        Page<SalesTransactionMaintenanceRegist001Dto> pageList = super.queryForPage(
        		pagenum,legth,()->salesTransactionMaintenanceRegist001Mapper.queryForRegistPage(param));
        return pageList;
    }

    /**
     * 启动批处理导出
     * @param param
     */
    public AjaxResult batchExportAndDoManual(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }

    /**
     * 启动批处理导出
     * @param param
     */
    public AjaxResult batchExport(Map<String, Object> param) {
        AjaxResult ajaxResult = new AjaxResult();
        return ajaxResult;
    }
    /**
     * 根据条件查询出需要导出的
     * @param param 输入条件
     * @return 查询得到的列表
     * @see
     * @since 1.0
     */
    public List<SalesTransactionMaintenanceRegist001Dto> queryExportRegistList(Map<String, Object> param){
        String dataStatus = (String)param.get("dataStatus");
        String sendStatus = (String)param.get("sendStatus");
        String submitFeedbackStatus = (String)param.get("submitFeedbackStatus");
        param.put("dataStatus", StringUtil.splitToList(GlobalConstant.COMMA, dataStatus));
        param.put("sendStatus", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
        param.put("submitFeedbackStatus", StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
        param.put("searchSubmitDeadlineStart", DateCalcUtil.getEndOfDay((String)param.get("searchSubmitDeadlineStart")));
        param.put("searchSubmitDeadlineEnd", DateCalcUtil.getEndOfDay((String)param.get("searchSubmitDeadlineEnd")));
        List<SalesTransactionMaintenanceRegist001Dto> pageList = salesTransactionMaintenanceRegist001Mapper.queryForRegistPage(param);
        return pageList;

    }

    /**
     * {@inheritDoc}
     */
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
        String exportMethodFlg = (String)pageParam.get("export_method_flg");

        // 导出（未选中数据）
        if (StringUtil.equals(exportMethodFlg, "export")) {
            // 设置参数
            String tradeNo = (String)pageParam.get("tradeNo");
            String institutionCode = (String)pageParam.get("institutionCode");
            String dataStatus = (String)pageParam.get("dataStatus");
            String sendStatus = (String)pageParam.get("sendStatus");
            String submitFeedbackStatus = (String)pageParam.get("submitFeedbackStatus");
            pageParam.put("institutionCodeList", StringUtil.splitToList(GlobalConstant.COMMA, institutionCode));
            pageParam.put("tradeNoList", StringUtil.splitToList(GlobalConstant.COMMA, tradeNo));
            pageParam.put("dataStatusList", StringUtil.splitToList(GlobalConstant.COMMA, dataStatus));
            pageParam.put("sendStatusList", StringUtil.splitToList(GlobalConstant.COMMA, sendStatus));
            pageParam.put("submitFeedbackStatusList", StringUtil.splitToList(GlobalConstant.COMMA, submitFeedbackStatus));
            pageParam.put("submitDeadLineStart", DateCalcUtil.getEndOfDay((String)pageParam.get("submitDeadLineStart")));
            pageParam.put("submitDeadLineEnd", DateCalcUtil.getEndOfDay((String)pageParam.get("submitDeadLineEnd")));

            String flag = (String)pageParam.get("flag");
            String configFile = null;
            // 导出中债excel
            if (StringUtil.equals("0", flag)) {
                configFile = "/com/joyintech/tams/report/submitmanagement/salestransactionmaintenanceregist/conf/export_conf.xml";
            }
            else if(StringUtil.equals("1", flag)) {
                configFile = "/com/joyintech/tams/report/submitmanagement/salestransactionmaintenanceregist/conf/export_conf_all.xml";
            }
            else if(StringUtil.equals("2", flag)) {
            	configFile = "/com/joyintech/tams/report/submitmanagement/salestransactionmaintenanceregist/conf/export_conf_del.xml";
            }

            exportParam.config(configFile).select(() -> salesTransactionMaintenanceRegist001Mapper.queryForRegist(pageParam));
        }
        // 将选择的数据导出成excel
        else if (StringUtil.equals(exportMethodFlg, "exportSelect")) {
            String exportKeys =(String) pageParam.get("expKeys");
            List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, exportKeys);
            List<SalesTransactionMaintenanceRegist001Dto> registList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
            SalesTransactionMaintenanceRegist001Dto SalesTransactionMaintenanceRegist001Dto = null;
            for (String string : keys) {
                SalesTransactionMaintenanceRegist001Dto = new SalesTransactionMaintenanceRegist001Dto();
                String[] key=StringUtil.split(string, ReportGlobalConstant.VERTICAL_LINE);
                SalesTransactionMaintenanceRegist001Dto.setInstitutionCode(key[0]);
                SalesTransactionMaintenanceRegist001Dto.setTradeNo(key[1]);
                SalesTransactionMaintenanceRegist001Dto.setSendStatus(key[2]);
                registList.add(SalesTransactionMaintenanceRegist001Dto);
            }

            String flag = (String)pageParam.get("flag");
            String configFile = null;
            // 导出中债excel
            if (StringUtil.equals("0", flag)) {
                configFile = "/com/joyintech/tams/report/submitmanagement/salestransactionmaintenanceregist/conf/export_conf.xml";
            }
            else {
                configFile = "/com/joyintech/tams/report/submitmanagement/salestransactionmaintenanceregist/conf/export_conf_all.xml";
            }

            exportParam.config(configFile).select(() -> salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(registList));
        }

     // 将选择的数据导出成excel删除模板
        else if (StringUtil.equals(exportMethodFlg, "exportDel")) {
            String exportKeys =(String) pageParam.get("expKeys");
            List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, exportKeys);
            List<SalesTransactionMaintenanceRegist001Dto> equiytRegistList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
            SalesTransactionMaintenanceRegist001Dto SalesTransactionMaintenanceRegist001Dto = null;
            for (String string : keys) {
                SalesTransactionMaintenanceRegist001Dto = new SalesTransactionMaintenanceRegist001Dto();
                String[] key=StringUtil.split(string, "|");
                SalesTransactionMaintenanceRegist001Dto.setInstitutionCode(key[0]);
                SalesTransactionMaintenanceRegist001Dto.setTradeNo(key[1]);
                SalesTransactionMaintenanceRegist001Dto.setSendStatus(key[2]);
                equiytRegistList.add(SalesTransactionMaintenanceRegist001Dto);
            }

            String flag = (String)pageParam.get("flag");
            String configFile = null;
            if (StringUtil.equals("2", flag)) {
                configFile = "/com/joyintech/tams/report/submitmanagement/equityinforegist/conf/export_conf_del.xml";
            }

            exportParam.config(configFile).select(() -> salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(equiytRegistList));
        }
    }

    public List<SalesTransactionMaintenanceRegist001Dto> queryForInfoRegist(Map<String,Object> param){
        return salesTransactionMaintenanceRegist001Mapper.queryForRegistPage(param);
    }


    public List<SalesTransactionMaintenanceRegist001Dto> queryExportListByKeys(List<SalesTransactionMaintenanceRegist001Dto> equityRegistList){
        return salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(equityRegistList);
    }

    /**
     * @param param 查询参数
     * @return 投资者登记信息列表
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    public List<SalesTransactionMaintenanceRegist001Dto> queryExportRegistDelList(Map<String, Object> param){
        String delKey = (String)param.get("deleteKeys");//删除的主键
 //       String flag = (String)param.get("flag");//是否设置为手工报送 01 设置 02 不设置
        String remark = (String)param.get("remark");//导出删除模板原因
        List<SalesTransactionMaintenanceRegist001Dto> registList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, delKey);
        List<SalesTransactionMaintenanceRegist001Dto> equiytRegistList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        SalesTransactionMaintenanceRegist001Dto SalesTransactionMaintenanceRegist001Dto = null;
        //报送状态表主键
        ChbSubmitStatus chbSubmitStatus = null;
        List<ChbSubmitStatus> listStatus = new ArrayList<ChbSubmitStatus>();
        for (String string : keys) {
            SalesTransactionMaintenanceRegist001Dto = new SalesTransactionMaintenanceRegist001Dto();
            String[] key=StringUtil.split(string, ReportGlobalConstant.VERTICAL_LINE);
            SalesTransactionMaintenanceRegist001Dto.setInstitutionCode(key[0]);
            SalesTransactionMaintenanceRegist001Dto.setTradeNo(key[1]);
            SalesTransactionMaintenanceRegist001Dto.setSendStatus(key[2]);
            equiytRegistList.add(SalesTransactionMaintenanceRegist001Dto);
            //报送状态表插入组装
            chbSubmitStatus = new ChbSubmitStatus();
            chbSubmitStatus.setSeqno(key[3]);
            chbSubmitStatus.setSubmitFeedbackStatus("03");
            listStatus.add(chbSubmitStatus);
        }
        if(CollectionUtils.isNotEmpty(equiytRegistList)){
            String submitStatus="";
            if("01".equals(param.get("flag"))){
                submitStatus = "03";
                //检查报送的数据中是否存在已报送数据
                List<SalesTransactionMaintenanceRegist001Dto> list = salesTransactionMaintenanceRegist001Mapper.queryRegistSubmitListByKeys(equiytRegistList);
                if(CollectionUtils.isNotEmpty(list)){
                    //讲第一次报送的数据数据履历表
                    salesTransactionMaintenanceRegist001Mapper.insertEquityRegistHis(list);
                    //删除已报送数据
                    salesTransactionMaintenanceRegist001Mapper.deleteSubmitRegist(list);
                }
                salesTransactionMaintenanceRegist001Mapper.insertSubmitStatus(listStatus);
            }

            if(StringUtil.isNotEmpty(submitStatus)||StringUtil.isNotEmpty(remark)){
                //先更新报送表，再将报送表的数据返回
                salesTransactionMaintenanceRegist001Mapper.updateExportRegDel(submitStatus,remark,equiytRegistList);
            }
            registList = salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(equiytRegistList);
            //将数据字典的值转换成如01 男这种格式
//            EquityInfoRegistCommonUtil.format(registList," ");
        	
        }
        return registList;
    }
    /**
     * <一句话功能简述> <功能详细描述>
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    public void updateSumStaus(Map<String, Object> map) {
        dealSearchParam(map);
        List<SalesTransactionMaintenanceRegist001Dto> chbRegistList  = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        if(map.get("flag") != null && "01".equals(map.get("flag"))){
            //按条件查询全部
            chbRegistList = salesTransactionMaintenanceRegist001Mapper.searchPage(map);
        }else{
            chbRegistList = (List<SalesTransactionMaintenanceRegist001Dto>)map.get("reportList");
        }
        Iterator<SalesTransactionMaintenanceRegist001Dto> it=chbRegistList.iterator();
        while(it.hasNext()){
            SalesTransactionMaintenanceRegist001Dto m =  it.next();
            if(!m.getSendStatus().equals("01")){
                it.remove();
            }
        }
        this.updateInterfSubmitAndSeq(DictCodeListExt2.报送状态_手工处理.getValue(), chbRegistList);
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
    public void updateInterfSubmitAndSeq(String sendStatus, List<SalesTransactionMaintenanceRegist001Dto> investList) {
        if (StringUtil.isEmptyList(investList)) {
            return;
        }
        int maxDataCountOneTime = reportSelectOrDeleteCountForShenFen;
        int dataCount = investList.size();
        if (dataCount <= maxDataCountOneTime) {
            //已报送数据进留痕表
            salesTransactionMaintenanceRegist001Mapper.insertIntoHis(investList);
            //删除正式表已报送数据
            salesTransactionMaintenanceRegist001Mapper.deleteLastSubmitRegist(investList);
            salesTransactionMaintenanceRegist001Mapper.updateInterfSubmitAndSeqBatch(sendStatus, investList);
            return;
        }
        List<SalesTransactionMaintenanceRegist001Dto> newDataList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        newDataList.addAll(investList);
        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++) {
            logger.info("单次更新登记表start===");
            //已报送数据进留痕表
            salesTransactionMaintenanceRegist001Mapper.insertIntoHis(investList);
            //删除正式表已报送数据
            salesTransactionMaintenanceRegist001Mapper.deleteLastSubmitRegist(investList);
            salesTransactionMaintenanceRegist001Mapper.updateInterfSubmitAndSeqBatch(sendStatus,
                    newDataList.subList(0, maxDataCountOneTime));
            newDataList.subList(0, maxDataCountOneTime).clear();
            logger.info("单次更新登记表end===");
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
            logger.info("单次更新登记表start===");
            //已报送数据进留痕表
            salesTransactionMaintenanceRegist001Mapper.insertIntoHis(investList);
            //删除正式表已报送数据
            salesTransactionMaintenanceRegist001Mapper.deleteLastSubmitRegist(investList);
            salesTransactionMaintenanceRegist001Mapper.updateInterfSubmitAndSeqBatch(sendStatus, newDataList);
            logger.info("单次更新登记表end===");
        }
    }
    /**
     * 已手工处理的数据将报送状态改为未报送
     *
     * @see
     * @since 1.0
     */
    @Transactional(rollbackFor = Exception.class)
    @SuppressWarnings("unchecked")
    public void cancelMannul(Map<String, Object> map) {
        dealSearchParam(map);
        List<SalesTransactionMaintenanceRegist001Dto> list = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        if(map.get("flag") != null && "01".equals(map.get("flag"))){
            list = salesTransactionMaintenanceRegist001Mapper.searchPage(map);
        }else{
            list = (List<SalesTransactionMaintenanceRegist001Dto>) map.get("reportList");
        }
        salesTransactionMaintenanceRegist001Mapper.cancelMannultRegist(list);
        // 将履历表中最后创建时间的数据挪回登记表
        salesTransactionMaintenanceRegist001Mapper.insertReportDataFromHis(list);
        //将挪回数据从履历表中删除
        salesTransactionMaintenanceRegist001Mapper.deleteReportDataHis(list);
    }

    /**
     * 数据检查
     * @param checkKeys 检查数据的关键字
     * @return 检查结果
     * @see
     * @since 1.0
     */
    public List<ExcelValidDto> checkData(String checkKeys){
        List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, checkKeys);
        keys.remove(StringUtil.EMPTY);
        List<SalesTransactionMaintenanceRegist001Dto> equiytRegistList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        List<SalesTransactionMaintenanceRegist001Dto> registList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        SalesTransactionMaintenanceRegist001Dto SalesTransactionMaintenanceRegist001Dto = null;
        if(CollectionUtils.isNotEmpty(keys)){
            for (String string : keys) {
                SalesTransactionMaintenanceRegist001Dto = new SalesTransactionMaintenanceRegist001Dto();
                String[] key=StringUtil.split(string, "|");
                SalesTransactionMaintenanceRegist001Dto.setInstitutionCode(key[0]);
                SalesTransactionMaintenanceRegist001Dto.setTradeNo(key[1]);
                SalesTransactionMaintenanceRegist001Dto.setSendStatus(key[2]);
                equiytRegistList.add(SalesTransactionMaintenanceRegist001Dto);}
        }
        this.queryExportListByKeys(equiytRegistList,registList);
        List<ExcelValidDto> checkResult = new ArrayList<>();
        if (CollectionUtils.isEmpty(registList)) {
            checkResult.add(new ExcelValidDto(StringUtil.EMPTY, String.valueOf(0), "无报送数据",null));
            return checkResult;
        }
    	
        checkResult = this.dealReportCheckData(registList, checkResult, false);
        return checkResult;
    }
    /**
     *
     * 处理报送前检查和数据检查的数据
     * @see
     * @since 1.0
     */
    public List<ExcelValidDto> dealReportCheckData(List<SalesTransactionMaintenanceRegist001Dto> registList,List<ExcelValidDto> checkResult
    		,boolean flag){
        int maxCount =reportInsertCount;
        boolean isDB2DataBase = CommonUtil.isDB2DataBase();

    	//存储已经批量进行过数据检查的数据
        List<String> isCheckedDataList = new ArrayList<>();
        //存储没有进行数据检查的数据
        List<SalesTransactionMaintenanceRegist001Dto> isNeedCheckDataList = new ArrayList<>();
        logger.info("检查报送流水号是否校验开始start");

        registList.forEach(dto -> {
        	if(!StringUtil.equals(ReportGlobalConstant.IS_UN_SUBMIT_CHECK,dto.getCheckSeqno())
        			&& StringUtil.isNotEmpty(dto.getCheckSeqno())){
                isCheckedDataList.add(dto.getCheckSeqno());
            }
            else{
                isNeedCheckDataList.add(dto);
            }
        });
        logger.info("检查报送流水号是否校验结束end");
        logger.info("已检查数据查询start");
        if(CollectionUtils.isNotEmpty(isCheckedDataList)){
          //查询分批处理（每次最多处理maxCount条）
            if(isCheckedDataList.size()<maxCount){
                checkResult = checkReport001Mapper.selectByCheckSeqno(isCheckedDataList);
            }
            else{
                int part = isCheckedDataList.size()/maxCount;
                for(int j=0; j<part; j++){
                    checkResult.addAll(checkReport001Mapper.selectByCheckSeqno(isCheckedDataList.subList(0, maxCount)));
                    isCheckedDataList.subList(0, maxCount).clear();
                }
                if(CollectionUtils.isNotEmpty(isCheckedDataList)){
                    checkResult.addAll(checkReport001Mapper.selectByCheckSeqno(isCheckedDataList));
                }
            }
        }
        logger.info("已检查送数据查询end");
        if(isDB2DataBase){
        	maxCount = 1;
        }
        if(CollectionUtils.isNotEmpty(isNeedCheckDataList)){
        	logger.info("数据check开始");
            List<ExcelValidDto> newCheckedInfo = SalesTransactionMaintenanceValid.checkData(registList,"1",null,null);
            logger.info("数据check结束");
            checkResult.addAll(newCheckedInfo);
            //组装结果集
            List<CheckReport001Dto> resultList = new ArrayList<>();
            //投资者身份信息
            List<SalesTransactionMaintenanceRegist001Dto> equityInfoRegistUpdateList = new ArrayList<>();
            logger.info("初次检查赋值start");
            newCheckedInfo.forEach(item->{
            	if(flag&&!IS_CHECK_SUCESS.equals(SysDataUtil.getCodeFromDataDictByName(DictCode.检查结果, item.getCheckResult()))){
                    CheckReport001Dto chbReportCheck = new CheckReport001Dto();
                    BeanUtil.copyPropertiesIgnoreNull(chbReportCheck, item);
//                  chbReportCheck.setCheckSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.检查序列号));
                    chbReportCheck.setCheckSeqno(IDGenerator.generate());
                    chbReportCheck.setRepCode(DictCodeListExt2.报表名称_投资者身份信息登记.getValue());
                    chbReportCheck.setRepName(DictCodeListExt2.报表名称_投资者身份信息登记.getCodeName());
                    chbReportCheck.setCheckResult(SysDataUtil.getCodeFromDataDictByName(DictCode.检查结果, item.getCheckResult()));
                    resultList.add(chbReportCheck);

                    //更新检查序列号对应的数据
                    SalesTransactionMaintenanceRegist001Dto dto = new SalesTransactionMaintenanceRegist001Dto();
                    dto.setTradeNo(item.getValidKey());
                    dto.setCheckSeqno(chbReportCheck.getCheckSeqno());
                    equityInfoRegistUpdateList.add(dto);
            	}
            	else if(!flag){
                    CheckReport001Dto chbReportCheck = new CheckReport001Dto();
                    BeanUtil.copyPropertiesIgnoreNull(chbReportCheck, item);
//                  chbReportCheck.setCheckSeqno(CommonUtil.getSequenceIndexData(DataIndexSequenceType.检查序列号));
                    chbReportCheck.setCheckSeqno(IDGenerator.generate());
                    chbReportCheck.setRepCode(DictCodeListExt2.报表名称_投资者身份信息登记.getValue());
                    chbReportCheck.setRepName(DictCodeListExt2.报表名称_投资者身份信息登记.getCodeName());
                    chbReportCheck.setCheckResult(SysDataUtil.getCodeFromDataDictByName(DictCode.检查结果, item.getCheckResult()));
                    resultList.add(chbReportCheck);

                    //更新检查序列号对应的数据
                    SalesTransactionMaintenanceRegist001Dto dto = new SalesTransactionMaintenanceRegist001Dto();
                    dto.setTradeNo(item.getValidKey());
                    dto.setCheckSeqno(chbReportCheck.getCheckSeqno());
                    equityInfoRegistUpdateList.add(dto);
            	}
            });
            logger.info("初次检查赋值end");
            //由于resultList与equityInfoRegistUpdateList的size相同，故size只判断一个
            if(CollectionUtils.isNotEmpty(resultList)){
            	 //查询分批处理（每次最多处理maxCount条）
                if(resultList.size()<maxCount){
                	this.doCheckInfo(resultList,equityInfoRegistUpdateList);
                }
                else{
                    int part = resultList.size()/maxCount;
                    for(int j=0; j<part; j++){
                        this.doCheckInfo(resultList.subList(0, maxCount),equityInfoRegistUpdateList.subList(0, maxCount));
                    	resultList.subList(0, maxCount).clear();
                        equityInfoRegistUpdateList.subList(0, maxCount).clear();
                    }
                    if(CollectionUtils.isNotEmpty(resultList)){
                    	this.doCheckInfo(resultList,equityInfoRegistUpdateList);
                    }
                }
            }
        }
        return checkResult;
    }
    /**
     * 通过主键查询要导出的登记数据
     * @param expKeys 登记表主键
     * @return 权益人登记信息List
     * @see
     * @since 1.0
     */
    public List<SalesTransactionMaintenanceRegist001Dto> queryExportSelectRegistList(String expKeys){
        List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, expKeys);
        List<SalesTransactionMaintenanceRegist001Dto> registList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        SalesTransactionMaintenanceRegist001Dto SalesTransactionMaintenanceRegist001Dto = null;
        for (String string : keys) {
            SalesTransactionMaintenanceRegist001Dto = new SalesTransactionMaintenanceRegist001Dto();
            String[] key=StringUtil.split(string, "|");
            SalesTransactionMaintenanceRegist001Dto.setInstitutionCode(key[0]);
            SalesTransactionMaintenanceRegist001Dto.setTradeNo(key[1]);
            SalesTransactionMaintenanceRegist001Dto.setSendStatus(key[2]);
            registList.add(SalesTransactionMaintenanceRegist001Dto);
        }
        List<SalesTransactionMaintenanceRegist001Dto> pageList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        int maxDataCountOneTime = 500;
        int dataCount = registList.size();
        if (dataCount <= maxDataCountOneTime) {
        	pageList.addAll(salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(registList));
            return pageList;
        }

        List<SalesTransactionMaintenanceRegist001Dto> newDataList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        newDataList.addAll(registList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
        	pageList.addAll(salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(newDataList.subList(0, maxDataCountOneTime)));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
        	pageList.addAll(salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(newDataList));
        }
        return pageList;
    }
    /**
     * 根据查询条件获取keyList
     * @param param 查询条件
     * @return 字符串结果
     * @see
     * @since 1.0
     */
    public String getKeyList(Map<String, Object> param) {
        StringBuffer sb=new StringBuffer();
        param.put("sendStatus", DictCodeListExt2.报送状态_未报送.getValue());
        param.put("deptCode", CommonUtil.getDeptCode());
        List<SalesTransactionMaintenanceRegist001Dto> list= this.queryExportRegistList(param);
        if(CollectionUtils.isNotEmpty(list)){
            for(SalesTransactionMaintenanceRegist001Dto dto : list){
                sb.append(dto.getInstitutionCode()+"|"+dto.getTradeNo()+"|"+dto.getSendStatus()+"|"
                    +dto.getSumitSeq()+GlobalConstant.COMMA);
            }
        }
        return sb.toString();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.startsWith(propName, "updateTime")) {
            Date cellValue = (Date)value;
            cell.setCellValue(DateUtil.formatDateTime(cellValue));
        }
        if(StringUtil.startsWith(propName, "submitTime")) {
            Date cellValue = (Date)value;
            cell.setCellValue(DateUtil.formatDateTime(cellValue));
        }
        if(StringUtil.startsWith(propName, "chbSubmitDeadline")) {
            Date cellValue = (Date)value;
            cell.setCellValue(DateUtil.formatDateTime(cellValue));
        }
    }

    /**
     * 校验是否所有的数据都符合
     * <功能详细描述>
     * @param upKey 参数
     * @return error 列表
     * @see
     * @since 1.0
     */
    public List<ValidMessage> checkIsNotSubmit(String upKey) {
        List<ValidMessage> errorList =new ArrayList<ValidMessage>();
        List<String> keys = StringUtil.splitToList(GlobalConstant.COMMA, upKey);
        for (String key : keys) {
            List<String> list =StringUtil.splitToList("|", key);
            Map<String , Object>map =new HashMap<String , Object>();
            map.put("institutionCode", list.get(0));
            map.put("tradeNo", list.get(2));
            map.put("noSubmit", DictCodeListExt2.报送状态_未报送.getValue());
            if(salesTransactionMaintenanceRegist001Mapper.isNotSubmitP(map)!= 0){
                errorList.add(new ValidMessage("存在未报送的数据。"));
            }
        }
        return errorList;
    }
    /**
     * 分批次执行queryExportListByKeys
     *
     * @param dataList 条件几盒
     * @param registList list结果集
     * @see
     * @since 1.0
     */
    private void queryExportListByKeys(List<SalesTransactionMaintenanceRegist001Dto> dataList,List<SalesTransactionMaintenanceRegist001Dto> registList ) {
        if (StringUtil.isEmptyList(dataList)) {
            return;
        }
        int maxDataCountOneTime = reportSelectOrDeleteCount;
        int dataCount = dataList.size();
        if (dataCount <= maxDataCountOneTime) {
        	registList.addAll(salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(dataList));
            return;
        }

        List<SalesTransactionMaintenanceRegist001Dto> newDataList = new ArrayList<SalesTransactionMaintenanceRegist001Dto>();
        newDataList.addAll(dataList);

        // 分批处理
        int part = dataCount / maxDataCountOneTime; // 分批数
        for (int i = 0; i < part; i++ ) {
        	registList.addAll(salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(newDataList.subList(0, maxDataCountOneTime)));
            newDataList.subList(0, maxDataCountOneTime).clear();
        }
        // 最后剩下的数据
        if (CollectionUtils.isNotEmpty(newDataList)) {
        	registList.addAll(salesTransactionMaintenanceRegist001Mapper.queryExportListByKeys(newDataList));
        }
    }

    private void doCheckInfo(List<CheckReport001Dto> resultList,List<SalesTransactionMaintenanceRegist001Dto> equityInfoRegistUpdateList){
    	//将新生成的检查信息插入报送前检查表
    	logger.info("单次处理报送前检查表start===============");
        checkReport001Mapper.batchInsert(resultList);

        List<String> indentifyMarkList = new ArrayList<String>();
        //组装检查通过的识别标识结果集
        resultList.parallelStream().forEach(item->{
        	if(IS_CHECK_SUCESS.equals(item.getCheckResult())){
        		indentifyMarkList.add(item.getValidKey());
        	}
        });
        if(CollectionUtils.isNotEmpty(indentifyMarkList)){
        	//同一条数据，如果报送检查通过，则将之前检查不通过的删除
        	checkReport001Mapper.delReportUnCheckDatas(indentifyMarkList);
        }
      //批量更新投资者身份信息检查序列号
        salesTransactionMaintenanceRegist001Mapper.updateEquityRegistCheckSeqno(equityInfoRegistUpdateList);
        logger.info("单次处理报送前检查表end===============");
    }
   
   /**
    * 检查对应的task是否有未执行或正在执行中的批处理任务 <功能详细描述>
    * 
    * @param beanname
    * @return false未执行或正在执行中的任务 true 未检查到对应的未执行或正在执行中的任务
    */
   public boolean checkIfInBatchByTaskBeanName(String beanname) {
	   SalesTransactionMaintenanceRegist001Service chbAssetInfoInterf = SpringContextUtil.getBean(
			   SalesTransactionMaintenanceRegist001Service.class);
       int count = chbAssetInfoInterf.getNoendCountByTaskBeanName(beanname);
       if (count > 0) {
           return false;
       }
       return true;
   }
   
   @Transactional(propagation = Propagation.REQUIRES_NEW)
   @DataSource(DataSourceType.OTHER)
   public int getNoendCountByTaskBeanName(String beanname) {
       return salesTransactionMaintenanceRegist001Mapper.selectNoendCountByTaskBeanName(beanname);
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
                List<SalesTransactionMaintenanceRegist001Dto> astValueList = JsonUtils.json2List(keyList,
                        SalesTransactionMaintenanceRegist001Dto.class);
                pageParam.put("registList", astValueList);
            } catch (IOException e) {
                logger.error(e.getMessage(),e);
            }
        } else {
            // 查询参数条件
            // 数据状态
            String dataStatus = (String) pageParam.get("dataStatus");
            // 报送状态
            String sendStatus = (String) pageParam.get("sendStatus");
            // 反馈状态
            String submitFeedbackStatus = (String) pageParam.get("submitFeedbackStatus");


            // 将数据状态当做多选下拉框处理
            if (StringUtil.isNotBlank(dataStatus)) {
                pageParam.put("dataStatusList", StringUtil.splitToList(GlobalConstant.COMMA, dataStatus));
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
    * 导出功能查询
    *
    * @param pageParam 查询参数
    * @return 结果集
    * @see
    * @since 1.0
    */
   public int countExportNum(Map<String, Object> pageParam) {
       dealSearchParam(pageParam);
       int dataNum = salesTransactionMaintenanceRegist001Mapper.countExportNum(pageParam);
       return dataNum;
   }
}
