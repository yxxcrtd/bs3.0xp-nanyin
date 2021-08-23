package com.joyintech.tams.invest.entrust.etribrepo.service;


import com.alibaba.fastjson.JSONArray;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.TrdProductDeal;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etribrepo.dto.EtrIbRepoinfo002Dto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.*;


/**
 * 质押式回购投资委托画面端service 〈功能详细描述〉
 */
@Service
public class EtrIbRepoInfo004Service extends CommonExportService<EtrIbRepoinfo002Dto> implements BaseProcessService<EtrIbRepoinfo002Dto> {

	/**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;
    
    @Autowired
    private EtrCommonService etrCommonService;
    
    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;
    
    
	@Override
	public void doComplete(EtrIbRepoinfo002Dto arg0, OperationType arg1) {
		
	}
	
	/**
     * 获取委托列表数据
     *
     * @return
     */
    public EtrIbRepoinfo002Dto getEntrustListData(String tempList) {
    	EtrIbRepoinfo002Dto dto = new EtrIbRepoinfo002Dto();
        // 质押券信息列表
        List<PledgeBondInfoDto> pledgeBondList = new ArrayList<>();
        dto.setBussRepoSec(new BussRepoSecDto());
        dto.getBussRepoSec().setPageFrom("EtrRepo002");
        dto.getBussRepoSec().setPledgeBondList(pledgeBondList);
        // 委托列表
        List<EtrIbRepoinfo002Dto> etrRepoDtoList = new ArrayList<>();
        dto.setSelectedItem(etrRepoDtoList);
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++ ) {
            JSONArray rowData = JSONArray.parseArray(((JSONArray)objects.get(i)).toJSONString());
            if (StringUtil.isNotEmpty(rowData.get(1))) {
                EtrIbRepoinfo002Dto etrRepoDto = new EtrIbRepoinfo002Dto();
                etrRepoDtoList.add(etrRepoDto);
                etrRepoDto.setBussRepoSec(new BussRepoSecDto());
                etrRepoDto.getBussRepoSec().setPageFrom("EtrRepo002");
                etrRepoDto.getBussRepoSec().setPledgeBondList(new ArrayList<PledgeBondInfoDto>());
                // 根据模板中固定的下标来赋值
                etrRepoDto.setIndexId(rowData.get(0).toString());
                // 委托开始日
                etrRepoDto.setVdate(DateUtil.parse(rowData.get(1).toString()));
                // 委托终止日
                etrRepoDto.setMdate(DateUtil.parse(rowData.get(2).toString()));
                // 组合代码
                etrRepoDto.setPortfolioId(rowData.get(3).toString());
                //组合名称  add by xxg
                etrRepoDto.setPortfolioName(etrCommonService.getPortfolioName(rowData.get(3).toString()));
                // 交易方向
                etrRepoDto.setPs(rowData.get(4).toString().split(":")[0]);
                // 币种
                etrRepoDto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
                // 清算速度
                etrRepoDto.setSettleSpreed(rowData.get(5).toString().split(":")[0]);
                // 回购类型
               /* if (StringUtil.isNotEmpty(rowData.get(8))) {
                    etrRepoDto.setAssetType1(rowData.get(8).toString().split(":")[0]);
                }*/
                // 委托金额
                etrRepoDto.setEntrustAmt(new BigDecimal(rowData.get(6).toString()));
                // 价格模式
                etrRepoDto.setPriceModel(rowData.get(7).toString().split(":")[0]);
                // 利率（%）
                if (StringUtil.isNotBlank(rowData.get(8).toString())) {
                    etrRepoDto.setPrice(new BigDecimal(rowData.get(8).toString()));
                }
                // 最低成交金额
               /* if (StringUtil.isNotBlank(rowData.get(12).toString())) {
                    etrRepoDto.setMinAmt(new BigDecimal(rowData.get(12).toString()));
                }*/
                // 交易场所
                //etrRepoDto.setTradeMarket(rowData.get(13).toString().split(":")[0]);
                // 期限
                etrRepoDto.setRepoTerm(new BigDecimal(rowData.get(9).toString()));
                if (StringUtil.isNotEmpty(etrRepoDto.getRepoTerm())) {
                    try {
                        String ipDateStr = iPlatformDate.getSysDateStr();
                        Date ipDate1 = DateUtil.getLastDay(DateUtil.parse(ipDateStr));
                        int days = DateUtil.getDayOfMonth(DateUtil.format(ipDate1));
                        Date ipDatex2 = DateUtil.addMonthDateStr(ipDateStr, 2);

                        Date ipDate2 = DateUtil.getLastDay(ipDatex2);
                        int days2 = days + DateUtil.getDayOfMonth(DateUtil.format(ipDate2));
                        Date ipDatex3 = DateUtil.addMonthDateStr(ipDateStr, 3);
                        Date ipDate3 = DateUtil.getLastDay(ipDatex3);
                        int days3 = days2 + DateUtil.getDayOfMonth(DateUtil.format(ipDate3));
                        Date ipDatex4 = DateUtil.addMonthDateStr(ipDateStr, 4);
                        Date ipDate4 = DateUtil.getLastDay(ipDatex4);
                        int days4 = days3 + DateUtil.getDayOfMonth(DateUtil.format(ipDate4));
                        Date ipDatex5 = DateUtil.addMonthDateStr(ipDateStr, 5);
                        Date ipDate5 = DateUtil.getLastDay(ipDatex5);
                        int days5 = days4 + DateUtil.getDayOfMonth(DateUtil.format(ipDate5));
                        Date ipDatex6 = DateUtil.addMonthDateStr(ipDateStr, 6);
                        Date ipDate6 = DateUtil.getLastDay(ipDatex6);
                        int days6 = days5 + DateUtil.getDayOfMonth(DateUtil.format(ipDate6));
                        Date ipDatex7 = DateUtil.addMonthDateStr(ipDateStr, 7);
                        Date ipDate7 = DateUtil.getLastDay(ipDatex7);
                        int days7 = days6 + DateUtil.getDayOfMonth(DateUtil.format(ipDate7));
                        Date ipDatex8 = DateUtil.addMonthDateStr(ipDateStr, 8);
                        Date ipDate8 = DateUtil.getLastDay(ipDatex8);
                        int days8 = days7 + DateUtil.getDayOfMonth(DateUtil.format(ipDate8));
                        Date ipDatex9 = DateUtil.addMonthDateStr(ipDateStr, 9);
                        Date ipDate9 = DateUtil.getLastDay(ipDatex9);
                        int days9 = days8 + DateUtil.getDayOfMonth(DateUtil.format(ipDate9));
                        Date ipDatex10 = DateUtil.addMonthDateStr(ipDateStr, 10);
                        Date ipDate10 = DateUtil.getLastDay(ipDatex10);
                        int days10 = days9 + DateUtil.getDayOfMonth(DateUtil.format(ipDate10));
                        Date ipDatex11 = DateUtil.addMonthDateStr(ipDateStr, 11);
                        Date ipDate11 = DateUtil.getLastDay(ipDatex11);
                        int days11 = days10 + DateUtil.getDayOfMonth(DateUtil.format(ipDate11));
                        Date ipDatex12 = DateUtil.addMonthDateStr(ipDateStr, 12);
                        Date ipDate12 = DateUtil.getLastDay(ipDatex12);
                        int days12 = days11 + DateUtil.getDayOfMonth(DateUtil.format(ipDate12));

                        if (etrRepoDto.getRepoTerm().compareTo(BigDecimal.ONE) == 0) {
                            etrRepoDto.setTermType("R001");
                        } else if (etrRepoDto.getRepoTerm().compareTo(BigDecimal.ONE) > 0 && etrRepoDto.getRepoTerm().compareTo(new BigDecimal(7)) <= 0) {
                            etrRepoDto.setTermType("R007");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(7)) > 0 && etrRepoDto.getRepoTerm().compareTo(new BigDecimal(14)) <= 0) {
                            etrRepoDto.setTermType("R014");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(14)) > 0 && etrRepoDto.getRepoTerm().compareTo(new BigDecimal(21)) <= 0) {
                            etrRepoDto.setTermType("R021");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(days)) <= 0) {
                            etrRepoDto.setTermType("R1M");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(days2)) <= 0) {
                            etrRepoDto.setTermType("R2M");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(days3)) <= 0) {
                            etrRepoDto.setTermType("R3M");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(days4)) <= 0) {
                            etrRepoDto.setTermType("R4M");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(days6)) <= 0) {
                            etrRepoDto.setTermType("R6M");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(days9)) <= 0) {
                            etrRepoDto.setTermType("R9M");
                        } else if (etrRepoDto.getRepoTerm().compareTo(new BigDecimal(days12)) <= 0) {
                            etrRepoDto.setTermType("R1Y");
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                // 交易日历
                //etrRepoDto.setCalendarId(rowData.get(14).toString());
                //etrRepoDto.setCalendarId(rowData.get(15).toString().split(":")[0]);
                Map<String, Object> param = new HashMap<>();

                if (StringUtil.isNotBlank(rowData.get(10).toString())) {
                    etrRepoDto.setSecAcctId(rowData.get(10).toString());
                }
                // 备注
                etrRepoDto.setRemark(rowData.get(11).toString());
//                etrRepoDto.setZyqk(rowData.get(14).toString());
                etrRepoDto.setTradeMarket(BussinessDictCodeList.交易场所_银行间.getValue());
                etrRepoDto.setInvManagerId(LoginUserUtil.getUserName());
                param = new HashMap<>();
                param.put("portfolioId", etrRepoDto.getPortfolioId());
                param.put("ccy", etrRepoDto.getCcy());
                param.put("vdate", etrRepoDto.getVdateText());
                param.put("calId", etrRepoDto.getCalendarId());
                param.put("manageId", etrRepoDto.getSecuritiesCust());
                param.put("chlId", etrRepoDto.getChlId());
                Map<String, Object> cashMap = this.getProAvailableCash(param);
                // 指令可用现金
                if (cashMap != null && cashMap.get("cashT") != null) {
                    etrRepoDto.setProAvailableCash(
                        new BigDecimal(cashMap.get("cashT").toString()));
                }
                else {
                    etrRepoDto.setProAvailableCash(BigDecimal.ZERO);
                }
                // // 指令可用持仓
               /* // etrRepoDto.setAssetAvailablePosition(rowData.get(9).toString());
                etrRepoDto.setEtrInvestPersonInfo(new EtrInvestPersonInfoDto());
                // 投资经理
                etrRepoDto.getEtrInvestPersonInfo().setInvManagerId(dto.getCreateUser());
                // 投资处室
                etrRepoDto.getEtrInvestPersonInfo().setRoom(dto.getCreateDept());*/
            }
           /* // 质押券信息
            PledgeBondInfoDto pledgeBondInfoDto = new PledgeBondInfoDto();
            // 债券代码
            pledgeBondInfoDto.setBondCode(rowData.get(19).toString());
            EtrRepoDto etrRepoDto = etrRepoDtoList.get(etrRepoDtoList.size() - 1);
            List<PledgeBondInfoDto> list = selectAvaPledgeBondDetailList(
                pledgeBondInfoDto.getBondCode(), etrRepoDto);
            if(!StringUtil.isEmptyList(list)) {
                // 债券简称
                // 可质押券面总额
                // 首次净价
                // 债券类型
                // 登记托管机构
                BeanUtil.copyPropertiesIgnoreNull(pledgeBondInfoDto, list.get(0));
            }
            // 债券代码
            pledgeBondInfoDto.setBondCode(rowData.get(19).toString());
            // 质押券面总额
            pledgeBondInfoDto.setPledgePrin(new BigDecimal(rowData.get(20).toString()));
            // 折算比例%
            pledgeBondInfoDto.setPledgeRatio(new BigDecimal(rowData.get(21).toString()));
            pledgeBondList.add(pledgeBondInfoDto);
            etrRepoDto.getBussRepoSec().setTotalPrinAmt(
                ArithmeticUtil.doAdd(etrRepoDto.getBussRepoSec().getTotalPrinAmt(),
                    ArithmeticUtil.divide(
                        ArithmeticUtil.multiply(pledgeBondInfoDto.getPledgePrin(),
                            pledgeBondInfoDto.getPledgeRatio()),
                        new BigDecimal(100))));
            etrRepoDto.getBussRepoSec().getPledgeBondList().add(pledgeBondInfoDto);*/
        }
        dto.setSelectedItem(etrRepoDtoList);
        return dto;
        
    }
    
    /**
     * 验证导入的数据
     * @param fileName 文件名
     * @return
     */
    public AjaxResult checkImportData(String fileName) {
        List<List<String>> objects = ExcelUtil.getTempList(fileName);

        if (objects.size() > 100) {
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("errorMsg", getMessage("ETR_E_0021"));
            AjaxResult wr = new AjaxResult();
            wr.setData(resultMap);
            return wr;
        }

        String totalMsg = "";
        JSONArray jsonArrays = new JSONArray();
        EtrIbRepoinfo002Dto etrRepoDto = new EtrIbRepoinfo002Dto();
        // 失败条数
        int errorNum = 0;
        // 循环中用的，一个委托对应的全部Arrays
        JSONArray itemJsonArrays = new JSONArray();
        // 错误message（一个委托一条，委托开始时初始化）
        String confirmMsg = "";
        for (int i = 1; i < objects.size(); i++ ) {
            // excel的行号
            String lineNo = String.valueOf(i + 1);
            List<String> rowData = objects.get(i);
            // 1~16均为空时认为所在行仅质押券信息
            boolean isEntrust = false;
//            for (int j = 0; j <= 14; j++ ) {
//                if (StringUtil.isNotEmpty(rowData.get(j))) {
//                    isEntrust = true;
//                    break;
//                }
//            }
//            // 如果是委托所在行，check 0~17
//            if (isEntrust) {
            if (StringUtil.isBlank(confirmMsg)) {
                jsonArrays.addAll(itemJsonArrays);
            }
            else {
                totalMsg = etrCommonService.StringAddByBr(totalMsg, confirmMsg);
                errorNum++ ;
            }
            confirmMsg = "";
            itemJsonArrays = new JSONArray();
            // 委托开始日非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(rowData.get(1).toString(), "EtrRepo003.vdate",
                    lineNo));
            // 委托开始日格式check
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsDate(rowData.get(1).toString(), "EtrRepo003.vdate",
                    lineNo));
            // 委托终止日非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(rowData.get(2).toString(), "EtrRepo003.mdate",
                    lineNo));
            // 委托终止日格式check
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsDate(rowData.get(2).toString(), "EtrRepo003.mdate",
                    lineNo));
            // 组合代码非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(rowData.get(3).toString(),
                    "EtrRepo003.portfolioId", lineNo));
            // 组合代码check
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkPortfolioIdExist(rowData.get(3).toString(),
                    "EtrRepo003.portfolioId", lineNo));
            // 交易方向非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(rowData.get(4).toString(), "EtrRepo003.ps",
                    lineNo));
