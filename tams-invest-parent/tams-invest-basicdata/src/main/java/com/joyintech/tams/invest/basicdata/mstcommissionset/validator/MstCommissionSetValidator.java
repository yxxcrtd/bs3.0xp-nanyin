package com.joyintech.tams.invest.basicdata.mstcommissionset.validator;

import com.joyintech.calculation.util.ArithmeticUtil;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.BeanValidator;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.StlSecAcctInfoMapper;
import com.joyintech.tams.common.base.dao.TrdFeeConfigMapper;
import com.joyintech.tams.common.base.entity.StlSecAcctInfo;
import com.joyintech.tams.common.base.entity.TrdFeeConfigDetail;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.dto.MstBankTranFeeManageDto;
import com.joyintech.tams.invest.basicdata.mstbanktranfeemanage.service.MstBankTranFeeManageService;
import com.joyintech.tams.invest.basicdata.mstcommissionset.dto.MstCommissionSetDto;
import com.joyintech.tams.invest.basicdata.mstcommissionset.service.MstCommissionSetService;
import com.joyintech.tams.invest.common.constant.BussinessDictCode;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 佣金设置验证
 *
 * @author 裴家俊
 * @version 1.0 2019年3月22日
 * @see MstCommissionSetValidator
 * @since 1.0
 */
@Component
public class MstCommissionSetValidator implements BeanValidator {

    /**
     * 共通Mapper的通用代理方案
     */

    /**
     * MstCommissionSetService
     */
    @Autowired
    private MstCommissionSetService mstCommissionSetService;

    @Autowired
    private MstBankTranFeeManageService mstBankTranFeeManageService;

    @Autowired
    private TrdFeeConfigMapper trdFeeConfigMapper;
    /**
     * {@linkplain}
     */
    @Autowired
    private StlSecAcctInfoMapper stlSecAcctInfoMapper;

