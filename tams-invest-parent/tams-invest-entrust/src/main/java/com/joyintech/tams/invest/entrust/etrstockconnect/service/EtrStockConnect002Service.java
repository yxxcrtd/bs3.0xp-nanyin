
package com.joyintech.tams.invest.entrust.etrstockconnect.service;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.*;
import com.joyintech.tams.common.base.entity.EtrEntrust;
import com.joyintech.tams.common.base.entity.EtrEntrustAdd;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import com.joyintech.tams.invest.common.trade.dto.EtrSecPositionDto;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.WorkflowContext;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import com.joyintech.workflow.engine.util.WorkFlowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 港股通
 *
 * @see EtrStockConnect002Service
 * @since 1.0
 */
@Service
public class EtrStockConnect002Service extends CommonExportService<EtrStockConnectDto> implements BaseProcessService<EtrStockConnectDto> {

    /**
     * 市场行情接口地址
     */
  /*  @Value("${MarketInfoInterf.BaseUrl}")
    private String MarketInfoInterf_BaseUrl;*/
    /**
     * 交易所行情接口地址
     */
    @Value("${MarketInfoInterf.exchangeUrl}")
    private String MarketInfoInterf_exchangeUrl;

    /**
     * 指令共通Service
     */
    @Autowired
    private EtrCommonService etrCommonService;

    @Autowired
    private IPlatformDate iPlatformDate;

    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    @Autowired
    EtrStockConnect001Service etrStockConnect001Service;

//    @Autowired
//    SendService sendService;
//
//    @Autowired
//    Send001Service send001Service;
//
//    @Autowired
//    Send011Service send011Service;

    @Override
    public void doBack(EtrStockConnectDto entity) {
        //产品化_所有投资指令预占需求_场内交易
        AllTradeCommonUtil.deletePosition(null, entity.getEntrustId());
        //产品化_所有投资指令预占需求_场内交易
    }

