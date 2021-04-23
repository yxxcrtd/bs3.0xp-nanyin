/*
 * 文件名：EntrustCompleteUtil.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：zhangrui1
 * 修改时间：2020年9月24日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */
 
package com.joyintech.tams.invest.common.riskinfrds.util;

import com.joyintech.framework.base.BaseService;
import com.joyintech.framework.util.CacheUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.SpringContextUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.CommonUtil;
import com.joyintech.tams.invest.common.client.ClientData;
import com.joyintech.tams.invest.common.client.ClientUtil;
import com.joyintech.tams.invest.common.component.base.entrust.impl.dao.BaseEtrInvestInfoMapper;
import com.joyintech.tams.invest.common.constant.BussinessDictCodeList;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.constantrds.BussinessConstantRds;
import com.joyintech.tams.invest.common.constantrds.BussinessDictCodeListRds;
import com.joyintech.tams.invest.common.riskinfrds.util.service.EntrustCompleteService;
import com.joyintech.tams.invest.common.trade.AllTradeCommonUtil;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 委托完成释放剩余额度Util
 * 
 * @author zhangrui1
 * @version 1.0 2020年9月24日
 * @see EntrustCompleteUtil
 * @since 1.0
 */
@Service
public class EntrustCompleteUtil extends BaseService<EtrEntrust> {

    private EntrustCompleteUtil() {
        // private this construct
    }

    private static final String SYS_CODE = "riskinf_system_code";

    private static final String SYSTEMCODE = "TAMS-INVEST";
    /*************************************************************/
    /**                        *接口字段区域 *                        */
    /*************************************************************/
    public static final String INF_EVENTDATE = "eventDate";
    public static final String INF_EVENTID = "eventId";
    public static final String INF_PS = "ps";
    public static final String INF_COUNTERID = "counterId";
    public static final String INF_RSK_LIST = "RSK_LIST";
    public static final String INF_REVOKEFLG = "revokeFlg";
    public static final String INF_ISINNERTRD = "isInnerTrd";

    /**
     * 委托完成标识
     */
    public static final String ETRCOMPLETEFLG = "etrCompleteFlg";

    /*************************************************************/
    /**                      *△接口字段区域△*                      */
    /*************************************************************/

    public static final String RISK_INF_URL = "/interf/execute";

    private static final BaseEtrInvestInfoMapper getBaseEtrInvestInfoMapper() {
        return SpringContextUtil.getApplicationContext().getBean(BaseEtrInvestInfoMapper.class);
    }

    private static final EntrustCompleteService getService() {
        return SpringContextUtil.getApplicationContext().getBean(EntrustCompleteService.class);
    }

    /**
     * 取得委托详细信息
     * 
     * @param entrustIdList 委托代码列表
     * @return 委托详细信息
     */
    private static final List<EtrEntrust> getEntrustList(List<String> entrustIdList) {
        Map<String, Object> param = new HashMap<>();
        param.put("entrustIdList", entrustIdList);
        return getBaseEtrInvestInfoMapper().selectByPrimaryKeyPtl(param);
    }

    /**
     * 取得委托附属表详细信息
     *
     * @param entrustId 委托代码
     * @return 委托详细信息
     */
    private static final EtrEntrustAdd getEntrustAdd(String entrustId) {
        Map<String, Object> param = new HashMap<>();
        param.put("entrustId", entrustId);
        return getBaseEtrInvestInfoMapper().selectAddByPrimaryKeyPtl(param);
    }

    /**
     * 委托完成进行释放预占剩余额度
     * 
     * @param entrustIdList 委托编号列表
     * @return 释放结果
     */
    public static final ClientData doComplete(List<String> entrustIdList) {
        return doComplete(entrustIdList, null);
    }

    /**
     * 委托完成进行释放预占剩余额度
     * 
     * @param entrustIdList 委托编号列表
     * @param pSet 不需要释放剩余预占交易反向列表
     * @return 释放结果
     */
    public static final ClientData doComplete(List<String> entrustIdList,
                                              Collection<String> pSet) {
        return doSend(entrustIdList, pSet,
            data -> data.put(ETRCOMPLETEFLG, BussinessDictCodeListRds.是否_是.getValue()));
    }

    /**
     * 判断是否需要释放剩余额度
     * 
     * @param dto 指令信息
     * @param pSet 不需要释放剩余额度的交易方向
     * @return true : 需要释放剩余额度
     */
    public static final boolean isNeedRelease(EtrEntrust dto, Collection<String> pSet) {
        return null != dto && !pSet.contains(dto.getPs());
    }

