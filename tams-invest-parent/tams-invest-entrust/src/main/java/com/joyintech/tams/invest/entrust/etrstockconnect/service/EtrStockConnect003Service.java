
package com.joyintech.tams.invest.entrust.etrstockconnect.service;

import com.alibaba.fastjson.JSONArray;
import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.invest.common.util.ExcelUtil;
import com.joyintech.tams.invest.entrust.common.service.EtrCommonService;
import com.joyintech.tams.invest.entrust.etrstock.service.EtrStockService;
import com.joyintech.tams.invest.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 指令导入
 * 〈功能详细描述〉
 * @see EtrStockConnect003Service
 * @since 1.0
 */
@Service
public class EtrStockConnect003Service extends CommonExportService<EtrStockConnectDto> implements BaseProcessService<EtrStockConnectDto> {

	/**
     * 委托多指令共同Service
     */
    @Autowired
    private EtrCommonService etrCommonService;
    
    /**
     * 股票指令Service
     */
    @Autowired
    private EtrStockService etrStockService;
    
    /**
     * 日期相关
     */
    @Autowired
    private DateCalService dateCalService;

    @Autowired
    private EtrStockConnect002Service etrStockConnect002Service;
    
    
    
	@Override
	public void doComplete(EtrStockConnectDto entity, OperationType type) {
		
	}
	
