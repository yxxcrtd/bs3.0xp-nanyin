package com.joyintech.tams.cfets.cfetsmanualsettle.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.business.common.controller.JoyinbusinessCommonController;
import com.joyintech.tams.business.common.dao.JoyinbusinessCommonMapper;
import com.joyintech.tams.business.common.dto.PositionRulePtlData;
import com.joyintech.tams.business.common.service.JoyinbusinessCommonService;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.cfets.cfetsmanualsettle.dao.CfetsManualSettleMapper;
import com.joyintech.tams.cfets.cfetsmanualsettle.dto.*;
import com.joyintech.tams.common.base.dao.TrdBankManualSetDetailMapper;
import com.joyintech.tams.common.base.dao.TrdBankManualSettleFlowMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.*;

/**
 * 手工交收
 * @author sjj
 * @version 1.0 2021年11月12日
 * @see CfetsManualSettleUpdateService
 * @since 1.0
 */
@Service("CfetsManualSettleUpdateService")
public class CfetsManualSettleUpdateService extends BaseService<CfetsManualSettleUpdateRequest> implements BaseProcessService<CfetsManualSettleUpdateRequest>{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(CfetsManualSettleUpdateService.class);

    @Autowired
    private CfetsManualSettleMapper cfetsManualSettleMapper;

    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private TrdBankManualSettleFlowMapper trdBankManualSettleFlowMapper;

    @Autowired
    private TrdBankManualSetDetailMapper trdBankManualSetDetailMapper;

    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    @Autowired
    private JoyinbusinessCommonService joyinbusinessCommonService;

    /**
     * 手工交收修改check
     * @param cfetsRequest
     * @return message
     */
    public String checkManualSettleUpdate(CfetsManualSettleUpdateRequest cfetsRequest, BindingResult br) {
        String returnMsg ="";
        if(cfetsRequest == null){
            returnMsg = "请选择一条记录进行修改！";
            return returnMsg;
        }
        if(!StringUtil.equals(cfetsRequest.getSettleStatus(),DictCodeListExt3.交收状态_未交收.getValue())){
            returnMsg = "请选择一条未交收进行修改！";
            return returnMsg;
        }
        //IF(所选行.业务类型 = 数据字典.平安业务类型.债券中间收息 ||所选行.业务类型 = 数据字典.平安业务类型.债券中间还本
        // ||所选行.业务类型 = 数据字典.平安业务类型.债券到期 ||所选行.业务类型 = 数据字典.平安业务类型.债券类资产行权
        //  ||所选行.业务类型 = 数据字典.平安业务类型.融出债券收息还本（收） )
        if(!(StringUtil.equals(cfetsRequest.getTgBusiType(),DictCodeListExt3.头寸业务类型_债券存续期交易.getValue())
                ||StringUtil.equals(cfetsRequest.getPs(),BussinessDictCodeList.交易方向_行权赎回.getValue()))){
            returnMsg = "只允许存续期交易、债券行权赎回进行交收数据修改！";
        }
        //平台日期和交收日期进行比较
        if(!StringUtil.equals(cfetsRequest.getSettleDate(), DateUtil.format(platformDateImpl.getSysDate()))){
            returnMsg = "只允许平台日等于交收日期进行交收数据修改！";
        }

        WflValidInputParameter wflValidInputParameter  = new WflValidInputParameter();
        wflValidInputParameter.setPageId("CfetsManualSettleUpdate");
        wflValidInputParameter.setBusinessDataPk1(cfetsRequest.getTradeId());
        //查询流程中数据"01"
        wflValidInputParameter.setSeachMode("01");
        List<String> result = cfetsManualSettleMapper.getBusinessData(wflValidInputParameter);
        wflValidInputParameter.setPageId("CfetsManualSettle");
        List<String> resultu = cfetsManualSettleMapper.getBusinessData(wflValidInputParameter);
        if(!StringUtil.isEmptyList(result) || !StringUtil.isEmptyList(resultu)){
            //存在流程中数据
            returnMsg =getMessage("BUSS_COM_E_0023");
        }
        return returnMsg;
    }

