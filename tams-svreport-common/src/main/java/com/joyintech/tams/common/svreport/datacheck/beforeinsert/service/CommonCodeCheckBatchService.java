package com.joyintech.tams.common.svreport.datacheck.beforeinsert.service;

import com.joyintech.framework.base.intf.DataDict;
import com.joyintech.framework.base.intf.DataDictItem;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.dao.ChbAssetInfoErrodataMapper;
import com.joyintech.tams.common.base.entity.ChbAssetInfoErrodataWithBLOBs;
import com.joyintech.tams.common.base.entity.ChbBotAstPositionSub;
import com.joyintech.tams.common.base.entity.ChbPrdDeclareSub;
import com.joyintech.tams.common.base.entity.ChbPrdIssueSub;
import com.joyintech.tams.common.svreport.chb.constant.SVReportGlobalConstant;
import com.joyintech.tams.common.svreport.chb.util.SvrDataUtil;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dao.CommonCodeCheckMapper;
import com.joyintech.tams.common.svreport.datacheck.beforeinsert.dto.ChbCheckTableInfo;
import com.joyintech.tams.common.svreport.datacheck.dao.ChbInterfErrodataMapper;
import com.joyintech.tams.common.svreport.datacheck.dto.ChbInterfErrodataWithBLOBs;
import com.joyintech.tams.common.svreport.datacheck.dto.CheckElement;
import com.joyintech.tams.common.svreport.datacheck.service.BaseCheckLib;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.interf.common.constant.DateUtil;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


/**
 * 中债一二期入业务表前的代码check
 * @author Administrator
 *
 */
@Service
public class CommonCodeCheckBatchService {
	
	@Autowired
	private CommonCodeCheckMapper comminCodeCheckMapper;

    /**
     * mybatis的sqlsession管理核心
     */
    @Autowired
    private SqlSessionTemplate template;

    /**
     * 日志
     */
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 登记的入库前的代码检查
     * 只做校验，不做其他处理
     * @param  list 被检查的数据
     * @param  tableInfo  检查的表名
     */
    public void codeCheckCommonOnly(List<Map<String, String>> list, ChbCheckTableInfo tableInfo){

        Set<String> prodRegistCodeList = new HashSet<>();

        Map<String, List<String>> prodRegistCodeMap = new HashMap<>();

        List<Map<String, String>> newDataList = new ArrayList<>(list);
        //循环处理待check数据
        for (int i = list.size() - 1; i >= 0; i--){
            Map<String, String> map=list.get(i);
            switch (tableInfo) {
                case 从业人员登记表SUB:
                    checkEmployeeSubInfo(map);
                    break;
                case 资产要素登记表SUB:
                case 资产要素登记表INFO:
                case 资产要素登记TMP:
                    checkAssetSubInfo(map);
                    break;
                case 个人发行登记表SUB:
                case 个人发行登记表INFO:
                case 个人发行登记TMP:
                    checkPrdoIssue(map, prodRegistCodeList);
                    break;
                case 募集总量登记SUB:
                case 募集总量登记TMP:
                    checkProdRaisetot(map, prodRegistCodeList);
                    break;
                case 底层资产持仓信息登记表SUB:
                case 底层资产持仓信息登记INFO:
                case 底层资产持仓信息登记TMP:
                    checkBotAstPos(map, newDataList);
                    break;
                case 投资交易表登记SUB:
                case 投资交易表登记TMP:
                    checkInvestTradeInfo(map, prodRegistCodeMap);
                    break;
                case 资产估值信息登记表SUB:
                case 资产估值信息登记TMP:
                    checkAstValInfo(map, list);
                    break;
                case 产品存续登记表SUB:
                case 产品存续登记TMP:
                    checkProdDeal(map, list);
                    break;
                case 个人产品申报登记SUB:
                case 个人产品申报登记INFO:
                case 个人产品申报登记TMP:
                    checkPrdoDeclare(map,list);
                    break;
                case 终止登记SUB:
                case 终止登记TMP:
                    checkProdEndSubTmp(map,prodRegistCodeList);
                    break;
                default:
                    break;
            }
        }
    }

	/**
	 * 产品募集总量登记的入库前的代码检查
	 * @param  list 被检查的数据
	 * @param  tableInfo  检查的表名
     * @param  fileName 导入的文件名
	 */
	public void codeCheckCommon(List<Map<String, String>> list, ChbCheckTableInfo tableInfo, String fileName){
		//错误信息集合
		List<ChbInterfErrodataWithBLOBs> erroList=new ArrayList<ChbInterfErrodataWithBLOBs>();
        // 资产要素错误信息集合
        List<ChbAssetInfoErrodataWithBLOBs> chbAssetInfoErrorList = new ArrayList<ChbAssetInfoErrodataWithBLOBs>();

        codeCheckCommonOnly(list, tableInfo);

        for (int i = list.size() - 1; i >= 0; i--){
            Map<String, String> map=list.get(i);

            // add by zhangliang 20210112 没有错误信息时删除key，防止后面错误判断
            if (map.containsKey("ERRMSG") && StringUtil.isEmpty(map.get("ERRMSG"))){
                map.remove("ERRMSG");
            }

            //如果代码错误校验有错误信息
            ChbInterfErrodataWithBLOBs errodata = createChbInterfErrdata(StringUtil.isNotEmpty(fileName) ? fileName : tableInfo.getTableName(),
                    tableInfo.getTableName(), map,tableInfo.getBussinessKey(), tableInfo.getCheckEuplicatedKeys());

            if(errodata != null) {
                erroList.add(errodata);
                list.remove(i);
            }

            if (StringUtil.equals(CacheUtil.getSysParam("INSERT_ERRDATA_MODE"), "02") && StringUtil.equals(tableInfo.getTableName(), ChbCheckTableInfo.资产要素登记表INFO.getTableName())){
                // 记录资产要素错误信息表
                ChbAssetInfoErrodataWithBLOBs chbAssetInfoErrodata = createChbAssetInfoInterfErrdata(map);
                if(chbAssetInfoErrodata != null) {
                    chbAssetInfoErrorList.add(chbAssetInfoErrodata);
                }
            }
        }

		//错误信息入库
        if (StringUtil.isEmptyList(chbAssetInfoErrorList)){
            if(!erroList.isEmpty()){
                insertErroData(erroList);
            }
        }else{
            // 资产要素错误信息入库
            if(!chbAssetInfoErrorList.isEmpty()){
                deleteAssetInfoErrorDataByAssetCode(chbAssetInfoErrorList);
                insertChbAssetInfoErroData(chbAssetInfoErrorList);
            }
        }
	}

	/**
	 * 从业人员登记
	 * @param map
	 */
    private void checkEmployeeSubInfo(Map<String, String> dataMap) {
    	String  errMsg = dataMap.getOrDefault("ERRMSG", "");
    	
    	// 发行机构代码
        String issuePartyId = dataMap.get("ISSUE_PARTY_ID");
        // 银行模式才判断发行机构代码，联社模式不判断
        if(StringUtil.isNotEmpty(issuePartyId) && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
	        // 发行机构代码必须与银行代码相同。
	        if(!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)){
	            errMsg +="发行机构代码必须与银行代码相同[ISSUE_PARTY_ID="+issuePartyId+"];";
	        }
        }
        
        //已登记人员
//		int cnt = comminCodeCheckMapper.findEmployeeSub(getKey(dataMap));
//		if (cnt > 0){
//	         errMsg +="从业人员类型: " + (dataMap.get("EMPLOYEE_TYPE") + " " + SvrDataUtil.getNameFromDataDict(DictCode.从业人员类型, dataMap.get("EMPLOYEE_TYPE")))
//			                +" 身份证件号: " + dataMap.get("IDENTIFY_NUMBER") + "已注册,不能重复注册;";
//	    }
		
		dataMap.put("ERRMSG", errMsg);
	}

