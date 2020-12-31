/*
 * 文件名：EtrPlanAssetCService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：xiexinting
 * 修改时间：2019年1月3日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.entrust.etrplanassetc.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.security.User;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.select.SuggestDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.common.base.entity.TrdProductDealAdd;
import com.joyintech.tams.component.business.common.service.BasRiskMathCommService;
import com.joyintech.tams.component.business.common.service.CommonBusinessService;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.PtlSecPositionFlowDto;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.AllTradeCommonInputDto;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrplanassetc.dto.EtrPlanAssetCDto;
import com.joyintech.tams.invest.entrust.etrwtspotex.dto.EtrInvestPersonInfoDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.exception.WorkFlowException;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * 〈一句话功能简述〉
 * 〈功能详细描述〉
 * @author xiexinting
 * @version 1.0 2019年1月3日
 * @see EtrPlanAssetCService
 * @since 1.0
 */
@Service
public class EtrPlanAssetCService extends CommonExportService<EtrPlanAssetCDto> implements BaseProcessService<EtrPlanAssetCDto> {

    /**
     * 风险试算组件Service
     */
    @Autowired(required = false)
    private BasRiskMathCommService basRiskMathCommService;
    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    /**
     * 共通组件
     */
    @Autowired
    private CommonBusinessService commonBusinessService;
    /**
     * 委托共通
     */
    @Autowired
    private EtrCommonService etrCommonService;

