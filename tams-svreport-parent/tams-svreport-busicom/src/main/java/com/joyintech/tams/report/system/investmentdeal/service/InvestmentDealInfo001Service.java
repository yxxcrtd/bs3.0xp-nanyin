package com.joyintech.tams.report.system.investmentdeal.service;

import com.joyintech.component.svrreport.chb.chbcommoncheck.ChbDataCheckCommonUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.ajax.MessageType;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.entity.ChbInvestTradeErrordataWithBLOBs;
import com.joyintech.tams.common.base.entity.ChbMstCounterParty;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.report.common.dto.ExcelValidDto;
import com.joyintech.tams.report.common.util.ExcelUtil;
import com.joyintech.tams.report.common.util.SysDataUtil;
import com.joyintech.tams.report.system.chbmst.dto.ChbMstCounterPartyDto;
import com.joyintech.tams.report.system.investmentdeal.dao.InvestmentDealInfo001Mapper;
import com.joyintech.tams.report.system.investmentdeal.dto.InvestmentDealInfoDto;
import com.joyintech.tams.svreport.interf.dis.prodcashyield.dto.DisRepCashYield;
import com.joyintech.tams.systemtools.dto.SysDataDict;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class InvestmentDealInfo001Service extends CommonExportService<InvestmentDealInfoDto> {

    @Autowired
    private InvestmentDealInfo001Mapper investmentDealInfo001Mapper;
    @Autowired
    private ChbDataCheckCommonUtil chbDataCheckCommonUtil;
    /**
     * 当在做导入前的check就是判断业务数据是否需要覆盖的时候在webresult的data中塞入这个值可以不会弹框两次
     */
    private static final String NO_INFO_SHOWOUT = "noInfoShowOut";

    public Page<InvestmentDealInfoDto> searchForPage(Map<String, Object> param){
        Page<InvestmentDealInfoDto> page;
        int pagenum = Integer.parseInt(param.get(GlobalConstant.PARAM_PAGE).toString());
        int legth = Integer.parseInt(param.get(GlobalConstant.PARAM_LENGTH).toString());
        param.put("prodId", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("prodId")));
        param.put("assetCode", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("assetCode")));
        param.put("status", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("status")));
        page=  super.queryForPage(pagenum,legth,()->investmentDealInfo001Mapper.queryForPage(param));
        dealDataList(page.getRoot());
        return page;
    }

    /**
     * 处理查询结果 <功能详细描述>
     *
     * @param list
     *            待处理数据
     * @see
     * @since 1.0
     */
    private void dealDataList(List<InvestmentDealInfoDto> list) {
        list.forEach(item -> {
            item.setCcyStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.币种_中债, item.getCcy()));
            item.setAssetMethodStr(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.资产计量方式, item.getAssetMethod()));
            item.setCurAssetTypeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.资金流动类型, item.getCurAssetType()));
            item.setRivalPartyTypeStr (SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.交易对手类型, item.getRivalPartyType()));
            item.setStatusName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.维护数据状态, item.getStatus()));
            item.setDataSourceName(SysDataUtil.getZZDictCodeAndNameNew(
                    DictCode.数据来源, item.getDataSource()));
        });


    }

    public List<InvestmentDealInfoDto> queryReportList(Map<String, Object> param) {
        param.put("prodId", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("prodId")));
        param.put("assetCode", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("assetCode")));
        param.put("status", StringUtil.splitToList(GlobalConstant.COMMA, (String)param.get("status")));
        List<InvestmentDealInfoDto> list = investmentDealInfo001Mapper.queryReportList( param);
        dealDataList(list);
        return list;
    }

    public AjaxResult checkConfirmMsgForImportData(List<List<String>> dataList) {
        List<ValidMessage> resultList = new ArrayList<>();
        AjaxResult webResult = new AjaxResult();
        List<ValidMessage> errorList = new ArrayList<>();
        Map<String,Object> map=new HashMap<>();
        for (int i = 0; i < dataList.size(); i++ ) {
            for (int j = i+1; j < dataList.size(); j++ ) {
                if(StringUtils.equals(dataList.get(i).get(3), dataList.get(j).get(3))){
                    ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行行内交易编码与"+"第"+(j+2)+"行行内交易编码相同");
                    errorList.add(validMessage);
                }
            }
        }
        errorList=resultList;
        if(errorList.size() == 0){
            for (int i = 0; i < dataList.size(); i++ ) {
                //操作类型
                String type ="";
                if(null!=dataList.get(i).get(0)&& !"".equals(dataList.get(i).get(0))){
                    type = dataList.get(i).get(0).toString();
                }
                InvestmentDealInfoDto dto=new InvestmentDealInfoDto();
                coverEntity(dto,dataList,i);
                resultList = chbDataCheckCommonUtil.chbCheckCommon(resultList, dto, "InvestmentDealInfo002", "", ValidMessage.class);
                map.put("tradeCode",dto.getTradeCode());
                InvestmentDealInfoDto infoDto= investmentDealInfo001Mapper.selectOneDate(map);
                if("新增".equals(type)){
                    if(StringUtil.isNotEmpty(infoDto)){
                        ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行已存在行内交易编码的数据");
                        errorList.add(validMessage);
                    }
                }
                if("修改".equals(type) || "删除".equals(type)){
                    if(!StringUtil.isNotEmpty(infoDto)){
                        ValidMessage validMessage = new ValidMessage("第"+(i+2)+"行不存在该行内交易编码的数据,请核查");
                        errorList.add(validMessage);
                    }
                }
            }
        }else{
            webResult.setErrorList(errorList);
        }
        return webResult;
    }

    public List<ExcelValidDto> importData(List<List<String>> dataList, List<List<String>> tempList,String userName, String orgCode, String deptCode) throws ParseException {
        if (!StringUtil.isEmptyList(dataList)) {
            List<String> headList = new ArrayList<String>();
            headList.add("操作类型");
            headList.add("产品代码");
            headList.add("行内资产/负债编码");
            headList.add("行内交易编码");
            headList.add("资金流动类型");
            headList.add("资金流动类型说明");
            headList.add("数量");
            headList.add("资产计量方式");
            headList.add("币种");
            headList.add("折算人民币金额");
            headList.add("发生金额");
            headList.add("单位成交价格（净价）");
            headList.add("单位成交价格（全价）");
            headList.add("到期收益率");
            headList.add("交易对手");
            headList.add("交易对手方类型");
            headList.add("成交编号/合同号");
            headList.add("交易日");
            headList.add("备注");
            List<ExcelValidDto> tempCheckResult = ExcelUtil.checkExcelTemp(tempList, headList);
            if (!StringUtil.isEmptyList(tempCheckResult)) {
                return tempCheckResult;
            }
            if (null != dataList) {
                Map<String,Object> map=new HashMap<>();
                for (int i = 0; i < dataList.size(); i++ ) {
                    InvestmentDealInfoDto dto = new InvestmentDealInfoDto();
                    coverEntity(dto,dataList,i);
                    //根据产品代码，启用日期，信披费用类型 查询出数据,有数据则更新,无数据则插入
                    map.put("tradeCode",dto.getTradeCode());
                    if("新增".equals(dto.getType()) || "修改".equals(dto.getType())){
                        save(dto);
                    }
                    if("删除".equals(dto.getType())){
                        delData(dto);
                    }
                }
            }
        }else{
            List<ExcelValidDto> checkResult = new ArrayList<>();
            ExcelValidDto excelValidDto = new ExcelValidDto();
            String errorMsg = "模板为空，请输入数据！";
            excelValidDto.setErrorInfo(errorMsg);
            checkResult.add(excelValidDto);
            return checkResult;
        }
        return null;
    }

    private void coverEntity(InvestmentDealInfoDto dto,List<List<String>> dataList,int i){
        dto.setProdId(dataList.get(i).get(1));
        dto.setAssetCode(dataList.get(i).get(2));
        dto.setTradeCode(dataList.get(i).get(3));
        dto.setCurAssetType(dataList.get(i).get(4));
        dto.setCurAssetdetType(dataList.get(i).get(5));
        if (StringUtil.isNotEmpty(dataList.get(i)) && StringUtil.isNotEmpty(dataList.get(i).get(6))){
            dto.setNum(new BigDecimal(dataList.get(i).get(6)));
        }
        dto.setAssetMethod(dataList.get(i).get(7));
        dto.setCcy(dataList.get(i).get(8));
        if (StringUtil.isNotEmpty(dataList.get(i)) && StringUtil.isNotEmpty(dataList.get(i).get(9))) {
            dto.setCnyAmt(new BigDecimal(dataList.get(i).get(9)));
        }
        if (StringUtil.isNotEmpty(dataList.get(i)) && StringUtil.isNotEmpty(dataList.get(i).get(10))) {
            dto.setAmt(new BigDecimal(dataList.get(i).get(10)));
        }
        if (StringUtil.isNotEmpty(dataList.get(i)) && StringUtil.isNotEmpty(dataList.get(i).get(11))) {
            dto.setUnitPriceFull(new BigDecimal(dataList.get(i).get(11)));
        }
        if (StringUtil.isNotEmpty(dataList.get(i)) && StringUtil.isNotEmpty(dataList.get(i).get(12))) {
            dto.setUnitPriceNet(new BigDecimal(dataList.get(i).get(12)));
        }
        if (StringUtil.isNotEmpty(dataList.get(i)) && StringUtil.isNotEmpty(dataList.get(i).get(13))) {
            dto.setEndRate(new BigDecimal(dataList.get(i).get(13)));
        }
        dto.setTradeCounter(dataList.get(i).get(14));
        dto.setRivalPartyType(dataList.get(i).get(15));
        dto.setContractCode(dataList.get(i).get(16));
        if (StringUtil.isNotEmpty(dataList.get(i)) && StringUtil.isNotEmpty(dataList.get(i).get(17))) {
            dto.setDealdate(DateUtil.parse(dataList.get(i).get(17)));
        }
        dto.setRemark(dataList.get(i).get(18));
        dto.setDataSource(DictCodeListExt2.数据来源_EXCEL导入.getValue());
    }

    public InvestmentDealInfoDto selectOneDate(Map<String, Object> param) {
        return investmentDealInfo001Mapper.selectOneDate( param);
    }

    public ChbInvestTradeErrordataWithBLOBs selectInvestmentDealInfoErrordata(String datauuid) {
        return investmentDealInfo001Mapper.selectInvestmentDealInfoErrordata( datauuid);
    }

    public void updateOne(InvestmentDealInfoDto dto) {
        investmentDealInfo001Mapper.updateOne(dto);
    }

    public List<SelectOptionDto> getProdId(Map<String, Object> param) {
        return investmentDealInfo001Mapper.getProdId( param);
    }

    public void save(InvestmentDealInfoDto dto){
        AjaxResult webResult = new AjaxResult();
        Map<String, Object> map=new HashMap<>();
        map.put("tradeCode",dto.getTradeCode());
        InvestmentDealInfoDto infoDto= investmentDealInfo001Mapper.selectOneDate(map);
        if (StringUtil.isNotEmpty(infoDto)){
            dto.setTradeSeqno(infoDto.getTradeSeqno());
            investmentDealInfo001Mapper.updateOne(dto);
        }else {
            dto.setStatus(DictCodeListExt1.维护数据状态_有效.getValue());
            dto.setType("01");
            investmentDealInfo001Mapper.insertOne(dto);
            investmentDealInfo001Mapper.deleteErrorDataOne(dto.getTradeCode());
        }
    }

    public List<SelectOptionDto> getTradeCounter(Map<String, Object> param) {
        return investmentDealInfo001Mapper.getTradeCounter( param);
    }

    public void delData(InvestmentDealInfoDto dto){
        Map<String, Object> map=new HashMap<>();
        map.put("tradeCode",dto.getTradeCode());
        InvestmentDealInfoDto infoDto= investmentDealInfo001Mapper.selectOneDate(map);
        if (StringUtil.isNotEmpty(infoDto)){
            dto.setTradeSeqno(infoDto.getTradeSeqno());
            dto.setStatus(DictCodeListExt1.维护数据状态_删除.getValue());
            investmentDealInfo001Mapper.updateOne(dto);
        }else {
            investmentDealInfo001Mapper.deleteErrorDataOne(dto.getTradeCode());
        }
    }

}
