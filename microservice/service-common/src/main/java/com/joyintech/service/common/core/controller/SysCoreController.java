/*
 * 文件名：SysCoreController.java 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved. 描述：理财资管管理系统V2.0 修改人：何阳阳 修改时间：2018年9月14日 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.common.core.controller;

import com.joyintech.framework.annotation.DTXTransaction;
import com.joyintech.framework.base.entity.ProcessMark;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.IDGenerator;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.service.common.core.dto.AutoCouponDto;
import com.joyintech.service.common.core.dto.InfChinamutualfunddescriptionDto;
import com.joyintech.service.common.core.dto.InterfPayhsrcsecinfoDto;
import com.joyintech.service.common.core.service.SysCoreService;
import com.joyintech.tams.common.base.entity.*;
import com.joyintech.tams.component.base.core.MstWorkflowCommonService;
import com.joyintech.tams.component.base.core.dto.WflSpecialValidInputParameter;
import com.joyintech.tams.component.base.core.dto.WflValidInputParameter;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.invest.common.component.base.core.BaseGetBussinessId;
import com.joyintech.tams.invest.common.component.base.core.IPlatformDate;
import com.joyintech.tams.invest.common.component.base.core.MstFileCommonService;
import com.joyintech.tams.invest.common.dto.MstFileInputParameter;
import com.joyintech.tams.invest.common.dto.MstFileOutputParameter;
import com.joyintech.tams.invest.common.dto.common.BussinessCodeParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 何阳阳
 * @version 1.0 2018年9月14日
 * @see SysCoreController
 * @since 1.0
 */
@RestController("commonCoreCtr_dtx")
@RequestMapping("/core")
public class SysCoreController {

    @Autowired(required = false)
    private BaseGetBussinessId baseGetBussinessId;

    @Autowired(required = false)
    private IPlatformDate platformDate;

    @Autowired(required = false)
    private MstFileCommonService fileCommonService;

    @Autowired(required = false)
    private MstWorkflowCommonService mstWorkflowCommonService;

    /**
     * SysCoreService
     */
    @Autowired
    private SysCoreService sysCoreService;

    @PostMapping("selectRule")
    public String selectRule(@RequestBody BussinessCodeParameter parameter) {
        if (null != baseGetBussinessId) {
            if (null != parameter.getCommonBusinessExtType() && !parameter.getCommonBusinessExtType().isEmpty()) {
                if (StringUtil.isNotEmpty(parameter.getCommonBusinessType())) {
                    parameter.getCommonBusinessType().setCodeName(
                        parameter.getCommonBusinessExtType().get("codeName").toString());
                    parameter.getCommonBusinessType().setValue(parameter.getCommonBusinessExtType().get("value").toString());
                }
                else {
                    DictCodeList dictCodeList = DictCodeList.枚举LIST_EXT转换LIST;
                    dictCodeList.setCodeName(parameter.getCommonBusinessExtType().get("codeName").toString());
                    dictCodeList.setValue(parameter.getCommonBusinessExtType().get("value").toString());
                    parameter.setCommonBusinessType(dictCodeList);
                }
            }
            return baseGetBussinessId.buildBussinessCode(parameter);
        }
        return IDGenerator.generate();
    }

    @Transactional
    @DTXTransaction
    @PostMapping("updatePlatDate")
    public int updatePlatDate(@RequestBody Date newDate) {
        if (null != newDate) {
            return platformDate.updateSysDate(newDate);
        }
        return 0;
    }

    @GetMapping("platDate")
    public Date platDate() {
        if (null != platformDate) {
            return platformDate.getSysDate();
        }
        return DateUtil.now();
    }

    @GetMapping("dbDate")
    public Date dbDate() {
        if (null != platformDate) {
            return platformDate.getDbDate();
        }
        return DateUtil.now();
    }

    @Transactional
    @DTXTransaction
    @PostMapping("insertFileUploadInfo")
    public MstFileOutputParameter insertFileUploadInfo(@RequestBody MstFileInputParameter input) {
        if (null != fileCommonService) {
            return fileCommonService.updateBusinessId(input);
        }
        return new MstFileOutputParameter();
    }

    @GetMapping("getUploadInfo/{bussinessId}")
    public MstFileOutputParameter getUploadFile(@PathVariable("bussinessId") String bussinessId) {
        if (null != fileCommonService) {
            return fileCommonService.getFileInfoByBusiId(bussinessId);
        }
        return new MstFileOutputParameter();
    }

    @PostMapping("getFileInfo")
    public MstFileOutputParameter getFileInfo(@RequestBody Map<String, Object> map) {
        if (null != fileCommonService) {
            return fileCommonService.getFileInfoByFileName((String)map.get("fileName"));
        }
        return new MstFileOutputParameter();
    }