//    private String getKey(Map<String,String> map){
//    	return map.get("EMPLOYEE_TYPE") + GlobalConstant.VERTICAL_LINE + map.get("IDENTIFY_NUMBER");
//    }
    
	private boolean checkValIsNum(String str){
        Map<String, String> data = new HashMap<>();
        CheckElement rule = new CheckElement();
        rule.setSrcproperty("CHECK_STR");
        rule.setNullable("N");
        data.put("CHECK_STR", str);
        return BaseCheckLib.isNumber2(data, rule);
    }

    private boolean checkValIsDate(String str){
        Map<String, String> data = new HashMap<>();
        CheckElement rule = new CheckElement();
        data.put("CHECK_STR", str);
        rule.setSrcproperty("CHECK_STR");
        rule.setNullable("N");
        rule.setObjvalue(DateUtil.DEFAULT_DATE_FORMAT_EN);
        return BaseCheckLib.isValidDate(data, rule);
    }

    /**
     * 底层资产持仓登记
     * @param dataMap
     */
    private void checkBotAstPos(Map<String, String> dataMap, List<Map<String, String>> list){
        String  errMsg = dataMap.getOrDefault("ERRMSG", "");
        // 发行机构代码
        String issuePartyId=dataMap.get("ISSUE_PARTY_ID");
        // 资管计划代码(对应资产管理计划行内资产/负债编码)
        String planAssetId=dataMap.get("PLAN_ASSET_ID");
        // 行内资产/负债编码
        String assetCode=dataMap.get("ASSET_CODE");
        // 持仓日期
        String posDate = dataMap.get("POS_DATE");
        // 当前总数量
        String totalNum = dataMap.get("TOTAL_NUM");
        // 资管计划当前总折算人民币金额（元）
        String issueAmt = dataMap.get("ISSUE_AMT");
        // 资管计划折算人民币未投资头寸（元）
        String uninvestCash = dataMap.get("UNINVEST_CASH");

        List<ChbBotAstPositionSub> subList = new ArrayList<>();
        if(StringUtil.isNotEmpty(issuePartyId)){
        // 发行机构代码必须与银行代码相同。
        // 银行模式才判断发行机构代码，联社模式不判断
        if(!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)
                && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
            errMsg +="发行机构代码必须与银行代码相同[ISSUE_PARTY_ID="+issuePartyId+"];";
        }}
        // 对应资管及委外资产行内资产/负债编码和底层资产行内资产/负债编码不能相同。
        if (StringUtil.equals(planAssetId, assetCode)){
            errMsg +="对应资管及委外资产行内资产/负债编码和底层资产行内资产/负债编码不能相同[PLAN_ASSET_ID="+planAssetId+"];";
        }

        if (StringUtil.isNotEmpty(planAssetId) && StringUtil.isNotEmpty(posDate) && StringUtil.isNotEmpty(assetCode)){
            // 报文内相当资产代码个数
            int assetCodeEquCount = 0;
            int totalNumSameCount = 0;
            int issueAmtSameCount = 0;
            int uninvestCashSameCount= 0;
            for (Map<String, String> otherDataMap : list){
                // 资管计划代码相同、持仓日期相同、底层资产代码不同
                if (StringUtil.equals(otherDataMap.get("PLAN_ASSET_ID"), planAssetId) && StringUtil.equals(otherDataMap.get("POS_DATE"), posDate)){
                    if (!StringUtil.equals(assetCode, otherDataMap.get("ASSET_CODE"))){
                        if (checkValIsNum(totalNum) && checkValIsNum(otherDataMap.get("TOTAL_NUM"))){
                            if (new BigDecimal(totalNum).compareTo(new BigDecimal(otherDataMap.get("TOTAL_NUM"))) != 0){
                                totalNumSameCount ++;
                            }
                        }
                        if (checkValIsNum(issueAmt) && checkValIsNum(otherDataMap.get("ISSUE_AMT"))){
                            if (new BigDecimal(issueAmt).compareTo(new BigDecimal(otherDataMap.get("ISSUE_AMT"))) != 0){
                                issueAmtSameCount ++;
                            }
                        }
                        if (checkValIsNum(uninvestCash) && checkValIsNum(otherDataMap.get("UNINVEST_CASH"))){
                            if (new BigDecimal(uninvestCash).compareTo(new BigDecimal(otherDataMap.get("UNINVEST_CASH"))) != 0){
                                uninvestCashSameCount ++;
                            }
                        }
                    }
                    else{
                        assetCodeEquCount ++;
                    }
                }

            }
            // 同一对应资管及委外资产行内资产/负债编码、同一持仓日期时，所有的资管及委外资产当前总数量必须相同。
            if (totalNumSameCount > 0){
                errMsg +="同一对应资管及委外资产行内资产/负债编码、同一持仓日期时，所有的资管及委外资产当前总数量必须相同[PLAN_ASSET_ID="+planAssetId+"];";
            }
            // 同一对应资管及委外资产行内资产/负债编码、同一持仓日期时，所有的资管及委外资产当前总折算人民币金额（元）必须相同。
            if (issueAmtSameCount > 0){
                errMsg +="同一对应资管及委外资产行内资产/负债编码、同一持仓日期时，所有的资管及委外资产当前总折算人民币金额（元）必须相同[PLAN_ASSET_ID="+planAssetId+"];";
            }
            // 同一对应资管及委外资产行内资产/负债编码、同一持仓日期时，所有的资管及委外资产未投资头寸（元）必须相同。
            if (uninvestCashSameCount > 0){
                errMsg +="同一对应资管及委外资产行内资产/负债编码、同一持仓日期时，所有的资管及委外资产未投资头寸（元）必须相同[PLAN_ASSET_ID="+planAssetId+"];";
            }
            // 对应资管及委外资产行内资产/负债编码、底层资产行内资产/负债编码和持仓日期在报文内不能同时重复。
            if (assetCodeEquCount > 1){
                errMsg +="对应资管及委外资产行内资产/负债编码、底层资产行内资产/负债编码和持仓日期在报文内不能同时重复[PLAN_ASSET_ID="+planAssetId+"];";
            }
        }

        // 根据资管计划代码和持仓日期查询底层资产持仓信息
        if (StringUtil.isNotEmpty(planAssetId) && StringUtil.isNotEmpty(posDate) && checkValIsDate(posDate) && StringUtil.isNotEmpty(assetCode)){
            ChbBotAstPositionSub chbBotAstPosSubTmp = new ChbBotAstPositionSub();
            chbBotAstPosSubTmp.setPlanAssetId(planAssetId);
            chbBotAstPosSubTmp.setPosDate(DateUtil.parse(posDate));
            subList = comminCodeCheckMapper.queryChbBotAstPosByInfo(chbBotAstPosSubTmp);

        }

        // 系统总发行数量标志位
        boolean totleNumSysEquFlag = false;
        boolean totleIssueAmtSysEquFlag = false;
        boolean uninvestCashSysEquFlag = false;

        if (!StringUtil.isEmptyList(subList)){
            for (ChbBotAstPositionSub tmp : subList){
                if (!StringUtil.equals(assetCode, tmp.getAssetCode())){
                    if (checkValIsNum(totalNum) && new BigDecimal(totalNum).compareTo(tmp.getTotalNum()) != 0){
                        totleNumSysEquFlag = true;
                    }
                    if (checkValIsNum(issueAmt) && new BigDecimal(issueAmt).compareTo(tmp.getIssueAmt()) != 0){
                        totleIssueAmtSysEquFlag = true;
                    }
                    if (checkValIsNum(uninvestCash) && new BigDecimal(uninvestCash).compareTo(tmp.getUninvestCash()) != 0){
                        uninvestCashSysEquFlag = true;
                    }
                }
            }
        }

        // 资管及委外资产当前总发行数量与系统中的资管及委外资产当前总发行数量不一致
        if (totleNumSysEquFlag){
            errMsg +="资管及委外资产当前总发行数量与系统中的资管及委外资产当前总发行数量不一致[PLAN_ASSET_ID="+planAssetId+"];";
        }
        // 资管及委外资产当前总折算人民币发行金额(元)与系统中的资管及委外资产当前总折算人民币发行金额(元)不一致
        if (totleIssueAmtSysEquFlag){
            errMsg +="资管及委外资产当前总折算人民币发行金额(元)与系统中的资管及委外资产当前总折算人民币发行金额(元)不一致[PLAN_ASSET_ID="+planAssetId+"];";
        }
        // 资管及委外资产折算人民币未投资头寸(元)与系统中的资管及委外资产折算人民币未投资头寸(元)不一致
        if (uninvestCashSysEquFlag){
            errMsg +="资管及委外资产折算人民币未投资头寸(元)与系统中的资管及委外资产折算人民币未投资头寸(元)不一致[PLAN_ASSET_ID="+planAssetId+"];";
        }

//      // 对应资管及委外资产行内资产/负债编码为:zichan2020041018, 底层资产行内资产负债/编码为:zichan2020041001, 持仓日期为:20200103的记录在系统中重复
//      if (!StringUtil.isEmptyList(subList)){
//          for (ChbBotAstPosSubTmp tmp : subList){
//              if (StringUtil.equals(assetCode, tmp.getAssetCode())){
//                  errMsg +="对应资管及委外资产行内资产/负债编码为:" + planAssetId + ", 底层资产行内资产负债/编码为:" + assetCode + ", 持仓日期为:" + posDate + "的记录在系统中重复";
//                  break;
//              }
//          }
//      }
        // 底层资产行内资产负债/编码为:zichan2020080102的底层资产不存在
//      if (StringUtil.isNotEmpty(assetCode) && comminCodeCheckMapper.queryChbAssetInfoSubByAssetCode(assetCode) == 0){
//          errMsg +="底层资产行内资产负债/编码为:" + assetCode + "的底层资产不存在";
//      }
        dataMap.put("ERRMSG", errMsg);
    }

    /**
     * 代码检查募集总量登记
     * @param dataMap
     */
    private void checkProdRaisetot(Map<String, String> dataMap,Set<String> prodRegistCodeList){
        String  errMsg=dataMap.getOrDefault("ERRMSG", "");
        // 数据对象
        Map<String, String> data = new HashMap<>();
        // 规则对象
        CheckElement rule = new CheckElement();
        // 币种数据字典
        DataDict ccyDto = CacheUtil.getDictCode(DictCode.币种_中债.getValue());
        // 销售区域
        DataDict saleAreaDto = CacheUtil.getDictCode(DictCode.销售区域_中债.getValue());
        // 发行机构代码
        String issuePartyId=dataMap.get("ISSUE_PARTY_ID");
        // 产品登记编码
        String prodRegistCode = dataMap.get("PROD_REGIST_CODE");
        // 产品代码
        String prodId = dataMap.get("PROD_ID");
        // 认购币种及金额
        String subscribeCcyAmt = dataMap.get("SUBSCRIBE_CCY_AMT");
        // 产品销售区域及募集金额
        String saleAreaAmt = dataMap.get("SALE_AREA_AMT");
        // 实际募集金额
        String totalAmt = dataMap.get("TOTAL_AMT");
        // 代销总金额
        String consignAmt = dataMap.get("CONSIGN_AMT");
        // 备注
        String remark = dataMap.get("REMARK");
        // 区域募集总金额
        BigDecimal totleAreaAmt = BigDecimal.ZERO;
        // 区域募集金额
        List<String> areaAmtList = new ArrayList<>();
        // 折算人民币金额
        List<String> subscribeCcyAmtList = new ArrayList<>();
        // 折算人民币金额汇总
        BigDecimal totleCcyAmt = BigDecimal.ZERO;

        // 发行机构代码必须与银行代码相同。
        // 银行模式才判断发行机构代码，联社模式不判断
        if(StringUtil.isNotEmpty(issuePartyId) && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
        if(!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)){
            errMsg +="发行机构代码必须与银行代码相同[ISSUE_PARTY_ID="+issuePartyId+"];";
        }}
        // 产品登记编码报文内不可重复。
        if (StringUtil.isNotEmpty(prodRegistCode) && prodRegistCodeList.contains(prodRegistCode)){
            errMsg +="产品登记编码报文内不可重复[PROD_REGIST_CODE="+prodRegistCode+"];";
        }
        else{
            prodRegistCodeList.add(prodRegistCode);
        }
