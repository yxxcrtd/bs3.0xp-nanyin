package com.joyintech.interf.bankname.cfetsup.secdialogquote.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.interf.bankname.cfetsup.constant.FxDictCodeList;
import com.joyintech.interf.bankname.cfetsup.outbound.CallReturn;
import com.joyintech.interf.bankname.cfetsup.outbound.service.ClientService;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dao.FxSecdialogQuoteMapper;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.dto.FxSecdialogQuote002Dto;
import com.joyintech.interf.bankname.cfetsup.secdialogquote.entity.FxSecdialogQuote;
import com.joyintech.interf.bankname.cfetsup.secrepoquote.dto.EtrRepoDto;
import com.joyintech.tams.common.base.dao.FinProductMapper;
import com.joyintech.tams.common.base.entity.FinProduct;
import com.joyintech.tams.component.business.common.service.DateCalService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.trade.dto.SecInfoModelDto;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class FxSecdialogQuote002Service extends BaseService<FxSecdialogQuote002Dto> implements BaseProcessService<FxSecdialogQuote002Dto> {

    @Autowired
    private FxSecdialogQuoteMapper fxSecdialogQuoteMapper;
    @Autowired
    private ClientService clientService;

    /**
     * FinProductMapper
     */
    @Autowired(required = false)
    private FinProductMapper finProductMapper;

    @Autowired
    private IPlatformDate pPlatformDate;

    /**
     * DateCalService
     */
    @Autowired(required = false)
    private DateCalService dateCalService;

    /**
     * 内部报价编号前缀
     */
    private static final String SEC_REPO_QUOTE_ID = "QTSECREPO";

    public Page<SelectOptionDto> getFinprodId(Map<String, Object> params){

        return  new Page(fxSecdialogQuoteMapper.getFinprodId(params));
    }

    /**
     * 行权收益率是否显示
     *
     * @param
     * @return 行权收益率是否显示
     * @see
     * @since 1.0
     */
    public String isExerYieldDivShow(Map<String, Object> param) {
        String seccode = (String) param.get("seccode");
        if (StringUtil.isBlank(seccode)) {
            return DictCodeList.是否_否.getValue();
        }

        // 是否含权债
        FinProduct finProduct = finProductMapper.selectByPrimaryKey(seccode);
        if (finProduct == null) {
            return DictCodeList.是否_否.getValue();
        }
        else {
            String isRight = finProduct.getIsRight();
            if (StringUtil.contains(isRight, GlobalConstant.COMMA)) {
                isRight = StringUtil.splitToList(GlobalConstant.COMMA, isRight).get(0);
            }
            if (!StringUtil.equals(DictCodeList.是否_是.getValue(), isRight)) {
                return DictCodeList.是否_否.getValue();
            }
        }

        // tradeDate
        if (StringUtil.isBlank((String) param.get("tradeDate"))) {
            param.put("sysDate", pPlatformDate.getSysDate());
        }
        else {
            param.put("sysDate", DateUtil.parse((String) param.get("tradeDate")));
        }

        int dataCount = fxSecdialogQuoteMapper.selectBondHasEexrPlanCount(param);
        if (dataCount > 0) {
            return DictCodeList.是否_是.getValue();
        }

        return DictCodeList.是否_否.getValue();
    }

    /**
     * 1.7计算按钮点击事件
     *
     * @param inputDto 画面参数
     * @return 计算结果
     * @see
     * @since 1.0
     */
    public AjaxResult calculateData(FxSecdialogQuote002Dto inputDto) {
        AjaxResult ajaxResult = new AjaxResult();

        // 校验输入参数
        List<ValidMessage> errorList = this.validCalcAmt(inputDto);
        if (!StringUtil.isEmptyList(errorList)) {
            ajaxResult.setErrorList(errorList);
            return ajaxResult;
        }

        // 计算逻辑
//        this.calcAmt(inputDto);
//        ajaxResult.setData(inputDto);

        return ajaxResult;
    }

    private List<ValidMessage> validCalcAmt(FxSecdialogQuote002Dto dto) {
        List<ValidMessage> validMessageList = new ArrayList<ValidMessage>();
        // 1.交割日
        if (dto.getSettledate() == null) {
            ValidMessage validMessage = new ValidMessage("settledate",
                    getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.settledate")));
            validMessageList.add(validMessage);
        }
        // 2.资产代码
        if (StringUtil.isBlank(dto.getSeccode())) {
            ValidMessage validMessage = new ValidMessage("seccdoe",
                    getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.seccode")));
            validMessageList.add(validMessage);
        }
        // 投资目的
//        if (StringUtil.isBlank(dto.getInvAim())) {
//            ValidMessage validMessage = new ValidMessage("invAim",
//                    getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.invAim")));
//            validMessageList.add(validMessage);
//        }
        // 3.劵面总额
        if (dto.getPrinamt() == null) {
            ValidMessage validMessage = new ValidMessage("prinamt",
                    getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.prinamt")));
            validMessageList.add(validMessage);
        }

        if (StringUtil.isBlank(dto.getCalculateField())) {
            ValidMessage validMessage = new ValidMessage(
                    getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.calculateField")));
            validMessageList.add(validMessage);
        }
        else {
            switch (dto.getCalculateField()) {
                case "cleanprice":
                    if (dto.getCleanprice() == null) {
                        ValidMessage validMessage = new ValidMessage("cleanprice",
                                getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.cleanprice")));
                        validMessageList.add(validMessage);
                    }
                    break;
                case "dpriceamt":
                    if (dto.getDpriceamt() == null) {
                        ValidMessage validMessage = new ValidMessage("dpriceamt",
                                getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.dpriceamt")));
                        validMessageList.add(validMessage);
                    }
                    break;
                case "matureyield":
                    if (dto.getMatureyield() == null) {
                        ValidMessage validMessage = new ValidMessage("matureyield",
                                getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.matureyield")));
                        validMessageList.add(validMessage);
                    }
                    break;
                case "exerciseyield":
                    if (dto.getExerciseyield() == null) {
                        ValidMessage validMessage = new ValidMessage("exerciseyield",
                                getMessage("BUSS_COM_E_0038", getMessage("FxSecdialogQuote002.exerciseyield")));
                        validMessageList.add(validMessage);
                    }
                    // 根据行权收益率反算其他值的情况，若下一行权日净价未维护，则提示错误消息
                    else {
                        try {
                            Map<String, Object> tempParamMap = new HashMap<String, Object>();
                            tempParamMap.put("finprodId", dto.getSeccode());
                            tempParamMap.put("ps", dto.getPs());
                            // d.获取债券基本资料信息
                            SecInfoModelDto secInfoModelDto = restTemplate.postForObject(
                                    ServiceApplication.TRADE_SERVICE_NAME + "/TrdBondInvestTrade/getSecInfoModel", tempParamMap,
                                    SecInfoModelDto.class);

                            if (secInfoModelDto != null && secInfoModelDto.getNextExeDate() != null
                                    && secInfoModelDto.getNextExercisePrice() == null) {
                                ValidMessage validMessage = new ValidMessage("exerYield", getMessage("TRD_E_0015"));
                                validMessageList.add(validMessage);
                            }
                        }
                        catch (Exception e) {
                        }
                    }
                    break;
                default:
                    break;
            }
        }
        return validMessageList;
    }


    /**
     * 获取下一个工作日
     *
     * @param parameters 画面参数
     * @return 下一个工作日
     * @see
     * @since 1.0
     */
    public String getNextWorkDay(Map<String, Object> parameters) {
        if (dateCalService != null) {
            Date tradeDate = DateUtil.parse((String)parameters.get("dealdate"));
            List<String> holiday = dateCalService.getDealCalendarHoliday(tradeDate, DateUtil.addDays(tradeDate, 31),
                    (String)parameters.get("calendarId"));
            Date tradingDay = dateCalService.calculationTradingDay(tradeDate, 1, holiday, "1");
            return DateUtil.format(tradingDay);
        }

        return null;
    }

    /**
     * 生成内部报价编号
     * @return
     */
    public String genNbQuoteId(){
        //quoteSecRepo20200111234 quoteSecRepo+时间+序列 回购对话
        return SEC_REPO_QUOTE_ID + DateUtil.format(new Date(),"yyyyMMdd") + fxSecdialogQuoteMapper.getSequence();
    }

    /**
     * 根据委托代码查询委托信息
     * @param entrustId
     */
    public EtrRepoDto selectByEntrustId(String entrustId){
        return restTemplate.getForObject(ServiceApplication.ENTRUST_SERVICE_NAME
                        + "/EtrRepo/selectByKey?entrustId=" + entrustId,
                EtrRepoDto.class);
    }

    /**
     * 根据seqno查询报价
     */

    public FxSecdialogQuote002Dto selectByPrimaryKey(String seqno){
        FxSecdialogQuote002Dto dto = new FxSecdialogQuote002Dto();
        FxSecdialogQuote entity = fxSecdialogQuoteMapper.selectByPrimaryKey(seqno);
        BeanUtil.copyPropertiesIgnoreNull(dto,entity);
        dto.setPortfolioId(entity.getAccountid());
        return dto;
    }

    /**
     * 根据委托信息回去报价信息
     * @param dto
     * @return
     */
    public FxSecdialogQuote002Dto getRepoQuoteInfo(EtrRepoDto dto){
        FxSecdialogQuote002Dto entity = new FxSecdialogQuote002Dto();
        //组合代码
        entity.setPortfolioId(dto.getPortfolioId());
        entity.setPortfolioName(dto.getPortfolioId());
        //委托代码
        entity.setTrustid(dto.getEntrustId());
        //交易市场
        entity.setJycs(dto.getTradeMarket());
        //结算币种
        entity.setCcy(dto.getCcy());
        //债券币种
//        entity.setBondccy(dto.getCcy());
        //交易方向
        if(StringUtil.isNotEmpty(dto.getPs())){
            if(dto.getPs().equalsIgnoreCase("T01")){
                entity.setPs(FxDictCodeList.交易方向_买入.getItemCode());
            }else if(dto.getPs().equalsIgnoreCase("T02")){
                entity.setPs(FxDictCodeList.交易方向_卖出.getItemCode());
            }
        }
        //交易日历
        entity.setCalendarId(dto.getCalendarId());
        //清算速度
        if(dto.getSettleSpeed()!=null){
            if(dto.getSettleSpeed()==0){
                entity.setSettlespeed(DictCodeList.清算速度_T_0.getValue());
            }else if(dto.getSettleSpeed()==1){
                entity.setSettlespeed(DictCodeList.清算速度_T_1.getValue());
            }
        }
        //委托金额
        entity.setDealamt(dto.getEntrustAmt());
        //资产代码
        entity.setSeccode(dto.getFinprodId());
        //资产简称
        entity.setSecname(dto.getFinprodName());
        //券面总额
        entity.setPrinamt(dto.getEntrustAmt());
        //净价
        entity.setCleanprice(dto.getPrice());
        //到期收益率

        //行权收益率

        //全价




        return entity;
    }

    @Override
    public void doInsert(FxSecdialogQuote002Dto dto, OperationType operationType) {
        if(operationType == OperationType.NEW) {//新增
            if (dto.getOperateType().equalsIgnoreCase("0")) {
                FxSecdialogQuote entity = packEntity(dto);
                entity.setBusinesstype("add");
                entity.setTradestatus("adding");
                entity.setSendstatus("sending");
                //选中净价、全价总额，limittype为0，到期收益率为1，行权收益率为2
                if(dto.getCalculateField().equalsIgnoreCase("prinamt") || dto.getCalculateField().equalsIgnoreCase("dpriceamt")){
                    entity.setLimittype("0");
                }else if(dto.getCalculateField().equalsIgnoreCase("matureyield")){
                    entity.setLimittype("1");
                }else if(dto.getCalculateField().equalsIgnoreCase("exerciseyield")){
                    entity.setLimittype("2");
                }
                fxSecdialogQuoteMapper.insertSelective(entity);
                //历史表留痕
                fxSecdialogQuoteMapper.insertHis(entity);
            }else if(dto.getOperateType().equalsIgnoreCase("3")){//回复报价
                if (dto.getReplyType().equalsIgnoreCase(FxDictCodeList.回复报价_操作类型_成交.getItemCode())) {//成交
                    FxSecdialogQuote entity = packEntity(dto);
                    entity.setBusinesstype("confirm");
                    entity.setTradestatus("dealing");
                    entity.setSendstatus("sending");
                    fxSecdialogQuoteMapper.deleteByPrimaryKey(entity.getSeqno());
                    //重新赋值流水号
                    entity.setSeqno(IDGenerator.generate());
                    fxSecdialogQuoteMapper.insert(entity);
                    fxSecdialogQuoteMapper.insertHis(entity);
                } else if (dto.getReplyType().equalsIgnoreCase(FxDictCodeList.回复报价_操作类型_终止.getItemCode())) {//终止
                    FxSecdialogQuote entity = packEntity(dto);
                    entity.setBusinesstype("refuse");
                    entity.setTradestatus("terminating");
                    entity.setSendstatus("sending");
                    fxSecdialogQuoteMapper.deleteByPrimaryKey(entity.getSeqno());
                    //重新赋值流水号
                    entity.setSeqno(IDGenerator.generate());
                    fxSecdialogQuoteMapper.insert(entity);
                    fxSecdialogQuoteMapper.insertHis(entity);
                }
            }else if(dto.getOperateType().equalsIgnoreCase("4")){//修改报价
                FxSecdialogQuote entity = packEntity(dto);
                entity.setBusinesstype("update");
                entity.setTradestatus("updating");
                entity.setSendstatus("sending");
                fxSecdialogQuoteMapper.deleteByPrimaryKey(entity.getSeqno());
                //重新赋值流水号
                entity.setSeqno(IDGenerator.generate());
                fxSecdialogQuoteMapper.insert(entity);
                fxSecdialogQuoteMapper.insertHis(entity);
            }
        } else if (operationType == OperationType.MODIFY) {//修改
            FxSecdialogQuote entity = packEntity(dto);
            entity.setBusinesstype("add");
            entity.setTradestatus("adding");
            entity.setSendstatus("sending");
            BeanUtil.copyPropertiesIgnoreNull(entity, dto);
            //先删除后新增
            fxSecdialogQuoteMapper.deleteByPrimaryKey(entity.getSeqno());
            //重新赋值流水号
            entity.setSeqno(IDGenerator.generate());
            fxSecdialogQuoteMapper.insertSelective(entity);
            //历史表留痕
            fxSecdialogQuoteMapper.insertHis(entity);
        } else if(operationType == OperationType.REVOKE || operationType == OperationType.DELETE){//撤销或者删除
            fxSecdialogQuoteMapper.revoke(dto.getSeqno());
        } else if(dto.getOperateType().equalsIgnoreCase("7")){  //复制
            FxSecdialogQuote entity = packEntity(dto);
            entity.setBusinesstype("add");
            entity.setTradestatus("adding");
            entity.setSendstatus("sending");
            //重新赋值流水号
            entity.setSeqno(IDGenerator.generate());
            //修改报价,插入修改报价后记录
            fxSecdialogQuoteMapper.insertSelective(entity);
            //"历史表插入修改报价后记录
            fxSecdialogQuoteMapper.insertHis(entity);

        }
    }

    @Override
    public void doUpdate(FxSecdialogQuote002Dto entity) {
        System.out.println("===========doUpdate==============");
    }

    @Override
    public void doDelete(String workflowId) {
        System.out.println("===========doDelete==============");
    }

    @Override
    public void doComplete(FxSecdialogQuote002Dto dto, OperationType operationType) {
            FxSecdialogQuote entity = new FxSecdialogQuote();
            BeanUtil.copyPropertiesIgnoreNull(entity,dto);
        if(operationType == OperationType.NEW) {//新增
            if(dto.getOperateType().equalsIgnoreCase("0")){//新增
                //修改为有效状态
                entity.setEffectflag(FxDictCodeList.有效状态_有效.getItemCode());
                fxSecdialogQuoteMapper.updateByPrimaryKeySelective(entity);
                //修改历史表的有效状态
                fxSecdialogQuoteMapper.updateHis(entity);
                //发送接口
                CallReturn callReturn = clientService.send(entity,"add","addQuote");
                System.out.println(callReturn);
            }else if(dto.getOperateType().equalsIgnoreCase("3")){//回复报价
                 if (dto.getReplyType().equalsIgnoreCase(FxDictCodeList.回复报价_操作类型_成交.getItemCode())) {//成交
                     //修改为有效状态
                     entity.setEffectflag(FxDictCodeList.有效状态_有效.getItemCode());
                     fxSecdialogQuoteMapper.updateByPrimaryKeySelective(entity);
                     //修改历史表的有效状态
                     fxSecdialogQuoteMapper.updateHis(entity);
                     //发送接口
                     CallReturn callReturn = clientService.send(entity,"confirm","confirmQuote");
                     System.out.println(callReturn);
                } else if (dto.getReplyType().equalsIgnoreCase(FxDictCodeList.回复报价_操作类型_终止.getItemCode())) {//终止
                     //修改为有效状态
                     entity.setEffectflag(FxDictCodeList.有效状态_有效.getItemCode());
                     fxSecdialogQuoteMapper.updateByPrimaryKeySelective(entity);
                     //修改历史表的有效状态
                     fxSecdialogQuoteMapper.updateHis(entity);
                     //发送接口
                     CallReturn callReturn = clientService.send(entity,"refuse","refuseQuote");
                     System.out.println(callReturn);
                }
            }else if(dto.getOperateType().equalsIgnoreCase("4")){//修改报价
                //修改为有效状态
                entity.setEffectflag(FxDictCodeList.有效状态_有效.getItemCode());
                fxSecdialogQuoteMapper.updateByPrimaryKeySelective(entity);
                //修改历史表的有效状态
                fxSecdialogQuoteMapper.updateHis(entity);
                //发送接口
                CallReturn callReturn = clientService.send(entity,"update","updateQuote");
                System.out.println(callReturn);
            }else if(dto.getOperateType().equalsIgnoreCase("7")){  //复制
                //修改为有效状态
                entity.setEffectflag(FxDictCodeList.有效状态_有效.getItemCode());
                fxSecdialogQuoteMapper.updateByPrimaryKeySelective(entity);
                //修改历史表的有效状态
                fxSecdialogQuoteMapper.updateHis(entity);
                //发送接口
                CallReturn callReturn = clientService.send(entity,"add","addQuote");
                System.out.println(callReturn);
            }
        } else if (operationType == OperationType.MODIFY) {//修改
            //新增,修改为有效状态
            entity.setEffectflag(FxDictCodeList.有效状态_有效.getItemCode());
            fxSecdialogQuoteMapper.updateByPrimaryKeySelective(entity);
            //修改历史表的有效状态
            fxSecdialogQuoteMapper.updateHis(entity);
            //发送接口
            CallReturn callReturn = clientService.send(entity,"add","addQuote");
            System.out.println(callReturn);
        } else if(operationType == OperationType.REVOKE){//撤销
            fxSecdialogQuoteMapper.revoke(dto.getSeqno());
        }
    }

    public FxSecdialogQuote packEntity(FxSecdialogQuote002Dto dto) {
        FxSecdialogQuote entity = new FxSecdialogQuote();
        if (StringUtil.isNotEmpty(dto)) {
            BeanUtil.copyPropertiesIgnoreNull(entity, dto);
            //产品代码
            entity.setAccountid(dto.getPortfolioId());
            //报价日期
            entity.setQuotedate(dto.getDealdate());
            //业务发生时间
            //    entity.setTransacttime(new Date());
            //报价有效时间
            //entity.setEffecttime();
            //有效状态
            entity.setEffectflag(FxDictCodeList.有效状态_有效.getItemCode());
            //交易状态
            entity.setTradestatus(FxDictCodeList.交易状态_待回复.getItemCode());
            //发送状态
            entity.setSendstatus(FxDictCodeList.发送类型_发送类型1.getItemCode());
            //内部报价编码
            entity.setNbquoteid(genNbQuoteId());
            //新建人员
            entity.setCreatorid(LoginUserUtil.getUserName());
            //新建时间
            entity.setCreatedtime(new Date());
            //修改人
            entity.setLastmodifierid(LoginUserUtil.getUserName());
            //修改时间
            entity.setLastmodifiedtime(new Date());
        }
        return entity;
    }

    public void revoke(FxSecdialogQuote entity){
        entity.setBusinesstype("revoke");
        entity.setTradestatus("revoking");
        entity.setSendstatus("sending");
        fxSecdialogQuoteMapper.deleteByPrimaryKey(entity.getSeqno());
        //重新赋值流水号
        entity.setSeqno(IDGenerator.generate());
        fxSecdialogQuoteMapper.insert(entity);
        fxSecdialogQuoteMapper.insertHis(entity);
        //发送接口
        CallReturn callReturn = clientService.send(entity,entity.getBusinessType(),entity.getBusinessType()+"Quote");

    }
}