    /**
     * 计划类净值委托分页查询 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public Page<EtrPlanAssetCDto> selectForPage(Map<String, Object> param) {
        if (StringUtil.isNotEmpty((String)param.get("entrustDate"))) {
            List<String> dateList = StringUtil.splitToList(" ", (String)param.get("entrustDate"));
            param.put("startDate", dateList.get(0));
            param.put("endDate", dateList.get(2));
        }
        if(StringUtil.isNotEmpty((String)param.get("assetType1"))){
            List<String> assetType1List = StringUtil.splitToList(",", (String)param.get("assetType1"));
            param.put("assetType1List", assetType1List);
        }
        /*添加三四级分类 lishuping 2019/7/29 start*/
        if(StringUtil.isNotEmpty((String)param.get("assetType10"))){
            List<String> assetType10List = StringUtil.splitToList(",", (String)param.get("assetType10"));
            param.put("assetType10List", assetType10List);
        }
        if(StringUtil.isNotEmpty((String)param.get("assetType11"))){
            List<String> assetType11List = StringUtil.splitToList(",", (String)param.get("assetType11"));
            param.put("assetType11List", assetType11List);
        }
        /*添加三四级分类 lishuping 2019/7/29 end*/
        param.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/findPage", param,
            EtrPlanAssetCDto.class);
    }
    
    /**
     * 组合代码联想文本框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getentrustId(String param) {
        User user = SecurityUtil.getLoginUser();
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getUserName());
        map.put("param", param);
        return restTemplate.postForList(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrPlanAssetC/getentrustId" , map,
            SuggestDto.class);
    }

    /**
     * 资产代码联想文本框 <功能详细描述>
     * 
     * @param param
     * @return
     * @see
     * @since 1.0
     */
    public List<SuggestDto> getfinprodId(String param) {
        return restTemplate.getForList(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrPlanAssetC/getfinprodId?finprodId=" + param,
            SuggestDto.class);
    }

    /**
     * 删除委托 <功能详细描述>
     * 
     * @param entrustId
     * @see
     * @since 1.0
     */
    public boolean deleteDelegate(String entrustId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                         + "/EtrPlanAssetC/delete?entrustId=" + entrustId,
            boolean.class);
    }

    /**
     * 关闭委托 <功能详细描述>
     * 
     * @param entrustId
     * @param closeWay
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay, String entrustStatus) {
        boolean res = restTemplate.getForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/close?entrustId=" + entrustId
                                         + "&closeWay=" + closeWay,
            boolean.class);
        
    	//处理投资指令授信 20200526 start
    	// 已撤回/已退回 的不做授信处理
    	if (! DictCodeList.委托状态_已撤回.getValue().equals(entrustStatus) && ! DictCodeList.委托状态_已退回.getValue().equals(entrustStatus)) {
            dealCloseEtrIsNeedOccupyCre(entrustId);
    	}
        //处理投资指令授信 20200526 end
    	return res;
    }

    /**
     * 撤回委托 <功能详细描述>
     * 
     * @param entity
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrPlanAssetCDto dto) {
        Boolean res = restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/recall", dto, boolean.class);
        
        //处理投资指令授信 20200526 start
        dealCloseEtrIsNeedOccupyCre(dto.getEntrustId());
        //处理投资指令授信 20200526 end
        return res;
    }

    /**
     * 工作流完成
     */
    @Override
    public void doComplete(EtrPlanAssetCDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        Date sysDate =  CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        dto.setEntrustDate(sysDate);
        basRiskMathCommService.insert(dto.getRiskTrialRetDto(), dto.getEntrustId());
        restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/doComplete", dto,
            Integer.class);
        
        //处理投资指令授信 start
        // 买入交易提交占用授信,卖出交易流程走完占用
        if(dto.getPs() != null && 
        		!(dto.getPs().equals(DictCodeList.交易方向_买入.getValue()) || dto.getPs().equals(DictCodeList.交易方向_申购.getValue()))) {
            dealEtrIsNeedOccupyCre(dto, null);
        }
        //处理投资指令授信 end
    }
    
    /**
     * 
     * 流程发起时调用
     */
     @Override
     public void doInsert(EtrPlanAssetCDto dto, OperationType type){
         dto.setOperateType(type.toString());
         restTemplate.postForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/doInsert",dto,
             Integer.class);
         
         // 买入交易提交占用授信,卖出交易流程走完占用
         if(dto.getPs() != null && 
        		 (dto.getPs().equals(DictCodeList.交易方向_买入.getValue()) || dto.getPs().equals(DictCodeList.交易方向_申购.getValue()))) {
             dealEtrIsNeedOccupyCre(dto, null);
         }
     }

     /**
      * 
      * 流程修改时调用
      */
     @Override
     public void doUpdate(EtrPlanAssetCDto dto){
         restTemplate.postForObject(
             ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/doInsert",dto,
             Integer.class);
         
         // 买入交易提交占用授信,卖出交易流程走完占用
         if(dto.getPs() != null && 
        		 (dto.getPs().equals(DictCodeList.交易方向_买入.getValue()) || dto.getPs().equals(DictCodeList.交易方向_申购.getValue()))) {
             dealEtrIsNeedOccupyCre(dto, null);
         }
     }
    /**
     * 新建页面组合代码联想下拉框 <功能详细描述>
     * 
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getentrustIdAdd(Map<String, Object> map) {
        map.put("userId", SecurityUtil.getLoginUser().getUserName());
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrPlanAssetC/getentrustIdAdd" ,map,
            SelectOptionDto.class);
    }

    /**
     * 新建页面资产代码联想下拉框 <功能详细描述>
     * 
     * @param search
     * @return
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME
                                       + "/EtrPlanAssetC/getfinprodIdForAdd",map,SelectOptionDto.class);
    }

    /**
     * 校验方法 <功能详细描述>
     * 
     * @see
     * @since 1.0
     */
    public List<ValidMessage> check(EtrPlanAssetCDto dto) {
        dto.setCloseWay(DictCodeListExt1.委托关闭方式_人工.getValue());
        return restTemplate.postForList(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/check", dto,
            ValidMessage.class);
    }

    /**
     * 根据主键查询修改/查看信息 <功能详细描述>
     * 
     * @param entrustId
     * @return
     * @see
     * @since 1.0
     */
    public EtrPlanAssetCDto selectByKey(String entrustId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                         + "/EtrPlanAssetC/selectByKey?entrustId=" + entrustId,
            EtrPlanAssetCDto.class);
    }
    
    /**
     * 导出
     * 
     * @param exportParam 导出信息
     * @see
     * @since 1.0
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config("/com/joyintech/tams/invest/entrust/etrplanassetc/conf/export_conf.xml")
            .dataSource(DataSourceType.MASERT);
    }
    @Override
    public Page<EtrPlanAssetCDto> selectPageData(Map<String, Object> selectParam) {
        return selectForPage(selectParam);
    }
    @Override
    public void setCellValue(String beanName, Cell cell, String propName, Object value) {
        if(StringUtil.equals(propName, "transactioncom")){
            cell.setCellValue(value+"%");
        }
    }
    
    /**
     * 组合可用现金查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {
        Date vdate = DateUtil.parse((String)param.get("vdate"));
        String calId = (String)param.get("calId");
        if(StringUtil.isNotEmpty(calId)){
            List<String> holidayList = dateCalService.getDealCalendarHoliday(DateUtil.parse(DateUtil.format(vdate)),DateUtil.addDay(DateUtil.parse(DateUtil.format(vdate)), 15),calId);
            Date tradeDay = dateCalService.calculationTradingDay(DateUtil.parse(DateUtil.format(vdate)), 1, holidayList, "1");
            param.put("vdate1",DateUtil.format(tradeDay));
        }
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/getProAvailableCash",param,
            Map.class);
    }

    /**
     * 组合可用持仓查询
     * <功能详细描述>
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal getAssetAvailablePosition(PtlSecPositionFlowDto dto) {
        Date sysDate =  CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        dto.setCdate(DateUtil.parse(DateUtil.format(sysDate)));
        dto.setHodingType(DictCodeList.持仓类型_可用.getValue());
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrPlanAssetC/getAssetAvailablePosition",dto,
            BigDecimal.class);
    }
    
    /**
     * 
     * 查询授权额度
     * <功能详细描述>
     * @param map
     * @return
     * @see
     * @since 1.0
     */
    public BigDecimal selectLimitAmt(EtrPlanAssetCDto dto){
        Map<String, Object> map = new HashMap<>();
        map.put("userName", SecurityUtil.getLoginUser().getUserName());
        map.put("assetType", DictCodeList.平安资产类型_计划类资产_净值型.getValue());
        map.put("assetSecType", dto.getAssetType1());
        map.put("tradeMarket", dto.getTradeMarket());
        map.put("ps",dto.getPs());
        return restTemplate.postForObject(
            ServiceApplication.ENTRUST_SERVICE_NAME + "/common/SelectLimitAmt/selectLimitAmt", map,
            BigDecimal.class);
    }

    // add by renping 授权增加 2019-08-22 start
    @Override
    public Map<String, Object> getRuntimeVariables(ProcessMark entity, String taskId) throws WorkFlowException {
        EtrPlanAssetCDto dto = this.getBussinessEntity(entity);
        if (taskId != null) {
            try {
            	Map<String, Object> params = CommonBusinessService.setParams(dto.getCounterId(), (null == dto.getEntrustAmt() ? dto.getEntrustNum() : dto.getEntrustAmt()),
                    LoginUserUtil.getUserName(),
                    BussinessDictCodeList.平安资产类型_计划类资产_货币型.getValue(), dto.getFinprodType2(),
                    dto.getTradeMarket(), dto.getPs(), dto.getFinprodId(), dto.getWorkflowId());
            	return commonBusinessService.是否需要上级审批_委托用(params);
            }
            catch (WorkFlowException e) {
                // 未设置授权或设置成不授权时，抛出异常，当前用户只能退回
                throw new WorkFlowException(e);
            }
        }
        else {
            return null;
        }
    }
    // add by renping 授权增加 2019-08-22 end
    // 多对多指令新增 add by zhnagpuyu 2020/1/2 start
    /**
     * 获取委托列表数据
     *
     * @return
     */
    public List<EtrPlanAssetCDto> getEntrustListData(String tempList) {
        List<EtrPlanAssetCDto> etrPlanAssetCDtoList = new ArrayList<>();
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++ ) {
            EtrPlanAssetCDto dto = new EtrPlanAssetCDto();
            JSONArray rowData = JSONArray.parseArray(((JSONArray)objects.get(i)).toJSONString());
            // 根据模板中固定的下标来赋值
            // 序号
            dto.setIndexId(rowData.get(0).toString().trim());
            // 委托开始日
            dto.setVdate(DateUtil.parse(rowData.get(1).toString().trim()));
            // 委托终止日
            dto.setMdate(DateUtil.parse(rowData.get(2).toString().trim()));
            // 组合代码
            dto.setPortfolioId(rowData.get(3).toString().trim());
            // 市场代码
            dto.setFinprodMarketId(rowData.get(4).toString().trim());
            // 交易方向
            dto.setPs(rowData.get(5).toString().trim().split(":")[0]);
            // 根据交易方向来赋值委托份额和委托金额
            String[] s2 = rowData.get(5).toString().split(":");
			if (StringUtil.equals(s2[0], DictCodeList.交易方向_买入.getValue())) {
				// 委托份额,委托金额
				dto.setEntrustNum(BigDecimal.valueOf(Double.valueOf(rowData.get(6).toString().trim())));
				dto.setEntrustNumShow(dto.getEntrustNum());
				if (StringUtil.isNotEmpty(rowData.get(7).toString().trim())) {
					dto.setEntrustAmt(BigDecimal.valueOf(Double.valueOf(rowData.get(7).toString().trim())));
					dto.setEntrustAmtShow(dto.getEntrustAmt());
				}
			}
            else if (StringUtil.equals(s2[0], DictCodeList.交易方向_卖出.getValue())) {
                // 委托份额
                dto.setEntrustNum2(
                    BigDecimal.valueOf(Double.valueOf(rowData.get(6).toString().trim())));
                // 当交易方向为卖出的时候，委托金额=委托份额+待结转份额
                if(StringUtil.isNotEmpty(rowData.get(8).toString().trim())){
                dto.setEntrustAmt2(dto.getEntrustNum2().add(
                    BigDecimal.valueOf(Double.valueOf(rowData.get(8).toString().trim()))));
                }else {
                    dto.setEntrustAmt2(dto.getEntrustNum2());
                }
                dto.setEntrustNum(dto.getEntrustNum2());
                dto.setEntrustAmt(dto.getEntrustAmt2());
                dto.setEntrustNumShow(dto.getEntrustNum2());
                dto.setEntrustAmtShow(dto.getEntrustAmt2());
            }
            else if (StringUtil.equals(s2[0], DictCodeList.交易方向_申购.getValue())) {
                dto.setEntrustAmt(
                    BigDecimal.valueOf(Double.valueOf(rowData.get(7).toString().trim())));
                dto.setEntrustAmtShow(dto.getEntrustAmt());
            }
            else {
                dto.setEntrustNum(
                    BigDecimal.valueOf(Double.valueOf(rowData.get(6).toString().trim())));
                dto.setEntrustNumShow(dto.getEntrustNum());
            }
            // 交易方向为买入和卖出的时候页面待结转份额入库
            if ((StringUtil.equals(s2[0], DictCodeList.交易方向_买入.getValue())
                || StringUtil.equals(s2[0], DictCodeList.交易方向_卖出.getValue()))&&StringUtil.isNotEmpty(rowData.get(8).toString().trim())) {
                dto.setPrice(BigDecimal.valueOf(Double.valueOf(rowData.get(8).toString().trim())));
            }
            // 获取备注
            dto.setRemark(rowData.get(9).toString().trim());
            // 获取交易对手
            dto.setCounterId(rowData.get(10).toString().trim());
            // 获取交易对手名称
            dto.setCounterIdName(getCounterName(rowData.get(10).toString().trim()));
            // 投资经理
            dto.setEtrInvestPersonInfo(new EtrInvestPersonInfoDto());
            dto.getEtrInvestPersonInfo().setInvManagerId(dto.getCreateUser());
            // 获取交易对手
            dto.getEtrInvestPersonInfo().setCounterId(rowData.get(10).toString().trim());
            // 投资处室
            dto.getEtrInvestPersonInfo().setRoom(dto.getCreateDept());
            // 根据市场代码查出联动值
            Map<String, Object> map = new HashMap<>();
            map.put("initValue", rowData.get(4).toString());
            map.put("page", 1);
            map.put("length", 20);
            Page<SelectOptionDto> finprodIds = getfinprodIdForAdd(map);
            SelectOptionDto selectOptionDto = finprodIds.getRoot().get(0);
            JSONObject jsonObject = JSONObject.parseObject(
                JSONObject.toJSONString(selectOptionDto.getObject()));
            // 资产名称
            dto.setFinprodName(jsonObject.get("finprodName").toString());
            dto.setCcy(jsonObject.get("ccy").toString());
            dto.setAssetType1(jsonObject.get("assetType1").toString());
            dto.setAssetType10(jsonObject.get("assetType10").toString());
            dto.setAssetType11(jsonObject.get("assetType11").toString());
            if (jsonObject.containsKey("entrustDate")) {
                dto.setEntrustDate(DateUtil.parse(jsonObject.get("entrustDate").toString()));
            }
            dto.setFinprodId(jsonObject.get("finprodId").toString());
			// 指令可用现金
			if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
					|| StringUtil.equals(dto.getPs(), DictCodeList.交易方向_申购.getValue())) {
				Map<String, Object> param = new HashMap<>();
				param.put("finprodId", jsonObject.get("finprodId").toString());
				param.put("portfolioId", dto.getPortfolioId());
				param.put("ccy", dto.getCcy());
				param.put("vdate", DateUtil.format(dto.getVdate()));
				Map<String, Object> proAvailableCash = getProAvailableCash(param);
				if (proAvailableCash != null && proAvailableCash.get("cashT") != null) {
					dto.setEtrAvailableCash(
							BigDecimal.valueOf(Double.valueOf(proAvailableCash.get("cashT").toString())));
                    //组合可用现金
					dto.setProAvailableCash(BigDecimal.valueOf(Double.valueOf(proAvailableCash.get("cashT").toString())));
				}
				// 托管户可用现金
				if ((proAvailableCash!=null&&proAvailableCash.containsKey("cashTG")) && (
						proAvailableCash.get("cashTG") != null)) {
					dto.setAccAvailableCash(
							BigDecimal.valueOf(Double.valueOf(proAvailableCash.get("cashTG").toString())));
				}
			}
			// 指令可用持仓
			if (StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
					|| StringUtil.equals(dto.getPs(), DictCodeList.交易方向_赎回.getValue())) {
				EtrSecPositionDto etrSecPositionDto = new EtrSecPositionDto();
				Date sysDate = DateUtil.platDateFromCache();
				etrSecPositionDto.setCdate(DateUtil.parse(DateUtil.format(sysDate)));
				etrSecPositionDto.setFinprodId(jsonObject.get("finprodId").toString());
				etrSecPositionDto.setPortfolioId(dto.getPortfolioId());
				etrSecPositionDto.setCcy(dto.getCcy());
				BigDecimal assetAvailablePosition = etrCommonService.getAssetAvailablePosition(etrSecPositionDto);
				dto.setAssetAvailablePosition(BigDecimal.valueOf(Double.valueOf(assetAvailablePosition.toString())));
			}
            etrPlanAssetCDtoList.add(dto);
        }
        return etrPlanAssetCDtoList;
    }

    /**
     * 查询交易对手名称
     *
     * @return
     */
    public String getCounterName(String counterId) {
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                                         + "/EtrBondFirstLBidding/getCounterName?counterId="
                                         + counterId,
            String.class);
    }

    /**
     * 验证导入的数据
     *
     * @param tempList 导入的数据
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
    	List<List<String>> objects = ExcelUtil.getTempList(fileName);
        int totalData = 0;
        // 导入数据超过100条做报错信息的check
        for (int i = 2; i < objects.size(); i++ ) {
        	List<String> rowData = objects.get(i);
            for (int j = 0; j <= 10; j++ ) {
                if (StringUtil.isNotEmpty(rowData.get(j))) {
                    totalData++ ;
                    break;
                }
            }
        }
        if (totalData > 99) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", getMessage("ETR_E_0021"));
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }
        else {
            String confirmMsg1 = "";
            JSONArray jsonArrays = new JSONArray();
            // 失败条数
            int errorNum = 0;
            for (int i = 1; i < objects.size(); i++ ) {
                String confirmMsg = "";
                List<String> rowData = objects.get(i);
                String lineNo = String.valueOf(i + 1);
                String indexId = rowData.get(0).toString();
                if (StringUtil.isBlank(indexId)) {
                    confirmMsg = "第" + (i + 1) + "行序号不能为空";
                    break;
                }
                // 委托开始日check非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(1).toString(),
                        "EtrPlanAssetR002.vdate", lineNo));
                // 委托开始日格式check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsDate(rowData.get(1).toString(),
                        "EtrPlanAssetR002.vdate", lineNo));
                // 委托结束日
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(2).toString(),
                        "EtrPlanAssetR002.mdate", lineNo));
                // 委托终止日格式check
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsDate(rowData.get(2).toString(),
                        "EtrPlanAssetR002.mdate", lineNo));
                // 组合代码
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(3).toString(),
                        "EtrPlanAssetR002.portfolioId", lineNo));
                // 组合代码是否存在
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkPortfolioIdExist(rowData.get(3).toString(),
                        "EtrPlanAssetR002.portfolioId", lineNo));
                // 资产代码
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(4).toString(),
                        "EtrPlanAssetR002.finprodId", lineNo));
                if (StringUtil.isNotEmpty(rowData.get(4).toString())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("search", rowData.get(4).toString());
                    map.put("page", 1);
                    map.put("length", 20);
                    Page<SelectOptionDto> finprodIds = getfinprodIdForAdd(map);
                    if (finprodIds.getRoot().isEmpty()) {
                        confirmMsg = etrCommonService.StringAddByBr(confirmMsg, getMessage(
                            "ETR_E_0017", getMessage("EtrPlanAssetR002.finprodId"), lineNo));
                    }
                }
                // 交易方向非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(5).toString(), "EtrPlanAssetR002.ps",
                            lineNo));
                // 根据交易方向来chec委托份额和委托金额
                String[] s2 = rowData.get(5).toString().split(":");
                if (StringUtil.equals(s2[0], DictCodeList.交易方向_买入.getValue())) {
                    // 委托份额
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        etrCommonService.checkIsBlank(rowData.get(6).toString(),
                            "EtrPlanAssetR002.entrustNum", lineNo));
                    /*// 委托金额
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        etrCommonService.checkIsBlank(rowData.get(7).toString(),
                            "EtrPlanAssetR002.entrustAmt", indexId));*/
                }else if(StringUtil.equals(s2[0], DictCodeList.交易方向_卖出.getValue())) {
                	 // 委托份额
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        etrCommonService.checkIsBlank(rowData.get(6).toString(),
                            "EtrPlanAssetR002.entrustNum", lineNo));
                    // 交易方向为卖出时，委托金额为空check
                    if (StringUtil.isNotBlank(rowData.get(7).toString())) {
                        confirmMsg = etrCommonService.StringAddByBr(confirmMsg, getMessage(
                            "ETR_E_0030", getMessage("EtrPlanAssetR002.ps"), lineNo));
                    }
                }if (StringUtil.equals(s2[0], DictCodeList.交易方向_申购.getValue())) {
                    // 委托金额
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        etrCommonService.checkIsBlank(rowData.get(7).toString(),
                            "EtrPlanAssetR002.entrustAmt", lineNo));
                    // 交易方向为申购时，委托份额为空check
                    if (StringUtil.isNotBlank(rowData.get(6).toString())) {
                        confirmMsg = etrCommonService.StringAddByBr(confirmMsg, getMessage(
                            "ETR_E_0031", getMessage("EtrPlanAssetR002.ps"), lineNo));
                    }
                }
                else if(StringUtil.equals(s2[0], DictCodeList.交易方向_赎回.getValue())){
                    // 委托份额
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        etrCommonService.checkIsBlank(rowData.get(6).toString(),
                            "EtrPlanAssetR002.entrustNum", lineNo));
                    // 交易方向为赎回时，委托金额为空check
                    if (StringUtil.isNotBlank(rowData.get(7).toString())) {
                        confirmMsg = etrCommonService.StringAddByBr(confirmMsg, getMessage(
                            "ETR_E_0032", getMessage("EtrPlanAssetR002.ps"), lineNo));
                    }
                }
                // 交易对手非空
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    etrCommonService.checkIsBlank(rowData.get(10).toString(),
                        "etrInvestPersonInfo.counterId", lineNo));
                //是否存在check
                if (StringUtil.isNotBlank(rowData.get(10).toString())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("initValue", rowData.get(10).toString());
                    map.put("page", 1);
                    map.put("length", 20);
                    map.put("counterType", null);
                    Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                            ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getCounterId",
                            map, SelectOptionDto.class);
                    if (selectOptionDtoPage.getRoot().isEmpty()) {
                        confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, getMessage(
                                "ETR_E_0017", getMessage("etrInvestPersonInfo.counterId"), lineNo));
                    }
                }
                if (StringUtil.isNotBlank(rowData.get(10).toString())) {
                    if ( StringUtil.equals(s2[0], DictCodeList.交易方向_买入.getValue())
                        || StringUtil.equals(s2[0], DictCodeList.交易方向_卖出.getValue())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("initValue", rowData.get(10).toString());
                        map.put("page", 1);
                        map.put("length", 20);
                        map.put("counterType", DictCodeListExt1.交易对手类型_内部交易对手.getValue());
                        Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                            ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getCounterId",
                            map, SelectOptionDto.class);
                        if (selectOptionDtoPage.getRoot().isEmpty()) {
                            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                                getMessage("ETR_E_0017",
                                    getMessage("etrInvestPersonInfo.counterId"), lineNo));
                        }

                    }
                }
                if (StringUtil.isNotBlank(rowData.get(10).toString())) {
                    if (StringUtil.equals(s2[0], DictCodeList.交易方向_赎回.getValue())
                            || StringUtil.equals(s2[0], DictCodeList.交易方向_申购.getValue())) {
                        Map<String, Object> map = new HashMap<>();
                        map.put("initValue", rowData.get(10).toString());
                        map.put("page", 1);
                        map.put("length", 20);
                        map.put("counterType", DictCodeListExt1.交易对手类型_内部交易对手.getValue());
                        Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
                                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/EntrustCommonPersonSelect/getCounterId",
                                map, SelectOptionDto.class);
                        if (!selectOptionDtoPage.getRoot().isEmpty()) {
                            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                                    getMessage("ETR_E_0017",
                                            getMessage("etrInvestPersonInfo.counterId"), lineNo));
                        }

                    }
                }
                if (StringUtil.isBlank(confirmMsg)) {
                    jsonArrays.add(rowData);
                }
                else {
                    confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, confirmMsg);
                    errorNum++ ;
                }
            }
            confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1,
                getMessage("ETR_C_0001", String.valueOf(errorNum)));
            Map<String, Object> resultMap = new HashMap<>();
            if (StringUtil.isNotBlank(confirmMsg1)) {
                resultMap.put("confirmMsg", confirmMsg1);
            }
            resultMap.put("correctData", JSONArray.toJSONString(jsonArrays));
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }
    }

    /**
     * check 数字非空及格式
     * 
     * @param confirmMsg 错误信息
     * @param indexId 序号
     * @param data 数据
     * @param labelName 字段名称
     * @return
     */
    private String checkIsDouble(String confirmMsg, String indexId, String data,
                                 String labelName) {
        String message = etrCommonService.checkIsBlank(data, labelName, indexId);
        confirmMsg = etrCommonService.StringAddByBr(confirmMsg, message);
        if (StringUtil.isNotBlank(data)) {
            try {
                Double.parseDouble(data);
            }
            catch (NumberFormatException e) {
                confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                    getMessage("ETR_E_0016", getMessage(labelName), indexId));
            }
        }
        return confirmMsg;
    }
    // 多对多指令新增 add by zhnagpuyu 2020/1/2 start
    
    // 指令授信 start
    public TrdProductDeal prepareDeal(EtrPlanAssetCDto entity) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDeal,entity);
        trdProductDeal.setTradeId(entity.getEntrustId());
        trdProductDeal.setpTradeId(entity.getEntrustId());
        trdProductDeal.setCounterId(entity.getEtrInvestPersonInfo().getCounterId());
        trdProductDeal.setTradeAmt(entity.getPrinAmt());
        trdProductDeal.setPrinAmt(entity.getPrinAmt());
        return trdProductDeal;
    }
    
    
    /**
     * 交易授信处理
     * @param entrustId
     */
    public void dealEtrIsNeedOccupyCre(EtrPlanAssetCDto entity, String operationType) {
        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
        inputDto.setProdid(entity.getPortfolioId());
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        TrdProductDeal trdProductDeal = prepareDeal(entity);
        inputDto.setTrdProductDeal(trdProductDeal);

        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getrTradeId());
        trdProductDealAdd.setPortfolioId(entity.getPortfolioId());
