
package com.joyintech.service.entrust.etrstockconnect.service;

import com.joyintech.background.component.order.exchange.common.dto.EtrSinglePortfolioDto;
import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.entrust.common.dao.EntrustCommonPersonSelectMapper;
import com.joyintech.service.entrust.etrexchangetrade.dto.EtrExchangeTrade001Dto;
import com.joyintech.service.entrust.etrstock.dto.EtrStockDto;
import com.joyintech.service.entrust.etrstockconnect.dao.EtrStockConnect002Mapper;
import com.joyintech.service.entrust.etrstockconnect.dto.EtrStockConnectDto;
import com.joyintech.tams.common.base.dao.FinProductAddMapper;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.entrust.BusiAutoEtrDispService;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.etr.EtrCashPositionFlowDto;
import com.joyintech.tams.invest.common.dto.etr.EtrPositionHoldDto;
import com.joyintech.tams.invest.common.dto.etr.EtrSecPositionFlowDto;
import com.joyintech.tams.invest.common.entrust.dto.EntrustCommonDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import com.joyintech.tams.invest.common.trade.service.impl.etrcash.EtrCommonCashPositionDealService;
import com.joyintech.tams.systemtools.interf.IPlatformDate;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *港股通指令
 * @see EtrStockConnect001Service
 * @since 1.0
 */
@Service("backEtrStockConnect002Service")
public class EtrStockConnect002Service extends BaseService {

    @Autowired
    private EtrStockConnect002Mapper etrStockConnect002Mapper;

    /**
     * 委托人员信息
     */
    @Autowired
    private EntrustCommonPersonSelectMapper entrustCommonPersonSelectMapper;

    /**
     * 平台日获取接口
     */
    @Autowired(required=false)
    private IPlatformDate iPlatformDate;

    /**
     * 附件信息共通Service
     */
    @Autowired(required = false)
    private MstFileCommonService mstFileCommonService;

    /**
     * 投资委托基础组件
     */
    @Autowired
    private BaseEtrInvestInfoService baseEtrInvestInfoService;