	/**
     * 获取委托列表数据
     * 
     * @return
     */
    public List<EtrStockConnectDto> getEntrustListData(String tempList) {
        List<EtrStockConnectDto> EtrBoutRepoDtoList = new ArrayList<>();
        JSONArray objects = JSONArray.parseArray(tempList);
        for (int i = 0; i < objects.size(); i++ ) {
        	EtrStockConnectDto dto = new EtrStockConnectDto();
            JSONArray data = JSONArray.parseArray(((JSONArray)objects.get(i)).toJSONString());
            // 根据模板中固定的下标来复制
            dto.setIndexId(data.get(0).toString().trim());
            // 委托开始日
            dto.setVdate(DateUtil.parse(data.get(1).toString().trim()));
            // 委托结束日
            dto.setMdate(DateUtil.parse(data.get(2).toString().trim()));
            // 组合代码
            dto.setParentPortfolioId(data.get(3).toString().trim());
            dto.setPortfolioId(data.get(4).toString().trim());
            // 资产代码
            dto.setFinprodMarketId(data.get(5).toString().trim());
            // 港股通市场
            if (StringUtil.isNotBlank(data.get(6).toString())) {
                String[] s = data.get(6).toString().split(":");
                dto.setHkseMarket(s[0]);
            }
            // 交易方向
            if (StringUtil.isNotBlank(data.get(7).toString())) {
                String[] s = data.get(7).toString().split(":");
                dto.setPs(s[0]);
            }
            // 价格模式
            if (StringUtil.isNotBlank(data.get(8).toString())) {
                // 分割价格模式，获取code
                String[] s = data.get(8).toString().split(":");
                dto.setPriceModel(s[0]);
            }
            if (StringUtil.isNotBlank(data.get(9).toString())) {
                dto.setPrice(BigDecimal.valueOf(Double.valueOf(data.get(9).toString().trim())));
            }
            if (StringUtil.isNotBlank(data.get(10).toString())) {
                dto.setEntrustNum(BigDecimal.valueOf(Double.valueOf(data.get(10).toString().trim())));
            }
            if (StringUtil.isNotBlank(data.get(11).toString())) {
                dto.setEntrustAmtHk(BigDecimal.valueOf(Double.valueOf(data.get(11).toString().trim())));
            }


            dto.setClosePrice(EtrCommonCashPositionDealService.getHkseClosePrice(dto.getFinprodId(),
                    DateUtil.platDateFromCache()));
            // 数量和金额(港元)选填
            if(dto.getEntrustNum()==null && dto.getEntrustAmtHk() !=null){
                  if(StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_市价指令.getValue())
                          && dto.getClosePrice() !=null){
                      dto.setTargetType(DictCodeListExt3.目标类型_金额.getValue());
                      dto.setEntrustNum( ArithmeticUtil.divide(dto.getEntrustAmtHk(),dto.getClosePrice(),14).setScale( 0, BigDecimal.ROUND_DOWN ));
                   }
                  else if(dto.getPrice() !=null){
                      dto.setTargetType(DictCodeListExt3.目标类型_金额.getValue());
                      dto.setEntrustNum( ArithmeticUtil.divide(dto.getEntrustAmtHk(),dto.getPrice(),14).setScale( 0, BigDecimal.ROUND_DOWN ));
                  }
            }

            if(dto.getEntrustNum()!=null && dto.getEntrustAmtHk() ==null){
                if(StringUtil.equals(dto.getPriceModel(), DictCodeListExt2.价格模式_市价指令.getValue())
                        && dto.getClosePrice() !=null){
                    dto.setTargetType(DictCodeListExt3.目标类型_数量.getValue());
                    dto.setEntrustAmtHk(dto.getEntrustNum().multiply(dto.getClosePrice()));
                }
                else if(dto.getPrice() !=null){
                    dto.setTargetType(DictCodeListExt3.目标类型_数量.getValue());
                    dto.setEntrustAmtHk(dto.getEntrustNum().multiply(dto.getPrice()));
                }
            }

            // 根据投资金额(港元)计算投资金额(元)
            if(StringUtil.isNotEmpty(dto.getEntrustAmtHk()) && StringUtil.isNotEmpty(dto.getHkseMarket())
                    && StringUtil.isNotEmpty(dto.getPs())){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("hkseMarket", dto.getHkseMarket());
                String vdate = DateUtil.format( dto.getVdate());
                map.put("vdate", vdate);
                EtrStockConnectDto rateDto = etrStockConnect002Service.getReferenceRate(map);
                if(rateDto != null){
                    BigDecimal referenceRateIn = rateDto.getReferenceRateIn();
                    BigDecimal referenceRateOut = rateDto.getReferenceRateOut();
                    if(referenceRateIn!=null && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())){
                        dto.setEntrustAmt(dto.getEntrustAmtHk().multiply(referenceRateIn));
                    }
                    else if(referenceRateOut!=null && StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())){
                        dto.setEntrustAmt(dto.getEntrustAmtHk().multiply(referenceRateOut));
                    }
                }
            }

            //查询子组合名称
            Map<String, Object> map1 = new HashMap<String, Object>();
            map1.put("initValue", dto.getPortfolioId());
            map1.put("page", 1);
            map1.put("length", 20);
            Page<SelectOptionDto> page1 = qryPortfolioIdChild(map1);
            if (StringUtil.isNotEmpty(page1) && StringUtil.isNotEmpty(page1.getRoot())) {
                // 若查出一条直接获取资产名称和资产代码
                if (page1.getRoot().size() == 1) {
                    SelectOptionDto d = page1.getRoot().get(0);
                    dto.setPortfolioName(d.getText());
                }
            }

            //查询母组合名称
            /*map1.put("initValue", dto.getParentPortfolioId());
            Page<SelectOptionDto> page2 =selectParentPortfolioId(map1);
            if (StringUtil.isNotEmpty(page2) && StringUtil.isNotEmpty(page2.getRoot())) {
                // 若查出一条直接获取资产名称和资产代码
                if (page2.getRoot().size() == 1) {
                    SelectOptionDto d = page2.getRoot().get(0);
                }
            }*/

            // 根据资产代码查出资产名称和FinprodId
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("initValue", dto.getFinprodMarketId());
            map.put("page", 1);
            map.put("length", 20);
            Page<SelectOptionDto> page = etrStockConnect002Service.getfinprodIdForAdd(map);
            if (StringUtil.isNotEmpty(page) && StringUtil.isNotEmpty(page.getRoot())) {
                // 若查出一条直接获取资产名称和资产代码
                if (page.getRoot().size() == 1) {
                    SelectOptionDto d = page.getRoot().get(0);
                    dto.setFinprodName(d.getText());
                    if (StringUtil.isNotEmpty(d.getObject())) {
                        Map<String, Object> m = (Map<String, Object>)d.getObject();
                        dto.setFinprodId((String)m.get("finprodId"));
                    }
                }
                // 若查出多条，遍历出想要的那条，再获取资产名称和资产代码
                else if (page.getRoot().size() > 1) {
                    for (SelectOptionDto d : page.getRoot()) {
                        if (StringUtil.equals(d.getId(), dto.getFinprodMarketId())) {
                            dto.setFinprodName(d.getText());
                            if (StringUtil.isNotEmpty(d.getObject())) {
                                Map<String, Object> m = (Map<String, Object>)d.getObject();
                                dto.setFinprodId((String)m.get("finprodId"));
                            }
                        }
                    }
                }
            }

            //查询证券账户
            if(StringUtil.isNotEmpty(dto.getPortfolioId()) && StringUtil.isNotEmpty(dto.getHkseMarket())){
                Map<String, Object> mapSec = new HashMap<>();
                mapSec.put("portfolioId",dto.getPortfolioId());
                mapSec.put("hkseMarket",dto.getHkseMarket());
                StlSecAcctInfo stlSecAcctInfo = etrStockConnect002Service.getSecManageAcctId(mapSec);
                if(stlSecAcctInfo != null){
                    dto.setSecAcctId(stlSecAcctInfo.getSecAcctId());
                }
            }

            // 表格隐藏字段
            dto.setInvManagerId(LoginUserUtil.getUserName());
            dto.setCcy(DictCodeList.币种_人民币_CNY.getValue());
            dto.setTradeMarket(DictCodeListExt1.交易场所_香港联合交易所.getValue());
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            EtrBoutRepoDtoList.add(dto);
        }
        return EtrBoutRepoDtoList;
    }
    
    /**
     * 验证导入的数据
     * 
     * @param tempList 导入的数据
     * @return wr 报错信息与正确的数据
     */
    /**
     * 验证导入的数据
     * @param fileName 导入的数据
     * @return wr 报错信息与正确的数据
     */
    public AjaxResult checkImportData(String fileName) {
        String confirmMsg = "";
        String changeLineMark = "<br/>";
        List<List<String>> objects = ExcelUtil.getTempList(fileName);
        Map<String, Object> resultMap = new HashMap<>();
        // 判断导入数据是否大于100条，如果大于100条，报出信息
        if (objects.size() < 102) {
            int noLine = 1;
            int x = objects.size()-noLine;
            List<List<String>> jsonArrays = new ArrayList<>();
            //jsonArrays.add(objects.get(0));
            // 循环check每条数据
            for (int i = 1; i < objects.size(); i++ ) {
                String lineNo = String.valueOf(i + 1);
                String confirmMsg1 = "";
                List<String> rowData = objects.get(i);
                String indexId = rowData.get(0).toString();
                if (StringUtil.isBlank(indexId)) {
                    confirmMsg1 = getMessage("ETR_ST_002", i + 1);
                    break;
                }

                // 委托开始日check是否为空和日期格式是否正确
                String message = etrCommonService.checkIsBlank(rowData.get(1).toString(),
                        "EtrStockConnect002.vdate", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                message = etrCommonService.checkIsDate(rowData.get(1).toString(),
                        "EtrStockConnect002.vdate", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);

                // 委托结束日非空和日期格式是否正确
                message = etrCommonService.checkIsBlank(rowData.get(2).toString(),
                        "EtrStockConnect002.mdate", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                message = etrCommonService.checkIsDate(rowData.get(2).toString(),
                        "EtrStockConnect002.mdate", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);

                // 组合代码非空和是否存在check
                message = etrCommonService.checkIsBlank(rowData.get(4).toString(),
                        "EtrStockConnect002.portfolioId", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                message = etrCommonService.checkPortfolioIdExist(rowData.get(4).toString(),
                        "EtrStockConnect002.portfolioId", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);


                // 资产代码非空和是否存在check
                message = etrCommonService.checkIsBlank(rowData.get(5).toString(),
                        "EtrStockConnect002.finprodId", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                if (StringUtil.isNotEmpty(rowData.get(5).toString())) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("search", rowData.get(5).toString());
                    map.put("page", 1);
                    map.put("length", 20);
                    Page<SelectOptionDto> finprodIds = etrStockConnect002Service.getfinprodIdForAdd(map);
                    if (finprodIds.getRoot().isEmpty()) {
                        confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, getMessage(
                                "ETR_E_0017", getMessage("EtrStockConnect002.finprodId"), lineNo));
                    }
                }

                // 交易方向非空check
                message = etrCommonService.checkIsBlank(rowData.get(7).toString(),
                        "EtrStockConnect002.ps", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);

                // 交易方向只能是买入、卖出
                if (StringUtil.isNotEmpty(rowData.get(7).toString())) {
                    String[] t = rowData.get(7).toString().trim().split(":");
                    if (!StringUtil.equals(t[0], DictCodeList.交易方向_买入.getValue())
                          && !StringUtil.equals(t[0], DictCodeList.交易方向_卖出.getValue())) {
                        message = "多对多指令交易方向只能为买入/卖出(行号为" + lineNo+")";
                        confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                    }
                }

                // 价格模式非空
                message = etrCommonService.checkIsBlank(rowData.get(8).toString(),
                        "EtrStockConnect002.priceModel", lineNo);
                confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);

                BigDecimal price = null;
                String finprodId  ="F10_" + rowData.get(5).toString(); //用于查询最小买卖单位、昨日收盘价
                // 当价格模式为市价指令是，委托价格不可填,其他的价格模式，委托价格必填
                if (StringUtil.isNotEmpty(rowData.get(8).toString())) {
                    String[] t = rowData.get(8).toString().trim().split(":");
                    if (StringUtil.equals(t[0], DictCodeListExt2.价格模式_市价指令.getValue())) {
                        if (StringUtil.isNotBlank(rowData.get(9).toString())) {
                            if (StringUtil.isNotBlank(confirmMsg1)) {
                                confirmMsg1 += changeLineMark;
                            }
                            confirmMsg1 += getMessage("ETR_ST_003",
                                    getMessage("EtrStockConnect002.price"), lineNo);
                        }
                        price = EtrCommonCashPositionDealService.getHkseClosePrice(finprodId,
                                DateUtil.platDateFromCache());
                    }
                    else {
                        message = etrCommonService.checkIsBlank(rowData.get(9).toString(),
                                "EtrStockConnect002.price", lineNo);
                        confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                        price = BigDecimal.valueOf(Double.valueOf(rowData.get(9).toString().trim()));
                    }
                }

                // 数量和金额(港元)选填
                String entrustNumStr = rowData.get(10).toString();
                String entrustAmtHkStr = rowData.get(11).toString();
                if(StringUtil.isNotEmpty(entrustAmtHkStr) && StringUtil.isNotEmpty(entrustNumStr)){
                    message = "指令数量和指令金额选填(行号为" + lineNo+")";
                    confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                }
                if(!StringUtil.isNotEmpty(entrustAmtHkStr) && !StringUtil.isNotEmpty(entrustNumStr)){
                    message = "指令数量和指令金额选填(行号为" + lineNo+")";
                    confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                }

                //最小买卖单位(输入数量)
                if(StringUtil.isNotEmpty(entrustNumStr) && !StringUtil.isNotEmpty(entrustAmtHkStr)){
                    EtrStockConnectDto dtoMin = new EtrStockConnectDto();
                    Map<String,Object> map = new HashMap<>();
                    BigDecimal entrustNum = BigDecimal.valueOf(Double.valueOf(rowData.get(10).toString().trim()));
                    map.put("finprodId",finprodId);
                    dtoMin = etrStockConnect002Service.getFinProdInfo(map);
                    if(null!=dtoMin && null != dtoMin.getMinSaleUnit()){
                        BigDecimal multiple = entrustNum.divide(dtoMin.getMinSaleUnit());
                        if(new BigDecimal(multiple.intValue()).compareTo(multiple) != 0){
                            message = "指令数量应为实际最小买卖单位的整数倍! 该资产最小买卖单位为"+dtoMin.getMinSaleUnit()+"股(行号为" + lineNo+")";
                            confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                        }
                    }
                }

                //最小买卖单位(输入金额)
                if(!StringUtil.isNotEmpty(entrustNumStr) && StringUtil.isNotEmpty(entrustAmtHkStr) && price !=null){
                    EtrStockConnectDto dtoMin = new EtrStockConnectDto();
                    Map<String,Object> map = new HashMap<>();
                    BigDecimal entrustAmtHk = BigDecimal.valueOf(Double.valueOf(rowData.get(11).toString().trim()));
                    BigDecimal entrustNum = ArithmeticUtil.divide(entrustAmtHk, price, 14).setScale( 0, BigDecimal.ROUND_DOWN );

                    map.put("finprodId",finprodId);
                    dtoMin = etrStockConnect002Service.getFinProdInfo(map);
                    if(null!=dtoMin && null != dtoMin.getMinSaleUnit()){
                        BigDecimal multiple = entrustNum.divide(dtoMin.getMinSaleUnit());
                        if(new BigDecimal(multiple.intValue()).compareTo(multiple) != 0){
                            message = "指令数量应为实际最小买卖单位的整数倍! 该资产最小买卖单位为"+dtoMin.getMinSaleUnit()+"股(行号为" + lineNo+")";
                            confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                        }
                    }
                }

                //查询证券账户
                String portfolioId = null;
                String hkseMarket = null;
                if(StringUtil.isNotBlank(rowData.get(4).toString())){
                    portfolioId = rowData.get(4).toString().trim();
                }
                if (StringUtil.isNotBlank(rowData.get(6).toString())) {
                    String[] s = rowData.get(6).toString().split(":");
                    hkseMarket = s[0];
                }
                if(StringUtil.isNotEmpty(portfolioId) && StringUtil.isNotEmpty(hkseMarket)){
                    Map<String, Object> mapSec = new HashMap<>();
                    mapSec.put("portfolioId",portfolioId);
                    mapSec.put("hkseMarket",hkseMarket);
                    StlSecAcctInfo stlSecAcctInfo = etrStockConnect002Service.getSecManageAcctId(mapSec);
                    if(stlSecAcctInfo == null){
                        message = "组合未找到相应交易所开通港股通的证券账户!(行号为" + lineNo+")";
                        confirmMsg1 = etrCommonService.StringAddByBr(confirmMsg1, message);
                    }
                }

                // 若是数据合格，将此条数据放在jsonArrays中返回
                if (StringUtil.isBlank(confirmMsg1)) {
                    jsonArrays.add(rowData);
                }
                // 将所有错误信息汇总返回
                if (StringUtil.isNotBlank(confirmMsg1)) {
                    confirmMsg += confirmMsg1;
                    confirmMsg += changeLineMark;
                }
            }
            // 计算多少条数据导入失败
            int y = jsonArrays.size();
            int num = x - y;
            // 导入失败多少条数据报出的信息
            confirmMsg += changeLineMark + getMessage("ETR_ST_009", num);

            if (StringUtil.isNotBlank(confirmMsg)) {
                confirmMsg += changeLineMark + getMessage("ETR_ST_010");
                resultMap.put("confirmMsg", confirmMsg);
            }
            resultMap.put("correctData", JSONArray.toJSONString(jsonArrays));
        }
        else {
            // 大于100条，提示大于100条，重新导入信息
            JSONArray jsonArrays = new JSONArray();
            confirmMsg = this.getMessage("ETR_ST_001");
            resultMap.put("confirmMsg", confirmMsg);
            jsonArrays.add(0);
            resultMap.put("correctData", JSONArray.toJSONString(jsonArrays));
        }
        AjaxResult wr = new AjaxResult();
        wr.setData(resultMap);
        return wr;
    }

    /**
     * getfinprodIdForAdd
     * 新建页面资产代码联想下拉框
     */
    public Page<SelectOptionDto> selectParentPortfolioId(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/selectParentPortfolioId",map, SelectOptionDto.class);
    }

    /**
     * getfinprodIdForAdd
     * 新建页面资产代码联想下拉框
     */
    public Page<SelectOptionDto> qryPortfolioIdChild(Map<String, Object> map) {
        return restTemplate.postForPage(ServiceApplication.COMMON_SERVICE_NAME + "/CommonSelect/getPortfolioIdChild",map, SelectOptionDto.class);
    }

}
