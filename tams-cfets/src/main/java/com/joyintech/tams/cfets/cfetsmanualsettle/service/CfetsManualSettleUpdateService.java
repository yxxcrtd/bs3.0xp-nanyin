package com.joyintech.tams.cfets.cfetsmanualsettle.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.cfets.cfetsmanualsettle.dao.CfetsManualSettleMapper;
import com.joyintech.tams.cfets.cfetsmanualsettle.dto.*;
import com.joyintech.tams.common.base.dao.TrdBankManualSetDetailMapper;
import com.joyintech.tams.common.base.dao.TrdBankManualSettleFlowMapper;
import com.joyintech.tams.common.base.dao.TrdFinProductDealTotalMapper;
import com.joyintech.tams.common.base.dao.TrdProductDealMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.service.MapperDelegateService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import com.joyintech.workflow.engine.constant.OperationType;
import com.joyintech.workflow.engine.service.BaseProcessService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

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

//    @Autowired
//    private MapperDelegateService bussCommonMapperDelegateService;

    @Autowired
    private TrdFinProductDealTotalMapper trdFinProductDealTotalMapper;

    @Autowired
    private TrdProductDealMapper trdProductDealMapper;

    @Autowired
    private TrdBankManualSettleFlowMapper trdBankManualSettleFlowMapper;

    @Autowired
    private TrdBankManualSetDetailMapper trdBankManualSetDetailMapper;

    /**
     * 手工交收修改check
     * @param cfetsRequest
     * @return message
     */
    public String checkManualSettleUpdate(CfetsManualSettleUpdateRequest cfetsRequest, BindingResult br) {
        String returnMsg ="";
        if(cfetsRequest == null || cfetsRequest.getSelectedItem() == null){
            returnMsg = "请选择一条记录进行修改！";
            return returnMsg;
        }
        if(!StringUtil.equals(cfetsRequest.getSelectedItem().getSettleStatus(),DictCodeListExt3.交收状态_未交收.getValue())){
            returnMsg = "请选择一条未交收进行修改！";
            return returnMsg;
        }
        //IF(所选行.业务类型 = 数据字典.平安业务类型.债券中间收息 ||所选行.业务类型 = 数据字典.平安业务类型.债券中间还本
        // ||所选行.业务类型 = 数据字典.平安业务类型.债券到期 ||所选行.业务类型 = 数据字典.平安业务类型.债券类资产行权
        //  ||所选行.业务类型 = 数据字典.平安业务类型.融出债券收息还本（收） )
        if(!(StringUtil.equals(cfetsRequest.getSelectedItem().getTgBusiType(),DictCodeListExt1.平安业务类型_债券中间收息.getValue())
                ||StringUtil.equals(cfetsRequest.getSelectedItem().getTgBusiType(),DictCodeListExt1.平安业务类型_债券中间还本.getValue())
                ||StringUtil.equals(cfetsRequest.getSelectedItem().getTgBusiType(),DictCodeListExt1.平安业务类型_债券到期.getValue())
                ||StringUtil.equals(cfetsRequest.getSelectedItem().getTgBusiType(),DictCodeListExt1.平安业务类型_债券类资产行权.getValue())
                ||StringUtil.equals(cfetsRequest.getSelectedItem().getTgBusiType(),DictCodeListExt3.平安业务类型_融出债券付息还本_收.getValue()))){
            returnMsg = "只允许存续期交易进行交收数据修改！";
        }
        return returnMsg;
    }

    /**
     * 查询待修改记录
     * @param cfetsRequest
     * @return
     */
    public List<PortSettleinfoDto> queryPortSettleinfo(CfetsManualSettleUpdateRequest cfetsRequest) {
        Map<String,Object> pageParam = BeanUtil.object2Map(cfetsRequest.getSelectedItem());
        return cfetsManualSettleMapper.queryPortSettleinfo(pageParam);
    }

    @Override
    public void doComplete(CfetsManualSettleUpdateRequest cfetsManualSettleUpdateRequest, OperationType operationType) {
        try{
            //处理业务数据
            TrdBankManualSettleInfo trdBankManualSettleInfo =new TrdBankManualSettleInfo();
            //交收编号
            trdBankManualSettleInfo.setSettleSeqNo(cfetsManualSettleUpdateRequest.getSelectedItem().getSettleSeqNo() ==null?
                    IDGenerator.generate() : cfetsManualSettleUpdateRequest.getSelectedItem().getSettleSeqNo());
            //原业务编号
            trdBankManualSettleInfo.setTradeId(cfetsManualSettleUpdateRequest.getSelectedItem().getTradeId());
            //交收时间=平台日
            trdBankManualSettleInfo.setSettleDate(platformDateImpl.getSysDate());
            //交收数量
            trdBankManualSettleInfo.setSettleNum(cfetsManualSettleUpdateRequest.getSettleNumT());
            //交收金额
            trdBankManualSettleInfo.setSettleAmt(cfetsManualSettleUpdateRequest.getSettleAmtT());
            //业务类别
            trdBankManualSettleInfo.setBusiType(cfetsManualSettleUpdateRequest.getSelectedItem().getTgBusiType());
            //交收状态
            trdBankManualSettleInfo.setSettleStatus(DictCodeListExt3.交收状态_已交收.getValue());

            trdBankManualSettleInfo.setFinprodId(cfetsManualSettleUpdateRequest.getSelectedItem().getFinprodId());

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
            trdBankManualSettleFlow.setSettleAmt(cfetsManualSettleUpdateRequest.getSettleAmtT());
            //金融产品代码=画面.资产代码
            trdBankManualSettleFlow.setFinprodId(cfetsManualSettleUpdateRequest.getSelectedItem().getFinprodId());
            //金融产品代码=画面.资产简称
            trdBankManualSettleFlow.setFinprodAbbr(cfetsManualSettleUpdateRequest.getSelectedItem().getFinprodAbbr());
            //操作类型=交收
            trdBankManualSettleFlow.setOperType(DictCodeListExt3.交收操作类型_交收.getValue());

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
                trdBankManualSetDetail.setTradeId(cfetsManualSettleUpdateRequest.getSelectedItem().getTradeId());
                //金融产品代码=画面.资产代码
                trdBankManualSetDetail.setFinprodId(cfetsManualSettleUpdateRequest.getSelectedItem().getFinprodId());
                //金融产品简称=画面.资产简称
                trdBankManualSetDetail.setFinprodAbbr(cfetsManualSettleUpdateRequest.getSelectedItem().getFinprodAbbr());
                //组合代码=成交确认明细.投资组合代码
                trdBankManualSetDetail.setPortfolioId(portSettleinfoDto.getPortfolioId());
                //交割数量=成交确认明细.交易份额
                trdBankManualSetDetail.setSettleNum(portSettleinfoDto.getSettleNum());
                //交割金额=成交确认明细.交易金额
                trdBankManualSetDetail.setSettleAmt(portSettleinfoDto.getSettleAmt());
                //业务类别=画面.投管业务类型
                trdBankManualSetDetail.setBusiType(cfetsManualSettleUpdateRequest.getSelectedItem().getTgBusiType());

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

            //更新相关交易表
            //【金融产品交易汇总表】
            //【金融产品交易汇总附属表】
            TrdFinProductDealTotal record =new TrdFinProductDealTotal();
            // 金融产品交易汇总
            record = trdFinProductDealTotalMapper.selectByPrimaryKey(cfetsManualSettleUpdateRequest.getSelectedItem().getTradeId());
            record.setTotleTradeId(cfetsManualSettleUpdateRequest.getSelectedItem().getTradeId());
            record.setIntAmt(cfetsManualSettleUpdateRequest.getSettleAmtT());
            record.setFpriceAmt(record.getPrinAmt().add(record.getIntAmt()));
            record.setTradeAmt(record.getFpriceAmt());
            trdFinProductDealTotalMapper.updateByPrimaryKeySelective(record);

            //【金融产品交易表】TRD_PRODUCT_DEAL
            //【金融产品交易附属表】
            List<TrdProductDealAdd> trdProductDealAdds = cfetsManualSettleMapper.queryTrdProductDealsByTotalTradeId(cfetsManualSettleUpdateRequest.getSelectedItem().getTradeId());
            for(TrdBankManualSetDetail trdBankManualSetDetail : listTrdBnakSetDetails){
                for(TrdProductDealAdd trdProductDealAdd : trdProductDealAdds){
                    if(StringUtil.equals(trdProductDealAdd.getPortfolioId(),trdBankManualSetDetail.getPortfolioId())){
                        TrdProductDeal trdProductDeal = trdProductDealMapper.selectByPrimaryKey(trdProductDealAdd.getTradeId());
                        trdProductDeal.setIntAmt(trdBankManualSetDetail.getSettleAmt());
                        trdProductDeal.setFpriceAmt(trdProductDeal.getPrinAmt().add(trdProductDeal.getIntAmt()));
                        trdProductDeal.setShareAmt(trdProductDeal.getFpriceAmt());
                        trdProductDealMapper.updateByPrimaryKeySelective(trdProductDeal);
                        continue;
                    }
                }
            }
        }
        catch (Exception e) {
            logger.error("交收修改失败",e);
        }
        //头寸 TODO
        //sendPositionMethod(cfetsManualSettleRequest);

    }
}