//            // 币种非空
//            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
//                etrCommonService.checkIsBlank(rowData.get(6).toString(), "EtrRepo003.ccy",
//                    lineNo));
            // 清算速度非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(rowData.get(5).toString(),
                    "EtrRepo003.settleSpreed", lineNo));
            // 委托金额
            String entrustAmt = rowData.get(6).toString();
            // 委托金额非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(entrustAmt, "EtrRepo003.entrustAmt", lineNo));
            // 委托金额是数值
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsNumber(entrustAmt, "EtrRepo003.entrustAmt", lineNo));
            // 委托金额是大于0
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsGtZero(entrustAmt, "EtrRepo003.entrustAmt", lineNo));
            // 价格模式非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(rowData.get(7).toString(),
                    "EtrRepo003.priceModel", lineNo));
            // 利率
            String price = rowData.get(8).toString();
            // 利率是数值
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsNumber(price, "EtrRepo003.rate", lineNo));
            // 利率是大于0
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsGtZero(price, "EtrRepo003.rate", lineNo));
            // 期限
            String repoTerm = rowData.get(9).toString();
            // 期限非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(repoTerm, "EtrRepo003.repoTerm", lineNo));
            // 期限是数值
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsNumber(repoTerm, "EtrRepo003.repoTerm", lineNo));
            // 期限是大于0
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsGtZero(repoTerm, "EtrRepo003.repoTerm", lineNo));
//            String zyqk = rowData.get(14).toString();
//            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
//                    etrCommonService.checkIsBlank(zyqk, "EtrRepo003.repoTerm", lineNo));
//            // 通道存在
//            if (StringUtil.isNotBlank(rowData.get(12).toString())) {
//                Map<String, Object> param = new HashMap<>();
//                param.put("initValue", rowData.get(12).toString());
//                param.put("page", 1);
//                param.put("length", 20);
//                Page<SelectOptionDto> selectOptionDtoPage = restTemplate.postForPage(
//                    ServiceApplication.COMMON_SERVICE_NAME + "/common/BussChannelCommonService/getChannelId",
//                    param, SelectOptionDto.class);
//                if (selectOptionDtoPage.getRoot().isEmpty()) {
//                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
//                        getMessage("ETR_E_0017", getMessage("invest.channelId"), lineNo));
//                }
//            }

