/*
 * 文件名：BaseEtrInvestInfoServiceImpl.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：huwei 修改时间：2018年8月28日
 * 修改内容：新建 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.tams.invest.common.component.base.entrust.impl;

import com.joyintech.framework.constant.GlobalConstant;
import com.joyintech.framework.message.PropertyMessageSource;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.validation.ValidMessage;
import com.joyintech.tams.common.base.dao.*;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt3;
import com.joyintech.tams.invest.common.component.base.entrust.BaseEtrInvestInfoService;
import com.joyintech.tams.invest.common.component.base.entrust.dto.BaseEtrFinishAmtDto;
import com.joyintech.tams.invest.common.component.base.entrust.impl.dao.BaseEtrInvestInfoMapper;
import com.joyintech.tams.invest.common.dto.BaseEtrInvestInfoDto;
import com.joyintech.tams.invest.common.dto.trade.entrust.EtrFinishStatusDto;
import com.joyintech.tams.invest.common.trade.service.BaseEtrCashPositionCommService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 投资委托
 *
 * @author huwei
 * @version 1.0 2018年8月28日
 * @see BaseEtrInvestInfoServiceImpl
 * @since 1.0
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BaseEtrInvestInfoServiceImpl implements BaseEtrInvestInfoService {

    /**
     * 委托指令Mapper
     */
    @Autowired
    private EtrEntrustMapper etrEntrustMapper;

    /**
     * 委托指令附属表Mapper
     */
    @Autowired
    private EtrEntrustAddMapper etrEntrustAddMapper;

    /**
     * 委托指令回购质押品Mapper
     */
    @Autowired
    private EtrPledgeMapper etrPledgeMapper;

    /**
     * 委托完成状况表Mapper
     */
    @Autowired
    private EtrFinishStatusMapper etrFinishStatusMapper;

    /**
     * 委托指令分发Mapper
     */
    @Autowired
    private EtrDistributeMapper etrDistributeMapper;

    /**
     * 投资委托信息Mapper
     */
    @Autowired
    private BaseEtrInvestInfoMapper etrInvestInfoMapper;

    /**
     * 金融产品Mapper
     */
    @Autowired
    private FinProductMapper finProductMapper;

    /**
     * 投资委托信息Service
     */
    @Autowired
    private BaseEtrInvestInfoServiceImpl etrInvestInfoServiceImpl;

    /**
     * PropertyMessageSource
     */
    @Autowired
    private PropertyMessageSource messageResource;

    /**
     * 委托投标标位Mapper
     */
    @Autowired
    private EtrEntrustBidPriceMapper etrEntrustBidPriceMapper;

    @Autowired(required = false)
    private BaseEtrCashPositionCommService baseEtrCashPositionCommService;
    /**
     * 截标状态Mapper
     */
    @Autowired
    private BidCloseStatusMapper bidCloseStatusMapper;

    /**
     * 委托操作相关校验 <一句话功能简述> <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public List<ValidMessage> checkDelegateOperation(String entrustId, String delegateOperationType, List<String> traderList,
                                                     String delegateCloseWay) {
        List<ValidMessage> errorList = new ArrayList<>();
        // 调用查询已生效的委托，获得投资委托信息dto
        BaseEtrInvestInfoDto etrInvestInfoDto = etrInvestInfoServiceImpl.queryActiveMandate(entrustId);
        // 委托操作类型 = “删除”并且投资委托信息dto.委托指令Entity.委托状态不是“新建”、“已撤回”、“已退回”
        if (DictCodeListExt1.委托操作类型_删除.getValue().equals(delegateOperationType)) {
            if (etrInvestInfoDto.getEtrEntrust() != null) {
                if (!(DictCodeList.委托状态_新建.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                      || DictCodeList.委托状态_已撤回.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                      || DictCodeList.委托状态_已退回.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus()))) {
                    errorList.add(new ValidMessage(
                        // “新建”、“已撤回”、“已退回”
                        this.getMessage("ETR_E_0008", this.getMessage("BaseEtrInvestInfoServiceImpl.message01"))));
                }
            }
        }
        // 委托操作类型 = “修改”并且投资委托信息dto.委托指令Entity.委托状态不是“新建”、“已撤回”、“已退回”
        if (DictCodeListExt1.委托操作类型_修改.getValue().equals(delegateOperationType)) {
            if (etrInvestInfoDto.getEtrEntrust() != null) {
                // OPEX-869 【债券一级交易】投标信息确认后不可改。改为：投资经理可以修改除已关闭、已完成、已截标外的所有状态的信息  lizhi start
                if(StringUtil.equals(etrInvestInfoDto.getEtrEntrust().getFinprodType2(),DictCodeListExt2.金融产品类型_投管用_一级投标债券.getValue())){
                    BidCloseStatus bidCloseStatus = bidCloseStatusMapper.selectByPrimaryKey(etrInvestInfoDto.getEtrEntrust().getFinprodId());
                    boolean isCloseStatus = false ;
                    if (null != bidCloseStatus && StringUtil.equals(bidCloseStatus.getCloseStatus(),
                        DictCodeListExt3.截标状态_已截标.getValue())){
                        isCloseStatus = true ;
                    }
                    if (DictCodeList.委托状态_已关闭.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                        || DictCodeList.委托状态_已完成.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                        || isCloseStatus) {
                        errorList.add(new ValidMessage(this.getMessage("BaseEtrInvestInfoServiceImpl.message03")));
                    }
                }else{
                    if (!(DictCodeList.委托状态_新建.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                        || DictCodeList.委托状态_已撤回.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                        || DictCodeList.委托状态_已退回.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus()))) {
                        errorList.add(new ValidMessage(
                            // “新建”、“已撤回”、“已退回”
                            this.getMessage("ETR_E_0009", this.getMessage("BaseEtrInvestInfoServiceImpl.message01"))));
                    }
                }
                // OPEX-869 【债券一级交易】投标信息确认后不可改,投资经理可以修改除已关闭、已完成、已截标外的所有状态的信息  lizhi end
            }
        }
        // 委托操作类型 = “撤回”并且投资委托信息dto.委托指令Entity.委托状态不是“待接收”
        if (DictCodeListExt1.委托操作类型_撤回.getValue().equals(delegateOperationType)) {
            if (etrInvestInfoDto.getEtrEntrust() != null) {
                if (!DictCodeList.委托状态_待接收.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())) {
                    errorList.add(new ValidMessage(
                        // “待接收”
                        this.getMessage("ETR_E_0010", this.getMessage("BaseEtrInvestInfoServiceImpl.toReceive"))));
                }
            }
        }
        // 委托操作类型 = “分发”并且投资委托信息dto.委托指令Entity.委托状态不是“待接收”
        if (DictCodeListExt1.委托操作类型_分发.getValue().equals(delegateOperationType)) {
            if (etrInvestInfoDto.getEtrEntrust() != null) {
                if (!(DictCodeList.委托状态_待接收.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                      || DictCodeList.委托状态_已退回.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus()))) {
                    errorList.add(new ValidMessage(
                        // “待接收”
                        this.getMessage("ETR_E_0003", this.getMessage("BaseEtrInvestInfoServiceImpl.toReceive"))));
                }
            }
        }
        // 委托操作类型 = “分发”并且交易员列表不为空的情况下，遍历投资委托信息dto.List<委托指令分发Entity>,存在相同交易员的情况下
        if (DictCodeListExt1.委托操作类型_分发.getValue().equals(delegateOperationType)) {
            List<EtrDistribute> etrDistributeList = etrInvestInfoDto.getEtrDistributeList();
            Map<String, Object> map = new HashMap<>();
            for (String str : traderList) {
                map.put(str, str);
            }
            for (EtrDistribute etrDistribute : etrDistributeList) {
                if (map.containsKey(etrDistribute.getTrader())) {
                    errorList.add(new ValidMessage(
                        // 该委托分发已存在，无法分发！
                        this.getMessage("ETR_E_0011")));
                    break;
                }
            }
        }
        // 委托操作类型 = “接收”并且投资委托信息dto.委托指令Entity.委托状态不是“待接收”
        if (DictCodeListExt1.委托操作类型_接收.getValue().equals(delegateOperationType)
            && !DictCodeList.委托状态_待接收.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())) {
            errorList.add(new ValidMessage(
                // “待接收”
                this.getMessage("ETR_E_0004", this.getMessage("BaseEtrInvestInfoServiceImpl.toReceive"))));
        }
        // 委托操作类型 = “退回”并且投资委托信息dto.委托指令Entity.委托状态不是“已接收”
        if (DictCodeListExt1.委托操作类型_退回.getValue().equals(delegateOperationType)
            && !DictCodeList.委托状态_已接收.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())) {
            errorList.add(new ValidMessage(
                // “已接收”
                this.getMessage("ETR_E_0005", this.getMessage("BaseEtrInvestInfoServiceImpl.notfinished"))));
        }
        // 委托操作类型 = “关闭”
        if (DictCodeListExt1.委托操作类型_关闭.getValue().equals(delegateOperationType)) {
            // 输入.委托关闭方式 = “人工”并且投资委托信息dto.委托指令Entity.委托状态不是“已退回”
            if (DictCodeListExt1.委托关闭方式_人工.getValue().equals(delegateCloseWay)) {
                if (!(DictCodeList.委托状态_已退回.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                      || DictCodeList.委托状态_已接收.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                      || DictCodeList.委托状态_已撤回.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus()))) {
                    errorList.add(new ValidMessage(
                        // “已退回”
                        this.getMessage("ETR_E_0006",
                            this.getMessage("BaseEtrInvestInfoServiceImpl.hasReturned") + "/"
                                                      + this.getMessage("BaseEtrInvestInfoServiceImpl.hasReceive") + "/"
                                                      + this.getMessage("BaseEtrInvestInfoServiceImpl.hasWithdrawn"))));
                }
            }
            // 输入.委托关闭方式 = “自动”
            if (DictCodeListExt1.委托关闭方式_自动.getValue().equals(delegateCloseWay)) {
                // 不做校验
                return errorList;
            }
        }
        // 委托操作类型 = “完成”并且投资委托信息dto.委托指令Entity.委托状态不是“已接收”
        if (DictCodeListExt1.委托操作类型_完成.getValue().equals(delegateOperationType)
            && !DictCodeList.委托状态_已接收.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())) {
            errorList.add(new ValidMessage(
                // 参数{0}：“已接收”、{1}:“完成”
                this.getMessage("ETR_E_0007", this.getMessage("BaseEtrInvestInfoServiceImpl.hasReceive"),
                    this.getMessage("BaseEtrInvestInfoServiceImpl.accomplish"))));
        }
        // 委托操作类型 = “取消完成”并且投资委托信息dto.委托指令Entity.委托状态不是“已接收”、“已完成”
        if (DictCodeListExt1.委托操作类型_取消完成.getValue().equals(delegateOperationType)) {
            if (!DictCodeList.委托状态_已接收.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())
                || !DictCodeList.委托状态_已完成.getValue().equals(etrInvestInfoDto.getEtrEntrust().getEntrustStatus())) {
                errorList.add(new ValidMessage(
                    // 参数{0}：“已接收”、“已完成”、{1}:“取消完成”
                    this.getMessage("ETR_E_0007", this.getMessage("BaseEtrInvestInfoServiceImpl.message02"),
                        this.getMessage("BaseEtrInvestInfoServiceImpl.cancelComplete"))));
            }
        }
        // 委托操作类型 = “退回”且已完成部分交易，则不可退回
        if (DictCodeListExt1.委托操作类型_退回.getValue().equals(delegateOperationType)
            && CollectionUtils.isNotEmpty(etrInvestInfoDto.getEtrFinishStatusList())) {
            errorList.add(new ValidMessage(
                // 已有交易，不可再拒绝！
                this.getMessage("ETR_E_0013")));
        }
        return errorList;
    }

    /**
     * <一句话功能简述> <功能详细描述> 返回多条信息
     *
     * @param
     * @param key key
     * @param args args
     * @return key key
     * @see
     * @since 1.0
     */
    private String getMessage(String key, String... args) {
        return messageResource.getMessage(key, args, Locale.SIMPLIFIED_CHINESE);
    }

    /**
     * <一句话功能简述> <功能详细描述> 返回单条信息
     *
     * @param
     * @param key key
     * @return 国际化语言
     * @see
     * @since 1.0
     */
    private String getMessage(String key) {
        return messageResource.getMessage(key, null, Locale.SIMPLIFIED_CHINESE);
    }

    /**
     * 查询已经生效的委托
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public BaseEtrInvestInfoDto queryActiveMandate(String entrustId) {
        BaseEtrInvestInfoDto etrInvestInfoDto = new BaseEtrInvestInfoDto();
//        if (!"null".equals(entrustId)) {
          if(StringUtil.isNotEmpty(entrustId)){
            List<String> entrustIdList = StringUtil.splitToList(GlobalConstant.COMMA, entrustId);
            if (entrustIdList.size() > 1) {
                EtrEntrust etrEntrust = null;
                // 委托指令查询(多组合)
                Map<String, Object> pageParam = new HashMap<String, Object>();
                pageParam.put("entrustIdList", entrustIdList);
                List<EtrEntrust> etrEntrustList = etrInvestInfoMapper.selectByPrimaryKeyPtl(pageParam);
                BigDecimal entrustAmt = new BigDecimal(0);
                for (int i = 0; i < etrEntrustList.size(); i++ ) {
                    if(etrEntrustList.get(i).getEntrustAmt()!=null){entrustAmt = entrustAmt.add(etrEntrustList.get(i).getEntrustAmt());}else{
                         entrustAmt=entrustAmt.add(etrEntrustList.get(i).getEntrustNum());
                     }
                }
                etrEntrustList.get(0).setEntrustAmt(entrustAmt);
                etrEntrust = etrEntrustList.get(0);
                // 设置外置显示dto
                etrInvestInfoDto = queryActiveMandateBySingle(entrustIdList.get(0));
                etrInvestInfoDto.setEtrEntrust(etrEntrust);
                // 多笔委托信息存储容器
                List<BaseEtrInvestInfoDto> baseEtrInvestInfoDtoList = new ArrayList<>();
                etrInvestInfoDto.setBaseEtrInvestInfoDtoList(baseEtrInvestInfoDtoList);
                for (String entrustIdT : entrustIdList) {
                    // 获取单笔委托信息
                    BaseEtrInvestInfoDto etrInvestInfoDtoT = queryActiveMandateBySingle(entrustIdT);
                    baseEtrInvestInfoDtoList.add(etrInvestInfoDtoT);
                }
            }
            else {
                BaseEtrInvestInfoDto etrInvestInfoDtoTemp = queryActiveMandateBySingle(entrustId);
                BeanUtil.copyPropertiesIgnoreNull(etrInvestInfoDto, etrInvestInfoDtoTemp);
                etrInvestInfoDto.setBaseEtrInvestInfoDtoList(
                        Arrays.asList(new BaseEtrInvestInfoDto[] {etrInvestInfoDtoTemp}));
            }
        }

        // // 委托指令附属表查询
        // EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(entrustId);
        // // 委托指令分发查询
        // EtrDistributeKey etrDistributeKey = new EtrDistributeKey();
        // etrDistributeKey.setEntrustId(entrustId);
        // List<EtrDistribute> etrDistributeList = etrInvestInfoMapper.selectEtrDistributeByPrimaryKey(
        // etrDistributeKey);
        // // 委托完成状况表查询
        // EtrFinishStatusKey etrFinishStatusKey = new EtrFinishStatusKey();
        // etrFinishStatusKey.setEntrustId(entrustId);
        // List<EtrFinishStatus> etrFinishStatusList = etrInvestInfoMapper.selectEtrFinishStatusByPrimaryKey(
        // etrFinishStatusKey);
        // // 委托指令回购质押品查询
        // EtrPledgeKey etrPledgeKey = new EtrPledgeKey();
        // etrPledgeKey.setEntrustId(entrustId);
        // List<EtrPledge> etrPledgeList = etrInvestInfoMapper.selectEtrPledgeByPrimaryKey(
        // etrPledgeKey);
        // // 将值传入EtrInvestInfoDto
        // BaseEtrInvestInfoDto etrInvestInfoDto = new BaseEtrInvestInfoDto();
        // etrInvestInfoDto.setEtrEntrust(etrEntrust);
        // etrInvestInfoDto.setEtrEntrustAdd(etrEntrustAdd);
        // etrInvestInfoDto.setEtrDistributeList(etrDistributeList);
        // etrInvestInfoDto.setEtrFinishStatusList(etrFinishStatusList);
        // etrInvestInfoDto.setEtrPledgeList(etrPledgeList);
        // if (etrEntrust != null && StringUtil.isNotEmpty(etrEntrust.getFinprodId())) {
        // FinProduct product = finProductMapper.selectByPrimaryKey(etrEntrust.getFinprodId());
        // if(product != null) {
        // etrInvestInfoDto.setFinprodMarketId(product.getFinprodMarketId());
        // }
        // }
        return etrInvestInfoDto;
    }

    /**
     * 查询已经生效的委托 单体
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    public BaseEtrInvestInfoDto queryActiveMandateBySingle(String entrustId) {
        // 委托
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
        // 委托指令附属表查询
        EtrEntrustAdd etrEntrustAdd = etrEntrustAddMapper.selectByPrimaryKey(entrustId);
        // 委托指令分发查询
        EtrDistributeKey etrDistributeKey = new EtrDistributeKey();
        etrDistributeKey.setEntrustId(entrustId);
        List<EtrDistribute> etrDistributeList = etrInvestInfoMapper.selectEtrDistributeByPrimaryKey(etrDistributeKey);
        // 委托完成状况表查询
        EtrFinishStatusKey etrFinishStatusKey = new EtrFinishStatusKey();
        etrFinishStatusKey.setEntrustId(entrustId);
        List<EtrFinishStatus> etrFinishStatusList = etrInvestInfoMapper.selectEtrFinishStatusByPrimaryKey(etrFinishStatusKey);
        // 委托指令回购质押品查询
        EtrPledgeKey etrPledgeKey = new EtrPledgeKey();
        etrPledgeKey.setEntrustId(entrustId);
        List<EtrPledge> etrPledgeList = etrInvestInfoMapper.selectEtrPledgeByPrimaryKey(etrPledgeKey);
        // 将值传入EtrInvestInfoDto
        BaseEtrInvestInfoDto etrInvestInfoDto = new BaseEtrInvestInfoDto();
        etrInvestInfoDto.setEtrEntrust(etrEntrust);
        etrInvestInfoDto.setEtrEntrustAdd(etrEntrustAdd);
        etrInvestInfoDto.setEtrDistributeList(etrDistributeList);
        etrInvestInfoDto.setEtrFinishStatusList(etrFinishStatusList);
        etrInvestInfoDto.setEtrPledgeList(etrPledgeList);
        if(etrEntrust != null && null!=etrEntrust.getChlId()){
            etrInvestInfoDto.setChlId(etrEntrust.getChlId());
        }

        if (etrEntrustAdd != null && StringUtil.isNotEmpty(etrEntrustAdd.getrFinprodId())) {
            FinProduct finProduct = finProductMapper.selectByPrimaryKey(etrEntrustAdd.getrFinprodId());
            if (finProduct != null) {
                etrInvestInfoDto.setChangeToFinprodMarketId(finProduct.getFinprodMarketId());
                etrInvestInfoDto.setChangeToFinprodName(StringUtil.isEmpty(finProduct.getFinprodAbbr()) ? finProduct.getFinprodName() : finProduct.getFinprodAbbr());
            }
        }
        if (etrEntrust != null && StringUtil.isNotEmpty(etrEntrust.getFinprodId())) {
            FinProduct product = finProductMapper.selectByPrimaryKey(etrEntrust.getFinprodId());
            if (product != null) {
                etrInvestInfoDto.setFinprodMarketId(product.getFinprodMarketId());
                etrInvestInfoDto.setFinprodId(product.getFinprodId());
            }
        }
        return etrInvestInfoDto;
    }

    /**
     * 新建委托 <一句话功能简述> <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean newDelegate(BaseEtrInvestInfoDto dto) {
        if (dto != null) {
            // 保存委托指令
            if (!DictCodeList.委托状态_待接收.getValue().equals(dto.getEtrEntrust().getEntrustStatus())) {
                dto.getEtrEntrust().setEntrustStatus(DictCodeList.委托状态_待接收.getValue());
            }
            etrEntrustMapper.insertSelective(dto.getEtrEntrust());
            // 保存委托指令附属表
            etrEntrustAddMapper.insertSelective(dto.getEtrEntrustAdd());
            // 保存List<委托指令回购质押品>
            if (dto.getEtrPledgeList() != null && dto.getEtrPledgeList().size() > 0) {
                for (EtrPledge etrPledge : dto.getEtrPledgeList()) {
                    etrPledgeMapper.insertSelective(etrPledge);
                }
            }
            // 保存list<委托指令分发>
            if (dto.getEtrDistributeList() != null && dto.getEtrDistributeList().size() > 0) {
                for (EtrDistribute etrDistribute : dto.getEtrDistributeList()) {
                    if (!DictCodeList.委托状态_待接收.getValue().equals(etrDistribute.getRecStatus())) {
                        etrDistribute.setRecStatus(DictCodeList.委托状态_待接收.getValue());
                    }
                    etrDistributeMapper.insertSelective(etrDistribute);
                }
            }
            // 保存List<委托完成状况表>
            if (dto.getEtrFinishStatusList() != null && dto.getEtrFinishStatusList().size() > 0) {
                for (EtrFinishStatus etrFinishStatus : dto.getEtrFinishStatusList()) {
                    etrFinishStatusMapper.insertSelective(etrFinishStatus);
                }
            }

            // 保存List<委托投标标位表>
            if (dto.getBidPriceList() != null && !dto.getBidPriceList().isEmpty()) {
                for (EtrEntrustBidPrice etrEntrustBidPrice : dto.getBidPriceList()) {
                    etrEntrustBidPrice.setBidPrice(etrEntrustBidPrice.getBidPrice().divide(BigDecimal.valueOf(100)));
                    etrEntrustBidPriceMapper.insertSelective(etrEntrustBidPrice);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 删除委托 <一句话功能简述> <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean deleteDelegate(String entrustId) {
        if (entrustId != null) {
            // 删除委托指令
            etrEntrustMapper.deleteByPrimaryKey(entrustId);
            // 删除委托指令附属
            etrEntrustAddMapper.deleteByPrimaryKey(entrustId);
            // 删除委托指令回购质押品
            EtrPledgeKey etrPledgeKey = new EtrPledgeKey();
            etrPledgeKey.setEntrustId(entrustId);
            etrInvestInfoMapper.deleteEtrPledgeByPrimaryKey(etrPledgeKey);
            // 删除委托指令分发
            EtrDistribute etrDistribute = new EtrDistribute();
            etrDistribute.setEntrustId(entrustId);
            etrInvestInfoMapper.deleteEtrDistributeByPrimaryKey(etrDistribute);
            // 删除委托完成状况表
            EtrFinishStatus etrFinishStatus = new EtrFinishStatus();
            etrFinishStatus.setEntrustId(entrustId);
            etrInvestInfoMapper.deleteEtrFinishStatusByPrimaryKey(etrFinishStatus);
            // 全删全插委托投标标位表
            etrInvestInfoMapper.deleteBidPrice(entrustId);
            // 判断删除委托是否全部执行成功
            if (baseEtrCashPositionCommService != null) {
                baseEtrCashPositionCommService.entrustCloseExecute(entrustId);
            }
            return true;
        }
        return false;
    }

    /**
     * 修改委托 <一句话功能简述> <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean updateDelegate(BaseEtrInvestInfoDto dto) {
        if (dto != null) {
            // 调用删除委托接口（投资委托信息dto.委托指令Entity.委托代码）
            etrInvestInfoServiceImpl.deleteDelegate(dto.getEtrEntrust().getEntrustId());
            // 调用新建委托接口
            etrInvestInfoServiceImpl.newDelegate(dto);
            // 判断修改委托是否执行成功
            return true;
        }
        return false;
    }

    /**
     * 更新委托状态 <一句话功能简述> <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean updateDelegateStatus(String entrustId, String entrustStatus) {
        // 委托指令查询
        EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
        // 更改委托状态
        etrEntrust.setEntrustStatus(entrustStatus);
        // 修改委托指令表中值
        int flag = etrEntrustMapper.updateByPrimaryKeySelective(etrEntrust);
        // 判断更新委托状态执行成功
        if (flag == 1) {
            return true;
        }
        return false;
    }

    /**
     * 撤回委托 <一句话功能简述> <功能详细描述>
     *
     * @param dto
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean recallDelegate(BaseEtrInvestInfoDto dto) {
        // 将传来dto中的委托指令的委托状态改为"已撤回"
        dto.getEtrEntrust().setEntrustStatus(DictCodeList.委托状态_已撤回.getValue());
        // 调用投资委托组件的更新委托状态接口，将委托状态设置为"已撤回"
        boolean flag = etrInvestInfoServiceImpl.updateDelegateStatus(dto.getEtrEntrust().getEntrustId(),
            dto.getEtrEntrust().getEntrustStatus());
        // 判断撤回委托是否执行成功
        if (baseEtrCashPositionCommService != null) {
            baseEtrCashPositionCommService.entrustCloseExecute(dto.getEtrEntrust().getEntrustId());
        }
        if (flag) {
            return true;
        }
        return false;
    }

    /**
     * 分发委托 <一句话功能简述> <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean distributeDelegate(List<EtrDistribute> list) {
        // 保存list<委托指令分发>
        if (CollectionUtils.isNotEmpty(list)) {
            etrInvestInfoMapper.deletByList(list);
            for (EtrDistribute etrDistribute : list) {
                etrDistributeMapper.insertSelective(etrDistribute);
            }
            return true;
        }
        return false;
    }

    /**
     * 接收委托 <一句话功能简述> <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean receptionDelegate(List<EtrDistribute> list) {
        if (list != null && list.size() > 0) {
            // 接收该委托的交易员的接收状态设为已接收，其他交易员的委托数据删除
            for (EtrDistribute etrDistribute : list) {
                etrInvestInfoServiceImpl.updateDelegateStatus(etrDistribute.getEntrustId(), DictCodeList.委托状态_已接收.getValue());
                EtrDistributeKey key = new EtrDistributeKey();
                key.setEntrustId(etrDistribute.getEntrustId());
                List<EtrDistribute> etrDistributeList = etrInvestInfoMapper.selectEtrDistributeByPrimaryKey(key);
                for (EtrDistribute entity : etrDistributeList) {
                    if (StringUtil.equals(entity.getTrader(), etrDistribute.getTrader())) {
                        Map<String, Object> param2 = new HashMap<String, Object>();
                        param2.put("ENTRUST_RECEIVE_TYPE_HASCANCEL", DictCodeList.委托状态_已接收.getValue());
                        param2.put("entrustId", entity.getEntrustId());
                        param2.put("trader", entity.getTrader());
                        etrInvestInfoMapper.updateEtrDistributeByPrimaryKeySelective(param2);
                    }
                    else {
                        EtrDistributeKey etrDistributeKey = new EtrDistributeKey();
                        etrDistributeKey.setEntrustId(entity.getEntrustId());
                        etrDistributeKey.setTrader(entity.getTrader());
                        etrInvestInfoMapper.deleteEtrDistributeByPrimaryKey(etrDistributeKey);
                    }
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 退回委托 <一句话功能简述> <功能详细描述>
     *
     * @param reason 退回原因
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean returnDelegate(List<EtrDistribute> list, String reason) {
        // 将委托状态更新为已退回，交易员的委托数据删除
        if (list != null && list.size() > 0) {
            for (EtrDistribute etrDistribute : list) {
                EtrDistributeKey key = new EtrDistributeKey();
                key.setEntrustId(etrDistribute.getEntrustId());
                List<EtrDistribute> etrDistributeList = etrInvestInfoMapper.selectEtrDistributeByPrimaryKey(key);
                // 更新委托表的委托状态
                etrInvestInfoServiceImpl.updateDelegateStatus(etrDistribute.getEntrustId(), DictCodeList.委托状态_已退回.getValue());
                // 委托退回原因
                etrInvestInfoServiceImpl.updateBackReason(etrDistribute.getEntrustId(), reason);
                // 删除交易员的委托数据
                for (EtrDistribute entity : etrDistributeList) {
                    entity.setRecStatus(DictCodeList.委托状态_已退回.getValue());
                    etrDistributeMapper.updateByPrimaryKeySelective(entity);
                    // etrDistributeMapper.deleteByPrimaryKey(entity);
                }
                if (baseEtrCashPositionCommService != null) {
                    baseEtrCashPositionCommService.entrustCloseExecute(etrDistribute.getEntrustId());
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 关闭委托 <一句话功能简述> <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean closeDelegate(String entrustId, String closeWay) {
        EtrEntrust ee = etrEntrustMapper.selectByPrimaryKey(entrustId);
        if (ee != null) {
            // 调用投资委托组件的更新委托状态接口，将委托状态设置为“已关闭”
            String tempEntrustStatu = ee.getEntrustStatus();// 获取该委托原始状态，防止后续更改成关闭
            boolean flag = etrInvestInfoServiceImpl.updateDelegateStatus(entrustId, DictCodeList.委托状态_已关闭.getValue());

            if (baseEtrCashPositionCommService != null && DictCodeList.委托状态_已接收.getValue().equals(tempEntrustStatu)) {
                baseEtrCashPositionCommService.entrustCloseExecute(entrustId);
            }

            // 判断关闭委托是否完成
            if (flag) {
                return true;
            }
        }
        return false;
    }

    /**
     * 委托完成状况查询 <一句话功能简述> <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public List<BaseEtrFinishAmtDto> queryDelegateStatus(List<String> entrustIdList) {
        // 根据委托代码，查询“委托完成状况表”，汇总完成额度，获取委托完成额度
        List<BaseEtrFinishAmtDto> etrFinishAmtDtoList = new ArrayList<BaseEtrFinishAmtDto>();
        for (String entrustId : entrustIdList) {
            // 查询总完成额度
            BaseEtrFinishAmtDto etrFinishAmtDto = etrInvestInfoMapper.selectEtrFinishAmtDtoByPrimaryKey(entrustId);

            // 查询委托额度
            EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
            if (etrFinishAmtDto != null && etrEntrust != null) {
                BigDecimal 委托额度 = etrEntrust.getEntrustAmt();
                if (委托额度 == null) {
                    委托额度 = etrEntrust.getEntrustNum();
                }

                BigDecimal 总交易完成度 = ArithmeticUtil.divide(etrFinishAmtDto.getSumFinishAmt(), 委托额度, 4);
                总交易完成度 = ArithmeticUtil.multiply(总交易完成度, BigDecimal.valueOf(100));
                etrFinishAmtDto.setSumFinishPercent(总交易完成度);

                etrFinishAmtDtoList.add(etrFinishAmtDto);
            }

        }
        return etrFinishAmtDtoList;
    }

    /**
     * 完成委托 <一句话功能简述> <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean completeDelegate(List<EtrFinishStatusDto> etrFinishStatusList) {
        if (CollectionUtils.isEmpty(etrFinishStatusList)) {
            return false;
        }
        // 遍历List<委托完成状况表Entity>，保存到“委托完成状况表”
        int count = 0;
        for (EtrFinishStatusDto etrFinishStatus : etrFinishStatusList) {
            if(StringUtil.isNotBlank(etrFinishStatus.getCstpTradeId())){
                EtrFinishStatusKey key = new EtrFinishStatusKey();
                key.setEntrustId(etrFinishStatus.getEntrustId());
                key.setTradeId(etrFinishStatus.getCstpTradeId());
                EtrFinishStatus etrFinishStatus1 = etrFinishStatusMapper.selectByPrimaryKey(key);
                if(etrFinishStatus1!=null){
                    etrFinishStatusMapper.deleteByPrimaryKey(key);
                }
            }
            int i = etrFinishStatusMapper.insertSelective(etrFinishStatus);
            count = count + i;
        }
        // 调用委托完成状况查询
        List<String> entrustIdList = new ArrayList<String>();
        for (EtrFinishStatus etrFinishStatus : etrFinishStatusList) {
            entrustIdList.add(etrFinishStatus.getEntrustId());
        }

        List<BaseEtrFinishAmtDto> etrFinishAmtDtoList = etrInvestInfoServiceImpl.queryDelegateStatus(entrustIdList);
        // 判断委托代码的完成额度是否大于等于100
        BigDecimal bigDecimal = new BigDecimal("100.00");
        for (BaseEtrFinishAmtDto etrFinishAmtDto : etrFinishAmtDtoList) {
            if (etrFinishAmtDto.getSumFinishPercent().compareTo(bigDecimal) == 1
                || etrFinishAmtDto.getSumFinishPercent().compareTo(bigDecimal) == 0) {
                // 如果满足，则调用投资委托组件的更新委托状态接口，将委托状态设置为“已完成”
                etrInvestInfoServiceImpl.updateDelegateStatus(etrFinishAmtDto.getEntrustId(), DictCodeList.委托状态_已完成.getValue());
            }
        }
        // 判断完成委托是否完成
        if (count == etrFinishStatusList.size()) {
            return true;
        }
        return false;
    }

    /**
     * 取消完成委托 <一句话功能简述> <功能详细描述>
     *
     * @param
     * @return
     * @see
     * @since 1.0
     */
    @Override
    public boolean cancelCompleteDelegate(String entrustId, String tradeId) {
        // 删除委托完成状况表
        EtrFinishStatus etrFinishStatus = new EtrFinishStatus();
        etrFinishStatus.setEntrustId(entrustId);
        etrFinishStatus.setTradeId(tradeId);
        int count = etrInvestInfoMapper.deleteEtrFinishStatusByPrimaryKey(etrFinishStatus);
        // 调用投资委托组件的更新委托状态接口，将委托状态设置为“已接收”
        boolean flag = etrInvestInfoServiceImpl.updateDelegateStatus(entrustId, DictCodeList.委托状态_已接收.getValue());
        // 判断取消完成委托是否完成
        if (flag && count >= 1) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updateBackReason(String entrustId, String reason) {
        if (StringUtil.isNotBlank(entrustId) && StringUtil.isNotBlank(reason)) {
            // 委托指令查询
            EtrEntrust etrEntrust = etrEntrustMapper.selectByPrimaryKey(entrustId);
            //退回备注不覆盖之前的备注 edit by xueying 20200725 start
            String remark=etrEntrust.getRemark();
            reason=remark+"\n"+"退回原因：" + reason;
            // 更改委托状态
            etrEntrust.setRemark(reason);
            //退回备注不覆盖之前的备注 edit by xueying 20200725 end
            // 修改委托指令表中值
            int flag = etrEntrustMapper.updateByPrimaryKeySelective(etrEntrust);
            // 判断更新委托状态执行成功
            if (flag == 1) {
                return true;
            }
        }
        return false;
    }
}