    /**
     * 委托自动分发
     */
    @Autowired(required=false)
    private BusiAutoEtrDispService busiAutoEtrDispService;
    /**
     * 委托指令共同
     */
    @Autowired(required=false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;

    @Autowired(required=false)
    FinProductAddMapper finProductAddMapper;

    /**
     *@title: doInsert
     *@description: 流程启动时为附件绑定工作流代码
     *@throws:
     */
    public void doInsert(EtrStockConnectDto dto, String operateType) {

        // 上传文件修改更新
        MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
        mstFileInputParameter.setBussinessId(dto.getWorkflowId());
        mstFileInputParameter.setFileList(dto.getFileArray());
        if (mstFileCommonService != null) {
            mstFileCommonService.updateBusinessId(mstFileInputParameter);
        }
    }

    /**
     * 工作流完成方法
     */
    public void doComplete(EtrStockConnectDto dto, String operationType) throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        entrustCommonDto.setEtrEntrust(etrEntrust);
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);
        if (StringUtil.equals(operationType, "NEW")) {
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            //dto.setIsOutTrade(judgeCounterType(dto.getEtrInvestPersonInfo().getCounterId()));
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto);
            BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto.getEtrInvestPersonInfo());
            if(iPlatformDate != null){
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            BeanUtil.copyPropertiesIgnoreNull(etrEntrustAdd, dto);
            //港股通市场
            etrEntrustAdd.setHkMarket(dto.getHkseMarket());
            etrEntrustAdd.setEntrustAmtTarget(dto.getEntrustAmtHk());
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {

                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,
                        dto.getEtrInvestPersonInfo().getTrader());
                for (String trader : traderList) {
                    EtrDistribute etrDistribute = new EtrDistribute();
                    // 设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    // 设置指定交易员指定交易员 = 指定交易员
                    etrDistribute.setTrader(trader);
                    // 设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                //entity.setEtrDistributeList(etrDistributeList);
            }
            //自动分发
            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    entity.setEtrDistributeList(etrDistributeList);
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if(busiAutoEtrDispService.checkEntrustTime(etrEntrust)){
                        //自动分发
                        entity.setEtrDistributeList(busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                    }
                }
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            baseEtrInvestInfoService.newDelegate(entity);
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }else {
            dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
            //dto.setIsOutTrade(judgeCounterType(dto.getEtrInvestPersonInfo().getCounterId()));
            dto.setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto);
            BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto.getEtrInvestPersonInfo());
            if(iPlatformDate != null){
                etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
            }
            BeanUtil.copyPropertiesIgnoreNull(etrEntrustAdd, dto);
            //港股通市场
            etrEntrustAdd.setHkMarket(dto.getHkseMarket());
            etrEntrustAdd.setEntrustAmtTarget(dto.getEntrustAmtHk());
            etrEntrustAdd.setVdate(null);
            etrEntrustAdd.setMdate(null);
            etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());
            List<EtrDistribute> etrDistributeList = new ArrayList<>();
            if (StringUtil.isNotEmpty(dto.getEtrInvestPersonInfo().getTrader())) {

                List<String> traderList = StringUtil.splitToList(GlobalConstant.COMMA,
                        dto.getEtrInvestPersonInfo().getTrader());
                for (String trader : traderList) {
                    EtrDistribute etrDistribute = new EtrDistribute();
                    // 设置接收状态
                    etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    // 设置指定交易员指定交易员 = 指定交易员
                    etrDistribute.setTrader(trader);
                    // 设置委托代码
                    etrDistribute.setEntrustId(dto.getEntrustId());
                    //
                    etrDistributeList.add(etrDistribute);
                }
                //entity.setEtrDistributeList(etrDistributeList);
            }
            //自动分发
            if(busiAutoEtrDispService != null){
                //检测是否设置交易员
                if(!busiAutoEtrDispService.checkHaveTrader(etrDistributeList)){
                    //跳出自动分发
                    entity.setEtrDistributeList(etrDistributeList);
                }
                else{
                    //查询委托指令下达日期，如果在自动分发区间内则改指令需自动分发
                    if(busiAutoEtrDispService.checkEntrustTime(etrEntrust)){
                        //自动分发
                        entity.setEtrDistributeList(busiAutoEtrDispService.selectTrader(etrEntrust, etrEntrustAdd));
                    }
                }
            }
            entity.setEtrEntrust(etrEntrust);
            entity.setEtrEntrustAdd(etrEntrustAdd);
            baseEtrInvestInfoService.updateDelegate(entity);
            // 文件
            MstFileInputParameter mstFileInputParameter = new MstFileInputParameter();
            mstFileInputParameter.setFileList(dto.getFileArray());
            mstFileInputParameter.setBussinessId(dto.getEntrustId());
            if (mstFileCommonService != null) {
                mstFileCommonService.updateBusinessId(mstFileInputParameter);
            }
        }
        //流程中预占