    @PostMapping("isInWflProcess")
    public boolean isInWflProcess(@RequestBody WflValidInputParameter input) {
        if (null != mstWorkflowCommonService) {
            return mstWorkflowCommonService.isInWflProcess(input);
        }
        return false;
    }

    @PostMapping("isInWflProcessSpe")
    public boolean isInWflProcess(@RequestBody WflSpecialValidInputParameter input) {
        if (null != mstWorkflowCommonService) {
            return mstWorkflowCommonService.isInWflProcess(input);
        }
        return false;
    }

    @PostMapping("isProcessData")
    public boolean isProcessData(@RequestBody WflValidInputParameter input) {
        return sysCoreService.isProcessData(input) > 0;
    }

    @PostMapping("getDataStatusFromProcessMark")
    public String getDataStatusFromProcessMark(@RequestBody WflValidInputParameter input) {
        if (null != mstWorkflowCommonService) {
            return mstWorkflowCommonService.getDataStatusFromProcessMark(input);
        }
        return null;
    }

    @PostMapping("getDataEntityFromProcessMark")
    public ProcessMark getDataEntityFromProcessMark(@RequestBody WflValidInputParameter input) {
        if (null != mstWorkflowCommonService) {
            return mstWorkflowCommonService.getDataEntityFromProcessMark(input);
        }
        return null;
    }

    @PostMapping("getBusinessEntityByWorkFlowId")
    public String getBusinessEntityByWorkFlowId(@RequestBody Map<String, Object> map) {
        if (null != mstWorkflowCommonService) {
            String workflowId = (String)map.get("workflowId");
            String beanName = (String)map.get("beanName");
            return mstWorkflowCommonService.getBusinessEntityByWorkFlowId(workflowId, beanName);
        }
        return null;
    }

    /**
     * 利用币种与交易日查询汇率行情表中最新的收盘价
     *
     * @param ccy 币种
     * @param tradeDate 交易日
     * @return 汇率行情表中最新的收盘价
     * @see
     * @since 1.0
     */
    @PostMapping("selectClosePrice/{ccy}/{tradeDate}")
    public BigDecimal selectClosePrice(@PathVariable("ccy") String ccy, @PathVariable("tradeDate") String tradeDate) {
        return sysCoreService.selectClosePrice(ccy, tradeDate);
    }

    /**
     * 资产是否存在交易
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    @GetMapping("isTransInAsset")
    public int isTransInAsset(String finprodId) {
        return sysCoreService.isTransInAsset(finprodId);
    }

    /**
     * 获取最大交易日期 <功能详细描述>
     *
     * @param finprodId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("getMaxTradeDate")
    public Date getMaxTradeDate(String finprodId) {
        return sysCoreService.getMaxTradeDate(finprodId);
    }

    /**
     * 获取wind资产信息(债券) <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectWindBondAsset")
    public List<InterfPayhsrcsecinfoDto> selectWindBondAsset(@RequestBody Map<String,Object> map) {
        return sysCoreService.selectWindBondAsset(map);
    }

    /**
     * 获取wind资产信息(基金) <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @PostMapping("selectWindFundAsset")
    public List<InfChinamutualfunddescriptionDto> selectWindFundAsset(@RequestBody Map<String,Object> map) {
        return sysCoreService.selectWindFundAsset(map);
    }

    /**
     * 获取wind资产信息(债券) <功能详细描述>
     *
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectWindBondAssetBySecId")
    public InterfPayhsrcsecinfoDto selectWindBondAssetBySecId(String secid) {
        return sysCoreService.selectWindBondAssetBySecId(secid);
    }

    /**
     * 获取wind资产信息(基金) <功能详细描述>
     *
     * @param fInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectWindFundAssetByfInfoWindcode")
    public InterfCmfunddescrip selectWindFundAssetByfInfoWindcode(String fInfoWindcode) {
        return sysCoreService.selectWindFundAssetByfInfoWindcode(fInfoWindcode);
    }

    /**
     * 获取wind行权信息(债券) <功能详细描述>
     *
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectWindBondExercise")
    public List<InterfPayhsrcsecoption> selectWindBondExercise(String secid) {
        return sysCoreService.selectWindBondExercise(secid);
    }

    /**
     * 获取wind付息计划(债券) <功能详细描述>
     *
     * @param secid
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectWindBondSchedule")
    public List<InterfPayhsrcsecschedule> selectWindBondSchedule(String secid) {
        return sysCoreService.selectWindBondSchedule(secid);
    }

    /**
     * 获取WIND_中国债券特殊条款(债券) <功能详细描述>
     *
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectInterfCbondspecialconditions")
    public InterfCbondspecialconditions selectInterfCbondspecialconditions(String sInfoWindcode) {
        return sysCoreService.selectInterfCbondspecialconditions(sInfoWindcode);
    }

    /**
     * 获取WIND_浮息债报价表(债券) <功能详细描述>
     *
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectInterfFloatingcouponsrate")
    public List<InterfFloatcouprate> selectInterfFloatingcouponsrate(String sInfoWindcode) {
        return sysCoreService.selectInterfFloatingcouponsrate(sInfoWindcode);
    }

    /**
     * 获取Wind 债券分类板块(债券) <功能详细描述>
     *
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectInterfCbondindustrywind")
    public InterfCbondindustrywind selectInterfCbondindustrywind(String sInfoWindcode) {
        return sysCoreService.selectInterfCbondindustrywind(sInfoWindcode);
    }

    /**
     * 获取中国债券基本资料(债券) <功能详细描述>
     *
     * @param sInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectInterfCbonddescription")
    public InterfCbonddescription selectInterfCbonddescription(String sInfoWindcode) {
        return sysCoreService.selectInterfCbonddescription(sInfoWindcode);
    }

    /**
     * 获取中国货币市场基金份额结转方式 <功能详细描述>
     *
     * @param fInfoWindcode
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectWindFundMoneymarketfscarryoverm")
    public InfCmoneymarketfscarryoverm selectWindFundMoneymarketfscarryoverm(String fInfoWindcode) {
        return sysCoreService.selectWindFundMoneymarketfscarryoverm(fInfoWindcode);
    }

    /**
     * 自动加券(债券) <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("autoBondCoupon")
    public void autoBondCoupon(@RequestBody InterfPayhsrcsecinfoDto dto) {
        sysCoreService.autoBondCoupon(dto);
    }

    /**
     * 自动加券(基金) <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("autoFundCoupon")
    public void autoFundCoupon(@RequestBody InfChinamutualfunddescriptionDto dto) {
        sysCoreService.autoFundCoupon(dto);
    }

    /**
     * 自动加券 投资委托根据资产代码查询入库表之后的金融产品代码信息 <功能详细描述>
     *
     * @param finprodMarketId
     * @return
     * @see
     * @since 1.0
     */
    @GetMapping("selectAutoFinProd")
    public AutoCouponDto selectAutoFinProd(@RequestParam String finprodMarketId) {
        return sysCoreService.selectAutoFinProd(finprodMarketId);
    }

