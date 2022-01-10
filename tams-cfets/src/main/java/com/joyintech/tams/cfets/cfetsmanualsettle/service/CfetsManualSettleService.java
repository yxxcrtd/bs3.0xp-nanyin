package com.joyintech.tams.cfets.cfetsmanualsettle.service;

import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.security.SecurityUtil;
import com.joyintech.framework.util.*;
import com.joyintech.tams.business.common.dao.CfgReadonlyFormShowMapper;
import com.joyintech.tams.business.common.dto.CfetsOprHisInfoRequest;
import com.joyintech.tams.business.common.dto.PositionRulePtlData;
import com.joyintech.tams.business.common.service.JoyinbusinessCommonService;
import com.joyintech.tams.business.common.service.PositionHttpRequest;
import com.joyintech.tams.cfets.cfetsmanualsettle.dao.CfetsManualSettleMapper;
import com.joyintech.tams.cfets.cfetsmanualsettle.dto.*;
import com.joyintech.tams.cfets.etrib.bond.dto.CfetsEtrIbClosePosEtrRequest;
import com.joyintech.tams.cfets.etrib.bond.dto.CfetsEtrIbClosePositionRequest;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoPosPortfolioCashRequest;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoPosPortfolioSecRequest;
import com.joyintech.tams.cfets.etrib.repo.dto.CfetsEtrIbRepoPositionRequest;
import com.joyintech.tams.cfets.trdquery.dto.CfetsTrdBondInvestTradeDto;
import com.joyintech.tams.cfets.trdquery.service.CfetsTrdBondInvestTrade002Service;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.business.core.dto.WflValidInputParameter;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.autoperiod.common.AutoPeriodCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.impl.BaseEtrInvestInfoServiceImpl;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.dto.common.BussRepoSecDto;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import com.joyintech.tams.invest.common.dto.common.PledgeBondInfoDto;
import com.joyintech.tams.invest.common.riskinfrds.util.EntrustCompleteUtil;
import com.joyintech.tams.invest.entrust.common.aop.EntrustRdsCommonAop;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 手工交收
 * @author sjj
 * @version 1.0 2021年11月12日
 * @see CfetsManualSettleService
 * @since 1.0
 */
@Service("CfetsManualSettleService")
public class CfetsManualSettleService extends BaseService<CfetsManualSettleDto> implements BaseProcessService<CfetsManualSettleDto> {

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(CfetsManualSettleService.class);

    @Autowired
    private CfetsManualSettleMapper cfetsManualSettleMapper;

    @Autowired
    private IPlatformDate platformDateImpl;

    @Autowired
    private CfgReadonlyFormShowMapper cfgReadonlyFormShowMapper;

    @Autowired
    private TrdBankManualSettleFlowMapper trdBankManualSettleFlowMapper;

    @Autowired
    private TrdBankManualSetDetailMapper trdBankManualSetDetailMapper;

    @Autowired
    private AutoPeriodCommonService autoPeriodCommonService;

    @Autowired
    private JoyinbusinessCommonService joyinbusinessCommonService;

    @Autowired
    private TrdTradeFeeTotalMapper trdTradeFeeTotalMapper;

    @Autowired
    private CfetsTrdBondInvestTrade002Service cfetsTrdBondInvestTrade002Service;

    /**
     * 委托指令Mapper
     */
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    /**
     * 投资委托信息Service
     */
    @Autowired
    private BaseEtrInvestInfoServiceImpl etrInvestInfoServiceImpl;