//      // 该产品不存在。
//      if (StringUtil.isNotEmpty(prodRegistCode)){
//          List<ChbPrdresistCode> chbPrdresistCodeList = comminCodeCheckMapper.queryChbPrdresistCodeByRegCode(prodRegistCode);
//          if (StringUtil.isEmptyList(chbPrdresistCodeList)){
//              errMsg +="该产品不存在[PROD_REGIST_CODE="+prodRegistCode+"];";
//          }
//      }
        // CNY,2000,2000;HKD,1000,CNY,800
        if (StringUtil.isNotEmpty(subscribeCcyAmt)){
            List<String> singleCcyAmtList = Arrays.asList(subscribeCcyAmt.split(GlobalConstant.SEMICOLON));// CNY,2000,2000
            // 认购币种
            List<String> subscribeCcyList = new ArrayList<>();
            // 认购金额
            List<String> subscribeAmtList = new ArrayList<>();
            /*// 折算人民币金额标志位
            boolean subscribeCcyAmtFlag = false;
            // 认购金额标志位
            boolean subscribeAmtFlag = false;
            // 认购币种标志位
            boolean subscribeCcyFlag = false;*/
            for (String singleCcyAm : singleCcyAmtList){
                List<String> ccyAmtDetailList = Arrays.asList(singleCcyAm.split(GlobalConstant.COMMA));
                if (ccyAmtDetailList.size() == 3 && StringUtil.isNotEmpty(ccyAmtDetailList.get(0))
                    && StringUtil.isNotEmpty(ccyAmtDetailList.get(1)) && StringUtil.isNotEmpty(ccyAmtDetailList.get(2))){
                    subscribeCcyAmtList.add(ccyAmtDetailList.get(2));
                    subscribeAmtList.add(ccyAmtDetailList.get(1));
                    subscribeCcyList.add(ccyAmtDetailList.get(0));

                    // 认购币种
                    boolean ccyDataFlag = false;
                    if (null != ccyDto) {
                        List<? extends DataDictItem> items = ccyDto.getItems();
                        for (DataDictItem item : items) {
                            if (ccyAmtDetailList.get(0).equals(item.getItemCode())){
                                ccyDataFlag = true;
                                break;
                            }
                        }
                    }
                    if (!ccyDataFlag){
                        errMsg +="认购币种必须在枚举范围内[SUBSCRIBE_CCY_AMT="+singleCcyAm+"];";
                    }
                    // 认购金额必须为n（15,2）格式。
                    rule.setSrcproperty("SUBSCRIBEAMT");
                    rule.setObjvalue("15,2");
                    rule.setNullable("Y");
                    data.put("SUBSCRIBEAMT", ccyAmtDetailList.get(1));
                    if (!BaseCheckLib.isNumber2(data, rule)){
                        errMsg +="认购金额必须为n（15,2）格式[SUBSCRIBE_CCY_AMT="+singleCcyAm+"];";
                    }
                    // 折算人民币金额必须为n（15,2）格式。
                    data.put("SUBSCRIBEAMT", ccyAmtDetailList.get(2));
                    if (!BaseCheckLib.isNumber2(data, rule)){
                        errMsg +="折算人民币金额必须为n（15,2）格式[SUBSCRIBE_CCY_AMT="+singleCcyAm+"];";
                    }
                    // 认购金额必须大于等于0。
                    rule.setObjvalue("0");
                    rule.setCompareto(">=");
                    rule.setNullable("N");
                    data.put("SUBSCRIBEAMT", ccyAmtDetailList.get(1));
                    if (!BaseCheckLib.compareValueAsNumber(data, rule)){
                        errMsg +="认购金额必须大于等于0[SUBSCRIBE_CCY_AMT="+singleCcyAm+"];";
                    }
                    // 折算人民币金额必须大于等于0。
                    data.put("SUBSCRIBEAMT", ccyAmtDetailList.get(2));
                    if (!BaseCheckLib.compareValueAsNumber(data, rule)){
                        errMsg +="折算人民币金额必须大于等于0[SUBSCRIBE_CCY_AMT="+singleCcyAm+"];";
                    }

                    if (StringUtil.equals(ccyAmtDetailList.get(0), DictCodeListExt1.币种_中债_人民币.getValue())){
                        if (checkValIsNum(ccyAmtDetailList.get(1)) && checkValIsNum(ccyAmtDetailList.get(2))){
                            if (new BigDecimal(ccyAmtDetailList.get(1)).compareTo(new BigDecimal(ccyAmtDetailList.get(2))) != 0){
                                // 当认购金额的币种为人民币(CNY)时，折算人民币金额与认购金额必须相等。
                                errMsg +="当认购金额的币种为人民币(CNY)时，折算人民币金额与认购金额必须相等[SUBSCRIBE_CCY_AMT="+singleCcyAm+"];";
                            }
                        }
                    }
                }
                else{
                    errMsg +="认购币种及金额格式不正确[SUBSCRIBE_CCY_AMT="+singleCcyAm+"];";
                }
                /*else if (ccyAmtDetailList.size() == 2){
                    subscribeCcyAmtFlag = true;

                    subscribeAmtList.add(ccyAmtDetailList.get(1));
                    subscribeCcyList.add(ccyAmtDetailList.get(0));
                }
                else if (ccyAmtDetailList.size() == 1){
                    subscribeAmtFlag = true;
                    subscribeCcyAmtFlag = true;

                    subscribeCcyList.add(ccyAmtDetailList.get(0));
                }
                else if (ccyAmtDetailList.size() == 0){
                    subscribeCcyFlag = true;
                    subscribeAmtFlag = true;
                    subscribeCcyAmtFlag = true;
                }*/
            }

            /*// 认购币种要素不可为空
            if (subscribeCcyFlag){
                errMsg +="认购币种必须在枚举范围内[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
            }
            // 认购金额要素不可为空。
            if(subscribeAmtFlag){
                errMsg +="认购金额要素不可为空[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
            }
            // 折算人民币金额要素不可为空。
            if (subscribeCcyAmtFlag){
                errMsg +="折算人民币金额要素不可为空[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
            }*/
            // 认购金额的币种的多选选项不可重复。
            HashSet<String> subscribeCcySet = new HashSet<String>(subscribeCcyList);
            if (subscribeCcySet.size() != subscribeCcyList.size()){
                errMsg +="认购金额的币种的多选选项不可重复[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
            }
            /*for (String ccy : subscribeCcyList){
                boolean flag = false;
                if (null != ccyDto) {
                    List<? extends DataDictItem> items = ccyDto.getItems();
                    for (DataDictItem item : items) {
                        if (ccy.equals(item.getItemCode())){
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag){
                    errMsg +="认购币种必须在枚举范围内[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
                    break;
                }
            }
            // 认购金额必须为n（15,2）格式。
            rule.setSrcproperty("SUBSCRIBEAMT");
            rule.setObjvalue("15,2");
            rule.setNullable("Y");
            for (String subscribeAmt : subscribeAmtList){
                data.put("SUBSCRIBEAMT", subscribeAmt);
                if (!BaseCheckLib.isNumber2(data, rule)){
                    errMsg +="认购金额必须为n（15,2）格式[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
                    break;
                }
            }
            // 折算人民币金额必须为n（15,2）格式。
            for (String subscribeccyAmt : subscribeCcyAmtList){
                data.put("SUBSCRIBEAMT", subscribeccyAmt);
                if (!BaseCheckLib.isNumber2(data, rule)){
                    errMsg +="折算人民币金额必须为n（15,2）格式[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
                    break;
                }
            }
            // 认购金额必须大于等于0。
            rule.setObjvalue("0");
            rule.setCompareto(">=");
            for (String subscribeAmt : subscribeAmtList){
                data.put("SUBSCRIBEAMT", subscribeAmt);
                if (!BaseCheckLib.compareValueAsNumber(data, rule)){
                    errMsg +="认购金额必须大于等于0[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
                    break;
                }
            }
            // 折算人民币金额必须大于等于0。
            for (String subscribeccyAmt : subscribeCcyAmtList){
                data.put("SUBSCRIBEAMT", subscribeccyAmt);
                if (!BaseCheckLib.compareValueAsNumber(data, rule)){
                    errMsg +="折算人民币金额必须大于等于0[SUBSCRIBE_CCY_AMT="+subscribeCcyAmt+"];";
                    break;
                }
            }*/
        }

        // 产品销售区域
        List<String> saleAreaList = new ArrayList<>();
        HashSet<String> saleAreaSet = null;
        // 370000 山东省,60000.00;370200 青岛市,60000.00
        if (StringUtil.isNotEmpty(saleAreaAmt)){
            List<String> singleSaleAreaAmtList = Arrays.asList(saleAreaAmt.split(GlobalConstant.SEMICOLON));// 370000 山东省,60000.00
            /*// 产品销售区域标志位
            boolean saleAreaFlag = false;
            // 区域募集金额标志位
            boolean areaAmtFlag = false;*/
            for (String singleAreaAmt : singleSaleAreaAmtList){
                List<String> ccyAmtDetailList = Arrays.asList(singleAreaAmt.split(GlobalConstant.COMMA));
                if (ccyAmtDetailList.size() == 2 && StringUtil.isNotEmpty(ccyAmtDetailList.get(0))&& StringUtil.isNotEmpty(ccyAmtDetailList.get(1))){
                    List<String> areaInfoList = Arrays.asList(ccyAmtDetailList.get(0).split(" "));// 370000 山东省

                    if (areaInfoList.size() == 2 && StringUtil.isNotEmpty(areaInfoList.get(0))&& StringUtil.isNotEmpty(areaInfoList.get(1))){
                        // 产品销售区域不在值域范围内。
                        boolean saleAreaDataFlag = false;
                        String areaDataValue = "";
                        if (null != saleAreaDto) {
                            List<? extends DataDictItem> items = saleAreaDto.getItems();
                            for (DataDictItem item : items) {
                                if (areaInfoList.get(0).equals(item.getItemCode())){
                                    saleAreaDataFlag = true;
                                    areaDataValue = item.getItemValue();
                                    break;
                                }
                            }
                        }
                        if (!saleAreaDataFlag){
                            errMsg +="产品销售区域不在值域范围内[SALE_AREA_AMT="+singleAreaAmt+"];";
                        }
                        else{
                            if (!StringUtil.equals(areaInfoList.get(1), areaDataValue)){
                                errMsg +="产品销售区域值域信息不匹配[SALE_AREA_AMT="+singleAreaAmt+"];";
                            }
                        }

                        // 区域募集金额必须为n（15,2）格式。
                        rule.setSrcproperty("AREAAMT");
                        rule.setObjvalue("15,2");
                        rule.setNullable("Y");
                        data.put("AREAAMT", ccyAmtDetailList.get(1));
                        if (!BaseCheckLib.isNumber2(data, rule)){
                            errMsg +="区域募集金额必须为n（15,2）格式[SALE_AREA_AMT="+singleAreaAmt+"];";
                        }
                        // 区域募集金额必须大于等于0。
                        rule.setObjvalue("0");
                        rule.setCompareto(">=");
                        rule.setNullable("N");
                        data.put("AREAAMT", ccyAmtDetailList.get(1));
                        if (!BaseCheckLib.compareValueAsNumber(data, rule)){
                            errMsg +="区域募集金额必须大于等于0[SALE_AREA_AMT="+singleAreaAmt+"];";
                        }

                        areaAmtList.add(ccyAmtDetailList.get(1));
                        saleAreaList.add(areaInfoList.get(0));
                    }
                    else{
                        errMsg +="产品销售区域格式不正确[SALE_AREA_AMT="+singleAreaAmt+"];";
                    }
                }
                else{
                    errMsg +="产品销售区域格式不正确[SALE_AREA_AMT="+singleAreaAmt+"];";
                }
                /*else if (ccyAmtDetailList.size() == 1){
                    areaAmtFlag = true;

                    saleAreaList.add(ccyAmtDetailList.get(0));
                }
                else if (ccyAmtDetailList.size() == 0){
                    saleAreaFlag = true;
                    areaAmtFlag = true;
                }*/
            }

            /*// 产品销售区域要素不可为空。
            if (saleAreaFlag){
                errMsg +="产品销售区域要素不可为空[SALE_AREA_AMT="+saleAreaAmt+"];";
            }
            // 区域募集金额要素不可为空。
            if(areaAmtFlag){
                errMsg +="区域募集金额要素不可为空[SALE_AREA_AMT="+saleAreaAmt+"];";
            }*/
            // 产品销售区域的多选选项不可重复。
            saleAreaSet = new HashSet<String>(saleAreaList);
            if (saleAreaSet.size() != saleAreaList.size()){
                errMsg +="产品销售区域的多选选项不可重复[SALE_AREA_AMT="+saleAreaAmt+"];";
            }
            /*// 产品销售区域不在值域范围内。
            for (String saleArea : saleAreaList){
                boolean flag = false;
                if (null != saleAreaDto) {
                    List<? extends DataDictItem> items = saleAreaDto.getItems();
                    for (DataDictItem item : items) {
                        if (saleArea.equals(item.getItemCode())){
                            flag = true;
                            break;
                        }
                    }
                }
                if (!flag){
                    errMsg +="产品销售区域不在值域范围内[SALE_AREA_AMT="+saleAreaAmt+"];";
                    break;
                }
            }

            // 区域募集金额必须为n（15,2）格式。
            rule.setSrcproperty("AREAAMT");
            rule.setObjvalue("15,2");
            rule.setNullable("Y");
            for (String areaAmt : areaAmtList){
                data.put("AREAAMT", areaAmt);
                if (!BaseCheckLib.isNumber2(data, rule)){
                    errMsg +="区域募集金额必须为n（15,2）格式[SALE_AREA_AMT="+saleAreaAmt+"];";
                    break;
                }
            }
            // 区域募集金额必须大于等于0。
            rule.setObjvalue("0");
            rule.setCompareto(">=");
            for (String areaAmt : areaAmtList){
                data.put("AREAAMT", areaAmt);
                if (!BaseCheckLib.compareValueAsNumber(data, rule)){
                    errMsg +="区域募集金额必须大于等于0[SALE_AREA_AMT="+saleAreaAmt+"];";
                    break;
                }
            }*/
        }

        // 实际募集金额(元)≠∑各区域募集金额（元）≠∑各币种折算人民币金额（元），请检查这三个元素的值，重新填写。
        // 实际募集金额(元)
        BigDecimal actTotalAmt = BigDecimal.ZERO;
        if (checkValIsNum(totalAmt)){
            actTotalAmt = new BigDecimal(totalAmt);
        }
        // ∑各区域募集金额（元）
        for (String areaAmt : areaAmtList){
            if (checkValIsNum(areaAmt)){
                totleAreaAmt = ArithmeticUtil.doAdd(totleAreaAmt, new BigDecimal(areaAmt));
            }
        }
        // ∑各币种折算人民币金额（元）
        for (String subscribeccyAmt : subscribeCcyAmtList){
            if (checkValIsNum(subscribeccyAmt)){
                totleCcyAmt = ArithmeticUtil.doAdd(totleCcyAmt, new BigDecimal(subscribeccyAmt));
            }
        }
        if (actTotalAmt.compareTo(totleAreaAmt) != 0 || totleAreaAmt.compareTo(totleCcyAmt) !=0){
            errMsg +="实际募集金额(元)≠∑各区域募集金额（元）≠∑各币种折算人民币金额（元），请检查这三个元素的值，重新填写。";
        }


        // delete by zhangliang 20201106 逻辑移到报送前处理 start
        // 产品销售区域超出产品申报登记选择的区域，备注必填。
        /*if (saleAreaSet != null && StringUtil.isNotEmpty(prodId)){
            Map<String, Object> map = new HashMap<>();
            map.put("prodId", prodId);
            List<ChbPrdDeclareSub> chbPrdDeclareSubList = comminCodeCheckMapper.queryChbPrdDeclareSubByRegCode(map);
            if(!StringUtil.isEmptyList(chbPrdDeclareSubList) && StringUtil.isNotEmpty(chbPrdDeclareSubList.get(0).getSaleArea())){
                List<String> declareAreaList = Arrays.asList(chbPrdDeclareSubList.get(0).getSaleArea().split(GlobalConstant.COMMA));
                boolean flag = false;
                for (String area : saleAreaSet){
                    if (!declareAreaList.contains(area)){
                        flag = true;
                        break;
                    }
                }
                if (flag && StringUtil.isEmpty(remark)){
                    errMsg +="产品销售区域超出产品申报登记选择的区域，备注必填[REMARK="+remark+"];";
                }
            }
        }*/
        // delete by zhangliang 20201106 逻辑移到报送前处理 end
        // 该要素数值小于等于实际募集金额
        if (checkValIsNum(consignAmt) && checkValIsNum(totalAmt)){
            if (new BigDecimal(consignAmt).compareTo(new BigDecimal(totalAmt)) > 0){
                errMsg +="代销总金额必须小于等于实际募集金额[CONSIGN_AMT="+consignAmt+"];";
            }
        }

        dataMap.put("ERRMSG", errMsg);
    }

    /**
     * 代码检查产品申报登记
     * @param dataMap
     */
    private  void  checkPrdoIssue(Map<String, String> dataMap,Set<String> prodRegistCodeList){
        String  errMsg=dataMap.getOrDefault("ERRMSG", "");
        // 发行机构代码
        String issuePartyId=dataMap.get("ISSUE_PARTY_ID");
        // 产品登记编码
        String prodRegistCode = dataMap.get("PROD_REGIST_CODE");
        // 理财产品代码
        String prodId = dataMap.get("PROD_ID");
        // 募集起始日期
        String raiseVdate = dataMap.get("RAISE_VDATE");

        // 产品登记编码报文内不可重复。
        if (StringUtil.isNotEmpty(prodRegistCode) && prodRegistCodeList.contains(prodRegistCode)){
            errMsg +="产品登记编码报文内不可重复[PROD_REGIST_CODE="+prodRegistCode+"];";
        }
        else{
            prodRegistCodeList.add(prodRegistCode);
        }
//      // 该产品不存在。
//      if (StringUtil.isNotEmpty(prodRegistCode)){
//          List<ChbPrdresistCode> chbPrdresistCodeList = comminCodeCheckMapper.queryChbPrdresistCodeByRegCode(prodRegistCode);
//          if (StringUtil.isEmptyList(chbPrdresistCodeList)){
//              errMsg +="该产品不存在[PROD_REGIST_CODE="+prodRegistCode+"];";
//          }
//      }
        // 银行模式才判断发行机构代码，联社模式不判断
        if(StringUtil.isNotEmpty(issuePartyId) && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
        // 发行机构代码必须与银行代码相同。
        if(!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)){
            errMsg +="发行机构代码必须与银行代码相同[ISSUE_PARTY_ID="+issuePartyId+"];";
        }
        }
        // 募集起始日期应落在申报登记要素中募集起始日期（从）/（到）的区间内.
        List<ChbPrdDeclareSub> chbPrdDeclareSubList = new ArrayList<>();
        if (StringUtil.isNotEmpty(prodId)){
            Map<String, Object> map = new HashMap<>();
            map.put("prodId", prodId);
            chbPrdDeclareSubList = comminCodeCheckMapper.queryChbPrdDeclareSubByRegCode(map);
            if (!StringUtil.isEmptyList(chbPrdDeclareSubList)){
                if ((StringUtil.isNotEmpty(chbPrdDeclareSubList.get(0).getRaisePstrdate()) &&
                        DateUtil.parse(raiseVdate).compareTo(chbPrdDeclareSubList.get(0).getRaisePstrdate()) < 0)
                        || (StringUtil.isNotEmpty(chbPrdDeclareSubList.get(0).getRaisePenddate()) &&
                        DateUtil.parse(raiseVdate).compareTo(chbPrdDeclareSubList.get(0).getRaisePenddate()) > 0)){
                    errMsg +="募集起始日期应落在申报登记要素中募集起始日期（从）/（到）的区间内[RAISE_VDATE="+raiseVdate+"];";
                }
            }
        }
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,开放模式不能有值；
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,规律开放周期不能有值；
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,其他规律开放周期（天）不能有值！
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,无规律开放说明不能有值。
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,首次开放周期起始日不能有值；
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,节假日是否开放不能有值；
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,平均开放次数（年化）不能有值；
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,开放期业务不能有值；
        // 产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,开放期业务说明不能有值；
        // 产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,开放模式不能为空。
        // 产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,首次开放周期起始日不能为空。
        // 产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,节假日是否开放不能为空。
        // 产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,平均开放次数（年化）不能为空。
        // 产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,开放期业务不能为空。
        if (!StringUtil.isEmptyList(chbPrdDeclareSubList)){
            String operateMode = chbPrdDeclareSubList.get(0).getOperateMode();
            if (StringUtil.equals(operateMode, DictCodeListExt2.产品运作模式_封闭式净值型.getValue())
                    || StringUtil.equals(operateMode, DictCodeListExt2.产品运作模式_封闭式非净值型.getValue())){
                // 开放模式
                if (StringUtil.isNotEmpty(dataMap.get("OPEN_MODE"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,开放模式不能有值[OPEN_MODE="+dataMap.get("OPEN_MODE")+"];";
                }
                // 规律开放周期
                if (StringUtil.isNotEmpty(dataMap.get("OPEN_CIRCLE"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,规律开放周期不能有值[OPEN_CIRCLE="+dataMap.get("OPEN_CIRCLE")+"];";
                }
                // 其他规律开放周期（天）
                if (StringUtil.isNotEmpty(dataMap.get("OPEN_CIRCLE_DAYS"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,其他规律开放周期（天）不能有值[OPEN_CIRCLE_DAYS="+dataMap.get("OPEN_CIRCLE_DAYS")+"];";
                }
                // 无规律开放说明
                if (StringUtil.isNotEmpty(dataMap.get("RULE_COMMENT"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,无规律开放说明不能有值[RULE_COMMENT="+dataMap.get("RULE_COMMENT")+"];";
                }
                // 首次开放周期起始日
                if (StringUtil.isNotEmpty(dataMap.get("FIRST_OPEN_DAY"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,首次开放周期起始日不能有值[FIRST_OPEN_DAY="+dataMap.get("FIRST_OPEN_DAY")+"];";
                }
                // 节假日是否开放
                if (StringUtil.isNotEmpty(dataMap.get("HOLIDAY_ON_FLAG"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,节假日是否开放不能有值[HOLIDAY_ON_FLAG="+dataMap.get("HOLIDAY_ON_FLAG")+"];";
                }
                // 平均开放次数（年化）
                if (StringUtil.isNotEmpty(dataMap.get("AVG_OPEN_COUNT"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,平均开放次数（年化）不能有值[AVG_OPEN_COUNT="+dataMap.get("AVG_OPEN_COUNT")+"];";
                }
                // 开放期业务
                if (StringUtil.isNotEmpty(dataMap.get("OPEN_BUSS"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,开放期业务不能有值[OPEN_BUSS="+dataMap.get("OPEN_BUSS")+"];";
                }
                // 开放期业务说明
                if (StringUtil.isNotEmpty(dataMap.get("OPEN_BUSS_REMARK"))){
                    errMsg +="产品运作模式选择“封闭式净值型”和“封闭式非净值型”的产品,开放期业务说明不能有值[OPEN_BUSS_REMARK="+dataMap.get("OPEN_BUSS_REMARK")+"];";
                }
            }
            if (StringUtil.equals(operateMode, DictCodeListExt2.产品运作模式_开放式净值型.getValue())
                    || StringUtil.equals(operateMode, DictCodeListExt2.产品运作模式_开放式非净值型.getValue())){
                // 开放模式
                if (StringUtil.isEmpty(dataMap.get("OPEN_MODE"))){
                    errMsg +="产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,开放模式不能为空[OPEN_MODE="+dataMap.get("OPEN_MODE")+"];";
                }
                // 首次开放周期起始日
                if (StringUtil.isEmpty(dataMap.get("FIRST_OPEN_DAY"))){
                    errMsg +="产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,首次开放周期起始日不能为空[FIRST_OPEN_DAY="+dataMap.get("FIRST_OPEN_DAY")+"];";
                }
                // 节假日是否开放
                if (StringUtil.isEmpty(dataMap.get("HOLIDAY_ON_FLAG"))){
                    errMsg +="产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,节假日是否开放不能为空[HOLIDAY_ON_FLAG="+dataMap.get("HOLIDAY_ON_FLAG")+"];";
                }
                // 平均开放次数（年化）
                if (StringUtil.isEmpty(dataMap.get("AVG_OPEN_COUNT"))){
                    errMsg +="产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,平均开放次数（年化）不能为空[AVG_OPEN_COUNT="+dataMap.get("AVG_OPEN_COUNT")+"];";
                }
                // 开放期业务
                if (StringUtil.isEmpty(dataMap.get("OPEN_BUSS"))){
                    errMsg +="产品运作模式选择“开放式净值型”和“开放式非净值型”的产品,开放期业务不能为空[OPEN_BUSS="+dataMap.get("OPEN_BUSS")+"];";
                }
            }
        }


        dataMap.put("ERRMSG", errMsg);
    }

    /**
     * 代码检查产品申报登记
     * @param dataMap
     */
    public  void  checkPrdoDeclare(Map<String, String> dataMap,List<Map<String, String>> list){
        String  errMsg=dataMap.getOrDefault("ERRMSG", "");
        //行内标识码
        String   prodInsId=dataMap.get("PROD_INS_ID");
        //发行机构代码
        String   issuePartyId=dataMap.get("ISSUE_PARTY_ID");
        //投资资产种类及比例要素
        String   invTypeRat=dataMap.get("INV_TYPE_RAT");
        if(StringUtil.isNotEmpty(issuePartyId)){
        //发行机构代码必须与银行代码相同。
        // 银行模式才判断发行机构代码，联社模式不判断
        if(!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)
                && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
            errMsg +="发行机构代码必须与银行代码相同[ISSUE_PARTY_ID="+issuePartyId+"];";
        }
        }
        //不同产品之间，产品名称与产品期次不得同时重复
        List<String>  listCheck1=comminCodeCheckMapper.genChbPrdDeclareSub1ByProdInsId(prodInsId);
        //产品名称和产品期次不能同时重复！
        List<String>  listCheck2=comminCodeCheckMapper.genChbPrdDeclareSub2ByProdInsId(prodInsId);
        if(listCheck2.size()>0||listCheck1.size()>0){
            errMsg +="产品名称和产品期次不能同时重复[PROD_NAME="+dataMap.get("PROD_NAME")+",PROD_STAGE="+dataMap.get("PROD_STAGE")+"];";
        }
        //投资资产种类及比例要素的判断
        boolean  flag=true;
        String  regex="^(100|[1-9]?\\d(\\.\\d\\d?)?)%$|0$";
        if(StringUtil.isNotEmpty(invTypeRat)){
            //对于投资资产种类及比例要素按照";"切割成各个单元
            List<String>  invTaypeRatList=Arrays.asList(invTypeRat.split(GlobalConstant.SEMICOLON));
            //循环list，对于单元进行处理
            for(String invTaype:invTaypeRatList){
                //按照":"对于单元进行切割
                List<String>  invTaypePatten=Arrays.asList(invTaype.split(GlobalConstant.COLON));
                //单元切割后，不是2个部分。则格式不通过
                if(invTaypePatten!=null&&invTaypePatten.size()!=2){
                    flag=false;
                    break;
                }else{
                    //单元切割后是2个部分，获取数值和文字
                    //数值
                    String  pattenOne=invTaypePatten.get(0);
                    if(pattenOne.contains(GlobalConstant.SPLIT)){
                        //表示为区间
                        List<String>  pattenOneSection=Arrays.asList(pattenOne.split(GlobalConstant.SPLIT));
                        //校验区间
                        if(!pattenOneSection.get(0).matches(regex)||
                                !pattenOneSection.get(1).matches(regex)){
                            flag=false;
                            break;
                        }
                    }else{
                        //不是区间，单个数字百分号
                        if(!pattenOne.matches(regex)){
                            flag=false;
                            break;
                        }
                    }
                    //文字
                    //String  pattenTwo=invTaypePatten.get(1);
                }
            }
        }
        if(!flag){
            errMsg +="投资资产种类及比例要素格式不对。正确的格式：数字%：文字；数字%-数字%：文字（其中，百分号、冒号、分号、连字号均应为英文标点，文字和数字部分均不得含有英文百分号、冒号、分号、连字号）。其中，数字格式为n..（5，2），且需大于等于0、小于等于100。[INV_TYPE_RAT="+dataMap.get("INV_TYPE_RAT")+"];";
        }
        
        dataMap.put("ERRMSG", errMsg);
    }

    // add by dubaojun 2020-10-12 配置无法实现，代码实现的check start
    /**
     * 代码检查产品交易登记
     *
     * @param dataMap 被检查的数据
     * @param prodRegistCodeMap 产品登记编码需要用的数据
     */
    public void checkInvestTradeInfo(Map<String, String> dataMap, Map<String, List<String>> prodRegistCodeMap) {
        StringBuilder errMsgBuilder = new StringBuilder(dataMap.getOrDefault("ERRMSG", ""));
        // 发行机构代码
        String registBankCode = dataMap.get("REGIST_BANK_CODE");
        // 产品登记编码
//        String prodRegistCode = dataMap.get("PROD_REGIST_CODE");
//        // 资金流动类型
//        String curAssetType = dataMap.get("CUR_ASSET_TYPE");
//        // 行内交易编码
//        String tradeCode = dataMap.get("TRADE_CODE");
//        // 行内资产/负债编码
//        String assetCode = dataMap.get("ASSET_CODE");
        // 1、发行机构代码必须与银行代码相同。
        // 银行模式才判断发行机构代码，联社模式不判断
        if(StringUtil.isNotEmpty(registBankCode) && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
        if (!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), registBankCode)) {
            errMsgBuilder.append("发行机构代码必须与银行代码相同[").append("REGIST_BANK_CODE=").append(registBankCode).append("];");
        }
        }
//        if (StringUtil.isNotEmpty(prodRegistCode)) {
//          // 2、产品登记编码是否匹配数据库中已有的记录
//          List<ChbPrdresistCode> chbPrdresistCodeList = comminCodeCheckMapper.queryChbPrdresistCodeByRegCode(prodRegistCode);
//          if (StringUtil.isEmptyList(chbPrdresistCodeList)) {
//              errMsgBuilder.append("产品不存在[").append("PROD_REGIST_CODE=").append(prodRegistCode).append("];");
//          }
            // 3、对于同一只理财产品（产品登记编码相同），当“资金流动类型”选择01-10选项的情况，“行内交易编码”与“行内资产/负债编码”和“资金流动类型”这3个要素不允许同时重复
            // 获取产品对应的数据
//            List<String> list = prodRegistCodeMap.get(prodRegistCode);
//            if(list == null) {
//                list = new ArrayList<>();
//                prodRegistCodeMap.put(prodRegistCode, list);
//            }
//            if("01".equals(curAssetType) || "02".equals(curAssetType) || "03".equals(curAssetType) || "04".equals(curAssetType)
//                    || "05".equals(curAssetType) || "06".equals(curAssetType) || "07".equals(curAssetType)
//                    || "08".equals(curAssetType) || "09".equals(curAssetType) || "10".equals(curAssetType)) {
//                String key = tradeCode + "_" + assetCode + "_" + curAssetType;
//                if(list.contains(key)) {
//                    errMsgBuilder.append("同一只理财产品，当“资金流动类型“选择01-10时，“行内交易编码”与“行内资产/负债编码”和“资金流动类型”在报文内不可同时重复[")
//                            .append("PROD_REGIST_CODE=").append(prodRegistCode).append(",CUR_ASSET_TYPE=").append(curAssetType)
//                            .append(",TRADE_CODE=").append(tradeCode).append(",ASSET_CODE=").append(assetCode).append("];");
//                }
//                else {
//                    list.add(key);
//                }
//            }
//            // 4、对于同一只理财产品（产品登记编码相同），当“资金流动类型”选择11-12选项，“行内交易编码”和“资金流动类型”这2个要素不允许同时重复
//            else if("11".equals(curAssetType) || "12".equals(curAssetType)) {
//                String key = assetCode + "_" + curAssetType;
//                if(list.contains(key)) {
//                    errMsgBuilder.append("同一只理财产品，当“资金流动类型”选择11-12时，“行内交易编码”和“资金流动类型”在报文内不可同时重复[")
//                            .append("PROD_REGIST_CODE=").append(prodRegistCode).append(",CUR_ASSET_TYPE=").append(curAssetType)
//                            .append(",TRADE_CODE=").append(tradeCode).append("];");
//                }
//                else {
//                    list.add(key);
//                }
//            }
//          // 5、对于同一只理财产品，当“资金流动类型”选择01-10选项的情况，该要素与“行内资产/负债编码”和“资金流动类型”这3个要素不允许同时与登记信息重复
//          Map<String, String> paraMap = new HashMap<>();
//          paraMap.put("prodId", dataMap.get("PROD_ID"));
//          paraMap.put("curAssetType", curAssetType);
//          paraMap.put("tradeCode", tradeCode);
//          List<String> assetCodeList = comminCodeCheckMapper.selectChbInvestTradeSubByBussKey(paraMap);
//          if("01".equals(curAssetType) || "02".equals(curAssetType) || "03".equals(curAssetType) || "04".equals(curAssetType)
//                  || "05".equals(curAssetType) || "06".equals(curAssetType) || "07".equals(curAssetType)
//                  || "08".equals(curAssetType) || "09".equals(curAssetType) || "10".equals(curAssetType)) {
//              if(assetCodeList.contains(assetCode)) {
//                  errMsgBuilder.append("理财交易记录已存在[")
//                          .append("PROD_REGIST_CODE=").append(prodRegistCode).append(",CUR_ASSET_TYPE=").append(curAssetType)
//                          .append(",TRADE_CODE=").append(tradeCode).append(",ASSET_CODE=").append(assetCode).append("];");
//              }
//          }
//          // 6、对于同一只理财产品，当“资金流动类型”选择11-12选项，该要素和“资金流动类型”这2个要素不允许同时与登记信息重复
//          else if("11".equals(curAssetType) || "12".equals(curAssetType)) {
//              if(!assetCodeList.isEmpty()) {
//                  errMsgBuilder.append("理财交易记录已存在[")
//                          .append("PROD_REGIST_CODE=").append(prodRegistCode).append(",CUR_ASSET_TYPE=").append(curAssetType)
//                          .append(",TRADE_CODE=").append(tradeCode).append("];");
//              }
//          }
//        }
//      // 7、行内资产/负债编码是否匹配数据库中已有的记录
//      if(StringUtil.isNotEmpty(assetCode)) {
//          int count = comminCodeCheckMapper.countChbAssetInfoSubByAssetCode(assetCode);
//          if(count == 0) {
//              errMsgBuilder.append("未进行资产要素登记[").append("ASSET_CODE=").append(assetCode).append("];");
//          }
//      }
        dataMap.put("ERRMSG", errMsgBuilder.toString());
    }

    /**
     * 代码检查资产估值登记
     *
     * @param dataMap 被检查的数据
     * @param list 所有被检查的数据
     */
    public void checkAstValInfo(Map<String, String> dataMap, List<Map<String, String>> list) {
        StringBuilder errMsgBuilder = new StringBuilder(dataMap.getOrDefault("ERRMSG", ""));
        // 发行机构代码
        String issuePartyId = dataMap.get("ISSUE_PARTY_ID");
        // 1、发行机构代码必须与银行代码相同。
        // 银行模式才判断发行机构代码，联社模式不判断
        if(StringUtil.isNotEmpty(issuePartyId) && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
        if (!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)) {
            errMsgBuilder.append("发行机构代码必须与银行代码相同[").append("ISSUE_PARTY_ID=").append(issuePartyId).append("];");
        }}
        dataMap.put("ERRMSG", errMsgBuilder.toString());
    }
    // add by dubaojun 2020-10-12 配置无法实现，代码实现的check end

    /**
     * 代码检查资产估值登记
     *
     * @param dataMap 被检查的数据
     * @param list 所有被检查的数据
     */
    public void checkAssetSubInfo(Map<String, String> dataMap) {
        StringBuilder errMsgBuilder = new StringBuilder(dataMap.getOrDefault("ERRMSG", ""));
        // 发行机构代码
        String issuePartyId = dataMap.get("ISSUE_PARTY_ID");
        //固定收益部分所占比例
        String   incomeRatio= dataMap.get("INCOME_RATIO");
        //衍生金融工具所占比例
        String   derInstrRatio= dataMap.get("DER_INSTR_RATIO");
        //二级资产类型
        String   assetType2=dataMap.get("ASSET_TYPE2");

        // 1、发行机构代码必须与银行代码相同。
        // 银行模式才判断发行机构代码，联社模式不判断
        if(StringUtil.isNotEmpty(issuePartyId) && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
        if (!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)) {
            errMsgBuilder.append("发行机构代码必须与银行代码相同[").append("ISSUE_PARTY_ID=").append(issuePartyId).append("];");
        }
        }
        if(StringUtil.equals(assetType2, "1503")){
        //“固定收益部分所占比例”与“衍生金融工具所占比例”之和需小于等于1
        if(StringUtil.isNotEmpty(incomeRatio)&&isBigDecimal(incomeRatio)&&
                StringUtil.isNotEmpty(derInstrRatio)&&isBigDecimal(derInstrRatio)){
            if(new BigDecimal(incomeRatio).add(new BigDecimal(derInstrRatio)).compareTo(new BigDecimal(100))>0){
                errMsgBuilder.append("以百分数形式表示的固定收益部分   所占比例与衍生金融工具所占比例之和需小于等于100[").append("INCOME_RATIO=").append(incomeRatio).append(",DER_INSTR_RATIO").append(derInstrRatio).append("];");
            }
        }
        }

        //投资资产种类及比例要素的判断
        //投资资产种类及比例要素
        String   invTypeRat=dataMap.get("FUND_INVEST_ASSET");
        boolean  flag=true;
        String  regex="^(100|[1-9]?\\d(\\.\\d\\d?)?)%$|0$";
        if(StringUtil.isNotEmpty(invTypeRat)){
            //对于投资资产种类及比例要素按照";"切割成各个单元
            List<String>  invTaypeRatList=Arrays.asList(invTypeRat.split(GlobalConstant.SEMICOLON));
            //循环list，对于单元进行处理
            for(String invTaype:invTaypeRatList){
                //按照":"对于单元进行切割
                List<String>  invTaypePatten=Arrays.asList(invTaype.split(GlobalConstant.COLON));
                //单元切割后，不是2个部分。则格式不通过
                if(invTaypePatten!=null&&invTaypePatten.size()!=2){
                    flag=false;
                    break;
                }else{
                    //单元切割后是2个部分，获取数值和文字
                    //数值
                    String  pattenOne=invTaypePatten.get(0);
                    if(pattenOne.contains(GlobalConstant.SPLIT)){
                        //表示为区间
                        List<String>  pattenOneSection=Arrays.asList(pattenOne.split(GlobalConstant.SPLIT));
                        //校验区间
                        if(!pattenOneSection.get(0).matches(regex)||
                                !pattenOneSection.get(1).matches(regex)){
                            flag=false;
                            break;
                        }
                    }else{
                        //不是区间，单个数字百分号
                        if(!pattenOne.matches(regex)){
                            flag=false;
                            break;
                        }
                    }
                    //文字
                    //String  pattenTwo=invTaypePatten.get(1);
                }
            }
        }
        if(!flag){
            errMsgBuilder.append("投资资产种类及比例要素格式不对。正确的格式：数字%：文字；数字%-数字%：文字（其中，百分号、冒号、分号、连字号均应为英文标点，文字和数字部分均不得含有英文百分号、冒号、分号、连字号）。其中，数字格式为n..（5，2），且需大于等于0、小于等于100。[FUND_INVEST_ASSET="+dataMap.get("FUND_INVEST_ASSET")+"];");
        }

        dataMap.put("ERRMSG", errMsgBuilder.toString());
    }
    
    /**
     * 代码检查产品存续期交易临时表
     * @param dataMap
     */
    public  void  checkProdDeal(Map<String, String> dataMap,List<Map<String, String>> list){
        String  errMsg=dataMap.getOrDefault("ERRMSG", "");
        // 数据信息
        Map<String, String> data = new HashMap<>();
        // 规则对象
        CheckElement rule = new CheckElement();
        // 产品登记编码
        String prodRegistCode = dataMap.get("PROD_REGIST_CODE");
        //发行机构代码
        String   issuePartyId=dataMap.get("ISSUE_PARTY_ID");
        // 理财产品代码
        String prodId = dataMap.get("PROD_ID");
        //币种以及申购兑付金额
        String   ccyPurChase =dataMap.get("CCY_PURCHASE");//CNY,135500.00,10700.00,0.00;EUR,235500.00,20700.00,0.00;

        //获取申报登记相关
        List<ChbPrdDeclareSub> chbPrdDeclareSubList = new ArrayList<>();
        if (StringUtil.isNotEmpty(prodId)){
            Map<String, Object> map = new HashMap<>();
            map.put("prodId", prodId);
            chbPrdDeclareSubList = comminCodeCheckMapper.queryChbPrdDeclareSubByRegCode(map);
        }
        // 银行模式才判断发行机构代码，联社模式不判断
        if(StringUtil.isNotEmpty(issuePartyId) && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
        //发行机构代码必须与银行代码相同。
        if(!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)){
            errMsg +="发行机构代码必须与银行代码相同[ISSUE_PARTY_ID="+issuePartyId+"];";
        }}
//      // 该产品不存在。
//      if (StringUtil.isNotEmpty(prodRegistCode)){
//          List<ChbPrdresistCode> chbPrdresistCodeList = comminCodeCheckMapper.queryChbPrdresistCodeByRegCode(prodRegistCode);
//          if (StringUtil.isEmptyList(chbPrdresistCodeList)){
//              errMsg +="该产品不存在[PROD_REGIST_CODE="+prodRegistCode+"];";
//          }
//      }
        if (!StringUtil.isEmptyList(chbPrdDeclareSubList)){
            //运作模式
            String  operateMode=chbPrdDeclareSubList.get(0).getOperateMode();
            if (StringUtil.equals(operateMode, DictCodeListExt2.产品运作模式_开放式净值型.getValue())||
                    StringUtil.equals(operateMode, DictCodeListExt2.产品运作模式_封闭式净值型.getValue())   ){
                //对于净值型产品，净值币种不能为空
                if (StringUtil.isEmpty(dataMap.get("VALUE_CCY"))){
                    errMsg +="对于净值型产品,净值币种不能为空[VALUE_CCY="+dataMap.get("VALUE_CCY")+"];";
                }
                //对于净值型产品,初始净值不能为空  
                if (StringUtil.isEmpty(dataMap.get("ORIGINAL_VALUE"))){
                    errMsg +="对于净值型产品,初始净值不能为空[ORIGINAL_VALUE="+dataMap.get("ORIGINAL_VALUE")+"];";
                }
                //对于净值型产品,产品净值不能为空。
                if (StringUtil.isEmpty(dataMap.get("PRD_VALUE"))){
                    errMsg +="对于净值型产品,产品净值不能为空[PRD_VALUE="+dataMap.get("PRD_VALUE")+"];";
                }
                //对于净值型产品,累计净值不能为空。
                if (StringUtil.isEmpty(dataMap.get("TOTAL_VALUE"))){
                    errMsg +="对于净值型产品,累计净值不能为空[TOTAL_VALUE="+dataMap.get("TOTAL_VALUE")+"];";
                }
                //对于净值型产品,折算人民币初始净值不能为空。
                if (StringUtil.isEmpty(dataMap.get("CNY_ORL_VALUE"))){
                    errMsg +="对于净值型产品,折算人民币初始净值不能为空[CNY_ORL_VALUE="+dataMap.get("CNY_ORL_VALUE")+"];";
                }
                //对于净值型产品,折算人民币净值不能为空。
                if (StringUtil.isEmpty(dataMap.get("CNY_PRD_VALUE"))){
                    errMsg +="对于净值型产品,折算人民币净值不能为空[CNY_PRD_VALUE="+dataMap.get("CNY_PRD_VALUE")+"];";
                }
                //对于净值型产品,折算人民币累计净值不能为空。
                if (StringUtil.isEmpty(dataMap.get("CNY_TAL_VALUE"))){
                    errMsg +="对于净值型产品,折算人民币累计净值不能为空[CNY_TAL_VALUE="+dataMap.get("CNY_TAL_VALUE")+"];";
                }
            }else if(StringUtil.equals(operateMode, DictCodeListExt2.产品运作模式_开放式非净值型.getValue())||
                    StringUtil.equals(operateMode, DictCodeListExt2.产品运作模式_封闭式非净值型.getValue())  ){
                //对于非净值型产品,实现收益率不能为空。
                if (StringUtil.isEmpty(dataMap.get("REA_YIELD"))){
                    errMsg +="对于非净值型产品,实现收益率不能为空[REA_YIELD="+dataMap.get("REA_YIELD")+"];";
                }
                //对于非净值型产品,产品余额不能为空。
                if (StringUtil.isEmpty(dataMap.get("BALANCE_AMT"))){
                    errMsg +="对于非净值型产品,产品余额不能为空[BALANCE_AMT="+dataMap.get("BALANCE_AMT")+"];";
                }
            }
        }
        //分割币种以及兑付金额字符串
        if(StringUtil.isNotEmpty(ccyPurChase)){
            String[]   ccyStr=ccyPurChase.split(";");
            Set<String>  ccyset=new  HashSet<>();
            if(ccyStr!=null&&ccyStr.length>0){
                //多种币种以及兑付金额字符
                for(int i=0;i<ccyStr.length;i++){
                    String  st1=ccyStr[i];
                    String[]   st1str=st1.split(",");
                    //币种
                    String  ccy="";
                    //币种累计申购金额
                    String  ccyPurchase="";
                    //币种累计兑付金额
                    String  ccyCash="";
                    //币种累计兑付收益
                    String  ccyProfit="";
                    //获取币种
                    if(st1str!=null&&st1str.length<4){
                        errMsg +="币种及申购兑付金额格式不正确!正确格式:CNY,100,100,100[CCY_PURCHASE="+st1+"];";
                        //break;
                    }else{
                        //获取币种
                        ccy=st1str[0];
                        ccyset.add(ccy);
                        //币种累计申购金额
                        ccyPurchase=st1str[1];
                        //币种累计兑付金额
                        ccyCash=st1str[2];
                        //币种累计兑付收益
                        ccyProfit=st1str[3];
                    
                    //币种要素不可为空。
                    if(StringUtil.isEmpty(ccy)){
                        errMsg +="币种要素不可为空[CCY_PURCHASE="+ccy+"];";
                       //break;
                    }
                    //币种不在值域范围内。
                    if(StringUtil.isNotEmpty(ccy)){
                        if(!DictCodeListExt1.valuesfromDictCode(DictCode.币种_中债).contains(ccy)){
                            errMsg +="币种不在值域范围内[CCY_PURCHASE="+ccy+"];";
                               //break;
                        }
                    }

                    //该币种累计申购金额（元）要素不可为空。
                    if(StringUtil.isEmpty(ccyPurchase)){
                        errMsg +="该币种累计申购金额（元）要素不可为空[CCY_PURCHASE="+ccyPurchase+"];";
                           //break;
                    }
                    //该币种累计申购金额（元）必须大于等于0。
                    if(StringUtil.isNotEmpty(ccyPurchase)&&isBigDecimal(ccyPurchase)){
                        if(BigDecimal.ZERO.compareTo(new  BigDecimal(ccyPurchase))>0){
                            errMsg +="币种累计申购金额（元）必须大于等于0[CCY_PURCHASE="+ccyPurchase+"];";
                               //break;
                        }
                    }
                    //该币种累计申购金额（元）必须为n（15,2）格式。
                    rule.setSrcproperty("SUBSCRIBEAMT");
                    rule.setObjvalue("15,2");
                    rule.setNullable("Y");
                    data.put("SUBSCRIBEAMT", ccyPurchase);
                    if (!BaseCheckLib.isNumber2(data, rule)){
                        errMsg +="该币种累计申购金额（元）必须为n（15,2）格式[CCY_PURCHASE="+ccyPurchase+"];";
                           //break;
                    }

                    //该币种累计兑付金额（元）要素不可为空。
                    if(StringUtil.isEmpty(ccyCash)){
                        errMsg +="该币种累计兑付金额（元）要素不可为空[CCY_PURCHASE="+ccyCash+"];";
                           //break;
                    }
                    //该币种累计兑付金额（元）必须大于等于0。
                    if(StringUtil.isNotEmpty(ccyCash)&&isBigDecimal(ccyPurchase)){
                        if(BigDecimal.ZERO.compareTo(new  BigDecimal(ccyCash))>0){
                            errMsg +="币种累计兑付金额（元）必须大于等于0[CCY_PURCHASE="+ccyCash+"];";
                              // break;
                        }
                    }
                    //该币种累计兑付金额（元）必须为n（15,2）格式。
                    rule.setSrcproperty("SUBSCRIBEAMT");
                    rule.setObjvalue("15,2");
                    rule.setNullable("Y");
                    data.put("SUBSCRIBEAMT", ccyCash);
                    if(!BaseCheckLib.isNumber2(data, rule)){
                        errMsg +="该币种累计兑付金额（元）必须为n（15,2）格式[CCY_PURCHASE="+ccyCash+"];";
                           //break;
                    }

                    //该币种累计兑付收益金额（元）要素不可为空。
                    if(StringUtil.isEmpty(ccyProfit)){
                        errMsg +="该币种累计兑付收益金额（元）要素不可为空[CCY_PURCHASE="+ccyProfit+"];";
                           //break;
                    }
                    //该币种累计兑付收益金额（元）必须为n（15,2）格式。
                    rule.setSrcproperty("SUBSCRIBEAMT");
                    rule.setObjvalue("15,2");
                    rule.setNullable("Y");
                    data.put("SUBSCRIBEAMT", ccyProfit);
                    if(!BaseCheckLib.isNumber2(data, rule)){
                        errMsg +="该币种累计兑付收益金额（元）必须为n（15,2）格式[CCY_PURCHASE="+ccyProfit+"];";
                           //break;
                    }
                    
                    //该币种累计兑付收益金额（元）必须小于等于该币种累计兑付金额（元）。
                    if(StringUtil.isNotEmpty(ccyProfit)&&StringUtil.isNotEmpty(ccyCash)
                            &&isBigDecimal(ccyProfit)&&isBigDecimal(ccyCash)){
                    if(new BigDecimal(ccyProfit).compareTo(new BigDecimal(ccyCash))>0){
                        errMsg +="该币种累计兑付收益金额（元）必须小于等于该币种累计兑付金额（元）[CCY_PURCHASE="+st1+"];";
                           //break;
                    }
                    }
                    //TODO  币种必须为人名币 待确认
                    }
                }
            }
            if(ccyset!=null&&ccyset.size()>0&&ccyStr!=null&&ccyStr.length>0
                    ){
                if(ccyset.size()<ccyStr.length)
                    errMsg +="币种多选选项不能重复[CCY_PURCHASE="+ccyPurChase+"];";
            }
        }
        dataMap.put("ERRMSG", errMsg);
    }
    /**
     * 代码检查产品终止数据
     * @param dataMap
     * @param list
     */
    public  void  checkProdEndSubTmp(Map<String, String> dataMap,Set<String> prodRegistCodeList){
        String  errMsg=dataMap.getOrDefault("ERRMSG", "");
        //发行机构代码
        String   issuePartyId=dataMap.get("ISSUE_PARTY_ID");
        // 产品登记编码
        String prodRegistCode = dataMap.get("PROD_REGIST_CODE");
        //产品实际终止日
        String mDate=dataMap.get("MDATE");
        //理财产品代码
        String  prodId=dataMap.get("PROD_ID");
        //银行实际实现收入
        String  profit=dataMap.get("PROFIT");
        //本机构管理费
        String  manFeeIn=dataMap.get("MAN_FEE_IN");
        //本机构托管费
        String custFeeIn=dataMap.get("CUST_FEE_IN");
        //本机构销售手续费
        String  couFeeIn=dataMap.get("COU_FEE_IN");
        //本机构其他产品费用
        String  otherFeeIn=dataMap.get("OTHER_FEE_IN");
        // 产品登记编码报文内不可重复。
        if(StringUtil.isNotEmpty(prodRegistCode) && prodRegistCodeList.contains(prodRegistCode)){
            errMsg +="产品登记编码报文内不可重复[PROD_REGIST_CODE="+prodRegistCode+"];";
        }else{
            prodRegistCodeList.add(prodRegistCode);
        }
//      // 该产品不存在。
//      if (StringUtil.isNotEmpty(prodRegistCode)){
//          List<ChbPrdresistCode> chbPrdresistCodeList = comminCodeCheckMapper.queryChbPrdresistCodeByRegCode(prodRegistCode);
//          if (StringUtil.isEmptyList(chbPrdresistCodeList)){
//              errMsg +="该产品不存在[PROD_REGIST_CODE="+prodRegistCode+"];";
//          }
//      }

        //发行机构代码必须与银行代码相同。
        // 银行模式才判断发行机构代码，联社模式不判断
        if(StringUtil.isNotEmpty(issuePartyId) && StringUtil.equals(SvrDataUtil.getSysParam("AUTHORITY_TYPE"), "01")){
        if(!StringUtil.equals(SvrDataUtil.getSysParam(SVReportGlobalConstant.SVR_ISSUE_PARTY_ID), issuePartyId)){
            errMsg +="发行机构代码必须与银行代码相同[ISSUE_PARTY_ID="+issuePartyId+"];";
        }
        }
        //理财产品实际终止日期必须大于发行登记要素中的“产品起始日期”
        List<ChbPrdIssueSub>  listChbPrdIssueSub=comminCodeCheckMapper.getChbPrdIssueSubByProdId(prodId);
        if(!StringUtil.isEmptyList(listChbPrdIssueSub)){
            //产品起始日期
            Date prdVdate=listChbPrdIssueSub.get(0).getPrdVdate();
            if(prdVdate.compareTo(DateUtil.parse(mDate))>=0){
                errMsg +="理财产品实际终止日期应大于发行登记中的“产品起始日期”[MDATE="+mDate+"];";
            }
        }
        //银行实际实现收入=本机构管理费+本机构托管费+本机构销售手续费+本机构其他产品费用。
        try{
            if(new  BigDecimal(profit).compareTo(new BigDecimal(manFeeIn).
                    add(new BigDecimal(custFeeIn).
                            add(new  BigDecimal(otherFeeIn)
                            ).add(new  BigDecimal(couFeeIn))))!=0){
                errMsg +="银行实际实现收入≠本机构管理费+本机构托管费+本机构销售手续费+本机构其他产品费用，请检查这五个元素的值，重新填写[PROFIT="+profit+
                        ",MAN_FEE_IN="+manFeeIn+",CUST_FEE_IN="+custFeeIn+",COU_FEE_IN="+couFeeIn+",OTHER_FEE_IN="+otherFeeIn+"];";
            }}catch(Exception e){
            errMsg +="银行实际实现收入≠本机构管理费+本机构托管费+本机构销售手续费+本机构其他产品费用，请检查这五个元素的值，重新填写[PROFIT="+profit+
                    ",MAN_FEE_IN="+manFeeIn+",CUST_FEE_IN="+custFeeIn+",COU_FEE_IN="+couFeeIn+",OTHER_FEE_IN="+otherFeeIn+"];";

        }

        dataMap.put("ERRMSG", errMsg);

    }

    public static boolean isBigDecimal(String str){
        if(str==null || str.trim().length() == 0){
            return false;
        }
//      char[] chars = str.toCharArray();
//      int sz = chars.length;
//      int i = (chars[0] == '-') ? 1 : 0;
//      if(i == sz) return false;
//      
//      if(chars[i] == '.') return false;//除了负号，第一位不能为'小数点'
//      
//      boolean radixPoint = false;
//      for(; i < sz; i++){
//          if(chars[i] == '.'){
//              if(radixPoint) return false;
//              radixPoint = true;
//          }else if(!(chars[i] >= '0' && chars[i] <= '9')){
//              return false;
//          }
//      }
        return Pattern.matches("^-?[0-9]+.?[0-9]*$", str);
    }

    private void insertErroData(List<ChbInterfErrodataWithBLOBs> erroList) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            logger.debug("错误信息数据插入开始--------------" + new Date());
            ChbInterfErrodataMapper batchMapper = session.getMapper(ChbInterfErrodataMapper.class);
            for(ChbInterfErrodataWithBLOBs single: erroList){
                batchMapper.insert(single);
                count ++;
                // 每20次提交
                if (count % 20 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        catch (Exception e) {
            session.rollback();
            logger.error("SQL执行异常：", e);
        }
        finally {
            session.close();
        }
        logger.debug("错误信息数据插入结束--------------" + new Date());
    }

    /**
     * 批量插入资产要素错误信息表
     * @param erroList 错误信息表
     */
    private void insertChbAssetInfoErroData(List<ChbAssetInfoErrodataWithBLOBs> erroList) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            logger.debug("资产要素登记错误信息数据插入开始--------------" + new Date());
            ChbAssetInfoErrodataMapper batchMapper = session.getMapper(ChbAssetInfoErrodataMapper.class);
            for(ChbAssetInfoErrodataWithBLOBs single: erroList){
                batchMapper.insert(single);
                count ++;
                // 每20次提交
                if (count % 20 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        catch (Exception e) {
            session.rollback();
            logger.error("SQL执行异常：", e);
        }
        finally {
            session.close();
        }
        logger.debug("资产要素登记错误信息数据插入结束--------------" + new Date());
    }

    /**
     * 批量插入资产要素错误信息表
     * @param erroList 错误信息表
     */
    private void deleteAssetInfoErrorDataByAssetCode(List<ChbAssetInfoErrodataWithBLOBs> erroList) {
        SqlSession session = template.getSqlSessionFactory().openSession(ExecutorType.BATCH, false);
        try {
            int count = 0;
            logger.debug("资产要素登记错误信息数据删除开始--------------" + new Date());
            CommonCodeCheckMapper batchMapper = session.getMapper(CommonCodeCheckMapper.class);
            for(ChbAssetInfoErrodataWithBLOBs single: erroList){
                if (StringUtil.isNotEmpty(single.getAssetCode())){
                    batchMapper.deleteAssetInfoErrorDataByAssetCode(single.getAssetCode());
                    count ++;
                }
                // 每20次提交
                if (count % 20 == 0) {
                    session.commit();
                    // 清理缓存，防止溢出
                    session.clearCache();
                }
            }
            session.commit();
            // 清理缓存，防止溢出
            session.clearCache();
        }
        catch (Exception e) {
            session.rollback();
            logger.error("SQL执行异常：", e);
        }
        finally {
            session.close();
        }
        logger.debug("资产要素登记错误信息数据删除结束--------------" + new Date());
    }

    /**
     * 根据错误消息创建需要入库的Dto
     *
     * @param tableName 检查的表名
     * @param map 数据
     * @param dataId 业务主键
     * @return 需要入库的Dto
     */
    public ChbInterfErrodataWithBLOBs createChbInterfErrdata(String fireName,
                                                             String interfType, Map<String, String> map,
                                                             String bussinessKey, String... checkEuplicatedKeys) {
        String msg = map.get("ERRMSG");
        if (StringUtil.isEmpty(msg)) {
            return null;
        }
        String dataId = null;
        if(StringUtil.isEmpty(bussinessKey) || StringUtil.isEmpty(map.get(bussinessKey))){
            //若根据业务主键找不到值，则根据checkEuplicatedKeys联合主键来进行赋值
            StringBuffer keyVFinal = new StringBuffer();
            for(String key: checkEuplicatedKeys){
                keyVFinal.append(map.get(key));
                keyVFinal.append("|");
            }
            dataId = keyVFinal.toString();
        }
        else {
            dataId = map.get(bussinessKey);
        }
        // 拼装错误信息
        ChbInterfErrodataWithBLOBs errodata = new ChbInterfErrodataWithBLOBs();
        // 主键uuid
        errodata.setDatauuid(IDGenerator.generate(36));
        // 文件名
        errodata.setFireName(fireName);
        // 业务主键
        errodata.setDataid(dataId);
        // 接口类型
        errodata.setInterfType(interfType);
        // 拼接错误字段信息
        Map<String, String> newMap = new HashMap<>(map);
        newMap.remove("ERRMSG");
        errodata.setOrgData(JsonUtils.object2Json(newMap));
        // 错误提示信息
        errodata.setErrMsg(msg);
        // 数据状态
        errodata.setStatus("E");
        errodata.setCreateUser("system");
        errodata.setUpdateUser("system");
        errodata.setCreateTime(new Date());
        errodata.setUpdateTime(new Date());
        return errodata;
    }

    /**
     * 根据错误消息创建需要入库的Dto
     *
     * @param map 数据
     * @return 需要入库的Dto
     */
    public ChbAssetInfoErrodataWithBLOBs createChbAssetInfoInterfErrdata(Map<String, String> map) {
        String msg = map.get("ERRMSG");
        if (StringUtil.isEmpty(msg)) {
            return null;
        }
        // 拼装错误信息
        ChbAssetInfoErrodataWithBLOBs chbAssetInfoErrodata = new ChbAssetInfoErrodataWithBLOBs();
        // 主键uuid
        chbAssetInfoErrodata.setDatauuid(IDGenerator.generate(36));
        // 行内资产负债编码
        chbAssetInfoErrodata.setAssetCode(map.get("ASSET_CODE"));
        // 资产名称
        chbAssetInfoErrodata.setAssetName(map.get("ASSET_NAME"));
        // 资产类型
        chbAssetInfoErrodata.setAssetType(map.get("ASSET_TYPE"));
        // 资产/负债二级类别
        chbAssetInfoErrodata.setAssetType2(map.get("ASSET_TYPE2"));
        // 起息日
        chbAssetInfoErrodata.setVdate(map.get("VDATE"));
        // 到期日
        chbAssetInfoErrodata.setMdate(map.get("MDATE"));
        // 上游资产一级分类
        chbAssetInfoErrodata.setOrgAssetType1(map.get("ORG_ASSET_TYPE1"));
        // 上游资产二级分类
        chbAssetInfoErrodata.setOrgAssetType2(map.get("ORG_ASSET_TYPE2"));
        // 上游资产三级分类
        chbAssetInfoErrodata.setOrgAssetType3(map.get("ORG_ASSET_TYPE3"));
        // 上游资产四级分类
        chbAssetInfoErrodata.setOrgAssetType4(map.get("ORG_ASSET_TYPE4"));
        // 上游资产五级分类
        chbAssetInfoErrodata.setOrgAssetType5(map.get("ORG_ASSET_TYPE5"));
        // 拼接错误字段信息
        Map<String, String> newMap = new HashMap<>(map);
        newMap.remove("ERRMSG");
        chbAssetInfoErrodata.setOrgData(JsonUtils.object2Json(newMap));
        // 错误提示信息
        chbAssetInfoErrodata.setErrMsg(msg);
        // 数据状态
        chbAssetInfoErrodata.setStatus("E");
        chbAssetInfoErrodata.setCreateUser("system");
        chbAssetInfoErrodata.setUpdateUser("system");
        chbAssetInfoErrodata.setCreateTime(new Date());
        chbAssetInfoErrodata.setUpdateTime(new Date());

        return chbAssetInfoErrodata;
    }
}