    /**
     * 自动加券(基金 获取wind代码) 根据前台代码获取wind代码
     *
     * @param dto
     * @see
     * @since 1.0
     */
    @GetMapping("getFwindCode")
    public List<FinProduct> getFwindCode(String finprodMarketId) {
        return sysCoreService.getFwindCode(finprodMarketId);
    }

    /**
     * 查询是否存在债券市值行情count
     *
     * @param sInfoWindcode(新增)
     * @return
     */
    @GetMapping("selectMstBondValuationInfoCount")
    public List<Integer> selectMstBondValuationInfoCount(String finprodMarketId) {
        return sysCoreService.selectMstBondValuationInfoCount(finprodMarketId);
    }

    /**
     * 查询资讯_债券市值行情
     *
     * @param sInfoWindcode(新增)
     * @return
     */
    @GetMapping("selectInterfPayhsrccbvlt")
    public List<InterfPayhsrccbvlt> selectInterfPayhsrccbvlt(String finprodMarketId, String sysDate, String newDate) {
        return sysCoreService.selectInterfPayhsrccbvlt(finprodMarketId, sysDate, newDate);
    }

    /**
     * 查询资讯_市场行情数据INTERF_BOND_MARKET_INFO
     *
     * @param finprodMarketId
     * @param sysDate
     * @param newDate
     * @return
     */
    @GetMapping("selectInterfBondMarketInfo")
    public List<InterfBondMarketInfo> selectInterfBondMarketInfo(String finprodMarketId, String sysDate, String newDate) {
        return sysCoreService.selectInterfBondMarketInfo(finprodMarketId, sysDate, newDate);
    }

    /**
     * 判断当前日期是否存在回购标准券折算率 (FIN_STDSEC_CONVERSE_RADIO)
     *
     * @param finprodMarketId
     * @return
     */
    @GetMapping("selectFinStdsecConverseRadioCount")
    public List<Integer> selectFinStdsecConverseRadioCount(String finprodMarketId, String sysDate) {
        return sysCoreService.selectFinStdsecConverseRadioCount(finprodMarketId, sysDate);
    }

    /**
     * 查询资讯_回购标准折算率(INTERF_STDSEC_CON_RADIO)
     *
     * @param finprodMarketId
     * @return
     */
    @GetMapping("selectInterfStdsecConRadio")
    public List<InterfStdsecConRadio> selectInterfStdsecConRadio(String finprodMarketId, String sysDate) {
        return sysCoreService.selectInterfStdsecConRadio(finprodMarketId, sysDate);
    }

