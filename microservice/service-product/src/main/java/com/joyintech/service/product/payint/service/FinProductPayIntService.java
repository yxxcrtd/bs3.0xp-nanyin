/*
 * 文件名：FinProductPayIntService.java
 * 版权：Copyright 2017-2018 JoyinTech. Co. Ltd. All Rights Reserved.
 * 描述：理财资管管理系统V2.0
 * 修改人：etliu
 * 修改时间：2018年12月29日
 * 修改内容：新建
 * 系统名称：理财资管管理系统V2.0
 */

package com.joyintech.service.product.payint.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.joyintech.tams.systemtools.service.SystemToolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.ArithmeticUtil;
import com.joyintech.framework.util.BeanUtil;
import com.joyintech.framework.util.DateUtil;
import com.joyintech.framework.util.StringUtil;
import com.joyintech.framework.util.SysDataUtil;
import com.joyintech.service.product.payint.dao.FinProductPayIntMapper;
import com.joyintech.service.product.payint.dto.FinProductPayIntDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntIntDetailDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntProdBaseInfoDto;
import com.joyintech.service.product.payint.dto.FinProductPayIntShowDto;
import com.joyintech.tams.invest.common.component.base.core.impl.PlatformDateImpl;
import com.joyintech.tams.constant.DictCode;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;

/**
 * 产品中间付息查询微服务Service
 *
 * @author etliu
 * @version 1.0 2018年12月29日
 * @see FinProductPayIntService
 * @since 1.0
 */
@Service("backFinProductPayIntService")
public class FinProductPayIntService extends BaseService<FinProductPayIntDto> {

    /**
     * FinProductPayIntMapper
     */
    @Autowired
    private FinProductPayIntMapper finProductPayIntMapper;

    /**
     * PlatformDateImpl
     */
    @Autowired
    private PlatformDateImpl platformDateImpl;

    @Autowired
    private SystemToolService systemToolService;

    /**
     * 产品代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    public Page<SelectOptionDto> queryProdIdInfo(int page, int length, Map<String, Object> paramMap) {
        paramMap.put("FINPROD_TYPE", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        paramMap.put("PROFIT_TYPE", DictCodeList.收益类型_收益型.getValue());
        return this.queryForPageForSelectReturn(page, length, ()-> finProductPayIntMapper.queryProdIdInfo(paramMap));
    }

    /**
     * 组合代码下拉框
     * @param paramMap 过滤参数
     * @return 下拉框列表
     * @throws Exception
     */
    public Page<SelectOptionDto> selectPortfolioId(int page, int length, Map<String, Object> paramMap) {
        paramMap.put("PROFIT_TYPE", DictCodeList.收益类型_收益型.getValue());
        // 一体化对应etliu start
//        return this.queryForPageForSelectReturn(page, length, ()-> finProductPayIntMapper.queryProdIdInfo(paramMap));
        return this.queryForPageForSelectReturn(page, length, ()-> finProductPayIntMapper.selectPortfolioId(paramMap));
        // 一体化对应etliu end
    }