    /**
     * 释放/恢复预占剩余额度
     * 
     * @param entrustIdList 委托编号列表
     * @param consumer 分支处理
     * @return 结果
     */
    @SuppressWarnings("unchecked")
    private static final ClientData doSend(List<String> entrustIdList, Collection<String> pSet,
                                           Consumer<Map<String, Object>> consumer) {
        if (CollectionUtils.isEmpty(entrustIdList)) {
            return null;
        }
        // 根据委托编号取出委托信息
        List<EtrEntrust> entrustList = getEntrustList(entrustIdList);
        // 过滤出需要释放剩余预占的交易
        if (!CollectionUtils.isEmpty(pSet)) {
            entrustList = entrustList.stream().filter(dto -> isNeedRelease(dto, pSet)).collect(
                Collectors.toList());
        }
        if (CollectionUtils.isEmpty(entrustList)) {
            return null;
        }
        // 取得内部交易对手
        Map<String, PtlPortfolio> innerIds = AllTradeCommonUtil.selectPortfolioIds(
            entrustList.stream().filter(
                entrust -> entrust != null && StringUtil.isNotEmpty(entrust.getCounterId())).map(
                    EtrEntrust::getCounterId).collect(
                Collectors.toList()));
        // 拼接报文
        ClientData data = convert(entrustList,innerIds);
        // 释放/恢复分支处理
        if (consumer != null) {
            ((List<Map<String, Object>>)data.getBodyField(INF_RSK_LIST)).forEach(consumer);
        }
        // 发送报文
        return ClientUtil.sendRequestInf(
            ServiceApplication.RISK_INF_SERVICE_NAME + RISK_INF_URL,
            data.convertToMap(), getService().getJoyinRestTemplate());
    }

    /**
     * 撤销交易时恢复预占剩余额度
     * 
     * @param entrustIdList 委托编号列表
     * @param pSet 不需要释放剩余预占交易反向列表
     * @return 处理结果
     */
    public static final ClientData doCancel(List<String> entrustIdList, Collection<String> pSet) {
        return doSend(entrustIdList, pSet,
            data -> data.put(ETRCOMPLETEFLG, BussinessDictCodeListRds.是否_否.getValue()));
    }

    /**
     * 撤销交易时恢复预占剩余额度
     * 
     * @param entrustIdList 委托编号列表
     * @return 处理结果
     */
    public static final ClientData doCancel(List<String> entrustIdList) {
        return doCancel(entrustIdList, null);
    }

