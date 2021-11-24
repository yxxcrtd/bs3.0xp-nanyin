package com.joyintech.tams.cfets.cfetsmanualsettle.service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.security.LoginUserUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.business.common.dao.CfgReadonlyFormShowMapper;
import com.joyintech.tams.business.common.entity.CfgReadonlyFormShow;
import com.joyintech.tams.cfets.cfetsmanualsettle.dao.CfetsManualSettleMapper;
import com.joyintech.tams.cfets.cfetsmanualsettle.dto.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * 手工交收
 * @author sjj
 * @version 1.0 2021年11月12日
 * @see CfetsManualSettleQueryService
 * @since 1.0
 */
@Service("CfetsManualSettleQueryService")
public class CfetsManualSettleQueryService extends BaseService{

    /**
     * 日志
     */
    private Logger logger = LoggerFactory.getLogger(CfetsManualSettleQueryService.class);

    @Autowired
    private CfetsManualSettleMapper cfetsManualSettleMapper;

    @Autowired
    private CfgReadonlyFormShowMapper cfgReadonlyFormShowMapper;

    /**
     * 手工交收列表分页查询
     * @param dto 入数
     * @return Page<CfetsManualSettleTraderListRespond>
     */
    public Page<CfetsManualSettleTradeListRespond> findPage(CfetsManualSettleTradeListRequest dto) {
        Map<String,Object> pageParam = BeanUtil.object2Map(dto);

        List<String> listTemp = Arrays.asList(dto.getSettleStatus());
        if(!listTemp.contains("all")){
            pageParam.put("settleStatusList", listTemp);
            if(listTemp.contains(DictCodeListExt3.交收状态_未交收.getValue())){
                pageParam.put("isContainDelivery01", true);
            }
        }else{
            pageParam.put("isContainDelivery01", true);
        }

        if(dto.getSettleDate() == null){
            pageParam.put("settleDateStart", DateUtil.format(DateUtil.platDateFromCache()));
            pageParam.put("settleDateEnd", DateUtil.format(DateUtil.platDateFromCache()));
        }else{
            pageParam.put("settleDateStart", DateUtil.parse(dto.getSettleDate()[0]));
            pageParam.put("settleDateEnd", DateUtil.parse(dto.getSettleDate()[1]));
        }

        String portfolioId = (String)pageParam.get("portfolioId");
        if(StringUtil.isNotEmpty(pageParam.get("portfolioId"))){
            List<String> portfolioIdList = StringUtil.splitToList(",",(String)pageParam.get("portfolioId"));
            pageParam.put("portfolioIdList", portfolioIdList);
        }

        String finprodMarketId = (String)pageParam.get("finprodMarketId");
        if(StringUtil.isNotEmpty(pageParam.get("finprodMarketId"))){
            List<String> finprodMarketIdList = StringUtil.splitToList(",",(String)pageParam.get("finprodMarketId"));
            pageParam.put("finprodMarketIdList", finprodMarketIdList);
        }

        //平安业务类型
        pageParam.put("PINAN_BUSINESS_TYPE_09", DictCodeListExt1.平安业务类型_债券类资产买入.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_10", DictCodeListExt1.平安业务类型_债券类资产卖出.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_11", DictCodeListExt1.平安业务类型_债券类资产行权.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_35", DictCodeListExt1.平安业务类型_债券中间收息.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_36", DictCodeListExt1.平安业务类型_债券中间还本.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_37", DictCodeListExt1.平安业务类型_债券到期.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_20", DictCodeListExt1.平安业务类型_正回购.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_51", DictCodeListExt1.平安业务类型_逆回购到期.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_52", DictCodeListExt1.平安业务类型_正回购到期.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_74", DictCodeListExt1.平安业务类型_买断式正回购.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_75", DictCodeListExt1.平安业务类型_买断式逆回购.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_76", DictCodeListExt1.平安业务类型_买断式正回购到期.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_77", DictCodeListExt1.平安业务类型_买断式逆回购到期.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_105", DictCodeListExt2.平安业务类型_债券借贷融入.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_106", DictCodeListExt2.平安业务类型_债券借贷融出.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_107", DictCodeListExt2.平安业务类型_债券借贷融入到期.getValue());
        pageParam.put("PINAN_BUSINESS_TYPE_108", DictCodeListExt2.平安业务类型_债券借贷融出到期.getValue());

        //收付方向
        pageParam.put("feePs02", DictCodeListExt1.收付方向_支出.getValue());
        //结算状态
        pageParam.put("settlementStatus01", DictCodeList.结算状态_已结算.getValue());
        pageParam.put("settlementStatus02", DictCodeList.结算状态_已结算.getValue());
        //指令处理状态
        pageParam.put("commandDealStatus01", DictCodeList.指令处理状态_处理成功.getValue());
        pageParam.put("commandDealStatus02", DictCodeList.指令处理状态_处理失败.getValue());
        //指令处理状态
        pageParam.put("cleanStatus01", DictCodeListExt3.清算状态_清算成功.getValue());
        pageParam.put("cleanStatus02", DictCodeListExt3.清算状态_清算失败.getValue());
        pageParam.put("cleanStatus03", DictCodeListExt3.清算状态_清算中.getValue());

        //交收状态
        pageParam.put("DELIVERY_STATUS_01", DictCodeListExt3.交收状态_未交收.getValue());
        pageParam.put("DELIVERY_STATUS_02", DictCodeListExt3.交收状态_已交收.getValue());
        pageParam.put("DELIVERY_STATUS_NAME01", DictCodeListExt3.交收状态_未交收.getCodeName());

        pageParam.put("TRADE_STATUS_01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("TRADE_STATUS_03", DictCodeListExt1.交易状态_撤销.getValue());

        pageParam.put("isCalcN", BussinessDictCodeList.是否_否.getValue());

        return super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(),
                () -> cfetsManualSettleMapper.findPage(pageParam));
    }

    /**
     * 交收流水列表分页查询
     *
     * @param dto
     * @return Page<CfetsManualSettleFlowRespond>
     */
    public Page<CfetsManualSettleFlowRespond> findManualSettleFlow(CfetsManualSettleCommonRequest dto) {
        CfetsManualSettleDto cfetsManualSettleDto =dto.getSelectedItem().get(0);
        Map<String,Object> pageParam = BeanUtil.object2Map(cfetsManualSettleDto);
        List<CfetsManualSettleFlowRespond> cfetsManualSettleFlowRespondList = new ArrayList<CfetsManualSettleFlowRespond>();
        Page<CfetsManualSettleFlowRespond> pageLimit = new Page<CfetsManualSettleFlowRespond>();
        if(StringUtil.isBlank((String)pageParam.get("settleStatus")) ||StringUtil.equals(DictCodeListExt3.交收状态_未交收.getValue(),(String)pageParam.get("settleStatus"))){
            CfetsManualSettleFlowRespond cfetsManualSettleFlowRespond = new CfetsManualSettleFlowRespond();
            //组装流水信息
            cfetsManualSettleFlowRespond.setSeqNo(1);
            cfetsManualSettleFlowRespond.setSettleDate(cfetsManualSettleDto.getSettleDate());
            cfetsManualSettleFlowRespond.setJiaoShouDate(new Date());
            cfetsManualSettleFlowRespond.setFinprodMarketId(cfetsManualSettleDto.getFinprodMarketId());
            cfetsManualSettleFlowRespond.setFinprodAbbr(cfetsManualSettleDto.getFinprodAbbr());
            cfetsManualSettleFlowRespond.setSettleNum(cfetsManualSettleDto.getSettleNum());
            cfetsManualSettleFlowRespond.setSettleAmt(cfetsManualSettleDto.getSettleAmt());

            cfetsManualSettleFlowRespondList.add(cfetsManualSettleFlowRespond);

            pageLimit.setRoot(cfetsManualSettleFlowRespondList);
        }else{
            pageLimit =  super.queryForPage(dto.getPageMode().getPageNo(), dto.getPageMode().getPageSize(),
                    () -> cfetsManualSettleMapper.findManualSettleFlow(pageParam));
        }

        return pageLimit;
    }

    /**
     * 只读表单设置及数据查询_成交信息查询
     * @param pageCode
     * @param dtCode
     * @param spcdCode
     * @return
     */
    public Map<String, Object> findManualSettleInfo(String pageCode, String dtCode, String spcdCode,CfetsManualSettleCommonRequest dto) {
        Map<String, Object> resultData = new HashMap<>();
        List<CfgReadonlyFormShow> infoForm = cfgReadonlyFormShowMapper.getReadonlyForm(pageCode,dtCode,spcdCode);

        Map<String,Object> pageParam = BeanUtil.object2Map(dto.getSelectedItem().get(0));
        pageParam.put("FINPROD_TYPE_TGF01", DictCodeListExt1.金融产品类型_投管用_债券.getValue());
        pageParam.put("TRADE_STATUS01", DictCodeListExt1.交易状态_初始.getValue());
        pageParam.put("YESNO_N", DictCodeList.是否_否.getValue());
        CfetsManualSettleInfoRespond cfetsManualSettleInfoRespond = cfetsManualSettleMapper.findManualSettleInfo(pageParam);
        //组合分配信息
        pageParam.put("userName", LoginUserUtil.getUserName());
        pageParam.put("CASH_TYPE_1_02", DictCodeListExt1.现金流一级类型_利息.getValue());
        pageParam.put("CASH_TYPE_2_C02", DictCodeListExt1.现金流二级类型_利息.getValue());
        cfetsManualSettleInfoRespond.setPortIntFenPeiInfoDtoList(cfetsManualSettleMapper.findPortIntFenPeiInfoList(pageParam));

        resultData.put(dtCode+"_form",infoForm);
        resultData.put(dtCode+"_data",cfetsManualSettleInfoRespond);

        //金额信息
        List<CfgReadonlyFormShow> infoForm1 = cfgReadonlyFormShowMapper.getReadonlyForm(pageCode,dtCode,spcdCode);
        resultData.put(dtCode+"_form1",infoForm1);
        resultData.put(dtCode+"_data1",cfetsManualSettleInfoRespond);

        //组合
        spcdCode = spcdCode+",P";
        List<CfgReadonlyFormShow> infoForm2 = cfgReadonlyFormShowMapper.getReadonlyForm(pageCode,dtCode,spcdCode);
        resultData.put(dtCode+"_form2",infoForm2);
        resultData.put(dtCode+"_data2",cfetsManualSettleInfoRespond.getPortIntFenPeiInfoDtoList());
        return resultData;
    }

}