    /**
     * 查询产品中间付息信息
     *
     * @param page page
     * @param length length
     * @param pageParam 参数
     * @return 产品中间付息信息
     * @throws Exception 异常
     * @see
     * @since 1.0
     */
    public Page<FinProductPayIntDto> selectForPage(int page, int length,
                                                     Map<String, Object> pageParam)
        throws Exception {
        //pageParam.put("VALID_STATUS_01", DictCodeListExt1.画面数据状态_待处理.getValue());
        pageParam.put("VALID_STATUS_05", DictCodeListExt1.画面数据状态_有效.getValue());
        pageParam.put("VALID_STATUS_06", DictCodeListExt1.画面数据状态_撤销.getValue());
        pageParam.put("PS_QJ", DictCodeList.交易方向_份额强减.getValue());
        pageParam.put("PS_ZJSX", DictCodeList.交易方向_中间付息.getValue());
        pageParam.put("YES_NO_N", DictCodeList.是否_否.getValue());
        pageParam.put("YES", DictCodeList.是否_是.getValue());
        pageParam.put("FINPROD_TYPE_LCCP", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
     // 一体化对应etliu start
        pageParam.put("PROFIT_TYPE", DictCodeList.收益类型_收益型.getValue());
        pageParam.put("ISSUE_STATUS_QRFX", DictCodeListExt1.发行状态_确认发行.getValue());
     // 一体化对应etliu end
        pageParam.put("tradeStatus", DictCodeListExt1.交易状态_初始.getValue());
        return this.queryForPage(page, length,
            () -> finProductPayIntMapper.selectPageData(pageParam));
    }

    /**
     * 1.6确认数据取得
     * 产品基本信息数据查询
     * @param paramDto 参数
     * @return 产品基本信息数据
     * @throws Exception
     */
    public FinProductPayIntProdBaseInfoDto selectConfirmProdBaseInfo(FinProductPayIntDto paramDto) {
        FinProductPayIntProdBaseInfoDto resultDto = new FinProductPayIntProdBaseInfoDto();
        // 产品中间付息查询画面点确认按钮的场合
        if (StringUtil.isBlank(paramDto.getBussId())) {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("CASH_TYPE_LX", DictCodeListExt1.现金流二级类型_利息.getValue());
            paramMap.put("payintEndDate", paramDto.getPayintEndDate());
            paramMap.put("finprodId", paramDto.getFinprodId());
            // 一体化对应etliu start
            paramMap.put("branch", paramDto.getBranch());
            // 一体化对应etliu end

            FinProductPayIntProdBaseInfoDto tempDto = finProductPayIntMapper.selectConfirmProdBaseInfo(paramMap);
            if (tempDto != null) {
                resultDto = tempDto;
            }

            BeanUtil.copyPropertiesIgnoreNull(resultDto, paramDto);
            // 本次计息天数:入参.本次付息结束日-入参.本次付息起始日
            resultDto.setThisIntAccrDay(DateUtil.getBetweenDayNumber(paramDto.getPayintStartDate(),
                paramDto.getPayintEndDate()));
            // 支付日:平台日期
            resultDto.setPayDate(platformDateImpl.getSysDate());

            // 一体化对应etliu start
            if (resultDto.getPayCycleVal() != null) {
                resultDto.setPayIntRule(DictCodeListExt1.付息周期_周期支付.getValue());
            }
            // 一体化对应etliu end
        }
        //  产品中间付息查询画面双击查看的场合
        else {
            Map<String, Object> paramMap = new HashMap<String, Object>();
            paramMap.put("CASH_TYPE_LX", DictCodeListExt1.现金流二级类型_利息.getValue());
            paramMap.put("payintEndDate", paramDto.getPayintEndDate());
            paramMap.put("finprodId", paramDto.getFinprodId());
            // 一体化对应etliu start
            paramMap.put("branch", paramDto.getBranch());
            // 一体化对应etliu end

            FinProductPayIntProdBaseInfoDto tempDto = finProductPayIntMapper.selectConfirmProdBaseInfo(paramMap);
            if (tempDto != null) {
                resultDto = tempDto;
            }
        }

        return resultDto;
    }

    /**
     * 查询利息明细信息
     * 1.6.2利息明细信息
     *
     * @param paramDto 参数
     * @return 利息明细信息
     * @throws Exception
     */
    public List<FinProductPayIntIntDetailDto> findIntDetailInfo(FinProductPayIntProdBaseInfoDto paramDto) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("CASH_TYPE_1_LX", DictCodeListExt1.现金流一级类型_利息.getValue());
        paramMap.put("CASH_TYPE_2_LX", DictCodeListExt1.现金流二级类型_利息.getValue());
        paramMap.put("HODING_TYPE_SJ", DictCodeList.持仓类型_实际.getValue());
        paramMap.put("OPERATION_TYPE_FBS", DictCodeList.开放类型_封闭式.getValue());
        paramMap.put("payintEndDate", paramDto.getPayintEndDate());
        paramMap.put("finprodId", paramDto.getFinprodId());

        List<FinProductPayIntIntDetailDto> resultList = new ArrayList<FinProductPayIntIntDetailDto>();

        // 入参.是否分层 为是时；
        if (StringUtil.equals(DictCodeList.是否_是.getValue(), paramDto.getIsLay())) {
            resultList = finProductPayIntMapper.selectLayIntDetailData(paramMap);
        }
        // 入参.是否分层 为否时；
        else {
            resultList = finProductPayIntMapper.selectNotLayIntDetailData(paramMap);
        }

        if (!StringUtil.isEmptyList(resultList)) {
            Map<String, String> valueMap = systemToolService.getDataDictItems(DictCode.销售部门);
            for (FinProductPayIntIntDetailDto resultDto : resultList) {
                // 计息区间为  AA：max（本次付息开始日，子产品起息日）-AB：min（本次付息结束日，子产品到期日）
                Date AA = DateUtil.compareDate(paramDto.getPayintStartDate(),
                    resultDto.getSonProdVdate()) > 0 ? paramDto.getPayintStartDate() : resultDto.getSonProdVdate();
                Date AB = DateUtil.compareDate(paramDto.getPayintEndDate(),
                    resultDto.getSonProdMdate()) < 0 ? paramDto.getPayintEndDate() : resultDto.getSonProdMdate();
                long 计息区间 = DateUtil.getBetweenDayNumber(AA, AB);
                resultDto.setCalcIntPeriod(计息区间);

                // 计息基础
                int 计息基础 = 365;
                if (StringUtil.equals(DictCodeList.计息基础_A_360.getValue(), resultDto.getBasis())) {
                    计息基础 = 360;
                }
                resultDto.setCalcIntBasic(计息基础);

                // 应付利息 = 计息本金*应付收益率%*计息区间/计息基础；
                BigDecimal 应付利息 = ArithmeticUtil.multiply(resultDto.getIntAccrPrinAmt(),
                    resultDto.getTheoryYield(), BigDecimal.valueOf(计息区间));
                应付利息 = ArithmeticUtil.divide(应付利息, 计息基础, 2);
                resultDto.setTheoryInt(应付利息);

                // 实付利息默认等于应付利息
                resultDto.setActInt(应付利息);

                // 实付收益率 = 实付利息*计息基础/计息本金/计息区间
                BigDecimal 实付收益率 = ArithmeticUtil.multiply(resultDto.getActInt(),
                    BigDecimal.valueOf(计息基础));
                实付收益率 = ArithmeticUtil.divide(实付收益率, ArithmeticUtil.multiply(
                    resultDto.getIntAccrPrinAmt(), BigDecimal.valueOf(计息区间)), 2);
                resultDto.setActYield(实付收益率);

                // 应付收益率和实付收益率画面展示乘以100
                if (resultDto.getTheoryYield() != null) {
                    resultDto.setTheoryYield(ArithmeticUtil.multiply(resultDto.getTheoryYield(),
                        BigDecimal.valueOf(100)));
                }
                if (resultDto.getActYield() != null) {
                    resultDto.setActYield(
                        ArithmeticUtil.multiply(resultDto.getActYield(), BigDecimal.valueOf(100)));
                }
                //处理模式
                if(StringUtil.isNotEmpty(resultDto.getProdType())){
                    String prodType = valueMap.get(resultDto.getProdType());
                    resultDto.setProType(prodType);
                }
            }
        }
		return resultList;
	}