    /**
     * 拼接报文
     * 
     * @param entrustList 委托详细信息
     * @param innerIds 内部交易对手列表
     * @return 报文
     */
    protected static final ClientData convert(List<EtrEntrust> entrustList,
                                              Map<String, PtlPortfolio> innerIds) {
        ClientData data = new ClientData();
        Map<String, Object> head = ClientUtil.head();
        // 预占批量释放
        head.put(BussinessConstantRds.STDPROCODE,
            BussinessDictCodeListRds.接口系统ID_预占释放批量接口.getValue());
        // 业务归属系统代码
        String sysCode = CacheUtil.getSysParam(SYS_CODE);
        head.put(BussinessConstantRds.STD400BSYS, StringUtil.defaultString(sysCode, SYSTEMCODE));
        // 发起方系统代码
        head.put(BussinessConstantRds.STD400AQID, StringUtil.defaultString(sysCode, SYSTEMCODE));
        // 报文流水号
        String seqNo = (String)head.get(BussinessConstantRds.STDTERMTRC);
        int length = seqNo.length();
        head.put(BussinessConstantRds.STDTERMTRC,
            sysCode + StringUtil.substring(seqNo, length - 26));
        Map<String, Object> body = new HashMap<>();
        List<Map<String, Object>> list = new ArrayList<>();
        // 买入方向
        String ps = BussinessDictCodeListRds.交易方向_买入.getValue();
        // 平台日期
        String sysDate = DateUtil.format(DateUtil.platDateFromCache(),
            DateUtil.DEFAULT_DATE_FORMAT_EN);
        entrustList.forEach(entrust -> {
            Map<String, Object> finDto = new HashMap<>();
            // 事件编号
            finDto.put(INF_EVENTID, entrust.getEntrustId());
            // 交易方向
            finDto.put(INF_PS, entrust.getPs());
            // 事件日期
            finDto.put(INF_EVENTDATE, sysDate);
            // 撤销标识
            finDto.put(INF_REVOKEFLG, BussinessDictCodeListRds.是否_是.getValue());
            // 是否内部交易
            boolean isInnerId = innerIds.containsKey(entrust.getCounterId());
            finDto.put(INF_ISINNERTRD,
                isInnerId ? BussinessDictCodeListRds.是否_是.getValue() : BussinessDictCodeListRds.是否_否.getValue());
            list.add(finDto);
            // 生成反方向指令（内部交易）
            if (isInnerId) {
                // 事件编号=委托编号+交易方向
                finDto.put(INF_EVENTID, entrust.getEntrustId() + entrust.getPs());
                finDto = new HashMap<>();
                // 事件编号=委托编号+交易方向
                finDto.put(INF_EVENTID, entrust.getEntrustId() + ps);
                // 事件日期
                finDto.put(INF_EVENTDATE, sysDate);
                // 交易方向
                finDto.put(INF_PS, ps);
                // 撤销标识
                finDto.put(INF_REVOKEFLG, BussinessDictCodeListRds.是否_是.getValue());
                // 是否内部交易
                finDto.put(INF_ISINNERTRD, BussinessDictCodeListRds.是否_是.getValue());
                list.add(finDto);
            }
            // 转换申请生成买入方向指令
            else if (CommonUtil.isType(entrust.getPs(), DictCodeListExt2.交易方向_转换申请)
                     || CommonUtil.isType(entrust.getPs(), DictCodeListExt1.交易方向_债转股)) {
                // 改变交易方向
                finDto.put(INF_PS, BussinessDictCodeList.交易方向_卖出.getValue());
                // 改变事件编号=委托编号+交易方向
                finDto.put(INF_EVENTID,
                    entrust.getEntrustId() + BussinessDictCodeList.交易方向_卖出.getValue());
                finDto = new HashMap<>();
                // 事件编号=委托编号+交易方向
                finDto.put(INF_EVENTID, entrust.getEntrustId() + ps);
                // 事件日期
                finDto.put(INF_EVENTDATE, sysDate);
                // 交易方向
                finDto.put(INF_PS, ps);
                // 撤销标识
                finDto.put(INF_REVOKEFLG, BussinessDictCodeListRds.是否_是.getValue());
                // 是否内部交易
                finDto.put(INF_ISINNERTRD, BussinessDictCodeListRds.是否_否.getValue());
                list.add(finDto);
            }
            else if((CommonUtil.isType(entrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式正回购)
                    || CommonUtil.isType(entrust.getFinprodType2(), DictCodeListExt1.金融产品类型_投管用_债券质押式逆回购))
                 && CommonUtil.isType(entrust.getPs(), DictCodeListExt3.交易方向_到期续做)) {
                EtrEntrustAdd entrustAdd = getEntrustAdd(entrust.getEntrustId());
                if(entrustAdd != null &&  CommonUtil.isType(entrustAdd.getAssetType1(), DictCodeList.回购类型_质押式协议回购)) {

                    // 交易方向
                    finDto.put(INF_PS, ps);
                    // 事件编号=委托编号+交易方向
                    finDto.put(INF_EVENTID, entrust.getEntrustId() + ps);
                    EtrFinishStatusKey etrFinishStatusKey = new EtrFinishStatusKey();
                    etrFinishStatusKey.setEntrustId(entrust.getEntrustId());
                    //查询指令完成度，如果存在>0的完成金额，则不能释放到期那笔预占
                    List<EtrFinishStatus> etrFinishStatusList = getBaseEtrInvestInfoMapper().selectEtrFinishStatusByPrimaryKey(etrFinishStatusKey);
                    //是否存在已完成的金额
                    boolean isHaveFinishAmount = false;
                    if(etrFinishStatusList != null)  {
                        for(EtrFinishStatus item:etrFinishStatusList){
                            if(BigDecimal.ZERO.compareTo(item.getFinishAmt())<0) {
                                isHaveFinishAmount = true;
                            }
                        }
                    }
                    if(!"1".equals(entrustAdd.getRedoType()) && !isHaveFinishAmount) {
                        finDto = new HashMap<>();
                        // 事件编号
                        finDto.put(INF_EVENTID, entrust.getEntrustId() + BussinessDictCodeList.交易方向_到期.getValue());
                        // 交易方向
                        finDto.put(INF_PS, BussinessDictCodeList.交易方向_到期.getValue());
                        // 事件日期
                        finDto.put(INF_EVENTDATE, sysDate);
                        // 撤销标识
                        finDto.put(INF_REVOKEFLG, BussinessDictCodeListRds.是否_是.getValue());
                        list.add(finDto);
                    }

                }
            }
        });
        // 报文体
        body.put(INF_RSK_LIST, list);
        data.setHead(head);
        data.setBody(body);
        return data;
    }

    /**
     * 取得指令完成状况
     * 
     * @param etrList 指令代码
     * @return 指令完成状况
     */
    public static final Map<String, Boolean> getEtrCompletedMap(List<String> etrList) {
        return getService().getEtrCompletedMap(etrList);
    }

    /**
     * 判断指令完成状况是否完成
     * 
     * @param entrustId 指令代码
     * @return 指令完成状况，true : 指令完成
     */
    public static final boolean isEtrCompleted(String entrustId) {
        List<String> etrList = new ArrayList<>();
        etrList.add(entrustId);
        Map<String, Boolean> res = getEtrCompletedMap(etrList);
        return res != null && res.getOrDefault(entrustId, Boolean.FALSE);
    }
}