    /**
     * 手工交收交收check
     * @param cfetsRequest
     * @return message
     */
    public AjaxResult checkManualSettleSave(CfetsManualSettleRequest cfetsRequest, BindingResult br) {
        AjaxResult ajaxResult = new AjaxResult();
        List<CfetsManualSettleDto> returnList = new ArrayList<CfetsManualSettleDto>();
        if(StringUtil.equals(cfetsRequest.getSelectedItem().get(0).getButtonType(), DictCodeListExt3.交收操作类型_交收.getValue())){
            for(CfetsManualSettleDto cfetsDto : cfetsRequest.getSelectedItem()) {

            //是否存在流程中的数据
            Boolean isInwflFlag = this.isInwfl(cfetsDto.getTradeId());
            //判断勾选记录交收状态是否为“待交收”，
            //①交易方向=买入 AND 业务类型 = 数据字典.FINPROD_TYPE_TG.债券 AND 清算状态！=“清算成功”
            //②交易方向 IN （到期，提前终止，逾期返售） AND 业务类型 = 数据字典.FINPROD_TYPE_TG.债券质押式正回购 AND 清算状态！=“清算成功”
            //③交易方向=到期 AND 业务类型  IN（ 数据字典.FINPROD_TYPE_TG.债券买断式正回购，债券借贷融入） AND 清算状态！=“清算成功”
            //④交易方向=买入 AND 业务类型 IN（数据字典.FINPROD_TYPE_TG.债券买断式逆回购 ，数据字典.FINPROD_TYPE_TG.债券质押式逆回购 )AND 清算状态！=“清算成功”
            //④交易方向=买入 AND 业务类型 IN（数据字典.FINPROD_TYPE_TG.债券买断式逆回购 ，数据字典.FINPROD_TYPE_TG.债券质押式逆回购 )AND 清算状态！=“清算成功”
            if(!isInwflFlag && StringUtil.equals(cfetsDto.getSettleStatus(), DictCodeListExt3.交收状态_未交收.getValue())
                && StringUtil.equals(cfetsDto.getSettleDate(),DateUtil.format(platformDateImpl.getSysDate()))
                && ((StringUtil.equals(cfetsDto.getPs(), DictCodeList.交易方向_买入.getValue())
                        && StringUtil.equals(cfetsDto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())
                        && StringUtil.equals(cfetsDto.getCleanStatus(), DictCodeListExt3.清算状态_清算成功.getValue()))
                    || ((StringUtil.equals(cfetsDto.getPs(), DictCodeList.交易方向_到期.getValue())
                        || StringUtil.equals(cfetsDto.getPs(), DictCodeList.交易方向_提前终止.getValue())
                        || StringUtil.equals(cfetsDto.getPs(), DictCodeListExt3.交易方向_投资端_逾期返售.getValue()))
                        && StringUtil.equals(cfetsDto.getFinprodType2(), DictCodeListExt1.金融产品类型_债券质押式正回购.getValue())
                        && StringUtil.equals(cfetsDto.getCleanStatus(), DictCodeListExt3.清算状态_清算成功.getValue()))
                    || (StringUtil.equals(cfetsDto.getPs(), DictCodeList.交易方向_到期.getValue())
                        && (StringUtil.equals(cfetsDto.getFinprodType2(), DictCodeListExt1.金融产品类型_债券买断式正回购.getValue())
                        || StringUtil.equals(cfetsDto.getFinprodType2(), DictCodeListExt1.金融产品类型_债券借贷融入.getValue()))
                        && StringUtil.equals(cfetsDto.getCleanStatus(), DictCodeListExt3.清算状态_清算成功.getValue()))
                    || (StringUtil.equals(cfetsDto.getPs(), DictCodeList.交易方向_买入.getValue())
                        && (StringUtil.equals(cfetsDto.getFinprodType2(), DictCodeListExt1.金融产品类型_债券买断式逆回购.getValue())
                        || StringUtil.equals(cfetsDto.getFinprodType2(), DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue()))
                        && StringUtil.equals(cfetsDto.getCleanStatus(), DictCodeListExt3.清算状态_清算成功.getValue()))
                    || (StringUtil.equals(cfetsDto.getTgBusiType(), DictCodeListExt3.头寸业务类型_债券存续期交易.getValue())
                            ||StringUtil.equals(cfetsDto.getTgBusiType(), DictCodeListExt3.头寸业务类型_债券行权交易.getValue())
                                || (StringUtil.equals(cfetsDto.getTgBusiType(), DictCodeListExt3.头寸业务类型_现券交易.getValue())
                                         && !StringUtil.equals(cfetsDto.getPs(), DictCodeList.交易方向_买入.getValue()))
                                || (StringUtil.equals(cfetsDto.getFinprodType2(), DictCodeListExt1.金融产品类型_债券质押式正回购.getValue())
                                && StringUtil.equals(cfetsDto.getPs(), DictCodeList.交易方向_正回购.getValue()))))){
                    returnList.add(cfetsDto);
                }
            }
        }else if(StringUtil.equals(cfetsRequest.getSelectedItem().get(0).getButtonType(), DictCodeListExt3.交收操作类型_交收失败.getValue())){
            for(CfetsManualSettleDto cfetsDto : cfetsRequest.getSelectedItem()) {
                //是否存在流程中的数据
                Boolean isInwflFlag = this.isInwfl(cfetsDto.getTradeId());
                //判断勾选记录交收状态是否为“待交收”
                if(!isInwflFlag && (StringUtil.equals(cfetsDto.getSettleDate(),DateUtil.format(platformDateImpl.getSysDate())))
                    &&(StringUtil.equals(cfetsDto.getSettleStatus(),DictCodeListExt3.交收状态_未交收.getValue())
                        &&(StringUtil.equals(cfetsDto.getTgBusiType(),DictCodeListExt3.头寸业务类型_现券交易.getValue())
                            ||StringUtil.equals(cfetsDto.getTgBusiType(),DictCodeListExt3.头寸业务类型_债券行权交易.getValue())))){
                    returnList.add(cfetsDto);
                }
            }

        }else if(StringUtil.equals(cfetsRequest.getSelectedItem().get(0).getButtonType(), DictCodeListExt3.交收操作类型_回溯.getValue())){
            for(CfetsManualSettleDto cfetsDto : cfetsRequest.getSelectedItem()) {
                //是否存在流程中的数据
                Boolean isInwflFlag = this.isInwfl(cfetsDto.getTradeId());
                //判断勾选记录交收状态是否为“已交收”
                if(!isInwflFlag && StringUtil.equals(cfetsDto.getSettleDate(),DateUtil.format(platformDateImpl.getSysDate()))
                        && StringUtil.equals(cfetsDto.getSettleStatus(),DictCodeListExt3.交收状态_已交收.getValue())){
                    returnList.add(cfetsDto);
                }
            }
        }

        ajaxResult.setData(returnList);

        //查询页面、按钮是否走流程
        Map<String, String> param = new HashMap<String, String>();
        param.put("pageId","CfetsManualSettle");
        param.put("element_id",cfetsRequest.getSelectedItem().get(0).getElementId());

        ajaxResult.setMessage(this.isNeedWorkflow(param));

        return ajaxResult;
    }