    @Override
    public void doUpdate(EtrStockConnectDto dto) {
        restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/doInsert", dto, Integer.class);

        if (!StringUtil.equals("revoke", dto.getOperateType()) && !StringUtil.equals("delete", dto.getOperateType())) {
            // 先删除流程中数据
            doDeleteFlow(dto.getWorkflowId());
            // 重新insert
            doInsertFlow(dto);
            //产品化_所有投资指令预占需求_场内交易
//            doInsetOrUpdateForEtrPositionHold(dto);
            //产品化_所有投资指令预占需求_场内交易
            ProcessMark newEntity;
            try {
                newEntity = WorkFlowUtil.getBusinessObjectFromRequest(EtrStockConnectDto.class, true);
                // 工作流ID
                newEntity.setWorkflowId(dto.getWorkflowId());
                newEntity.setStatus(dto.getStatus());
                newEntity.setBusinessData(JsonUtils.object2Json(dto));
                // 操作留痕表
                WorkflowContext.getProcessMarkService().insertIntoProcessMark(newEntity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void doInsert(EtrStockConnectDto dto, OperationType operationType) {
        if ("submit".equals(dto.getOperateType())) {
            doInsertFlow(dto);
            //产品化_所有投资指令预占需求_场内交易
//            doInsetOrUpdateForEtrPositionHold(dto);
            //产品化_所有投资指令预占需求_场内交易
        }
        restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/doInsert", dto, Integer.class);
    }

    private void doInsertFlow(EtrStockConnectDto dto) {
        try {
            BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
            EtrEntrust etrEntrust = new EtrEntrust();
            EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            dto.setIsOutTrade(BussinessDictCodeList.是否_是.getValue());
            dto.setEntrustStatus(BussinessDictCodeList.委托状态_流程中.getValue());
            BeanUtil.copyProperties(etrEntrust, dto);
            BeanUtil.copyProperties(etrEntrust, dto.getEtrInvestPersonInfo());
            if (iPlatformDate != null) {
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            BeanUtil.copyProperties(etrEntrustAdd, dto);
            //港股通市场
            etrEntrustAdd.setHkMarket(dto.getHkseMarket());
            etrEntrustAdd.setEntrustAmtTarget(dto.getEntrustAmtHk());
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);

            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);

            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            entity.setMstFileInputParameter(mstFileInputParameter);
            entity.setWorkFlowId(dto.getWorkflowId());
            simpleRestTemplate.postForObject(
                    ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/doInsertEtrFlow", entity,
                    Boolean.class);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doDelete(String workflowId) {
        doDeleteFlow(workflowId);
    }

    private void doDeleteFlow(String workflowId) {
        Map<String, Object> param = new HashMap<>();
        param.put("workflowId", workflowId);
        simpleRestTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/common/entrustcommonbase/doDeleteEtrFlow", param,
                Boolean.class);
    }

    /**
     * doComplete
     */
    @Override
    public void doComplete(EtrStockConnectDto dto, OperationType operationType) {
        dto.setOperateType(operationType.toString());
        Date sysDate = CacheUtil.getAppCache(GlobalConstant.NOW_SYS_DATE, Date.class);
        dto.setEntrustDate(sysDate);
        restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/doComplete", dto, Integer.class);
        //留痕表中删除
        doDeleteFlow(dto.getWorkflowId());

//        //发送到b45
//        Map<String, Object> param = new HashMap<>();
//        param.put("entrustId", dto.getEntrustId());
//        sendService.sendToB45(param, send001Service);
    }

    /**
     * 关闭委托 <功能详细描述>
     */
    public boolean closeDelegate(String entrustId, String closeWay, String entrustStatus) {
        //发送到b45
//        Map<String, Object> param = new HashMap<>();
//        param.put("entrustId", entrustId);
//        boolean res = sendService.sendToB45(param, send011Service);
        boolean res = true;
        if (res) {
            res = restTemplate.getForObject(
                    ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/close?entrustId=" + entrustId
                            + "&closeWay=" + closeWay,
                    boolean.class);
        }
        return res;
    }

    /**
     * 撤回委托 <功能详细描述>
     */
    public boolean recallDelegate(EtrStockConnectDto dto) {
        Boolean res = restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/recall", dto, boolean.class);

        return res;
    }

    /**
     * 插入和修改时，指令持仓流水 、现金流水处理
     */
    public void doInsetOrUpdateForEtrPositionHold(EtrStockConnectDto dto) {
        restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/doInsetOrUpdateForEtrPositionHold", dto,
                Integer.class);
    }


    /**
     * getfinprodIdForAdd
     * 新建页面资产代码联想下拉框
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/getfinprodIdForAdd", map, SelectOptionDto.class);
    }

    /**
     * @title: getProAvailableCash
     * @description: 组合可用现金查询
     * @throws:
     */
    public Map<String, Object> getProAvailableCash(Map<String, Object> param) {

        Date sysDate = DateUtil.platDateFromCache();
        param.put("sysDate", DateUtil.format(sysDate));
        Map<String, Object> map = etrCommonService.getProAvailableCash(param);

        Map<String, Object> result =  new HashMap<>();
        BigDecimal cashSH = map.get("cashSH") == null ? BigDecimal.ZERO : (BigDecimal)map.get("cashSH");
        BigDecimal cashSZ = map.get("cashSZ") == null ? BigDecimal.ZERO : (BigDecimal)map.get("cashSZ");
        result.put("cashSH",cashSH);
        result.put("cashSZ",cashSZ);
        return result;
    }

    /**
     * @title: getAssetAvailablePosition
     * @description: 组合可用持仓查询
     * @return: java.math.BigDecimal
     * @throws:
     */
    public BigDecimal selectAvailableNum(EtrSecPositionDto dto) {

        Date sysDate = DateUtil.platDateFromCache();
        dto.setCdate(sysDate);
        dto.setHodingType(DictCodeList.持仓类型_可用.getValue());
        return etrCommonService.getAssetAvailablePosition(dto);
    }

    /**
     * 金额、数量联动
     */
    public void calNumAndAmt(EtrStockConnectDto dto) {

        dto.setClosePrice(EtrCommonCashPositionDealService.getHkseClosePrice(dto.getFinprodId(),
                DateUtil.platDateFromCache()));

        //金额
        if (StringUtil.equals(dto.getCalFlag(), "entrustAmtHk")) {
            if (dto.getEntrustAmtHk() != null) {
                if ((StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_限价指令.getValue())
                        || StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_指定价格.getValue()))
                        && dto.getPrice() != null) {
                    dto.setEntrustNum(ArithmeticUtil.divide(dto.getEntrustAmtHk(), dto.getPrice(), 14).setScale(0, BigDecimal.ROUND_DOWN));
                } else if (StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_市价指令.getValue())
                        && dto.getClosePrice() != null) {
                    dto.setEntrustNum(ArithmeticUtil.divide(dto.getEntrustAmtHk(), dto.getClosePrice(), 14).setScale(0, BigDecimal.ROUND_DOWN));
                }
                if (dto.getReferenceRate() != null) {
                    dto.setEntrustAmt(ArithmeticUtil.multiply(dto.getEntrustAmtHk(), dto.getReferenceRate()));
                }
            }
        }
        //数量
        else if (StringUtil.equals(dto.getCalFlag(), "entrustNum")) {
            if (dto.getEntrustNum() != null) {
                if ((StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_限价指令.getValue())
                        || StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_指定价格.getValue()))
                        && dto.getPrice() != null) {
                    dto.setEntrustAmtHk(ArithmeticUtil.multiply(dto.getEntrustNum(), dto.getPrice()));
                } else if (StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_市价指令.getValue())
                        && dto.getClosePrice() != null) {
                    dto.setEntrustAmtHk(ArithmeticUtil.multiply(dto.getEntrustNum(), dto.getClosePrice()));
                }
                if (dto.getReferenceRate() != null) {
                    dto.setEntrustAmt(ArithmeticUtil.multiply(dto.getEntrustAmtHk(), dto.getReferenceRate()));
                }
            }
        }
        //如果参考汇率为空，交易金额(元)置空
        if (dto.getEntrustAmtHk() == null || dto.getReferenceRate() == null) {
            dto.setEntrustAmt(null);
        }
    }

    /**
     * @param entrustNum         指令数量
     * @param minSaleUnit 最小交易单位对象
     * @return 格式化指令数量
     */
    private BigDecimal formatEntrustNum(BigDecimal entrustNum, BigDecimal minSaleUnit) {
        if (entrustNum == null || entrustNum.compareTo(BigDecimal.ZERO) == 0) {
            return entrustNum;
        }
        BigDecimal minNum = new BigDecimal(1);
        if (null != minSaleUnit) {
            minNum = minSaleUnit;
        }
        entrustNum = ArithmeticUtil.multiply(ArithmeticUtil.divide(entrustNum, minNum, 0, RoundingMode.DOWN), minNum);
        return entrustNum;
    }

    /**
     * 是否开通港股通
     */
    public StlSecAcctInfo selectStlSecAcctInfo(String secAcctId) {
        Map<String, Object> map = new HashMap<>();
        map.put("secAcctId", secAcctId);
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/selectStlSecAcctInfo", map, StlSecAcctInfo.class);
    }

    /**
     * 查询证券账户
     *
     * @return
     */
    public StlSecAcctInfo getSecManageAcctId(Map<String, Object> map) {
        String hkseMarket = (String) map.get("hkseMarket");
        if (StringUtil.equals(hkseMarket, DictCodeListExt2.是否沪港通或深港通_沪港通.getValue())) {
            map.put("tradeMarket", DictCodeListExt1.交易场所_上海证券交易所.getValue());
        } else {
            map.put("tradeMarket", DictCodeListExt1.交易场所_深圳证券交易所.getValue());
        }
        return restTemplate.postForObject(
                ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/getSecManageAcctId", map,
                StlSecAcctInfo.class);
    }

    /**
     * 配售代码
     */
    public String getSaleCode(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/getSaleCode", map, String.class);
    }

    /**
     * 获取资产各项信息
     */
    public EtrStockConnectDto getFinProdInfo(Map<String, Object> map) {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/getFinProdInfo", map, EtrStockConnectDto.class);
    }

    /**
     * 获取市价
     *
     * @param param 界面参数
     * @return 返回值
     */
    public AjaxResult getHkseClosePrice(Map<String, Object> param) {
        AjaxResult wr = new AjaxResult();
        BigDecimal price = EtrCommonCashPositionDealService.getHkseClosePrice((String) param.get("finprodId"), DateUtil.addDay(DateUtil.platDateFromCache(), -1));
        wr.setData(price);
        return wr;
    }

    /**
     * @description: 查询参考汇率
     */
    public EtrStockConnectDto getReferenceRate(Map<String, Object> param) {
        return restTemplate.postForObject(ServiceApplication.ENTRUST_SERVICE_NAME + "/EtrStockConnect002/getReferenceRate", param, EtrStockConnectDto.class);
    }


}
