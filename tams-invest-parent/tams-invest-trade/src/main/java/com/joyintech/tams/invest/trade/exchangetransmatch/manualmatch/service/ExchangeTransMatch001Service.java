package com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.service;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.export.CommonExportService;
import com.joyintech.framework.export.ExportParam;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.jdbc.enums.DataSourceType;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.JsonUtils;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.dao.SettleTransDataTotalAddMapper;
import com.joyintech.tams.common.base.dao.SettleTransDataTotalMapper;
import com.joyintech.tams.common.base.entity.SettleTransDataTotal;
import com.joyintech.tams.common.base.entity.SettleTransDataTotalAdd;
import com.joyintech.tams.common.base.entity.SettleTransDataTotalMatch;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.transmatch.dto.ExchangeTransMatchCommonDto;
import com.joyintech.tams.invest.trade.cstp.dto.CstpSelectDto;
import com.joyintech.tams.invest.trade.exchangetransmatch.common.service.ExchangeTransMatchCommonService;
import com.joyintech.tams.invest.trade.exchangetransmatch.manualmatch.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class ExchangeTransMatch001Service extends CommonExportService<ExchangeTransMatch004Dto>{

    @Autowired
    private ExchangeTransMatchCommonService exchangeTransMatchCommonService;
    @Autowired
    private SettleTransDataTotalMapper settleTransDataTotalMapper;

    @Autowired
    private SettleTransDataTotalAddMapper settleTransDataTotalAddMapper;


    /**
     * 获取资产代码下拉框
     * @return 下拉框集合
     * @see
     * @since 1.0
     */
    public Page<SelectOptionDto> getFinprodId(Map<String,Object> map){
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/getFinprodId", map, SelectOptionDto.class);
    }

    /**
     * 获取交易编号
     * @param map 查询参数
     * @return 下拉框集合
     * @return
     * @see
     * @since 1.0
     */

    public Page<SelectOptionDto> getBusiCode(Map<String,Object> map){
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/getBusiCode", map, SelectOptionDto.class);
    }

    /**
     * 申请编号
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getApplyNo(Map<String,Object> map){
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/getApplyNo", map, SelectOptionDto.class);
    }

    /**
     * 获取证券账户
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getSecAcctNo(Map<String,Object> map){
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/getSecAcctNo", map, SelectOptionDto.class);
    }

    /**
     * 获取成交编号
     * @param map
     * @return
     */
    public Page<SelectOptionDto> getDealId(Map<String,Object> map){
        return restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/getDealId", map, SelectOptionDto.class);
    }


    /**
     * 分页查询成交数据
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ExchangeTransMatch002Dto> findPage(Map<String,Object> map){
        if(StringUtil.isNotEmpty(map.get("applyNo"))) {
            String applyNo = (String)map.get("applyNo");
            map.put("applyNoList",StringUtil.splitToList(GlobalConstant.COMMA, applyNo));
        }

        Page<ExchangeTransMatch002Dto> page = restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/findPage", map, ExchangeTransMatch002Dto.class);

        return page;
    }

    /**
     * 分页查询委托交易数据
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ExchangeTransMatch003Dto> findEntrustPage(Map<String,Object> map){
        if(StringUtil.isNotEmpty(map.get("portfolioId"))) {
            String portfolioId = (String)map.get("portfolioId");
            map.put("portfolioIdList",StringUtil.splitToList(GlobalConstant.COMMA, portfolioId));
        }

        if(StringUtil.isNotEmpty(map.get("finprodType2"))) {
            String finprodType2 = (String)map.get("finprodType2");
            map.put("finprodType2List",StringUtil.splitToList(GlobalConstant.COMMA, finprodType2));
        }
        Page<ExchangeTransMatch003Dto> page = restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/findPage", map, ExchangeTransMatch003Dto.class);
        return page;
    }

    /**
     * 分页查询
     * @param map 查询参数
     * @return 分页查询结果
     * @see
     * @since 1.0
     */
    public Page<ExchangeTransMatch004Dto> findTransMatchInfoPage(Map<String,Object> map){

        Page<ExchangeTransMatch004Dto> page = restTemplate.postForPage(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/findTransMatchInfoPage", map, ExchangeTransMatch004Dto.class);

        return page;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Page<ExchangeTransMatch004Dto> selectPageData(Map<String, Object> map) {
        return findTransMatchInfoPage(map);
    }

    /**
     * 导出
     */
    @Override
    protected void buildExportParam(ExportParam exportParam) {
        exportParam.config(
                "/com/joyintech/tams/invest/trade/exchangetransmatch/manualmatch/conf/export_conf.xml").dataSource(DataSourceType.MASERT);
    }


    /**
     * 成交匹配
     * @param exchangeTransMatch002Dto
     */
    public void transactionMacth(ExchangeTransMatch002Dto exchangeTransMatch002Dto){
        if(StringUtil.isEmptyList(exchangeTransMatch002Dto.getEntrustList())) {
            return;
        }

        ExchangeTransMatchCommonDto exchangeTransMatchCommonDto = new ExchangeTransMatchCommonDto();
        BeanUtil.copyPropertiesIgnoreNull(exchangeTransMatchCommonDto,exchangeTransMatch002Dto);
        List<String> entrustIdList = new ArrayList<>();
        if(!StringUtil.isEmptyList(exchangeTransMatch002Dto.getEntrustList())) {
            exchangeTransMatch002Dto.getEntrustList().stream().forEach(item ->{
                entrustIdList.add(item.getEntrustId());
            });
        }
        exchangeTransMatchCommonDto.setEntrustIdList(entrustIdList);

        //调用成交匹配共通方法
        exchangeTransMatchCommonService.transactionMacth(exchangeTransMatchCommonDto);

    }

    public String genTransactionRecord(ExchangeTransMatch002Dto dto) {
        String dtoStr = "";
        SettleTransDataTotal settleTransDataTotal = settleTransDataTotalMapper.selectByPrimaryKey(dto.getSeqId());
        SettleTransDataTotalAdd settleTransDataTotalAdd = settleTransDataTotalAddMapper.selectByPrimaryKey(dto.getSeqId());
        Map<String,Object> map=JsonUtils.object2Map(settleTransDataTotal);
        if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券.getValue())) {
            if(StringUtil.equals(dto.getPs(), DictCodeList.交易方向_买入.getValue())
            || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_卖出.getValue())
            || StringUtil.equals(dto.getPs(), DictCodeList.交易方向_行权_回售.getValue())
            || StringUtil.equals(dto.getPs(), DictCodeListExt3.交易方向_配债.getValue())) {

            }
            dtoStr = JsonUtils.object2Json(null);
        }
        else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购.getValue())
        || StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购.getValue())) {
            //期限品种
            map.put("termTypeReback",settleTransDataTotal.getTermType());
            //回购利率
            map.put("rebackPrice",settleTransDataTotal.getIntAmt());
            //回购类型
            map.put("repoType",settleTransDataTotalAdd.getRepoType());
            dtoStr = JsonUtils.object2Json(map);
        }
        else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_基金.getValue())) {
            dtoStr = JsonUtils.object2Json(null);
        }
        else if(StringUtil.equals(dto.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_股票.getValue())) {
            dtoStr = JsonUtils.object2Json(null);
        }
        
        return dtoStr;
    }

    public List<ExchangeTransMatch005Dto> findEntrustList(String seqNo) {
       return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/findEntrustList", seqNo, ExchangeTransMatch005Dto.class);
    }

    public List<ExchangeTransMatch006Dto> findDealList(String seqNo) {
        return restTemplate.postForList(ServiceApplication.TRADE_SERVICE_NAME
                + "/ExchangeTransMatchController/findDealList", seqNo, ExchangeTransMatch006Dto.class);
    }
}