    /**
     * 查询是否存在场外行情信息-基金加券
     *
     * @param finprodMarketId
     * @return
     */
    @GetMapping("selectFinProductValuationCount")
    public List<Integer> selectFinProductValuationCount(String finprodMarketId) {
        return sysCoreService.selectFinProductValuationCount(finprodMarketId);
    }

    /**
     * 查询资讯_基金净值list-基金加券
     *
     * @param sInfoWindcode
     * @return
     */
    @GetMapping("selectInterfCmfundnav")
    public List<InterfCmfundnav> selectInterfCmfundnav(String finprodMarketId, String sysDate, String newDate) {
        return sysCoreService.selectInterfCmfundnav(finprodMarketId, sysDate, newDate);
    }

    /**
     * 查询是否存在场外行情信息-基金加券(INTERF_F_MARKET_INFO)
     *
     * @param finprodMarketId
     * @return
     */
    @GetMapping("selectMstFundMarketInfoCount")
    public List<Integer> selectMstFundMarketInfoCount(String finprodMarketId) {
        return sysCoreService.selectMstFundMarketInfoCount(finprodMarketId);
    }

    /**
     * 查询资讯_基金市场行情list-基金加券
     *
     * @param sInfoWindcode
     * @return
     */
    @GetMapping("selectInterfFMarketInfo")
    public List<InterfFMarketInfo> selectInterfFMarketInfo(String finprodMarketId, String sysDate, String newDate) {
        return sysCoreService.selectInterfFMarketInfo(finprodMarketId, sysDate, newDate);
    }

    /**
     * 债券估值行情保存 <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("mstBondValuationInfoSave")
    public void mstBondValuationInfoSave(@RequestBody InterfPayhsrcsecinfoDto dto) {
        sysCoreService.mstBondValuationInfoSave(dto);
    }

    /**
     * 债券市场行情保存(新增) <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("mstBondMarketInfoSave")
    public void mstBondMarketInfoSave(@RequestBody InterfPayhsrcsecinfoDto dto) {
        sysCoreService.mstBondMarketInfoSave(dto);
    }

    /**
     * 回购标准折算率保存(新增) <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("finStdsecConverseRadioSave")
    public void finStdsecConverseRadioSave(@RequestBody InterfPayhsrcsecinfoDto dto) {
        sysCoreService.finStdsecConverseRadioSave(dto);
    }

    /**
     * 基金-场外金融产品保存(新增) <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("finProductValuationSave")
    public void finProductValuationSave(@RequestBody InfChinamutualfunddescriptionDto dto) {
        sysCoreService.finProductValuationSave(dto);
    }

    /**
     * 基金-基金市场行情保存(新增) <功能详细描述>
     *
     * @param dto
     * @see
     * @since 1.0
     */
    @Transactional
    @DTXTransaction
    @PostMapping("mstFundMarketInfoSave")
    public void mstFundMarketInfoSave(@RequestBody InfChinamutualfunddescriptionDto dto) {
        sysCoreService.mstFundMarketInfoSave(dto);
    }

    /**
     * 查询客户信息附属表list-债券中债
     *
     * @param customerid
     * @return
     */
    @GetMapping("selectMstCustomerInfoAdd")
    public List<MstCustomerInfoAdd> selectMstCustomerInfoAdd(String customerid) {
        return sysCoreService.selectMstCustomerInfoAdd(customerid);
    }

    /**
     * 查询资讯_债券评级信息表
     *
     * @param secid
     * @return
     */
    @GetMapping("selectInterfPayhsrcsecrating")
    public List<InterfPayhsrcsecrating> selectInterfPayhsrcsecrating(String secid) {
        return sysCoreService.selectInterfPayhsrcsecrating(secid);
    }

    /**
     * 查询资讯_债券评级中主体评级结果
     *
     * @param secid
     * @param date
     * @return
     */
    @GetMapping("selectRating")
    public List<InterfPayhsrcsecrating> selectRating(String secid, String date) {
        return sysCoreService.selectRating(secid, date);
    }

    // 资产五级分类联动检索 add by renping 2019-07-11 start
    /**
     * 资产五级分类联动数据取得
     * @param param 参数
     * @return 结果
     * @see
     * @since 1.0
     */
    @PostMapping("getAssetFiveType")
    public FinProductTypeConfig getAssetFiveType(@RequestBody FinProductTypeConfig param) {
        return sysCoreService.getAssetFiveType(param);
    }
    // 资产五级分类联动检索 add by renping 2019-07-11 start


    /**
     * 资产是否存在行权交易
     *
     * @param finprodId 金融产品代码
     * @return 交易笔数
     * @see
     * @since 1.0
     */
    @GetMapping("isBondExerciseAsset")
    public int isBondExerciseAsset(String finprodId) {
        return sysCoreService.isBondExerciseAsset(finprodId);
    }
}