	public List<SelectOptionDto> queryProdIdInfoIssueSuc(Map<String, Object> param) {
		param.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
        return finProductPayIntMapper.queryProdIdInfoIssueSuc(param);
	}

//    /**
//     * 查询获取存续期报送信息所需金额信息
//     *
//     * @param paramDto 参数
//     * @return 存续期报送信息所需金额信息
//     * @throws Exception
//     */
//    public RepDuration genRepDurationAmountInfo(FinProductPayIntProdBaseInfoDto paramDto) {
//        RepDuration repDuration = new RepDuration();
//
//        Map<String, Object> paramMap = new HashMap<String, Object>();
//        paramMap.put("finprodId", paramDto.getFinprodId());
//        paramMap.put("payintStartDate", paramDto.getPayintStartDate());
//        paramMap.put("payintEndDate", paramDto.getPayintEndDate());
//
//        // 对应业务区间,以交易方向为维度做分组汇总
//
//        return repDuration;
//    }


	public  FinProductPayIntProdBaseInfoDto queryProdInfoByprodId(Map<String, Object> map) {
	    map.put("finprodTypeProd", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());

		return finProductPayIntMapper.queryProdInfoByprodId(map);
	}


	/**
	 * 收益型产品中间付息双击查看
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	public  FinProductPayIntShowDto selectProdPayInfoByFinprodId(Map<String, Object> map) {
	    map.put("PS_ZJSX", DictCodeList.交易方向_中间付息.getValue());
	    map.put("FINPROD_TYPE_LCCP", DictCodeListExt1.金融产品类型_投管用_理财产品.getValue());
	    map.put("PROFIT_TYPE", DictCodeList.收益类型_收益型.getValue());
	    map.put("BUSI_TYPE",DictCodeList.共通业务类型_收益型产品中间付息管理.getValue());
	    return finProductPayIntMapper.selectProdPayInfoByFinprodId(map);
	}
	/**
	 * 收益型产品中间付息双击查看
	 * <功能详细描述>
	 * @param map
	 * @return
	 * @see
	 * @since 1.0
	 */
	public  List<FinProductPayIntIntDetailDto> selectLayIntDetailDataList(Map<String, Object> map) {
	    return finProductPayIntMapper.selectLayIntDetailDataList(map);
	}

	
}
