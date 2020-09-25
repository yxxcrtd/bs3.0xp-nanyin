package com.joyintech.tams.invest.product.productduration.addreduce.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.util.StringUtil;
import com.joyintech.framework.ajax.AjaxResult;
import com.joyintech.framework.base.BaseService;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.framework.grid.Page;
import com.joyintech.framework.select.SelectOptionDto;
import com.joyintech.framework.util.RequestUtil;
import com.joyintech.tams.invest.common.dto.period.common.BussTrdTransferOrderDto;
import com.joyintech.tams.constant.DictCodeList;
import com.joyintech.tams.constant.DictCodeListExt2;
import com.joyintech.tams.constant.DictCodeListExt1;
import com.joyintech.tams.invest.common.constant.ServiceApplication;
import com.joyintech.tams.invest.common.dto.product.addreduce.ProductFeeInfo;
import com.joyintech.tams.invest.common.dto.product.addreduce.ReduceInfoDto;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.FinProductAddReduce002Dto;
import com.joyintech.tams.invest.product.productduration.addreduce.dto.ProductFeeInfoDto;

/**
 * @author : 强成西
 * @version V1.0
 * @Project: pingan-parent
 * @Package com.joyintech.tams.invest.product.productduration.addreduce.service
 * @Description: 强增强减service
 * @date Date : 2018年09月07日 9:15
 */
@Service
public class FinProductAddReduce002Service extends BaseService {

    @Transactional(readOnly = true)
    public Page<SelectOptionDto> getProdId() {
        Map<String, Object> param = RequestUtil.getPageParam();
        //add by zhujun @2019-05-16 兼容净值型产品 强增强减
        param.put("profitType", DictCodeList.收益类型_收益型.getValue());
        return restTemplate.postForPage(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/getProdId", param,
            SelectOptionDto.class);
    }

    public AjaxResult getSaleDepartment() {
        String deptName = restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/getSaleDepartment", RequestUtil.getPageParam(),
            String.class);
        AjaxResult result = new AjaxResult();
        result.setData(deptName);
        return result;
    }

    public String getValueByDate() {
        Map<String, Object> param = RequestUtil.getPageParam();
        BigDecimal value = restTemplate.postForObject(
            ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/getValueByDate", param, BigDecimal.class);

        if (value != null) {
            String unitvalueDec = (String)RequestUtil.getPageParam().get("unitvalueDec");
            if (StringUtil.isEmpty(unitvalueDec)) {
                unitvalueDec = "2";
            }
            String unitvalueMode = (String)RequestUtil.getPageParam().get("unitvalueMode");
            if (StringUtil.isEmpty(unitvalueMode)) {
                unitvalueMode = DictCodeList.尾数处理方式_四舍五入.getValue();
            }

            return value.setScale(Integer.parseInt(unitvalueDec), DictCodeList.尾数处理方式_四舍五入.getValue().equals(
                unitvalueMode) ? RoundingMode.HALF_UP : RoundingMode.DOWN).toPlainString();
        }
        return StringUtils.EMPTY;
    }

    public FinProductAddReduce002Dto selectByTotalTradeId(String totalTradeId, String finProdId) {
        Map<String, Object> params = new HashMap<>();
        params.put("totalTradeId", totalTradeId);
        params.put("finProdId", finProdId);
        return restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/selectByTotalTradeId",
            params, FinProductAddReduce002Dto.class);
    }

    public List<ReduceInfoDto> findReduceInfoData() {
        if (StringUtil.isEmpty(RequestUtil.getParam("finprodId")) || StringUtil.isEmpty(RequestUtil.getParam("tradeDate"))
            || StringUtil.isEmpty(RequestUtil.getParam("isLay"))) {
            return new ArrayList<>();
        }
        //add by zlp 20190228 start 判断支付日产品持仓,小于0则不展示到期列表信息
        Map<String, Object> params = new HashMap<>();
        params.put("finprodId", RequestUtil.getParam("finprodId"));
        params.put("tradeDate", RequestUtil.getParam("tradeDate"));
        BigDecimal amt = restTemplate.postForObject(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/getProductAmount", params, BigDecimal.class);
        if(amt!=null&&amt.compareTo(new BigDecimal(0))<=0) {
        	return new ArrayList<>();
        }
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/findReduceInfoData",
            RequestUtil.getPageParam(), ReduceInfoDto.class);
    }

    public List<ProductFeeInfoDto> findProdFeeData() {
        if (StringUtil.isEmpty(RequestUtil.getParam("finprodId")) || StringUtil.isEmpty(RequestUtil.getParam("tradeDate"))
            || StringUtil.isEmpty(RequestUtil.getParam("isLay"))) {
            return new ArrayList<>();
        }
        return restTemplate.postForList(ServiceApplication.PRODUCT_SERVICE_NAME + "/FinProductAddReduce/findProdFeeData",
            RequestUtil.getPageParam(), ProductFeeInfoDto.class);
    }

    public List<BussTrdTransferOrderDto> selectPayOrders(String totalTradeId, String profitType) {
        return restTemplate.postForList(ServiceApplication.SETTLE_SERVICE_NAME + "/BussSettleOrderComm/selectOrdersByBizCode",
            totalTradeId, BussTrdTransferOrderDto.class);
    }
}