//            // 证券账户
//            if (StringUtil.isNotBlank(rowData.get(10).toString())) {
//                etrInvestBondInfoDto.setSecAcctId(rowData.get(10).toString());
//                param = new HashMap<>();
//                param.put("initValue", rowData.get(10).toString());
//                param.put("portfolioId", etrInvestBondInfoDto.getPortfolioId());
//                param.put("tradeMarket", etrInvestBondInfoDto.getTradeMarket());
//                param.put("page", 1);
//                param.put("length", 20);
//                List<SelectOptionDto> selectOptionDtoList = this.getSecManageAcctId(param);
//                if (!StringUtil.isEmptyList(selectOptionDtoList)) {
//                    etrInvestBondInfoDto.setSecManageAcctIdName(
//                        selectOptionDtoList.get(0).getText());
//                }
//            }
//            // 用于可添加质押券check的参数
//            etrRepoDto = new EtrIbRepoinfo002Dto();
//            // 组合代码
//            etrRepoDto.setPortfolioId(rowData.get(4).toString());
//            // 交易方向
//            String[] ps = rowData.get(5).toString().split(":");
//            if (ps.length > 0) {
//                etrRepoDto.setPs(rowData.get(5).toString().split(":")[0]);
//            }
            // 证券账户
           // etrRepoDto.setSecuritiesCust(rowData.get(16).toString());
            // 通道
            // etrRepoDto.setChlId(rowData.get(12).toString());

            // check18~20
           /* String bondCode = rowData.get(19).toString();
            // 债券代码非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(bondCode, "EtrRepo003.bondCode", lineNo));
            // 债券代码存在且可添加
            if (StringUtil.isNotEmpty(bondCode) && StringUtil.isNotEmpty(etrRepoDto.getPs())
                && StringUtil.isNotEmpty(etrRepoDto.getPortfolioId())) {
                List<PledgeBondInfoDto> list = selectAvaPledgeBondDetailList(bondCode, etrRepoDto);
                if (StringUtil.isEmptyList(list)) {
                    confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                        getMessage("ETR_E_0018", getMessage("EtrRepo003.bondCode"), lineNo));
                }
            }*/
           /* String pledgePrin = rowData.get(20).toString();
            // 质押券面总额(元)非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(pledgePrin, "EtrRepo003.pledgePrin", lineNo));
            // 质押券面总额(元)是数值
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsNumber(pledgePrin, "EtrRepo003.pledgePrin", lineNo));
            // 质押券面总额(元)是大于0
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsGtZero(pledgePrin, "EtrRepo003.pledgePrin", lineNo));
            String pledgeRatio = rowData.get(21).toString();
            // 折算比例非空
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsBlank(pledgeRatio, "EtrRepo003.pledgeRatio", lineNo));
            // 折算比例是数值
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsNumber(pledgeRatio, "EtrRepo003.pledgeRatio", lineNo));
            // 折算比例是大于0
            confirmMsg = etrCommonService.StringAddByBr(confirmMsg,
                etrCommonService.checkIsGtZero(pledgeRatio, "EtrRepo003.pledgeRatio", lineNo));
                */
            if (StringUtil.isBlank(confirmMsg)) {
                itemJsonArrays.add(rowData);
            }
        }
        // 最后一条特殊处理
        if (StringUtil.isBlank(confirmMsg)) {
            jsonArrays.addAll(itemJsonArrays);
        }
        else {
            totalMsg = etrCommonService.StringAddByBr(totalMsg, confirmMsg);
            errorNum++ ;
        }
        totalMsg = etrCommonService.StringAddByBr(totalMsg,
            getMessage("ETR_C_0001", String.valueOf(errorNum)));
        Map<String, Object> resultMap = new HashMap<>();
        if (StringUtil.isNotBlank(totalMsg)) {
            resultMap.put("confirmMsg", totalMsg); 
        }
        resultMap.put("correctData", JSONArray.toJSONString(jsonArrays));
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
        
    }
    
    // 指令授信 start
    public TrdProductDeal prepareDeal(EtrIbRepoinfo002Dto entity) {
        TrdProductDeal trdProductDeal = new TrdProductDeal();
        BeanUtil.copyPropertiesIgnoreNull(trdProductDeal,entity);
        trdProductDeal.setTradeId(entity.getEntrustId());
        trdProductDeal.setpTradeId(entity.getEntrustId());
//        trdProductDeal.setCounterId(entity.getEtrInvestPersonInfo().getCounterId());
        trdProductDeal.setTradeAmt(entity.getPrinAmt());
        trdProductDeal.setPrinAmt(entity.getPrinAmt());
        return trdProductDeal;
    }
    
    public String getZyqkId(String zyqName){
    	return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/getZyqkId",zyqName,
    			String.class);
    }
    
    /**
     * 组合可用现金查询
     * <功能详细描述>
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
            param.put("sysDateT1",DateUtil.format(tradeDay));
        }
        return etrCommonService.getProAvailableCash(param);
//        return restTemplate.postForObject(
//            ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrRepo/getProAvailableCash",param,
//            Map.class);
    }
    
}