    /**
     * 判断选择的记录是否存在流程中
     * @param tradeId
     * @return Boolean
     */
    private Boolean isInwfl(String tradeId) {
        Boolean returnMsg =false;
        WflValidInputParameter wflValidInputParameter  = new WflValidInputParameter();
        wflValidInputParameter.setPageId("CfetsManualSettle");
        wflValidInputParameter.setBusinessDataPk1(tradeId);
        //查询流程中数据"01"
        wflValidInputParameter.setSeachMode("01");
        List<String> result = cfetsManualSettleMapper.getBusinessData(wflValidInputParameter);
        wflValidInputParameter.setPageId("CfetsManualSettleUpdate");
        List<String> resultu = cfetsManualSettleMapper.getBusinessData(wflValidInputParameter);
        if(!StringUtil.isEmptyList(result) || !StringUtil.isEmptyList(resultu)){
            //存在流程中数据
            returnMsg = true;
        }
        return returnMsg;
    }

    /**
     * 构建银行间手工交收信息
     * @param cfetsManualSettleDto
     * @return CfetsManualSettleInsertDto
     */
    private List<CfetsManualSettleInsertDto> handleSettleData(CfetsManualSettleDto cfetsManualSettleDto) {

        List<CfetsManualSettleInsertDto> empList = new ArrayList<CfetsManualSettleInsertDto>();

        //手工交收入库dto
        CfetsManualSettleInsertDto cfetsManualSettleInsertDto = new CfetsManualSettleInsertDto();

        //银行间手工交收信息Entity
        TrdBankManualSettleInfo trdBankManualSettleInfo = this.createTrdBankManualSettleInfo(cfetsManualSettleDto);

        //银行间手工交收流水信息Entity
        TrdBankManualSettleFlow trdBankManualSettleFlow = this.createTrdBankManualSettleFlow(cfetsManualSettleDto,trdBankManualSettleInfo);

        //调用【成交确认明细查询】
        List<TrdBankManualSetDetail> listTrdBnakSetDetails =new ArrayList<TrdBankManualSetDetail>();

        List<CfetsManualSettleDealInfo> listTemp = cfetsManualSettleMapper.getDealDetailsByTradeId(cfetsManualSettleDto.getTradeId());
        for(CfetsManualSettleDealInfo cfetsManualSettleDealInfo : listTemp){
            //银行间手工交收明细Entiy
            TrdBankManualSetDetail trdBankManualSetDetail =new TrdBankManualSetDetail();
            //交收流水序号=调用【共通】生成
            trdBankManualSetDetail.setSeqNo(IDGenerator.generate());
            //交收编号=银行间手工交收信息Entiy.交收编号
            trdBankManualSetDetail.setSettleSeqNo(trdBankManualSettleInfo.getSettleSeqNo());
            //交收日期=平台日
            trdBankManualSetDetail.setSettleDate(platformDateImpl.getSysDate());
            //原业务编号=成交确认明细.交易编号
            trdBankManualSetDetail.setTradeId(cfetsManualSettleDealInfo.getTradeId());
            //金融产品代码=画面.资产代码
            trdBankManualSetDetail.setFinprodId(cfetsManualSettleDto.getFinprodId());
            //金融产品简称=画面.资产简称
            trdBankManualSetDetail.setFinprodAbbr(cfetsManualSettleDto.getFinprodAbbr());
            //组合代码=成交确认明细.投资组合代码
            trdBankManualSetDetail.setPortfolioId(cfetsManualSettleDealInfo.getPortfolioId());
            //交割数量=成交确认明细.交易份额
            trdBankManualSetDetail.setSettleNum(cfetsManualSettleDealInfo.getShareAmt());
            //交割金额=成交确认明细.交易金额
            trdBankManualSetDetail.setSettleAmt(cfetsManualSettleDealInfo.getTradeAmt());
            //业务类别=画面.投管业务类型
            trdBankManualSetDetail.setBusiType(cfetsManualSettleDto.getTgBusiType());

            //交收状态=数据字典.交收状态.已交收
            if(StringUtil.equals(cfetsManualSettleDto.getButtonType(),DictCodeListExt3.交收操作类型_交收.getValue())
                    ||StringUtil.equals(cfetsManualSettleDto.getButtonType(),DictCodeListExt3.交收操作类型_交收修改.getValue())){
                trdBankManualSetDetail.setSettleStatus(DictCodeListExt3.交收状态_已交收.getValue());
            }else if(StringUtil.equals(cfetsManualSettleDto.getButtonType(),DictCodeListExt3.交收操作类型_交收失败.getValue())){
                trdBankManualSetDetail.setSettleStatus(DictCodeListExt3.交收状态_交收失败.getValue());
            }else if(StringUtil.equals(cfetsManualSettleDto.getButtonType(),DictCodeListExt3.交收操作类型_回溯.getValue())){
                trdBankManualSetDetail.setSettleStatus(DictCodeListExt3.交收状态_未交收.getValue());
            }

            listTrdBnakSetDetails.add(trdBankManualSetDetail);
        }

        cfetsManualSettleInsertDto.setTrdBankManualSettleInfo(trdBankManualSettleInfo);
        cfetsManualSettleInsertDto.setTrdBankManualSettleFlow(trdBankManualSettleFlow);
        cfetsManualSettleInsertDto.setListTrdBnakSetDetails(listTrdBnakSetDetails);
        cfetsManualSettleInsertDto.setCcy(cfetsManualSettleDto.getCcy());
        cfetsManualSettleInsertDto.setPs(cfetsManualSettleDto.getPs());
        cfetsManualSettleInsertDto.setTradeId(cfetsManualSettleDto.getTradeId());

        empList.add(cfetsManualSettleInsertDto);

        return empList;
    }