//        trdProductDealAdd.setCreditSource(entity.getCreditSource());
        inputDto.setTrdProductDealAdd(trdProductDealAdd);

        trdProductDeal.setTradeStatus(operationType);
        AllTradeCommonUtil.do投资指令授信(inputDto);
    }
    
    /**
     * 关闭/撤回指令授信处理
     * @param entrustId
     */
    public void dealCloseEtrIsNeedOccupyCre(String entrustId) {
		Map<String, Object> map = commonBusinessService.selectByEntrustId(entrustId);
        AllTradeCommonInputDto inputDto = new AllTradeCommonInputDto();
        inputDto.setProdid((String)map.get("portfolioId"));
        inputDto.setProdFinprodType(DictCodeListExt1.金融产品类型_投资组合.getValue());
        TrdProductDeal trdProductDeal = (TrdProductDeal) BeanUtil.mapToObject(map,TrdProductDeal.class);
        trdProductDeal.setpTradeId((String)map.get("entrustId"));
        trdProductDeal.setTradeAmt((BigDecimal)map.get("entrustAmt"));
        trdProductDeal.setPrinAmt((BigDecimal)map.get("entrustAmt")); 
        trdProductDeal.setTradeDate((Date)map.get("entrustDate"));
        trdProductDeal.setTradeStatus(DictCodeListExt1.交易状态_撤销.getValue());
        inputDto.setTrdProductDeal(trdProductDeal);
        
        TrdProductDealAdd trdProductDealAdd = new TrdProductDealAdd();
        trdProductDealAdd.setTradeId(trdProductDeal.getrTradeId());
        trdProductDealAdd.setPortfolioId((String)map.get("portfolioId"));
//        trdProductDealAdd.setCreditSource((String)map.get("creditSource"));
        inputDto.setTrdProductDealAdd(trdProductDealAdd);

        AllTradeCommonUtil.do投资指令授信(inputDto);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doBack(EtrPlanAssetCDto entity) {
        // 买入交易提交占用授信,卖出交易流程走完占用
        if(entity.getPs() != null && 
        		(entity.getPs().equals(DictCodeList.交易方向_买入.getValue()) || entity.getPs().equals(DictCodeList.交易方向_申购.getValue()))) {
            dealEtrIsNeedOccupyCre(entity, DictCodeListExt1.交易状态_撤销.getValue());
        } 
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doRevoke(EtrPlanAssetCDto entity) {
        // 买入交易提交占用授信,卖出交易流程走完占用
        if(entity.getPs() != null && 
        		(entity.getPs().equals(DictCodeList.交易方向_买入.getValue()) || entity.getPs().equals(DictCodeList.交易方向_申购.getValue()))) {
            dealEtrIsNeedOccupyCre(entity, DictCodeListExt1.交易状态_撤销.getValue());
        }
    }
    // 指令授信 end
}