    /**
     * 查询待修改记录
     * @param cfetsRequest
     * @return
     */
    public List<PortSettleinfoDto> queryPortSettleinfo(CfetsManualSettleUpdateRequest cfetsRequest) {
        Map<String,Object> pageParam = BeanUtil.object2Map(cfetsRequest);
        return cfetsManualSettleMapper.queryPortSettleinfo(pageParam);
    }

    @Override
    public void doComplete(CfetsManualSettleUpdateRequest cfetsManualSettleUpdateRequest, OperationType operationType) {
        try{
            //处理业务数据
            TrdBankManualSettleInfo trdBankManualSettleInfo =new TrdBankManualSettleInfo();

            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_手工交收);
            String settleSeqNo =autoPeriodCommonService.buildBussinessCode(parameter);
            //交收编号
            trdBankManualSettleInfo.setSettleSeqNo(cfetsManualSettleUpdateRequest.getSettleSeqNo() ==null?
                    settleSeqNo : cfetsManualSettleUpdateRequest.getSettleSeqNo());
            //原业务编号
            trdBankManualSettleInfo.setTradeId(cfetsManualSettleUpdateRequest.getTradeId());
            //交收时间=平台日
            trdBankManualSettleInfo.setSettleDate(platformDateImpl.getSysDate());
            //金融产品代码
            trdBankManualSettleInfo.setFinprodId(cfetsManualSettleUpdateRequest.getFinprodId());
            //交收数量
            trdBankManualSettleInfo.setSettleNum(cfetsManualSettleUpdateRequest.getSettleNumT());
            //交收金额
            if(StringUtil.equals(cfetsManualSettleUpdateRequest.getTgBusiType(),DictCodeListExt3.头寸业务类型_现券交易.getValue())
                    && StringUtil.equals(cfetsManualSettleUpdateRequest.getPs(),DictCodeList.交易方向_买入.getValue())){
            }else{
                trdBankManualSettleInfo.setSettleAmt(cfetsManualSettleUpdateRequest.getSettleAmtT());
            }
            //业务类别
            trdBankManualSettleInfo.setBusiType(cfetsManualSettleUpdateRequest.getTgBusiType());
            //交收状态
            trdBankManualSettleInfo.setSettleStatus(DictCodeListExt3.交收状态_已交收.getValue());

            trdBankManualSettleInfo.setFinprodId(cfetsManualSettleUpdateRequest.getFinprodId());

            trdBankManualSettleInfo.setCreateDept("");

            TrdBankManualSettleFlow trdBankManualSettleFlow = new TrdBankManualSettleFlow();
            //交收流水编号=调用【共通】生成
            trdBankManualSettleFlow.setSeqNo(IDGenerator.generate());
            //交收编号=银行间手工交收信息Entiy.交收编号
            trdBankManualSettleFlow.setSettleSeqNo(trdBankManualSettleInfo.getSettleSeqNo());
            //交收时间=当前时间
            trdBankManualSettleFlow.setSettleTime(new Date());
            //交收数量=画面.交收数量
            trdBankManualSettleFlow.setSettleNum(cfetsManualSettleUpdateRequest.getSettleNumT());
            //交收金额=画面.交收金额
            if(StringUtil.equals(cfetsManualSettleUpdateRequest.getTgBusiType(),DictCodeListExt3.头寸业务类型_现券交易.getValue())
                    && StringUtil.equals(cfetsManualSettleUpdateRequest.getPs(),DictCodeList.交易方向_买入.getValue())){
            }else{
                trdBankManualSettleFlow.setSettleAmt(cfetsManualSettleUpdateRequest.getSettleAmtT());
            }
            //金融产品代码=画面.资产代码
            trdBankManualSettleFlow.setFinprodId(cfetsManualSettleUpdateRequest.getFinprodId());
            //金融产品代码=画面.资产简称
            trdBankManualSettleFlow.setFinprodAbbr(cfetsManualSettleUpdateRequest.getFinprodAbbr());
            //操作类型=交收
            trdBankManualSettleFlow.setOperType(DictCodeListExt3.交收操作类型_交收修改.getValue());

            List<TrdBankManualSetDetail> listTrdBnakSetDetails =new ArrayList<TrdBankManualSetDetail>();

            for(PortSettleinfoDto portSettleinfoDto : cfetsManualSettleUpdateRequest.getPortSettleinfoDtoList()){
                //银行间手工交收明细Entiy
                TrdBankManualSetDetail trdBankManualSetDetail =new TrdBankManualSetDetail();
                //交收流水序号=调用【共通】生成
                trdBankManualSetDetail.setSeqNo(IDGenerator.generate());
                //交收编号=银行间手工交收信息Entiy.交收编号
                trdBankManualSetDetail.setSettleSeqNo(trdBankManualSettleInfo.getSettleSeqNo());
                //交收日期=平台日
                trdBankManualSetDetail.setSettleDate(platformDateImpl.getSysDate());
                //原业务编号=成交确认明细.交易编号
                trdBankManualSetDetail.setTradeId(cfetsManualSettleUpdateRequest.getTradeId());
                //金融产品代码=画面.资产代码
                trdBankManualSetDetail.setFinprodId(cfetsManualSettleUpdateRequest.getFinprodId());
                //金融产品简称=画面.资产简称
                trdBankManualSetDetail.setFinprodAbbr(cfetsManualSettleUpdateRequest.getFinprodAbbr());
                //组合代码=成交确认明细.投资组合代码
                trdBankManualSetDetail.setPortfolioId(portSettleinfoDto.getPortfolioId());
                //交割数量=成交确认明细.交易份额
                trdBankManualSetDetail.setSettleNum(portSettleinfoDto.getSettleNum());
                //交割金额=成交确认明细.交易金额
                trdBankManualSetDetail.setSettleAmt(portSettleinfoDto.getSettleAmt());
                //业务类别=画面.投管业务类型
                trdBankManualSetDetail.setBusiType(cfetsManualSettleUpdateRequest.getTgBusiType());

                //交收状态=数据字典.交收状态.已交收
                trdBankManualSetDetail.setSettleStatus(DictCodeListExt3.交收状态_已交收.getValue());

                listTrdBnakSetDetails.add(trdBankManualSetDetail);
            }

            //存在交收编号，说明是做过交收后又回溯，则做更新动作
            cfetsManualSettleMapper.mergeInfo(trdBankManualSettleInfo);
            //新增交收流水
            trdBankManualSettleFlowMapper.insertSelective(trdBankManualSettleFlow);
            //新增交收明细
            for(TrdBankManualSetDetail trdBankManualSetDetail:listTrdBnakSetDetails){
                trdBankManualSetDetailMapper.insertSelective(trdBankManualSetDetail);
            }

            //头寸
            sendPositionMethod(cfetsManualSettleUpdateRequest,trdBankManualSettleInfo);
//            //更新相关交易表
//            //【金融产品交易汇总表】
//            //【金融产品交易汇总附属表】
//            TrdFinProductDealTotal record =new TrdFinProductDealTotal();
//            // 金融产品交易汇总
//            record = trdFinProductDealTotalMapper.selectByPrimaryKey(cfetsManualSettleUpdateRequest.getTradeId());
//            record.setTotleTradeId(cfetsManualSettleUpdateRequest.getTradeId());
//            record.setIntAmt(cfetsManualSettleUpdateRequest.getSettleAmtT());
//            record.setFpriceAmt(record.getPrinAmt().add(record.getIntAmt()));
//            record.setTradeAmt(record.getFpriceAmt());
//            trdFinProductDealTotalMapper.updateByPrimaryKeySelective(record);
//
//            //【金融产品交易表】TRD_PRODUCT_DEAL
//            //【金融产品交易附属表】
//            List<TrdProductDealAdd> trdProductDealAdds = cfetsManualSettleMapper.queryTrdProductDealsByTotalTradeId(cfetsManualSettleUpdateRequest.getTradeId());
//            for(TrdBankManualSetDetail trdBankManualSetDetail : listTrdBnakSetDetails){
//                for(TrdProductDealAdd trdProductDealAdd : trdProductDealAdds){
//                    if(StringUtil.equals(trdProductDealAdd.getPortfolioId(),trdBankManualSetDetail.getPortfolioId())){
//                        TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(trdProductDealAdd.getTradeId());
//                        trdProductDeal.setIntAmt(trdBankManualSetDetail.getSettleAmt());
//                        trdProductDeal.setFpriceAmt(trdProductDeal.getPrinAmt().add(trdProductDeal.getIntAmt()));
//                        trdProductDeal.setShareAmt(trdProductDeal.getFpriceAmt());
//                        trdProductDealMapper.updateByPrimaryKeySelective(trdProductDeal);
//                        continue;
//                    }
//                }
//            }
        }
        catch (Exception e) {
            logger.error("交收修改失败",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 构建发送头寸信息
     * @param cfetsManualSettleUpdateRequest
     * @param trdBankManualSettleInfo
     * @return
     */
    private void sendPositionMethod(CfetsManualSettleUpdateRequest cfetsManualSettleUpdateRequest,TrdBankManualSettleInfo trdBankManualSettleInfo) {
        List<PositionRulePtlData> ptlList=new ArrayList<>();
        List<PortSettleinfoDto> listTemp = cfetsManualSettleUpdateRequest.getPortSettleinfoDtoList();

        List<CfetsManualSettleDealInfo> listTemp1 = cfetsManualSettleMapper.getDealDetailsByTradeId(cfetsManualSettleUpdateRequest.getTradeId());
        //存放<交易编号,券面总额>
        Map<String,BigDecimal> mapTemp = new HashMap<>();
        for(CfetsManualSettleDealInfo cfetsManualSettleDealInfo : listTemp1){

            mapTemp.put(cfetsManualSettleDealInfo.getTradeId(),cfetsManualSettleDealInfo.getShareAmt());

            String key = cfetsManualSettleDealInfo.getTradeId();

            if(mapTemp.get(key) ==null){
                mapTemp.put(cfetsManualSettleDealInfo.getTradeId(),cfetsManualSettleDealInfo.getShareAmt());
            }
        }
        for(PortSettleinfoDto dto : listTemp){
            PositionRulePtlData positionRulePtlData = new PositionRulePtlData();
            positionRulePtlData.setPortfolioId(dto.getPortfolioId());
            positionRulePtlData.setTradeId(dto.getTradeId());
            positionRulePtlData.setEtrId(dto.getEntrustId());
            if(StringUtil.isNotEmpty(dto.getPortfolioId())){
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("portfolioId", dto.getPortfolioId());
                map.put("acctType", BussinessDictCodeList.资金账户类型_托管账户.getValue());
                map.put("ccy", dto.getCcy());
                String cashAcctId = this.qryPortfolioCashAcctId(map);
                if (StringUtil.isNotEmpty(cashAcctId)) {
                    positionRulePtlData.setCashAcctId(cashAcctId); // 托管户
                }
            }
            if (StringUtil.isNotEmpty(dto.getPortfolioId()) && StringUtil.isNotEmpty(dto.getFinprodId())) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("portfolioId", dto.getPortfolioId());
                map.put("finprodId", dto.getFinprodId());
                String secAcctId = this.qryPortfolioSecAcctId(map);
                if (StringUtil.isNotEmpty(secAcctId)) {
                    positionRulePtlData.setSecAcctId(secAcctId); // 证券账户
                }
            }
            positionRulePtlData.setSecAmount(mapTemp.get(dto.getTradeId())==null?BigDecimal.ZERO : mapTemp.get(dto.getTradeId()));
            positionRulePtlData.setCashAmount(dto.getSettleAmt());
            positionRulePtlData.setInvAim(dto.getInvAim());
            positionRulePtlData.setOwnSecAmount(dto.getOurShareAmt()==null? BigDecimal.ZERO : ArithmeticUtil.multiply(dto.getOurShareAmt(),new BigDecimal(100)));
            positionRulePtlData.setIntegrateSecAmount(dto.getLendinShareAmt()==null? BigDecimal.ZERO : ArithmeticUtil.multiply(dto.getLendinShareAmt(),new BigDecimal(100)));

            ptlList.add(positionRulePtlData);
        }

        CfetsManualSettlePositionDto req = new CfetsManualSettlePositionDto();
        req.setInterfaceType("Bond001");
        //指令或交易标识	ETR:指令,TRD:交易
        req.setModelType(DictCodeListExt1.子系统代码_交易.getValue());
        //用户名	当前操作用户
        req.setUserId(SecurityUtil.getLoginUser().getUserName());
        //汇总交易编号	单交易时传交易编号
        req.setbTotalTradeId(cfetsManualSettleUpdateRequest.getTradeId());
        //汇总交易编号
        req.setTotalTradeId(trdBankManualSettleInfo.getSettleSeqNo());
        //流程状态	流程节点 ，提交 审批通过
        req.setFlowStatus(DictCodeListExt3.流程节点_审批生效.getValue());
        //余额校验	Y：需校验   N：不需校验
        String balanceCheck="N";
        req.setBalanceCheck(balanceCheck);
        // 业务类型：01债券一级投标，02现券交易，03债券行权交易，04债券存续期交易
        req.setBusiType(DictCodeListExt3.头寸业务类型_债券存续期交易.getValue());
        if(StringUtil.isNotEmpty(cfetsManualSettleUpdateRequest.getPs())){
            if(StringUtil.equals(cfetsManualSettleUpdateRequest.getPs(),BussinessDictCodeList.交易方向_行权赎回.getValue())){
                req.setBusiType(DictCodeListExt3.头寸业务类型_债券行权交易.getValue());
            }
        }
        //交易场所
        req.setTradePlace(BussinessDictCodeList.交易场所_银行间.getValue());
        //清算速度
        if(!StringUtil.isEmptyList(listTemp) && StringUtil.isNotEmpty(listTemp.get(0).getSettleSpeed())){
            req.setSettleSpeed(listTemp.get(0).getSettleSpeed());
        }else{
            req.setSettleSpeed("01");
        }
        //业务动作BUSI_ACT 09:交收确认 10:交收失败
        req.setBusiAct("09");
        //交易方向
        req.setPs(cfetsManualSettleUpdateRequest.getPs());
        //新建 修改 关闭
        req.setSystemAct("01");
        if("05".equals(cfetsManualSettleUpdateRequest.getButtonType())) {
            req.setDecisionAct("01");
        }
        req.setCdate(platformDateImpl.getSysDate());
        req.setCcy(cfetsManualSettleUpdateRequest.getCcy());
        req.setFinprodId(cfetsManualSettleUpdateRequest.getFinprodId());
        req.setPtlList(ptlList);
        PositionHttpRequest.send(req);
    }


    /**
     * 查询证券账户
     * @param param
     * @return
     */
    public String qryPortfolioSecAcctId(Map<String, Object> param) {
        return cfetsManualSettleMapper.qryPortfolioSecAcctId(param);
    }

    /**
     * 查询托管户
     * @param param
     * @return
     */
    public String qryPortfolioCashAcctId(Map<String, Object> param) {
        return cfetsManualSettleMapper.qryPortfolioCashAcctId(param);
    }

    /**
     * 查询页面、按钮是否走流程
     * @param param
     * @return
     */
    public String isNeedWorkflow(Map<String, String> param) {
        param.put("pageId", "CfetsManualSettleUpdate");
        param.put("element_id", "CfetsManualSettleUpdate_btn_submit");
        return joyinbusinessCommonService.isNeedWorkflow(param);
    }
}