    @Override
    public List<ValidMessage> valid(Object value) {
        MstCommissionSetDto dto = (MstCommissionSetDto)value;
        List<ValidMessage> resultList = new ArrayList<ValidMessage>();
        if (StringUtil.equals(dto.getOperateType(), "delete")) {
            if (CollectionUtils.isNotEmpty(dto.getSelectedItem())) {
                dto.getSelectedItem().forEach(item -> {
                    if (mstCommissionSetService.checkBusinessDataInFlow(item)) {
                        resultList.add(new ValidMessage(
                                this.getMessage("COMMON_E_0012", item.getBatchSeq())));
                    }
                    if (!StringUtil.isNotEmpty(
                            mstCommissionSetService.getBussinessEntityBySeqNo(item.getBatchSeq()))) {
                        resultList.add(new ValidMessage(this.getMessage("COMMON_E_0001")));
                    }
                });
                return resultList;
            }

        }
        else {
            if (StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_NEW)
                    || StringUtil.equals(dto.getPageModel(), GlobalConstant.DETAIL_PAGE_MODEL_EDIT)) {
                // add by wangguoxian 当费用类型选择为佣金时，证券账户必填 20190717 start
                if (StringUtil.equals(BussinessDictCodeList.费用类型_佣金.getValue(), dto.getFeeType())
                        && StringUtil.isBlank(dto.getSecAcctId())) {
                    ValidMessage validMessage = new ValidMessage("secAcctId",
                            getMessage("FW_E_0006", getMessage("MstCommissionSet002.secAcct")));
                    resultList.add(validMessage);
                }
                // add by wangguoxian 当费用类型选择为佣金时，证券账户必填 20190717 end
                // add by wangguoxian 当费用类型选择为佣金时，是否随交易支付必填 20190717 start
                if (StringUtil.equals(BussinessDictCodeList.费用类型_佣金.getValue(), dto.getFeeType())
                        && StringUtil.isBlank(dto.getIsPayWithTrade())) {
                    ValidMessage validMessage = new ValidMessage("isPayWithTrade",
                            getMessage("FW_E_0006", getMessage("MstCommissionSet002.isPayWithTrade")));
                    resultList.add(validMessage);
                }
                // add by wangguoxian 当费用类型选择为佣金时，是否随交易支付必填 20190717 end
                // 交易品种选择质押式回购和买断式回购时，期限品种必填
                if (StringUtil.equals(BussinessDictCodeList.平安资产类型_回购.getValue(),
                        dto.getTradeType())) {
                    if (StringUtil.isBlank(dto.getTermType())) {
                        ValidMessage validMessage = new ValidMessage("termType",
                                getMessage("FW_E_0006", getMessage("MstCommissionSet002.termType")));
                        resultList.add(validMessage);
                    }
                }

                // 计费类型选择固定费率时，计数基数必输
                if (StringUtil.equals(BussinessDictCodeList.计费类型_固定费率.getValue(),
                        dto.getChargeType())) {
                    if (StringUtil.isBlank(dto.getChargeBasis())) {
                        ValidMessage validMessage = new ValidMessage("chargeBasis", getMessage(
                                "FW_E_0006", getMessage("MstCommissionSet002.chargeBasis")));
                        resultList.add(validMessage);
                    }
                }
                if(!"edit".equals(dto.getOperateType())&&"F01".equals(dto.getTradeType())){
                    if(!StringUtil.isNotEmpty(dto.getTradeMarketMode())){
                        ValidMessage validMessage = new ValidMessage("tradeMarketMode",getMessage("请输入交易市场。"));
                        resultList.add(validMessage);
                    }
                }
                if(!"edit".equals(dto.getOperateType())&&"Y".equals(dto.getIsGrade())){
                    if(dto.getTrdFeeConfigDetailList()==null||dto.getTrdFeeConfigDetailList().size()==0){
                        ValidMessage validMessage = new ValidMessage(getMessage("当【是否分档】为“是”时，分档信息必填。"));
                        resultList.add(validMessage);
                    }
                    boolean flag1=true,flag2=true,flag3=true,flag4=true;
                    for(TrdFeeConfigDetail TrdFeeConfigDetailDto:dto.getTrdFeeConfigDetailList()){
                        if(!StringUtil.isNotEmpty(TrdFeeConfigDetailDto.getIsCludeStartamt())&&flag3){
                            resultList.add(new ValidMessage(getMessage("分档信息中是否包含起点金额不能为空")));
                            flag3=false;
                        }
                        if(!StringUtil.isNotEmpty(TrdFeeConfigDetailDto.getGradeAmount())&&flag4){
                            resultList.add(new ValidMessage(getMessage("分档信息中分档起点金额（万元）不能为空")));
                            flag4=false;
                        }
                        if("01".equals(dto.getChargeType())&&!StringUtil.isNotEmpty(TrdFeeConfigDetailDto.getChargeRate())&&flag1){
                            resultList.add(new ValidMessage(getMessage("分档信息中费率（‰）不能为空")));
                            flag1=false;
                        }
                        if("02".equals(dto.getChargeType())&&!StringUtil.isNotEmpty(TrdFeeConfigDetailDto.getChargeValue())&&flag2){
                            resultList.add(new ValidMessage(getMessage("分档信息中费用值不能为空")));
                            flag2=false;
                        }
                    }
                }else if(!"Y".equals(dto.getIsGrade())){
                    if (StringUtil.equals(BussinessDictCodeList.计费类型_固定费率.getValue(),
                            dto.getChargeType())) {
                        if (!StringUtil.isNotEmpty(dto.getChargeRate())) {
                            ValidMessage validMessage = new ValidMessage("chargeRate",
                                    getMessage("FW_E_0006",getMessage("MstCommissionSet002.chargeRate")));
                            resultList.add(validMessage);
                        }
                    }
                    else {
                        if (!StringUtil.isNotEmpty(dto.getChargeValue())) {
                            ValidMessage validMessage = new ValidMessage("chargeValue", getMessage(
                                    "FW_E_0006",getMessage("MstCommissionSet002.chargeValue")));
                            resultList.add(validMessage);
                        }
                    }
                }
                if (null != dto.getChargeValue()
                        && dto.getChargeValue().compareTo(BigDecimal.ZERO) == -1) {
                    resultList.add(
                            new ValidMessage("chargeValue", this.getMessage("BUSS_COM_E_0004",
                                    this.getMessage("MstCommissionSet002.chargeValue"), "0")));
                }
                if (null != dto.getChargeRate()
                        && dto.getChargeRate().compareTo(BigDecimal.ZERO) == -1) {
                    resultList.add(
                            new ValidMessage("chargeRate", this.getMessage("BUSS_COM_E_0004",
                                    this.getMessage("MstCommissionSet002.chargeRate"), "0")));
                }
                if (null != dto.getFeeMini()
                        && dto.getFeeMini().compareTo(BigDecimal.ZERO) == -1) {
                    resultList.add(new ValidMessage("feeMini", this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("MstCommissionSet002.feeMini"), "0")));
                }
                if (null != dto.getFeeMaxi()
                        && dto.getFeeMaxi().compareTo(BigDecimal.ZERO) == -1) {
                    resultList.add(new ValidMessage("feeMaxi", this.getMessage("BUSS_COM_E_0004",
                            this.getMessage("MstCommissionSet002.feeMaxi"), "0")));
                }
                // 判断最小金额不能大于最大金额
                if (StringUtil.isNotEmpty(dto.getFeeMini())
                        && StringUtil.isNotEmpty(dto.getFeeMaxi())) {
                    if (ArithmeticUtil.doSub(dto.getFeeMini(), dto.getFeeMaxi()).compareTo(
                            BigDecimal.ZERO) > 0) {
                        resultList.add(new ValidMessage(this.getMessage("BUSS_COM_E_0003",
                                getMessage("MstCommissionSet002.feeMini"),
                                getMessage("MstCommissionSet002.feeMaxi"))));
                    }

                }
                List<MstCommissionSetDto> pageDataList = dto.getSelectedItem();
                if("F01".equals(dto.getTradeType())) pageDataList = sepWindtype(pageDataList);
                if(!"edit".equals(dto.getOperateType())){
                    for(MstCommissionSetDto mstCommissionSetDto:pageDataList){
                        Map<String,Object> param = new HashMap<>();
                        param.put("batchSeq",StringUtil.equals(dto.getPageModel(),
                                GlobalConstant.DETAIL_PAGE_MODEL_NEW) ? StringUtil.EMPTY : dto.getBatchSeq());
                        param.put("tradeType",mstCommissionSetDto.getTradeType());
                        param.put("feeType",mstCommissionSetDto.getFeeType());
                        String effDateStr = DateUtil.format(mstCommissionSetDto.getEffDate(),"yyyy-MM-dd HH:mm:ss");
                        param.put("effDate", effDateStr);
                        param.put("tradeMarket",mstCommissionSetDto.getTradeMarket());
                        param.put("ps", mstCommissionSetDto.getPs());
                        param.put("secAcctId", mstCommissionSetDto.getSecAcctId());
                        param.put("termType",mstCommissionSetDto.getTermType());
                        param.put("tradeMarketMode",mstCommissionSetDto.getTradeMarketMode());
                        param.put("windtypeone",mstCommissionSetDto.getWindtypeone());
                        param.put("windtypetwo",mstCommissionSetDto.getWindtypetwo());
                        int count = mstCommissionSetService.searchRepeatBySecPs(param);
                        if (count > 0) {
                            String secAcctName = "",tradeMarketMode = "",windtypeone = "",windtypetwo = "",termType = "";
                            if(StringUtil.isNotEmpty(mstCommissionSetDto.getSecAcctId())){
                                StlSecAcctInfo stlSecAcctInfo = stlSecAcctInfoMapper.selectByPrimaryKey(mstCommissionSetDto.getSecAcctId());
                                secAcctName = stlSecAcctInfo.getSecAcctName()+"(证券账户)";
                            }
                            if(StringUtil.isNotEmpty(mstCommissionSetDto.getTradeMarketMode())) tradeMarketMode = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易市场, mstCommissionSetDto.getTradeMarketMode())+"(交易市场)";
                            if(StringUtil.isNotEmpty(mstCommissionSetDto.getWindtypeone())) windtypeone = SysDataUtil.getNameFromDataDict(BussinessDictCode.WIND一级分类_含债券一级分类等, mstCommissionSetDto.getWindtypeone())+"(资产分类一)";
                            if(StringUtil.isNotEmpty(mstCommissionSetDto.getWindtypetwo())) windtypetwo = SysDataUtil.getNameFromDataDict(BussinessDictCode.WIND二级分类_含债券二级分类等, mstCommissionSetDto.getWindtypetwo())+"(资产分类二)";
                            if(StringUtil.isNotEmpty(mstCommissionSetDto.getTermType())) termType = mstCommissionSetDto.getWindtypetwo() + "(资产分类二)";
                            resultList.add(new ValidMessage(SysDataUtil.getNameFromDataDict(BussinessDictCode.交易场所, mstCommissionSetDto.getTradeMarket())+"(交易场所)"
                                    + secAcctName +SysDataUtil.getNameFromDataDict(BussinessDictCode.平安资产类型, dto.getTradeType())+"(交易品种)"
                                    + tradeMarketMode + windtypeone + windtypetwo + termType + SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, mstCommissionSetDto.getPs()) + "(交易方向)"
                                    + DateUtil.format(mstCommissionSetDto.getEffDate(), "yyyy-MM-dd") + "(生效日)交易费用已设置，请重新选择！"
                            ));
                        }
                    }
                }
                //判断新建或修改的数据跟流程表中是否冲突
                WflValidInputParameter parameter = new WflValidInputParameter();
                parameter.setPageId(dto.getPageId());
                //直接调用银行间费用的get
                List<MstBankTranFeeManageDto> workFlowDataObjList = mstBankTranFeeManageService.getBusinessData(parameter);
                List<MstCommissionSetDto> workFlowDataList = new ArrayList<MstCommissionSetDto>();
                //update by 张年 20190830 start
                if(workFlowDataObjList != null && workFlowDataObjList.size()>0 && pageDataList != null && pageDataList.size()>0 ) {
                    for (MstBankTranFeeManageDto workFlowDataObj : workFlowDataObjList) {
                        if (workFlowDataObj.getAllData() != null && !StringUtil.equals(workFlowDataObj.getAllData(), "")) {
                            try {
                                List<MstCommissionSetDto> allDataList = JsonUtils.json2List(workFlowDataObj.getAllData(), MstCommissionSetDto.class);
                                //原先组装方法pk1过长
                                if (null != allDataList && !allDataList.isEmpty()) {
                                    //交易方向
                                    String[] arrPs = allDataList.get(0).getPs().split(",");
                                    //期限品种
                                    for (String ps : arrPs) {
                                        MstCommissionSetDto mstCommissionSetDto = new MstCommissionSetDto();
                                        mstCommissionSetDto.setFeeType(allDataList.get(0).getFeeType());
                                        mstCommissionSetDto.setTermType(allDataList.get(0).getTermType());
                                        mstCommissionSetDto.setTradeType(allDataList.get(0).getTradeType());
                                        mstCommissionSetDto.setEffDate(allDataList.get(0).getEffDate());
                                        mstCommissionSetDto.setTradeMarket(allDataList.get(0).getTradeMarket());
                                        mstCommissionSetDto.setTradeMarketMode(allDataList.get(0).getTradeMarketMode());
                                        mstCommissionSetDto.setWindtypeone(allDataList.get(0).getWindtypeone());
                                        mstCommissionSetDto.setWindtypetwo(allDataList.get(0).getWindtypetwo());
                                        mstCommissionSetDto.setPs(ps);
                                        mstCommissionSetDto.setWorkflowId(workFlowDataObj.getWorkflowId());
                                        mstCommissionSetDto.setStatus(workFlowDataObj.getStatus());
                                        if(StringUtil.isNotEmpty(allDataList.get(0).getTermType())){
                                            String[] arrTerm = allDataList.get(0).getTermType().split(",");
                                            for (String termType : arrTerm) {
                                                mstCommissionSetDto.setTermType(termType);
                                                if(StringUtil.isNotEmpty(allDataList.get(0).getSecAcctId())){
                                                    for (String secAcctId : allDataList.get(0).getSecAcctId().split(",")) {
                                                        mstCommissionSetDto.setSecAcctId(secAcctId);
                                                        workFlowDataList.add(mstCommissionSetDto);
                                                    }
                                                }else{
                                                    workFlowDataList.add(mstCommissionSetDto);
                                                }
                                            }
                                        }else{
                                            if(StringUtil.isNotEmpty(allDataList.get(0).getSecAcctId())){
                                                for (String secAcctId : allDataList.get(0).getSecAcctId().split(",")) {
                                                    mstCommissionSetDto.setSecAcctId(secAcctId);
                                                    workFlowDataList.add(mstCommissionSetDto);
                                                }
                                            }else{
                                                workFlowDataList.add(mstCommissionSetDto);
                                            }
                                        }
                                    }
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    if("F01".equals(dto.getTradeType())) workFlowDataList = sepWindtype(workFlowDataList);
                    //定义一个全局开关
                    for(MstCommissionSetDto pageData:pageDataList){
                        //如果交易品种相同，交易方向相同并且，工作流代码不相同(即不是同一工作流程中的的数据)
                        for(MstCommissionSetDto workFlowData : workFlowDataList){
                            if(StringUtil.equals(pageData.getTradeType(), workFlowData.getTradeType())
                                    && (StringUtil.equals(pageData.getTradeMarket(), workFlowData.getTradeMarket()))
                                    && (StringUtil.equals(pageData.getTradeMarketMode(), workFlowData.getTradeMarketMode()))
                                    && StringUtil.equals(pageData.getPs(), workFlowData.getPs())
                                    && StringUtil.equals(pageData.getFeeType(), workFlowData.getFeeType())
                                    && StringUtil.equals(pageData.getTermType(), workFlowData.getTermType())
                                    && StringUtil.equals(pageData.getSecAcctId(), workFlowData.getSecAcctId())
                                    && DateUtil.compareDate(pageData.getEffDate(),workFlowData.getEffDate())==0
                                    && StringUtil.equals(pageData.getWindtypeone(), workFlowData.getWindtypeone())
                                    && StringUtil.equals(pageData.getWindtypetwo(), workFlowData.getWindtypetwo())
                                    && !StringUtil.equals(workFlowData.getWorkflowId(), dto.getWorkflowId())
                                    && !StringUtil.equals(workFlowData.getStatus(),"U")){
                                String secAcctName = "",tradeMarketMode = "",windtypeone = "",windtypetwo = "",termType = "";
                                if(StringUtil.isNotEmpty(pageData.getSecAcctId())){
                                    StlSecAcctInfo stlSecAcctInfo = stlSecAcctInfoMapper.selectByPrimaryKey(pageData.getSecAcctId());
                                    secAcctName = stlSecAcctInfo.getSecAcctName()+"(证券账户)";
                                }
                                if(StringUtil.isNotEmpty(pageData.getTradeMarketMode())) tradeMarketMode = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易市场, pageData.getTradeMarketMode())+"(交易市场)";
                                if(StringUtil.isNotEmpty(pageData.getWindtypeone())) windtypeone = SysDataUtil.getNameFromDataDict(BussinessDictCode.WIND一级分类_含债券一级分类等, pageData.getWindtypeone())+"(资产分类一)";
                                if(StringUtil.isNotEmpty(pageData.getWindtypetwo())) windtypetwo = SysDataUtil.getNameFromDataDict(BussinessDictCode.WIND二级分类_含债券二级分类等, pageData.getWindtypetwo())+"(资产分类二)";
                                if(StringUtil.isNotEmpty(pageData.getTermType())) termType = pageData.getWindtypetwo() + "(资产分类二)";
                                resultList.add(new ValidMessage(SysDataUtil.getNameFromDataDict(BussinessDictCode.交易场所, pageData.getTradeMarket())+"(交易场所)"
                                        + secAcctName +SysDataUtil.getNameFromDataDict(BussinessDictCode.平安资产类型, dto.getTradeType())+"(交易品种)"
                                        + tradeMarketMode + windtypeone + windtypetwo + termType + SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向, pageData.getPs()) + "(交易方向)"
                                        + DateUtil.format(pageData.getEffDate(), "yyyy-MM-dd") + "(生效日)交易费用已存在流程中数据，请重新选择！"
                                ));
                            }
                        }
                    }
                }
                //Add  by  张年  20190829  end
            }
        }
        return resultList;
    }

    /**
     * 把资产分类一、资产分类二还未拆分的数据按照分类规则进行拆分生成多条数据
     * @param list 需要拆分的list
     * @return
     */
    private List<MstCommissionSetDto> sepWindtype(List<MstCommissionSetDto> list) {
        if(list==null||list.size()<=0) return list;
        List<MstCommissionSetDto> objList = new ArrayList<>();
        for(MstCommissionSetDto dto:list){
            if(StringUtil.isNotEmpty(dto.getWindtypeone())){
                for(String windtypeone:dto.getWindtypeone().split(",")){
                    dto.setWindtypeone(windtypeone);
                    List<String> windtypetwoList=trdFeeConfigMapper.findWindtypetwo(windtypeone);
                    if(StringUtil.isNotEmpty(dto.getWindtypetwo())&&windtypetwoList!=null&&windtypetwoList.size()>0){
                        Boolean flag = false;
                        for(String windtypetwo:dto.getWindtypetwo().split(",")){
                            if(windtypetwoList.contains(windtypetwo)){
                                MstCommissionSetDto entity = new MstCommissionSetDto();
                                BeanUtil.copyPropertiesIgnoreNull(entity, dto);
                                entity.setWindtypetwo(windtypetwo);
                                objList.add(entity);
                                flag = true;
                            }
                            if(!flag){
                                MstCommissionSetDto entity = new MstCommissionSetDto();
                                BeanUtil.copyPropertiesIgnoreNull(entity, dto);
                                entity.setWindtypetwo(null);
                                objList.add(entity);
                            }
                        }
                    }else{
                        MstCommissionSetDto entity = new MstCommissionSetDto();
                        BeanUtil.copyPropertiesIgnoreNull(entity, dto);
                        entity.setWindtypetwo(null);
                        objList.add(entity);
                    }
                }
            }else{
                MstCommissionSetDto entity = new MstCommissionSetDto();
                BeanUtil.copyPropertiesIgnoreNull(entity, dto);
                objList.add(entity);
            }
        }
        return objList;
    }

    // add by wangguoxian 20190718 start
    /**
     *
     * 获取交易方向名称
     * @param ps 交易方向
     * @return 交易方向名称
     * @see
     * @since 1.0
     */
    private String getPsStr(String ps) {
        String psStr = StringUtil.EMPTY;
        if (ps.startsWith("T")) {
            psStr = SysDataUtil.getNameFromDataDict(BussinessDictCode.交易方向,
                    ps);
        }
        else {
            psStr = SysDataUtil.getNameFromDataDict(
                    BussinessDictCode.回购拆借方向, ps);
        }
        return psStr;
    }
    // add by wangguoxian 20190718 end

}