    /**
     * 构建银行间手工交收信息Entity
     * @param cfetsManualSettleDto
     * @param
     * @return
     */
    private TrdBankManualSettleInfo createTrdBankManualSettleInfo(CfetsManualSettleDto cfetsManualSettleDto) {
        TrdBankManualSettleInfo trdBankManualSettleInfo =new TrdBankManualSettleInfo();
        String buttonType = cfetsManualSettleDto.getButtonType();
        //首次交收、交收失败、首次修改（回溯后修改）
        if(StringUtil.isBlank(cfetsManualSettleDto.getSettleSeqNo())){
            BussinessCodeParameter parameter = new BussinessCodeParameter();
            parameter.setCommonBusinessType(DictCodeListExt3.共通业务类型_手工交收);
            String settleSeqNo =autoPeriodCommonService.buildBussinessCode(parameter);
            //交收编号
            trdBankManualSettleInfo.setSettleSeqNo(settleSeqNo);
            //原业务编号
            trdBankManualSettleInfo.setTradeId(cfetsManualSettleDto.getTradeId());
            //金融产品代码
            trdBankManualSettleInfo.setFinprodId(cfetsManualSettleDto.getFinprodId());
            //交收时间=平台日
            trdBankManualSettleInfo.setSettleDate(platformDateImpl.getSysDate());
            //交收数量
            trdBankManualSettleInfo.setSettleNum(cfetsManualSettleDto.getSettleNum());
            //交收金额
            if(StringUtil.equals(cfetsManualSettleDto.getTgBusiType(),DictCodeListExt3.头寸业务类型_现券交易.getValue())
                    && StringUtil.equals(cfetsManualSettleDto.getPs(),DictCodeList.交易方向_买入.getValue())){
            }else{
                trdBankManualSettleInfo.setSettleAmt(cfetsManualSettleDto.getSettleAmt());
            }
            //业务类别
            trdBankManualSettleInfo.setBusiType(cfetsManualSettleDto.getTgBusiType());
            //交收状态
            if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收.getValue())
                    ||StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收修改.getValue())){
                trdBankManualSettleInfo.setSettleStatus(DictCodeListExt3.交收状态_已交收.getValue());
            }else if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收失败.getValue())){
                trdBankManualSettleInfo.setSettleStatus(DictCodeListExt3.交收状态_交收失败.getValue());
                trdBankManualSettleInfo.setRemark(cfetsManualSettleDto.getRemark());
            }
        }else{
            //回溯、再次交收（回溯后交收）、首次修改（回溯后修改）
            trdBankManualSettleInfo.setSettleSeqNo(cfetsManualSettleDto.getSettleSeqNo());
            trdBankManualSettleInfo.setFinprodId(cfetsManualSettleDto.getFinprodId());
            trdBankManualSettleInfo.setSettleAmt(cfetsManualSettleDto.getSettleAmt());
            //交收状态
            trdBankManualSettleInfo.setSettleStatus(DictCodeListExt3.交收状态_未交收.getValue());
            if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收.getValue())
                    ||StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收修改.getValue())){
                trdBankManualSettleInfo.setSettleStatus(DictCodeListExt3.交收状态_已交收.getValue());
            }else if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收失败.getValue())){
                trdBankManualSettleInfo.setSettleStatus(DictCodeListExt3.交收状态_交收失败.getValue());
                trdBankManualSettleInfo.setRemark(cfetsManualSettleDto.getRemark());
            }
        }
        return trdBankManualSettleInfo;
    }

    /**
     * 银行间手工交收流水信息Entity
     * @param cfetsManualSettleDto
     * @param trdBankManualSettleInfo 交收信息表
     * @param
     * @return
     */

    private TrdBankManualSettleFlow createTrdBankManualSettleFlow(CfetsManualSettleDto cfetsManualSettleDto,
                                                                  TrdBankManualSettleInfo trdBankManualSettleInfo) {
        TrdBankManualSettleFlow trdBankManualSettleFlow = new TrdBankManualSettleFlow();
        String buttonType = cfetsManualSettleDto.getButtonType();
        //交收流水编号=调用【共通】生成
        trdBankManualSettleFlow.setSeqNo(IDGenerator.generate());
        //交收编号=银行间手工交收信息Entiy.交收编号
        trdBankManualSettleFlow.setSettleSeqNo(trdBankManualSettleInfo.getSettleSeqNo());
        //交收时间=当前时间
        trdBankManualSettleFlow.setSettleTime(new Date());
        //交收数量=画面.交收数量
        trdBankManualSettleFlow.setSettleNum(cfetsManualSettleDto.getSettleNum());
        //交收金额=画面.交收金额
        if(StringUtil.equals(cfetsManualSettleDto.getTgBusiType(),DictCodeListExt3.头寸业务类型_现券交易.getValue())
                && StringUtil.equals(cfetsManualSettleDto.getPs(),DictCodeList.交易方向_买入.getValue())){
        }else{
            trdBankManualSettleFlow.setSettleAmt(cfetsManualSettleDto.getSettleAmt());
        }
        //金融产品代码=画面.资产代码
        trdBankManualSettleFlow.setFinprodId(cfetsManualSettleDto.getFinprodId());
        //金融产品代码=画面.资产简称
        trdBankManualSettleFlow.setFinprodAbbr(cfetsManualSettleDto.getFinprodAbbr());
        //操作类型=交收
        if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收修改.getValue())){
            buttonType = DictCodeListExt3.交收操作类型_交收.getValue();
        }
        trdBankManualSettleFlow.setOperType(buttonType);

        return trdBankManualSettleFlow;
    }
    /**
     * 构建债券发送头寸信息
     * @param cfetsManualSettleRequest
     * @return
     */
    private void sendPositionMethod(CfetsManualSettleInsertDto cfetsManualSettleRequest,String buttonType) {
        //组装组合金额信息
        List<PositionRulePtlData> ptlList=new ArrayList<>();
        TrdBankManualSettleInfo trdBankManualSettleInfo = cfetsManualSettleRequest.getTrdBankManualSettleInfo();
        if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_回溯.getValue()) ||
             StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收.getValue()) ||
               StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收修改.getValue())||
                StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收失败.getValue())){
            //调用cancel接口 暂时注释，防止后面头寸再改
//            PositionHttpRequest.sendRevoke(trdBankManualSettleInfo.getSettleSeqNo(),DictCodeListExt1.子系统代码_交易.getValue(),"Bond001");
//        }else if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收.getValue())
//                     ||StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收修改.getValue())){
            List<CfetsManualSettleDealInfo> listTemp = cfetsManualSettleMapper.getDealDetailsByTradeId(cfetsManualSettleRequest.getTradeId());

            for(CfetsManualSettleDealInfo dto : listTemp){
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
                positionRulePtlData.setSecAmount(dto.getShareAmt());
                positionRulePtlData.setCashAmount(dto.getTradeAmt());
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
            req.setbTotalTradeId(cfetsManualSettleRequest.getTradeId());
            //汇总交易编号
            req.setTotalTradeId(trdBankManualSettleInfo.getSettleSeqNo());
            //流程状态	流程节点 ，提交 审批通过
            req.setFlowStatus(DictCodeListExt3.流程节点_审批生效.getValue());
            //余额校验	Y：需校验   N：不需校验
            String balanceCheck="N";
            req.setBalanceCheck(balanceCheck);
            // 业务类型：01债券一级投标，02现券交易，03债券行权交易，04债券存续期交易
            req.setBusiType(DictCodeListExt3.头寸业务类型_现券交易.getValue());
            if(StringUtil.isNotEmpty(cfetsManualSettleRequest.getPs())){
                if(StringUtil.equals(cfetsManualSettleRequest.getPs(),BussinessDictCodeList.交易方向_行权回售.getValue())
                    ||StringUtil.equals(cfetsManualSettleRequest.getPs(),BussinessDictCodeList.交易方向_行权赎回.getValue())){
                    req.setBusiType(DictCodeListExt3.头寸业务类型_债券行权交易.getValue());
                }else if(StringUtil.equals(cfetsManualSettleRequest.getPs(),BussinessDictCodeList.交易方向_到期.getValue())
                        ||StringUtil.equals(cfetsManualSettleRequest.getPs(),BussinessDictCodeList.交易方向_还本.getValue())
                        ||StringUtil.equals(cfetsManualSettleRequest.getPs(),BussinessDictCodeList.交易方向_收息.getValue())){
                    req.setBusiType(DictCodeListExt3.头寸业务类型_债券存续期交易.getValue());
                }
            }
            //交易场所
            req.setTradePlace(BussinessDictCodeList.交易场所_银行间.getValue());
            //清算速度
            if(!StringUtil.isEmptyList(listTemp) && StringUtil.isNotEmpty(listTemp.get(0).getSettleSpeed())){
                req.setSettleSpeed(listTemp.get(0).getSettleSpeed().toString());
                if(listTemp.get(0).getSettleSpeed().compareTo(new Long(0))==0){
                    req.setSettleSpeed("01");
                }else if(listTemp.get(0).getSettleSpeed().compareTo(new Long(1))==0){
                    req.setSettleSpeed("02");
                }else if(listTemp.get(0).getSettleSpeed().compareTo(new Long(2))==0){
                    req.setSettleSpeed("03");
                }
            }else{
                req.setSettleSpeed("01");
            }
            //业务动作BUSI_ACT 09:交收确认 10:交收失败
            req.setBusiAct("09");
            if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收失败.getValue())){
                req.setBusiAct("10");
            }else if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_回溯.getValue())){
                req.setBusiAct("20");
            }
            //交易方向
            req.setPs(cfetsManualSettleRequest.getPs());
            //新建 修改 关闭
            req.setSystemAct("01");
            req.setCdate(platformDateImpl.getSysDate());
            req.setCcy(cfetsManualSettleRequest.getCcy());
            req.setFinprodId(trdBankManualSettleInfo.getFinprodId());
            req.setPtlList(ptlList);
            PositionHttpRequest.send(req);
        }
    }
    /**
     * 构建回购发送头寸信息
     * @param cfetsManualSettleRequest
     * @return
     */
    private void sendRepoPositionMethod(CfetsManualSettleInsertDto cfetsManualSettleRequest,String buttonType) {
        //组装组合金额信息
        List<CfetsEtrIbRepoPosPortfolioCashRequest> ptlCashList = new ArrayList<>();
        List<CfetsEtrIbRepoPosPortfolioSecRequest> ptlSecList = new ArrayList<>();

        TrdBankManualSettleInfo trdBankManualSettleInfo = cfetsManualSettleRequest.getTrdBankManualSettleInfo();
        if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_回溯.getValue())){
            //调用cancel接口
            PositionHttpRequest.sendRevoke(trdBankManualSettleInfo.getSettleSeqNo(),DictCodeListExt1.子系统代码_交易.getValue(),"REPO001");
        }else if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收.getValue())
                ||StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收修改.getValue())){
            List<CfetsManualSettleDealInfo> listTemp = cfetsManualSettleMapper.getDealDetailsByTradeId(cfetsManualSettleRequest.getTradeId());

            for(CfetsManualSettleDealInfo dto : listTemp){
                //调用头寸
                CfetsEtrIbRepoPosPortfolioCashRequest posport = new CfetsEtrIbRepoPosPortfolioCashRequest();
                posport.setPortfolioId(dto.getPortfolioId());
                posport.setEtrId(dto.getEntrustId());
                posport.setTradeId(dto.getTradeId());
                if (StringUtil.isNotEmpty(dto.getPortfolioId())) {
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("portfolioId", dto.getPortfolioId());
                    map.put("acctType", BussinessDictCodeList.资金账户类型_托管账户.getValue());
                    map.put("ccy", dto.getCcy());
                    String cashAcctId = this.qryPortfolioCashAcctId(map);
                    if (StringUtil.isNotEmpty(cashAcctId)) {
                        posport.setCashAcctId(cashAcctId); // 托管户
                    }
                }
                posport.setCashAmount(dto.getTradeAmt());
                ptlCashList.add(posport);
                List<PledgeBondInfoDto> pledgeBondList=cfetsManualSettleMapper.getPledgeListByTradeId(dto.getFinprodId());
                if (StringUtil.isNotEmpty(pledgeBondList)) {
                    for (PledgeBondInfoDto regFinPledge : pledgeBondList) {
                        CfetsEtrIbRepoPosPortfolioSecRequest secport = new CfetsEtrIbRepoPosPortfolioSecRequest();
                        secport.setPortfolioId(dto.getPortfolioId());
                        secport.setEtrId(dto.getEntrustId());
                        secport.setTradeId(dto.getTradeId());
                        secport.setFinprodId(regFinPledge.getFinprodId());
                        Map<String, Object> map = new HashMap<String, Object>();
                        map.put("portfolioId", dto.getPortfolioId());
                        map.put("finprodId", regFinPledge.getFinprodId());
                        String secAcctId = this.qryPortfolioSecAcctId(map);
                        if (StringUtil.isNotEmpty(secAcctId)) {
                            secport.setSecAcctId(secAcctId); // 证券账户
                        }
                        secport.setSecAmount(regFinPledge.getPledgePrin());
                        secport.setOwnSecAmount(regFinPledge.getPledgePrin());
                        secport.setIntegrateSecAmount(BigDecimal.ZERO);
                        ptlSecList.add(secport);
                    }
                }
            }

            //调用头寸
            CfetsEtrIbRepoPositionRequest req = new CfetsEtrIbRepoPositionRequest();
            req.setInterfaceType("REPO001");
            req.setUserId(SecurityUtil.getLoginUser().getUserName());
            req.setTotalTradeId(trdBankManualSettleInfo.getSettleSeqNo());
            req.setFlowStatus(DictCodeListExt3.流程节点_审批生效.getValue());
            req.setModelType(DictCodeListExt1.子系统代码_交易.getValue());
            req.setRepoPledgeModel(CacheUtil.getSysParam("repo_pledge_model"));
            //余额校验	Y：需校验   N：不需校验
            String balanceCheck="N";
            req.setBalanceCheck(balanceCheck);
            req.setBusiType(DictCodeListExt3.头寸业务类型_质押式回购首期.getValue());
            req.setTradePlace(BussinessDictCodeList.交易场所_银行间.getValue());
            //清算速度
            if(!StringUtil.isEmptyList(listTemp) && StringUtil.isNotEmpty(listTemp.get(0).getSettleSpeed())){
                req.setSettleSpeed(listTemp.get(0).getSettleSpeed().toString());
                if(listTemp.get(0).getSettleSpeed().compareTo(new Long(0))==0){
                    req.setSettleSpeed("01");
                }else if(listTemp.get(0).getSettleSpeed().compareTo(new Long(1))==0){
                    req.setSettleSpeed("02");
                }else if(listTemp.get(0).getSettleSpeed().compareTo(new Long(2))==0){
                    req.setSettleSpeed("03");
                }
            }else{
                req.setSettleSpeed("01");
            }
            req.setBusiAct(DictCodeListExt3.业务动作_交收确认.getValue());
            //业务动作BUSI_ACT 09:交收确认 10:交收失败
//            if(StringUtil.equals(buttonType,DictCodeListExt3.交收操作类型_交收失败.getValue())){
//                req.setBusiAct("10");
//            }
            String finprodType2=listTemp.get(0).getFinprodType2();
            if(StringUtil.equals(finprodType2,DictCodeListExt1.金融产品类型_债券质押式正回购.getValue())){
                req.setPs(DictCodeList.回购拆借方向_正回购.getValue());
            }else if(StringUtil.equals(finprodType2,DictCodeListExt1.金融产品类型_债券质押式逆回购.getValue())){
                req.setPs(DictCodeList.回购拆借方向_逆回购.getValue());
            }
            req.setSystemAct(DictCodeListExt3.系统动作_新建.getValue());
            req.setCdate(platformDateImpl.getSysDate());
            req.setCcy(cfetsManualSettleRequest.getCcy());
            req.setbTotalTradeId(cfetsManualSettleRequest.getTradeId());
            req.setPtlCashList(ptlCashList);
            req.setPtlSecList(ptlSecList);
            PositionHttpRequest.send(req);
        }
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


    @Override
    public void doComplete(CfetsManualSettleDto entity, OperationType type) {
        try{
            //处理业务数据
            List<CfetsManualSettleInsertDto> insertDtoLists = handleSettleData(entity);

            //插入银行间手工交收相关表
            for(CfetsManualSettleInsertDto cfetsManualSettleInsertDto : insertDtoLists){

                //存在交收编号，说明是做过交收后又回溯，则做更新动作
                cfetsManualSettleMapper.mergeInfo(cfetsManualSettleInsertDto.getTrdBankManualSettleInfo());
                //新增交收流水
                trdBankManualSettleFlowMapper.insertSelective(cfetsManualSettleInsertDto.getTrdBankManualSettleFlow());
                //新增交收明细
                for(TrdBankManualSetDetail trdBankManualSetDetail:cfetsManualSettleInsertDto.getListTrdBnakSetDetails()){
                    trdBankManualSetDetailMapper.insertSelective(trdBankManualSetDetail);
                }
                //发送头寸
                if(StringUtil.equals(entity.getButtonType(), DictCodeListExt3.交收操作类型_交收.getValue())
                        ||StringUtil.equals(entity.getButtonType(),DictCodeListExt3.交收操作类型_回溯.getValue())
                        ||StringUtil.equals(entity.getButtonType(),DictCodeListExt3.交收操作类型_交收失败.getValue())
                        ||StringUtil.equals(entity.getButtonType(),DictCodeListExt3.交收操作类型_交收修改.getValue())){

                    if(StringUtil.equals(entity.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())){
                        //对债券数据发送头寸
                        sendPositionMethod(cfetsManualSettleInsertDto,entity.getButtonType());
                    }else{
                       //对回购数据进行处理
                        sendRepoPositionMethod(cfetsManualSettleInsertDto,entity.getButtonType());
                    }
                }
                //交收失败后，自动撤销原交易、交易登记、行权登记数据，关闭对应指令
                if(StringUtil.equals(entity.getButtonType(),DictCodeListExt3.交收操作类型_交收失败.getValue())){
                    CfetsTrdBondInvestTradeDto revokeEntity = this.getTrdBondInvestTradeDto(entity.getTradeId());
                    String settleRemark ="交收失败";
                    cfetsTrdBondInvestTrade002Service.doCompleteRevoke(revokeEntity,settleRemark,false);
                    //关闭对应指令
                    List<CfetsManualSettleDealInfo> listTemp = cfetsManualSettleMapper.getDealDetailsByTradeId(entity.getTradeId());
                    for(CfetsManualSettleDealInfo dealInfo : listTemp){
                        this.closeDelegateMethod(dealInfo.getEntrustId(),"","交收失败");
                    }
                    try {
                        this.closeDelegate(listTemp,entity.getRemark());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        catch (Exception e) {
            logger.error("交收失败",e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 查询页面、按钮是否走流程
     * @param param
     * @return
     */
    public String isNeedWorkflow(Map<String, String> param) {
        return joyinbusinessCommonService.isNeedWorkflow(param);
    }
    /**
     * 债券投资交易新建画面查看模式查询债券投资交易信息
     *
     * @param tradeId 交易编号
     * @return 债券投资交易信息
     * @see
     * @since 1.0
     */
    public CfetsTrdBondInvestTradeDto getTrdBondInvestTradeDto(String tradeId) {
        Map<String, Object> params = new HashMap<>();
        params.put("tradeId", tradeId);
        params.put("B09", DictCodeListExt1.一次性费用类型_通道费.getValue());
        params.put("Y", DictCodeList.是否_是.getValue());
        CfetsTrdBondInvestTradeDto resultDto = cfetsManualSettleMapper.getTrdBondInvestTradeDto(params);
        if (resultDto != null) {
            if (resultDto.getSettleSpeed() != null) {
                resultDto.setSettleSpeedStr(String.valueOf(resultDto.getSettleSpeed()));
            }
            else if(resultDto.getSettleSpeed() == 1L){
                resultDto.setSettleSpeedStr("1");
            }else if(resultDto.getSettleSpeed() == 2L){
                resultDto.setSettleSpeedStr("2");
            }else{
                resultDto.setSettleSpeedStr(resultDto.getSettleSpeed().toString());
            }
            // 到期收益率
            if (resultDto.getYield() != null) {
                resultDto.setYield(ArithmeticUtil.multiply(resultDto.getYield(), BigDecimal.valueOf(100)));
            }
            // 行权收益率
            if (resultDto.getExerYield() != null) {
                resultDto.setExerYield(ArithmeticUtil.multiply(resultDto.getExerYield(), BigDecimal.valueOf(100)));
            }

            // 交易手续费
            TrdTradeFeeTotalKey trdTradeFeeTotalKey = new TrdTradeFeeTotalKey();
            trdTradeFeeTotalKey.setTotleTradeId(resultDto.getTotleTradeId());
            trdTradeFeeTotalKey.setFeeType(BussinessDictCodeList.费用类型_交易手续费.getValue());
            TrdTradeFeeTotal trdTradeFeeTotal = trdTradeFeeTotalMapper.selectByPrimaryKey(trdTradeFeeTotalKey);
            if (trdTradeFeeTotal != null) {
                resultDto.setProcedureAmt(trdTradeFeeTotal.getFeeAmt());
            }

            // 结算费服务费
            trdTradeFeeTotalKey.setFeeType(BussinessDictCodeList.费用类型_结算手续费.getValue());
            trdTradeFeeTotal = trdTradeFeeTotalMapper.selectByPrimaryKey(trdTradeFeeTotalKey);
            if (trdTradeFeeTotal != null) {
                resultDto.setSettleServiceAmt(trdTradeFeeTotal.getFeeAmt());
            }
        }
        return resultDto;
    }

    /**
     * 委托状态设置为“已关闭”
     * @param entrustId 委托代码
     * @see
     * @since 1.0
     */
    public boolean closeDelegateMethod(String entrustId,String closeWay,String settleRemark){
        EtrEntrust ee = etrEntrustMapper.selectByPrimaryKey(entrustId);
        if (ee != null) {
            // 调用投资委托组件的更新委托状态接口，将委托状态设置为“已关闭”
            String tempEntrustStatu = ee.getEntrustStatus();// 获取该委托原始状态，防止后续更改成关闭
            boolean flag = etrInvestInfoServiceImpl.updateDelegateStatus(entrustId, DictCodeList.委托状态_已关闭.getValue());

            String remark = ee.getRemark() ==null?settleRemark:ee.getRemark().concat(settleRemark);
            EtrEntrust record =new EtrEntrust();
            record.setEntrustId(entrustId);
            int ii= etrEntrustMapper.updateByPrimaryKeySelective(record);
            // 判断关闭委托是否完成
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 委托状态设置为“已关闭”
     * @param listTemp
     * @see
     * @since 1.0
     */
    private void closeDelegate(List<CfetsManualSettleDealInfo> listTemp,String remark) throws Exception {
        List<String> entrustIdList = listTemp.stream().map(
                CfetsManualSettleDealInfo::getEntrustId).collect(Collectors.toList());
        // ZGZGST-2330:委托关闭判断是否有完成额度，分情况处理 fuyongsheng 20210919 edit start
        if(CollectionUtils.isNotEmpty(entrustIdList)){
            Map<String, BigDecimal> etrFinishMap = EntrustCompleteUtil.getEtrFinishAmtList(entrustIdList);
            //委托没有做过交易的，和撤销相同逻辑，删除预占
            List<CfetsManualSettleDealInfo> recallEntrustIdList = new ArrayList<>();
            //委托已有完成额度，做过交易，走关闭完成的逻辑，更新预占
            List<String> closeEntrustIdList = new ArrayList<>();
            for(CfetsManualSettleDealInfo etrDto : listTemp) {
                if(StringUtil.isNotEmpty(etrFinishMap) && StringUtil.isNotEmpty(etrFinishMap.get(etrDto.getEntrustId()))
                        && BigDecimal.ZERO.compareTo(etrFinishMap.get(etrDto.getEntrustId()))<0) {
                    closeEntrustIdList.add(etrDto.getEntrustId());
                }
                else if(entrustIdList.contains(etrDto.getEntrustId())){
                    recallEntrustIdList.add(etrDto);
                }
            }

            //没有做过交易，直接关闭的
            if(!StringUtil.isEmptyList(recallEntrustIdList)) {
                // add by yushilong 批量撤销预占 start
                EntrustRdsCommonAop.doListPreOccupy(recallEntrustIdList, DictCodeList.平安资产类型_债券.getValue(),
                        BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue(), false, null);
                // add by yushilong 批量撤销预占 end
            }

            //已经有委托完成度的
            if(!StringUtil.isEmptyList(closeEntrustIdList)) {
                EntrustCompleteUtil.doComplete(closeEntrustIdList);
            }

        }

//        //发送头寸，批量关闭
//        this.closeDelegatePositionMethod(listTemp);
        //更新操作日志
        this.closeDelegatOprHisMethod(listTemp,remark);
    }

    /**
     * 更新操作日志
     * @param dtolist
     * @param remark
     * @see
     * @since 1.0
     */
    public void closeDelegatOprHisMethod(List<CfetsManualSettleDealInfo> dtolist,String remark){
        if(dtolist != null && dtolist.size()>0) {
            for (CfetsManualSettleDealInfo entity : dtolist) {
                //更新操作记录日志
                CfetsOprHisInfoRequest oprHisReq=new CfetsOprHisInfoRequest();
                oprHisReq.setEntrustId(entity.getEntrustId());
                oprHisReq.setOperType(DictCodeListExt3.操作类型_关闭.getValue());
                oprHisReq.setBusiDes(remark);
                joyinbusinessCommonService.updateCfetsOprHis(oprHisReq);
            }
        }
    }

//    public void closeDelegatePositionMethod(List<CfetsManualSettleDealInfo> dtolist){
//        CfetsEtrIbClosePositionRequest req=new CfetsEtrIbClosePositionRequest();
//        List<CfetsEtrIbClosePosEtrRequest> etrList=new ArrayList<>();
//        for(CfetsManualSettleDealInfo t: dtolist){
//            CfetsEtrIbClosePosEtrRequest closeItem=new CfetsEtrIbClosePosEtrRequest();
//            String busiTypePosition=DictCodeListExt3.头寸业务类型_现券交易.getValue();
//            if(StringUtil.isNotEmpty(t.getPs())
//                    && StringUtil.equals(t.getPs(),BussinessDictCodeList.交易方向_行权回售.getValue())){
//                busiTypePosition=DictCodeListExt3.头寸业务类型_债券行权交易.getValue(); // 业务类型：01债券一级投标，02现券交易，03债券行权交易，04债券存续期交易
//            }
//
//            closeItem.setBusiType(busiTypePosition);
//            closeItem.setPs(t.getPs());
//            closeItem.setCloseId(t.getEntrustId());
//            closeItem.setSystemAct(DictCodeListExt3.系统动作_关闭.getValue());
//            closeItem.setBusiAct(DictCodeListExt3.业务动作_指令手动关闭.getValue());
//            etrList.add(closeItem);
//        }
//        req.setData(etrList);
//        req.setUserId(SecurityUtil.getLoginUser().getUserName());
//        req.setTotalTradeId(IDGenerator.generate());
//        req.setModelType(DictCodeListExt1.子系统代码_委托.getValue());
//        PositionHttpRequest.batchClose(req);
//    }

}