/*        if(baseEtrCashPositionCommService!=null){
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
        }*/
    }

    /**
     * 插入和修改时，指令持仓流水 、现金流水处理(场内指令)
     */
    public void doInsetOrUpdateForEtrPositionHold(EtrStockConnectDto dto){
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        EntrustCommonDto entrustCommonDto = new EntrustCommonDto();
        entrustCommonDto.setEtrEntrust(etrEntrust);
        entrustCommonDto.setEtrEntrustAdd(etrEntrustAdd);

        dto.setFinprodType2(DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto);
        BeanUtil.copyPropertiesIgnoreNull(etrEntrust, dto.getEtrInvestPersonInfo());
        if(iPlatformDate != null){
            etrEntrust.setEntrustDate(iPlatformDate.getSysDate());
        }
        BeanUtil.copyPropertiesIgnoreNull(etrEntrustAdd, dto);
        //港股通市场
        etrEntrustAdd.setHkMarket(dto.getHkseMarket());
        etrEntrustAdd.setEntrustAmtTarget(dto.getEntrustAmtHk());
        etrEntrustAdd.setVdate(null);
        etrEntrustAdd.setMdate(null);
        etrEntrustAdd.setAssetType(DictCodeListExt2.平安资产类型_股票.getValue());


        //前台委托数量和委托金额都不能为空
        /* String 股票代码 = etrEntrust.getFinprodId();
        BigDecimal 最新收盘价 = EtrCommonCashPositionDealService.getClosePrice(股票代码,
                entrustCommonDto.getEtrEntrust().getEntrustDate());
        BigDecimal 汇率 = dto.getReferenceRate();
        if (汇率 == null) {
            汇率 = BigDecimal.ZERO;
        }
        //买入时，接入金额
        String ps = etrEntrust.getPs();
        if (DictCodeList.交易方向_买入.getValue().equals(ps)||DictCodeListExt2.交易方向_新股中签.getValue().equals(ps)||DictCodeListExt2.交易方向_增发.getValue().equals(ps)||DictCodeListExt2.交易方向_配股.getValue().equals(ps)) {
            if(etrEntrust.getEntrustAmt() == null){
                etrEntrust.setEntrustAmt(etrEntrust.getEntrustNum().multiply(最新收盘价).multiply(汇率));
            }
        }
        //卖出时，接入数量
        else if(DictCodeList.交易方向_卖出.getValue().equals(ps) || DictCodeListExt3.交易方向_卖碎股.getValue().equals(ps)){
            if(etrEntrust.getEntrustNum() == null){
                etrEntrust.setEntrustNum(etrEntrust.getEntrustAmt().divide(汇率).divide(最新收盘价));
            }
        }*/
        //流程中预占
       if(baseEtrCashPositionCommService!=null){
            baseEtrCashPositionCommService.entrustStartExecute(entrustCommonDto);
       }
    }


    /**
     * 撤回委托
     *
     * @param dto 实体类
     * @return boolean
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     * @see
     * @since 1.0
     */
    public boolean recallDelegate(EtrStockConnectDto dto)
            throws IllegalAccessException, InvocationTargetException {
        BaseEtrInvestInfoDto entity = new BaseEtrInvestInfoDto();
        EtrEntrust etrEntrust = new EtrEntrust();
        EtrEntrustAdd etrEntrustAdd = new EtrEntrustAdd();
        BeanUtil.copyProperties(etrEntrust, dto);
        BeanUtil.copyProperties(etrEntrustAdd, dto);
        List<EtrDistribute> etrDistributeList = new ArrayList<>();
        EtrDistribute etrDistribute = new EtrDistribute();
        BeanUtil.copyProperties(etrDistribute, dto);
        etrDistributeList.add(etrDistribute);
        entity.setEtrEntrust(etrEntrust);
        entity.setEtrEntrustAdd(etrEntrustAdd);
        entity.setEtrDistributeList(etrDistributeList);
        return baseEtrInvestInfoService.recallDelegate(entity);
    }

    /**
     * 关闭委托
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    public boolean closeDelegate(String entrustId, String closeWay) {
        return baseEtrInvestInfoService.closeDelegate(entrustId, closeWay);
    }

    /**
     *@title: getfinprodIdForAdd
     *@description: 获取新建界面的资产代码联想下拉框
     */
    public Page<SelectOptionDto> getfinprodIdForAdd(int page, int length, Map<String, String> map) {
        map.put("TRADE_PLACE_13", DictCodeListExt1.交易场所_香港联合交易所.getValue());
        map.put("FINPROD_TYPE_TG_F10", DictCodeListExt1.金融产品类型_投管用_股票.getValue());
        return this.queryForPageForSelectReturn(page, length,()-> etrStockConnect002Mapper.getfinprodIdForAdd(map));
    }

    /**
     * 查询组合证券账户
     */
    public StlSecAcctInfo getSecManageAcctId(Map<String,Object> param){
        return etrStockConnect002Mapper.getSecManageAcctId(param);
    }

    /**
     * 组合证券账户查询
     */
    public StlSecAcctInfo selectStlSecAcctInfo(Map<String,Object> param){
        return etrStockConnect002Mapper.selectStlSecAcctInfo(param);
    }

    /**
     * 配售代码
     */
    public String getSaleCode(Map<String, Object> map) {
        map.put("othType","06");
        return etrStockConnect002Mapper.getSaleCode(map);
    }

    /**
     * 最小买卖单位
     */
    public EtrStockConnectDto getFinProdInfo(Map<String, Object> map) {
        return etrStockConnect002Mapper.getFinProdInfo(map);
    }

    /**
     * 参考汇率
     */
    public  EtrStockConnectDto getReferenceRate(Map<String, Object> param) {
        if(StringUtil.equals(param.get("hkseMarket"),DictCodeListExt2.是否沪港通或深港通_沪港通.getValue())){
            param.put("type_in","HKS03");
            param.put("type_out","HKS04");
        }else {
            param.put("type_in","HKS07");
            param.put("type_out","HKS08");
        }
        param.put("ccy",DictCodeList.币种_港元_HKD.getValue());
        return etrStockConnect002Mapper.getReferenceRate(param);
    }


}
